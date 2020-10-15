package hecc_up.heccCeptions;

//standard HECC_UP parser exception

public abstract class ParserException extends Exception{
    public ParserException(String s){ super("ERROR!\n" + s);}
}