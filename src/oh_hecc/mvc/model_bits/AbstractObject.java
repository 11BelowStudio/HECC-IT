package oh_hecc.mvc.model_bits;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Superclass for all objects in the model.
 */
public abstract class AbstractObject {

    /**
     * The position of this object
     */
    final Vector2D position;

    /**
     * Width of this object
     */
    int width;

    /**
     * Height of this object
     */
    int height;

    /**
     * A hitbox of sorts. But intended for clicking instead. Clickbox? idk.
     */
    Rectangle areaRectangle;

    /**
     * What colour this object is supposed to be
     */
    Color objectColour;


    /**
     * safety orange: #e97600
     */
    static final Color SAFETY_ORANGE = new Color(233, 118, 0);
    /**
     * safety purple: #964f8e
     */
    static final Color SAFETY_PURPLE = new Color(150, 79, 142);


    /**
     * Constructor that sets position to an empty Vector2D
     */
    AbstractObject(){
        this (new Vector2D());
    }

    /**
     * Constructor that sets position to be a given position
     * @param pos the position that this copies (obtained by reference)
     */
    AbstractObject(Vector2D pos) {
        position = pos;
        areaRectangle = new Rectangle();
    }

    Vector2D getPosition(){
        return position;
    }


    public abstract void update();


    public void draw(Graphics2D g){
        AffineTransform initialTransform = g.getTransform();
        g.translate(position.x, position.y);
        individualDraw(g);
        g.setTransform(initialTransform);
    }

    abstract void individualDraw(Graphics2D g);

    public boolean wasClicked(Point clickLocation){
        return areaRectangle.contains(clickLocation);
    }

    /**
     * Call this whenever this object needs to be moved by a certain vector
     * @param moveVector the vector2D representing the movement that needs to be done by this object
     */
    public void move(Vector2D moveVector){
        position.add(moveVector);
    }
}
