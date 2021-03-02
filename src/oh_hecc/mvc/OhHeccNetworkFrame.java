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
        theFrame = new JFrame("OH-HECC!");
        theFrame.setIconImage(ImageManager.getImage("OH-HECC icon"));
        //TODO: add a WindowListener to theFrame via HeccItRunner, so that, when the user tries to close the window, they may save their work before it closes.
        theFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        theFrame.setLayout(new BorderLayout());
    }

    public OhHeccNetworkFrame(JFrame f) {
        theFrame = f;
        //theFrame.setIconImage(ImageManager.getImage("OH-HECC icon"));
        theFrame.setIconImages(ImageManager.getOhHeccIcons());
        theFrame.setTitle("OH-HECC!");
        theFrame.getContentPane().removeAll();
        theFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        theFrame.setLayout(new BorderLayout());
    }

    /**
     * Constructor that utilizes an existing JFrame and View instead
     * @param f the existing JFrame
     * @param v the existing View
     */
    public OhHeccNetworkFrame(JFrame f, View v) {
        this(f);
        //theFrame = f;
        //theFrame.getContentPane().removeAll();
        //theFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //theFrame.setLayout(new BorderLayout());
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

        /*
        theFrame.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()){
                            case KeyEvent.VK_LEFT:
                                theView.theModelThatsBeingViewed.xMove(false);
                                System.out.println("l");
                                break;
                            case KeyEvent.VK_RIGHT:
                                theView.theModelThatsBeingViewed.xMove(true);
                                System.out.println("r");
                                break;
                            case KeyEvent.VK_UP:
                                theView.theModelThatsBeingViewed.yMove(false);
                                System.out.println("u");
                                break;
                            case KeyEvent.VK_DOWN:
                                theView.theModelThatsBeingViewed.yMove(true);
                                System.out.println("d");
                                break;
                        }
                        theFrame.repaint();
                    }
                }
        );

         */


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
