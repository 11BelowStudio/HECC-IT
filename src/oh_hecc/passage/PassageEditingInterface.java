package oh_hecc.passage;

import heccCeptions.DuplicatePassageNameException;
import heccCeptions.InvalidMetadataDeclarationException;
import heccCeptions.InvalidPassageNameException;
import oh_hecc.component_editing_windows.PassageEditorWindow;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface PassageEditingInterface extends SharedPassage {



    /**
     * Called to update the tag list via the OH-HECC editor
     * @param tagListString the string list of tags which are to be given to this (space delimited)
     * @return a String ArrayList formed from the string tag list. Empty arrayList will be returned if empty string provided as input.
     * @throws InvalidMetadataDeclarationException if the string tag list was invalid
     */
    static ArrayList<String> makePassageTagListFromString(String tagListString) throws InvalidMetadataDeclarationException {
        if (tagListString.equals("")) { return new ArrayList<>(); } //returns empty tag list if input is empty

        //String validListString = ""; //this will be overwritten with the valid tag list if it exists within the tagListString
        Matcher validListMatcher = Pattern.compile(TAG_STRING_REGEX).matcher(tagListString); //attempts to find valid list
        if (validListMatcher.find()){ //if valid list was found
            String validListString = validListMatcher.group(0).trim(); //takes note of the valid list if it exists (and trims it)
            return SharedPassage.actuallyPutValidTagListStringIntoArrayList(validListString); //puts it into an ArrayList
        } else {
            throw new InvalidMetadataDeclarationException(tagListString, "passage tag list metadata");
        }
    }


    /**
     * This will update the given rawContent such that all links with the old passage name will be updated to link to the new passage name
     * @param rawContent the raw passage content that needs updating
     * @param oldPassageName the old passage name which is being looked for
     * @param renameTo the renamed passage that the links must be redirected to
     * @return the rawContent but with the appropriate passage links renamed
     */
    static String getPassageContentWithRenamedLinks(String rawContent, String oldPassageName, String renameTo){
        //if (linkedPassages.contains(oldPassageName)) {
        rawContent = rawContent.replaceAll(
                "(\\[\\[[\\h]*" + oldPassageName + "[\\h]*]])",
                "[[" + renameTo + "]]"
        ); //direct links can be replaceall'd ez
        //indirect links are more tricky.
        Matcher indirectMatcher = Pattern.compile(
                "(\\[\\[[^\\[\\]\\|]+\\|[\\h]*" + oldPassageName + "[\\h]*]])",
                Pattern.MULTILINE
        ).matcher(rawContent); //first it needs to find the indirect links
        while (indirectMatcher.find()) { //whist it can find an indirect link
            String currentMatch = indirectMatcher.group(0); //finds the first one
            String currentRenamed = currentMatch.replaceAll(
                    "(\\|[\\h]*" + oldPassageName + "[\\h]*]])",
                    "|" + renameTo + "]]"
            ); //makes a copy of that match with the old passage name replaced with the new passage name
            rawContent = rawContent.replace(currentMatch, currentRenamed); //replaces the match with the updated version of it
            indirectMatcher.reset(rawContent); //resets the matcher, so it can now look for the next instance of the indirect link
        }
        //}
        return rawContent; //returns the updated rawContent
    }


    /**
     * This method will be used when the passage content needs to be updated indirectly.
     * Replaces the passageContent, and updates the linkedPassages appropriately
     * @param newContent the new content that the passage now holds
     */
    void setPassageContent(String newContent);


    /**
     * This method will be used when the passage content needs to be updated directly.
     * Replaces the passageContent, and updates the linkedPassages and map of all passages accordingly
     * @param newContent the new content that the passage now holds
     * @param allPassages the map of all passages (just in case any new passages need to be added to the map)
     * @return the updated version of allPassages, with any previously unknown linked passages added to that map
     */
    Map<UUID, PassageEditingInterface> updatePassageContent(String newContent, Map<UUID, PassageEditingInterface> allPassages);




    /**
     * This method will be used when attempting to rename this passage.
     * @param newName the new name that the user is trying to give this passage.
     * @param allPassageNames the set of all passage names
     * @return the previous name of this passage (before it got renamed)
     * @throws InvalidPassageNameException if the passage name isn't a valid passage name
     * @throws DuplicatePassageNameException if there's already a passage with this name which exists
     */
    String renameThisPassage(String newName, Set<String> allPassageNames) throws InvalidPassageNameException, DuplicatePassageNameException;


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
    Map<UUID, ? extends PassageEditingInterface> renameThisPassage(String newName, Map<UUID, ? extends PassageEditingInterface> allPassages) throws InvalidPassageNameException, DuplicatePassageNameException;

    /**
     * This method is responsible for deleting this passage (and removing it from the map of all passages)
     * @param allPassages The map of all passages
     * @return a version of the map of all passages with this passage completely removed
     */
    Map<UUID, ? extends PassageEditingInterface> deleteThisPassage(Map<UUID, ? extends PassageEditingInterface> allPassages);

    /**
     * Method that can be used to remove a passage name from another passage's linked passages
     * @param passageToRemove the passage to remove
     * @return the passage that has been removed
     */
    boolean removeLinkedPassage(String passageToRemove);

    /**
     * Obtains the list of passage tags as a string, seperated by spaces, but without the opening/closing []
     * @return the string version of the passage tags (divided by spaces, no opening/closing [])
     */
    String getPassageTagsAsString();

    /**
     * Updates the passage tag list, from a string passage tag list (word characters only, space delimited)
     * @param newPassageTagListString the space-delimited new passage tag list string that will be used to update the passageTags of this passage
     * @return true if this is carried out successfully
     * @throws InvalidMetadataDeclarationException if the given passage tag list was invalid
     */
    boolean updatePassageTags(String newPassageTagListString) throws InvalidMetadataDeclarationException;


    /**
     * Removes a specified tag from the passageTags
     * @param tagToRemove the tag that needs to be removed
     * @return whether or not it was successful
     */
    boolean removePassageTag(String tagToRemove);

    /**
     * Add a new specified tag to the passageTags
     * @param tagToAdd the tag to add
     * @return whether or not it was successfullly added
     */
    boolean addPassageTag(String tagToAdd);


    /**
     * Updates the position of this Passage
     * @param newPosition the new position of this passage
     */
    void updatePosition(Vector2D newPosition);


    /**
     * Updates the inline passage declaration comment
     * @param newComment the new comment that goes in there
     */
    void setInlinePassageComment(String newComment);

    /**
     * updates the trailing passage comment
     * @param newComment the new trailing comment for the passage
     */
    void setTrailingComment(String newComment);



    /**
     * Creates a PassageEditorWindow for the given passage (and the map of all the passages)
     * @param passageToEdit the individual passage in question which is being edited and needs to have a PassageEditorWindow opened for it
     * @param allPassages the map of all passages
     * @return a {@link PassageEditorWindow} which allows a user to edit the passage in question
     */
    static PassageEditorWindow openEditorWindow(PassageEditingInterface passageToEdit, Map<UUID, PassageEditingInterface> allPassages){
        return new PassageEditorWindow(passageToEdit,allPassages);
    }

    /**
     * Basically, it's the static {openEditorWindow method but as an instance method instead.
     * @param allPassages the map of all passages
     * @return a {@link PassageEditorWindow} allowing a user to edit this passage.
     */
    PassageEditorWindow openEditorWindow(Map<UUID, PassageEditingInterface> allPassages);



}
