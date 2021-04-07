package oh_hecc.mvc;

import javax.swing.*;
import java.awt.event.*;

/**
 * A controller that basically just controls a ControllableModelInterface
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

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

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

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

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

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
