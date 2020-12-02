package oh_hecc.mvc.model_bits;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

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
     * The area what is filled in when drawing this object (sometimes)
     */
    Area fillArea;

    /**
     * What colour this object is supposed to be
     */
    Color objectColour;


    /**
     * safety red: #bd1e24
     */
    static final Color SAFETY_RED = new Color(189, 30, 36);
    /**
     * safety yellow: #f6c700
     */
    static final Color SAFETY_YELLOW = new Color(246, 199, 0);
    /**
     * safety orange: #e97600
     */
    static final Color SAFETY_ORANGE = new Color(233, 118, 0);
    /**
     * safety green: #007256
     */
    static final Color SAFETY_GREEN = new Color(0,114,86);
    /**
     * safety blue: #0067a7
     */
    static final Color SAFETY_BLUE = new Color(0,103,167);
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

    /**
     * Obtains the position vector of this AbstractObject
     * @return
     */
    public Vector2D getPosition(){
        return position;
    }


    /**
     * Update method.
     * areaRectangle is moved to be centered around the position of this object.
     * Then it calls individualObject for the subclass-specific bits.
     */
    public void update(){
        areaRectangle = new Rectangle((int)(getPosition().x - (width/2)), (int)(getPosition().y - (height/2)), width, height);
        individualUpdate();
    }

    /**
     * Subclass-specific update method.
     * Needs to be overridden.
     */
    public abstract void individualUpdate();

    /**
     * A method to draw this object.
     * translates the argument's affine transform by the position, calls individualDraw(g) for this object,
     * then resets the transform to how it was before it got translated.
     * @param g the graphics2D context being used to draw this object.
     */
    public void draw(Graphics2D g){
        AffineTransform initialTransform = g.getTransform();
        g.translate(position.x, position.y);
        individualDraw(g);
        g.setTransform(initialTransform);
    }

    /**
     * Subclass-specific draw method.
     * @param g the graphics context being used.
     */
    abstract void individualDraw(Graphics2D g);

    /**
     * Check whether or not this object's areaRectangle intersects with a particular area
     * @param a the area that this object is being checked against
     * @return true if it intersects, false otherwise.
     */
    public boolean checkIntersectWithArea(Area a){
        return (a.intersects(areaRectangle));
    }

    /**
     * Check whether or not this object's areaRectangle contains the clickLocation point
     * @param clickLocation the point that this areaRectangle is being checked against
     * @return true if areaRectangle contains clickLocation
     */
    public boolean wasClicked(Point clickLocation){
        return areaRectangle.contains(clickLocation);
        //return fillArea.contains(clickLocation);
    }

    /**
     * Call this whenever this object needs to be moved by a certain vector.
     * Adds the moveVector to this object's position.
     * @param moveVector the vector2D representing the movement that needs to be done by this object
     */
    public void move(Vector2D moveVector){
        position.add(moveVector);
    }
}
