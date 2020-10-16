# HECC-IT (Hypertext Editing And Creation Code Integrated Toolkit)

By R. Lowe

Git repo for this project: https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m

readme last updated 16/10/2020

## Welcome to HECC-IT!

This is a toolkit that you can use to produce hypertext games.

You could consider this as the MMVP (Minimal Minimal Viable Product)
version of HECC-IT, seeing as there isn't much to use just yet.
Currently, the only thing this does is construct hypertext games,
constructed from .hecc files.

So let's cut to the chase.

## What the HECC does this do?

* You can write your hypertext game in .hecc format.
    * Consult the 'HECC-SPECC' document if you need to see a reference for it.
        * HECC Super Precise Explanation for Creating Code
    * You can use any text editor for this task
        * Just make sure you save it as a .hecc file
* You can use HECC-UP to convert your .hecc code into a playable HECCIN Game
    * Select your .hecc file
    * Select a folder to output your game to
    * If your .hecc file contains valid .hecc code
        * It will output the HECCIN Game defined by the .hecc file into that folder
    * If your .hecc file does not contain valid .hecc code
        * Your game will not be exported, and it will tell you what to fix.
* You can then play your HECCIN Game
    * Open 'index.html' in the output folder you selected to play it.
        * Make sure you don't delete 'heccer.js' or 'hecced.js'
        

## Glossary

~~Prepare for some terrible puns here.~~

* HECC
    * Hypertext Creation and Editing Code
        * the language you can write your game in
* HECC-SPECC
    * HECC Super Precise Explanation for Creating Code
        * The specification for the .hecc language
* HECC-UP
    * HECC Uncomplicated Parser
        * Give it a .hecc file, and a folder to output a HECCIN Game to
        * It will then use that .hecc file to construct a HECCIN Game
        * This HECCIN Game will be saved in the specified folder.
* HECCIN Game
    * HECC-Infused Nice Game
        * A generic term for hypertext games constructed by HECC-IT.
        * .hecc code -> HECC-UP -> HECCIN Game
    * Playable via browser
        * Open index.html in your browser of choice
        * Make sure you have JavaScript enabled, and that you have heccer.js and hecced.js in the same folder as index.html
* heccer.js
    * HECC Environment for Runtime
        * Basically the JavaScript engine that runs your HECCIN Game
* hecced.js
    * HECCED Exported Data
        * This holds the data that your game consists of.
            * Exported from the .hecc file by HECC-UP
        * Responsible for giving the game data to the heccer, and getting the heccer to start running the game.

~~It's **my** mental breakdown, so **I** get to choose the names!~~