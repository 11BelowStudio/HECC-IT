package oh_hecc.mvc;

import oh_hecc.mvc.controller.Controller;
import oh_hecc.mvc.controller.ControllerInterface;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public abstract class Model extends Canvas{

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

    final Object SYNC_OBJECT = new Object();

    Color backgroundColour = W3_NIGHT;

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


    public void update(){
        //updateModel();
        synchronized (SYNC_OBJECT) {
            refreshDrawables();
        }
    }



    abstract void updateModel();

    /**
     * Use this to refresh the drawable items for the model.
     */
    abstract void refreshDrawables();

    @Override
    public void paint(Graphics g0){
        Graphics2D g = (Graphics2D) g0;

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
                RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF
        );


        AffineTransform initialTransform = g.getTransform();
        synchronized (SYNC_OBJECT) {
            drawModel(g);
        }
        g.setTransform(initialTransform);
    }

    public void draw(Graphics2D g){
        //g.setColor(backgroundColour);
        //g.fillRect(0,0,MODEL_WIDTH,MODEL_HEIGHT);
        drawModel(g);
    }

    public abstract void drawModel(Graphics2D g);


    public abstract void xMove(boolean positive);

    public abstract void yMove(boolean positive);


    @Override
    public void repaint(){
        update();
        super.repaint();
    }


}
