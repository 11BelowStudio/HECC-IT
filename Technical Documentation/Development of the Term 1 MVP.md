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
need to include one. The syntax for this was later modified to allow an author to explicitly end a
multiline comment using another `;;` line, if they so desired.

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

The first part of OH-HECC to be created were the classes which would store the passages that
constitute the game as well as the metadata of the game in a way that would allow them to be edited.

The '[OH-HECC MVP editable passages, editable metadata, and GameDataObject.md](OH-HECC%20MVP%20editable%20passages,%20editable%20metadata,%20and%20GameDataObject.md)'
document (accessible via that link) contains the technical documentation on this topic.
 
I have discussed how these classes were developed, how they work, and the means by which they can be
edited. I have also discussed the `GameDataObject` data structure which was employed later on in the
development of the MVP to encapsulate all the business logic for the game data. There are also some
diagrams in the final section of that document to illustrate the state of these classes as they were
when the MVP was finished. It's in a separate document to this one because it is rather long.

After `EditableMetadata` and `EditablePassage` classes were implemented, I proceeded to start
developing the OH-HECC tool itself.

## Part 2: Developing the Optional Help for HECC

The remainder of the development of OH-HECC involved the production of the actual 'editor' part of
it.

The '[MVP Development of OH-HECC.md](MVP%20Development%20of%20OH-HECC.md)' document (accessible via
that link) holds the technical documentation on this topic.

It discusses the design process of the OH-HECC tool, how the tool was developed (along with the
changes made to the tool during the development process), and explains how each of the components
of OH-HECC work.

However, at the end of this process, I had a functional minimum viable product.

## The final Minimum Viable Product

[Here is the Term 1 MVP release](https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m/-/releases/2.0-MVP).

I built OH-HECC and HECC-UP into two .jar files (`OH-HECC.jar` and `HECC-UP.jar`), allowing a user
to have access to the editing and the exporting functionality of HECC-IT. These were distributed with
a markdown document containing the current version of the HECC-SPECC, along with a folder of example
.hecc files. The example hecc files consisted of the `HeccSample.hecc` file from earlier on in
development, the `A Conversation.hecc` file from the Challenge Week MVP, along with
`oh hecc there's no metadata here.hecc` (a hecc file with a `::Start` passage and no metadata), and
`completely empty.hecc` (a .hecc file with no actual hecc code inside it). The former two were
included as examples of what to do when making a .hecc file, whilst the other two were included mostly
to let the author see for themselves what happens if each component is given a file with different
problems (in the case of the former, OH-HECC will fill in the metadata, whilst HECC-UP will complain,
but will begrudgingly export it anyway. In the case of the latter, OH-HECC will try to set it up like
a newly-made .hecc file, whilst HECC-UP will complain and refuse to export it).

I did not get around to writing a new .hecc file to demonstrate the capabilities of the minimum viable
product, as the actual functionality of the games exported had not progressed since the challenge week
iteration, and I was still trying to come up with an idea for the 'final' HECCIN' Game I would use to
demonstrate the eventual final version of OH-HECC.

Despite that minor problem, at this point in development, **I could have just stopped here.**

Why? It's because I had actually fulfilled the aim I was trying to achieve with HECC-IT. I had
produced a hypertext game authoring tool, which allows the author to freely write their games using
raw source code or use a GUI, never forcing an author to exclusively use one or the other. The games
produced were still 100% clientside and playable via browser, not requiring an author or a user to
perform any complicated setup on their end in order to play a game produced with this tool/make a
game produced with this tool playable. The GUI tool presented the game as a network of connected
passages, which an author could freely edit with some ease. It had the necessary metadata to keep it
compliant enough with the Treaty of Babel. Finally, because the tool was written in Java, it has
inherent interoperability with any desktop operating system, assuming that the author had installed
Java on their computer.

However, I didn't, because whilst HECC-IT was good, it could have been better. There were still some
features I wanted to implement, such as some form of guard conditions in the outputs, markdown
formatting, better input validation (so it would become harder to break OH-HECC), fixing some slightly
unexpected behaviour (such as the scrolling not working unless an editor window had been opened and
closed already, or a few passage links refusing to update with the child passage's movement until the
parent passage was moved). I also didn't really have a proper example game.

Therefore, as Term 1 concluded, I decided that I would attempt doing these during term 2. My parents
wanted me to take a bit of a break over the winter holidays, and my desktop's HDD died on the first
week of the holidays anyway, which did throw a slight spanner in the works until I could install a
new HDD, and re-download all my university-related files from the University OneDrive/from this git
repo. Because this problem happened whilst I was being asked to take a break anyway, I didn't record
it on Jira, I thought it might be worth mentioning it here briefly. I thought I was in a good place
with the project overall.

Things went considerably more downhill during Term 2.