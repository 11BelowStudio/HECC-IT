package hecc_up.heccCeptions;

//exception for when passages share names

public class DuplicatePassageNameException extends ParserException{
    public DuplicatePassageNameException(String passageName){ super("Multiple passages share the same name ("+ passageName + "), which is not allowed!");}
}