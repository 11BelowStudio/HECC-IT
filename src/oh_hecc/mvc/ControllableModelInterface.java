package oh_hecc.mvc;

import java.awt.*;

/**
 * An interface that can be called by a Controller to control a Model
 */
public interface ControllableModelInterface {

    /**
     * Call this when the model is left-clicked
     *
     * @param mLocation location of mouse
     */
    void leftClick(Point mLocation);

    /**
     * Call this when there's a right-click
     * @param mLocation location of mouse
     */
    void rightClick(Point mLocation);

    /**
     * Call this when there's a left press
     * @param mLocation location of mouse
     */
    void leftPress(Point mLocation);

    /**
     * Call this when there's a right press
     * @param mLocation location of mouse
     */
    void rightPress(Point mLocation);

    /**
     * Call this when left button is released
     * @param mLocation location of mouse
     */
    void leftRelease(Point mLocation);

    /**
     * Call this when right button is released
     * @param mLocation location of mouse
     */
    void rightRelease(Point mLocation);

    /**
     * Call this when dragging with left held
     * @param mLocation location of mouse
     */
    void leftDrag(Point mLocation);

    /**
     * Call this when dragging with right held
     *
     * @param mLocation location of mouse
     */
    void rightDrag(Point mLocation);

    /**
     * Move the viewable area by a fixed amount in the X dimension
     *
     * @param positive if true, move it +100, if false, move it -100.
     */
    void xMove(boolean positive);


    /**
     * Move the viewable area by a fixed amount in the Y dimension
     *
     * @param positive if true, move it +100, if false, move it -100.
     */
    void yMove(boolean positive);


}
