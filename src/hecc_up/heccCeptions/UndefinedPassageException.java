package hecc_up.heccCeptions;

//exception for when an undefined passage is linked

public class UndefinedPassageException extends ParserException{
    public UndefinedPassageException(String passageName){ super("There is a link to a passage called '" + passageName +"', which isn't defined!");}
}