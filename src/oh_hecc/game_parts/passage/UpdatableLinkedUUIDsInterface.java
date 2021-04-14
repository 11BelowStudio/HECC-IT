package oh_hecc.game_parts.passage;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Literally just has the updateLinkedUUIDs method, for the loop where this has to be called for every passage.
 */
public interface UpdatableLinkedUUIDsInterface {

    /**
     * Method that'll be used to update the set containing the UUIDs of all the passages that this passage is linked to.
     * call this for each element in the set of (? extends SharedPassages) <b>after</b> everything's been added to it.
     * @param allPassages the set of all passages mapped to UUIDs (where the UUIDs will be read from basically)
     */
    void updateLinkedUUIDs(Collection<? extends SharedPassage> allPassages);


}
