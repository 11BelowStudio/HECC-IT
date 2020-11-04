package oh_hecc;

import oh_hecc.metadata.MetadataEditingInterface;

import javax.swing.*;

public class MetadataEditorWindow {
    MetadataEditingInterface theMetadata;

    JFrame theFrame;

    MetadataEditorWindow(EditableMetadata metadata){
        theMetadata = metadata;

        theFrame = new JFrame("Metadata Editor Window");

    }
}
