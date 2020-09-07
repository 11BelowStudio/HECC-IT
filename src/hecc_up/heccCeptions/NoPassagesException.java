package hecc_up.heccCeptions;

//exception for when there are no passages

public class NoPassagesException extends ParserException {
    public NoPassagesException(){ super("How do you expect anyone to play this game, seeing as you forgot to define any passages?");}
}