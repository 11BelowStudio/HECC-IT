package heccCeptions;

/**
 * exception used when an undefined passage is linked
 */
public class UndefinedPassageException extends HeccCeption {
    /**
     * YEET
     *
     * @param passageName the name of the passage what doesn't exist
     */
    public UndefinedPassageException(String passageName) {
        super("Why are you trying to link to a passage called '" + passageName + "' even though that passage doesn't exist?");
    }
}