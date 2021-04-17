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
is not an option for the player, and that 'fight' (getting rid of the bomb)
is the only feasible option they have.

If the player opts to fight or hide from Percival, they will always fail. These
options are here because, after the player got abandoned in the briefing room by
Percival, the player may be feeling somewhat distrustful of him, and seek an
escape from the bomb by escaping the person who brought them to it. However,
Percival is the sort of character who, upon being given a job to do, will do it,
and his job at this point in time is 'get the player to the briefing room'.
The failures if the player chooses these options is supposed to make the player
realize that, to survive, working against Percival isn't an option, so they
will need to give Percival a reason to help them.

The option to greet Percival instead is here to give an opportunity for the
character of Percival to become a bit more fleshed-out (by giving the player
an opportunity to interact with that character), 





# Citations

[1] Spike Chunsoft, Minato City (Tokyo), Japan, *Zero Escape: The Nonary Games*, 2017 (2009-2016). [PC].

[2] J. L. Borges, *The garden of forking paths*. Buenos Aires, Argentina: Editorial Sur, 1941.