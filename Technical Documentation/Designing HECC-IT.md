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
  from the author or the player in order to actually share/play the games produced with this tool.
  
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
define what the interactions between the components would be. Eventually, I produced this overcomplicated diagram,
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

Firstly, the links: There are severa


