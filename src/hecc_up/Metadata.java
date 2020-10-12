package hecc_up;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Metadata {
    
    private String rawMetadata; //string with the raw metadata
    private boolean hasMetadata; //boolean value to keep track of whether or not it actually has metadata

    //These are going to be used to store the metadata about the game title and the author name
    private String title = "An Interactive Fiction";
    private String filenameTitle = "An Interactive Fiction.iFiction";
    private String author = "Anonymous";
    
    //This stuff is going to be used by the PassageParser
    private String startPassage = "Start"; //starting passage defaults to 'Start' if not declared otherwise

    private boolean isIFIDDeclared = false; //IFID not declared by default
    private String ifid; //holds the IFID if it is declared

    //constructs this Metadata object with actual raw metadata to parse
    public Metadata(String givenMetadata, boolean isThereActuallyMetadata){
        rawMetadata = givenMetadata;
        hasMetadata = isThereActuallyMetadata;
    }

    //wrapper function for the metadata parsing stuff
    public void parseMetadata(){
        if (hasMetadata){ //only bothers doing this if there actually is any metadata to parse
            findStartPassage();
            findIFID();
            findTitle();
            findAuthor();
        }
    }

    //finds starting passage declaration
    private void findStartPassage(){
        /*
        finds the declaration for the starting passage, in a string defined as starting with '!StartPassageName:',
            allowing some whitespace, then the starting passage name,
            then allowing trailing whitespace.
        This first matcher will find the passage name, along with any leading whitespace
        line must be of the form '!StartPassageName: starting passage name'
         */
        Matcher startMatcher = Pattern.compile("(?<=^!StartPassageName:)\\s*[\\w]+[\\w- ]*[\\w]+(?=\\s*$)", Pattern.MULTILINE).matcher(rawMetadata);
        if (startMatcher.find()) {
            //if it found it, it'll then attempt to omit any leading whitespace, via another Matcher
            String untrimmedStart = startMatcher.group(0);
            //System.out.println(leadingWhitespaceStartPassageName);
            Matcher trimmedStartMatcher = Pattern.compile("[\\w]+[\\w- ]*[\\w]+").matcher(untrimmedStart);
            if (trimmedStartMatcher.find()) {
                //the version of the start passage name with the leading whitespace is then set as the starting passage name
                startPassage = trimmedStartMatcher.group(0);
                System.out.println(startPassage);
            }
        }
    }

    //finds IFID declaration
    private void findIFID(){
        /*
        attempts to find an IFID declaration, in the form '!IFID: UUID goes here'
        sequence of 8-4-4-4-12 hex characters (seperated by hyphens)
        first matcher permits leading whitespace, so ofc it'll need to yeet that stuff later
         */
        Matcher ifidMatcher = Pattern.compile("(?<=^!IFID:)\\s*[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}(?=\\s*$)", Pattern.MULTILINE).matcher(rawMetadata);
        if (ifidMatcher.find()) {
            //if found, it then needs to yeet the leading whitespace
            String untrimmedIFID = ifidMatcher.group(0);
            Matcher trimmedIfidMatcher = Pattern.compile("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}").matcher(untrimmedIFID);
            if (trimmedIfidMatcher.find()) {
                //no whitespace left, so found IFID is converted to uppercase and saved as ifid
                ifid = trimmedIfidMatcher.group(0).toUpperCase();
                //IFID has been declared, so this is true.
                isIFIDDeclared = true;
            }
        }
    }

    //TODO: method to find Title declaration
    private void findTitle(){
        /*
        Attempts to find the title of the game.
        Valid titles must start with 1 non-whitespace character, and end with 1 non-whitespace character.
            Any amount of non-whitespace characters and/or spaces are allowed between the start/end non-whitespace characters.
         */
        Matcher titleMatcher = Pattern.compile("(?<=^!StoryTitle:)\\s*[\\S]+[\\S ]*[\\S]+(?=\\s*$)", Pattern.MULTILINE).matcher(rawMetadata);
        if (titleMatcher.find()) {
            //if it found it, it'll then attempt to omit any leading whitespace, via another Matcher
            String untrimmedTitle = titleMatcher.group(0);
            Matcher trimmedTitleMatcher = Pattern.compile("[\\S]+[\\S ]*[\\S]+").matcher(untrimmedTitle);
            if (trimmedTitleMatcher.find()) {
                //the version of the title without the leading whitespace is then set as the title
                title = trimmedTitleMatcher.group(0);
                System.out.println(title);
                //removes any characters which are not valid for filenames from title to produce filenameTitle
                filenameTitle = title.replaceAll("[/\\\\:?*\"<>|.]","").concat(".iFiction");
            }
        }
    }

    //TODO: method to find Author declaration
    private void findAuthor(){
        /*
        Attempts to find the name of the author
            Must start with a capital letter, and must end in a letter (uppercase or lowercase)
            May have any number of letters (any case), full stops (for initials), commas (for multiple authors), and spaces
         */
        Matcher authorMatcher = Pattern.compile("(?<=^!Author:)\\s*[A-Z]+[a-zA-Z., ]*[a-zA-Z]+(?=\\s*$)", Pattern.MULTILINE).matcher(rawMetadata);
        if (authorMatcher.find()) {
            //if it found a declaration, it'll then attempt to omit any leading whitespace, via another Matcher
            String untrimmedAuthor = authorMatcher.group(0);
            Matcher trimmedAuthorMatcher = Pattern.compile("[A-Z]+[a-zA-Z., ]*[a-zA-Z]+").matcher(untrimmedAuthor);
            if (trimmedAuthorMatcher.find()) {
                //the version of the author string without the leading whitespace is then set as the author
                author = trimmedAuthorMatcher.group(0);
                System.out.println(author);
            }
        }
    }

    //checks whether or not the IFID string exists
    public boolean doesStartPassageExist(Set<String> passageNames){
        //returns true if a passage with the same name as the start passage exists
        //returns false otherwise
        return (passageNames.contains(startPassage));
    }

    //returns the start passage string
    public String getStartPassage() { return startPassage; }

    //returns true if IFID was declared, false otherwise
    public boolean doesIFIDExist(){ return isIFIDDeclared; }

    //returns the IFID string
    public String getIFID(){ return ifid; }


    //returns the IFID string but formatted in such a way that it works with the current IFID html declaration stuff (will be removed later)
    public String getIfidButHtmlFormatted(){
        if (isIFIDDeclared) {
            return "<!-- UUID://" + ifid + "// -->\n";
        } else{
            return "<!-- no IFID declared! -->\n";
        }
    }

    //returns the 'title' field of this object
    public String getTitle(){ return title; }

    //returns the filenameTitle attribute
    public String getFilenameTitle(){return filenameTitle; }

    //returns the 'author' field of this object
    public String getAuthor(){ return author; }

    //TODO: iFiction metadata export stuff
    //public String getiFictionMetadata(){}

    //this is only here for debugging reasons, pls to ignore
    public void printDebugData(){
        System.out.println("METADATA OBJECT DEBUG DATA:");
        System.out.println("START PASSAGE: " + startPassage);
        System.out.println("IFID: "+ ifid);
        System.out.println("TITLE: " + title);
        System.out.println("FILENAME TITLE: " + filenameTitle);
        System.out.println("AUTHOR: " + author);
        System.out.println("RAW METADATA:\n" + rawMetadata);
    }


}
