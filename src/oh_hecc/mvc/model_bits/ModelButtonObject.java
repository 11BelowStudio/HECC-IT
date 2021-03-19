package oh_hecc.mvc.model_bits;

import oh_hecc.mvc.EditModelInterface;
import oh_hecc.mvc.Model;
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.Area;

/**
 * This class basically represents the buttons that will appear across the bottom of the model,
 * allowing the user to do things like access the metadata editing window, add a new passage,
 * save their work, save+quit, etc.
 * <p>
 * Y'know, the boring bits.
 */
public class ModelButtonObject extends EditModelObject {

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
    public ModelButtonObject(EditModelInterface model, float relativeLeft , float relativeRight, String text){
        super(new Vector2D(), model);

        scaledLeftSide = relativeLeft;
        scaledRightSide = relativeRight;

        buttonLabel = new StringObject(text, StringObject.MIDDLE_ALIGN);

        height = 32;

        //Dimension modelDimension = model.getSize();
        int mw = model.getWidth();
        int mh = model.getHeight();

        //TODO: maybe set these to dummy values?
        width = (int) ((scaledRightSide - scaledLeftSide) * mw);

        position.set(
                (scaledLeftSide * mw) + (width / 2.0),
                mh - (height / 2.0)
        );

        areaRectangle = new Rectangle((int) (getPosition().x - (width / 2)), (int) (getPosition().y - (height / 2)), width, height);

        fillArea = new Area(new Rectangle(-width/2, -height/2, width, height));

        objectColour = Model.W3_MIDNIGHT;

    }

    @Override
    public void individualUpdate() {
        // nothing.
    }

    @Override
    public boolean wasClicked(Point clickLocation) {
        if (super.wasClicked(clickLocation)){
            System.out.println(buttonLabel.getString());
            return true;
        }
        return false;
    }

    /**
     * Model should call this for all buttons when it's resized,
     * so the buttons will remain the same size (relative to the viewable model itself)
     * and at the the bottom of the model
     * @param w new width of the model
     * @param h new height of the model
     */
    public void resize(int w, int h){
        width = (int)((scaledRightSide-scaledLeftSide) * w);

        position.set(
                (scaledLeftSide * w) + (width/2.0),
                h - (height/2.0)
        );

        areaRectangle = new Rectangle((int) (position.x -(width/2)),(int) (position.y -(height/2)), width,height);

        fillArea = new Area(new Rectangle(-width/2, -height/2, width, height));
    }

    @Override
    void individualDraw(Graphics2D g) {
        //draw the button rectangle
        g.setColor(objectColour);
        g.fill(fillArea);

        //black border for button
        g.setColor(Color.BLACK);
        g.draw(fillArea);

        //draw the button label
        buttonLabel.draw(g);

    }


}
