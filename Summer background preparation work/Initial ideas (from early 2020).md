
HYPERTEXT GAME

* write a tool for the creation of hypertext games, then use it to create one.
    * tool: PC
    * game: any platform

* concepts
    * output game in the form of a text file
        * special characters on own line used to split individual fields
        * these special characters would be forbidden from the input
            * regex to remove them from input when writing the game
        * each 'section' of game starts with an identifier after node start line
        * may need to declare the number of options, probably have a limit of 4 or so
        * each 'option' refers to another node
        * would be stored individually as objects after being read
            * could reverse-engineer my ScoreRecord/HighScore code from ce203?
        * need to store strings in UTF-8
            * allows other languages to be used
        * needs to be able to handle strings of varying lengths without it becoming Weird(tm)
            * further support for other languages
        * localization files for UI stuff?
    
    * maybe also a tree-like display for the structure of the game
        * leaf nodes; end
        * would probably work more like a multiple linked list though
            * each node has list of child nodes
            * each node can have multiple parents
                * parents store children, not vice versa
        * probably lay out based on deepest possible depth of the node from the top
            * node with a 5-parent path and 2-parent path: shown on 5 parent depth
            ```
                ┌─N──N──N──N──N──┐
                P                E
                └─N──N───────────┘
            (note to self: markdown doesn't support ascii art)
            ```
             ^ basic idea of what I'm trying to acheive with that
    * preventing weirdness with the 'compiler'
        * view paths from start node to end nodes
            * complain to user if path links back to previously traversed node in current path
                * stopping loops
            * many depth first searches
                * record depths of each search
        * ensure that the nodes actually link up
            * complain to the user if a node is never reached by the search
            * complain to the user if a node refers to a child that doesn't exist
        * ensure that the ends are actually ends
    * playing the game
        * record of the visited nodes, probably as arrayList
            * exportable as .txt file(?)
        * handling compilation errors when playing the game
            * maybe remove options to invalid nodes?
            * replace invalid nodes with a generic 'well, we ran out of text here, so I guess this is the end' ending node(?)
            * visited nodes .txt is not valid path; restart user's progress(?)
        * export the game as html pages I guess
            * pretty much make a template, and put strings into the template bits
            * GET requests containing list of already visited nodes, in order; effectively a stack
                * URL would work as a save file in that case



* stretch goals
    * add text to speech for the output
        * option to mute it
        * could also try to make a phonetic text to speech thing myself?
            * might sound kinda bad though ngl
            * would be nice proof of concept for a version with an actual voice actor
    * speech to text for when the player is playing the game
        * player says name of the choice, could have options for special input words which can also be recognized as a particular option
        * option to disable it
    * maybe an option for audio files?
        * audio clips for a 'page' would be loaded with the page itself
        * audio would be loaded instead of the text-to-speech


IMPLEMENTATION
* Implementation ideas:

* intermediate declarative language for saving WIP stuff:
    * HECC (Hypertext Editing and Creation Code)
    * delcaring properties of the 'nodes' basically
    * parsed into output of a folder of .html pages containing the hypertext game
        * index.html is simple redirect to the defined 'START' node
    * central javascript 
    * 'guard' conditions
        * Probably could have 'REQUIRE' and 'PREVENT' modifiers for nodes
            * 'REQUIRE' condition
                * stack MUST hold the listed 'REQUIRE' nodes 
                    * REQUIRE(A) = A must be present (TRUE if A present)
            * 'PREVENT' condition
                * stack MUST NOT hold listed 'PREVENT' nodes
                    * PREVENT(A) = A must NOT be present (FALSE if A present)
                * Can be used as a boolean NOT
        * Boolean logic?
            * Lists of nodes
                * REQUIRE lists are 'AND' by default (all required)
                    * REQUIRE(A,B,C)
                        * A ^ B ^ C
                * PREVENT lists are 'NOT' by default (prevents any)
                    * PREVENT(D,E,F)
                        * !D v !E v !F
            * Can write 'AND', 'OR' (with brackets around them) for more complex sessions
        * Might auto-PREVENT options leading to nodes already visited
            * ALLOW-REVISIT guard condition can be declared, to ignore the default 'PREVENT(this)' conditions


* keeping track of path taken
    * stack with GET requests of the node page IDs
        * 'back' button: move to page at top of stack, current page popped off top of stack
        * select node: move to that page, current popped to top of stack
        * example
            On page Z, after going to pages X then Y:
                Stack holds X and Y
                Go to page W:
                    Z pushed to stack, now holds X,Y,Z
                Go back from page Z:
                    Moved to page Y (top of stack), Y popped from stack, stack holds X.


* Create template HTML/JavaScript page first

* Define the intermediate declarative language
    * HECC (Hypertext Editing and Creation Code)
        * Probably just going to be an XML schema in practice

* Create parser for intermediate to HTML output
    * HECC-UP (Hypertext Editing and Creation Code Ultra Parser)

* Create GUI program to create intermediate files (which could then be parsed and output)
    * HECCER (Hypertext Editing and Creation Code Editing Resource)
    * Need to ensure that it will output intermediate files in valid HECC syntax

* Can work on exporting to different formats later on
    
		
