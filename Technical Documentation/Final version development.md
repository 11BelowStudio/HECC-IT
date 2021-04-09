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
runtime anyway. Finally, there was also the potential of (ab)using this to help out with the desired
conditional formatting.