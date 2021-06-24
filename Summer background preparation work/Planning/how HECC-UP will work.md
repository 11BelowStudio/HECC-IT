# How HECC-UP will work

## Parts of HECC-UP

* Some sort of front-end allowing
    * Users to declare where a .hecc file is
    * Specify where they want the hecc game to be output to
    * Get it to parse the stuff
    * Output finished game/error message
* Back-end things
    * Parser class
    * ParsedPassage class
        * Used to hold the Passages as they are being parsed
* 

## Stuff to be checked whilst parsing (regexes etc)

* Things of note
    * Java regex ('Pattern') documentation
        * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
    * regex tester
        * https://regexr.com/
        * https://regex101.com/
* Loading the HECC file
    * Open it as an ArrayList of strings (delimited at newlines)
* Iterate through that ArrayList of strings
    * Lines before first passage name
        * Ignore them (for now)
* Passage names
    * Regex
        * Flags to include at the start
            * `(?m)`
                * Allows multiline stuff
        * `^::.+`
            * Matches if
                * starts with `::`
                * Then contains 1 or more of anything that isn't a line break
        * `^::[\w]+[\w- ]*[\w]+` or `^:{2}[\w]+[\w- ]*[\w]+`
            * Matches if
                * Starts with `::`
                * Then contains 1 or more
                    * 'word' characters
                * Then contains 0 or more
                    * 'word' characters, hyphens, or spaces
                * Followed by 1 or more
                    * 'word' characters
        
    * Search for these first, construct `ParsedPassage` objects using those names
        * Remove them from the ArrayList
* Passage content
    * First, omit lines containing just whitespace (leading whitespace before passage content)
        * `^\s*$`
            * Matches if the line only contains whitespace
    * When the first line that isn't entirely whitespace is reached
        * This is passage content.
    * Finding passage links
        * Raw links
            * `\[\[[\w- ]*[\w]+\]\]`
                * Opens with `[[`, contains valid passage name, ends with `]]`.
            * `\[\[[^\[\]\|]+\|[\w- ]*[\w]+\]\]`
                * Opens with `[[`
                * Then contains the text displayed for the link (which must be at least 1 character long, and may not contain `[`, `]`, or `|`)
                * Then contains a single `|`
                * Then contains a valid passage name
                * Ends with `]]`
        
## Objects

### MainInterface

* Attributes
    * `Parser` object
    * Needs a `JFileChooser` object
        * https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
        * https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html

### Parser

#### Just look at [src/misc_testing_and_such/hecc_up_testing/HeccUpParserTest.java](https://github.com/11BelowStudio/HECC-IT/blob/all_the_summer_prep_work_archived/src/misc_testing_and_such/hecc_up_testing/HeccUpParserTest.java) instead.

* Attributes
    * `string unparsedContent`
        * A string that literally just contains the unparsed content
    * `ArrayList<String> unparsedLines`
        * ArrayList containing all the lines in the currently unparsed HECC file
    * `Map<String, ParsedPassage> parsedPassages = new TreeMap<String, ParsedPassage>()`
        * Holds passage names, along with their associated ParsedPassage object
    * `Set<String> passageNames = new TreeSet<String>();`
        * Holds all the passage names
* Constructor
    * `Parser(File fileToParse){}`
        * Must open fileToParse, and read it into `unparsedLines`
        * Throw exception if fileToParse could not be opened/read
* Methods
    * `parseContent()`
        * Basically look at `regex planning.md`



    

### ParsedPassage

* Used to hold parsed passage content
* Attributes
    * `String name`
        * The string name of the passage
    * `ArrayList<String> rawContent`
        * The raw lines of passage content
    * `ArrayList<String> parsedContent`
        * The lines of passage content after they've been parsed
* Methods
    * `ParsedPassage(String passageName)`
        * Constructor
            * Called whenever a passage is declared
        * What it does
            * Sets everything up
                * `name = passageName;`
                * `rawContent = new ArrayList<>;`
                * `parsedContent = newArrayList<>;`
    * `giveParsedContent(String contentToAdd)`
        * Appends the given String to the `parsedContent` arraylist