# *Backblast*

First things first: obligatory spoiler warning for Backblast.

If you have not played Backblast yet, you can either download it from [this repository](../game/Backblast.zip),
or you can play it on my itch.io page, at [https://11belowstudio.itch.io/backblast](https://11belowstudio.itch.io/backblast).
(If I haven't gotten around to making the page public yet, the password you need to enter to see
it will be `301`).

With that said and done, time to discuss the development of it.

# The initial idea

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
more about the facility/establish that things are definitely different
(by acknowledging the hat), or have some other interaction with Percival
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
options available to them, leading to some more information about what's going on and the player's
brutal demise. Once all of those 'paths' had been explored, the player would then know enough to be able
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



# Citations

[1] Spike Chunsoft, Minato City (Tokyo), Japan, *Zero Escape: The Nonary Games*, 2017 (2009-2016). [PC].

[2] J. L. Borges, *The garden of forking paths*. Buenos Aires, Argentina: Editorial Sur, 1941.