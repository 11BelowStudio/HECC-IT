package oh_hecc.mvc;

import oh_hecc.game_parts.component_editing_windows.EditorWindowInterface;
import oh_hecc.game_parts.metadata.EditableMetadata;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.EditablePassage;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.mvc.controller.ActionViewer;
import oh_hecc.mvc.controller.ControllerInterface;
import oh_hecc.mvc.model_bits.ModelButtonObject;
import oh_hecc.mvc.model_bits.PassageObject;
import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.*;

/**
 * Okay so this is the Model of the actual network of passages and such
 */
public class PassageModel extends Model implements EditModelInterface {




    final Map<UUID, PassageEditingInterface> passageMap;
    final Map<UUID, PassageObject> objectMap;

    final Set<PassageObject> drawablePassageObjects;

    final Set<ModelButtonObject> drawableModelButtons;

    final Set<ModelButtonObject> buttons;

    final Set<PassageObject> selectedObjects;

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

    private EditorWindowInterface editWindow;

    private boolean needToAddListenerToEditWindow;

    private final Vector2D drawingTopRight;

    /**
     * A blue colour for the selection area
     */
    private static Color SELECTION_AREA_COLOUR = new Color(47, 189, 203);

    public PassageModel(ControllerInterface c, EditableMetadata metadata, Map<UUID, PassageEditingInterface> allPassages){
        super(c);

        theMetadata = metadata;
        passageMap = allPassages;

        objectMap = new HashMap<>();

        for (PassageEditingInterface p: passageMap.values()) {
            p.updateLinkedUUIDs(allPassages);
            objectMap.put(p.getPassageUUID(), new PassageObject(this,p));
        }


        buttons = new HashSet<>();

        //TODO: make the buttons

        drawablePassageObjects = new HashSet<>();

        drawableModelButtons = new HashSet<>();

        topRightCorner = new Vector2D();

        scrollMovementVector = new Vector2D();

        activity = CurrentActivity.DOING_NOTHING;

        selectionArea = new Area();

        selectedObjects = new HashSet<>();

        needToAddListenerToEditWindow = true;

        //dont mind me, just trying to avoid compiler problems.
        editWindow = closeEvent -> { };

        drawingTopRight = new Vector2D();

    }


    void updateModel(){
        ActionViewer currentAction = theController.getAction();
        //boolean inputRecieved = currentAction.checkForInput();

        switch (activity){
            case DOING_NOTHING:
                doingNothingActivity(currentAction);
                /*
                if (inputRecieved){
                    //TODO: wat do if input recieved?
                    if (currentAction.checkLeftDoubleClick()){
                        Point location = currentAction.getLeftDoubleClickLocation();
                        UUID clickedThis;
                        boolean clicked = false;
                        for (PassageObject p: objectMap.values()) {
                            if (p.wasClicked(location)){
                                clicked = true;
                                clickedThis = p.getTheUUID();
                                break;
                            }
                        }
                        if (clicked){
                            getPassageObjectFromUUID(clickedThis).openEditingWindow()
                        }
                    }
                }
                 */
                break;
            case LC_DRAGGING_SELECTION_BOX:
                draggingSelectionBoxActivity(currentAction);
                /*
                if(inputRecieved){

                    AffineTransform at = new AffineTransform();
                    at.translate(topRightCorner.x, topRightCorner.y);
                    selectionArea = new Area(currentAction.getLeftDragRect()).createTransformedArea(at);

                    if(currentAction.checkLeftHold()){
                        //nothing really has to be done in this case I guess
                    } else if (currentAction.isLeftDragFinished()){ //if done drawing the selection rectangle
                        //clear set of selected objects
                        selectedObjects.clear();

                        for(PassageObject o: objectMap.values()){
                            //check for passage objects that intersect with the selection area
                            if (o.checkIntersectWithArea(selectionArea)){
                                o.nowSelected();
                                selectedObjects.add(o);
                            }
                        }
                        selectionArea.reset(); //clear the selection area
                        if (selectedObjects.isEmpty()){
                            //if nothing's selected, it's time to go back to doing nothing
                            activity = CurrentActivity.DOING_NOTHING;
                        } else{
                            activity = CurrentActivity.LC_OBJECTS_SELECTED;
                        }
                        break;
                    }
                } else {
                    selectionArea.reset();
                    activity = CurrentActivity.DOING_NOTHING;
                }

                 */
                break;
            case LC_OBJECTS_SELECTED:
                objectsSelectedActivity(currentAction);
                //TODO: wat do?
                /*
                if(inputRecieved){
                    if (currentAction.checkLeftHold()){
                        activity = CurrentActivity.LC_MOVING_OBJECTS;
                    } else if (currentAction.checkRightHold()){
                        activity = CurrentActivity.RC_MOVING_VIEW;
                    } else if (currentAction.checkLeftClick()){
                        activity = CurrentActivity.DOING_NOTHING;
                    }
                }

                 */
                break;
            case LC_MOVING_OBJECTS:
                //TODO: wat do?
                movingObjectsActivity(currentAction);
                break;
            case LDC_EDITING_PASSAGE:
                editingPassageActivity(currentAction);
                //TODO: wat do?
                break;
            case LC_PRESSED_BUTTON:
                pressedButtonActivity(currentAction);
                //TODO: wat do?
                break;
            case RC_MOVING_VIEW:
                movingViewActivity(currentAction);
                //TODO: wat do?
                break;
        }
    }


    private void doingNothingActivity(ActionViewer currentAction){
        //TODO: finish off for other inputs
        if (currentAction.checkForInput()){
            //TODO: wat do if input recieved?
            if (currentAction.checkLeftDoubleClick()){
                Point location = currentAction.getLeftDoubleClickLocation();
                UUID clickedThis = null;
                boolean clicked = false;
                for (PassageObject p: objectMap.values()) {
                    if (p.wasClicked(location)){
                        clicked = true;
                        clickedThis = p.getTheUUID();
                        break;
                    }
                }
                if (clicked){
                    needToAddListenerToEditWindow = true;
                    editWindow = getPassageObjectFromUUID(clickedThis).openEditingWindow();
                    activity = CurrentActivity.LDC_EDITING_PASSAGE;
                    editingPassageActivity(currentAction);
                }
            }
        }
    }

    private void draggingSelectionBoxActivity(ActionViewer currentAction){
        if(currentAction.checkForInput()){

            AffineTransform at = new AffineTransform();
            at.translate(topRightCorner.x, topRightCorner.y);
            selectionArea = new Area(currentAction.getLeftDragRect()).createTransformedArea(at);

            if(currentAction.checkLeftHold()){
                //nothing really has to be done in this case I guess
            } else if (currentAction.isLeftDragFinished()){ //if done drawing the selection rectangle
                //clear set of selected objects
                selectedObjects.clear();

                for(PassageObject o: objectMap.values()){
                    //check for passage objects that intersect with the selection area
                    if (o.checkIntersectWithArea(selectionArea)){
                        o.nowSelected();
                        selectedObjects.add(o);
                    }
                }
                selectionArea.reset(); //clear the selection area
                if (selectedObjects.isEmpty()){
                    //if nothing's selected, it's time to go back to doing nothing
                    activity = CurrentActivity.DOING_NOTHING;
                } else{
                    activity = CurrentActivity.LC_OBJECTS_SELECTED;
                }

            }
        } else {
            selectionArea.reset();
            activity = CurrentActivity.DOING_NOTHING;
        }
    }


    private void objectsSelectedActivity(ActionViewer currentAction){
        if(currentAction.checkForInput()){
            if (currentAction.checkLeftHold()){
                activity = CurrentActivity.LC_MOVING_OBJECTS;
            } else if (currentAction.checkRightHold()){
                deselectObjects();
                activity = CurrentActivity.RC_MOVING_VIEW;
            } else if (currentAction.checkLeftClick()){
                deselectObjects();
                activity = CurrentActivity.DOING_NOTHING;
            }
        }
    }

    private void deselectObjects(){
        for (PassageObject o: selectedObjects) {
            o.deselected();
        }
        selectedObjects.clear();
    }

    private void movingObjectsActivity(ActionViewer currentAction){
        if (currentAction.checkForInput()){
            if (currentAction.checkLeftHold()){
                for (PassageObject o: selectedObjects){
                    o.move(currentAction.getCurrentLeftDrag());
                }
                for (PassageObject o: objectMap.values()) {
                    o.updateLinkedObjectPositions();
                }
            } else if (currentAction.isLeftDragFinished()){
                for (PassageObject o: selectedObjects){
                    o.move(currentAction.getCurrentLeftDrag());
                }
                for (PassageObject o: objectMap.values()) {
                    o.updateLinkedObjectPositions();
                }
                deselectObjects();
                activity = CurrentActivity.DOING_NOTHING;
            }
        } else {
            deselectObjects();
            activity = CurrentActivity.DOING_NOTHING;
        }
    }

    //TODO: finish this
    private void editingPassageActivity(ActionViewer currentAction){
        if (needToAddListenerToEditWindow){
            editWindow.addWindowClosedListener(
                    windowEvent -> {
                        makeSureStartPassageExists();
                        activity = CurrentActivity.DOING_NOTHING;
                    }
            );
        }
    }

    //TODO this
    private void pressedButtonActivity(ActionViewer currentAction){

    }

    //TODO this (move TopRightCorner)
    private void movingViewActivity(ActionViewer currentAction){

    }


    void refreshDrawables(){

        drawingTopRight.set(topRightCorner);

        drawablePassageObjects.clear();
        drawablePassageObjects.addAll(objectMap.values());

        drawableModelButtons.clear();
        drawableModelButtons.addAll(buttons);
    }


    public void drawModel(Graphics2D g){

        Shape existingClip = g.getClip();

        //backup of the original lack of a transform
        AffineTransform unscrolled = g.getTransform();

        //translates everything in the negative direction to where the top-right corner currently is
        g.translate(-drawingTopRight.x,-drawingTopRight.y);

        g.setClip((int)drawingTopRight.x,(int)drawingTopRight.y, MODEL_WIDTH,MODEL_HEIGHT);

        g.setColor(SELECTION_AREA_COLOUR);
        g.fill(selectionArea);

        //the objects representing links between passages are drawn first
        for (PassageObject p: drawablePassageObjects) {
            p.drawLinks(g);
        }

        //then the passage objects themselves are drawn
        for (PassageObject p: drawablePassageObjects){
            p.draw(g);
        }

        //now it goes back to where it was before it was scrolled
        g.setTransform(unscrolled);

        g.setClip(0,0,MODEL_WIDTH,MODEL_HEIGHT);
        for (ModelButtonObject b: drawableModelButtons){
            b.draw(g);
        }
    }





    private void makeSureStartPassageExists(){
        boolean startDoesntExist = true;
        String startName = theMetadata.getStartPassage();
        for (PassageEditingInterface p: passageMap.values()) {
            if (p.getPassageName().equals(theMetadata.getStartPassage())){
                startDoesntExist = false;
                break;
            }
        }
        if (startDoesntExist){
            //if start doesn't exist, give user the option of adding the start in automatically
            if (JOptionPane.showConfirmDialog(
                            null,
                            "<html><p>No passage called<br>" +
                                    startName +
                                    "<br>exists in your game, however,<br>"+
                                    "your metadata indicates that a passage with<br>"+
                                    "that name should be the start passage.<br><br>"+
                                    "Do you want a new passage with that name to be generated?</html>",
                            "Your start passage went AWOL.",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    ) == JOptionPane.YES_OPTION) {
                PassageEditingInterface newStart = new EditablePassage(theMetadata.getStartPassage(), new Vector2D(topRightCorner).add(MODEL_WIDTH / 2.0, 0));
                passageMap.put(newStart.getPassageUUID(), newStart);
                objectMap.put(newStart.getPassageUUID(), new PassageObject(this, newStart));
            }
        }
    }

    @Override
    public PassageEditingInterface getPassageFromUUID(UUID uuidOfPassageToGet){
        return passageMap.get(uuidOfPassageToGet);
    }

    @Override
    public PassageObject getPassageObjectFromUUID(UUID uuidOfPassageObjectToGet){
        return objectMap.get(uuidOfPassageObjectToGet);
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
