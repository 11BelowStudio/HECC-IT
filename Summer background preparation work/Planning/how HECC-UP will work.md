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
        * Start appending those lines to the 
        
## Objects

### ParsedPassage

* Used to hold parsed passage content
