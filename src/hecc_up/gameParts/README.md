# src/hecc_up/gameParts/

These are some of the classes used by HECC-UP to represent the game.

I will admit that yes, there is some coupling with the classes in
[OH-HECC's game parts package](../../oh_hecc/game_parts), partially because both HECC-UP
and OH-HECC need to parse the same .hecc files into a format they understand, so it made
sense to minimize code duplication.

Ideally, all of these classes should be rendered 100% obsolete, with both HECC-UP and
OH-HECC having the same shared interface for reading and parsing the .hecc file.

But, until then, we're dealing with some awkward coupling and such.

## The classes and such

* [Metadata](./Metadata.java)
    * Holds metadata about the current HECCIN' Game.
        * Everything before the first passage declaration.
    * Implements [FolderOutputterMetadataInterface](../FolderOutputterMetadataInterface.java), 
      so the FolderOutputter only has a lighterweight version of this to worry about.
      
    * Uses some methods from [MetadataReadingInterface](../../oh_hecc/game_parts/metadata/MetadataReadingInterface.java)
      to help with the parsing and such. 
      
* [Variable](./Variable.java)
    * I wanted to add user-defined variables to HECC-IT, which could be used by the
      author of a HECCIN' Game to be able to keep track of certain values and such,
      and have the state stack of the heccer.js keep track of a dictionary of what the
      value of each variable was at that particular point in the gamestate.
      
    * Unfortunately, I didn't get around to implementing that.
    
    * However, the Variable class was implemented in HECC-UP already, just in case.
    
    * Basically just stores the variable names, default values, and optional trailing comments.
    
* [VariableTests](./VariableTests.java)
    * Some unit tests for the Variable class.
    

* [Passage](./Passage.java)
    * `@Deprecated`
    * It's been replaced with [OutputtablePassage](../../oh_hecc/game_parts/passage/OutputtablePassage.java),
      which HeccParser accesses via the
      [OutputtablePassage interface](../../oh_hecc/game_parts/passage/OutputtablePassageInterface.java).