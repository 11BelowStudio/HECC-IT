package heccCeptions;


/**
 * exception for when the specified start passage doesn't actually exist
 */
public class MissingStartingPassageException extends ParserException {
    /**
     * YEET
     * @param startingPassageName the specified starting passage (that doesn't exist)
     */
    public MissingStartingPassageException(String startingPassageName){super("The starting passage is defined as '"+startingPassageName+"', but there are no passages with that name!");}
}
