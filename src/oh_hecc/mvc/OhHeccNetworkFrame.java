package oh_hecc.mvc;

import utilities.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    public OhHeccNetworkFrame() {
        this(new JFrame());
    }

    /**
     * A version of the constructor that uses a predefined JFrame instead (that it'll basically claim ownership of)
     * @param f a pre-declared JFrame that this object will basically claim ownership of.
     */
    public OhHeccNetworkFrame(JFrame f) {
        theFrame = f;
        theFrame.setIconImages(ImageManager.getOhHeccIcons());
        theFrame.setTitle("OH-HECC! (Optional Help for HECC)");
        theFrame.getContentPane().removeAll();
        theFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        theFrame.setLayout(new BorderLayout());
    }

    /**
     * Constructor that utilizes an existing JFrame and View instead
     * @param f the existing JFrame, which this object will commandeer and put the View object into
     * @param v the existing View object to put into that JFrame.
     */
    public OhHeccNetworkFrame(JFrame f, View v) {
        this(f);
        addTheView(v);
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
        //theFrame.add(BorderLayout.CENTER,theView);


        //makes theFrame visible
        theFrame.pack();
        theFrame.invalidate();
        theFrame.setVisible(true);

        //theView.setSize(800,600);

        theFrame.repaint();


    }

    /**
     * Adds the listeners that need to be added to theFrame.
     */
    public void addTheListeners(){

        //adds the resizing ComponentListener to theFrame
        theFrame.addComponentListener(
                new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        System.out.println("Resize event");
                        Dimension newDimension = theFrame.getContentPane().getSize();
                        theView.setSize(newDimension);
                        theView.repaint();
                    }
                }
        );
        theFrame.setSize(800,600);


        theFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.out.println("close event");
                        confirmWindowClose();
                    }
                }
        );


        ModelController mc = new ModelController(theView.theModelThatsBeingViewed, theFrame);
        theFrame.addKeyListener(mc);

        theFrame.revalidate();

        theFrame.getContentPane().addMouseListener(mc);
        theFrame.getContentPane().addMouseMotionListener(mc);

        theFrame.getContentPane().revalidate();
        theFrame.repaint();

        theFrame.requestFocus();

    }


    /**
     * Brings up a JOptionPane ConfirmDialog to ask the user if they're sure they want to quit
     * Closes the window if the user confirms that they want to close the window.
     */
    void confirmWindowClose(){
        if (JOptionPane.showConfirmDialog(
                null,
                "<html><p>"
                        +"Do you want to close this window?<br>"
                        +"All unsaved changes will be lost!"
                        +"</p></html>",
                "Are you sure?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        ) == JOptionPane.YES_OPTION){
            closeTheWindow();
        }
    }

    /**
     * Literally just closes this window (frame is made invisible, and is promptly disposed of)
     */
    void closeTheWindow(){
        theFrame.setVisible(false);
        theFrame.dispose();
    }

}
