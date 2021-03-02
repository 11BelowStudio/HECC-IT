package heccCeptions;


/**
 * standard HECC exception
 */
public abstract class HeccCeption extends Exception {

    /**
     * The raw error message, without the prefix.
     */
    private final String errorMessage;

    /**
     * YEET
     *
     * @param s an message
     */
    public HeccCeption(String s) {
        super("What the HECC!?!?!\n" + s);
        errorMessage = s;
    }

    /**
     * Returns the error message without the prefix.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}