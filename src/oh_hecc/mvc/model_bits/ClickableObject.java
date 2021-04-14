package oh_hecc.mvc.model_bits;

import java.awt.*;

/**
 * Interface of objects that can be clicked
 */
public interface ClickableObject {

    /**
     * Check whether or not this object's areaRectangle contains the clickLocation point
     * @param clickLocation the point that this areaRectangle is being checked against
     * @return true if areaRectangle contains clickLocation
     */
    boolean wasClicked(Point clickLocation);
}
