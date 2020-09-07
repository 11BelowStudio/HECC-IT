package hecc_up.heccCeptions;

public class MissingStartingPassageException extends ParserException {
    public MissingStartingPassageException(String startingPassageName){super("The starting passage is defined as '"+startingPassageName+"', but there are no passages with that name!");}
}
