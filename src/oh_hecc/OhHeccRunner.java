package oh_hecc;

import oh_hecc.game_parts.GameDataObject;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.mvc.controller.Controller;
import oh_hecc.mvc.OhHeccNetworkFrame;
import oh_hecc.mvc.PassageModel;
import oh_hecc.mvc.View;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class OhHeccRunner {

    private final JFrame theFrame;


    private OhHeccNetworkFrame editFrame;

    private PassageModel editModel;

    private View editorView;

    private OhHeccParser heccParser;


    private GameDataObject theGameData;

    private ChooseFile chooseFile;

    public OhHeccRunner(){

        //TODO: make this. Asks user for a hecc file to open/create a new hecc file. Then opens the hecc file via MVC stuff (and runs it)
        theFrame = new JFrame("OH-HECC!");
        theFrame.setLayout(new BorderLayout());

        chooseFile = new ChooseFile(
                this::openFileAtLocation,
                this::makeNewHeccFileAtLocation
        );
        theFrame.add(chooseFile.getThePanel(), BorderLayout.CENTER);

        theFrame.pack();
        theFrame.revalidate();
        theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        theFrame.setVisible(true);



    }


    /**
     * Opens a hecc file at a given location for editing
     * @param heccFilePath the Path leading to the file that needs to be opened for editing
     * @return true if it was opened successfully
     */
    boolean openFileAtLocation(Path heccFilePath){
        boolean success = false;
        try{
            heccParser = new OhHeccParser(
                    String.join("\n",Files.readAllLines(heccFilePath))
            );
            theGameData = new GameDataObject(heccParser.getHeccMap(),heccParser.getMetadata(),heccFilePath);

            //TODO: open model, yeet file chooser panel

            startEditingTheGameData();

            success = true;
            System.out.println("nice");
        } catch (Exception e){
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
        } catch (Exception e) {}
        return success;
    }


    void startEditingTheGameData(){
        editModel = new PassageModel(theGameData);
        editorView = new View(editModel);
        editFrame = new OhHeccNetworkFrame(theFrame, editorView);

        editFrame.addTheListeners();

        editFrame.theFrame.invalidate();

        /*
        Timer repaintTimer = new Timer(
                1000,
                e -> editorView.repaint()
        );

        repaintTimer.start();

         */

        editFrame.theFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {

                        //repaintTimer.stop();

                        //TODO: better save thing
                        /*
                        try {
                            Files.write(theGameData.getSavePath(), Collections.singleton(theGameData.toHecc()));
                        } catch (IOException ignored) {
                        }

                         */
                    }
                }
        );

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

    public static void main(String[] args) throws Exception{
        OhHeccRunner heccRunner = new OhHeccRunner();
        //heccRunner.yknowWhatItsTimeToTestThisOut();
    }
}
