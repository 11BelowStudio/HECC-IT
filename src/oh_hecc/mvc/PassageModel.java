package oh_hecc.mvc;


import heccCeptions.HeccCeption;
import hecc_up.HeccUpGUI;
import oh_hecc.game_parts.MVCGameDataInterface;
import oh_hecc.game_parts.component_editing_windows.EditorWindowInterface;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.EditablePassage;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.mvc.model_bits.*;
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
public class PassageModel extends Model implements EditModelInterface, ControllableModelInterface {

    /**
     * The actual GameDataObject
     */
    private final MVCGameDataInterface theData;

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
     * The set of all the ModelButtonObject buttons
     */
    private final Set<ModelButtonObject> buttons;

    /**
     * The button that, when pressed, should save the .hecc file
     */
    private final ModelButtonObject saveButton;

    /**
     * The button that, when pressed, should save the .hecc file and launch HECC-UP
     * (if there aren't any problems with the .hecc file ofc)
     */
    private final ModelButtonObject heccUpButton;

    /**
     * The button that, when pressed, should open the metadata editor window
     */
    private final ModelButtonObject editMetadataObjectButton;

    /**
     * The button that, when pressed, should add a new passage to the map
     */
    private final ModelButtonObject addPassageButton;

    /**
     * A StartHighlightObject. Will be positioned behind the starting passage object at all times.
     */
    private final StartHighlightObject startHighlight;

    /**
     * The set containing the copies of the PassageObjects that are actually rendered
     */
    final Set<DrawablePassageObject> drawablePassageObjects;

    /**
     * The set of the copies of the buttonObjects that are actually rendered
     */
    final Set<DrawableObjectWithText> drawableModelButtons;

    /**
     * A set of all currently-selected SelectableObjects
     */
    final Set<SelectableObject> selectedObjects;



    /**
     * A copy of topLeftCorner used for the draw operation
     */
    private final Vector2D drawingTopLeft;


    /**
     * The current activity being performed by the model
     */
    private CurrentActivity activity;

    /**
     * The area currently being selected
     */
    private Area selectionArea;

    /**
     * Mouse position during this left-click drag frame
     */
    private final Vector2D currentLeftDragPos;

    /**
     * Mouse position during this right-click drag frame
     */
    private final Vector2D currentRightDragPos;



    /**
     * A blue colour for the selection area
     */
    private static final Color SELECTION_AREA_COLOUR = new Color(47, 189, 203);

    /**
     * Constructs the PassageModel object
     * @param data the GameDataObject containing all the data for the game.
     */
    public PassageModel(MVCGameDataInterface data){
        super();

        theData = data;

        theMetadata = data.getTheMetadata();
        passageMap = data.getPassageMap();

        objectMap = new HashMap<>();

        for (PassageEditingInterface p: passageMap.values()) {
            p.updateLinkedUUIDs(passageMap);
            objectMap.put(p.getPassageUUID(), new PassageObject(this,p));
        }

        for(UpdatableObject p: objectMap.values()){
            p.update();
        }



        startHighlight = new StartHighlightObject();

        buttons = new HashSet<>();


        saveButton = new ModelButtonObject(0, 0.25f, "Save");

        heccUpButton = new ModelButtonObject( 0.25f, 0.5f, "Export with HECC-UP");

        editMetadataObjectButton = new ModelButtonObject(0.5f,0.75f,"Edit metadata");

        addPassageButton = new ModelButtonObject(0.75f,1f,"Add passage");

        buttons.add(saveButton);
        buttons.add(heccUpButton);
        buttons.add(editMetadataObjectButton);
        buttons.add(addPassageButton);


        currentLeftDragPos = new Vector2D();

        currentRightDragPos = new Vector2D();


        drawablePassageObjects = new HashSet<>();



        drawableModelButtons = new HashSet<>();

        //topRightCorner = new Vector2D(-getPreferredSize().getWidth()/2.0, -getPreferredSize().getHeight()/2.0);



        activity = CurrentActivity.DOING_NOTHING;

        selectionArea = new Area();

        selectedObjects = new HashSet<>();

        drawingTopLeft = new Vector2D();

        refreshDrawables();
        revalidate();
        repaint();

    }


    /**
     * Basically the update loop.
     * Updates the objectMap so it's consistent with the passageMap.
     * Updates the startHighlight if necessary.
     * And finally calls the super.revalidate() method (so that can be handled)
     */
    @Override
    public void revalidate() {
        Set<UUID> allPossibleUUIDSet = new HashSet<>();
        allPossibleUUIDSet.addAll(objectMap.keySet());
        allPossibleUUIDSet.addAll(passageMap.keySet());
        for (UUID u: allPossibleUUIDSet){
            if (!passageMap.containsKey(u)){
                // we yeet any passageObjects for objects that don't exist any more
                objectMap.remove(u);
            } else if (!objectMap.containsKey(u)) {
                // we add a PassageObject if this PassageEditingInterface has just been created
                objectMap.put(u, new PassageObject(this,passageMap.get(u)));
            } else {
                // update the linked UUIDs for the existing passage
                passageMap.get(u).updateLinkedUUIDs(passageMap);
                // and we update the object itself.
                objectMap.get(u).update();
            }
        }

        //move the start highlight to be highlighting the start passage
        Optional<UUID> startUUID = theData.getStartUUID();
        if (startUUID.isPresent()) {
            startHighlight.setStartObject(objectMap.get(startUUID.get()));
        } else {
            startHighlight.hide();
            // if there is no start passage, we hide it.
        }


        //System.out.println("and imma invalidate myself");

        super.revalidate(); //and so, we properly revalidate this.
    }


    /**
     * Call this when the model is left-clicked
     * @param mLocation location of mouse
     */
    @Override
    public void leftClick(Point mLocation){
        //System.out.println("left click time");

        // won't do anything if an editing dialog is open or if the user's moving the view
        switch (activity){
            case DIALOG_OPEN:
            case RC_MOVING_VIEW:
                return;
        }



        activity = CurrentActivity.DOING_NOTHING; // we are doing nothing now
        clearSelection(); // clears the selected objects.

        // now sees if anything was clicked

        if (editMetadataObjectButton.wasClicked(mLocation)) { // if edit metadata button was clicked, we edit metadata.
            startEditingTheMetadata(); // we attempt to start editing the metadata.

        } else if (addPassageButton.wasClicked(mLocation)) { // if add button was clicked, we add the passage.
            addNewPassage(); // we attempt to add a new passage
        } else if (heccUpButton.wasClicked(mLocation)) { // if hecc up button was clicked
            launchHeccUp(); // we attempt to launch hecc-up
        } else if (saveButton.wasClicked(mLocation)) { // if the save button was clicked
            saveTheHecc(); // we save the hecc.
        } else {

            Point scrolledMouse = moveMouseByScroll(mLocation); // factoring any offset from moving the viewport

            //this will hold the clicked passageObject if it was clicked
            Optional<PassageObject> clicked =
                objectMap.values().stream().filter(
                        o -> o.wasClicked(scrolledMouse)
                ).findAny(); //basically tries to find the first one where the 'wasClicked' method evaluates to true

            if (clicked.isPresent()){
                //if a passage object was clicked, we start editing it.
                activity = CurrentActivity.DIALOG_OPEN;

                EditorWindowInterface w = theData.openPassageEditWindow(clicked.get().getTheUUID());

                w.addWindowClosedListener(this::editingWindowClosed);
            }
        }
        revalidate();
    }

    /**
     * Opens the metadata editor window.
     */
    private void startEditingTheMetadata(){
        if (activity != CurrentActivity.DIALOG_OPEN) { // we don't do this if there's already a dialog open.
            activity = CurrentActivity.DIALOG_OPEN;
            EditorWindowInterface w = theData.openMetadataEditWindow();
            w.addWindowClosedListener(this::editingWindowClosed);
        }
    }

    /**
     * Adds a new passage to the map.
     */
    private void addNewPassage(){
        if (activity != CurrentActivity.DIALOG_OPEN) {
            PassageEditingInterface newPassage = new EditablePassage(Vector2D.add(topLeftCorner, getWidth() / 2.0, getHeight() / 2.0));
            passageMap.put(newPassage.getPassageUUID(), newPassage);
            revalidate();
        }
    }

    /**
     * Attempts to open HECC-UP
     */
    private void launchHeccUp(){
        if (activity != CurrentActivity.DIALOG_OPEN){
            if (saveTheHecc()) { // we attempt to save the .hecc file
                // and then we open HECC-UP if successful.
                activity = CurrentActivity.DIALOG_OPEN;
                new HeccUpGUI(theData.getSavePath(), this::editingWindowClosed);

            }
        }
    }


    /**
     * This method attempts to save the .hecc file.
     * If the .hecc file could be saved successfully, it lets the user know that it was saved successfully.
     * If the .hecc file couldn't be saved successfully, it'll present the 'Emergency Save Method' dialog to the user,
     * allowing them to basically copy and paste the contents of their .hecc file and save it manually.
     *
     * @return true if it could be saved. false if it failed. will not happen if an editing dialog is open.
     */
    private boolean saveTheHecc() {
        if (activity != CurrentActivity.DIALOG_OPEN) {
            try {
                //we attempt to save the hecc.
                theData.saveTheHeccCheckingValidity();
                JOptionPane.showMessageDialog(
                        this,
                        ".hecc file saved successfully!",
                        "that's pretty heccin' nice",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return true;
            } catch (HeccCeption h) {
                // if the hecc wasn't exactly valid, we still save it, but we let the author know what the problem was.
                JOptionPane.showMessageDialog(
                        this,
                        "Your work has been saved, but there's a minor problem you need to fix before you can export it:\n" + h.getErrorMessage(),
                        "pls fix this",
                        JOptionPane.WARNING_MESSAGE
                );

            } catch (IOException ioe) {
                // and if it couldn't be saved at all, we panic.

                JOptionPane.showMessageDialog(
                        this,
                        "Could not save the .hecc file!",
                        "Something hecced up.",
                        JOptionPane.ERROR_MESSAGE
                );
                JFrame f = new JFrame("The emergency save method.");
                f.setLayout(new BorderLayout());
                JPanel topPanel = new JPanel();
                topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
                topPanel.add(new JLabel("We couldn't save your .hecc file."));
                topPanel.add(new JLabel("However, here's your .hecc code, for you to copy and paste into a new .hecc file"));
                topPanel.add(new JLabel("We apologize for any inconvenience caused."));
                f.add(topPanel, BorderLayout.NORTH);
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
            }
        }
        return false;
    }

    /**
     * This method will be called whenever an editing window is closed.
     */
    void editingWindowClosed() {
        this.requestFocusInWindow();
        revalidate();
        activity = CurrentActivity.DOING_NOTHING;
        repaint();
    }


    /**
     * Deselects and clears the set of selectedObjects
     */
    private void clearSelection(){
        //deselects all the selectedObjects
        selectedObjects.forEach(SelectableObject::deselected);
        selectedObjects.clear(); //clears the set of selectedObjects
        activity = CurrentActivity.DOING_NOTHING; // and we're doing nothing.
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
                if (pressed.isPresent()) {
                    SelectableObject p = pressed.get();
                    p.nowSelected();
                    selectedObjects.add(p);
                    activity = CurrentActivity.LC_MOVING_OBJECTS;

                } else {
                    //TODO: START DRAGGING SELECTION AREA
                    activity = CurrentActivity.LC_DRAGGING_SELECTION_BOX;

                }
                break;
            case LC_OBJECTS_SELECTED:
                //TODO: any other things I need to do when left-clicking whilst done selecting the things?

                //If mouse down on a selected object
                if (selectedObjects.stream().anyMatch(p -> p.wasClicked(mLocation))) {
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





    /**
     * Call this when left button is released
     *
     * @param mLocation location of mouse
     */
    @Override
    public void leftRelease(Point mLocation) {
        moveMouseByScroll(mLocation);
        switch (activity) {
            case LC_DRAGGING_SELECTION_BOX:
                //if we were dragging selection box, finalize selection

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

            case LC_MOVING_OBJECTS:
                //if we were moving objects, stop moving them
                clearSelection();
                activity = CurrentActivity.DOING_NOTHING;
                break;

        }

    }




    /**
     * Call this when dragging with left held
     *
     * @param mLocation location of mouse
     */
    @Override
    public void leftDrag(Point mLocation) {
        moveMouseByScroll(mLocation);
        Vector2D lastLeftDrag = new Vector2D(currentLeftDragPos);
        currentLeftDragPos.set(mLocation);
        switch (activity) {
            case LC_DRAGGING_SELECTION_BOX:
                //TODO: drag selection box
                break;

            case LC_MOVING_OBJECTS:
                Vector2D movement = Vector2D.subtract(currentLeftDragPos, lastLeftDrag);
                selectedObjects.forEach(
                        o -> o.move(movement)
                );
                break;
        }
    }

    /**
     * Call this when there's a right-click. Does nothing.
     *
     * @param mLocation location of mouse
     */
    @Override
    public void rightClick(Point mLocation) { }

    /**
     * Call this when there's a right press.
     *
     * Sets currentRightDragPos to the current mouse location (not correcting for scroll)
     *
     * @param mLocation location of mouse
     */
    @Override
    public void rightPress(Point mLocation) {
        //moveMouseByScroll(mLocation);
        currentRightDragPos.set(mLocation);
        //moveMouseByScroll(mLocation);


        switch (activity){
            case DOING_NOTHING:
                // if we're doing nothing, we're now moving the view.
                activity = CurrentActivity.RC_MOVING_VIEW;
                break;
        }

    }

    /**
     * Call this when right button is released.
     * If we're currently moving the view, we stop doing that.
     *
     * @param mLocation location of mouse
     */
    @Override
    public void rightRelease(Point mLocation) {

        moveMouseByScroll(mLocation);
        switch (activity){
            case RC_MOVING_VIEW:
                activity = CurrentActivity.DOING_NOTHING;
                break;
        }

    }

    /**
     * Call this when dragging with right mouse held.
     *
     * We make a copy of the last currentRightDragPos (set in last frame). We then overwrite it with the current
     * mouse location (not correcting for scroll).
     *
     * If we're moving the view, we obtain the difference between last frame's drag position and
     * this frame's drag position. We subtract this difference from the topLeftCorner. Then, we revalidate it
     * (we'll be repainting it anyway immediately afterwards); this then scrolls the view. It scrolls it such that
     * the mouse click location is in the same relative position of the model, appearing to the user as if they're
     * 'dragging' it with their mouse.
     *
     * @param mLocation location of mouse
     */
    @Override
    public void rightDrag(Point mLocation) {
        Vector2D lastRightDrag = new Vector2D(currentRightDragPos);
        //moveMouseByScroll(mLocation);
        currentRightDragPos.set(mLocation);

        switch (activity){
            case RC_MOVING_VIEW:
                Vector2D movement = Vector2D.subtract(currentRightDragPos, lastRightDrag);
                topLeftCorner.subtract(movement);

                revalidate();
                break;
        }
        //moveMouseByScroll(mLocation);
    }



    /**
     * Moves a Point (usually the mouse location) by the topLeftCorner vector
     * @param m the mouse location Point
     * @return m but translated by topLeftCorner
     */
    private Point moveMouseByScroll(Point m){
        m.translate((int) topLeftCorner.x,(int) topLeftCorner.y);
        return m;
    }


    /**
     * Refreshes the collections of 'drawable' objects basically so they can be drawn.
     * Basically cloning the topRightCorner, objectMap, and buttons set, so, if those are modified mid-draw operation,
     * it won't cause anything supernatural/untoward to happen.
     */
    void refreshDrawables(){



        drawingTopLeft.set(topLeftCorner);

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
        g.translate(-drawingTopLeft.x,-drawingTopLeft.y);

        g.setClip((int) drawingTopLeft.x,(int) drawingTopLeft.y, getWidth(),getHeight());
        //AbstractObject.SCROLL_OFFSET.set(drawingTopRight);


        //g.translate(-ScrollableModelObject.SCROLL_VECTOR.x,-ScrollableModelObject.SCROLL_VECTOR.y);

        g.setColor(SELECTION_AREA_COLOUR);
        g.fill(selectionArea);

        //draw the start highlight
        startHighlight.update();
        startHighlight.draw(g);

        //the objects representing links between passages are drawn first, so they're underneath everything else.
        for (DrawablePassageObject p: drawablePassageObjects) {
            p.drawLinks(g);
        }

        //then the passage objects themselves are drawn
        for (DrawableObject p: drawablePassageObjects){
            p.draw(g);
        }

        //and then the names of the passage objects are drawn, on top of all the passages.
        for (DrawableObjectWithText p: drawablePassageObjects){
            p.drawText(g);
        }

        //now it goes back to where it was before it was scrolled
        g.setTransform(unscrolled);

        g.setClip(existingClip);
        //AbstractObject.SCROLL_OFFSET.set(0,0);

        g.setClip(0, 0, getWidth(), getHeight());
        for (DrawableObject b : drawableModelButtons) {
            b.draw(g);
        }

        for (DrawableObjectWithText b : drawableModelButtons) {
            b.drawText(g);
        }
    }


    /**
     * Move the viewable area in the X dimension (if currently doing nothing)
     *
     * @param xDist move it by this amount.
     */
    private void xMove(float xDist) {
        if(activity.equals(CurrentActivity.DOING_NOTHING)) {
            topLeftCorner.x += xDist;
            revalidate();
            repaint();
        }
    }

    /**
     * Move the viewable area in the Y dimension (if currently doing nothing)
     *
     * @param yDist move it by this amount.
     */
    private void yMove(float yDist) {
        if(activity.equals(CurrentActivity.DOING_NOTHING)) {
            topLeftCorner.y += yDist;
            revalidate();
            repaint();
        }
    }

    /**
     * Move the viewable area by a fixed amount in the X dimension
     *
     * @param positive if true, move it +100, if false, move it -100.
     */
    @Override
    public void xMove(boolean positive) {
        xMove((positive ? 100 : -100));
    }

    /**
     * Move the viewable area by a fixed amount in the Y dimension
     * @param positive if true, move it +100, if false, move it -100.
     */
    @Override
    public void yMove(boolean positive) {
        yMove((positive? 100: -100));
    }

    /**
     * Obtains a PassageEditingInterface object from the passageMap of theData from UUID
     * @param uuidOfPassageToGet the UUID of the passage to get
     * @return that passage
     */
    @Override
    public PassageEditingInterface getPassageFromUUID(UUID uuidOfPassageToGet){
        return theData.getPassageFromUUID(uuidOfPassageToGet);
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

    /**
     * Resizes this model
     * @param d the new size for it
     */
    @Override
    public void setSize(Dimension d){
        super.setSize(d);
        for (ModelButtonObject b: buttons) {
            b.resize(getWidth(),getHeight());
        }
        revalidate();
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
         * Dialog box open
         */
        DIALOG_OPEN,
        /**
         * Right click drag: start dragging the view.
         */
        RC_MOVING_VIEW
    }



}
