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
        * `(^::.+)`
            * Matches if
                * starts with `::`
                * Then contains 1 or more of anything that isn't a line break
        * `(^::)([\w- ]*[\w]+)` or `(^:{2})([\w- ]*[\w]+)`
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
    * Finding passage links
        * Raw links
            * `(\[\[)([\w- ]*[\w]+)(\]\])`
                * Opens with `[[`, contains valid passage name, ends with `]]`.
            * `(\[\[)([^\[\]\|]+)(\|)([\w- ]*[\w]+)(\]\])`
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

* Attributes
    * `string unparsedContent`
        * A string that literally just contains the unparsed content
    * `ArrayList<String> unparsedLines`
        * ArrayList containing all the lines in the currently unparsed HECC file
    * `TreeMap<String, ArrayList<String>> unparsedPassages = new TreeMap<String, ArrayList<String>>`
        * Holds passage names, along with the unparsed passage content
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
        * Step 1: find passages
            * Declare
                * ```
                  boolean firstDeclarationFound = false;
                  string currentPassageName;
                  ArrayList<String> currentPassageContent = new ArrayList<>();
                  Pattern passageDeclarationPattern = Pattern.compile("^::.+"); //attempts to find things that start with ::, followed by 1 or more non-endline characters
                  Matcher passageDeclarationMatcher = passageDeclarationPattern.matcher("");
                  Pattern validPassageNamePattern = Pattern.compile("[\w- ]*[\w]+"); //attempts to find things that contain 0 or more alphanumeric/_/-/spaces followed by 1 or more alphanumeric/_/-
                  Matcher validPassageNameMatcher = validPassageNamePattern.matcher("");
                  
                  ```
            * Read through contents of `unparsedLines` (probably using a fori loop, not foreach)
            * If the line is a passage declaration (starts with '::') (`if (passageDeclarationMatcher.reset(unparsedLines.get(i)).matches()){}`)
                * if `firstDeclarationFound` is still false
                    * set it to true
                * else
                    * add a new entry to `unparsedPassages`, with `currentPassageName` and `currentPassageContent`
                * Clear `currentPassageContent`
                * Set `currentPassageName` to the name of this newly-declared passage
            * ```
                boolean firstDeclarationFound = false;
                string thisLine;
                string currentPassageName;
                ArrayList<String> currentPassageContent = new ArrayList<>();
                Pattern passageDeclarationPattern = Pattern.compile("^::.+"); //attempts to find things that start with ::, followed by 1 or more non-endline characters
                Matcher passageDeclarationMatcher = passageDeclarationPattern.matcher("");
                Pattern validPassageNamePattern = Pattern.compile("[\w- ]*[\w]+"); //attempts to find things that contain 0 or more alphanumeric/_/-/spaces followed by 1 or more alphanumeric/_/-
                Matcher validPassageNameMatcher = validPassageNamePattern.matcher("");
                
              
                for(i = 0; i<unparsedLines.size(); i++){ //for every line in unparsedLines
                    thisLine = unparsedLines.get(i);
                    if(passageDeclarationMatcher.reset(thisLine).matches()){ //if this line is a passage declaration
                        if(firstDeclarationFound){
                            //if the first declaration has been found, finish up the last declaration
                            unparsedPassages.put(currentPassageName,currentPassageContent);
                            //everything from the last passage is put into the map, and it's now ready to start with this newly declared passage
                            currentPassageContent.clear(); //clear the list of currentPassageContent
                        } else{
                            firstDeclarationFound = true;
                            //set firstDeclarationFound to true if this is the first declaration
                        }
                        //set up the validPassageNameMatcher to check thisLine
                        validPassageNameMatcher.reset(thisLine);
                        if(validPassageNameMatcher.find()){ //if a valid pasage name was found
                            currentPassageName = validPassageNameMatcher.group(1); //set that as currentPassageName
                            if(!passageNames.add(currentPassageName)){ //attempt to add an entry for currentPassageName to the passageNames Set
                                throw new DuplicatePassageNameException(i,currentPassageName);
                                //complain loudly if it fails due to another passage having that name
                            }
                        } else{
                            throw new InvalidPassageNameException(i, thisLine);
                            //complain loudly if this line doesn't contain a valid passage name
                        }
                        
                    } else{ //if this line isn't a passage declaration
                        if(firstDeclarationFound){ //if the first passage has been declared
                            currentPassageContent.append(thisLine); //append this to currentPassageContent
                        //} else{
                            //this is where metadata recording will go in future versions  
                        }
                    }
                }
                //now it's done with the loop
                if(firstDeclarationFound){
                    //if the first declaration has been found, finish up the last declaration
                    unparsedPassages.put(currentPassageName,currentPassageContent);
                    //everything from the last passage is put into the map
                } else{
                    throw new NoPassageDeclarationException();
                    //complain loudly if no passages were declared
                }
              ```
        * Step 2 (assuming an exception hasn't been thrown yet): analyse passages
            * ```
                Matcher speechMarkMatcher = Pattern.compile("\"").matcher("");
                Matcher contentMatcher = Pattern.compile("^\s*$").matcher("");
                Matcher linkDeclarationMatcher = Pattern.compile("(\[\[)([\w- ]*[\w]+)(\]\])").matcher("");
                Matcher indirectLinkDeclarationMatcher = Pattern.compile("(\[\[)([^\[\]\|]+)(\|)([\w- ]*[\w]+)(\]\])").matcher("");
                ArrayList<String> currentPassageContent = new ArrayList<>();
                ParsedPassage thisPassage;
                boolean noContent;
                String thisLine;
                String passageNameArray[] = passageNames.toArray(new String[passageNames.size()]);
                for(String p: passageNameArray){
                    currentPassageContent = unparsedPassages.get(p);
                    thisPassage = new ParsedPassage(p);
                    noContent = true;
                    for(String c: currentPassageContent){
                        if (noContent){
                            if(contentMatcher.reset(c).matches()){
                                continue; //skip this line if it's just whitespace
                            } else{
                                noContent = false; //otherwise, there's content
                                thisPassage.giveParsedContent("\"<p>\""); //add an opening <p> tag to the passage content
                            }
                        }
                        thisLine = thisLine.replaceAll("\"","\\\\\""); //making sure all "s are escaped in the exported version of this string
                        //TODO:
                            //Check for link declaration/indirect link declaration matches
                            //Process those into passageLinks
                                //"<a class=\\\\\"passageLink\\\\\" onclick=\'theHeccer.goToPassage(\\\\\"passageName\\\\\")\'>link text</a>"
                            //Throw UndeclaredPassageException(passageName) if passage wasnt declared
                    }
                    if (noContent){
                        throw new EmptyPassageException(c);
                    } else{
                        thisPassage.append("\"</p>\"");
                    }
                }
              ```

* open entire file as arraylist of strings

* declaration rules
    * ::Passage name may only contain alphanumeric characters spaces - and _

* find passage declaration lines
    * start with ::

* record indexes of passage declaration lines
    * perhaps arraylist of integers
    * map<index, string>

* extract passage name substrings
    * Must be at least 1 character long
        * InvalidPassageNameException
    * Must not start with space
        * InvalidPassageNameException
    * Any sequence of spaces within passage name must be followed by a hyphen/underscore/alphanumeric character
        * any trailing spaces will be omitted from passage name
    * Set up map of ParsedPassage objects
        * parsedPassageMap.add(parsedName, new ParsedPassage(parsedName);
            * DuplicatePassageNameException thrown if passages share name

* Then, for every line between passage declarations
    * Add it to the unparsed content for the passage declared above it

* Whilst going through         

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