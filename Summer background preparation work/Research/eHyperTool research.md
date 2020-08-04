##how it works
* Cloud application (run on a web server, not a native executable)
    * Accessed on web browser
    * Users need to log in to the eHyperTool website in order to use it


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
            * Accept a node description
            * Accept a set of user options
            * Accept a closing remark for the node
            * Edit/display meta-information about the node
                * name (identifier)
                * author
                * last change
                * status (how complete it is)
                * notes (personal notes/reminders by editor, not present in output)
                

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
    * 

##what features it has
* Guard conditions
    * ```If the reader [has/has not] previously visited [node name] then [show/hide] this option.```
    

##what features it's missing
* ~~implementation~~
* Styles/templates for the nodes
    * No options for how the output will be displayed to the reader
* Must connect nodes together via the 'authoring' view
* Cannot move nodes around in the selection view

##how the interior logic and such works in the outputs it produces 
*

##sources etc
* [1] R. Bartle, C. Beck, private communication (*eHyperTool Design Specification Version 1.02*), June 2020. (document from 2011)
* [2] R. Bartle. "Demo 1" youhaventlived.com https://www.youhaventlived.com/cbdemo/ (accessed Aug 4. 2020)