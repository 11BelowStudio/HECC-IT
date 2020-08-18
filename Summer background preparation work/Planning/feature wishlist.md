# 'wishlist' of features fpr the hypertext game tool


##Necessary stuff
### The tool
* Must be able to write 'passages' containing content for the story
* Must have 'links' between the 'passages'
* Must be able to export the game in a playable format
* 

### The outputs
* Passages must be displayed to the user
* The links between the passages must also be displayed to the user
* A user must be able to use a link to navigate between two distinct passages
* Should be playable via web (least work required for running output, and for playing output)

##Desirable stuff
### The tool
* A GUI of some description
    * Likely to take a similar form as Twine
    * Probably built off a similar MVC architecture as a bunch of the games I've made this year
        * Need to work out how to do viewport scrolling/support resizing
        * And also saving the state of the model
    * Overview of connected nodes
    * Can click on a node to edit it
        * Swing dialog box
    * Menu bar of some description
* An option to use a purely code-based interface (for power users)
* Compliance with the Treaty of Babel
* Saving/loading WIP stuff
* Some form of automatic debugging (ensuring that there's no infinite loops or something like that)
* 

### The outputs
* Some method of tracking user history (sequence of visited passages)
* Saving/loading games in progress
* Keeping the entire gameplay within a single .html page (updating the content of that page with each passage instead of going between pages)
    * Could opt to keep all the JavaScript/CSS needed within that page (like twine), or 'include' it from external files within the same directory as that HTML page (like squiffy/undum)
* 

###Cool bits of additional functionality (for both ends)
* Variables
    * Some way of declaring them
    * Some way of actually using them
        * Conditional statements
        * Updating them
        * Outputting them to the user
    * Type validation (maybe)
* Having 'passages' and 'sub-passages'
    * 'sub-passage' as sub-sections of a main 'passage'
        * clicking on a 'sub-passage' link adds additional content to the main 'passage' stuff currently being displayed
        * any visible sub-passages disappear along with parent passage when navigating to a different passage.
* Guard conditions
    * Might fall under the umbrella category of 'conditional statements', but it would be nice to have some method of ensuring people can only go to a given passage if they satisfy a particular condition.
* Options for saving/loading
    * Maybe could allow the player to save/load whenever
    * Or I could allow the writer to override this, banning the player from saving/loading whenever, and force saves/loads at specific points (or just not save at all).
* Timers
    * Allow writer to
        * create a timer of sorts (maybe allow them to define 'code' that will fire every *x* milliseconds or something)
        * a method of 'stopping' the timer
            * Command to stop it
            * Automatically stop the automatically-firing code when changing passages as well
* Formatting
    * Content input
        * Allow users to write in plaintext/markdown (to convert to HTML at runtime)?
        * Allow users to write in HTML?
            * Could allow users to insert HTML elements in the plaintext/markdown input
    * Content formatting
        * Allow user-defined CSS?
    * Images
        * Could include some ease-of-use things for images (in case people don't want to deal with defining it via HTML)
            * Something that allows a user to reference an image file in an 'images' sub-folder of the folder containing the output game
            * Something that allows a user to reference an image via URL
            * A dedicated CSS page for base64-encoded images, ability for images to be easily referenced from it, with some functionality for the images to be encoded here automatically
                * User provides an image and an identifier for it
                * A ```.identifier{}``` CSS element is automatically added to base64 CSS, similar to https://twinery.org/cookbook/images/harlowe/harlowe_images.html
                * That image can then be easily referenced elsewhere
                * Might need to also implement a method for easily previewing/removing these base64 encoded images
    * Customizing how stuff gets displayed to the user
        * Defining special animations when transitioning between certain nodes