# Development of the Term 1 MVP

During the latter 9 weeks of Term 1, I was continuing development on the minimal viable product for
HECC-IT, but this time, with emphasis on the 'viable'. The main improvement made in this stage was the
creation of OH-HECC, however, several improvements were made to HECC-UP in this stage of development as
well.

The first part of this stage of development was to clean up HECC-UP a bit, mostly to make it easier
to maintain later on.

# Improving HECC-UP

Here's a diagram to summarize the initial changes made to HECC-UP

![HECC-UP initial improvements](./MVP%20development/HECC-UP%20improvements.png)

The HeccCeptions have been omitted from this diagram, as well as most of the internal methods (and a few
attributes) within the various game_parts (because there were a lot of them), and the internal details
of the HeccParser/HeccUpGUI/FolderOutputter (the latter three remained mostly unchanged anyway).

As you can see, there is now an intermediate HeccUpHandler class in-between the HeccUpGUI and the
HeccParser/FolderOutputter classes. This decoupling of the GUI from the lower-level logic was done
intentionally, so I could potentially get OH-HECC to use the HeccUpHandler stuff in order to parse the
.hecc file into a format which it could use. Moving the Passage and Metadata into a different package
was also part of this plan, as I was hoping to enhance the Passage and Metadata classes, so they could
be usable by OH-HECC as well. I had also expanded the HECCIN' Game's functionality a little bit, by
allowing the game Passage objects to have a string array of 'tags' as well, like these Passage objects.

I had also formalized some changes to the HECC-SPECC, for declaring comments and passage metadata.
To declare a comment within the game's metadata (before the first passage declaration), it would
be possible to declare them with lines beginning with a `//`. The system will take note of as many `//`
lines as there are in the .hecc file above the first passage declaration, will read them in order, but
it will treat them all as a single multiline comment. I chose to make these explicit, rather than
implicit, because it would make it easier for OH-HECC to distinguish between lines that are supposed to
be read as part of the comment and lines which are just whitespace, therefore making this aspect of
OH-HECC less confusing when OH-HECC is made.

Passages can now have comments, positions, and tags. The position goes unused by HECC-UP, but will be
important for OH-HECC (as that indicates where the passage will be in the network overview), and the
comments are also ignored by HECC-UP. Comments may be inline with the passage declaration, or be
'trailing', below the passage content. The HECC-SPECC defines the inline passage metadata (tags,
position, inline comment) as being defined as follows:  
`::Passage name [list of tags] <39,64> // comment for rest of line`  
where `::Passage name` is a passage
declaration (as usual), `[list of tags]` is a space-separated list of 'tags' for the passage (which
must consist of 'word' characters (letters, numbers, underscores)), `<39,64>` is a vector position for
the passage (in the form `<x,y>`, permitting negative numbers and decimal points), and everything after
the `//` until the end of this line is treated as a comment. Any of these pieces of inline metadata can
be omitted if an author does not want to use them. The position and tags can be in any order, but the
passage name declaration must always be present and the first thing on the line, and the inline comment
(if present) must be the last thing on the line. This syntax was, once again, heavily influenced by the
passage declaration syntax of Twee2. However, whilst Twee2 requires the tags to be declared first,
before the position, HECC-IT doesn't have this restriction. Additionally, the comment declaration here
isn't an option in Twee2.

An author may declare a trailing comment below the contents of a passage by entering a line consisting
only of a `;;` under the passage content. Everything after the `;;` line and before the next passage
declaration/end of the file is treated as a 'trailing comment' belonging to the above passage. These
trailing comments may be multiline. If an author does not wish to include a trailing comment, they don't
need to include one.

You're probably going to ask about the Variable class now. One thing which I was planning to do with
this project, which I didn't get around to doing, was properly implementing variables into this system.
I had added some scope to the HECC-SPECC for declaring variables within a .hecc file's metadata, in the
form:  
`!var: variableName = value //an comment`  
An author could opt to omit the `= value`, in which case,
the value would be considered to be `= 0`. The comment for the variable could also be omitted (and this
comment would not be considered to be part of the metadata multi-line comment). This was planned to
accept variables that could be strings or numbers (but I thought of also implementing some way for
advanced users to declare that a variable was intended to be a JavaScript object or something), and,
due to the JavaScript-based nature of the outputs (and the loose typing of JavaScript), there wouldn't
be any reason to require the datatypes of these variables to be explicitly declared anywhere.

Within the HECCIN' Game, I was planning to store these variables in a map of Strings (variable names)
and `Any`s (the actual values). Specifically, I was planning on having several maps; each `GameState`
in the `GameStateStack` would hold an archive of the state of the variables at that point in time, as
shown in the below diagram:

![how variables would have been stored](./MVP%20development/planned%20state%20stack%20variable%20stuff.png)

I did not get around to working out if the version of the variable map stored in the `GameState`
should be a copy of the variables as they were prior to that gamestate being loaded (so I could keep
using the same 'refer to the top gamestate' methodology as I had been using so far, even if that did
run the risk of potentially opening up the door to some 'save scumming'), or if they should hold the
variables after evaluating that gamestate (and then working out how to prevent the variables from
being modified again when trying to go back to the passage). I also didn't work out how to declare
the usage of variables within the passage contents either. I might try to add support for the variables
in a future update for HECC-IT (after I'm done with my exams), however, for the time being, these are
still completely unimplemented.

HECC-UP still had a complete lack of unit tests (still relying on manual 'putting in a .hecc file and
seeing what comes out the other end'), however, I did manage to unit test the `Variable` class. Those
unit tests just tested the declarations of a variable with just a value, with a value and a comment,
with just a comment, and without variable/comment. Those tests can be seen [here](https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m/-/blob/MVP-Archived/src/gameParts/VariableTests.java)

On a less negative note, I should probably cover the development of OH-HECC now.

# Developing OH-HECC

## Part 1: Editable passages, editable metadata, and the GameDataObject

The first part of OH-HECC to be created were the classes which would store the passages that constitute
the game as well as the metadata of the game in a way that would allow them to be edited.

The '[OH-HECC MVP editable passages, editable metadata, and GameDataObject.md](OH-HECC%20MVP%20editable%20passages,%20editable%20metadata,%20and%20GameDataObject.md)'
document (accessible via that link) contains the technical documentation on this topic.
 
I have discussed how these classes were developed, how they work, and the means by which they can be
edited. I have also discussed the `GameDataObject` data structure which was employed later on in the
development of the MVP to encapsulate all of the business logic for the game data. There are also some
diagrams in the final section of that document to illustrate the state of these classes as they were
when the MVP was finished. It's in a seperate document to this one because it is rather long.

After `EditableMetadata` and `EditablePassage` classes were implemented, I proceeded to start
developing the OH-HECC tool itself.

## Part 2: Developing the Optional Help for HECC

As mentioned in the design section, I was planning on giving OH-HECC a model-view-controller
architecture, similar to the one I have used in the past for a few other personal projects.
That 