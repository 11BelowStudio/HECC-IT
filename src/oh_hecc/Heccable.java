package oh_hecc;

/**
 * An interface which indicates that this class can be converted into HECC code
 */
public interface Heccable {
    /**
     * Method to obtain the .hecc representation of this object
     * @return a string containing this object in .hecc code format
     */
    String toHecc();
}
