package hecc_up;

import hecc_up.heccCeptions.*;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passage {

    //TODO: put the OH-HECC specific stuff into a seperate class to the HECC-UP specific stuff.
    //TODO: make the HECC-UP Passage focus entirely on holding a representation of HECCED-formatted data
    //TODO: maybe make the HECC-UP Passage into a Kotlin data class or something?

    private String passageName;

    private String unparsedContent;

    private String parsedContent;

    private String parsedTags;

    private Set<String> linkedPassages;

    private ArrayList<String> tags;
        //will be used later on, with OH-HECC

    private Vector2D position;
        //will be used later on, with OH-HECC

    //private final Matcher speechMatcher = Pattern.compile("(?<speechmarks>\\\")",Pattern.MULTILINE).matcher("");

    //private final Matcher directLinkMatcher = Pattern.compile("(?<directLinks>\\[\\[[\\w]+[\\w- ]*[\\w]+\\]\\])", Pattern.MULTILINE).matcher("");

    //private final Matcher indirectLinkMatcher = Pattern.compile("(?<indirectLinks>\\[\\[[^\\[\\]\\|]+\\|[\\w]+[\\w- ]*[\\w]+\\]\\])", Pattern.MULTILINE).matcher("");

    //private final Matcher lineEndWhitespaceMatcher = Pattern.compile("\\s*\\R$", Pattern.MULTILINE).matcher("");

    /**
     * Constructs a passage object without any metadata
     * @param passageName the name of this passage
     * @param unparsedContent the raw content of it
     */
    public Passage(String passageName, String unparsedContent){
        this.passageName = passageName.trim();
        this.unparsedContent = unparsedContent.trim();
        linkedPassages = new TreeSet<>();

        System.out.println(unparsedContent);

        //initialises metadata as blank values
        position = new Vector2D();
        tags = new ArrayList<>();
    }

    /**
     * Creates a Passage object with metadata
     * @param passageName the name of this passage
     * @param unparsedContent the raw content of it
     * @param lineEndMetadata the raw metadata of it
     */
    public Passage(String passageName, String unparsedContent, String lineEndMetadata){
        this(passageName,unparsedContent);
        //processes metadata
        readMetadata(lineEndMetadata);

    }

    /**
     * This is a wrapper function for all the metadata reading stuff
     * @param lineEndMetadata
     */
    private void readMetadata(String lineEndMetadata){
        System.out.println(lineEndMetadata);
        tags = readTagMetadata(lineEndMetadata);
        position.set(readVectorMetadata(lineEndMetadata));
    }

    /**
     * This reads the tag metadata stuff
     * @param lineEndMetadata The full line end metadata
     * @return an ArrayList<String> containing all the tag metadata
     */
    private ArrayList<String> readTagMetadata(String lineEndMetadata){
        ArrayList<String> tagList = new ArrayList<>();
        //this processes the list of tags (if they exist)
            //tags may be alphanumeric with underscores, divided by spaces
            //must be within []
            //like '[List of tags divided by spaces and allows d1gits plus under_scores]'
        Matcher tagListMatcher = Pattern.compile(
                "\\[([a-zA-Z0-9_]+ +)*[a-zA-Z0-9_]+]").matcher(lineEndMetadata);
                //finds the tag list metadata
        if (tagListMatcher.find()){ //if found
            String tagListString = tagListMatcher.group(0); //gets it
            tagListString = tagListString.substring(1,tagListString.length()-1); //removes surrounding []
            String[] tagListArray = tagListString.split(" "); //splits it at spaces
            for (String s: tagListArray){ //for each of the tags
                if (!s.isEmpty()){
                    tagList.add(s.trim()); //add it to the tagList
                }
            }
        }
        return tagList; //return the tagList
    }

    /**
     * This reads the position Vector2D metadata
     * @param lineEndMetadata the full line end metadata stuff
     * @return A Vector2D for the OH-HECC position of this passage
     */
    private Vector2D readVectorMetadata(String lineEndMetadata){
        Vector2D readVector = new Vector2D();
        //Searches for it in the form
            //<x,y>
                //x and y are double numbers, may have decimals, and can have leading/trailing whitespace
        Matcher vectorCoordsMatcher = Pattern.compile(
                "<\\s*\\d*\\.?\\d+\\s*,\\s*\\d*\\.?\\d+\\s*>"
        ).matcher(lineEndMetadata);
        if (vectorCoordsMatcher.find()){
            //if it's found, it extracts that string
            String vectorCoordsString = vectorCoordsMatcher.group(0);
            //trims the trailing/leading '<''>' characters
            vectorCoordsString = vectorCoordsString.substring(1,vectorCoordsString.length()-1);
            //converts it into an array, splitting at the comma
            String[] vectorCoordsArray = vectorCoordsString.split(",");
            if(vectorCoordsArray.length > 1){ //if there's 2 (or more) indexes in the array
                int i = 0; //counter thing
                double[] coords = {0,0}; //2d array of doubles to store the coordinate doubles
                while (i < 2){
                    try{
                        //puts the double value of the string in the current index of the array into coords[i]
                        coords[i] = Double.parseDouble(vectorCoordsArray[i]);
                    } catch (Exception e){
                        //sets coords[i] to 0 if no double could be found
                        coords[i] = 0;
                    }
                    i++;
                }
                //sets the coordinates of the readVector accordingly
                readVector.set(coords[0],coords[1]);
            }
        }
        return readVector;
    }

    /**
     * Parses the unparsedContent into parsedContent, and parses the tags into parsedTags
     */
    public void parseContent(){

        //string for the WIP parsed content
        String parsedContentWIP = unparsedContent.trim();

        //escaping html characters
        parsedContentWIP = escapeHtmlCharacters(parsedContentWIP);
        
        //converting links to actual hecc links, and setting up linkedPassages
        parsedContentWIP = parsePassageLinks(parsedContentWIP, true);
        
        //formatting the rest of the passage content
        parsedContent = parsePassageContent(parsedContentWIP);

        System.out.println(parsedContent);

        //and finally, parsing the tags for the passage
        parsedTags = parseTags(tags);

    }

    /**
     * Used to wrap the html character escaping stuff
     * @return version of input with the escape characters escaped
     */
    private String escapeHtmlCharacters(String input){
        input = input.replaceAll("&","&amp"); //escapes ampersands
        input = input.replaceAll("<","&lt"); //escapes <
        input = input.replaceAll(">","&gt"); //escapes >
        input = input.replaceAll("\"","&quot"); //escapes "
        input = input.replaceAll("'","&#39"); //escapes '
        return input;
    }

    /**
     * This is used to convert raw hecc passage content into some decently formatted HTML
     * @param input the passage content to format
     * @return input but with HTML formatting
     */
    private String parsePassageContent(String input){
        
        //TODO: markdown formatting code would probably go here
        
        //putting an opening paragraph tag onto the formatted string
        String formatted = "\"<p>";
        
        //replacing instances of two consecutive newlines with a paragraph end/start of new paragraph
        input = input.replaceAll("\n\n","</p><p>");


        //replacing single newlines with '<br>' tags
        input = input.replaceAll("\n","<br>");
        
        //concatenates the formatted input to the end of the formatted (opening paragraph tag) string
        formatted = formatted.concat(input);
        
        //concatenates a closing paragraph tag to the end of the formatted string
        formatted = formatted.concat("</p>\"");
        
        return formatted; //returns the formatted string
    }

    /**
     * Function used for parsing the passage links
     * @param input the raw string input
     * @param convertLinks whether or not it should bother converting the links into hecc links or not
     * @return the input string but (potentially) with the passage links converted into hecc links
     */
    private String parsePassageLinks(String input, boolean convertLinks){
        linkedPassages.clear(); //clears existing set of linked passages
        //first does the direct links
        input = actuallyParseThePassageLinks(input, true, convertLinks);

        //now the indirect links + returning the string
        return actuallyParseThePassageLinks(input, false, convertLinks);
    }

    /**
     * A method that does all the passage link parsing stuff
     *
     * @param input the input passage text to parse
     * @param direct whether or not it's looking for direct passage links
     * @param converting whether or not it's converting these links into hecc links
     * @return the input passage text with the links converted into hecc links
     */
    private String actuallyParseThePassageLinks(String input, boolean direct, boolean converting){

        String regex; //local variable for the regex being used
        if (direct){
            //direct link regex [[Passage name]]
            regex = "(\\[\\[[\\s]*[\\w]+[\\w- ]*[\\w]+[\\s]*]])";
        } else{
            //indirect link regex [[Link Text | Passage Name]]
            regex = "(\\[\\[[^\\[\\]\\|]+\\|[\\s]*[\\w]+[\\w- ]*[\\w]+[\\s]*]])";
        }
        //creates the matcher
        Matcher theMatcher = Pattern.compile(
                regex,
                Pattern.MULTILINE
        ).matcher(input);
        boolean notDone; //boolean for do while
        do{
            if (notDone = (theMatcher.find())){ //theMatcher attempts to find the regex in the input. notDone if found.
                String temp = theMatcher.group(0); //gets the match
                temp = temp.substring(2, temp.length()-2).trim(); //removes surrounding [[]], trims it
                if (direct){ //if direct links are being found
                    linkedPassages.add(temp); //adds it to linked passage
                    if (converting){ //if converting to links
                        //turns it into a hecced link
                        input = theMatcher.replaceFirst(convertDirectLink(temp));
                        //updates the input for the matcher
                        theMatcher.reset(input);
                    }
                } else{ //if indirect link
                    String[] linkParts = temp.split("\\|"); //splits it into two at the |
                    linkedPassages.add(linkParts[1].trim()); //puts the 2nd part (the passage name) into the linkedPassages set
                    if (converting){ //if converting into links
                        //turns it into a hecced link
                        input = theMatcher.replaceFirst(convertIndirectLink(linkParts));
                        //updates the input for the matcher
                        theMatcher.reset(input);
                    }
                }
            }
        } while (notDone);

        return input; //returns the input stuff

    }

    /**
     * Creates a direct hecc link using the passageName as an input
     * @param passageName the passagename for the direct link
     * @return the direct link html string
     */
    private String convertDirectLink(String passageName){
        return "<a class='passageLink' onclick='theHeccer.goToPassage(\\\\\""
                + passageName
                + "\\\\\")'>"
                + passageName
                + "</a>";
    }

    /**
     * creates an indirect hecc link using a string array of the link parts as an input
     * @param linkParts [0] holds link text, [1] contains name of passage being linked
     * @return the indirect link html string
     */
    private String convertIndirectLink(String[] linkParts){
        return "<a class='passageLink' onclick='theHeccer.goToPassage(\\\\\""
                + linkParts[1].trim()
                + "\\\\\")'>"
                + linkParts[0].trim()
                + "</a>";
    }

    /**
     * This parses the passage tags
     * @param inputTags arrayList of tags to parse
     * @return a string with the array representation of those tags
     */
    private String parseTags(ArrayList<String> inputTags){
        //creating a stringBuilder
        StringBuilder parsedTagBuilder = new StringBuilder();
        //opening the array declaration
        parsedTagBuilder.append("[");
        //if it's not empty, start filling up the array
        boolean notDone;
        if(!inputTags.isEmpty()){
            int i = 0; //cursor
            int size = inputTags.size(); //cba to check this constantly
            do{
                //get the string representation of the parsed tag, put it in parsedTags
                parsedTagBuilder.append("\"");
                parsedTagBuilder.append(inputTags.get(i));
                parsedTagBuilder.append("\"");
                //increment i
                i++;
                //notDone is true if i is still smaller than the size of the input array
                if(notDone = (i < size)){
                    //concatenate a comma onto the parsedTags if there's more parsed tags to come
                    parsedTagBuilder.append(",");
                }
                //keep on doing it until it's finally done
            } while (notDone);
        }

        parsedTagBuilder.append("]"); //finally close the array declaration
        return parsedTagBuilder.toString(); //return the string that was built
    }
    

    /**
     * A public method, callable by anything, that can update the linkedPassages from the unparsedContent
     */
    public void updateLinkedPassages(){
        parsePassageLinks(unparsedContent,false);
    }

    /**
     * This can be used to check if all the linked passages actually exist, returning the result as a boolean
     *
     * @param allPassages The set of all the declared passages
     * @return true if all the passages this passage links to exist, false otherwise
     */
    public boolean validateLinkedPassages(Set<String> allPassages){
        try {
            validateLinkedPassagesThrowingException(allPassages);
            return true;
        } catch (UndefinedPassageException e){
            return false;
        }
    }

    /**
     * This can be used to check if all the linked passages actually exist, throwing an exception if they don't exist
     * 
     * @param allPassages The set of all declared passages
     * @throws UndefinedPassageException if one of this passage's linked passages don't actually exist
     */
    public void validateLinkedPassagesThrowingException(Set <String> allPassages) throws UndefinedPassageException{
        for (String s : linkedPassages) {
            if (!allPassages.contains(s)) {
                throw new UndefinedPassageException(s);
            }
        }
    }

    /**
     * Gets the HECCED.js representation of this passage
     * 
     * @return A string with the necessary JavaScript code to construct this passage
     */
    public String getHeccedRepresentation(){
        //making a StringBuilder
        StringBuilder heccedBuilder = new StringBuilder();
        
        //starting constructor
        heccedBuilder.append("\ttheHeccer.addPassageToMap(\n\t\tnew Passage(\n\t\t\t\"");

        //passage name
        heccedBuilder.append(passageName);

        //end of passage name, moving to content
        heccedBuilder.append("\",\n\t\t\t");

        //parsed content concatenated
        heccedBuilder.append(parsedContent);

        //and y'know what, may as well shove the tags for the passage in as well, why the hecc not, might come in useful later on
        heccedBuilder.append(",\n\t\t\t");

        //parsed tags concatenated
        heccedBuilder.append(parsedTags);

        //end of the declaration, finishing up the line of code
        heccedBuilder.append("\n\t\t)\n\t);\n\n\n");

        //returning the hecced string
        return heccedBuilder.toString();
    }

    /**
     * This just prints the passage info, for debugging reasons.
     */
    public void printPassageInfoForDebuggingReasons(){

        System.out.println("Passage name: " + passageName);
        System.out.println("Unparsed passage content:\n" + unparsedContent);
        System.out.println("Parsed passage content:\n"+ parsedContent);
        System.out.println("Position: " + position);
        System.out.println("Parsed tags: " + tags);
        System.out.println("Linked passages: " + linkedPassages);

    }




}
