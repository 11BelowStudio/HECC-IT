package oh_hecc.mvc.model_bits;

import oh_hecc.mvc.EditModelInterface;
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.UUID;

/**
 * Okay so this is basically an object that represents a link from one PassageObject to another.
 */
public class PassageLinkObject extends EditModelObject {

    /**
     * The UUID that this 'link' points to
     */
    private final UUID toUUID;

    /**
     * The object that this 'link' points from
     */
    private final ObjectWithAPosition fromObject;
    //private PassageObject toObject;

    /**
     * The vector difference between the origin position and the destination position
     */
    private final Vector2D vectorFromSourceToDestination = new Vector2D();

    /**
     * The relative rotation of the link object
     */
    private double rotationAngle = 0;

    /**
     * The triangle shape
     */
    private final Polygon pointTriangle;

    /**
     * The x coordinates for the triangle.
     */
    private static final int[] TRIANGLE_X_POINTS = new int[] {-15, 0, 15};

    /**
     * Whether or not this object is just linking back to itself
     */
    private final boolean selfLink;

    /**
     * Constructor for the PassageLinkObject
     * @param model the EditModelInterface object that this object appears in
     * @param sourceObject the source PassageObject that this 'link' is 'from'.
     * @param destinationUUID the UUID of the PassageEditingInterface/PassageObject that this 'link' effectively 'links' to.
     */
    PassageLinkObject(EditModelInterface model, ObjectWithAPositionAndUUID sourceObject, UUID destinationUUID) {
        super(sourceObject.getPosition(), model);



        fromObject = sourceObject;

        //UUID fromUUID = fromObject.getTheUUID();
        toUUID = destinationUUID;

        //Vector2D pointToPosition = theModel.getPassageFromUUID(toUUID).getPosition();

        selfLink = sourceObject.getTheUUID().equals(toUUID); // if it goes to/from the same object, it's a self-link.


        if (!selfLink) {
            vectorFromSourceToDestination.set(
                    Vector2D.subtract(theModel.getPassageFromUUID(toUUID).getPosition(), getPosition())
            );
            rotationAngle = vectorFromSourceToDestination.angle();
        }

        pointTriangle = new Polygon(
                TRIANGLE_X_POINTS,
                new int[] {0,(int)(vectorFromSourceToDestination.mag()),0},
                3
        );



        objectColour = SAFETY_PURPLE;

    }

    /**
     * updates the position of where this object should point from/to,
     * as well as the length of the actual pointy arrow thing,
     * in response to either the object it points from or to moving.
     */
    @Override
    void individualUpdate() {
        final Vector2D pos = fromObject.getPosition();

        //updates this position to be the same as the fromObject position
        this.position.set(pos);

        if (!selfLink){ // don't bother if this links back to itself

            //updates the vector that points from the source to the destination
            vectorFromSourceToDestination.set(Vector2D.subtract(theModel.getPassageFromUUID(toUUID).getPosition(), pos));

            //finds the angle of the vector that points from the source to the destination
            rotationAngle = vectorFromSourceToDestination.angle();
            rotationAngle -= (Math.PI / 2.0);
            //resizes pointTriangle to be as tall as the magnitude of that vector
            pointTriangle.ypoints[1] = (int) vectorFromSourceToDestination.mag();
        }
    }


    /**
     * Basically rotates the affine transformation so the point triangle is pointing in the right direction, then draws
     * the point triangle to point to the object that this links to
     * @param g the graphics context being used.
     */
    @Override
    void individualDraw(Graphics2D g) {


        if (!selfLink) { // don't bother drawing it if it links back to itself
            g.setColor(objectColour);
            //g.drawLine((int)0,(int)0,(int)vectorFromSourceToDestination.x,(int)vectorFromSourceToDestination.y);


            //rotates this according to the rotationAngle
            final AffineTransform notRotated = g.getTransform();
            g.rotate(rotationAngle);

            //makes this the appropriate colour
            g.setColor(objectColour);

            //fills in the triangle pointing from source object to the destination object
            g.fill(pointTriangle);

            //and an outline just because
            g.setColor(Color.BLACK);
            g.draw(pointTriangle);

            //resets the transformation
            g.setTransform(notRotated);
        }
    }
}
