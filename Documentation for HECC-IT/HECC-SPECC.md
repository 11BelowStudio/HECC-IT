# Hypertext Editing and Creation Code - Super Precise Explanation for Creating Code (v0.5: OH-HECC Prep)

## The specification
* Defining passages
    * `::Passage name`
        * This defines a passage called 'Passage name'
        * It can be accessed by clicking links to that passage
    * `::Start`
        * This defines the 'Start' passage
        * The game will start at the 'Start' passage (by default)
            * ~~There currently isn't any method to re-define which passage the game will start at in v0.1~~
            * unless a user manually defines a different starting passage in the metadata
    * Rules for passage names
        * Must be strings contained in a single line, after the '::'
            * A limited amount of metadata is permitted in the passage declaration line
                * A list of tags `[like this space delimited]` will be permitted (even though they currently don't have any functionality)
                * A position vector, in the format `<x,y>` (where x and y are integers or doubles) may also be declared for the passage on the same line as the passage declaration (however, they also don't have any functionality, and this functionality is only going to be used for OH-HECC) 
                * A comment, beginning with `//`, continuing to the end of the line, is permitted
                    * This will not be visible in the HECCIN Game
        * Letters, numbers, spaces, underscores, and hyphens are allowed in passage names.
            * Nothing else
        * Must start with a letter
        * Leading/Trailing whitespace will be omitted.
            * `:: k ` will be treated as `::k` (the passage will be called 'k', not ' k ')
* Defining passage content
    * Just type stuff on the lines after the passage declaration
        * Passages may not be empty.
    * Everything after the passage declaration will be treated as the content of the most recently declared passage
    * Passage content ends
        * when the next passage declaration begins
        * the end of the file is reached.
        * A `;;` line is reached
            * Everything after this line and before the next passage declaration/end of file
             will be treated as a multiline comment, associated with the preceeding passage.
             Any multiline comment such as this will not be present in the output. 
    * Line/paragaph breaks
        * All whitespace before/after the passage content will be omitted in the output.
        * Two consecutive newlines will be treated as a paragraph break.
        * A single newline will be converted into a <br>
    * All <, >, ', ", and & in the .hecc file will be escaped in the HTML output.
* Links to passages
    * Within passage content
    * Two types of links
        * [[Name of passage being linked to]]
            * Displayed as a link saying 'Name of passage being linked to'
            * When clicked, the passage called 'Name of passage being linked to' will be displayed.
        * [[Link text|Name of passage being linked to]]
            * Displayed as a link saying 'Link text'
            * When clicked, the passage called 'Name of passage being linked to' will be displayed.
* **Everything before the first passage declaration is checked to see if it's metadata ~~will be ignored~~**
    * Any non-metadata lines before the first passage declaration officially don't exist
    * Metadata
        * `!StartPassageName: name of start passage` allows a different starting passage to be defined
        * `!IFID: (UUID goes here)` Allows an IFID (Interactive Fiction ID) to be declared for the game. This will be written to index.html
        * `!StoryTitle: Name of Story` Allows the title of a story to be declared
        * `!Author: Author name goes here` Allows the author (you) to be credited for your work.
        * Variables
            * `!Var: variableName = value //optional comment`
                * Allows a variable with the specified name, with a default value of 'value',
                optionally annotated with a comment, to be declared
                * Rules for these
                    * Variable name (mandatory)
                        * Only alphanumeric characters/underscores allowed
                        * Must have a unique name
                            * If multiple variables share the same name, things will end badly
                    * Initial value (optional)
                        * Preceeded by an '=', ends at end of line/start of comment
                        * If numeric, treated as a number
                            * May define it as a string by putting speechmarks around it
                        * If not numeric, treated as a String
                        * If not declared
                            * defaults to 0
                    * Comment (optional)
                        * Preceeded by an '//', ends at end of line
                        * May write whatever you want here to remind you about what the variable does


## Example HECC code
```
this line is before the first passage declaration, so, officially, this line doesn't exist! 

!StartPassageName: Start
!IFID: de7b3d02-81bb-4c2a-82ba-7ca9398b2262
!StoryTitle: HECCSample
!Author: R. Lowe

::Start

starting passage content goes here.
The following line contains a link to "Another passage".
[[Another passage]]

::Another passage [yes] <34,35>

congrats you clicked that link to get here, Another passage.
why not [[click this|Yet Another Passage]] as well?

::Yet Another Passage

woah you clicked that so you're now at Yet Another Passage.

Do you want to go [[Left]], [[Right]], [[Back to the start|Start]], or [[Skip this nonsense|dave]]?

::Left

You go to the left, but the path leads you back to [[dave]].

::Right

You went to the right, but the path leads you back to [[dave]].

::dave

This passage is called dave.
dave's content doesn't include any links to any other passages.
So I guess this counts as the end.
```