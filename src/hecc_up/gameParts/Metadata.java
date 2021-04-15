package hecc_up.gameParts;

import heccCeptions.NoMatchException;
import hecc_up.FolderOutputterMetadataInterface;
import oh_hecc.game_parts.metadata.MetadataReadingInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static oh_hecc.game_parts.metadata.MetadataReadingInterface.*;

/**
 * This object basically represents the metadata for the HECCIN' Game
 */
public class Metadata implements FolderOutputterMetadataInterface, MetadataReadingInterface {

    //TODO: maybe encapsulate HECC-UP specific functions in a seperate object to OH-HECC specific functions?

    /**
     * string with the raw metadata
     */
    private final String rawMetadata;


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
    private String ifid = ""; //holds the IFID if it is declared

    /**
     * All the variable definitions.
     *
     * STILL UNUSED!
     */
    private final List<Variable> variables;


    /**
     * This is a constructor for the metadata object
     * @param givenMetadata the raw, unparsed, metadata as a string
     */
    public Metadata(String givenMetadata){
        rawMetadata = givenMetadata;
        variables = new ArrayList<>();
    }

    /**
     * Wrapper data for the metadata parsing stuff
     * Does nothing if there's no metadata to parse
     */
    public void parseMetadata(){
        if (!rawMetadata.trim().isEmpty()){ //only bothers doing this if there actually is any metadata to parse
            startPassage = findStartPassage();
            //startPassage = MetadataReadingInterface.findStartPassage(rawMetadata);
            findIFID();
            findTitle();
            findAuthor();
            findVariables();
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
        final Matcher metadataMatcher = Pattern.compile(
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
     * Finds start passage declaration.
     * @return start passage string
     * @see oh_hecc.game_parts.metadata.MetadataReadingInterface#findStartPassage(String)
     */
    String findStartPassage(){
        startPassage = MetadataReadingInterface.findStartPassage(rawMetadata);
        return startPassage;
    }


    /**
     * Finds IFID declaration.
     * @return IFID string
     * @see oh_hecc.game_parts.metadata.MetadataReadingInterface#findIfid(String)
     */
    String findIFID(){
        /*
        attempts to find an IFID declaration, in the form '!IFID: UUID goes here'
            sequence of 8-4-4-4-12 hex characters (seperated by hyphens)
         */
        try{
            ifid = MetadataReadingInterface.findIfid(rawMetadata);
            isIfidDeclared = true;
        } catch (NoMatchException e){
            isIfidDeclared = false;
        }
        return ifid;
    }

    /**
     * A method which finds the title metadata
     * @return Title string
     * @see oh_hecc.game_parts.metadata.MetadataReadingInterface#findTitle(String)
     */
    String findTitle(){
        /*
        Titles are declared as '!StoryTitle: Title Goes Here'
        Valid titles must start with 1 non-whitespace character, and end with 1 non-whitespace character.
            Any amount of non-whitespace characters and/or spaces are allowed
            between the start/end non-whitespace characters.
         */
        try{
            title = MetadataReadingInterface.findTitle(rawMetadata);
            isTitleDeclared = true;
        } catch (NoMatchException e){
            title = "An Interactive Fiction";
            isTitleDeclared = false;
        }
        return title;
    }

    /**
     * Method to find the author metadata
     * @return author string
     * @see oh_hecc.game_parts.metadata.MetadataReadingInterface#findAuthor(String)
     */
    String findAuthor(){
        /*
        Author name declared as '!Author: Author name goes here'
            Must start with a letter, and must end in a letter (uppercase or lowercase)
            May have any number of letters (any case), full stops (for initials), commas (for multiple authors), and spaces
         */
        try{
            author = MetadataReadingInterface.findAuthor(rawMetadata);
            isAuthorDeclared = true;
        } catch (NoMatchException e){
            author = "Anonymous";
            isAuthorDeclared = false;
        }
        return author;
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
     * @return the list of variables
     */
    List<Variable> findVariables(){
        variables.clear();
        final List<Variable> vars = MetadataReadingInterface.findVariables(rawMetadata);
        variables.addAll(vars);
        return vars;
    }

    /**
     * returns the variables
     * @return the variables
     */
    public List<Variable> getVariables(){
        return variables;
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
     * obtains the comment
     *
     * @return the comment
     *
     * UNUSED!
     */
    @Override
    public String getComment() { return ""; }

    /**
     * Returns whether or not the IFID was declared
     * @return isIfidDeclared
     */
    public boolean doesIfidExist(){ return isIfidDeclared; }


    /**
     * returns the IFID string
     * @return ifid
     */
    public String getIfid(){
        if (isIfidDeclared) {
            return ifid;
        } else {
            return "unspecified";
        }
    }

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
            final StringBuilder instructionBuilder = new StringBuilder("\n");
            instructionBuilder.append("Your .hecc file appears to be missing some optional metadata. Here's what's wrong, and what you need to insert before the first passage declaration to fix this issue:\n");
            if (!isIfidDeclared) {
                //Add instructions on declaring an IFID if no IFID was declared
                instructionBuilder.append(
                        "No Interactive Fiction Identifier declaration found! You can fix this with this line of code:\n"
                        + IFID_PREFIX + " " + UUID.randomUUID().toString().toUpperCase()
                        + "\n"
                );
            }
            if (!isTitleDeclared) {
                //Add instructions for declaring the title if no title was declared
                instructionBuilder.append(
                        "No title declaration found! You can fix this with this line of code:\n"
                        + TITLE_PREFIX + " INSERT TITLE HERE"
                        + "\n"
                );
            }
            if (!isAuthorDeclared){
                //Add instructions for declaring author if no author was declared
                instructionBuilder.append(
                        "No author declaration found! You can fix this with this line of code:\n"
                        + AUTHOR_PREFIX +" YOUR NAME HERE"
                        + "\n"
                );
            }
            //returns the string built by instructionBuilder with trailing whitespace removed
            return (instructionBuilder.toString().trim());
        }
    }

    /**
     * gets the title string
     * @return the 'title' field of this object
     */
    public String getTitle(){ return title; }

    /**
     * gets the Title, but with html escape characters
     * @return the title but with any special html characters escaped and such
     */
    public String getTitleHtmlEscaped(){
        return getHtmlEscapedString(title);
    }

    /**
     * gets the author
     * @return the 'author' field of this object
     */
    public String getAuthor(){ return author; }

    /**
     * gets the author, but HTML escaped
     * @return the html-escaped version of the author's name
     */
    public String getAuthorHTMLEscaped(){
        return getHtmlEscapedString(author);
    }

    /**
     * escapes a string for use in HTML stuff
     * @param escapeThis the string being escaped
     * @return escapeThis but HTML escaped
     */
    private String getHtmlEscapedString(String escapeThis){
        escapeThis = escapeThis.replaceAll("&","&amp"); //escapes ampersands
        escapeThis = escapeThis.replaceAll("<","&lt"); //escapes <
        escapeThis = escapeThis.replaceAll(">","&gt"); //escapes >
        escapeThis = escapeThis.replaceAll("\"","&quot"); //escapes "
        escapeThis = escapeThis.replaceAll("'","&#39"); //escapes '
        return escapeThis;
    }


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
        System.out.println("VARIABLES: " + variables);
        System.out.println("RAW METADATA:\n" + rawMetadata);
        System.out.println("end of metadata debug info");
    }


    /**
     * Returns the IFID string, but formatted such that it's usable in the HTML page
     * @return the appropriate string for declaring the IFID in the HTML page
     */
    @Override
    public String getIfidButHtmlFormatted(){
        if (isIfidDeclared) {
            return "<!-- UUID://" + ifid + "// -->";
        } else{
            return "<!-- no IFID declared! -->";
        }
    }

    //TODO: actually I might need to get this handled by a completely different class
    /**
     * Returns the info from this metadata object, but in the form you'd expect to see in an iFiction file
     * @return a string containing all the iFiction data stuff
     */
    @Override
    public String getIFictionMetadata() {
        final StringBuilder iFictionBuilder = new StringBuilder();
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
        iFictionBuilder.append(getTitleHtmlEscaped());
        iFictionBuilder.append(
                "</title>\n" +
                "\t\t\t<author>"
        );
        iFictionBuilder.append(getAuthorHTMLEscaped());
        iFictionBuilder.append(
                "</author>\n" +
                "\t\t</bibliographic>\n" +
                "\t</story>\n" +
                "</ifindex>"
        );
        return iFictionBuilder.toString();
    }

    /**
     * Method to obtain the .hecc representation of this object
     *
     * @return a string containing this object in .hecc code format
     *
     * UNUSED!
     */
    @Override
    public String toHecc() {
        return "";
    }
}
