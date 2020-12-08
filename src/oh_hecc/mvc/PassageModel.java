package oh_hecc.mvc;


import oh_hecc.game_parts.GameDataObject;
import oh_hecc.game_parts.component_editing_windows.EditorWindowInterface;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.EditablePassage;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.mvc.controller.ActionViewer;
import oh_hecc.mvc.model_bits.ModelButtonObject;
import oh_hecc.mvc.model_bits.PassageObject;
import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Okay so this is the Model of the actual network of passages and such
 */
public class PassageModel extends Model implements EditModelInterface, MouseControlModelInterface {

    /**
     * The actual GameDataObject
     */
    private final GameDataObject theData;

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

    final ModelButtonObject saveButton;

    final ModelButtonObject saveAndQuitButton;

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
     * Mouse position during this left-click drag frame
     */
    private final Vector2D currentLeftDragPos;



    /**
     * A blue colour for the selection area
     */
    private static Color SELECTION_AREA_COLOUR = new Color(47, 189, 203);

    /**
     * Constructs the PassageModel object
     * @param data the GameDataObject containing all the data for the game.
     */
    public PassageModel(GameDataObject data){
        super();

        theData = data;

        theMetadata = data.getTheMetadata();
        passageMap = data.getPassageMap();

        objectMap = new HashMap<>();

        for (PassageEditingInterface p: passageMap.values()) {
            p.updateLinkedUUIDs(passageMap);
            objectMap.put(p.getPassageUUID(), new PassageObject(this,p));
        }

        for(PassageObject p: objectMap.values()){
            //p.updateLinkedObjectPositions();
            p.update();
        }



        buttons = new HashSet<>();

        //TODO: make the buttons

        saveButton = new ModelButtonObject(this,0,0.25f,"Save"); //TODO: option to quit?

        saveAndQuitButton = new ModelButtonObject(this,0.25f,0.5f,"Save and quit");

        editMetadataObjectButton = new ModelButtonObject(this,0.5f,0.75f,"Edit metadata");

        addPassageButton = new ModelButtonObject(this,0.75f,1f,"Add passage");

        buttons.add(saveButton);
        buttons.add(saveAndQuitButton);
        buttons.add(editMetadataObjectButton);
        buttons.add(addPassageButton);


        currentLeftDragPos = new Vector2D();


        drawablePassageObjects = new HashSet<>();



        drawableModelButtons = new HashSet<>();

        topRightCorner = new Vector2D(-getPreferredSize().getWidth()/2.0, -getPreferredSize().getHeight()/2.0);

        mouseDragVector = new Vector2D();

        activity = CurrentActivity.DOING_NOTHING;

        selectionArea = new Area();

        selectedObjects = new HashSet<>();

        needToAddListenerToEditWindow = true;

        //dont mind me, just trying to avoid compiler problems.
        editWindow = closeEvent -> { };

        drawingTopRight = new Vector2D();

        makeSureStartPassageExists(null);
        refreshDrawables();
        repaint();

        //MouseController m = new MouseController(this);
        //this.addMouseListener(m);
        //this.addMouseMotionListener(m);

    }


    private void actuallyValidateStuff(){ actuallyValidateStuff(null);}

    private void actuallyValidateStuff(String previousStartPassage) {
        System.out.println("invalid");

        System.out.println("closed");
        makeSureStartPassageExists(previousStartPassage);


        /*
        for (PassageEditingInterface e: passageMap.values()) {
            System.out.println(e.getPassageName());
        }
        */
        //TODO: something to account for passages getting added to the passageMap

        Set<UUID> allPossibleUUIDSet = new HashSet<>();
        allPossibleUUIDSet.addAll(objectMap.keySet());
        allPossibleUUIDSet.addAll(passageMap.keySet());
        for (UUID u: allPossibleUUIDSet){
            if (!passageMap.containsKey(u)){
                System.out.println(u + " doesnt exist");
                objectMap.remove(u);
            } else if (!objectMap.containsKey(u)) {
                objectMap.put(u, new PassageObject(this,passageMap.get(u)));
            } else {
                passageMap.get(u).updateLinkedUUIDs(passageMap);
                objectMap.get(u).update();
            }
        }
        System.out.println("epic gamer moment");

        repaint();
        invalidate();

        /*
        repaint();
        try {
            Thread.sleep(100);
        } catch (Exception e){}

         */
        //TODO: call the repaint method of the frame instead I guess?
        //System.out.println("painted");
        //revalidate();
        //update(this.getGraphics());
    }


    /**
     * Call this when the model is left-clicked
     * @param mLocation location of mouse
     */
    @Override
    public void leftClick(Point mLocation){
        System.out.println("left click time");
        if (activity.equals(CurrentActivity.DIALOG_OPEN)){
            return;
        }

        activity = CurrentActivity.DOING_NOTHING;
        clearSelection();

        /*
        for (ModelButtonObject b: buttons) {
            if (b.wasClicked(mLocation)){
                //TODO: buttons doing a thing
                System.out.println(b.getPosition());
                return;
            }
        }*/
        if (editMetadataObjectButton.wasClicked(mLocation)){
            activity = CurrentActivity.DIALOG_OPEN;
            String lastStart = theMetadata.getStartPassage();
            //EditorWindowInterface w = theMetadata.openEditingWindow();
            EditorWindowInterface w = theData.openMetadataEditWindow();
            w.addWindowClosedListener(
                    e -> {
                        //System.out.println("previous start: " + lastStart);
                        actuallyValidateStuff(lastStart);
                        activity = CurrentActivity.DOING_NOTHING;
                        this.repaint();
                    }
            );
        } else if (addPassageButton.wasClicked(mLocation)){
            //TODO: add passage if this button was clicked
            System.out.println("passage button clicked");
            PassageEditingInterface newPassage = new EditablePassage(Vector2D.add(topRightCorner,getWidth()/2.0,getHeight()/2.0));
            passageMap.put(newPassage.getPassageUUID(),newPassage);
            actuallyValidateStuff();
        } else if (saveAndQuitButton.wasClicked(mLocation)){
            //TODO: save and quit button stuff if pressed
            try{
                theData.saveTheHecc();
                JOptionPane.showMessageDialog(
                        this,
                        ".hecc file saved successfully!",
                        "nice",
                        JOptionPane.INFORMATION_MESSAGE
                );
                System.exit(0);
            } catch (IOException e){
                JOptionPane.showMessageDialog(
                        this,
                        "Could not save the game!",
                        "uh oh",
                        JOptionPane.ERROR_MESSAGE
                );
                Thread t = new Thread( () -> {
                    JFrame f = new JFrame("The backup save method.");
                    f.setLayout(new BorderLayout());
                    f.add(new JLabel("Here's your .hecc code anyway so you don't lose it"), BorderLayout.NORTH);
                    JTextArea heccArea = new JTextArea(theData.toHecc());
                    heccArea.setLineWrap(true);
                    heccArea.setWrapStyleWord(true);
                    f.add(new JScrollPane(
                            heccArea,
                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
                    ));
                    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    f.pack();
                    f.revalidate();
                    f.setVisible(true);
                } );
                t.start();
                try {
                    t.join();
                } catch (InterruptedException ignored){}
            }
        } else if (saveButton.wasClicked(mLocation)){
            //TODO: save button stuff if pressed
            try{
                theData.saveTheHecc();
                JOptionPane.showMessageDialog(
                        this,
                        ".hecc file saved successfully!",
                        "nice",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch ( IOException e){
                JOptionPane.showMessageDialog(
                        this,
                        "Could not save the game!",
                        "uh oh",
                        JOptionPane.ERROR_MESSAGE
                );
                JFrame f = new JFrame("The backup save method.");
                f.setLayout(new BorderLayout());
                f.add(new JLabel("Here's your .hecc code anyway so you don't lose it"), BorderLayout.NORTH);
                JTextArea heccArea = new JTextArea(theData.toHecc());
                heccArea.setLineWrap(true);
                heccArea.setWrapStyleWord(true);
                f.add(new JScrollPane(
                        heccArea,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
                ));
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                f.pack();
                f.revalidate();
                f.setVisible(true);
            }
        } else {
            //move the mouse so it's scrolled by the screen scroll amount
            Point scrolledMouse = moveMouseByScroll(mLocation);
            //this will hold the clicked passageObject if it was clicked
            Optional<PassageObject> clicked =
                objectMap.values().stream().filter(
                        o -> o.wasClicked(scrolledMouse)
                ).findAny(); //basically tries to find the first one where the 'wasClicked' method evaluates to true

            if (clicked.isPresent()){
                //if something was clicked
                activity = CurrentActivity.DIALOG_OPEN;

                EditorWindowInterface w = theData.openPassageEditWindow(clicked.get().getTheUUID());
                //if it's the start passage
                /*
                if (theMetadata.getStartPassage().equals(p.getPassageName())){
                    //open the passage editor window with a reference to the metadata object
                    w = PassageEditingInterface.openEditorWindow(p,passageMap,theMetadata);
                } else {
                    //if this isn't the start passage, we don't give a damn about the metadata object
                    w = PassageEditingInterface.openEditorWindow(p,passageMap);
                }

                 */
                w.addWindowClosedListener(
                        e -> {
                            actuallyValidateStuff();
                            activity = CurrentActivity.DOING_NOTHING;
                        }
                );
                //TODO: other bits to do with opening the stuff
            }
            /*
            for (PassageObject o : drawablePassageObjects ) { //objectMap.values()
                if (o.wasClicked(scrolledMouse)) {
                    EditorWindowInterface w = o.openEditingWindow();
                    w.addWindowClosedListener(
                            e -> this.actuallyValidateStuff()
                    );
                    //TODO: other bits to do with opening the stuff
                    break;
                }
            }

             */
        }
        revalidate();
    }




    /**
     * Call this when there's a right-click
     *
     * @param mLocation location of mouse
     */
    @Override
    public void rightClick(Point mLocation) {
        moveMouseByScroll(mLocation);
        super.rightClick(mLocation);
    }

    /**
     * Call this when there's a left press
     * @param mLocation location of mouse
     */
    @Override
    public void leftPress(Point mLocation){
        moveMouseByScroll(mLocation);

        currentLeftDragPos.set(mLocation);
        switch (activity){
            case DOING_NOTHING:

                Optional<PassageObject> pressed =
                        objectMap.values().stream().filter(p -> p.wasClicked(mLocation)).findAny();
                if (pressed.isPresent()){
                    PassageObject p = pressed.get();
                    p.nowSelected();
                    selectedObjects.add(p);
                    activity = CurrentActivity.LC_MOVING_OBJECTS;
                    break;
                } else{
                    //TODO: START DRAGGING SELECTION AREA
                    activity = CurrentActivity.LC_DRAGGING_SELECTION_BOX;
                    break;
                }
                //break;
            case LC_OBJECTS_SELECTED:
                //TODO: any other things I need to do when left-clicking whilst done selecting the things?

                //If mouse down on a selected object
                if (selectedObjects.stream().anyMatch( p-> p.wasClicked(mLocation))){
                    //start moving them
                   activity = CurrentActivity.LC_MOVING_OBJECTS;
                } else {
                    //if mouse down anywhere else, they're now unselected.
                    clearSelection();
                    activity = CurrentActivity.DOING_NOTHING;
                }
                break;
        }

    }


    private void clearSelection(){
        //deselect selectedObjects
        selectedObjects.forEach(PassageObject::deselected);
        selectedObjects.clear(); //clear the list of selectedObjects
    }

    /**
     * Call this when there's a right press
     *
     * @param mLocation location of mouse
     */
    @Override
    public void rightPress(Point mLocation) {
        moveMouseByScroll(mLocation);
        super.rightPress(mLocation);
    }

    /**
     * Call this when left button is released
     *
     * @param mLocation location of mouse
     */
    @Override
    public void leftRelease(Point mLocation) {
        moveMouseByScroll(mLocation);
        super.leftRelease(mLocation);
        switch (activity){
            case LC_DRAGGING_SELECTION_BOX: //if was dragging selection box, finalize selection

                clearSelection();
                //adds all objects which intersect with selectionArea to selectObjects
                selectedObjects.addAll(
                    objectMap.values().stream().filter(
                            o -> o.checkIntersectWithArea(selectionArea)
                    ).collect(Collectors.toSet())
                );
                activity = CurrentActivity.LC_OBJECTS_SELECTED;

                //TODO: clear selection area
                break;

            case LC_MOVING_OBJECTS: //if was moving objects, stop moving them
                clearSelection();
                activity = CurrentActivity.DOING_NOTHING;
                break;
        }

    }

    /**
     * Call this when right button is released
     *
     * @param mLocation location of mouse
     */
    @Override
    public void rightRelease(Point mLocation) {
        moveMouseByScroll(mLocation);
        super.rightRelease(mLocation);
    }

    /**
     * Call this when dragging with left held
     *
     * @param mLocation location of mouse
     */
    @Override
    public void leftDrag(Point mLocation) {
        moveMouseByScroll(mLocation);
        super.leftDrag(mLocation);
        //lastLeftDrag is set to last frame's currentLeftDragPos.
        Vector2D lastLeftDrag = new Vector2D(currentLeftDragPos);
        //updates currentLeftDragPos to current mouse position
        currentLeftDragPos.set(mLocation);
        switch (activity){
            case LC_DRAGGING_SELECTION_BOX: //if dragging selection box
                //TODO: drag selection box
                break;
            case LC_MOVING_OBJECTS: //if moving objects
                Vector2D movement = Vector2D.subtract(currentLeftDragPos,lastLeftDrag);
                selectedObjects.forEach(
                        o -> o.move(movement)
                );

                break;

        }
    }

    /**
     * Call this when dragging with right held
     *
     * @param mLocation location of mouse
     */
    @Override
    public void rightDrag(Point mLocation) {
        moveMouseByScroll(mLocation);
        super.rightDrag(mLocation);
    }



    /**
     * Moves a Point (usually the mouse location) by the topRightCorner vector
     * @param m the mouse location Point
     * @return m but translated by topRightCorner
     */
    private Point moveMouseByScroll(Point m){
        m.translate((int)topRightCorner.x,(int)topRightCorner.y);
        return m;
    }



    //TODO: replace the parts of this with public methods callable by the Controller object (maybe via interface?), in response to mouse events.

    @Deprecated
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
            case DIALOG_OPEN:
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


    @Deprecated
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
                    //editWindow = getPassageObjectFromUUID(clickedThis).openEditingWindow();
                    activity = CurrentActivity.DIALOG_OPEN;
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
                        makeSureStartPassageExists(null);
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


    /**
     * Refreshes the collections of 'drawable' objects basically so  they can be drawn.
     */
    void refreshDrawables(){

        drawingTopRight.set(topRightCorner);

        drawablePassageObjects.clear();
        drawablePassageObjects.addAll(objectMap.values());
        //ScrollableModelObject.SET_SCROLL(drawingTopRight);
        /*
        for (PassageObject p: drawablePassageObjects) {
            p.scroll(topRightCorner);
        }

         */

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
        //AbstractObject.SCROLL_OFFSET.set(drawingTopRight);


        //g.translate(-ScrollableModelObject.SCROLL_VECTOR.x,-ScrollableModelObject.SCROLL_VECTOR.y);

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
        //AbstractObject.SCROLL_OFFSET.set(0,0);

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
     * This method can verify that the start passage of the game exists
     */
    private void makeSureStartPassageExists(String lastStartName){
        /*
        Optional<String> lastStart = Optional.ofNullable(lastStartName);
        String startName = theMetadata.getStartPassage();
        /*
        boolean startDoesntExist = true;
        for (PassageEditingInterface p: passageMap.values()) {
            if (p.getPassageName().equals(theMetadata.getStartPassage())){
                startDoesntExist = false;
                break;
            }
        }
        if (startDoesntExist){
         *//*
        //if none of the passages in the passageMap match the name of the start passage
        if (passageMap.values().stream().noneMatch(p -> p.getPassageName().equals(startName))){

            //but, if the previous name of the starting passage was given as an argument
            if (lastStart.isPresent()){
                //see if a passage with that name exists
                Optional<PassageEditingInterface> p = passageMap.values().stream().filter(
                        e -> e.getPassageName().equals(lastStart.get())
                ).findAny();
                //if it does
                if (p.isPresent()){
                    try {
                        //rename that passage to the name of the new start passage.
                        p.get().renameThisPassage(theMetadata.getStartPassage(), passageMap);
                        return;
                    } catch (Exception e){}
                }
            }

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
                PassageEditingInterface newStart = new EditablePassage(startName, new Vector2D(topRightCorner).add(MODEL_WIDTH / 2.0, 0));
                passageMap.put(newStart.getPassageUUID(), newStart);
                objectMap.put(newStart.getPassageUUID(), new PassageObject(this, newStart));
            }
        }
        */
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
        return passageMap.values().stream().filter(
                p -> getThesePassages.contains(p.getPassageUUID())
        ).collect(Collectors.toSet());
        /*
        Set<PassageEditingInterface> thePassages = new HashSet<>();
        if (!getThesePassages.isEmpty()){
            for(UUID u: getThesePassages){
                thePassages.add(passageMap.get(u));
            }
        }
        return thePassages;*/
    }

    /**
     * Obtains the PassageEditingInterface objects of all the passages which the given passage links to
     *
     * @param uuidOfSourceObject the UUID of the passage that we're trying to find the 'child' passages of
     * @return the UUIDs of all the 'child' passages
     */
    @Override
    public Set<PassageEditingInterface> getPassageEditingInterfaceObjectsConnectedToGivenObject(UUID uuidOfSourceObject){
        return theData.getPassageEditingInterfaceObjectsConnectedToGivenObject(uuidOfSourceObject);
        /*
        Set<PassageEditingInterface> theLinkedPassages = new HashSet<>();
        passageMap.get(uuidOfSourceObject).getLinkedPassageUUIDs().forEach(
                u -> theLinkedPassages.add(passageMap.get(u))
        );
        return theLinkedPassages;

         */
        /*
        Set<UUID> linkedUUIDs = passageMap.get(uuidOfSourceObject).getLinkedPassageUUIDs();
        if (!linkedUUIDs.isEmpty()){
            for (UUID u: linkedUUIDs) {
                theLinkedPassages.add(passageMap.get(u));
            }
        }
        return theLinkedPassages;

         */
    }

    @Override
    public Map<UUID, PassageEditingInterface> getThePassageMap(){
        return passageMap;
    }

    /**
     * Obtains the UUIDs of the passages that link to the destination passage
     *
     * @param destination the UUID of the passage that we're trying to find the 'parent' passages of
     * @return the UUIDs of all the 'parent' passage
     */
    @Override
    public Set<UUID> getThePassageObjectsWhichLinkToGivenPassageFromUUID(UUID destination) {
        return theData.getThePassageObjectsWhichLinkToGivenPassageFromUUID(destination);
        /*
        return passageMap.keySet().stream().filter(
                p -> passageMap.get(p).getLinkedPassageUUIDs().contains(destination)
        ).collect(Collectors.toSet());

         */
    }

    /**
     * Update the passageLinks of the passage objects that link to the given passage
     *
     * @param destination the UUID of the destination passage whose parents need to update their links.
     */
    @Override
    public void updatePassageObjectLinksWhichLinkToSpecifiedPassage(UUID destination) {
        getThePassageObjectsWhichLinkToGivenPassageFromUUID(destination).forEach(
                k -> objectMap.get(k).updatePositionOfSpecificLink(destination)
        );
    }

    @Override
    public void setSize(Dimension d){
        super.setSize(d);
        for (ModelButtonObject b: buttons) {
            b.resize(getWidth(),getHeight());
        }
        repaint();
    }

    public String getHecced(){
        //String hecced =
        return theData.toHecc();
        //System.out.println(hecced);
        //return hecced;
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
         * Dialog box open
         */
        DIALOG_OPEN,
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
