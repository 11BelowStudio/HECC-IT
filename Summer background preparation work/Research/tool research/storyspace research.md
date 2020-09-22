# StorySpace 3
## how it works
* Documents saved in an XML format [1]
    * Appears to have some compatibility with 'Tinderbox' (also by Eastgate, and also unusable on Windows)
* 'Topographic writing'[2]
    * Nodes called 'writing spaces'
    * Several views of the nodes [3]
        * 'Map view'
            * links between the nodes
        * 'Tree' view/'Chart' view
            * Horizontal flow chart
        * 'Outline' view
            * Vertical list of node names
* Implementation [4]
    * StorySpace 3 written in Objective C++
    * Classes
        * Primitive Hypertext, Link, and Node classes
            * Pretty much everything needed to use Node
        * 'NodeFacade' and 'HypertextFacade' classes
            * Held 'the underlying Node or Hypertext'
            * Used to 'provide small and focused slices of functionality'
                * 'NodeLinks provides access to links'
                * 'NodeIndexer provides... support tf-idf similarity services'
                * 'NodeDeleter.. methods for deleting Nodes'
            * They 'simply wrap a pointer, can be created and thrown away at will'
                * Might be useful to help avoid refactoring stuff later on
    * Guard Fields
        * Guards are attributes on the links themselves
        * What it can track
            * Visited nodes
                * visited(A)
            * Unvisited nodes
                * unvisited(B)
            * Whether or not certain text has been clicked
                * clicked(Text)
            * Attribute-Value stores (from Tinderbox support)
                * $Variable(/Note) [comparison function goes here]
        * Operators for checking guards
            * &: and
            * |: or
            * !: not
        * Modifiers within nodes
            * $Requirements
                * Must be met before any incoming link can be traversed
                    * unvisited(this): prevents a node from being revisted
                * Can be used to implement 'shark links':
                    * if these have fulfilled requirements, user is automatically sent to them
            * $Visits
                * Counter of how many times a certain node has been visited
            * $OnVisit
                * Action that may be performed once a node is visited
                
        

## how to use it
* [5]
    * Writing spaces in a map
        * Can contain text, graphics, sounds, etc
        * Opening writing space named 'start' by default
            * In map view, you can see the WS title (and a bit of the contents text)
    * Draw line between a writing space:
        * link between spaces formed
        * Dialog box appears
            * 'From', 'To', 'type', 'URL', and 'Guard' dialog pane appears
                * https://eclecticlightdotcom.files.wordpress.com/2015/12/sspacea03.png?w=940[5]
                * 'From': what writing space it's from
                * 'To': what writing space it leads to
                * 'Type': What type of link it is
                    * 'text': Link in the form of text on the 'from' WS that you click on
                        * has a field to enter the text that is clicked
                * 'URL'
                * 'Guard': the guard conditions for the link
* Reading
    * https://www.youtube.com/watch?v=djIrHF8S6-Q
    * https://www.youtube.com/watch?v=KXFEqyXrbqU

##what options it gives the user
* Options for different views
* Guard conditions
* Images
    * https://eclecticlight.co/2015/12/12/storyspace-3-using-guards-to-structure-reading/
        * Need 3 versions of images (standard, 512*512 small view, 128*128 thumbnail)
    

## what features it has
* Guard fields
    * Allow readers to follow a link only if specified conditions are met
    * "Access to links may depend on whether a reader has previously visited a given writing space, or selected a specific anchor." [3]

## what features it's missing 
* Only usable on mac [1]
    * Cannot use it on non-mac systems
    * Allegedly can be translated into HTML
* Formatting
    * 'paragraph and style formatting are not available.'[3]
        * might have been added in later versions
* Need to download a runtime environment to run them
* Very limited web export options [2] (refer to paragraph 71)

## How it saves stuff
* Looked at a sample .tbx from https://eclecticlight.co/2015/12/12/storyspace-3-using-guards-to-structure-reading/ (same site as the one [5] is hosted on)
* xml files (.tbx)
    * Within ```<tinderbox>``` tag
    * Defines ```<attrib>``` first
        * Names, parent attributes, whether or not they're editable, whether or not they're visible in editor, what type they are (probably refers to a constant int within the source code), what 'kind' they are, whether or not they 'canInherit', and default values.
    * Then defines ```<color>``` tags
        * Colours with names and hex codes, probably intended for user 'themes' or something in the editor (and also for formatting output?)
    * Then defines a ```<menu>```, unsure what that does
    * Defines ```<linkTypes>```
        * Name, label (if different to name), whether or not it's visible, whether or not the label is shown, whether or not it's 'required', a colour hex code, a colorString (probably referring to the earlier <color> definitions, maybe to set the colour(?)), and a 'style' int (probably also referring to a const int in the program)
    * Defines ```<item>```s
        * Most likely the 'writing spaces'
        * ```<item>``` tag itself defines an ID (unique integer), a string for the 'Creator' of it, and a 'proto' string (probably a prefab?)
        * Multiple ```<attribute>``` tags within item
            * ```<attribute name = "whateverItsCalled>value</attribute>```
            * name refers to a defined ```<attrib>``` with the same Name from earlier on
        * ```<text>``` tag containing a string (textual content of the writing space)
        * ```<rtfd>``` tag containing a very long incomprehensible string, probably just raw data saved as a string
    * Defines ```<adornment>```s
        * Similar to ```<item>```, but instead of having ```<text>``` and ```<rtfd>```, it has ```<image>```
            * ```<image>``` is a long incomphrensible string, probably just raw data as well
    * Defines ```<links>```
        * contain a name (referring to defined ```<linkType>```), sourceid (referring to source node), sourcecreator, 'sstart, slen, style, arrowtype' (probably used for formatting in the editor), 'destid' (referring to destination node), 'destcreator', 'color' (probably also for formatting purposes), and 'guard' conditions (if needed)
    * Defines ```<macros>```
        * Not sure what these are supposed to be used for
    * Defines ```<preferences>```
        * Looks like saved preferences for the project in the editor
        * Each preference has its own tag, with a value for it in the middle
    * Defines ```<windows>```
        * Probably also editor preferences. Defines bounds of it, and 'ruler'
        * Defines ```<tabs>``` within windows
            * Whether or not it's selected, what it is, settings, etc
            * Looks like more editor preferences
    * Defines ```<utilityWindows>```
        * Probably supposed to be like the windows but just for those opened by utilities.
    * Defines ```<tabs>``` again
        * Looks like the same stuff as the tabs defined in the ```<window>``` earlier on
    * ```<searches>```
        * Not entirely sure, probably used to record defined searches for paths through the hypertext
    * Closing ```</tinderbox>``` tag at the end
* Looks like it's done in this order so, when it's parsing stuff, it can ensure everything it uses (like attributes) have actually been declared before being referenced within nodes.
    * Might need to use that sort of structure in my product
* order of parsing
    * definitions -> content -> links between content -> preferences
        * raw attributes -> types of colours -> types of links -> content -> links between content -> editor preferences and such
* **LINKS ARE SEPARATE OBJECTS FROM THE NODES THAT THEY LINK TOGETHER**

    

## Sources etc   
* [1]"Storyspace: Storyspace", *eastgate.com*, 2020 [Online] Available: http://www.eastgate.com/storyspace/ [Accessed: 03- Aug- 2020].
    * http://www.eastgate.com/storyspace/

* [2]B. Barnet. "Machine Enhanced (Re)minding: the Development of Storyspace", Digital Humanities Quarterly, vol. 6, no. 2, 2012. Available: http://hdl.handle.net/1959.3/246799. [Accessed 3 August 2020].
    * DOI: 1959.3/246799
    * http://www.digitalhumanities.org/dhq/vol/6/2/000128/000128.html 
    
* [3] C. Keep, T. McLaughlin, and R. Parmar. "Storyspace" *The Electronic Labyrinth*, 1995 [Online] Available: http://www2.iath.virginia.edu/elab/hfl0023.html [Accessed: 03- Aug- 2020].
    * http://www2.iath.virginia.edu/elab/
    
* [4] M. Bernstein, "Storyspace 3" in *HT '16: Proceedings of the 27th ACM Conference on Hypertext and Social Media*, July, 2016, Halifax Nova Scotia Canada [Online]. Place of publication: Association for Computing Machinery, New York, NY, USA, Year. Available: Association for Computing Machinery Digital Library, https://dl.acm.org/doi/10.1145/2914586.2914624. [Accessed: 03- 08- 2020].

* [5] H. Oakley, "Getting started with Storyspace 3", *The Eclectic Light Company*, 2015 [Online] Available: https://eclecticlight.co/2015/12/08/getting-started-with-storyspace-3/ [Accessed: 03- Aug- 2020].
    * https://eclecticlight.co/tag/storyspace/