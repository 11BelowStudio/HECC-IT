package heccCeptions;


/**
 * exception for when the specified start passage doesn't actually exist
 */
public class MissingStartingPassageException extends HeccCeption {
    /**
     * YEET
     *
     * @param startingPassageName the specified starting passage (that doesn't exist)
     */
    public MissingStartingPassageException(String startingPassageName) {
        super("How is the player supposed to start from '" + startingPassageName +
                "', when that passage doesn't exist?");
    }
}
