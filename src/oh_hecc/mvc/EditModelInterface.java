package oh_hecc.mvc;

import oh_hecc.game_parts.passage.PassageEditingInterface;

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



    /**
     * Update the passageLinks of the passage objects that link to the given passage
     * @param destination the UUID of the destination passage whose parents need to update their links.
     */
    void updatePassageObjectLinksWhichLinkToSpecifiedPassage(UUID destination);


}
