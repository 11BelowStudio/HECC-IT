package oh_hecc.game_parts.passage;

import heccCeptions.DuplicatePassageNameException;
import heccCeptions.InvalidMetadataDeclarationException;
import heccCeptions.InvalidPassageNameException;
import oh_hecc.Parseable;
import utilities.Vector2D;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Like Passage but this time it's actually Editable!
 */
public class EditablePassage extends AbstractPassage implements PassageEditingInterface, PassageReadingInterface, SharedPassage {

    /**
     * The passageUUID of this particular passage.
     * Yes, it's a UUID.
     * There's a stupidly low chance that multiple passages could have identical UUIDs.
     * but, if you somehow have enough passages so that there's a collision with these,
     * chances are that this would be the least of your concerns.
     * Anywho, they're generated dynamically (never saved in .hecc format or in the final heccin game),
     * so I'm not even going to give you the opportunity to set up a collision.
     */
    final UUID passageUUID;

    /**
     * The name of this passage
     */
    //private String passageName;

    /**
     * The actual raw contents of this passage
     */
    //private String passageContent;

    /**
     * The comment that's inline with the passage declaration
     */
    private String inlinePassageComment;

    /**
     * The set with the names of the other passages this passage links to
     */
    //private final Set<String> linkedPassages;

    /**
     * The set of UUIDs of the passages that this passage is linked to
     */
    private final Set<UUID> linkedUUIDs;

    /**
     * The string that's the multiline comment behind this particular passage
     */
    private String trailingComment;

    /**
     * The list of tags that this passage has
     */
    //private final List<String> passageTags;

    /**
     * The position of this passage
     */
    private final Vector2D position;

    /**
     * Something to let the user know about the status of this passage.
     */
    //private PassageStatus status;



    /**
     * A no-argument constructor for an editable passage. Only called by the other constructors of this class
     */
    EditablePassage(){
        super();
        passageUUID = UUID.randomUUID();
        passageName = "Untitled Passage "+passageUUID;
        passageContent = "Sample Content";
        //passageTags = new ArrayList<>();
        inlinePassageComment = "";
        trailingComment = "";
        position = new Vector2D();
        //linkedPassages = new HashSet<>();

        linkedUUIDs = new HashSet<>();
        updatePassageStatus();
    }

    /**
     * Will be called by OH-HECC when pressing the new passage button.
     * @param position the position for this passage.
     */
    public EditablePassage(Vector2D position){
        this();
        this.position.set(position);
        updatePassageStatus();
    }

    /**
     * Will be called by OH-HECC when making the first passage in a new heccin game
     * @param passageName the name for this passage
     */
    public EditablePassage(String passageName){
        this();
        this.passageName = passageName.trim();
        updatePassageStatus();
    }

    /**
     * Will be called when a user creates a link to a passage which doesn't exist yet, creating a passage with that name
     * @param passageName the name of the passage to create
     * @param parentPosition the position of the parent passage that made this one
     */
    public EditablePassage(String passageName, Vector2D parentPosition){
        this();
        this.passageName = passageName.trim();
        this.position.set(Vector2D.add(parentPosition,0,100)); //100 below parent passage
        updatePassageStatus();
    }

    /**
     * A constructor for an EditablePassage object read from a .HECC file
     * @param passageName the name given to this passage
     * @param unparsedContent the raw content of this passage
     * @param comment the trailing comment for this passage
     * @param lineEndMetadata the metadata at the end of the declaration line
     */
    public EditablePassage(String passageName, String unparsedContent, String comment, String lineEndMetadata){
        this();
        this.passageName = passageName.trim();
        passageContent = unparsedContent;
        trailingComment = comment;
        passageTags.addAll(PassageReadingInterface.readTagMetadata(lineEndMetadata));
        position.set(PassageReadingInterface.readVectorMetadata(lineEndMetadata));
        inlinePassageComment = PassageReadingInterface.getInlineComment(lineEndMetadata);
        linkedPassages.addAll(SharedPassage.findLinks(unparsedContent));

        updatePassageStatus();
    }




    /**
     * This method will be used when the passage content needs to be updated indirectly.
     * Replaces the passageContent, and updates the linkedPassages appropriately
     * @param newContent the new content that the passage now holds
     */
    @Override
    public void setPassageContent(String newContent) {

        String content = sanitizeContent(newContent); //sanitization.

        passageContent = content; //replaces the content
        linkedPassages.clear();
        linkedPassages.addAll(SharedPassage.findLinks(content)); //updates the linked passages appropriately

        updatePassageStatus();
    }

    /**
     * Santizes content by escaping protected HECC characters from it (lines starting with ;; or ::)
     *
     * @param content the content to be sanitized
     * @return the content but sanitized.
     */
    private String sanitizeContent(String content) {
        content = content.replaceAll("(?m)(^;;)", "\\\\;;");
        content = content.replaceAll("(?m)(^::)", "\\\\::");
        return content;
    }

    /**
     * This method is used to update the 'status' of this passage.
     * If the passage contents are empty, status is EMPTY_CONTENT
     * If the passage contents (still) contains a reference to a deleted passage, this is a 'DELETED_LINK' passage.
     * If the passage contains no links to other notes, this passage is an END_NODE
     * otherwise, it's NORMAL.
     */
    void updatePassageStatus() {
        if (this.getPassageContent().equals("")) {
            status = PassageStatus.EMPTY_CONTENT;
        } else if(SharedPassage.doesPassageContentContainDeletedLinks(passageContent)){
            status = PassageStatus.DELETED_LINK;
        } else if (linkedPassages.isEmpty()){
            status = PassageStatus.END_NODE;
        } else{
            status = PassageStatus.NORMAL;
        }
    }


    /**
     * This method will be used when the passage content needs to be updated directly.
     * Replaces the passageContent, and updates the linkedPassages appropriately
     * @param newContent the new content that the passage now holds
     * @param allPassages the map of all passages (just in case any new passages need to be added to the map)
     */
    public Map<UUID, PassageEditingInterface> updatePassageContent(String newContent, Map<UUID, PassageEditingInterface> allPassages){ //<T extends PassageEditingInterface>
        this.setPassageContent(newContent);

        //gets all the linked passage names that aren't the names of passages that are in allPassages
        linkedPassages.stream()
                .filter(
                    s -> allPassages.values().stream().noneMatch( p -> p.getPassageName().equals(s))
                )
                .forEach(
                //then, for all of those not-yet-present passages, they're made and added to the map of all passages.
                    s ->
                    {
                        PassageEditingInterface p = new EditablePassage(s, this.position);
                        allPassages.put(p.getPassageUUID(),p);
                    }
        );

        updateLinkedUUIDs(allPassages);

        return allPassages;
    }



    /**
     * This method will be used when attempting to rename this passage.
     * @param newName the new name that the user is trying to give this passage.
     * @param allPassageNames the set of all passage names
     * @return the previous name of this passage (before it got renamed)
     * @throws InvalidPassageNameException if the passage name isn't a valid passage name
     * @throws DuplicatePassageNameException if there's already a passage with this name which exists
     * @deprecated
     */
    @Override
    public String renameThisPassage(String newName, Set<String> allPassageNames) throws InvalidPassageNameException, DuplicatePassageNameException {
        String oldName = passageName; //backup of old name
        String trimmedValidatedName = Parseable.validatePassageNameRegex(newName);

        if (allPassageNames.contains(trimmedValidatedName)){
            throw new DuplicatePassageNameException(trimmedValidatedName); //complain if passage with newName exists already
        }
        passageName = trimmedValidatedName; //updates the passage name
        return oldName; //returns old name
    }


    /**
     * This method will be used when attempting to rename this passage.
     *
     * @param newName     the new name that the user is trying to give this passage.
     * @param allPassages the map of all passages.
     *                    Any passages with links to this passage will have their links updated to point to the new name of this passage,
     *                    assuming that this passage is successfully renamed.
     * @return the updated copy of allPassages
     * @throws InvalidPassageNameException   if the passage name isn't a valid passage name
     * @throws DuplicatePassageNameException if there's already a passage with this name which exists
     */
    @Override
    public Map<UUID, PassageEditingInterface> renameThisPassage(String newName, Map<UUID, PassageEditingInterface> allPassages)
            throws InvalidPassageNameException, DuplicatePassageNameException {
        String oldName = passageName; //backup of old name
        String trimmedValidatedName = Parseable.validatePassageNameRegex(newName); //validates the format of the new name

        //checks to see if the new passage name isn't a duplicate of an existing passage name
        if (allPassages.values().stream()
                .anyMatch(
                        p -> p.getPassageName().equals(trimmedValidatedName)
                )
        ) {
            throw new DuplicatePassageNameException(trimmedValidatedName);
        }

        //updates this passage name
        passageName = trimmedValidatedName;

        //updates all passages that link to this passage so they link to its new name
        allPassages.values().stream()
            .filter(
                    //finding the passage s that link to this one
                p -> p.getLinkedPassageUUIDs().contains(passageUUID)
            )
            .forEach(
                    //and updating their content
                p -> p.setPassageContent(
                        PassageEditingInterface.getPassageContentWithRenamedLinks(p.getPassageContent(),oldName,trimmedValidatedName)
                )
        );


        //returns the modified map of all passages
        return allPassages;

    }

    /**
     * This method is responsible for deleting this passage (and removing it from the map of all passages)
     * @param allPassages The map of all passages
     * @return a version of the map of all passages with this passage completely removed
     */
    @Override
    public Map<UUID, PassageEditingInterface> deleteThisPassage(Map<UUID, PassageEditingInterface> allPassages){

        //removes this passage from the map of all passages
        allPassages.remove(this.getPassageUUID());

        //makes a version of this passage's name with the deleted passage name placeholder suffix
        String deletedName = this.passageName + SharedPassage.DELETED_PASSAGE_NAME_PLACEHOLDER_SUFFIX;

        //and now proceeds to remove all the links to this passage from the passages that link to it
        allPassages.values().stream()
            .filter(
                    //finding the passages that link to this one
                p -> p.getLinkedPassageUUIDs().contains(this.passageUUID)
            )
            .forEach(
                    //removing their links to this passage
                p-> p.setPassageContent(
                    PassageEditingInterface.getPassageContentWithRenamedLinks(
                        p.getPassageContent(),
                        this.passageName,
                        deletedName
                    )
                )
        );

        //return the map of all passages (except this one)
        return allPassages;
    }


    /**
     * Obtains the passage UUID (read-only)
     * @return the identifier for this passage
     */
    @Override
    public UUID getPassageUUID(){
        return this.passageUUID;
    }



    /**
     * Obtains the list of passage tags as a string, seperated by spaces, but without the opening/closing []
     * @return the string version of the passage tags (divided by spaces, no opening/closing [])
     */
    @Override
    public String getPassageTagsAsString(){
        StringBuilder tagBuilder = new StringBuilder();
        for (String tag: passageTags) {
            tagBuilder.append(tag);
            if (passageTags.iterator().hasNext()){
                tagBuilder.append(" ");
            }
        }
        return tagBuilder.toString();
    }

    /**
     * Updates the passage tag list, from a string passage tag list (word characters only, space delimited)
     * @param newPassageTagListString the space-delimited new passage tag list string that will be used to update the passageTags of this passage
     * @return true if this is carried out successfully
     * @throws InvalidMetadataDeclarationException if the given passage tag list was invalid
     */
    @Override
    public boolean updatePassageTags(String newPassageTagListString) throws InvalidMetadataDeclarationException{
        passageTags.clear();
        passageTags.addAll(PassageEditingInterface.makePassageTagListFromString(newPassageTagListString));
        return true;
    }

    /**
     * Removes a specified tag from the passageTags
     * @param tagToRemove the tag that needs to be removed
     * @return whether or not it was successful
     */
    @Override
    public boolean removePassageTag(String tagToRemove){
        return passageTags.remove(tagToRemove);
    }

    /**
     * Add a new specified tag to the passageTags
     * @param tagToAdd the tag to add
     * @return whether or not it was successfullly added
     */
    @Override
    public boolean addPassageTag(String tagToAdd){
        return passageTags.add(tagToAdd);
    }

    /**
     * Updates the position of this Passage
     * @param newPosition the new position of this passage
     */
    @Override
    public void updatePosition(Vector2D newPosition){
        position.set(newPosition);
    }

    /**
     * Gets the position of this Passage
     * @return the position Vector2D
     */
    @Override
    public Vector2D getPosition(){
        return position;
    }


    /**
     * Gets the set of linked passage UUIDs
     * @return the set of the linked passage UUIDs
     */
    public Set<UUID> getLinkedPassageUUIDs(){
        return linkedUUIDs;
    }

    /**
     * returns the comment that's inline within the passage declaration
     * @return the passage declaration comment
     */
    @Override
    public String getInlinePassageComment(){
        return inlinePassageComment;
    }

    /**
     * Updates the inline passage declaration comment
     * @param newComment the new comment that goes in there
     */
    @Override
    public void setInlinePassageComment(String newComment){
        inlinePassageComment = newComment;
    }

    /**
     * obtains the trailing comment from the passage
     * @return the trailing comment
     */
    @Override
    public String getTrailingComment(){
        return trailingComment;
    }

    /**
     * updates the trailing passage comment
     *
     * @param newComment the new trailing comment for the passage
     */
    @Override
    public void setTrailingComment(String newComment) {
        trailingComment = sanitizeContent(newComment); }

    /**
     * Is this passage a 'point of no return'?
     * (it's a point of no return if it has a 'noreturn' tag)
     *
     * @return true if yes, false if not.
     */
    @Override
    public boolean isThisAPointOfNoReturn() {
        return passageTags.contains("noreturn");
    }

    /**
     * Work out whether or not this passage is valid, via the 'isValid()' method of this passage's PassageStatus enum.
     *
     * @return true if this passage is valid, false if not.
     */
    @Override
    public boolean isThisValid() {
        return getPassageStatus().isValid();
    }

    /**
     * Method that can be used to remove a passage from another passage's linked passages
     *
     * @param removeThisPassageName the passage to remove
     * @param removeThisPassageUUID the UUID of the passage to remove
     * @return true if it got yote
     */
    @Override
    public boolean removeLinkedPassage(String removeThisPassageName, UUID removeThisPassageUUID) {
        boolean a = linkedPassages.remove(removeThisPassageName);
        boolean b = linkedUUIDs.remove(removeThisPassageUUID);
        return (a && b);
    }


    @Override
    public void updateLinkedUUIDs(Map<UUID, ? extends SharedPassage> allPassages){
        //clears existing list of linkedUUIDs
        linkedUUIDs.clear();

        //add the UUIDs of all the named linked passages to the set of linked UUIDs.
        allPassages.values().stream()
                .filter(
                    p -> this.linkedPassages.contains(p.getPassageName())
                )
                .limit(linkedPassages.size())
                .forEach(
                    p -> linkedUUIDs.add(p.getPassageUUID())
        );

        /*
        //if the set of linkedPassages isn't empty
        if (!linkedPassages.isEmpty()) {
            //copies linkedPassages into a new set (for efficiency later on)
            Set<String> setToCheck = new HashSet<>(linkedPassages);
            for (SharedPassage e : allPassages.values()) {

                boolean foundString = false; //haven't found it yet

                //then, for every string in the set of passage names to find
                for (String s : setToCheck) {
                    //we see if that string is what the name of this passage is
                    if (e.getPassageName().equals(s)) {
                        //if so, foundString is true, and we add the passage's UUID to this passage's linkedUUIDs
                        foundString = true;
                        linkedUUIDs.add(e.getPassageUUID());
                        break;
                    }
                }
                //if we found the passage
                if (foundString) {
                    //we remove the name of that passage from the setToCheck (so we don't waste more time on it)
                    setToCheck.remove(e.getPassageName());
                    if (setToCheck.isEmpty()) {
                        break; //we stop if there's no more to find
                    }
                }
            }
        }

         */
    }


    /**
     * This obtains a version of this passage in .hecc format
     * @return this passage but in HECC format
     */
    @Override
    public String toHecc(){
        StringBuilder heccBuilder = new StringBuilder();
        //Creating passage declaration
        heccBuilder.append("::");
        heccBuilder.append(passageName);
        heccBuilder.append(" ");
        //and now, the passage tags
        heccBuilder.append(getHeccPassageTags(passageTags));
        heccBuilder.append(" ");
        //and now, the position vector
        heccBuilder.append(getHeccPosition(position));
        heccBuilder.append(" ");
        //and now, the inline comment
        heccBuilder.append("//");
        heccBuilder.append(inlinePassageComment);
        heccBuilder.append("\n");
        //and now, the content
        heccBuilder.append(passageContent);
        //and finally, the trailing comment
        heccBuilder.append("\n;;\n");
        heccBuilder.append(trailingComment);
        heccBuilder.append("\n;;\n");
        //end with a newline, and then return the string
        //heccBuilder.append("\n");
        return heccBuilder.toString();
    }

    /**
     * Obtains the passage tags in .hecc-compatiable format
     * @param passageTags the List of passageTags
     * @return the list of passage tags as a string in the format [list of tags]
     */
    private static String getHeccPassageTags(List<String> passageTags){
        StringBuilder passageTagBuilder = new StringBuilder();
        passageTagBuilder.append("[");
        for(int i = 0; i < passageTags.size(); i++){
            passageTagBuilder.append(passageTags.get(i));
            if (i < passageTags.size()-1){
                passageTagBuilder.append(" ");
            }
        }
        passageTagBuilder.append("]");
        return passageTagBuilder.toString();
    }

    /**
     * Obtains the position Vector2D in .hecc format
     * @param position the vector2D to be made into .hecc
     * @return a string version of the Vector2D, in the format < x,y> (except without that space)
     */
    private static String getHeccPosition(Vector2D position){
        StringBuilder posBuilder = new StringBuilder();
        posBuilder.append("<");
        posBuilder.append(position.x);
        posBuilder.append(",");
        posBuilder.append(position.y);
        posBuilder.append(">");
        return posBuilder.toString();
    }


    /**
     * Outputs this passage as a string. For debugging reasons.
     * toHecc() isn't really applicable in this situation, as that's just
     * an abstraction of the full details of this particular object.
     * @return a string version of this passage.
     */
    @Override
    public String outputAsStringForDebuggingReasons(){
        StringBuilder sb = new StringBuilder();
        sb.append("UUID: ");
        sb.append(passageUUID.toString());
        sb.append("\npassageName: ");
        sb.append(passageName);
        sb.append("\npassageTags: ");
        sb.append(getHeccPassageTags(passageTags));
        sb.append("\nposition: ");
        sb.append(position);
        sb.append("\ninline comment: ");
        sb.append(inlinePassageComment);
        sb.append("\npassageContent:\n");
        sb.append(passageContent);
        sb.append("\ntrailing comment:\n");
        sb.append(trailingComment);
        sb.append("\nlinked passages: ");
        for (String s: linkedPassages) {
            sb.append(s);
            sb.append(", ");
        }
        sb.append("\nlinked passage UUIDs: ");
        for (UUID u: linkedUUIDs) {
            sb.append(u);
            sb.append(", ");
        }
        sb.append("\nEND PASSAGE DATA");
        return sb.toString();
    }

}
