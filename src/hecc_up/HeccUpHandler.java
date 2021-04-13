package hecc_up;

import heccCeptions.HeccCeption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * An intermediate class, between the user-facing LoggerInterface object,
 * and the lower-level PassageParser and FolderOutputter objects
 */
public class HeccUpHandler {

    //TODO: incorporate into OH-HECC

    /**
     * The LoggerInterface object that this will log important info to
     */
    private final LoggerInterface logger;


    /**
     * Testing method for testing with.
     */
    HeccUpHandler(){
        logger = new LoggerInterface(){};
    }


    /**
     * This constructs the HeccUpHandler
     * @param parent the logger class that's the parent of this one
     */
    public HeccUpHandler(LoggerInterface parent){
        logger = parent;
    }

    /**
     * This is a public wrapper function for heccUpTheGame
     *
     * @param heccFilePath     path to where the hecc file to be tested is
     * @param outputFolderPath path to where to output the heccin game to
     * @return boolean, whether or not this hecced up the game successfully
     */
    public boolean attemptToHeccUpTheGame(Path heccFilePath, Path outputFolderPath) {
        try {
            if (heccUpTheGame(heccFilePath, outputFolderPath)) {
                logger.logInfo("Done!");
                return true;
            } else {
                logger.logInfo("Unable to output the game!");
            }
        } catch (Exception e) {
            logger.logInfo(e.getMessage());
        }
        return false;
    }

    /**
     * Attempts to hecc up the game
     *
     * @param heccFilePath     where the .hecc file is
     * @param outputFolderPath where the output folder is
     * @return true if it could be parsed/output successfully. false otherwise.
     * @throws HeccCeption parser-related exception
     * @throws IOException thrown if there's a problem opening/reading the file
     */
    private boolean heccUpTheGame(Path heccFilePath, Path outputFolderPath) throws HeccCeption, IOException {

        final HeccParser parser = new HeccParser(
                String.join("\n", Files.readAllLines(heccFilePath)),
                logger
        );

        final FolderOutputter outputter = new FolderOutputter(outputFolderPath);
        //first, check that the output folder actually exists
        //if (outputter.doesOutputFolderExist()) {
        //    logger.logInfo("Output folder has been made");


        //if the output folder exists, attempt to construct the passage objects
        if (attemptToParseTheGame(parser)){

            logger.logInfo("Proceeding to output");
            //uses heccedData and metadata to from passageParser to output the HECCIN Game
            outputter.outputTheGameWithMetadata(parser.getHeccedData(), parser.getMetadata());

            //confirm it's done
            return true;
        }

        //} else {
        ///    //log an error message if the output folder vanishes
        //    logger.logInfo("The game cannot be parsed and output, as there is no output folder for it");
        //}
        return false;
    }


    /**
     * Will attempt to parse the game. Package-private so it can be accessed by HeccUpTests.
     * @param theParser the HeccParser that has the game info in it.
     * @return true if it succeeded, false otherwise. Will also modify the state of the HeccParser.
     * @throws HeccCeption if there's a problem with the hecc file in the hecc parser.
     */
    boolean attemptToParseTheGame(HeccParser theParser) throws HeccCeption{

        //attempt to construct the passage objects
        if (theParser.constructThePassageObjects()) {
            //then, attempt to prepare the hecced data
            logger.logInfo("Passage objects constructed");

            return theParser.prepareHeccedData(); //attempt to prepare the hecced data.
        } else {
            return false;
        }

    }


}
