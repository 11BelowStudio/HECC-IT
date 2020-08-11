#ChoiceScript

## how it works
* Built on Node.js
    * Basically server-side javascript
* Running ChoiceScript locally requires you to launch a ChoiceScript server in localhost, and keep it running whilst playing the ChoiceScript game
* ChoiceScript games saved as a folder of .txt files
    * choicescript folder/web/mygame
    * .txt files must be in UTF-8 encoding

##how to use it
* Must first launch ChoiceScript server with run-server.bat
* Writing stuff
    * Important files
        * startup.txt
            * First file which is opened when playing the game
            * Things that need to be defined at the start (before anything else)
                * Title
                    * ```*title Title Goes Here```
                * Author
                    * ```*author Author Name Goes Here```
                * Scenes
                    * ```
                      *scene_list
                          startup
                          namesOfOtherTxtFilesUsedAsScenes
                          etc
                      ```
                        * Basically defines which .txt files the game needs to be concerned with
                * declaring variables
                    * ```*create variableName value```
                        * May only create variables at the start of startup.txt.
            * Can then start writing the game
    * Writing things
        * plaintext
            * Just write stuff (without any formatting/special commands/etc) that you want to be displayed to the reader
        * Special commands
            * These take the form ```*keyword```
                * Simple ones
                    * ```*comment comment text``` used for comments
                    * ```*link URL goes here``` used for links
                    * ```*page_break``` used to insert a page break (user must click 'next' to see following content)
                * Less simple ones
                    * Choices
                        * ```
                          *choice
                              #Choice 1 text
                                  content that appears after making choice 1
                              #Choice 2 text
                                  content that appears after making choice 2
                              #Choice 3 text
                                  etc
                          ```
                            * The indentation for the choice text and the choice content is mandatory.
                        * ```*fake_choice```
                            * Like ```*choice```, but no commands are allowed within the body of the choice
                                * Each choice eventually leads to the stuff following the ```*fake_choice``` block
                    * Finishing indented blocks
                        * Staying within the scene
                            * ```*goto labelName``` jumps to the line in the scene which has been given the specified label
                        * Finishing the entire scene
                            * ```*finish``` finishes this scene, sends user to the start of the next 'scene' in the list defined in startup.txt's scene_list
                                * If the current scene in the last one in the list, this is treated as an ```*ending```
                            * ```*goto_scene sceneName``` finishes this scene, sends user to the start of the specified scene
                                * If the specified scene doesn't exist in the scene_list, this is treated as an ```*ending```
                            * ```*ending``` finishes the scene, instructs game to insert a 'play again' menu at the end
                    * Re-using code
                        * ```
                          *label labelname
                          content that comes after the label declaration
                          ```
                            * indicades that the following content belongs to the 'labelname' label
                        * ```*goto labelname```
                            * jumps to the line of code where the 'labelname' label was defined, resumes from there

##what options it gives the user
* Variables
    * Choice of variable scopes
        * ```*create variableName value``` at start of startup.txt for global variables (usable by any scene)
        * ```*temp variableName value``` at the start of any scene for a local variable (only usable within that scene)
    * Dynamically typed
    * Setting values
        * ```*set variableName value```
        * ```*set variableName expression that evaluates to new value```
        * Allowing user to specify value
            * ```*input_text variableName```
                * text box given to user to specify a value for that given variable
            * ```*input_number variableName minValue maxValue```
                * text box given to user to specify a value for the given variable
                * only accepts a numeric input within the given range (inclusive)
        * ```*rand variableName minValue maxValue``` sets the given variable to a random integer value within the given range (inclusive)
    * Conditional statements
        * Can use normal comparison operators (but '=' is used for equality, not '==', as ```*set``` is used for assignment instead of '=')
            * Numbers and string representations of numbers are treated as identical (so ```"2" = 2``` evaluates as 'true')
        * Can use boolean operators (```(A) and (B), (A) or (B), not(A)```)
        * Using conditional statements
            * Options that can only be selected following a condition
                * ```
                  *choice
                    *selectable_if (condition) #Option that's only selectable if a condition is met
                      option content
                  ```
                * ```
                  *choice
                    *if conditionA
                      #option that is only visible if condition is true
                        option content
                        *finish
                    * else
                      #option that is only visible if condition is false
                        other option content
                        *finish
                  ```
    * Showing variables
        * ```${variableName}```
* Subroutines
    * Basically allows the game to temporarily jump to another line of code, before going back to what it was doing before the subroutine was called.
    * ```*gosub labelname```
        * Goes to the line where the 'labelname' label is defined, but treating it as a subroutine, not as a 'jump'
    * ```*return```
        * This must be used to terminate any 'subroutine'. Jumps back to the line that called this subroutine in the first place, resuming the game from there.
* Can use multiple files for the project
* Images
    * ```*image filename alignment description```
        * filename refers to the filename of it within the mygame folder
        * alignment is used for CSS alignment ('left','right', or 'none')
        * description is a textual description of the image (for visually-impaired users)
* 'bugs' (basically exceptions)
    * ```*bug Error message``` If this line is executed, it crashes the game, and leaves that specified error messsage

##what features it has
* Variables
* Some automatic testing stuff
    * 'Quicktest'
        * Attempts testing every ```#option``` in every ```*choice```, as well as both sides of every ```*if``` statement
            * Skips any previously-tested code
        * Best suited to code coverage stuff (returns a warning for every line it could not test)
        * Also reports any errors (syntax etc) encountered during play
    * 'Randomtest'
        * Enter how many iterations you want to run it for, and it'll run through the code that many times, making random choices
            * Recommended to run it 10,000 times
            * Makes random decisions for every choice
            * Reports how many times each line of code was used
        * Not entirely random
            * Starts from a hardcoded 'seed' value at the start of a test
                * Two randomtests (of 10000 iterations) on the same game will have the same results
                    * Intended to make it easier to get consistent results between randomtests
                * Can manually specify a 'seed' to start from (overriding the hardcoded starting 'seed') if desired
        * Running a 'randomtest' sets a special 'choice_randomtest' variable to true: can use this to force randomtest to jump to a certain label (if needed)
        * Also reports errors (syntax etc) encountered when playing
        
##what features it's missing
* No GUI stuff (at all)
* Requires a server to be running in order to play games
* No styling options
    * Sure, there's some markup-style ```[b]bold[/b]``` and ```[i]italic[/i]``` formatting options, but that's it.
* No officially endorsed IDE or anything like that

##how the interior logic and such works in the outputs it produces
* What each file appears to be used for
    * top level
        * 'startupgenerator.js' appears to be used to start generating a game
        * 'mygamegenerator.js' appears to be responsible for parsing the scenes list
        * not sure about the others
    * /web/ directory
        * it appears that '/web/ui.js' is used to define the user interface
        * '/web/scene.js' appears to be responsible for the objects holding scene data from the parsed .txt files
        * '/web/navigator.js' appears to be responsible for handling navigations between scenes
        * not sure about the others
    * /web/mygame
        * holds the index.html and mygame.js files, used to start running the game
        * /web/mygame/scenes holds the .txt files that actually make up the game
* Games are run via server, not within the browser
    * Probably allows DRM and such to work for the games that are hosted on the choiceofgames website
    * Makes it harder to get games set up and running (as there's extra webserver weirdness to deal with)
* I honestly can't be arsed to deal with any sort of serverside stuff in my system (as that's an extra, unnecessary level of complexity for everyone involved), so I probably won't bother looking in any deeper into how the inner workings work.


##sources etc
* https://www.choiceofgames.com/make-your-own-games/choicescript-intro/
* https://nodejs.org/en/about/