//HECC UP output (as of 29/01/2021) (R. Lowe, 2021)

var startingPassageName = "Start";


function getHECCED(){

	theHeccer.addPassageToMap(
		new Passage(
			"Start4",
			"You open the envelope, and the paper inside it feels similarly expensive.\n\nThe text on that envelope is, again, entirely handwritten, from the line at the top addressing you by name, to the signature at the end.\n\n*Greetings. If you are reading this, we at the Institution for Practical Parallelization (the IPP) are offering you the possibility of helping us out with some of our research into the practical applications of theoretical parallelism in a wide range of use cases, from the simple 'gaining knowledge faster', to the more complex tasks of understanding the outcomes of decisions before they're made. And you have been identified as an individual who has potential to do great things, for both yourself, and for society as a whole.*\n\n*Now, I can't get further into details at this point in time, as all of the really juicy information is hidden under layers after layers of NDAs and legislation and paperwork and all of that jazz. Even telling you about the very existence of this institution is in a legal grey area*\n\n*What I can tell you is that we are offering you a 5-day placement here. You will be staying at the institution during this placement, but no expense has been spared on the room and board. Yes, you will be expected to work, but I can assure you that it'll be less soul-destroying than the sort of work you already do in your day-to-day life. And, to sweeten the deal, we will compensate you for your time; you will be paid a healthy sum of money, and you may get the opportunity to help us out further in the future.*\n\n*Regrettably, we cannot give you any further information, and we can't even tell you when this study will be, due to the aforementioned legal problems*\n\n*If you do want to participate in this study, please use the included signed addressed envelope to send us your response. All we need is a single-word 'Yes' or 'No'.*\n\n*Thank you for your attention, and we hope for your participation*\n\n*- Dr. A. Albert, OBE*\n\nYou are taken aback by the somewhat unexpected contents of this letter.\n\nYou re-read it several times, to make sure you read what you thought you read.\n\nBut those words are all present on there.\n\nOf course, you have no reason to believe anything on this piece of paper.\n\nBut why would anyone go through all this trouble for a simple 'yes' or 'no'?\n\nAnd the payment sounds like a nice bonus, especially considering those bills piled up by your doorstep.\n\nEverything seems legit.\n\nBut how are you going to reply?\n\nAre you going to reply with a [[Yes|StartYes]], or are you going to send a [[No|StartNo]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"StartNo",
			"You think about it for a bit, but the offer seems a bit suspicious.\n\nAfter all, it smells a bit pseudoscience-y, and the limited information you have makes you think that this might be a front for some sort of cult.\n\nAnd if there's one thing that you don't have the energy to deal with, it's getting caught up in some cult shenanigans.\n\nSo, in the most respectful manner possible for a single-word response, you write a rather large 'NO' on a piece of paper you then put in the provided envelope.\n\nLater on that day, you put the envelope into a postbox, and send it back from whence it came.\n\n[[And that's the end of that|Ending1]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Start2",
			"The noise was just the sound of some post getting shoved through your letterbox.\n\nA shower of junk mail gets carelessly pushed through, forming an unsightly puddle of advertisements for services you don't need on your floor.\n\nThis is promptly followed by some less useless but still not exactly wanted envelopes containing bills and such. You don't even need to open them to know they're bills, because of that distinctive 'if undelivered return to' text on the back of the envelopes, and the windows on the front of the envelopes to the automatically generated addressee information on the automatically generated documents they contain.\n\nBut there is one last envelope, stuck in the flap of the letterbox, above the puddle of mediocrity.\n\nAn envelope with an entirely handwritten name and address.\n\nBut it's not handwriting from anyone you recognize.\n\n[[You cautiously pick up the envelope|Start3]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Start3",
			"The envelope feels expensive. It feels like the sender really wanted to send you this, and get your attention.\n\nYou turn it around to try opening it.\n\nYou notice some handwritten text on the rear of the envelope saying '**PRIVATE AND CONFIDENTIAL**'. And where a bill would say where to return the envelope if it's undelivered, this instead says '**IF UNDELIVERED, PLEASE INCINERATE THIS ENVELOPE**'.\n\nYou turn it around again to make sure that it definitely *is* addressed to you.\n\nIt is.\n\nSomeone clearly wants you to receive this letter, and clearly doesn't want anyone else to read it.\n\nSo you [[open the envelope|Start4]], and see what all the fuss is about.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu Follow Percival",
			"You decide that it would be safest to stick with Percival, seeing as he has a vague idea about where he's going, and you don't want to get lost.\n\nThe two of you continue walking down the same sort of nondescript hallways as last time. No signs, no windows, no sense of direction, just endless unmarked doors.\n\nPercival makes the same remarks as he made earlier on regarding cost saving measures and things getting lost.\n\nInstead of making the journey in silence this time, do you want to [[find out what Percival knows|deja vu askPercival]]?\nOr do you want to just [[focus on working out the layout of this facility|deja vu silent treatment]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"It's an ordinary morning for you.\nNothing wacky and/or uncharacteristic, just the same stuff you usually experience.\n\nOver the radio, you can hear a discussion about the Mandela Effect.\n\nSuddenly, there's a noise at the door.\n\nDo you [[investigate it|Start2]], or do you [[keep listening to the radio|StartMandelaEffect]]?",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu percival leave",
			"\"Now, where was I...\"\n\nHe glances at his clipboard.\n\n\"Ah, yes. So, Subject C, If you would just follow me, I'll take you to the briefing room where things can be explained to you in a bit more detail.\"\n\nYou shudder a bit, after what happened in that briefing room last time. However, this time, it doesn't sound like Percival's going to force you to go with him. You don't think he'd listen if you were to attempt explaining what happened last time though, as it doesn't like like he's aware of there even being a 'last time'. But, at very least, you think you know what to do this time.\n\nWhat are you going to do?\nCooperate, with a [[\"Lead the way, Percival!\"|deja vu Follow Percival]].\nPretend to cooperate, but [[run off|deja vu run away]] ASAP.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu whens lunch",
			"You decide that asking when lunch is would be the best question to start with. May as well start with the easy questions so he lowers his defences, before giving him the really big questions.\n\nHe glances at his clipboard, muttering to himself \"Let's see here... lunch, lunch, lunch... when is it again... Aha!\"\n\nHe looks back at you, answering with \"Says here that you'll get lunch after we're done in the briefing room.\"\n\n*We're* done? That sounds different. Last time, he just left you in there.\n\n\"Speaking of which, looks like you won't need to wait that long for your lunch.\"\n\nAs he says this, he suddenly stops in front of a door. A door labelled with the words [[BRIEFING ROOM|BriefingRoom2 - entry]].",
			["noreturn", "lunch"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"helloSusan-alreadySaw1",
			"{if:pAny(\"deja vu been there already\")}{Percival interjected.\n\n\"I already told you that you haven't seen it yet!\"\n\n}Susan let out a sigh.\n\n\"So, what exactly do you mean by that?\"\n\n\"[[Well, I woke up in a weird room, got sent to the briefing room, saw the video, then something weird happened, and I woke up back in that weird room again...|helloSusan-somethingWeird]]\"\n\"[[It means I already watched it, and when I watched it the first time, it pretty much told me I was supposed to have thrown it out of the window.|helloSusan-videoSaidSo]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"plans and such 1",
			"Sample Content",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2 - in",
			"You walk into the briefing room, followed by Percival.\n\nHe closes the door behind him, as you [[look around the room|BriefingRoom2-1]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"plans and such 2",
			"sample content",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"elsewhere-4-MeetPercival",
			"\"Hello, subject C.\"\n\nThe very nasal voice of the person greeting you takes you aback. It didn't sound quite like that from the other side of the door.\n\nThey're dressed up in the sort of garb you'd expect some sort of stereotypical caricature of a health-and-safety inspector. A cheap-looking black suit, like the sort of suit worn by someone who takes themselves way too seriously, with overly-polished shiny black shoes, yet contrasted by a very bright high-vis vest. They appear to have an ID badge on their high-vis vest, but you can't read it from where you are. You can see a painstakingly manicured, yet somewhat subtle, moustache on their face, as well as a pair of browline glasses in front of their eyes. Those glasses could have given off an air of quiet sophistication, if it wasn't for the rest of their outfit, and that somewhat gratituous pair of cheap-looking safety spectacles that they're wearing over their glasses. They're also wearing a white hard hat on their head, with the letters 'IPP' written on the front of it. They're cradling a clipboard with their left arm, and they have a fountain pen in their right hand.\n\nThey clearly work here, and they're clearly here to take you to the briefing room mentioned in that letter from earlier.\n\nAnd they're clearly expecting you to say something.\n\n[[\"Who the hell are you?\"|MeetPercival-whois]]\n[[\"Where am I?\"|MeetPercival-whereis]]\n[[\"Hello? Room service?\"|MeetPercival-RoomService]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"plans and such 3",
			"sample content",
			["name", "pending"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu hide",
			"You open the door to what you hope is an en-suite.\n\nTo your surprise, it actually is one.\n\nUnfortunately, it looks like Percival's worked out that you're in there as well.\n\n\"Subject C, would you mind hurrying up in there? We have a full schedule today, and the sooner you get out of there, the better.\"\n\nPercival's voice sounds oddly calm for the voice of someone who recently took you to what felt like a near-death experience.\n\nAre you going to respond to him?\n\n[[\"Just give me a few minutes, Percival, I'm on the toilet\"|deja vu hide give up]]\n[[Say nothing. Hope that he goes away.|deja vu hide gas]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"StartYes",
			"After thinking about it for a bit, you eventually decide to accept the offer.\n\nAfter all, that's a lot of money they're offering.\n\nSo, in the most restrained manner possible for a single-word response, you write a rather large 'YES' on a piece of paper you then put in the provided envelope.\n\nLater on that day, you put the envelope into a postbox, and send it back from whence it came.\n\n[[And now it's time to wait for a response|StartYes-2]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu run away",
			"You follow Percival out of the room, into the same nondescript hallways as earlier.  You decide to wait until Percival turns around a corner before running away in the other direction.\n\nEventually, you reach a 4-way junction.\n\nPercival goes down the left corridor. \n\nYou can't help but think that as soon as you start running, you won't be able to go back. \nPercival's calling for you, with a \"Subject C?\".\n\nWhere do you go?\n[[Actually, I'll stick with Percival.|deja vu Follow Percival]]\n[[Straight ahead!|deja vu maze 01]]\n[[To the right!|deja vu maze 02]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"StartYes-2",
			"It's an ordinary morning for you.\nNothing wacky and/or uncharacteristic, just the same stuff you usually experience.\n\nOver the radio, you can hear a discussion about some hoax letters people have been receiving.\n\nSuddenly, there's a noise at the door.\n\nDo you [[investigate it|StartYes-3]], or do you [[keep listening to the radio|StartYes-Hoax]]?",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom1-tryTheDoor",
			"You rush to the door.\n\nBut, just like the door you used to leave the bedroom, there doesn't appear to be any way for it to be opened from the inside.\n\nThere's no signs of anyone outside, and, try as you might, the door won't budge.\n\n[[But the film continues to play|BriefingRoom1-5]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"StartYes-3",
			"The noise was just the sound of some post getting shoved through your letterbox.\n\nExcept instead of the usual deluge of junk mail, there's a strange grey cylinder.\n\nAnd there's a lot of [[smoke|StartYes-4]] coming out of it",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"StartYes-4",
			"The smoke fills the room.\n\nBefore you know it, it fills your lungs as well\n\n[[And everything fades to black|movedElsewhere]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"helloSusan-bomb",
			"The room is filled with a stunned silence, quickly broken by Susan.\n\n\"Wait, what was that you said about a bomb?\"\n\nYou explain that {if:pAny(\"helloSusan-alreadySaw1\")}{you watched the video already, and }{else:when you saw the video, }it ended with something along the lines of 'I was supposed to have thrown the projector out of the window', before a large flash came from the projector and you found yourself back in that room.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu endless corridor",
			"The corridor continues. {if:pAny(\"deja vu endless corridor\")}{You start to suspect that it might never end.}\n\nWill you [[turn back|deja vu endless give up]]?\n\nOr will you [[carry on|deja vu endless corridor]]?",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-whoThat",
			"Percival looks at you with a look of some concern.\n\n\"That was my boss.\"\n\nHe looks like he's perfectly aware that he's in a bit of an awkward situation. {if:pAny(\"deja vu been there already\", \"deja vu new hat\")}{However, on the positive side, perhaps his boss knows a bit more about what's going on than he does.}\n\n\"[[Well then, Percival, I suppose you'd better come up with an excuse|BriefingRoom2-departure]].\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"helloSusan-somethingWeird",
			"This clearly piqued her curiousity.\n\n\"So what exactly do you mean by 'something weird'?\"\n\n\"[[Well, I'd say that the projector turning out to be a bomb was kinda weird.|helloSusan-bomb]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"helloSusan",
			"You enter the room and are greeted by a somewhat boring-looking office, with a desk in the middle of it, and a strong whiff of coffee. Around the office, you can see a few bookshelves full of folders, a few motivational-style posters glorifying the benefits of coffee, and a rather dated-looking desktop computer. There's also a particularly conspicuous lack of any natural light here as well.\n\nBehind the desk, you can see a middle-aged woman in a slightly disheveled-looking suit, and hair which looks like it's starting to become slightly unkempt, who has very noticable bags under her eyes, probably caused by overwork. She looks like how you'd expect someone who's relatively important enough to get her own office and is pretty much at her wit's end to look. You think that this probably is Susan. And she's talking to you.\n\n\"So, would you mind explaining to me what that was all about?\"\n\nHer voice is nowhere near as painful as Percival's voice. However, it's clear from the tone of voice that she's a bit confused about what you just did, albeit less so than Percival, but ultimately sounds disappointed, as if the events of a few minutes ago are yet another problem on top of an existing backlog of problems. Perhaps it might be worth trying to explain what happened to you.\n\n\"[[I was under the impression that you wanted me to get rid of that projector|helloSusan-yeet1]].\"\n\"[[I saw the briefing already, but I haven't quite gotten as far as this point, so I'm not entirely sure what you're expecting me to say here|helloSusan-alreadySaw1]].\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom1-5",
			"*\"Thing is, if you were going to be of any use to us, you would have done something else instead of sitting around and watching this cartoon. Oh well.\"*\n\nYou're taken by surprise at that almost-instruction.\n\n*\"But I suppose that those bikers had to get caught in the crash in order for them to be warned about it, didn't they?\"*\n\nBefore you can decipher that statement, you notice a very bright, very warm, flash coming from the projector.\n\nHeading straight towards you.\n\n[[And everything fades to white|movedElsewhere]].",
			["noreturn", "death", "BriefingRoomDeath"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu-2",
			"Knowing your luck with people knocking at your door and then weird things happening, you come to the conclusion that you'd rather not deal with any more shenanigans from visitors.\n\nBut this time, you think you know what to expect.\n\nYou hear Percival's voice from the other side of the door again.\n\n\"Subject C, I know you're awake and in there, I'm going to open the door.\"\n\nYou hear the noise of a crank being turned, and some bolts getting unbolted.\n\nThe door is slowly opening.\n\nDo you:\n[[Hide in the en-suite|deja vu hide]]\n[[Attempt to ambush Percival|deja vu ambush]]\n[[\"What is it this time, Percival?\"|deja vu what is it]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom1-4",
			"The voiceover kept talking, this time feeling a bit more direct than it did previously.\n\n*\"We are performing quite a bit of research into the feasibility of performing such a feat in reality, and you have been identified as a person of interest in this search, which is why we have summoned you here.\"*\n\nYou find yourself feeling rather confused, because you're not entirely sure if there's been any times in your life where you have done anything even close to the sort of thing being discussed earlier on.\n\nThe voice shifts to a somewhat more apologetic tone, which catches you somewhat off-guard.\n\n*\"Regrettably, in this search, sacrifices may have to be made, so, if the worst happens, you have our condolences.\"*\n\nThe projection shifts to some cartoon mourners, on cue with the narration.\n\nPart of you thinks that it might be worth [[trying to escape|BriefingRoom1-tryTheDoor]]. But part of you thinks [[it's probably nothing to worry about|BriefingRoom1-5]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu PedantryWeekly",
			"You can see Percival's eyes light up with excitement.\n\n\"**YES!** Finally, my achievements have been recognized!\"\n\nHe appears to have fallen for your bluff. Almost *too* well.\n\nEither way, he doesn't look like he's going to press the issue any further. And you highly doubt he'll even want to.\n\nBut he looks like he's getting [[back to the task at hand|deja vu percival leave]]",
			["pedantic"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom1-3",
			"*\"After 1 mile, they got into a horrific traffic accident. 90 of those bikers lost their lives. And nobody could have predicted it.\"*\n\nThe cheery music stopped dead, and the scene changed to a graveyard, with 90 graves. There was an uncomfortably long pause at that scene, where the video was clearly still playing, but was lingering, in silence, almost as if it was offering a moment of silence to the memory of those deceased imaginary bikers.\n\n*\"But, what if one of the bikers, after getting caught in that accident, could have gone back and warned the others about it, thereby saving 90 lives?\"*\n\nThe scene shifted back to the discussion the bikers were having, and one of them was suddenly acting very agitated, very enthusiastically miming a warning about the stuff that was going to happen if the bikers were to go down the left road.\n\n*\"What if they had been given this forewarning, and had all gone down the right road instead?\"*\n\nThis was accompanied by the same animation from earlier of the bikers getting back onto their bike, and heading down the right road.\n\nAt this point, you start to wonder if you somehow managed to find yourself in a stereotypical cable TV pseudoscience documentary.\n\nBut, once again, your train of thought was interrupted.\n\n*\"But I know you're not interested in this theoretical malarkey, you're interested in some practical examples, aren't you?\"*\n\nOn cue, the projection started to show a large hand, pointing towards the viewer, towards you, to make it obvious that the narrator was, in fact, trying to talk to you.\n\n*\"Yes, Subject C, you.\"*\n\nYou feel a slight feeling of discomfort at this revelation that you apparently are the intended audience for the next part of this recording.\n\nYou braced yourself, [[preparing for what was to come|BriefingRoom1-4]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"StartMandelaEffect",
			"The guest on the radio show is talking about how people may misremember things, or remember things happening that didn't happen. For example, remembering the funeral of Nelson Mandela after he supposedly 'died in prison', when he didn't. Or remembering seeing a cornucopia on the labels for a certain fruit-related brand of clothing, when there never was one. They claim that the reason for this is something to do with people somehow travelling between parallel universes, from one where the thing that didn't happen did happen, to 'our' universe (where it didn't happen).\n\nYou quickly lose interest in the conversation, because it sounds like a very contrived excuse to defend one's pride when misremembering a minor detail. Anyway, the conversation's ended, and it's now the ad break.\n\nYou may as well [[see what that noise was|Start2]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom1-2",
			"You sit down and look towards the projection.\n\nThe text is replaced by a title screen of sorts for something that looks like a rather old, sepia-toned, edutainment film, on the subject of *Understanding What May Happen and Ensuring The Right Things Happen*, accompanied by the sort of cheesy badly-preserved orchestral score you'd expect to hear alongside an old cartoon.\n\nThe scene quickly shifts to a cartoon of some bikers riding their bikes across a road.\n\nA somewhat tinny voiceover, with an american newsreader-y accent, started speaking.\n\n*\"Suppose there was a group of 100 bikers, travelling together, as they normally would, down a highway. When, suddenly...\"*\n\nThe cartoon bikers suddenly ground to a halt, with a very exaggerated braking motion.\n\n*\"...they reached a fork in the road!\"*\n\nThe viewpoint suddenly shifted to the bikers' collective perspective, offering a view of a junction in the road, with a road sign pointing to the left saying 'This-a-way', and another road sign pointing to the right saying 'That-a-way'\n\n*\"Now, being a tight knit group, the bikers figured it would be best to work out as a group whether they wanted to go this-a-way, or that-a-way. After all, they wanted to stick together, and they could only do that if they all took the same route as each other.\"*\n\nIn time with the voiceover, the camera went back to the initial perspective, depicting all the bikers suddenly getting off their bikes and then having a civil discussion with each other.\n\n*\"Eventually, they all decided to go this-a-way, and then they hopped back onto their bikes, and were on their merry way, going this-a-way.\"*\n\nJust like before, they hopped back onto the bikes and resumed biking along, down the left route. [[The camera didn't follow them|BriefingRoom1-3]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu-1",
			"You try to remember what just happened.\n\nDidn't you already wake up here?\n\nBut what was that about with the briefing room? And that film?\n\nYour mind starts racing, trying to comprehend what happened to you.\n\nYou hear someone [[knocking|deja vu-2]] on the door, and a muffled, familiar voice calling for a \"*Subject C?*\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom1-1",
			"You step into the room, and you hear the door slam shut behind you.\n\nOn the positive side, you are free from having to listen to Percival's voice.\n\nOn the negative side, you are now all alone again, which makes you think that something's not right.\n\nThe room has many rows of tables, with benches behind them, looking somewhat like a lecture hall.\n\nThe rear wall, behind those tables and benches, has a large window, overlooking an empty warehouse-like room.\n\nOn the front most desk, there's a projector, projecting some text saying '**YOUR ATTENTION, PLEASE**' onto the front wall.\n\n[[May as well find out what all the fuss is about.|BriefingRoom1-2]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MeetPercival-whois",
			"Their expression suddenly brightens, as if they were hoping for you to ask that question.\n\n\"Yes! Finally, I, the great Percival, he whom files the files and lists the checklists, have finally been seen as important enough to be worthy of getting asked who I am by one of the test subjects! I shall finally be able to make the dramatic introduction I have always sought to make!\"\n\nAs he says this, he jumps in an expression of joy, looking like he's just checked off something on his bucket list. But at least you now know who this person you're talking to is. But your train of thought is interrupted, as you hear his nasal voice pipe up again, as realizes that he has a job to do.\n\n\"Ah. I've just blown my chance to make a dramatic introduction, haven't I? Great.\"\n\nYou can very clearly hear the disappointment in his voice.\n\n\"Yes, my name is Percival, and I'm one of the people who work here at the Institution for Practical Parallelization\"\n\n[[\"So why exactly are you here, Percival?\"|MeetPercival-Explained]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu Hello Again Percival",
			"\"So, how **did** you know my name? I don't remember actually speaking to you before, Subject C. You've only just arrived here, and you've only just woken up, and I definitely don't remember meeting you at any point in the past...\"\n\nYou feel just as confused as Percival is feeling at this point in time. You could have sworn that you just met Percival earlier on, and he took you to that briefing room, and you then woke up back here.\n\nPercival's probably starting to smell a rat. Quick, think of an excuse!\n[[\"I subscribe to Pedantry Weekly and they had an entire article about you, Percival, so that's how I knew.\"|deja vu PedantryWeekly]]\n[[\"You just sounded like the sort of guy who would be called Percival\"|deja vu soundsLikePercival]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"helloSusan-videoSaidSo",
			"Susan looks at you with some concern.\n\n\"The video was supposed to be on some simple health and safety stuff. And, even if you did somehow see it already, I highly doubt that it would have started telling you to start throwing projectors out of windows.\"\n\n\"[[Well, the video ended by telling me that I should have thrown it out of the window instead, probably because it was a bomb.|helloSusan-bomb]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-KO",
			"You wake up slumped over a desk.\n\nLooks like, once again, you've been knocked out and moved elsewhere.\n\nIn front of you, in his signature cheap black suit, high-vis jacket, and yellow hard-hat combo, is Percival. You think that something looks a bit off about his clothing, but you're not entirely sure what. You take a look at his name badge, just to make sure that this person in front of you is Percival. He is.\n\nAnd he's very annoyed.\n\n\"Did you *really* need to do that? Do you have **any** idea how hard it is to carry people, especially carrying them from one end of this facility to the other?\"\n\nYou notice his red face, that expression of exhaustion, and all the sweat dripping down his forehead, so he's probably not lying. It's not a pretty sight, so you try to forget that you noticed it, whilst he sighs and continues.\n\n\"Anyway, I went to your room to escort you here, to the briefing room, so you can find out what you're supposed to be doing here. I'll just give you a moment to wake up properly before the briefing starts.\"\n\nAs he says this, he leans against a nearby wall and collapses a bit, still trying to recover from what was probably more physical exercise than he's had to do in years, muttering complaints to himself about why he was expected to carry you here, instead of being allowed to get someone else to take you here.\n\nYou [[take a look around the room|BriefingRoom2-1]], to get your bearings.",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu leaving toilet",
			"You flush the toilet, wash your hands, and dry them off.  \n{if:pAny(\"deja vu didnt flush\")}{You hear a sigh of relief from the other side of the door.}\n\nYou open the door.\n\nOn the other side of the door is, once again, Percival.\n\nUnlike last time, his pose allows you to see his ID badge clearly, and it clearly says 'Percival'. But you can't help but think that something looks odd about his clothes this time. Something looks different, but you're not sure what. Was he really wearing that cheap black suit last time? What about that high-vis vest? Or that yellow hard-hat?\n\nYour train of thought is, again, [[interrupted by Percival|deja vu Hello Again Percival]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MeetPercival-ToTheBriefingRoom",
			"You follow Percival as he guides you through the facility.\n\nThere isn't much to look at here. All the corridors look identical, and there aren't any signs on the walls to offer any sense of direction, or any windows to offer any sense of location.\n\n\"It's because of cost-saving measures, you see\", says Percival.\n\nThis doesn't make any sense, considering the very generous reimbursement this institution was offering you in return for your participation. Then again, that money had to come from somewhere.\n\nBut the thing that really doesn't make any sense is the route Percival's taking you.\n\nWhy has he taken 5 90-degree left-hand turns in a row?\n\n\"I'm not lost, you see, it's merely a measure to ensure that some things remain lost\", remarks Percival, as if he sensed that you were about to ask.\n\nThe rest of the journey is made in silence.\n\nEventually, you reach a door with the words '**BRIEFING ROOM**' on the door.\n\nPercival ushers you [[into the room|BriefingRoom1-1]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu maze end",
			"Eventually, you notice a complete lack of doors on the walls.\n\nThe corridor continues for what seems like forever.\n\nBut you know you just have to [[keep going|deja vu endless corridor]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Ending1",
			"It's an ordinary morning for you.\nNothing wacky and/or uncharacteristic, just the same stuff you usually experience.\n\nOver the radio, you can hear a discussion about some hoax letters people have been receiving.\n\nSuddenly, there's a noise at the door.\n\nDo you [[investigate it|Ending1-2]], or do you [[keep listening to the radio|Ending1-Hoax]]?",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu ambush 2",
			"As you're preparing to beat him up, Percival casually turns around, to double-check that he actually was at the right room.\n\nAfter all, he is Percival. The man who follows every single instruction to the letter and never leaves any check on a checklist unchecked. Could he, Percival, have somehow made a mistake somewhere?\n\nHis eyes widen as he inadvertently notices you approaching him to beat him up.\n\nHe screams a very nasally scream, in panic.\n\nYou scream, panicking because he wasn't supposed to have seen you.\n\nHe panics, trying to defend himself.\n\nYou panic, trying to follow through on your planned ambush.\n\nHe fumbles around his high-vis vest and digs up a small black and yellow device, making a distinctive *zzzzt* noise.\n\nYou launch yourself at him, trying to take him down before he can fully comprehend what's happening.\n\n*zzzzt*\n\n[[Everything fades to black|BriefingRoom2-KO]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu what is it",
			"The door suddenly stops opening.\n\nYou can hear a very audible expression of surprise from the other side of the door, and the noise of a clipboard being dropped.\n\nThis is promptly followed by the noises of the person on the other side picking up the aforementioned clipboard and trying to regain their composure, before opening the door very quickly with a rather loud, rather surprised, very nasally \"How the-!?!?\"\n\nOn the other side of the door is, once again, Percival.\n\nUnlike last time, his pose allows you to see his ID badge clearly, and it clearly says 'Percival'. But you can't help but think that something looks odd about his clothes this time. Something looks different, but you're not sure what. Was he really wearing that cheap black suit last time? What about that high-vis vest? Or that yellow hard-hat?\n\nYour train of thought is, again, [[interrupted by Percival|deja vu Hello Again Percival]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MeetPercival-RoomService",
			"This mysterious individual clearly doesn't seem too happy about being asked if they're here for room service.\n\n\"***Room service!?*** Me!? Percival!? He who checks off the checklists!? Mistaken for a mere customer service tool!?\"\n\nThe nasal tone of his voice still takes you by surprise. But at least you now know who's at the door.\n\n[[\"So, Percival, why are you here if you're not here to take my breakfast order?\"|MeetPercival-Explained]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Ending1-Hoax",
			"You hear that, allegedly, many people have been receiving handwritten letters from some organization that doesn't exist, promising free money to recipients, akin to a bogus job offer scam. The police have released a formal statement, saying that they don't have any reason to believe that anything illegal has happened, but heavily discouraged replying to those letters.\n\nUnfortunately for you, you've already replied. But your reply was a 'no'.\n\nAs the ad break starts, you promptly put those worries to one side, as you [[investigate the noise at your front door|Ending1-2]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu ambush",
			"You hide in the blind spot from the door, waiting for Percival to open the door, so you can catch him off-guard.\n\n\"Subject C? Where are you?\"\n\nHe's entering the room.\n\nYou don't move a muscle.\n\nYou can see him walk past the door, looking for you within the main area of the room.\n\nHe doesn't know you're here. This is good.\n\nAll that's left is just creeping up behind him and [[kicking his high-vis behind into next Tuesday|deja vu ambush 2]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu askPercival",
			"You get Percival's attention with a simple \"Hey, Percival\".\n\nHe looks back, responding with a \"Yes?\"\n\nNow that you have his attention. what are you going to say to him?\n[[\"What if I told you that I've been in that briefing room already?\"|deja vu been there already]]\n[[\"Is that a new hat you're wearing?\"|deja vu new hat]]\n[[\"When's lunch?\"|deja vu whens lunch]]\n[[\"Actually, never mind.\"|deja vu silent treatment]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MeetPercival-Explained",
			"\"If you would just follow me, I'll take you to the briefing room where things can be explained to you in a bit more detail. And if you don't come with me willingly, I would like to mention that I am authorized to knock you out again and carry you there. Yes, I know, it sounds awkward, and I'd rather not do that either.\"\n\nYou shudder at the thought of the mere thought of Percival attempting to fight, and inevitably ending up in a situation that manages to fill you with secondhand embarrassment.\n\nAnd then there's the thought of somehow *losing* to this pathetic excuse of a person.\n\nIt would probably be for the best if you just [[go quietly|MeetPercival-ToTheBriefingRoom]], at least until you know what's going on.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2 - entry",
			"Like last time, Percival opens up the briefing room door for you, speaking to you as he does this.\n\n\"So, in here, we're going to watch a short, instructional video, which should explain what you'll be doing here.{if:tAny(\"lunch\")}{ And yes, after this is done, you'll be able to get some lunch.}{else:{if:tAny(\"beenthere\")/}{ And I can assure you that you definitely have not been here before./}}\"\n\nUnlike the last time, you notice that there's actually a handle inside the door.\n\nPercival then ushers you [[into the room|BriefingRoom2 - in]], with a polite-sounding \"After you\".",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu silent treatment",
			"The two of you continue walking in a somewhat awkward silence. You keep trying to look for anything that might help you to work out where you are, but, unfortunately for you, this rather long, straight, unsignposted corridor doesn't contain any information of any use to you.\n\nEventually, Percival stops, with a slightly triumphant \"Here we are!\".\n\nYou notice the words on the door he's stopped at.\n\n[[BRIEFING ROOM|BriefingRoom2 - entry]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu didnt flush",
			"You start to open the door.\n\nYou hear a scream from the other side.\n\n\"**WHAT ARE YOU DOING!? WHAT SORT OF PERSON DOESN'T FLUSH OR WASH THEIR HANDS!? HAVE YOU EVER HEARD OF THE CONCEPT OF BASIC HYGIENE!?**\"\n\nYou close the door again, and suspect that it might be in your best interests to [[flush the toilet and wash your hands|deja vu leaving toilet]] first. Unnecessary earache from Percival doesn't seem like a very attractive prospect.",
			["noflush"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MeetPercival-whereis",
			"\"Well, I suppose that it's understandable that you'd be unsure about where you are. After all, I'm sure that the sight of the great Percival, of he who leaves no checklist unchecked, would get anyone somewhat disoriented.\"\n\nYou think to yourself that his earache-inducing voice is much more likely to disorient you than his appearance ever could. Earaches aside, at least you now know who you're talking to. But this train of thought is swiftly derailed by his sonic weapon of a voice.\n\n\"You are currently at the research campus of the Institution for Practical Parallelization, specifically, inside the guest room where you will be sleeping for the next few days. As you can see, no expense was spared on making it somewhat livable.\"\n\nYou mutter under your breath that someone probably did spare some expense on this jumpsuit you've been outfitted in.\n\n[[\"So why exactly are you here, Percival?\"|MeetPercival-Explained]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"movedElsewhere",
			"You wake up in an unfamiliar rectangular room, on your back, looking up at a softly flickering fluorescent light on the ceiling, illuminating the room.\n\nThere is a single bed in the room, affixed to the wall. It looks reasonably comfortable, not that you know for sure, seeing as you've woken up on the floor.\n\nIt's certainly not the most comfortable floor you've woken up on. But it could be worse. Sure, it's a hard, wooden floor. At least it's not concrete or something like that.\n\nThe walls are completely bare, besides some curtains on the wall opposite the door.\n\nThat's when you realize that there's a door.\n\nIt looks suspiciously like the sort of door you'd expect to see in the deep, staff-only, backrooms of a ship. More of a hatch in the wall than anything else. You think that this might be the exit, but you can't see any obvious way to open it.\n\nThere is a somewhat triangular extrusion from a corner of the room, next to the exit door. This extrusion also has a door, but the door looks much more like a normal door. You come to the conclusion that it's probably an en-suite wetroom shower/toilet room. You don't feel that you need to use it right now, so you resume looking around the room.\n\nIt's at this point when you realize that you're no longer wearing the clothes you had on earlier. Someone decided to put you in a beige prison uniform-esque jumpsuit instead, with a somewhat disconcerting bit of text saying 'SUBJECT C' on it.\n\nYou notice a wardrobe opposite the bed.\n\nAll it contains are even more of the same jumpsuits.\n\nYou start to realize that the offer might have been too good to be true.\n\n{if:tAny(\"death\")}{You can't help but realize that this all feels [[oddly familiar|deja vu-1]].}{else:But you then notice that there's [[a folded piece of paper|elsewhere-2]] on the bed, with your name handwritten on it.}",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu maze 02",
			"Percival's voice fades away as you start to run.\n\nYou reach another junction.\n\nWhere now?\n\n[[Left|deja vu maze 2]]\n[[Right|deja vu maze end]]\n[[Forwards|deja vu maze 4]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu endless return",
			"You walk into the black expanse.\n\nThe darkness envelops your vision, making it impossible for you to see anything in front of you or behind you.\n\nIt feels as if you're stuck in some sort of limbo; some sort of void where nothing should be.\n\nYet you are here, you can feel the floor you're walking on, you can touch the walls with your hands; it's just the dodgy lighting, that's all.\n\n[[So you keep going|deja vu endless return 2]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"helloSusan-yeet1",
			"She looks unimpressed with that explanation, and turns to face Percival.\n\n\"Did you somehow forget to tell the subject that they were supposed to watch that health-and-safety video?\"\n\nPercival looks like he's starting to panic.\n\n\"N-no! Of course not! I was told to bring the subject to the briefing room, tell them to watch the video, and that I should bring them to the cafeteria after they were done watching the video.\"\n\nShe looks back at you, still looking unimpressed.\n\n\"So what exactly gave you the impression that you had to get rid of the projector?\"\n\n\"[[Because the video told me to do it|helloSusan-videoSaidSo]].\"\n\"[[Oh, I don't know... perhaps the fact that it was a bomb|helloSusan-bomb]]?\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu hide give up",
			"You can hear a very audible expression of surprise from the other side of the door, and the noise of a clipboard being dropped.\n\nThis is promptly followed by the noises of the person on the other side picking up the aforementioned clipboard and trying to regain their composure, and a very muffled \"W-what!? J-just hurry up in there.\"\n\nIt's pretty clear that you've lost your hiding space now, and there's no question that you're definitely in the en-suite.\n\nYou haven't actually gotten around to doing anything, so you may as well [[just leave|deja vu didnt flush]]. \nBut it might be worth just [[flushing the toilet and washing your hands|deja vu leaving toilet]] first, just in case Percival starts to suspect something.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu maze 01",
			"Percival's voice fades away as you start to run.\n\nYou reach another junction.\n\nWhere now?\n\n[[Left|deja vu maze 1]]\n[[Right|deja vu maze 2]]\n[[Forwards|deja vu maze 3]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Ending1-2",
			"The noise was just the sound of some post getting shoved through your letterbox.\n\nToday, there's just one envelope, stuck in the flap of the letterbox.\n\nAn envelope with an entirely handwritten name and address.\n\nAnd it's handwriting you recognize.\n\n[[You cautiously pick up the envelope|Ending1-3]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-whoops",
			"He looks at you with a look of confusion\n\n\"Why would you be expected to throw that projector out of the window in the first place!?\"\n\nYou open your mouth to start trying to explain the last time to Percival. But then you realize that he'd just get even more confused. {if:pAny(\"deja vu been there already\")}{Besides, he didn't even show any signs of beginning to understand it the first time you tried to explain it to him.}\n\nBesides, why waste effort trying to explain it to him now, when you're clearly going to be expected to explain it at this other person's office anyway?\n\n\"[[Whatever, let's just get on with it|BriefingRoom2-departure]].\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu been there already",
			"He glances back at you, with a look of confusion.\n\n\"No, you haven't been there before. In fact, on this clipboard, I can tell you exactly where you *have* been before, as it's listed here in full.\"\n\nYou suspect that Percival's probably going to start saying the quiet part loud, and he definitely won't listen if you tell him that you've been in there before, so you think that it's in your best interests to just let him keep speaking instead of making a futile attempt to correct him.\n\n\"Let's see here... We received your response confirming your intent to participate here, so a subject retrieval team was sent to retrieve you, which they did. Whilst you were still unconscious, first thing that happened to you here was decontamination and being given that decontaminated outfit, after all, safety first. The science team did give you that injection they're testing, maybe you're disoriented because of that? I doubt it, personally. Anyway, after that, it says here that you were then moved to your room, where you woke up, and I was sent to escort you to the briefing room, which I'm doing now... Not entirely sure how you could have gone to the briefing room already...\"\n\nYour mind starts racing as you try to comprehend all the information Percival accidentally gave you. Retrieval? Decontamination? Injection?\n\nHowever, before you can properly think it through, Percival suddenly stops.\n\n\"Speaking of which...\"\n\nYou notice that he's stopped outside a door labelled with the words [[BRIEFING ROOM|BriefingRoom2 - entry]]",
			["noreturn", "beenthere"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu hide gas",
			"You say nothing.\n\nYou hear Percival making his way out of the room.\n\nYou're safe.\n\nYou hear a gentle *pshhhhh* coming from various points in the walls.\n\nSmoke fills the room.\n\n[[And everything fades to black|BriefingRoom2-KO]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu new hat",
			"Of course! That's why something looked odd about Percival! He was wearing a white hard hat last time, not a yellow one!\n\nBut Percival looks at you with a look of confusion.\n\n\"New hat? What are you talking about? I've always worn this yellow hard hat. {if:tAny(\"pedant\")}{Did they use a picture of me in a different hat in Pedantic Weekly that one time?}{else:Then again, this *is* the first time I've met you, so I guess you probably wouldn't have seen me in this before}\".\n\nThis response merely poses more questions than it does answers. But it does make you think of that conversation you heard on the radio about the Mandela Effect. Could you have somehow found yourself in a different timeline where certain specific details are just slightly different? Not that you'll have an opportunity to ask of course, as Percival suddenly stops and remarks \"We're here!\"\n\nThe door he's stopped at is labelled very clearly with the words [[BRIEFING ROOM|BriefingRoom2 - entry]].",
			["noreturn","hat"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-6",
			"The voice sounds like it's somewhere in the uncanny valley between text-to-speech and actual human speech. You're unsure if it's just because of the poor quality of the intercom, or if that's because that's what their voice actually sounds like. They're speaking in a manner that denotes exhaustion erring into the realms of apathy, as if they've been at their wit's end for some time, and this is yet another problem on top of a massive backlog of problems.\n\n\"May I have your attention please. Percival, please escort yourself and Subject C to my office for debriefing. Now.\"\n\nPercival looks frustrated.\n\n\"Well, that wasn't supposed to happen\" he mutters.\n\nHe looks back at you.\n\n\"You're coming with me.{if:pAny(\"deja vu maze 4\", \"BriefingRoom2-KO\")}{ And this time, I'm going to make sure you stick with me.}\"\n\nHe doesn't look like he's going to give you any choice in the matter. {if:pAny(\"deja vu maze 4\", \"BriefingRoom2-KO\", \"BriefingRoom2-tryTheDoor\")}{Considering what happened the last time you tried to avoid Percival, you figure that, even if you were to try to escape him, he'll still manage to bring you with him. }{if:pAny(\"deja vu new hat\", \"deja vu been there already\")}{However, this does seem like an opportunity to actually get some answers about what's happening here.}\n\n\"[[Who was that|BriefingRoom2-whoThat]]?\"\n\"[[Wait, does this mean that I wasn't supposed to get rid of the projector|BriefingRoom2-whoops]]?\"\n\"[[I suppose I'd better follow you then|BriefingRoom2-departure]].\"\n{if:pAny(\"deja vu whens lunch\")}{\"[[So is it lunchtime now|BriefingRoom2-lunch]]?\"}",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu maze 4",
			"As you keep running, you eventually bump into someone, as they walk through a small side-hallway that you didn't quite notice.\n\nUnfortunately for you, it's Percival.\n\n\"Ah! There you are!\"\n\nHe doesn't seem particularly annoyed.\n\n\"Good thing I bumped into you here, I was afraid that you'd get completely lost, and never find your way back!\"\n\nYou shudder as you {if:pAny(\"deja vu endless give up\")}{remember something very familiar happening to you earlier on}{else:imagine quite how lost Percival considers to be 'lost'}.\n\n\"Well, it looks like you managed to find your own way here, so that's convenient.\"\n\nHe points towards a nearby door, with some words on it.\n\n[[BRIEFING ROOM|BriefingRoom2 - entry]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-5",
			"The projector flies through the window just as well as a brick would, breaking the glass with a satisfying **SMASH**, before falling all the way down to the floor of the warehouse, landing with a not-quite-as-satisfying-but-still-relieving **CRUNCH**.\n\nUnfortunately for you, Percival's worked out what you just did as well. And words cannot express just how confused and outraged he currently is, so much so that even he's struggling to actually say the words he's trying to say.\n\n\"W-w-what do you think you're playing at!? Why did you just throw the projector out of the window like that!? Projectors are expensive!? You were supposed to watch the briefing, not defenestrate it!?\"\n\nBefore you can even start trying to explain yourself, both of you are interrupted by the noise of an intercom system crackling, diverting your attention from the big hole in the window. Seeing as there's nothing else to do, you both [[listen to the announcement that's being made|BriefingRoom2-6]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-4",
			"The stunned silence from Percival is almost deafening. It's almost as if he was expecting you to say something that would have made a bit more sense.\n\nAt very least, it sounds like he's not really going to be able to stop you whilst he's busy trying to comprehend what you've just told him.\n\nYou keep walking, carrying the projector with you, all the way to the window at the back of the room.\n\nLooking through the window, into the large warehouse-like room, you notice a complete lack of, well, anything, on the floor below the window. However, it doesn't look like there's something you can use to open the window from in here.\n\nWell, nothing, besides the projector.\n\nYou guess that you may as well [[take care of the projector|BriefingRoom2-5]] before Percival has a chance to stop you.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-3",
			"It's lighter than you thought it would be. Then again, you weren't entirely sure how heavy projectors usually are in the first place. The '**YOUR ATTENTION, PLEASE**' text moves and distorts on the wall whilst you're trying to hold the projector in such a way that makes it comfortable for you to carry.\n\nThis{if:pAny(\"BriefingRoom2-tryTheDoor\")}{, once again,} clearly hasn't gone unnoticed by Percival.\n\n\"What do you think you're doing with that projector!?\"\n\nHow are you going to respond to him?\n[[\"It looked at me funny\"|BriefingRoom2-4]]\n[[\"The projection's too small for me to read, I'm just moving it back a bit so I can actually see what it's saying.\"|BriefingRoom2-4]]\n[[\"No time to explain!\"|BriefingRoom2-4]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-2",
			"You walk up to the projector. It's still projecting the words '**YOUR ATTENTION, PLEASE**' onto the wall, as if it's trying very hard to act like it's just an everyday, rather boring, projector.\n\nUp close, it just looks like a run-of-the-mill projector. However, it isn't plugged into anything, which is a bit odd, even if you ignore what it did to you the last time you were stuck in a room with it. Perhaps battery-powered, wi-fi projectors, are the norm for these sorts of shady secretive organizations. Not that the details of why this is really matter to you, besides the lack of wires to unplug being rather convenient for what you're about to do.\n\nYou lean over the projector, and [[pick it up|BriefingRoom2-3]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-1",
			"The briefing room looks roughly identical to how it looked last time. It still looks like a small lecture hall, with a large window in the rear wall, and has a projector at the front, projecting the words '**YOUR ATTENTION, PLEASE**' onto the front wall.\n\nThe only differences this time are that Percival is in the room with you, and that the doors look like they can be opened from the inside.\n\nYou cast your mind back to what happened last time you were in this room.\n\nTwo immediately obvious courses of action spring to mind. Do you:\n[[Attempt to leave the room|BriefingRoom2-tryTheDoor]]\n[[Get rid of the projector before it does anything funny *again*|BriefingRoom2-2]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-departure",
			"Percival ushers you out of the room, through a different door to the one you used to enter the room, into a different set of corridoors.\n\nInterestingly, the corridors look unlike the previous set of corridors. These look like the sort of corridors you'd expect to see in an office building. There's an off-beige carpet with a drab trim as they meet the inoffensively plain white walls. As Percival keeps ushering you through them, you notice a few windows on some of the doors, looking into some decently-sized rooms of office cubicles, and you notice a few people in there trying to give off the impression that they are, in fact, working. Unfortunately, you don't get a chance to have a proper look in there, as Percival's herding you along at a rather rapid pace.\n\nAfter a couple of minutes, you and Percival turn around a corner, and you see some doors that have nameplates next to them. Unfortunately, you don't have enough time to stop and read them. These are probably the offices of the people here who are relatively important enough to have their own office. You can see a look of mild envy in Percival's face as he's shepherding you through this hallway, as if he's still a bit annoyed that he doesn't have his own office.\n\nEventually, Percival tells you to stop, because he's reached his destination. The nameplate says 'Susan Lewis'. He lets out a sigh of defeat.\n\n\"I really hope you have a good excuse to give her\" he tells you, as he opens the door.\n\nYou feel yourself [[getting pushed into the room|helloSusan]] before you have a chance to actually look inside the room.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu endless give up",
			"You turn around.\n\nBut you can't help but notice that there isn't any light in the corridor behind you, instead, all you can see is an expanse of darkness, looking just as endless as the length of the corridor you still need to walk down.\n\nYou come to the realization that you aren't supposed to be here.\n\nDo you [[walk back, through the darkness|deja vu endless return]]?\nOr do you [[try to keep going down the corridor|deja vu endless corridor]], even though you can't see any end to it?",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu maze 1",
			"Guess what? Another junction!\n\n[[Left|deja vu maze 4]]\n[[Right|deja vu maze 2]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-tryTheDoor",
			"You make your way to the door.\n\nUnfortunately, Percival notices you trying to make a run for it, and manages to get himself between you and the door.\n\n\"Where do you think you're going!? Briefing first, leaving second. I am not supposed to allow you to leave until after you take a seat and watch the briefing.\"\n\nIt's pretty clear that Percival won't let you leave this room. And you highly doubt that he'd listen to you, let alone believe you, if you were to explain what happened to you last time{if:tAny(\"beenthere\")}{, especially considering that he completely denied that there was a 'last time'}.\n\nThen again, some people do say that actions speak louder than words, so you may as well let your actions do the talking; by [[getting rid of that projector|BriefingRoom2-2]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu maze 2",
			"Another junction.\n\n[[Left|deja vu maze 4]]\n[[Right|deja vu maze end]]\n[[Forwards|deja vu maze 3]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu soundsLikePercival",
			"\"Oh.\"\n\nYou can hear the disappointment in Percival's voice. He was probably expecting to hear something *slightly* more interesting.\n\nEither way, that seems to have answered that question, as he looks like he's about to [[get back to the task at hand|deja vu percival leave]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu maze 3",
			"You reach another junction.\n\n[[Left|deja vu maze 4]]\n[[Right|deja vu maze 1]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu endless return 3",
			"No. You can't.\n\nThere is no floor. Never was.\n\nUnfortunately, you only realize this a bit too late, after you lost your footing.\n\nYou're falling.\n\nYou don't know how long you've been falling for.\n\nAll you know is that you feel like you're falling.\n\nFalling for a long time.\n\nSo long.\n\nIt feels like you've been falling for hours.\n\nYou're starting to get tired.\n\nYour body wants to sleep.\n\nPerhaps you should just [[get some sleep|BriefingRoom2-KO]].",
			["noreturn","gotLost"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"StartYes-Hoax",
			"You hear that, allegedly, many people have been receiving handwritten letters from some organization that doesn't exist, promising free money to recipients, akin to a bogus job offer scam. The police have released a formal statement, saying that they don't have any reason to believe that anything illegal has happened, but heavily discouraged replying to those letters.\n\nUnfortunately for you, you've already replied. And your reply was a 'yes'.\n\nHopefully nothing bad is going to happen, right?\n\nYou also can't help but notice [[some smoke|StartYes-4]] starting to fill the room, coming from the general direction of your front door.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"elsewhere-3",
			"You look towards the door with some concern, knowing what happened the last time someone from this institute started knocking at your door.\n\nHowever, this door does look rather solid, and there doesn't appear to be any obvious holes that someone could shove yet another smoke grenade through.\nBut chances are that if they really wanted to do that again, they'd have probably put some nozzles for that in the room itself, so they could engulf the room directly.\n\nYou don't want to risk it though, so you stay as still as possible, trying to not make any noise to indicate that you're awake, to buy yourself some more time to properly wrap your head around the fact that you're now here, where-ever 'here' actually is.\n\nYou hear the voice from the other side of the door again.\n\n\"Subject C, I know you're awake and in there, I'm going to open the door.\"\n\nYou hear the noise of a crank being turned, and some bolts getting unbolted.\n\nThe door opens, revealing a rather boring looking hallway, and [[a relatively unassuming-looking fellow standing in the doorway|elsewhere-4-MeetPercival]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-lunch",
			"Percival looks even more disappointed than he already was.\n\n\"No.\"\n\nChances are that he really doesn't want to discuss it any further.\n\n\"[[Whatever, let's just get on with it|BriefingRoom2-departure]].\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu endless return 2",
			"You keep going.\n\nEventually, your hands stop to feel the walls to either side of you.\n\nBut the floor's still there, right?\n\nThere's something you can walk on, right?\n\n[[So you can keep walking back, right?|deja vu endless return 3]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Ending1-3",
			"This time, there is no warning about privacy and confidentiality on the envelope.\n\nSo you don't waste time faffing about with making sure it's actually addressed to you, so you just [[open it|Ending1-4]] and start reading it.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Ending1-4",
			"There's just a single piece of A6 paper inside, bearing three handwritten lines.\n\n*Perhaps in another timeline. Such is the nature of our work.*\n\n*Farewell.*\n\n*- Dr A. Albert*\n\nHowever, as far as you are concerned, you get the impression that this is **the end** of this chain of events, and you can carry on with your life as usual.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"elsewhere-2",
			"You prepare for the worst as you unfold the piece of paper and start reading it.\n\n*Greetings.*\n\n*I would like to apologize profusely for the theatrics when bringing you here.*\n\n*Yes, I know, sleeping gas is far from the most pleasant method of welcoming you here, however, we have to take these sorts of precautions with the whole 'top secret' nature of this place. Personally, I would have preferred it if we could have just told you the address and allowed you to make your own way here. But that's life, I suppose.*\n\n*I should also mention that, by being here, you are officially bound by all that legislation that prohibits any discussion of the stuff you're doing here. Officially, you're on a residential training bootcamp for creating algorithms to efficiently traverse directed graphs and other sorts of techno mumbo-jumbo that nobody really understands anyway.*\n\n*So, you're probably wondering what's happening here. This is understandable. In short, you'll be helping us out with some experiments into the nature of reality itself. Yes, you might get very confused by everything that's going on, but that's generally what happens here.*\n\n*Now, once you've read this, we'll bring you to the briefing room where we'll explain everything that's going on. And yes, everyone in this facility will be referring to you as 'Subject C' from now on, mostly because we haven't let the staff know your name, and mostly because it's easier to do our work this way.*\n\n*Thank you, once again, for your participation*\n\n*- Dr. Archibald Albert*\n\nAs you finish reading this slip of paper, you can hear some [[knocking|elsewhere-3]] on the door, and a muffled, unfamiliar voice calling for a \"*Subject C?*\".",
			[]
		)
	);

	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

