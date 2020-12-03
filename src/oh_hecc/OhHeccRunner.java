package oh_hecc;

import oh_hecc.game_parts.metadata.EditableMetadata;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.mvc.Model;
import oh_hecc.mvc.controller.Controller;
import oh_hecc.mvc.OhHeccNetworkFrame;
import oh_hecc.mvc.PassageModel;
import oh_hecc.mvc.View;
import utilities.TextAssetReader;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class OhHeccRunner {

    private OhHeccChooseFileFrame chooseFileFrame;

    private String heccFilePath;

    private OhHeccNetworkFrame editFrame;

    private PassageModel editModel;

    private View editorView;

    private OhHeccParser heccParser;

    private Controller ohHeccItsAController;

    public OhHeccRunner(){
        //TODO: make this. Asks user for a hecc file to open/create a new hecc file. Then opens the hecc file via MVC stuff (and runs it)
    }


    void openFileAtLocation(String heccFilePath){
        //heccParser
    }


    void makeNewHeccFileAtLocation(String heccFilePath){

    }

    /**
     * Dummy method for testing stuff out.
     *
     * yeet this asap.
     */
    private void yknowWhatItsTimeToTestThisOut() throws Exception{
        heccFilePath = "src/assets/textAssets/HeccSample2.hecc";
        heccParser = new OhHeccParser(
                TextAssetReader.fileToString(heccFilePath)
        );
        editModel = new PassageModel(heccParser.getMetadata(),heccParser.getHeccMap());
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
                            Files.write(Paths.get(heccFilePath), Collections.singleton(editModel.getHecced()));
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
        heccRunner.yknowWhatItsTimeToTestThisOut();
    }
}
