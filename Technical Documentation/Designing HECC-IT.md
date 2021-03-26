# Designing HECC-IT

## The background reading

Before I designed HECC-IT, I looked at a range of existing hypertext game authoring tools, attempting to answer
the following key questions for each of them:

* How does it work?
* How is it used?
* What options does it give the user?
* What features does it have?
* What features doesn't it have?
* How do the outputs work?

The full list of tools I looked at were:

* [ChoiceScript](https://www.choiceofgames.com/make-your-own-games/choicescript-intro/)
* eHyperTool (*or, at very least, the specification of it, a copy of which was provided by Dr Bartle*)
* [Inform](http://inform7.com/)
* [Inklewriter](https://www.inklewriter.com/) and [Ink](https://www.inklestudios.com/ink/)
* [Quest](https://textadventures.co.uk/quest/)
* [Ren'Py](https://www.renpy.org/)
* [Squiffy](https://textadventures.co.uk/squiffy)
* [Storyspace](http://www.eastgate.com/storyspace/)
* [TADS](https://www.tads.org/)
* [Twine](https://twinery.org/)
  * and [Twee2](https://dan-q.github.io/twee2/)
* [Undum](https://idmillington.github.io/undum/)

I also found out about the [Treaty of Babel](https://babel.ifarchive.org/babel_rev9.html) standard for interactive
fiction bibliography when I was performing this research. Since then, the treaty was [revised](https://babel.ifarchive.org/babel_rev10.html)
in January 2021, due to a couple of additional interactive fiction authoring tools becoming signatories to the treaty,
and a few minor changes to a few details of the treaty.

Full details about this research was within the 'literature review' part of the main project.
In case you wanted to see some of the notes taken for this, they can be seen [here](https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m/-/tree/8e547d379531eca87cc5075382d1fecba2605693/Summer%20background%20preparation%20work/Research).

However, this research was vital to shaping the overall vision I had for HECC-IT.

## The outcomes of the reading

One feature that stood out to me about (almost) all of these tools was the fact that they were all presented either in
the form of a GUI, or in the form of 'please write some raw code, there may or may not be an IDE'. None of the tools
gave the author the ability to freely decide whether or not they wanted to use a GUI or raw source code, generally
requiring the author to exclusively use the GUI or exclusively write raw code. And, of those which offered the author
the ability to change their mind, it always came with a caveat.

Here's a breakdown of how these tools are categorized, and, for those with the 'flexibility', what the caveat is:

* GUI only
    * eHyperTool
    * Quest
    * Storyspace
    
* Raw code/IDE only
    * ChoiceScript
    * Inform
    * Ren'Py
    * Squiffy
    * TADS
    * Undum

* The ones with a caveat
    * Inklewriter + Ink
        * Inklewriter is the GUI, but
            * You have to use it on the Inklewriter website (no download option)
            * You need an account on the website in order to save/export/download your work/open work from `.ink`
            * To open it with 'Ink', it needs to be converted into `.ink` code
                * And there's no guarantee that the converter will work successfully
        * Ink is the IDE for 'raw code', but
            * To open your `.ink` file via Inklewriter, you need to export it as a .json file first
                * And uploading that .json file on Inklewriter to continue work on it (or to just preview it) requires
                  an account on the Inklewriter website
                  
        * In short, it's a bit of a faff trying to convert between the two, and you can't use the GUI if you're not
          connected to the internet and/or don't want to make an account on the website.
          
    * Twine + Twee2
        * Twine is the GUI
            * This is effectively self-contained.
    
        * Twee2 is the raw code, but
            * You need to use the command line to convert from Twee2 code to Twine format (allowing it to be played)
                * This is offputting for casual users who don't want to use the command line.
            * You can decompile a Twine .html file into Twee2 code
                * This, again, requires use of the command line
                * This functionality is not available to users who are on Windows.
    
        * You need to faff around with the command line to convert between the two, and is only a one-way conversion
          on Windows.
          
From this, I have been able to identify a pretty clear gap in the market; There is no hypertext game authoring system
which allows a writer the flexibility to freely choose between raw code and a GUI. Suppose a writer wants to write a
game using a system that allows them to have a graphical overview of the game they're writing; They must commit to
using a GUI-based tool, and, even if they want to make a rather small change somewhere, they need to go through the
effort of opening the GUI tool and then opening the work-in-progress game in the tool and editing it via the tool.
Suppose a writer wants to be able to write the raw code without needing to deal with a GUI; if that writer wants to
get an overview of how everything in their game is linked together (in case they want to check that everything is as
it should be), they can't.

HECC-IT is intended to fill this niche.

## The vision for HECC-IT

* Work-in-progress games are to be stored in a high-level intermediate scripting language, which can be edited
  manually by a user with ease.
  
* There should be a parsing utility which a user may use to convert games from this intermediate format into
  playable hypertext games.
  
* There should also be a GUI which allows users to edit games stored in that intermediate format,
  presenting it as a network of connected passages.
  
* The games produced should be 100% clientside, playable via any modern web browser, requiring no unnecessary faff
  from the author, or the player, in order to actually share/play the games produced with this tool.
  
* The tool should work on any PC, regardless of operating system.

GUI editor (if the user wants to use it) <-> intermediate code -> playable game

### The name

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
    
### The components

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

![HECC-IT component interactions verbose](./design%20images/HECC-IT%20component%20interactions%20overview%20verbose.png)

A simpler version of that diagram was produced later on:

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
    

## 'But how will the .hecc format work?'

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
          

## Designing the inner workings of each part of HECC-IT

Again, I started with the outputs first, and worked my way back from there.
The design itself was jotted down rather crudely on a markdown file, which can be read [here](../Summer%20background%20preparation%20work/Planning/How%20the%20HECCER%20module%20will%20work.md).
This was promptly followed by the production of a prototype version of the output game, which can be looked at (and 
downloaded from) [here](../Summer%20background%20preparation%20work/Planning/testing%20etc/HECCER%20prototyping/index%20but%20everything's%20inline%20instead.html).
The overall logic within the produced games hasn't really changed much since then. It worked, so I decided to work on
the rest of the logic instead.

However, I will admit that I didn't get around to sorting out the planned saving/loading games functionality,
or implementing the initially planned support for variables.

TODO: DIAGRAM