package heccCeptions;

/**
 * Exception used by the Metadata regex stuff, thrown if the matcher doesn't match anything
 */
public class NoMatchException extends HeccCeption {
    /**
     * YEET
     */
    public NoMatchException(){ super ("Nothing found!");}
}
