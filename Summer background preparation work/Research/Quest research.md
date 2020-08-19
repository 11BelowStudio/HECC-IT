# Quest

~~will only be looking at the 'gamebook' aspects of Quest, as the 'text adventure' bits aren't entirely relevant to the task at hand~~

Actively discouraged from using this to make 'gamebooks' (see http://docs.textadventures.co.uk/quest/tutorial/creating_a_gamebook.html), so imma probably not look at this in any more detail.

## how it works
*

## how to use it
* You don't.
    * Actively discouraged from making 'gamebooks' with it.
        * Official documentation for the gamebook (http://docs.textadventures.co.uk/quest/tutorial/creating_a_gamebook.html) states:
            * *NOTE: Rather than using the Game Book feature of Quest, we would suggest you use either the full product (and turn off the game panes and command bar so the player just uses hyperlinks), or use Squiffy. Quest Text Adventures have a full world model, where objects and rooms relate to each other in a meaningful way, and have numerous features not supported by Game Books. Squiffy has no world model, but is great for creating multiple choice games that focus on text and story, and will produce a game that can be run in any browser, without the need for a dedicated server. Quest Game Books represent the worst of both worlds.*
            * **tl;dr** If you're making a gamebook, use Squiffy instead.

## what options it gives the user
* Can make 'gamebooks' and 'text adventures'
* Creating a new game
    * 'Create new game' dialog
        * User asked if they want to create a 'text adventure' or a 'gamebook'
            * text adventure: zork etc
            * gamebook: hypertext game
                * will be focusing on the 'gamebook' stuff it has
        * User asked what language they want their game to be in
        * User asked to specify a name for it
        * User asked to specify where they want to save it (as .aslx file)
* Editing a game
    * Can edit game via GUI
        * Select a page from the sidebar
        * Can edit
            * Page title
            * Text of the page
            * Options
                * Text of the option
                * What page the option leads to
                * Can add/delete options
    * Game is basically an XML-style group of objects
    

## what features it has
* Can export it as a .quest/.cas file to be played via quest 5
* Options to create text adventure games and also gamebooks
* Appears to have scripts and attributes (but those only seem to be properly supported by the 'text adventure' settings)

## what features it's missing
* Any sort of export to web (needs a dedicated server to play on the web)
* 

## how the interior logic and such works in the outputs it produces
* Uses ASLX format (based on XML)
    * Game schema
    * ```<asl version="550" templatetype="gamebook"> ... </asl>``` wraps game content
        * ```<include ref="GamebookCore.aslx"/>``` reference to the 'GamebookCore.aslx' core library that Quest Gamebooks are built on
        * ```<game name="```game name goes here```">...</game>``` contains metadata about the game
            * Basically holds all the game metadata within nested tags (basically just metadata supported by the Treaty of Babel)
        * ```<object name = "objectName">...</object>``` (for each page in the game)
            * Every single page in the game is basically an object
            * Attributes nested within the 'object' tags
    * Schema used to represent pages
    * ```<object name = "PageID">...<object>```
        * ```<description>``` Text displayed to the user for this page ```</description>```
        * ```<options type = "stringdictionary">...</options>``` defines options
            * ```<item>...</item>``` holding each option in key/value pairs
                * ```<key>```ID of the page this option leads to```</key>```
                * ```<value>```The text for the option```</value>```
        * Starting page contains a player object
            * ```<object name = "player"><inhert name="defaultplayer"/></object>```

## sources etc
* https://textadventures.co.uk/quest/
* http://docs.textadventures.co.uk/quest/tutorial/