#Hypertext Editing and Creation Code - Super Precise Explanation for Creating Code (v0.1: MVP Edition)

## The specification
* Defining passages
    * `::Passage name`
        * This defines a passage called 'Passage name'
        * It can be accessed by clicking links to that passage
    * `::Start`
        * This defines the 'Start' passage
        * The game will start at the 'Start' passage (by default)
            * There currently isn't any method to re-define which passage the game will start at in v0.1
    * Rules for passage names
        * Must be strings contained in a single line, after the '::'
            * Nothing else is allowed in the same line as a passage declaration.
        * Letters, numbers, spaces, underscores, and hyphens are allowed.
            * Nothing else
        * Must start with a letter
        * Leading/Trailing whitespace will be omitted.
            * `:: k ` will be treated as `::k` (the passage will be called 'k', not ' k ')
* Defining passage content
    * Just type stuff on the lines after the passage declaration
    * Everything after the passage declaration will be treated as the content of the most recently declared passage
    * Passage content ends when the next passage declaration begins
* Links to passages
    * Within passage content
    * Two types of links
        * [[Name of passage being linked to]]
            * Displayed as a link saying 'Name of passage being linked to'
            * When clicked, the passage called 'Name of passage being linked to' will be displayed.
        * [[Link text|Name of passage being linked to]]
            * Displayed as a link saying 'Link text'
            * When clicked, the passage called 'Name of passage being linked to' will be displayed.


## Example HECC code
```
::Start

passage content goes here

[[Another passage]]

::Another passage

congrats you clicked that link to get here

[[click this|Yet Another Passage]]

::Yet Another Passage

woah you're at yet another passage
```