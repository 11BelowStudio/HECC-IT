package oh_hecc.game_parts.passage;

import heccCeptions.DuplicatePassageNameException;
import heccCeptions.InvalidMetadataDeclarationException;
import heccCeptions.InvalidPassageNameException;
import oh_hecc.Parseable;
import utilities.Vector2D;

import java.util.*;
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
     * The comment that's inline with the passage declaration
     */
    private String inlinePassageComment;


    /**
     * The set of UUIDs of the passages that this passage is linked to
     */
    private final Set<UUID> linkedUUIDs;

    /**
     * The string that's the multiline comment behind this particular passage
     */
    private String trailingComment;

    /**
     * The position of this passage
     */
    private final Vector2D position;



    /**
     * A no-argument constructor for an editable passage. Only called by the other constructors of this class.
     * Will be given a random UUID as a passageUUID, will be called 'Untitled Passage {that UUID}', and will have
     * content consisting of "Sample Content".
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
        setPassageContent(unparsedContent);
        trailingComment = comment;

        // making sure we stop the metadata at the first newline
        final String[] quickAndDirtyLineEndFix = lineEndMetadata.split("\\R",2);
        lineEndMetadata = quickAndDirtyLineEndFix[0];

        // now parsing the metadata
        passageTags.addAll(PassageReadingInterface.readTagMetadata(lineEndMetadata));
        position.set(PassageReadingInterface.readVectorMetadata(lineEndMetadata));
        inlinePassageComment = PassageReadingInterface.getInlineComment(lineEndMetadata);

        updatePassageStatus();
    }

    /**
     * Used by the EditablePassageTest class (no metadata)
     * @param passageName the name for this passage
     * @param content the raw content of this passage
     */
    EditablePassage(String passageName, String content){
        this(passageName,content,"","");
    }




    /**
     * This method will be used when the passage content needs to be updated indirectly.
     * Replaces the passageContent, and updates the linkedPassages appropriately
     * @param newContent the new content that the passage now holds
     */
    @Override
    public void setPassageContent(String newContent) {

        final String content = sanitizeContent(newContent); //sanitization.

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
    @SuppressWarnings("RegExpAnonymousGroup")
    private String sanitizeContent(String content) {

        /*
        why are there 4 \s? Simple.
            One \ is needed to be rendered before the ;; or ::.
            Another one needs to escape that \ in the produced string.
            Both of these need another \ before each of them to work in the replacement string anyway
         */

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
    @Override
    void updatePassageStatus() {
        if (this.getPassageContent().trim().isEmpty()) {
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
    @Override
    public Map<UUID, PassageEditingInterface> updatePassageContent(
            String newContent,
            Map<UUID, PassageEditingInterface> allPassages
    ){
        this.setPassageContent(newContent);

        //gets all the linked passage names that aren't the names of passages that are in allPassage


        return this.resolvePassageLinks(allPassages);
    }

    /**
     * Basically handles adding passages to the list of all passages if this passage's content has links to passages
     * which don't actually exist
     * @param allPassages the map of all passages
     * @return the map of all passages, updated if necessary (for any links to undeclared passages)
     */
    public Map<UUID, PassageEditingInterface> resolvePassageLinks(Map<UUID, PassageEditingInterface> allPassages){
        //gets all the linked passage names that aren't the names of passages that are in allPassages

        linkedPassages.stream()
                .filter(
                        // we find out what passage names in the linked passages set refer to passages that don't exist.
                        s -> allPassages.values().stream().noneMatch(
                                p -> p.getPassageName().equals(s)
                        )
                )
                .forEach(
                        //then, for all of those not-yet-present passages, they're made and added to the map of all passages.
                        s ->
                        {
                            final PassageEditingInterface p = new EditablePassage(s, this.position);
                            allPassages.put(p.getPassageUUID(),p);
                        }
                );

        updateLinkedUUIDs(allPassages.values());

        return allPassages;

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
    public Map<UUID, PassageEditingInterface> renameThisPassage(
            String newName,
            Map<UUID, PassageEditingInterface> allPassages
    ) throws InvalidPassageNameException, DuplicatePassageNameException {
        final String oldName = passageName; //backup of old name

        final String trimmedName = newName.trim(); // obtains trimmed version of name
        final String validatedName = Parseable.validatePassageNameRegex(trimmedName); //validates the format of the new name

        if (!trimmedName.equals(validatedName)){ // complain if the full trimmed new name isn't valid
            throw new InvalidPassageNameException(trimmedName);
        }

        //checks to see if the new passage name isn't a duplicate of an existing passage name
        if (allPassages.values().stream()
                .anyMatch(
                        p -> p.getPassageName().equals(validatedName)
                )
        ) {
            throw new DuplicatePassageNameException(validatedName);
        }

        //updates this passage name
        passageName = validatedName;

        //updates all passages that link to this passage so they link to its new name
        allPassages.values().stream()
            .filter(
                    //finding the passage s that link to this one
                p -> p.getLinkedPassageUUIDs().contains(passageUUID)
            ).forEach(
                    //and updating their content
                p -> p.setPassageContent(
                        PassageEditingInterface.getPassageContentWithRenamedLinks(p.getPassageContent(),oldName,validatedName)
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
        final String deletedName = this.passageName + SharedPassage.DELETED_PASSAGE_NAME_PLACEHOLDER_SUFFIX;

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
        final StringBuilder tagBuilder = new StringBuilder();
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
        inlinePassageComment = newComment.replaceAll("\\R", " ");
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
        trailingComment = sanitizeContent(newComment);
    }

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
        final boolean a = linkedPassages.remove(removeThisPassageName);
        final boolean b = linkedUUIDs.remove(removeThisPassageUUID);
        return (a && b);
    }


    /**
     * Method that'll be used to update the set containing the UUIDs of all the passages that this passage is linked to.
     * call this for each element in the set of (? extends SharedPassages) <b>after</b> everything's been added to it.
     * @param allPassages the collection of all passages mapped to UUIDs (where the UUIDs will be read from basically)
     */
    @Override
    public void updateLinkedUUIDs(Collection<? extends SharedPassage> allPassages) {
        //clears existing list of linkedUUIDs
        linkedUUIDs.clear();
        //add the UUIDs of all the named linked passages to the set of linked UUIDs.
        allPassages.stream()
        .filter(
                p -> this.linkedPassages.contains(p.getPassageName())
        )
        .forEach(
                p -> linkedUUIDs.add(p.getPassageUUID())
        );
    }


    /**
     * This obtains a version of this passage in .hecc format
     * @return this passage but in HECC format
     */
    @Override
    public String toHecc() {
        //Creating passage declaration
        //end with a newline, and then return the string
        //heccBuilder.append("\n");

        return "::" +
                passageName +
                " " +
                //and now, the passage tags
                getHeccPassageTags(passageTags) +
                " " +
                //and now, the position vector
                getHeccPosition(position) +
                " " +
                //and now, the inline comment
                "//" +
                inlinePassageComment +
                "\n" +
                //and now, the content
                passageContent +
                //and finally, the trailing comment
                "\n;;\n" +
                trailingComment +
                "\n;;" +
                //and we end with a newline
                "\n";
    }

    /**
     * Obtains the passage tags in .hecc-compatiable format
     * @param passageTags the List of passageTags
     * @return the list of passage tags as a string in the format [list of tags]
     */
    private static String getHeccPassageTags(List<String> passageTags){
        final StringBuilder passageTagBuilder = new StringBuilder();
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
     * @return a string version of the Vector2D, in the format {@literal <x,y>}, with x and y rounded to nearest int.
     */
    private static String getHeccPosition(Vector2D position){
        return "<" +
                Math.round(position.x) +
                "," +
                Math.round(position.y) +
                ">";
    }


    /**
     * Outputs this passage as a string. For debugging reasons.
     * toHecc() isn't really applicable in this situation, as that's just
     * an abstraction of the full details of this particular object.
     * @return a string version of this passage.
     */
    @Override
    public String getAsStringForDebuggingReasons(){
        final StringBuilder sb = new StringBuilder();
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
