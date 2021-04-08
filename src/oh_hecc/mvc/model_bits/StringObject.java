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

    /**
     * The alignment for the string
     */
    private int alignment;

    /**
     * Makes the string display left-aligned (to the right of origin)
     */
    public static final int LEFT_ALIGN = 0;
    /**
     * Makes the string display right-aligned (to the left of origin)
     */
    public static final int RIGHT_ALIGN = 1;
    /**
     * Makes the string display center-aligned (midpoint is origin)
     */
    public static final int MIDDLE_ALIGN = 2;


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

    @Override
    void individualUpdate(){ } // no individual update


    public boolean isClicked(Point clickLocation){ return false; } // not clickable


    @Override
    public void individualDraw(Graphics2D g) {
        Font tempFont = g.getFont();
        g.setFont(tempFont.deriveFont(Font.BOLD)); // we're using bold text
        g.setColor(Color.black);
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int w = metrics.stringWidth(theString);
        int h = metrics.getHeight();
        int heightOffset = -h/2;
        int widthOffset = 0;
        // basically moves the horizontal position of the rendered text to make it appear 'aligned' a certain way
        switch (alignment) {
            case StringObject.LEFT_ALIGN:
                widthOffset = 0; // it's left-aligned by default
                break;
            case StringObject.RIGHT_ALIGN:
                widthOffset = -w; // makes it basically go to the right
                break;
            case StringObject.MIDDLE_ALIGN:
                widthOffset = -(w / 2); // we move it halfway so the midpoint of the string is at the position
                break;
        }
        // we draw 4 slightly-offset copies of the string as a totes legit outline
        g.drawString(theString,widthOffset+1,+1);
        g.drawString(theString,widthOffset-1,+1);
        g.drawString(theString,widthOffset-1,-1);
        g.drawString(theString,widthOffset+1,-1);

        // now we draw the actual string, not as an outline
        g.setColor(objectColour);
        g.drawString(theString,widthOffset,0);
        g.setFont(tempFont);
        // and yeah here's the area rectangle which we don't use.
        // why was it updated in the draw operation? because we can only get the fontmetrics in the draw operation.
        //areaRectangle = new Rectangle((int)position.x - (w/2), (int)position.y + heightOffset,w,h);
    }

    /**
     * Overwrites the text in the string held in this StringObject
     * @param s the new string to display
     * @return this StringObject
     */
    public StringObject setText(String s){ theString = s; return this;}

}
