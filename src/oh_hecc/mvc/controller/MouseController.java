package oh_hecc.mvc.controller;

import oh_hecc.mvc.MouseControlModelInterface;

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

    public MouseController(MouseControlModelInterface m){
        controlledModel = m;
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
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
