package oh_hecc.mvc;

import oh_hecc.game_parts.component_editing_windows.EditorWindowInterface;
import oh_hecc.game_parts.component_editing_windows.GenericEditorWindow;
import oh_hecc.game_parts.metadata.EditableMetadata;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.mvc.controller.ActionViewer;
import oh_hecc.mvc.controller.Controller;
import oh_hecc.mvc.controller.ControllerInterface;
import oh_hecc.mvc.model_bits.ModelButtonObject;
import oh_hecc.mvc.model_bits.PassageObject;
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.*;

/**
 * Okay so this is the Model of the actual network of passages and such
 */
public class PassageModel extends Model implements EditModelInterface {




    final Map<UUID, PassageEditingInterface> passageMap;
    final Map<UUID, PassageObject> visibleNetwork;

    final Set<ModelButtonObject> buttons;

    final MetadataEditingInterface theMetadata;

    final Vector2D topRightCorner;

    final Vector2D scrollMovementVector;

    /**
     * The current activity being performed by the model
     */
    private CurrentActivity activity;

    /**
     * The area currently being selected
     */
    private Area selectionArea;

    public PassageModel(ControllerInterface c, EditableMetadata metadata, Map<UUID, PassageEditingInterface> allPassages){
        super(c);

        theMetadata = metadata;
        passageMap = allPassages;

        visibleNetwork = new HashMap<>();

        for (PassageEditingInterface p: passageMap.values()) {
            p.updateLinkedUUIDs(allPassages);
            visibleNetwork.put(p.getPassageUUID(), new PassageObject(this,p));
        }

        buttons = new HashSet<>();

        //TODO: make the buttons

        topRightCorner = new Vector2D();

        scrollMovementVector = new Vector2D();

        activity = CurrentActivity.DOING_NOTHING;

        selectionArea = new Area();

    }


    public void update(){
        ActionViewer currentAction = theController.getAction();
        boolean inputRecieved = currentAction.checkForInput();
        switch (activity){
            case DOING_NOTHING:
                if (inputRecieved){
                    //TODO: wat do if input recieved?
                }
                break;
            case LC_DRAGGING_SELECTION_BOX:
                if (inputRecieved){
                    //TODO: wat do if input recieved?
                } else {
                    //TODO: wat do if no input recieved?
                }
                AffineTransform at = new AffineTransform();
                at.translate(topRightCorner.x, topRightCorner.y);
                selectionArea = new Area(currentAction.getLeftDragRect()).createTransformedArea(at);
                break;
            case LC_OBJECTS_SELECTED:
                //TODO: wat do?
                break;
            case LC_MOVING_OBJECTS:
                //TODO: wat do?
                break;
            case LDC_EDITING_PASSAGE:
                //TODO: wat do?
                break;
            case LC_PRESSED_BUTTON:
                //TODO: wat do?
                break;
            case RC_MOVING_VIEW:
                //TODO: wat do?
                break;
        }
    }


    public void drawModel(Graphics2D g){
        super.drawModel(g);

        //backup of the original lack of a transform
        AffineTransform unscrolled = g.getTransform();

        //translates everything in the negative direction to where the top-right corner currently is
        g.translate(-topRightCorner.x,-topRightCorner.y);

        //the objects representing links between passages are drawn first
        for (PassageObject p: visibleNetwork.values()) {
            p.drawLinks(g);
        }

        //then the passage objects themselves are drawn
        for (PassageObject p: visibleNetwork.values()){
            p.draw(g);
        }

        //now it goes back to where it was before it was scrolled
        g.setTransform(unscrolled);
    }



    @Override
    public PassageEditingInterface getPassageFromUUID(UUID uuidOfPassageToGet){
        return passageMap.get(uuidOfPassageToGet);
    }

    @Override
    public PassageObject getPassageObjectFromUUID(UUID uuidOfPassageObjectToGet){
        return visibleNetwork.get(uuidOfPassageObjectToGet);
    }

    @Override
    public Set<UUID> getUUIDsOfPassagesLinkedToParticularPassageFromUUID(UUID sourcePassageUUID){
        return passageMap.get(sourcePassageUUID).getLinkedPassageUUIDs();
    }

    @Override
    public Set<PassageEditingInterface> getPassagesFromSetOfUUIDs(Set<UUID> getThesePassages){
        Set<PassageEditingInterface> thePassages = new HashSet<>();
        if (!getThesePassages.isEmpty()){
            for(UUID u: getThesePassages){
                thePassages.add(passageMap.get(u));
            }
        }
        return thePassages;
    }

    @Override
    public Set<PassageEditingInterface> getPassageEditingInterfaceObjectsConnectedToGivenObject(UUID uuidOfSourceObject){
        Set<PassageEditingInterface> theLinkedPassages = new HashSet<>();
        Set<UUID> linkedUUIDs = passageMap.get(uuidOfSourceObject).getLinkedPassageUUIDs();
        if (!linkedUUIDs.isEmpty()){
            for (UUID u: linkedUUIDs) {
                theLinkedPassages.add(passageMap.get(u));
            }
        }
        return theLinkedPassages;
    }

    @Override
    public Map<UUID, PassageEditingInterface> getThePassageMap(){
        return passageMap;
    }




    /**
     * An enumeration to represent the current action being performed by the model
     */
    private enum CurrentActivity{
        /**
         * if nothing in particular is happening, waiting for user input
         */
        DOING_NOTHING,
        /**
         * if left click is held, dragging selection box to select multiple passages
         */
        LC_DRAGGING_SELECTION_BOX,
        /**
         * If objects are selected (either from single left clicking one object, or making a selection box that encloses several objects).
         * Can deselect them by left-click or right click
         */
        LC_OBJECTS_SELECTED,
        /**
         * If moving objects (left click drag whilst objects are selected -> move those objects)
         */
        LC_MOVING_OBJECTS,
        /**
         * Double-click a PassageObject: start editing that passage object.
         */
        LDC_EDITING_PASSAGE,
        /**
         * Click on one of the buttons at the bottom of the screen: doing the operation that button is responsible for
         */
        LC_PRESSED_BUTTON,
        /**
         * Right click drag: start dragging the view.
         */
        RC_MOVING_VIEW
    }


}
