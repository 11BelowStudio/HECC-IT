package oh_hecc.mvc.model_bits;

import utilities.Vector2D;

/**
 * An interface that literally just has a 'Vector2D getPosition()' method
 */
public interface ObjectWithAPosition {

    /**
     * Obtains the position vector of the object that implements this interface.
     * @return the position
     */
    Vector2D getPosition();
}
