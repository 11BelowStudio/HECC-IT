package oh_hecc.mvc;

import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.mvc.model_bits.PassageObject;

import java.awt.*;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * A relatively lightweight interface for PassageModelObject objects to use
 * instead of the PassageModel class.
 */
public interface EditModelInterface {



    PassageEditingInterface getPassageFromUUID(UUID uuidOfPassageToGet);

    PassageObject getPassageObjectFromUUID(UUID uuidOfPassageObjectToGet);


    Set<UUID> getUUIDsOfPassagesLinkedToParticularPassageFromUUID(UUID sourcePassageUUID);

    Set<PassageEditingInterface> getPassagesFromSetOfUUIDs(Set<UUID> getThesePassages);

    Set<PassageEditingInterface> getPassageEditingInterfaceObjectsConnectedToGivenObject(UUID uuidOfSourceObject);

    Map<UUID, PassageEditingInterface> getThePassageMap();


}
