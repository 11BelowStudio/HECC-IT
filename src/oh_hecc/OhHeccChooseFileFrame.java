package oh_hecc;

import oh_hecc.mvc.View;

import javax.swing.*;

/**
 * This class is intended as a frame that holds the prompt for a user to pick a HECC file to edit from
 * (or alternatively create a completely new HECC file to write)
 */
public class OhHeccChooseFileFrame {
    JFrame theFrame;

    String savedFileLocation;

    View ohHeccView;


    public OhHeccChooseFileFrame(){
        theFrame = new JFrame();
    }
}
