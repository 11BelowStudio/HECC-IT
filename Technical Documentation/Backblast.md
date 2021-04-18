# *Backblast*

First things first: obligatory spoiler warning for Backblast.

If you have not played Backblast yet, you can either download it from [this repository](../game/Backblast.zip),
or you can play it on my itch.io page, at [https://11belowstudio.itch.io/backblast](https://11belowstudio.itch.io/backblast).
(If I haven't gotten around to making the page public yet, the password you need to enter to see
it will be `301`).

With that said and done, time to discuss the development of it and the thought process behind it.

## The initial idea

As mentioned earlier on, in the document about the final version of the HECCIN' Game, I was
aiming to create a game involving timeloops, where the player would 'die' multiple times,
and then having to use that knowledge they gained when they 'died' to avoid 'dying' again.
In theory, this premise could have made for a rather interesting game, but, in practice, I would
say that it completely failed to live up to expectations.

The root cause for all of the problems in this game was the complete lack of anything that
resembled a proper plan for what would happen in the game, only having vague ideas.

For example, from the start, I knew I wanted the 'physics' by which the player would be able to
travel between 'realities' upon death would be similar to the 'physics' by which that sort of
thing happens in *Zero Escape: Zero Time Dilemma*[1]. I also knew that I wanted to give the
player have a choice of routes for the game, all of which eventually end in the player 'dying',
eventually unlocking the path to the 'true ending'. However, I wasn't entirely sure what
the details of the plot would be, how to actually justify the 'going back in time', or who any
of the characters would be. Therefore, a lot of it was just 'making it up as I went along'.

One of the earliest 'vague ideas' I had would be that the game would be set in a facility of
sorts that was investigating parallel universes, probably with some sort of big time-manipulation
macguffin present in the facility, which the antagonist (who would probably have been one of
the people behind the creation of the machine in the first place) was trying to blow up,
with their motive being that they had 'learned *too* much' using that macguffin, wanting to
destroy it because of it being too dangerous to use. In this situation, the 'going back in time
upon exploding' would be justified using an excuse along the lines of 'spacetime getting damaged
by the machine when it blows up'. Each of the 'routes' would involve following another character
around and seeing if they have an alibi, finding out a bit more about how the bomb could be
defused, and ultimately 'dying' shortly after establishing that the current person was not
the killer. I toyed with the idea of the lead researcher being the culprit (their excuse being
that, when they were using the machine to research one thing, they accidentally found out
something very unpleasant about the nature of reality, leading to them getting cold feet and
wanting to save everyone else from that forbidden knowledge), or perhaps even the 'player' being
the culprit (except it was a version of the 'player' from another reality who planted the bomb,
and that the actual player got moved to this reality as a side effect of 'their' actions).
I ultimately opted against this plan because I wasn't sure how to justify the player finding
themselves in this situation in the first place, and I was at a bit of a loss for ideas when
it came to coming up with these characters, who would need to all be rather distinct from each
other, and would all need to be viewable as potential culprits.

Then I had the idea of keeping the research lab setting, but the player would be there as a
'test subject' instead, and that the antagonist could be trying to kill the player repeatedly
to see if the player was able to 'prove' that it somehow is possible to travel between parallel
universes after death, by successfully not dying and also working out who the culprit was.
I also considered the idea of having the actual bomber be someone seeking to sabotage the work
of the people who are trying to perform that research (with the 'that's forbidden knowledge'
motive), attempting to blow the whole place up because it's rather hard to do anything when all
your equipment has been completely destroyed, and being allowed to get away with it by the person
at the top of the managerial foodchain there because that person knows about the multiple
universes, with a mindset along the lines of 'if they succeed in this timeline, it doesn't
matter, because there will be a timeline where they will fail'.

With that vague idea in mind, I started writing it, in the hope of some new ideas appearing
as I went along.

I should also mention that, as I was writing the first part of it, the topic of the Hero's
Journey/the Monomyth was starting to get discussed in the CE317 Virtual Worlds module, so I
also considered trying to include elements from the Hero's Journey story structure into my game.
However, owing to a rather extended period of writer's block I suffered from about halfway
through the story of the game, followed by a rather sudden flash of inspiration/new ideas, and
a rather enthusiastic bit of writing out the remainder of the game after that, this structure
was abandoned towards the latter half of the game.

## The intro

I wanted the intro to serve the purpose of getting the player into the situation they were in
for the game, and do it in a way that wasn't overly forced, with the player ultimately being
the person who 'chose' to put themselves in the messed up situation that they would find
themselves in.

The plan was simple: it would be an ordinary morning for the player. They would get some post,
and, as well as junk mail, a rather suspicious-looking letter advertising the 'research
placement' which would get them into the mess, which the player would reply to, either
accepting the offer, or rejecting it. The game would resume on another ordinary morning.
If the player rejected it, the game would end; if they accepted it, they would be 'kidnapped'
and taken to the facility.

The conversation of the Mandela Effect which the player can 'hear' over the radio and has the
option of listening to further is intended to be a 'call to adventure' of sorts. The player's
fate, should they choose to accept it, is to basically travel between multiple parallel worlds,
where things may not be entirely the same, with their memories of the 'other' events that may
not have happened in 'this' reality from the 'other' reality. Some sort of 'refusal of the call'
is present if the player opts to ignore listening to it in the first place, or shortly after
the conversation, where the somewhat passive-aggressive narrative voice in the work dismisses
it as something completely made up.

The letter was an excuse at something that resembled a 'supernatural aid' for the player.
I attempted to establish some sort of 'problem' for the player's 'mundane world' in the form
of a large pile of bills to pay off, and the supernatural aid (the letter) was intended to be
an offer of 'come here to this place and you can get the money you need to pay off the bills'.
It was at this point where I had a thought about how to potentially do the 'rescue from
without' stage of the Hero's Journey later on; perhaps the player could eventually be trying
to escape the facility, with the money in hand, but being pursued by someone; ultimately, the
player would be 'rescued' by a bailiff who has tracked down the player because of all the
unpaid bills (or, in other words, because the mundane world's problem needed fixing). That
particular vague idea wasn't implemented in the final game, unfortunately.

Back to the topic at hand: the letter. This introduced the *Institution for Practical
Parallelization* (with the name hinting at them trying to find some 'practical' use for
parallel universes), as well as the character of Dr Albert. Dr Albert's name was a reference
to the story *The Garden of Forking Paths* by Jorge Luis Borges[2], which was mentioned in some
of the literature on the topic of hypertext games which I had encountered when doing the
background research for the project. In short, that story mentions a fictional
novel/'labyrinth' which had been written by an ancestor of the protagonist, which another
character, called Doctor Stephen Albert, had just worked out the true nature of (essentially
a hypertext, and this was long before hypertexts were a real concept), before promptly being
murdered by the protagonist because the protagonist had just been rumbled for being a spy
and needed to get a message involving 'Albert' to the germans before getting executed himself.
I deemed this name to be appropriate for the Dr Albert within *Backblast*, because, just like
Doctor Stephen Albert, he is investigating things involving 'constantly diverging paths' in
time, shows up in a twist of fate that is rather useful for the protagonist, and both have
died (Doctor Stephen Albert gets murdered by the protagonist, but Dr Albert has died once
already, before the events of the game, and dies again in one of the endings). However, unlike
Doctor Stephen Albert, Dr Albert is less of a 'victim' character, and more of a 'potential
mastermind' sort of character, being the person who gets the player into this mess in the
first place.

The first name of Dr Albert, being Archibald, didn't really have any intentional symbolism
behind it, besides that it sounded appropriately pretentious.

The first major choice the player is given is the choice of whether or not they want to accept
the offer they were sent in the post. This was heavily inspired by the first decision the player
has to make in *Zero Escape: Zero Time Dilemma*[1], where the antagonist flips a coin, tells
the other characters that if they guess it correctly, they can go free, but if they get it
wrong, chaos will ensue, before the player is prompted to make a decision about what the result
of the coin flip was. In that game, the player's first choice will always be right, leading to
the ending where nobody dies (to establish that there is a timeline where nothing happens).
However, for the game to properly start, the player must play through that sequence again and
actively 'guess' it incorrectly, knowing the right answer. *Backblast* doesn't start with that
sort of hostage situation, however, I wanted to keep the spirit of 'the player is the one who
chose to put themselves in this current predicament' present in that opening sequence.

The first ending for the game, where the player picks 'no', happens shortly afterwards.
The passages immediately after sending the reply are 'noreturn' passages, to incite some sense
of absorptive 'reading' for this hypertext within the player, establishing a feeling of 'you've
made this choice, you can't go back now'. This was also intended to mirror how, within a
book-based hypertext fiction, there isn't really a 'back' button a reader can use to easily
go back to an earlier 'part' of the game (unless they remember what section number they were at),
so they just have to continue with the consequences of their actions. In the 'no' ending, the
player receives a followup letter to offer some closure on the topic (confirming that, in this
timeline, the player is not going to be bothered again), but still should incite some
curiosity from the player when it mentions 'another timeline'. The intended effect is that the
player will replay the game (in 'another timeline'), and accepting it instead, with the player
subconsciously being aware of 'this is the other timeline that was mentioned last time'.

The 'yes' choice here is intended to be some form of 'crossing the first threshold', as the
player has actively made a step into the other world (by replying to state their intent to enter
the other world). This ending pans out in roughly the same way as the 'no' ending at first,
except the player is kidnapped instead this time. The lack of any option for the player to
escape the sleeping gas bomb that was put into their letterbox was an intentional design choice;
the player would have already refused to 'escape', by replying with a 'yes' instead of a 'no',
so it's their own fault that they're in this mess, and is now paying the price for that choice.

This is also where the player should realize that 'fading to black' means that they have been
knocked out.

## The part leading up to First Death

I wanted the player to feel trapped as soon as they 'woke up' in the room, which is why the
first passage here is a 'noreturn' passage. This was intended to be the 'belly of the whale'
stage of the 'Hero's Journey' which the player is embarking on, where they would find themselves
trapped in the 'other world', with their identity stripped away from them (being referred to
from then on as 'Subject C'), and in a genuine state of 'what is going on'. The layout of the
room was intended to not be entirely unlike the layout of the room on campus which I spent all
of the first term in, mostly because I was starting to feel a bit of cabin fever during that
term. The details of the lack of an actual window, and the door not being openable from the
inside, served to make the player feel even more trapped. The letter the player reads here is
partially for exposition (letting the player know why they are here), and also for indirectly
reinforcing the fact that the player is only here because of their own choice to reply with
a 'yes' to the letter.

This part of the game is intended to be more of a 'setting the context' than a proper part
of the actual 'game', which is why the player is unable to make any meaningful choices at
this point of the game. At this point in time, the player has not got a specific 'goal' to
achieve, and the player has no real motive for making a choice that may be meaningful, which
is why this section is very much linear.

And then there's Percival.

Percival's name wasn't chosen for any symbolic reasons. Nothing about Percival was intended to
be symbolic. Percival was originally a character who I created for another game/exercise in
beating a dead horse of mine called *The Button Factory*, starting out as just another throwaway
line in a 12-minute long audio track of throwaway lines and terrible lyrics accompanied by a
kazoo, before turning into another 4-minute long rant/soliloquy about working in a 'making sense
department' of a button factory in a genuinely painful-to-hear overly nasal voice that exists
for the sole purpose of ruining the player's flow if they are somehow doing decently at the game
(that game *was* created for a 'So Bad It's Good' game jam though). He's called Percival because
I asked my brother to suggest a good name for a character with that terrible voice, and that's
the name he suggested. I chose to re-use Percival for this game because he was genuinely a fun
character to write, he's the sort of character who nobody would bother telling anything to
(meaning he can be used to ask the questions that I want the player to be asking as well),
and he's so incompetent that nobody would ever seriously suspect him of being the culprit.
Additionally, it'll be a nice reference for the three or so people who actually care about it.

As you may have guessed, I already knew what sort of character he would be: overly pedantic,
blissfully unaware of the scale of his incompetence, and someone that the player would be
laughing at, not with. Most importantly, his job is to make the player feel somewhat safe,
because he's clearly too inept to have any sort of malicious intent.

This is so, when the player gets taken to the briefing room, the player will be caught somewhat
off-guard by the events that happen there. The briefing room is arguably also a more obvious
challenge-style 'threshold'. When I was writing it, I opted to make Percival remain outside,
so the player would feel somewhat isolated, and feel rather distrustful of Percival (after all,
Percival was outside the room as the player was stuck inside it, in front of a bomb). The 'video'
that the player 'watches' in here is supposed to be a bit of drip-fed exposition/foreshadowing
for what is going to happen rather shortly, to try to build up some tension, and make the player
feel a bit curious about what exactly is supposed to be happening next. The actual explanation
with the bikers was lovingly influenced by a scene towards the end of *Zero Escape: Virtue's
Last Reward*[1] where a very similar explanation is used towards the end to explain the physics
of that game's reality. In that game, it's presented as a hypothetical, but, in this game's
overall lore, the events discussed by this cartoon happened to the character of Dr Albert.

The sudden turn to the rather morbid subject matter in that cartoon, along with it directly
addressing the player (as Subject C) is intended to be a bit of a gut punch for the player,
and make them immediately start to panic and feel unsafe. The non-option of attempting
to escape, as well as the `noreturn` tag at the start of the briefing room sequence, was
intended to amplify this effect (as the player cannot escape in any way), before the bomb
detonates, shortly after the player finds out that they were supposed to have gotten rid
of it earlier on.

The detonation of the bomb is intended to be the point where this 'intro' ends, as the
player now knows that someone is trying to kill them (or at least cause them some harm,
as it's not explicitly stated that the player was killed, merely that there was a bright
flash of light, leading to everything fading to white for the player). This is intended
to be a climax of sorts for the tension building up throughout this 'intro' sequence,
and, more importantly, gives the player the necessary motivation to make choices
that could be meaningful.

I will admit that the overall briefing room sequence (including the latter part of
it, to be discussed later on) was somewhat influenced by a suggestion made by Dr
Bartle when I was explaining the basic concept for this game (that suggestion
being not knowing there is a bomb in the room, dying from it, and then having
the option to escape from/get rid of the bomb). However, the 'briefing room' aspect
of it was my own idea, because having a bomb blow up out of the blue in front of
the player would be rather confusing and could feel forced, but the context given
from the briefing makes the sudden presence of the bomb make sense.

The player is then sent back to the passage that was at the start of this sequence
(where they wake up in the room, describing the room), to immediately hit them
with a healthy dose of deja-vu, get them to start asking themselves what happened
(having to base it only on what they remember, due to the `noreturn` tag here),
and to establish that they need to try to work out how to avoid dying this time.
The text at the end of this iteration of the passage is completely different.
This was implemented via the conditional statements feature of HECC-IT (seeing
if the player had died yet), sending the player to the 'deja vu' section.

## The 'Deja Vu' section.

The goal for this section was to make the player realize that they had somehow
been sent back in time from the explosion, albeit to a timeline that is slightly
different, and get them to save themselves from the explosion.

The first choice the player is given here is how they will respond to Percival.
This choice is ultimately meaningless, as, either way, the player will find
themselves in the briefing room again, whether they like it or not. The
intended effect is to establish that 'flight' (ie not going near the bomb)
is not a viable option for the player, and that 'fight' (getting rid of the bomb)
is the only feasible option they have. However, I also wanted to establish
that Percival isn't really seeking to harm the player (at least, not right now).
If the player opts to fight Percival, I intentionally wrote it so Percival
'won' simply by a fluke, not out of malice. Additionally, the futility of
fighting Percival and hiding from Percival were foreshadowed in the 'previous'
iteration of this scene (with Percival mentioning that he was allowed to knock
the player out).

The option to greet Percival instead is here to give an opportunity for the
character of Percival to become a bit more fleshed-out (by giving the player
an opportunity to interact with that character), and for it to be established
in more concrete terms that this is a different timeline to the first one.
Percival acts surprised when you greet him (not knowing you, establishing
that you have gone back to the first meeting), and is depicted slightly
differently (establishing that this is a different timeline, via his hat
being a different colour).

When Percival escorts you to the briefing room, the player is presented
with another slightly meaningful choice. They may either go with Percival,
or they could run away (which the player probably would want to do).
Staying with Percival is rewarded with an opportunity to find out a bit
more about the facility (letting it slip that the player had been 'given' an
experimental serum), establish that things are definitely different
(by acknowledging the hat), or have some other interaction with Percival.
Running away instead leads to the 'maze' section. Most of the routes in
this section lead you back to Percival, in front of the briefing room,
once again establishing that there is no point in trying to escape.

The 'secret ending' in the maze section initially wasn't an ending.
At first, the 'endless corridor' segment would end with the player turning
around, getting knocked out, and finding themselves in front of Percival
in the briefing room, just like the 'fight' and 'hide' choices. But this
sequence of events made no sense within the context of the game, and was
a bit of a waste of effort for the player considering how hidden it was.
Therefore, I opted to turn this into a secret 'joke ending' instead.

I added the part with the door, and I had considered making the player
open the door, leading to a dog in a control room, like the 'dog ending'
in *Silent Hill 2*. Then I realized that doing this would just be an
overly blatant rip-off of that ending from *Silent Hill 2*. However,
as I had already included a radio conversation in the 'no' ending
that alluded to the letter at the start being a hoax, I decided to
make this ending one where it was all just a prank. This ending, being
accessed via a series of events that don't make sense, was never
intended to make sense, and the narrative voice acknowledges this,
and should leave the player with a sense of 'what the hell was that?'.
To a player who wanted to escape, this does deliver them an escape,
but the sheer confusion in this ending was intended to convey the fact
that the player wasn't supposed to escape. I don't consider this ending
to be canon (to any timeline), but it's a nice secret for the people
who bungle their way through the 'maze' to reach it.

Most players will eventually find themselves back in the briefing room.
Again, because of the different timeline, Percival is in the room with
them (and, if the player opted to try fighting/hiding from Percival,
they will find themselves in front of a rather exhausted Percival).
Once again, the player is not allowed to leave the room, even if they
try to use that option, because Percival won't let them. This means
that the only feasible option that the player has is to get rid of
the projector (as previously instructed). The main reason why I chose
to put Percival in this scene is firstly to make the player doubt
any thoughts they may have had about Percival being the culprit (as
no culprit would be stupid enough to put themselves in harm's way),
but also to be able to see the player taking care of the projector,
and to then ask the questions that would need to be asked here.

As throwing the projector out of the window is the only viable course of action,
the player will do that, despite Percival's expressions of utter confusion. By doing that,
the player has arguably properly passed the threshold, and is now properly
within the 'other world' of the Institution for Practical Parallelization.
Unfortunately, just like the player may be feeling at this stage of the game,
I too was stuck with a feeling of 'okay, what now?', as the player had just managed
to avoid their first (of several) deaths.

## The aftermath of the defenestration of the projector.

This stage of the game went through a couple of revisions. At first, I decided that I would
introduce a new character here; Susan. She would have been Percival's (long-suffering)
supervisor, who would be introduced as a voice over the intercom to break the silence
after the defenestration of that projector, summoning the percival and the player to her
office, and then trying to work out why you (the player) felt the need to discard of the
projector, and why Percival just stood there instead of doing anything about it. This would
then have been followed by the player moving the conversation forward to the topic of the
bomb (which neither of them would have known anything about), to the formulation of some
sort of plan to work out what is going on.

That idea was scrapped when I realized that Susan wasn't really serving a role that was entirely
relevant to the plot, opened up several plot holes (how could someone who would have immediately
known about the projector getting chucked somehow not know about someone planting bombs everywhere
recently?), there was a rather awkward interval in which Percival escorts you to her office, and
the questions she was asking could have been asked in a much more natural, and in a much more
entertaining way, by Percival.

At this stage, I used Percival as a tool to nudge the player's mindset in the 'right' direction
regarding what just happened. The questions he asks the player about what just happened are there
to offer the player an opportunity to rationalize what just happened in 'their' own words, and the
player is also drip-fed a bit more exposition. The player has the option to accuse Percival of
being the killer (by unsuccessfully trying to call a bluff when he's just repeating information
that the player told him), which fails, which is intended to make the player think that they can
trust Percival. There's a bit of exposition about the previous test subjects dying, which is
intended to make the player know that they aren't actually safe, because there is someone who
is out to get them. I decided to end this part by giving Percival a reason to want to keep the player
alive (making him announce that someone's trying to get him fired by killing you), in turn giving the
player a reason to trust Percival, and putting the player into the correct mindset for the next part:
trying to work out who the killer is.

## The 'main loop' area of the game

The plan for this mirrored the initial concept I had for this game. The player would have a set of
options available to them, leading to some more information about what's going on, and the player's
brutal demise. This would be like some sort of 'road of trials', albeit one where the textual trials
are all failed, because their actual purpose was for the player, as 'the hero', to know more about
the world. Once all of those 'paths' had been explored, the player would then know enough to be able
to confront the antagonist, so they could then do that. But first, I would need to decide who the
antagonist would be.

Eventually, I came up with the character of Dr Cillian Spreewald; a researcher in the IPP, and you are
here to be the test subject for an experiment of his. His name is a pun on the phrase 'killing spree',
and was intended to be the sort of person who cares more about advancing his own understanding than
any net sum of knowledge, has even more of a disregard for the concept of ethical approval than
anyone else working in this facility, and almost definitely got the two letters in front of his name
from a diploma mill. In other words, he's probably the sort of person one would expect to see getting up
to some rather nefarious misadventures in pseudoscience.

I decided to use Percival to explain to the player what the options are. I got Percival to establish that
escape is not an option (due to the facility being on lockdown), so, even if a player did want to escape,
they would understand why it is not presented as an option.

The first option I wrote was the route where the player decides to wait out this lockdown in the room that
they woke up in. This route doesn't lead to anything meaningful happening, besides Spreewald potentially
getting mentioned, being able to find out a bit more exposition from Percival, and having the option to
really annoy Percival. The death here is supposed to reinforce the idea that there isn't anywhere that
the player could hide, and the note that accompanies the bomb is there to let the player know that the
culprit wants to be found (thereby justifying the protagonist confronting the culprit later on).

I then started work on the 'cafeteria' route. This route was intended to be an introduction to the
character of Dr Spreewald, and the player would be approaching him more to 'find out what he might know',
rather than approaching him to confront him just yet. I started to introduce him, and give the player
an opportunity to probe him for some information that could be used against him later on, however,
it was at this moment in time that I started to struggle with a bout of writer's block, and wasn't sure
how to continue it.

## Starting to write another game in the meantime whilst dealing with some writer's block.

I was struggling to come up with ideas for how to continue the game, so I took a break from writing it.
I had some other coursework to get done, and was hoping that a new idea would appear eventually. One
idea eventually did appear. I started to write another game, [*Assigned*](../game/Assigned). This
was more of a rant about the overall system of assigning people genders at birth based on
completely random physical characteristics that nobody has any control over, and how people
are expected to just fit into one of two neatly defined categories for their entire life based
on that. The choices in this game were intentionally designed to not actually be 'choices' as
a result. The only truly meaningful branch I had planned to include in this game was at the
start; using the eval-based conditional statement functionality of HECC-IT, I was able to
get things to randomly display via using a `Math.random()>number goes here` within the 'if'
branches. This would be used to randomly 'assign' what the player would be, by using that to
only show one of three links.

The first passage can link to 3 different passages: There is a 45% chance that the player could be
sent to the passage where they are assigned male at birth, and a 45% chance that the player is
sent to the passage where they are assigned female at birth. There is also a 10% chance that the
player is sent to passage in which they are 'born' intersex, but are forcibly re-assigned to
male/female (50% chance of each). For all the choices following this assignment in this game,
they all contain a conditional statement which checks what the player had been 'assigned' as,
via checking which one of the 'assignment' passages had been visited. If the player had made the
'correct' choice (making the choice that they would be expected to make due to what they were
'assigned' as), the text effectively congratulates them, but if they made the 'wrong' choice,
they are effectively ridiculed, and the other options is forcibly chosen instead.
This was intended to mirror how, in society, that's basically how things work.

I did also consider adding in some stuff to the game later on where the player could
'realize' that something was wrong, and offer the option of being able to fix that problem.
This would promptly be followed by the realization that there's an absurdly long waiting
list, followed by an additional two years of gatekeeping, that would need to be navigated
through first.

The intent of this game was to instill a sense of helplessness in the reader, and make them
realize that this overall system is, for lack of a better term, rather messed up. This is a
bit of a rant, so feel free to skip the rest of this paragraph if you want to.
This system has affected me personally quite a bit since I started to explore my gender
identity, and knowing about the endless waiting lists/gatekeeping I have to look forward
to, along with the continued lack of legal recognition for a 'neither' option, combined with
my own gender identity not being binary female, and the fact that due to the legal declaration
that is needed to get a Gender Recognition Certificate in the first place it could be
nigh-impossible for me to get it updated to have an 'X' instead (if the option ever is added)
if I had already got one with an 'F' on it due to how it might actually turn out to be a
criminal offense to get it corrected to more accurately reflect my gender identity despite
the only options that are presently available to me in this society are both the wrong option
(even if one option is much less wrong than the other one), has just made me feel honestly
completely helpless. There's no real way to win, just many ways to lose, and it looks like
things in this country are going to get worse, not better. And people still wonder why
there's a 40% suicide rate!

Now, there's the question of 'Why didn't I finish writing *Assigned*?'. It was a combination
of several issues. The first was writer's block (again), as I was trying to write some sort of
life story thing, but I genuinely don't remember that much about my early-ish childhood, so I
was struggling to come up with ideas for what events could happen. The next was that it
basically hurt to write it, because it kinda just brought up a bunch of rather depressing
thoughts that I realized I was trying to ignore for the sake of trying to not yeet myself
off the nearest bridge or something along those lines out of sheer hopelessness. The most
important reason, however, was that the writing felt *too* on-the-nose/unsubtle/preachy.
Chances are that this would completely deter an average player from wanting to
play through it or appreciate it, and would just frustrate the player for all the wrong
reasons (not filling them with the intended disgust at the overall system, but filling them
with resentment because they might feel like they're getting treated like an idiot by myself).

There was one final reason. It's because I had already created another game before with basically
the same overall intent (rage at the gender binary system/assigning genders at birth), but delivered
it in a much more effective manner, delivering the message to the player as even more of an active
participant, presented behind several layers of abstraction, in an experience that lasts only a
couple of minutes, not overstaying its welcome. That game, *2*, is already available for free,
at [https://11belowstudio.itch.io/2-a-game-about-a-sorting-system](https://11belowstudio.itch.io/2-a-game-about-a-sorting-system).
Therefore, as *Assigned* would have basically been me repeating myself in a much less engaging way,
I opted to cease development on it, get started with the technical documentation, and resume work
on *Backblast* as soon as I had an opportunity to plan out how to continue it.

## Resuming the development of *Backblast*

On Easter Saturday, my family and I were invited to visit my granddad (he had been given his jabs already,
my parents been given their first jabs, and we maintained social distancing outside), so, whilst I was in
the car going to/from his house, I had a perfect opportunity to properly design the rest of *Backblast*.
Which I did.

The photos of the sheets of paper that I wrote these designs on can be seen in the [../game/some%20plans%20I%20guess](../game/some%20plans%20I%20guess)
directory.

This was when I had the idea of multiple culprits (each of whom made a mutually-exclusive choice to kill you
in each timeline), and Percival actually being Subject A. Those plans regarding the ending were made after
the initial plans on that sheet regarding the 'cafeteria'/'research wing' branches, so some details of those
branches were changed after the initial plan.

For the 'cafeteria' branch, I changed it so Dr Spreewald would leave the room because of him receiving a call.
I chose to add a meaningful decision here, where the player could opt to eavesdrop on his call (or not).
Both of these routes still lead to the player dying in this branch (from a bomb in the room if they opt to
stay in/return to the room, or from a booby trap if they opt to run away), however, if the player chooses to
eavesdrop on the conversation (where Dr Spreewald was talking to Dr Albert about the projector being thrown,
and casting some doubt onto Percival), the player now knows about this call. This is one of several `percivalSus`
passages which unlock an option to accuse Percival later on, and allows the player to justify their accusation
when challenged about it by referring to that conversation. But if the player doesn't eavesdrop on the
conversation, they won't have this knowledge, meaning they won't be able to make the accusation through that
route. Of course, there's nothing presented to the player at any point in the game which indicates that this
is a meaningful choice, because explicitly stating 'THIS IS MEANINGFUL' at that choice would completely break
any immersion that the player would have. If the player knows this information, they know that they know it,
so that option which requires this knowledge would be expected to appear. But if they don't know this
information, they don't know that this information was there in the first place, so showing them an unpickable
option later on (an option that they could have only thought of if they had the information in the first place)
anyway would not make sense.

The 'archives' branch, initially being conceptualized as a 'research wing' branch, stayed very much as first
planned. I did change a few specifics about the information that the player could read, however. If the player
opted to read the notebook, doubt would be cast on Dr Albert, allowing the player to accuse him later on. If
the player read it to the end, they would also have a reason to accuse Percival as well later on, unlocking
that option. If the player read the file about them, they could either find out a bit more about the experiment
(not unlocking any accusation options, but opening a branch of dialog when accusing Dr Spreewald), or about
their own situation (casting doubt onto Percival, when seeing Percival's name put onto the documents in a
suspicious way). The end of this branch, where the player dies thanks to Percival, is intentionally left
slightly vague. Depending on what the player had just read, it might seem like they had just died from
Percival's incompetence, however, if the player had picked an option that incriminated Percival, I wanted to
leave them with an impression that Percival had intentionally killed the player before the player could blow
his cover.

## The endings

I chose to have all 3 of the characters introduced so far to potentially be the culprit for the sake of
replayability. The thought process was that if a player played through it one time, and got the ending where
one character was the killer, if they were to replay it they would probably accuse the other potential culprit
first; instead of the rather boring method of that accusation of the other person simply being correct,
having both initial accusations be wrong, thereby making both lead to a completely different ending (or
converging in the Percival Ending) would probably be a pleasant surprise for the player. There is still
the problem of it feeling like a bit of a cheap trick to a player, however, I have established an explanation
for these events. It had already been established that, in the fiction of this game, the player goes through
several timelines. The 'fixed point' that these timelines all diverge from was the point at which the player
was given the serum (mentioned a few times throughout the game), between getting kidnapped and waking up in
the facility. The choice made by each of the potential antagonists to kill the player (or not) were all made
following that 'fixed point' in time. This means, through an overly contrived excuse along the lines of
Schrödinger's cat, all three of them are technically potentially the culprit at once, until the player
eventually realizes which particular timeline they are in. It also means that the character who the player
accuses first could be used in a role akin to that of the 'goddess' from the traditional Hero's Journey,
with the latter being usable in a role not entirely unlike that of the 'father' from that story structure.
Unfortunately, this is where this sort of story structure completely falls apart within *Backblast*, as
there is no real 'temptress', 'ultimate boon', or any of the post-apotheosis stages.

The 'main' ending was the one where the player accuses Dr Spreewald first. This is the only one of these
endings without any specific pre-requisites, so it's effectively the default ending. The player approaches
Dr Spreewald in the cafeteria again, this time to accuse him of being the culprit instead. In this timeline,
Dr Spreewald hadn't come up with his own agenda, meaning he had no reason to kill the player, but being doubted by
default because the player didn't currently have a reason to suspect anyone else. It also establishes that
the video watched when the player first died was set up by Dr Albert instead, in spite of what Dr Spreewald
had planned for the player. Additionally, if the player had seen the notes casting doubt on Dr Spreewald's
methods in this timeline, it's rationalized by those notes being there because Dr Albert thought that
Dr Spreewald was not going far enough. After the player realizes that accusing Spreewald was the wrong call,
they're then prompted to think of someone else to accuse. If the player had made the meaningful decisions
earlier on to cast doubt on Percival, accusing Percival will be an option; otherwise, accusing Percival won't
be an option. Accusing Dr Albert at this point is always an option.

In the latter half of this ending, I wrote it in such a way that clearly answers some key questions for the
player in a way that would only make sense if Dr Albert was the true culprit (explaining the '5-day placement'
in the form of 'living the same day 5 times', hinting at the biker cartoon being based on a 'true story', and
having Dr Albert justify his actions in a very self-righteous 'technically I haven't done anything' sort
of way). This ending also provides the cleanest resolution of them all (befitting its role as the
'main ending'), whereby the player simply has their memory wiped and ends up back home. This ending still
leaves a few questions unresolved, such as how the bombs were planted, with the intent being that the player
would replay the game to find that out, and ultimately get one of the other endings.

The ending where Dr Albert is accused first is somewhat darker in tone, and more closely resembles the initial
idea I had regarding Dr Spreewald's reveal as the villain. In this timeline, the concerns raised by Dr Albert
about Dr Spreewald were justified by it being revealed that Dr Spreewald was as nefarious as first conceived,
and that Dr Albert was waiting for some more concrete evidence before getting rid of him (evidence which the player
just provided by going to Dr Albert first). The question is raised about why Dr Albert's notebook was present
in the archives in the first place, which can be explained either by it being found by Dr Spreewald and put
there to incriminate Dr Albert, or by Percival doing it for some unknown reason (giving the player a reason
to potentially accuse him later on).

If the player then comes to the conclusion of Dr Spreewald being the culprit, because this ending establishes
that Dr Spreewald is secretly a very much results-driven, self-interested sort of person, his actions in
this ending don't seem to come out of nowhere. He figures out that the player is here, and they've worked out
his plan because they're not in any of the places where he would have been expecting them to show up, so,
knowing he has still already 'won', he sees no reason to play it safe, confronting the player as they attempt
to leave (as the player has no reason to confront him themselves). His plan is more of a stereotypical 'evil
villain' sort of plan, and is intentionally written in such a way that makes it unclear if is feasible or not.
His plan, being to give himself the same serum as the player was given, see if the player was able to 'come
back' from death, and promptly end his life in this timeline to go to another timeline where he is the only
person with this power/knowing about this power, is supposed to shock the player at how utterly nihilistic
his plan is, and to make them genuinely scared when he appears. Ultimately, despite him being armed, Dr
Spreewald opts to not kill the player, because that would be seen by the player as a very cheap 'you lose'
ending, and would not make sense in context. However, this being a hypertext game, I wanted to give that
option to the player, in the form of the spare bullet Dr Spreewald tells the player about/taunts the player
with. Most players may opt to simply leave the facility, leading to one of two endings providing some
much-needed levity (either leaving the facility but realizing they have no idea where they are, or asking
Percival where their stuff is leading to a potential spin-off in which the player and Percival try to work
out where the player's stuff actually is).

Some players may decide to use the gun to follow Dr Spreewald. Percival will discourage the player from
doing this if the player picks up the gun, as one would expect. If the player follows through anyway, the
following events are intentionally unspecified. I did not want to provide a concrete answer
as to whether or not Dr Spreewald's plan worked, because I wanted this question to linger in the mind of the
player. However, due to the revelations in the Subject A ending, the official answer is that his plan failed
either way, because Subject A also had that power already in all the timelines that Dr Spreewald could have
gone to, and that Dr Spreewald would have eventually become a victim of his own inability to die.

## The Percival Ending/Subject A Ending, and the eventual meaning behind *Backblast*.

This was the last ending I thought of, and was actually a massive retcon. Unlike the prior two endings,
where the player simply just needs to accuse one person then accuse the other person, this ending requires
a bit more effort from the player. At the point where these endings converge, Percival will ask the player
to explain why they're accusing him. If the player fails to successfully accuse Percival (by mentioning
the appropriate evidence they found, and noticing Percival saying something he shouldn't know), the player
will be sent back to the main 'ending path' which they were on. This is because this ending is written as
more of a 'hidden true ending' sort of route, therefore justifying more effort from the reader in order for
it to be reached.

I was initially not sure what motive Percival would have had to do the actions that he had performed in the
route leading to this ending, as Percival was never intended to be that sort of person. I had considered
having him be some sort of saboteur, but, again, I wasn't sure who would actively want to sabotage the
facility. It was this point I remembered that I had mentioned two previous test subjects, but they currently
had no prior relevance to the plot, however, from the moment when the player found themselves in the facility
and branded as Subject C, the existence of previous test subjects had been alluded to. I then considered the
'power' of effective immortality which the player had been given, which Dr Albert was trying to replicate,
and Dr Spreewald was trying to take for himself; as well as how that 'power' could turn out to be a 'curse'.
It was at this point when I realised just how terrifying it could potentially be to be completely unable to
die.

I know this is probably an uncomfortable topic for everyone, but I guess that everyone has to eventually
cease to exist eventually, into whatever is/isn't waiting for everyone outside of this reality. Sure, there
are many characters in fiction who are physically unable to die, and, in hindsight, *Undertale* does already
explore some of the implications of what would potentially happen if a character had the power of immortality
via the medium of what is essentially save scumming, but I started to consider what the implications would be
if someone were to keep going back to a fixed point in their life would be. In all fairness, it's honestly
rather terrifying, as, even though your body wouldn't be forced to keep going for infinitely longer than it
was built for, your mind would. What's worse is that no matter what happens, no matter what you've lived
through, reality would just keep infinitely resetting, so even if you wanted to use your immortality to do
something almost worthwhile (like insulting everyone who ever existed), you can't. You'd just be spending
eternity limited to one short blink of time. It would probably get rather boring eventually.

This was when I found myself reaching the obvious conclusion: Subject A wasn't dead. When he was experimented
on, he was given a serum that worked too well, making him a reluctant immortal, now completely bored out of
his mind; so bored that, instead of dying to a falling light fixture again, he decided to 'accidentally'
cause someone else to die in that way, allowing him to fake his death and take the place of that other person.
This would explain why Percival would have been doing this incredibly out-of-character action: because he
never was Percival in the first place.

But why? Why would I present this to the player? Because I wanted to fill the player with a sense of genuine
existential dread. So far, this game has been a bit of a power fantasy for the player; they can't be killed,
so there isn't really anything that the player can actually fear. Besides, of course, the fear of being unable
to die. I will also admit that, in the past, there have been times when I've considered ending it all (the
main thing that's stopped me from doing it is because I know it would just be a massive inconvenience for
everyone else), and I guess that one of the worst outcomes that I could have been met with in those situations
would have been continuing to exist in this way. Once the player thinks about it (and this ending will make
the player think about it), *Backblast* is a horror game. Man vs a complete inability to escape the known.
etc.

I chose to use this ending to also explain why, when the player 'dies' in the main loop, they keep being
revived at the main loop, instead of all the way back in the room. The reason for why I designed it in that way
was for the convenience of the player, so they wouldn't have to keep meeting Percival and going to the briefing
room and throwing the projector out of the window and convincing Percival to work with them every single time
they went through this 'main loop'. I justified this via explaining that, whilst Subject A was stuck reliving
eternity from a fixed point, the player's 'fixed point' was gradually creeping forward, therefore justifying this
gameplay design choice within the fiction of the game. It also somewhat justifies the 'point of no return'
passages that appear occasionally, as those could also be interpreted as 'fixed points' of sorts, even if the
player is regularly sent back to before that 'point of no return'. This also ties in to Subject A's motivation
for wanting to kill the player in this ending; upon the player dying in one reality, it's established that the
player's mind is sent back in time within a different reality, taking the place of the player's mind in that
reality. Subject A sees himself as trying to 'save' the player from the curse of immortality by killing the player
in one reality, causing the version of the player in the other reality to get 'overwritten', and therefore 'saved'
from being unable to die.

However, to Subject A, the player is still one of infinitely many iterations of one of the 234 people who ever
could be 'Subject C', ultimately meaning that the wellbeing of the player isn't actually high up on Subject A's
list of priorities. Subject A had mentioned that the player's 'fixed point' keeps moving forward in time, and
did pose the question about what would happen if the player's 'fixed point' were to move to a point where the
player is just about to die anyway. At first, I had considered leaving that question as a hypothetical, and
making this ending 'end' by Subject A destroying the facility, without the player having a way to stop him,
and ending with everything fading to white. But that would be rather unsatisfying, and I could also answer that
question. So, after Subject A concluded the exposition/providing the existential dread, he would still attempt
to detonate the facility. The player would have the option to just let him do it, fight him (ultimately failing),
or try to sneak after him to manually override it. I wanted the player to approach this choice with a mindset of
'even if he wins, I will just come back to before this mess started', not worrying at all about it.

This is where I would (metaphorically) pull the rug out from under the player's feet, by revealing that their
'fixed point' had already moved forwards to the point in time when Subject A had just started to set off the
self-destruct sequence. This would give the player a reason to want to stop Subject A, as, if they don't stop
him, the player would be stuck within these 5 minutes or so for eternity, constantly being killed by a genuinely
bored immortal who doesn't care. This more closely resembled the initial vision I had for this game, at least
in terms of how the player would need to stop themselves from dying before a given deadline. Whilst Subject A
does show some semblance of pity for the player, by letting the player have a chance to not die if the player
could correctly 'guess' what hand he was apparently holding a piece of paper in, I still wanted to make it very
clear that Subject A genuinely didn't care about the player, through Subject A very clearly 'cheating' by
not giving the player an option that is correct. You could argue that this is just a rehash of the wire-based
shenanigans from 'Countdown', and I would admit that, yes, you would be correct. However, whilst in that game,
there is no actual justification within the fiction for that, in this game, there is a justification in the
fiction, and serves the purpose mentioned a few sentences ago.

Subject A calling out the player for 'cheating' at the end of this ending is, once again, Subject A showing his
true colours as someone who still hates to lose, despite basically already having lost everything, and still
ultimately seeing himself as superior to the player. He still allows the player to override the self-destruct,
because, at this point, he's had enough of this timeline anyway, and was planning on seeing himself out whilst
the player was preoccupied trying to find the override button. The text when the player is trying to press the
button was written in a way to just emphasise how desperate to override it the player should be feeling at this
point in time, not bothering with any longwinded descriptions of what the player can see, just blindly and
frantically hitting buttons in the hope of pressing the correct one. This would ultimately lead to the player
starting to panic a bit when the countdown reaches 1, and then panicking even more when the description of the
bright light appeared.

In this instance, the bright light was just the lights in the room turning back on after the override had
successfully been averted, as some sort of 'light at the end of the tunnel'. Subject A had served his purpose
in the game, so I opted to get rid of him at around this time (officially, he killed himself whilst the player
was busy trying to override the self-destruct sequence, thereby meaning that the player would have been freed
from his influence for now on). This ending had then done everything it needed to do, it had given the player
a few answers to the questions about what was going on, given the player a healthy dose of existential dread,
and presented a more 'genuine' challenge to overcome. So I opted to end it here, with the player having an
opportunity to reflect on what just happened, and to reflect on the revelations that had been made in this
ending.


# Conclusions and such

Do I think *Backblast* a good game? To be honest, I'm genuinely not sure if it even qualifies as a game. However,
I guess it might still linger in the mind of the player for a bit after they're done playing it.

It still has a lot of wasted potential, and I would say that the main problem with *Backblast* was the lack of
planning I gave it, mostly just playing it by ear. In fact, it's almost like everything before and after the
'break' I took from writing it were completely different games, because, whilst everything after the break was
planned out to some extent, I was just playing it by ear in the former half.

The biggest problem with it is still the overall linearity. The only three real branches were the 'saying no'
ending, the 'joke' ending, and the three other endings are all still one branch in the great scheme of things.
This means that there were only 5 truly meaningful choices that the player got to make throughout the game.
However, I just couldn't really think of anywhere that looked like a suitable candidate for somewhere to put
a meaningful choice. 

Another problem with it was with the 'noreturn' passages, and how, to the player, they would be seen as
completely unnecessary and rather frustrating. I have tried to reduce the number of them throughout the game,
but I guess that reducing the count of them won't really fix the problem of them being a bit too prominent.
But the biggest problem with them is probably how they are used to just hide the meaninglessness of some choices.
For example, if I didn't use them at the point where the player always gets the first accusation wrong, it would
just make it look even more like a cheap trick to artificially add more content to the game.

The theme of death and existential crises did also start to get a bit overbearing by the end of the game as well,
the idea of following the Hero's Journey went completely out of the window, and the only character to get any
real opportunity for character development was Percival, but that amounted to nothing because his character was
completely discarded by the 'Subject A' ending.

I guess that the original idea I had for the game probably would have worked better than the idea that was
used in the end. At least that idea would have properly delivered on the branching content, the 'mystery'
aspect, and on the timeloops.

So, to conclude, I genuinely do not think I can call *Backblast* a success in any way, shape, or form. The worst
part is that I'm not sure if it can be salvaged, due to the problems with it being inherent to the game itself.
Ideally, I would aim to just scrap this and start anew with a new concept, but I don't have enough time left
before the deadline to do this. I should have recognized these problems sooner, and dealt with them sooner,
instead of only recognizing them now.

# An overview of *Backblast*'s passage map, as shown by OH-HECC.

![backblast overview](./final/backblast%20overview.png)


# Citations

[1] Spike Chunsoft, Minato City (Tokyo), Japan, *Zero Escape: The Nonary Games*, 2017 (2009-2016). [PC].

[2] J. L. Borges, *The garden of forking paths*. Buenos Aires, Argentina: Editorial Sur, 1941.