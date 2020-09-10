
# HECC-IT (Hypertext Editing and Creation Code - Integrated Toolkit)

* A toolkit which can be used to create Hypertext games

## Why am I doing this project?

* 

## Why am I doing this project now?

* 

## What can I bring to the table that nobody else has brought to it?

* Should accommodate power users as well as casual users
    * **How HECC-IT does this**
        * Power users can simply write a .hecc file themselves, and then use HECC-UP to build their HECCIN Game
        * Casual users can use the OH-HECC GUI utility to produce their .hecc file in a more user-friendly format, before giving that .hecc file to HECC-UP to build their HECCIN Game
        * And it will be possible for a user to write part of it in raw .hecc, part of it via OH-HECC, without being limited to one or the other
    * **How this compares to existing hypertext game tools**
        * A combination of *Twine* and *twee2*
            * twee2 accommodates power users (write raw twee2 code, command line utility to make it a twine game), Twine accommodates casual users (GUI overview)
                * But those are two completely distinct programs, so, if you want to try the other one, you'd need to go out of your way to get the other one.
                * Whilst twee2 can output a Twine game, which you can then play or edit further with Twine, it's complicated trying to convert from Twine to twee2 (making it unfeasible to use a combination of Twine and twee2 to make a game)
                    * Sure, twee2 does have some functionality for decompiling a Twine game into twee2 code.
                        * However, this functionality doesn't work on Windows.
                        * So, if you are on Windows, you don't have the flexibility to swap between Twine and twee2 when making your game.
            * Problems with it that HECC-IT doesn't have
                * Can't really use both Twee2 and Twine to make a game (at least on Windows)
                    * *How HECC-IT avoids this issue*
                        * OH-HECC reads/produces .hecc code, and .hecc code can be directly edited by hand, no decompilation needed between the OH-HECC GUI and the raw hecc code. Compilation only happens at the end (via HECC-UP).
                * Additional barrier to entry with Twee2, as, if you want to use Twee2 stuff, you need to go out of your way to download Twee2.
                    * *How HECC-IT avoids this issue*
                        * HECC-IT consists of OH-HECC and HECC-UP. OH-HECC produces .hecc code. HECC-UP converts .hecc code into a HECCIN Game. And there's nothing to stop you from just writing the .hecc code by hand before giving it to HECC-UP (no additional software needed to allow you to power-user it). 
        * A combination of *inklewriter* and *inky*
            * Inklewriter is a browser-based graphical hypertext game creation tool, inky is an executable IDE for writing 'ink' files (writing raw .ink code).
                * Both export into basically the same game format (but inklewriter is saved in the cloud, inky is saved locally)
                * There are ways to export/import between Inky and Inklewriter
                    * Can export from Inklewriter into raw .ink code, or a JSON representation of the game
                        * Raw .ink code can be pasted into inky directly to allow you to continue writing the game locally
                        * the JSON representation can only be imported by Inklewriter to make a copy of the game
                    * Can export from Inky into JSON representation
                        * Can then import that into Inklewriter, to get an Inklewriter copy of the game
            * Shortcomings of this approach, which mine doesn't have
                * You can only use Inklewriter via the Inklewriter website (and you need to make an Inklewriter account in order to save/export your game)
                    * If you don't have an active internet connection, or you don't want to make an Inklewriter account, you can't use the Inklewriter GUI.
                    * *How HECC-IT avoids this problem*
                        * you download the HECC-IT tools and then you can use them. none of this serverside account faff. you download it and you use it. simple.
                * Transferring games between Inklewriter and Ink is a bit of a pain
                    * *How to transfer data between those products*
                        * Inklewriter to ink: Need to make an account, press share, click on the '.ink format' link, copy the exported .ink code, open Inky, paste the exported .ink code into the .ink file, hope that everything works.
                        * .ink to Inklewriter: Need to go to 'file->export to JSON', save the .JSON file, open the .JSON file, copy the contents of the .JSON file, go to Inklewriter, log in, press 'Import', paste the JSON, press 'import story', then you're finally done.
                    * *How HECC-IT avoids this problem*
                        * If you want to write raw HECC, just open the .hecc file in a text editor, and keep saving your work as you go along in the .hecc file.
                        * If you want to use a GUI, open OH-HECC, use that to open the .hecc file, and any changes you make to the game will be saved in the .hecc file.
                        * No need to convert to a different intermediate format between editors, because .hecc itself *is* an intermediate format.
        * Pretty much all the others are either exclusively a GUI, or exclusively code-based.
            * The others
                * Code/IDE only
                    * ChoiceScript
                    * Inform
                    * Quest
                    * Squiffy
                    * TADS
                    * Undum
                * GUI only
                    * eHyperTool
                    * storyspace
            * HECC-IT gives you a choice of GUI (OH-HECC) or code (raw .hecc code)
* 

## What are the project objectives?

* Create the full HECC-IT toolkit
    * Define the 'HECC' language
        * Hypertext Editing and Creation Code
    * Create the 'HECC-UP' parser
        * HECC Ultra Parser
        * Takes HECC code as input
        * Outputs a HECCIN Game with that HECC code
    * Create the 'HECCIN Game'
        * HECC Infused Nice Game
        * Components
            * index.html
            * HECCER.js
            * HECCED.js
        * Components that need to be premade
            * index.html
                * Essentially the interface for the game (allows player to, y'know, play it)
            * HECCER.js
                * HECC Environment for Runtime
                * This is basically the engine
                * Content of index.html is manipulated by the HECCER.
        * Components produced by HECC-UP
            * HECCED.js
                * HECC Exported Data
                * Essentially holds the contents of the game
                * Constructed by HECC-UP, consider it as the 'HECCED' version of the raw .hecc file.
                * Feeds the game contents into the HECCER
    * Create the 'OH-HECC' utility
        * Optional Help for HECC
        * GUI utility which can be used to produce .hecc code, without needing to touch any raw .hecc code.
            * Can load .hecc files to edit them further
            * Saves data in .hecc format

## What technical achievements will I aim to achieve during Challenge Week?

* 

## What is the management plan for this project?

* 