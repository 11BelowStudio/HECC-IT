package hecc_up;

import heccCeptions.HeccCeption;
import utilities.TextAssetReader;

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
     * This constructs the HeccUpHandler
     * @param parent the logger class that's the parent of this one
     */
    public HeccUpHandler(LoggerInterface parent){
        logger = parent;
    }

    /**
     * This is a public wrapper function for heccUpTheGame
     * @param heccFileLocation where the hecc file to be tested is
     * @param outputFolderLocation where to output the heccin game to
     * @return boolean, whether or not this hecced up the game successfully
     */
    public boolean attemptToHeccUpTheGame(String heccFileLocation, String outputFolderLocation){
        try{
            if (heccUpTheGame(heccFileLocation, outputFolderLocation)){
                logger.logInfo("Done!");
                return true;
            } else{
                logger.logInfo("Unable to output the game!");
            }
        } catch (Exception e){
            logger.logInfo(e.getMessage());
        }
        return false;
    }

    /**
     * Attempts to hecc up the game
     * @param heccFileLocation where the .hecc file is
     * @param outputFolderLocation where the output folder is
     * @return true if it could be parsed/output successfully. false otherwise.
     * @throws HeccCeption parser-related exception
     * @throws IOException thrown if there's a problem opening/reading the file
     */
    private boolean heccUpTheGame(String heccFileLocation, String outputFolderLocation) throws HeccCeption, IOException {
        HeccParser parser = new HeccParser(
                TextAssetReader.fileToString(heccFileLocation),
                logger
        );
        FolderOutputter outputter = new FolderOutputter(
                outputFolderLocation,
                logger
        );
        //first, check that the output folder actually exists
        if (outputter.doesOutputFolderExist()) {
            //if the output folder exists, attempt to construct the passage objects
            logger.logInfo("output folder exists");

            //if the output folder exists, attempt to construct the passage objects
            if (parser.constructThePassageObjects()) {
                //then, attempt to prepare the hecced data
                if (parser.prepareHeccedData()) {
                    //finally, if everything worked, output the game

                    //uses heccedData and metadata to from passageParser to output the HECCIN Game
                    outputter.outputTheGameWithMetadata(parser.getHeccedData(), parser.getMetadata());

                    //confirm it's done
                    return true;
                }
            }
        } else {
            //log an error message if the output folder vanishes
            logger.logInfo("The game cannot be parsed and output, as there is no output folder for it");
        }
        return false;
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
        HeccParser parser = new HeccParser(
                String.join("\n", Files.readAllLines(heccFilePath)),
                logger
        );
        FolderOutputter outputter = new FolderOutputter(
                outputFolderPath,
                logger
        );
        //first, check that the output folder actually exists
        if (outputter.doesOutputFolderExist()) {
            //if the output folder exists, attempt to construct the passage objects
            logger.logInfo("output folder exists");

            //if the output folder exists, attempt to construct the passage objects
            if (parser.constructThePassageObjects()) {
                //then, attempt to prepare the hecced data
                logger.logInfo("Passage objects constructed");
                if (parser.prepareHeccedData()) {
                    //finally, if everything worked, output the game
                    logger.logInfo("Proceeding to output");
                    //uses heccedData and metadata to from passageParser to output the HECCIN Game
                    outputter.outputTheGameWithMetadata(parser.getHeccedData(), parser.getMetadata());

                    //confirm it's done
                    return true;
                }
            }
        } else {
            //log an error message if the output folder vanishes
            logger.logInfo("The game cannot be parsed and output, as there is no output folder for it");
        }
        return false;
    }


}
