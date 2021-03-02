package oh_hecc;

import hecc_up.HeccUpGUI;
import oh_hecc.game_parts.GameDataObject;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.mvc.OhHeccNetworkFrame;
import oh_hecc.mvc.PassageModel;
import oh_hecc.mvc.View;
import utilities.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    private GameDataObject theGameData;

    private final ChooseFile chooseFile;


    /**
     * This is the constructor for the HECC-IT runner.
     * It'll ask the user to either make a new HECC file, or pick an existing .hecc file.
     * If the user wants to make a new HECC file, they can then edit it with OH-HECC.
     * If the user selects an existing HECC file, they can choose to edit it with OH-HECC, or export it with HECC-UP.
     */
    public HeccItRunner() {


        theFrame = new JFrame("HECC-IT!");
        theFrame.setLayout(new BorderLayout());

        theFrame.setIconImage(ImageManager.getImage("HECC-IT icon"));

        chooseFile = new ChooseFile(
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
            System.out.println("nice");
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
            System.out.println("nice");
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
            System.out.println("oh hecc that's a new file");
        } catch (Exception e) {
        }
        return success;
    }


    /**
     * This is called if the user chooses to
     */
    void startEditingTheGameData() {
        editModel = new PassageModel(theGameData);
        editorView = new View(editModel);
        editFrame = new OhHeccNetworkFrame(theFrame, editorView);

        editFrame.addTheListeners();
        editFrame.theFrame.invalidate();
    }

    /**
     * Dummy method for testing stuff out.
     *
     * yeet this asap.
     * @deprecated
     */
    private void yknowWhatItsTimeToTestThisOut() throws Exception{
        Path heccPath = Paths.get("src/assets/textAssets/HeccSample2.hecc");
        /*
        heccFilePath = "src/assets/textAssets/HeccSample2.hecc";
        heccParser = new OhHeccParser(
                TextAssetReader.fileToString(heccFilePath)
        );

         */
        heccParser = new OhHeccParser(
                String.join("\n",Files.readAllLines(heccPath))
        );
        theGameData = new GameDataObject(heccParser.getHeccMap(), heccParser.getMetadata(), heccPath);

        editModel = new PassageModel(theGameData);
        editorView = new View(editModel);
        //editFrame = new OhHeccNetworkFrame();
        //editFrame.addTheView(editorView);

        editFrame = new OhHeccNetworkFrame();
        //editFrame.addTheView(editModel);
        editFrame.addTheView(editorView);
        editFrame.addTheListeners();

        editFrame.theFrame.invalidate();

        Timer repaintTimer = new Timer(
                1000,
                e -> editorView.repaint()
        );

        repaintTimer.start();

        editFrame.theFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {

                        repaintTimer.stop();

                        //TODO: better save thing
                        try {
                            Files.write(theGameData.getSavePath(), Collections.singleton(theGameData.toHecc()));
                        } catch (IOException ignored) {
                        }
                    }
                }
        );



        //Model m = new PassageModel(new EditableMetadata("sample name","a.n.onymous"), new HashMap<>());
        //View v = new View(m);
        //OhHeccNetworkFrame f = new OhHeccNetworkFrame();
        //f.addTheView(v);
        //f.addTheListeners();

    }

    public static void main(String[] args) throws Exception {
        HeccItRunner heccRunner = new HeccItRunner();
    }
}
