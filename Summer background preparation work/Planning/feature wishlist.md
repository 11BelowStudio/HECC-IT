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
    * HECCER.js
        * HECC Environment for Runtime
            * Runs the HECC-made game in browser
    * HECCED.js
        * HECC Exported Data
            * The written HECC code is parsed into Passage objects, and the objects are held here.
* GUI program
    * OH-HECC
        * Optional Help with HECC
        * can read and produce HECC
            * Does it via a GUI so people don't need to write HECC themselves
        * includes HECC-UP to parse produced HECC
* The output game
    * HECCIN Game
        * HECC-Infused Nice Game
        * Yep, it's a HECCIN game!

## The bare minimum for the MVP
### Tool (HECC-UP)
* You write it in HECC (v0.1)
    * Hypertext Editing and Compilation Code (v0.1)
    * Parsed via HECC-UP (v0.1)
        * HECC Uncomplicated Parser (v0.1)
    * Output is basically a folder containing the important files for the game.
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

### The output
* .html format (playable via browser)
* Probably going to try to keep all gameplay within the same .html page
    * Use JavaScript to update the inner HTML of the page body in response to users clicking the passage links
        * **works**
* Will need to create a JavaScript engine (HECCER.js)
    * Load passages
        * Need to decide how the output passages will be saved
            * Maybe have them in custom HTML tags in the HTML page (like Twine/Undum)
            * Or I could have them in a game.js file, basically containing JavaScript objects for each passage (like Squiffy/Undum)
                * **put them in a 'HECCED.js' file, so yeah this works**
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
            * Saving games
                * **Save a stringified version of the gamestate stack in browser localstorage, parse that stringified version into a not-stringified version to load the game**
            * Variables
                * **Extend gamestate objects to have a dictionary or something of variable (re-)declarations, implement method of gamestatestack object to query most recent (re-)declaration when a value of a variable needs to be used (returning 0 if nothing found).**
                * **Also implement method to add (re-)declarations to topmost gamestate, in response to any code stuff**
            * Guard conditions
                * **Check gamestate stack for specified passage(s)/passages with specified tag(s)/values of specified variable(s), respond accordingly**
* The HTML page
    * Probably going to make this 'index.html'
    * Define a div or something in the body which will be used to display the current passage
        * `document.getElementByID("This element displays the passsages").innerHTML = document.getElementByID("passage that needs to be displayed").innerHTML;` or something to change which passage is currently being displayed.
    * Might need to look into custom HTML elements (and making sure they dont get displayed to the user or anything, but remain accessible to any JavaScript code)
* CSS
    * make sure stuff looks presentable I guess
        * Could get away with having it declared inline within the HTML page

## Necessary stuff
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

## Desirable stuff
### The tool
* A GUI of some description
    * Saving/loading WIP stuff
    * Likely to take a similar form as Twine
    * Probably built off a similar MVC architecture as a bunch of the games I've made this year
        * Need to work out how to do viewport scrolling/support resizing
        * And also saving the state of the model
            * **Get the raw HECC representation of the Passage objects, write that to the HECC file**
    * Overview of connected nodes
        * **Nodes could be connected with some sort of stretched triangle object, with the midpoint of the base at the midpoint of the source node, with the point of the triangle in the midpoint of the destination node**
        * **Would need to be rendered before the nodes**
    * Can click on a node to edit it
        * **`Controller implements MouseListener` class, obtains click location, checks to see if it intersects with the hitbox of a node, and, if it does, calls the method to open the edit dialog of that node**
        * Swing dialog box
            * **Method in Passage objects to allow them to get their name, tags, and content, construct a swing dialog box with those, then, when the dialog box is closed, the updated name/tags/content of these would replace the inner name/tags/content of them**
    * Menu bar of some description
        * **Would probably need to render this after everything else, each button would need to effectively be its own object, can't just use swing buttons with the MVC architecture stuff I've been using for my games so far**
    * Maybe also a 'string of pearls' node overview
        * **Rather unfeasible**
            * **Would first need to go from the start node, then make some sort of 'tree' for nodes that each node links to**
            * **Then, I'd need to work out sets of nodes that diverge from a given 'parent' and merge back together at a given 'child'**
            * **Then, find the positions of the nodes in these sets, and then create a circle or something to surround all of those nodes**
* Compliance with the Treaty of Babel
    * Generate a UUID as an IFID
        * **Java has a UUID class that can trivialise this**
    * Metadata
        * Author name
            * **Just ask user to enter their name**
        * Game title
            * **Just ask user to enter what their game is called**
        * etc
* Some form of automatic debugging (ensuring that there's no infinite loops or something like that)
* Tags for passages (for ease of organization)
    * **User can enter a list of strings (space delimited), converted into array/arrayList of strings, should make this relatively ez

### The outputs
* Some method of tracking user history (sequence of visited passages)
* Saving/loading games in progress
* Keeping the entire gameplay within a single .html page (updating the content of that page with each passage instead of going between pages)
    * Could opt to keep all the JavaScript/CSS needed within that page (like twine), or 'include' it from external files within the same directory as that HTML page (like squiffy/undum)
* 

### Cool bits of additional functionality (for both ends)
* Variables
    * Some way of declaring them
    * Some way of actually using them
        * Conditional statements
        * Updating them
        * Outputting them to the user
    * Type validation (maybe)
    * **Might need to do something like what Squiffy has with it's `squiffy.ui.processText: function(text){}` stuff, where, when a section/passage is loaded, it looks for any tokens ('{}' or '@' text), then processes them on-the-fly into what they're supposed to be** 
        * **I can also pass a function definition into a constructor as an argument (see testing etc/PassingFunctionDefinitionAsConstructorArgument.html), so I can pass a user-defined Javascript function into a Passage object that way.**
            * **However, I guess that the parsing-conditions-when-loading-a-passage stuff might be more effective, seeing as that means that the variables and such will have to be accessed on demand for the conditional statement, so I may as well do that whole thing processed on demand**
    * **Maybe require them all to be declared at the very start, with the other metadata**
        * **Some scope for automatic error detection by HECC-UP in regards to variables; complaining as soon as an undeclared variable is used**
            * **Author will also be able to tell how many/what variables their game has/uses, might help to keep track of them all**
        * **HECC-UP could construct a Metadata object, containing variable declarations as well, which would be passed to the HECCER by HECCED (along with the passages) at runtime**
            * **gamestate objects would also hold the values of all the variables at the current point in time (so it's easier to keep track of them)**
        * **less runtime weirdness**
* Having 'passages' and 'sub-passages'
    * 'sub-passage' as sub-sections of a main 'passage'
        * clicking on a 'sub-passage' link adds additional content to the main 'passage' stuff currently being displayed
        * any visible sub-passages disappear along with parent passage when navigating to a different passage.
    * **Might be awkward to implement into the gamestates thing, may be better off implementing 'label' text (changes when something else is clicked) or something like that instead.**
* Guard conditions
    * Might fall under the umbrella category of 'conditional statements', but it would be nice to have some method of ensuring people can only go to a given passage if they satisfy a particular condition.
        * **Yeah this is probably going to only be implemented with the conditional statements.**
            * **Variable-related conditionals are kinda unlikely to happen any time soon.**
            * **However, any sort of 'was passage X visited' conditionals are more likely to be implemented sooner**
* Options for saving/loading
    * Maybe could allow the player to save/load whenever
        * **This option would require a save/load panel thing to be added to the HTML page, which would need to be usable whenever.**
    * Or I could allow the writer to override this, banning the player from saving/loading whenever, and force saves/loads at specific points (or just not save at all).
        * **This option means there's less work on the user-facing end needed. However, I'd need to implement code to recognize when the forced saves/loads are called.**
    * **Would involve saving a stringified version of the gamestate stack in the browser localstorage, I guess**
        * **Might be worth identifying saves as 'IFID - Game title - Save file name'. IFID and Game Title read from metadata (or set to default values.) Save game name would be set in the 'save' commands.**
        * **Loading a game would find search for entry in localstorage with the appropriate key in the format 'IFID - Game title - Save file name'.**
            * **If something is found, the value would need to be parsed into a gamestatestack of gamestate objects, replacing the existing gamestatestack, and loading the appropriate passage**
            * **If nothing's found, no change would be made to the gamestatestack, but it should return something to indicate that the load operation was unsuccessful.**
* Timers
    * Allow writer to
        * create a timer of sorts (maybe allow them to define 'code' that will fire every *x* milliseconds or something)
        * a method of 'stopping' the timer
            * Command to stop it
            * Automatically stop the automatically-firing code when changing passages as well
        * **Rather unfeasible. Definitely a stretch goal or something**
* Formatting
    * Content input
        * Allow users to write in plaintext/markdown (to convert to HTML by HECC-UP)?
            * **Would add a bit of extra work for the parsing process, and would mean that I'd need to rework the line break stuff a bit (because markdown's line break stuff differs somewhat to the line break stuff I have in HECC rn), but shouldn't be too difficult.**
        * Allow users to write in HTML?
            * Could allow users to insert HTML elements in the plaintext/markdown input
                * **sure I guess**
    * Content formatting
        * Allow user-defined CSS?
            * **Users might need to define this in the metadata area for the game.**
                * **Or could allow users to define that a certain other file holds the CSS, which would then need to be read to obtain the CSS?**
            * **Would need to move CSS from being inline within index.html to being a different file first, to make it easier to add the user-defined CSS underneath the pre-defined CSS.**
            * **Might be allowed to define passages as belonging to these custom CSS classes?**
    * Images
        * Could include some ease-of-use things for images (in case people don't want to deal with defining it via HTML)
            * Something that allows a user to reference an image file in an 'images' sub-folder of the folder containing the output game
            * Something that allows a user to reference an image via URL
            * A dedicated CSS page for base64-encoded images, ability for images to be easily referenced from it, with some functionality for the images to be encoded here automatically
                * User provides an image and an identifier for it
                * A ```.identifier{}``` CSS element is automatically added to base64 CSS, similar to https://twinery.org/cookbook/images/harlowe/harlowe_images.html
                * That image can then be easily referenced elsewhere
                * Might need to also implement a method for easily previewing/removing these base64 encoded images
                * **This would probably need to be done via OH-HECC**
            * **I guess that, if I implement Markdown stuff, that could be used for the 'simplifying how to use images' stuff**
    * Customizing how stuff gets displayed to the user
        * Defining special animations when transitioning between certain nodes?
            * **Not sure how I would implement this. Maybe create a couple of new HTML objects with the old/new passage content, hide the 'real' passage stuff, animate the old/new things, then, once that animation's done, put the new passage content in the 'real' passagecontent, and hide the animated passagecontents?**
            * **Definitely a strech goal**
        * Special per-node CSS options?
            * Maybe consider implementing an option for some form of special per-passage CSS stuff?
                * **Might be better off just doing this in the 'user-defined CSS' stuff, by allowing passages to have CSS classes.**
* Some scope for note-taking
    * Sure, everything that isn't an instance of metadata which appears before the first passage declaration is technically a note (according to HECC-UP), but I'm not sure if these unofficial notes would survive OH-HECC.
    * **Allow 'notes' to be defined for a passage (probably appearing below the end of the content of that passage), ignored by HECC-UP, but allows author to take notes regarding a certain passage**
        * **Rather feasible.**
* Git integration with OH-HECC
    * Could theoretically integrate git into OH-HECC (similar to IntelliJ's git plugin), to allow version control of some description for the .hecc file
        * **Rather unfeasible, would need to work out how to get command line git working, work out how to make java automatically do git stuff, then include functionality within OH-HECC to do the command line git stuff for the .hecc file**
* Some stretchtext-style method of having text that, when clicked, will show/hide some extra content.
    * On click, it calls a method to put some text in a specified container area (or hide aforementioned text)
        * **Defining the area for the expanded text to go in might be problematic, however, it's certainly theoretically possible**.
* Some form of navigation overlay (tree view of the overarching game structure) for the HECCIN Game (similar to that used in the Henry Stickmin games)
    * **Unlikely to happen, and could easily break a lot of potential games**