# src/oh_hecc

These are the top-level classes for OH-HECC (and, by extension, HECC-IT).


* [HeccItRunner](./HeccItRunner.java)
    * Basically the 'entry point' for the program. Holds the main method, and it's responsible
      for creating an instance of `ChooseFile`, getting the `OhHeccParser` to parse some .hecc
      (if necessary), before then opening HECC-UP or OH-HECC depending on whether the user
      wanted to export or edit a .hecc file.
      
* [OhHeccParser](./OhHeccParser.java)
    * Parses .hecc code for use in OH-HECC.
    
* [GameDataGetterParserInterface](./GameDataGetterParserInterface.java)
    * An interface, implemented by `OhHeccParser`, which is used to expose the getter methods of
      it, allowing it to be passed as an argument to a constructor of the
      `oh_hecc.game_parts.GameDataObject` class.
      
* [ChooseFile](./ChooseFile.java)
    * The main menu of HECC-IT, allowing a user to choose an existing .hecc file/make a new one,
      and then open OH-HECC/HECC-IT.
    * [ChooseFile.form](./ChooseFile.form) is a GUI designer file used for this class.
      
* [Heccable](./Heccable.java)
    * An interface for objects that can be converted to .hecc code. Has a
      `String toHecc()` method for that purpose.
      
* [Parseable](./Parseable.java)
    * yeah it just holds some regexes for passage names
    
* [/game_parts](./game_parts)
    * The various passage/metadata/general game data classes used within OH-HECC.
    
* [/mvc](./mvc)
    * The model-view-controller classes used within OH-HECC