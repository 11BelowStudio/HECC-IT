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
    private final JFrame theFrame;

    /**
     * The View (as a JComponent) which this frame views
     */
    private JComponent theView;


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

        // confirming window closed
        theFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        //System.out.println("close event");
                        confirmWindowClose();
                    }
                }
        );
        //adds the resizing ComponentListener to theFrame
        theFrame.addComponentListener(
                new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        //System.out.println("Resize event");
                        Dimension newDimension = theFrame.getContentPane().getSize();
                        theView.setSize(newDimension);
                        theView.repaint();
                    }
                }
        );
    }

    /**
     * Constructor that utilizes an existing JFrame and View instead, and calls all the setup stuff.
     * @param f the existing JFrame, which this object will commandeer and put the View object into
     * @param v the existing View object to put into that JFrame.
     * @param mc the ModelController which has listeners that need to be added to the JFrame
     */
    public OhHeccNetworkFrame(JFrame f, View v, ModelController mc) {
        this(f);
        addTheView(v);
        addTheModelController(mc);
        finishSetup();
    }

    /**
     * Adds theViewToAdd to TheFrame. CENTER in TheFrame's content pane.
     * @param viewToAdd the View object that's being added to theFrame
     */
    public void addTheView(View viewToAdd){

        //adds theView

        theView = viewToAdd;
        theFrame.getContentPane().add(BorderLayout.CENTER, theView);


    }

    /**
     * Adds the ModelController to the frame
     * (as a keylistener to the frame, and as mouse(motion)listener(s) to its content pane)
     * @param mc the ModelController to be added
     */
    public void addTheModelController(ModelController mc){
        theFrame.addKeyListener(mc);
        theFrame.getContentPane().addMouseListener(mc);
        theFrame.getContentPane().addMouseMotionListener(mc);
    }

    /**
     * Finishes setting up the frame.
     * Makes it 800*600, requests focus, and invalidates it (so it gets repainted)
     */
    public void finishSetup(){
        theFrame.setSize(800,600);
        theFrame.requestFocus();
        theFrame.invalidate();
    }

    /**
     * Brings up a JOptionPane ConfirmDialog to ask the user if they're sure they want to quit
     * Closes the window if the user confirms that they want to close the window.
     */
    void confirmWindowClose(){
        if (JOptionPane.showConfirmDialog(
                theFrame,
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
