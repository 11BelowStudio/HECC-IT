# Term 2 - Developing the final version of HECC-IT

During term 2, I made several improvements to the HECCIN' Game (such as adding support for markdown
formatting, conditional statements, and general code cleanup), made some improvements to HECC-IT itself
(mostly back-end, involving the background logic, yet with a few substantial enhancements for user
experience), and created a proper hypertext game using HECC-IT.

## Part 1: The improvements to the HECCIN' Game

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
in the `Heccer` class, being refreshed via a method being called upon a passage being loaded.
This method would create two empty sets (for names and tags), iterate through the `GameStateStack` to
add the visited passage names to the name set, and then it would iterate through that name set, to add
the tags for those visited passages to the visited tag set, before finally saving those as the current
visited sets. I chose to use a separate iteration for the tags because, this way, I wouldn't find
myself attempting to add all the tags for one passage multiple times if that passage had been visited
multiple times. There was a slight problem with working out what unit testing
framework I could actually use for JavaScript, however, I eventually found out about [QUnit](https://qunitjs.com/),
so, I used that to verify that the current iteration of `heccer.js` actually worked as intended.
These preliminary unit tests can be seen in [src_js/tests for seen passages and tags.html](../src-js/tests%20for%20seen%20passages%20and%20tags.html),
all of which passed as expected.

I then created a subfolder, [src_js/showdown](../src-js/showdown), which I then copied the existing
index.html/heccer.js files (and the version of hecced.js from 'A Conversation') to, as well as a copy
of showdown.js (along with a copy of the legal info for it). The first thing I did was attempt to
rewrite index.html to 'include' the code in `showdown.min.js`, before rewriting heccer.js so, when the
current passage is 'loaded', it'll be processed by showdown.js before being displayed on index.html.
I also rewrote the hecced.js file to include some markdown formatted elements. I then opened the
index.html file in my browser, to see if the markdown formatting was working as expected, and, to my
surprise, it was. I did need to change a few settings for the showdown converter within index.html
(such as enabling the 'simple line breaks' rule), but, it worked as anticipated.

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
everything worked as intended. It did.

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
`pAny("passage","names","go","here")` and `pAll("passage","names","go","here")` functions. These
could then be combined via the `and(booleans,go,here)` and `or(booleans,go,here)` functions, which
would take a variable-length number of boolean parameters, returning the result of a boolean AND
operation being performed on them in the case of the former, whilst returning the result of a boolean
OR in the case of the latter. This would be rounded off by a `not(boolean)` function, which simply
returns the inverse of the input. I then had the slight problem of how to go about implementing this.

It was at this point where I found myself making what might be the single most questionable decision
of this entire development process; I chose to implement it via `eval()` abuse. I created a class
called `Checcer` (yes, these bad names write themselves) within the `heccer.js` file, which the
`Heccer` object would have an instance of (called `checcer`). The aforementioned any/all functions,
as well as those boolean operations, would be implemented as functions of this `checcer`. When an
if statement was being processed, I would give the string between the `{if:` and the first `}` to
the `checcer` via calling `theHeccer.checcer.checc(statementToCheck)` function to evaluate it.
This function would start by replacing the instances of the raw aforementioned method calls within
the 'statementToCheck' with ones that are in the form `this.pAny(` etc. instead, using the methods
held within the `Checcer` object.
This string would then be evaluated via `!!eval(statement)`. The `!!` is used here to convert any
potential 'truthy' or 'falsy' return values into a simple boolean true or false. This method call
is also in a try/catch block, so, if an exception is thrown during the evaluation of the statement,
it will just return false instead. The `or` and `and` methods also use the `!!eval(statement)`
to evaluate each statement that they need to evaluate (with the 'or' method returning true upon
reaching the first true evaluation (false if no trues are met), and the 'and' method returning
false upon reaching the first false/exception-throwing method (true otherwise)). The 'not' method
just has a simple `!eval(statement)` which just negates the potential truthy/falsy value. The 'any'
and 'all' methods would use a reference to the `theHeccer` object itself to call some methods that
would check if a given string was in the visited states/visited tags sets.

Later on in the development cycle (pCount, tCount)

As I was writing this code, moved the declaration of the `theHeccer` object itself into the `heccer.js`
file, instead of being in `index.html`. This, in turn, would mean that my IDE also wouldn't be able
to complain about the methods of the `Checcer` object referencing a potentially undefined variable.
This rewrite still performed completely as expected. I unit tested the functionality of the checcer
class before I attempted to test the conditional formatting, as the formatting would obviously fail
if the underlying logic was faulty. These unit tests can be seen [here](../src-js/tests%20for%20heccer2.html),
and, eventually, they all passed as expected.

Eventually, I had this version of the heccer which supported conditional statements fully implemented.
I then chose to unit test it, to see if it behaved as expected with conditional statements of varying
degrees of stupidity, and these unit tests can be seen [here](../src-js/showdown/heccer%20conditional%20tests.html).
It worked exactly as expected.

mention noreturn

class diagram of updated heccer

discuss 'Countdown'