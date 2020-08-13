#Squiffy

## how it works
* Write stuff in a .squiffy file, exported  as a folder with HTML, CSS and JavaScript files.

##how to use it
*

##what options it gives the user
* Can declare 'sections' and 'passages'
    * Differences
        * Sections
            * Main units of text
                * Effectively the main 'nodes'
            * Functionality
                * Going to a new section disables all active section/passage links
            * Declaring them
                * ```
                  [[section name]]:
                  section content goes here
                  ```
            * Linking to them
                * ```[[name of section being linked to]]```
                * ```[[link text]](name of section)```
            * Can link back to earlier sections/current section
        * Passages
            * Smaller units of text within sections
                * Appear underneath a section, but the story still remains in the section
            * Functionality
                * Going to a new passage doesn't disable the links within the same section
            * **Must be declared in the sections that link to them**
                * Cannot link to a passage that was defined in an earlier section
            * Declaring them
                * ```
                  [passage name]:
                  passage content goes here
                  ```
            * Linking to them
                * ```[name of passage being linked to]```
                * ```[link text](name of passage)```
    * Common functionality
        * Every section/passage must have unique names
            * If they share a name, the bottom-most declaration will be used.
            * Passages sharing names can also cause problems when checking what passages have been seen
        * Section/passage declarations implicitly end at the start of the next section/passage declaration
        * Can fire some custom JavaScript upon navigating to a section/passage
            * ```
              [[section or passage declaration]]:
                  //JavaScript goes here
                  //must be indented by 4 spaces/1 tab
                  //don't need to declare the end of the JavaScript
              The actual section/passage content goes below the custom JavaScript
              ```
        * Can embed text from other sections/other passages in current section
            * ```here's some text from another section: {other section}```
                * The text from the 'other section' section appears on the following line
            * ```here's some text from a passage within this section: {passage within this section}```
                * The text from the 'passage within this section' passage appears on the following line
            * **Recursive embeds are not allowed**
                * Passages/sections with any sort of recursive embedding are displayed as being completely empty.
* 'continue' links
    * Effectively allow a new section (and a link to it) to be implicitly declared at the end of a section
        * ```
          this is the first section
          
          +++click this to continue
          
          wow you're in a new section
          
          +++k
          
          waoh
          ```
            * The ```+++text``` is used to define the name of the following section, and is used as the text for the link to it
            
* Formatting
    * Supports markdown formatting
    * Allows user-defined HTML formatting
* Setting where the story starts
    * Default
        * Story starts in the first (topmost) section
    * Manually
        * ```@start StartSection```
            * Story starts at the StartSection section
* Setting story title
    * ```@title Title goes here```

##what features it has
* Can use custom JavaScript
    * But this can only fire at the start of a section/passage
* Variables (attributes)
    * Setting them
        * Setting them within section/passage
            * ```@set variableName = value```
        * Setting them within a link
            * ```[[link text]](section this link sends you to, variableName=value)```
        * Setting them via JavaScript
            * ```squiffy.set("variable","value");```
    * Supported datatypes
        * numbers
            * Numeric values are treated as numbers
            * Operations
                * Increment
                    * ```@inc variableToIncrement``` increments by 1
                    * ```@inc incrementThis x``` increments by *x*
                * Decrement
                    * ```@dec variableToDecrement``` decrements by 1
                    * ```@dec decrementThis x``` decrements by *x*
                * Can also use mathematical operators on them
        * strings
            * Other values are treated as strings
        * booleans
            * Values are set differently to the other ones
                * True
                    * ```@set variable_that_is_true```
                * false
                    * ```@set not variable_that_is_false```
                    * ```@unset variable_that_is_false```
    * Reading/using them
        * Displaying them to the reader
            * ```The value of this variable is {variableName}```
        * Reading via JavaScript
            * ```var jsVariableName = squiffy.get("variableName");```
        * Conditional statements
            * How things can be compared
                * Only supports 'if' and 'else' (no 'else if', need to use nested 'if else' statements.)
                * Also doesn't appear to support boolean AND/OR
                * Comparing attribute to hardcoded value
                    * ```{if variableName=value:stuff}{else:other stuff}```
                * Comparing two attributes: need '@' before second one
                    * ```{if a=@b:stuff}```
            * How to use the conditional statements
                * Conditionally displaying text
                    * ```{if condition:text displayed when true}{else: text displayed when false}```
                        * Can be used for guard conditions (only displaying link if conition is met)
                * Conditionally setting values of attributes
                    * ```{if condition:{@a+=1,c-=1,d=2,not e}}{else:{@b+=2,c+=2,d=3,e}}```
* Keeping track of what has/hasn't been visited
    * Using JavaScript
        * ```squiffy.story.seen("name of passage or section being checked")```
            * True if the passage/section with that name has been visited
            * False otherwise
    * Within the section/passage content
        * ```{if seen sectionOrPassageName:stuff to display if seen}{else:stuff to display if not seen}```
* Some level of error detection
    * Can detect links to undefined sections/passages
* Text that changes when user clicks it
    * Changing text to change it/Cycling through options
        * Can cycle through options indefinitely, or only go through the sequence once
            * Indefinitely
                * ```{rotate:A:B:C}```
                    * Clicking on C makes it go back to A
            * Only once
                * ```{sequence:A:B:C}```
                    * Cannot click on C
                * Last option can be a section/passage link
                    * ```{sequence:A:B:[[C]]}```
                        * C is a link to Section C
                    * ```{sequence:A:B:[C](bruh)}```
                        * C is a link to passage 'bruh'
        * Can store the result of the cycling as an attribute
            * ```{rotate rResult:A:B:C}```
                * ```rResult``` is set to the currently displayed option from the rotating text
            * ```{sequence sResult:A:B:C}```
                * ```sResult``` is set to the currently displayed option from the sequence text
            * **should only use the result in a section following the 'rotate'/'sequence' where it was chosen
                * prevents users from continuing to cycle it after it should have been set
    * Clicking on some text to change different text
        * Defining what text is to be changed
            * ```the text that will change is {label:labelID=this text}.```
                * Defines a label, identified as 'labelID', which initially says 'this text'
                * The labelled 'this text' text can now be changed.
        * Replacing text
            * For sake of argument, assume ```the text that will change is {label:labelID=this text}.``` has already been defined
            * Within the passage
                * ```click [this](@replace labelID=deez nuts) to make the text say 'deez nuts'```
                    * Clicking 'this' replaces the 'labelID' text with 'deez nuts'
            * Replacing the text with a section/passage
                * ```
                  click [this](@replace labelID=lmao gottem) to make the text say 'deez nuts'
                  [lmao gottem]:
                  deez nuts
                  ```
                    * Clicking 'this' replaces the 'labelID' text with the contents of the 'lmao gottem' passage (aforementioned contents being 'deez nuts')
            * Replacing text after the next section/passage is displayed
                * ```
                  [[time for deez nuts]]
                  [[time for deez nuts]]:
                  @replace labelID=deez nuts
                  
                  lmao gottem
                  ```
                    * When clicking the link to the 'time for deez nuts' section, the ```@replace``` script is called, meaning that the contents of the 'labelID' label from earlier on gets changed to say 'deez nuts'
* Can include a 'turn count' within a section
    * Passages can be set up to fire once a certain number of passage clicks have been made in a section
        * A passage defined as ```[@n]:``` is opened once 'n' passage clicks have been made in this section
            * These passages do not count towards the passage click counter
            * ```[@1]:``` fires after the player clicks 1 passage
            * ```[@2]:``` fires after the player clicks 2 passages
                * etc
    * Can set up a passage to fire after all the passages within a section have been clicked on
        * ```[@last]:``` fires after every passage link is clicked
    * Can use these to fire JavaScript
        * Sending a player to a particular section once 2 passages are clicked
            * ```
              [@2]:
                  squiffy.story.go("section that you get sent to after clicking 2 passages");
              ```


##what features it's missing
* Any form of graphical overview of the story
* Some debugging features
    * Cannot detect loops within the story
    * Cannot detect instances of passages/sections sharing the same identifier

##how the interior logic and such works in the outputs it produces
*

##sources etc
* https://textadventures.co.uk/squiffy
* http://docs.textadventures.co.uk/squiffy/
* https://github.com/textadventures/squiffy