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

### 2.A: The basis for OH-HECC's architecture

As mentioned in the design section, I was planning on giving OH-HECC a model-view-controller
architecture, similar to the one I have used in the past for a few other personal projects.
That architecture can be seen below:

![general MVC architecture](./MVP%20development/oh-hecc%20development/general%20MVC%20architecture.png)

Everything would basically be in the same 'Packij' package (descriptive name, I know). The `GameFrame`
would be responsible for a JFrame which would hold the `View`. The `View` would be a `JComponent`,
and would hold a `Model`, given to it by the `GameRunner`, allowing it to be rendered. The
`GameRunner` would hold the aforementioned `GameFrame`, `View`, and would generally hold multiple
`Model` objects, only one of which would be considered 'active' at a time. The `Model` effectively
only held methods which could be used as an update loop and as a render loop (a public, concrete,
'wrapper' method, handling any synchronization which needed to be done, with abstract methods for
the actual update/draw methods to be performed in), sometimes also holding certain collections of
`GameObjects` which all of the subclasses of `Model` for that particular game used. The `Model`
would always have a `Game` subclass, holding all of the collections of `GameObjects` needed for
the particular game, with the implemented version of the abstract update method handling all of
the interactions between the `GameObject`s, and the implemented draw method drawing all of the
drawable `GameObject`s appropriately. The games would also have a `TitleScreen` subclass of
`Model`, but that would not be applicable to this use case. The models would be controlled via
a `Controller` object (specifically, by obtaining an `Action` from the `Controller` to see the inputs
for that particular update frame). The `GameObject` subclasses would be in their own package, and
their positions/velocities/directions would be represented by 2D vectors (in a `Vector2D` class).
Finally, running the update/draw loops, as well as swapping between active `Model`s/resetting them
would be managed by the `GameRunner` class.

Why am I telling you about this? Because I was planning on generifying this further to produce OH-HECC.
Instead of running a game and reading from/writing to a high scores file, it would just be running a
model editor, and reading the model's current state from/writing the current state to a .hecc file.

On the topic of reading the state from/writing the state to a .hecc file...

### 2.B: The OhHeccParser.

I would need to first work out how I would turn the .hecc file into the map of `PassageEditingInterface`
objects and the `MetadataEditingInterface` object.

I was still planning on eventually merging the HECC-UP components with the OH-HECC components, but I
also didn't want to break anything with HECC-UP, so I chose to copy the code present in HECC-UP,
and change a few parts of it to work for OH-HECC instead. The main difference between the HECC-UP
parser and the OH-HECC parser was that whilst the HECC-UP parser was strict (expecting perfect .hecc
code), the OH-HECC parser was more lenient. If there's an issue which would lead to the HECC-UP parser
to throw an exception, the OH-HECC parser won't care, and will just continue parsing as normal. The
idea was that OH-HECC would fix any of the problems encountered in file as it parsed it (such as
passages with duplicate names, unresolved passage links, a start passage which doesn't exist, etc),
however, quite a few of these error detection measures were completely overlooked, and were only
retroactively implemented at the last minute for the final deliverable.

Because OH-HECC needed all the passages to have a set of the UUIDs of the passages they're linked to,
one notable change in the OH-HECC parser was the addition of an extra iteration through the values in
the passage map for the sole purpose of initialising these UUID sets. The linked name sets are still
created in the same way as in HECC-UP (within the passage itself), but the OH-HECC parser cannot
predict what UUID will be assigned to each passage until they have all had a UUID assigned to them;
therefore, this has to be sorted out for every passage (by finding the passages in the map with the
names that are in the linked passage name set).

Just like the HECC-UP parser, I couldn't really predict what the outputs of this parser would be,
due to the somewhat non-deterministic nature of the UUIDs which would be assigned to every single
`EditablePassage`. So, I used the cop-out method of testing it via a main method in the class itself,
looking at the console outputs, to see if they behaved as expected. I did add a few unit tests to this
class later on though, which can be seen [here](../src/oh_hecc/OhHeccParserTest.java), although they
aren't very exhaustive.

However, with this parser in action, I could now put together the MVC stuff for the data it had parsed

### 2.C The Actual MVC architecture

The initial plan for OH-HECC's MVC architecture looked something like this:

![model bits v1](./MVP%20development/oh-hecc%20development/oh%20hecc%20model%20bits%20iteration%201.png)

The passage map and the editable metadata would be held within the `PassageModel`. The
`AbstractObject` would be an abstract parent class for all the objects that will appear within the
`Model`. The `StringObject` would simply hold a String, in white text with a black outline, which
could be rendered in the model. The `EditModelObject` would basically hold a reference to the
`PassageModel`, via an `EditModelInterface`, for the objects in the model which would need to hold
a reference to the model itself in some form, such as getting some info about the model itself,
or getting references to some passages within the model. The `ModelButtonObject` would be for the
'buttons'/'toolbar' visible at the bottom of the editor view, and the text for each of them would
be rendered as `StringObject`s. The `PassageObject`s would represent one passage in the network
of passages. They would all have a reference to an `EditablePassage` object (which they would
obtain the name, position vector, UUID, and set of linked UUIDs of), and, when clicked, they
should open the passage editor window for that passage. Additionally, when the user holds their
mouse on them, they should be able to drag them around the view. The `PassageObject`s would also
have a `StringObject` (showing the name of the passage it's associated with) and a set of
`PassageLinkObjects`, for each passage they're linked to. The `PassageLinkObject` would need to
hold a reference to its parent `PassageObject` (to get the position of it), as well as the UUID
of the passage that this represents a link to. I could then implement the 'triangle pointing to
the linked object' via finding the difference between those two position Vector2Ds, rendering
the triangle between them by initially offsetting it by the parent's position, so the midpoint of
the base is at the midpoint of the parent `PassageObject`, and then basically rotating it/stretching
it so that the tip of the triangle is at the relative (x,y) coordinate in the difference vector,
at the midpoint of the `PassageObject` it links to. Finally, the GUI should be resizeable with the
window, and, when pressing the arrow keys, the viewable area should scroll.

The model would store the `PassageObjects` in a map (associated with the UUIDs of the passages they
represent), for ease of indexing, as well as the map of the `PassageEditingInterface` objects.
Yes, doing it like this does mean that there is some redundant data. However, it also means that
it'll be easier to remove any references to passages which have been deleted; if there are any
keys from the `PassageObject` map which are not in the `PassageEditingInterface` map's keys (due to
those passages being deleted), the corresponding `PassageObject`s can be discarded. Additionally,
if a passage gets added, any keys which are only in the `PassageEditingInterface` map but not
in the `PassageObject` map can be identified, so the corresponding `PassageObject`s can be created.

The `Controller` would have a `ControllerAction` object, which would be viewed by the `PassageModel`
in an update loop (via a read-only `ActionViewer` interface), the state of which would dictate the
actions to be performed by the `PassageModel`. Finally, the update/repaint loops would be handled
centrally by the `OhHeccRunner`.

Then there was the implementation.

The various classes in the `model_bits` package were implemented as intended. However, when I got
to the `PassageModel`, and in particular, the `Controller`-related classes, I realized that this
particular control implementation was a stupid idea. Unlike those prior game projects, where an
update loop was a necessity, it would be completely overkill in this scenario, because, if an
author isn't actively interacting with the model, the model's state wouldn't change, so any constant
update loops or repaint loops would just be a waste of processor resources. Therefore, I took a
closer look at Java's GUI libraries, working out how to make something only repaint if a user
were to interact with it, at which point I realized I could just make the `Model` be a subclass
of some variety of GUI component, which would let a `MouseListener` or `KeyListener` of some
description simply call the controlling methods, which in turn call the `invalidate`/`repaint`
methods of this GUI component, omitting the update loop entirely.

In the MVP iteration, I didn't do this in a very optimal way. I had a `MouseController` class which
implemented `MouseListener` (as well as `MouseMotionListener`), to listen for mouse inputs, and then
call the appropriate methods of the `PassageModel`, via a `MouseControlModelInterface`.