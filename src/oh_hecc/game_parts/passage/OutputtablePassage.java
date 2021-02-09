package oh_hecc.game_parts.passage;

import heccCeptions.UndefinedPassageException;
import utilities.Vector2D;

import java.util.*;

public class OutputtablePassage extends AbstractPassage implements PassageReadingInterface, PassageOutputtingInterface {


    private final String parsedTags;

    public OutputtablePassage(String passageName, String unparsedContent){
        super(passageName, unparsedContent);
        parsedTags = parseTags(new ArrayList<>());
        cleanUpContent();
    }

    public OutputtablePassage(String passageName, String unparsedContent, String lineEndMetadata){
        super(passageName,unparsedContent,lineEndMetadata);
        parsedTags = parseTags(passageTags);
        cleanUpContent();
    }

    /**
     * Basically just escaping all the newlines and speechmarks
     */
    private void cleanUpContent(){
        passageContent = passageContent.replaceAll("\n","\\\\n");
        passageContent = passageContent.replaceAll("\"","\\\\\""); //escapes "
    }



    /**
     * This parses the passage tags
     * @param inputTags arrayList of tags to parse
     * @return a string with the array representation of those tags
     */
    private String parseTags(List<String> inputTags){
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
     * This can be used to check if all the linked passages actually exist, returning the result as a boolean
     *
     * @param allPassages The set of all the declared passages
     * @return true if all the passages this passage links to exist, false otherwise
     */
    public boolean validateLinkedPassages(Set<String> allPassages){
        if (linkedPassages.isEmpty()){
            return true; //don't bother checking if it's empty
        }
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
        heccedBuilder.append("\",\n\t\t\t\"");

        //parsed content concatenated
        heccedBuilder.append(passageContent);

        //and y'know what, may as well shove the tags for the passage in as well, why the hecc not, might come in useful later on
        heccedBuilder.append("\",\n\t\t\t");

        //parsed tags concatenated
        heccedBuilder.append(parsedTags);

        //end of the declaration, finishing up the line of code
        heccedBuilder.append("\n\t\t)\n\t);\n");

        //returning the hecced string
        return heccedBuilder.toString();
    }


    @Override
    public String toHecc() {
        return "";
    }

    @Override
    public UUID getPassageUUID() {
        return UUID.randomUUID();
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D();
    }

    @Override
    public Set<UUID> getLinkedPassageUUIDs() {
        return new HashSet<>();
    }

    @Override
    public String getInlinePassageComment() {
        return "";
    }

    @Override
    public String getTrailingComment() {
        return "";
    }

    /**
     * This just prints the passage info, for debugging reasons.
     */
    public void printPassageInfoForDebuggingReasons(){
        System.out.println("Passage name: " + passageName);
        System.out.println("Passage content:\n" + passageContent);
        System.out.println("Parsed tags: " + parsedTags);
        System.out.println("Linked passages: " + linkedPassages);
        System.out.println("end passage data");
    }

    @Override
    public String outputAsStringForDebuggingReasons() {
        return "Passage name: " + passageName +
                "\nPassage content:\n" + passageContent +
                "\nParsed tags: " + parsedTags +
                "\nLinked passages: " + linkedPassages +
                "\nend passage data";

    }
}