##how it works
* Can use Twine online (at twinery.org) or can download the Twine executable so you don't need to use a web browser to make a twine game
* 

##how to use it
* Given a canvas-like area for 'passages'
    * An untitled passage is created by default (as a starting passage)
* Menu bar on the bottom
    * Can go back to the 'home' page
    * Can open settings for the story
        * Edit the JavaScript for the story
        * Edit the CSS for the story (overwrites premade CSS for the story format being used)
        * Change story format
        * Rename the story
        * Select all passages
        * 'Snap to grid'
            * makes passages snap to the grid when moving them
        * 'Story statistics'
            * Character count
            * Word count
            * Passage count
            * Link count
            * Broken link count
                * Links that refer to a passage that doesn't exist
            * When the story was last changed
            * IFID for the story [7]
        * 'view proofing copy'
            * Exports a plaintext 'proofing format' version of the story (saved in PC's temp folder), and opens that in web browser
        * 'publish to file'
            * Exports a copy of the .html file that is used for the WIP story, allows the user to save it wherever they want
    * 'Quick Find'/'Find and replace'
        * Can type something in the 'quick find' option to find all passages containing that string
        * Can click a button to open the 'find and replace' dialog box
            * Text area to enter a string to search for
            * Checkboxes for options (include passage names, match case, regex)
            * Text area to enter a string to replace the found string with (optional)
            * Matches for the string being searched for displayed at the bottom of this dialog box
                * Shows the count of passages that match
                * Shows the names of the passages containing matches, along with the count of matches
                    * Can expand these to show the matched stuff within that passage highlighted
            * Button to 'replace all' matches
            * Button (that only appears when hovering over the passage name) to replace all within that passage
    * Buttons to choose how much detail for the passages you want to see
        * Only story structure (no passage titles/excerpts)
        * Only passage titles
        * Passage titles+excerpts (shows passage title and a bit of the passage content)
    * 'test'
        * Makes a copy of the .html document in /temp, with 'debug' tools active, opens it in your default web browser
            * Tracks 'turns'
                * going to node = 1 turn
                * can easily go back to earlier 'turn'
            * Keeps track of any variables and such declared within the game (and their states)
            * Option to enable 'debug view'
                * see what node the text links actually link to
    * 'play'
        * Makes a copy of the .html document in /temp (no debug tools, displayed as it would be to the user), opens that in your default web browser
        * Basically shows the finished product
    * Add passage
        * Adds a new untitled passage to the canvas

       

##what options it gives the user
* Choice of story formats
    * 4 main story formats available
        * Harlowe (default) [2]
        * Sugarcube [3]
        * Snowman [4]
        * Chapbook [5]
    

##what features it has
* 

##what features it's missing
* 

##how the interior logic and such works in the outputs it produces 
* Twine stories are saved and output in a single .html file
    * All game logic handled by Javascript within that .html file
        * Page structure, logic, etc. differs depending on which 'story format' is being used for the project
    * How the game itself is saved [6]
        * Some specialised markup at the start of the ```<body>```
            * ```<tw-story></tw-story>```(not sure what this is used for)
            * ```<tw-storydata [attributes]>``` nested stuff ```</tw-storydata>```
                * Attributes of the tag
                    * ```name = "what the story is called"``` (used by the twine editor to identify the story)
                    * ```startnode = "ID of the node you start at"```
                    * ```creator = "Twine"``` (probably supposed to refer to the tool used to create it)
                    * ```creator_version = "version number"``` (version number of the previous)
                    * ```ifid = "very long number"``` (universally unique sequence of characters to identify a project, see 'Treaty of Babel' [7])
                    * ```format = "story format used```
                    * ```format-version = "version of aforementioned format used"```
                    * ```zoom = "decimal level of the zoom in the editor"```
                    * ```options = ""```
                        * Empty string in most cases
                        * String is ```"debug"``` for the .html file generated for 'debugging' mode
                * The nested stuff
                    * ```<style id="twine-user-stylesheet" type="text/twine-css">``` CSS GOES HERE ```</style>```
                        * Stylesheet to use for the story
                    * ```<script role="script" id="twine-user-script" type="text/twine-javascript">``` JavaScript goes here ```</script>```
                        * JavaScript to execute when the story starts
                    * ```<tw-tag [attributes]></tw-tag>```
                        * Attributes
                            * ```name = "name of the tag"```
                            * ```color = "gray/red/orange/yellow/green/blue/purple"``` (one of those 7 named colours)
                    * ```<tw-passagedata [attributes]>``` passage content ```</tw-passagedata>```
                        * Attributes
                            * ```pid = "internal identifier of the passage"```
                            * ```name = "name of the passage (displayed to user in the editor)"```
                            * ```tags = "list of tags given to the passage in the editor (spaces as delimiters)"```
                            * ```position = "xPosition,yPosition``` (position of upper-left corner of it when viewed in editor)
                            * ```size = "width,height``` (width/height of node when viewed in editor)
                        * The passage content (within the ```tw-passagedata``` tags) is the stuff the user actually sees
                            * Content stored as a single text node (no child nodes allowed)
                            * All &, <, >, ", and ' must be escaped into HTML entities 

##sources etc
* [1] "Twine wiki". twinery.org https://twinery.org/wiki/start (Accessed Aug. 5, 2020)
* [2] *Harlowe 3.1.0 Manual* (2019) Accessed: Aug. 5, 2020. [Online] Available: https://twine2.neocities.org/
* [3] T. M. Edwards. "Sugarcube". motoslave.net https://www.motoslave.net/sugarcube/2/ (Accessed Aug. 5, 2020)
* [4] *Snowman 2.0 Documentation* v. 1.7 (2019) Accessed: Aug 5, 2020. [Online] Available: https://videlais.github.io/snowman/2/
* [5] C. Klimas. "Chapbook, a story format for Twine 2" klembot.github.io https://klembot.github.io/chapbook/ (Accessed Aug. 5, 2020)
* [6] T. M. Edwards, D. Cox. "Twine Specifications" github.com/iftechfoundation https://github.com/iftechfoundation/twine-specs (Accessed Aug. 5, 2020)
* [7] Interactive Fiction Technological Foundation. "The Treaty of Babel". ifarchive.org https://babel.ifarchive.org/ (Accessed Aug. 5, 2020)