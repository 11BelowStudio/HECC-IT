package hecc_up.heccCeptions;

//exception for when passage has no content

public class EmptyPassageException extends ParserException{
    public EmptyPassageException(String passageName){ super(passageName + " contains no content!");}
}