package oh_hecc.game_parts.passage;

import java.util.Map;
import java.util.UUID;

/**
 * Literally just has the updateLinkedUUIDs method, for the loop where this has to be called for every passage.
 */
public interface UpdatableLinkedUUIDsInterface {

    /**
     * Method that'll be used to update the set containing the UUIDs of all the passages that this passage is linked to.
     * call this for each element in the map of (? extends SharedPassages) <b>after</b> everything's been added to it.
     * @param allPassages the map of all passages mapped to UUIDs (where the UUIDs will be read from basically)
     */
    void updateLinkedUUIDs(Map<UUID, ? extends SharedPassage> allPassages);
}
