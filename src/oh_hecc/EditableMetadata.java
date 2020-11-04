package oh_hecc;

import GameParts.Variable;
import heccCeptions.InvalidMetadataDeclarationException;
import heccCeptions.InvalidPassageNameException;
import heccCeptions.NoMatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditableMetadata implements Heccable, Parseable {

    //TODO: The 'editable' bit of this. Methods to edit title, author, variables, start passage, and multiline comment (ensuring new values are valid).

    /**
     * what the game title is. defaults to "An Interactive Fiction"
     */
    private String title = "An Interactive Fiction"; //default title used if undefined
    /**
     * The regex which titles must satisfy.
     * Titles are declared as '!StoryTitle: Title Goes Here' (the !StoryTitle: prefix is not present here though)
     * Valid titles must start with 1 non-whitespace character, and end with 1 non-whitespace character.
     * Any amount of non-whitespace characters and/or spaces are allowed between the start/end non-whitespace characters.
     * Some horizontal whitespace permitted at very start/end, will be trimmed out anyway
     */
    static final String VALID_TITLE_REGEX = "\\h*[\\S]+[\\S ]*[\\S]+(?=\\h*$)";

    /**
     * author name. "Anonymous" by default
     */
    private String author = "Anonymous"; //default author name used if undefined
    /**
     * Author name declared as '!Author: Author name goes here'
     * Must start with a letter, and must end in a letter (uppercase or lowercase)
     * May have any number of letters (any case), full stops (for initials), commas (for multiple authors), and spaces
     */
    static final String VALID_AUTHOR_REGEX = "\\h*[A-Za-z]+[a-zA-Z., ]*[a-zA-Z]+(?=\\h*$)";

    /**
     * The starting passage. defaults to "Start" if undefined
     */
    private String startPassage = "Start";


    /**
     * The IFID. empty by default
     */
    private String ifid;

    /**
     * All the variable definitions
     */
    private ArrayList<Variable> variables;

    /**
     * The multline comment held within the metadata. empty by default
     */
    private String comment;


    /**
     * No-argument constructor, only called by the other constructors,
     * that initialises stuff to default values
     */
    private EditableMetadata(){
        title = "An Interactive Fiction";
        author = "Anonymous";
        ifid = generateIFIDString();
        variables = new ArrayList<>();
        comment = "";
    }

    /**
     * Metadata constructor that takes an author name and a game title as a constructor
     * Will only be used when creating a completely new HECC file
     */
    public EditableMetadata(String theTitle, String theAuthor){
        this();
        title = theTitle;
        author = theAuthor;
    }

    /**
     * EditableMetadata constructor that takes raw .hecc metadata as a constructor,
     * and uses that to construct this
     * @param rawMetadata the raw hecc metadata that this will be built from
     */
    public EditableMetadata(String rawMetadata){
        this(); //defaults made
        //gets the start passage
        startPassage = EditableMetadata.findStartPassage(rawMetadata);
        //attempts to find the IFID declaration, title declaration, and author declaration
        try{ ifid = EditableMetadata.findIfid(rawMetadata); } catch (NoMatchException ignored){}
        try{ title = EditableMetadata.findTitle(rawMetadata); } catch (NoMatchException ignored){}
        try{ author = EditableMetadata.findAuthor(rawMetadata); } catch (NoMatchException ignored){}

        //then the variables
        variables.addAll(EditableMetadata.findVariables(rawMetadata));

        //finally, the multiline comment
        comment = EditableMetadata.findComment(rawMetadata);
    }


    /**
     * Literally just generates an UUID (used as an IFID), returns its string version in uppercase
     * @return a string with a new UUID (in uppercase)
     */
    static String generateIFIDString(){
        UUID ifid = UUID.randomUUID();
        return (ifid.toString().toUpperCase());
    }

    /**
     * A method which does the metadata regex matching things
     *
     * @param regex the regular expression being looked for in the input
     * @param input the string that the regex is being applied to
     * @return the string that  was found by the regex from the input
     * @throws NoMatchException if no match was found
     */
    static String metadataRegexHandler(String regex, String input) throws NoMatchException {
        Matcher metadataMatcher = Pattern.compile(
                regex,
                Pattern.MULTILINE
        ).matcher(input);
        if (metadataMatcher.find()){
            return metadataMatcher.group(0).trim(); //returns first match, with whitespace trimmed
        } else{
            throw new NoMatchException(); //throws exception if no matches were found
        }
    }

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
            start = metadataRegexHandler(
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
        return metadataRegexHandler(
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
        return metadataRegexHandler(
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
        return metadataRegexHandler(
                "(?<=^!Author:)" + VALID_AUTHOR_REGEX,
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

    /**
     * Checks whether or not a new title for the game would be valid or not
     * @param newTitleToCheck the new title which needs checking
     * @return a trimmed version of the new title being checked (if valid)
     * @throws InvalidMetadataDeclarationException if the new title is invalid
     */
    static String checkTitleValidity(String newTitleToCheck) throws InvalidMetadataDeclarationException {
        Matcher validTitleMatcher = Pattern.compile(VALID_TITLE_REGEX).matcher(newTitleToCheck);
        if (validTitleMatcher.find()){
            return validTitleMatcher.group(0).trim(); //returns the trimmed match if there is a match
        } else {
            throw new InvalidMetadataDeclarationException(newTitleToCheck, "title"); //exception thrown if no match found
        }
    }

    /**
     * Attempts to update the title of the game
     * @param newTitle the new title being used
     * @return true if the new title was valid (and the title could be changed)
     * @throws InvalidMetadataDeclarationException if newTitle is invalid
     */
    boolean updateTitle(String newTitle) throws InvalidMetadataDeclarationException {
        title = EditableMetadata.checkTitleValidity(newTitle);
        return true;
    }

    /**
     * Checks the validity of a given 'author' value
     * @param newAuthorToCheck the 'author' string that needs to be checked
     * @return a trimmed version of the newAuthorToCheck (matching the VALID_AUTHOR_REGEX)
     * @throws InvalidMetadataDeclarationException if newAuthorToCheck is not a valid author name
     */
    static String checkAuthorValidity(String newAuthorToCheck) throws InvalidMetadataDeclarationException{
        Matcher authorMatcher = Pattern.compile(VALID_AUTHOR_REGEX).matcher(newAuthorToCheck);
        if (authorMatcher.find()){
            return authorMatcher.group(0).trim();
        } else{
            throw new InvalidMetadataDeclarationException(newAuthorToCheck, "author name");
        }
    }

    /**
     * Attempts to update the 'author' field of this EditableMetadata object
     * @param newAuthor the new value for the 'author' field
     * @return true if the 'author' was updated successfully
     * @throws InvalidMetadataDeclarationException if the given 'newAuthor' is not a valid 'author' input
     */
    boolean updateAuthor(String newAuthor) throws InvalidMetadataDeclarationException{
        author = EditableMetadata.checkAuthorValidity(newAuthor);
        return true;
    }

    /**
     * Attempts to update the 'startPassage' field of this object
     * @param newStartPassage the name of the new intended start passage
     * @return true if the start passage was updated successfully
     * @throws InvalidPassageNameException if the new start passage isn't valid
     */
    boolean updateStartPassage(String newStartPassage) throws InvalidPassageNameException {
        startPassage = Parseable.validatePassageNameRegex(newStartPassage);
        return true;
    }

    /**
     * updates the comment
     * @param newComment the new comment for the object
     */
    void updateComment(String newComment){
        comment = newComment;
    }

    /**
     * Obtains the 'title' of this object
     * @return the 'title' string
     */
    public String getTitle(){
        return title;
    }

    /**
     * Obtains the 'author' belonging to this object
     * @return the 'author' string
     */
    public String getAuthor(){
        return author;
    }

    /**
     * Obtains the start passage
     * @return the start passage
     */
    public String getStartPassage(){
        return startPassage;
    }

    /**
     * obtains the comment
     * @return the comment
     */
    public String getComment(){
        return comment;
    }

    /**
     * Converts this metadata object into hecc code
     * @return the metadata in .hecc text form
     */
    @Override
    public String toHecc(){
        StringBuilder heccBuilder = new StringBuilder();
        heccBuilder.append("Hecc code generated by OH-HECC at ");
        heccBuilder.append(new java.util.Date().toString()); //casually throwing in when this was made
        heccBuilder.append("\n\n");

        //title declaration
        heccBuilder.append("!StoryTitle: ");
        heccBuilder.append(title);
        heccBuilder.append("\n");

        //author declaration
        heccBuilder.append("!Author: ");
        heccBuilder.append(author);
        heccBuilder.append("\n");

        //IFID declaration
        heccBuilder.append("!IFID: ");
        heccBuilder.append(ifid);
        heccBuilder.append("\n");

        //Start passage declaration
        heccBuilder.append("!StartPassageName: ");
        heccBuilder.append(startPassage);
        heccBuilder.append("\n");


        //Variables
        heccBuilder.append("\n");
        for (Variable v: variables) {
            heccBuilder.append(v.toHecc());
            heccBuilder.append("\n");
        }
        heccBuilder.append("\n");

        //the multiline comment (after informing author that only the multiline comment comments will be preserved by OH-HECC)
        heccBuilder.append("Note to author: only comments saved in this multiline comment area (lines starting with //) will be preserved by OH-HECC!\n");
        heccBuilder.append(multilineCommentHeccFormatted(comment));
        heccBuilder.append("\n\n");

        //annotation indicating that the metadata is over.
        heccBuilder.append("end of metadata. game content starts below:\n");

        return heccBuilder.toString();
    }

    /**
     * Returns the hecc-formatted version of the multiline comment
     * @param multilineComment the multiline comment attribute of this EditableMetadata
     * @return the multiline comment, but formatted as .hecc
     */
    static String multilineCommentHeccFormatted(String multilineComment){
        String[] segmentsOfTheComment = multilineComment.split("\n");
        StringBuilder heccedMultilineCommentBuilder = new StringBuilder();
        for (String s: segmentsOfTheComment) {
            heccedMultilineCommentBuilder.append("// ");
            heccedMultilineCommentBuilder.append(s);
            heccedMultilineCommentBuilder.append("\n");
        }
        return heccedMultilineCommentBuilder.toString();
    }
}
