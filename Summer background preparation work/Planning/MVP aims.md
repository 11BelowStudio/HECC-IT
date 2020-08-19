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
                * Allowing user-defined styling
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
* There shall be an option to allow a player to go 'back' a passage
    * A stack of 'GameState' objects (`gameStateStack`) will be held in browser sessionstorage to contain player history
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