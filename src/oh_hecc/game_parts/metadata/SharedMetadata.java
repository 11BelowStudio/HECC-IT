package oh_hecc.game_parts.metadata;

import heccCeptions.NoMatchException;
import oh_hecc.Heccable;
import oh_hecc.Parseable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A high-level interface for the Metadata class, holding things that are needed by its sub interfaces/high level functionality
 */
public interface SharedMetadata extends Heccable, Parseable {





    /**
     * Author name declared as '!author: Author name goes here'
     * Must start with a letter, and must end in a letter (uppercase or lowercase)
     * May have any number of letters (any case), full stops (for initials), commas (for multiple authors), and spaces
     */
    String VALID_AUTHOR_REGEX = "\\h*([A-Za-z]+[a-zA-Z., ]*)?[a-zA-Z]+(?=\\h*$)";

    /**
     * The regex which titles must satisfy.
     * Titles are declared as '!StoryTitle: Title Goes Here' (the !StoryTitle: prefix is not present here though)
     * Valid titles must start with 1 non-whitespace character, and end with 1 non-whitespace character.
     * Any amount of non-whitespace characters and/or spaces are allowed between the start/end non-whitespace characters.
     * Some horizontal whitespace permitted at very start/end, will be trimmed out anyway
     */
    String VALID_TITLE_REGEX = "\\h*(([\\S]+[\\S ]*)?[\\S]+)(?=\\h*$)";


    /**
     * A method which does the metadata regex matching things. Yep, it's case-insensitive.
     *
     * @param regex the regular expression being looked for in the input
     * @param input the string that the regex is being applied to
     * @return the string that  was found by the regex from the input
     * @throws NoMatchException if no match was found
     */
    static String metadataRegexHandler(String regex, String input) throws NoMatchException {
        Matcher metadataMatcher = Pattern.compile(
                regex,
                Pattern.MULTILINE | Pattern.CASE_INSENSITIVE
        ).matcher(input);
        if (metadataMatcher.find()){
            return metadataMatcher.group(0).trim(); //returns first match, with whitespace trimmed
        } else{
            throw new NoMatchException(); //throws exception if no matches were found
        }
    }

    /**
     * Obtains the 'title' of this object
     * @return the 'title' string
     */
    String getTitle();

    /**
     * Obtains the 'author' belonging to this object
     * @return the 'author' string
     */
    String getAuthor();

    /**
     * Obtains the IFID
     * @return the IFID
     */
    String getIfid();

    /**
     * Obtains the start passage
     * @return the start passage
     */
    String getStartPassage();

    /**
     * obtains the comment
     * @return the comment
     */
    String getComment();


    /**
     * string representation of the metadata (for debugging)
     * @return string version of the metadata
     */
    String toString();

}
