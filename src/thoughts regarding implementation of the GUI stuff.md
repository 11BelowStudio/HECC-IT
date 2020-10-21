# Stuff I need to do to implement the OH-HECC GUI stuff

okay so I might need to hold off some of the optimization,
just in case I prematurely optimize it such that it becomes
nigh-impossible to implement the things I need to implement for OH-HECC

* Things that need to be added to the HECC-SPECC
    * Declaring variables in metadata
        * ```!Var: exampleVariable1 = "String" //oh look a string```
        * ```!Var: exampleVariable2 = 42```
        * ```!Var: exampleVariable3 = 2.71828```
        * ```!Var: exampleVariable4 = true```
        * ```!Var: exampleVariable5``` defaults to value of 0
        * ```!Var: exampleVariable6 = asdf``` converts to "asdf"
    * Scope for properly defining comments and such
        * In metadata
            * ```// user-defined comment line goes here```
                * All ;; lines will be grouped together when read by OH-HECC
                * Will be output at the end of the OH-HECC metadata
            * ```line without any sort of prefix```
                * OH-HECC outputter will insert these before each section of metadata
                to indicate what that section is meant to be.
                * These will not be read by OH-HECC. at all. they'll just get wiped instead.
        * In passages
            * `::PassageName <pos> [list of tags] //comment to end of line`
            * `;;` can conclude a passage, following lines will  
                * These comments will be associated with the preceeding passage
* What I'll need to include
    * Variable object
        * Attributes
            * String variableName
            * String defaultValue
            * String comment
        * Methods
            * constructor
                * string input, converted into the 3 components of it
            * converting it to hecc code variable declaration
            * converting it to heccer variable declaration
    * Model of the network of passages
        * A map of passages
            * Key: passage names
            * Values: passage objects
    * Rendered Passages
        * Attributes
            * OH-HECC Passage
            * Collection of ArrowObjects
                * 
    * ArrowObject
        * Attributes
            * Vector2D from
            * Vector2D to
    * OH-HECC passage
        * Attributes
            * Name
            * Unparsed content
            * Tags
            * Position
            * Set of the names of passages it's linked to
            * Comments section
        * Methods
            * 
            
* Sequence of using it
    * Opens OH-HECC
        * Selects .hecc file to open
            * .hecc file contents parsed
                * metadata object created (preserving comments)
                    * String title
                    * String author
                    * String ifid
                    * ArrayList<Variable> variables
                    * String multiline comment
                * set of passage names made
                * map of passage names/hecc passage objects
                    * hecc passage objects
                        * String name
                        * Vector2D position
                        * ArrayList<String> tags
                        * String top-line commment
                        * String raw data
                            * everything to first ;; or :: line
                        * String trailing comment
                            * everything between first ;; line and next :: line
                        * Set<String> linked passages
            * model constructed 
                