package oh_hecc.metadata;

import GameParts.Variable;
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
public interface MetadataReadingInterface extends SharedMetadata {

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
        line must be of the form '!StartPassageName: starting passage name'
         */
        String start = "Start";
        try{
            /*
            start = metadataRegexHandler(
                    "(?<=^!StartPassageName:)\\s*[\\w]+[\\w- ]*[\\w]+(?=\\s*$)",
                    rawData
            );*/
            start = SharedMetadata.metadataRegexHandler(
                    "(?<=^!StartPassageName:)" + Parseable.STANDALONE_PASSAGE_NAME_REGEX_WITH_WHITESPACE,
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
                "(?<=^!IFID:)\\h*[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}(?=\\h*$)",
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
                "(?<=^!StoryTitle:)" + VALID_TITLE_REGEX,
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
                "(?<=^!Author:)" + SharedMetadata.VALID_AUTHOR_REGEX,
                rawData
        );
    }

    /**
     * Attempts to find the variable declarations within the metadata stuff, adding each of them to the variables arrayList
     *
     * Variables must be in the form
     *  !Var:
     *  variableName
     *      1 or more word characters (alphanumeric+underscore)
     *  = defaultValue
     *      defaultValue must be 1 or more non-whitespace characters
     *      optional
     *  // comment
     *      comment is anything between the // and the end of line
     *      optional
     * @param rawData the raw metadata that is being looked through
     * @return a List of all the Variable objects
     */
    static List<Variable> findVariables(String rawData){
        List<Variable> variables = new ArrayList<>();
        Matcher variableMatcher = Pattern.compile(
                "(?<=^!Var:)\\h*\\w+\\h*(=\\h*.+?\\h*)?(\\/\\/\\h*?.+)?(?=$)",
                Pattern.MULTILINE
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
        StringBuilder commentBuilder = new StringBuilder();
        Matcher commentMatcher = Pattern.compile(
                "((?<=^\\/\\/).*$)",
                Pattern.MULTILINE
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

