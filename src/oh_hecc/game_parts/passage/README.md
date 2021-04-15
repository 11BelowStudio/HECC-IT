# src/oh_hecc/game_parts/passage

Various classes/interfaces used to represent Passage objects in the HECCIN' Game

## enum

* [PassageStatus](./PassageStatus.java)
    * Basically represents the 'status' of a passage.
        * 'valid' states are
            * `END_NODE` (no links, no problems)
            * `NORMAL` (has links, no problems)
        * 'invalid' states are
            * `EMPTY_CONTENT` (no content)
            * `DELETED_LINK` (content has a link that's been marked as pointing to a now-deleted passage)

## Classes

* [AbstractPassage](./AbstractPassage.java)
    * A superclass for the `Passage` objects, storing passage name, passage content, names of
      passages which this passage has links to, a list of 'tag' strings for the passage,
      and a status (as a value from the `PassageStatus` enum). Responsible for initialising
      those values.
      
* [OutputtablePassage](./OutputtablePassage.java)
    * A subclass of the `AbstractPassage`, intended for use in `HECC-UP`. No setters here,
      only getters.
      
* [OutputtablePassageTest](./OutputtablePassageTest.java)
    * Some unit tests for the aforementioned `OutputtablePassage`, to ensure that the initial
      (and final) state of these objects is set up correctly.
      
* [EditablePassage](./EditablePassage.java)
    * A subclass of the `AbstractPassage`, intended for use in `OH-HECC`. Setters (with
      validation checks included) and getters galore.
      
* [EditablePassageTest](./EditablePassageTest.java)
    * Some unit tests for the `EditablePassage`.
    
## Interfaces

* [SharedPassage](./SharedPassage.java)
    * A general interface for the `Passage` objects. Exposes getters, has some static regex
      validation methods.
      
* [PassageOutputtingInterface](./PassageOutputtingInterface.java)
    * An interface for the `OutputtablePassage` used by `HECC-UP`, only exposes the getters
      that `HECC-UP` actually uses

* [PassageOutputtingLinkCheckingInterface](./PassageOutputtingLinkCheckingInterface.java)
    * An interface for the `PassageOutputtingInterface`, used within one specific method
      of `HECC-UP`.
      
* [PassageReadingInterface](./PassageReadingInterface.java)
    * An interface intended for the 'reading' of passages. Has some static regexes/validation
      methods.
    
* [PassageEditingInterface](./PassageEditingInterface.java)
    * An interface which exposes the setters and getters methods one would ever need to use for
      editing an `EditablePassage` and then some.
      
* [PassageModelEditablePassageInterface](./PassageModelEditablePassageInterface.java)
    * A lighterweight interface for `EditablePassage` that just has the getters/setters that
      are directly used by the `PassageModel` (in `src/oh_hecc/mvc`)

* [ModelBitsPassageEditingInterface](./ModelBitsPassageInterface.java)
    * A lighter-er-weight interface for `PassageModelEditablePassageInterface`, used by the
      `src/oh_hecc/mvc/model_bits/PassageObject` class.
      
* [UpdatableLinkedUUIDsInterface](./UpdatableLinkedUUIDsInterface.java)
    * A very lightweight interface for the `PassageEditingInterface` which only exposes the
      `updateLinkedUUIDs(Collection<? extends SharedPassage> allPassages)` (for use when
      updating the set of the UUIDs of passages that this passage links to)