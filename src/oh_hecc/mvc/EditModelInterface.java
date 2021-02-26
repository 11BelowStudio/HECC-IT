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


    /**
     * Obtains the UUIDs of the passages that link to the destination passage
     * @param destination the UUID of the passage that we're trying to find the 'parent' passages of
     * @return the UUIDs of all the 'parent' passage
     */
    Set<UUID> getThePassageObjectsWhichLinkToGivenPassageFromUUID(UUID destination);

    /**
     * Update the passageLinks of the passage objects that link to the given passage
     * @param destination the UUID of the destination passage whose parents need to update their links.
     */
    void updatePassageObjectLinksWhichLinkToSpecifiedPassage(UUID destination);

    /**
     * Returns the size of the model
     *
     * @return the dimension representing the size of the model
     */
    Dimension getSize();


    /**
     * returns the x size of the model
     *
     * @return x size
     */
    int getWidth();

    /**
     * returns the y size of the model
     *
     * @return y size
     */
    int getHeight();


}
