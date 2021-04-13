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


    private final UUID toUUID;

    private final ObjectWithAPosition fromObject;
    //private PassageObject toObject;

    private final Vector2D vectorFromSourceToDestination;

    private double rotationAngle;

    private final Polygon pointTriangle;

    /**
     * Constructor for the PassageLinkObject
     * @param model the EditModelInterface object that this object appears in
     * @param sourceObject the source PassageObject that this 'link' is 'from'.
     * @param destinationUUID the UUID of the PassageEditingInterface/PassageObject that this 'link' effectively 'links' to.
     */
    PassageLinkObject(EditModelInterface model, ObjectWithAPosition sourceObject, UUID destinationUUID) {
        super(sourceObject.getPosition(), model);



        fromObject = sourceObject;

        //UUID fromUUID = fromObject.getTheUUID();
        toUUID = destinationUUID;

        //Vector2D pointToPosition = theModel.getPassageFromUUID(toUUID).getPosition();


        vectorFromSourceToDestination = Vector2D.subtract(theModel.getPassageFromUUID(toUUID).getPosition(), getPosition());


        rotationAngle = vectorFromSourceToDestination.angle();

        pointTriangle = new Polygon(
                new int[] {-16, 0, 16},
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
        //updates this position to be the same as the fromObject position
        this.position.set(fromObject.getPosition());
        //updates the vector that points from the source to the destination
        vectorFromSourceToDestination.set(Vector2D.subtract(theModel.getPassageFromUUID(toUUID).getPosition(), getPosition()));

        //finds the angle of the vector that points from the source to the destination
        rotationAngle = vectorFromSourceToDestination.angle();
        rotationAngle -= (Math.PI/2.0);
        //resizes pointTriangle to be as tall as the magnitude of that vector
        pointTriangle.ypoints[1] = (int)vectorFromSourceToDestination.mag();
    }




    @Override
    void individualDraw(Graphics2D g) {


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
