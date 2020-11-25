package oh_hecc.mvc;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ControllerAction {

    Point leftClickLocation;

    Point rightClickLocation;

    boolean clickedThisFrame;

    boolean holdingMouseDown;

    ControllerAction(){
        leftClickLocation = new Point();
        rightClickLocation = new Point();
        clickedThisFrame = false;
        holdingMouseDown = false;
    }

    public void setLeftClickLocation(Point mouseClickLocation) {
        leftClickLocation.setLocation(mouseClickLocation);
    }

    public void setRightClickLocation(Point mouseClickLocation){
        rightClickLocation.setLocation(mouseClickLocation);
    }

    public Point getLeftClickLocation(){
        return leftClickLocation.getLocation();
    }

    public Point getRightClickLocation(){
        return rightClickLocation.getLocation();
    }
}
