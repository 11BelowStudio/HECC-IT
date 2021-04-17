# **HECC-IT (Hypertext Editing And Creation Code Integrated Toolkit)**

### The hypertext game authoring tool for indecisive people

By Rachel Lowe

Public git repo for HECC-IT: https://github.com/11BelowStudio/HECC-IT

itch.io page for HECC-IT: https://11belowstudio.itch.io/hecc-it

(if you're reading this before May 2021, chances are that those might not be public yet,
but, as soon as I'm sure that I won't get accused of plagiarising from myself, I shall be
making those pages public)

readme last updated 16/4/2021

## Welcome to HECC-IT!

This is a toolkit that you can use to produce hypertext games.

You can convert .hecc files into playable hypertext games with HECC-UP.
And you can get some help making and editing your .hecc files using OH-HECC.

So let's cut to the chase.


## How do I use HECC-IT?


Full instructions for using HECC-IT, as well as the .hecc file format, are given within
the [HECC-SPECC](./HECC-SPECC.md)
document.

You will need:
* Java 8 (or later) installed on your computer
    * You can download it from https://www.java.com/en/download/
* A mouse
* A keyboard
* A monitor (optional, but highly recommended)

Just click on the `HECC-IT.jar` file contained in this folder, and you'll be able to start
using HECC-IT.


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
    * Hypertext Creation and Editing Code
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
    * Used under the terms of the [MIT license](https://github.com/showdownjs/showdown/blob/master/LICENSE), which Showdown is distributed under.

~~It's **my** mental breakdown, so **I** get to choose the names!~~

## Legal stuff

HECC-IT is distributed under the terms of the Mozilla Public License Version 2.0,
a copy of which can be seen [here](./LICENSE.md)

The heccer.js file in the output, being pre-written and considered a part of HECC-IT,
is also distributed under the terms of the Mozilla Public License Version 2.0, by me.

The index.html file in the output is distributed under the terms of the MIT license,
so the author of the game (you) may modify that file however they see fit.

The copy of Showdown.min.js is used under the terms of the MIT license, as can be seen on the
[Showdown repository](/https://github.com/showdownjs/showdown/blob/master/LICENSE).

Any .hecc files produced by OH-HECC, and any `hecced.js` files produced by HECC-UP, are
considered to be owned by the author of those aforementioned files, not me.
The author (you) can pick whatever license they (you) want to use for those files.