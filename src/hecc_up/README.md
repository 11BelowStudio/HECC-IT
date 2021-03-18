# **HECC-UP!**

This package contains the files and such that constitute HECC-UP (The HECC Ultra Parser).
Basically, the bits that read in a .hecc file, and convert them into a playable game.

## The classes and such here are:

* [FolderOutputter](./FolderOutputter.java)
    * Responsible for actually outputting the heccin' game into a folder.
    
* [FolderOutputterMetadataInterface](./FolderOutputterMetadataInterface.java)
    * Interface implemented by ./gameParts/Metadata.java, this just provides access to 
      the important bits that the FolderOutputter actually needs access to.
      
* [HeccParser](./HeccParser.java)
    * Responsible for parsing the .hecc file into a map of
      [OutputtablePassage](../oh_hecc/game_parts/passage/OutputtablePassage.java)s and a
      [Metadata](./gameParts/Metadata.java) object.
    * It does this via some overuse of regex, or it might have been abuse.
        * This was first written in August 2020, but has recieved substantial
          improvements since then, such as making it object-oriented for easier
          maintainability, some better comments in it, etc.
          
    * Basically it's given a string of the contents of a .hecc file, and turns it into a
      format that the FolderOutputter can actually output it in.
      
* [HeccUpGUI](./HeccUpGUI.java)
    * The GUI for HECC-UP.
    * It uses Swing for the GUI, and was written by hand, so all of the code that sets 
      up the layout is in there.
      
    * What it does
        * Asks users for a .hecc file (can choose via a JFileChooser), and for 
          a folder to output the HECCIN' Game described by that .hecc file to.
          
        * Calls the HeccUpHandler to convert that .hecc file into a HECCIN' Game.
          
        * If there's a problem with the .hecc file, it has a 'console' where the warning 
          can be logged and read by the user.
          
        * If processed successfully, it offers the user the option to just open the
          index.html of their HECCIN' Game in their browser.
          
    * Usage
        * Initial plan
            * User launches main of HeccUpGUI, manually selects .hecc file and folder,
              then attempts to output it.
              
        * Current usage
            * User selects .hecc file for HECC-IT, may open it in HECC-UP instantly or
              after editing it a bit with OH-HECC.
              
            * .hecc file pre-selected, and a folder within same directory as .hecc file
              is also pre-selected.
              
            * Unless user wants to change the output folder location, they just need to
              press the 'HECC-IT!' button to output the game.
              
    * Implements LoggerInterface
        * There's a JTextArea in a JScrollPane at the bottom of the window.
          
        * Info is logged there, where the user can read it.
              
* [HeccUpHander](./HeccUpHandler.java)
    * This handles the behind-the-scenes stuff for HECC-UP.
        * Calls the HeccParser and such to parse the .hecc file, and also gets the
          FolderOutputter to actually output that parsed .hecc file.
          
        * If there's a problem, it'll tell the LoggerInterface object to, well, log it.
    
        * And if it works, that will be logged by the LoggerInterface object as well.
    
* [LoggerInterface](./LoggerInterface.java)
    * Logs stuff.
        * Has a method, `logInfo(String infoToLog)`, and implementing classes must
          log that given string of info to log, in some way, shape, or form.
    * Default implementation
        * Literally just prints it to console.
    
* ~~[OldHeccUpMain](./OldHeccUpMain.java)~~
    * `@Deprecated`.
    * Was used for checking if HECC-UP worked, by taking in a sample .hecc file
      and seeing what that would be output as.
      
    * 100% in console, 100% uninteractable, probably could just get rid of it tbh.
    

* [gameParts](./gameParts/)
    * Some of the classes and such representing the game data, used in HECC-UP.