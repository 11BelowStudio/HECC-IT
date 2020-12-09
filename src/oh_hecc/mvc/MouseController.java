package oh_hecc.mvc;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * A controller that basically just controls a MouseControlModelInterface
 */
public class MouseController implements MouseListener, MouseMotionListener {

    /**
     * The model that's being controlled.
     */
    private final MouseControlModelInterface controlledModel;

    /**
     * The frame holding the model that's being controlled
     */
    private final JFrame theFrame;

    /**
     * Creates this controller.
     * @param m the model that's being controlled
     * @param f the frame holding the model that's being controlled
     */
    public MouseController(MouseControlModelInterface m, JFrame f){
        controlledModel = m;
        theFrame = f;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()){
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
        switch (e.getButton()){
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
        switch (e.getButton()){
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
        if (SwingUtilities.isRightMouseButton(e)){
            controlledModel.rightDrag(e.getPoint());
        }
        theFrame.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
