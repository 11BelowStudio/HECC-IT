# **src/utilities**

These are some utility classes, performing some pretty generic functions, not initially made for use within HECC-IT,
but they're useful enough for my purposes, so, they're here.

## What these classes are

* [AttributeString](./AttributeString.java)
    * A generic class, which is intended to simplify the process of making a string that has:
        * a string prefix
        * a value (generically typed, but represented as a string)
        * a string suffix (optional).
    * I made this class back in late 2019, when I was taking CE203 (Application Programming), as a utility class 
      to make it easier for me to show stuff to the player in the 2nd assignment, but back then it was in the form of
      a subclass that extended JLabel. I refactored it into a class that just had the string (not the JLabel stuff)
      at some point in early 2020.
        * This class has remained unchanged in HECC-IT (well, besides the addition of JavaDoc comments)
    
* [ImageManager](./ImageManager.java)
    * A class that can read image files as static resources; practically, this means the images will be included within
      the HECC-IT .jar file, without having to be read from an external folder when the program is running.
      
    * This was based on some sample code provided by Dr. Dimitri Ognibene as part of the CE218 Computer Game Programming
      module. The only change it has had since then was the addition of JavaDoc comments, and I changed some of the
      images it refers to.
      
* [TextAssetReader](./TextAssetReader.java)
    * A class that can read text files
        * as static resources
            * allowing the files to be baked into the HECC-IT .jar file
                * such as the premade files which are included in the outputs of HECC-UP
        * as needed
            * Allowing the user-defined .hecc files to be read (no need to copy the lines of code elsewhere)
    
        * as List<String>
            * For the index.html, where the outputter will need to go through it line by line to
              fill in some placeholders
              
        * as a String
            * For the other prebaked files, where they can just be regurgitated out as-is.
    
* [Vector2D](./Vector2D.java)
    * A class that represents a vector in 2D space.
    
    * This class was based on a template provided by Dr. Dimitri Ognibene,
      as part of the CE218 Computer Game Programming module. Since then, I have added a lot of functions to it,
      many of which aren't actually used by HECC-IT specifically.
      
    * However, this is vital for OH-HECC, as this is used to keep track of where everything is in 2D space,
      allowing all of the passages and such to have a position, and also allows the passageLinkObjects to have a 
      direction and an angle for their particular arrows.