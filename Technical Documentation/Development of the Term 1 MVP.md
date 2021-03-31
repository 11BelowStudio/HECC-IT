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

## Part 1: Editable passages and editable metadata

The first part of OH-HECC I worked on was how to edit the metadata and edit the passages. I didn't want
to break HECC-UP when doing this, however, so I decided to create some separate `EditableMetadata` and
`EditablePassage` classes in the meantime. I intended to merge these with HECC-UP's `Metadata` and
`Passage` classes, but employing the Interface Separation Principle to only expose the necessary
methods to OH-HECC and HECC-UP via some interfaces which these classes would implement. I didn't get
around to performing this merge of the passages and metadata classes, unfortunately. Due to the large
quantity of classes and interfaces involved, I'll be putting the automatically-generated class diagram
for these classes at the end of this section.

### 1.A: Editable Metadata

I started by creating an `EditableMetadata` class, followed by some unit tests for it ([here](https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m/-/blob/MVP-Archived/src/oh_hecc/game_parts/metadata/EditableMetadataTests.java)),
which worked as intended. This class was intended to hold the same metadata as the HECC-UP Metadata
class, except providing methods through which the aforementioned metadata could be edited. The IFID
metadata was intentionally left uneditable, as that is intended to be constant (assigned when a .hecc
file is first created by/first opened in OH-HECC, and saved in the .hecc file). The variable list was
also not made editable, because of the unimplemented nature of them. The methods to update the author,
title, and start passage declarations include some checks to validate that these are valid values,
through the use of regexes. If the new values are valid, that value overwrites the current stored value
for that field. Otherwise, the change is discarded, and a HeccCeption of some variety will be thrown.
I chose to take the HeccCeption route, as that would make it easier to display an error message to the
user later on, with a full explanation of what the problem is, as that explanation is already
pre-defined within the class of the specific HeccCeption. There wasn't any need for any regexes in
the multiline comment, as that's already made 'safe' in the .hecc code by having those lines
automatically prefixed with a `//`. There's no way for a user to break it by attempting to prematurely
declare an end to the comment, as the only declaration for an 'end' to it is the lack of it continuing.
Even if a user wants to put a metadata declaration, or a passage declaration, within this comment,
because the lines they will appear on are prefixed with a `//` instead of just having that declaration
by itself, it won't break the parsing process. Finally, this editable metadata can be output in .hecc
format via the use of a `toHecc()` method, via a `Heccable` interface (turning it into a String of
.hecc code).

Several interfaces were created for this class. The first one was the `Parseable` interface; A somewhat
misleadingly-named interface, as it merely held some constant regexes for passage names, and a static
method to validate a passage name. There was also the aforementioned `Heccable` interface, with the
`toHecc()` method which would be responsible for turning the thing into .hecc code. These were extended
by the `SharedMetadata` interface; this exposed the getter methods for the metadata, along with storing
some regex strings for game title and author info, and a static method for looking through a string,
given a regex, returning the trimmed version of the first match, or throwing a HeccCeption if it
couldn't be found. This method was put here to minimize code duplication for all the regex checks which
any `Metadata` class would need. This interface was extended, again, by the `MetadataReadingInterface`
and `MetadataEditingInterface` interfaces. `MetadataReadingInterface` simply held some constants for
the hecc metadata prefixes (for ease of editing if these were to be changed in the HECC-SPECC), along
with some static methods for obtaining the declared metadata from some raw .hecc code.
The `MetadataEditingInterface` exposes the methods which can be used to edit the metadata fields,
and has static methods to validate a given game title, and a given author. This would be the main
interface which the `EditableMetadata` would be accessed through. On that note, the `EditableMetadata`
class directly implemented the `MetadataEditingInterface` and `MetadataReadingInterface` interfaces.

I proceeded to create a `MetadataEditorWindow` class. I chose to make the metadata/passages editable
via a `JFrame` with some text input fields which would pop up if the user clicked on the appropriate
parts of OH-HECC. I did it this way because it allowed me get this implemented and working without a
dependency on OH-HECC; OH-HECC would only need to construct one of these windows, passing the
appropriate game part to it in the constructor. Like HECC-UP, this GUI was implemented entirely by
hand. At the top of the window, it shows the author what the current values for the metadata are.
The rest of the window has some `JTextFields` for the game title, author name, start passage name,
and a `JTextArea` in a `JScrollPane`. Each of these has a button underneath them to save the changes
made by the author to those fields. The bottom of the window has a button to exit the dialog.
A screenshot of the window can be seen below:

![metadata editor window](./MVP%20development/metadata%20editor%20window/metadata%20editor%20window%20mvp.PNG)

If an author attempts to put an invalid value into the 'author' and 'start passage' fields, the
text for those fields will turn red (until the input is replaced with a valid one). Attempting to
update these fields with an invalid input will result in the user being greeted by a `JOptionPane`
explaining that it's invalid, what the expected valid input should contain, and,
when this is dismissed, the value in that text field will be reset to its previous value. This can be
seen below.

![metadata_editor_window_invalid](./MVP%20development/metadata%20editor%20window/metadata%20editor%20window%20mvp%20invalid.PNG)
![metadata_editor_window_author_invalid](./MVP%20development/metadata%20editor%20window/metadata%20editor%20window%20mvp%20invalid%20author.PNG)
![metadata_editor_window_start_invalid](./MVP%20development/metadata%20editor%20window/metadata%20editor%20window%20mvp%20invalid%20start.PNG)
![metadata_editor_window_title_invalid](./MVP%20development/metadata%20editor%20window/metadata%20editor%20window%20mvp%20invalid%20title.PNG)

Finally, when closing the window, the user is warned that any unsaved changes will be lost.

![metadata editor unsaved warn](./MVP%20development/metadata%20editor%20window/metadata%20close%20warn.PNG)

The metadata editor window hasn't been unit-tested, as all of the important logic is not held in the
editor window; instead, it's held in the classes below it. However, I did write a main method for the
`MetadataEditorWindow` class itself, giving it a dummy metadata object, printing its initial state to
console when it's opened, letting me edit it via the window, and, upon being closed, printing the new
state of the metadata object to the console. I used this to informally check whether or not this
worked as expected, and, as far as I could tell, it worked. With this done for the time being,
I could move on to the passages.

### 1.B: Editable Passage

One of the first questions that popped into my mind when I started to work on this class was the
question of how these passages would be held in a data structure by OH-HECC. HECC-UP effectively
held them in a graph, with a map of passage names and passages, where each passage had a set holding
the names of the passage objects it has links to; effectively, an adjacency list. Identifying passages
by their name was sufficient for the purposes of HECC-UP, as no two passages would be allowed to have
a duplicate name, and the passages would never be renamed. However, for OH-HECC, this would pose a few
problems, especially if a passage was to be renamed. I would need to find some way of updating
every single reference to a passage's old name when a passage would be renamed (via looking for
instances of the old name). The problem was that if I was going to be storing the passages in a map
again, that could pose a problem, because keys of maps are supposed to be immutable; the passage name
would have to be mutable. I could have opted to simply store the passage objects in a set or an array,
completely omitting the key identifiers, however, that would have have made it much harder to access
a given passage from a reference, as I would need to iterate through that collection to manually find
the passage every time; it would be inefficient, slow, and generally rather annoying. I had to think
of some other method of giving each passage an immutable, unique, identifier; an identifier which
couldn't conflict with any other passage's unique identifier. At this point I started to kick myself
after I realized the blatantly obvious solution: giving each passage a UUID. The IFID metadata,
defined in the Treaty of Babel, already involved UUIDs, and I was already using Java's `UUID` class
to generate a random IFID in the no-argument constructor of the `EditableMetadata` object. So, I
gave the `EditablePassage` a `passageUUID` variable, which would be a randomly-generated UUID as a
constant identifier for the passage. Whilst there is a theoretical chance of two UUIDs being identical,
this chance is so small it's negligible, and I chose to only assign these UUIDs to the passage objects
upon construction within OH-HECC (not allowing any manual assignment of them), and Java's
`UUID.randomUUID()` method apparently uses a very robust random number generator. If the author using
OH-HECC finds themselves in a situation where the UUIDs of two passages are identical, chances are that
this collision will be the least of their concerns in that given situation.

With that initial roadblock out of the way, it was time to actually work on the `EditablePassage`.

Like HECC-UP's `Passage`, this would still have the passage name, passage content, and passage tags.
It would also have the inline comment string, trailing comment string, the aforementioned UUID, and
a position. It would also need the set of passages it is linked to, but it would need a set of both
the names and of the UUIDs of those passages. In terms of methods, it would need methods to get
each of these values, methods to parse the raw .hecc content/export the passage in .hecc, as well
as update the values (whilst validating the new values). It would also need methods to allow it to be
renamed and deleted, but these methods would also need to somehow modify the map of all passages to
ensure any references to this passage are dealt with accordingly. Additionally, the method to edit
the content of the passage would need a reference to the map of all passages as well; this would be
used to allow the map of linked passage names and UUIDs to be updated, creating new passages and
adding them to the map if appropriate.

Similar to the `EditableMetadata`, I opted to implement the `EditablePassage` via interfaces.
The top-level interface, `SharedPassage`, extended the `Heccable` and `Parseable` interfaces,
and provided some constant regex strings, static methods for parsing passage content, and
exposed the various getter methods of the `EditablePassage`. `SharedPassage` was again extended
by the `PassageReadingInterface` and `PassageEditingInterface` interfaces. `PassageReadingInterface`
simply held some static methods used for parsing raw .hecc format passage information into actual
data. `PassageEditingInterface` held some static methods for parsing a string with a space-delimited
list of passage tags into a `List<String>` and finding the names of passages linked to in some passage
content, as well as exposing the various setter methods for the `EditablePassage`. Finally, the
`EditablePassage` itself implemented the `PassageReadingInterface` and `PassageOutputtingInterface`
interfaces.

Like the `EditableMetadata`, the `EditablePassage` was also unit tested, trying out the various
getters and setters in a similar context to the one that they would be used in, which can be seen
[here](https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m/-/blob/MVP-Archived/src/oh_hecc/game_parts/passage/EditablePassageTest.java).
I also decided that the map of `EditablePassage` objects should be a map of `PassageEditingInterface`
objects, factoring that detail into the methods declared in `PassageEditingInterface` using the map.
So, with that done, I moved on to making the editor window for the editable passage. However, at this
point in development, I didn't actually consider implementing a check for any suspect `::` or `;;`
lines in the middle of the passage content or trailing comment, and I forgot to check for that in
those unit tests. However, later on in development, I did revisit this class, refactoring the
`setPassageContent` and `setPassageComment` methods so, if there were any `;;` and/or `::` lines,
they would have a `\` automatically appended to the start of them (escaping them). This method
was unit tested as well, and can be seen in the updated unit tests, [here](../src/oh_hecc/game_parts/passage/EditablePassageTest.java).

Similar to the metadata editor window, the passage editor window would need to take an
`EditablePassage` as an argument, however, due to those other methods it involves, it would also
need to take in the map of `EditablePassage` objects as an argument as well; this also meant that
I would need to ensure that only one `PassageEditingWindow` could be open at once, to ensure that
the overall network of passages could only changed by one thing at a time. The code responsible for
manipulating the overall network of passages would still be within the `EditablePassage` itself,
not in the GUI, so I didn't need to worry about that bit of decoupling. However, I would also need
to pass the actual `MetadataEditingInterface` for the game to this editor window as well, because,
if the start passage was to be renamed, I would need to edit the reference to the start passage held
within the metadata as well. However, I didn't want to pass the full `EditableMetadata` to this as-is,
so I came up with a solution. I created a new `PassageEditWindowMetadataInterface` interface, which
`MetadataEditingInterface` would extend; this interface would just expose the getter and setter for
the start passage reference, so, if the author was renaming/deleting the start passage, this reference
within the metadata could be updated appropriately. Later on in development, I did re-refactor this
to not include a direct reference to the metadata (regardless of interfaces), but that's how it worked
at this point in time.

It would, once again, present the author with an overview of the various fields of the passage,
such as the name, tags, comments (inline and trailing), and content, along with buttons to update
these values, as well as buttons to close the window and to delete the passage. This GUI was, again,
implemented entirely manually, and delegated the underlying logic to the `PassageEditingInterface`.
This is what the window looked like:

![passage edit window](./MVP%20development/passage%20editor%20window/passage%20editor%20window.PNG)

If the user attempted to enter a passage name which was invalid, the passage name text would turn
red, and, upon attempting to submit it, they would be greeted with an error message pointing out
that it was invalid (and the passage name will not actually be updated), like so:

![invalid name](./MVP%20development/passage%20editor%20window/passage%20editor%20window%20invalid%20name.PNG)
![invalid name submit](./MVP%20development/passage%20editor%20window/passage%20editor%20window%20invalid%20name%20warning.PNG)

Additionally, if an author attempts to rename a passage to be the same as the name of a passage which
already exists, the passage will not be renamed, and will again complain to the user:

![duplicate name](./MVP%20development/passage%20editor%20window/duplicate%20warn.PNG)

The passage tags don't have a validation method which is quite as vocal, however, if an invalid
character is encountered in the input field, everything before that character will be treated as
the tag string. Everything from that character onwards will be ignored, and will be removed from
the text input box when the author clicks on the 'update tags' button.

There isn't any validation performed on the passage content/trailing comment. However, the passage
link set for this passage will be updated to refer to the passages which are linked in the content
of this passage, in case any links in this content are added/removed/changed. The content and trailing
comment `JTextArea`s are still in a `JScrollPane`, so, if the author is writing a lot of content,
they can scroll up/down to keep seeing it all.

Attempting to close the passage editor window will warn the author that anything they haven't saved
will be lost, like so:

![close warn](./MVP%20development/passage%20editor%20window/passage%20close%20warn.PNG)

Finally, attempting to use the 'delete' button on the passage will inform the author that deleted
passages cannot be undeleted, asking them if they're sure that they want to delete it, before asking
them again to confirm that they genuinely want it to be deleted. Upon receiving this 2nd confirmation,
the passage will be deleted. It will be removed from the map of passages, and any passages which
contained a link to that passage will have that link removed (having a `! WAS DELETED !` appended to
the end of the passage name in those links). The deletion process is shown below:

![delet warn 1](./MVP%20development/passage%20editor%20window/delet%20warn.PNG)
![delet warn 2](./MVP%20development/passage%20editor%20window/delet%20warn%202.PNG)
![deleted](./MVP%20development/passage%20editor%20window/delet%20confirmed.PNG)

Finally, just like the `MetadataEditorWindow`, there wasn't any unit testing performed for the
`PassageEditorWindow`, due to the inherent problems with attempting to unit test a GUI. However,
a main method is present in the `PassageEditorWindow` class, giving a dummy setup for a passage
network (with two passages, one linking to the other, and the linked passage is the start passage),
opening up the passage editor window for the start passage. The initial state of the network is
printed to console, as well as the state of it when the passage editor window is closed/the passage
being edited is deleted. This allowed me to check that this GUI was working as expected.

Now, with these things done, I could finally move on to developing the rest of OH-HECC.

Before I get on to discussing OH-HECC, however, I will also discuss one class which was developed
later on during the MVP development cycle, but is still very much related to the topic at hand;
the `GameDataObject` class.

### 1.C: The GameDataObject Class

This was developed after most of the meat-and-bones of OH-HECC was developed, as a single class
to encapsulate all of the game's structure, in an attempt to decouple the 