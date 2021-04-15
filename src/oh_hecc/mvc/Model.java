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
     * A Vector2D that indicates where the top-left corner of the viewable area is
     */
    final Vector2D topLeftCorner;

    /**
     * No-argument constructor.
     *
     * makes this 800*600, and sets the top-left corner such that 0,0 is in the middle of the viewable area.
     */
    public Model() {
        setSize(800,600);
        topLeftCorner = new Vector2D(-getWidth() / 2, -getHeight() / 2);
    }

    /**
     * Use this to refresh the drawable items for the model.
     */
    abstract void refreshDrawables();

    /**
     * We basically draw the model.
     * @param g0 the Graphics context being used.
     */
    @Override
    public final void paintComponent(Graphics g0){
        super.paintComponent(g0);
        final Graphics2D g = (Graphics2D) g0;
        draw(g);
    }


    /**
     * Fills in the background, enables anti-aliasing, gets a backup of the current affine transform,
     * and then it'll call drawModel(g).
     * This method is effectively a wrapper for refreshDrawables, and is intentionally NOT overridable.
     * @param g Graphics2D being used to draw this
     */
    public final void draw(Graphics2D g) {
        g.setColor(backgroundColour);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );
        final AffineTransform initialTransform = g.getTransform();
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


}
