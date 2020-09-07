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

    private Set<String> linkedPassages;

    private ArrayList<String> tags;
        //will be used later on, with OH-HECC

    //private Vector2D position;
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
        //position = new Vector2D();
        tags = new ArrayList();
    }

    public Passage(String passageName, String unparsedContent, String lineEndMetadata){
        this(passageName,unparsedContent);
        System.out.println(lineEndMetadata);
        //TODO: process metadata

        Matcher tagListMatcher = Pattern.compile("\\[.*\\]").matcher(lineEndMetadata);
        if (tagListMatcher.find()){
            String tagListString = tagListMatcher.group(0);
            tagListString = tagListString.substring(1,tagListString.length()-1);
            String[] tagListArray = tagListString.split(" ");
            tags.addAll(Arrays.asList(tagListArray));
            //System.out.println(tags);
        }


    }

    public void parseContent(){

        String parsedContentWIP; //string for the WIP parsed content

        //escaping all speechmakrs within the parsed content
        Matcher speechMatcher = Pattern.compile("(?<speechmarks>\\\")",Pattern.MULTILINE).matcher(unparsedContent);
        //speechMatcher.reset(unparsedContent);
        parsedContentWIP = speechMatcher.replaceAll("\\\\\"");

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
                String link = "<a class=\\\\\"passageLink\\\\\" onclick=\'theHeccer.goToPassage(\\\\\""+temp+"\\\\\")\'>"+temp+"</a>";
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
                String link = "<a class=\\\\\"passageLink\\\\\" onclick=\'theHeccer.goToPassage(\\\\\""+linkParts[1]+"\\\\\")\'>"+linkParts[0]+"</a>";
                //System.out.println(link);
                parsedContentWIP = indirectLinkMatcher.replaceFirst(link);
                indirectLinkMatcher.reset(parsedContentWIP);
            }
        } while(notDone);


        //replacing newlines with '</br>' tags
        parsedContentWIP = parsedContentWIP.replaceAll("\\s*\\R","</br>");

        System.out.println(parsedContentWIP);

        //removing trailing </br> from the WIP parsed content
        boolean stillHasTrailingLinebreaks;
        do{
            int lengthMinus5 = parsedContentWIP.length()-5;
            String temp = (parsedContentWIP.substring(lengthMinus5));
            stillHasTrailingLinebreaks = temp.equals("</br>");
            if (stillHasTrailingLinebreaks){
                parsedContentWIP = parsedContentWIP.substring(0,lengthMinus5);
            }
        } while (stillHasTrailingLinebreaks);

        //putting an opening speechmark and opening '<p>' tag in the parsed content
        parsedContent = "\"<p>";

        //concatenating the WIP parsed content to the parsed content
        parsedContent = parsedContent.concat(parsedContentWIP);

        //concatenating a closing '</p>' tag (and closing speechmark) to the parsed content
        parsedContent = parsedContent.concat("</p>\"");

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
            for (String s : linkedPassages) {
                if (!allPassages.contains(s)) {
                    throw new UndefinedPassageException(s);
                }
            }
            return true;
        } catch (ParserException e){
            e.printStackTrace();
            return false;
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
        //end of parsed content, finishing the line of code
        heccedRepresentation = heccedRepresentation.concat("\n\t\t)\n\t);\n\n\n");
        //returning the heccedRepresentation
        return heccedRepresentation;
    }






}
