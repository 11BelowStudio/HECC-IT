# **HECC-IT (Hypertext Editing And Creation Code Integrated Toolkit)**

### The hypertext game authoring tool for indecisive people

By Rachel Lowe

Git repo for this project: https://github.com/11BelowStudio/HECC-IT/

Itch.io page for this project: https://11belowstudio.itch.io/hecc-it

## Welcome to HECC-IT!

This is a toolkit that you can use to produce hypertext games.

You can convert .hecc files into playable hypertext games with HECC-UP.
And you can get some help making and editing your .hecc files using OH-HECC.

So let's cut to the chase.


## The technical documentation for CE301

Please go to [./Technical Documentation/README.md](./Technical%20Documentation/README.md) for the
table of contents for the technical documentation.

If you want to see the information about every single class/method/etc all individually
documented, please go to the [./JavaDocs](./JavaDocs.zip) folder for the full JavaDoc-style
documentation. You will to download and unzip that folder on your computer to actually
browse through them though.

If you wanted a brief overview of the classes in each package, please refer to the
package-level READMEs in each subfolder of [./src](./src).

If you wanted to know about how to use HECC-IT, please consult the [HECC-SPECC](./Documentation%20for%20HECC-IT/HECC-SPECC.md)
document for more information.

And if you wanted to download HECC-IT so you can use it yourself, please download and unzip
the [HECC-IT downloadable version](./HECC-IT%20downloadable%20version.zip).

## What the HECC does this do?

* You can use OH-HECC to make and edit your hypertext game in .hecc format.
    * You can also just write your .hecc file with any text editor if you'd prefer to do that.
    * Consult the 'HECC-SPECC' document if you need to see a reference for the HECC language.
        * HECC Super Precise Explanation for Creating Code
* You can use HECC-UP to convert your .hecc code into a playable HECCIN Game
    * Select your .hecc file
    * Select a folder to output your game to
    * If your .hecc file contains valid .hecc code
        * It will output the HECCIN' Game defined by the .hecc file into that folder
    * If your .hecc file does not contain valid .hecc code
        * Your game will not be exported, and it will tell you what to fix.
* You can then play your HECCIN Game
    * Open 'index.html' in the output folder you selected to play it.
        * Make sure you don't delete `heccer.js`, `hecced.js`, or `showdown.min.js`
        

## Glossary

~~Prepare for some terrible puns here.~~

* *HECC*
    * Hypertext Editing and Creation Code
        * the language you can write your game in.
* *HECC-SPECC*
    * HECC Super Precise Explanation for Creating Code
        * The specification for the .hecc language.
        * Also contains instructions for using HECC-IT.
* *OH-HECC*
    * Optional Help for HECC
        * A GUI that you have the option of using to make your .hecc code
* *HECC-UP*
    * HECC Uncomplicated Parser
        * Give it a .hecc file, and a folder to output a HECCIN Game to
        * It will then use that .hecc file to construct a HECCIN Game
        * This HECCIN Game will be saved in the specified folder.
* *HECCIN' Game*
    * HECC-Infused Nice Game
        * A generic term for hypertext games constructed by HECC-IT.
        * .hecc code -> HECC-UP -> HECCIN Game
    * Playable via browser
        * Open index.html in your browser of choice
        * Make sure you have JavaScript enabled, and that you have heccer.js and hecced.js in the same folder as index.html
* *heccer.js*
    * HECC Environment for Runtime
        * Basically the JavaScript engine that runs your HECCIN Game
* *hecced.js*
    * HECCED Exported Data
        * This holds the data that your game consists of.
            * Exported from the .hecc file by HECC-UP
        * Responsible for giving the game data to the heccer, and getting the heccer to start running the game.
    
* *showdown.min.js*
    * A markdown/html converter written in JavaScript, which is included in the outputs to handle
      the markdown formatting.

    * Showdown is available at [https://github.com/showdownjs/showdown](https://github.com/showdownjs/showdown)
      * Showdown.min.js is available at [https://github.com/showdownjs/showdown/blob/master/dist/showdown.min.js](https://github.com/showdownjs/showdown/blob/master/dist/showdown.min.js)
    * Showdown was produced by [Estevão Santos](https://github.com/tivie) and [several other contributors](https://github.com/showdownjs/showdown/blob/master/CREDITS.md)
    * Used under the terms of the [MIT license](https://github.com/showdownjs/showdown/blob/master/LICENSE), which Showdown has been distributed under.

~~It's **my** mental breakdown, so **I** get to choose the names!~~

## Legal stuff

HECC-IT is distributed under the terms of the Mozilla Public License Version 2.0,
a copy of which can be seen [here](./LICENSE.md)

The ['heccer.js'](./src/assets/textAssets/heccer.js) file in the output, being pre-written
and considered a part of HECC-IT, is also distributed under the terms of the
Mozilla Public License Version 2.0, by me.

The ['index.html'](./src/assets/textAssets/index.html) file in the output is distributed
under the terms of the MIT license, so the author may modify that file however they see fit.

The copy of Showdown.min.js is used under the terms of the MIT license, as can be seen on the
[Showdown repository](/https://github.com/showdownjs/showdown/blob/master/LICENSE).

Any .hecc files produced by OH-HECC, and any `hecced.js` files produced by HECC-UP, are
considered to be owned by the author of those aforementioned files, not me.
The author can pick whatever license they want to use for those files.
