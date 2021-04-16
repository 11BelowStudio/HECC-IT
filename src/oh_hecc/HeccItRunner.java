package oh_hecc;

import hecc_up.HeccUpGUI;
import oh_hecc.game_parts.GameDataObject;
import oh_hecc.game_parts.MVCGameDataInterface;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.mvc.ModelController;
import oh_hecc.mvc.OhHeccNetworkFrame;
import oh_hecc.mvc.PassageModel;
import utilities.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

/**
 * The main class/entry point for the program.
 * User can pick an existing .hecc file to open, or make a new .hecc file.
 * If they opt to make a new one, OH-HECC will open to allow them to edit it.
 * If they opt to open an existing .hecc file, they have a choice between opening it in OH-HECC for editing or HECC-UP for exporting.
 */
public class HeccItRunner {
    /*
      This Source Code Form is subject to the terms of the Mozilla Public
      License, v. 2.0. If a copy of the MPL was not distributed with this
      file, You can obtain one at http://mozilla.org/MPL/2.0/.
     */

    /**
     * The JFrame holding the main HECC-IT program
     */
    private final JFrame theFrame;


    /**
     * This is the constructor for the HECC-IT runner.
     * It'll ask the user to either make a new HECC file, or pick an existing .hecc file.
     * If the user wants to make a new HECC file, they can then edit it with OH-HECC.
     * If the user selects an existing HECC file, they can choose to edit it with OH-HECC, or export it with HECC-UP.
     */
    public HeccItRunner() {


        theFrame = new JFrame("HECC-IT!");
        theFrame.setLayout(new BorderLayout());

        // gives it the HECC-IT logo(s)
        theFrame.setIconImages(ImageManager.getHeccItIcons());

        final ChooseFile chooseFile = new ChooseFile(
                this::openAndStartEditingFileAtLocation,
                this::makeNewHeccFileAtLocation,
                this::openAndHeccUpFileAtLocation
        );
        theFrame.add(chooseFile.getThePanel(), BorderLayout.CENTER);

        theFrame.pack();
        theFrame.revalidate();
        theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        theFrame.setVisible(true);


    }


    /**
     * Opens a hecc file at a given location for editing in OH-HECC
     *
     * @param heccFilePath the Path leading to the file that needs to be opened for editing
     * @return true if it was opened successfully
     */
    boolean openAndStartEditingFileAtLocation(Path heccFilePath) {
        boolean success = false;
        try {
            /*
            OhHeccParser heccParser = new OhHeccParser(
                    String.join("\n", Files.readAllLines(heccFilePath))
            );

            MVCGameDataInterface theGameData = new GameDataObject(
                    heccParser.getHeccMap(),
                    heccParser.getMetadata(),
                    heccFilePath
            );


            startEditingTheGameData(theGameData);

             */
            startEditingTheGameData( // starts editing the gamedata
                    new GameDataObject( // puts the parsed hecc into a gamedataobject (with save file location)
                            new OhHeccParser( // parses the hecc
                                    String.join("\n", Files.readAllLines(heccFilePath)) // reads the hecc
                            ),
                            heccFilePath
                    )
            );

            success = true;
            //System.out.println("nice");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Opens a hecc file at a given location for exporting in HECC-UP
     *
     * @param heccFilePath the Path leading to the file that needs to be opened for exporting
     * @return true if it was opened successfully
     */
    boolean openAndHeccUpFileAtLocation(Path heccFilePath) {
        boolean success = false;
        try {
            new HeccUpGUI(heccFilePath, theFrame);
            success = true;
            //System.out.println("nice");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Makes a new hecc file with given metadata at a given location, and opens it for editing
     * @param heccFilePath the Path leading to the file that needs to be opened for editing
     * @param meta the metadata for this brand new hecc file
     * @return true if the file could be made and opened successfully
     */
    boolean makeNewHeccFileAtLocation(Path heccFilePath, MetadataEditingInterface meta){
        boolean success = false;
        try{
            //makes game data
            final MVCGameDataInterface theGameData = new GameDataObject(
                    meta,
                    heccFilePath
            );
            //makes the .hecc file
            Files.write(theGameData.getSavePath(), Collections.singleton(theGameData.toHecc()));

            //now it'll start editing
            startEditingTheGameData(theGameData);

            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }


    /**
     * This is called to basically load OH-HECC once some game data has been loaded, allowing the user to start
     * editing their .HECC file.
     * @param theGameData the constructed MVCGameDataInterface that holds the game data that needs editing
     */
    void startEditingTheGameData(MVCGameDataInterface theGameData) {
        // creates the PassageModel
        final PassageModel editModel = new PassageModel(theGameData);

        // basically repurposes theFrame for OH-HECC instead, also constructing a new View and ModelController en-route.
        new OhHeccNetworkFrame(
                theFrame,
                //new View(editModel),
                editModel,
                new ModelController(editModel, theFrame)
        );

    }

    /**
     * THE MAIN METHOD FOR HECC-IT. THIS IS <b>THE</b> ENTRY POINT FOR THE PROGRAM
     * @param args command line arguments that aren't used
     */
    public static void main(String[] args){
        /*
            This Source Code Form is subject to the terms of the Mozilla Public
            License, v. 2.0. If a copy of the MPL was not distributed with this
            file, You can obtain one at http://mozilla.org/MPL/2.0/.
         */
        new HeccItRunner();
    }
}
