package oh_hecc.mvc.model_bits;

import utilities.Vector2D;

import java.awt.*;

/**
 * An AbstractObject that basically just displays a string (White text with black border) at a given position in the model.
 */
public class StringObject extends AbstractObject {

    /**
     * The string that is actually being displayed.
     */
    private String theString;

    private int alignment;

    private Font theFont;


    public static final int LEFT_ALIGN = 0;
    public static final int RIGHT_ALIGN = 1;
    public static final int MIDDLE_ALIGN = 2;







    /**
     * Constructor given initial text, alignment, and font
     * @param s starting text
     * @param a alignment
     * @param f font for the text
     */
    public StringObject(String s, int a, Font f){
        this(new Vector2D(),s,a,f);
    }


    /**
     * Constructor given initial position, string, alignment, and font
     * @param p initial position
     * @param s initial text
     * @param a alignment
     * @param f font for the string
     */
    public StringObject(Vector2D p, String s, int a, Font f){
        this(p,s,a);
        theFont = f;
    }

    /**
     * Constructor given initial string, and alignment
     * @param s initial text
     * @param a alignment
     */
    public StringObject(String s, int a){
        this(new Vector2D(),s,a);
    }

    /**
     * Constructor given initial position, string, and alignment
     * @param p initial position
     * @param s initial text
     * @param a alignment
     */
    public StringObject(Vector2D p, String s, int a){
        this(p,a);
        setText(s);
    }

    /**
     * Constructor given a position and initial string for this StringObject
     * @param p position for this StringObject
     * @param s string initially displayed by this StringObject
     */
    public StringObject(Vector2D p, String s){
        this(p);
        setText(s);
    }

    /**
     * Constructor given a position and alignment for this StringObject
     * @param p position for this StringObject
     * @param a alignment for this StringObject
     */
    public StringObject(Vector2D p, int a){
        this(p);
        alignment = a;
    }

    /**
     * Constructor given a position
     * @param p position for this StringObject
     */
    public StringObject(Vector2D p){
        super(p);
        width = 0;
        height = 0;
        alignment = LEFT_ALIGN;
        theString = "";
        objectColour = Color.WHITE;
    }


    public String getString(){ return theString; }

    @Override
    public void individualUpdate(){

    }


    public boolean isClicked(Point clickLocation){
        return false;
    }

    void setTheFont(Font f){
        theFont = f;
    }


    @Override
    public void individualDraw(Graphics2D g) {
        Font tempFont = g.getFont();
        g.setFont(tempFont.deriveFont(Font.BOLD));
        g.setColor(Color.black);
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int w = metrics.stringWidth(theString);
        int h = metrics.getHeight();
        int heightOffset = -h/2;
        int widthOffset = 0;
        switch (alignment) {
            case StringObject.LEFT_ALIGN:
                widthOffset = 0;
                break;
            case StringObject.RIGHT_ALIGN:
                widthOffset = -w;
                break;
            case StringObject.MIDDLE_ALIGN:
                widthOffset = -(w / 2);
                break;
        }
        g.drawString(theString,widthOffset+1,+1);
        g.drawString(theString,widthOffset-1,+1);
        g.drawString(theString,widthOffset-1,-1);
        g.drawString(theString,widthOffset+1,-1);
        g.setColor(objectColour);
        g.drawString(theString,widthOffset,0);
        g.setFont(tempFont);
        areaRectangle = new Rectangle((int)position.x - (w/2), (int)position.y + heightOffset,w,h);
    }

    public StringObject setText(String s){ theString = s; return this;}

}
