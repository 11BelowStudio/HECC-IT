# 'wishlist' of features for the hypertext game tool

Names and such for components
* Entirely text-based format for making games
    * HECC
        * Hypertext Editing and Creation Code
        * Defined in the HECC-SPECC
            * HECC Super Precise Explanation for Creating Code
* The parser for HECC to HTML
    * HECC-UP
        * HECC Uncomplicated Parser (for MVP)
        * HECC Ultra Parser (for finished project)
* JavaScript runtime engine stuff
    * HECCER
        * HECC Environment for Runtime
* GUI program
    * OH-HECC
        * Optional Help with HECC
        * can read and produce HECC
            * Does it via a GUI so people don't need to write HECC themselves
        * includes HECC-UP to parse produced HECC

##The bare minimum for the MVP
###Tool (HECC-UP)
* You write it in HECC (v0.1)
    * Hypertext Editing and Compilation Code (v0.1)
    * Parsed via HECC-UP (v0.1)
        * HECC Uncomplicated Parser (v0.1)
    * Output is 
* Entirely written, parsed from a .txt file to a .html file (similar to .ink, Squiffy, Twee2)
* Might use similar passage declaration syntax to Twee2
    * ::PassageName
* Similar linking syntax to Twee2/Squiffy
    * [[Link to passage]]
    * [[Link text|Link to passage]]
* Everything underneath a passage declaration: content for that passage
    * Probably going to be put into a `<p></p>` tag (with `<br/>` tags at newlines)
* Might just opt to refrain from variables, saving in-progress games, and such for the time being
    * Just focusing on the parsing and such
* Tool will ask user for a folder to output the exported game into
    * index.html (main html page)
    * HECCER.js (HECC engine)
        * HECC Environment for Runtime
    * style.css (CSS)

###The output
* .html format (playable via browser)
* Probably going to try to keep all gameplay within the same .html page
    * Use JavaScript to update the inner HTML of the page body in response to users clicking the passage links
* Will need to create a JavaScript engine (HECCER.js)
    * Load passages
        * Need to decide how the output passages will be saved
            * Maybe have them in custom HTML tags in the HTML page (like Twine/Undum)
        * Probably could pre-process the contents into HTML content when parsing the code into the output
    * Display passages
        * Passages displayed within some innerHTML stuff
            * Contents of innerHTML replaced when a new passage has to be displayed
        * Display a defined start passage when user loads the game
        * Display a passage when the user navigates to the passage (by clicking a passage link)
    * Could potentially start including a user history stack within the MVP
        * Going to new passage: record of being in that passage pushed onto the stack
        * Allowing users to go back by popping stuff off the top of the stack
        * Should make it easier to actually implement saving games, variables, and guard conditions later on
* The HTML page
    * Probably going to make this 'index.html'
    * Define a div or something in the body which will be used to display the current passage
        * `document.getElementByID("This element displays the passsages").innerHTML = document.getElementByID("passage that needs to be displayed").innerHTML;` or something to change which passage is currently being displayed.
    * Might need to look into custom HTML elements (and making sure they dont get displayed to the user or anything, but remain accessible to any JavaScript code)
* CSS
    * make sure stuff looks presentable I guess
        * Could get away with having it declared inline within the HTML page

##Necessary stuff
### The tool
* Must be able to write 'passages' containing content for the story
* Must have 'links' between the 'passages'
* Must be able to parse and export the game in a playable format
* Might stick to an entirely written (non-GUI) format for the MVP

### The outputs
* Passages must be displayed to the user
* The links between the passages must also be displayed to the user
* A user must be able to use a link to navigate between two distinct passages
* Should be playable via web (least work required for running output, and for playing output)

##Desirable stuff
### The tool
* A GUI of some description
    * Saving/loading WIP stuff
    * Likely to take a similar form as Twine
    * Probably built off a similar MVC architecture as a bunch of the games I've made this year
        * Need to work out how to do viewport scrolling/support resizing
        * And also saving the state of the model
    * Overview of connected nodes
    * Can click on a node to edit it
        * Swing dialog box
    * Menu bar of some description
    * Maybe also a 'string of pearls' node overview
* Compliance with the Treaty of Babel
    * Generate a UUID as an IFID
    * Metadata
        * Author name
        * Game title
        * etc
* Some form of automatic debugging (ensuring that there's no infinite loops or something like that)
* Tags for passages (for ease of organization)

### The outputs
* Some method of tracking user history (sequence of visited passages)
* Saving/loading games in progress
* Keeping the entire gameplay within a single .html page (updating the content of that page with each passage instead of going between pages)
    * Could opt to keep all the JavaScript/CSS needed within that page (like twine), or 'include' it from external files within the same directory as that HTML page (like squiffy/undum)
* 

###Cool bits of additional functionality (for both ends)
* Variables
    * Some way of declaring them
    * Some way of actually using them
        * Conditional statements
        * Updating them
        * Outputting them to the user
    * Type validation (maybe)
* Having 'passages' and 'sub-passages'
    * 'sub-passage' as sub-sections of a main 'passage'
        * clicking on a 'sub-passage' link adds additional content to the main 'passage' stuff currently being displayed
        * any visible sub-passages disappear along with parent passage when navigating to a different passage.
* Guard conditions
    * Might fall under the umbrella category of 'conditional statements', but it would be nice to have some method of ensuring people can only go to a given passage if they satisfy a particular condition.
* Options for saving/loading
    * Maybe could allow the player to save/load whenever
    * Or I could allow the writer to override this, banning the player from saving/loading whenever, and force saves/loads at specific points (or just not save at all).
* Timers
    * Allow writer to
        * create a timer of sorts (maybe allow them to define 'code' that will fire every *x* milliseconds or something)
        * a method of 'stopping' the timer
            * Command to stop it
            * Automatically stop the automatically-firing code when changing passages as well
* Formatting
    * Content input
        * Allow users to write in plaintext/markdown (to convert to HTML at runtime)?
        * Allow users to write in HTML?
            * Could allow users to insert HTML elements in the plaintext/markdown input
    * Content formatting
        * Allow user-defined CSS?
    * Images
        * Could include some ease-of-use things for images (in case people don't want to deal with defining it via HTML)
            * Something that allows a user to reference an image file in an 'images' sub-folder of the folder containing the output game
            * Something that allows a user to reference an image via URL
            * A dedicated CSS page for base64-encoded images, ability for images to be easily referenced from it, with some functionality for the images to be encoded here automatically
                * User provides an image and an identifier for it
                * A ```.identifier{}``` CSS element is automatically added to base64 CSS, similar to https://twinery.org/cookbook/images/harlowe/harlowe_images.html
                * That image can then be easily referenced elsewhere
                * Might need to also implement a method for easily previewing/removing these base64 encoded images
    * Customizing how stuff gets displayed to the user
        * Defining special animations when transitioning between certain nodes?
        * Special per-node CSS options?
            * Maybe consider implementing an option for some form of special per-passage CSS stuff?