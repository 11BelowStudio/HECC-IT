package oh_hecc;

import oh_hecc.game_parts.metadata.EditableMetadata;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.EditablePassage;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import utilities.TextAssetReader;
import utilities.Vector2D;

import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OhHeccParser implements GameDataGetterParserInterface {


    /**
     * a map of all the passages
     */
    private final Map<UUID, PassageEditingInterface> heccMap;

    /**
     * The metadata
     */
    private final MetadataEditingInterface theMetadata;


    /**
     * The raw .hecc metadata code
     */
    private final String rawMetadata;


    /**
     * Creates the HeccParser object
     * @param rawHeccData the raw hecc data that needs passing
     */
    public OhHeccParser(String rawHeccData){//, LoggerInterface boundary){

        //logger = boundary;

        //heccedData = new ArrayList<>();

        heccMap = new HashMap<>();

        String[] splitData = splitRawDataToMetadataAndPassageString(rawHeccData.trim().concat("\n"));

        /*
            you might be asking 'why am I concatting a "\n" to the end of the raw hecc data?'
            well, long story short, if there isn't an "\n" at the end of the last line of the input, that last line doesn't get looked at.
            so I may as well make sure that it does get looked at, y'know?
        */


        rawMetadata = splitData[0];


        //the raw .hecc passage code
        //String rawPassages = splitData[1];

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

    /**
     * Splits the full hecc code into a string with the metadata and a string with everything afterwards
     * @param rawData the raw hecc data
     * @return an array with 2 indexes.
     *          index 0 holds the metadata string (everything before 1st passage declaration).
     *          index 1 holds everything from the 1st passage declaration onwards.
     */
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

    /**
     * This method constructs the passage map
     * @param dataToParse the raw hecc data being parsed
     * @return A map of the passage objects declared in the raw data
     */
    private Map<UUID, PassageEditingInterface> constructPassageMap(String dataToParse){
        //creating the map
        Map<UUID, PassageEditingInterface> pMap = new HashMap<>();

        // this will keep track of all the names that have been used for passages, so duplicates can be avoided.
        Set<String> passageNames = new HashSet<>();

        boolean notDone;

        //matches declarations
        Matcher declarationMatcher = Pattern.compile(
                "(^::("+ Parseable.PASSAGE_NAME_REGEX_WITH_WHITESPACE + "))",
                Pattern.MULTILINE
        ).matcher(dataToParse);

        //will give this the everythingAfterDeclaration (the content)
        Matcher passageContentMatcher = Pattern.compile(
                "((?<=\\r\\n|\\r|\\n)(?!^::).*\\n(?!^::)|\\r(?!^::)|\\n\\r(?!^::)*.+)",
                Pattern.MULTILINE
        ).matcher("");

        //will use this to crop leading whitespace lines
        Matcher entirelyWhitespaceMatcher = Pattern.compile(
                "^\\h*$",
                Pattern.MULTILINE
        ).matcher("");

        //matches whitespace at the end of the line
        Matcher lineEndWhitespaceMatcher = Pattern.compile(
                "\\h*\\R$",
                Pattern.MULTILINE
        ).matcher("");

        //This matches the line that indicates the start/end of a multiline comment at the end of a passage (containing only ;;)
        Matcher commentStartEndMatcher = Pattern.compile(
                "^;;\\R$",
                Pattern.MULTILINE
        ).matcher("");



        String currentPassageName;
        String nextPassageName = "";
        String everythingAfterDeclaration;

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


            StringBuilder contentBuilder = new StringBuilder(); //builds a content string
            StringBuilder commentBuilder = new StringBuilder(); //builds a comment string

            contentFound = false;
            boolean commentStarted = false;
            String temp;
            //attempts to add the actual content to the current passage content
            while(passageContentMatcher.find()){
                temp = passageContentMatcher.group(0);

                //System.out.println(temp);


                //if comment has started
                if (commentStarted){
                    if (commentStartEndMatcher.reset(temp).matches()){ //if the start-end indicator is reached again, that's the end of the comment.
                        break; //no more looping
                    }
                    //otherwise append the current line to the currentComment
                    commentBuilder.append(temp);
                    continue;
                } else {
                    if (contentFound) { //if content has been found
                        if (commentStartEndMatcher.reset(temp).matches()) { //if the current line matches the comment start line
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
                //currentContent = currentContent.concat(temp);
                contentBuilder.append(temp);
            }

            String trimmedName = currentPassageName.trim(); // we obtain the trimmed version of the passage name

            if (!passageNames.add(trimmedName)){ // we attempt to add it to the set of passage names, but, if it fails...

                int duplicateCounter = 1; // yep, we're going to be appending a counter to it until it works

                String newName = trimmedName + "_" + duplicateCounter; // appends _1, sees if it's unique
                while (!passageNames.add(newName)){ // keeps incrementing x in the _x suffix until it is unique enough
                    duplicateCounter += 1;
                    newName = trimmedName + "_" + duplicateCounter;
                }
                trimmedName = newName; // overwrites the duplicate name with one that's unique enough
            }

            PassageEditingInterface thePassage = new EditablePassage(
                    trimmedName,
                    contentBuilder.toString().trim(),
                    commentBuilder.toString().trim(),
                    currentPassageMetadata
            );
            pMap.put(thePassage.getPassageUUID(),thePassage);
        } while(notDone);


        // this holds passages from the .hecc that may have links to undefined passages
        Set<PassageEditingInterface> thePassagesWithUnresolvedLinks = new HashSet<>();

        //And now, time to actually update the UUIDs.
        for (PassageEditingInterface e: pMap.values()){
            e.updateLinkedUUIDs(pMap);
            // we also look for passages with links to undefined passages
            if (e.getLinkedPassages().size() != e.getLinkedPassageUUIDs().size()){
                // we'll know it's unresolved because their linked UUIDs wont match their linked names
                thePassagesWithUnresolvedLinks.add(e);
            }
        }

        // and we resolve the problems with the unresolved links
        for (PassageEditingInterface unresolved: thePassagesWithUnresolvedLinks) {
            unresolved.resolvePassageLinks(pMap);
        }



        // if any passages have the default position of (0,0), HECC-IT will attempt to displace them from their parent,
        // so they're not all on top of each other. It does look ugly, but that's an occupational hazard.
        for (PassageEditingInterface e: pMap.values()){
            if (e.getPosition().isZero()){

                if (theMetadata.getStartPassage().equals(e.getPassageName())){
                    continue; // the start passage won't get displaced.
                }

                UUID currentUUID = e.getPassageUUID();

                // attempts to find any parent passage (containing a link to this passage).
                Optional<PassageEditingInterface> parent = pMap.values().stream().filter(
                        p1 -> p1.getLinkedPassageUUIDs().contains(currentUUID)
                ).findAny();

                // if it does have a parent, the position of this vector is displaced by
                // a random vector at a random angle from the parent object's position
                if (parent.isPresent()){
                    e.updatePosition(
                        Vector2D.randomVectorFromOrigin(parent.get().getPosition(), 128, 128)
                    );
                }

            }
        }


        return pMap;
    }


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
            System.out.println(e.getValue().getAsStringForDebuggingReasons());
        }
        System.out.println("\nyep thats everything printed");

    }

    /**
     * Turns the read data back to a .hecc string
     * @return the .hecc version of the data which has been read.
     */
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


    /**
     * A deprecated main function, intended for validation and such
     * @param args
     * @throws Exception
     */
    @Deprecated
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
