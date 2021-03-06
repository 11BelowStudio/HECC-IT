package oh_hecc.game_parts.metadata;

import hecc_up.gameParts.Variable;
import heccCeptions.NoMatchException;
import oh_hecc.Parseable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is an interface which encapsulates all the metadata reading functionality
 * Only contains static methods
 */
@SuppressWarnings("RegExpAnonymousGroup")
public interface MetadataReadingInterface extends SharedMetadata {

    /**
     * Author declaration must be prefixed as `!author:`
     */
    String AUTHOR_PREFIX = "!author:";

    /**
     * Title declaration must be prefixed as `!title:`
     */
    String TITLE_PREFIX = "!title:";

    /**
     * Start declaration must be prefixed as `!start:`
     */
    String START_PREFIX = "!start:";

    /**
     * IFID declaration must be prefixed as `!ifid:`
     */
    String IFID_PREFIX = "!ifid:";

    /**
     * variable declarations must be prefixed as `!var:`
     */
    String VARIABLE_PREFIX = "!var:";

    /**
     * Comment lines (at least, the comments you want to keep) must be prefixed with `//`
     */
    String COMMENT_PREFIX = "//";

    /**
     * A method that performs the start passage finding stuff
     * @param rawData the raw metadata
     * @return the name of the defined starting passage ("Start" if it wasn't defined)
     */
    static String findStartPassage(String rawData){
        /*
        finds the declaration for the starting passage,
            in a string defined as starting with '!StartPassageName:',
            allowing some whitespace, then the starting passage name,
            then allowing trailing whitespace.
        This first matcher will find the passage name, along with any leading whitespace
        line must be of the form '!start: starting passage name'
         */
        String start = "Start";
        try{
            start = SharedMetadata.metadataRegexHandler(
                    "(?<=^"+START_PREFIX+")" + Parseable.STANDALONE_PASSAGE_NAME_REGEX_WITH_WHITESPACE,
                    rawData
            );
        } catch (NoMatchException ignored){}
        return start;
    }

    /**
     * Finds the IFID declaration
     * @param rawData the raw metadata that the IFID is being searched for in
     * @return the declared IFID within the metadata (if it exists)
     * @throws NoMatchException if no IFID declaration could be found
     */
    static String findIfid(String rawData) throws NoMatchException {
        /*
        attempts to find an IFID declaration, in the form '!IFID: UUID goes here'
            sequence of 8-4-4-4-12 hex characters (seperated by hyphens)
         */
        return SharedMetadata.metadataRegexHandler(
                "(?<="+IFID_PREFIX+")\\h*[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}(?=\\h*$)",
                rawData
        ).toUpperCase(); //converts the match to uppercase
    }

    /**
     * A method which finds the title declaration within the metadata
     * @param rawData the raw metadata that is being searched for
     * @return The title of the game (if it was declared)
     * @throws NoMatchException if no title declaration could be found
     */
    static String findTitle(String rawData) throws NoMatchException{
        /*
        Titles are declared as '!StoryTitle: Title Goes Here'
        Valid titles must start with 1 non-whitespace character, and end with 1 non-whitespace character.
            Any amount of non-whitespace characters and/or spaces are allowed
            between the start/end non-whitespace characters.
         */
        return SharedMetadata.metadataRegexHandler(
                "(?<=^"+TITLE_PREFIX+")" + VALID_TITLE_REGEX,
                rawData
        );
    }


    /**
     * Method to find the author declaration from the metadata
     * @param rawData the raw metadata being searched through
     * @return the name of the author
     * @throws NoMatchException if no author declaration could be found
     */
    static String findAuthor(String rawData) throws NoMatchException {
        /*
        Author name declared as '!Author: Author name goes here'
            Must start with a letter, and must end in a letter (uppercase or lowercase)
            May have any number of letters (any case), full stops (for initials), commas (for multiple authors), and spaces
         */
        return SharedMetadata.metadataRegexHandler(
                "(?<=^"+AUTHOR_PREFIX+")" + SharedMetadata.VALID_AUTHOR_REGEX,
                rawData
        );
    }

    /**
     * Attempts to find the variable declarations within the metadata stuff, adding each of them to the variables arrayList
     * <p>
     * Variables must be in the form:
     * <dl>
     *  <dt>!var:</dt>
     *  <dt>variableName</dt>
     *      <dd>1 or more word characters (alphanumeric+underscore)</dd>
     *  <dt>= defaultValue</dt>
     *      <dd>defaultValue must be 1 or more non-whitespace characters
     *      (optional)</dd>
     *  <dt>// comment</dt>
     *      <dd>comment is anything between the // and the end of line
     *      (optional)</dd>
     * </dl>
     * @param rawData the raw metadata that is being looked through
     * @return a List of all the Variable objects
     */
    static List<Variable> findVariables(String rawData){
        final List<Variable> variables = new ArrayList<>();
        final Matcher variableMatcher = Pattern.compile(
                "(?<=^"+VARIABLE_PREFIX+")\\h*\\w+\\h*(=\\h*.+?\\h*)?(//\\h*?.+)?(?=$)",
                Pattern.MULTILINE | Pattern.CASE_INSENSITIVE
        ).matcher(rawData);
        while(variableMatcher.find()){
            //System.out.println(variableMatcher.group(0));
            variables.add(new Variable(variableMatcher.group(0).trim()));
        }
        return variables;
    }

    /**
     * This reads the multiline comment within the metadata (all lines starting with //)
     * @param rawData the full metadata being read
     * @return the multiline comment held within the metadata (as a string)
     */
    static String findComment(String rawData){
        //a StringBuilder that constructs the comment
        final StringBuilder commentBuilder = new StringBuilder();
        final Matcher commentMatcher = Pattern.compile(
                "((?<=^"+COMMENT_PREFIX+").*$)",
                Pattern.MULTILINE | Pattern.CASE_INSENSITIVE
        ).matcher(rawData); //matches lines that start with a '//' (getting everything from the // to end of line)

        while(commentMatcher.find()){
            //adds the line of the comment to the commentBuilder
            commentBuilder.append(commentMatcher.group(0));
            //followed by a newline
            commentBuilder.append("\n");
        }
        //returns the string built by the commentBuilder
        return commentBuilder.toString();
    }
}

