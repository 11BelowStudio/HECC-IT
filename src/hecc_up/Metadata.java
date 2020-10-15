package hecc_up;

import utilities.IFIDgenerator;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Metadata {

    //TODO: maybe encapsulate HECC-UP specific functions in a seperate object to OH-HECC specific functions?
    
    private String rawMetadata; //string with the raw metadata
    private boolean hasMetadata; //boolean value to keep track of whether or not it actually has metadata

    //These are going to be used to store the metadata about the game title and the author name
    private String title = "An Interactive Fiction"; //default title used if undefined
    private boolean isTitleDeclared = false; //title undeclared by default

    private String author = "Anonymous"; //default author name used if undefined
    private boolean isAuthorDeclared = false; //author undeclared by default
    
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
                isTitleDeclared = true;
                System.out.println(title);
            }
        }
    }

    //TODO: method to find Author declaration
    private void findAuthor(){
        /*
        Attempts to find the name of the author
            Must start with a ~~capital~~ letter, and must end in a letter (uppercase or lowercase)
            May have any number of letters (any case), full stops (for initials), commas (for multiple authors), and spaces
         */
        Matcher authorMatcher = Pattern.compile("(?<=^!Author:)\\s*[A-Za-z]+[a-zA-Z., ]*[a-zA-Z]+(?=\\s*$)", Pattern.MULTILINE).matcher(rawMetadata);
        if (authorMatcher.find()) {
            //if it found a declaration, it'll then attempt to omit any leading whitespace, via another Matcher
            String untrimmedAuthor = authorMatcher.group(0);
            Matcher trimmedAuthorMatcher = Pattern.compile("[A-Za-z]+[a-zA-Z., ]*[a-zA-Z]+").matcher(untrimmedAuthor);
            if (trimmedAuthorMatcher.find()) {
                //the version of the author string without the leading whitespace is then set as the author
                author = trimmedAuthorMatcher.group(0);
                isAuthorDeclared = true;
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

    //returns true if all optional metadata was declared, false otherwise
    public boolean isAllOptionalMetadataDeclared(){
        return (isIFIDDeclared && isAuthorDeclared && isTitleDeclared);
    }

    //will output a string containing instructions for how to output all currently undeclared optional metadata
    public String outputMetadataDefinitionInstructions(){
        if (isAllOptionalMetadataDeclared()){
            return "No metadata problems detected!";
        } else {
            StringBuilder instructionBuilder = new StringBuilder("\n");
            instructionBuilder.append("Your .hecc file appears to be missing some optional metadata. Here's what's wrong, and what you need to insert before the first passage declaration to fix this issue:\n");
            if (!isIFIDDeclared) {
                instructionBuilder.append(
                        "No Interactive Fiction Identifier declaration found! You can fix this with this line of code:\n"
                        + "!IFID: " + IFIDgenerator.generateIFIDString()
                        + "\n"
                );
            }
            if (!isTitleDeclared) {
                instructionBuilder.append(
                        "No title declaration found! You can fix this with this line of code:\n"
                        + "!StoryTitle: INSERT TITLE HERE"
                        + "\n"
                );
            }
            if (!isAuthorDeclared){
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

    //this is only here for debugging reasons, pls to ignore
    public void printDebugData(){
        System.out.println("METADATA OBJECT DEBUG DATA:");
        System.out.println("START PASSAGE: " + startPassage);
        System.out.println("IFID: "+ ifid);
        System.out.println("TITLE: " + title);
        System.out.println("AUTHOR: " + author);
        System.out.println("RAW METADATA:\n" + rawMetadata);
    }


}
