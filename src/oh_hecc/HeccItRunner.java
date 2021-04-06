package oh_hecc;

import hecc_up.HeccUpGUI;
import oh_hecc.game_parts.GameDataObject;
import oh_hecc.game_parts.MVCGameDataInterface;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.mvc.OhHeccNetworkFrame;
import oh_hecc.mvc.PassageModel;
import oh_hecc.mvc.View;
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

    private final JFrame theFrame;


    private OhHeccNetworkFrame editFrame;

    private PassageModel editModel;

    private View editorView;

    private OhHeccParser heccParser;


    private MVCGameDataInterface theGameData;


    /**
     * This is the constructor for the HECC-IT runner.
     * It'll ask the user to either make a new HECC file, or pick an existing .hecc file.
     * If the user wants to make a new HECC file, they can then edit it with OH-HECC.
     * If the user selects an existing HECC file, they can choose to edit it with OH-HECC, or export it with HECC-UP.
     */
    public HeccItRunner() {


        theFrame = new JFrame("HECC-IT!");
        theFrame.setLayout(new BorderLayout());

        //theFrame.setIconImage(ImageManager.getImage("HECC-IT icon"));
        theFrame.setIconImages(ImageManager.getHeccItIcons());

        ChooseFile chooseFile = new ChooseFile(
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
            heccParser = new OhHeccParser(
                    String.join("\n", Files.readAllLines(heccFilePath))
            );
            theGameData = new GameDataObject(heccParser.getHeccMap(), heccParser.getMetadata(), heccFilePath);


            startEditingTheGameData();

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
            theGameData = new GameDataObject(meta, heccFilePath);
            //makes the .hecc file
            Files.write(theGameData.getSavePath(), Collections.singleton(theGameData.toHecc()));

            //now it'll start editing
            startEditingTheGameData();

            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }


    /**
     * This is called to basically load OH-HECC once some game data has been loaded, allowing the user to start
     * editing their .HECC file.
     */
    void startEditingTheGameData() {
        editModel = new PassageModel(theGameData);
        editorView = new View(editModel);
        editFrame = new OhHeccNetworkFrame(theFrame, editorView);

        editFrame.addTheListeners();
        editFrame.theFrame.invalidate();
    }

    public static void main(String[] args){
        new HeccItRunner();
    }
}
