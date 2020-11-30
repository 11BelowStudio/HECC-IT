package oh_hecc;

import oh_hecc.game_parts.metadata.EditableMetadata;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.mvc.Model;
import oh_hecc.mvc.controller.Controller;
import oh_hecc.mvc.OhHeccNetworkFrame;
import oh_hecc.mvc.PassageModel;
import oh_hecc.mvc.View;
import utilities.TextAssetReader;

import java.util.HashMap;
import java.util.UUID;

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
        heccParser = new OhHeccParser(
                TextAssetReader.fileToString("src/assets/textAssets/HeccSample.hecc")
        );
        editModel = new PassageModel(heccParser.getMetadata(),heccParser.getHeccMap());
        editorView = new View(editModel);
        //editFrame = new OhHeccNetworkFrame();
        //editFrame.addTheView(editorView);

        editFrame = new OhHeccNetworkFrame();
        //editFrame.addTheView(editModel);
        editFrame.addTheView(editorView);
        editFrame.addTheListeners();


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
