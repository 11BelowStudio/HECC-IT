package hecc_up;

import hecc_up.heccCeptions.*;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passage {

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


    public Passage(String passageName, String unparsedContent){
        this.passageName = passageName;
        this.unparsedContent = unparsedContent;
        linkedPassages = new TreeSet<>();

        System.out.println(unparsedContent);

        //TODO: initialise metadata as blank values
        position = new Vector2D();
        tags = new ArrayList<>();
    }

    public Passage(String passageName, String unparsedContent, String lineEndMetadata){
        this(passageName,unparsedContent);
        System.out.println(lineEndMetadata);
        //TODO: process metadata

        //this processes the list of tags (if they exist)
            //tags may be alphanumeric with underscores, divided by spaces
            //must be within []
                //like '[List of tags divided by spaces and allows d1gits plus under_scores]'
        Matcher tagListMatcher = Pattern.compile("\\[([a-zA-Z0-9_]+\\ +)*[a-zA-Z0-9_]+\\]").matcher(lineEndMetadata);
        if (tagListMatcher.find()){
            String tagListString = tagListMatcher.group(0);
            tagListString = tagListString.substring(1,tagListString.length()-1);
            String[] tagListArray = tagListString.split(" ");
            for (String s: tagListArray){
                if (!s.isEmpty()){
                    tags.add(s);
                }
            }
        }

        //this initialises the position vector (if it's declared)

        //Searches for it in the form
            //<x,y>
                //x and y are double numbers, may have decimals, and can have leading/trailing whitespace
        Matcher vectorCoordsMatcher = Pattern.compile("<\\s*\\d*\\.?\\d+\\s*,\\s*\\d*\\.?\\d+\\s*>").matcher(lineEndMetadata);
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
                //sets 'position' accordingly
                this.position.set(coords[0],coords[1]);
                // System.out.println(position);
            }
        }


    }

    public void parseContent(){

        String parsedContentWIP; //string for the WIP parsed content

        //escaping all &, <, >, ", and '
        parsedContentWIP = unparsedContent.replaceAll("&","&amp");
        parsedContentWIP = parsedContentWIP.replaceAll("<","&lt");
        parsedContentWIP = parsedContentWIP.replaceAll(">","&rt");
        parsedContentWIP = parsedContentWIP.replaceAll("\"","&quot");
        parsedContentWIP = parsedContentWIP.replaceAll("'","&#39");

        /*
        //escaping all speechmakrs within the parsed content
        Matcher speechMatcher = Pattern.compile("(?<speechmarks>\\\")",Pattern.MULTILINE).matcher(unparsedContent);
        //speechMatcher.reset(unparsedContent);
        parsedContentWIP = speechMatcher.replaceAll("\\\\\"");
        */


        boolean notDone;

        linkedPassages.clear(); //wiping records of linked passages


        //converting links to actual hecc links
        Matcher directLinkMatcher = Pattern.compile("(?<directLinks>\\[\\[[\\w]+[\\w- ]*[\\w]+\\]\\])", Pattern.MULTILINE).matcher(parsedContentWIP);
        //directLinkMatcher.reset(parsedContentWIP);
        do{
            notDone = directLinkMatcher.find();
            if (notDone){
                String temp = directLinkMatcher.group(0);
                temp = temp.substring(2, temp.length()-2);
                linkedPassages.add(temp);
                String link = "<a class='passageLink' onclick='theHeccer.goToPassage(\\\\\""+temp+"\\\\\")'>"+temp+"</a>";
                parsedContentWIP = directLinkMatcher.replaceFirst(link);
                directLinkMatcher.reset(parsedContentWIP);
            }
        } while(notDone);


        //converting indirect links to actual hecc links
        Matcher indirectLinkMatcher = Pattern.compile("(?<indirectLinks>\\[\\[[^\\[\\]\\|]+\\|[\\w]+[\\w- ]*[\\w]+\\]\\])", Pattern.MULTILINE).matcher(parsedContentWIP);
        //indirectLinkMatcher.reset(parsedContentWIP);
        do{
            notDone = indirectLinkMatcher.find();
            if (notDone){
                String temp = indirectLinkMatcher.group(0);
                //System.out.println(temp);
                temp = temp.substring(2, temp.length()-2);
                //System.out.println(temp);
                String[] linkParts = temp.split("\\|");
                linkedPassages.add(linkParts[1]);
                String link = "<a class='passageLink' onclick='theHeccer.goToPassage(\\\\\""+linkParts[1]+"\\\\\")'>"+linkParts[0]+"</a>";
                //System.out.println(link);
                parsedContentWIP = indirectLinkMatcher.replaceFirst(link);
                indirectLinkMatcher.reset(parsedContentWIP);
            }
        } while(notDone);


        //putting an opening speechmark and opening '<p>' tag in the parsed content
        parsedContentWIP = "\"<p>".concat(parsedContentWIP);

        //concatenating the WIP parsed content to the parsed content
        //parsedContent = parsedContent.concat(parsedContentWIP);

        //replacing two newlines with a paragraph end/start of new paragraph
        parsedContentWIP = parsedContentWIP.replaceAll("\n\n","</p><p>");

        //and now, removing trailing empty <p></p>s from the parsed content
        do{
            int lengthMinus7 = parsedContentWIP.length()-7;
            String temp = (parsedContentWIP.substring(lengthMinus7));
            notDone = temp.equals("<p></p>");
            if (notDone){
                parsedContentWIP = parsedContentWIP.substring(0,lengthMinus7);
            }
        } while (notDone);

        //replacing single newlines with '<br>' tags
        parsedContentWIP = parsedContentWIP.replaceAll("\n","<br>");

        //removing trailing <br>s from the WIP parsed content
        do{
            int lengthMinus4 = parsedContentWIP.length()-4;
            String temp = (parsedContentWIP.substring(lengthMinus4));
            notDone = temp.equals("<br>");
            if (notDone){
                parsedContentWIP = parsedContentWIP.substring(0,lengthMinus4);
            }
        } while (notDone);


        //concatenating a closing '</p>' tag (and closing speechmark) to the WIP parsed content, and making that the final parsed content
        parsedContent = parsedContentWIP.concat("</p>\"");

        System.out.println(parsedContent);

        //and now, the parsed tags for the passage

        //opening the array declaration
        parsedTags = "[";
        //if it's not empty, start filling up parsedTags
        if(!tags.isEmpty()){
            int i = 0; //cursor
            do{
                //get the string representation of the parsed tag, put it in parsedTags
                parsedTags = parsedTags.concat("\""+tags.get(i)+"\"");
                //increment i
                i++;
                //notDone is true if i is still smaller than the size of tags
                if(notDone = (i < tags.size())){
                    //concatenate a comma onto the parsedTags if there's more parsed tags to come
                    parsedTags = parsedTags.concat(",");
                }
                //keep on doing it until it's finally done
            } while (notDone);
        }

        parsedTags = parsedTags.concat("]"); //finally close the array declaration

    }

    public void updateLinkedPassages(){

        linkedPassages.clear(); //clears existing set of linked passages

        boolean notDone;

        //finds direct link passages (links with the name of the passage visible)
        Matcher directLinkMatcher = Pattern.compile("(?<directLinks>\\[\\[[\\w]+[\\w- ]*[\\w]+\\]\\])", Pattern.MULTILINE).matcher(unparsedContent);

        do{
            notDone = directLinkMatcher.find();
            if (notDone){
                String temp = directLinkMatcher.group(0);
                temp = temp.substring(2, temp.length()-2);
                linkedPassages.add(temp); //adds the passage names to linkedPassages
            }
        } while(notDone);

        //now checks the indirectly linked passages (links that dont show the name of the passage)
        Matcher indirectLinkMatcher = Pattern.compile("(?<indirectLinks>\\[\\[[^\\[\\]\\|]+\\|[\\w]+[\\w- ]*[\\w]+\\]\\])", Pattern.MULTILINE).matcher(unparsedContent);
        do{
            notDone = indirectLinkMatcher.find();
            if (notDone){
                String temp = indirectLinkMatcher.group(0);
                temp = temp.substring(2, temp.length()-2);
                String[] linkParts = temp.split("\\|");
                linkedPassages.add(linkParts[1]); //passage name added to linkedPassages
            }
        } while(notDone);


    }

    public boolean validateLinkedPassages(Set<String> allPassages){
        try {
            validateLinkedPassagesThrowingException(allPassages);
            return true;
        } catch (ParserException e){
            e.printStackTrace();
            return false;
        }
    }

    public void validateLinkedPassagesThrowingException(Set <String> allPassages) throws UndefinedPassageException{
        for (String s : linkedPassages) {
            if (!allPassages.contains(s)) {
                throw new UndefinedPassageException(s);
            }
        }
    }

    public String getHeccedRepresentation(){
        //starting the constructor
        String heccedRepresentation = "\ttheHeccer.addPassageToMap(\n\t\tnew Passage(\n\t\t\t\"";

        //passage name
        heccedRepresentation = heccedRepresentation.concat(passageName);

        //end of passage name, moving to content
        heccedRepresentation = heccedRepresentation.concat("\",\n\t\t\t");

        //parsed content concatenated
        heccedRepresentation = heccedRepresentation.concat(parsedContent);

        //and y'know what, may as well shove the tags for the passage in as well, why the hecc not, might come in useful later on
        heccedRepresentation = heccedRepresentation.concat(",\n\t\t\t");

        //parsed tags concatenated
        heccedRepresentation = heccedRepresentation.concat(parsedTags);

        //end of the declaration, finishing up the line of code
        heccedRepresentation = heccedRepresentation.concat("\n\t\t)\n\t);\n\n\n");

        //returning the heccedRepresentation
        return heccedRepresentation;
    }


    public void printPassageInfoForDebuggingReasons(){

        System.out.println("Passage name: " + passageName);
        System.out.println("Unparsed passage content:\n" + unparsedContent);
        System.out.println("Parsed passage content:\n"+ parsedContent);
        System.out.println("Position: " + position);
        System.out.println("Parsed tags: " + tags);
        System.out.println("Linked passages: " + linkedPassages);

    }




}
