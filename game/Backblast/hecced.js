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
			"deja vu percival leave",
			"\"Now, where was I...\"\n\nHe glances at his clipboard.\n\n\"Ah, yes. So, Subject C, If you would just follow me, I'll take you to the briefing room where things can be explained to you in a bit more detail.\"\n\nYou shudder a bit, after what happened in that briefing room last time. However, this time, it doesn't sound like Percival's going to force you to go with him. You don't think he'd listen if you were to attempt explaining what happened last time though, as it doesn't like like he's aware of there even being a 'last time'. But, at very least, you think you know what to do this time.\n\nWhat are you going to do?\nCooperate, with a [[\"Lead the way, Percival!\"|deja vu Follow Percival]].\nPretend to cooperate, but [[run off|deja vu run away]] ASAP.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu whens lunch",
			"You decide that asking when lunch is would be the best question to start with. May as well start with the easy questions so he lowers his defences, before giving him the really big questions.\n\nHe glances at his clipboard, muttering to himself \"Let's see here... lunch, lunch, lunch... when is it again... Aha!\"\n\nHe looks back at you, answering with \"Says here that you'll get lunch after we're done in the briefing room.\"\n\n*We're* done? That sounds different. Last time, he just left you in there.\n\n\"Speaking of which, looks like you won't need to wait that long for your lunch.\"\n\nAs he says this, he suddenly stops in front of a door. A door labelled with the words [[BRIEFING ROOM|BriefingRoom2 - entry]].",
			["noreturn","lunch"]
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
			"elsewhere-4-MeetPercival",
			"\"Hello, Subject C.\"\n\nThe very nasal voice of the person greeting you takes you aback. It didn't sound quite like that from the other side of the door.\n\nThey're dressed up in the sort of garb you'd expect some sort of stereotypical caricature of a health-and-safety inspector. A cheap-looking black suit, like the sort of suit worn by someone who takes themselves way too seriously, with overly-polished shiny black shoes, yet contrasted by a very bright high-vis vest. They appear to have an ID badge on their high-vis vest, but you can't read it from where you are. You can see a painstakingly manicured, yet somewhat subtle, moustache on their face, as well as a pair of browline glasses in front of their eyes. Those glasses could have given off an air of quiet sophistication, if it wasn't for the rest of their outfit, and that somewhat gratituous pair of cheap-looking safety spectacles that they're wearing over their glasses. They're also wearing a white hard hat on their head, with the letters 'IPP' written on the front of it. They're cradling a clipboard with their left arm, and they have a fountain pen in their right hand.\n\nThey clearly work here, and they're clearly here to take you to the briefing room mentioned in that letter from earlier.\n\nAnd they're clearly expecting you to say something.\n\n[[\"Who the hell are you?\"|MeetPercival-whois]]\n[[\"Where am I?\"|MeetPercival-whereis]]\n[[\"Hello? Room service?\"|MeetPercival-RoomService]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-savedYourLife",
			"Percival looks at you as if you're speaking to him in a language he doesn't understand.\n\n\"What?\"\n\nIt appears as if he's got no idea what you're talking about. Then again, if someone were to do something completely stupid in front of you and then turn around and claim that they just saved your life, despite you being under the impression that nothing's endangering your life, you'd probably be feeling pretty confused as well.\n\n\"[[I got rid of the bomb before it could kill us. That makes me a hero!|BriefingRoom2-itsABomb]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-muttering",
			"Percival clearly seems very engrossed in his train of thought, because he clearly hasn't heard you\n\n\"So, there was that explosion last week with Subject B... and that other one before that one with Subject A...\"\n\n Explosions?\n\nOther subjects?\n\nSounds like some juicy information.\n\n\"[[So, what exactly are you muttering to yourself about?|BriefingRoom2-whatsthebuzz]]\"",
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
			"deja vu endless corridor",
			"The corridor continues. {if:pAny(\"deja vu endless corridor\")}{You start to suspect that it might never end.}\n\nWill you [[turn back|deja vu endless give up]]?\n\nOr will you [[carry on|deja vu endless corridor]]?",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"ResearchLabs",
			"Sample Content",
			[]
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
			"YourRoom the bomb",
			"You rip off some of the wrapping paper.\n\nWhoever this secret santa is, they're terrible at getting decent presents.\n\nBecause they got you [[a bomb|YourRoom boom]].",
			["noreturn"]
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
			"MeetPercival-whois",
			"Their expression suddenly brightens, as if they were hoping for you to ask that question.\n\n\"Yes! Finally, I, the great Percival, he whom files the files and lists the checklists, have finally been seen as important enough to be worthy of getting asked who I am by one of the test subjects! I shall finally be able to make the dramatic introduction I have always sought to make!\"\n\nAs he says this, he jumps in an expression of joy, looking like he's just checked off something on his bucket list. But at least you now know who this person you're talking to is. But your train of thought is interrupted, as you hear his nasal voice pipe up again, as realizes that he has a job to do.\n\n\"Ah. I've just blown my chance to make a dramatic introduction, haven't I? Great.\"\n\nYou can very clearly hear the disappointment in his voice.\n\n\"Yes, my name is Percival, and I'm one of the people who work here at the Institution for Practical Parallelization\"\n\n[[\"So why exactly are you here, Percival?\"|MeetPercival-Explained]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria eat",
			"After taking a look at the labels on the sandwich platters, and finding a sandwich that you're prepared to eat, you sit down and tuck in.\n\nThe sandwich doesn't taste particularly good or bad, it just tastes like a very middle-of-the-road sandwich.\n\nHowever, as soon as you're done eating, you notice that other person, who you tried to ignore, is walking up to you and Percival, and looks like they're about to talk to you.\n\nWhat's the plan now?\n\nContinue to [[act natural|Cafeteria meet Dr Spreewald]], and hope that they aren't going to delay you for too long.\n\"[[Say, Percival, where are we supposed to be headed off to next?|Cafeteria make excuses]]\"",
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
			"BriefingRoom2-whatsthebuzz",
			"This time, Percival actually has noticed you trying to get his attention.\n\nBut he actually seems willing to explain what's happening, for once.\n\n\"Well, I suspect that someone's trying to get me fired.\"\n\nHe explains to you that there were two prior test subjects before you, hence your designation as 'C'. He was supposed to be supervising them as they were following the carefully defined itinerary of places that he was meant to escort them to. However, both of those subjects mysteriously died in some unexplained explosions, and, being the last person to have seen them alive, Percival was required to pay for the cleanup. And after Subject B died, Percival was told that he was supposed to keep you alive, or else he'd get fired.\n\nPercival's employment status is honestly pretty low on your list of priorities at this particular point in time. However, it's pretty clear that he does have a vested interest in keeping you alive, which is a good thing for you.\n\n\"[[So, you going to help me to not get killed?|BriefingRoom2-goingToHelpMe]]\"",
			[]
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
			"deja vu ambush",
			"You hide in the blind spot from the door, waiting for Percival to open the door, so you can catch him off-guard.\n\n\"Subject C? Where are you?\"\n\nHe's entering the room.\n\nYou don't move a muscle.\n\nYou can see him walk past the door, looking for you within the main area of the room.\n\nHe doesn't know you're here. This is good.\n\nAll that's left is just creeping up behind him and [[kicking his high-vis behind into next Tuesday|deja vu ambush 2]].",
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
			"YourRoom silent treatment",
			"The two of you walk back to the room in silence.\n\n\"I guess that it is hard to come up with smalltalk in a situtation like this\", Percival remarks.\n\nEventually, the two of you [[come to a stop|YourRoom arrival]], outside of your room.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MainLoopIntro-whereExit",
			"\"The exit? Ah, well, about that...\"\n\nPercival's apolgetic tone of voice here doesn't really sit very comfortably with you.\n\n\"Thing is, this facility goes under lockdown when there's supposed to be some experimentation going on. I don't understand the specifics of why myself, probably something to do with ensuring controlled conditions and such. Either way, we're stuck in here for the next few days. There is a master override switch, but I don't really know where it is, or have access to it. And there aren't really any evacuation procedures for when there's a big problem that normally would require evacuation, so we can't just set off the fire alarm and leave like that.\"\n\n\"[[You know this facility better than I do. Any thoughts on where to start?|MainLoopIntro-PickAPath]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria hello there",
			"In spite all common sense making it very clear that this is going to be a very bad idea, you decide to walk up to this person and initiate a conversation with them.\n\nAs you walk past Percival, you notice his expression contort into one of some shock, as you demonstrate your brazen disregard for his suggestion to act natural.\n\nIf you were hoping to scare off this person through shock tactics, however, that hasn't worked.\n\n\"Oh good, you've arrived! I was wondering if you were going to turn up!\"\n\nTheir enthusiasm does strike you as a little bit unexpected. But it's too late to turn back now, and they [[haven't stopped speaking to you|Cafeteria meet Dr Spreewald]]",
			[]
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
			"YourRoom arrival",
			"Percival starts to turn the wheel on the outside of the room of your door, and you watch all the deadbolts holding the door closed get released. From here, it feels as if you're about to enter a bank vault, not a simple en-suite room. After he's done turning the wheel, you help him to open the door.\n\nIt looks like your room might have had a visitor whilst the two of you were away. Either that, or the light is one of those fancy power-saving lights that are on a timer and turn off automatically. Either way, the two of you are greeted with a very dark room.\n\nUnfortunately, you don't remember where the lightswitch is.\n\n\"[[Percival, would you mind helping me find the lightswitch?|YourRoom reentry]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu maze 02",
			"Percival's voice fades away as you start to run.\n\nYou reach another junction.\n\nWhere now?\n\n[[Left|deja vu maze 2]]\n[[Right|deja vu maze end]]\n[[Forwards|deja vu maze 4]]",
			["noreturn","maze"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu maze 01",
			"Percival's voice fades away as you start to run.\n\nYou reach another junction.\n\nWhere now?\n\n[[Left|deja vu maze 1]]\n[[Right|deja vu maze 2]]\n[[Forwards|deja vu maze 3]]",
			["noreturn","maze"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria make excuses",
			"Before Percival is able to look at the clipboard to find out what's next, the stranger answers your question for you, whether you wanted them to do that or not.\n\n\"No need to check, the next thing on the itinerary is for me to formally introduce myself to you\".\n\nPercival finishes looking at his clipboard, and tries to hide a slight bit of fear, as he's forced to agree.\n\n\"Ah. Yeah, that does check out.\"\n\nFrom your current position, seated at a table, it's not exactly easy for you to quickly get up and leave. And if you did at this point, it would definitely be rather suspicious.\n\nSo you may as well [[let this person introduce themselves|Cafeteria meet Dr Spreewald]].",
			[]
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
			"deja vu hide gas",
			"You say nothing.\n\nYou hear Percival making his way out of the room.\n\nYou're safe.\n\nYou hear a gentle *pshhhhh* coming from various points in the walls.\n\nSmoke fills the room.\n\n[[And everything fades to black|BriefingRoom2-KO]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria entry",
			"You follow Percival into the cafeteria, if it can truly be called that.\n\nIt looks more like a meeting room that's being used as the lunch room for some sort of corporate event, with an excessive amount of coporate catering-style sandwich platters.\n\nHowever, you aren't the only people in the room. There's someone else in there, in a somewhat scruffy-looking labcoat, tucking in to a sandwich, on the other side of the room.\n\nPercival murmurs to you that you should probably act natural.\n\nWith this in mind, what are you going to do now?\n\n[[Not acknowledge the other person, and get some food|Cafeteria eat]].\n\"[[Hello there!|Cafeteria hello there]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria meet Dr Spreewald",
			"\"I guess I should introduce myself, shouldn't I? My name is Dr. Spreewald, but please, call me Cillian.\"",
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
			"deja vu soundsLikePercival",
			"\"Oh.\"\n\nYou can hear the disappointment in Percival's voice. He was probably expecting to hear something *slightly* more interesting.\n\nEither way, that seems to have answered that question, as he looks like he's about to [[get back to the task at hand|deja vu percival leave]].",
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
			"deja vu endless return 2",
			"You keep going.\n\nEventually, your hands stop to feel the walls to either side of you.\n\nBut the floor's still there, right?\n\nThere's something you can walk on, right?\n\n[[So you can keep walking back, right?|deja vu endless return 3]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"YourRoom theHint",
			"You take the postcard and turn it around, whilst Percival continues to shout endless expletives and frantically search for an exit.\n\nThe handwriting on the other side is the same unfamiliar handwriting.\n\n*Don't hide, try again and find me!*\n\nIt looks like a hint of some description. Whoever put this here, and trapped you in your room, clearly wants you to find them.\n\nBut how are you going to 'try again' when you're cornered?\n\nAs you start thinking about this, Percival collapses from exhaustion, probably because he isn't used to frantically searching for an exit without any success. Now that he's no longer causing a racket, you start to hear something you weren't able to hear earlier.\n\nA very distinctive beeping sound.\n\nAnd it's coming from [[the present|YourRoom the bomb]].\n\n{if:pAny(\"YourRoom PercivalAngery\")}{Part of you is starting to regret wanting to quickly get killed again to undo that unpleasantness with Percival.}",
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
			"There's just a single piece of A6 paper inside, bearing three handwritten lines.\n\n*Perhaps in another timeline. Such is the nature of our work.*\n\n*Farewell.*\n\n*- Dr A. Albert*\n\nHowever, as far as you are concerned, you get the impression that this is **the end** of this chain of events, and you can carry on with your life as usual.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"elsewhere-2",
			"You prepare for the worst as you unfold the piece of paper and start reading it.\n\n*Greetings.*\n\n*I would like to apologize profusely for the theatrics when bringing you here.*\n\n*Yes, I know, sleeping gas is far from the most pleasant method of welcoming you here, however, we have to take these sorts of precautions with the whole 'top secret' nature of this place. Personally, I would have preferred it if we could have just told you the address and allowed you to make your own way here. But that's life, I suppose.*\n\n*I should also mention that, by being here, you are officially bound by all that legislation that prohibits any discussion of the stuff you're doing here. Officially, you're on a residential training bootcamp for creating algorithms to efficiently traverse directed graphs and other sorts of techno mumbo-jumbo that nobody really understands anyway.*\n\n*So, you're probably wondering what's happening here. This is understandable. In short, you'll be helping us out with some experiments into the nature of reality itself. Yes, you might get very confused by everything that's going on, but that's generally what happens here.*\n\n*Now, once you've read this, we'll bring you to the briefing room where we'll explain everything that's going on. And yes, everyone in this facility will be referring to you as 'Subject C' from now on, mostly because we haven't let the staff know your name, and mostly because it's easier to do our work this way.*\n\n*Thank you, once again, for your participation*\n\n*- Dr. A. Albert*\n\nAs you finish reading this slip of paper, you can hear some [[knocking|elsewhere-3]] on the door, and a muffled, unfamiliar voice calling for a \"*Subject C?*\".",
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
			"Start",
			"It's an ordinary morning for you.\nNothing wacky and/or uncharacteristic, just the same stuff you usually experience.\n\nOver the radio, you can hear a discussion about the Mandela Effect.\n\nSuddenly, there's a noise at the door.\n\nDo you [[investigate it|Start2]], or do you [[keep listening to the radio|StartMandelaEffect]]?",
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
			"BriefingRoom2-alreadyToldYou",
			"Percival looks as if he's about to kick himself for not making the not-so-obvious connection there.\n\n\"So, you're saying that the reason for you acting strangely earlier was because you already had met me and had already had that projector blow up on you?\"\n\nYou confirm that yes, that is basically what happened.\n\nHis expression shifts to a gravely serious one.\n\n\"You know what this means, right? Someone is clearly trying to kill you, and, even worse; **they're trying to get me fired!**\"\n\nPercival's employment status is honestly the least of your concerns right now, but, at least it appears that he's firmly on the side of 'not wanting to kill you', which is a good thing.\n\n\"[[So, you going to help me to not get killed?|BriefingRoom2-goingToHelpMe]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"plans and such 3",
			"sample content",
			["name","pending"]
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
			"BriefingRoom1-tryTheDoor",
			"You rush to the door.\n\nBut, just like the door you used to leave the bedroom, there doesn't appear to be any way for it to be opened from the inside.\n\nThere's no signs of anyone outside, and, try as you might, the door won't budge.\n\n[[But the film continues to play|BriefingRoom1-5]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-lunchtime",
			"Percival buries his face in his palm, and his voice gives you the unmistakable impression that he's given up all hope on working out what's going on in your head.\n\n\"What does throwing a projector out of a window have to do with your ability to get lunch?\"\n\n\"[[Well, it isn't as if I'd be able to eat lunch if that bomb had gone off in our faces, y'know?|BriefingRoom2-itsABomb]]\"",
			["lunch"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-callingBluff",
			"Of course! Who else could be the culprit but Percival? He's obviously bluffing, so the look on his face is going to be priceless when he realizes that you know that it's him!\n\nAt least, that's what your train of thought was telling you. Which his expression, of genuine apathy and confusion, certainly isn't corroborating.\n\n\"No? You told me it was a bomb. And, considering the other recent explosions, I'm inclined to believe your claim that it's a bomb.\"\n\nPerhaps you should consider trying to make sure you actually know what you're saying when attempting to make an accusation instead of just throwing accusions out wildly.\n\n\"I know, I know, you're looking for someone to accuse right now, I get it. But I've never met you before, so why would I have any reason to kill you? And why would I be in the same room as the bomb as it would go off? And that's before we factor in that, if you were to die on my watch, the cleaning costs come out of my pay, and I get one step closer to getting fired!\"\n\nIt looks like we can safely mark Percival as not being a suspect. He might be a complete idiot, but he's probably not stupid enough to blow himself up or allow himself to get left with the cleaning bill. Perhaps you should explain to him how you actually knew it was a bomb.\n\n{if:pAny(\"deja vu been there already\", \"deja vu new hat\")}{\"[[Well, you remember that conversation we had earlier on, and you got confused about me remembering things?|BriefingRoom2-alreadyToldYou]]\"}{else:\"[[Because it already killed me.|BriefingRoom2-alreadyKilledMe]]\"}\n\"[[Because you just told me.|BriefingRoom2-callingBluff]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-alreadyKilledMe",
			"You try to explain everything that happened to you so far, with the briefing and the explosion and the finding yourself back in the room and then everything happening again in the same order.\n\n\"So, what you're telling me is that someone's trying to kill you, right?\"\n\nThis appears to be the case, so you confirm that it is.\n\n\"And that means, because I've been told that I'll get fired if someone else dies on my watch... **SOMEONE'S TRYING TO GET ME FIRED!**\"\n\nPercival's employment status is the least of your concerns at this particular point in time, but, at very least, he obviously has a reason for not wanting you dead, which is probably a good thing.\n\n\"[[So, you going to help me to not get killed?|BriefingRoom2-goingToHelpMe]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom1-5",
			"*\"Thing is, if you were going to be of any use to us, you would have done something else instead of sitting around and watching this cartoon. Oh well.\"*\n\nYou're taken by surprise at that almost-instruction.\n\n*\"But I suppose that those bikers had to get caught in the crash in order for them to be warned about it, didn't they?\"*\n\nBefore you can decipher that statement, you notice a very bright, very warm, flash coming from the projector.\n\nHeading straight towards you.\n\n[[And everything fades to white|movedElsewhere]].",
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
			"BriefingRoom2-itsABomb",
			"Percival's face turns pale as soon as those words reach his ears.\n\nHe turns away from you and starts muttering to himself, trying to process what you've just told him.\n\n\"O-o-oh... oh no... i-i-is this... no, it can't be...\"\n\nBased on everything he's told you so far, it sounds as if he has less of an idea about what's going on than you do. But this sudden fear definitely appears genuine.\n\nEither way, it sounds like he's probably just going to keep muttering to himself unless you help him to put two and two together.\n\n\"[[So, what are you muttering about?|BriefingRoom2-muttering]]\"\n\"[[Did somebody not give you the memo about the bomb?|BriefingRoom2-notGotTheMemo]]\"",
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
			"BriefingRoom1-2",
			"You sit down and look towards the projection.\n\nThe text is replaced by a title screen of sorts for something that looks like a rather old, sepia-toned, edutainment film, on the subject of *Understanding What May Happen and Ensuring The Right Things Happen*, accompanied by the sort of cheesy badly-preserved orchestral score you'd expect to hear alongside an old cartoon.\n\nThe scene quickly shifts to a cartoon of some bikers riding their bikes across a road.\n\nA somewhat tinny voiceover, with an american newsreader-y accent, started speaking.\n\n*\"Suppose there was a group of 100 bikers, travelling together, as they normally would, down a highway. When, suddenly...\"*\n\nThe cartoon bikers suddenly ground to a halt, with a very exaggerated braking motion.\n\n*\"...they reached a fork in the road!\"*\n\nThe viewpoint suddenly shifted to the bikers' collective perspective, offering a view of a junction in the road, with a road sign pointing to the left saying 'This-a-way', and another road sign pointing to the right saying 'That-a-way'\n\n*\"Now, being a tight knit group, the bikers figured it would be best to work out as a group whether they wanted to go this-a-way, or that-a-way. After all, they wanted to stick together, and they could only do that if they all took the same route as each other.\"*\n\nIn time with the voiceover, the camera went back to the initial perspective, depicting all the bikers suddenly getting off their bikes and then having a civil discussion with each other.\n\n*\"Eventually, they all decided to go this-a-way, and then they hopped back onto their bikes, and were on their merry way, going this-a-way.\"*\n\nJust like before, they hopped back onto the bikes and resumed biking along, down the left route. [[The camera didn't follow them|BriefingRoom1-3]].",
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
			"BriefingRoom1-1",
			"You step into the room, and you hear the door slam shut behind you.\n\nOn the positive side, you are free from having to listen to Percival's voice.\n\nOn the negative side, you are now all alone again, which makes you think that something's not right.\n\nThe room has many rows of tables, with benches behind them, looking somewhat like a lecture hall.\n\nThe rear wall, behind those tables and benches, has a large window, overlooking an empty warehouse-like room.\n\nOn the front most desk, there's a projector, projecting some text saying '**YOUR ATTENTION, PLEASE**' onto the front wall.\n\n[[May as well find out what all the fuss is about.|BriefingRoom1-2]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"YourRoom reentry",
			"{if:pAny(\"YourRoom PercivalAngery\")}{Percival looks at you with a sense of some superiority.\n\n\"Where would you be without me, hmm?\"\n\nYou decide it would be best to not answer, and to just go into the room with him.}{else:\"Yes, I do! I'll show you where it's hidden, follow me.\"\n\nThat comes as a bit of a relief. You follow Percival into the room, and let him show you what to do.}\n\nHe goes a bit further into the room, and you can hear him fiddling about with something.\n\n\"What you're supposed to do is... this!\"\n\nYou notice that he's turned the light on.\n\nUnfortunately, you can't help but hear the sound of a door being slammed shut behind you, followed by a noise that doesn't sound completely unlike a blowtorch.\n\n[[You come to the realization that the two of you are now cornered|YourRoom cornered]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"YourRoom PercivalAngery",
			"{if:tAny(\"maze\")}{\"And I'm *toootally* sure that you totally know where you're going, don't you?\"\n\nYou genuinely didn't expect Percival to hit you with sarcasm like that. It honestly stings a bit.}{else:The silence from Percival tells you all you need to know about how much he appreciated your 'clever' comment.}\n\nPart of you now wants yourself to get killed again, mostly to undo how awkward things are probably going to get from here.\n\nThe rest of your journey is made in silence.\n\nEventually, Percival stops, with an apathetic \"We're here\", [[right outside your room|YourRoom arrival]]",
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
			"YourRoom senseOfDirection",
			"He lets out a slight chuckle.\n\n\"Do you really think that I, Percival, he who checks off the checklists, who files the files, and lists the to-do lists, would be foolish enough to get lost!? No! I always ensure I write down my directions in full, and follow them all to the letter!\"\n\nHe makes a gesture towards his clipboard as he's saying that, as if to give off the impression that he is too organized to get lost.\n\nHow do you want to continue the conversation?\n\n\"[[So, what you're saying is that without that clipboard, you'd be completely lost?|YourRoom PercivalAngery]]\"\n{if:not(pAny(\"YourRoom whyKill\"))}{\"[[On an unrelated topic, why do you think someone's trying to get you fired by killing me? Sounds a bit convoluted to me.|YourRoom whyKill]]\"}\nOr do you just want to [[get to your room|YourRoom silent treatment]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria",
			"Percival quickly checks his watch, and his expression lights up with some glee.\n\n\"Well, it looks like we'll be able to get there exactly on schedule! Just follow me.\"\n\nOn that note, he opens a door on the opposite side of the room, which you hadn't noticed before now, and escorts you into a completely different hallway. This hallway looks more like the sort of drab corporate thing that you'd expect to see in a generic office block, yet it's still just as nondescript as the hallways from earlier on.\n\n\"Technically I shouldn't be using this route, but it's just a few doors down this hallway\", Percival explains, as he guides you through the hallway.\n\nHowever, before any attempt at smalltalk can be made, he goes through a door clearly marked with the words 'CAFETERIA'.\n\nYou may as well [[follow him in|Cafeteria entry]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MainLoopDeath",
			"When you wake up, you realize you're on the ground of the briefing room, in front of Percival, who's looking at you withsome serious concern.\n\n\"Are you okay? You just fainted out of the blue. I guess that trying to save yourself from dying probably can have that effect on some people. Do you need a hand up?\"\n\nHe sticks his arm out for you, and you allow him to help you back onto your feet.\n\nYou take a moment to re-orientate yourself. Ah yes. Percival had just offered to help you to avoid dying, didn't he? Not that it really helped, seeing as you probably just died again.\n\n\"[[I think I just died again|MainLoopDeath-IDiedAgain]]\"\n\"[[Sorry, I've completely forgotten what you were talking about. Mind repeating it for me?|MainLoopIntro-PickAPath]]\"",
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
			"MeetPercival-RoomService",
			"This mysterious individual clearly doesn't seem too happy about being asked if they're here for room service.\n\n\"***Room service!?*** Me!? Percival!? He who checks off the checklists!? Mistaken for a mere customer service tool!?\"\n\nThe nasal tone of his voice still takes you by surprise. But at least you now know who's at the door.\n\n[[\"So, Percival, why are you here if you're not here to take my breakfast order?\"|MeetPercival-Explained]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-goingToHelpMe",
			"Percival's expression looks somewhat defeated.\n\n\"I guess I probably should.\"\n\nThis comes as a bit of relief for you. You might have no real idea about what's going on here, but at very least Percival might have a vague idea about what to do. Sure, he might turn out to be completely useless, but, he's probably almost better than nothing.\n\n\"[[So, where's the exit?|MainLoopIntro-whereExit]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"YourRoom cornered",
			"Percival suddenly runs to the door, and tries to get the door to move.\n\nAs he comes to the realization that he can't open it, he lets out a stream of expletives that you never expected to hear from him.\n\nHe runs to the curtains opposite the door, throws them open, and lets out another stream of expletives when he realizes that there's only a wall behind them, instead of a window.\n\nIt's pretty clear that there's no point in talking to him, as it doesn't look like he's going to be able to say anything coherent.\n\nYou look around the room to see if there's anything of any importance that wasn't there earlier on.\n\nThere is.\n\nThere's a giftwrapped present on the bed, with a plain white postcard sellotaped onto it, with the words 'SUBJECT C' scribbled onto it in some more handwriting you don't recognize.\n\nAs you can't see anything else of any importance, you may as well [[take a closer look at that present|YourRoom theHint]].",
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
			"MainLoopIntro-PickAPath",
			"Percival takes a look at his clipboard.\n\n\"Lets see here... there might be something on here that's of some use... ah ha!\"\n\n\"On the intinerary, it says that I'm supposed to escort you to the cafeteria next. I'd prefer this option myself, because that's what I'm supposed to be doing.\"\n\n{if:pAny(\"Cafeteria\")}{You remember going there already. The food was decent, but the whole 'exploding' stuff did leave a sour taste in your mouth.}{else:It definitely sounds like it could be a trap, but there's definitely a good chance of finding some answers there.}\n\n\"Lets see what else there is... I guess I could bring you back to your room, where you could wait out the lockdown in relative comfort.\"\n\n{if:pAny(\"YourRoom\")}{You remember the complete-lack-of-care package that someone left in your room. Hiding definitely isn't an option for you. But it looked like the culprit didn't want to hide either.}{else:This option sounds like it could at least give you some time to process everything, and to actually get around to trying out that bed.}\n\n\"Or there are the research laboratories, and I highly doubt that anyone would be stupid enough to risk destroying all of that equiment.\"\n\n{if:pAny(\"ResearchLabs\")}{Unfortunately, they clearly were stupid enough to do that, and you were stupid enough to underestimate their stupidity.}{else:This sounds like a rational choice of a place to hide. If nothing else, you might be able to find out a bit more about any ulterior motives going on here}\n\n\"But if there's any other suggestions you have, I'd like to hear them.\"\n\n{if:pAll(\"CafeteriaDeath\",\"RoomDeath\",\"LabDeath\")}{\"[[Actually, yes, I have a plan|MainLoop-end]]\"}{else:You can't think of anything right now. Perhaps after you get some more information about everything going on, you might have a better idea of what to do.\n\nWith this in mind, where do you want to go?}\n\n{if:not(pAny(\"Cafeteria\"))}{\"[[I'm hungry, so let's go to the cafeteria|Cafeteria]]\"}\n{if:not(pAny(\"YourRoom\"))}{\"[[I guess that I may as well wait it out in that room I woke up in|YourRoom]]\"}\n{if:not(pAny(\"ResearchLabs\"))}{\"[[Chances are that those labs might be too important to destroy, so that sounds like a relatively safe bet to me.|ResearchLabs]]\"}",
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
			"You wake up in an unfamiliar rectangular room, on your back, looking up at a softly flickering fluorescent light on a whitewashed ceiling, illuminating the room.\n\nThere is a rather bland-looking single bed in the room, affixed to the wall. It appears reasonably comfortable, not that you know for sure, seeing as you've woken up on the floor.\n\nThe floor is a very hard, wooden floor. It's certainly not the most comfortable floor you've woken up on. But it could be worse. At least it's not concrete or something like that.\n\nThe walls are completely bare, besides some curtains on the wall opposite the door. There doesn't appear to be any light seeping past the curtains, so you guess that they're probably only there for decorative purposes.\n\nThat's when you realize that there's a door.\n\nIt looks suspiciously like the sort of door you'd expect to see in the deep, staff-only, backrooms of a ship. More of a hatch in the wall than anything else. You think that this might be the exit, but you can't see any obvious way to open it.\n\nThere is a somewhat triangular extrusion from a corner of the room, next to the exit door. This extrusion also has a door, but the door looks much more like a normal door. You come to the conclusion that it's probably an en-suite wetroom shower/toilet room. You don't feel that you need to use it right now, so you resume looking around the room.\n\nIt's at this point when you realize that you're no longer wearing the clothes you had on earlier. Someone decided to put you in a beige prison uniform-esque jumpsuit instead, with a somewhat disconcerting bit of text saying 'SUBJECT C' on it.\n\nYou notice a wardrobe opposite the bed.\n\nAll it contains are even more of the same jumpsuits.\n\nYou start to realize that the offer might have been too good to be true.\n\n{if:tAny(\"death\")}{You can't help but realize that this all feels [[oddly familiar|deja vu-1]].}{else:But you then notice that there's [[a folded piece of paper|elsewhere-2]] on the bed, with your name handwritten on it.}",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"YourRoom boom",
			"And just as quickly as that realization came to you, so does a very bright, very warm, flash.\n\nYou feel it sweep you off your feet.\n\n[[And everything fades to white|MainLoopDeath]]",
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
			"deja vu hide give up",
			"You can hear a very audible expression of surprise from the other side of the door, and the noise of a clipboard being dropped.\n\nThis is promptly followed by the noises of the person on the other side picking up the aforementioned clipboard and trying to regain their composure, and a very muffled \"W-what!? J-just hurry up in there.\"\n\nIt's pretty clear that you've lost your hiding space now, and there's no question that you're definitely in the en-suite.\n\nYou haven't actually gotten around to doing anything, so you may as well [[just leave|deja vu didnt flush]]. \nBut it might be worth just [[flushing the toilet and washing your hands|deja vu leaving toilet]] first, just in case Percival starts to suspect something.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MainLoop-end",
			"Sample Content",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"plans and such 2 actually unused",
			"sample content",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu been there already",
			"He glances back at you, with a look of confusion.\n\n\"No, you haven't been there before. In fact, on this clipboard, I can tell you exactly where you *have* been before, as it's listed here in full.\"\n\nYou suspect that Percival's probably going to start saying the quiet part loud, and he definitely won't listen if you tell him that you've been in there before, so you think that it's in your best interests to just let him keep speaking instead of making a futile attempt to correct him.\n\n\"Let's see here... We received your response confirming your intent to participate here, so a subject retrieval team was sent to retrieve you, which they did. Whilst you were still unconscious, first thing that happened to you here was decontamination and being given that decontaminated outfit, after all, safety first. The science team did give you that injection they're testing, maybe you're disoriented because of that? I doubt it, personally. Anyway, after that, it says here that you were then moved to your room, where you woke up, and I was sent to escort you to the briefing room, which I'm doing now... Not entirely sure how you could have gone to the briefing room already...\"\n\nYour mind starts racing as you try to comprehend all the information Percival accidentally gave you. Retrieval? Decontamination? Injection?\n\nHowever, before you can properly think it through, Percival suddenly stops.\n\n\"Speaking of which...\"\n\nYou notice that he's stopped outside a door labelled with the words [[BRIEFING ROOM|BriefingRoom2 - entry]]",
			["noreturn","beenthere"]
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
			"BriefingRoom2-5",
			"The projector flies through the window just as well as a brick would, breaking the glass with a satisfying **SMASH**, before falling all the way down to the floor of the warehouse, landing with a not-quite-as-satisfying-but-still-relieving **CRUNCH**.\n\nUnfortunately for you, Percival's worked out what you just did as well. And words cannot express just how confused and outraged he currently is, so much so that even he's struggling to actually say the words he's trying to say.\n\n\"W-w-what do you think you're playing at!? Why did you just throw the projector out of the window like that!? Projectors are expensive!? You were supposed to watch the briefing, not defenestrate it!?\"\n\n\"[[Briefing!? You call 'getting blown up' a briefing!?|BriefingRoom2-itsABomb]]\"\n\"[[I save your life and *this* is the thanks I get?|BriefingRoom2-savedYourLife]]\"\n{if:pAny(\"deja vu whens lunch\")}{\"[[Well, now that's done, I suppose it's time for lunch.|BriefingRoom2-lunchtime]]\"}",
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
			"MainLoopDeath-IDiedAgain",
			"Percival is once again concerned.\n\n\"You sure? I just saw you faint on me before we got around to working out how to leave, or, at very least, get you to not die. And you haven't died yet.\"\n\nYou think it's probably for the best that you don't let Percival know about what just killed the two of you. He's got enough going on as-is without you causing him an existential crisis as well.\n\n\"[[So, what were we talking about again?|MainLoopIntro-PickAPath]]\"",
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
			"deja vu endless give up",
			"You turn around.\n\nBut you can't help but notice that there isn't any light in the corridor behind you, instead, all you can see is an expanse of darkness, looking just as endless as the length of the corridor you still need to walk down.\n\nYou come to the realization that you aren't supposed to be here.\n\nDo you [[walk back, through the darkness|deja vu endless return]]?\nOr do you [[try to keep going down the corridor|deja vu endless corridor]], even though you can't see any end to it?",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-notGotTheMemo",
			"Percival stops muttering to himself and looks back at you.\n\n\"Well, there have been a few unexplained explosions recently, but, here's the thing; they're all unexplained. Only thing I've been told is 'don't go near anything that might be a bomb'. As you can tell, that's been some really helpful advice, seeing as that projector apparently was a bomb...\"\n\nHis voice trails off as he's saying this, as if he's gone off on his own train of thought.\n\nSuddenly, as if he's just noticed an elephant loitering in the corner that nobody else has, he snaps back to reality and asks you the really obvious question.\n\n\"Say, how *did* you know it was a bomb?\"\n\n{if:pAny(\"deja vu been there already\", \"deja vu new hat\")}{\"[[Well, you remember that conversation we had earlier on, and you got confused about me remembering things?|BriefingRoom2-alreadyToldYou]]\"}{else:\"[[Because it already killed me.|BriefingRoom2-alreadyKilledMe]]\"}\n\"[[Because you just told me.|BriefingRoom2-callingBluff]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"YourRoom",
			"Percival appears somewhat energized now that he has an idea about what he's doing next.\n\n\"That sounds safe enough. Follow me!\"\n\nHe starts to lead you out of the room, and back into those nondescript corridors from earlier. You know it might take some time for Percival to lead you back to your room, so, whilst you have some downtime, why not try asking him for some further information?\n\n\"[[So, why exactly do you think someone's trying to get you fired by killing me? Sounds a bit convoluted to me.|YourRoom whyKill]]\"\n\"[[How are you able to work out where you're going in these hallways?|YourRoom senseOfDirection]]\"\nOr do you just want to [[get back to your room|YourRoom silent treatment]] without making a huge fuss about it?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"YourRoom whyKill",
			"Percival looks a bit bothered by this question.\n\n\"I suppose that is a good question. But something here doesn't really add up. Why would someone want to kill the test subjects? They clearly want the test subjects alive, otherwise, they would have probably stopped with the first one. And Dr. Spreewald *did* say something about needing you to remain alive, and he said I'd be responsible if another of his subjects died...\"\n\n{if:tCount(\"spreewald\")==1}{You don't recall hearing anything about a Dr. Spreewald before now. But whoever this person is, they might know something that Percival doesn't.}{else:That person *again*. At least it still sounds like they might be of some relevance in this timeline as well.}\n\nUnfortunately, before you can press Percival for some more tidbits of information, he lets out a \"We're back!\", as the two of you [[reach your destination|YourRoom arrival]].",
			["spreewald"]
		)
	);

	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

