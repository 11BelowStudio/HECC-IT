package oh_hecc.mvc.controller;

import utilities.Vector2D;

import java.awt.*;

/**
 * Interface for ControllerAction class that basically just holds the methods used for querying it.
 */
@Deprecated
public interface ActionViewer {

    boolean checkForInput();

    Point getLeftLocation();

    boolean checkLeftHold();

    Vector2D getCurrentLeftDrag();

    boolean isLeftDragFinished();

    Rectangle getLeftDragRect();

    boolean checkLeftClick();

    boolean checkLeftDoubleClick();

    Point getLeftDoubleClickLocation();

    Point getRightLocation();

    Vector2D getCurrentRightDrag();

    boolean checkRightHold();

    boolean checkRightClick();
}
