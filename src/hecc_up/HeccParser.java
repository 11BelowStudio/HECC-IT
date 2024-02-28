package hecc_up;

import heccCeptions.*;
import hecc_up.gameParts.Metadata;
import oh_hecc.game_parts.passage.OutputtablePassage;
import oh_hecc.game_parts.passage.PassageOutputtingInterface;
import oh_hecc.game_parts.passage.PassageOutputtingLinkCheckingInterface;
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
    private final List<String> heccedData;

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

        passageNames = new HashSet<>();

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
    public boolean constructThePassageObjects()
            throws NoPassagesException, DuplicatePassageNameException, EmptyPassageException, DeletedLinkPresentException {

        //trims metadata stuff from dataToParse, and creates the Metadata object
        final String dataToParse = makeMetadataObject(this.dataToParse);

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

        final Matcher firstDeclarationMatcher = Pattern.compile(
                "(^::)",
                Pattern.MULTILINE
        ).matcher(dataToParse);

        if(firstDeclarationMatcher.find()){
            int startIndex = firstDeclarationMatcher.start();
            if (startIndex > 1){
                //metadata is everything before first declaration
                metadataString = dataToParse.substring(0,startIndex-1);
                //dataToParse takes up everything after first declaration
                dataToParse = dataToParse.substring(startIndex);
            }
        }

        metadata = new Metadata(metadataString);

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

        final Set<String> pNames = new HashSet<>();

        //attempts to find passage declarations
        final Matcher passageNameMatcher = Pattern.compile(
                "(?<names>(?<=^::)([\\w]+[\\w- ]*)?[\\w]+)",
                Pattern.MULTILINE
        ).matcher(dataToParse);


        while (passageNameMatcher.find()){
            final String currentName = passageNameMatcher.group(0);
            if (!pNames.add(currentName)){
                throw new DuplicatePassageNameException(currentName);
                //complains if there are multiple passages with the same name
            }
        }

        if (pNames.isEmpty()){
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
        final Map<String, PassageOutputtingInterface> pMap = new HashMap<>();

        boolean notDone;

        //matches declarations
        final Matcher declarationMatcher = Pattern.compile(
                "(?<declarations>^::([\\w]+[\\w- ]*)?[\\w]+)",
                Pattern.MULTILINE
        ).matcher(dataToParse);

        //will give this the everythingAfterDeclaration (the content)
        final Matcher passageContentMatcher = Pattern.compile(
                "(?<content>(?<=\\r\\n|\\r|\\n)(?!^::).*\\n(?!^::)|\\r(?!^::)|\\n\\r(?!^::)*.+)",
                Pattern.MULTILINE
        ).matcher("");

        //will use this to crop leading whitespace lines
        final Matcher entirelyWhitespaceMatcher = Pattern.compile(
                "^\\h*$",
                Pattern.MULTILINE
        ).matcher("");

        //matches whitespace at the end of the line
        final Matcher lineEndWhitespaceMatcher = Pattern.compile(
                "\\h*\\R$",
                Pattern.MULTILINE
        ).matcher("");

        //This matches the line that indicates the start of a multiline comment at the end of a passage (containing only ;;)
        final Matcher commentStartEndMatcher = Pattern.compile(
                "^;;\\R$",
                Pattern.MULTILINE
        ).matcher("");

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

                if (currentContent.isEmpty() || currentContent.equals(";;")){
                    // if a passage is empty (or just has the start of the trailing comment), we complain.
                    throw new EmptyPassageException(currentPassageName);
                }

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
        if (metadata.doesStartPassageExist(passageNames)){
            //ensure that every single linked passage is valid
            for (Map.Entry<String, PassageOutputtingInterface> e: passageMap.entrySet()){
                final PassageOutputtingInterface current = e.getValue();
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

        heccedData.add("//HECC UP output (as of 16/04/2021) (HECC-IT produced by Rachel Lowe, 2021)\n\n");

        heccedData.add("// This hecced.js file contains the data for:\n");
        heccedData.add("// " + metadata.getTitle() + "\n");
        heccedData.add("// by " + metadata.getAuthor() + "\n");
        heccedData.add("// IFID: " + metadata.getIfid() + "\n\n");


        // some legal stuff
        heccedData.add(
                "/*\n" +
                "LEGAL STUFF:\n" +
                "The author of the game held in this hecced.js file shall be considered the\n" +
                "\towner of this file, and may opt to select any license they want for this hecced.js file.\n"+
                "If the author of this game has not selected a license, assume that this hecced.js file has\n"+
                "\tbeen distributed under the terms of the Mozilla Public License (v2.0) by the author.\n" +
                "\t\tYou can obtain a copy of that license at http://mozilla.org/MPL/2.0/\n"+
                "If the author of this file wishes to use a different license, they may include another one\n"+
                "\twithin the source code of this file, underneath this comment block, which shall,\n" +
                "\tfor all intents and purposes, be considered to be the license for this hecced.js file.\n" +
                "Alternatively, another license may be distributed with this file, in a file called 'LICENSE',\n" +
                "\twhich shall be the license under which the hecced.js file has been distributed.\n"+
                "TL;DR the author of this game owns and gets to choose the license for this hecced.js file (because it's their game).\n" +
                "*/\n\n"
        );

        final String theStart = metadata.getStartPassage();

        //declaration of starting passage name is added to heccedData
        heccedData.add("var startingPassageName = \""+theStart+"\";\n\n");

        //starts the declaration of the getHECCED function in heccedData
        heccedData.add("function getHECCED(){\n\n");


        try {
            // we attempt to find the passages that are actually linked to something else, and only output them.

            //final Set<String> nonOrphanPassageNames = getNamesOfAllNonOrphanPassages(new HashSet<>(), theStart);

            //for (String passageName : nonOrphanPassageNames) {
            //    heccedData.add(passageMap.get(passageName).getHecced());
            //}

            // we obtain the non-orphan passages
            //final List<PassageOutputtingLinkCheckingInterface> nonOrphans = getAllNonOrphanPassages(theStart);

            // and then they get hecced.
            //for (PassageOutputtingLinkCheckingInterface outputThis: nonOrphans) {
            //    heccedData.add(outputThis.getHecced());
            //}

            // we add the hecced non-orphan passages to the hecced data
            heccedData.addAll(outputAllNonOrphanPassages(theStart));

        } catch (StackOverflowError soe){

            // if recursion screws us over, we just output all the passages.

            for (Map.Entry<String, PassageOutputtingInterface> e: passageMap.entrySet()){
                final PassageOutputtingInterface current = e.getValue();

                //ensure that the passages they link to are valid
                if (current.validateLinkedPassages(passageNames)){
                    heccedData.add(current.getHecced());
                    e.setValue(current);
                } else {
                    //stop everything if an invalid link is found
                    return false;
                }
            }
        }


        heccedData.add("\n\ttheHeccer.printPassages();\n");

        heccedData.add("\n\ttheHeccer.loadCurrentPassage();\n\n");

        heccedData.add("}\n");

        heccedData.add("\n//that's all, folks!");



        //outputs any info the user might need regarding any missing metadata in the Metadata object
        logger.logInfo(metadata.outputMetadataDefinitionInstructions());


        return true;
    }


    /**
     * Obtains a list containing the hecced data of all the passages that are linked to the start passage,
     * depth-first non-recursive search
     * @param startPassage name of the start passage
     * @return the set of all the PassageOutputtingLinkCheckingInterface objects that are reachable from the Start passage
     * @throws UndefinedPassageException if a passage fails a validity check
     */
    private List<String> outputAllNonOrphanPassages(String startPassage) throws UndefinedPassageException{

        // these are the known linked passages we will be returning
        final List<String> knownLinked = new ArrayList<>();
        //final List<PassageOutputtingLinkCheckingInterface> knownLinked = new ArrayList<>();

        // a set of all the names of passages we haven't found yet
        final Set<String> allNotFoundYet = new HashSet<>(passageNames);
        // a list holding the names of the passages we are currently iterating through
        final List<String> currentPassages = new ArrayList<>();
        // a set holding the names of all the passages that are children of the current layer of passaged
        final Set<String> nextPassages = new HashSet<>();


        // add the start passage name to the 'nextPassages' set
        nextPassages.add(startPassage);
        // and remove it from the 'notFoundYet' set
        allNotFoundYet.remove(startPassage);

        // if there are next passages
        while (!nextPassages.isEmpty()){

            // the next passages are now the current passages
            currentPassages.clear();
            currentPassages.addAll(nextPassages);
            // and the next passages are cleared, so they can be the actual next passages
            nextPassages.clear();

            // now, for all of the 'current passages'
            for (String p: currentPassages) {
                // we obtain that particular passage
                final PassageOutputtingLinkCheckingInterface thisPassage = passageMap.get(p);
                // we ensure it's valid
                thisPassage.validateLinkedPassagesThrowingException(passageNames);

                // it gets hecced, and that hecced data is added to the list of known linked passages
                knownLinked.add(thisPassage.getHecced());
                // knownLinked.add(thisPassage.getHecced());

                // we obtain the names of child passages
                final Set<String> children = new HashSet<>(thisPassage.getLinkedPassages());
                // we only keep the ones that haven't been found yet
                children.retainAll(allNotFoundYet);
                // then we ensure that the ones that hadn't yet been found are no longer in that set of 'not found' passages.
                allNotFoundYet.removeAll(children);
                // we add these newly-found children to the set of next passages
                nextPassages.addAll(children);

            }

        }
        // we return the known linked passages
        return knownLinked;

    }

    /**
     * This method is used to get the names of all non-orphan passages, recursively.
     * @param knownLinked a set with the names of all known passages that are linked together.
     * @param currentPassageName the name of the current passageOutputtingInterface object that is being looked at.
     *                       If this passage name is already in knownLinked, it's skipped.
     *                       If it links to a passage which is already in knownLinked, that passage is skipped
     *                       (so we don't get any overflows).
     * @return a set with the names of all passages that are linked together.
     * @throws UndefinedPassageException if there's a problem with the current passage.
     * @throws StackOverflowError because recursion do be like that sometimes
     * @deprecated use outputAllNonOrphanPassages instead, it's not recursive, outputs the hecced data, much better.
     * @see HeccParser#outputAllNonOrphanPassages(String) outputAllNonOrphanPassages
     */
    @Deprecated
    private Set<String> getNamesOfAllNonOrphanPassages(
            Set<String> knownLinked,
            String currentPassageName
    ) throws UndefinedPassageException, StackOverflowError {



        if (knownLinked.contains(currentPassageName)){
            return knownLinked; // if this one is already known, we just skip it
        }

        // we get the actual passage we need for this situation
        final PassageOutputtingLinkCheckingInterface currentPassage = passageMap.get(currentPassageName);


        // if the contents of the passage are bad, this gets thrown.
        currentPassage.validateLinkedPassagesThrowingException(passageNames);

        // this passage is known and is added to the set
        knownLinked.add(currentPassageName);

        // we take a copy of the set of the passages which this passage is linked to
        final Set<String> linkedPassageNames = new HashSet<>(currentPassage.getLinkedPassages());

        if (!linkedPassageNames.isEmpty()){ // if it has any links

            //we don't want to accidentally infinite loop ourselves, so any already-found passages are ignored.
            linkedPassageNames.removeAll(knownLinked);

            for (String passageName: linkedPassageNames){ //we loop through the remainer

                if (knownLinked.contains(passageName)){
                    continue;
                    // if we were looking through a previous child and this current one was already found, it's skipped.
                }

                getNamesOfAllNonOrphanPassages(knownLinked, passageName); // we attempt to do the same for this child
            }
        }

        return knownLinked; // boom thats all the child passages that this passage is linked to.

    }


    /**
     * Obtains the Metadata object
     * @return the Metadata object
     */
    public Metadata getMetadata(){
        return metadata;
    }

    /**
     * Obtains the heccedData list
     * @return the heccedData list
     */
    public List<String> getHeccedData(){
        return heccedData;
    }

    /**
     * Prints the Passage objects in the passageMap for debugging reasons
     */
    public void printPassageObjects(){
        //prints the passage objects for debugging reasons
        for (Map.Entry<String, PassageOutputtingInterface> e: passageMap.entrySet()){
            System.out.println(e.getValue().getAsStringForDebuggingReasons());
            System.out.println("\n");
        }

    }



}
