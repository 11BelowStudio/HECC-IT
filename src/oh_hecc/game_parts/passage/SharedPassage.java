package oh_hecc.game_parts.passage;

import oh_hecc.Heccable;
import oh_hecc.Parseable;
import utilities.Vector2D;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An interface for EditablePassage that encapsulates the basic things for both reading/editing a passage.
 */
@SuppressWarnings("RegExpAnonymousGroup")
public interface SharedPassage extends Parseable, Heccable {

    /**
     * The regular expression to be used for the tag string (opening and closing [] omitted)
     * alphanumeric with underscores, divided by spaces
     */
    String TAG_STRING_REGEX = "([\\w]+[ ])*[\\w]+";

    /**
     * Regular expression for direct passage links
     */
    String DIRECT_PASSAGE_LINK_REGEX = "(\\[\\["+ PASSAGE_NAME_REGEX_WITH_WHITESPACE +"]])";

    /**
     * Regular expression for indirect passage links
     */
    String INDIRECT_PASSAGE_LINK_REGEX = "(\\[\\[[\\h]*[^\\[\\]\\|]+[\\h]*\\|"+ PASSAGE_NAME_REGEX_WITH_WHITESPACE +"]])";

    /**
     * Suffix that's appended to the end of the names of deleted passages
     */
    String DELETED_PASSAGE_NAME_PLACEHOLDER_SUFFIX = "! WAS DELETED !";

    /**
     * A regular expression for finding any deleted link placeholders
     */
    String DELETED_PASSAGE_LINK_REGEX = "(\\[\\[([\\h]*[^\\[\\]\\|]+[\\h]*\\|)?" + PASSAGE_NAME_REGEX_WITH_WHITESPACE + DELETED_PASSAGE_NAME_PLACEHOLDER_SUFFIX + "[\\h]*]])";


    /**
     * Checks if given content contains a link to a now-deleted passage.
     * @param content the content to check
     * @return true if it contains a link to a now-deleted passage. false otherwise.
     */
    static boolean doesPassageContentContainDeletedLinks(String content){
        final Matcher deletedLinkMatcher = Pattern.compile(
                DELETED_PASSAGE_LINK_REGEX,
                Pattern.MULTILINE
        ).matcher(content);
        return deletedLinkMatcher.find();
    }


    /**
     * Actually puts the valid tag list string into an ArrayList
     * @param validTagListString the valid tag list string that's being put into the ArrayList (space delimited)
     * @return an ArrayList holding the tag names that the given validTagListString held
     */
    static ArrayList<String> actuallyPutValidTagListStringIntoArrayList(String validTagListString){
        final ArrayList<String> foundTags = new ArrayList<>();
        final String[] tagListArray = validTagListString.split(" "); //splits list string at spaces
        for (String s: tagListArray){ //for each of the tags
            if (!s.isEmpty()){
                foundTags.add(s.trim()); //add it to the tagList
            }
        }
        return foundTags;
    }


    /**
     * A wrapper method for actuallyParseThePassageLinks
     * @param rawContent the raw passage content with the links that are being looked for
     * @return the set with all the names of the passage links
     */
    static Set<String> findLinks(String rawContent){
        //finds the direct links, puts them into the set of found links
        final Set<String> foundLinks = actuallyParseThePassageLinks(rawContent, true);
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
    static Set<String> actuallyParseThePassageLinks(String input, boolean direct){
        final Set<String> foundLinks = new HashSet<>();
        String regex; //local variable for the regex being used
        if (direct){
            //direct link regex [[Passage name]]
            //regex = "(\\[\\[[\\s]*[\\w]+[\\w- ]*[\\w]+[\\s]*]])";
            regex = DIRECT_PASSAGE_LINK_REGEX;
        } else{
            //indirect link regex [[Link Text | Passage Name]]
            //regex = "(\\[\\[[^\\[\\]\\|]+\\|[\\s]*[\\w]+[\\w- ]*[\\w]+[\\s]*]])";
            regex = INDIRECT_PASSAGE_LINK_REGEX;
        }
        //creates the matcher
        final Matcher theMatcher = Pattern.compile(
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
     * Obtains the passage UUID (read-only)
     * @return the identifier for this passage
     */
    UUID getPassageUUID();

    /**
     * Obtains the passage content
     * @return passage content
     */
    String getPassageContent();

    /**
     * Obtains the passage name
     * @return passage name
     */
    String getPassageName();


    /**
     * Obtains the list of passage tags
     * @return passageTags
     */
    List<String> getPassageTags();

    /**
     * Gets the position of this Passage
     * @return the position Vector2D
     */
    Vector2D getPosition();

    /**
     * gets the set of the names of linked passages
     * @return linkedPassages
     */
    Set<String> getLinkedPassages();

    /**
     * gets the set of the UUIDs of linked passages
     * @return the set of UUIDs of linked passages
     */
    Set<UUID> getLinkedPassageUUIDs();

    /**
     * returns the comment that's inline within the passage declaration
     * @return the passage declaration comment
     */
    String getInlinePassageComment();

    /**
     * obtains the trailing comment from the passage
     * @return the trailing comment
     */
    String getTrailingComment();

    /**
     * Gets the passage as a string for debugging reasons
     * @return a string representing this passage for debugging reasons
     */
    String getAsStringForDebuggingReasons();

    /**
     * Obtain an enum representing the current status of the passage
     * @return a passageStatus value for this passage
     */
    PassageStatus getPassageStatus();
}
