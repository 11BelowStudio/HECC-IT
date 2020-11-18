package oh_hecc;

import oh_hecc.game_parts.metadata.EditableMetadata;
import oh_hecc.game_parts.passage.PassageEditingInterface;

import java.util.Map;
import java.util.UUID;

public class OhHeccParser {

    String rawHeccCode;

    Map<UUID, PassageEditingInterface> heccMap;

    EditableMetadata theMetadata;


    //TODO: parse the HECC file into the heccMap and theMetadata

}
