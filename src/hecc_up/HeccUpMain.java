package hecc_up;

import hecc_up.heccCeptions.ParserException;
import utilities.TextAssetReader;

public class HeccUpMain {
    //The main class for HeccUp

    private PassageParser passageParser;

    private FolderOutputter outputter;



    public HeccUpMain(){

        passageParser = new PassageParser(TextAssetReader.getHeccString());

        outputter = new FolderOutputter();

        outputter.setupOutputFolder("heccin_game");

    }

    public void heccUpTheGame(){

        //first, check that the output folder actually exists
        if(outputter.doesOutputFolderExist()) {
            System.out.println("output folder exists");
            try {
                //if the output folder exists, attempt to construct the passage objects
                if (passageParser.constructThePassageObjects()) {
                    //then, attempt to prepare the hecced data
                    if (passageParser.prepareHeccedData()) {
                        //finally, if everything worked, output the game
                        outputter.outputTheGame(passageParser.getHeccedData());

                        System.out.println("It is done.");
                    }
                }
            } catch (ParserException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The game cannot be parsed and output, as there is no output folder for it");
        }
    }



    public static void main(String[] args) {
        HeccUpMain heccUp = new HeccUpMain();
        heccUp.heccUpTheGame();
    }
}


//TODO stuff for the MVP

//TODO: a version that doesn't have everything in the same Java file

//TODO: replace the map of parsed passages (passage name, passage content) with a map of ParsedPassage objects (passage name, ParsedPassage object)

//TODO: allow user to specify what .hecc file they want to read

//TODO: allow user to specify where they want to save their hecc game

//TODO: general improvements to index.html to make it look less crap

//TODO: GUI for HECC-UP (not talking about OH-HECC)

//TODO stuff for after MVP

//TODO (after MVP): Metadata

//TODO (after MVP): OH-HECC (Optional Help for HECC)