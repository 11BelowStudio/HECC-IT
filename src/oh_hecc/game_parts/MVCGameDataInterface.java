package oh_hecc.game_parts;

import heccCeptions.HeccCeption;
import oh_hecc.Heccable;
import oh_hecc.game_parts.component_editing_windows.EditorWindowInterface;
import oh_hecc.game_parts.component_editing_windows.PassageEditorWindow;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.ModelBitsPassageInterface;
import oh_hecc.game_parts.passage.PassageEditingInterface;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * A lighterweight interface for the GameDataObject that will have the functionality needed by PassageModel.java
 * <br>
 * Currently very lightweight because it doesn't do anything yet.
 */
public interface MVCGameDataInterface extends Heccable {

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

    /**
     * Obtains the UUID of the start passage. Will not forcibly create a start passage.
     * @return an Optional holding the UUID of the start passage (if it exists)
     */
    Optional<UUID> getStartUUID();

    /**
     * Gets the path to where this object is saved
     * @return savePath
     */
    Path getSavePath();

    /**
     * Puts the specified PassageEditingInterface object onto the PassageMap.
     * @param p the passage to put on the map.
     */
    void putPassageOntoMap(PassageEditingInterface p);

    /**
     * Obtains the PassageEditingInterface object for the passage with the given UUID
     * @param uuidOfPassageToGet the UUID of the PassageEditingInterface object which is needed.
     * @return the appropriate PassageEditingInterface object.
     */
    PassageEditingInterface getPassageFromUUID(UUID uuidOfPassageToGet);


    /**
     * Obtains the UUIDs of the passages that link to the destination passage
     *
     * @param destination the UUID of the passage that we're trying to find the 'parent' passages of
     * @return the UUIDs of all the 'parent' passages
     */
    Set<UUID> getUUIDsOfPassagesThatLinkToThisOne(UUID destination);


    /**
     * Creates a PassageEditorWindow for the given passage (and the entire gamedata)
     * @param passage the UUID of the passage in question which is having its PassageEditorWindow opened
     * @return a {@link PassageEditorWindow} which allows a user to edit the passage in question
     */
    EditorWindowInterface openPassageEditWindow(UUID passage);

    /**
     * Creates a MetadataEditorWindow for this game
     * @return the MetadataEditorWindow allowing the user to edit the metadata
     */
    EditorWindowInterface openMetadataEditWindow();


    /**
     * Saves the .hecc
     *
     * @throws IOException if it couldn't be saved.
     */
    void saveTheHecc() throws IOException;

    /**
     * Attempts to check the hecc for validity, and overwrites the _lastValidVersion backup of the .hecc file
     * (only overwrites the '_lastValidVersion' backup if it is valid)
     *
     * @throws IOException if there's an IO problem preventing it from being saved.
     * @throws HeccCeption if there's a problem with the .hecc file that renders it invalid.
     */
    void saveTheHeccCheckingValidity() throws IOException, HeccCeption;
}
