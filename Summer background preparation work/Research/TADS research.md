Text Adventure Development System

##how it works
* Upon closer inspection, it looks like this is actually a text adventure game creation tool (zork etc), not a hypertext game tool
    * but I'll still try to find out what I can anyway
* Object-oriented, code-based tool
    * User basically declares the objects in the game (object type, attributes, methods, etc)
        * Premade object types simplify the process somewhat
    * User only really needs to worry about the single .t file that all the objects and such for the game are supposed to be declared in.
    * Advanced users can tinker around with the source code (of the premade adv3 library) that supports the main .t file if they want to
* Output game is compiled and run on a virtual machine

##how to use it
* Creating a project [1]
    * 1: give a name/folder location for project
        * dialog box before anything else happens prompting you for this
    * 2: select project type
        * 5 options
            * Adv3 introductory/advanced
                * creating a game that you can download and play on a computer using the tads3 runtime stuff
            * WebUI introductory/advanced
                * same as previous ones, but is intended to be deployed on a web server instead (and can then be played via a browser)
            * Plain T3
                * no interactive fiction libraries, for advanced users who want to do their own thing.
    * 3: bibliography
        * enter a title and description (blurb) for the project, along with an author name/email. stored in game file, can be edited later, basically metadata for archiving/attributions/etc.
* Actually using it [1],[3]
    * Object-oriented system
    * There is a .t source file which you write objects and such in
        * Defines the character set first
            * ```#charset "us-ascii```
        * Then includes the header files for the libraries (adv3.h) and language (en_us.h) being used
            * Same syntax as C++ here
            * ```#include <adv3.h>```
        * Defines a ```GameID``` object (called 'versionInfo')
            * Holds identifying information for the game
            * metadata etc
            * stuff that was initially defined in the bibliography dialog box when setting up the project
        * Defines the objects and such being used in the game
            * Syntax for declarations
                * Can use a template syntax for defining objects
                    * ```objectName: Class 'the name of the object' "object description"```
                    * other values etc held by the object are defined in the text below it
                * Can initialise attributes of objects individually
                    * ```
                        objectName: Class
                            attribute = initialValue
                            otherAttribute = valueForThatAttribute
                            (etc)
                        ;
                * methods for objects can be defined
                    * ```
                        methodName()
                        {
                            [method body goes here]
                        }
                    * Some methods can be called for when a player attempts to perform a certain action on the object (via a DirectObjectHandler)
                        * ```
                            dobjFor(ActionName)
                            {
                                action(){ [method body];}
                            }
                * definition of the object ends with a semicolon.
            * Main types of objects
                * ```Room```
                    * Basically locations
                    * Can have other rooms connected to them in certain directions
                        * compass directions, above/below, ship directions
                        * ```north = otherRoom```
                    * Can have other things within them
                * ```Thing```
                    * Represents physical objects
                    * They are present in rooms
                        * Can declare them immediately after the room they're supposed to appear in (implcitly in the room)
                            * ```+thingName: Thing [other constructor stuff]```
                        * Can declare them as distinct objects, with the room they're supposed to appear in explicitly declared
                            * ```+thingName: Thing 'words that can be used to refer to it' 'thing name' @thingLocation "description of the thing"```
                        * Can override methods of these to allow certain actions to be performed on these
                    * Many pre-defined subclasses of this exist for objects such as 'Actors' (NPCs), keys, buttons, 'fixtures', etc
                        * ```Room``` is actually a subclass of ```Thing```
        * Defines the ```Actor``` that the player plays as
            * Any Actor can serve as a player character (but the actor being used as a player character is defined shortly)
            * Need to ensure that the initial location of this actor is set to where the player is supposed to start.
                * ```location = nameOfRoomWhereThePlayerStarts```
            * This is also where you would define the starting state of the player character as well.
    * Defines the ```gameMain: GameMainDef``` object (for game startup procedure)
        * Defines initial player character
            * suppose the player character is the Actor 'me', this would be defined as
                * ```initialPlayerChar = me```
            * Defines an introductory message + 'goodbye' message
                * ```showIntro(){ "intro message";} showGoodbye(){"quit game message";}```



##what features it has
* Can save/resume games
* User input for commands when playing
    * User writes in command, parsed and executed by the T3 VM
* Users don't need to use the premade adv3 library included in TADS if they don't want to
    * Can write and use their own libraries for it instead
        * These libraries are included in the output they built, so it can still be played by someone else via the T3 VM, without needing a copy of that custom library.

##what features it's missing
* Styling and such
    * Can use some inline HTML styling for the text
* Not actually a hypertext game tool
    * text adventure tool (zork etc) instead
* You require the T3 Virtual Machine (in some form) in order to play things produced by this
    * There is an option to make a TADS game playable on the web, but it requires a server-side implementation of the T3 VM to be active in order to run.

##how the interior logic and such works in the outputs it produces 
* I tried opening up the outputs (both the .t3 game and the .t3v save file) in notepad++ to see if I could find anything interesting, but it was all somewhat incomprehensible, so I'll just assume those are just raw binaries or something.
* Runs on a 'T3 Virtual Machine'[4]
    * Somewhat similar to JVM, but has features that JVM lacks (undo, save/restore, runtime typing, etc)
        * Java object model different to TADS object model
    * Written in C++
* TADS object model
    * ' A TADS object is a set of property/value pairs, where each value can be either a primitive type or code, and inheritance through zero or more superclasses. A new property can be added to an object dynamically. All properties are "virtual" (in the C++ sense) in that an object can override an inherited setting of a property simply by defining its own setting.'[4]
    


##sources etc
* [1] *TADS 3* (2012), M. J. Roberts. Accessed: Aug 4, 2020. [Online]. Available: https://www.tads.org/
* [2] M. J. Roberts. "TADS - the Text Adventure Development System, an Interactive Fiction authoring tool" tads.org https://www.tads.org/ (accessed Aug 4, 2020)
* [3] M. J. Roberts, *TADS 3 Library Reference Manual (version 3.1.3)* (2013) [Online] Accessed: Aug 4, 2020. Available: https://www.tads.org/t3doc/doc/libref/index.html
* [4] S. Breslin, E. Eve, M. Nizette, M. Roberts, A. Sewe, M. J. Roberts. *TADS 3 Technical Manual* (2006) [Online] Accessed: Aug 4, 2020. Available: https://www.tads.org/t3doc/doc/techman/toc.htm