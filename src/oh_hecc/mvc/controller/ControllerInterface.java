package oh_hecc.mvc.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * An interface for the Controller
 */
public interface ControllerInterface extends MouseListener, MouseMotionListener {

    /**
     * Obtains the action of the controller, albeit wrapped in the ActionViewer interface
     * @return the actionViewer of the controller.
     */
    ActionViewer getAction();
}
