package oh_hecc.mvc.model_bits;

import oh_hecc.mvc.Model;
import oh_hecc.mvc.PassageModel;
import utilities.Vector2D;

import java.awt.*;

/**
 * This class basically represents the buttons that will appear across the bottom of the model,
 * allowing the user to do things like access the metadata editing window, add a new passage,
 * save their work, save+quit, etc.
 * <p>
 * Y'know, the boring bits.
 */
public class ModelButtonObject extends PassageModelObject {

    /**
     * The words that are on this button
     */
    StringObject buttonLabel;

    /**
     *
     */
    float scaledLeftSide;
    float scaledRightSide;


    /**
     * The constructor for the ModelButtonObject
     * @param model the PassageModel that this object refers to
     * @param relativeLeft 0-1. 0: the left side of this button will be at the far left of viewport. 1: left side of button at far right of viewport
     * @param relativeRight 0-1. 0: the right side of this button will be at the far left of viewport. 1: right side of button at far right of viewport
     * @param text text for the button
     */
    public ModelButtonObject(PassageModel model, float relativeLeft , float relativeRight, String text){
        super(new Vector2D(), model);

        scaledLeftSide = relativeLeft;
        scaledRightSide = relativeRight;

        buttonLabel = new StringObject(text,StringObject.MIDDLE_ALIGN);

        height = 32;

        width = (int)((scaledRightSide-scaledLeftSide) * Model.GET_MODEL_WIDTH());

        position.set(
                (scaledLeftSide * Model.GET_MODEL_WIDTH()) + (width/2.0),
                Model.GET_MODEL_HEIGHT() - (height/2.0)
        );

        areaRectangle = new Rectangle(-(width/2),-height/2, width,height);

    }

    @Override
    public void update() {

    }

    /**
     * Call this if the model gets resized, so this will remain the same size (relative to the viewable model itself)
     * and at the the bottom of the model
     */
    public void resize(){
        width = (int)((scaledRightSide-scaledLeftSide) * Model.GET_MODEL_WIDTH());

        position.set(
                (scaledLeftSide * Model.GET_MODEL_WIDTH()) + (width/2.0),
                Model.GET_MODEL_HEIGHT() - (height/2.0)
        );

        areaRectangle = new Rectangle(-(width/2),-height/2, width,height);
    }

    @Override
    void individualDraw(Graphics2D g) {
        //draw the button rectangle
        g.setColor(objectColour);
        g.fill(areaRectangle);

        //black border for button
        g.setColor(Color.BLACK);
        g.draw(areaRectangle);

        //draw the button label
        buttonLabel.draw(g);

    }
}
