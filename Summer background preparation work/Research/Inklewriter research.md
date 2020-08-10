#inklewriter

## how it works
* Online tool for writing interactive stories
* Must create an account on the website in order to save/load/share stories


##how to use it
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

##what options it gives the user
* Conditional statements
    * Can be used for
        * Options
        * Whether or not an existing paragraph is to be added to a given paragraph
    * What they can check
        * If a given marker has been passed
        * If a given marker has not been passed
        * If multiple conditions are being checked, it uses 'AND' (only true if all inputs are true)
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
* Formtting options
    * Bold text
    * Italic text
    * Images (if you can provide a link to them)

##what features it has
*

##what features it's missing
* Variables (besides 'whether or not a node was passed')
* Any sort of custom styling options

##how the interior logic and such works in the outputs it produces
*

##sources etc
* https://www.inklewriter.com/
* https://www.inklestudios.com/inklewriter/ (old homepage)