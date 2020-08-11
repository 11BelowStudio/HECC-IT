#ChoiceScript

## how it works
* Built on Node.js
    * Basically server-side javascript
* Running ChoiceScript locally requires you to launch a ChoiceScript server in localhost, and keep it running whilst playing the ChoiceScript game
* ChoiceScript games saved as a folder of .txt files
    * choicescript folder/web/mygame

##how to use it
* Must first launch ChoiceScript server with run-server.bat
* Writing stuff
    * Important files
        * startup.txt
            * First file which is opened when playing the game
            * Things that need to be defined at the start (before anything else)
                * Title
                    * ```*title Title Goes Here```
                * Author
                    * ```*author Author Name Goes Here```
                * Scenes
                    * ```
                      *scene_list
                        startup
                        namesOfOtherTxtFilesUsedAsScenes
                        etc
                      ```
                        * Basically defines which .txt files the game needs to be concerned with
                * declaring variables
                    * ```*create variableName value```
            * Can then start writing the game

##what options it gives the user
*

##what features it has
*

##what features it's missing
*

##how the interior logic and such works in the outputs it produces
*

##sources etc
* https://www.choiceofgames.com/make-your-own-games/choicescript-intro/
* https://nodejs.org/en/about/