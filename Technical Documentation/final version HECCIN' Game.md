# The Improvements to the HECCIN' Game

## 1.1: Improving the HECCIN' Game

The HECCIN' Game had remained mostly untouched since the prototype version, which had been produced
over the summer, so, I decided to put some more work into it. Before this term started, I was planning
to implement the variable system into the HECCIN' Game, but I wasn't sure where to start with it, so,
I had initially picked CE305 (Languages and Compilers) as one of my options for this term. My thought
process was that I'd probably be able to apply the content from that module to produce a proper
expression parser within the HECCIN' Game. Unfortunately, this plan went awry when, on the week before
term 2 started, I looked at my timetable and realized that this optional module had a clash with a
compulsory module. So, I had to drop this optional module and pick a new one, thereby having to abandon
this plan. This was a setback, but not a complete failure.

As the term started, I decided to divert my efforts towards the markdown support I wanted to
incorporate into the game. I had already asked my supervisor, Dr Bartle, if there would be any issues
if I were to find a pre-existing markdown implementation for use in this product, and he confirmed that
I could use an existing implementation, as long as I did give credit for it and such. The question now
was one of finding an implementation, and where in the product I wanted to use it. I could have
found a Java-based markdown-to-html parser, in which case, the markdown-to-html conversion would need
to be performed within the HECC-UP export process, meaning there would be no overhead from it when
running the game, but it would mean that it might get a bit difficult to perform any guard
condition-related processing on the output (as I would basically need to prevent that stuff from
getting converted into html/escaped, and then find it again within the html). Whilst the MVP iterations
of HECC-UP did already perform some html conversion itself, I eventually opted against that approach,
due to that lack of flexibility it would offer me in terms of how I could conditionally format it at
runtime (due to the aforementioned guard conditions). So, I opted to find a JavaScript-based markdown
parser, giving the `Passage` objects in the HECCIN' Game their content in the form of a raw markdown
string, so, when 'loading' the current passage, the parser would go through it at that point, applying
any html formatting which needed to be applied right then and there, showing the HTML just-in-time.
Yes, it does mean that there is a potentially lengthy operation carried out every single time that a
player clicks a link in the passage content, however, it also means that any conditional formatting
could be carried out 'just-in-time' at the same time, therefore, there would be some overhead at
runtime anyway. The only changes that HECC-UP would need would be the removal of the current insertion
of HTML elements, and making it output a copy of this markdown converter as well as the existing
premade outputs. Finally, there was also the potential of (ab)using this to help out with the desired
conditional formatting.

With this in mind, I started to look for a JavaScript-based markdown-to-html converter. I specifically
wanted one which had a permissive enough license for me to incorporate it into HECC-UP, had no
dependencies on any external resources, and ideally had some method of allowing user-defined custom
formatting rules (mostly so I could incorporate the passage links into it). I didn't properly document
the various implementations I looked at, and I've genuinely forgotten what all of them were as well.
However, I eventually found [Showdown.js](https://github.com/showdownjs/showdown). Strictly speaking,
this is a bidirectional markdown<->html converter, however, for my purposes, it was sufficient.
It is distributed under the MIT license, so, as long as I was to include a copy of that in the copies
of showdown which HECC-UP outputs, I am covered on that front. It didn't appear to have any
dependencies on external resources (besides one particular emoji of the github logo, which, being an
image hyperlink, within one part of the system which can be disabled anyway, is not critical to the
overall operation of the system), meaning it is usable 100% clientside. Finally, it also had an
'extensions' system which allows for user-defined formatting rules, meeting that criteria as well.
So, I decided to try using it.

The first thing I did was create the [src_js](../src-js) folder, to use as a place where I could
safely make changes to the HECCIN' Game, without interfering with the 'production' versions in
the `src/assets/textAssets` folder. I also chose to perform some unit tests on the current version
of the heccer, in regards to whether or not it was able to keep track of what passages/what passage
tags had/hadn't been encountered yet. I would keep track of this via a set of passage names and tags
in the `Heccer` class, being refreshed via a method of the `GameStateStack` being called upon
a passage being loaded. This method would create two empty sets (for names and tags), iterate through
the list of `GameState` objects in the `GameStateStack` (ignoring the 'top'/currently loading state,
so, if an author wanted to check if the 'current' passage etc had been visited already, they could)
in order to add the visited passage names to the name set, and then it would iterate through that
name set, to add the tags for those visited passages to the visited tag set. These sets would then be
returned, and would replace the prior visited sets. I chose to use a separate iteration for the tags
because, this way, I wouldn't find myself attempting to add all the tags for one passage multiple
times if that passage had been visited multiple times. There was a slight problem with working out
what unit testing framework I could actually use for JavaScript, however, I eventually found out about
[QUnit](https://qunitjs.com/) [1], so, I used that to verify that the current iteration of `heccer.js`
actually worked as intended. These preliminary unit tests can be seen in [src_js/tests for seen passages and tags.html](../src-js/tests%20for%20seen%20passages%20and%20tags.html),
all of which passed as expected (see below).

##### Figure 34: The first batch of unit tests for the HECCIN' Game all passing

![first tests](./final/heccin%20game/seen%20passages+tags%20unit%20tests%20working.PNG)

I then created a subfolder, [src_js/showdown](../src-js/showdown) [2], which I then copied the existing
index.html/heccer.js files (and the version of hecced.js from 'A Conversation') to, as well as a copy
of showdown.js (along with a copy of the legal info for it). The first thing I did was attempt to
rewrite index.html to 'include' the code in `showdown.min.js`, before rewriting heccer.js so, when the
current passage is 'loaded', it'll be processed by showdown.js before being displayed on index.html.
I also rewrote the hecced.js file to include some markdown formatted elements. I then opened the
index.html file in my browser, to see if the markdown formatting was working as expected, and, to my
surprise, it was. I did need to change a few settings for the showdown converter within index.html
(such as enabling the 'simple line breaks' rule), but, it worked as anticipated. These checks
can be seen in the '[index.html](../src-js/showdown/index.html)' file in this directory.

The next step was to move on to the 'extensions' feature of showdown. I was pleasantly surprised by the
fact that the still pre-parsed passage link html elements were left functional by showdown, however, I
realized that it would make sense to move the link parsing into the runtime parsing anyway, because, if
I were to make any changes to the way that the passage links themselves were to be represented in the
html, I would only need to make changes in the JavaScript, instead of needing to make changes to both
the Java and the JavaScript code. This would also be a good test-run of the feature. So, I looked at
the documentation for the feature, and started to create a '[heccstension](../src-js/showdown/heccstension%20deprecated.js)'
extension. I rewrote the existing code I wrote in HECC-UP for converting the links from raw .hecc form
to valid HTML, updated index.html to load that 'heccstension' into the showdown converter being used
within the HECCIN' Game, updated the hecced.js code appropriately, and opened index.html to see if
everything worked as intended. It did. Eventually, I decided to move the 'heccstension' declaration
into the 'heccer.js' file, so I wouldn't have to deal with yet another file.

I could have stopped here and moved on to other things, however, it was at this point when I realized
that these extensions could also be abused to allow the implementation of conditional statements.
The first problem was working out what sort of syntax I wanted to use for it. Within the original
heccer tests, I considered using a syntax similar to that used within the Harlowe story format for
Twine, with keywords in brackets followed by content in square brackets, in the form
`{($seenTag('A'):)[yes](else:)[no]}`, displaying `yes` if a passage with tag `A` had been seen,
otherwise displaying `no`. However, I wasn't entirely sure how to go about writing something that
can parse that, especially considering that square brackets are already considered 'special characters'
within .hecc, so, if an author were to try putting some text that said `[idk]` within one of these
branches, there's a risk of the branch being treated as a link to a passage called `idk`, which was
not supposed to happen. Eventually, I had a better idea. I would represent it in the form
`{if:tAny("A")}{yes}{else:no}`.

The first curly braces would have the condition being evaluated, in the form `{if:condition}`.
This would need to immediately be followed by another pair of curly brackets, in the form
`{show this if true}`. If the 'if' condition evaluated to true, the content between these curly
braces would be shown, but it wouldn't if it evaluated to false. This content could optionally be
immediately followed by an `{else:show this if false}`. If this 'else' branch is present, everything
between the `{else:` and the closing `}` would be shown if the 'if' evaluated to false. This would
be rather simple to parse, as I would just need to look for the `{if:`, then the `}{` (indicating
the end of the 'if' and start of 'true content'), then the `}` (to indicate the end of the 'true'
content), and then see if there was an `{else:` immediately afterwards (terminating at the next `}`).
I did realize that there would be one slight problem: what if a user wanted to put a closing `}`
within a branch for some reason, either as part of the text or as part of some nested if/else
conditions? Luckily, there was a simple workaround: allowing a user to escape these with a `/}`,
meaning these `}`s would be ignored when the parser is trying to obtain the parts of the if condition.
Then, when the parser is attempting to re-insert the 'correct' string into the passage content,
I could then unescape the `/}`s, meaning that they would, once again, be a `}`. The slight drawback
to this is that any nested if conditions will need to have all of the `}`s escaped an extra time
for every layer of nesting (needing `/}` at 1 nested layer, `//}` at 2 layers, and so on), but it
could be seen as giving the author some peace of mind knowing that it's explicitly clear what level
of nesting each statement is at. This 'extension' method would still need to be called again with the
'returned' contents of this if statement before properly being able to reinsert it into the formatted
content, to allow any nested if conditions to be processed, and I would also need to explicitly call
this extension method on everything after this current if/else, in order for that to be parsed as well.

I planned to implement 4 checking operations that could be performed in the 'if' statement.
(along with 3 boolean operations). Visited tags could be checked with `tAny("tags","go","here")` and
`tAll("tags","go","here")`, both of which being functions that could take a variable number of string
arguments, with the former returning true if any of the tag string arguments had been encountered
so far, and the latter returning true only if all the tag string arguments had been encountered
so far, both returning false otherwise (with the two being interchangeable if only one argument is
given). Visited passages could be inspected in the same way using the
`pAny("passage","names","go","here")` and `pAll("passage","names","go","here")` functions.
Once again, all four of these functions would only take previously-visited passages into account, and
will completely ignore the passage which is currently 'active', so, if an author wants to see if the
current passage/a passage with the same tag as the current one has been visited before, they can.
These could then be combined via the `and(booleans,go,here)` and `or(booleans,go,here)` functions,
which would take a variable-length number of boolean parameters, returning the result of a boolean AND
operation being performed on them in the case of the former, whilst returning the result of a boolean
OR in the case of the latter. This would be rounded off by a `not(boolean)` function, which simply
returns the inverse of the input. I then had the slight problem of how to go about implementing this.

It was at this point where I found myself making what might be the single most questionable decision
of this entire development process; I chose to implement it via `eval()` abuse. I created a class
called `Checcer` (yes, these bad names write themselves) within the `heccer.js` file, called `checcer`,
holding a reference to the singleton `Heccer` object. The aforementioned any/all functions,
as well as those boolean operations, would be implemented as functions of this `checcer`. When an
if statement was being processed, I would give the string between the `{if:` and the first `}` to
the `checcer` via calling `theCheccer.checc(statementToCheck)` function to evaluate it.
This function would start by replacing the instances of the raw aforementioned method calls within
the 'statementToCheck' with ones that are in the form `this.pAny(` etc. instead, using the methods
held within the `Checcer` object. I am aware that `eval()` is a very bad solution to this problem,
and I have tried to work out some way of replacing it with something that doesn't involve `eval`s,
such as `Function` calls, however, none of these have been successful yet.

This string would then be evaluated via `!!eval(statement)`. The `!!` is used here to convert any
potential 'truthy' or 'falsy' return values into a simple boolean true or false. This method call
is also in a try/catch block, so, if an exception is thrown during the evaluation of the statement,
it will just return false instead. The `or` and `and` methods also use the `!!eval(statement)`
to evaluate each statement that they need to evaluate (with the 'or' method returning true upon
reaching the first true evaluation (false if no trues are met), and the 'and' method returning
false upon reaching the first false/exception-throwing method (true otherwise)). The 'not' method
just has a simple `!eval(statement)` which just negates the potential truthy/falsy value. The 'any'
and 'all' methods would check if a given string was in the visited states/visited tags sets via
`this.theHeccer.stateStack.seenTags` or `this.theHeccer.stateStack.visitedPassages`, looking at the
collections themselves.

As I was writing this code, moved the declaration of the `theHeccer` object itself into the `heccer.js`
file, instead of being in `index.html`. This, in turn, would mean that my IDE also wouldn't be able
to complain about the methods of the `Checcer` object referencing a potentially undefined variable.
This rewrite still performed completely as expected. I unit-tested the functionality of the checcer
class before I attempted to test the conditional formatting, as the formatting would obviously fail
if the underlying logic was faulty. These unit tests can be seen [here](../src-js/checcerTests.html),
and, eventually, they all passed as expected.

##### Figure 36: The unit tests for the 'checcer' part of the HECCIN' Game all passing as expected

![checcerTests](./final/heccin%20game/checcer%20tests%20working.PNG)

Eventually, I had this version of the heccer which supported conditional statements fully implemented.
I then chose to unit test it, to see if it behaved as expected with conditional statements of varying
degrees of stupidity, and these unit tests can be seen [here](../src-js/showdown/heccer%20conditional%20tests.html).
It worked exactly as expected.

##### Figure 37: The unit tests for the conditional formatting within the HECCIN' Game all passing as expected

![conditionalTests](./final/heccin%20game/conditionals%20working.PNG)

Later on in the development cycle, I added two more methods to the `Checcer` class; these being
`pCount(passageName)` and `tCount(tag)`, usable to obtain the number of times that the passage with
the given name/the given tag have been encountered so far (returning this as a number, which the
user can then perform other sorts of mathematical/comparison operations on). For this to work, I had
to refactor the code which kept track of the visited passages/tags to keep track of them in maps
(using passage/tag names as keys, and encounter count as values) instead of sets. I rewrote the code
that fills these maps to fill both within a single iteration (as I would need to update the counts for
the tag map each time a certain passage was encountered anyway). The existing `Checcer` code was
updated to refer to the entry sets of these maps, and I added the `pCount`/`tCount` methods to the
class. These methods would simply attempt to obtain the value associated with the given key in those
maps, and, if the value was not present, it would return 0. I updated the aforementioned unit tests
to include tests for this function, and, once again, it worked as expected.

One final addition made to the HECCIN' Game was the addition of a special `noreturn` tag that could
be given to passages, to disable the 'back' button. Inspired by the discussion of how
'The cycle, not the branch, goto, or jump, is the central hypertext structure'[3] within M. Bernstein's
article *On hypertext narrative*, I decided that the final hypertext game I would produce would be one
that is about time loops (in some form), where the player would be trying to stop something from
happening, would fail multiple times, but, each time they fail, they would find out more about the
problem and how to stop it, before ultimately being able to stop it from happening. However, this
approach would work better if I could make sure the player obtained the information 'legitimately'
(as in, playing through the sequence which gives them the necessary information before they can use
aforementioned information). The above conditional statements could allow me to keep track of whether
or not a player had encountered the passage which holds the information in question, however, there is
a potential problem. A player could go through the branch until they find the passage with the
information, and then choose to 'cheat' by, instead of going through the intended 'failure' and loop,
using the back button to navigate back to the passage where they would want to apply said information,
and would then complain about not having the ability to use this information which, according to the
game, they don't have. I remembered the discussion on the 'absorptive' mode of reading hypertexts,
within H. K. Rustad's article about *A Four-Sided Model for Reading Hypertext Fiction*, being
characterized 'by the experience of a lack of control'[4]. This was when I realized that there was
one piece of control that could be removed; the back button. By allowing an author to selectively
disable to back button, this could ensure that the player continues down a route as expected, not
allowing them to back out of it when they're not supposed to, and to further deter any 'cheating' via
a player trying to apply any knowledge they don't officially have.

The `noreturn` tag is implemented in a rather simple way: If an author wants to disable the 'back'
button for a given passage, they simply need to give that passage a `noreturn` tag in its tag list.
The `Heccer` object in the HECCIN' Game would be given three more attributes: A String with the default
'back button' text (`Click this button to go back`), a String holding the text that will be displayed
on the back button when it has been disabled (`POINT OF NO RETURN`), and a boolean which indicates
whether or not the back button is enabled (`allowedToGoBack`). When a passage is made active, just
before it is loaded, the heccer looks at whether or not that passage has a `noreturn` tag. If it has
that tag, it looks at `allowedToGoBack`. If that's true, it doesn't change anything, but, if it's
false, it will set it to false, and replace the back button text with the 'disabled' text. If there
is no `noreturn` tag, `allowedToGoBack` is set to true, and the back button text is replaced with the
'enabled' text. I tried it out (once again, within the [src-js/showdown/index.html](../src-js/showdown/index.html)
file), and it worked as expected.

I did need to rewrite the `TextAssetReader` and `FolderOutputter` classes in HECC-UP to read/output
a copy of showdown.js along with the other components of the HECCIN' Game when done parsing them,
which I did. I also replaced the copies of `heccer.js` and `index.html` within the
`src/assets/textAssets` folder with the updated versions as well. I attempted to re-output the existing
games I had made with HECC-UP, to verify that the new files were output as expected, which they did.
I also made a couple of minor changes to the inline css within `index.html` (changing a few specifics
with the layout, and making the text 20px sans serif), and made the `FolderOutputter` put the title of
the HECCIN' Game within the `<title></title>` HTML tags for `index.html`.

All that was left was to make a better game, using these new features. But first, some class diagrams.

## 1.2: Class diagrams of the improved HECCIN' Game

Firstly, here's a class diagram showing how the global variables in each file within the HECCIN' Game interact
with each other:

##### Figure 38: A class diagram showing the global variables within each file of the HECCIN' Game

![globals](./final/heccin%20game/globals.png)

And here's a version of the class diagram with the important details included in it.

##### Figure 39: A full class diagram for the final version of the HECCIN' Game

![final](./final/heccin%20game/final%20class%20diagram.png)

As you can see, I didn't get around to implementing the variables or saving/loading games. However,
through the use of passages (and their tags) and the conditional statements, it is still possible
for an author to create a game which 'remembers' what a player has done, and to give them different
options appropriately.

## 1.3: *Countdown*

As mentioned earlier, I was thinking of creating a hypertext game that involved timeloops in some way,
and that the player was supposed to try preventing a certain thing from happening, being sent back to
an earlier point in time (but still aware of any information they found out en-route) when the
aforementioned thing happens. The first question I had to ask myself was 'what is the thing that the
player will need to stop?'. It would need to be some sort of thing that would be feasible for a single
human to stop, somehow be able to send the player back in time (in some way), be something that the
player can't feasibly get away from (so the player would have a reason to stop it), and somehow be
able to justify only the player (and perhaps also an antagonist figure) having the knowledge of the
previous iterations in which the player failed. It would also need to be something that would happen
anyway, regardless of any (in)action from all of the characters involved.

I had a couple of ideas for this. At first I considered a scenario where the player was in a spaceship
which was on a collision course with a nearby planet, and the player would need to try to find an
override for the spaceship's autopilot, whilst also trying to work out who was responsible for
tampering with the autopilot. I opted against this approach because I couldn't really find a good
excuse to let the player suspend their disbelief at being in a spaceship, this particular 'deadline'
being one that would realistically take too long to be engaging or be too short for the player to be
able to do anything about it (as the player would need enough 'time' before impact to feasibly get
the spaceship to not be on a collision course), it didn't offer any excuses to send the player back
'early' if they're done with the important parts of the route, and I couldn't think of any good excuse
for someone on a spaceship to sabotage the autopilot like that, or for the player to be sent back
after dying. I then had another idea: a bomb. It's somewhat more down-to-earth (so less suspension of
disbelief would be needed), it's feasible for a single person to stop it blowing up (regardless of
how much time is left on the fuse), gives me an excuse to kill off the player a bit sooner if I need
to send them back early, it's immediate, and there won't be any plot holes from the antagonist doing
something that would definitely kill them as well. Additionally, I would have some flexibility when
coming up with an excuse for why the player was able to come back, as well as the setting for the game.

*Countdown* itself was still more of a tech demo for the conditional statements than a proper game,
having a rather simple overall plot. The player finds themselves in a dark room, the light turns on,
a box is dropped in front of them, then they realize that the box contains a bomb. They're given
the option to look for an exit or to start defusing the bomb; looking for an exit eventually leads to
the bomb exploding, sending them back to the choice where they can pick whether they want to look for
an exit or start defusing, with a `noreturn` tag on that passage so the player doesn't go back to just
before they blew up. When the 'defuse' option is selected, the player is given a choice to cut a red
wire or a blue wire. Regardless of their choice, the bomb eventually blows up. When the player goes
back to that choice, they will only be able to pick the other wire (because they would know that
cutting the first wire won't work). They're then given a choice between waiting to see what happens,
or cutting the other wire as well. If they choose to see what happens, it blows up, because the
correct answer was to cut both wires. Whenever they head back to the 'pick a wire' passage, the
prior choices will be gone, and the only choice will be the (correct) 'cut both'. Regardless of
when exactly the player chose to cut both wires (after failing one defusal or failing two defusals),
the player is then given a choice between a big red button and a small green button. The big red
button choice, once again, sends them back to the 'explosion' passage. The green button choice is the
'correct' one, and sends the player to the ending.

As a story, and as a game, *Countdown* is honestly terrible. It's very short, nothing interesting
happens, no explanation is given for why the player is there, and the 'correct' answer for cutting the
wires (cutting both) is arbitrarily hidden until the player has already 'lost' once already by cutting
only a single wire. To a player approaching this as a game, this could look like this game is trying
to trick them and make them feel stupid, because the correct answer was not presented as an option,
which they probably will resent. However, as a demonstration of the conditional statements working,
I would argue that it fulfils this purpose sufficiently, as, when the player is defusing the bomb,
they'll notice that the game remembered their choices (specifically, their choice of wires), and that
it edited the choices presented to them accordingly. Then, if the aforementioned player wanted to
write their own HECCIN' Game with conditional statements, they could use `Countdown` as a template
to write something even better.

If you want to play `Countdown`, the files for it are  in [HECCIN Games/countdown/countdown](../HECCIN%20Games/countdown/countdown),
and the `.hecc` file for it is present at [HECCIN Games/countdown/Countdown.hecc](../HECCIN%20Games/countdown/Countdown.hecc).
Alternatively, it can be played as part of [The HECC-IT Demo](https://11belowstudio.itch.io/the-hecc-it-demo).
An overview of `Countdown.hecc`, as shown by the final version of OH-HECC, can be seen below:

##### Figure 40: *Countdown*, as presented by OH-HECC

![Countdown overview](./final/heccin%20game/countdown%20overview.PNG)

The HECCIN' Game part of HECC-IT didn't receive any further changes worth discussing further during
this stage of development, besides some of the code being cleaned up a bit towards the end of the
overall development process, and being used to produce yet another game, *Backblast*, which will
be discussed further on. With this in mind, I suppose I should move on to discussing the improvements
made to the tool component of HECC-IT itself, before finally discussing *Backblast*.

## 1.4: Citations

[1] OpenJS Foundation, “QUnit,” Qunitjs.com. [Online]. Available: https://qunitjs.com/ [Accessed: 19-Jan-2021].

[2] E. Santos, P. Deschênes, C. Innis, R. Sharp, K. Käfer, R. Braun, D. Tarr, C. Chen, T. Stone, R. Sutherland, P. Lang, B. Combee, A. Backstrom, H. Wolfe, A. Courtiol, K. Balakrishnan, rheber, J. Gruber and J. Fraser, "showdownjs/showdown: A bidirectional Markdown to HTML to Markdown converter written in Javascript," 2 November 2019. [Online]. Available: https://github.com/showdownjs/showdown. [Accessed 19-Jan-2021].

[3] M. Bernstein, “On hypertext narrative”, in *Proceedings of the 20th ACM conference on Hypertext and hypermedia - HT ’09*, 2009

[4] H. K. Rustad, "A Four-Sided Model for Reading Hypertext Fiction". *Hyperrhiz: New Media Cultures*, vol. 6, 2009
