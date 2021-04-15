# src/oh_hecc/game_parts/component_editing_windows

Classes that work as JFrame-based GUIs for editing `EditablePassage`/`EditableMetadata` objects.

* [GenericEditorWindow.java](./GenericEditorWindow.java)
    * Superclass for `MetadataEditorWindow` and `PassageEditorWindow`, reponsible for setting
      up the stuff that both of those windows use, such as making the JFrame, and holding the
      `EditWindowGameDataInterface` object.
      
* [MetadataEditorWindow](./MetadataEditorWindow.java)
    * Used for editing a `MetadataEditingInterface` object.
    
* [PassageEditorWindow](./PassageEditorWindow.java)
    * Used for editing (and deleting!) a `PassageEditingInterface` object.
    
* [EditorWindowInterface](./EditorWindowInterface.java)
    * An interface which `GenericEditorWindow` implements.
    * Has a `void addWindowClosedListener(Runnable closeEvent)` method which accepts a `Runnable`,
      which is supposed to be `.run()` when the JFrame belonging to the implementing class is closed.