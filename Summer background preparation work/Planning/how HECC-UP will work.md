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
        * `(^::[\w- ]*[\w]+)` or `(^:{2}[\w- ]*[\w]+)`
            * Matches if
                * Starts with `::`
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
        
## Objects

### MainInterface

* Attributes
    * `Parser` object
    * Needs a `JFileChooser` object
        * https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
        * https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html

### Parser

* Attributes
    * `string unparsedContent`
        * A string that literally just contains the unparsed content
    * `ArrayList<String> unparsedLines`
        * ArrayList containing all the lines in the currently unparsed HECC file
    * `TreeMap<String, ArrayList<String>> unparsedPassages`
        * Holds passage names, along with the unparsed passage content
    * `TreeMap<String, ParsedPassage> parsedPassages`
        * Holds passage names, along with their associated ParsedPassage object
* Constructor
    * `Parser(File fileToParse){}`
        * Must open fileToParse, and read it into `unparsedLines`
        * Throw exception if fileToParse could not be opened/read
* Methods
    * `parseContent()`
        * Step 1: find passages
            * Declare
                * ```
                  boolean firstDeclarationFound = false;
                  string currentPassageName;
                  ArrayList<String> currentPassageContent = new ArrayList<>();
                  Pattern passageNamePattern = Pattern.compile(
                  ```
            * Read through contents of `unparsedLines`
            * If the line is a passage declaration
                * if `firstDeclarationFound` is still false
                    * set it to true
                * else
                    * add a new entry to `unparsedPassages`, with `currentPassageName` and `currentPassageContent`
                * Clear `currentPassageContent`
                * Set `currentPassageName` to the name of this newly-declared passage

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