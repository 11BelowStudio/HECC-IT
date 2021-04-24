# (3.2).1. Designing HECC-IT

## 1.1: A recap of the literature review.

In case you wanted a recap of what I found out during the research I performed on the tools,
a short summary of them can be read in the [recap of the literature review](./recap%20of%20the%20literature%20review.md)
document.
          
As explained earlier, I found an obvious gap in the market; There is no hypertext game authoring system
which allows a writer the flexibility to freely choose between raw code and a GUI. Suppose a writer wants to write a
game using a system that allows them to have a graphical overview of the game they're writing; They must commit to
using a GUI-based tool, and, even if they want to make a rather small change somewhere, they need to go through the
effort of opening the GUI tool and then opening the work-in-progress game in the tool and editing it via the tool.
Suppose a writer wants to be able to write the raw code without needing to deal with a GUI; if that writer wants to
get an overview of how everything in their game is linked together (in case they want to check that everything is as
it should be), they can't.

HECC-IT is intended to fill this niche.

## 1.2: The vision for HECC-IT

* Work-in-progress games are to be stored in a high-level intermediate scripting language, which can
  be edited manually by a user with ease.
  
* There should be a parsing utility which a user may use to convert games from this intermediate
  format into playable hypertext games.
  
* There should also be a GUI which allows users to edit games stored in that intermediate format,
  presenting it as a network of connected passages.
  
* The games produced should be 100% clientside, playable via any modern web browser, requiring no
  faffing around from the author, or the player, in order to actually share/play the games produced
  with this tool.
  
* The tool should work on any PC, regardless of operating system.

* It should comply with the 'Treaty of Babel' standard for interactive fiction bibliography.
    * I knew that there would be nearly 0 chance of HECC-IT ever formally becoming a signatory to it,
      as I know that there is still very little chance of most users deciding to use HECC-IT anyway,
      however, I wanted to make it as simple as possible for HECC-IT to become compliant with it.

GUI editor (if the user wants to use it) <-> intermediate code -> playable game

### 1.2.1: The name

With that done, I needed to come up with a name; after all, if this tool didn't have a name, talking about it would
be a pretty difficult task. I knew I wanted to give it a pretty stupid name, partially because it would be attention-
grabbing, and partially because I felt that giving it a stupid name would make it less soul-crushing to work on.

**HECC-IT** wasn't the first name I thought of (and also wasn't even the first iteration of that particular brand).
Here are all the names I considered at one point in pre-development:

* *jHyper*
    * This half-baked idea was supposed to be along the lines of 'Java-based hypertext game authoring tool'.
      I knew from the start of the project that I would probably be writing the tool itself in Java,
      mostly because Java is the programming language I'm most confident in using.
    * This name wasn't used simply because it sucked. It was boring, generic,
      sounded like a 90s-era serverside programming tool, and wasn't what I was going for.
      
* *HAPPY*
    * Stood for '*Hypertext Authoring and Parsing Pipeline, Y'know?*'
    * It was a stupid name, which is what I was going for, but it felt forced, sounded *too* generic,
      and I could feel myself eventually getting driven up the wall by that utterly terrible name.
    * However, I liked the overall 'acroynm that's actually a stupid word' idea,
      and I figured that a word starting with H would work, so I tried to think about some other words I could use.
      
* *Bob.*
    * Yes, literally just calling the tool '*Bob.*'.
        * As in 'This is Bob. Bob is a hypertext game authoring tool. Say hi to Bob'. 
        * Same sort of energy as that TV channel which is literally just called *Dave*.
    * I decided that if I couldn't come up with a decent funny name for the tool, it would be called Bob.
      Yes, it makes no sense, but some of the names of the other tools also didn't make sense. It balances out.
        * Still less generic than *eHyperTool*.
    
* ***HECC***
    * This is an acronym, standing for *Hypertext Editing and Creation Code*.
    * It gets across that this relates to hypertext things, has code, and allows for editing and also creation.
        * Granted, this was more of a descriptor of the intermediate format I wanted to use,
          but I felt it was good basis to use for naming the other components of this tool.
    * The word '[Hecc](https://www.urbandictionary.com/define.php?term=hecc)' also satisfies the silliness
      requirement, and I'm guessing that this particular name might be somewhat attention-grabbing as well.
        * 'hecc' has been part of internet lingo for some time (I don't have any concrete numbers for this,
          unfortunately, but I have seen it being used regularly enough to notice it at least), so,
          someone might notice the name, think '*who the hecc calls something HECC*?', maybe investigate it
          further, and hopefully attempt to use it.
        * It's also one of those words that's a substitute for a word that's used as a substitute for a word that
          is treated as an obscenity by some people (Hell -> Heck -> Hecc), with some sort of 'not taking itself
          too seriously' attitude about it (because if it was taking itself seriously, it wouldn't use that name),
          like a self-deprecating parody of itself or something.
            * Another benefit of this is that *it's usable as an expletive* (in the grammatical sense)
    * So I went with this.
    
### 1.2.2: The components

As mentioned earlier on, I was planning on producing this as a set of components.
Now that I had a name I could use as a starting point for naming these components, I was able to start to properly
define what the interactions between the components would be.

The first thing I thought of was actually how the outputs would be structured.
At first, before starting on any of the research work for this project,
I was thinking of having the output games structured as a folder of .html files, with each passage being its own
.html file, with the links between each passage being conventional .html links (like how basic websites
are structured), and passing any variables/a stack of the 'visited passage' history as it goes along, via the
(ab)use of HTTP GET or POST requests. I quickly realized that this approach would be stupid, due to the potentially
ludicrous number of .html files that would need to be produced, the inevitable mass-duplication of
boilerplate code, and the massive overhead from all the GET/POST requests that would be happening.
That, and none of the existing systems attempted this approach either, with good reason.

The next idea was to just have it as a single pre-written .html page, with a pre-written JavaScript file that
essentially works  as a finite state machine (partially because CE313 was listed as a co-requisite for this project,
but that module  wasn't on the module directory, so I pointed that out to Dr Bartle and asked him what part of that
module was the  important bit, and he mentioned that the stuff about state machines was relevant;
and partially because every other system effectively worked like that as well), accompanied by another JavaScript
file, containing the game data as the 'states' for the aforementioned finite state machine, which also gives these
states to the FSM. This approach made sense, would lead to minimal re-use of boilerplate code, and didn't have too
many files. I could have put the pre-written JavaScript within the pre-written html file, however, I wanted them
to be separate files, mostly for ease of maintainability when writing them.


Eventually, I produced this overcomplicated diagram,
as a plan for how this system would work, with all the names in it as well:

##### Figure 1: HECC-IT components and their interactions

![HECC-IT component interactions verbose](./design%20images/HECC-IT%20component%20interactions%20overview%20verbose.png)

A simpler version of that diagram was produced later on:

##### Figure 2: An abstracted overview of the HECC-IT components

![HECC-IT component interactions simpler](./design%20images/HECC-IT%20component%20interactions%20overview%20simpler.png)

This what everything would need to do:

* ***index.html*** would need to 
    * be the boundary between the player and the game so the player can play the game
* ***heccer.js*** would need to 
    * deal with actually running the hypertext game that is being played.
* ***hecced.js*** would need to
    * store the game data, and pass this data to the 'engine' within *heccer.js* somehow.
* ***HECC-UP*** would need to
    * take a .hecc file as an input, ensuring that the file contains valid .hecc code
    * construct a *hecced.js* file, as declared in the .hecc file, and export it in a specified folder
    * export copies of a premade *index.html* and *heccer.js* file into the same folder as the *hecced.js* file.
* ***.hecc*** would need to
    * Allow a user to define 'passages' with 'links' between them (and maybe some metadata as well)
* ***OH-HECC*** would need to
    * Read a .hecc file/create a new .hecc file
    * Present an overview of the connected passages in the .hecc file
    * Allow the user to edit these passages/add more passages/delete passages
    * Allow the modified .hecc file to be saved.
    * However, the usage of *OH-HECC* must **not** be a requisite for creating a game via *HECC-IT*.
        * It's intended to be ***Optional*** *Help*. This means that users who do not want to use *OH-HECC* must
          have the option to reject the optional help, and write the .hecc code manually.
          

    

## 1.3: 'But how will the .hecc format work?'

This question is actually deeper than something like 'what is the syntax'. This is also a question about how the
games produced with HECC-IT would be structured.

Firstly, the links: I have noticed several approaches being used by existing systems. These were:

* Having the links in a fixed position at the end of the passages (*Choose-your-own-adventure* book-style)
    * This was how *Quest*, *Inklewriter*, and *eHyperTool* handled the 'links'.
        * To a lesser extent, the 'jump'-based system used by *Ren'Py* and *ChoiceScript* also fall
          under this category.
    * The advantages to this would have been making it easier to handle links in general, as they would all be
      in their own specially-designated parts of the passage content, which in turn would have made guard conditions,
      keeping track of which passages are linked, and other such features easier to implement
    * I opted against this approach, mostly due to the lack of flexibility offered to the writer in terms of
      game structure inherent to this style of link.

* Having the links be their own objects, just like the passages, within the game (unlike links on a website)
    * This is how *Storyspace* handled links.
    * The advantage to this would have been offering the author greater control over the links.
    * I opted against this, because it would have been incredibly complex both to implement,
      and for the author to use (Especially if the author would have wanted to write the .hecc code manually, as
      they would probably need to manually define each link object). Additionally, this could have also lead to
      an unnecessary amount of computational overhead, both in the editor, and in the output games.

* Having the links declared inline within the passage content (like links on a website)
    * This was how *Squiffy*, *Twine*, and *Undum* handled the 'links'.
    * The advantages of this were the flexibility it would give to the author in terms of how they want to handle
      the presentation of the links in their game. Additionally, it would allow the links to be embedded in
      a more natural way in the passage content, making it easier for the user to declare them in the .hecc code.
    * However, it did mean that there would be less control over the links themselves, as they would effectively just
      be part of the normal text.
    * I opted for this approach, as the ease of use and the flexibility they allow appeared to be worthy tradeoffs for
      the lack of control over the links themselves.
      
Then there was the question of the syntax for the .hecc format. This was heavily influenced by the syntaxes of
*Twine*/*Twee2* and *Squiffy*. I was operating under the theory of 'if it ain't broke, don't fix it', and I wanted
to have some familiarity for users of the other tools. However, I did make a few improvements of my own.

I liked the passage declaration syntax of *Twee2*, so that was effectively borrowed as-is. Additionally, I felt that
the linking syntax of *Twine* was also rather nice, so the method of linking to a passage directly (via
`[[Passage name]]`, showing the name of the passage being linked to) was copied as is, but only the `|` method of
indirectly linking to a passage was linked, not the `->` or `<-` methods, for a consistent code structure (so, entering
`[[Link text|Passage name]]` is the only way of linking to the named passage whilst showing 'link text').

The game metadata declaration syntax for .hecc resembles that of *Squiffy*, but it was changed to make it more
explict. In *Squiffy*, you can declare a game's title via `@title game title goes here`, at any point in the game's
content. But that means the system must be prepared to encounter that metadata at any point in the file, and there's
no explicit point where the type of metadata ends and the declaration begins. I adapted this for .hecc so it would
be declared in the form `!title: game title goes here` instead, and all such metadata declarations must be made
before the first passage is declared. The colon makes it clear where the boundary between the metadata type and the
actual metadata is, The `!` prefix was used instead mostly out of personal preference, but also because it looks more
immediately important than a `@`.

However, .hecc differs substantially in terms of the commenting syntax. It officially offers 3 varieties of comments,
one optional method of note-taking, and one intentionally undocumented, heavily-discouraged, form of commenting.

* The official commenting methods
    * Metadata multi-line comments.
        * You can put comments in the metadata area of the .hecc file, before the first passage is declared,
          but you should put a `//` on any comment lines. However, `OH-HECC` will treat any metadata comment lines as
          one extended multi-line comment, and will output them as such after editing the .hecc file.
          
    * Inline passage declaration comments
        * On a passage declaration line, after declaring the passage's name and (optional) metadata,
          a user can put a `//` at the end of that stuff, and everything after the `//` on that line is treated as an
          inline comment.
          
    * Trailing passage comments
        * After declaring some passage content, a user may enter a line containing only a `;;`.
          Everything on the following lines will be treated as a comment associated with that passage.
        * Initially, these were implicitly 'closed' by the next passage declaration, however, this was revised,
          so the comment needs to be explicitly closed by another `;;` line.
          
* The unoffical note-taking method
    * Adding a new passage to the game, unconnected to any other passages, which exists for the sole purpose
      of being used to take notes.
        * This will need to be taken into account when creating the parser(s) for .hecc, so any 'orphan' passages such
          as these won't actually be visible in the outputs (because they aren't intended to be reached anyway).
          
* The intentionally undocumented commenting method
    * Any lines before the first passage declaration which are not metadata definitions or explicitly marked as being
      a comment line **will** be ignored by HECC-UP and OH-HECC. If a user wanted to leave comments in their .hecc file
      like that, they can, and HECC-UP won't reject the .hecc code as being invalid. However, if they were to open their
      .hecc file in OH-HECC, these lines would also get ignored by the parser, meaning that, when they save their .hecc
      file, those comments will be lost.
        * Because I don't want authors to lose their comments due to that, I shall actively discourage authors from
          doing this.
          
I also decided that specification for the HECC language was to be called the '*HECC-SPECC*' (or, the
*HECC Super Precise Explanation For Creating Code*), partially because I wanted to be extra, and also
to remain on-brand and such with the rest of *HECC-IT*.

## 1.4: Designing the inner workings of each part of HECC-IT

### 1.4.1: The outputs

Again, I started with the outputs first, and worked my way back from there.
The design itself was jotted down rather crudely on a markdown file, which can be read [here](../Summer%20background%20preparation%20work/Planning/How%20the%20HECCER%20module%20will%20work.md).
This was promptly followed by the production of a prototype version of the output game, which can be looked at (and 
downloaded from) [here](../Summer%20background%20preparation%20work/Planning/testing%20etc/HECCER%20prototyping/index%20but%20everything's%20inline%20instead.html).
The overall logic within the produced games hasn't really changed much since then. It worked, so I decided to work on
the rest of the logic instead.

However, I will admit that I didn't get around to sorting out the planned saving/loading games functionality,
or implementing the initially planned support for variables.

Here is a diagram of the inner workings of the first version of the heccin' game:

##### Figure 3: A class diagram for the first version of the HECCIN' Game

![Class diagram of the first version of the heccin' game](./design%20images/heccin%20game%20v1.png)

Here's how it loads passages

##### Figure 4: A Sequence Diagram showing the process by which passages are displayed to the player

![Loading passages in the first version of the heccin' game](./design%20images/heccin%20game%20v1%20passage%20loading%20process.png)

### 1.4.2: HECC-UP

This seemed like the best part of the tool to start working from.

There was a rather simple process which HECC-UP would need to complete, which went as follows:

##### Figure 5: The HECC-UP parsing process as a flowchart

![HECC-UP workflow process](./design%20images/hecc%20up%20process.png)

Most of the thought process I had whilst working out how to implement HECC-UP was, once again,
crudely jotted down on a markdown file, which, again, can be read [here](../Summer%20background%20preparation%20work/Planning/how%20HECC-UP%20will%20work.md),
with the source code for a very crude prototype jotted down on another markdown file, [here](../Summer%20background%20preparation%20work/Planning/testing%20etc/regex%20stuff/regex%20planning.md).
The .java version of the early prototype is no longer present within the src/ folder
(due to it having some dependencies on things which I wanted to deprecate/remove from the source code),
however, a version of it can still be seen on some of the archive branches, [here](https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m/-/blob/all_the_summer_prep_work_archived/src/misc_testing_and_such/hecc_up_testing/HeccUpParserTest.java).

However, this was still merely a prototype, and needed significant improvements, not only to make it
fully functional, but also to be possible to maintain. After all, that version was basically
procedural, had no encapsulation, was very disorganized, and was generally not good. Therefore, I needed
to significantly refactor it before I could justify releasing it as a MVP.

Eventually, I chose to refactor it to have a structure like the structure in the following class diagram:

##### Figure 6: A class diagram for HECC-IT

![HECC-UP refactored class diagram](./design%20images/hecc%20up%20v1%20classes.png)

The HeccUpGUI class in the above diagram is simplified a bit (omitting the GUI-related parts), and
the HeccCeptions package has also been omitted (they're basically just thrown if parts of the .hecc
file are invalid).

This has since been re-refactored to have a seperate `HeccUpHandler` class, acting as an intermediary
between the LoggerInterface class (the GUI) and the FolderOutputter/PassageParser classes.
Additionally, the outputtable Passage and Metadata classes have been coupled somewhat to OH-HECC's
interfaces for Metadata/Passages. Yes, whilst coupling is, in general, bad (and I acknowledge that),
I felt as if it would be justifiable in this instance, because both OH-HECC and HECC-UP need to share
some common regexes and such for parsing existing .hecc files, and ensuring that the data they hold
would be valid in the context of a .hecc file. Therefore, by coupling the two, it would mean that both
could have access to the same regexes as each other, therefore, making them easier to maintain in the
long term, as they would have less code duplication. Additionally, any overhead with unnecessary
inherited are being circumvented via the use of interfaces, ensuring that only the necessary method
signatures are being made visible. This will be covered more in the section of the technical
documentation covering the development of HECC-UP.

On that note, I should probably cover OH-HECC.

### 1.4.3: OH-HECC

The first thing I considered was how OH-HECC would look like to a user. I decided to base the overall
appearance off the appearance of Twine, with a network of connected passages, clearly showing which
passages were connected to which other passages. A very crude picture of this initial idea can be
seen below:

##### Figure 7: A sketch of what OH-HECC would look like.

![oh-hecc basic idea](./design%20images/oh%20hecc%20basic%20idea.png)

The rectangles with the words 'bob' and 'dylan' in them are supposed to be passages (called 'bob' and
'dylan'). 'Bob' has 1 parent passage (the leftmost outline one), and links to two other passages (the
rightmost outline one, and 'dylan'). 'Dylan' doesn't link to any other passages. The topmost
rectangles are also passages, but are just outlines, to easily illustrate that the triangles (which
illustrate linked passages) point from/to the midpoints of the passages. The rectangles with words in
them at the bottom of the image were supposed to be buttons, and, when pressed, they would all do the
thing that the text on them says (such as saving the .hecc file, adding a new passage, etc.)
Additionally, I decided that, when a passage would be clicked, an 'editor window' would appear,
which the user could use to edit the passage. The network of passages would be un-interactable until the
editor window was closed again, so, any changes made in the window would need to be finalized or
discarded before any further changes to the game could be made. A similar thing would happen, but for
the metadata instead, if a user were to click on the 'edit metadata' button.
I didn't start to properly consider how OH-HECC would work until I was done with the Challenge Week
version of HECC-UP, as HECC-UP was intended to be the minimum-MVP, and, if there was no HECC-UP to
do something useful with the .hecc files, there wouldn't be any reason for an application to produce
the .hecc files to exist in the first place.

Eventually, I came up with a first idea for how OH-HECC could work, and it had a structure along the
lines of this:

##### Figure 8: A class diagram with the initial idea for OH-HECC's architecture

![oh_hecc initial idea](./design%20images/initial%20idea%20for%20OH-HECC.png)

The main thing of note here would be the Model-View-Controller (MVC) architecture for OH-HECC. This
was partially based on the architecture I used for my CE218 coursework (which I had since adapted
for use in some other games I made for some game jams over the summer break). I chose to base it on
that because I knew that the architecture worked, I had experience with using it, and it would mean
that I could focus more on implementing the specific parts I would need to implement for this
particular use case. However, the final product's architecture didn't entirely resemble this, due to
a few significant changes in methodology (and several things not working as well as first intended).

The first notable difference was in how the data for the game itself would be stored within OH-HECC.
In the above diagram, I hadn't considered the requirement for the windows that would be usable to edit
the various components of the game. Additionally, a lot of things that shouldn't have been directly
bound together, without an interface or something like that in the way, were bound directly together.

However, I shall be discussing this further in the document covering the development
of OH-HECC.