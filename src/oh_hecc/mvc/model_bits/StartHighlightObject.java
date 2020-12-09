package oh_hecc.mvc.model_bits;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

/**
 * An object that will be visible behind the starting PassageObject basically as a highlight for it
 */
public class StartHighlightObject extends AbstractObject {

    /**
     * Whether or not this object should be visible.
     */
    private boolean visible = false;

    private AbstractObject startObject;

    /**
     * Creates this object
     */
    public StartHighlightObject(){
        this.objectColour = SAFETY_BLUE.brighter();
        this.width = 72;
        this.height = 40;
        this.fillArea = new Area(new RoundRectangle2D.Double(-width/2.0,-height/2.0,width,height,4,4));
        visible = false;
    }

    /**
     * Updates the start object reference to refer to the specified object instead
     * @param obj the object which this should appear behind
     */
    public void setStartObject(AbstractObject obj){
        startObject = obj;
        visible = true;
    }

    /**
     * hides this object
     */
    public void hide(){
        visible = false;
    }

    @Override
    public void individualUpdate() {
        if (visible) {
            this.position.set(startObject.getPosition());
        }
    }

    @Override
    void individualDraw(Graphics2D g) {
        if (visible) {
            g.setColor(objectColour);
            g.fill(fillArea);
        }
    }
}