package oh_hecc.mvc;

import utilities.Vector2D;

import java.awt.*;

public abstract class Model {

    //Sky blue: 94bfac
    static Color SKYBLUE =new Color(48, 191, 172);
    //night: 282b2f
    static Color NIGHT = new Color(40, 43, 47);
    //sunrise: cfb48a
    static Color SUNRISE = new Color(207, 180, 138);
    //sunset pink: e3bbbd
    static Color SUNSET = new Color(227, 187, 189);

    //w3schools camo grey: 9495a5
    static Color W3_CAMO_GREY = new Color(148, 149, 165);
    //w3schools BS 381 (381 642) night: #282b2f
    static Color W3_NIGHT = new Color(40, 43, 47);
    //w3schools BS 4800 (20-C-40) midnight: #29374b
    static Color W3_MIDNIGHT = new Color(41, 55, 75);

    Controller theController;

    Color backgroundColour = W3_NIGHT;

    static final int DEFAULT_MODEL_WIDTH = 800;
    static final int DEFAULT_MODEL_HEIGHT = 600;

    static int MODEL_WIDTH = DEFAULT_MODEL_WIDTH;
    static int MODEL_HEIGHT = DEFAULT_MODEL_HEIGHT;

    static void RESIZE_MODEL(int newWidth, int newHeight){
        MODEL_WIDTH = newWidth;
        MODEL_HEIGHT = newHeight;
    }

    public static int GET_MODEL_WIDTH(){return MODEL_WIDTH;}
    public static int GET_MODEL_HEIGHT(){return MODEL_HEIGHT;}

    public Model(Controller c){
        theController = c;
    }

    public abstract void update();

    public void drawModel(Graphics2D g){
        g.setColor(backgroundColour);
        g.fillRect(0,0,MODEL_WIDTH,MODEL_HEIGHT);
    }
}
