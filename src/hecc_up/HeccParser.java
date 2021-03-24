package hecc_up;

import hecc_up.gameParts.Metadata;
import heccCeptions.*;
import oh_hecc.game_parts.passage.OutputtablePassage;
import oh_hecc.game_parts.passage.PassageOutputtingInterface;
import oh_hecc.game_parts.passage.SharedPassage;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This parses the full HECC code
 * bottom text
 */
public class HeccParser {

    //TODO: Pipeline design pattern for parsing? https://java-design-patterns.com/patterns/pipeline/
    //TODO: layers with interface segregation principle?
    //TODO: maybe adapter pattern?

    /**
     * the thing what called the passageParser (which this might need to log info to)
     */
    private final LoggerInterface logger;

    /**
     * the hecced data which this will construct and output
     */
    private final ArrayList<String> heccedData;

    /**
     * a map of all the passages
     */
    private final Map<String, PassageOutputtingInterface> passageMap;

    /**
     * a set of all the passage names
     */
    private final Set<String> passageNames;

    /**
     * the raw .hecc data
     */
    private final String dataToParse;

    /**
     * the metadata object
     */
    private Metadata metadata;


    /**
     * Creates the HeccParser object
     * @param rawHeccData the raw hecc data that needs passing
     * @param boundary the implementation of LoggerInterface that this logs important info to
     */
    public HeccParser(String rawHeccData, LoggerInterface boundary){


        logger = boundary;

        heccedData = new ArrayList<>();

        passageMap = new HashMap<>();

        passageNames = new TreeSet<>();

        dataToParse = rawHeccData.trim().concat("\n");
        /*
        you might be asking 'why am I concatting a "\n" to the end of the raw hecc data?'
        well, long story short, if there isn't an "\n" at the end of the last line of the input, that last line doesn't get looked at.
        so I may as well make sure that it does get looked at, y'know?
         */

    }

    //this is responsible for setting up the passage objects and such

    /**
     * This is responsible for setting up the metadata, passageNames, and passageMap objects
     * @return true if this is executed successfully
     * @throws NoPassagesException if there are no passages defined
     * @throws DuplicatePassageNameException if two passages have the same name
     * @throws EmptyPassageException if there's an empty passage
     * @throws DeletedLinkPresentException if the passage contains a link to a deleted passage
     */
    public boolean constructThePassageObjects() throws
            NoPassagesException, DuplicatePassageNameException, EmptyPassageException, DeletedLinkPresentException {

        //trims metadata stuff from dataToParse, and creates the Metadata object
        String dataToParse = makeMetadataObject(this.dataToParse);

        metadata.parseMetadata();

        // KEEPING TRACK OF ALL THE PASSAGE NAMES
        passageNames.clear();
        passageNames.addAll(findPassageNames(dataToParse));


        //CONSTRUCTING THE PASSAGE MAP
        passageMap.clear();
        passageMap.putAll(constructPassageMap(dataToParse));



        return true;

    }

    /**
     * This method basically constructs the Metadata object
     * @param dataToParse the full hecc data being parsed
     * @return all the hecc data from the first passage declaration.
     * The metadata object is made as an attribute of this class.
     */
    private String makeMetadataObject(String dataToParse){
        String metadataString = "";
        boolean hasMetadata = false;

        Matcher firstDeclarationMatcher = Pattern.compile(
                "(^::)",
                Pattern.MULTILINE
        ).matcher(dataToParse);

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

        metadata = new Metadata(metadataString,hasMetadata);

        return dataToParse;
    }

    /**
     * Attempts to find the names of all the passages in the raw hecc data
     * @param dataToParse the raw hecc data being parsed
     * @return a Set of all the String passage names
     * @throws DuplicatePassageNameException if multiple passages share the same name
     * @throws NoPassagesException if no passages exist
     */
    private Set<String> findPassageNames(String dataToParse) throws
            DuplicatePassageNameException, NoPassagesException{

        Set<String> pNames = new TreeSet<>();

        //attempts to find passage declarations
        Matcher passageNameMatcher = Pattern.compile(
                "(?<names>(?<=^::)([\\w]+[\\w- ]*)?[\\w]+)"
                , Pattern.MULTILINE
        ).matcher(dataToParse);


        while (passageNameMatcher.find()){
            String currentName = passageNameMatcher.group(0);
            if (!pNames.add(currentName)){
                throw new DuplicatePassageNameException(currentName);
                //complains if there are multiple passages with the same name
            }
        }

        if (pNames.size() == 0){
            throw new NoPassagesException();
            //complains if there are no passages
        }
        return pNames;
    }

    /**
     * This method constructs the passage map
     * @param dataToParse the raw hecc data being parsed
     * @return A map of the passage objects declared in the raw data
     * @throws EmptyPassageException if a passage is empty
     * @throws DeletedLinkPresentException if the passage contains a link to a deleted passage
     */
    private Map<String, PassageOutputtingInterface> constructPassageMap(String dataToParse) throws
            EmptyPassageException, DeletedLinkPresentException{
        //creating the map
        Map<String, PassageOutputtingInterface> pMap = new HashMap<>();

        boolean notDone;

        //matches declarations
        Matcher declarationMatcher = Pattern.compile(
                "(?<declarations>^::([\\w]+[\\w- ]*)?[\\w]+)",
                Pattern.MULTILINE
        ).matcher(dataToParse);

        //will give this the everythingAfterDeclaration (the content)
        Matcher passageContentMatcher = Pattern.compile(
                "(?<content>(?<=\\r\\n|\\r|\\n)(?!^::).*\\n(?!^::)|\\r(?!^::)|\\n\\r(?!^::)*.+)",
                Pattern.MULTILINE
        ).matcher("");

        //will use this to crop leading whitespace lines
        Matcher entirelyWhitespaceMatcher = Pattern.compile(
                "^\\h*$",
                Pattern.MULTILINE
        ).matcher("");

        //matches whitespace at the end of the line
        Matcher lineEndWhitespaceMatcher = Pattern.compile("\\h*\\R$", Pattern.MULTILINE).matcher("");

        //This matches the line that indicates the start of a multiline comment at the end of a passage (containing only ;;)
        Matcher commentStartEndMatcher = Pattern.compile("^;;\\R$", Pattern.MULTILINE).matcher("");

        String currentPassageName;
        String nextPassageName = "";
        String everythingAfterDeclaration;
        String currentContent;

        String currentPassageMetadata;

        int nextDeclarationStart = 0;
        int thisDeclarationStart;

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
                passageMetadataFound = !(currentPassageMetadata.isEmpty());
            }

            //obtains the entire declaration thing for this passage
            everythingAfterDeclaration = dataToParse.substring(thisDeclarationStart, nextDeclarationStart);
            passageContentMatcher.reset(everythingAfterDeclaration);

            currentContent = ""; //sets up the current content string

            contentFound = false;
            boolean commentStarted = false;
            String temp;
            //attempts to add the actual content to the current passage content
            while(passageContentMatcher.find()){
                temp = passageContentMatcher.group(0);

                if (contentFound) { //if content has been found
                    if (commentStartEndMatcher.reset(temp).matches()) { //if the current line matches the comment start/end line
                        break;
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
                //adds the current line of content to the currentContent
                currentContent = currentContent.concat(temp);
            }


            if (contentFound) {
                currentContent = currentContent.trim();
                //if content was found, add it to the passage content map

                if (SharedPassage.doesPassageContentContainDeletedLinks(currentContent)){
                    // complains if there's a link to an obviously deleted passage
                    throw new DeletedLinkPresentException(currentPassageName);
                }

                if (passageMetadataFound){
                    pMap.put(currentPassageName,new OutputtablePassage(currentPassageName,currentContent,currentPassageMetadata));
                } else{
                    pMap.put(currentPassageName,new OutputtablePassage(currentPassageName,currentContent));
                }
            } else{
                throw new EmptyPassageException(currentPassageName);
            }
        } while(notDone);

        return pMap;
    }

    /**
     * A method that can be used to ensure that the passage names are all valid and such
     * @return true if all passages are valid, false if there's a problem
     * @throws UndefinedPassageException if a passage has a link to a passage which doesn't exist
     * @throws MissingStartingPassageException if the defined starting passage doesn't exist
     */
    public boolean validatePassages() throws UndefinedPassageException, MissingStartingPassageException {
        if ((metadata.doesStartPassageExist(passageNames))){
            //ensure that every single linked passage is valid
            for (Map.Entry<String, PassageOutputtingInterface> e: passageMap.entrySet()){
                PassageOutputtingInterface current = e.getValue();
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

    /**
     * This prepares the heccedData ArrayList, using the Metadata object and the passageMap map
     * @return true if this was performed successfully
     * @throws UndefinedPassageException if there's an undefined passage somewhere
     * @throws MissingStartingPassageException if the specified starting passage doesn't exist
     */
    public boolean prepareHeccedData() throws UndefinedPassageException, MissingStartingPassageException {

        if (!validatePassages()){
            return false;
        }
        //this prepares the heccedData

        heccedData.clear();

        heccedData.add("//HECC UP output (as of 29/01/2021) (Rachel Lowe, 2021)\n\n");

        //declaration of starting passage name is added to heccedData
        heccedData.add("var startingPassageName = \""+metadata.getStartPassage()+"\";\n\n");

        //starts the declaration of the getHECCED function in heccedData
        heccedData.add("function getHECCED(){\n\n");


        //parses each passage, and ensure that the links are all valid and such
        for (Map.Entry<String, PassageOutputtingInterface> e: passageMap.entrySet()){
            PassageOutputtingInterface current = e.getValue();

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



        //outputs any info the user might need regarding any missing metadata in the Metadata object
        logger.logInfo(metadata.outputMetadataDefinitionInstructions());


        return true;
    }


    /**
     * Obtains the Metadata object
     * @return the Metadata object
     */
    public Metadata getMetadata(){
        return metadata;
    }

    /**
     * Obtains the heccedData arrayList
     * @return the heccedData arrayList
     */
    public ArrayList<String> getHeccedData(){
        return heccedData;
    }

    /**
     * Prints the Passage objects in the passageMap for debugging reasons
     */
    public void printPassageObjects(){
        //prints the passage objects for debugging reasons
        for (Map.Entry<String, PassageOutputtingInterface> e: passageMap.entrySet()){
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
        String ifidString = "!IFID: " + UUID.randomUUID().toString().toUpperCase();
        return (suggestion.concat(ifidString));
    }


}
