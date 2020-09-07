package hecc_up.heccCeptions;

//exception for when passage has invalid name

public class InvalidPassageNameException extends ParserException{
    public InvalidPassageNameException(String passageName){ super(passageName + " is not a valid passage name!");}
}