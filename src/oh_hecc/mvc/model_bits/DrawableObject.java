package oh_hecc.mvc.model_bits;

import java.awt.*;

/**
 * Literally just has a `draw(Graphics2D g)` method (used for drawing)
 */
public interface DrawableObject {

    /**
     * A method to draw this object.
     * @param g the graphics2D context being used to draw this object.
     */
    void draw(Graphics2D g);
}
