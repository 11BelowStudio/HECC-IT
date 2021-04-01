package oh_hecc.mvc;

import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * A superclass for the 'Model' bit of 'MVC'.
 * Extends JComponent.
 */
public abstract class Model extends JComponent implements ControllableModelInterface {

    //TODO: clipping?

    /**
     * w3schools BS 381 (381 642) night: #282b2f
     */
    public static final Color W3_NIGHT = new Color(40, 43, 47);
    /**
     * w3schools BS 4800 (20-C-40) midnight: #29374b
     */
    public static final Color W3_MIDNIGHT = new Color(41, 55, 75);


    /**
     * oh look, some synchronization for multithreaded stuff. This is mostly here so I don't get problems with the
     * drawables being edited whilst they're being drawn.
     */
    private final Object SYNC_OBJECT = new Object();

    /**
     * Background colour for this model
     */
    final Color backgroundColour = W3_NIGHT;

    /**
     * default width/height stuff
     */
    static final int DEFAULT_MODEL_WIDTH = 800;
    static final int DEFAULT_MODEL_HEIGHT = 600;

    /**
     * The width/height of the model.
     */
    static int MODEL_WIDTH = DEFAULT_MODEL_WIDTH;
    static int MODEL_HEIGHT = DEFAULT_MODEL_HEIGHT;

    /**
     * A Vector2D that indicates where the top-right corner of the viewable area is
     */
    final Vector2D topRightCorner;

    /**
     * No-argument constructor.
     *
     * Just sets up the top-right corner such that 0,0 is in the middle of the viewable area.
     */
    public Model() {
        topRightCorner = new Vector2D(-getWidth() / 2, -getHeight() / 2);

    }

    /**
     * returns the x size of the model
     *
     * @return x size
     */
    public int getWidth() {
        return MODEL_WIDTH;
    }

    /**
     * returns the y size of the model
     *
     * @return y size
     */
    public int getHeight() {
        return MODEL_HEIGHT;
    }


    /**
     * Resizes the model to be the same size as the given dimension.
     *
     * @param d the given dimension
     */
    public void setSize(Dimension d) {
        MODEL_WIDTH = d.width;
        MODEL_HEIGHT = d.height;
    }

    /**
     * Returns the size of the model
     *
     * @return the dimension representing the size of the model
     */
    public Dimension getSize() {
        return new Dimension(MODEL_WIDTH, MODEL_HEIGHT);
    }

    /**
     * Use this to refresh the drawable items for the model.
     */
    abstract void refreshDrawables();


    /**
     * Fills in the background, enables anti-aliasing, gets a backup of the current affine transform,
     * and then it'll call drawModel(g).
     * This method is effectively a wrapper for refreshDrawables, and is intentionally NOT overridable.
     * @param g Graphics2D being used to draw this
     */
    public final void draw(Graphics2D g) {
        //System.out.println("draw");
        g.setColor(backgroundColour);
        g.fillRect(0, 0, MODEL_WIDTH, MODEL_HEIGHT);
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );
        AffineTransform initialTransform = g.getTransform();
        synchronized (SYNC_OBJECT) {
            drawModel(g);
        }
        g.setTransform(initialTransform);
    }

    /**
     * Calls the refreshDrawables method (refreshing the drawable things, synchronized so it won't interfere with any
     * currently-executing draw operations), and then calls the revalidate method of the JComponent superclass.
     */
    @Override
    public void revalidate(){
        synchronized(SYNC_OBJECT){
            refreshDrawables();
        }
        super.revalidate();
    }

    /**
     * This method actually draws the model itself.
     * <p>
     * Subclass should override this method to hold the actual drawing code
     * @param g the Graphics2D context being used for the drawing of the model
     */
    public abstract void drawModel(Graphics2D g);


    /**
     * Move the viewport in the X direction
     * @param positive whether it should be moved in positive X or in negative X
     */
    public abstract void xMove(boolean positive);

    /**
     * Move the viewport in the Y direction
     * @param positive whether it should be moved in positive Y or in negative Y
     */
    public abstract void yMove(boolean positive);


}
