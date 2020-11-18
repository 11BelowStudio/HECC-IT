package oh_hecc.mvc.model_bits;

import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.mvc.PassageModel;
import utilities.Vector2D;

import java.awt.*;
import java.util.*;

/**
 * ok so basically this represents an EditablePassage object
 * but it's an GUI form instead.
 * bottom text
 */
public class PassageObject extends PassageModelObject {


    /**
     * The UUID of the PassageEditingInterface that this passage represents
     */
    private final UUID theUUID;



    private final PassageEditingInterface thePassage;

    private final Map<UUID, PassageLinkObject> linkMap;

    StringObject passageNameObject;

    public PassageObject(PassageModel model, PassageEditingInterface passage){
        super(passage.getPosition(), model);

        //orang
        this.objectColour = SAFETY_ORANGE;

        width = 64;
        height = 32;

        areaRectangle = new Rectangle((int)(getPosition().x - (width/2)), (int)(getPosition().y - (height/2)), width, height);



        thePassage = passage;
        theUUID = thePassage.getPassageUUID();

        passageNameObject = new StringObject(thePassage.getPassageName(),StringObject.MIDDLE_ALIGN);

        Set<UUID> linkedUUIDs = thePassage.getLinkedPassageUUIDs();

        linkMap = new HashMap<>();

        if (!linkedUUIDs.isEmpty()){
            for (UUID u: linkedUUIDs){
                linkMap.put(u, new PassageLinkObject(theModel, this, u));
            }
        }




    }

    UUID getTheUUID(){
        return theUUID;
    }

    public void moveTo(Vector2D newPosition){
        this.position.set(newPosition);
        thePassage.updatePosition(this.position);
    }

    @Override
    public void update() {

        this.position.set(thePassage.getPosition());

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



    @Override
    void individualDraw(Graphics2D g) {

        //setting the colour to objectColour
        g.setColor(objectColour);

        //filling in this object's areaRectangle
        g.fill(areaRectangle);

        //draws the passageNameObject (on top of this object)
        passageNameObject.draw(g);
    }

    /**
     * Opens the passage editing window for the passage which this object refers to
     */
    void openEditingWindow(){
        thePassage.openEditorWindow(theModel.getThePassageMap());
    }

    /**
     * Moves this object to the specified new position (and also updates the position of its associated passage object appropriately)
     * @param moveVector the vector2D representing the movement that needs to be done by this object
     */
    @Override
    public void move(Vector2D moveVector) {
        super.move(moveVector);
        thePassage.updatePosition(position);
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
