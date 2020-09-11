# Twee2

## how it works
* Like twine, but command-line instead (No GUI/IDE/etc)
* Written in Ruby
    * Output is still .html (same as Twine)

## how to use it
* Overview of using it
    * Installation
        * Install 'ruby'
        * Download twee2 by typing 'gem install twee2' into command prompt
    * Making a game
        * Open text editor of choice
        * Write a twee2 game, save the file in .tw2 format
    * Outputting a game
        * Open command prompt, navigate to the .tw2 file
        * Enter 'twee build twee2filename.tw2 outputfilename.html' into command prompt
        * The game written in the .tw2 file will be built, and exported into the .html file.


## what options it gives the user
* Basically has the same functionality as Twine2, minus GUI, plus a few things
* May choose where your WIP game is saved


## what features it has
* Compiling twee2 code via command line
    * Two methods of doing this
        * ```twee2 build input.tw2 output.html```
            * Produces output.html, based on code in input.tw2
        * ```twee2 watch input.tw2 output.html```
            * Like build, but also watches input.tw2 for changes, recompiling whenever input.tw2 is updated
                * However, if input.tw2 'includes' other files, and those other files are updated (not input.tw2), this won't notice that happening.
* Can decompile compiled Twee2/Twine 2 story files from HTML to .tw2
    * ```twee2 decompile input.html output.tw2```
        * The input may be a web URL
        * **Doesn't work on the Windows version**
* Can split a game across multiple .tw2 files

## what features it's missing
* Any sort of non-command line interface
    * no GUI/IDE
* You still need to manually define an IFID within your Twee2 code
    * But the compiler instructs you how to do this (with a uniquely-generated one) if it doesn't detect an IFID declaration in your twee2 code
    * Also means that it won't overrwrite the IFID when compiling a new version of an existing game

## how the interior logic and such works in the outputs it produces
* twee2 syntax
    * Similar to Twine syntax
    * Differences
        * Declaring passages
            * ```::Title of passage``` declares the name of the passage
                * Content goes underneath the passage declaration
            * Declaring tags
                * ```::Title of passage [listOfTags seperatedBySpaces]```
                    * Tags come after the passage name, within square brackets.
                    * Spaces are used to seperate tag names
            * Declaring passage co-ordinates
                * ```::Title of passage <123,456>```
                    * Co-ordinates aren't actively used by Twee2
                        * Only used if you build the .html file, then open it in Twine's GUI (so it can display the passage at the declared co-ordinates)
                    * <x position, y position>
                    * Must be declared after tags (if they exist)
            * Special passages
                * ```::Start```
                    * Initial passage that the player sees when they start reading
                    * May only override this using build configuration options
                * ```::StorySubtitle```, ```::StoryAuthor```, ```::StoryMenu```, and ```::StorySettings```
                    * Used for special passages in Twee1, unused in Twee2, so they're ignored by Twee2.
                * ```::StoryIncludes```
                    * List of additional Twee2 files to 'include' in the story
                    * May include multiple ```::StoryIncludes``` as you want, but best practice is to only use one.
                    * May not recursively include files (may only include files from the top-level file)
                * ```::@include name-of-file-to-insert```
                    * the contents of the specified file will be inserted at that point of this file.
                    * May not use this to recursively include files (may only include files from the top-level file)
            * Special tags
                * ```::passageName [stylesheet]```
                    * Contents of a passage tagged as a 'stylesheet' will be injected into the story's stylesheet.
                    * ```[sass stylesheet]``` and ```[scss stylesheet]```
                        * May be used to declare SASS stylesheet stuff (and which dialect is being used (SASS or SCSS))
                * ```::otherPassage [script]```
                    * Contents of a 'script' passage will have their contents injected into the output as JavaScript.
                    * ```[coffee script]```
                        * Tagging it as 'coffee''script' allows you to declare the JavaScript as Coffeescript instead (still converted into JavaScript as the end result)
                * ```::yetAnotherPassage [haml]```
                    * Tagging a passage as 'haml' allows it to be written in HAML, instead of Markdown.
                        * Allows JavaScript/CoffeeScript to be injected into these passages as well.
                * ```::anotherPassage [twee2]```
                    * Tagging a passage as 'twee2' means it won't be included in the story, but it may include Ruby code to be executed when building the game.
                    * Build configuration options
                        * ```Twee2::build_config.story_ifid = '[your IFID]'```
                            * Declare an IFID for your story
                            * If this is not present within the Twee2 file when built, the compiler will generate a UUID, state the code needed to add that UUID to your game as the IFID, and suggest that you add that code to your game.
                                * The compiler won't automatically add an IFID declaration to your game for you.
                        * ```Twee2::build_config.story_format = '[a story format]'```
                            * Specify the Twine story format being used by your story
                            * If this is not declared, you may use the '--format' option when building your game via command line
                            * Format defaults to Harlowe if unspecified.
* Output is identical to Twine output
* How it does the compilation stuff
    * Loads story file with given name
        * Exception thrown if it doesn't exist
    * Creates a ruby 'attr_accessor' (basically like a dictionary) to hold passages and child story files
    * Loads the story file into memory
    * Split the story file at newlines, send split lines into a list of lines
    * Searches for any 'includes' within that file
        * If this file 'includes' any other files, add the included files to 'child_story_files'
    * After all the included files have been found
        * Splits the newly found files at newlines, adds these lines to that existing list of lines
    * Starts actually parsing the file
        * Starts building the passages
            * Goes through file contents, line by line
            * If the line is passage declaration
                * Stops adding content to previous passage
                * Adds this passage to the attr_accessor of passages
                * Starts adding content to this passage instead
            * If the line isn't a passage declaration
                * That line is added as 'content' for the most-recently declared passage
        * Removes excessive trailing whitespace from each passage
        * Runs any 'preprocessors' (if required)
            * [haml] passages converted to HTML
            * CoffeeScript converted to JavaScript
            * SASS/SCSS converted into raw CSS
    * Once passages are built
        * Checks for special passages (by checking for special passage names/tags)
            * Special passages marked as not being included in the output
            * Certain special passages (stylesheets, scripts, twee2, etc) are put into seperate lists (so they can be used later)
        * Every other passage has a passage ID ('pid') given to it (incremented by 1 for each passage)
            * defined 'start' passage given a 'pid' of 0
        * The story_start_pid is set to whatever the starting passage's pid is.
    * Starts building the Twine2 XML stuff
        * Creates the tw-storydata tag
        * Adds the [style] passages to the twine-user-stylesheet
        * Adds the [script] passages to the twine-user-script
        * Every normal passage is converted to tw-passagedata format, and included within the twine tw-passagedata.
    * The Twine2 XML then has the rest of the Twine2 stuff added to it.
    * Outputs the built Twine2 .html game.

## sources etc
* https://dan-q.github.io/twee2/