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


    private final UUID fromUUID;
    private final UUID toUUID;

    private final PassageObject fromObject;
    //private PassageObject toObject;

    private final Vector2D pointToPosition;

    private final Vector2D vectorFromSourceToDestination;

    private double rotationAngle;

    private Polygon pointTriangle;

    PassageLinkObject(EditModelInterface model, PassageObject sourceObject, UUID destinationUUID) {
        super(sourceObject.position, model);



        fromObject = sourceObject;

        fromUUID = fromObject.getTheUUID();
        toUUID = destinationUUID;

        pointToPosition = theModel.getPassageFromUUID(toUUID).getPosition();


        vectorFromSourceToDestination = Vector2D.subtract(theModel.getPassageFromUUID(toUUID).getPosition(), getPosition());


        rotationAngle = vectorFromSourceToDestination.angle();

        pointTriangle = new Polygon(
                new int[] {-32, 0, 32},
                new int[] {0,(int)(vectorFromSourceToDestination.mag()),0},
                3
        );

        objectColour = SAFETY_PURPLE;



    }

    /**
     * Returns the UUID of the passage that this points to
     * @return the UUID of the passage that this points to
     */
    UUID getTheUUID(){
        return toUUID;
    }



    /**
     * Call this to update the position of where this object should point from/to,
     * as well as the length of the actual pointy arrow thing,
     * in response to either the object it points from or to moving.
     */
    void updatePosition(){
        //updates this position to be the same as the fromObject position
        this.position.set(fromObject.position);
        //updates the vector that points from the source to the destination
        vectorFromSourceToDestination.set(Vector2D.subtract(theModel.getPassageFromUUID(toUUID).getPosition(), getPosition()));

        //finds the angle of the vector that points from the source to the destination
        rotationAngle = vectorFromSourceToDestination.angle();
        //resizes pointTriangle to be as tall as the magnitude of that vector
        pointTriangle.ypoints[1] = (int)vectorFromSourceToDestination.mag();
    }

    @Override
    public void update() {

    }




    @Override
    void individualDraw(Graphics2D g) {
        //TODO: draw arrow from position pointing to pointToPosition

        //rotates this according to the rotationAngle
        AffineTransform notRotated = g.getTransform();
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
