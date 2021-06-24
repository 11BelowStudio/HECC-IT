# (3.2).3.2.1 Editable passages, editable metadata, and the GameDataObject

The first part of OH-HECC I worked on was how to edit the metadata and edit the passages. I didn't want
to break HECC-UP when doing this, however, so I decided to create some separate `EditableMetadata` and
`EditablePassage` classes in the meantime. I intended to merge these with HECC-UP's `Metadata` and
`Passage` classes, but employing the Interface Separation Principle to only expose the necessary
methods to OH-HECC and HECC-UP via some interfaces which these classes would implement. I didn't get
around to performing this merge of the passages and metadata classes, unfortunately. Due to the large
quantity of classes and interfaces involved, I'll be putting the automatically-generated class diagram
for these classes at the end of this section.

## 3.2.1.A: Editable Metadata

I started by creating an `EditableMetadata` class, followed by some unit tests for it ([here](https://github.com/11BelowStudio/HECC-IT/blob/MVP-Archived/src/oh_hecc/game_parts/metadata/EditableMetadataTests.java)),
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

I created some unit tests for this class, which can be seen in the [`src/oh_hecc/game_parts/metadata/EditableMetadataTests.java`](../src/oh_hecc/game_parts/metadata/EditableMetadataTests.java)
class. I first checked if the constructors worked as intended, which they did. I then attempted to
try putting some valid data into the various update methods, to see if they would update as expected,
which they did. Additionally, any invalid inputs are also rejected, just as expected.

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

##### Figure 17: A screenshot of the metadata editor window:

![metadata editor window](./MVP%20development/data%20classes/metadata%20editor%20window/metadata%20editor%20window%20mvp.PNG)

If an author attempts to put an invalid value into the 'author' and 'start passage' fields, the
text for those fields will turn red (until the input is replaced with a valid one). Attempting to
update these fields with an invalid input will result in the user being greeted by a `JOptionPane`
explaining that it's invalid, what the expected valid input should contain, and,
when this is dismissed, the value in that text field will be reset to its previous value. This can be
seen below.

##### Figure(s) 18: Screenshots to show the metadata editor window notifying the user about invalid metadata declarations

![metadata_editor_window_invalid](./MVP%20development/data%20classes/metadata%20editor%20window/metadata%20editor%20window%20mvp%20invalid.PNG)
![metadata_editor_window_author_invalid](./MVP%20development/data%20classes/metadata%20editor%20window/metadata%20editor%20window%20mvp%20invalid%20author.PNG)
![metadata_editor_window_start_invalid](./MVP%20development/data%20classes/metadata%20editor%20window/metadata%20editor%20window%20mvp%20invalid%20start.PNG)
![metadata_editor_window_title_invalid](./MVP%20development/data%20classes/metadata%20editor%20window/metadata%20editor%20window%20mvp%20invalid%20title.PNG)

Finally, when closing the window, the user is warned that any unsaved changes will be lost.

##### Figure 19: A screenshot of the warning about unsaved changes given by the metadata editor window

![metadata editor unsaved warn](./MVP%20development/data%20classes/metadata%20editor%20window/metadata%20close%20warn.PNG)

The metadata editor window hasn't been unit-tested, as all of the important logic is not held in the
editor window; instead, it's held in the classes below it. However, I did write a main method for the
`MetadataEditorWindow` class itself, giving it a dummy metadata object, printing its initial state to
console when it's opened, letting me edit it via the window, and, upon being closed, printing the new
state of the metadata object to the console. I used this to informally check whether or not this
worked as expected, and, as far as I could tell, it worked. With this done for the time being,
I could move on to the passages.

## 3.2.1.B: Editable Passage

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
[here](../src/oh_hecc/game_parts/passage/EditablePassageTest.java).
As you can see in there, I tested the various constructors, and then the setters and getters. Any
invalid inputs for the various properties of the `EditablePassage` are rejected as intended, and,
if a valid input is given, the `EditablePassage` is updated as-expected. Additionally, if a passage
is successfully renamed/deleted, any passages which link to it will have their links to it updated
appropriately.
I also decided that the map of `EditablePassage` objects should be a map of `PassageEditingInterface`
objects, factoring that detail into the methods declared in `PassageEditingInterface` using the map.
So, with that done, I moved on to making the editor window for the editable passage. However, at this
point in development, I didn't actually consider implementing a check for any suspect `::` or `;;`
lines in the middle of the passage content or trailing comment, and I forgot to check for that in
those unit tests. However, later on in development, I did revisit this class, refactoring the
`setPassageContent` and `setPassageComment` methods so, if there were any `;;` and/or `::` lines,
they would have a `\` automatically appended to the start of them (escaping them). This method
was unit tested as well, and can be seen in the updated unit tests.


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

##### Figure 20: A screenshot of the passage editor window

![passage edit window](./MVP%20development/data%20classes/passage%20editor%20window/passage%20editor%20window.PNG)

If the user attempted to enter a passage name which was invalid, the passage name text would turn
red, and, upon attempting to submit it, they would be greeted with an error message pointing out
that it was invalid (and the passage name will not actually be updated), like so:

##### Figure(s) 21: Screenshots of the passage editor window warning a user about an invalid passage name

![invalid name](./MVP%20development/data%20classes/passage%20editor%20window/passage%20editor%20window%20invalid%20name.PNG)
![invalid name submit](./MVP%20development/data%20classes/passage%20editor%20window/passage%20editor%20window%20invalid%20name%20warning.PNG)

Additionally, if an author attempts to rename a passage to be the same as the name of a passage which
already exists, the passage will not be renamed, and will again complain to the user:

##### Figure 22: Screenshot of the passage editor window warning a user about a duplicate passage name

![duplicate name](./MVP%20development/data%20classes/passage%20editor%20window/duplicate%20warn.PNG)

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

##### Figure 22: Passage editor window warning the user about unsaved changes being discarded

![close warn](./MVP%20development/data%20classes/passage%20editor%20window/passage%20close%20warn.PNG)

Finally, attempting to use the 'delete' button on the passage will inform the author that deleted
passages cannot be undeleted, asking them if they're sure that they want to delete it, before asking
them again to confirm that they genuinely want it to be deleted. Upon receiving this 2nd confirmation,
the passage will be deleted. It will be removed from the map of passages, and any passages which
contained a link to that passage will have that link removed (having a `! WAS DELETED !` appended to
the end of the passage name in those links). The deletion process is shown below:

##### Figure(s) 23: Passage editor window deletion process

![delet warn 1](./MVP%20development/data%20classes/passage%20editor%20window/delet%20warn.PNG)
![delet warn 2](./MVP%20development/data%20classes/passage%20editor%20window/delet%20warn%202.PNG)
![deleted](./MVP%20development/data%20classes/passage%20editor%20window/delet%20confirmed.PNG)

Finally, just like the `MetadataEditorWindow`, there wasn't any unit testing performed for the
`PassageEditorWindow`, due to the inherent problems with attempting to unit test a GUI. However,
a main method is present in the `PassageEditorWindow` class, giving a dummy setup for a passage
network (with two passages, one linking to the other, and the linked passage is the start passage),
opening up the passage editor window for the start passage. The initial state of the network is
printed to console, as well as the state of it when the passage editor window is closed/the passage
being edited is deleted. This allowed me to check that this GUI was working as expected.

Eventually, as both the PassageEditorWindow and the MetadataEditorWindow were both very much identical
(both requiring a reference to a Metadata object of some variety, both having a JFrame with a listener
for when it's closed, both having the same 'exit' button, and both needing some passage name
validation), I opted to make both of these extend the same `GenericEditorWindow` abstract class.
I also opted to change how the listener for the window being closed would be passed to the JFrames.
At first, I had to construct a `WindowListener` and pass it to the editor object via its constructor,
however, the problem with this was that it could have been difficult to get OH-HECC to pass the
listener it would need to these objects, as the editors were currently opened via a method in the
`PassageEditingInterface`/`MetadataEditingInterface` objects themselves; this would have meant
passing this listener through a rather long series of chained function calls (especially considering
the other objects that already needed to be passed to it). However, I had a better idea. I made
`GenericEditorWindow` implement an `EditorWindowInterface` interface. This interface had only one
method; `addWindowClosedListener`. This method would accept a `Consumer<WindowEvent>` functional
interface as an argument, and the implementation would construct and add a `WindowListener` to the
JFrame, with the `windowClosed(WindowEvent e)`  method in that calling the `accept(e)` method of
that functional interface which was passed to it. This would allow OH-HECC to get the constructed
`GenericEditorWindow` as an `EditorWindowInterface`, and promptly add the appropriate listener to it,
without needing to deal with the overhead of passing the listener through all the methods between
OH-HECC and the actual constructor of the window itself. Later on, this method would be revised to
accept a `Runnable` instead of a `Consumer<WindowEvent>` (running it via `.run()` instead of
`.accept(e)`), due to none of the 'listeners' passed to it via this method actually using the
`WindowEvent` argument.

I also eventually added a `PassageStatus` enum to the `PassageEditingInterface` file, mostly for
displaying the status of the passage in HECC-UP (whether it had a link, didn't have any links,
or was empty).

Now, with these things done, I could finally move on to developing the rest of OH-HECC.

Before I get on to discussing OH-HECC, however, I will also discuss one class which was developed
later on during the MVP development cycle, but is still very much related to the topic at hand;
the `GameDataObject` class.

## 3.2.1.C: The GameDataObject Class

This was developed after most of the meat-and-bones of OH-HECC was developed, as a single class
to encapsulate all of the game's structure, in an attempt to decouple this logic from the passage
model structure. This contains the map of UUIDs and `PassageEditingInterface` objects, along with
the `MetadataEditingInterface` object, with the `Path` to where the .hecc file is, as well as a
`UUID` reference to the start passage. It had methods to obtain passages from the map by UUID,
check whether or not the start passage exists (forcibly creating a new one if necessary), and
several methods to apply operations to the whole passage map (mainly dealing with links between
passages).

The GameDataObject was also used to handle opening the previously discussed editor windows.
The metadata editor window was opened by a dedicated method, however, to open a passage editor window
for a given passage, OH-HECC would need to specify the UUID of the passage which needed to be edited;
the GameDataObject would then handle opening and returning the PassageEditorWindow for that passage.
The editor windows would also have a `windowClosedListener` added to them by the GameDataObject, to
ensure that the start passage still exists, and to update the linked UUIDs of the passages if
necessary. Additionally, I created an `EditWindowGameDataInterface` (implemented by the
GameDataObject), which the GameDataObject would pass itself as to the constructor of the editor
windows. This exposed only the methods of the GameDataObject which needed to be exposed to allow
the necessary passage/metadata editing operations to still happen as expected; such as handling
the deletion of passages via UUID, the renaming of the start passage, and obtaining the metadata
and the map of passages. In hindsight, I should have made the passage editor window simply hold
a UUID passage reference (instead of a proper `PassageEditingInterface` object), and then the
operations to edit that passage should have been in the form of methods in this interface for
the GameDataObject which would send the UUID of the passage being edited and the new value for
the aforementioned field. This way, I could have hidden the passage map properly from the GUI
(not even having to call a method to get it from the `EditWindowGameDataInterface`), as all the
operations with it would have been on this lower layer. However, the obvious problem with this
approach would have been that the large quantity of getters and setters this would require in
the interface could get particularly unwieldly, and there would be some overhead from having
to access the appropriate passage object from the map of passage objects when using any of those
getters and setters.

I also created an `MVCGameDataInterface` interface, to act as an intermediary between the
GameDataObject and OH-HECC's `PassageModel`. I didn't get around to implementing this during the
MVP stage of development, however, I was able to get this implemented later on, during the 2nd term.
I still should have decoupled them further (as the `PassageModel` was still directly storing copies
of the passage map and the editable metadata), however, most of the logic which manipulated the
map of passages which used to be done within OH-HECC directly had been moved to the GameDataObject
(and replaced within OH-HECC with calls to the moved methods). A few unit tests had been produced
for the GameDataObject, mostly regarding the start passage logic within it, which can be seen [here](../src/oh_hecc/game_parts/GameDataObjectTests.java).

The final thing which the `GameDataObject` was given responsibility for was saving the .hecc files.
This used to be within the `PassageModel` as well, but was moved to the `GameDataObject` for the
reasons discussed above. The `Path` attribute within the GameDataObject, passed to it in the
constructor, was the reference to where the .hecc file being edited was saved. The `GameDataObject`
itself wasn't responsible for the parsing of aforementioned .hecc file though; the constructor
received the already-parsed map of passages and editable metadata with that Path. The practical
upshot of storing this `Path` was that, to save the .hecc, the save routine didn't require any
arguments; the necessary data and where to store it was encapsulated in the class already. The save
routine went through a total of 4 iterations.

The first one was a very 'quick and dirty' one-liner, (ab)using the Stream API. It would get the
`toHecc()` string of the `EditableMetadata`, followed by a newline. Then, it would go to the passage
map, get the stream of the values (passages) stored in it, use the 'map' operation to obtain the
`Heccable::toHecc` versions of each of the passages, and then concatenating each of those hecced
passages (each followed by a newline) to the metadata hecc string, returning that string. The reason
why this was the 'and dirty' method was because there wasn't any obvious order to the passages in the
output .hecc file; yes, they were technically ordered according to the hashed version of the UUIDs
which each passage was assigned, however, if an author wanted to edit the game manually, the passages
would appear to be in a completely random order, which could have gotten frustrating. So, I had to
think of a better idea.

I then considered using a breadth-first approach for the passages: I would obtain a copy of the set
of all the passage UUIDs, a list of 'child passages' to output/to queue up for outputting, and find
the start passage, putting its UUID in the 'output next' list (and removing it from the set of all
UUIDs that hadn't been found). I would then copy the 'queued' list to the 'current' list (clearing the
'queued' list), then, for each UUID in the 'current' list, I would add that passage's `toHecc()`
string to the overall .hecc string, remove it from the 'current' list, and find the UUIDs of the
passages it's linked to. For each of these linked passages, I would check if they were in the set of
passages I still needed to find; if they were, that UUID would be removed from the 'not found' list,
and appended to the 'queued' list (if it wasn't in the 'not found' list, it would be skipped). When
the 'current' list was empty, I would copy the 'queued' list to the 'current' list, empty the
'queued' list and repeat until both the 'queued' and 'current' lists were empty. However, if there
were still leftover passages in the set of passages that hadn't been found, I would add the first
UUID from that to the 'queued' list, removing it from the 'not found' set, and resume with copying
it to 'current', restarting the process like this as many times as necessary until all the passages
were output to the .hecc file. Unlike the first approach, the passages were in a somewhat logical
order, so a writer could make sense of it. However, there was one minor problem: it lead to completely
unrelated passages frequently being put next to each other (such as two passages from different
branches), simply because they were the same depth, which made the .hecc file still somewhat difficult
for a human author to read and edit. So, I needed a different approach.

I ultimately went with a recursive, depth-first approach. The reasoning for this would be that all
the passages for a single branch would be next to each other, continuing until that branch ended
(or merged back into an earlier branch), ultimately presenting the nonlinear game structure in a
way that made a bit more sense in a linear .hecc file. Similar to the breadth-first approach, I used
a set to keep track of which passages hadn't been output yet, so I wouldn't end up outputting the
same passage twice, and I would still use the same approach for restarting the process if any
parentless passages were left over after all the connected passages had been output. For each
passage, I would check if its UUID was still in the set of all un-output passages, then I would
append its `toHecc()` contents to the `hecc` string. I would then obtain the set of passage UUIDs it
was linked to (initially I made a mistake here by making a shallow copy instead of a deep copy; but
this was fixed in term 2), use `retainAll` to remove any UUIDs of passages which had been output,
then I would iterate through each of those children, outputting them with the same process (and
double-checking that they hadn't already been output before outputting them). Surprisingly, this
approach actually worked.

In term 2, I edited the save routine to include a catch block for a `StackOverflowError` (just in
case this recursive routine got overwhelmed by a large quantity of passages and/or links). If a
stack overflow exception was to happen, it will just use the 'quick and dirty' stream-based method
to get the .hecc string instead, as that version doesn't faff around with trying to output the
passages in a readable order (which, if the game was already large enough to cause a stack overflow,
is likely to still be problematic), it'll just output it in an arbitrary order.

## 3.2.1.D Some Diagrams

Here is a full class diagram for all of these components, as they were when the MVP was submitted.
Several tangentally related classes from other packages (the `HeccCeption`s, as well as the `Vector2D`
and `AttributeString<T>` from the `utilities` package) have been omitted, for purposes of making the
relationships between these classes clearer. (for more information about the `utilities` package, please
refer to the package-level documentation for it, which can be read [here](../src/utilities/README.md))

##### Figure 24: Full class diagram for the  OH-HECC data classes

![full component diagram](./MVP%20development/data%20classes/The%20component%20editing%20classes%20dependencies.png)

Here is a more basic overview of the key interfaces and classes discussed earlier on, omitting the
inner details (as those are in the above diagram), and some of the classes which are a bit less
important in the overall structure:

##### Figure 25: A more abstract overview of the OH-HECC data classes

![simpler component diagram](./MVP%20development/data%20classes/OH-HECC%20game%20data%20classes.png)