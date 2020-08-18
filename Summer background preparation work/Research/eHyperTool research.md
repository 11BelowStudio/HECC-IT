##how it works
* Cloud application (run on a web server, not a native executable)
    * Accessed on web browser
    * Users need to log in to the eHyperTool website in order to use it
* **LINKS ARE NOT DISTINCT OBJECTS (attributes of nodes)**


##how to use it
* Two main types of users (authors/publishers)
* What it does
    * Order of functionality
        * Start project
        * Authoring
            * Import existing components
            * Create story
                * Selecting node to write/edit
                * Writing/editing the node
                * Preview the node (as the user would see it) 
        * Publishing
            * Produce front cover
            * Produce front matter
            * Produce back cover/back matter
            * Export readable story
    * Views that the user has access to
        * Importing
        * Exporting
        * Creation
            * Authoring/previewing/etc the nodes
        * Producing
            * The front cover/front matter/back cover/back matter stuff
* Main functionality in more detail
    * Starting project
        * Sets up some data for the project
            * Author (username by default, can be changed to a pen name)
            * Project title
            * Project type
            * Default prompt that reader sees before options block
            * Time/date of project creation (uneditable)
            * Time/date of last edit to project (uneditable)
            * Publisher (uneditable)
            * Comment (allows notes for project)
            * List of the node names
        * Creates a 'Start' node
        * Project saved on the server
    * Selection view
        * Modal window (rest of application inaccessible until it's closed)
        * Functionality
            * Displays the nodes (with identifying info)
            * Shows links between nodes
                * Distinction between regular links/guard links
            * Can be zoomed in/out
            * Identifies currently-selected node
            * Icons on nodes to denote status
            * Start/end/unattached nodes displayed in a distinct way
            * Can search for nodes by their titles.
            * Can declare new nodes
                * Cannot connect nodes with this view (must do it via authoring instead)
            * Can select nodes to open them in the authoring view
            * Can close selection view without a node being selected
    * Authoring view
        * Multi-tabbed
            * 1 tab per node
        * Basic functionality
            * Accept node title
            * Accept a background sound for the node
            * Allow a node image
            * Accept a node description
            * Accept a set of user options
                * add/remove options
                * Text displayed to the user for the option
                * Which node the option links to (cannot link back to self)
                * Guard conditions for the options
            * Accept a closing remark for the node
            * Edit/display meta-information about the node
                * name (identifier)
                * author
                * last change
                * status (how complete it is)
                * notes (personal notes/reminders by editor, not present in output)
        * Extra functionality it has for nodes
            * Can add extra blocks of text that appear after the node text (subject to guard conditions)
            * Can add extra text that appears after the user selects an option and goes to the next node (subject to guard conditions)
        * Menu bar
            * new projects
            * save/load projects
            * edit details
            * swap to produce mode
            * swap to reading mode
            * select node via select view
            * delete current node (cannot delete start node)
            * open start node
            * browse through previously viewed nodes
            * copy/paste nodes
            * undo/redo
            * view lists of nodes
                * parents/children of current node
                * nodes which have a guard that relates to this node
                * nodes with certain statuses
            * search for node
                * via selection view or via text
    * Reading mode
        * Basically a preview of the finished hypertext game product (using only the functionality supported by the type of project)
    * Produce mode
        * front cover
            * image
        * front matter
            * title page
                * title, legal metadata stuff (publisher info, copyright info, ISBN, edition, other legal stuff)
            * foreword
            * preface
            * acknowledgement
            * intro
            * dedication
        * back matter
            * appendix
            * glossary
            * bibliography
            * all optional
        * back cover
            * image
    * Exporting
        * Outputs the hypertext game (in the appropriate project type)
            * User downloads it (not stored on the server)
        * Options for exporting
            * format
            * whether or not to include the front/back cover/matter stuff
        * Checks performed when exporting
            * Whether or not the story contains features unsupported by the desired output format
                * Warns user if unsupported features are present, but will proceed anyway (omitting the unsupported bits) if user wants to proceed.
            * Ensures that the output is valid
                * Will check that all nodes link to start node and to an end node
                    * Nodes with no options treated as end nodes
                * Things that the user has the option of ignoring
                    * Unconnected nodes
                    * Infinite loops
                * Things that the user is not allowed to ignore
                    * Incomplete nodes (no title/description/etc)
        * formats
            * plain text
                * nodes numbered, start node is 1 (first encountered), other nodes in random orders, options reflected by '[Go to *number*]', ends marked as '[end]'
                * no images/sounds/timers/guards
            * vanilla HTML
                * each node is HTML page, options are links to the HTML pages for those nodes
                * no timers/guards
            * 'DHTML'
                * same as vanilla HTML, but with Javascript to handle timers and guards, and cookies to record saved games
            * kindle
                * read by proprietary kindle-reading software (also option to bundle the reading software with the exported story)

##what options it gives the user
* Options for project output
    * Output types
        * Book
            * Simplest form, no sounds/RNG/timers/guards
        * HTML
            * no guards
        * eText (executable)
            * full functionality
    * Swapping between types
        * Swapping to different output: functionality unsupported by new output type simply hidden (not lost if the project swaps back to that original type)

##what features it has
* Guard conditions
    * ```If the reader [has/has not] previously visited [node name] then [show/hide] this option.```
* Displaying images to reader within nodes
* Exporting options
* Some scope for multiple users to collaborate on making a single story
* Timers

##what features it's missing
* ~~implementation~~
* Styles/templates for the nodes
    * No options for how the output will be displayed to the reader
* Must connect nodes together via the 'authoring' view
* Cannot move nodes around in the selection view
* Selection view displays nodes in a 'parent -> child' format only
    * Means that some nodes with parents on multiple layers appear again on each new level that they're a child on
        * Could cause some confusion
* Server-side tool: requires constant maintenance/upkeep/etc for it to function
* Guards only appear to allow a single node to be taken into account
    * Can't do anything like 'if((visited(A) and visited(B)) or unvisited(C))' in a guard condition

##how the interior logic and such works in the outputs it produces 
* Looking at [2] for the outputs
* HTML pages, with some inline javascript and some javascript that's shared by all the pages.
    * Each node is its own .html page
        * Node title and description present within the raw html of the page itself
        * Shared javascript
            * ```head.js```
                * Declares most of the variables/functions used within the page
                * Important variables
                    * ```adv``` array
                        * Holds the strings for the text that appears when hovering the mouse over a particular option
                    * ```flags``` array
                        * Array of the different values for the 'flags' passed between pages
                * Important functions
                    * ```on(n)``` and ```off(n)```
                        * shows/hides some text that appears when hovering over the option strings
                        * 'n' corresponds to an index of the 'adv' array (obtains the appropriate mouseover text from there)
                    * ```option (n, s, t)```
                        * puts the option mouseover text ('t') into index 'n' of the 'adv' array
                        * Returns some HTML code which creates some text (a string 's') with javascript functionality associated with it
                            * calls ```on(n)``` when mouse hovers over it
                            * calls ```off(n)``` when mouse no longer hovers over it
                            * calls ```select(n)``` when clicked
                    * ```notebook()```
                        * called after body.js works out the flags
                        * Puts the appropriate content into the 'notebook' panel (corresponding to the values of each of the flags)
                            * who your assistant is, what information you've found out, etc.
                        * Does this by editing the inner HTML of the HTML table data element with the 'notes' ID on the actual HTML page
            * ```body.js```
                * Parses the parameters passed to the page via the GET method
                    * 'time' passed as an integer, converted into a string to show the current 'time' for the node to the reader.
                    * Flag parameters are obfuscated by being passed as a single string of characters (instead of as an array of boolean values, which is what they are treated as), so this first needs to de-obfuscate them.
                        * 1st character
                            * used for the 'assisted by' flag values (who you're being assisted by (if anyone), whether or not you've told them to shut up (by clicking on the 'assisted by' string in the 'notebook' area))
                        * 2nd character
                            * combination of 'body released' and 'cocaine on victim's skirt' flag values
                    * After de-obfuscating the flag parameters, it sets the appropriate indexes of the ```flag``` array to the appropriate values, and calls ```notebook()```
        * Node options defined in some inline javascript within the page's ```<head>``` and within the ```<body>```
            * ```select(n)``` function within ```<head>```
                * Called when clicking one of the option text strings
                    * Each option text string calls this method with a particular 'n' value when clicked on
                * Increments a 'time' value (declared within the ```body.js```) to the next time period
                * Then uses a switch-case statement to handle the option being selected
                    * set any 'flags' if appropriate (which are passed via a GET method in the URL)
                    * proceed to the appropriate node (html page)
            * Options are defined within the ```<body>```
                * Edits the inner HTML of the HTML table data element with the 'options' ID on the actual HTML page
                * Each option is put here via a call to the ```option(n,s,t)``` function in ```body.js```
                    * The index (n), option text (s), and mouseover text (t) is defined inline like this
                    * ```body.js``` is responsible for actually converting those raw strings/raw integer into the functional options.
                    
##sources etc
* [1] R. Bartle, C. Beck, private communication (*eHyperTool Design Specification Version 1.02*), June 2020. (document from 2011)
* [2] R. Bartle. "Demo 1" youhaventlived.com https://www.youhaventlived.com/cbdemo/ (accessed Aug 4. 2020)