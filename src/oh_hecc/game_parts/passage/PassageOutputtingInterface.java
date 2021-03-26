package oh_hecc.game_parts.passage;

import heccCeptions.UndefinedPassageException;

import java.util.Set;

public interface PassageOutputtingInterface {


    /**
     * This can be used to check if all the linked passages actually exist, returning the result as a boolean
     *
     * @param allPassages The set of all the declared passages
     * @return true if all the passages this passage links to exist, false otherwise
     */
    boolean validateLinkedPassages(Set<String> allPassages);

    /**
     * This can be used to check if all the linked passages actually exist, throwing an exception if they don't exist
     *
     * @param allPassages The set of all declared passages
     * @throws UndefinedPassageException if one of this passage's linked passages don't actually exist
     */
    void validateLinkedPassagesThrowingException(Set <String> allPassages) throws UndefinedPassageException;

    /**
     * Gets the HECCED.js representation of this passage
     *
     * @return A string with the necessary JavaScript code to construct this passage
     */
    String toHecc();

    /**
     * This just prints the passage info, for debugging reasons.
     */
    void printPassageInfoForDebuggingReasons();


    /**
     * Obtains the name of this passage
     * @return the name of this passage
     */
    String getPassageName();

    /**
     * gets the set of linked passages
     * @return linkedPassages
     */
    Set<String> getLinkedPassages();



}
