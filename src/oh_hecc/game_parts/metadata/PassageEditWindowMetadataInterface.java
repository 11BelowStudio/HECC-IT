package oh_hecc.game_parts.metadata;

import heccCeptions.InvalidPassageNameException;

/**
 * An interface for the EditableMetadata that was used by the PassageEditorWindow
 * @deprecated not used by the PassageEditorWindow now, made redundant by the EditWindowGameDataInterface
 */
@Deprecated
public interface PassageEditWindowMetadataInterface {

    /**
     * Obtains the start passage
     * @return the start passage
     */
    String getStartPassage();

    /**
     * Attempts to update the 'startPassage' field of this object
     * @param newStartPassage the name of the new intended start passage
     * @return true if the start passage was updated successfully
     * @throws InvalidPassageNameException if the new start passage isn't valid
     */
    boolean updateStartPassage(String newStartPassage) throws InvalidPassageNameException;
}
