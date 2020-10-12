package hecc_up;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Metadata {
    
    private String rawMetadata; //string with the raw metadata
    private boolean hasMetadata; //boolean value to keep track of whether or not it actually has metadata

    //These are going to be used to store the metadata about the game title and the author name
    private String title = "An Interactive Fiction";
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
            //TODO: find title
            //TODO: find author
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
        Matcher startPassageDeclarationMatcher = Pattern.compile("(?<=^!StartPassageName:)\\s*[\\w]+[\\w- ]*[\\w]+(?=\\s*$)", Pattern.MULTILINE).matcher(rawMetadata);
        if (startPassageDeclarationMatcher.find()) {
            //if it found it, it'll then attempt to omit any leading whitespace, via another Matcher
            String leadingWhitespaceStartPassageName = startPassageDeclarationMatcher.group(0);
            //System.out.println(leadingWhitespaceStartPassageName);
            Matcher startPassageWithoutLeadingWhitespaceMatcher = Pattern.compile("[\\w]+[\\w- ]*[\\w]+").matcher(leadingWhitespaceStartPassageName);
            if (startPassageWithoutLeadingWhitespaceMatcher.find()) {
                //the version of the start passage name with the leading whitespace is then set as the starting passage name
                startPassage = startPassageWithoutLeadingWhitespaceMatcher.group(0);
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
        Matcher ifidDeclarationMatcher = Pattern.compile("(?<=^!IFID:)\\s*[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}(?=\\s*$)", Pattern.MULTILINE).matcher(rawMetadata);
        if (ifidDeclarationMatcher.find()) {
            //if found, it then needs to yeet the leading whitespace
            String leadingWhitespaceIFID = ifidDeclarationMatcher.group(0);
            Matcher ifidMatcher = Pattern.compile("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}").matcher(leadingWhitespaceIFID);
            if (ifidMatcher.find()) {
                ifid = ifidMatcher.group(0).toUpperCase();
                //stringIFID = "<!-- UUID://"+ifidMatcher.group(0).toUpperCase()+"// -->";
                //here's the string that needs to be included in index.html for the IFID stuff to work (converted to uppercase because pretty much every other html story format uses uppercase so I may as well do that)
                isIFIDDeclared = true;
            }
        }
    }

    //TODO: method to find Title declaration
    private void findTitle(){

    }

    //TODO: method to find Author declaration
    private void findAuthor(){

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

    //returns the 'author' field of this object
    public String getAuthor(){ return author; }

    //TODO: iFiction metadata export stuff
    //public String getiFictionMetadata(){}

    public void printDebugData(){
        System.out.println("METADATA OBJECT DEBUG DATA:");
        System.out.println("START PASSAGE: " + startPassage);
        System.out.println("IFID: "+ ifid);
        System.out.println("TITLE: " + title);
        System.out.println("AUTHOR: " + author);
        System.out.println("RAW METADATA:\n" + rawMetadata);
    }


}
