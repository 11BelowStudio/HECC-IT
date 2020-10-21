package hecc_up;

/**
 * An interface for boundary classes that are supposed to be able to log data.
 * It's an interface instead of a bit more code in HeccUpGUI,
 * to maintain backwards compatiability with HeccUpMain,
 * and for more future flexibility I guess
 */
public interface LoggerInterface {

    /**
     * Called to output string logging info to the user.
     * Default implementation just prints it to console.
     * @param infoToLog the info what needs logging
     */
    default void logInfo(String infoToLog){
        System.out.println(infoToLog);
    }
}
