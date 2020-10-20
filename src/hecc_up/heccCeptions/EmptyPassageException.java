package hecc_up.heccCeptions;

/**
 * exception for when a passage has no content
 */
public class EmptyPassageException extends ParserException{
    /**
     * YEET
     * @param passageName the passage that doesn't contain any content
     */
    public EmptyPassageException(String passageName){ super(passageName + " contains no content!");}
}