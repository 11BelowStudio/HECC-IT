package oh_hecc;

import oh_hecc.mvc.Controller;
import oh_hecc.mvc.OhHeccNetworkFrame;
import oh_hecc.mvc.PassageModel;
import oh_hecc.mvc.View;

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

    }


    void makeNewHeccFileAtLocation(String heccFilePath){

    }
}
