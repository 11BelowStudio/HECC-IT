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

## The outcomes of the reading/the vision for HECC-IT

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
            * And is effectively self-contained.
    
        * Twee2 is the raw code, but
            * You need to use the command line to convert from Twee2 code to Twine format (allowing it to be played)
                * This is offputting for casual users who don't want to use the command line.
            * You can decompile a Twine .html file into Twee2 code
                * This, again, requires use of the command line
                * This functionality is not available to users who are on Windows.
    
        * You need to faff around with the command line to convert between the two, and is only a one-way conversion
          on Windows.