package oh_hecc.mvc;

import java.awt.*;

/**
 * An interface for the Model being held in the View
 * @deprecated the view isn't actually being used, so there's no reason to have something that could be used to view
 * the model with the view, y'know?
 */
@Deprecated
public interface ViewableModelInterface {

    /**
     * A method to draw the model
     * @param g the graphics2D context being used.
     */
    void draw(Graphics2D g);

    /**
     * A method to set the size of the model
     * @param d the new size of the model in question
     */
    void setSize(Dimension d);
}
