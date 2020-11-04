package heccCeptions;

/**
 * exception for when passages share names
 */
public class DuplicatePassageNameException extends HeccCeption {
    /**
     * YEET
     * @param passageName the shared passage name
     */
    public DuplicatePassageNameException(String passageName){
        super("Multiple passages share the same name ("+ passageName + "). No idea which one you're referring to, so please come up with an original name for each passage.");
    }
}