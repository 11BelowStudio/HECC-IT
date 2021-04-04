package oh_hecc.mvc.model_bits;


import java.awt.*;

/**
 * A drawable interface for the PassageObject. Like DrawableObjectWithText but it has links as well.
 */
public interface DrawablePassageObject extends DrawableObjectWithText {

    /**
     * A method which can be used to draw the links of this object
     * @param g the Graphics2D context being used to draw the aforementioned links.
     */
    void drawLinks(Graphics2D g);
}
