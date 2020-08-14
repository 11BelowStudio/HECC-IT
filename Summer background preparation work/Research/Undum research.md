#Undum

## how it works
* High level overview stuff
    * Concepts
        * Games built around 3 main concepts
            * Situations
                * Code responsible for
                    * Adding content to screen
                    * Responding to user interaction
                        * All user interaction: clicking links in content
                * Links can change current situation
                * When a situation changes
                    * All previously available links are removed
                * Always one active situation at any time.
            * Actions
                * Series of actions may be offered by a Situation
                    * Internal to situation, do not cause situation to change
                * May output more content
                    * including more links
            * Qualitites
                * Represent current state of the character
                * Basically variables
                * **Only numeric values are supported**
                    * IntegerQuality
                        * rounded down to nearest integer when displayed
                    * NonZeroIntegerQuality
                        * like IntegerQuality, but only displayed when it has a non-zero value
                    * NUmericQuality
                        * Quality displayed as floating-point value
                    * WordScaleQuality
                        * Array of strings, when the value is *x*, the string at index *x* is displayed.
                            * May define an offset, so, when value is *x*, string at index *x*+*offset* is displayed
                    * FudgeAdjectivesQuality
                        * like WordScaleQuality, but uses 'terrible','poor','mediocre','fair','good','great','superb'.
                            * -3 offset by default ('fair' at 0)
                    * OnOffQuality
                        * Like a boolean.
                        * If value is 0, it's not displayed. If value is not 0, the quality is displayed (without a value)
                    * YesNoQuality
                        * Also like a boolean
                            * Displays one string if value is 0, displays other string if value is not 0.
                * Displayed via a formatting function
                    * progress bar, symbol, word, integer, etc
                * Can represent any kind of value
        * Other, less important, concepts
            * Quality groups
                * can display a set of qualities under a common haeading
            * Character Text
                * Short chunk of HTML used to
                    * summarize a character's qualities
                    * give hints
* Files and such
    * Single HTML file
        * Imports game engine, supporting files, and provides structure for content to be placed correctly on screen.
    * 3 JavaScript files
        * ```media/js/jquery-2.1.3.min.js```: literally just jQuery
        * ```media/js/undum.js```: the undum engine
        * ```media/js/mygame.game.js```: a JavaScript file that basically contains the definition for the game
    * CSS files
        * ```media/css/undum.css``` used for desktop, ```media/css/undum-mobile.css``` used for mobile (by default)


##how to use it
*

##what options it gives the user
* Undum v.2 onwards allows situations to be defined in either the JavaScript 'Game definition' file, or in the HTML file.

##what features it has
*

##what features it's missing
* No sort of editor or anything
    * Need to edit the code for the game directly

##how the interior logic and such works in the outputs it produces
* Data to put in each file
    * HTML file
        * Game title
            * Within ```<title></title>```
            * Within the 'label' ```<div>```
        * Top-left panel title/summary
            * Within the 'info_panel' ```<div>```
        * Copyright message
            * Within the 'legal' ```<div>```
        * Path to the main game file
            * Edit the file referenced by the final ```<script>``` to the file that your game uses
        * (optional) Can define some situations here
            * Within the 's_situations' ```<div>``` inside the 'content_library' ```<div>```
    * The game definition file ('.game.js' file)
        * undum.game.id
            * String holding a unique ID for the game. Can use anything 'guaranteed unique'.
            * Should ideally hold a UUID, but this requirement is not enforced (and user is responsible for inputting this manually)
                * **Not compliant with Treaty of Babel**
        * undum.game.version
            * String indicating what version of the game this is.
        * undum.game.mobileHide
            * integer defining fade-out speed of option text on mobile
        * undum.game.fadeSpeed
            * integer defining fade-out speed of options
        * undum.game.slideUpSpeed
            * integer defining slide-up speed after clicking on an option
        * undum.game.situations
            * Holds the definitions/constructors for each Situation object that the game consists of
                * Each must have a unique ID
            * Can refer to situations defined in the HTML file
        * undum.game.start
            * String, identifier of the starting situation.
        * undum.game.qualities
            * Holds definitions/constructors for all the qualities that the characters can possess
                * Each one must have a unique identifier
                * May also define which 'qualityGroup' the quality will be displayed in (by entering string ID of it)
                    * Qualities that are not in a qualityGroup displayed under the other qualities
                    * May not assign a quality to a qualityGroup that doesn't exist.
            * Any that are not defined won't be displayed to the player.
        * undum.game.qualityGroups
            * Define the 'quality groups'
                * Basically these allow qualities to be organized under headings in the character bar
        * undum.game.init(character, system)
            * Function that is run before game begins, initialising character qualities
            * Initialising character qualities
                * ```character.qualities.qualityID = numericValue;``` for each defined quality
            * May also set some descriptive text at the top of the character bar
                * ```system.setCharacterText("<p>HTML stuff used to describe the state of the character</p>")```
* JavaScript API stuff
    * 'Character' object
        * character.qualities
            * Maps quality IDs to current value
            * Can get definedQuality via ```var i = character.qualities.definedQuality;```
            * Should update definedQuality via ```system.setQuality('definedQuality',42);```
        * character.sandbox
            * General storage space for any other data that needs to be tracked throughout the game
            * Can store any data structures here
            * Never displayed to the user
    * 'System' object
        * Interface between code and UI
        * Attributes
            * rnd
                * A random number generator
                * Methods
                    * random()
                        * returns random number between 0 and 1
                    * randomInt(min,max)
                        * returns random integer between 'min' and 'max' (inclusive)
                    * dice(n, dx, plus)
                        * Rolls n Ddx, adds plus to result (plus may be positive or negative)
                    * aveDice(n, plus)
                        * rolls n D6 (with sides 2,3,3,4,4,5), adding plus to result
                    * diceString(definition)
                        * rolls dice according to definition string
                        * must be of form 'xdy+z'
                            * rolls x dice with y sides, adds z to result
            * time
                * numeric value, time in seconds since player started playing the game
        * Methods
            * clearContent(elementSelector)
                * Removes all content from page (clearing main content selector)
                * elementSelector is optional; providing it only clears that specified HTML DOM element.
            * write(content, elementSelector)
                * Writes new content to main flow of story
                    * content must be valid HTML elements
                * elementSelector is optional
                    * If provided, content will be added after selected DOM element
                * Story will scroll to start of insertion point
                    * unless jQuery's animation stuff was disabled in the initialisation code
            * writeHeading(content, elementSelector)
                * like write but it's in ```<h1>``` instead
            * writeBefore(content, elementSelector)
                * like write but adds it before everything else/before the specified element
            * writeInto(content, elementSelector)
                * Writes content into specified element (if not specified, works identically to write)
            * replaceWith(content, elementSelector)
                * Replaces content in specified element with the content passed to this method
                * if no element is specified, it replaces the entire current situation
            * writeChoices(listOfSituationIDs)
                * Creates a block of 'choices' to each of the listed situations.
                * Uses the 'optionText' method of each situation to get the text being used for each situation
            * getSituationIdChoices(listOfIDsOrTags, minChoices, maxChoices)
                * Give it a list of situation IDs/situation tags
                    * Considers all matching situations in priority order
                    * Picks the highest priority situations, ensuring that at least minChoices are chosen, but no more than maxChoices
                * Sorts the chosen situations in ascending displayOrder
            * 
                

##sources etc
* https://idmillington.github.io/undum/