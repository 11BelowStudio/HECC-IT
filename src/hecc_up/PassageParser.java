package hecc_up;

import hecc_up.heccCeptions.*;
import utilities.IFIDgenerator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassageParser {

    private final ArrayList<String> heccedData;

    private final Map<String, Passage> passageMap;

    private final Set<String> passageNames;

    private String dataToParse;

    private Metadata metadata;



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



    }

    //this is responsible for setting up the passage objects and such
    public boolean constructThePassageObjects() throws ParserException{


        boolean notDone = true;



        String metadataString = "no metadata";
        boolean hasMetadata = false;

        // STUFF THAT OMITS ANY METADATA STUFF THAT'S BEFORE THE FIRST PASSAGE DECLARATION

        Matcher firstDeclarationMatcher = Pattern.compile("(^::)", Pattern.MULTILINE).matcher(dataToParse);

        if(firstDeclarationMatcher.find()){
            int startIndex = firstDeclarationMatcher.start();
            if (startIndex > 1){
                //metadata is everything before first declaration
                metadataString = dataToParse.substring(0,startIndex-1);
                hasMetadata = true;
                //dataToParse takes up everything after first declaration
                dataToParse = dataToParse.substring(startIndex);
            }
        }


        //TODO: the metadata stuff
        //System.out.println(dataToParse);
        //System.out.println(metadata);

        //startingPassageName = "Start";

        //TODO: CONSTRUCT METADATA OBJECT WITH THE METADATA

        //constructs a new Metadata object to do the metadata parsing stuff
        metadata = new Metadata(metadataString, hasMetadata);

        //TODO: parse the Metadata object stuff

        metadata.parseMetadata();


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
                //complains if there are no passages
        } //else{
            //System.out.println(passageNames.size());

        //}


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

    public boolean validatePassages(){

        try {
            //TODO: replace this first bit with calls to the Metadata object
            //check that there is a passage with the same name as the starting passage name
            if ((metadata.doesStartPassageExist(passageNames))){
                //ensure that every single linked passage is valid
                for (Map.Entry<String, Passage> e: passageMap.entrySet()){
                    Passage current = e.getValue();
                    current.validateLinkedPassagesThrowingException(passageNames);
                    //exception is thrown if an undefined passage is being linked
                    e.setValue(current);
                }
                return true;
            } else{
                throw new MissingStartingPassageException(metadata.getStartPassage());
                //throw exception if no such starting passage exists
            }

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean prepareHeccedData(){

        if (!validatePassages()){
            return false;
        }


        //this prepares the heccedData

        heccedData.clear();

        heccedData.add("//HECC UP test output (as of 12/10/2020) (R. Lowe, 2020)\n\n");

        //declaration of starting passage name is added to heccedData
        //TODO: use a metadataObject.getStartPassage() call
        heccedData.add("var startingPassageName = \""+metadata.getStartPassage()+"\";\n\r\n\r");

        //starts the declaration of the getHECCED function in heccedData
        heccedData.add("function getHECCED(){\n\r");


        //parses each passage, and ensure that the links are all valid and such
        for (Map.Entry<String, Passage> e: passageMap.entrySet()){
            Passage current = e.getValue();

            current.parseContent();

            //ensure that the passages they link to are valid
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

        //TODO: check in metadata object for a declared IFID
        if (!metadata.doesIFIDExist()){
            System.out.println(suggestIFID());
        }

        metadata.printDebugData();

        return true;

    }


    public Metadata getMetadata(){
        return metadata;
    }

    public ArrayList<String> getHeccedData(){
        return heccedData;
    }

    public void printPassageObjects(){
        //prints the passage objects for debugging reasons
        for (Map.Entry<String, Passage> e: passageMap.entrySet()){
            e.getValue().printPassageInfoForDebuggingReasons();
            System.out.println("\n");
        }

    }

    private String suggestIFID(){
        //returns a string suggesting that you add an IFID
        String suggestion = "Oh no! It looks like you forgot to declare an IFID in your hecc game!\n"
                + "Declaring an IFID can be rather helpful in case you decide to post your game somewhere, so it can be archived.\n"
                + "If you want a better explanation, here's a better explanation: https://ifdb.tads.org/help-ifid\n"
                + "Anywho, here's a line of code with a newly generated IFID that you can put in your hecc file, before the first passage declaration,\n"
                + "in case you actually want to declare an IFID for your work:\n\n";
        String ifidString = "!IFID: " + IFIDgenerator.generateIFIDString();
        return (suggestion.concat(ifidString));
    }


}
