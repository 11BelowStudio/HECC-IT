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


## Rules for passage names

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

## Defining passages

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

    
## And now, some example HECC code
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