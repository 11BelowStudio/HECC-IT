package oh_hecc.mvc.controller;

import utilities.Vector2D;

import java.awt.*;

public class ControllerAction implements ActionViewer{

    private final Point leftLocation;
    private final Point leftClickLocation;
    private final Point leftDoubleClickLocation;
    private boolean leftDoubleClicked;

    private boolean leftClicked;
    private boolean holdingLeftMouseDown;
    /**
     * Where the left mouse drag started
     */
    private final Point leftDragStart;
    /**
     * Where the left mouse drag finished
     */
    private final Point leftDragEnd;
    private final Vector2D lastLeftDragLocation;
    /**
     * Vector from mouse location during last drag frame to current mouse location this drag frame
     */
    private final Vector2D currentLeftDrag;
    private boolean leftDragFinished;


    private final Point rightLocation;
    private final Point rightClickLocation;
    private boolean rightClicked;
    private boolean holdingRightMouseDown;
    /**
     * last drag frame's mouse location to this frame's mouse location
     */
    private final Vector2D lastRightDragLocation;
    /**
     * Vector from mouse location during last drag frame to current mouse location this drag frame
     */
    private final Vector2D currentRightDrag;

    private boolean somethingHappened;

    ControllerAction(){
        somethingHappened = false;

        leftLocation = new Point();
        leftClickLocation = new Point();
        leftClicked = false;
        holdingLeftMouseDown = false;
        lastLeftDragLocation = new Vector2D();
        currentLeftDrag = new Vector2D();

        leftDragStart = new Point();
        leftDragEnd = new Point();

        leftDoubleClicked = false;
        leftDoubleClickLocation = new Point();


        rightLocation = new Point();
        rightClickLocation = new Point();
        rightClicked = false;
        holdingRightMouseDown = false;
        lastRightDragLocation = new Vector2D();
        currentRightDrag = new Vector2D();
    }

    @Override
    public boolean checkForInput(){
        boolean temp = somethingHappened;
        if (somethingHappened){
            somethingHappened = false;
        }
        return temp;
    }

    private void somethingDidHappen(){
        somethingHappened = true;
    }

    void leftClick(Point mouseLocation){
        leftClicked = true;
        setLeftLocation(mouseLocation);
        leftClickLocation.setLocation(mouseLocation);
    }

    void setLeftDoubleClicked(Point location){
        setLeftLocation(location);
        leftDoubleClickLocation.setLocation(location);
        leftDoubleClicked = true;
    }

    void leftPress(Point mouseLocation){
        setLeftLocation(mouseLocation);
        lastLeftDragLocation.set(mouseLocation);
        currentLeftDrag.reset();
        leftDragStart.setLocation(mouseLocation);
        leftDragEnd.setLocation(mouseLocation);
        holdingLeftMouseDown = true;
    }

    void leftDrag(Point mouseLocation){
        leftDragHandler(mouseLocation);
    }

    void leftReleased(Point mouseLocation){
        leftDragHandler(mouseLocation);
        leftDragEnd.setLocation(mouseLocation);
        holdingLeftMouseDown = false;
        leftDragFinished = true;
    }

    private void leftDragHandler(Point mouseLocation){
        setLeftLocation(mouseLocation);
        currentLeftDrag.set(lastLeftDragLocation.getVectorTo(leftLocation));
        lastLeftDragLocation.set(leftLocation);
        leftDragEnd.setLocation(leftLocation);
    }

    private void setLeftLocation(Point mouseLocation) {
        leftLocation.setLocation(mouseLocation);
        somethingDidHappen();
    }

    void rightPress(Point mouseLocation){
        setRightLocation(mouseLocation);
        lastRightDragLocation.set(mouseLocation);
        holdingRightMouseDown = true;
        currentRightDrag.reset();
    }

    void rightClick(Point mouseLocation){
        rightClicked = true;
        setRightLocation(mouseLocation);
        rightClickLocation.setLocation(mouseLocation);
    }

    void rightDrag(Point mouseLocation){
        rightDragHandler(mouseLocation);
    }

    void rightReleased(Point mouseLocation){
        rightDragHandler(mouseLocation);
        holdingRightMouseDown = false;
    }

    private void rightDragHandler(Point mouseLocation){
        setRightLocation(mouseLocation);
        currentRightDrag.set(lastRightDragLocation.getVectorTo(rightLocation));
        lastRightDragLocation.set(rightLocation);
    }

    private void setRightLocation(Point mouseLocation){
        rightLocation.setLocation(mouseLocation);
        somethingDidHappen();
    }

    @Override
    public Point getLeftLocation(){
        return leftLocation.getLocation();
    }

    @Override
    public boolean checkLeftHold(){
        return holdingLeftMouseDown;
    }

    @Override
    public Vector2D getCurrentLeftDrag() {
        return currentLeftDrag;
    }

    @Override
    public boolean isLeftDragFinished(){
        boolean temp = leftDragFinished;
        if (leftDragFinished){
            leftDragFinished = false;
        }
        return temp;
    }

    @Override
    public Rectangle getLeftDragRect(){
        return new Rectangle(leftDragStart.x, leftDragStart.y, leftDragEnd.x - leftDragStart.x, leftDragEnd.y - leftDragStart.y);
    }

    @Override
    public boolean checkLeftClick(){
        boolean temp = leftClicked;
        if (leftClicked){
            leftClicked = false;
        }
        return temp;
    }

    @Override
    public boolean checkLeftDoubleClick(){
        boolean temp = leftDoubleClicked;
        if (leftDoubleClicked){
            leftDoubleClicked = false;
        }
        return temp;
    }

    @Override
    public Point getLeftDoubleClickLocation(){
        return leftDoubleClickLocation;
    }

    @Override
    public Point getRightLocation(){
        return rightLocation.getLocation();
    }

    @Override
    public Vector2D getCurrentRightDrag(){
        return currentRightDrag;
    }

    @Override
    public boolean checkRightHold(){
        return holdingRightMouseDown;
    }

    @Override
    public boolean checkRightClick(){
        boolean temp = rightClicked;
        if (rightClicked){
            rightClicked =false;
        }
        return temp;
    }


}
