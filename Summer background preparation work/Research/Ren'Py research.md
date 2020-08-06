##how it works
* Game consists of all the files within the directory of the game
    * .rpy scripts used to define the actual game stuff
        * Written in the 'Ren'Py language' (somewhat similar to Python, some parts actually take Python expressions)
* Object-Oriented


##how to use it
* Each 'node' is defined as a ```label```
    * Syntax for labels
        * Defining them
            * ```
              label labelName:
                [label content goes here]
              ```
        * Sending player to another label
            * ```jump otherLabel```
                * sends player to the start of the defined other label
                    * call stack not modified
            * ```call otherLabel```
                * adds the other label to the top of the call stack
                    * When a ```return``` statement is reached in that label (assuming that the other label is at the top of the call stack), execution of this label resumes from after this 'call' statement.
        * Ending label
            * Explicitly ending with a ```return```
                * If this label was 'called', this sends the player back to where this label was called
                * If there's no calling label to send the player back to, this ends the game.
            * Implicitly ending (by just starting to define the next label)
                * Player sent to the following label
    * Player will start at the label defined as ```label start:```
* Branching narratives etc
    * Done via a 'menu' within a label
        * ```
          label labelThatBranches:
            [label content]
            menu:
                "Text for option 1":
                    jump option1_label
                "Text for option 2":
                    jump option2_label
                "Text for option 3":
                    jump option3_label
          
          label option1_label:
            [content for this branching narrative]
            jump mergeLabel
          
          label option2_label:
            [content etc]
            jump mergeLabel
          
          label option3_label:
            [content, but this one doesn't merge back with the others]
            return
          
          label mergeLabel:
            [content of the label that merges the narratives of options 1 and 2]
            return
          ```
* ```Character``` objects
    * Used to control the look+feel of dialogue and narration
    * Defining them
        * ```define characterVariable = Character("name", *kind = parentCharacterToInhert*, *other arguements*)```
            * name string displayed at the top of the textbox when this character is 'speaking'
            * the 'kind' variable allows this character to inherit attributes from a pre-defined character
            * A lot of other attributes can be used here, such as defining the colour of the character's name, 
            
##what options it gives the user
* Some options at the start
    * Project name
    * Resolution for it (still allows scaling, but the chosen resolution will be used as default)
        * Predefined choices are 16:9 (default GUI optimized for 16:9), but custom resolution is allowed
    * Theme options (can be changed later)
        * Choice of dark/light backgrounds (like dark mode/light mode)
        * several accent colours
        * windowed/fullscreen/'planetarium' view
        * text speed
* Image stuff
    * Supported formats
        * PNG/WEBP supported for characters
        * JPG/JPEG/PNG/WEBP supported for backgrounds
    * Defining images
        * Image names consist of a tag and (optional) attributes
            * ```tag attribute anotherAttribute```
                * The tag is the first part of the name (everything before first space)
                * attributes are the strings following the tag (again, seperated by spaces)
                    * attributes are optional
                * tags/attributes must start with a letter, can contain letters, numbers, and underscores
            * Only one image with a certain tag may be displayed at a time
                * Cannot display ```bob happy``` and ```bob angery``` at once (as they share the 'bob' tag)
        * Can allow Ren'Py to define them automatically or you can do it manually
            * Automatically
                * put images into the ```/images``` directory for the Ren'Py project
                * Save filenames in the format ```tag attribute attribute```
                    * subdirectories/extensions/case ignored (converted to lowercase)
                    * Image can then be accessed as ```tag attribute attribute``` in the code
                * examples
                    * "/images/bg meadow.jpg" -> ```bg meadow```
                    * "/images/rob/Rob ANGERY.png" -> ```rob angery``` 
            * Manually
                * Use the ```image``` statement at the top of the Ren'Py script
                    * ```image tagName attribute = "name of the image file in the 'images' folder being used.png"```
    * Displaying images
        * Backgrounds: ```scene``` keyword
            * ```scene backgroundImageNameGoesHere```
                * replaces currently displayed background with this background
        * Other images: ```show``` keyword
            * ```show imageNameGoesHere```
                * Pretty much just displays the defined image in the middle of the screen (bottom edge of image touching bottom edge of viewport)
                * Will automatically replace any currently-displayed images with the same tag
                    * ```
                      show bob happy
                      [stuff goes here]
                      show bob angery
                      ```
                      would display the 'bob happy' sprite, keep it visible during the stuff, and then replace 'bob happy' with 'bob neutral'
                    * the replacement image will appear where the original image was (even if the original image was moved from the default position)
            * Can also hide them with the ```hide``` keyword
                * only need to use ```hide tag```
                    * if ```bob angery``` is visible, you only need ```hide bob```, not ```hide bob angery```
        * Transitions
            * ```
              show/scene [IMAGE]
              with [TRANSITION]
              ```
            * several premade transitions (fade/dissolve) that can be used to make it smoother
        * Positions
            * ```
              show [IMAGE] at [POSITION/TRANSFORMATION]
              ```
                * Several predefined positions (left, right, center (default), truecenter (centered x and y))
                * Custom positions
                    * Can instead use the 'animation and transformation language' of Ren'Py to define the position
                    * Can use the 'Transform' function to define a position
                * Can use this to move a currently shown image
* Audio
    * Formats
        * Supports .ogg, .opus, and .mp3
    * Using it
        * Referencing audio files
            * Referred as a string relative to the game directory
        * Music
            * ```play music "filename"```
                * starts automatically
                * will loop
                * can add ```fadeout``` and ```fadein``` to fade out current music/fade in new music
            * ```queue music "filename"```
                * currently-playing music will be replaced by the queued track instead of looping when it reaches the end
                * new music will loop as usual once it starts
            * ```stop music```
                * stops the music
        * sound effects
            * ```play sound "filename"```
                * simply plays that sound
                * doesn't loop

##what features it has
* Records player history (and plenty of options to save/restore progress)
    * Options to save/load at any point, no extra work from writer required to implement it
        * A quicksave/quickload system
        * A persistent save/load system (with a lot of save slots)
    * Can use the scrollbar to go back/forward
        * Scroll up to go back to previous parts of the game
        * Scroll back down to get back to the node you scrolled up from
* Image support
    * Can just put images into the /images folder, and they'll work

##what features it's missing
*

##how the interior logic and such works in the outputs it produces 
*

##sources etc
* [1] *Ren'Py* (7.3.5), T. Rothamel. Accessed: Aug. 6, 2020. Available: https://www.renpy.org/