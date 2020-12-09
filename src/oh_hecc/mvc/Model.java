package oh_hecc.mvc;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * A superclass for the 'Model' bit of 'MVC'.
 * Extends canvas.
 */
public abstract class Model extends Canvas implements MouseControlModelInterface{

    //TODO: work out if it's actually a good idea to extend Canvas?
    //TODO: clipping.


    /**
     * Sky blue: 94bfac
     */
    public static final Color SKYBLUE =new Color(48, 191, 172);
    /**
     * night: 282b2f
     */
    public static final Color NIGHT = new Color(40, 43, 47);
    /**
     * sunrise: cfb48a
     */
    public static final Color SUNRISE = new Color(207, 180, 138);
    /**
     * sunset pink: e3bbbd
     */
    public static final Color SUNSET = new Color(227, 187, 189);

    /**
     * w3schools camo grey: 9495a5
     */
    public static final Color W3_CAMO_GREY = new Color(148, 149, 165);
    /**
     * w3schools BS 381 (381 642) night: #282b2f
     */
    public static final Color W3_NIGHT = new Color(40, 43, 47);
    /**
     * w3schools BS 4800 (20-C-40) midnight: #29374b
     */
    public static final Color W3_MIDNIGHT = new Color(41, 55, 75);

    //final ControllerInterface theController;

    /**
     * oh look, some synchronization for multithreaded stuff
     */
    final Object SYNC_OBJECT = new Object();

    /**
     * Background colour for this model
     */
    final Color backgroundColour = W3_NIGHT;

    /**
     * default width/height stuff
     */
    static final int DEFAULT_MODEL_WIDTH = 800;
    static final int DEFAULT_MODEL_HEIGHT = 600;

    static int MODEL_WIDTH = DEFAULT_MODEL_WIDTH;
    static int MODEL_HEIGHT = DEFAULT_MODEL_HEIGHT;



    public static int GET_MODEL_WIDTH(){return MODEL_WIDTH;}
    public static int GET_MODEL_HEIGHT(){return MODEL_HEIGHT;}

    public Model(){
        //theController = c;
        setBackground(backgroundColour);
        setPreferredSize(new Dimension(DEFAULT_MODEL_WIDTH,DEFAULT_MODEL_HEIGHT));



    }

    /*
    public void update(Graphics g){
        update();
        super.update(g);
    }

     */
    /*
    public void update(){
        //updateModel();
        synchronized (SYNC_OBJECT) {
            refreshDrawables();
        }
    }
     */


    abstract void updateModel();

    /**
     * Use this to refresh the drawable items for the model.
     */
    abstract void refreshDrawables();

    @Override
    public void paint(Graphics g0){
        //super.paint(g0);
        //update();
        //refreshDrawables();
        System.out.println("paint time");
        Graphics2D g = (Graphics2D) g0;

        g.setColor(backgroundColour);
        g.fillRect(0,0,getWidth(),getHeight());


        /*
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        rh.put(
                RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF
        );
        g.setRenderingHints(rh);
         */

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
     * Literally just calls drawModel(g)
     * @param g Graphics2D being used to draw this
     */
    public void draw(Graphics2D g){
        //g.setColor(backgroundColour);
        //g.fillRect(0,0,MODEL_WIDTH,MODEL_HEIGHT);
        drawModel(g);
    }

    /**
     * This method actually draws the model itself.
     * <p>
     * Subclass should override this method to hold the actual drawing code
     * @param g the Graphics2D context being used for the drawing of the model
     */
    public abstract void drawModel(Graphics2D g);

    //public abstract String getHecced();


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


    @Override
    public void repaint(){
        //update();
        synchronized (SYNC_OBJECT) {
            refreshDrawables();
        }
        super.repaint();
    }


}
