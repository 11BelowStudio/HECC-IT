package oh_hecc.game_parts;

import heccCeptions.InvalidPassageNameException;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.PassageEditingInterface;

import java.util.Map;
import java.util.UUID;

/**
 * A somewhat more lightweight interface for the GameDataObject, used by the ComponentEditingWindows.
 */
public interface EditWindowGameDataInterface {


    /**
     * Attempts to update the 'startPassage' field of the metadata
     * @param newStartPassage the name of the new intended start passage
     * @return true if the start passage was updated successfully
     * @throws InvalidPassageNameException if the new start passage isn't valid
     */
    boolean updateStartPassage(String newStartPassage) throws InvalidPassageNameException;

    /**
     * Informs this object that the passage with the specified UUID has been deleted.
     * If aforementioned passage was the start passage, this object should set the start passage UUID to be that of another arbitrary passage which still exists.
     * @param uuid UUID of deleted passage.
     */
    void thisPassageHasBeenDeleted(UUID uuid);

    /**
    * Checks to see if the start passage was renamed, and, if so, it'll rename the reference to it in the metadata appropriately
    */
    void checkForStartRename();

    /**
     * Gets passage map.
     *
     * @return the passage map
     */
    Map<UUID, PassageEditingInterface> getPassageMap();

    /**
     * Gets the metadata.
     *
     * @return the the metadata
     */
    MetadataEditingInterface getTheMetadata();


}
