package oh_hecc;

import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.PassageEditingInterface;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * A single class that can encapsulate all the game data stuff
 */
public class GameDataObject implements Heccable {

    /**
     * Map of all passages, mapped to their UUIDs
     */
    private final Map<UUID, PassageEditingInterface> passageMap;

    /**
     * The metadataEditingInterface object for this heccin game
     */
    private final MetadataEditingInterface theMetadata;

    /**
     * Where the .hecc file is actually saved
     */
    private final Path saveFileLocation;

    /**
     * Instantiates a new Game data object.
     *
     * @param pMap the passage map
     * @param meta the metadataeditinginterface object
     * @param saveLocation where the hecc file is saved
     */
    GameDataObject(Map<UUID, PassageEditingInterface> pMap, MetadataEditingInterface meta, Path saveLocation){
        passageMap = pMap;
        theMetadata = meta;
        saveFileLocation = saveLocation;
    }

    /**
     * Gets passage map.
     *
     * @return the passage map
     */
    public Map<UUID, PassageEditingInterface> getPassageMap() {
        return passageMap;
    }

    /**
     * Gets the metadata.
     *
     * @return the the metadata
     */
    public MetadataEditingInterface getTheMetadata() {
        return theMetadata;
    }

    /**
     * Obtains the PassageEditingInterface objects of all the passages which the given passage links to
     *
     * @param uuidOfSourceObject the UUID of the passage that we're trying to find the 'child' passages of
     * @return the UUIDs of all the 'child' passages
     */
    public Set<PassageEditingInterface> getPassageEditingInterfaceObjectsConnectedToGivenObject(UUID uuidOfSourceObject) {
        Set<PassageEditingInterface> theLinkedPassages = new HashSet<>();
        passageMap.get(uuidOfSourceObject).getLinkedPassageUUIDs().forEach(
                u -> theLinkedPassages.add(passageMap.get(u))
        );
        return theLinkedPassages;
    }

    /**
     * Obtains the UUIDs of the passages that link to the destination passage
     *
     * @param destination the UUID of the passage that we're trying to find the 'parent' passages of
     * @return the UUIDs of all the 'parent' passage
     */
    public Set<UUID> getThePassageObjectsWhichLinkToGivenPassageFromUUID(UUID destination) {
        return passageMap.keySet().stream().filter(
                p -> passageMap.get(p).getLinkedPassageUUIDs().contains(destination)
        ).collect(Collectors.toSet());
    }

    /**
     * Get the .hecc version of the data held in this object
     * @return The data held within theMetadata and in passageMap but in .hecc form
     */
    @Override
    public String toHecc() {
        return theMetadata.toHecc().concat("\n").concat(
                passageMap.values().stream().map(Heccable::toHecc)
                        .collect(Collectors.joining("\n")));
    }
}
