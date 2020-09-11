# Undum

## how it works
* Basically provides a template for writing hypertext games.
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


## how to use it
* Download the undum files from the Github repo
* Open the 'games' folder
    * Make a copy of one of the 'tutorial' HTML files
    * Open 'media/games/tutorial'
        * Make a copy of one of the 'tutorial.game' JavaScript files
* In those copied files, edit the stuff that the comments say to edit, in order to write your own game.
* Basically writing HTML/JavaScript/CSS stuff by hand (except it's basically editing a template).

## what options it gives the user
* Undum v.2 onwards allows situations to be defined in either the JavaScript 'Game definition' file, or in the HTML file.
* May save/load content whenever

## what features it has
* Can use all the functionality of HTML5, CSS, and JavaScript
    * But you need to use Undum's own RNG and time stuff instead of JavaScript Date/Math.random() (or saving/loading games won't work)
* Doesn't require any form of dedicated server/runtime environment/anything like that to run
    * Runs direct out the box (assuming your browser supports HTML5, CSS, and JavaScript)
* Game stays within a single HTML page
    * Uses jQuery to update the stuff displayed to the user during the game
* Variables and such pretty much persistently displayed to the player within the 'character' panel to the side of the game stuff

## what features it's missing
* No sort of IDE/editor/anything like that
    * Need to edit the code for the game directly
    * Casual users may be put off it as a result
* Variables must be numbers
    * No support for other datatypes
* No automatic error detection
* The 'game.id' stuff is not compliant with the Treaty of Babel's IFID stuff
    * It suggests using a UUID, but it's ultimately the responsibility of the user to enter something there
        * Can't guarantee that an actually unique UUID is used
        * Can't guarantee that it will be alphanumeric-with-hyphens, between 8-63 characters


## how the interior logic and such works in the outputs it produces
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
* Qualities basically need to be declared and such at the start, in order to be used.
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
            * doLink(URL)
                * Carries out action associated with given URL, as if it was a href of a HTML link that a user clicked
                    * Options links are treated as ```<a href="situation ID">link text</a>```, so this handles opening the specified situation
            * setQuality(qualityId, newValue)
                * Sets specified character quality to the new value, and updates UI appropriately
            * animateQuality(qualityID, newValue, options)
                * Changes value of given quality, but also displays progress bar showing how it has changed
                * 'options' is object containing options for how the bar should be displayed
                    * attributes
                        * 'from:' Proportion on along progress bar where animation starts. Float between 0-1. Default is 0.
                        * 'to:' like 'from' but for the end result. Float between 0 and 1, default is 1.
                        * 'showValue:' if 'true' (default), new value of quality displayed above progress bar
                        * 'displayValue:' only used if showValue is true.
                            * if given: given value is used above progress bar (usedful for qualities without a definition)
                            * if not given: displayValue obtained from QualityDefinition
                        * 'title:' Title of progress bar. Title of quality is used if this is not given.
                        * 'leftLabel:' and 'rightLabel:' Labels for the left/right extents of the progress bar. Omitted if not given.
            * setCharacterText(content)
                * Sets block of character text appearing in the 'character' panel. Should be valid HTML.
            * clearLinks(URL)
                * Call it with an Undum link URL (section links etc) to remove all occurrences of that link from the page. Allows available options to be controlled dynamically.
    * 'Situation' object
        * Prototype of all situations in the game
            * Can be used directly (for maximum flexibility)
            * Can be used via SimpleSituation (more functionality, easier to test)
        * Constructor
            * new Situation(options)
                * options array specifies implementations for the methods
                * also specifies values for attributes
                    * 'tags' can be specified as a single string, or a list of strings
                        * If passed as a single string, it is split at spaces, commas, and tabs.
                    * 'optionText' can be given as a string instead of as a method implementation
                        * Specified text will be returned by optionText(...)
        * Attributes
            * tags
                * a list of string 'tags' that this situation is labelled with
                * accessed by System.getSituationIdChoices
            * displayOrder
                * numeric value (defaults to 1)
                * When displaying lists of implicitly generated choices, options are displayed in increasing displayOrder.
            * priority
                * number (defaults to 1)
                * When implicitly generating lists of choices, they are considered in descending priority order
                    * Higher priority situations prioritized over lower priority situations
            * frequency
                * Number (defaults to 1)
                * When implcitly generating lists of choices, and there are more choices available than slots, random situations are chosen, probability based on frequency value.
        * Methods
            * enter(character, system, from)
                * called when Undum enters a situation
                    * character and system are instances of Character and System.
                    * 'from' is a string containing ID of previous situation
                * Commonly overridden
                    * Generally used to describe current situation (via system.write()) and update character (by system.setQuality(), or updating character sandbox)
            * exit (character, system, to)
                * called when Undum exists this situation
                    * similar parameters to enter, except 'to' refers to the situation that the player is being sent to.
            * act(character, system, action)
                * character and system are same as before
                * 'action' is a string, containing the action identifier corresponding to the link the player clicked
                    * Common to use an if/switch statement to carry out appropriate response for the given action
                    * SimpleSituation provides switching behaviour by default
            * canView(character, system, hostSituation)
                * Called to determine whether or not this situation should be visible from the specified 'hostSituation'.
                * true if it should be visible, false otherwise.
            * canChoose(character, system, hostSituation)
                * called by System.writeChoices() to see if there should be a link to this situation from the current hostSituation
                * true if a link should be clickable, false otherwise.
        * SimpleSituation
            * like Situation, but easier to output content in 'enter()' method, and easier to switch between functions via the 'act' method. 'exit' method is identical to Situation
            * Constructor
                * new SimpleSituation(content, options)
                    * Creates a new simpleSituation that displays given 'content' when 'enter()' is called.
                        * content may be plaintext or a function
                            * function must return a string to be used as an output
                    * 'options' dictionary can define
                        * 'enter:'
                            * Providing an 'enter' function allows additional behaviour to be added to the existing 'enter' method.
                            * Called in addition to/after the default 'enter' method (doesn't override it)
                            * Should have same form as Situation.enter
                        * 'act:'
                            * Defining an 'act' function allows additional behaviour to be added to 'act' method
                            * Called in addition to/after default 'act' (no override)
                        * 'exit:'
                            * There's no default behaviour for 'exit', so defining an 'exit' function here will be the only 'exit' behaviour for this object.
                        * 'heading:'
                            * Specified 'content' is written out verbatim, but a defined 'heading' will be displayed before the 'content' text.
                            * may be plaintext or a function
                                * function must return a string to be used as an output
                        * 'actions:'
                            * Object that maps action identifiers to responses
                                * Responses
                                    * Can be display content to write to the screen
                                    * Can be a function processing the request
                                        * Need the same signature as Situation.act()
                                        * Will only be called if the situation recieves a call to acct with the corresponding identifier
                                            * only called if the action happens
                        * 'choices:'
                            * Optional list of tags and situation-IDs
                                * tags should be prefixed with '#', to distinguish themselves from situation IDs
                            * If used
                                * SimpleSituation will use these to output an implicit block of choices after content
                                * May also define 'minChoices:' and 'maxChoices:' to define how many choices may appear.
    * QualityDefinition
        * Basically state how/where to display a quality in the character panel.
        * Constructor
            * new QualityDefinition(title, options)
                * rarely used, derived type of QualityDefinition generally used instead
                * title
                    * string, can contain HTML. used to label the quality in the character panel.
                * options may include
                    * 'priority:'
                        * String used to sort qualities within groups (display order sorted by this string).
                        * If not given, they are sorted alphabetically by title.
                        * Generally supposed to not give it, or use a 0-padded number ('00001' etc.)
                    * 'group:'
                        * Identifier of a group in which to display this quality
                        * If given, that group must be defined in undum.game.qualityGroups
                    * 'extraClasses:'
                        * CSS classes attached to the tag surrounding the quality when displayed.
        * Methods
            * format(character, value)
                * Called to get a human-readable string to represent the given quality for the given character
                * Returns
                    * String to represent the value (if it should be displayed)
                    * Empty string (if the value doesn't need to be displayed)
                    * null (quality removed from character panel if null returned)
        * Derived classes
            * IntegerQuality
                * rounded down to nearest integer when displayed
            * NonZeroIntegerQuality
                * like IntegerQuality, but only displayed when it has a non-zero value
            * NUmericQuality
                * Quality displayed as floating-point value
            * WordScaleQuality
                * Array of strings, when the value is *x*, the string at index *x* is displayed.
                    * May define an offset, so, when value is *x*, string at index *x*+*offset* is displayed
                * Constructor
                    * new WordScaleQuality(title, words, options)
                        * title: title for the quality
                        * words: Array of strings which will be used to represent the value
                        * options:
                            * supports same options as QualityDefinition, as well as:
                            * offset:
                                * default is '0'. 
                                * when offset = *o*, when representing value *x*, the string at index *x*+*o* is shown.
                            * useBonuses:
                                * if true (default), values outside the given range of words will be constructed from the word and a numeric bonus
                                    * when *x* above range: "highest word+*x*"
                                    * when *x* below range: "lowest word-*x*"
            * FudgeAdjectivesQuality
                * like WordScaleQuality, but uses 'terrible','poor','mediocre','fair','good','great','superb'.
                    * -3 offset by default ('fair' at 0)
                * Constructor
                    * new FudgeAdjectivesQuality(title, options)
                        * same parameters as WordScaleQuality (but 'words' is pre-defined, and 'offset' defaults to -3)
            * OnOffQuality
                * Like a boolean.
                    * If value is 0, it's not displayed. If value is not 0, the quality is displayed (without a value)
                * Constructor
                    * new onOffQuality(title, options)
                        * similar title/options as default QualityDefinition
                        * Has one extra 'option'
                            * 'onDisplay:' if given, the specified string is displayed when the quality is non-zero.
            * YesNoQuality
                * Also like a boolean
                    * Displays one string if value is 0, displays other string if value is not 0.
                * Constructor
                    * new yesNoQuality(title, options)
                        * similar title/options as default QualityDefinition
                        * Has two extra 'option's
                            * 'yesDisplay:' String displayed to indicate non-zero value. Defaults to 'yes'
                            * 'noDisplay:' String displayed to indicate zero value. Defaults to 'no'
    * QualityGroup
        * Defines a set of qualities to display together in character panel, under optional subheading
        * Constructor
            * new QualityGroup(title, options)
                * title: string used as a subheading for the qualities in this group. may be 'null', indicating no heading is needed.
                * options: object with given optional parameters
                    * priority:
                        * string used to sort quality groups (like the Situation priority)
                    * extraClasses:
                        * extra CSS classes to attach to the CSS tag surrounding this quality group when displayed.
* Saving/loading
    * May save/load game via UI whilst playing the game
    * How saving/loading works
        * Saves the sequence of actions made from the start of the game to the state the game was in when saved
        * Loading a saved game effectively replays that given sequence of actions
            * Things that help saving/loading to work
                * The ```System.rnd``` random number generator starts from a specified seed (and the starting seed is saved with the game), so the sequence of RNG can be reproduced exactly as it was, to ensure that the gamestate can be reproduced.
                    * Use this instead of JavaScript's ```Math.random```
                * The ```System.time``` method co-ordinates with loading/saving, to ensure that the timing picks up where it left off.
                    * Use this instead of JavaScript's ```Date``` object
                * Can prevent certain elements from being triggered whilst replaying a saved game
                    * ```undum.isInteractive()``` returns true if game is being played normally, false otherwise: only fire alerts etc if this is true.

## sources etc
* https://idmillington.github.io/undum/