# Development of the MVP version of OH-HECC

## 2.A: The basis for OH-HECC's architecture

As mentioned in the design section, I was planning on giving OH-HECC a model-view-controller
architecture, similar to the one I have used in the past for a few other personal projects.
That architecture can be seen below:

##### Figure 26: The MVC architecture which I was planning to re-use for OH-HECC

![general MVC architecture](./MVP%20development/oh-hecc%20development/general%20MVC%20architecture.png)

Everything would basically be in the same 'Packij' package (descriptive name, I know). The `GameFrame`
would be responsible for a JFrame which would hold the `View`, filling its content pane.
The `View` would be a `JComponent`, and would hold a `Model`, given to it by the `GameRunner`,
allowing it to be rendered. The `GameRunner` would hold the aforementioned `GameFrame`, `View`,
and would generally hold multiple `Model` objects, only one of which would be considered 'active'
at a time (but that wouldn't be necessary for this particular use case). The `Model` effectively
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
To draw these objects, an `AffineTransform` would be used to offset the 'drawing' origin for the
object in question to be at it's actual position, then the object would be drawn about that offset
origin,after which, the `AffineTransform` would be reset to how it was beforehand, so the process
can be repeated for every other object's draw operation. Finally, running the update/draw loops,
as well as swapping between active `Model`s/resetting them, would be managed by the `GameRunner` class.

Why am I telling you about this? It's because I was planning on generifying this further to produce
OH-HECC. Instead of running a game and reading from/writing to a high scores file, it would just be
running a 'model editor', and reading the model's current state from/writing the current state to a
.hecc file.

On the topic of reading the state from/writing the state to a .hecc file...

## 2.B: The OhHeccParser.

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

## 2.C: The Actual MVC architecture

The initial plan for OH-HECC's MVC architecture looked something like this:

##### Figure 27: The plan for OH-HECC's MVC architecture

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
window, and, when pressing the arrow keys, the viewable area should scroll/the viewport/model should
move.

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
methods of this GUI component, omitting the update loop entirely. The draw operation and update
operations of the `Model` are still synchronized (via a single, static, `SYNC_OBJECT`), so only one
of those operations may happen at a time, so the model won't suddenly become completely different
mid-draw operation. Furthermore, instead of iterating through the `PassageMap` when attempting to
draw the `PassageObject`s, the `values` set of that map is deep-copied into a `DrawablePassageObjects`
set, and this set is iterated through instead by the draw thread, so, even if the `PassageMap`
somehow gets modified whilst the draw operation is happening, there won't be any problems caused by
concurrent modification.

In the MVP iteration, I didn't do this in an entirely optimal way. I had a `MouseController` class
which implemented `MouseListener` (as well as `MouseMotionListener`), to listen for mouse inputs,
and then call the appropriate methods of the `PassageModel`, via a `MouseControlModelInterface`.
This object would also have a reference to the `JFrame` which actually held the `View` (and the
`Model`), so, after an input is performed, it could call the `repaint` method of the `JFrame` to
ensure that the entire viewable area would be updated appropriately. An instance of this class would
be added as a `MouseListener` and as a `MouseMotionListener` to the content pane of the frame, so,
when obtaining the location of the mouse relative to the model (when clicked/dragged), it won't
be offset due to the dimensions of the frame itself being different to the dimensions of its content
pane. However, the part that listened for the keypresses, to scroll the window, was implemented in
a rather terrible way. I stupidly implemented this in an anonymous class, within a method of the
`OhHeccNetworkFrame` class, and would call the methods to move the model via the `View` object held
within the `OhHeccNetworkFrame`. Eventually, after the MVP iteration, I recognized this stupidity,
so I fixed it. I refactored the `MouseController` class to be a more general `ModelController` class
(implementing `KeyListener` and moving the key-listening methods from that anonymous class to this
class), extended the `MouseControlModelInterface` into a `ControllableModelInterface` (adding the
scrolling methods to this interface, thereby allowing the improved controller to use those controls),
and replaced the method call which added the anonymous `KeyListener` to the frame with one
that added the `ModelController` to it (again, as a `KeyListener`).

The viewport scrolling and the window resizing were both new challenges for me to overcome. In my
previous games, I didn't really have these (besides one game which presented the illusion of
scrolling, by moving certain game objects down whilst the player's avatar moved up, stopping the
'scrolling' movement when the avatar reached a certain fixed Y position), so, I would need to
implement these from scratch. The way I chose to implement the viewport scrolling was by
having a `Vector2D` to hold the current 'top left corner' of the viewport. Although, for
some reason, I called the variable `topRightCorner` when I made it, and I didn't realize this
mistake (and rename it) until a few minutes ago, but it still was the top-left corner.
When drawing the model, it would translate the `AffineTransform` of the `Graphics2D` context
by the `x` and `y` of that `Vector2D`, so all the subsequent `draw` operations on the
`PassageObject`s would have that initial offset already applied to them, so, because
they will have been moved, it looks like the viewport had been scrolled. This scrolling
offset would be undone before displaying the 'buttons', so they would all remain in the
fixed location at the bottom edge of the model.

This wasn't the first approach for scrolling which I thought of.
Initially, I considered having a static 'offset' `Vector2D` in the `AbstractObject`
class, my thought process being that I could subtract (or add) that from the position
in the draw thread (so the resulting draw position would be offset appropriately).
I actually started to implement that, however, this approach was abandoned upon
realizing that it was inefficient (requiring the offset to be individually applied
in every draw operation), and, due to how I opted to render the `StringObject`s
and the `PassageLinkObjects` (pre-offset by the position of a 'parent' object,
so they're at the origin of that object), this approach lead to those objects
having twice as much scrolling applied to them than was intended. The final nail in
the coffin of the first approach was the problem of clicking the objects. In the title
screen menus for my previous games, they would be interactable via the user clicking
the model, and then the game would see if the `Point` location of the click
intersected with an 'areaRectangle' `Rectangle` of some of the `GameObject`s which
should do something when clicked; if they intersected, the update loop would then
perform the appropriate action. However, with this approach, there wasn't an easy
way to offset the 'areaRectangle's for the `PassageObject`s, to make them clickable
after the scroll, without leaving these untouched for the `ModelButtonObject`s, so
they would remain clickable.

This issue with clicking was avoided entirely with the 'top right corner' approach.
I stored each of the `ModelButtonObject`s as raw variables in the `PassageModel`,
so, in the 'left click' method of the `PassageModel`, it could first use the raw,
non-offset, `Point` mouse position, seeing if any of those buttons were pressed
individually via a sequence of 'if-else if...' statements (to check if the point
intersected with a given button, and, if one does intersect, it was pressed, so
the action which was supposed to happen in response could happen). If none of
those buttons were pressed, it would go to the 'else' branch, to see if it
intersected with the areaRectangle of any `PassageObject`. However, before
attempting to find the clicked passage (if any), it translates the click location
`Point` by the topLeftCorner `Vector2D`'s x and y values, then sees if this
translated point intersects with a `PassageObject` (opening the appropriate
`PassageEditorWindow` for the clicked `PassageObject`, if one was clicked).
In other words, instead of moving every 'hitbox', I'm just moving the
single thing that is supposed to hit the 'hitboxes'.

This approach was also used for the 'dragging passage objects by holding
the mouse down on them' functionality of the `PassageModel`. In the 'left
press' function, I start off by translating the mouse press location by
the scroll amount, but, I then store the translated press location in the
`PassageModel`, as a 'currentLeftDragPos' `Vector2D`. Then, if this translated
mouse position intersects with a `PassageObject`, I put that in a set of
'selected' objects, and call a 'selected' method of that `PassageObject`,
which gives it a blue overlay when drawn. Then, in the 'left mouse drag'
function, I translate the mouse input by the scroll offset (again), convert
it to a `Vector2D`, find the difference between the 'currentLeftDragPos'
set in the most recent function call and the current one, and overwrite
the 'currentLeftDragPos' with the current translated mouse position. Then,
with the already-found difference, I can then move the selected passage
object(s) by calling their 'move(Vector2D moveVector)' methods (updating
their positions by the mouse movement vector), before then invalidating
and repainting the model. The 'selected' set is cleared (also deselecting
any 'selected' objects) within the 'left release' method of the
`PassageModel`, called when the left mouse button is released. It also
appears that the Java Virtual Machine can differentiate between mouse
clicks and holding the mouse, so, at least from my experience, the dragging
doesn't accidentally lead to an editor window being opened.

Now the scrolling has been discussed, I should probably cover the resizing.
There were two ways I could have done this (or three, if you count 'not
letting the user resize the window'). I could have made the various
viewable components in the passage model have a constant relative size,
so, when the user resizes the window, I would need to apply a scaling
operation to the affine transformation when drawing the window, so
everything would be rendered at the same scale relative to the window.
I decided against this approach, partially due to the potential problems
with resizing the clickable areas for all the passage objects, but also
because it would mean that the user's viewport of the passage map would
always be restricted to a single, relatively small area of it, which
could get frustrating, and it could lead to the view appearing too
zoomed in. So, I opted for the other approach; extending the drawable
area when the window is resized, allowing more of the passages to be
rendered. When Java is rendering graphics, the top-left corner of the
viewable area is considered to be the origin, so, regardless of how
big/small the viewable area is, the offset from that will remain
constant. I was able to just add a `ComponentListener` to the `JFrame`
in the `OhHeccNetworkFrame` in order to listen for window resize
events, and then get that to call the `setSize` method of the `View`,
which it had inherited from the `JComponent` base class, in order
to resize the drawable area of the `PassageModel`. However, there
was one problem (technically four) which still needed to be addressed;
the `ModelButttonObject`s.

These needed to remain at the bottom of the viewable model, like a
toolbar of sorts, and had to collectively fill the horizontal width
of the viewable model, regardless of the current horizontal width
of the model. I had already defined each button to start/end at a
certain relative position on the 'toolbar' at the bottom of the
model, expressed as a float, so all I needed to get now was the
total width/total height of the model so it could be rendered at
the appropriate position/width. The first solution I used for
this problem was honestly terrible; I made the width and height
of the model static, and accessed those via static references
in a resize operation for the buttons, called by the
`PassageModel`'s resize operation, whenever that gets called
itself by the `View`'s resize operation. I then realized that
this, again, was a stupid idea, because I could just pass the
new size from that resize operation directly to the resize
operation of the buttons, omitting the need for any reference-related
tomfoolery. The positions of these are also set with the resize
operation, such that the middle of the rectangles are at the
fixed relative position at the bottom of the viewable model
at all times, mostly so the `StringObject`s displayed over the buttons
can be rendered there, just like with the `StringObject`s on the
`PassageObject`s. Finally, as mentioned earlier, these 'button'
objects are, in reality, mostly decorative. Yes, they have a
method which can detect if they are clicked, and return true
if they are clicked. However, the buttons themselves are not
responsible for doing the action which is performed after
they're pressed; that's done by the `PassageModel` itself,
because trying to make the buttons themselves call a method
reference to do a certain thing would just be overcomplicating
matters.

The `StringObject` simply renders a string on the model, via the `drawString` method of `Graphics2D`.
This was repurposed from a few of my earlier projects, so, it has a few bits of additional
functionality built in to it, mostly to make it look nicer. In the constructor, it's possible to
specify initial text for it, as well as an alignment for the string when rendering it. In the MVP
iteration of this class, there was functionality to define what font should be used for the text,
however, upon realizing that not every user may have the font I might want to use installed, I have
opted to make it simply render the text using a bold version of the default font used by the JVM on
the user's own computer (in my case, it defaults to Segoe UI). The alignment options work by rendering
the text at a different location relative to the actual 'position' vector of the `StringObject`. The
`drawString` method of `Graphics2D` takes an x and y position as arguments along with the actual
`String` being rendered, rendering the string such that the first character is at the specified
position. When using the `StringObject.LEFT_ALIGN` setting, I don't modify the rendering position,
so the string is rendered aligned to the position on the left. For the `MIDDLE_ALIGN` and
`RIGHT_ALIGN` modes, I use the `getFontMetrics` method of the `Graphics2D` object to obtain a
`FontMetrics` object for the current font, which I then use to find the total width of the String I'm
trying to draw. This is probably not the most efficient way of obtaining this width data, as I need to
re-obtain the `FontMetrics` for every single `StringObject` in every draw operation, even if they all
have the same font. However, there is no way to obtain `FontMetrics` outside of the draw operation,
and doing it like this does mean that there is still the option of giving different `StringObject`s
different fonts, without breaking the alignment code for them. Once I have this width, I can then
offset the `x` position used for rendering the `StringObject` by a desired amount, depending on the
alignment. For the `MIDDLE_ALIGN` mode, I subtract half the width from `x`, so the midpoint is at the
'position' of the `StringObject`. For `RIGHT_ALIGN`, I subtract the full width from `x`, so the
`StringObject` is rendered such that the right edge is aligned with the 'position'. Within OH-HECC,
I only used the `MIDDLE_ALIGN` option, so I could have omitted the left/right alignment settings from
it, but I kept them in there, just in case I found a reason to use them. The outlines that these
strings have are rendered in a way that basically is cheating. I could have tried to obtain the
outline shapes for every single character, and rendered them over every character, giving them a
perfect black outline. But that would have been too convoluted, when there's a much simpler workaround.
When obtaining the offset for `x` necessary to render the string with the desired horizontal alignment,
I had it stored as a local variable within the method, meaning I wouldn't need to recalculate it.
I then render the string four times, in black, with x/y positions that are `+/-1` pixel from the
calculated offset position. Then, I render the string one final time, this time in white, at the
precalculated offset (no `+/-`ing involved). Because the black copies of the string had been offset
from the white copy, they, when combined, look just like a single black outline for the white string.
This method isn't entirely perfect, as parts of the default string with a height/width of 1 pixel
will not have an outline next to the edge which is 1 pixel long, such as the sides of the dot on a
lowercase 'i'. This could easily be fixed by adding another four black strings to the 'outline',
these ones being `+/-1` in one axis only (no offset on the other axis), however, I opted not to do
this, because I would almost be doubling the draw operations for the `StringObject`, for a minor
problem that is only present in very few situations. I did mitigate the problem anyway by making
the `Graphics2D` object render them in a bold version of the default font, and bold fonts tend to
not have many single-pixel elements in them anyway, further reducing the number of cases where this
problem could appear. There is one limitation to the `StringObject`: They cannot render a string with
newlines. These only use a single `drawString` call (if you don't count the outlining), and the
`drawString` method cannot draw newlines, so, if I wanted to render multiple lines of text, I would
need to use multiple `StringObject`s. However, OH-HECC doesn't need to do this anyway, so this problem
has been avoided. I was also considering adding in something that could be used to cut off the string
if it had a width beyond a certain horizontal limit, so the text on the `PassageObject`s would not
expand past the bounds of the `PassageObject`'s shape. I opted against this, mostly because I actually
liked the appearance of the text occasionally going over the limit of the bounds, and also because, if
by letting a user see the full passage names at all time in the editor, they won't be able to mistake
one passage for another.

Now, the functionality of OH-HECC. In the MVP iteration, the buttons along the bottom of the 'model'
would, when clicked, allow the user to save their `.hecc` file, save their `.hecc` file and close
OH-HECC, open the metadata editor window to edit the game's metadata, and add a new parentless passage
to the passage map. Initially, the `PassageModel` was also responsible for performing every single
operation on the raw passage map/`EditableMetadata`, however, a lot of that logic had been moved
to the `GameDataObject` later on. The only `PassageMap` logic retained within the `PassageModel` was
that which directly involved the map of `PassageObject`s etc within the `PassageModel` as well.
This selection of options would be enough to make OH-HECC usable for its intended purpose of allowing
users to edit a .hecc file. Later on, the 'save and quit' button would be replaced with one that would
save the game and open HECC-UP, to make HECC-IT somewhat more 'integrated'. I also created a
`PassageStatus` enum, which an `EditablePassage` would have, accessible via a method in the
`PassageEditingInterface`, to indicate the 'status' of the passage. At first, a passage had only three
'states'; It could be a `NORMAL` passage (at least one link), an `END_NODE` (no links), or it could
be a `DELETED_LINK` (if it had a link to a passage that had since been deleted, denoted by the
`! WAS DELETED !` suffix appended to the end of the passage name in the links to it in other passages
upon deletion). Then, in the constructor and update method for the `PassageObject`, it would query
the 'status' for the passage it was associated with, so, depending on the status, the fill colour for
the `PassageObject` would change appropriately; `NORMAL` passages would be orange, `END_NODE`s would
be yellow, and `DELETED_LINK`s would be red. This meant that an author could spot any problems with
deleted links in the overview of the network of connected passages, without needing to look through
the editing window of each passage individually.

On that note, I also added a `StartHighlightObject`, which would appear as a rounded light-blue
rectangle, which would appear around the start passage. Whenever the start passage's name was changed
(whether due to the passage itself being renamed, or due to the metadata referencing a different
start passage), the appropriate `PassageObject` would be found, and would be given to the
`StartHighlightObject`, which it would reference as an `AbstractObject`, as it would only need to
access the `Vector2D` position of it (this reference would later be abstracted out further to be
performed via an `ObjectWithAPosition` interface). The `StartHighlightObject` would be drawn before
anything else, so it would appear as if the start object has a blue highlight around it; this, in turn,
will allow the author to see what the current start object is, so, if the wrong passage is being used
as the start passage, the author can easily see this, and will know that they need to fix it.

##### Figure(s) 28: Some screenshots to show the basic functionality for OH-HECC

Here is a screenshot of the OH-HECC passage model:

![oh-hecc screenshot](./MVP%20development/oh-hecc%20development/oh%20hecc%20screenshot.PNG)

Here is a screenshot of the same passage map, but with the window successfully resized:

![oh-hecc screenshot resized](./MVP%20development/oh-hecc%20development/oh%20hecc%20screenshot%20resized.PNG)

Here is another screenshot of that passage map, with the viewport scrolled as well:

![oh-hecc screenshot scrolled](./MVP%20development/oh-hecc%20development/oh%20hecc%20screenshot%20scrolled.PNG)

Here's yet another screenshot, this time with the passage objects moved around:

![oh-hecc screenshot moved around](./MVP%20development/oh-hecc%20development/oh%20hecc%20screenshot%20moved%20around.PNG)

Here's a screenshot of what it looks like when a new passage is added to the passage map:

![oh-hecc screenshot new link](./MVP%20development/oh-hecc%20development/oh%20hecc%20screenshot%20new%20link.PNG)

Here's what it looks like when we delete `bob`, leaving `Right` with a deleted link:

![oh-hecc screenshot deleted link](./MVP%20development/oh-hecc%20development/oh%20hecc%20screenshot%20deleted%20link.PNG)

There was some other functionality which I was considering adding to OH-HECC which I never quite got
around to doing. The `PassageModel` contains an inner `CurrentActivity` enum, which contains all of
the actions I considered allowing the author to do. In short, the author was supposed to be allowed
to be doing nothing (implemented in MVP), have an editing dialog open (implemented in MVP), have
some objects selected (somewhat implemented), move aforementioned selected objects (implemented in
MVP), hold the right mouse button to move the view around (implemented as of the time of writing this
report), and be able to drag a selection box to select multiple objects at once by holding the left
mouse button (unimplemented). There was a `BUTTON_PRESSED` value in that enum as well, but that was
ultimately rendered redundant as those activities generally fell under the umbrella category of
`DIALOG_OPEN`. The purpose of this enum was to ensure that the author wouldn't be trying to do two
separate editing operations on the view at once, via switch statements in every 'receiving input'
method, only performing certain actions in response to the input if the current activity allowed
the activity to happen. Below is a full state machine of how exactly that worked:

##### Figure 29: The 'CurrentActivity' logic within OH-HECC, expressed as a state machine

![PassageModel Activity State Machine](./MVP%20development/oh-hecc%20development/PassageModel%20Activity%20State%20Machine.png)

I was also planning on adding some sort of method that the author could use to search through the
map of passages for a given passage, however, I never got around to adding that.

Finally, for the save routine in the `PassageModel`, I also implemented a 'fallback' of sorts,
in case there was a problem writing to the `.hecc` file. In these cases, the user would be informed
via a `JOptionPane` about the save operation failing, so the 'emergency save routine' would be used
instead. After dismissing that dialog, the user is presented with a new `JFrame`; at the top,
there are some `JLabel`s to tell the user that this is their .hecc code; in the final version, it
makes it more explicit that they need to copy and paste the .hecc code into a new .hecc file manually.
Below that is a (read-only) `JTextArea` in a `JScrollPane`, containing the .hecc code. It's presented
in a `JTextArea` because, by having it in a `JTextArea`, the user can actually select the text,
allowing them to simply copy and paste it into a new .hecc file manually. It isn't a very elegant
solution to these sorts of problems, however, in the cases where this fallback has to be used instead
of the normal save routine, 'elegance' is going to be the least of the user's concerns. A screenshot
of this is below:

##### Figure 30: OH-HECC's emergency save routine

![emergency save routine](./MVP%20development/oh-hecc%20development/mvp%20backup%20save%20method.PNG)

The entire 'MVC' part of OH-HECC wasn't unit tested, mostly due to the inherent awkwardness with
unit-testing a GUI. So, I improvised with a main method that would read a pre-defined .hecc file,
parse it into the game data, and then open that game data within OH-HECC. I informally played around
with the editor, to see if everything behaved as expected, and for the most part, it did. So, as far
as I was concerned for the MVP, it was good enough.

Now, with the main editing GUI of OH-HECC done, there was just the problem of how the user would
open OH-HECC to edit their .hecc files (and yes, the class diagram for OH-HECC will be shown after
that last part)

## 2.D: ChooseFile and OhHeccRunner

I knew I needed a GUI which could be used to create a new .hecc file, or select an existing .hecc file,
which could then be opened in OH-HECC. So, I started to create one, planning on effectively repurposing
the existing 'choose a .hecc file' code from HECC-UP. However, there was the problem of what I wanted
it to look like. I wanted to give users the option to either create a new .hecc file or start editing
an existing .hecc file, and I didn't want it to look like one option was more important than the other.
Therefore, I chose to put the two next to each other. Unfortunately, I wasn't sure how to code this
manually, so I decided to try using IntelliJ's GUI designer to implement this. The 'create file' area
would need two text fields (for a game title and author name), with a button which would open a
`JFileChooser`, allowing the user to choose where they want to save their new .hecc file, before
automatically opening OH-HECC properly. The 'open file' area would need a `JTextArea` to show the
filepath of the currently-selected file (if any), a button to open a `JFileChooser` to select a
.hecc file, and a button, only visible after a file has been chosen, which then needs to parse the
.hecc file and open it in OH-HECC.

Using the GUI designer, I was able to put together the 'ChooseFile' panel, which looked like this:

##### Figure 30: The 'ChooseFile' panel

![ChooseFile panel](./MVP%20development/oh-hecc%20development/choosefile.PNG)

I wasn't able to get both of the main panels in this to be the same size as each other without
removing the vertical bar between them, and, in the final iteration (not in MVP version), that
vertical bar was removed. One rather nice design choice with the `ChooseFile` class is that the
class effectively only constructs a `JPanel` to put the GUI on, instead of constructing a `JFrame`
to put the GUI on. This means that the main `OhHeccRunner` class can create a `JFrame`, then
constructs an instance of this `ChooseFile` class (making this GUI), obtains the the `JPanel`
constructed by this class, and then puts it on the main `JFrame`. Then, when launching the actual
OH-HECC editor, instead of opening a new `JFrame` which the user would then need to manually
navigate to, the existing `JFrame` can be passed to the `OhHeccNetworkFrame`, basically keeping this
all in a single `JFrame`. However, once opening the 'OH-HECC' editor, the program cannot go back
to this 'choose file' screen, so a user would need to reopen the program.

Like in the `MetadataEditorWindow`, I have the same sort of automatic validation built in to the
game title and author name inputs, so, if the inputs are invalid, the text will be rendered in red.
However, to get the point across to the author even more explicitly, these text fields also have an
`InputVerifier` specified for them: the author won't be able to start editing the other input field
unless they fix the invalid input in that input field. They can still press the button to try making
the .hecc file, but it will complain about the invalid input being there. There is one more check
which isn't performed in the EditableMetadata: the title will be checked to see if it would be usable
as part of a valid filename, by seeing if a `Path` can be constructed in the form `titleInput.hecc`:
If this fails (due to the title input not being usable in a filename), the title will be deemed as
invalid. This additional check is performed because the new .hecc file constructed will be called
`titleInput.hecc` (replace 'titleInput' with whatever name was input in the title input `JTextField`),
and an author would probably prefer it if this check was performed sooner, rather than later, when
they're actually trying to save their .hecc file. Some screenshots of this validation in action are
below:

##### Figure(s) 31: The ChooseFile panel upon entering invalid metadata

![invalid author](./MVP%20development/oh-hecc%20development/choosefile%20bad%20author.PNG)

![invalid author pls to fix](./MVP%20development/oh-hecc%20development/choosefile%20bad%20author%20pls%20to%20fix.PNG)

![invalid title pls to fix](./MVP%20development/oh-hecc%20development/choosefile%20bad%20title%20pls%20to%20fix.PNG)

The 'open existing hecc file' panel is much less interesting. It does exactly the same thing as the
'open hecc file' part of HECC-UP, asking the user to select a .hecc file. When one has been selected,
it will show the selected .hecc file, and it will reveal the button which can be used to start editing
that hecc file. This can be seen in the below screenshot:

##### Figure 32: The ChooseFile panel after the user selects a .hecc file

![picked a file](./MVP%20development/oh-hecc%20development/choosefile%20selected%20file.PNG)

That is just the surface-level functionality. There's a lot more going on under the hood.
The functions which create the .hecc file/open a .hecc file for editing aren't actually held within
the `ChooseFile` class. Instead, those methods belong to the `OhHeccRunner` class. An existing .hecc
file is edited with `openAndStartEditingFileAtLocation` (takes a `Path` to a .hecc file as an input,
attempts to parse it into a `GameDataObject`, before trying to open it in the editor), whilst a new
.hecc file is created for editing in `makeNewHeccFileAtLocation` (takes a `Path` to the new .hecc
file, along with a `MetadataEditingInterface` constructed by `ChooseFile` holding the inputs,
before constructing a `GameDataObject` with that Path/Metadata, writing that gamedata to a newly-made
empty .hecc file at the specified path, before opening that `GameDataObject` in the editor). If
there is a problem with these operations, these methods will return false. These are passed to
`ChooseFile` via functional interfaces (specifically, a `Predicate<Path>` and a
`BiPredicate<Path, MetadataEditingInterface>`), allowing `ChooseFile` to use those methods without
`ChooseFile` ever needing to reference the `OhHeccRunner` itself. This approach was used because,
that way, I could create a main method inside the `ChooseFile` class to run `ChooseFile` and verify
that it worked correctly, without needing to create the `OhHeccRunner` class.

The specific way that `ChooseFile` called those methods is a bit on the convoluted side.
When the buttons are pressed, the opening of the `JFileChooser` (and, in the case of the 'make file'
button, double-checking that the inputs are valid) happens first. Once the .hecc file/the path to
the .hecc file has been found, the lambda operation which actually parses the .hecc and opens OH-HECC
is performed using an anonymous `SwingWorker`. I chose to do it in a `SwingWorker` thread instead of
doing it in the event handling thread, just in case it takes a long time to parse the .hecc file
(which could be an issue if it's a very long .hecc file), so the entire program doesn't freeze in the
meantime.

## 2.F: Some last minute changes to HECC-UP

It was at this point when I found out that, when I was creating the regexes for OH-HECC, I
accidentally made HECC-UP's hecc parsing somewhat incompatible with OH-HECC's (meaning that a .hecc
file produced by OH-HECC wouldn't actually be read properly by HECC-UP). Therefore, to minimize code
duplication, I added a dependency between HECC-UP's passage/metadata classes and OH-HECC's
passage/ metadata interfaces, thereby ensuring both could use the same regexes/parsing methods,
without needing to completely rewrite them from scratch. I also chose to make one final improvement
to HECC-UP: upon successfully exporting a game, the author is now asked if they want to open the game
to test it out. If the user selects yes, I use Java's `Desktop` API to try opening the exported
`index.html` file in the author's default browser (allowing them to play the game). The final
improvement was that when clicking on the 'choose file' buttons when that particular file had already
been chosen, the `JFileChooser` would start from that currently selected file, instead of going back
to the default directory, just in case the user wanted a different file from that same directory (or
from a nearby directory).

## 2.E: The Long-Awaited Class Diagram

Finally, here's a class diagram for the entirety of the MVP version of OH-HECC (and HECC-UP).

##### Figure 33: A full class diagram for the entire MVP iteration of HECC-IT.

![chungus](./MVP%20development/big%20fat%20class%20diagram.png)