package oh_hecc.mvc;

import java.awt.*;

/**
 * An interface that can be called by a Controller to control a Model
 */
public interface MouseControlModelInterface {

    /**
     * Call this when the model is left-clicked
     * @param mLocation location of mouse
     */
    default void leftClick(Point mLocation){
        System.out.println("Left clicked at " + mLocation.toString());
    }

    /**
     * Call this when there's a right-click
     * @param mLocation location of mouse
     */
    default void rightClick(Point mLocation){
        System.out.println("Right clicked at " + mLocation.toString());
    }

    /**
     * Call this when there's a left press
     * @param mLocation location of mouse
     */
    default void leftPress(Point mLocation){
        System.out.println("Left press at " + mLocation.toString());
    }

    /**
     * Call this when there's a right press
     * @param mLocation location of mouse
     */
    default void rightPress(Point mLocation){
        System.out.println("Right press at " + mLocation.toString());
    }

    /**
     * Call this when left button is released
     * @param mLocation location of mouse
     */
    default void leftRelease(Point mLocation){
        System.out.println("Left release at " + mLocation.toString());
    }

    /**
     * Call this when right button is released
     * @param mLocation location of mouse
     */
    default void rightRelease(Point mLocation){
        System.out.println("Right release at " + mLocation.toString());
    }

    /**
     * Call this when dragging with left held
     * @param mLocation location of mouse
     */
    default void leftDrag(Point mLocation){
        System.out.println("left drag at " + mLocation.toString());
    }

    /**
     * Call this when dragging with right held
     * @param mLocation location of mouse
     */
    default void rightDrag(Point mLocation){
        System.out.println("Right drag at " + mLocation.toString());
    }


}
