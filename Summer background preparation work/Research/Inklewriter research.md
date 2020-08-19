#inklewriter

## how it works
* Online tool for writing interactive stories
* Must create an account on the website in order to save/load/share stories
* Mostly JavaScript

## how to use it
* Start with single paragraph with sample 'Once upon a time...' text
    * Can edit text within that paragraph
    * Can add options at the end of that paragraph
        * Can enter option text
        * Click on the arrow next to the option text to go to the paragraph for that option
    * Can make a new paragraph 'join to an existing paragraph'
        * Basically appends the existing paragraph to the end of this paragraph (displaying it under the text for this new paragraph)
        * User treated as being at that existing paragraph
    * Can give paragraphs 'markers'
        * Used for conditional logic
    * Can define 'sections'
        * Passages following the passage where a section is defined are treated as being in that section
        * Mainly used for ease of arranging stuff in the 'contents' pane
    * Can add 'markers' to passages
        * Used for conditional statements (can check if specific markers have/have not been passed)
        * Can also be used as counters

## what options it gives the user
* Conditional statements
    * Can be used for
        * Options
        * Whether or not an existing paragraph is to be added to a given paragraph
    * What they can check
        * If a given marker has been passed
        * If a given marker has not been passed
        * If multiple conditions are being checked, it uses 'AND' (only true if all inputs are true)
        * Can be used to check counters
    * Some level of automatic testing
        * Cannot check to see if the same marker has been 'passed' and 'not passed'
        * Warns the user if the specified conditions can never be met
* Viewing the game
    * 'Writing' and 'reading' views
        * 'Writing' view for all the editing tools and such
        * 'Reading' view for seeing what the end result will look like to a reader
    * 'contents' pane
        * Shows a list of all the nodes (in dropdown lists, under the sections for each)
    * 'map' pane
        * Shows the structure of the game in the form of a map of nodes
            * Shows the connections between sections on the top-level view
            * Can click on the sections to show the connections between the nodes in the sections
* Navigating between nodes
    * Navigate between them in the same way that the player would navigate between them
        * Click on the arrow next to the option, the paragraph it leads to appears underneath current paragraph
        * If you navigate to a node from the map/contents, the sequence of nodes leading to that node will be visible above it on the 'write' pane
            * If certain conditions must have been met to see that node, the sequence of nodes leading to it which allow the conditions to be met will be visible.
* Formatting options
    * Bold text
    * Italic text
    * Images (if you can provide a link to them)
* How to use counters
    * Set a 'marker'/'flag' to say ```variableName = value``` or ```variableName [arithmetic operator] value```
        * Value associated with the variable defaults to 0 if unassigned
    * Using them in conditionals
        * ```variableName [comparisonOperator] comparisonValue```
            * If put in 'check if passed', it will check if this evaluates to true
            * If put in 'check if not passed', if will check if this evaluates to false
    * Displaying them
        * ```[number: variableName]``` displays it as a raw number
        * ```[value: variableName]``` displays it as words ('One' instead of '1')
* 'shuffling' between options
    * ```{~option 1|option 2|etc}``` randomly displays one of 'option 1', 'option 2', or 'etc'

## what features it has
* Sharing stories
    * Sharing them via URL on the inklewriter website
    * Exporting them in .JSON format
    * Exporting them in .ink format
* More options if you use 'ink'/'inky'
    * Styling
        * Global styling:
            * ```#theme: dark``` for darkmode
        * Setting CSS classes
            * putting ```#CLASS: classname``` at the end of a line allows you to style that line via ```.class{/*styling*/}``` within style.css
    * Clearing the visted stiches from the 'reading' view
        * Putting ```#CLEAR``` within a stitch will hide all previously visited stitches when this one is visited
    * Restarting the game
        * ```#RESTART``` restarts the game
    * Exporting from .ink
        * Exports a folder containing
            * index.html (main HTML page binding others together)
            * main.js (main JavaScript file, defines behaviour of how stuff is presented)
            * style.css (main CSS file)
            * yourStoryName.js (the actual ink content exported by inky)
            * ink.js (JavaScript port of the ink engine itself)
* viewing story
    * can view story as a sequence of nodes leading to a certain node, a map of nodes, a dropdown list of nodes in 'sections', or in the format it gets output as for readers
        

## what features it's missing
* Any sort of custom styling options (unless you use 'ink')
* basically need to choose between having a GUI or a powerful editor

## how the interior logic and such works in the outputs it produces
* Stories pretty much stored as objects (and can be shared as JSON objects, or in '.ink' format)
* JSON Schema
    * Main schema
        * ```title:``` String with the title of the story
                * ```data:``` The actual story data
                    * ```stitches:``` The actual data for the nodes/passages/etc that constitute the story
                        * ```[identifierForTheStitch]:``` This is basically the first 16 alphanumeric characters of the stitch text smashed together in camelcase, used as the identifier for the 'stitch'
                            * ```content:``` The list of 'content' objects that make up this 'stich'
                                * ```0:``` string with the text content of the node
                                * ```[indexOfThisObjectWithinContent]:``` Following components in the content will start from '1:', increasing with each one, and could be an option, link to other paragraph, section/marker, etc.
                    * ```initial:``` String with the name of the 'stitch' that the game starts at
                    * ```optionMirroring:``` no idea
                    * ```allowCheckpoints:``` Whether or not the game contains 'checkpoints'
                    * ```editorData:``` some data that's only used within the editor
                        * ```playPoint:``` the node that's currently open in the editor (the one with the furthest depth)
                        * ```libraryVisible:``` Whether or not the 'share' button has been pressed on the story (making it sharable)
                        * ```textSize:``` integer for a custom text size (but there doesn't actually appear to be an option to change this)
                * ```url_key:``` integer that goes at the end of the 'inklewriter.com/stories/' URL to share the story
            * Schemas for types of 'content' nodes
                * ```flagName:``` String for a 'marker'/'flag' that can be given to a 'stich', for use in any 'if passed'/'if not passed' conditions
                * ```ifCondition:``` 'flagname' string for the flag that must have been passed in order for the parent of this object to be visible (used for 'if passed' conditions)
                * ```notIfCondition:``` same as 'ifCondition' but for 'if not passed' conditions instead
                * Options
                    * ```option:``` Text displayed to the user for the option
                    * ```linkPath:``` Identifier for the 'stitch' that this option sends you to.
                    * ```ifConditions:``` Object containing 'ifCondition:' objects for each 'passed' condition for this. 'null' if no such conditions exist.
                    * ```notIfConditions:``` like ifConditions, but for the 'not passed' ('nofIfCondition:') conditions instead
                * ```divert:``` Identifier for the 'stitch' that is appended to the bottom of this 'stitch'
                    * note: a stitch with a 'divert' cannot have options
                * ```pageNum:``` an integer.
                    * positive integers: used for 'section' numbers (node designated as the start of the 'section' has this)
                    * negative integers: not sure
                * ```runOn: true```
                    * When a paragraph is set to 'run together' with another paragraph, this is 'true' on the paragraph behind it
* .ink format
    * Information about the story at start
        * ```
            // ---- [STORY TITLE] ----
            // Converted from original inklewriter URL:
            // https://www.inklewriter.com/stories/[URL NUMBER]
            # title: [TITLE GOES HERE]
            # author: [AUTHOR NAME GOES HERE]
            // -----------------------------
            ```
    * declaring variables
        * ```
            VAR thatsodd = 0 //counter variables initialised as 0
            VAR a = false //'flags' set as false
            VAR b = false
            ```
    * declaring start of story
         * ```-> idOfStart``` basically directs to the 'stitch' the story starts at 
    * declaring the contents of the 'stitches'
        * structure
            * ```
              declare stitch ID
              declare stitch text
                ~ declare any variables set by this stitch
                + declare options (if any)
                    -> declare ID of where the option leads
                -> declare ID of where this stitch redirects to
              ```
        * Start of declarations
            * If it's at the start of a 'section'
                * ```==== stitchID ====```
            * If it's not at the start of a 'section'
                * ```= stitchID```
        * conditions for content
            * simple, one-condition checks:
                * ```[line prefix]{condition}content hidden behind condition```
            * multiple conditions
                * ```
                  {condition:
                    content hidden behind condition
                  }
                  ```
        * text content of stitch
            * ```string containing the text content of this 'stitch'```
            * variables displayed with ```{variableName}```
            * images displayed with ```#IMAGE: [image path]```
        * setting variables
            * ```~ variableName = newValue```
        * options
            * ```
              + optionText
                -> IDofWhereItLeadsTo
              ```
                * starting with + allows the option to reappear after being chosen
            * ```
              * optionText
                -> IDofWhereItLeadsTo
              ```
                * starting with '*' means the option doesn't reappear after being chosen
        * 'diverts' to other stiches
            * ```-> stitchThatThisDivertsTo```
        * Declaring that this is the end
            * ```-> END```

## sources etc
* https://www.inklewriter.com/
* https://www.inklestudios.com/inklewriter/ (old homepage)
* https://www.inklestudios.com/ink/