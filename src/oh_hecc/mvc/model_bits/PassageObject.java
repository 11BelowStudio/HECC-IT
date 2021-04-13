package oh_hecc.mvc.model_bits;

import oh_hecc.game_parts.passage.ModelBitsPassageInterface;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.mvc.EditModelInterface;
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.*;

/**
 * ok so basically this represents an EditablePassage object
 * but it's an GUI form instead.
 * bottom text
 */
public class PassageObject extends EditModelObject implements DrawablePassageObject, SelectableObject {


    /**
     * The UUID of the PassageEditingInterface that this passage represents
     */
    private final UUID theUUID;

    /**
     * The PassageEditingInterface held by this object
     */
    private final ModelBitsPassageInterface thePassage;

    /**
     * Map of the PassageLinkObjects from this object.
     */
    private final Map<UUID, PassageLinkObject> linkMap;

    /**
     * Basically a label with the name of the passage.
     */
    private final StringObject passageNameObject;

    //private Rectangle fillRect;

    /**
     * Whether or not this object is 'selected'
     */
    private boolean isSelected;

    /**
     * What colour is overlaying this object (will be drawn over the actual colour when needed)
     */
    private Color overlayColour;

    /**
     * The colour that will be overlain if this PassageObject is currently selected.
     */
    private static final Color SELECTED_COLOUR = new Color(0,255,255,191);


    /**
     * The colour of a passage with the 'NORMAL' status
     */
    private static final Color NORMAL_COLOUR = SAFETY_ORANGE;
    //private static final Color NORMAl_OUTLINE_COLOUR = OUTLINE_SAFETY_ORANGE;

    /**
     * The colour of a passage with the 'END_NODE' status
     */
    private static final Color END_COLOUR = SAFETY_YELLOW;
    //private static final Color END_OUTLINE_COLOUR = OUTLINE_SAFETY_YELLOW;


    //private static final Color START_COLOUR = SAFETY_YELLOW;
    /**
     * The colour of a passage with the 'DELETED_LINK' status
     */
    private static final Color ERROR_COLOUR = SAFETY_RED;


    /**
     * darker orange if there's nothing in the passage ('EMPTY_CONTENT' status)
     */
    private static final Color EMPTY_COLOUR = new Color(190, 70, 30);

    /**
     * Colour for the outline if this passage is a 'point of no return'
     */
    private static final Color POINT_OF_NO_RETURN_OUTLINE_COLOUR = OUTLINE_SAFETY_RED;

    /**
     * Whether or not the passage this refers to is a 'Point Of No Return'
     */
    private boolean pointOfNoReturn = false;

    //private static Vector2D SCROLL_VECTOR = new Vector2D();

    /**
     * Constructor for the PassageObject
     * @param model the model that it's part of
     * @param passage the passage this represents
     */
    public PassageObject(EditModelInterface model, ModelBitsPassageInterface passage){
        super(passage.getPosition(), model);



        overlayColour = SELECTED_COLOUR;

        width = 64;
        height = 32;

        areaRectangle = new Rectangle((int)(getPosition().x - (width/2)), (int)(getPosition().y - (height/2)), width, height);

        fillArea = new Area(new Rectangle(- (width/2), - (height/2), width, height));
        //fillRect = new Rectangle(- (width/2), - (height/2), width, height);
        //fillArea = new Area(fillRect);

        isSelected = false;

        thePassage = passage;
        theUUID = thePassage.getPassageUUID();

        passageNameObject = new StringObject(thePassage.getPassageName(),StringObject.MIDDLE_ALIGN);

        final Set<UUID> linkedUUIDs = thePassage.getLinkedPassageUUIDs();


        linkMap = new HashMap<>();

        linkedUUIDs.forEach(u -> linkMap.put(u,new PassageLinkObject(model,this,u)));


        whatColourShouldThisObjectBe();

    }

    /**
     * Works out what colour this object should be, using the getPassageStatus() method of this object's passage.
     */
    private void whatColourShouldThisObjectBe() {
        switch (thePassage.getPassageStatus()) {
            case NORMAL:
                objectColour = NORMAL_COLOUR;
                break;
            case END_NODE:
                objectColour = END_COLOUR;
                break;
            case EMPTY_CONTENT:
                objectColour = EMPTY_COLOUR;
                break;
            case DELETED_LINK:
                objectColour = ERROR_COLOUR;
                break;
        }
        pointOfNoReturn = thePassage.isThisAPointOfNoReturn();
    }

    /**
     * A method which returns the UUID of the passage which this PassageObject represents
     * @return the UUID of the passage this PassageObject represents.
     */
    public UUID getTheUUID(){
        return theUUID;
    }



    @Override
    void individualUpdate() {
        this.passageNameObject.setText(thePassage.getPassageName()); // makes sure the passage name is correct
        this.position.set(thePassage.getPosition()); // ensures that the position data is correct

        final Set<UUID> setOfLinkedUUIDs = new HashSet<>(thePassage.getLinkedPassageUUIDs());
        if (!linkMap.keySet().equals(setOfLinkedUUIDs)){
            // if the keys of the linkMap aren't the same as the passage's linked UUIDs,
            // we'll need to fix the linkMap so it has all the necessary links.

            // first things first, we omit any links from the linkMap to passages which aren't linked to any more.
            linkMap.keySet().retainAll(setOfLinkedUUIDs);

            // then, we omit the present links from that set.
            setOfLinkedUUIDs.removeAll(linkMap.keySet());
            for (UUID u: setOfLinkedUUIDs) { // finally, if there's any links that need a linkObject, we construct it.
                linkMap.put(u,new PassageLinkObject(theModel,this,u));
            }
        }

        whatColourShouldThisObjectBe();

        updateLinkedObjectPositions();
    }



    /**
     * This method draws the linkObjects associated with this object
     * @param g the Graphics2D thing responsible for the actual drawing stuff
     */
    public void drawLinks(Graphics2D g) {
        for (DrawableObject l : linkMap.values()) {
            l.draw(g);
        }
    }

    /**
     * Call this if this PassageObject is now selected.
     * Sets isSelected to true, and overlays the colour of this passage object with the SELECTED_COLOUR.
     */
    public void nowSelected() {
        isSelected = true;
        overlayColour = SELECTED_COLOUR;
    }

    /**
     * Call this when the passageObject gets deselected.
     * sets isSelected to false.
     */
    public void deselected() {
        isSelected = false;
    }



    /**
     * Subclass-specific draw method.
     * @param g the graphics context being used.
     */
    @Override
    void individualDraw(Graphics2D g) {


        //setting the colour to objectColour
        g.setColor(objectColour);

        //filling in this object's areaRectangle
        //g.fill(fillArea);

        //actually we're filling it in 3d now so it basically has an outline.
        g.fill3DRect(-(width/2), -(height/2), width, height, true);

        if (isSelected){
            //has the 'selected' overlay if this passageObject is selected.
            g.setColor(overlayColour);
            g.fill(fillArea);

        }

        if (pointOfNoReturn){
            //outlined in red if the passage is a point of no return
            g.setColor(POINT_OF_NO_RETURN_OUTLINE_COLOUR);
            g.draw(fillArea);
        }


    }

    /**
     * This method just draws the passageNameObject for this passageObject.
     * Why? Because this way, these can all be rendered after all the passageObjects themselves,
     * so they won't end up covered by another passageObject's rectangle.
     * @param g the Graphics2D object responsible for the actual drawing stuff
     */
    public void drawText(Graphics2D g){
        final AffineTransform backup = g.getTransform();
        g.translate(position.x, position.y);
        passageNameObject.draw(g);
        g.setTransform(backup);
    }


    /**
     * Moves this object by the specified amount of movement.
     * Updates the position of this object,
     * updates the position of the passage,
     * updates this passage's links,
     * tells the model to update all the links of other passageObjects that link to this object,
     * and updates the areaRectangle of this passage (so it can still be clicked and such)
     * @param moveVector the vector2D representing the movement that needs to be done by this object
     */
    @Override
    public void move(Vector2D moveVector) {
        super.move(moveVector);
        thePassage.updatePosition(position); // updates the position of the passage itself.
        updateLinkedObjectPositions();
        theModel.updatePassageObjectLinksWhichLinkToSpecifiedPassage(theUUID);
        areaRectangle = new Rectangle((int)(getPosition().x - (width/2)), (int)(getPosition().y - (height/2)), width, height);
    }

    /**
     * Update the position of a specific passageLinkObject
     * @param updateThisOne the UUID of the link that needs updating
     */
    public void updatePositionOfSpecificLink(UUID updateThisOne){
        if (linkMap.containsKey(updateThisOne)){
            linkMap.get(updateThisOne).update();
        }
    }

    /**
     * Call this after all the passageObjects have been moved.
     * This will ensure that all of the child passageLinkObjects belonging to this object will be moved.
     */
    public void updateLinkedObjectPositions(){
        for (UpdatableObject l: linkMap.values()) {
            l.update();
        }
    }

}
