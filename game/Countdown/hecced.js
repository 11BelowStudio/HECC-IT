//HECC UP output (as of 29/01/2021) (R. Lowe, 2021)

var startingPassageName = "Start";

function getHECCED(){
	theHeccer.addPassageToMap(
		new Passage(
			"Start4",
			"You open the envelope, and the paper inside it feels similarly expensive.\n\nThe text on that envelope is, again, entirely handwritten, from the line at the top addressing you by name, to the signature at the end.\n\n*Greetings. If you are reading this, we at the Institution for Practical Parallelization (the IPP) are offering you the possibility of helping us out with some of our research into the practical applications of theoretical parallelism in a wide range of use cases, from the simple 'gaining knowledge faster', to the more complex tasks of understanding the outcomes of decisions before they're made. And you have been identified as an individual who has potential to do great things, for both yourself, and for society as a whole.*\n\n*Now, I can't get further into details at this point in time, as all of the really juicy information is hidden under layers after layers of NDAs and legislation and paperwork and all of that jazz. Even telling you about the very existence of this institution is in a legal grey area*\n\n*What I can tell you is that we are offering you a 5-day placement here. You will be staying at the institution during this placement, but no expense has been spared on the room and board. Yes, you will be expected to work, but I can assure you that it'll be less soul-destroying than the sort of work you already do in your day-to-day life. And, to sweeten the deal, we will compensate you for your time; you will be paid £2 million (tax-free), as soon as your placement ends. Now, you're probably concerned about our ability to provide this compensation. But hopefully the initial payment we have transferred to your bank account should address those concerns.*\n\n*Regrettably, we cannot give you any further information, and we can't even tell you when this study will be, due to the aforementioned legal problems*\n\n*If you do want to participate in this study, please use the included signed addressed envelope to send us your response. All we need is a single-word 'Yes' or 'No'.*\n\n*Thank you for your attention, and we hope for your participation*\n\n*- Dr. A. Albert, OBE*\n\nYou are taken aback by the somewhat unexpected contents of this letter.\n\nYou re-read it several times, to make sure it's actually correct.\n\nYou even open the letter by your feet that's clearly a bank statement, and, right there, you can see that a healthy sum of £500,000 has been deposited into your account, from an organization listed as 'IPP'.\n\nEverything seems legit.\n\nBut how are you going to reply?\n\nAre you going to reply with a [[Yes|StartYes]], or are you going to send a [[No|StartNo]]?",
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
			"You look towards your front door, as you notice some post getting shoved through your letterbox.\n\nA shower of junk mail gets carelessly pushed through, forming an unsightly puddle of advertisements for services you don't need on your floor.\n\nThis is promptly followed by some less useless but still not exactly wanted envelopes containing bills and such. You don't even need to open them to know they're bills, because of that distinctive 'if undelivered return to' text on the back of the envelopes, and the windows on the front of the envelopes to the automatically generated addressee information on the automatically generated documents they contain.\n\nBut there is one last envelope, stuck in the flap of the letterbox, above the puddle of mediocrity.\n\nAn envelope with an entirely handwritten name and address.\n\nBut it's not handwriting from anyone you recognize.\n\n[[You cautiously pick up the envelope|Start3]]",
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
			"MeetPercival-RoomService",
			"This mysterious individual clearly doesn't seem too happy about being asked if they're here for room service.\n\n\"***Room service!?*** Me!? Percival!? He who checks off the checklists!? Mistaken for a mere customer service tool!?\"\n\nThe nasal tone of his voice still takes you by surprise. But at least you now know who's at the door.\n\n[[\"So, Percival, why are you here if you're not here to take my breakfast order?\"|MeetPercival-Explained]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"It's an ordinary morning for you.\nNothing wacky and/or uncharacteristic, just the same stuff you usually experience.\n\n[[But there's a noise at the door.|Start2]]",
			["noreturn"]
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
			"elsewhere-4-MeetPercival",
			"\"Hello, subject C.\"\n\nThe very nasal voice of the person greeting you takes you aback. It didn't sound quite like that from the other side of the door.\n\nThey're dressed up in the sort of garb you'd expect some sort of stereotypical caricature of a health-and-safety inspector. A cheap-looking black suit, like the sort of suit worn by someone who takes themselves way too seriously, with overly-polished shiny black shoes, yet contrasted by a very bright high-vis vest. They appear to have an ID badge on their high-vis vest, but you can't read it from where you are. You can see a painstakingly manicured, yet somewhat subtle, moustache on their face, along with some cheap-looking safety spectacles, topped off with a white hard hat on their head, with the letters 'IPP' clearly visible on the front of the hard hat. They're cradling a clipboard with their left arm, and they have a fountain pen in their right hand.\n\nThey clearly work here, and they're clearly here to take you to the briefing room mentioned in that letter from earlier.\n\nAnd they're clearly expecting you to say something.\n\n[[\"Who the hell are you?\"|MeetPercival-whois]]\n[[\"Where am I?\"|MeetPercival-whereis]]\n[[\"Hello? Room service?\"|MeetPercival-RoomService]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MeetPercival-Explained",
			"\"If you would just follow me, I'll take you to the briefing room where things can be explained to you in a bit more detail. And if you don't come with me willingly, I would like to mention that I am authorized to knock you out again and carry you there. Yes, I know, it sounds awkward, and I'd rather not do that either.\"\n\nYou shudder at the thought of the mere thought of Percival attempting to fight, and inevitably ending up in a situation that manages to fill you with secondhand embarrassment.\n\nAnd then there's the thought of somehow *losing* to this pathetic excuse of a man.\n\nIt would probably be for the best if you just [[go quietly|MeetPercival-ToTheBriefingRoom]], at least until you know what's going on.",
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
			"StartYes-2",
			"It's an ordinary morning for you.\nNothing wacky and/or uncharacteristic, just the same stuff you usually experience.\n\n[[But there's a noise at the door.|StartYes-3]]",
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
			"You look towards your front door, as you notice some post getting shoved through your letterbox.\n\nExcept instead of the usual deluge of junk mail, there's a strange grey cylinder.\n\nAnd there's a lot of [[smoke|StartYes-4]] coming out of it",
			[]
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
			"You wake up in an unfamiliar rectangular room, on your back, looking up at a softly flickering fluorescent light on the ceiling, illuminating the room.\n\nThere is a single bed in the room, affixed to the wall. It looks reasonably comfortable, not that you know for sure, seeing as you've woken up on the floor.\n\nIt's certainly not the most comfortable floor you've woken up on. But it could be worse. Sure, it's a hard, wooden floor. At least it's not concrete or something like that.\n\nThe walls are completely bare, besides some curtains on the wall opposite the door.\n\nThat's when you realize that there's a door.\n\nIt looks suspiciously like the sort of door you'd expect to see in the deep, staff-only, backrooms of a ship. More of a hatch in the wall than anything else. You think that this might be the exit, but you can't see any obvious way to open it.\n\nThere is a somewhat triangular extrusion from a corner of the room, next to the exit door. This extrusion also has a door, but the door looks much more like a normal door. You come to the conclusion that it's probably an en-suite wetroom shower/toilet room. You don't feel that you need to use it right now, so you resume looking around the room.\n\nIt's at this point when you realize that you're no longer wearing the clothes you had on earlier. Someone decided to put you in a beige prison uniform-esque jumpsuit instead, with a somewhat disconcerting bit of text saying 'SUBJECT C' on it.\n\nYou notice a wardrobe opposite the bed.\n\nAll it contains are even more of the same jumpsuits.\n\nYou start to realize that the offer might have been too good to be true.\n\n{if:tAny(\"death\")}{You can't help but realize that this all feels [[oddly familiar|deja vu]].}{else:But you then notice that there's [[a folded piece of paper|elsewhere-2]] on the bed, with your name handwritten on it.}",
			["noreturn"]
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
			"Ending1-2",
			"You look towards your front door, as you notice some post getting shoved through your letterbox.\n\nToday, there's just one envelope, stuck in the flap of the letterbox, above the puddle of mediocrity.\n\nAn envelope with an entirely handwritten name and address.\n\nAnd it's handwriting you recognize.\n\n[[You cautiously pick up the envelope|Ending1-3]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom1-5",
			"*\"Thing is, if you were the one we were looking for, you would have thrown this projector out of the back window by now. Oh well.*\n\nYou're taken by surprise at that almost-instruction.\n\n*\"But I suppose that those bikers had to get caught in the crash in order for them to be warned about it, didn't they?\"*\n\nBefore you can move towards the projector in order to throw it out of the window, you notice a very bright, very warm, flash coming from the projector.\n\nHeading straight towards you.\n\n[[And everything fades to white|movedElsewhere]].",
			["noreturn","death","BriefingRoomDeath"]
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
			"BriefingRoom1-3",
			"*\"After 1 mile, they got into a horrific traffic accident. 90 of those bikers lost their lives. And nobody could have predicted it.\"*\n\nThe cheery music stopped dead, and the scene changed to a graveyard, with 90 graves. There was an uncomfortably long pause at that scene, where the video was clearly still playing, but was lingering, in silence, almost as if it was offering a moment of silence to the memory of those deceased imaginary bikers.\n\n*\"But, what if one of the bikers, after getting caught in that accident, could have gone back and warned the others about it, thereby saving 90 lives?\"*\n\nThe scene shifted back to the discussion the bikers were having, and one of them was suddenly acting very agitated, very enthusiastically miming a warning about the stuff that was going to happen if the bikers were to go down the left road.\n\n*\"What if they had been given this forewarning, and had all gone down the right road instead?\"*\n\nThis was accompanied by the same animation from earlier of the bikers getting back onto their bike, and heading down the right road.\n\nAt this point, you start to wonder if you somehow managed to find yourself in a stereotypical cable TV pseudoscience documentary.\n\nBut, once again, your train of thought was interrupted.\n\n*\"But I know you're not interested in this theoretical malarkey, you're interested in some practical examples, aren't you?\"*\n\nOn cue, the projection started to show a large hand, pointing towards the viewer, towards you, to make it obvious that the narrator was, in fact, trying to talk to you.\n\n*\"Yes, Subject C, you.\"*\n\nYou feel a slight feeling of discomfort at this revelation that you apparently are the intended audience for the next part of this recording.\n\nYou braced yourself, [[preparing for what was to come|BriefingRoom1-4]].",
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
			"deja vu",
			"You try to remember what just happened.\n\nDidn't you already wake up here?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom1-1",
			"You step into the room, and you hear the door slam shut behind you.\n\nOn the positive side, you are free from having to listen to Percival's voice.\n\nOn the negative side, you are now all alone again, which makes you think that something's not right.\n\nThe room has many rows of tables, with benches behind them, looking somewhat like a lecture hall.\n\nThe rear wall, behind those tables and benches, has a large window, overlooking an empty warehouse-like room.\n\nBut, in front of the tables and benches, there's some text saying '**YOUR ATTENTION, PLEASE**. Sit comfortably and then we'll start the briefing' projected on the wall.\n\n[[May as well find out what all the fuss is about.|BriefingRoom1-2]].",
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
			"elsewhere-3",
			"You look towards the door with some concern, knowing what happened the last time someone from this institute started knocking at your door.\n\nHowever, this door does look rather solid, and there doesn't appear to be any obvious holes that someone could shove yet another smoke grenade through.\nBut chances are that if they really wanted to do that again, they'd have probably put some nozzles for that in the room itself, so they could engulf the room directly.\n\nYou don't want to risk it though, so you stay as still as possible, trying to not make any noise to indicate that you're awake, to buy yourself some more time to properly wrap your head around the fact that you're now here, where-ever 'here' actually is.\n\nYou hear the voice from the other side of the door again.\n\n\"Subject C, I know you're awake and in there, I'm going to open the door.\"\n\nYou hear the noise of a crank being turned, and some bolts getting unbolted.\n\nThe door opens, revealing a rather boring looking hallway, and [[a relatively unassuming-looking fellow standing in the doorway|elsewhere-4-MeetPercival]].",
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
			"Ending1-3",
			"This time, there is no warning about privacy and confidentiality on the envelope.\n\nSo you don't waste time faffing about with making sure it's actually addressed to you, so you just [[open it|Ending1-4]] and start reading it.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Ending1-4",
			"There's just a single piece of A6 paper inside, bearing three handwritten lines.\n\n*Perhaps in [[another timeline|Start]]. Such is the nature of our work.*\n\n*Farewell.*\n\n*- Dr A. Albert*\n\nHowever, as far as you are concerned, you get the impression that this is **the end** of this chain of events, and you can carry on with your life as usual.",
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
	theHeccer.addPassageToMap(
		new Passage(
			"Ending1",
			"It's an ordinary morning for you.\nNothing wacky and/or uncharacteristic, just the same stuff you usually experience.\n\n[[But there's a noise at the door.|Ending1-2]]",
			["noreturn"]
		)
	);

	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

