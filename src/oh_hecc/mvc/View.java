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
    ViewableModelInterface theModelThatsBeingViewed;




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
    public View(ViewableModelInterface showThis){
        showThisModel(showThis);
    }


    /**
     * Call this method to make this View display a Model
     * @param m the model to display
     */
    public void showThisModel(ViewableModelInterface m){
        theModelThatsBeingViewed = m;
        drawingModel = true;

    }

    /**
     * We repaint this component, and, if we have a Model to draw, we draw it.
     * @param g0 the Graphics context being used.
     */
    @Override
    public void paintComponent(Graphics g0){
        super.paintComponent(g0);
        if (drawingModel){
            final Graphics2D g = (Graphics2D) g0;
            theModelThatsBeingViewed.draw(g);
        }

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
            theModelThatsBeingViewed.setSize(d);
        }
    }

}
