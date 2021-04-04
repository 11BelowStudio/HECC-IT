package oh_hecc.mvc.model_bits;

import java.awt.*;

/**
 * Like DrawableObject but it also has a method for drawing the text of the object.
 */
public interface DrawableObjectWithText extends DrawableObject {

    /**
     * A method to draw the text of the object
     * @param g the graphics2D context being used to draw the text of the object
     */
    void drawText(Graphics2D g);
}
