# src/oh_hecc/game_parts

This package contains various classes that are used to hold the game data.

* [GameDataObject](./GameDataObject.java)
    * Basically used to wrap all the game data in a single class.
    
* [MVCGameDataInterface](./MVCGameDataInterface.java)
    * An interface used when passing the `GameDataObject` to the `PassageModel`, only exposing
      the methods of the `GameDataObject` that the `PassageModel` actually needs.
      
* [EditWindowGameDataInterface](./EditWindowGameDataInterface.java)
    * An interface used when passing the `GameDataObject` to one of the `component_editing_windows`,
      only exposing the methods that are necessary to expose for use within those classes.
      
* [GameDataObjectTests](./GameDataObjectTests.java)
    * A couple of unit tests that are used to ensure that the `GameDataObject` is working as intended.
    
* [/metadata](./metadata)
    * A package containing some classes used to represent the metadata for the HECCIN' Game.

* [/passage](./passage)
    * A package containing some classes used to represent the passages that constitute the HECCIN' Game.

* [/component_editing_windows](./component_editing_windows)
    * Classes that offer an editing GUI for the metadata/passages.