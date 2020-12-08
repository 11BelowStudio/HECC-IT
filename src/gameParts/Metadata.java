package gameParts;

import heccCeptions.NoMatchException;
import hecc_up.FolderOutputterMetadataInterface;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This object basically represents the metadata for the HECCIN' Game
 */
public class Metadata implements FolderOutputterMetadataInterface {

    //TODO: maybe encapsulate HECC-UP specific functions in a seperate object to OH-HECC specific functions?

    /**
     * string with the raw metadata
     */
    private final String rawMetadata;

    /**
     * boolean value to keep track of whether or not this actually has metadata
     */
    private final boolean hasMetadata;

    //These are going to be used to store the metadata about the game title and the author name
    /**
     * what the game title is. defaults to "An Interactive Fiction"
     */
    private String title = "An Interactive Fiction"; //default title used if undefined
    /**
     * whether or not the game title was declared. false by default
     */
    private boolean isTitleDeclared = false; //title undeclared by default

    /**
     * author name. "Anonymous" by default
     */
    private String author = "Anonymous"; //default author name used if undefined
    /**
     * whether or not the author was declared. false by default
     */
    private boolean isAuthorDeclared = false; //author undeclared by default
    
    //This stuff is going to be used by the PassageParser
    /**
     * The starting passage. defaults to "Start" if undefined
     */
    private String startPassage = "Start"; //starting passage defaults to 'Start' if not declared otherwise

    /**
     * Whether or not the IFID was declared. False by default
     */
    private boolean isIfidDeclared = false; //IFID not declared by default
    /**
     * The IFID. empty by default
     */
    private String ifid; //holds the IFID if it is declared

    /**
     * All the variable definitions
     */
    private final ArrayList<Variable> variables;

    /**
     * The multline comment held within the metadata
     */
    private String multilineComment = "";

    /**
     * This is a constructor for the metadata object
     * @param givenMetadata the raw, unparsed, metadata as a string
     * @param isThereActuallyMetadata true if there actually is any metadata, false otherwise
     */
    public Metadata(String givenMetadata, boolean isThereActuallyMetadata){
        rawMetadata = givenMetadata;
        hasMetadata = isThereActuallyMetadata;
        variables = new ArrayList<>();
    }

    /**
     * Wrapper data for the metadata parsing stuff
     * Does nothing if there's no metadata to parse
     */
    public void parseMetadata(){
        if (hasMetadata){ //only bothers doing this if there actually is any metadata to parse
            startPassage = findStartPassage(rawMetadata);
            findIFID();
            findTitle();
            findAuthor();
            findVariables();
            multilineComment = findComment(rawMetadata);
        }
    }

    /**
     * A method which does the metadata regex matching things
     *
     * @param regex the regular expression being looked for in the input
     * @param input the string that the regex is being applied to
     * @return the string that  was found by the regex from the input
     * @throws NoMatchException if no match was found
     */
    private String metadataRegexHandler(String regex, String input) throws NoMatchException {
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
     * A method that wraps the start passage finding stuff
     * @param rawData the raw metadata
     * @return the name of the defined starting passage ("Start" if it wasn't defined)
     */
    private String findStartPassage(String rawData){
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
            startPassage = metadataRegexHandler(
                "(?<=^!StartPassageName:)\\h*[\\w]+[\\w- ]*[\\w]+(?=\\h*$)",
                rawData
            );
        } catch (NoMatchException e){
            //start = "Start";
        }
        return start;
    }

    /**
     * Finds IFID declaration
     */
    private void findIFID(){
        /*
        attempts to find an IFID declaration, in the form '!IFID: UUID goes here'
            sequence of 8-4-4-4-12 hex characters (seperated by hyphens)
         */
        try{
            ifid = metadataRegexHandler(
                "(?<=^!IFID:)\\h*[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}(?=\\h*$)",
                rawMetadata
            ).toUpperCase(); //converts the match to uppercase
            isIfidDeclared = true;
        } catch (NoMatchException e){
            isIfidDeclared = false;
        }
    }

    /**
     * A method which finds the title metadata
     */
    private void findTitle(){
        /*
        Titles are declared as '!StoryTitle: Title Goes Here'
        Valid titles must start with 1 non-whitespace character, and end with 1 non-whitespace character.
            Any amount of non-whitespace characters and/or spaces are allowed
            between the start/end non-whitespace characters.
         */
        try{
            title = metadataRegexHandler(
                "(?<=^!StoryTitle:)\\h*[\\S]+[\\S ]*[\\S]+(?=\\h*$)",
                rawMetadata
            );
            isTitleDeclared = true;
        } catch (NoMatchException e){
            title = "An Interactive Fiction";
            isTitleDeclared = false;
        }
    }

    /**
     * Method to find the author metadata
     */
    private void findAuthor(){
        /*
        Author name declared as '!Author: Author name goes here'
            Must start with a letter, and must end in a letter (uppercase or lowercase)
            May have any number of letters (any case), full stops (for initials), commas (for multiple authors), and spaces
         */
        try{
            author = metadataRegexHandler(
                "(?<=^!Author:)\\h*[A-Za-z]+[a-zA-Z., ]*[a-zA-Z]+(?=\\h*$)",
                rawMetadata
            );
            isAuthorDeclared = true;
        } catch (NoMatchException e){
            author = "Anonymous";
            isAuthorDeclared = false;
        }
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
     */
    private void findVariables(){
        Matcher variableMatcher = Pattern.compile(
                "(?<=^!Var:)\\h*\\w+\\h*(=\\h*.+?\\h*)?(//\\h*.+)?(?=\\h*$)",
                Pattern.MULTILINE
        ).matcher(rawMetadata);
        while(variableMatcher.find()){
            //System.out.println(variableMatcher.group(0));
            variables.add(new Variable(variableMatcher.group(0).trim()));
        }
        /*
        for (Variable v: variables) {
            System.out.println(v.toString());
        }*/
    }

    /**
     * This reads the multiline comment within the metadata (all lines starting with //)
     * @param rawData the full metadata being read
     * @return the multiline comment held within the metadata (as a string)
     */
    private String findComment(String rawData){
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
     * Used to check whether or not the start passage exists
     * @param passageNames set of all defined passage names
     * @return true if that set contains startPassage, false otherwise
     */
    public boolean doesStartPassageExist(Set<String> passageNames){
        return (passageNames.contains(startPassage));
    }

    /**
     * returns the startPassage string
     * @return startPassage
     */
    public String getStartPassage() { return startPassage; }

    /**
     * Returns whether or not the IFID was declared
     * @return isIfidDeclared
     */
    public boolean doesIfidExist(){ return isIfidDeclared; }


    /**
     * returns the IFID string
     * @return ifid
     */
    public String getIfid(){ return ifid; }

    //returns true if all optional metadata was declared, false otherwise

    /**
     * Returns true if all the optional metadata was declared, false otherwise
     * @return AND product of all the optional declarable metadata
     */
    public boolean isAllOptionalMetadataDeclared(){
        return (isIfidDeclared && isAuthorDeclared && isTitleDeclared);
    }


    /**
     * Outputs a string containing instructions for how to output all currently-undeclared optional metadata
     * @return String with the instructions
     */
    public String outputMetadataDefinitionInstructions(){
        if (isAllOptionalMetadataDeclared()){
            return "No metadata problems detected!";
        } else {
            StringBuilder instructionBuilder = new StringBuilder("\n");
            instructionBuilder.append("Your .hecc file appears to be missing some optional metadata. Here's what's wrong, and what you need to insert before the first passage declaration to fix this issue:\n");
            if (!isIfidDeclared) {
                //Add instructions on declaring an IFID if no IFID was declared
                instructionBuilder.append(
                        "No Interactive Fiction Identifier declaration found! You can fix this with this line of code:\n"
                        + "!IFID: " + UUID.randomUUID().toString().toUpperCase()
                        + "\n"
                );
            }
            if (!isTitleDeclared) {
                //Add instructions for declaring the title if no title was declared
                instructionBuilder.append(
                        "No title declaration found! You can fix this with this line of code:\n"
                        + "!StoryTitle: INSERT TITLE HERE"
                        + "\n"
                );
            }
            if (!isAuthorDeclared){
                //Add instructions for declaring author if no author was declared
                instructionBuilder.append(
                        "No author declaration found! You can fix this with this line of code:\n"
                        + "!Author: YOUR NAME HERE"
                        + "\n"
                );
            }
            //returns the string built by instructionBuilder with trailing whitespace removed
            return (instructionBuilder.toString().trim());
        }
    }

    /**
     * @return the 'title' field of this object
     */
    public String getTitle(){ return title; }

    /**
     *
     * @return the 'author' field of this object
     */
    public String getAuthor(){ return author; }

    //TODO: iFiction metadata export stuff
    //public String getiFictionMetadata(){}

    /**
     * This is here for debugging reasons
     * Basically just prints all the fields of this object
     * Nothing of much interest to see here tbh
     */
    public void printDebugData(){
        System.out.println("METADATA OBJECT DEBUG DATA:");
        System.out.println("START PASSAGE: " + startPassage);
        System.out.println("IFID: "+ ifid);
        System.out.println("TITLE: " + title);
        System.out.println("AUTHOR: " + author);
        System.out.println("RAW METADATA:\n" + rawMetadata);
        System.out.println("THE COMMENT:\n" + multilineComment);
        System.out.println("end of metadata debug info");
    }


    /**
     * Returns the IFID string, but formatted such that it's usable in the HTML page
     * @return the appropriate string for declaring the IFID in the HTML page
     */
    @Override
    public String getIfidButHtmlFormatted(){
        if (isIfidDeclared) {
            return "<!-- UUID://" + ifid + "// -->\n";
        } else{
            return "<!-- no IFID declared! -->\n";
        }
    }

    //TODO: actually I might need to get this handled by a completely different class
    /**
     * Returns the info from this metadata object, but in the form you'd expect to see in an iFiction file
     * @return a string containing all the iFiction data stuff
     */
    @Override
    public String getIFictionMetadata() {
        StringBuilder iFictionBuilder = new StringBuilder();
        iFictionBuilder.append(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ifindex version=\"1.0\" xmlns=\"http://babel.ifarchive.org/protocol/iFiction/\">\n" +
                "\t<!-- Bibliographic data generated via HECC-UP -->\n" +
                "\t<story>\n" +
                "\t\t<identification>\n"
        );
        if (isIfidDeclared){
            iFictionBuilder.append("\t\t\t<ifid>");
            iFictionBuilder.append(ifid);
            iFictionBuilder.append("</ifid>\n");
        }
        iFictionBuilder.append(
                "\t\t\t<format>html</format>\n" +
                "\t\t</identification>\n" +
                "\t\t<bibliographic>\n" +
                "\t\t\t<title>"
        );
        iFictionBuilder.append(title);
        iFictionBuilder.append(
                "</title>\n" +
                "\t\t\t<author>"
        );
        iFictionBuilder.append(author);
        iFictionBuilder.append(
                "</author>\n" +
                "\t\t</bibliographic>\n" +
                "\t</story>\n" +
                "</ifindex>"
        );
        return iFictionBuilder.toString();
    }
}
