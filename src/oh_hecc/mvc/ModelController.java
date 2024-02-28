package oh_hecc.mvc;

import javax.swing.*;
import java.awt.event.*;

/**
 * A controller that basically just controls a {@link ControllableModelInterface}
 */
public class ModelController implements MouseListener, MouseMotionListener, KeyListener {

    /**
     * The model that's being controlled.
     */
    private final ControllableModelInterface controlledModel;

    /**
     * The frame holding the model that's being controlled
     */
    private final JFrame theFrame;

    /**
     * Creates this controller.
     *
     * @param m the model that's being controlled
     * @param f the frame holding the model that's being controlled
     */
    public ModelController(ControllableModelInterface m, JFrame f) {
        controlledModel = m;
        theFrame = f;
    }


    /**
     * Called when the mouse is clicked, calls the appropriate click method of the controlledModel, then repaints the frame
     * @param e the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                controlledModel.leftClick(e.getPoint());
                break;
            case MouseEvent.BUTTON3:
                controlledModel.rightClick(e.getPoint());
                break;
        }
        theFrame.repaint();
    }

    /**
     * Called when the mouse is pressed, calls the appropriate press method of the controlledModel, repaints frame
     * @param e the mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                    controlledModel.leftPress(e.getPoint());
                    break;
            case MouseEvent.BUTTON3:
                    controlledModel.rightPress(e.getPoint());
                    break;
        }
        theFrame.repaint();
    }
    /**
     * Called when the mouse is released, calls the appropriate release method of the controlledModel, repaints frame
     * @param e the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                    controlledModel.leftRelease(e.getPoint());
                    break;
            case MouseEvent.BUTTON3:
                    controlledModel.rightRelease(e.getPoint());
                    break;
        }
        theFrame.repaint();
    }

    /**
     * Called when the mouse enters something, does nothing.
     * @param e the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * called when mouse exits something, does nothing.
     * @param e the mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
    /**
     * Called when the mouse is dragged, calls the appropriate drag method of the controlledModel, repaints frame
     * @param e the mouse event
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)){
            controlledModel.leftDrag(e.getPoint());
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            controlledModel.rightDrag(e.getPoint());
        }
        theFrame.repaint();
    }

    /**
     * called when mouse moves, does nothing.
     * @param e the mouse event
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }
    /**
     * called when key is typed, does nothing.
     * @param e the key event
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
    /**
     * Called when key is pressed, calls the appropriate keypress method of the controlledModel, then repaints the frame
     * @param e the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("pressed");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
                controlledModel.xMove(false);
                //System.out.println("l");
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
                controlledModel.xMove(true);
                //System.out.println("r");
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                controlledModel.yMove(false);
                //System.out.println("u");
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                controlledModel.yMove(true);
                //System.out.println("d");
                break;
        }
        theFrame.repaint();
    }
    /**
     * called when key is released, does nothing.
     * @param e the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
