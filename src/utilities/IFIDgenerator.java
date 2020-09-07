package utilities;

import java.util.UUID;

public class IFIDgenerator {

    //literally just generates an UUID and returns its string representation (in uppercase)
    public static String generateIFIDString(){
        UUID ifid = UUID.randomUUID();
        return (ifid.toString().toUpperCase());
    }
}
