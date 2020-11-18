package oh_hecc.mvc;

import java.awt.*;

public class ControllerAction {

    Point mouseClickLocation;

    ControllerAction(){

    }

    public void setMouseClickLocation(Point mouseClickLocation) {
        this.mouseClickLocation = mouseClickLocation;
    }

    public Point getMouseClickLocation() {
        return mouseClickLocation;
    }
}
