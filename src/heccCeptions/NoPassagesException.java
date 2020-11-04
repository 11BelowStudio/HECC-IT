package heccCeptions;

/**
 * exception for when there are no passages in the .hecc file
 */
public class NoPassagesException extends HeccCeption {
    /**
     * YEET
     */
    public NoPassagesException(){ super("How do you expect anyone to play this game, seeing as you forgot to define any passages?");}
}