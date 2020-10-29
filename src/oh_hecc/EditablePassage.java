package oh_hecc;

import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditablePassage {

    /**
     * This is basically here as a generator for a unique ID number for each passage
     */
    private static int PASSAGE_ID_COUNTER = 0;

    /**
     * Called once in the constructor of a passage object to give it a unique ID number.
     * Obtains the current value of the PASSAGE_ID_COUNTER, increments the static COUNTER,
     * but returns the non-incremented value as the ID
     * @return a unique int as an identifier for the given passage
     */
    private static synchronized int GENERATE_PASSAGE_IDENTIFIER(){
        int obtainedIdentifier = PASSAGE_ID_COUNTER;
        PASSAGE_ID_COUNTER++;
        return obtainedIdentifier;
    }

    /**
     * The passageID of this particular passage
     */
    private final int passageID;

    /**
     * The name of this passage
     */
    private String passageName;

    /**
     * The actual raw contents of this passage
     */
    private String passageContent;

    /**
     * The comment that's inline with the passage declaration
     */
    private String inlinePassageComment;

    /**
     * The set with the names of the other passages this passage links to
     */
    private Set<String> linkedPassages;

    /**
     * The string that's the multiline comment behind this particular passage
     */
    private String trailingComment;

    /**
     * The list of tags that this passage has
     */
    private List<String> passageTags;

    /**
     * The position of this passage
     */
    private final Vector2D position;


    /**
     * A no-argument constructor for an editable passage. Will be called when the 'new passage' button is pressed.
     */
    public EditablePassage(){
        this.passageID = EditablePassage.GENERATE_PASSAGE_IDENTIFIER();
        passageName = "Untitled Passage #"+passageID;
        passageContent = "Sample Content";
        passageTags = new ArrayList<>();
        inlinePassageComment = "";
        trailingComment = "";
        position = new Vector2D();
        linkedPassages = new TreeSet<>();
    }

    /**
     * Will be called when a user creates a link to a passage which doesn't exist yet, creating a passage with that name
     * @param passageName the name of the passage to create
     * @param parentPosition the position of the parent passage that made this one
     */
    public EditablePassage(String passageName, Vector2D parentPosition){
        this();
        this.passageName = passageName;
        this.position.set(parentPosition.add(0, 100)); //100 below parent passage
    }

    /**
     * A constructor for an EditablePassage object read from a .HECC file
     * @param passageName the name given to this passage
     * @param unparsedContent the raw content of this passage
     * @param comment the trailing comment for this passage
     * @param lineEndMetadata the metadata at the end of the declaration line
     */
    public EditablePassage(String passageName, String unparsedContent, String comment, String lineEndMetadata){
        this();
        this.passageName = passageName;
        passageContent = unparsedContent;
        trailingComment = comment;
        passageTags = EditablePassage.readTagMetadata(lineEndMetadata);
        position.set(EditablePassage.readVectorMetadata(lineEndMetadata));
        inlinePassageComment = EditablePassage.getInlineComment(lineEndMetadata);
        linkedPassages = EditablePassage.findLinks(unparsedContent);
    }

    /**
     * This reads the tag metadata stuff
     * @param lineEndMetadata The full line end metadata
     * @return an ArrayList<String> containing all the tag metadata
     */
    private static ArrayList<String> readTagMetadata(String lineEndMetadata){
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
    private static Vector2D readVectorMetadata(String lineEndMetadata){
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
     * Obtains the inline comment from the end of the line end metadata
     * @param lineEndMetadata the line end metadata
     * @return The comment of it (everything between a // and the end of the line).
     * returns empty string if no comment is found.
     */
    private static String getInlineComment(String lineEndMetadata){
        Matcher inlineCommentMatcher = Pattern.compile(
                "((?<=\\/\\/).*)"
        ).matcher(lineEndMetadata); //matches everything between the first // and the end of the line
        String theComment = ""; //blank comment by default
        if (inlineCommentMatcher.find()){
            theComment = inlineCommentMatcher.group(0).trim(); //obtains the comment (and also trims it) if it exists
            //System.out.println(theComment);
        }
        return theComment; //returns the comment

    }

    /**
     * A wrapper method for actuallyParseThePassageLinks
     * @param rawContent the raw passage content with the links that are being looked for
     * @return the set with all the names of the passage links
     */
    static Set<String> findLinks(String rawContent){
        //finds the direct links, puts them into the set of found links
        Set<String> foundLinks = actuallyParseThePassageLinks(rawContent, true);
        //adds the indirect links to the set of found links
        foundLinks.addAll(actuallyParseThePassageLinks(rawContent,false));
        //returns the found links
        return foundLinks;
    }

    /**
     * A method that finds passage links in the unparsed content
     * @param input the input passage text to parse
     * @param direct whether or not it's looking for direct passage links
     * @return the input passage text with the links converted into hecc links
     */
    private static Set<String> actuallyParseThePassageLinks(String input, boolean direct){
        Set<String> foundLinks = new TreeSet<String>();
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
                    foundLinks.add(temp); //adds it to linked passage
                } else{ //if indirect link
                    String[] linkParts = temp.split("\\|"); //splits it into two at the |
                    foundLinks.add(linkParts[1].trim()); //puts the 2nd part (the passage name) into the linkedPassages set
                }
            }
        } while (notDone);
        return foundLinks; //returns the set of the found links
    }

    /**
     * This will update the given rawContent such that all links with the old passage name will be updated to link to the new passage name
     * @param rawContent the raw passage content that needs updating
     * @param oldPassageName the old passage name which is being looked for
     * @param renameTo the renamed passage that the links must be redirected to
     * @return the rawContent but with the appropriate passage links renamed
     */
    static String getPassageContentWithRenamedLinks(String rawContent, String oldPassageName, String renameTo){
        //if (linkedPassages.contains(oldPassageName)) {
        rawContent = rawContent.replaceAll(
                "(\\[\\[[\\s]*" + oldPassageName + "[\\s]*]])",
                "[[" + renameTo + "]]"
        ); //direct links can be replaceall'd ez
        //indirect links are more tricky.
        Matcher indirectMatcher = Pattern.compile(
                "(\\[\\[[^\\[\\]\\|]+\\|[\\s]*" + oldPassageName + "[\\s]*]])",
                Pattern.MULTILINE
        ).matcher(rawContent); //first it needs to find the indirect links
        while (indirectMatcher.find()) { //whist it can find an indirect link
            String currentMatch = indirectMatcher.group(0); //finds the first one
            String currentRenamed = currentMatch.replaceAll(
                    "(\\|[\\s]*" + oldPassageName + "[\\s]*]])",
                    "|" + renameTo + "]]"
            ); //makes a copy of that match with the old passage name replaced with the new passage name
            rawContent = rawContent.replace(currentMatch, currentRenamed); //replaces the match with the updated version of it
            indirectMatcher.reset(rawContent); //resets the matcher, so it can now look for the next instance of the indirect link
        }
        //}
        return rawContent; //returns the updated rawContent
    }


    /**
     * This method will be used when the passage content needs to be updated.
     * Replaces the passageContent, and updates the linkedPassages appropriately
     * @param newContent the new content that the passage now holds
     */
    void updatePassageContent(String newContent){
        passageContent = newContent; //replaces the content
        linkedPassages = findLinks(newContent); //updates the linked passages appropriately
    }

    /**
     * Obtains the passage ID (read-only)
     * @return the identifier for this passage
     */
    int getPassageID(){
        return this.passageID;
    }

    /**
     * Obtains the passage content
     * @return passage content
     */
    String getPassageContent(){
        return this.passageContent;
    }

    /**
     * Obtains the passage name
     * @return passage name
     */
    String getPassageName(){
        return this.passageName;
    }

    /**
     * Obtains the list of passage tags
     * @return passageTags
     */
    List<String> getPassageTags(){
        return passageTags;
    }

    /**
     * Removes a specified tag from the passageTags
     * @param tagToRemove the tag that needs to be removed
     * @return whether or not it was successful
     */
    boolean removePassageTag(String tagToRemove){
        return passageTags.remove(tagToRemove);
    }

    /**
     * Add a new specified tag to the passageTags
     * @param tagToAdd the tag to add
     * @return whether or not it was successfullly added
     */
    boolean addPassageTag(String tagToAdd){
        return passageTags.add(tagToAdd);
    }

    /**
     * Updates the position of this Passage
     * @param newPosition the new position of this passage
     */
    void updatePosition(Vector2D newPosition){
        position.set(newPosition);
    }

    /**
     * Gets the position of this Passage
     * @return the position Vector2D
     */
    Vector2D getPosition(){
        return position;
    }

    /**
     * gets the set of linked passages
     * @return linkedPassages
     */
    Set<String> getLinkedPassages(){
        return linkedPassages;
    }

    /**
     * returns the comment that's inline within the passage declaration
     * @return the passage declaration comment
     */
    String getInlinePassageComment(){
        return inlinePassageComment;
    }

    /**
     * Updates the inline passage declaration comment
     * @param newComment the new comment that goes in there
     */
    void setInlinePassageComment(String newComment){
        inlinePassageComment = newComment;
    }

    /**
     * obtains the trailing comment from the passage
     * @return the trailing comment
     */
    String getTrailingComment(){
        return trailingComment;
    }

    /**
     * updates the trailing passage comment
     * @param newComment the new trailing comment for the passage
     */
    void setTrailingComment(String newComment){
        trailingComment = newComment;
    }

    /**
     * This obtains a version of this passage in .hecc format
     * @return this passage but in HECC format
     */
    String toHecc(){
        StringBuilder heccBuilder = new StringBuilder();
        //Creating passage declaration
        heccBuilder.append("::");
        heccBuilder.append(passageName);
        heccBuilder.append(" ");
        //and now, the passage tags
        heccBuilder.append(getHeccPassageTags(passageTags));
        heccBuilder.append(" ");
        //and now, the position vector
        heccBuilder.append(getHeccPosition(position));
        heccBuilder.append(" ");
        //and now, the inline comment
        heccBuilder.append("//");
        heccBuilder.append(inlinePassageComment);
        heccBuilder.append("\n");
        //and now, the content
        heccBuilder.append(passageContent);
        //and finally, the trailing comment
        heccBuilder.append("\n;;\n");
        heccBuilder.append(trailingComment);
        //end with a newline, and then return the string
        heccBuilder.append("\n");
        return heccBuilder.toString();
    }

    /**
     * Obtains the passage tags in .hecc-compatiable format
     * @param passageTags the List of passageTags
     * @return the list of passage tags as a string in the format [list of tags]
     */
    private String getHeccPassageTags(List<String> passageTags){
        StringBuilder passageTagBuilder = new StringBuilder();
        passageTagBuilder.append("[");
        for(int i = 0; i < passageTags.size(); i++){
            passageTagBuilder.append(passageTags.get(i));
            if (i < passageTags.size()-1){
                passageTagBuilder.append(" ");
            }
        }
        passageTagBuilder.append("]");
        return passageTagBuilder.toString();

    }

    /**
     * Obtains the position Vector2D in .hecc format
     * @param position the vector2D to be made into .hecc
     * @return a string version of the Vector2D, in the format < x,y> (except without that space)
     */
    private String getHeccPosition(Vector2D position){
        StringBuilder posBuilder = new StringBuilder();
        posBuilder.append("<");
        posBuilder.append(position.x);
        posBuilder.append(",");
        posBuilder.append(position.y);
        posBuilder.append(">");
        return posBuilder.toString();
    }

}