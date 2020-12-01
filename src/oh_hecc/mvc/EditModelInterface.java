package oh_hecc.mvc;

import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.mvc.model_bits.PassageObject;

import java.awt.*;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * A relatively lightweight interface for EditModelObject objects to use
 * instead of the PassageModel class.
 */
public interface EditModelInterface {

    /**
     * Obtains a PassageEditingInterface object from the implementing class's PassageMap via UUID
     * @param uuidOfPassageToGet the UUID of the passage to get
     * @return that passage
     */
    PassageEditingInterface getPassageFromUUID(UUID uuidOfPassageToGet);

    PassageObject getPassageObjectFromUUID(UUID uuidOfPassageObjectToGet);

    Set<UUID> getUUIDsOfPassagesLinkedToParticularPassageFromUUID(UUID sourcePassageUUID);

    Set<PassageEditingInterface> getPassagesFromSetOfUUIDs(Set<UUID> getThesePassages);

    Set<PassageEditingInterface> getPassageEditingInterfaceObjectsConnectedToGivenObject(UUID uuidOfSourceObject);

    /**
     * Gets the passageMap object from the implementing class
     * @return the passageMap from the implementing class
     */
    Map<UUID, PassageEditingInterface> getThePassageMap();


}
