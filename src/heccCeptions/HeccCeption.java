package heccCeptions;


/**
 * standard HECC exception
 */
public abstract class HeccCeption extends Exception{
    /**
     * YEET
     *
     * @param s an message
     */
    public HeccCeption(String s) {
        super("What the HECC!?!?!\n" + s);
    }
}