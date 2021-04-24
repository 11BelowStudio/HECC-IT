# (3.2).2. Development of the Challenge Week MVP

The minimum viable product for HECC-IT technically was the challenge week deliverable, not the term 1
deliverable. Yes, I know, the structure for the project was that the minimum viable product was supposed
to be the term 1 deliverable, however, due to the design of the tool, HECC-UP, by itself, could be a
'minimum viable product', with emphasis on the 'minimum'.

Overall, this deliverable consisted of the following components:

* HECC-UP.jar
    * With the pre-baked HECCIN' Game components within it
* 'A Conversation'
    * A simple HECCIN' Game which is also a poorly-disguised advert for HECC-IT.
    
# 2.1: The HECCIN' Game

Development of this mostly happened around the latter part of August 2020. It remained mostly unchanged
from the very crude prototype versions produced, besides the removal of the coloured-in divs (as those
were mostly there to make it possible for me to visualize the layout), changing the 'back button' so
the entire top div with the 'back' text was the back button, removal of the 'this is a prototype'
text at the bottom of the page, and the addition of an error message that is held in the passage content
div by default, so, if there's a problem with hecced/heccer that causes the first passage to not load
in the first place, the player/author can see that there is a problem, and there will be some
troubleshooting advice for them as well. This means that a user won't be completely at a loss if
nothing loads for them for any reason.

The HECCIN' Game, at this point in time, required the passage contents in the Passage objects to already
have their contents strings basically hold valid html code, so I knew I would need to perform this
conversion in HECC-UP. I didn't want to do this conversion in the JavaScript, because I didn't have much
confidence in my abilities with JavaScript at this particular point in time, and by performing the
conversion in HECC-UP, it would be easier to notice it if something went awry (as the hecced.js file
would hold the results of the conversion as-is). The HECCIN' Game also didn't have any checks to ensure
that the passage objects in the game themselves were actually valid; this was because this validity was
to be imposed by HECC-UP instead, therefore, the HECCIN' Game could assume that it wouldn't be receiving
any invalid passages.

I did mention that I was planning on incorporating some level of compliance with the 'Treaty of Babel'.
One of the requirements for this would be a routine which could produce an `iFiction` file for a given
work of hypertext fiction. Officially, this would need to be a routine written in C, for use in the
`babel` utility explained in the treaty. I didn't get around to producing aforementioned C routine,
however, for the time being, I chose to make HECC-UP output an `iFiction` file for the given game,
mostly to ensure that some of the bibliographic data was recorded somewhere. I suppose that, if I were
to commit further to ensuring compliance with the Treaty of Babel, I might need to rework the part of
HECC-UP that outputs this to include it within a commented-out part of the index.html instead, which
the aforementioned C routine I would need to write would just extract it verbatim from there.

Either way, the HECCIN' Game structure remained basically unaltered.

`index.html` still displayed the game to the user, `hecced.js` still defined the global start passage
identifier and the `getHECCED` method, and `heccer.js` still defined the 'engine' for the game.

# 2.2: HECC-UP

HECC-UP, as discussed earlier on, started off as a rather crude .md file, going to a similarly crude,
basically procedural Java file, before going further to be a much improved GUI application, which, by
itself, could qualify as a hypertext game authoring tool.

The prototype basically represented the passages in a map of strings; the key was the passage name,
and the value was the passage content. Initially, the passage content would just be everything between
the passage declarations, and, once the unparsed content of all the passages would be found, it would
then actually parse the content of the passages, turning them into strings of valid html. When
producing the hecced data, these strings would be escaped (again), so they would be valid single-line
hardcoded source code strings (basically going from `content` to `"content"`), which could then be
output into the hecced.js file (along with the start passage identifier). The game's metadata was
initially just stored in variables within this, with all the other stuff.

The first things to be refactored were the passages and the metadata, giving them dedicated `Metadata`
and `Passage` objects. The set of passage name strings remained as they were, because that was still
rather important for validating the passage links present within the game. The parser would still find
the content of each passage and construct the raw content strings for each passage line by line, but
the passage objects themselves would be responsible for turning the raw content into parsed content.
This was also the case for the `Metadata` object.

With that done, the next thing that needed to be done was to get HECC-UP to read a `.hecc` file from
a given location, instead of just reading a single hardcoded-in .hecc file. This turned out to be
relatively simple to do, which was a relief. The last thing from the prototype that needed changing
was how some of the important info from the HECC-UP would be output to the user. In the prototype,
it was all automatic and done in the console, so any information about problems with the .hecc file
(or lack of problems) would be printed out to the console; the obvious problem with that in a GUI
context would be the fact that the user would not be looking at the console. There were two options
for how I could have solved this: I could have shown them via `JOptionPane` pop-up dialogs, or I could
have come up with a more elegant solution. The `JOptionPane` idea would have been a much easier,
quick-and-dirty fix, but I opted not to use it, because it would have gotten rather annoying for the
user, having to constantly dismiss these dialogs. So, I came up with a more elegant solution instead.

I introduced the `LoggerInterface` interface, which the parser would use to 'log' info, instead of
directly printing it to console. The single method in that interface, `void logInfo(String infoToLog)`,
was a default method, which would just simply print to console (as a backup of sorts). However, the
plan was to add a `JTextArea` within a `JScrollPane` to the GUI application, and make the GUI
implement that interface. When info needed to be logged, it would get logged within that `JTextArea`,
allowing the user to see it, and making it scrollable would allow them to see all the details, without
needing to make the GUI window too big. The benefits this had over the `JOptionPane` route were twofold;
Users would not be interrupted by dialogs they would need to dismiss during the parsing process, and,
if a user wanted to, they could select and copy the text in the `JTextArea`, so they could paste it in
a TODO or something if they wanted to (which is not possible with `JOptionPane` text).

The HECC-UP GUI's layout was entirely hand-coded, mostly because I didn't want to wrestle with a
GUI builder at this point in time, and I had a decent idea for what I wanted HECC-UP to look like.
All it needed to do was accept a .hecc file, accept a folder to output a game to, attempt to output
the aforementioned game, and complain if appropriate. A recreation of the general idea I had at the
time can be seen below:

##### Figure 9: A sketch showing what the GUI of HECC-UP would look like

![hecc-up basic idea](./challenge%20week%20development/hecc%20up%20basic%20idea.png)

I was considering hiding the button responsible for building the game until the .hecc file and the
output folder had been chosen, however, I decided against that, because to a user, they might then
think that all they need to do would be select the two files that needed to be chosen, and then
feel confused if the program didn't automatically build the game, or could be a bit hesitant in case
they picked the wrong file and accidentally overwrote something important. So, I decided that this
button would be constantly visible, but, if the button was clicked too early, it would complain
via a `JOptionPane` pop-up, telling the users that they needed to select the .hecc file and output
folder first. To make this even more obvious, I opted to include some 'step' labels above each of
the buttons, to point out that the .hecc file and the output folder should be chosen before
attempting to parse the game.

Eventually, the GUI was built, and looked like this:

##### Figure 10: A screenshot of the challenge week iteration of HECC-UP

![Hecc-up GUI](./challenge%20week%20development/HECC-UP%20screenshot.PNG)

It makes what the user is supposed to do, and what each part of the thing does, relatively obvious.
Yes, I will admit that the `HECC-IT!` button is a bit ambiguous, however, there isn't much else
that it could really be, given the purpose of this program (creating a HECCIN' Game from a .hecc file).
Upon attempting to simply `HECC-IT!` when the files haven't been selected, the user will be greeted
by this warning:

##### Figure 11: A screenshot of the warning message which appears if the files are not selected

![Hecc-up pls to select](./challenge%20week%20development/HECC-UP%20screenshot%20pls%20do%20the%20other%20stuff%20first.PNG)

This makes it very clear to the user that they need to do the other stuff first. It's set up such that
this warning won't appear only if the `.hecc` file *and* the output folder have been selected. If at
least one of those steps haven't been done, this warning will still appear.

Now, what happens when a game is parsed?

If nothing supernatural happens when parsing a game, the log will report nothing going wrong, and
that it was parsed successfully, like so:

##### Figure 12: A screenshot of HECC-UP after successfully exporting a game

![Hecc-up success](./challenge%20week%20development/HECC-UP%20screenshot%20success.PNG)

The game will now be playable at the output folder.

If the `.hecc` file has some undeclared metadata (but is otherwise completely valid), it will still
compile successfully, but the user will be warned about the problems encountered. The screenshot below
shows the metadata warnings it can display, but I have manually resized the HECC-UP window to show
all the potential metadata warnings on it in a single screenshot:

##### Figure 13: A screenshot of HECC-UP notifying a user about metadata-related problems with their game

![Hecc-up no metadata](./challenge%20week%20development/HECC-UP%20screenshot%20success%20but%20pls%20metadata.PNG)

Once again, the game is playable at the output folder, and instructions for how to fix the metadata
problems have been provided (which a user may copy and paste directly into their .hecc file). However,
HECC-UP will *not* fix those problems automatically for a user. HECC-UP is only supposed to read a
.hecc file; if there are problems with the .hecc file, it should only point them out. Yes, the
game it will output will have default values for the author (`Anonymous`),
title (`A Hypertext Fiction`), and IFID (a random UUID), as per the Treaty of Babel specification,
but the user must actively choose to define these themselves if they don't want them to be set to
the defaults.

Finally, what happens if a user gives an invalid .hecc file as an input? Simple. HECC-UP will complain,
like so:

##### Figure 14: A screenshot of HECC-UP after detecting an error in the .hecc file and refusing to export it

![Hecc-up complaining](./challenge%20week%20development/HECC-UP%20error%20detected.PNG)

The game has not been constructed into a playable game. In this particular situation, the user
gave a completely empty `.hecc` file to HECC-UP, so it responded by complaining about the lack of any
passages.

Now, how does it stop parsing and return these specific error messages to the user? I admit that I did
overlook this in the discussion of the design of HECC-UP earlier on, but I suppose that now is an
appropriate time to start discussing the `heccCeptions` package.

## 2.2.1: The `heccCeptions` package

As the terrible pun in the name of this package implies, these are exceptions, but for hecc. ~~I see
an opportunity to make a pun, I take the opportunity to make the pun.~~ These are all thrown by the
parsing process if there's something faulty with the .hecc file. Most of these exceptions are
intentionally not caught within the parser, instead being caught by the code that starts the parsing
process, allowing these exceptions to interrupt the parsing process, and have their error messages
logged by the logger.

These exceptions are:

* `abstract class ParserException extends Exception`
  * This was the base class for all the `heccCeptions`. This has since been renamed to `HeccCeption`,
    mostly to remain on brand and such. This was mostly created as a catch-all for all the potential
    *hecc*ceptions that could be thrown (so I could just have a `catch(ParserException e){...}`, or
    I guess a `catch(HeccCeption e){...}` nowadays, at the end of the `try{...}` containing the parsing
    code).
  * Yes, all of the following heccCeptions extend this class.
  
* `DuplicatePassageNameException`
  * You are not allowed to have two passages with the same name in a `.hecc` file.
  * When this is thrown, HECC-UP will complain about having two declared passages with the same name,
    say what the duplicated name is (so, if you have two `::dave` declarations, it'll complain about
    multiple `dave`s), and will remark about having no idea which passage (or `dave`, in this example)
    is which.
  * This will get thrown when the parser is making the initial pass through the game content, finding 
    the passage declarations, detecting a duplicate if the operation to put a particular passage name
    into the set of passage names fails (due to that particular string already being in the set).
    
* `EmptyPassageException`
  * You are not allowed to have an empty passage in a `.hecc` file.
  * When this is thrown, HECC-UP will complain about the passage with the specified name having no
    content (so, if `::bob` has no content, it will complain about `bob` having nothing in it),
    remarking that it's not possible for a passage to be read if there's nothing in the passage.
  * This gets thrown when finding the raw content for each passage, if no content is found between
    a passage's declaration and the start of the next one/end of the file.
    
* `InvalidPassageNameException`
  * This was initially planned to be thrown if a passage was declared with an invalid name.
    However, within HECC-UP, this is rather redundant, because, if a passage doesn't have a valid name,
    the regex responsible for detecting the passage declarations in the first place will simply ignore
    that this declaration exists, and the content of that passage is grouped with the prior valid
    declared passage.
  * However, this was utilized within OH-HECC.
    
* `MissingStartingPassageDeclaration`
  * A user may define a start passage in the metadata like `!StartPassageName: passage name`, and the
    HECCIN' Game will start from the passage with that name. If this is not explicitly declared, the
    HECCIN' Game will start from the passage called `Start`. This, by itself, doesn't throw an exception.
  * However, if the defined start passage (or `Start` if one isn't defined) doesn't exist, this
    exception will be thrown, and HECC-UP will complain about being asked to start from a passage with
    a certain name (giving the name present in the definition), and having no idea how to start from
    a passage which doesn't exist.
  * This gets thrown after the initial search for passage declarations, if the string with the name
    of the desired start passage is not present within the set of all passage names.
    
* `NoPassagesException`
  * If no passages are declared, that means there's no content for the game, so HECC-UP will complain,
    because it's going to be nigh-impossble for a player to play a game with no content.
  * This gets thrown after the initial search for passage declarations, if no passage declarations
    could be found.
    
* `UndefinedPassageException`
  * If a passage's content has a link to a passage which doesn't exist (has a name which isn't in the
    set of known declared passages), this exception is thrown.
  * This will only get thrown in the routine responsible for setting up the data for hecced.js, when
    validating the links of every single passage.
  * I did realize in hindsight that the error message for this heccCeption isn't actually logged and
    made visible to the user in this version of HECC-UP. However, the passage parsing process still
    fails and doesn't result in the game being output if this is thrown, as intended.
    

## 2.2.2: The other parts of this stage in HECC-UP's development

Unfortunately, I wasn't able to get everything I initially planned to get done within this MVP stage
done. The most notable omission was some support for markdown formatting. At this stage in development,
HECC-UP still performed all the html formatting itself (turning the `[[passage name]]` links into
actual clickable links that tell the heccer to go to the given passage, manually inserting `<p>`s and
`<br/>`s, manually escaping other stuff, etc), so I wanted to include a proper markdown parser in
HECC-UP. As you might have guessed from the current state of the product, this didn't go as well as
first intended. I severely underestimated the amount of work that creating a markdown-to-html parser
would involve. After about 9 hours of trying to work on it but not really getting anywhere, and
considering that, in the great scheme of things, markdown is more of a 'nice-to-have' than a
'must-have', I chose to omit that from this stage of HECC-IT. I also enquired about whether or not,
under the guidelines and such for CE301, there would be any issues if I were to use a premade
implementation of a markdown parser (which there apparently wouldn't be, assuming that it was to
be used within the terms of the original license, and properly attributed and such), so I decided that
I would look into that further later on.

Additionally, the HECC-UP parser does still parse some content which isn't entirely necessary within
the context of HECC-UP, like some passage 'position' and 'tag' metadata. These were intended to be
used more in the OH-HECC part of the program (which they are), however, I wanted to get HECC-UP to
be able to parse them as well, mostly so I would have that done and out of the way for OH-HECC.
The idea was that I could then reuse the same parser class and other data classes for OH-HECC,
but just exposing different methods to OH-HECC and HECC-UP via different interfaces. Ultimately, this
didn't happen as first intended (with the two components essentially using their own classes instead),
but having the logic all working as expected in advance anyway did help.

Finally, I guess I should address the elephant in the room; the testing. At this point in time, I
wasn't sure enough about the expected outputs of HECC-UP to be able to define in code form what
exactly I expected to get out of it. So, I didn't get around to writing formal unit tests for HECC-UP.
However, during the process of writing HECC-UP, I did perform a lot of informal white-box testing,
mentally going through the overarching control flow of the program when writing each part of it.
When trying to work out the regexes to use for HECC-UP, I used regex101.com, to quickly try out
different regexes, easily see what they matched, and it was simple to make changes and test the changes
to the regexes if necessary. However, I accept that I probably should have documented this process a
bit better somewhere on the repo. Finally, for testing the finished product of HECC-UP, I chose to test
it by writing some .hecc files, expecting a certain type of result from running them through HECC-UP,
and informally monitored what happened. I tried out a valid .hecc file, expecting no errors, but
expecting a valid game to appear instead, and that happened. I then tried another .hecc file, with no
metadata declarations present in it, but otherwise 100% valid. That one also compiled, and logged the
appropriate metadata definition instructions to the console. Finally, I also tried another .hecc file
without any passages declared in it; as expected, HECC-UP complained about that file not having any
passages, and didn't attempt to output that as a game. In hindsight, I definitely should have
unit-tested HECC-UP instead (which I eventually did), or, at very least, I should have tested some
.hecc files which would have thrown the other types of HeccCeption as well.


Now with that all out of the way, I should probably cover the then-current state of the hecc language.

# 2.3: The HECC Language - or the *HECC-SPECC* v0.1

What's the use of a language without a specification? Oh yeah, there is none. This is why I made the
*HECC-SPECC* (the *HECC Super Precise Explanation For Creating Code*). It was (and still is) in the
form of a somewhat messy markdown document with a lot of indented bullet points. That version of the 
document may be read in full, [here](https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m/-/blob/archived-Challenge_Week/Documentation%20for%20HECC-IT/HECC-SPECC.md).

However, here's a quick overview of the important bits:

* Passages
  * Definitions
    * Must be defined by a line in the form `::passage name`
      * the passage name may have leading/trailing whitespace.
        * which will be omitted by HECC-UP
      * passage names may contain letters, numbers, spaces, underscores, and hyphens
        * but they must start with a letter
        * if a passage name contains anything else = invalid passage name
      * Some metadata after the passage name will be allowed, but is currently unused, so don't bother.
      * Passages cannot share the same name, and cannot have no name.
  * Contents
    * Anything on the lines after a passage declaration,
      up to the next passage declaration/end of the file,
      is treated as the content of the passage it's declared under.
    * No restrictions on content
        * But a passage cannot be empty
        * Attempting to put a passage declaration within the contents of another passage will lead to
          that declaration being treated as a declaration (obviously).
    * Formatting of contents
      * `<`, `>`, `'`, `"`, and `&` characters in the passage contents will be escaped.
      * Leading/trailing whitespace in the passage contents will be trimmed.
      * Newlines
        * A single newline will be replaced with a line break (`<br/>`)
        * Two consecutive newlines will be replaced by a paragraph break (`</p><p>`)
  * Passage links (within the content)
    * The passage being linked to must exist.
      * Leading/trailing whitespace within the passage name component of the link will be omitted
    * Can be direct or indirect
      * Direct links (where the name of the passage being linked to is clickable)
        * `[[passage name]]`
          * This is a link to the passage called `passage name`, rendered as text saying `passage name`
      * Indirect links (where some other text that is not the name of the passage is clickable)
        * `[[link text|passage name]]`
          * This is a link to the passage called `passage name`.
          * Rendered as text saying `link text`.
* Metadata
  * Everything before the first passage declaration which isn't a defined metadata line
    officially doesn't exist.
  * Start passage
    * Can be defined with `!StartPassageName: passage name`
      * The passage with the given name (in this case, `passage name`) will be used as the start passage.
      * Must be a passage which exists.
    * If not defined, it will start from a passage called `Start` instead (which must exist.)
  * Title
    * Can be defined with `!StoryTitle: title goes here`
      * Must start and end with 1 non-whitespace character (but in this version, had a minimum length of 2)
    * If not defined, title defaults to `An Interactive Fiction`
  * Author
    * Can be defined with `!Author: author goes here`
      * Must start and end with letters (A-Z, any case)
        * Unfortunately, I couldn't find a simple regex that could include non-ascii letters whilst rejecting non-letters.
      * May contain any number of letters, spaces, full stops, or commas.
  * IFID (Interactive Fiction IDentifier)
    * Strictly speaking, under the Treaty of Babel, this should be the MD5 hash of the story file
      instead, as HECC-IT is still outside of the agreement.
      * When I was doing the research for this (and even during this stage in development of this tool),
        Twine still wasn't a signatory to the treaty, but still assigned IFIDs to games produced with it
        (such as [this one](https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m/-/blob/all_the_summer_prep_work_archived/Summer%20background%20preparation%20work/Research/tool%20research/games%20I%20made%20to%20test%20the%20tools/EGM%20(Twine%20Edition).html)
        that I made in August 2020), so I figured that there wouldn't be any harm in doing it anyway.
    * Assuming that it potentially could be under the agreement, this would need to be a
      randomly-generated UUID, but with the letters capitalized instead.
        * Defined like `!ifid: 20BAA71D-1307-4EAE-B8FC-28920044C4EE`
  
Now, with that out of the way, I guess I should cover the game I produced as part of this minimum
viable product.

# 2.4: The HECCIN' Game - `A Conversation`

This was basically a last minute creation, and was more of a demonstration/shameless plug for the
HECC-IT authoring system instead of anything meaningful. The output from it, as-it-was when HECC-IT
was released, has been archived [here](https://cseegit.essex.ac.uk/ce301_2020/ce301_lowe_richard_m/-/tree/98894174dd66ec34fc9af9ce04501e2cb7af20d7/outputs/hecc_up_testing_v2/A%20Conversation).
However, if you don't want to download the game in order to run it, a version of that game
(same contents, but parsed by a more recent version of HECC-UP) is playable on my itch.io page,
as part of [The HECC-IT Demo](https://11belowstudio.itch.io/the-hecc-it-demo).

It's presented as a conversation between the player, and the voice that's inside of their head which
speaks to them when they read things. There are four endings to this game. The first ending is the
one where the player gets into an argument with that voice for saying stupid stuff (despite the player
being the one reading the stupid stuff), promptly followed by the voice refusing to say anything else
to the player (ending the game). The second ending is one where the player suggests reading something
else, followed by the voice agreeing with the player, ending the game. The final two endings both
involve the shameless plug for HECC-IT; one of the endings has the player suggest to the voice that
they should write something better than this terrible game, prompting the voice to agree,
ending the game (as the player should ideally start making a better game at this point). The final
ending has the player vocalize their annoyance at the shameless plug, followed by the voice telling
the player to blame the writer (me) instead, and that there are no more words here to say,
ending the game.

I chose to present it like this, instead of having the player having a 'conversation' with a clearly
defined other character, because I wanted to actively engage the player with this work, and I also
wanted to make the player acknowledge that they were, in fact, just reading words on a screen, in a
more creative way than the honestly overused 'character is self-aware trope'. I will admit that this
game is also terrible, however, again, that was intentional. I basically wanted to set a low bar for
quality, so, if someone turns up and sees HECC-IT, they'll hopefully not feel like they'll be pressured
to create some sort of nobel prize-winning hypertextual masterpiece, because no matter what they
produce, it'll probably be better than this. Additionally, because one of the endings is a call to
action to create a game using HECC-IT, and aforementioned ending involves the player actively choosing
this option to 'put the idiot who wrote this to shame', it's an extra not-so-subtle suggestion to
get the player to attempt to use HECC-IT, and potentially make the player think that it was their own
idea to use HECC-IT. Chances are that this subliminal messaging won't actually work on the average
player (who will probably go for one of the other 4 endings instead), however, if it makes even one
person decide to attempt using HECC-IT, I'll count that as a success.