package oh_hecc.mvc.model_bits;

import utilities.Vector2D;

import java.awt.*;

/**
 * Interface for an object which can be selected/deselected/moved.
 */
public interface SelectableObject {

    /**
     * Call this if this object is now selected.
     */
    void nowSelected();

    /**
     * Call this when the object gets deselected.
     */
    void deselected();

    /**
     * Moves this object by the specified amount of movement.
     * Should update the position of this object,
     * and update the areaRectangle of this object (so it can still be clicked and such),
     * and handle any other parts of it that might need to be moved
     * @param moveVector the vector2D representing the movement that needs to be done by this object
     */
    void move(Vector2D moveVector);

    /**
     * Check whether or not this object's areaRectangle contains the clickLocation point
     * @param clickLocation the point that this areaRectangle is being checked against
     * @return true if areaRectangle contains clickLocation
     */
    boolean wasClicked(Point clickLocation);
}
