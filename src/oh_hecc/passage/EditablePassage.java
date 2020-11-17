package oh_hecc.passage;

import heccCeptions.DuplicatePassageNameException;
import heccCeptions.InvalidMetadataDeclarationException;
import heccCeptions.InvalidPassageNameException;
import oh_hecc.Heccable;
import oh_hecc.Parseable;
import oh_hecc.component_editing_windows.PassageEditorWindow;
import utilities.Vector2D;

import java.util.*;

/**
 * Like Passage but this time it's actually Editable!
 */
public class EditablePassage implements Heccable, Parseable, SharedPassage, PassageEditingInterface, PassageReadingInterface {

    /**
     * The passageUUID of this particular passage.
     * Yes, it's a UUID.
     * There's a stupidly low chance that multiple passages could have identical UUIDs.
     * but, if you somehow have enough passages so that there's a collision with these,
     * chances are that this would be the least of your concerns.
     * Anywho, they're generated dynamically (never saved in .hecc format or in the final heccin game),
     * so I'm not even going to give you the opportunity to set up a collision.
     */
    private final UUID passageUUID;

    /**
     * The name of this passage
     */
    private String passageName;

    /**
     * The actual raw contents of this passage
     */
    private String passageContent;

    /**
     * The comment that's inline with the passage declaration
     */
    private String inlinePassageComment;

    /**
     * The set with the names of the other passages this passage links to
     */
    private Set<String> linkedPassages;

    /**
     * The string that's the multiline comment behind this particular passage
     */
    private String trailingComment;

    /**
     * The list of tags that this passage has
     */
    private List<String> passageTags;

    /**
     * The position of this passage
     */
    private final Vector2D position;



    /**
     * A no-argument constructor for an editable passage. Will be called when the 'new passage' button is pressed.
     */
    public EditablePassage(){
        passageUUID = UUID.randomUUID();
        passageName = "Untitled Passage "+passageUUID;
        passageContent = "Sample Content";
        passageTags = new ArrayList<>();
        inlinePassageComment = "";
        trailingComment = "";
        position = new Vector2D();
        linkedPassages = new TreeSet<>();
    }

    /**
     * Will be called when a user creates a link to a passage which doesn't exist yet, creating a passage with that name
     * @param passageName the name of the passage to create
     * @param parentPosition the position of the parent passage that made this one
     */
    public EditablePassage(String passageName, Vector2D parentPosition){
        this();
        this.passageName = passageName;
        this.position.set(parentPosition.add(0, 100)); //100 below parent passage
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
        this.passageName = passageName;
        passageContent = unparsedContent;
        trailingComment = comment;
        passageTags = PassageReadingInterface.readTagMetadata(lineEndMetadata);
        position.set(PassageReadingInterface.readVectorMetadata(lineEndMetadata));
        inlinePassageComment = PassageReadingInterface.getInlineComment(lineEndMetadata);
        linkedPassages = SharedPassage.findLinks(unparsedContent);
    }


    /**
     * This method will be used when the passage content needs to be updated indirectly.
     * Replaces the passageContent, and updates the linkedPassages appropriately
     * @param newContent the new content that the passage now holds
     */
    @Override
    public void setPassageContent(String newContent){
        passageContent = newContent; //replaces the content
        linkedPassages = SharedPassage.findLinks(newContent); //updates the linked passages appropriately
    }


    /**
     * This method will be used when the passage content needs to be updated directly.
     * Replaces the passageContent, and updates the linkedPassages appropriately
     * @param newContent the new content that the passage now holds
     * @param allPassages the map of all passages (just in case any new passages need to be added to the map)
     */
    @Override
    public Map<UUID, PassageEditingInterface> updatePassageContent(String newContent, Map<UUID, PassageEditingInterface> allPassages){
        this.setPassageContent(newContent);

        for(String s: linkedPassages){
            boolean doesntExist = true;
            for (PassageEditingInterface e: allPassages.values()) {
                if (e.getPassageName().equals(s)){
                    doesntExist = false;
                    break;
                }
            }
            if (doesntExist){
                PassageEditingInterface newChild = new EditablePassage(s,this.getPosition());
                allPassages.put(newChild.getPassageUUID(),newChild);
            }
        }
        return allPassages;
    }

    /**
     * This method will be used when attempting to rename this passage.
     * @param newName the new name that the user is trying to give this passage.
     * @param allPassageNames the set of all passage names
     * @return the previous name of this passage (before it got renamed)
     * @throws InvalidPassageNameException if the passage name isn't a valid passage name
     * @throws DuplicatePassageNameException if there's already a passage with this name which exists
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
     * @param newName the new name that the user is trying to give this passage.
     * @param allPassages the map of all passages.
     *        Any passages with links to this passage will have their links updated to point to the new name of this passage,
     *        assuming that this passage is successfully renamed.
     * @return the updated copy of allPassages
     * @throws InvalidPassageNameException if the passage name isn't a valid passage name
     * @throws DuplicatePassageNameException if there's already a passage with this name which exists
     */
    @Override
    public Map<UUID, ? extends PassageEditingInterface> renameThisPassage(String newName, Map<UUID, ? extends PassageEditingInterface> allPassages) throws InvalidPassageNameException, DuplicatePassageNameException{
        String oldName = passageName; //backup of old name
        String trimmedValidatedName = Parseable.validatePassageNameRegex(newName); //validates the format of the new name

        //checks to see if this isn't a duplicate passage name
        for (PassageEditingInterface e: allPassages.values()) {
            if (e.getPassageName().equals(trimmedValidatedName)){
                throw new DuplicatePassageNameException(trimmedValidatedName);
            }
        }

        //updates this passage name
        passageName = trimmedValidatedName;

        //updates all passages that link to this passage so they link to its new name
        for (PassageEditingInterface e: allPassages.values()) {
            if (e.getLinkedPassages().contains(oldName)){
                e.setPassageContent(PassageEditingInterface.getPassageContentWithRenamedLinks(e.getPassageContent(),oldName,trimmedValidatedName));
            }
        }

        //returns the modified map of all passages
        return allPassages;

    }

    /**
     * This method is responsible for deleting this passage (and removing it from the map of all passages)
     * @param allPassages The map of all passages
     * @return a version of the map of all passages with this passage completely removed
     */
    @Override
    public Map<UUID, ? extends PassageEditingInterface> deleteThisPassage(Map<UUID, ? extends PassageEditingInterface> allPassages){

        //removes this passage from the map of all passages
        allPassages.remove(this.getPassageUUID());

        //deletes all traces of this passage from the existing passages
        for (PassageEditingInterface e: allPassages.values()){
            if (e.getLinkedPassages().contains(this.passageName)){
                String deletedName = this.passageName + " !WAS DELETED!";
                e.setPassageContent(PassageEditingInterface.getPassageContentWithRenamedLinks(e.getPassageContent(),this.passageName,deletedName));//, allPassages);
                e.removeLinkedPassage(deletedName);
            }
        }

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
     * Obtains the passage content
     * @return passage content
     */
    @Override
    public String getPassageContent(){
        return this.passageContent;
    }

    /**
     * Obtains the passage name
     * @return passage name
     */
    @Override
    public String getPassageName(){
        return this.passageName;
    }

    /**
     * Obtains the list of passage tags
     * @return passageTags
     */
    @Override
    public List<String> getPassageTags(){
        return passageTags;
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
        this.passageTags = PassageEditingInterface.makePassageTagListFromString(newPassageTagListString);
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
     * gets the set of linked passages
     * @return linkedPassages
     */
    @Override
    public Set<String> getLinkedPassages(){
        return linkedPassages;
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
     * @param newComment the new trailing comment for the passage
     */
    @Override
    public void setTrailingComment(String newComment){
        trailingComment = newComment;
    }

    /**
     * Method that can be used to remove a passage name from another passage's linked passages
     * @param passageToRemove the passage to remove
     * @return the passage that has been removed
     */
    @Override
    public boolean removeLinkedPassage(String passageToRemove){
        return linkedPassages.remove(passageToRemove);
    }

    /**
     * Basically, it's the static {openEditorWindow method but as an instance method instead.
     * @param allPassages the map of all passages
     * @return a {@link PassageEditorWindow} allowing a user to edit this passage.
     */
    @Override
    public PassageEditorWindow openEditorWindow(Map<UUID, PassageEditingInterface> allPassages){
        return PassageEditingInterface.openEditorWindow(this,allPassages);
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
        //end with a newline, and then return the string
        heccBuilder.append("\n");
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
     * equals method. bottom text.
     * @param obj the thing that this is being compared to.
     *            If obj is an EditablePassage, this returns true if the passageUUID of this and obj are equal.
     *            If obj is a String, this returns true if the passageName of this is the same as the string obj.
     *            Otherwise, imma just ignore what obj is and return false anyway
     * @return true if it's equal, false otherwise
     */
    public boolean equals(Object obj){
        if (EditablePassage.class.equals(obj.getClass())) {
            return (this.passageUUID.compareTo(((SharedPassage) obj).getPassageUUID()) == 0);
        } else if (String.class.equals(obj.getClass())) {
            return (this.passageName.equals(obj));
        }
        return false;
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
        sb.append("\nEND PASSAGE DATA");
        return sb.toString();
    }

}
