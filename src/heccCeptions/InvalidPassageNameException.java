package heccCeptions;


/**
 * exception for when a passage has an invalid name
 */
public class InvalidPassageNameException extends HeccCeption {
    /**
     * YEET
     * @param passageName the invalid passage name
     */
    public InvalidPassageNameException(String passageName){ super(passageName + " is not a valid passage name!");}
}