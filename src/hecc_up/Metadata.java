package hecc_up;

import hecc_up.heccCeptions.NoMatchException;
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

    private boolean isIfidDeclared = false; //IFID not declared by default
    private String ifid; //holds the IFID if it is declared

    //constructs this Metadata object with actual raw metadata to parse
    public Metadata(String givenMetadata, boolean isThereActuallyMetadata){
        rawMetadata = givenMetadata;
        hasMetadata = isThereActuallyMetadata;
    }

    //wrapper function for the metadata parsing stuff

    /**
     * Wrapper data for the metadata parsing stuff
     * Does nothing if there's no metadata to parse
     */
    public void parseMetadata(){
        if (hasMetadata){ //only bothers doing this if there actually is any metadata to parse
            findStartPassage();
            findIFID();
            findTitle();
            findAuthor();
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
     */
    private void findStartPassage(){
        /*
        finds the declaration for the starting passage,
            in a string defined as starting with '!StartPassageName:',
            allowing some whitespace, then the starting passage name,
            then allowing trailing whitespace.
        This first matcher will find the passage name, along with any leading whitespace
        line must be of the form '!StartPassageName: starting passage name'
         */
        try{
            startPassage = metadataRegexHandler(
                "(?<=^!StartPassageName:)\\s*[\\w]+[\\w- ]*[\\w]+(?=\\s*$)",
                rawMetadata
            );
        } catch (NoMatchException e){
            startPassage = "Start";
        }
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
                "(?<=^!IFID:)\\s*[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}(?=\\s*$)",
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
                "(?<=^!StoryTitle:)\\s*[\\S]+[\\S ]*[\\S]+(?=\\s*$)",
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
                "(?<=^!Author:)\\s*[A-Za-z]+[a-zA-Z., ]*[a-zA-Z]+(?=\\s*$)",
                rawMetadata
            );
            isAuthorDeclared = true;
        } catch (NoMatchException e){
            author = "Anonymous";
            isAuthorDeclared = false;
        }
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
                        + "!IFID: " + IFIDgenerator.generateIFIDString()
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
     * Returns the IFID string, but formatted such that it's usable in the HTML page
     * @return the appropriate string for declaring the IFID in the HTML page
     */
    public String getIfidButHtmlFormatted(){
        if (isIfidDeclared) {
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
