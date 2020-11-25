package oh_hecc.mvc.controller;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements ControllerInterface, MouseListener, MouseMotionListener {


    private final ControllerAction action;

    public Controller(){
        action = new ControllerAction();
    }

    public ActionViewer getAction(){
        return action;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Point location = e.getPoint();
        switch (e.getButton()){
            case MouseEvent.BUTTON1:
                if (e.getClickCount() == 2){ action.setLeftDoubleClicked(location); }
                action.leftClick(location);
                break;
            case MouseEvent.BUTTON2:
                action.rightClick(location);
                break;
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Point location = e.getPoint();
        switch (e.getButton()){
            case MouseEvent.BUTTON1:
                action.leftPress(location);
                break;
            case MouseEvent.BUTTON2:
                action.rightPress(location);
                break;
        }
    }

    /**
     * Invoked when a mouse button has been released on a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Point location = e.getPoint();
        switch (e.getButton()){
            case MouseEvent.BUTTON1:
                action.leftReleased(location);
                break;
            case MouseEvent.BUTTON2:
                action.rightReleased(location);
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        Point location = e.getPoint();
        switch (e.getButton()){
            case MouseEvent.BUTTON1:
                action.leftDrag(location);
                break;
            case MouseEvent.BUTTON2:
                action.rightDrag(location);
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
