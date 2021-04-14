package oh_hecc.mvc.model_bits;

import utilities.Vector2D;

/**
 * An interface for objects that can be moved.
 */
public interface MoveableObject {

    /**
     * Moves this object by the specified amount of movement.
     * Should update the position of this object,
     * and update the areaRectangle of this object (so it can still be clicked and such),
     * and handle any other parts of it that might need to be moved
     * @param moveVector the vector2D representing the movement that needs to be done by this object
     */
    void move(Vector2D moveVector);
}
