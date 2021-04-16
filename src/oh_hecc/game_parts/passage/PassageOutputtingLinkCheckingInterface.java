package oh_hecc.game_parts.passage;

import heccCeptions.UndefinedPassageException;

import java.util.Set;

/**
 * An interface for getting the linked passages of a given passage and such,
 * for the sole purpose of the recursive linked passage finding stuff in hecc-up
 */
public interface PassageOutputtingLinkCheckingInterface {

    /**
     * This can be used to check if all the linked passages actually exist, throwing an exception if they don't exist
     *
     * @param allPassages The set of all declared passages
     * @throws UndefinedPassageException if one of this passage's linked passages don't actually exist
     */
    void validateLinkedPassagesThrowingException(Set<String> allPassages) throws UndefinedPassageException;


    /**
     * gets the set of linked passages
     * @return linkedPassages
     */
    Set<String> getLinkedPassages();

    /**
     * Obtains the hecced version of this passage
     * @return hecced.js declaration for this passage
     */
    String getHecced();
}
