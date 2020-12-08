package oh_hecc;

import GameParts.Metadata;
import GameParts.Passage;
import heccCeptions.*;
import hecc_up.LoggerInterface;
import oh_hecc.game_parts.metadata.EditableMetadata;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.EditablePassage;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import utilities.IFIDgenerator;
import utilities.TextAssetReader;

import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OhHeccParser {

    String rawHeccCode;


    /**
     * a map of all the passages
     */
    private final Map<UUID, PassageEditingInterface> heccMap;

    /**
     * The metadata
     */
    private final MetadataEditingInterface theMetadata;


    //TODO: parse the HECC file into the heccMap and theMetadata


    /**
     * the thing what called the passageParser (which this might need to log info to)
     */
    //private final LoggerInterface logger;

    /**
     * the hecced data which this will construct and output
     */
    //private final ArrayList<String> heccedData;

    /**
     * a set of all the passage names
     */
    private final Set<String> passageNames;

    /**
     * The raw .hecc metadata code
     */
    private final String rawMetadata;

    /**
     * the raw .hecc passage code
     */
    private final String rawPassages;




    /**
     * Creates the HeccParser object
     * @param rawHeccData the raw hecc data that needs passing
     */
    public OhHeccParser(String rawHeccData){//, LoggerInterface boundary){

        //logger = boundary;

        //heccedData = new ArrayList<>();

        heccMap = new HashMap<>();

        passageNames = new TreeSet<>();

        String[] splitData = splitRawDataToMetadataAndPassageString(rawHeccData.trim().concat("\n"));

        /*
            you might be asking 'why am I concatting a "\n" to the end of the raw hecc data?'
            well, long story short, if there isn't an "\n" at the end of the last line of the input, that last line doesn't get looked at.
            so I may as well make sure that it does get looked at, y'know?
        */


        rawMetadata = splitData[0];

        rawPassages = splitData[1];

        theMetadata = new EditableMetadata(splitData[0]);

        try {
            heccMap.putAll(constructPassageMap(splitData[1]));
        } catch (Exception e){
            JOptionPane.showMessageDialog(
                    null,
                    "Unable to read any passages within this .hecc file!",
                    "Could not read file",
                    JOptionPane.ERROR_MESSAGE
            );
        }


        //dataToParse = makeMetadataObject(rawHeccData.trim().concat("\n"));

        //heccMap.putAll(constructPassageMap(dataToParse));


    }

    //this is responsible for setting up the passage objects and such

    /**
     * This is responsible for setting up the metadata, passageNames, and passageMap objects
     * @return true if this is executed successfully
     */
    public boolean constructThePassageObjects(){

        //trims metadata stuff from dataToParse, and creates the Metadata object
        //theMetadata = makeMetadataObject();


        // KEEPING TRACK OF ALL THE PASSAGE NAMES
        //passageNames.clear();
        //passageNames.addAll(findPassageNames(dataToParse));


        //CONSTRUCTING THE PASSAGE MAP
        heccMap.clear();
        heccMap.putAll(constructPassageMap(rawPassages));

        /*
        for (UUID u: heccMap.keySet()) {
            PassageEditingInterface p = heccMap.get(u);
            p.updateLinkedUUIDs(heccMap);
            heccMap.put(u,p);
        }
        */

        //passageMap.clear();
        //passageMap.putAll(constructPassageMap(dataToParse));



        return true;

    }

    private String[] splitRawDataToMetadataAndPassageString(String rawData){
        String metadataString = "";
        String everythingAfterTheMetadata = "";

        Matcher firstDeclarationMatcher = Pattern.compile(
                "(^::)",
                Pattern.MULTILINE
        ).matcher(rawData);

        if(firstDeclarationMatcher.find()){
            int startIndex = firstDeclarationMatcher.start();
            //if there is something before the first declaration
            if (startIndex > 1){
                //metadata is everything before first declaration
                metadataString = rawData.substring(0,startIndex-1);
                //dataToParse takes up everything after first declaration
                everythingAfterTheMetadata = rawData.substring(startIndex);
            } else {
                //if there's nothing before the first declaration, it's all after the metadata
                everythingAfterTheMetadata = rawData;
            }
        } else {
            //there's no first declaration, it's all metadata.
            metadataString = rawData;
        }
        return new String[]{metadataString,everythingAfterTheMetadata};
    }


    /**
     * This method basically constructs the Metadata object
     * @return The constructed
     * The metadata object is made as an attribute of this class.
     */
    private MetadataEditingInterface makeMetadataObject(){
        return new EditableMetadata(rawMetadata);
    }

    /*
     * Attempts to find the names of all the passages in the raw hecc data
     * @param dataToParse the raw hecc data being parsed
     * @return a Set of all the String passage names
     *//*
    private Set<String> findPassageNames(String dataToParse) {

        Set<String> pNames = new TreeSet<>();

        //attempts to find passage declarations
        Matcher passageNameMatcher = Pattern.compile(
                "(?<names>(?<=^::)"+Parseable.PASSAGE_NAME_REGEX_WITH_WHITESPACE+")"
                , Pattern.MULTILINE
        ).matcher(dataToParse);


        while (passageNameMatcher.find()){
            String currentName = passageNameMatcher.group(0);
            if (!pNames.add(currentName)){
                //throw new DuplicatePassageNameException(currentName);
                //complains if there are multiple passages with the same name
            }
        }

        if (pNames.size() == 0){
            System.out.println("no passages found!");
            //throw new NoPassagesException();
            //complains if there are no passages
        }
        return pNames;
    }
    */

    /**
     * This method constructs the passage map
     * @param dataToParse the raw hecc data being parsed
     * @return A map of the passage objects declared in the raw data
     */
    private Map<UUID, PassageEditingInterface> constructPassageMap(String dataToParse){
        //creating the map
        Map<UUID, PassageEditingInterface> pMap = new HashMap<>();

        boolean notDone;

        //matches declarations
        Matcher declarationMatcher = Pattern.compile("(?<declarations>^::("+ Parseable.PASSAGE_NAME_REGEX_WITH_WHITESPACE + "))", Pattern.MULTILINE).matcher(dataToParse);
        //will give this the everythingAfterDeclaration (the content)
        Matcher passageContentMatcher = Pattern.compile("(?<content>(?<=\\r\\n|\\r|\\n)(?!^::).*\\n(?!^::)|\\r(?!^::)|\\n\\r(?!^::)*.+)", Pattern.MULTILINE).matcher("");
        //Matcher passageContentMatcher = Pattern.compile("(?<content>(?<=\\R)(?!^::).*\\R(?!^::).+)", Pattern.MULTILINE).matcher("");
        //will use this to crop leading whitespace lines
        Matcher entirelyWhitespaceMatcher = Pattern.compile("^\\h*$", Pattern.MULTILINE).matcher("");
        //matches whitespace at the end of the line
        Matcher lineEndWhitespaceMatcher = Pattern.compile("\\h*\\R$", Pattern.MULTILINE).matcher("");
        //This matches the line that indicates the start of a multiline comment at the end of a passage (containing only ;;)
        Matcher commentStartMatcher = Pattern.compile("^;;\\R$", Pattern.MULTILINE).matcher("");

        Matcher commentLineMatcher = Pattern.compile("^//",Pattern.MULTILINE).matcher(""); //TODO: maybe edit the comment stuff so it needs to start with // after the ;; line

        String currentPassageName;
        String nextPassageName = "";
        String everythingAfterDeclaration;
        String currentContent;
        String currentComment;

        String currentPassageMetadata;
        //int currentPassageMetadataStart;

        int nextDeclarationStart = 0;
        int thisDeclarationStart;
        //int thisContentStart = 0;

        boolean foundFirst = false;
        boolean contentFound;
        //boolean passageMetadataFound;

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
            //passageMetadataFound = false;
            //attempts to obtain the metadata for the passage
            //metadata is between the end of this passage's name and the end of the line it's on
            currentPassageMetadata = dataToParse.substring(
                    (thisDeclarationStart + (currentPassageName.length()+2)),
                    nextDeclarationStart
            );
            //attempts to find everything between the end of the passage declaration and the end of that line
            //(+2 to length because 2 was subtracted from the length because the :: at the start was omitted)


            lineEndWhitespaceMatcher.reset(currentPassageMetadata);
            if(lineEndWhitespaceMatcher.find()){
                //currentPassageMetadata is set to whatever is on the end of the passage metadata line, before the line end whitespace starts
                currentPassageMetadata = currentPassageMetadata.substring(
                        0,
                        lineEndWhitespaceMatcher.start()
                );

                //metadata has been found if this passage's metadata isn't empty
                //passageMetadataFound = !(currentPassageMetadata.isEmpty());

            }

            //obtains the entire declaration thing for this passage
            everythingAfterDeclaration = dataToParse.substring(thisDeclarationStart, nextDeclarationStart);
            passageContentMatcher.reset(everythingAfterDeclaration);

            currentContent = ""; //sets up the current content string
            currentComment = ""; //sets up current comment string

            contentFound = false;
            boolean commentStarted = false;
            String temp;
            //attempts to add the actual content to the current passage content
            while(passageContentMatcher.find()){
                temp = passageContentMatcher.group(0);

                //System.out.println(temp);


                if (commentStarted){
                    currentComment = currentComment.concat(temp);
                    continue;
                } else {
                    if (contentFound) { //if content has been found
                        if (commentStartMatcher.reset(temp).matches()) { //if the current line matches the comment start line
                            commentStarted = true; //the comment has started
                            continue; //skip this line
                        }
                    } else { //if no content has been found yet
                        //check if this current line is entirely whitespace
                        if (entirelyWhitespaceMatcher.reset(temp).matches()) {
                            continue;
                            //skip this line if it's entirely whitespace
                        } else {
                            contentFound = true;
                            //content has been found once first not-entirely-whitespace line has been reached
                        }
                    }
                }
                //lineEndWhitespaceMatcher.reset(temp);
                //temp = lineEndWhitespaceMatcher.replaceAll("</br>");
                //adds the current line of content to the currentContent
                currentContent = currentContent.concat(temp);
            }

            /*
            if (contentFound) {
                //System.out.println("Everything after declaration:");
                //System.out.println(currentContent);
                currentContent = currentContent.trim();
                //System.out.println("Trimmed everything after declaration:");
                //System.out.println(currentContent);
                //if content was found, add it to the passage content map
                if (passageMetadataFound){
                    pMap.put(currentPassageName,new Passage(currentPassageName,currentContent,currentComment,currentPassageMetadata));
                } else{
                    pMap.put(currentPassageName,new Passage(currentPassageName,currentContent,currentComment));
                }

            } else{
                //System.out.println("No content found for passage " + currentPassageName);
                //logger.logInfo("No content found for passage " + currentPassageName);
                //throw new EmptyPassageException(currentPassageName);
            }
             */
            PassageEditingInterface thePassage = new EditablePassage(currentPassageName.trim(),currentContent.trim(),currentComment,currentPassageMetadata);
            pMap.put(thePassage.getPassageUUID(),thePassage);
        } while(notDone);



        //And now, time to actually update the UUIDs.
        for (PassageEditingInterface e: pMap.values()){
            e.updateLinkedUUIDs(pMap);
        }


        return pMap;
    }


    /*
     * A method that can be used to ensure that the passage names are all valid and such
     * @return true if all passages are valid, false if there's a problem
     * @throws UndefinedPassageException if a passage has a link to a passage which doesn't exist
     * @throws MissingStartingPassageException if the defined starting passage doesn't exist
     *//*
    public boolean validatePassages() throws UndefinedPassageException, MissingStartingPassageException {
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
    }

     */

    /*
     * This prepares the heccedData ArrayList, using the Metadata object and the passageMap map
     * @return true if this was performed successfully
     * @throws UndefinedPassageException if there's an undefined passage somewhere
     * @throws MissingStartingPassageException if the specified starting passage doesn't exist
     *//*
    public boolean prepareHeccedData() throws UndefinedPassageException, MissingStartingPassageException {

        if (!validatePassages()){
            return false;
        }
        //this prepares the heccedData

        heccedData.clear();

        heccedData.add("//HECC UP output (as of 15/10/2020) (R. Lowe, 2020)\n\n");

        //declaration of starting passage name is added to heccedData
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

        //outputs any info the user might need regarding any missing metadata in the Metadata object
        logger.logInfo(metadata.outputMetadataDefinitionInstructions());

        metadata.printDebugData();

        //printPassageObjects();

        return true;
    }
    */


    /**
     * Obtains the Metadata object
     * @return the Metadata object
     */
    public MetadataEditingInterface getMetadata(){
        return theMetadata;
    }

    /**
     * Obtains the heccMap map
     * @return the heccMap map
     */
    public Map<UUID, PassageEditingInterface> getHeccMap(){
        return heccMap;
    }

    /**
     * Prints the Passage objects in the passageMap for debugging reasons
     */
    public void printParsedObjects(){
        System.out.println("Metadata:");
        System.out.println(theMetadata.toString());
        System.out.println("\n");

        //prints the passage objects for debugging reasons
        System.out.println("Passages:");
        for (Map.Entry<UUID, PassageEditingInterface> e: heccMap.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().outputAsStringForDebuggingReasons());
        }
        /*
        for (PassageEditingInterface e: heccMap.values()){
            System.out.println(e.outputAsStringForDebuggingReasons());
            System.out.println("");
        }*/
        System.out.println("\nyep thats everything printed");

    }


    public String toHecc(){
        StringBuilder heccBuilder = new StringBuilder();

        heccBuilder.append(theMetadata.toHecc());
        heccBuilder.append("\n");

        for (PassageEditingInterface h: heccMap.values()) {
            heccBuilder.append(h.toHecc());
            heccBuilder.append("\n");
        }

        return heccBuilder.toString();
    }


    public static void main(String[] args) throws Exception{
        String sample = TextAssetReader.fileToString("src/assets/textAssets/HeccSample.hecc");
        System.out.println("SAMPLE HECC CODE:\n----------------------------");
        System.out.println(sample);

        System.out.println("\nParse time");
        OhHeccParser p = new OhHeccParser(sample);
        System.out.println("parsed content in debug form:\n------------------------------");
        p.printParsedObjects();

        System.out.println("\nAnd now, the parsed content in .hecc form:\n--------------------------");
        System.out.println(p.toHecc());
    }




}
