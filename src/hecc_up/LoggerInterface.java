package hecc_up;

/**
 * An interface for boundary classes that are supposed to be able to log data
 * it's an interface instead of a bit more code in HeccUpGUI to maintain backwards compatiability with HeccUpMain
 */
public interface LoggerInterface {

    /**
     * Called to output string logging info to the user
     * @param infoToLog the info what needs logging
     *
     * default implementation just prints it to console
     */
    public default void logInfo(String infoToLog){
        System.out.println(infoToLog);
    }
}
