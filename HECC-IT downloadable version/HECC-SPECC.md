# Hypertext Editing and Creation Code - Super Precise Explanation for Creating Code (v3: Finally Presentable!)



## So, how the HECC does the `.hecc` format work?

The way that .hecc works is somewhat simple: you define metadata for your HECCIN' Game,
and then you define the passages that constitute the HECCIN' Game. You then open your .hecc file
within HECC-IT, select 'edit with OH-HECC' if you want to get an overview of it/edit it further,
and then select 'export with HECC-UP' (either from the main menu of HECC-IT or within OH-HECC) in
order to try exporting it as a HECCIN' Game.

If there is a problem with your hecc file when exporting it, HECC-UP will complain, and will explain
what the problem is, and you'll need to fix it yourself. If there's a problem in your hecc file when
opening it with OH-HECC, OH-HECC will attempt to fix the problem automatically, but, if it's a very
big problem (such as a completely invalid declaration), OH-HECC will just completely ignore it
(potentially treating it as part of another passage's contents/not acknowledging that it's there)


### Rules for passage names

Passage names must start/end with 'word' characters (alphanumeric/underscores), may contain hyphens
and spaces between the first and last character, and must be at least 1 character long. Other
characters are forbidden. Each passage must have a unique name.


## Defining metadata

The first part of a .hecc file, before the passage declarations, is for defining metadata.

Information about the types of metadata which can be declared, and how to declare them, are shown below.
The line in which they are declared must start with the `!`, and may not contain anything but the
declaration. Any leading/trailing whitespace in the value for the defined metadata field will be
ignored when it is read.

* Start passage
  * `!start: name of start passage`
    * The game will start from the given passage.
    * If this metadata is not declared, it will assume that it should start from a passage called `Start`.
    * If the declared start passage is not present in the passage definitions, HECC-UP will complain.
    
* Game title
    * `!title: game title goes here`
        * This is the title of the game you are writing.
        * The title must
            * Start and end with non-whitespace characters.
                * May contain any number of spaces + non-whitespace characters in-between.
            * Be at least 1 character long.
        * If this is not declared, HECC-UP will assume that your game is called `A Hypertext Fiction`
    
* Author name
    * `!author: your name here`
        * Your name here
        * Must
            * Start and end with 'word' characters (alphanumeric + underscores)
            * May contain any number of 'word' characters, spaces, commas, and full stops
              between the first and last characters
            * Be at least 1 character long
        * If this is not declared, HECC-UP will assume that your name is `Anonymous`.
    
* IFID
    * `!ifid: ifid goes here`
        * This is an Interactive Fiction IDentifier, used as a unique identifier for your game, and only your game.
        * Must
            * Be a UUID generated as specified by RFC 4122, but the letters must be uppercase.
        * Can be generated automatically by HECC-IT (**RECOMMENDED**)
            * Via HECC-UP
                * If undeclared, HECC-UP will generate an IFID, and a line of .hecc code which you
                  can use to put that IFID into your .hecc file will be logged in the
                  'important info' area, and you may copy that line of code and paste it into your
                  .hecc file (before re-compiling your .hecc file)
            * Via OH-HECC
                * Generated and put into your .hecc file automatically.
    
* Comments
    * `// comment line goes here`
        * You may have as many comment lines as you want, as long as they all start with `//`.
        * Leading/trailing whitespace between the `//` and the letters will be retained by OH-HECC.
        * OH-HECC will treat all `//` lines as a single, multi-line comment.
    
* Any line before the first passage declaration that is **not** a metadata declaration, and is **not**
  explicitly a comment line (with a `//`), **will be ignored by OH-HECC, and will be discarded**

### Defining passages

* Declaring the passage
    * You can declare a passage with a line consisting of `::Passage Name`
        * Start the line with a `::`, then write the passage name.
        * Leading/trailing whitespace before/after the passage name will be omitted.
        * You must follow the previously-defined rules for passage names.
    
    * Optional metadata which can be declared on the same line, after the passage name
        * A list of passage tags
            * `[these are some tags]`
            * Each of these tags must contain only letters, and must be separated by spaces.
            * Putting `noreturn` in the tag list of a passage will disable the 'back' button in the
              game when this passage is active.
            * If there is no tag list in the .hecc, it will be treated as a list of no tags.
        
        * A position
            * `<34, 466>`
            * Used in OH-HECC, represents the X and Y position of the passage in the editor view.
            * If these are undefined, OH-HECC will automatically give it a position.
                * Start passage will be put at 0,0
                * Other passages will be put at a random offset from one of their parent passages
                    * If there is no parent passage, it will be offset from 0,0.
                * Results may vary, but it's better than having them all stacked on top of each other.
    
        * A comment (must be after everything else)
            * `// everything on this line after this is a comment`
            * No restrictions on content, but the `//` must be after any metadata you wanted to declare.
                * Because everything after it is a comment.
    
* Passage content
    * Markdown formatting is enabled.
        * But every newline will be treated as a newline in the game produced by the .hecc code.
    * Please do not put lines starting with a `::` or a `;;` in your passage content
        * `::` denotes a passage declaration
            * And the end of this passage
        * `;;` denotes the end of passage content
            * Put one of these in at the end of the passage content. Not in the middle of it.
    * Must not be empty
    * Special formatting
        * Links to passages
            * Direct links
                * `[[passage name]]`
                    * When clicked in the HECCIN' Game, the player is sent to the passage called
                      'passage name'
                    * Appears as text saying 'passage name'
            * Indirect links
                * `[[link text|passage name]]`
                    * When clicked in the HECCIN' Game, the player is sent to the passage called
                      'passage name'
                    * Appears as text saying 'link text'
                        * Link text may not contain any `|` characters or any double square brackets
                            * no `[[`s or `]]`s
    
        * Conditional statements
            * `{if:condition}{Text shown if true}{else:This text is shown if false}`
                * The `{else:...}` is optional. But the `{if:...}{...}` is mandatory.
            * Conditions
                * The functions which evaluate the prior visited tags/passages **do not consider the
                  currently loaded passage**.
                * These functions are available:
                    * Functions that can be used as-is to return a boolean
                        * `tAny("tags","go","here")`
                            * Returns true if a passage with any of the given tags has been
                              encountered so far.
                            * Any number of tags can be given as arguments, as long as they are all
                              in speech/quotation marks, and have commas between them.
                        * `tAll("tags","go","here")`
                            * Returns true if **all** of the given tags have been encountered so far.
                            * Once again, any number of tags can be given as arguments
                            * `tAll("oneTag")` is functionally identical to `tAny("oneTag")`
                        * `pAny("passage names","go","here")`
                            * Returns true if any of the named passages have been visited so far
                            * Any number of passage names can be given as arguments
                        * `pAll("passage names","go","here")`
                            * Returns true if **all** of the named passages have been visited so far
                            * Any number of passage names can be given as argument
                            * `pAny("passage")` is functionally identical to `pAll("passage")`
                    * Functions that return a number (so you might need a comparison operator)
                        * `tCount("tag")`
                            * Returns the number of times that the given tag has been encountered.
                            * If it hasn't been encountered/doesn't exist, returns 0
                        * `pCount("passage name")`
                            * Returns the number of times that the given passage has been visited.
                            * If it hasn't been visited/doesn't exist, returns 0
                    * Functions that perform boolean operations on arguments
                        * `and(functions, go, here)`
                            * Returns true if **all** of the given functions return true.
                            * Can give it as many functions as you want
                                * But giving it just one is redundant.
                        * `or(functions, go, here)`
                            * Returns true if **any** of the given functions return true.
                            * Can give it as many functions as you want
                                * But only giving it one is, again, redundant.
                        * `not(function)`
                            * If the given function returns true, this returns false. And vice versa.
            * Nesting if conditions
                * If you want to put a `}` within the text for the if/else for any reason, you must
                  escape it for every level of nesting you have
                * `{if:pAny("Kevin")}{You've seen Kevin, and {if:pCount("bob")>=2/}{you've seen bob at least twice/}{else:you haven't seen bob twice/}}{else:You've never seen Kevin}`
    
* Trailing multi-line comments
    * You can explicitly end the passage with a line consisting entirely of `;;`.
    * Everything on the following lines will be treated as multi-line comment for the above passage.
    * These comments can be ended with
        * Another `;;` line (Recommended)
        * The declaration for the next passage
        * The end of the file.
    * Leading/trailing blank lines in the multi-line comment will be omitted by OH-HECC.
    

If you want to see some premade .hecc code, there's some at the end of this document.
But first, we need to talk about **HECC-IT**

## Using HECC-IT with your .hecc code

### Opening HECC-IT

Please ensure that your computer has Java 8 installed on it. If you don't have Java 8 installed,
you can install it from https://www.java.com/en/download/.

Just click on the `HECC-IT.jar` file that you (hopefully) downloaded with this document. If
you haven't downloaded `HECC-IT`, it should be available at [https://11belowstudio.itch.io/hecc-it](https://11belowstudio.itch.io/hecc-it).

### Using HECC-IT

In the main menu of HECC-IT, you will be asked to if you want to create a new .hecc file
(using the text input boxes on the left), or open an existing .hecc file (using the button
on the right).

If you want to create a new .hecc file, please ensure that the data you input in the text
input boxes follow the requirements outlined above, otherwise, you will not be able to create
that .hecc file. Upon submitting a game title/author name, you will be prompted to choose
a place to save your .hecc file. Once you've chosen a place to save it, you will be able to
start editing that .hecc file within OH-HECC.

If you want to open an existing .hecc file, please select the .hecc file using the file chooser
dialog that will open when you press 'choose a file'. Once this is done, you will then have
the option to edit that .hecc file using OH-HECC, or to export it now using HECC-UP. If you
select OH-HECC, you can still open HECC-UP to export it later on via OH-HECC.

### Using OH-HECC (Optional Help for HECC)

When opening OH-HECC, your .hecc code will be parsed, to produce the editable model of your
HECCIN' Game. If you give OH-HECC some .hecc code which isn't entirely valid, it will attempt
to fix the errors. Results of this automatic error correction may vary in usefulness. Any
passages with duplicate names will be renamed, any passages that are linked but don't exist
will be created, any passages without a position will be given a random position,
and any undeclared metadata will be automatically initialised.

Once OH-HECC finishes initialization, you will be presented with an overview of your HECCIN'
Game as a network of connected 'passages'. The text on each passage is the name of the passage.
The purple arrows show which passages contain 'links' to other passages. The colour of
a 'passage' corresponds to the 'status' of it.

* Orange passage:
    * This is a 'normal' passage, which holds at least one link to at least one other passage.
      If your game has an orange passage which doesn't have any visible links, that means
      the passage's content has a link back to itself.
      
* Yellow passage:
    * This is an 'end' passage, containing no links to any other passages.
    
* Red passage:
    * This passage's content contains a link to a passage which has since been deleted, so you
      will need to edit that passage to remove the link which has a `! WAS DELETED !` appended
      to the end of the passage name. OH-HECC will not completely remove those links
      automatically, in case you wanted to retain the text in that link/make it link to a
      different passage.
      
* Dark orange/red passage:
    * This passage has no content. You will need to add some content into it.
    
* Red outline:
    * This passage has been given the `noreturn` tag.
    
* Light-blue rounded rectangle surrounding the passage:
    * This is the 'start' passage, as defined by the game's Metadata.
    
You may freely resize the window. There are two ways to move the viewable area. You can move
the view by a fixed amount in a given direction by pressing the arrow keys on your keyboard/numpad.
You may also 'drag' the view by holding the right mouse button and moving the mouse. You cannot
interact with anything (or scroll using the arrow keys) whilst dragging the viewable area with
your right mouse button. You can stop scrolling the view by releasing the right mouse button.
To prevent you from getting lost from the passages, the scrolling is capped relative to the
positions of the passage objects, so the midpoint of the viewable area is always within
the bounds of the midpoints of the top-most/bottom-most/left-most/right-most passage objects.
This does mean that, if there's only one passage in your game, you cannot scroll the view.

You may move passages around by pressing and holding the left mouse button on them. The passage
will turn light-blue, and you may drag it with your mouse. You may not scroll the view whilst
doing this. Releasing the left mouse button will stop moving the passage.

To edit a passage, left-click on the passage object. This will open a dialog window allowing
you to rename the passage, update the passage's list of tags, update the passage content,
update the inline/multiline comments for the passage, and has an option to delete the passage.
Renaming this passage will fail if you attempt to give this the same name as an existing passage.
Any links to this passage within the content of any passage will be updated to still link
to this renamed passage. If you delete the current passage, the links that used to point to this
passage will be updated appropriately as well. Any `::` or `;;` lines within the passage content
or multiline comment will be forcibly escaped, so the hecc code won't break. If you put a link
to a passage that doesn't exist yet in the content of this passage, that new passage will be
created automatically. You may not interact with the passage overview in any way until you
close the passage editor window (and any updates to the viewable area will only happen after
you close this window). Closing it with the `x` button will discard unsaved changes, but the
`save and exit` button will save all changes (but, if a change cannot be saved, due to an
invalid input, the window will not close, to give you a chance to fix it). If you do press
the 'delete' button, you will be asked to confirm that you want to delete it (and that you are
sure that you want to delete it) before it will be deleted. This editing window will close
automatically upon a successful deletion.

The buttons at the bottom of this editable area all do different things when left-clicked,
and may only be clicked when you do not have an editing dialog open, are not scrolling the view,
and are not moving a passage object. 

The 'save' button will attempt to save your game in .hecc format to the .hecc file.
If there are no major problems with your game, it will also save/overwrite a
`_lastValidVersion.hecc` copy of your .hecc file (intended to be a backup of the last 'valid'
version of your hecc file, with no empty passages/passages with deleted links). If it cannot
save your game, it will open a new window showing you your .hecc code, which you can then copy
and paste into a `.hecc` file manually.

The 'export with HECC-UP' button will attempt to save your game, like the save routine. If your
game isn't 'valid' enough for the`_lastValidVersion` check to pass, it will not open HECC-UP,
and will tell you what the problem is. HECC-UP will be discussed later on.
You may not interact with OH-HECC whilst HECC-UP is open.

The 'edit metadata' button will open a window that will allow you to edit your game's metadata
(author name, game title, start passage name, and multiline comment). If you change the start
passage to the name of a passage that doesn't exist, you will have the option to rename the
existing start passage itself, or create a new start passage with that name. If you change the
start passage name to the name of a passage that does exist, that existing passage will be the
start passage. Closing the metadata editor window via the `x` will discard unsaved changes.
Closing it with the `save and exit` button at the bottom will attempt saving unsaved changes
(but, if a change cannot be saved, due to an invalid input, the window will not close, to give
you a chance to fix it).

The 'add passage' button adds a new passage, with a completely unique name, holding
`Sample Content`, to the passage map.

To close OH-HECC, just click the `x` on the top-right corner of the window. You will be asked
to confirm that you did want to quit, because any unsaved changes will be lost. As soon as
you press 'yes', OH-HECC will close.

### Using HECC-UP (HECC Ultra Parser)

Upon opening HECC-UP from HECC-IT or OH-HECC, the .hecc file you previously selected will be
pre-selected as the 'input', and a sub-directory within the same directory as your .hecc file,
with the same name as your .hecc file, will be pre-selected as your 'output' folder. The upper
two buttons open file choosers which you may use to manually select a different .hecc file
or output folder, in case you didn't want to use the default buttons. The 'HECC-UP!' button
at the bottom will parse the selected .hecc file, and attempt to convert it into a playable
HECCIN' Game. If there is a problem with the .hecc file, details of this problem will be output
to the 'event log' scrollable text area at the bottom of the window. Any passages within your
hecc file which are not linked to from your start passage will be omitted from the produced
game. If your game has been exported successfully, HECC-UP will ask you if you want to play
your game. If you press 'yes', it will attempt to open the game's index.html file in your default
web browser. If this fails, the location of your game's index.html file will be output in the
'log' area, so you can just open it manually.

To close HECC-UP, just press the `x` on the top-right corner of the window. If you opened
HECC-UP via OH-HECC, you can then resume using OH-HECC.

# And now, some example HECC code
```
this line is before the first passage declaration, so, officially, this line doesn't exist! 

!Start: Start
!IFID: 5B4FD379-ECAE-45E1-A015-902D7CCE1105
!Title: a new heccsample
!Author: Rachel Lowe

//This is a comment.
//this line is also a comment.
//we live in a society.
//
//bottom text.

//  This Source Code Form is subject to the terms of the Mozilla Public
//  License, v. 2.0. If a copy of the MPL was not distributed with this
//  file, You can obtain one at https://mozilla.org/MPL/2.0/.

::Start

starting passage content goes here.{if:pAny("Start")}{ What else did you expect?}
The following line contains a link to "Another passage".
[[Another passage]]
;;
this is a comment.

it has multiple lines.

pretty epic, is it not?
;;
::Another passage [yes] <34,35>

congrats you clicked that link to get here, Another passage.
why not [[click this|Yet Another Passage]] as well?
;;
;;
::Yet Another Passage //oh look another passage

woah you clicked that so you're now at Yet Another Passage{if:pAny("Yet Another Passage")}{, yet again!}{else:.}

Do you want to go [[Left]], [[Right]], [[Back to the start|Start]], or [[Skip this nonsense|dave]]?
;;
decisions, decisions.
;;
::Left

You go to the left, but the path leads you back to [[dave]].
;;
run from it, hide from it, dave still arrives
;;
::Right

You went to the right, but the path leads you back to [[dave]].
;;
;;
::dave [noreturn]

This passage is called dave.
dave's content doesn't include any links to any other passages.
And you cannot go back from dave.
So I guess this counts as the end.
;;
yep that's the end.

bottom text.
;;
```

# Legal bits

Any .hecc files you produce with OH-HECC, and the `hecced.js` file produced by HECC-UP (holding
the actual exported data for your game) officially belong to you, as the author of that game,
so you can distribute them under any license you want.

The copy of `showdown.min.js` exported by HECC-UP is distributed under the MIT license,
[as can be seen on the repository for it](https://github.com/showdownjs/showdown/blob/master/LICENSE).

The copy of `index.html` exported by HECC-UP is licensed to you under the MIT license, so you
can basically do whatever you want with index.html.

The copy of `heccer.js` (the engine itself) exported by HECC-UP, as well as the source code
of/ the executable of the overarching HECC-IT software suite, is licensed to you under the terms
of the [Mozilla Public License v 2.0](https://www.mozilla.org/media/MPL/2.0/index.48a3fe23ed13.txt).
Once again, this means you can basically do whatever you want with it, even redistributing those
files in under a proprietary license, as long as those components (`heccer.js` and the (source
code of) the main HECC-IT executable) are still freely available.

In other words, for `heccer.js` and HECC-IT:

    This Source Code Form is subject to the terms of the Mozilla Public
    License, v. 2.0. If a copy of the MPL was not distributed with this
    file, You can obtain one at https://mozilla.org/MPL/2.0/.


What does this mean for you?

* If you want to use HECC-IT to make a freely-available game, you can.

* If you want to use HECC-IT to make something proprietary, you can.
    * The copy of `heccer.js`, with any modifications made to it, will still need to
      be available under the Mozilla Public License v 2.0, but `heccer.js` doesn't contain any
      of the data specific to your game anyway.

    * The files that hold the actual game data (`hecced.js`/the .hecc file), are responsible
      for the formatting ofthe output (`showdown.min.js`), and are responsible for linking the
      components of the game/allowing the player to play the game (`index.html`), can be
      redistributed under proprietary terms.

    * **tl;dr**You can keep the game data as private as you want, as long as `heccer.js`
      (with any modifications) is still distributed under the Mozilla Public License v 2.0.

        * You can sell your game, but not the engine that runs the game.

* If you want to contribute to/modify HECC-IT, you can, as long as your contributions are
  available under the Mozilla Public License v 2.0.

* If you want to use HECC-IT itself within another product, even if it's proprietary, you can.
    * As long as the version of HECC-IT within that product (along with any changes you made
      to HECC-IT) are still available under the terms of the Mozilla Public License v 2.0