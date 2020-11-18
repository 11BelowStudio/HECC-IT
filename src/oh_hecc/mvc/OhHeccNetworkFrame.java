package oh_hecc.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * This class holds the JFrame which holds the View object responsible for showing the Model to the user
 */
public class OhHeccNetworkFrame {

    /**
     * The JFrame that shows theView (holding the View object) to the user
     */
    public JFrame theFrame;

    private View theView;


    /**
     * Constructs this object, albeit in the form of a frame, with the title 'OH-HECC!', that's invisible and contains nothing.
     * <p>
     * Yes, I know, it's *really* exciting.
     */
    public OhHeccNetworkFrame(){
        theFrame = new JFrame("OH-HECC!");
        //TODO: add a WindowListener to theFrame via OhHeccRunner, so that, when the user tries to close the window, they may save their work before it closes.
        theFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    /**
     * Adds theViewToAdd to TheFrame.
     * <p>
     * Also adds a ComponentListener that will notice if theFrame is resized,
     * and will call the 'setSize' method of theView
     * (resizing theView and the Model it holds to match the size of theFrame's content pane).
     * <p>
     * This method also makes theView actually visible.
     * @param viewToAdd the View object that's being added to theFrame
     */
    public void addTheView(View viewToAdd){

        //adds theView

        theView = viewToAdd;
        theFrame.getContentPane().add(BorderLayout.CENTER, theView);

        //makes theFrame visible
        theFrame.pack();
        theFrame.revalidate();
        theFrame.setVisible(true);

        //adds the resizing ComponentListener to theFrame
        theFrame.addComponentListener(
                new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        Dimension newDimension = theFrame.getContentPane().getSize();
                        theView.setSize(newDimension);
                    }
                }
        );



    }
}
