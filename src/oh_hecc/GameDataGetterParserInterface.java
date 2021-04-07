package oh_hecc;

import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.PassageEditingInterface;

import java.util.Map;
import java.util.UUID;

/**
 * An interface for the OhHeccParser that can be passed as an argument to the GameDataObject constructor
 */
public interface GameDataGetterParserInterface {

    /**
     * Obtains the MetadataEditingInterface object
     * @return the MetadataEditingInterface object
     */
    MetadataEditingInterface getMetadata();

    /**
     * Obtains the map of PassageEditingInterface objects
     * @return the map of PassageEditingInterface objects
     */
    Map<UUID, PassageEditingInterface> getHeccMap();
}
