package oh_hecc.mvc;


import javax.swing.*;
import java.awt.*;

/**
 * A view of the Model
 */
public class View extends JComponent {

    /**
     * Whether or not this should be drawing a model
     */
    boolean drawingModel;
    /**
     * Reference to the Model being viewed
     */
    Model theModelThatsBeingViewed;




    /**
     * Create a View that doesn't show a model.
     */
    public View(){

        drawingModel = false;
        this.setSize(800,600);
    }

    /**
     * Constructor to show a View with a Model
     * @param showThis the Model to show
     */
    public View(Model showThis){
        showThisModel(showThis);
    }


    /**
     * Call this method to make this View display a Model
     * @param m the model to display
     */
    public void showThisModel(Model m){
        theModelThatsBeingViewed = m;
        drawingModel = true;

    }

    @Override
    public void paintComponent(Graphics g0){
        super.paintComponent(g0);
        //super.paint(g0);
        //theModelThatsBeingViewed.revalidate();
        if (drawingModel){
            //theModelThatsBeingViewed.draw(g);
            theModelThatsBeingViewed.paint(g0);
        }
        /*
        Graphics2D g = (Graphics2D) g0;

        AffineTransform initialTransform = g.getTransform();


        //g.setColor(Color.RED);
        //g.fillRect(0,0,getWidth(),getHeight());


        g.setTransform(initialTransform);
        */

    }

    /**
     * Basically like the normal @code{Component.setSize(Dimension d)} method.
     * Except this also resizes the Model (via @code{Model.RESIZE_MODEL()} to
     * match the new size of this View (if #drawingModel is true ofc)
     * @param d the new size for this View
     * @see #setSize
     * @see Model
     */
    @Override
    public void setSize(Dimension d){
        super.setSize(d);
        if (drawingModel) {
            //Model.RESIZE_MODEL(getWidth(), getHeight());
            theModelThatsBeingViewed.setSize(d);
        }
    }

}
