package oh_hecc.mvc.model_bits;

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
public class PassageObject extends EditModelObject {


    /**
     * The UUID of the PassageEditingInterface that this passage represents
     */
    private final UUID theUUID;

    /**
     * The PassageEditingInterface held by this object
     */
    private final PassageEditingInterface thePassage;

    /**
     * Map of the PassageLinkObjects from this object.
     */
    private final Map<UUID, PassageLinkObject> linkMap;

    private final StringObject passageNameObject;

    //private Rectangle fillRect;

    private boolean isSelected;

    /**
     * What colour is overlaying this object (will be drawn over the actual colour when needed)
     */
    private Color overlayColour;

    /**
     * Colour for the outline of this object.
     */
    private Color outlineColour;

    private static final Color NORMAL_COLOUR = SAFETY_ORANGE;
    private static final Color NORMAl_OUTLINE_COLOUR = OUTLINE_SAFETY_ORANGE;

    private static final Color END_COLOUR = SAFETY_YELLOW;
    private static final Color END_OUTLINE_COLOUR = OUTLINE_SAFETY_YELLOW;

    private static final Color SELECTED_COLOUR = new Color(0,255,255,191);

    private static final Color START_COLOUR = SAFETY_YELLOW;

    private static final Color ERROR_COLOUR = SAFETY_RED;

    /**
     * Colour for the outline if this passage is a 'point of no return'
     */
    private static final Color POINT_OF_NO_RETURN_OUTLINE_COLOUR = OUTLINE_SAFETY_RED;

    /**
     * darker orange if there's nothing in the passage
     */
    private static final Color EMPTY_COLOUR = new Color(190, 70, 30);
    /**
     * empty_colour but it's 57% lightness instead of 43%
     */
    private static final Color EMPTY_OUTLINE_COLOUR = new Color(225, 105, 65);


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
    public PassageObject(EditModelInterface model, PassageEditingInterface passage){
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

        Set<UUID> linkedUUIDs = thePassage.getLinkedPassageUUIDs();


        linkMap = new HashMap<>();

        linkedUUIDs.forEach(u -> linkMap.put(u,new PassageLinkObject(model,this,u)));
        /*
        if (!linkedUUIDs.isEmpty()){
            System.out.println("linked UUIDS from " + passage.getPassageName());
            for (UUID u: linkedUUIDs){
                System.out.println(u);
                linkMap.put(u, new PassageLinkObject(model, this, u));
            }
        } else{
            System.out.println("no UUIDs for " + passage.getPassageName());
        }

         */

        //orang
        //this.objectColour = SAFETY_ORANGE;
        whatColourShouldThisObjectBe();

    }

    private void whatColourShouldThisObjectBe(){
        switch (thePassage.getPassageStatus()){
            case NORMAL:
                objectColour = NORMAL_COLOUR;
                break;
            case DELETED_LINK:
                objectColour = ERROR_COLOUR;
                break;
            case END_NODE:
                objectColour = END_COLOUR;
                break;
            case EMPTY_CONTENT:
                objectColour = EMPTY_COLOUR;
                break;
        }
        pointOfNoReturn = thePassage.isThisAPointOfNoReturn();
    }

    public UUID getTheUUID(){
        return theUUID;
    }

    public void moveTo(Vector2D newPosition){
        this.position.set(newPosition);
        areaRectangle = new Rectangle((int)(getPosition().x - (width/2)), (int)(getPosition().y - (height/2)), width, height);
        thePassage.updatePosition(this.position);
        updateLinkedObjectPositions();
    }



    @Override
    public void individualUpdate() {
        this.passageNameObject.setText(thePassage.getPassageName());
        this.position.set(thePassage.getPosition());

        Set<UUID> connectedSet = thePassage.getLinkedPassageUUIDs();
        if (!linkMap.keySet().equals(connectedSet)){
            linkMap.keySet().retainAll(connectedSet);
            connectedSet.removeAll(linkMap.keySet());
            for (UUID u: connectedSet) {
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
    public void drawLinks(Graphics2D g){


        for (PassageLinkObject l: linkMap.values()){
            l.draw(g);
        }
    }

    public void nowSelected(){
        isSelected = true;
        overlayColour = SELECTED_COLOUR;
    }

    public void deselected(){
        isSelected = false;
    }



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

        //and also does an outline of it.
        //g.setColor(outlineColour);
        //g.draw(fillArea);
        //g.draw3DRect(-(width/2), -(height/2), width, height, true);


        //draws the passageNameObject (on top of this object)
        //passageNameObject.draw(g);


    }

    /**
     * This method just draws the passageNameObject for this passageObject.
     * Why? Because this way, these can all be rendered after all the passageObjects themselves,
     * so they won't end up covered by another passageObject's rectangle.
     * @param g the Graphics2D object responsible for the actual drawing stuff
     */
    public void drawPassageNameObject(Graphics2D g){
        AffineTransform backup = g.getTransform();
        g.translate(position.x, position.y);
        passageNameObject.draw(g);
        g.setTransform(backup);
    }


    /*
    @Override
    public void draw(Graphics2D g){
        super.draw(g);
        g.setColor(Color.GREEN);
        g.fill(areaRectangle);
    }

     */


    /*
    public void scroll(Vector2D scrollBy){
        position.set(scrollBy);
    }

     */


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
        thePassage.updatePosition(position);
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
            linkMap.get(updateThisOne).updatePosition();
        }
    }

    /**
     * Call this after all the passageObjects have been moved.
     * This will ensure that all of the child passageLinkObjects belonging to this object will be moved.
     */
    public void updateLinkedObjectPositions(){
        for (PassageLinkObject l: linkMap.values()) {
            l.updatePosition();
        }
    }

    /**
     * Remove the passageLinkObject that points to the (now deleted) passage with the specified UUID
     * from this passageObject's linkMap
     * @param yeetThis the UUID that the link to yeet points to
     */
    public void yeetLinkToYotePassage(UUID yeetThis){
        linkMap.remove(yeetThis);
    }
}
