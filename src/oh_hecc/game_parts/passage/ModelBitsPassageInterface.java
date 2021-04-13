package oh_hecc.game_parts.passage;

import utilities.Vector2D;

import java.util.Set;
import java.util.UUID;

/**
 * An interface for the EditablePassage, used by the PassageObject in src/oh_hecc/mvc/model_bits.
 */
public interface ModelBitsPassageInterface {

    /**
     * Obtains the passage UUID (read-only)
     * @return the identifier for this passage
     */
    UUID getPassageUUID();


    /**
     * Obtains the passage name
     * @return passage name
     */
    String getPassageName();

    /**
     * Gets the position of this Passage
     * @return the position Vector2D
     */
    Vector2D getPosition();

    /**
     * Updates the position of this Passage
     * @param newPosition the new position of this passage
     */
    void updatePosition(Vector2D newPosition);

    /**
     * gets the set of the UUIDs of linked passages
     * @return the set of UUIDs of linked passages
     */
    Set<UUID> getLinkedPassageUUIDs();

    /**
     * Obtain an enum representing the current status of the passage
     * @return a passageStatus value for this passage
     */
    PassageStatus getPassageStatus();

    /**
     * Is this passage a 'point of no return'?
     * (it's a point of no return if it has a 'noreturn' tag)
     *
     * @return true if yes, false if not.
     */
    boolean isThisAPointOfNoReturn();



}
