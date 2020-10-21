package heccCeptions;


/**
 * standard HECC_UP parser exception
 */
public abstract class ParserException extends Exception{
    /**
     * YEET
     * @param s an message
     */
    public ParserException(String s){ super("ERROR!\n" + s);}
}