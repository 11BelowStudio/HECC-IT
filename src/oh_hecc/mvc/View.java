package oh_hecc.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class View extends JComponent {

    boolean drawingModel;
    Model theModelThatsBeingViewed;

    public View(){
        drawingModel = false;
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
        Graphics2D g = (Graphics2D) g0;

        AffineTransform initialTransform = g.getTransform();

        if (drawingModel){
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
            Model.RESIZE_MODEL(getWidth(), getHeight());
        }
    }
}
