#What the MVP will consist of

##Components
* HECC (Hypertext Editing and Creation Code) language
    * Follows the HECC-SPECC v0.1
    * Parsed via HECC-UP into a playable hypertext game.
* HECC-UP (HECC Uncomplicated Parser)
    * Inputs
        * A .txt file written in HECC
        * A name for the folder that the game will be exported into
    * Outputs
        * A folder, with the given name, containing the pre-written HECC game components, as well as a HECCED.js file parsed from the HECC file.
* The hypertext game
    * `index.html`
        * Game content shall be displayed entirely within this page
        * Must include the other files that make up the finished HECC game
    * `HECCER.js` (HECC Environment for Runtime)
        * Handles the execution of the HECC game that the player is playing
        * Effectively a game library, not the game itself (similar to how Undum works)
            * Defines a 'HECCER' class
                * Holds Passages
                * basically the top-level controlling thing
            * Defines a 'Passage' class
                * holds data about each 'Passage' in the game
                * Also defines behaviour of the passages
                * declarations for this are in HECCED.js
            * Defines a 'GameState' class
                * Will just hold a string identifier for the current passage
                * A stack of GameState objects will be held in browser sessionstorage
    * `HECCED.js` (HECC Exported Data)
        * Declarations/Constructors for the 'Passage' objects that make up the game.
        * Would also include any metadata stuff
        * Passage contents will have been pre-processed into HTML by HECC-UP already
    * *jQuery*
        * It appears that every other hypertext game engine that keeps all the action in a single html page uses jQuery in order to work, so I guess I might need to include jQuery as well.
        * Used for
            * Event handling
                * Handling the passage change when a passage link is clicked
            * HTML DOM traversal/manipulation
                * Getting to the HTML div that the current passage is supposed to be held in
                * Actually changing what passage is being displayed
            * Animations
                * Transitions between passages (maybe)
    * `style.css`
        * Styling for index.html to make the game look presentable

###How each part of the MVP output is generated
* `HECC-UP` shall be a Java program (mainly because I'm most used to writing stuff in Java), probably in .jar form
    * Most of the output files shall be pre-written, baked inside the .jar, and copies of these shall be created and exported
        * The pre-written ones
            * index.html
            * HECCER.js
            * jQuery (if used)
            * style.css (unless included inline within index.html)
        * How they'll be pre-written
            * Include a folder containing them within the `src` folder
            * basically recycle the `TextAssetReader.java` class I used for Inconvenient Space Rocks/skyjump/The Button Factory/2
                * to read them
                * and to get them statically referenced (so they're included within the `HECC-UP.jar`)
            * When exporting the HECC game, basically copy those into the output folder
        * Changes which may be made in the final product
            * index.html may be partially dynamically-generated
                * probably allowing user defined metadata in the `<head>` or something
            * style.css might also be partially dynamically-generated
                * Allowing user-defined styling to replace/come underneath the premade CSS
    * `HECCED.js` shall be dynamically generated
        * `HECC-UP` will parse the HECC code into `HECCER` Passage declarations
            * The passage contents will also be pre-parsed into valid HTML as well, so they're easier to display
        * These parsed passage declarations are then used to fill in `HECCED.js` basically
        
##Functionality
* Gameplay is contained within a single .html page
    * Page content is changed as the player goes between 'passages'
* Game consists of 'passages'
    * Player navigates to other passages by clicking 'links' to the other passages within the current passage
        * Link is within passage content, clicking it changes the page content to display a different passage
        * If a passage doesn't contain a link to another passage, that passage is implicitly an 'ending'
    * Passages shall be held in key/value pairs
        * Passage name string is the key, passage contents are the value
* There shall be an option to allow a player to go 'back' a passage
    * A stack of 'GameState' objects (`gameStateStack`) will be held in browser sessionstorage to contain player history
        * Stictly speaking, it would be a JavaScript Array object, but it would be used like a Stack.
        * The topmost GameState will be the current state of the game (the identifier of the current passage that the player's at)
        * Going 'back' will cause the topmost GameState to be popped off the `gameStateStack`
        * You may not go 'back' if there is nothing below the current GameState within the `gameStateStack`
* How the passage transitions will be implemented
    * Basic idea
        * A new GameState is pushed to/the top GameState is popped from the `gameStateStack`
        * `HECCER` shall inspect the new top GameState in `gameStateStack`
        * The passage that GameState refers to shall be obtained from the Passages defined in `HECCED.js`
        * That passage will replace the currently displayed passage
    * Starting the game
        * Once all the passage objects have been loaded, a GameState object (referring to the `Start` passage) shall be constructed
        * This `Start` GameState shall be pushed to the `gameStateStack`
        * `HECCER` shall then be instructed to load the passage that the topmost GameState refers to (so it loads `Start`)
    * Clicking a link to passage `X`
        * The link text would have some form of `onclick()` event handler, handling the passage change
        * The passage that the link refers to (`X`) shall be 
        * a new GameState object, referring to passage `X`, will be created
        * This `X` GameState shall be pushed to the `gameStateStack`
        * `HECCER` shall be notified of the change to the GameStates, and shall then load the passage referred to by the topmost GameState
    * Clicking the 'back' button
        * Allowing the 'back' button to appear
            * `HECCER` shall check the size of the `gameStateStack` whenever it changes the currently loaded passage
            * If the size is greater than 1, the back button shall appear, and be active.
                * Otherwise, the back button shall be hidden, and inactive
                * There shall be a boolean `canGoBack` variable in `HECCER` which defines whether or not the back button is active (true if active, false if inactive), which will have its value set appropriately by this
        * The back button would also have some form of an `onclick()` event handler, similar to the links
        * When the 'back' button is clicked
            * It will first check that the back button is active (by querying `HECCER.canGoBack`), only proceeding if true
            * If the back button is active
                * Pops the top GameState (the one with the current passage) from the `gameStateStack`
                * Instructs `HECCER` to query the topmost GameState on the `gameStateStack`.
                * The Passage referred to by that GameState is now loaded, and displayed to the user (replacing the current passage).

###I know you're about to ask 'why is the GameState stack stuff to allow a user to go back being included in the MVP when it isn't exactly necessary for an MVP?', so I'll answer that question now.
* Basically, it's being included in the MVP to make other things easier to implement, both now and later on
    * Stuff that it makes easier to implement now
        * Trying to load a passage
            * `HECCER` just needs to refer to the top GameState on the stack, and load the Passage which that GameState refers to
                * The Passages would basically be kept in Key/Value pairs, so the Passage objects may be easily obtained via their String identifiers
            * Also, it means that only a single 'load passage' method would be needed for `HECCER`, as all the passages would be loaded by reading the top GameState
                * Just change the stack, then tell `HECCER` to look at the stack
                    * Loading a link involves passing the passage identifier held by the link to a GameState constructor, putting that on the stack, then telling `HECCER` to look at the stack.
                    * Going back involves popping the top item from the stack, then telling `HECCER` to look at the stack.
        * Starting the game
            * I just initialise the stack to have a GameState with the `Start` passage on it, and then tell `HECCER` to look at the stack.
        * **tl;dr** In all cases, `HECCER` will load passages by looking at the GameState stack, all I need to do is modify the stack then tell `HECCER` to look at it.
    * Stuff that will be easier to implement later
        * Saving/loading
            * I'd basically just need to copy the gamestate stack from sessionstorage to localstorage in order to save the game.
            * Loading the game would simply involve copying the saved stack from localstorage (if it exists in localstorage) to sessionstorage
        * Variables
            * I could get away with the same thing that Twine does (variables are key/value declarations held in gamestates, setting a new value for a variable basically involves re-declaring it in the current gamestate, and referring to the topmost declaration for a variable when working out what value to use for it)
                * Would also mean that the values of variables would be reset appropriately when a user goes back a gamestate
                * Also means that no extra effort would be needed in terms of saving the variables along with the gamestates
        * I'd find myself having to record user history eventually anyway (so guard conditions requiring a user to have previously visited a certain passage would be possible)
            * So I may as well neatly implement it sooner, rather than reverse-engineering it in later.
            * Same thing with the back button
        * Users defining a start passage that isn't `Start`
            * Maybe change the constructor of the `HECCER` object to take an identifier string for a specified start passage as a parameter (defaulting to `Start`), so, if the user wanted to start at a passage that isn't called `Start`, the name of that other passage could be held by the starting GameState object.

##The game I'll be creating for the MVP, to demonstrate the functionality of the HECC software
* Probably just going to be a stupidly simple game (similar to the one in the HECC-SPECC v0.1), to demonstrate the functionality of the MVP HECC software
    * Game will be written in HECC
    * Parsed by HECC-UP
    * Output game will be playable
* You've heard of 'programmer art', now get ready for 'programmer literature'.
    * But it should still be a functional game, at very least.

##What I'll need to produce
* A game script written in HECC
* The `HECC-UP` parser
    * Converts HECC code into a `HECCED.js` file
    * outputs the `HECCED.js` file and the other pre-baked components into a specified directory.
* The HECC game components (to be baked into the `HECC-UP` parser)
    * index.html
    * style.css
    * The `HECCER.js` engine
    * A copy of jQuery (if used) would need to be baked into/output by the `HECC-UP` parser too
    
###What I need to do before challenge week
* Plan out how `HECCER.js` will work
    * Will I need to use jQuery?
* Plan out how `HECCED.js` will give the `HECCER` the parsed data

###Basic plan of attack for challenge week
* I need to make an index.html that `HECCER.js` can display content in
* I need to make the `HECCER.js` engine, and ensure I can pass the data I need to give it via `HECCED.js`
* I then need to work out how I'll put the data that `HECCER.js` needs within `HECCED.js`
    * Might make a hand-coded `HECCED.js` file, holding what the parser would output, to ensure that the engine works
* Then, I need to make the `HECC-UP` parser
    * Ensuring I bake copies of the index.html, HECCER.js, and (if used) jQuery files into the parser's src/
* Finally, produce the `HECC` game I'll feed into the parser
    * Might just use the sample `HECC` code present in the `HECC-SPEC v0.1.md` document
* Feed that HECC code into the parser, get the HECC game, and then make sure the HECC game works