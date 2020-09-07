package hecc_up;

import hecc_up.heccCeptions.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassageParser {

    private final ArrayList<String> heccedData;

    private final Map<String, Passage> passageMap;

    private final Set<String> passageNames;

    private String dataToParse;

    private boolean hasMetadata;

    private String startingPassageName;


    private String metadata;
    private String UUID;



    public PassageParser(String rawHeccData){

        heccedData = new ArrayList<>();

        passageMap = new HashMap<>();

        passageNames = new TreeSet<>();

        dataToParse = rawHeccData.concat("\n");
        /*
        you might be asking 'why am I concatting a "\n" to the end of the raw hecc data?'
        well, long story short, if there isn't an "\n" at the end of the last line of the input, that last line doesn't get looked at.
        so I may as well make sure that it does get looked at, y'know?
         */

        hasMetadata = false;

    }

    //this is responsible for setting up the passage objects and such
    public boolean constructThePassageObjects() throws ParserException{

        heccedData.clear();

        heccedData.add("//HECC UP parser test output (as of 07/09/2020) (R. Lowe, 2020)\n\n");

        boolean notDone = true;



        String metadata = "no metadata";
        hasMetadata = false;


        // STUFF THAT OMITS ANY METADATA STUFF THAT'S BEFORE THE FIRST PASSAGE DECLARATION

        Matcher firstDeclarationMatcher = Pattern.compile("(^::)", Pattern.MULTILINE).matcher(dataToParse);

        if(firstDeclarationMatcher.find()){
            int startIndex = firstDeclarationMatcher.start();
            if (startIndex > 1){
                //metadata is everything before first declaration
                metadata = dataToParse.substring(0,startIndex-1);
                hasMetadata = true;
                //dataToParse takes up everything after first declaration
                dataToParse = dataToParse.substring(startIndex);
            }
        }

        //TODO (after MVP): the metadata stuff
        //System.out.println(dataToParse);
        //System.out.println(metadata);

        startingPassageName = "Start";

        if(hasMetadata){

            //TODO (after MVP): check if a starting passage was declared in the metadata, update 'startingPassageName' appropriately

            //TODO (after MVP): check for IFID

            //TODO (after MVP): maybe have a 'metadata' object or something to handle this stuff?

        }


        // KEEPING TRACK OF ALL THE PASSAGE NAMES


        Matcher passageNameMatcher = Pattern.compile("(?<names>(?<=^::)[\\w]+[\\w- ]*[\\w]+)", Pattern.MULTILINE).matcher(dataToParse);


        while (passageNameMatcher.find()){
            String currentName = passageNameMatcher.group(0);
            //System.out.println(currentName);
            if (!passageNames.add(currentName)){
                //THROW EXCEPTION HERE
                throw new DuplicatePassageNameException(currentName);
                // System.out.println("uh oh looks like we got a duplicate passage!");
                //} else{
                //System.out.println("passage added successfully");
            }
        }

        if (passageNames.size() == 0){
            System.out.println("no passages found!");
            //THROW EXCEPTION HERE
            throw new NoPassagesException();
        } else{
            System.out.println(passageNames.size());
        }


        Matcher declarationMatcher = Pattern.compile("(?<declarations>^::[\\w]+[\\w- ]*[\\w]+)", Pattern.MULTILINE).matcher(dataToParse);
        //"(?<declarations>^::[\\w]+[\\w- ]*[\\w]+)"
        //passageNameMatcher.reset(dataToParse);

        //will give this the everythingAfterDeclaration (the content)
        Matcher passageContentMatcher = Pattern.compile("(?<content>(?<=\\r\\n|\\r|\\n)(?!^::).*\\n(?!^::)|\\r(?!^::)|\\n\\r(?!^::)*.+)", Pattern.MULTILINE).matcher("");

        //will use this to crop leading whitespace lines
        Matcher entirelyWhitespaceMatcher = Pattern.compile("^\\s*$", Pattern.MULTILINE).matcher("");

        Matcher lineEndWhitespaceMatcher = Pattern.compile("\\s*\\R$", Pattern.MULTILINE).matcher("");

        String currentPassageName;
        String nextPassageName = "";
        String everythingAfterDeclaration;
        String currentContent;

        String currentPassageMetadata;
        //int currentPassageMetadataStart;

        int nextDeclarationStart = 0;
        int thisDeclarationStart;
        //int thisContentStart = 0;

        boolean foundFirst = false;
        boolean contentFound;
        boolean passageMetadataFound;

        do{
            //find next declaration, and move 'next' declarations/passage names into 'current'
            notDone = declarationMatcher.find();
            thisDeclarationStart = nextDeclarationStart;
            currentPassageName = nextPassageName;
            if (notDone){ //if there is a next declaration

                //finds passage name of that next declaration, and where the next declaration starts
                String tempDeclaration = declarationMatcher.group(0);
                nextPassageName = tempDeclaration.substring(2); //omits the '::' at the start of the line
                nextDeclarationStart = declarationMatcher.start();
                //the position where this next declaration starts will be the cut-off for where stuff will be analysed

                if (!foundFirst){
                    //marks the first passage as 'found', then proceeds to the next iteration of this loop
                    foundFirst = true;
                    continue;
                }
            } else{
                //everything to the end of dataToParse will be considered if there's no next declaration
                nextDeclarationStart = dataToParse.length();
            }
            passageMetadataFound = false;
            //attempts to obtain the metadata for the passage
            //metadata is between the end of this passage's name and theend of the line it's on
            currentPassageMetadata = dataToParse.substring((thisDeclarationStart + (currentPassageName.length()+2)),nextDeclarationStart);
            //attempts to find everything between the end of the passage declaration and the end of that line
                //(+2 to length because 2 was subtracted from the length because the :: at the start was omitted)
            lineEndWhitespaceMatcher.reset(currentPassageMetadata);
            if(lineEndWhitespaceMatcher.find()){
                //currentPassageMetadata is set to whatever is on the end of the passage metadata line, before the line end whitespace starts
                currentPassageMetadata = currentPassageMetadata.substring(0,lineEndWhitespaceMatcher.start());

                //metadata has been found if this passage's metadata isn't empty
                passageMetadataFound = !(currentPassageMetadata.isEmpty());
            }

            //obtains the entire declaration thing for this passage
            everythingAfterDeclaration = dataToParse.substring(thisDeclarationStart, nextDeclarationStart);
            passageContentMatcher.reset(everythingAfterDeclaration);

            currentContent = ""; //sets up the current content string

            contentFound = false;
            String temp;
            while(passageContentMatcher.find()){
                temp = passageContentMatcher.group(0);
                if (!contentFound){ //if no content has been found
                    //check if this current line is entirely whitespace
                    if (entirelyWhitespaceMatcher.reset(temp).matches()){
                        continue;
                        //skip this line if it's entirely whitespace
                    } else{
                        contentFound = true;
                        //content has been found once first not-entirely-whitespace line has been reached
                    }
                }
                //lineEndWhitespaceMatcher.reset(temp);
                //temp = lineEndWhitespaceMatcher.replaceAll("</br>");
                currentContent = currentContent.concat(temp);
            }
            if (contentFound) {
                //if content was found, add it to the passage content map
                if (passageMetadataFound){
                    passageMap.put(currentPassageName,new Passage(currentPassageName,currentContent,currentPassageMetadata));
                } else{
                    passageMap.put(currentPassageName,new Passage(currentPassageName,currentContent));
                }
            } else{
                System.out.println("No content found for passage " + currentPassageName);
                throw new EmptyPassageException(currentPassageName);
            }
        } while(notDone);

        return true;

    }

    public boolean prepareHeccedData(){

        //this prepares the heccedData

        heccedData.clear();

        heccedData.add("//HECC UP test output (as of 07/09/2020) (R. Lowe, 2020)\n\n");

        //declaration of starting passage name is added to heccedData
        heccedData.add("var startingPassageName = \""+startingPassageName+"\";\n\r\n\r");

        //starts the declaration of the getHECCED function in heccedData
        heccedData.add("function getHECCED(){\n\r");


        //parses each passage, and ensure that the links are all valid and such
        for (Map.Entry<String, Passage> e: passageMap.entrySet()){
            Passage current = e.getValue();
            current.parseContent();
            if (current.validateLinkedPassages(passageNames)){
                heccedData.add(current.getHeccedRepresentation());
                e.setValue(current);
            } else {
                //stop everything if an invalid link is found
                return false;
            }
        }

        heccedData.add("\n\ttheHeccer.printPassages();\n");

        heccedData.add("\n\ttheHeccer.loadCurrentPassage();\n\n");

        heccedData.add("}\n");

        heccedData.add("\n//that's all, folks!\n\n");

        for(String h: heccedData){
            System.out.println(h);
        }

        return true;

    }

    public ArrayList<String> getHeccedData(){
        return heccedData;
    }

    public void printPassageObjects(){

    }


}
