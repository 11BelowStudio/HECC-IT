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

    /**
     * The Metadata for the game that this model represents
     */
    final MetadataEditingInterface theMetadata;

    /**
     * The map of all the PassageEditingInterface objects
     */
    final Map<UUID, PassageEditingInterface> passageMap;

    /**
     * The map of all the PassageObject objects
     */
    final Map<UUID, PassageObject> objectMap;

    /**
     * The map of all the ModelButtonObject buttons
     */
    final Set<ModelButtonObject> buttons;

    final ModelButtonObject exitButton;

    final ModelButtonObject helpButton;

    final ModelButtonObject editMetadataObjectButton;

    final ModelButtonObject addPassageButton;

    /**
     * The set containing the copies of the PassageObjects that are actually rendered
     */
    final Set<PassageObject> drawablePassageObjects;

    /**
     * The set of the copies of the buttonObjects that are actually rendered
     */
    final Set<ModelButtonObject> drawableModelButtons;

    /**
     * A set of all currently-selected PassageObjects
     */
    final Set<PassageObject> selectedObjects;

    /**
     * A Vector2D that indicates where the top-right corner of the viewable area is
     */
    final Vector2D topRightCorner;

    private final Vector2D drawingTopRight;

    /**
     * A vector2D representing the current mouse drag action (from where the drag started to where the mouse is now)
     */
    final Vector2D mouseDragVector;

    /**
     * The current activity being performed by the model
     */
    private CurrentActivity activity;

    /**
     * The area currently being selected
     */
    private Area selectionArea;

    /**
     * Any currently-opened EditorWindowInterface object
     */
    private EditorWindowInterface editWindow;

    /**
     * does it need to add listener to editwindow
     */
    private boolean needToAddListenerToEditWindow;



    /**
     * A blue colour for the selection area
     */
    private static Color SELECTION_AREA_COLOUR = new Color(47, 189, 203);

    /**
     * Constructs the PassageModel object
     * @param metadata the metadata of the game being edited
     * @param allPassages all the passages of the game.
     */
    public PassageModel(MetadataEditingInterface metadata, Map<UUID, PassageEditingInterface> allPassages){
        super();

        theMetadata = metadata;
        passageMap = allPassages;

        objectMap = new HashMap<>();

        for (PassageEditingInterface p: passageMap.values()) {
            p.updateLinkedUUIDs(allPassages);
            objectMap.put(p.getPassageUUID(), new PassageObject(this,p));
        }

        for(PassageObject p: objectMap.values()){
            p.updateLinkedObjectPositions();
        }



        buttons = new HashSet<>();

        //TODO: make the buttons

        exitButton = new ModelButtonObject(this,0,0.25f,"Save and Quit");

        helpButton = new ModelButtonObject(this,0.25f,0.5f,"Help");

        editMetadataObjectButton = new ModelButtonObject(this,0.5f,0.75f,"Edit metadata");

        addPassageButton = new ModelButtonObject(this,0.75f,1f,"Add passage");

        buttons.add(exitButton);
        buttons.add(helpButton);
        buttons.add(editMetadataObjectButton);
        buttons.add(addPassageButton);




        drawablePassageObjects = new HashSet<>();



        drawableModelButtons = new HashSet<>();

        topRightCorner = new Vector2D();

        mouseDragVector = new Vector2D();

        activity = CurrentActivity.DOING_NOTHING;

        selectionArea = new Area();

        selectedObjects = new HashSet<>();

        needToAddListenerToEditWindow = true;

        //dont mind me, just trying to avoid compiler problems.
        editWindow = closeEvent -> { };

        drawingTopRight = new Vector2D();

        makeSureStartPassageExists();

    }



    //TODO: replace the parts of this with public methods callable by the Controller object (maybe via interface?), in response to mouse events.

    void updateModel(){
        //ActionViewer currentAction = theController.getAction();
        //boolean inputRecieved = currentAction.checkForInput();

        switch (activity){
            case DOING_NOTHING:
                //doingNothingActivity(currentAction);
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
                //draggingSelectionBoxActivity(currentAction);
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
                //objectsSelectedActivity(currentAction);
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
                //movingObjectsActivity(currentAction);
                break;
            case LDC_EDITING_PASSAGE:
                //editingPassageActivity(currentAction);
                //TODO: wat do?
                break;
            case LC_PRESSED_BUTTON:
                //pressedButtonActivity(currentAction);
                //TODO: wat do?
                break;
            case RC_MOVING_VIEW:
                //movingViewActivity(currentAction);
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


    /**
     * Attempt to draw the model
     * @param g the graphics2D being used
     */
    public void drawModel(Graphics2D g){

        Shape existingClip = g.getClip();

        //backup of the original lack of a transform
        AffineTransform unscrolled = g.getTransform();

        //translates everything in the negative direction to where the top-right corner currently is
        g.translate(-drawingTopRight.x,-drawingTopRight.y);

        g.setClip((int)drawingTopRight.x,(int)drawingTopRight.y, getWidth(),getHeight());

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

        g.setClip(existingClip);

        g.setClip(0,0,getWidth(),getHeight());
        for (ModelButtonObject b: drawableModelButtons){
            b.draw(g);
        }
    }

    /**
     * Move the viewable area in the X dimension
     * @param positive if true, move it +100, if false, move it -100.
     */
    @Override
    public void xMove(boolean positive) {
        topRightCorner.x += (positive? 100 : -100);
        System.out.println("top-right: " + topRightCorner);
        repaint();
    }

    /**
     * Move the viewable area in the Y dimension
     * @param positive if true, move it +100, if false, move it -100.
     */
    @Override
    public void yMove(boolean positive) {
        topRightCorner.y += (positive? 100: -100);
        System.out.println("top-right: " + topRightCorner);
        repaint();
    }

    /**
     * This method can verify that the start passage of the
     */
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

    @Override
    public void setSize(Dimension d){
        super.setSize(d);
        for (ModelButtonObject b: buttons) {
            b.resize(getWidth(),getHeight());
        }
        repaint();
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
