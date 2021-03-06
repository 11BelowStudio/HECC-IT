package oh_hecc.game_parts.metadata;

import heccCeptions.InvalidMetadataDeclarationException;
import heccCeptions.InvalidPassageNameException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An interface to be used for editing the metadata.
 */
public interface MetadataEditingInterface extends SharedMetadata {

    /**
     * Checks whether or not a new title for the game would be valid or not
     * @param newTitleToCheck the new title which needs checking
     * @return a trimmed version of the new title being checked (if valid)
     * @throws InvalidMetadataDeclarationException if the new title is invalid
     */
    static String checkTitleValidity(String newTitleToCheck) throws InvalidMetadataDeclarationException {
        final Matcher validTitleMatcher = Pattern.compile(SharedMetadata.VALID_TITLE_REGEX).matcher(newTitleToCheck);
        if (validTitleMatcher.find()){
            return validTitleMatcher.group(0).trim(); //returns the trimmed match if there is a match
        } else {
            throw new InvalidMetadataDeclarationException(newTitleToCheck, "title"); //exception thrown if no match found
        }
    }

    /**
     * Checks the validity of a given 'author' value
     * @param newAuthorToCheck the 'author' string that needs to be checked
     * @return a trimmed version of the newAuthorToCheck (matching the VALID_AUTHOR_REGEX)
     * @throws InvalidMetadataDeclarationException if newAuthorToCheck is not a valid author name
     */
    static String checkAuthorValidity(String newAuthorToCheck) throws InvalidMetadataDeclarationException{
        final Matcher authorMatcher = Pattern.compile(VALID_AUTHOR_REGEX).matcher(newAuthorToCheck);
        if (authorMatcher.find()){
            return authorMatcher.group(0).trim();
        } else{
            throw new InvalidMetadataDeclarationException(newAuthorToCheck, "author name");
        }
    }

    /**
     * Attempts to update the title of the game
     * @param newTitle the new title being used
     * @return true if the new title was valid (and the title could be changed)
     * @throws InvalidMetadataDeclarationException if newTitle is invalid
     */
    boolean updateTitle(String newTitle) throws InvalidMetadataDeclarationException;

    /**
     * updates the comment
     * @param newComment the new comment for the object
     */
    void updateComment(String newComment);

    /**
     * Attempts to update the 'author' field of this EditableMetadata object
     * @param newAuthor the new value for the 'author' field
     * @return true if the 'author' was updated successfully
     * @throws InvalidMetadataDeclarationException if the given 'newAuthor' is not a valid 'author' input
     */
    boolean updateAuthor(String newAuthor) throws InvalidMetadataDeclarationException;

    /**
     * Attempts to update the 'startPassage' field of this object
     * @param newStartPassage the name of the new intended start passage
     * @return true if the start passage was updated successfully
     * @throws InvalidPassageNameException if the new start passage isn't valid
     */
    boolean updateStartPassage(String newStartPassage) throws InvalidPassageNameException;


    /**
     * le debug code has arrived
     * @return the metadata as a String
     */
    String toString();
}
