//HECC UP output (as of 29/01/2021) (Rachel Lowe, 2021)

var startingPassageName = "Start";

function getHECCED(){

	theHeccer.addPassageToMap(
		new Passage(
			"Start4",
			"You open the envelope, and the paper inside it feels similarly expensive.\n\nThe text on that envelope is, again, entirely handwritten, from the line at the top addressing you by name, to the signature at the end.\n\n*Greetings. If you are reading this, we at the Institution for Practical Parallelization (the IPP) are offering you the possibility of helping us out with some of our research into the practical applications of theoretical parallelism in a wide range of use cases, from the simple 'gaining knowledge faster', to the more complex tasks of understanding the outcomes of decisions before they're made. And you have been identified as an individual who has potential to do great things, for both yourself, and for society as a whole.*\n\n*Now, I can't get further into details at this point in time, as all of the really juicy information is hidden under layers after layers of NDAs and legislation and paperwork and all of that jazz. Even telling you about the very existence of this institution is in a legal grey area*\n\n*What I can tell you is that we are offering you a 5-day placement here. You will be staying at the institution during this placement, but no expense has been spared on the room and board. Yes, you will be expected to work, but I can assure you that it'll be less soul-destroying than the sort of work you already do in your day-to-day life. And, to sweeten the deal, we will compensate you for your time; you will be paid a healthy sum of money, and you may get the opportunity to help us out further in the future.*\n\n*Regrettably, we cannot give you any further information, and we can't even tell you when this study will be, due to the aforementioned legal problems*\n\n*If you do want to participate in this study, please use the included signed addressed envelope to send us your response. All we need is a single-word 'Yes' or 'No'.*\n\n*Thank you for your attention, and we hope for your participation*\n\n*- Dr A. Albert, OBE*\n\nYou are taken aback by the somewhat unexpected contents of this letter.\n\nYou re-read it several times, to make sure you read what you thought you read.\n\nBut those words are all present on there.\n\nOf course, you have no reason to believe anything on this piece of paper.\n\nBut why would anyone go through all this trouble for a simple 'yes' or 'no'?\n\nAnd the payment sounds like a nice bonus, especially considering those bills piled up by your doorstep.\n\nEverything seems legit.\n\nBut how are you going to reply?\n\nAre you going to reply with a [[Yes|StartYes]], or are you going to send a [[No|StartNo]]?",
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
			"Albert end been 5 days",
			"\"Recall the initial summons you responded to. It stated that this 'placement' here would last five days, did I not? Now, if you had truly died four times, that would imply that this is potentially the fifth iteration of this day you have lived through, does it not? You have additionally fulfilled the purpose which you were summoned here for. Therefore, I would say that you are done here.\"\n\n\"[[What do you mean by 'Im done here'?|Albert end done here]]\"",
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
			"Spreewald end going to shoot himself",
			"He points the gun at his head.\n\n\"You see, you were here to test some serum that should allow you to enter another timestream following a fatal event, armed with the knowledge of the impending danger, so it can be avoided. You have just proven that this serum, which I have administered to myself, works.\"\n\n\"[[Well, you've found out what you wanted to find out, so why go through these theatrics?|Spreewald end why shoot]]\"\n\"[[So why didn't you do this in the other timestreams?|Spreewald end plot hole alert]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd left",
			"Subject A opens his left hand. Nothing's there.\n\n\"Oh no, looks like you got it wrong! Oh well. Guess you'll just have to try again next time!\"\n\n{if:pAny(\"PEnd right\")}{That cheat! Both of his hands were empty!\n\n}Before you can protest, an announcement plays over the intercom.\n\n#**FACILITY SELF-DESTRUCT IN... 5... 4...**\n\n[[You glare at Subject A's smug expression|PEnd loop light]].",
			["noreturn"]
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
			"\"Hello, Subject C.\"\n\nThe very nasal voice of the person greeting you takes you aback. It didn't sound quite like that from the other side of the door.\n\nThey're dressed up in the sort of garb you'd expect some sort of stereotypical caricature of a health-and-safety inspector. A cheap-looking black suit, like the sort of suit worn by someone who takes themselves way too seriously, with overly-polished shiny black shoes, yet contrasted by a very bright high-vis vest. They appear to have an ID badge on their high-vis vest, but you can't read it from where you are. You can see a painstakingly manicured, yet somewhat subtle, moustache on their face, as well as a pair of browline glasses in front of their eyes. Those glasses could have given off an air of quiet sophistication, if it wasn't for the rest of their outfit, and that somewhat gratuitous pair of cheap-looking safety spectacles that they're wearing over their glasses. They're also wearing a white hard hat on their head, with the letters 'IPP' written on the front of it. They're cradling a clipboard with their left arm, and they have a fountain pen in their right hand.\n\nThey clearly work here, and they're clearly here to take you to the briefing room mentioned in that letter from earlier.\n\nAnd they're clearly expecting you to say something.\n\n[[\"Who the hell are you?\"|MeetPercival-whois]]\n[[\"Where am I?\"|MeetPercival-whereis]]\n[[\"Hello? Room service?\"|MeetPercival-RoomService]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria run turn back",
			"As you start to calmly walk away from the dead end, turning around the corner, Percival catches up with you.\n\nHe's also trying to go around the corner.\n\nYou only realize he's there when you feel him crashing into you.\n\nThe two of you [[fall down|Cafeteria run tripwire]] as a result.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd reactor offered a wager",
			"He's walking towards you, with both of his hands visible and closed.\n\n\"Oh really? Well then, how about a wager? If you win, I'll let you stop the self-destruct. But if I win, you'll just end up back alive, as you were, a few minutes ago, won't you?\"\n\n{if:pAny(\"PEnd loop alive\")}{You don't know if he knows that you were revived just as he was heading off to press the self-destruct button. But even if you were to tell him, he'd just find it hilarious, wouldn't he?\n\n}{if:pAny(\"PEnd brute force\")}{You know, from experience, that attempting to beat him up isn't an option.\n\n}\"[[Fine, I'll bite. What's your wager?|PEnd reactor the wager]]\"",
			[]
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
			"prank im going home",
			"You walk off the stage.\n\nYou can hear some confused applause in the distance as you vacate the limelight.\n\n[[You may as well work out where your actual clothes went|prank ending change clothes]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end hes dead",
			"There is a very loud **BANG!**\n\nCillian's now lifeless body collapses in front of you, in a pool of blood.\n\nYou have no idea if his harebrained scheme will work or not. Either way, he's gone.\n\nRegardless, it appears that there isn't really any reason to stick around here. It doesn't look like anyone will stop you from leaving, and you may as well depart this current bloodbath before someone starts asking questions.\n\n\"[[Well, Percival, I guess we probably should leave.|Spreewald end lets go]]\"\n\"[[Actually, there should be another bullet left. I'm going after him.|Spreewald end pick up gun]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Archives boom",
			"Before you can finish cursing Percival for his stupidity, you feel yourself engulfed by a bright, warm, flash.\n\n[[And everything fades to white|MainLoopDeath]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"S T-pose plan",
			"Of course! He'll definitely be rather unnerved by your knowledge of him which you shouldn't have!\n\nHe definitely looks and sounds rather confused right now.\n\n\"Oh. This is honestly unexpected. The experiments weren't supposed to have started yet.\"\n\nWait, the experiments weren't supposed to have started?\n\n\"[[Then what else do you call 'murdering me 4 times over'?|S blatant]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end silent treatment",
			"\"What's the matter? Cat got your tongue? Or, perhaps, are you trying to hope I don't realize that you've worked something out, hmm?\"\n\nYou [[brace yourself|Spreewald end he knows]] for what he's about to say.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end shoot",
			"Dr Albert's body collapses to the desk.\n\nCillian lets out a laugh, and looks back at you, now pointing the gun in your general direction, acting with some mock sadness.\n\n\"Now, I brought three bullets with me. But it looks like I've been caught anyway, and I probably need a few more to escape in one piece seeing as I've apparently been caught. Such a shame, isn't it?\"\n\n\"[[Is this the part where you shoot me?|Spreewald end part where he doesnt shoot you]]\"\n\"[[I dread to think what you're going to do next.|Spreewald end dreading whats next]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd reactor right thats it",
			"He finds your outrage amusing.\n\n\"Oh, I'm sorry, I *tootally* didn't know that this version of you would also be so frustrated at getting killed so frequently, oopsie!\"\n\nHe looks at the emergency override button.\n\n\"I wonder would would happen if I were to break this button?\"\n\n\"[[Don't do it!|PEnd reactor offered a wager]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end why shoot",
			"\"It's simple, really. I'm taking this knowledge to somewhere else, where only I know that it works. I can't keep it in this timestream, because the cat's out of the bag here. So, I may as well take care of this loose end. Speaking of which, you might want to stand back.\"\n\nHe [[cocks the hammer|Spreewald end cocked hammer]] of the revolver.\nAre you going to bother trying to assert that [[he won't get away with this|Spreewald end wanna be a hero]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"A ur murderer",
			"\"I will admit that I do not particularly appreciate the... methods being used by some of my colleagues. But to stoop to their level to stop that? Heavens no! I'm merely seeking to use whatever means I can to obstruct their use of those methods in the first place.\"\n\nLooks like he actually has some sense of decency. That was unexpected.\n\n\"Additionally, even if I *did* want to stoop to their level; I would just seek to eliminate them directly, rather than targeting their test subjects. And even if I wanted to eliminate you personally, I simply do not have a reasonable window of opportunity to do so, and that serum could complicate matters somewhat.\"\n\n\"[[So what you're telling me is that someone else is the killer, right?|A someone else]]\"",
			["serum"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd accept fate",
			"What's the point in trying?\n\nYou're going to die here. But you'll probably just end up in the past, to before when you knew the truth about Subject A; to a time where you can escape, with this knowledge!\n\nYou take this as an opportunity to [[meditate|PEnd accept meditate]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"S its albert",
			"Of course!\n\nHe ultimately controls this facility, so, if anyone is likely to be able to pull some strings to get someone murdered for their own research, it would have to be him!\n\nJust as you're having this epiphany, you can [[hear an intercom crackling into life|Albert End 1]].",
			["noreturn"]
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
			"Cafeteria eat",
			"After taking a look at the labels on the sandwich platters, and finding a sandwich that you're prepared to eat, you sit down and tuck in.\n\nThe sandwich doesn't taste particularly good or bad, it just tastes like a very middle-of-the-road sandwich.\n\nHowever, as soon as you're done eating, you notice that other person, who you tried to ignore, is walking up to you and Percival, and looks like they're about to talk to you.\n\nWhat's the plan now?\n\nContinue to [[act natural|Cafeteria meet Dr Spreewald]], and hope that they aren't going to delay you for too long.\n\"[[Say, Percival, where are we supposed to be headed off to next?|Cafeteria make excuses]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd caught",
			"Percival chuckles to himself.\n\n\"I've been rumbled, haven't I? Yep, you got me. Now, I know you're probably feeling betrayed, I know you're planning on calling security. But I can tell you that doing so won't be necessary. At all.\"\n\nThis was a somewhat unexpected heel-turn from Percival.\n\n\"[[Wait, so what exactly was your motive?|PEnd motive]]\"\n\"[[Why is there apparently no need to call security?|PEnd dont call]]\"",
			["noreturn","trueending"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd accept meditate",
			"You ponder what the nature of your existence actually is.\n\n#**EMERGENCY SELF-DESTRUCT SEQUENCE ENGAGED. THANK YOU FOR YOUR EFFORTS IN THIS TIMESTREAM.**\n\nYou consider what lessons you may be able to apply to your other timestreams.\n\n[[You feel calm.|PEnd accept meditate 2]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Albert end 2",
			"Behind the desk, you can see a rather old man, looking disconcertingly pleased with himself.\n\n\"Welcome, welcome! I'm sure you have many questions about what's been happening here. And yes, I am at liberty to answer them.\"\n\nHis warm disposition is somewhat offputting.\n\n\"[[Care to explain why you murdered me four times?|Albert end why kill]]\"\n\"[[Why are you so cheerful about getting caught!?|Albert end why happy]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd accept meditate 2",
			"You feel yourself making peace with this new existence.\n\n#**FACILITY SELF-DESTRUCT IN... 5... 4...**\n\n[[You have gained a sense of inner peace|PEnd loop light]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd subject B",
			"He sighs.\n\n\"B was lucky. The serum they were given didn't work. At all. In every single timestream I've existed in. They have been able to live their life and have it ended.\"\n\n\"[[Wait, what about me?|PEnd what about me]]\"\n\"[[So, do you know how to escape?|PEnd how to escape]]\"",
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
			"Ending1-Hoax",
			"You hear that, allegedly, many people have been receiving handwritten letters from some organization that doesn't exist, promising free money to recipients, akin to a bogus job offer scam. The police have released a formal statement, saying that they don't have any reason to believe that anything illegal has happened, but heavily discouraged replying to those letters.\n\nUnfortunately for you, you've already replied. But your reply was a 'no'.\n\nAs the ad break starts, you promptly put those worries to one side, as you [[investigate the noise at your front door|Ending1-2]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd who is",
			"\"This man had a name, back when he had a life. But that life was stolen from him, on the day when they designated this man as 'Subject A'.\"\n\nYou feel a wave of dread sweep over you. Is this truly your fate?\n\n\"[[What happened to Subject B?|PEnd subject B]]\"\n\"[[Have you ever tried to escape?|PEnd how to escape]]\"",
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
			"YourRoom silent treatment",
			"The two of you walk back to the room in silence.\n\n\"I guess that it is hard to come up with smalltalk in a situtation like this\", Percival remarks.\n\nEventually, the two of you [[come to a stop|YourRoom arrival]], outside of your room.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MainLoopIntro-whereExit",
			"\"The exit? Ah, well, about that...\"\n\nPercival's apologetic tone of voice here doesn't really sit very comfortably with you.\n\n\"Thing is, this facility goes under lockdown when there's supposed to be some experimentation going on. I don't understand the specifics of why myself, probably something to do with ensuring controlled conditions and such. Either way, we're stuck in here for the next few days. There is a master override code, but I don't know what it is or where to find it, so that's not an option. And there aren't really any evacuation procedures for when there's a big problem that normally would require evacuation, so we can't just set off the fire alarm and leave like that.\"\n\n\"[[You know this facility better than I do. Any thoughts on where to start?|MainLoopIntro-PickAPath]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd not a trick",
			"He laughs again.\n\n\"Of course not! I've had my fun with you in this timeline, and I suppose you've earned the ability to delay the inevitable even further..\"\n\nYou're about to open your mouth to protest, when you're interrupted by the intercom.\n\n#**FACILITY SELF-DESTRUCT IN... 5...**\n\n\"Don't dawdle, cheater!\"\n\n#**4...**\n\n[[You run to the override button.|PEnd button countdown 3]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd albert sus A",
			"Dr Albert raises his voice.\n\n\"If I may interject, Percival, the security team has apparently did see you enter the archives shortly after Spreewald left them earlier on this morning. Would you mind explaining what you were doing there?\"\n\nPercival looks like he's trying to comprehend the question, He answers it.\n\n\"I was merely going there to pick up the itinerary for Subject C, because Dr Spreewald told me he left it behind\"\n\n\"[[What happened to you having 'never gone in there before'?|PEnd caught lying]]\"\n\"[[And that documentation is kept in the archives?|PEnd it is really kept there]]\"",
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
			"Cafeteria ohno bomb time",
			"You can hear some more beeping noises. Perhaps somebody left another pager behind.\n\nDo you [[take a look at where the noise is coming from|Cafeteria boom]] or do you just [[ignore it, it's probably nothing|Cafeteria boom]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd brute force",
			"You launch yourself at Subject A.\n\nUnfortunately for you, he's expecting it. It's as if he had already experienced you (or, at least, another version of you) using the exact same attack on him, many times before!\n\nHe instinctively turns around, and launches a well-aimed punch at your kidneys, letting out a laugh as he does this.\n\n\"You fool! I told you there was no point in stopping me!\"\n\nYou collapse to the ground in pain, as he [[walks away|PEnd brute force floored]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria why bother",
			"\"I'm guessing that Percival forgot to explain it to you. You may have guessed that I am one of the researchers here, and I needed some participants for some experiments; which is why you are here.\"\n\nHis story appears to check out.\n\nBefore you can press him for more information, you can hear a sudden beeping noise.\n\nCillian rummages around in his pockets, picking up a pager, pressing a button on it. The beeping stops.\n\n\"Wait here for a moment, I need to sort this out real quick.\"\n\nWith that, he leaves the room.\n\nWhat are you going to do now?\n\nAre you going to [[wait for him to return|Cafeteria wait]], or will you [[leave the room|Cafeteria depart]]?",
			[]
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
			"Percival's voice fades away as you start to run.\n\nYou reach another junction.\n\nWhere now?\n\n[[Left|deja vu maze 2]]\n[[Right|deja vu maze 3]]\n[[Forwards|deja vu maze 4]]",
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
			"PEnd motive",
			"\"Well, I know that you're probably thinking I did it for some grand evil scheme, don't you? Truth is, I don't have one. I was just bored. Bored with this immortality which some people thrust upon me, a few weeks ago. Well, I say a few weeks ago, but truth is, it's been a rather long time.\"\n\n\"[[Immortality? What do you mean by that?|PEnd immortality]]\"\n\"[[Boredom? How so?|PEnd boredom]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd what about me",
			"He chuckles.\n\n\"You're lucky, well, unless 'you' are one of the unlucky ones. You're not stuck at the same point of your existence. After all, you last reawakened in the briefing room, not the bedroom. Eventually, just like a candle, you're going to run out of life to live, yet again. Some times, you found yourself sent back to a point where it was too late for you to avoid your impending deaths. And, every time you woke back up in the past, it appears that the mind you had in that timeline got overwritten. Yes, your 'self' may not die when your body does, but another 'self' of yours is killed instead. I wonder if your current 'self' is going to be allowed to escape when another 'self' of yours dies, or if you'll be one of the unlucky ones, forced to survive infinity within a gradually shortening lifespan?\"\n\nYou're starting to feel rather uncomfortable at the thought of this.\n\n\"[[Is there any way to escape this fate?|PEnd how to escape]]\"",
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
			"Cafeteria run tripwire",
			"You feel yourself hit something as you fall.\n\nSomething which felt like a rather taut rope, quickly breaking under your body weight.\n\nAs you hit the ground, you feel yet another bright, warm, flash engulf you.\n\n[[And everything fades to white|MainLoopDeath]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria entry",
			"You follow Percival into the cafeteria, if it can truly be called that.\n\nIt looks more like a meeting room that's being used as the lunch room for some sort of corporate event, with an excessive amount of corporate catering-style sandwich platters.\n\nHowever, you aren't the only people in the room. There's someone else in there, in a somewhat scruffy-looking lab-coat, tucking in to a sandwich, on the other side of the room.\n\nPercival murmurs to you that you should probably act natural.\n\nWith this in mind, what are you going to do now?\n\n[[Not acknowledge the other person, and get some food|Cafeteria eat]].\n\"[[Hello there!|Cafeteria hello there]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria meet Dr Spreewald",
			"\"I guess I should introduce myself, shouldn't I? My name is Dr. Spreewald, but please, call me Cillian.\"\n\n{if:tCount(\"spreewald\")===1}{You haven't met this person before, so you may as well make a decent first impression.}{else:So this is that 'Spreewald' person you've been hearing so much about recently.}\n\n\"[[So what are you supposed to be doing here?|Cafeteria whats your job]]\"\n\"[[By the way, would you happen to know anything about a bunch of explosions that have been happening recently?|Cafeteria why boom]]\"",
			["spreewald"]
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
			"A blatant",
			"He looks at you with an expression of resignation.\n\n\"Ah. I was wondering where that notebook went. I hoped that the terrible writing in the former half of the notebook would be enough of a deterrent to prevent people from jumping to any premature conclusions. Alas, it appears that even the best laid plans still go awry from time to time.\"\n\n\"[[Are you referring to your plan to destroy your colleague's career by killing me multiple times over?|A ur murderer]]\"\n\"[[Well, what 'pReMaTuRe CoNcLuSiOnS' are you referring to?|A premature conclusion]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"A bluff",
			"\"Is that the case? Pray, tell me more. How exactly is he trying to kill you?\"\n\nIt looks like he's playing dumb, so he can't fall into the trap you're trying to set.\n\nSo I guess you may as well force his hand, by pointing out to him that \"[[You already know. In fact, I know that you have all of this written down already, disguised as a draft of a generic rom-com, don't you?|A blatant]]\"",
			[]
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
			"Spreewald end blame percival",
			"\"Oh please, Subject C, ya know perfectly well that Percival wouldn't suddenly ignore his precious itinerary without a reason, wouldn't ya, Percy? There's no point in trying to pretend that you haven't worked it out, ya know?\"\n\nYou [[brace yourself|Spreewald end he knows]] for what he's about to say next.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu maze 2",
			"Another junction.\n\n[[Left|deja vu maze 4]]\n[[Right|deja vu maze end]]\n{if:not(pAny(\"deja vu maze 3\"))}{[[Forwards|deja vu maze 3]]}",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end thanks aka THE SLOUGH TRADING ESTATE ENDING",
			"With that, you leave the facility.\n\nYou find yourself outside, in what appears to be a trading estate, in front of a very boring-looking building. The weather is rather nice.\n\nThe door closes behind you.\n\nIt's at this point where you come to the realization that you have no idea where exactly you are.\n\nOh well. At least you escaped in one piece.",
			["noreturn","welcome","to","slough"]
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
			"Albert end back home",
			"It's an ordinary morning for you.\nNothing wacky and/or uncharacteristic, just the same stuff you usually experience.\n\nYou can't help but feel that something feels a bit odd this morning.\n\nOver the radio, you can hear a discussion about some hoax letters people have been receiving.\n\nSuddenly, there's a noise at the door.\n\nDo you [[investigate it|Albert end home 2]], or do you [[keep listening to the radio|Albert end Hoax]]?",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd albert sus S",
			"Percival interjects.\n\n\"Well, what if he wrote that in his notebook to deflect suspicion from himself? After all, I've had things pinned on me already, and having a scapegoat who can get fired relatively easily, such as myself, is going to be incredibly convenient for a true culprit. And why would I do anything that would get me fired?\"\n\nThis logic honestly checks out.\n\n\"[[So, I guess it's actually Dr Albert.|S its albert]]\"\n\"[[Wait, who said anything about a notebook?|PEnd caught lying]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu endless return 2",
			"You keep going.\n\nEvenutally, you see the light again.\n\nIt's illuminating another dead end.\n\n[[Except this one has a door|prank ending door]].",
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
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Archives notebook 4",
			"You keep reading.\n\n*I might need to keep tabs on Percival as well. He has been acting rather suspicious lately. It's giving me a rather bad feeling. Hopefully I can catch him in the act before he does anything particularly bad for everyone concerned...*\n\nAs if Percival knew that you were talking about something discussing him, you can [[hear him suddenly screaming in fear|Archives percival dun goofed]].",
			["percivalSus"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd brute force floored",
			"As you're writhing on the floor in pain, you hear some announcements over the facility's speaker system.\n\n#**EMERGENCY SELF-DESTRUCT SEQUENCE ENGAGED. THANK YOU FOR YOUR EFFORTS IN THIS TIMESTREAM.**\n\nYou want to get up, you want to stop this from happening.\n\nBut you can't. The pain is too much. All you can do is just [[wait for the pain to inevitably end|PEnd brute force dying]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd left the room",
			"Subject A has left the room, and is on his way to blow up the facility, and everyone in it.\n\nYour mind is racing with newfound questions about the nature of your existence. But there's no time for that now. After all, if you don't do anything right now, you won't have much of an existence left to question!\n\n{if:not(pAny(\"PEnd brute force\"))}{Will you [[attempt to stop Subject A with brute force|PEnd brute force]]?\n}You think it might be a good idea to [[try to find an override for the self-destruct|PEnd follow him]].\n{if:not(pAny(\"PEnd loop alive\"))}{Or will you just [[accept your fate|PEnd accept fate]]?}",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd reactor room",
			"The reactor room is bathed in the menacing red glow of some emergency lighting from above, accompanied by the warm orange glow of what is presumably the reactor from below the walkway you are on.\n\nOn the other end of the room, you can see Subject A, standing in front of a large red button, which used to be held behind a now-shattered pane of glass.\n\nThat must have been the self-destruct button.\n\nTo the right of you, instead of a wall, there is a handrail some distance away from the wall, in front of a gap.\n\nYou try to see if there's any sign of an override button.\n\nThere is one, on a panel filled with many other buttons.\n\nBut Subject A is standing in front of it.\n\nHe's noticed you.\n\n{if:not(pAny(\"PEnd reactor pls move\"))}{\"[[Say, would you kindly move out of the way of that self-destruct button over there?|PEnd reactor pls move]]\"\n}{if:not(pAny(\"PEnd reactor stop\"))}{\"[[Stop right there!|PEnd reactor stop]]\"\n}{if:not(pAny(\"PEnd reactor right thats it\"))}{\"[[Right, that's it, I've had enough of getting murdered for one day.|PEnd reactor right thats it]]\"}",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Archives notebook 2",
			"It's one of those dime-a-dozen guy-meets-girl, girl-meets-other-guy, other-guy-meets-another-guy, that-guy-meets-yet-another-girl, that-other-girl-meets-the-first-guy awkward love pentagon stories. It's certainly in need of a lot of polish, the plot is somewhat incoherent, but, it could be worse. It had potential though.\n\nUnfortunately, when the plot really started to get going, it stopped.\n\nBut it was replaced by [[something particularly intriguing|Archives notebook 3]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Archives notebook 3",
			"Now, this is juicy!\n\nIt looks like Dr Albert really doesn't like {if:pAny(\"Cafeteria meet Dr Spreewald\")}{Cillian}{else:this Dr Spreewald person{if:tCount(\"spreewald\")<1/}{, whoever they are/}}.\n\n*If that renegade upstart keeps trying to render everything I have built my career on completely redundant, I swear I may need to take matters into my own hands. I could probably pin the blame on that Percival fellow in a worst case scenario, but I simply cannot let Spreewald carry on completely unhindered. I guess he probably won't be able to do much if he doesn't have any subjects to test on, though. I suppose I should do something about this.*\n\nMaybe Percival might also be interested in this.\n\nDo you want to [[call him over|Archives summon Percival]], or do you want to [[keep reading|Archives notebook 4]]?",
			["albertSus","spreewald"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd how to escape",
			"\"If I knew, I wouldn't be having this exact conversation with you yet again, wouldn't I?\"\n\nHe has a point.\n\n\"Anyway, I'm bored. And now that the people in this timestream know the truth, I can tell that they're planning to, once again, find the same conclusions about my existence they always do. So, I'm just going to enable this facility's emergency self-destruct this time. I haven't done that in the past few years. Don't try to stop me. You won't succeed.\"\n\nWith that, Subject A turns around and [[leaves the room|PEnd left the room]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"It's an ordinary morning for you.\nNothing wacky and/or uncharacteristic, just the same stuff you usually experience.\n\nOver the radio, you can hear a discussion about the Mandela Effect.\n\nSuddenly, there's a noise at the door.\n\nDo you [[investigate it|Start2]], or do you [[keep listening to the radio|StartMandelaEffect]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Archives notebook 1",
			"The notebook has the same handwriting in it as the letter you recieved at the start of this wild chain of events.\n\nThe first page has the title *Unnamed romantic comedy book draft*.\n\nIt looks like Dr Albert is a writer in his spare time.\n\nDo you want to [[give it a read|Archives notebook 2]], or would you rather [[take a look inside the folder instead|Archives folder 1]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Archives about experiment",
			"It appears that this is what you're here to do:\n\n*Subject will receive the experimental serum which should allow them to tap into their memories from another timestream.*\n\n*Success for the serum will be assessed as follows:*\n\n*Subject will flip a coin.*\n\n*If heads, subject will be given some information to memorize. Viability of serum cannot happen in this timeline. ~~Subject will be sent to other timeline~~* ***No, that won't be happening.***\n\n*If tails, subject will be expected to recount that information. If recount could happen, the trial will be a success*.\n\nThat's odd. The thing that's closest to implying the presence of bombs has been crossed out. And you haven't flipped any coins yet. So this current chain of event definitely wasn't supposed to happen. But at least that serum might explain why everything's been happening the way it's been happening.\n\nAs you ponder this, you can't help but notice that [[Percival sounds like he's in a spot of bother|Archives percival dun goofed]].",
			["serum"]
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
			"Albert End 2",
			"Percival looks at you with a worried expression, as he starts to lead you out of the room.\n\n\"I really, really do hope you have a plan.\"\n\nYou're not entirely sure what to expect either.\n\nEventually, after some more corridors, you find yourself outside of an impressively-furnished door, with the name 'Dr Archibald Albert' emblazoned on it.\n\nYou hear a \"Come in\" from the other side of the door.\n\n[[Time to end this.|Albert end 2]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"prank ending change clothes",
			"After some asking around backstage, you're given a box with your actual clothes in them. You take them to a dressing room, and get back into them, putting the 'Subject C' jumpsuit back into the box, handing it back to the person who was holding onto your clothes earlier.\n\nYou get given some cash to hire a cab to get yourself back home, as well as a bit of compensation for being the victim of an utterly nonsensical TV prank.\n\nIt's been a long day.\n\nYou leave the studio, and [[hire a cab to take you home|prank hire cab]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Albert End 1",
			"You can hear the voice of a rather old man crackling over the intercom.\n\n\"*Congratulations, Subject C. You figured it out. Now, I know what you are probably planning to do next. So, Percival, would you kindly escort Subject C to my office?*\"\n\nYou are taken aback by how casually he admitted to it. However, this does fill you with some unease.\n\nAnother word comes through the intercom.\n\n\"***Now.***\"\n\nHe's killed you 4 times already, so, chances are he won't have any qualms about killing you yet again if you refuse.\n\n\"[[Well, Percival, we may as well get this over with.|Albert End 2]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd A expecting call",
			"Dr Albert responds\n\n\"About what?\"\n\nYou explain the call you overheard from Cillian, about 'doing what with what'. He muses over this for a moment, before having a realization.\n\n\"Ah yes. When you two interrupted me, I had just been informed that a projector had been thrown out of a window, and that Percival had been spotted going into the briefing room, alone, some time before that projector was disposed of. In fact, I was just about to ask Dr Spreewald if he knew anything about that himself! Say, Percival, what were you doing in there?\"\n\nPercival gives an answer.\n\n\"I was trying to make sure everything had been set up correctly! After all, I had to make sure everything was plugged in and working!\"\n\n\"[[Are you sure it was plugged in?|PEnd not plugged in]]\"\n\"[[And why did you go in there in the first place?|PEnd A why go in]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd caught lying",
			"\"Ah...\"\n\n\"[['Ah' indeed, Percival!|PEnd caught]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria whats your job",
			"\"Me? I'm researching stuff that's well beyond your paygrade for you to find out, and beyond my paygrade for me to explain it to you.\"\n\nYou feel a bit annoyed at that non-answer.\n\n\"[[So, why did you bother introducing yourself to me in the first place?|Cafeteria why bother]]\"\n{if:pAny(\"Cafeteria why boom\")}{}{else:\"[[And is explaining the recent explosions also above your paygrade?|Cafeteria why boom]]\"}",
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
			"deja vu PedantryWeekly",
			"You can see Percival's eyes light up with excitement.\n\n\"**YES!** Finally, my achievements have been recognized!\"\n\nHe appears to have fallen for your bluff. Almost *too* well.\n\nEither way, he doesn't look like he's going to press the issue any further. And you highly doubt he'll even want to.\n\nBut he looks like he's getting [[back to the task at hand|deja vu percival leave]]",
			["pedantic"]
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
			"Spreewald end wanna be a hero",
			"He lets out another laugh.\n\n\"Thing is, I already have gotten away with it. Even if ya do stop me from shooting myself now, I'll just find another way of departing this timestream. Hell, the security team have a 'shoot to kill' policy anyway, and they're already en-route. But sure, if ya really want to 'stop' me, by all means, feel free to use the leftover bullet in here. On that note...\"\n\nHe [[pulls the trigger|Spreewald end hes dead]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end part where he doesnt shoot you",
			"\"Me? Waste a bullet on ya? Ha! Your presence here already means I've failed to kill ya several times already, so I know there's no point in trying and failing to do that again. In fact, I have a much better idea!\"\n\nThis statement probably should sound reassuring, but, it somehow makes you feel even more on edge.\n\nAs he's saying that, [[he points the gun at something else|Spreewald end going to shoot himself]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"prank wth",
			"***\"PRECISELY! GIVE IT UP FOR THIS RANDOM PERSON!\"***\n\nThis is followed by a 'please clap' sign lighting up above the stage, and a totally not forced standing ovation.\n\nYou are ushered off the stage, as the presenter announces the next segment of the show, and introduces yet another one of those celebrities you've never heard of.\n\nNow that you think about it, [[you may as well work out where your actual clothes went|prank ending change clothes]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"A subtle",
			"You can see an old man, looking up at you from the paperwork he was reading.\n\n\"Oh, hello there, Subject C. I'm not sure why you felt it necessary to make your way over here, or, indeed, how you managed to find your way here. Nonetheless, I am all ears.\"\n\nGood. He doesn't suspect anything yet. How are you going to proceed?\n\nWill you continue the facade, with a \"[[I suspect that your colleague, Dr Spreewald, is attempting to kill me|A bluff]]\"?\nOr will you go mask off, and tell him that \"[[I found a rather interesting notebook, containing information that *somebody* is attempting to sabotage somebody else, intentionally trying to get myself caught in the crossfire|A blatant]]\"?",
			[]
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
			"MainLoopDeath",
			"When you wake up, you realize you're on the ground of the briefing room, in front of Percival, who's looking at you with a very concerned expression.\n\n\"Are you okay? You just fainted out of the blue. I guess that trying to save yourself from dying probably can have that effect on some people. Do you need a hand up?\"\n\nHe sticks his arm out for you, and you allow him to help you back onto your feet.\n\nYou take a moment to re-orientate yourself. Ah yes. Percival had just offered to help you to avoid dying, didn't he? Not that it really helped, seeing as you probably just died again.\n\n\"[[I think I just died again|MainLoopDeath-IDiedAgain]]\"\n\"[[Sorry, I've completely forgotten what you were talking about. Mind repeating it for me?|MainLoopIntro-PickAPath]]\"",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Archives desk",
			"You take a closer look at the desk, and sit yourself down on the chair that's right next to it.\n\nThe previous occupant of this desk left two things behind.\n\nOne of those things was a folder labelled with some embossing tape, bearing the words '4157 - SUBJECT C'.\n\nThe other thing they left behind was a rather nondescript-looking, ring-bound notebook, bearing no title, but with the letters 'A A' in the bottom-right of the cover page.\n\nYou don't have anything better to do right now, so are you going to [[look inside the folder|Archives folder 1]], or are you going to [[read the notebook|Archives notebook 1]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"YourRoom cornered",
			"Percival suddenly runs to the door, and tries to get the door to move.\n\nAs he comes to the realization that he can't open it, he lets out a stream of expletives that you never expected to hear from him.\n\nHe runs to the curtains opposite the door, throws them open, and lets out another stream of expletives when he realizes that there's only a wall behind them, instead of a window.\n\nIt's pretty clear that there's no point in talking to him, as it doesn't look like he's going to be able to say anything coherent.\n\nYou look around the room to see if there's anything of any importance that wasn't there earlier on.\n\nThere is.\n\nThere's a gift-wrapped present on the bed, with a plain white postcard sellotaped onto it, with the words 'SUBJECT C' scribbled onto it in some more handwriting you don't recognize.\n\nAs you can't see anything else of any importance, you may as well [[take a closer look at that present|YourRoom theHint]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd loop fadeout",
			"[[Everything fades to white|PEnd loop alive]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd brute force dying",
			"You wait for the inevitable.\n\n#**FACILITY SELF-DESTRUCT IN... 5... 4...**\n\n[[At last.|PEnd loop light]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MainLoopIntro-PickAPath",
			"Percival takes a look at his clipboard.\n\n\"Lets see here... there might be something on here that's of some use... ah ha!\"\n\n\"On the itinerary, it says that I'm supposed to escort you to the cafeteria next. I'd prefer this option myself, because that's what I'm supposed to be doing.\"\n\n{if:pAny(\"Cafeteria\")}{You remember going there already. The food was decent, but the whole 'exploding' stuff did leave a sour taste in your mouth.}{else:It definitely sounds like it could be a trap, but there's definitely a good chance of finding some answers there.}\n\n\"Lets see what else there is... I guess I could bring you back to your room, where you could wait out the lockdown in relative comfort.\"\n\n{if:pAny(\"YourRoom\")}{You remember the complete-lack-of-care package that someone left in your room. Hiding definitely isn't an option for you. But it looked like the culprit didn't want to hide either.}{else:This option sounds like it could at least give you some time to process everything, and to actually get around to trying out that bed.}\n\n\"Or there are the research archives. I've never been there before myself, so I have no idea what to expect there. But, I highly doubt that anyone would be stupid enough to risk destroying all of those documents.\"\n\n{if:pAny(\"Archives\")}{Unfortunately, they clearly were stupid enough to do that, and you were stupid enough to underestimate their stupidity.}{else:This sounds like a rational choice of a place to hide. If nothing else, you might be able to find out a bit more about any ulterior motives going on here}\n\n\"But if there's any other suggestions you have, I'd like to hear them.\"\n\n{if:pCount(\"MainLoopDeath\")==3}{\"[[Actually, yes, I have a plan|MainLoop-end]]\"}{else:You can't think of anything right now. Perhaps after you get some more information about everything going on, you might have a better idea of what to do.\n\nWith this in mind, where do you want to go?}\n\n{if:not(pAny(\"Cafeteria\"))}{\"[[I'm hungry, so let's go to the cafeteria|Cafeteria]]\"\n}{if:not(pAny(\"YourRoom\"))}{\"[[I guess that I may as well wait it out in that room I woke up in|YourRoom]]\"\n}{if:not(pAny(\"Archives\"))}{\"[[Chances are that those archives might be too important to destroy, so that sounds like a relatively safe bet to me.|Archives]]\"}",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"prank ending open door",
			"The door opens.\n\n[[You enter the room|the prank ending]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd boredom",
			"He gestures as if he's reminiscing about a very distant memory.\n\n\"Let me tell you a story. Once upon a time, there was a guy who was down on his luck, and needed some cash. One day, he received an offer for some free money, so, he naturally took the offer. He then found himself kidnapped, and had been given some mysterious serum, without his knowledge. He woke up in a strange room, and was soon greeted by a man holding a clipboard, as well as his kidnappers. Later on that die, he was tragically killed when a badly-installed light fitting fell from the ceiling, killing him.\"\n\nHe paused for effect.\n\n\"At least, he was, until that man woke up, back in that room, and was greeted by the same people for the first time, yet again. This time, he wasn't crushed by the light fitting. Eventually, he went home, and lived a long and fulfilling life, before dying peacefully, surrounded by his family.\"\n\nHis voice trailed off at this point, with his eyes starting to tear up, at that bittersweet scene.\n\n\"But he woke up. In that very same room. Being greeted by those very same people. Again. And again. And again. And again. And so, he got bored.\"\n\nYou can't help but feel somewhat sorry for this person.\n\n\"[[So, are you not actually Percival?|PEnd not percival]]\"\n\"[[Is there any way you can escape?|PEnd how to escape]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Albert end home 4",
			"There's just a single sheet of A6 paper inside it, with just one line printed on it.\n\n\"*Thank you for your participation. Compensation has been dispensed.*\"\n\nYou're not sure what it's referring to. It's probably been sent to the wrong address. Either way, you should probably get on with your life.",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Archives folder 1",
			"You open the folder which is apparently about you.\n\nIt looks like it's been divided into sections.\n\nDo you want to start looking at what they know [['about the subject'|Archives about you]], or do you want to look at the [['utilization of the subject'|Archives about experiment]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Albert end home 3",
			"There's nothing else on the envelope. Nothing on the front, nothing on the back.\n\n[[You cautiously open the envelope.|Albert end home 4]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"prank hire cab",
			"You don't feel like talking on the way back.\n\nNothing of note really happens.\n\nEventually, you make it home.\n\nYou pay the driver, and [[get back inside your home|prank the end]]",
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
			"Albert end home 2",
			"The noise was just the sound of some post getting shoved through your letterbox.\n\nToday, there's just one envelope, stuck in the flap of the letterbox.\n\nAn envelope with your name and address printed on it.\n\nYou're not entirely sure what this envelope could be.\n\n[[You cautiously pick up the envelope|Albert end home 3]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end lets go",
			"{if:pAny(\"Spreewald end pick up gun\")}{You put the gun down, and ask yourself what you were thinking. }Percival looks at his clipboard.\n\n\"Well, the exit is this way. Follow me!\"\n\nHe leads you to the exit. Nothing untoward happens on the way.\n\nHe enters the override code into the panel by the door.\n\nIt opens.\n\nPercival takes one last look at you.\n\n\"Well, Subject C, I certainly wasn't expecting today to go like this. I suppose this is goodbye.\"\n\n\"[[Thanks for everything, Percival.|Spreewald end thanks aka THE SLOUGH TRADING ESTATE ENDING]]\"\n\"[[By the way, Percival, what happened to the clothes I was wearing before I was brought here?|Spreewald end dude wheres my stuff]]\"",
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
			"PEnd alive",
			"You look behind you, to see if Subject A is still there.\n\nHe isn't.\n\nYou have no idea where he could be.\n\nBut that doesn't matter now.\n\n[[Your mind buzzes, trying to process everything that's just happened|PEnd the end]].",
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
			"Cafeteria hes sus",
			"Percival agrees with you.\n\n\"I will admit that he's acting a bit weird. But, at the same time, everyone's acting weird nowadays, aren't they?\"\n\nThat probably is a good point he's raised.\n\n[[You take a moment to ponder that response|Cafeteria ohno bomb time]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"A someone else",
			"\"Precisely.\"\n\nWell, that was awkward. But at least you know that Dr Albert isn't the person responsible for killing you all those times.\n\nNow, it's a matter of figuring out who would have had the opportunity and a reason to do so.\n\n\"[[Well, it's obviously Cillian then, isn't it?|A its spreewald]]\"\n{if:tAny(\"percivalSus\")}{\"[[Actually, I think that Percival might be the killer.|A its percival]]\"}",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd loop alive",
			"You find yourself on the floor.\n\nThe door to this room is open.\n\nSubject A has just left the room.\n\nYou curse fate for trapping you here.\n\n[[You need to get up, and stop Subject A|PEnd left the room]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria do nothing",
			"The two of you just remain seated in silence.\n\nPercival looks like he's taking this as an opportunity to meditate.\n\n[[You sit back in your chair, getting comfortable|Cafeteria ohno bomb time]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"S timelines",
			"\"I have no idea how you managed to look at that file, but I explicitly omitted that 'sending the subject to a different timeline' part of the experiment. You can blame Dr Albert for putting that there in the first place. No idea why the hell he thought that doing something like that would be a good idea anyway. And, even if I *was* planning on doing that, I would have only done that after the coin flip stage. Which, of course, hasn't happened yet.\"\n\nHe makes a good point.\n\n\"[[So what you're saying is that someone else was murdering me?|S someone else]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd the end",
			"You sit down, leaning against the wall.\n\nYou won.\n\nYou still exist.\n\nFor now.",
			["noreturn","TrueEnding","ExistentialCrisis"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd reactor pls move",
			"He chuckles to himself again.\n\n\"No. I don't think I will. In fact, I might just break it instead, how about that?\"\n\nYou find yourself somewhat horrified by his complete lack of care for anyone's safety, especially his own.\n\n\"[[Don't even think about it!|PEnd reactor offered a wager]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"S its percival",
			"Percival interjects\n\n\"What!? What makes you think it's me!?\"\n\nThat is a very good question he's asked you.\n\n\"[[Well, who else has been close enough to me in order to kill me in every timeline?|PEnd nope framed]]\"\n{if:pAny(\"Archives about you\")}{\"[[Why would your name be put onto my file in a way that looks like it was overwriting someone else's name?|PEnd overwritten]]\"}{else:{else:{if:pAny(\"Archives notebook 4\")/}{\"[[Well, what do you think Dr Albert meant when he wrote something about you 'acting rather suspicious'?|PEnd albert sus S]]\"/}}\n{if:pAny(\"Cafeteria eavesdrop\")}{\"[[Say, Cillian, shouldn't you be getting a call right about now about me and Percival?|PEnd S expecting call]]\"}",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd the serum",
			"\"Congratulations! We have a winner! Your prize is the knowledge of the hell you have found yourself in.\"\n\nYou gulp.\n\n\"[[What sort of 'hell' are we talking about?|PEnd boredom]]\"",
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
			"Cafeteria run deadend",
			"You turn the corner.\n\nYou are greeted by a dead end.\n\nA dead end that someone appears to be using as an armoury of sorts, with an excessive amount of high explosives scattered around.\n\nYou notice a tripwire on the floor. Luckily for you, you haven't stepped on it.\n\nPercival hasn't quite caught up with you yet.\n\n[[Perhaps it would be a good idea to turn back.|Cafeteria run turn back]].",
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
			"Cafeteria boom",
			"Just as soon as you decided what you were going to do, you found out what the noise actually was.\n\nOnce again, you felt a warm, bright, flash, engulfing your body.\n\n[[And everything fades to white|MainLoopDeath]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria hallway",
			"The two of you are back in the hallway from earlier.\n\nTo your left, you can faintly hear Cillian having a conversation with someone else, through that pager from earlier. You might be able to eavesdrop on the conversation if you can get a bit closer to him.\n\nThere doesn't appear to be anything of note to the right, but, you should have a decent chance of getting away from Cillian in that direction.\n\nSo, what are you going to do?\n\nWill you [[go left, to eavesdrop on the conversation|Cafeteria eavesdrop]], or will you [[go right, to run away|Cafeteria run away]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-muttering",
			"Percival clearly seems very engrossed in his train of thought, because he clearly hasn't heard you\n\n\"So, there was that explosion last week with Subject B... and that other accident before that one with Subject A...\"\n\n Explosions?\n\nOther subjects?\n\nSounds like some juicy information.\n\n\"[[So, what exactly are you muttering to yourself about?|BriefingRoom2-whatsthebuzz]]\"",
			[]
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
			"Cafeteria back",
			"You and Percival decide to go back to the 'cafeteria', acting natural, like you didn't just overhear the details of the conversation you just heard.\n\nThe two of you re-enter the room, sitting back down as you were earlier on.\n\nAll that's left is [[to wait for Cillian to return|Cafeteria ohno bomb time]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd loop light",
			"#**3... 2... 1...**\n\n[[You are engulfed in a bright light.|PEnd loop fadeout]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd right",
			"Subject A opens his right hand. Nothing's there.\n\n\"Oh no, looks like you got it wrong! Oh well. Guess you'll just have to try again next time!\"\n\n{if:pAny(\"PEnd left\")}{That cheat! Both of his hands were empty!\n\n}Before you can protest, an announcement plays over the intercom.\n\n#**FACILITY SELF-DESTRUCT IN... 5... 4...**\n\n[[You glare at Subject A's smug expression|PEnd loop light]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd S why go in",
			"Percival responds.\n\n\"Why, it's because I was told to go in to set it up by Dr Albert!\"\n\nThis is honestly a rather plausible explanation.\n\n\"[[So, that means Dr Albert wanted us to think it was you, when it was actually him?|S its albert]]\"",
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
			"Spreewald end cocked hammer",
			"He gives you one last smile.\n\n\"Send my regards to the cleaners, will ya? Toodles.\"\n\nAs he says this, [[he pulls the trigger|Spreewald end hes dead]].",
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
			"Archives about you",
			"Let's see what they've written about you...\n\nOkay, that was uncalled for.\n\nThey've got a scanned copy of the 'Yes' reply you sent back to them, accompanied with the word *Gullible*.\n\nIt also says here that you are here for some experiments to be conducted by {if:pAny(\"Cafeteria meet Dr Spreewald\")}{Cillian}{else:this Dr Spreewald person}, and that Percival has been assigned to usher you around the facility.\n\nBut that's odd. Percival's name is written over some correction fluid, and whilst the handwriting saying 'Percival' looks like the rest of the handwriting, something just looks... off... about it.\n\n[[Your train of thought is interrupted by the noise of Percival's voice|Archives percival dun goofed]]",
			["spreewald","percivalSus"]
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
			"the prank ending",
			"You walk through the door.\n\nYou find yourself in front of a camera, and a live studio audience.\n\n***\"COOOOOONGRATULATIONS! YOU'VE JUST GOT PRANKED ON NATIONAL TELEVISION!\"***\n\nIt appears that you've just fallen victim to a television company with too much money to splurge on terrible ideas.\n\nThe presenter, who appears to be one of those supposedly famous people you have never heard of before, is walking towards you with a microphone.\n\n*\"Now, would you mind letting us know how  you feel about this?\"*\n\n\"[[What the hell|prank wth]]\"\n\"[[This makes literally zero sense|prank no sense]]\"\n\"[[Screw this, I'm going home|prank im going home]]\"",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end has something in pocket",
			"As Dr Albert rummages around the underside of his desk, probably trying to hit a panic button, and make sure that he definitely has hit it, you can see Cillian procuring a metallic something from his pocket.\n\nHe points it at Dr Albert, and lets out a taunt.\n\n\"Go ahead, Archie, call security to get rid of me, they can't unmurder you!\"\n\n[[You realize that 'it' is a snub-nosed revolver.|Spreewald end has a gun]]",
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
			"Albert end done here",
			"\"It means that I wish you the best of luck in your future endeavours. Now, apologies in advance if this may appear rude, but, it's protocol.\"\n\nAs he's saying this, the old man procures a gas mask from his desk and puts it on.\n\n\"Yes, I took the liberty of locking the door already. Yes, you might wake up a bit disoriented, but induced amnesia sometimes has that effect. Farewell, Subject C.\"\n\nThe room starts filling with a [[thick, white smoke|Albert end smoke]].",
			["noreturn"]
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
			"Archives summon Percival",
			"You call Percival over.\n\nHe responds with a \"I shall be there momentarily! Just need to finish up here...\"\n\nThis is shortly followed by a [[shriek of fear from Percival|Archives percival dun goofed]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"S someone else",
			"\"Well, yeah. Someone who has access to the facility, knows your planned movements, and someone who has the means to set up all of these explosives...\"\n\nYou cast your mind back throughout all of this, to work out who the real culprit probably could be.\n\n\"[[Could it be Dr Albert?|S its albert]]\"\n{if:tAny(\"percivalSus\")}{\"[[I hate to say this, but I think it might just be Percival.|S its percival]]\"}",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-whatsthebuzz",
			"This time, Percival actually has noticed you trying to get his attention.\n\nBut he actually seems willing to explain what's happening, for once.\n\n\"Well, I suspect that someone's trying to get me fired.\"\n\nHe explains to you that there were two prior test subjects before you, hence your designation as 'C'. He was supposed to be supervising them as they were following the carefully defined itinerary of places that he was meant to escort them to. However, both of those subjects mysteriously died in some unexplained accidents and explosions, and, being the last person to have seen them alive, Percival was required to pay for the cleanup. And after Subject B died, Percival was told that he was supposed to keep you alive, or else he'd get fired.\n\nPercival's employment status is honestly pretty low on your list of priorities at this particular point in time. However, it's pretty clear that he does have a vested interest in keeping you alive, which is a good thing for you.\n\n\"[[So, you going to help me to not get killed?|BriefingRoom2-goingToHelpMe]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Albert end confession",
			"\"What is there to confess? I haven't done anything. You may claim to have been murdered, but, as far as I can see, you appear to be perfectly alive and well. So I don't know why you're trying to insinuate that I have confessed to doing something that's clearly not happened.\"\n\nAs much as it pains you to admit it, he has a point.\n\n\"[[So what are you so happy about?|Albert end why happy]]\"",
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
			"Albert end done died",
			"\"But, in this reality, you were never murdered. No harm came to you. You are completely intact.\"\n\n\"[[So what does that mean?|Albert end been 5 days]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd not plugged in",
			"You point out to Percival that it was never plugged in, and that he was obviously doing something else in there.\n\n\"Ah...\"\n\n\"[['Ah' indeed, Percival!|PEnd caught]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu maze end",
			"Eventually, you notice a complete lack of doors on the walls.\n\nThe corridor continues for what seems like forever.\n\nBut you know you just have to [[keep going|deja vu endless corridor]].",
			["noreturn","NotCanon"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"A its spreewald",
			"\"I feared that this would be the case. I should have never given him this final chance.\"\n\nYou can hear the despair in Dr Albert's voice. He looks at you, with a gravely serious expression.\n\n\"We need to get you out of here. Now.\"\n\n\"[[Sounds good to me. Where's the exit?|Spreewald end where exit]]\"\n\"[[Wait, why?|Spreewald end why leave]]\"",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd bluff",
			"\"Yes, you did! When we first met... ah.\"\n\n\"[['Ah' indeed, Percival.|PEnd caught]]\"",
			[]
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
			"Cafeteria hello there",
			"In spite all common sense making it very clear that this is going to be a very bad idea, you decide to walk up to this person and initiate a conversation with them.\n\nAs you walk past Percival, you notice his expression contort into one of some shock, as you demonstrate your brazen disregard for his suggestion to act natural.\n\nIf you were hoping to scare off this person through shock tactics, however, that hasn't worked.\n\n\"Oh good, you've arrived! I was wondering if you were going to turn up!\"\n\nTheir enthusiasm does strike you as a little bit unexpected. But it's too late to turn back now, and they [[haven't stopped speaking to you|Cafeteria meet Dr Spreewald]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd explain serum",
			"\"Well, when you arrived here, they injected you with a serum which is supposed to allow you to move your mind between timestreams. Why? Because they're apparently trying to work out whether or not it works, so they can use it for themselves. Do you remember every single time you died, and mysteriously found yourself alive again? That was the serum at work!\"\n\nA chill runs through your spine.\n\n\"[[So is this 'serum' the curse you were talking about?|PEnd the serum]]\"",
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
			"Spreewald end pick up gun",
			"You pick up the gun.\n\nPercival looks at you in horror.\n\n\"What are you doing!?\"\n\n\"[[I'm going after him.|Spreewald end suicide]]\"\n\"[[Actually, no, this is a stupid idea. Come on Percival, let's leave.|Spreewald end lets go]]\"",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end knock knock",
			"The door opens.\n\nOn the other side, you can see Cillian, with one hand in his pocket, looking awfully pleased with himself.\n\n\"What's this? You're having a party and you didn't invite me? Aww, that's a shame, isn't it? Say, Subject C, what are you doing here?\"\n\n\"[[Well, I was unsure what I was supposed to be doing here, and Percival escorted me to his boss|Spreewald end blame percival]]\"\nOr do you think it would be wiser to just [[say nothing|Spreewald end silent treatment]]?",
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
			"Confront Albert",
			"You ask Percival if he has any idea where you can actually find Dr Albert.\n\n\"Well, I think I remember where his office is. It's over at the other side of the building. He should be there.\"\n\nSo, you follow Percival as he guides you [[to Dr Albert's office|Confront A 2]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria depart",
			"You tell Percival that the two of you are going to be leaving the room, as you start to walk to the door.\n\nFor once, you're not locked in.\n\n[[You exit the room|Cafeteria hallway]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end suicided",
			"There is a loud **BANG!**\n\nYou can feel something hitting your head with a lot of force, knocking you down.\n\nAnd everything fades to white.",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Confront Spreewald",
			"You tell Percival that you've seen enough to conclude that Cillian is the killer.\n\n\"Hmm, I guess that makes sense\", he muses.\n\nAll that's left is to go and confront him.\n\nYou know he's currently sitting down in the cafeteria.\n\n[[To the cafeteria!|Confront S 2]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Albert end 4 murders",
			"\"I murdered you *four* times!? Well, that clearly makes no sense. After all, it's only possible to murder somebody once, and, I have to say, even if you somehow *had* been murdered, you seem awfully full of life for a corpse, aren't you?\"\n\n\"[[But you *still* murdered me.|Albert end well yes but technically no]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd S expecting call",
			"You can hear a pager beeping.\n\nCillian rummages around his pocket, picking it up.\n\n\"You talking about this?\" he asks.\n\nYou answer in the affirmative, and ask him to stay here and answer it.\n\nThe conversation plays out as it did in the previous timeline.\n\nEventually, Cillian hangs up, and asks a question.\n\n\"So, Percival, what did you do to the projector in the briefing room this morning after I had set it up, before you brought Subject C to the briefing room? After all, you've been spotted going in there, and it looks like C here then felt the need to throw it out of the window for some reason.\"\n\nPercival responds.\n\n\"I was trying to make sure everything had been set up correctly! After all, I had to make sure everything was plugged in and working!\"\n\n\"[[Are you sure it was plugged in?|PEnd not plugged in]]\"\n\"[[And why did you go in there in the first place?|PEnd S why go in]]\"",
			[]
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
			"deja vu hide gas",
			"You say nothing.\n\nYou hear Percival making his way out of the room.\n\nYou're safe.\n\nYou hear a gentle *pshhhhh* coming from various points in the walls.\n\nSmoke fills the room.\n\n[[And everything fades to black|BriefingRoom2-KO]]",
			[]
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
			"Spreewald end plot hole alert",
			"\"Perhaps I was in the other timestreams already, having already fulfilled this plan. Perhaps the 'me' you saw in the other timestreams was a fool, who was too short-sighted to come up with this plan. None of it matters though in this reality. Speaking of which, you might want to stand back\"\n\nHe [[cocks the hammer|Spreewald end cocked hammer]] of the revolver.\nAre you going to bother trying to assert that [[he won't get away with this|Spreewald end wanna be a hero]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"prank ending blackout",
			"And everything fades to black.",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"prank no sense",
			"***\"THAT'S HOW TELEVISION WORKS, FOLKS! EVERYONE, GIVE IT UP FOR THIS RANDOM PERSON!\"***\n\nThis is followed by a 'please clap' sign lighting up above the stage, and a totally not forced standing ovation.\n\nYou are ushered off the stage. This honestly comes as a relief, as you've had enough of this nonsense for a lifetime.\n\nNow that you think about it, [[you may as well work out where your actual clothes went|prank ending change clothes]].",
			[]
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
			"Cafeteria run away",
			"The two of you start running down the hallway.\n\nYou can't help but notice the complete lack of any junctions in this particular hallway.\n\n[[Guess you'll just have to continue going onwards|Cafeteria run 2]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end why leave",
			"\"If you were to go to where he's expecting you to go, he will probably end your life again, and you will be back at square 1. However, if you were to escape from death in front of him, he will probably realize that whatever he's trying to do has either failed, or, even worse, succeeded.\"\n\nYou shudder at the thought of what his 'success' could mean.\n\n\"[[So, where's the exit?|Spreewald end where exit]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"elsewhere-2",
			"You prepare for the worst as you unfold the piece of paper and start reading it.\n\n*Greetings.*\n\n*I would like to apologize profusely for the theatrics when bringing you here.*\n\n*Yes, I know, sleeping gas is far from the most pleasant method of welcoming you here, however, we have to take these sorts of precautions with the whole 'top secret' nature of this place. Personally, I would have preferred it if we could have just told you the address and allowed you to make your own way here. But that's life, I suppose.*\n\n*I should also mention that, by being here, you are officially bound by all that legislation that prohibits any discussion of the stuff you're doing here. Officially, you're on a residential training bootcamp for creating algorithms to efficiently traverse directed graphs and other sorts of techno mumbo-jumbo that nobody really understands anyway.*\n\n*So, you're probably wondering what's happening here. This is understandable. In short, you'll be helping us out with some experiments into the nature of reality itself. Yes, you might get very confused by everything that's going on, but that's generally what happens here.*\n\n*Now, once you've read this, we'll bring you to the briefing room where we'll explain everything that's going on. And yes, everyone in this facility will be referring to you as 'Subject C' from now on, mostly because we haven't let the staff know your name, and mostly because it's easier to do our work this way.*\n\n*Thank you, once again, for your participation*\n\n*- Dr A. Albert*\n\nAs you finish reading this slip of paper, you can hear some [[knocking|elsewhere-3]] on the door, and a muffled, unfamiliar voice calling for a \"*Subject C?*\".",
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
			"Cafeteria what happen",
			"\"To be completely honest, I'm not entirely sure either. Probably something to do with some paperwork he forgot to fill in.\"\n\n[[You wonder how long it's going to take for that to get sorted out|Cafeteria ohno bomb time]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"S bluff",
			"Of course! He'll think that his plan completely failed, so he'll obviously be rather confused about it!\n\n...at least, that's what the plan was.\n\n\"Welcome! Sorry about making you sit through that long video, health and safety and all that. Anywho, glad you could make it. I'm Dr Spreewald, but please, call me Cillian\"\n\nHe's completely and utterly unfazed.\n\nMight need to make it more explicit.\n\n\"[[I know what your deal is. I know you've killed me 4 times. And I'm here to put an end to this.|S blatant]]\"",
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
			"PEnd A why go in",
			"Percival responds.\n\n\"Why, it's because I was told to go in to set it up by Dr Spreewald!\"\n\nThis is honestly a rather plausible explanation.\n\n\"[[So, that means Dr Spreewald wanted us to think it was you, when it was actually him?|A its spreewald]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end time to leave",
			"Dr Albert looks back at you.\n\n\"Yes, it is. Farewell.\"\n\nWith that, you and Percival head towards the door of the office.\n\n[[But there's a noise at the door.|Spreewald end knock knock]]\n\nYou're getting a bad feeling about this.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd both empty",
			"Subject A opens both of his hands, revealing that they were both completely empty all along.\n\nHe starts applauding you.\n\n\"Bravo, cheater, you have won our wager. Eternity is now yours, whether you want it or not.\"\n\nHe moves out of your way, gesturing towards the override button, in a patronizing imitation of a doorman.\n\n\"[[Cheater? You're the one who tried to trick me into getting it completely wrong!|PEnd cheater]]\"\n\"[[This had better not be a trick|PEnd not a trick]]\"",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria wait",
			"Against your better judgement, you and Percival decide to wait for Cillian to return.\n\nIt might be some time until he's back though. Maybe it might be worth asking if Percival has any ideas about what's happening.\n\n\"[[Any idea what's distracting him?|Cafeteria what happen]]\"\n\"[[Something seems off about him.|Cafeteria hes sus]]\"\nOr do you just [[want to bask in the silence|Cafeteria do nothing]]?",
			[]
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
			"Archives",
			"Percival guides you through the nondescript hallways of the facility to a rather large room, full of filing cabinets containing what might just be the net sum of all of the potentially forbidden knowledge uncovered here.\n\n\"This is apparently where everything should be filed correctly, you see. But it does look like some people haven't been filing things in the appropriate order. Chances are that the people who are trying to kill you won't attempt trying to destroy all of this, so, I think we should be safe for a bit.\"\n\nThis is a very big room, so, in a worst case scenario, it might be rather difficult for anyone who may be chasing you in here to find you.\n\nYou notice Percival taking a look inside one of the filing cabinets.\n\n\"This is completely disorganized... how can anyone find anything in here?\" he mutters to himself. It looks like he's trying to get busy trying to work out how to organize these files, obviously operating under the assumption that there isn't an existing system of organization hidden underneath this disorder. It might be best to stay out of his way for the time being.\n\nYou take a look around the room again, for anything to look at to pass the time.\n\n[[Eventually, you notice a desk, which looked like it had recently been vacated, with a lamp above it which somebody forgot to turn off|Archives desk]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria run 2",
			"The hallway continues through a sequence of corners.\n\nNone of the doors on the hallway appear to lead to anywhere else, with most of them either being locked, or leading to empty, dead-end rooms.\n\nYou hope that there's going to be something of use [[around the next corner|Cafeteria run deadend]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"BriefingRoom2-callingBluff",
			"Of course! Who else could be the culprit but Percival? He's obviously bluffing, so the look on his face is going to be priceless when he realizes that you know that it's him!\n\nAt least, that's what your train of thought was telling you. Which his expression, of genuine apathy and confusion, certainly isn't corroborating.\n\n\"No? You told me it was a bomb. And, considering the other recent explosions, I'm inclined to believe your claim that it's a bomb.\"\n\nPerhaps you should consider trying to make sure you actually know what you're saying when attempting to make an accusation instead of just throwing accusations out wildly.\n\n\"I know, I know, you're looking for someone to accuse right now, I get it. But I've never met you before, so why would I have any reason to kill you? And why would I be in the same room as the bomb as it would go off? And that's before we factor in that, if you were to die on my watch, the cleaning costs come out of my pay, and I get one step closer to getting fired!\"\n\nIt looks like we can safely mark Percival as not being a suspect. He might be a complete idiot, but he's probably not stupid enough to blow himself up or allow himself to get left with the cleaning bill. Perhaps you should explain to him how you actually knew it was a bomb.\n\n{if:pAny(\"deja vu been there already\", \"deja vu new hat\")}{\"[[Well, you remember that conversation we had earlier on, and you got confused about me remembering things?|BriefingRoom2-alreadyToldYou]]\"}{else:\"[[Because it already killed me.|BriefingRoom2-alreadyKilledMe]]\"}",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd immortality",
			"\"Come on, C, you know what I mean! You've been afflicted with the same curse as I have!\"\n\nA sense of dread starts to creep up over you.\n\n{if:tAny(\"serum\")}{\"[[Are you referring to that serum I've been hearing about?|PEnd the serum]]\"\n}\"[[I'm not sure if I follow|PEnd explain serum]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd it is really kept there",
			"Dr Albert sighs.\n\n\"Yes, we do keep copies of such documentation in the archives. I suppose that a rational explanation could be that Spreewald was merely using Percival as a scapegoat.\"\n\nThe logic honestly checks out.\n\n\"[[So, it's Dr Spreewald then?|A its spreewald]]\"",
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
			"Albert end smoke",
			"The smoke quickly overwhelms your senses.\n\n[[Everything fades to black|Albert end back home]]",
			["noreturn"]
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
			"BriefingRoom2-itsABomb",
			"Percival's face turns pale as soon as those words reach his ears.\n\nHe turns away from you and starts muttering to himself, trying to process what you've just told him.\n\n\"O-o-oh... oh no... i-i-is this... no, it can't be...\"\n\nBased on everything he's told you so far, it sounds as if he has less of an idea about what's going on than you do. But this sudden fear definitely appears genuine.\n\nEither way, it sounds like he's probably just going to keep muttering to himself unless you help him to put two and two together.\n\n\"[[So, what are you muttering about?|BriefingRoom2-muttering]]\"\n\"[[Did somebody not give you the memo about the bomb?|BriefingRoom2-notGotTheMemo]]\"",
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
			"S blatant",
			"\"I killed you four times!?\"\n\nYou can clearly hear the confusion in his voice.\n\nYou tell him about the bomb in the projector of that video you just watched, getting trapped and blown up in your room a few minutes after you didn't arrive here, the archives blowing up, and {if:pAny(\"Cafeteria boom\")}{the bomb that he is planning to leave behind in this very room in a few minutes}{else:the booby trap at the end of the corridor}.\n\nHe looks equally confused and also outraged at these revelations.\n\n\"What the... Okay, firstly, why the hell would *I* be trying to kill you? I'm not a bloody thanatologist!\"\n\n{if:pAny(\"Archives about experiment\")}{\"[[Something about 'being sent into another timeline' on the notes you have about me, isn't it?|S timelines]]\"\n}\"[[That video said something about bikers and going back to before the crash to not have the crash, something along those lines.|S bikers]]\"",
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
			"{if:pAny(\"YourRoom PercivalAngery\")}{Percival looks at you with a sense of some superiority.\n\n\"Where would you be without me, hmm?\"\n\nYou decide it would be best to not answer, and to just go into the room with him.}{else:\"Yes, I do! I'll show you where it's hidden, follow me.\"\n\nThat comes as a bit of a relief. You follow Percival into the room, and let him show you what to do.}\n\nHe goes a bit further into the room, and you can hear him fiddling about with something.\n\n\"What you're supposed to do is... this!\"\n\nYou notice that he's turned the light on.\n\nUnfortunately, you can't help but hear the sound of a door being slammed shut behind you, and a noise that doesn't sound entirely unlike an automatic lock engaging.\n\n[[You come to the realization that the two of you are now cornered|YourRoom cornered]]",
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
			"Spreewald end dreading whats next",
			"\"Oh, please, don't you worry your pretty little head. You've done what I needed you for, so I'd just be wasting bullets if I were to use any of them on you.\"\n\nThis is far from reassuring, and fills you with even more dread.\n\nAs he's saying that, [[he points the gun at something else|Spreewald end going to shoot himself]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd overwritten",
			"Percival responds.\n\n\"But why would I want to assign myself to be responsible for someone who, upon meeting a new person, {if:pAny(\"MeetPercival-RoomService\")}{immediately mistakes them for room service}{else:{if:pAny(\"MeetPercival-whois\")/}{rudely asks them who the hell they are/}{else:doesn't care who they are, but immediately starts asking them obvious questions about where they currently are/}}?\"\n\nThat's an interesting point he's raised.\n\n\"[[Wait, did I really say that about you?|PEnd bluff]]\"\n\"[[So you could kill me?|PEnd nope framed]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"S bikers",
			"\"Bikers? Wait, are you talking about that 'ensuring the right things happen' video? You weren't supposed to be shown that one. At all.\"\n\nHe puts his face in his hand, in an indication of some frustration.\n\n\"Look, I have no idea what exactly is happening myself, but it looks to me like someone is trying to screw me over. And I think I know who he is.\"\n\n\"[[So, what you're saying is that this 'someone else' is the person who has been killing me, right?|S someone else]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"prank ending door",
			"Well, what else can you do here?\n\nYou may as well [[open the door|prank ending open door]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"A its percival",
			"Percival interjects\n\n\"What!? What makes you think it's me!?\"\n\nThat is a very good question he's asked you.\n\n\"[[Well, who else has been close enough to me in order to kill me in every timeline?|PEnd nope framed]]\"\n{if:pAny(\"Archives about you\")}{\"[[Why would your name be put onto my file in a way that looks like it was overwriting someone else's name?|PEnd overwritten]]\"}{else:{if:pAny(\"Archives notebook 4\")/}{\"[[Why don't you ask Dr Albert what he meant by you 'acting rather suspicious'?|PEnd albert sus A]]\"/}}\n{if:pAny(\"Cafeteria eavesdrop\")}{\"[[Say, Dr Albert, aren't you supposed to be calling Dr Spreewald about me and Percival shortly?|PEnd A expecting call]]\"}",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Confront S 2",
			"Percival guides you to the cafeteria for the first time again. Eventually, when you get back there, he expresses some concern about your plan.\n\n\"I do hope you have a plan\"\n\nWell, you're not entirely sure either. Cillian is still here, so that's gone as expected. But how are you going to make your grand entrance?\n\nWill you assert dominance over the situation, with a bold \"[[Yoo Cillian, my man,  what's up?|S T-pose plan]]\"\n\nOr will you attempt to call his bluff, with a \"[[That was a very boring video I just watched.|S bluff]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Albert end well yes but technically no",
			"\"Then again, I'm sure that the biker who went back, to warn the group about the impending crash, probably thought that they had died in that horrific accident as well. But I'm sure that if he was to get up from this chair, call the police, and report his own death in that horrible traffic accident, he'd probably be reprimanded for wasting police time, as he clearly would be unusually active for a corpse which supposedly should have died many years ago, wouldn't he? Just like how you would probably get laughed out of the police station, due to your very much alive appearance, right?\"\n\nHe has a point. You're currently not dead. So, technically, he hasn't actually murdered you.\n\n\"[[So what are you so happy about?|Albert end why happy]]\"",
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
			"PEnd button countdown over",
			"[[You are engulfed in a bright light|PEnd light]]",
			["noreturn"]
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
			"YourRoom senseOfDirection",
			"He lets out a slight chuckle.\n\n\"Do you really think that I, Percival, he who checks off the checklists, who files the files, and lists the to-do lists, would be foolish enough to get lost!? No! I always ensure I write down my directions in full, and follow them all to the letter!\"\n\nHe makes a gesture towards his clipboard as he's saying that, as if to give off the impression that he is too organized to get lost.\n\nHow do you want to continue the conversation?\n\n\"[[So, what you're saying is that without that clipboard, you'd be completely lost?|YourRoom PercivalAngery]]\"\n{if:not(pAny(\"YourRoom whyKill\"))}{\"[[On an unrelated topic, why do you think someone's trying to get you fired by killing me? Sounds a bit convoluted to me.|YourRoom whyKill]]\"}\nOr do you just want to [[get to your room|YourRoom silent treatment]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd dont call",
			"\"Because... well, in this particular timestream, I decided to give them a little present which will go off...\"\n\nHe pauses, and checks his watch.\n\n\"...right about now.\"\n\nHe looks away from his watch and back at you.\n\n\"Now, any questions?\"\n\n\"[[So why exactly did you do this?|PEnd motive]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Confront A 2",
			"Percival escorts you, eventually reaching a door labelled 'Dr Archibald Albert'.\n\nWhen you get there, Percival asks you a question.\n\n\"So, do you actually have a plan?\"\n\nThat's a good question.\n\nAre you going to show off that you know more than you should, barging in with a \"[[So, you're trying to sabotage someone else's career by trying to kill me multiple times over?|A T-pose]]\"\n\nOr are you going to go the more subtle route, simply stating \"[[I would like to make a complaint about someone working in this facility|A subtle]]\"?",
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
			"BriefingRoom2-goingToHelpMe",
			"Percival's expression looks somewhat defeated.\n\n\"I guess I probably should.\"\n\nThis comes as a bit of relief for you. You might have no real idea about what's going on here, but at very least Percival might have a vague idea about what to do. Sure, he might turn out to be completely useless, but, he's probably almost better than nothing.\n\n\"[[So, where's the exit?|MainLoopIntro-whereExit]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end where exit",
			"\"Well, the facility presently is in lockdown, to ensure a controlled environment for experimentation. However, there is an emergency override which can be enabled. The code for this is presently 526146, and there will be a panel where you can input it at the emergency exit. Yes, I am aware that this is a rather awkward process for unlocking an exit door, however, the nature of our work is such that this is a requirement.\"\n\nYou take a note of that number, just in case it comes in useful. He looks towards Percival.\n\n\"Now, Percival, I am sure you are capable of guiding Subject C to the exit, yes?\"\n\nPercival responds with a yes.\n\n\"[[So, I guess it's time for me to head off.|Spreewald end time to leave]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd follow him",
			"You try to sneak behind Subject A as he makes his way towards where the self-destruct button for the facility is, trying to not get seen by him. He's making his way there at a rather fast pace.\n\nEventually, you see him walk through a door labelled \"**REACTOR**\".\n\nI suppose the power for this facility had to come from somewhere, so it makes some sense that it would have its own power supply, just in case.\n\nYou can hear an announcement over the intercom.\n\n#**EMERGENCY SELF-DESTRUCT SEQUENCE ENGAGED. THANK YOU FOR YOUR EFFORTS IN THIS TIMESTREAM.**\n\nYou don't have much time left.\n\n[[You go into the reactor room.|PEnd reactor room]]",
			["noreturn"]
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
			"PEnd not percival",
			"He lets out a laugh.\n\n\"Of course not! One time, noticed that he looked similar to the man with the clipboard. So, as he was feeling particularly bored, he spent some time trying to work out how the clipboard man behaved. So, he hatched several plans to dispose of the clipboard man and take his place, which he then did, several times. The first few times, someone worked out his identity. But, he got better at it, and tried living out the clipboard man's life as well, for fun.\"\n\n\"[[So, if Percival is dead, who are you?|PEnd who is]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end has a gun",
			"Dr Albert looks at Cillian with a horrified expression.\n\n\"Cillian!? What is the meaning of this!?\"\n\nSpreewald lets out a chuckle.\n\n\"Just clearing up a loose end, that's all.\"\n\nBefore Dr Albert can respond, there is an ear-shattering [[**BANG!**|Spreewald end shoot]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"A T-pose",
			"Of course! That'll catch this mysterious person off-guard!\n\nYou barge into the office, with your accusation.\n\nYou see an old man, sitting behind a desk, looking rather shocked as you interrupted him looking at some paperwork.\n\n\"I say! That is no way to make an introduction. Murder? Sabotage? I don't have the faintest idea about what you're talking about, Subject C.\"\n\n\"[[I read your notebook in the archives. I know what you're up to.|A blatant]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Cafeteria why boom",
			"\"{if:pAny(\"Cafeteria what is your job\")}{}{else:Well, isn't that a doozy of a conversation-starter! }I'm not entirely sure what it is you're talking about.\"\n\n{if:tCount(\"spreewald\")==1}{You're not entirely sure if it's worth pressing him for more information, as this is the first time you're seeing him.}{else:You've probably said a bit too much. He's probably going to be a bit suspicious of you from here.}\n\n\n{if:pAny(\"Cafeteria whats your job\")}{}{else:\"[[Okay, forget that, do you know anything for sure about what you're doing here?|Cafeteria whats your job]]\"}\n\"[[You know what? Never mind. Why exactly did we start having this conversation?|Cafeteria why bother]]\"",
			["cafSus"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Albert end Hoax",
			"You hear that, allegedly, many people have been receiving handwritten letters from some organization that doesn't exist, promising free money to recipients, akin to a bogus job offer scam. The police have released a formal statement, saying that they don't have any reason to believe that anything illegal has happened, but heavily discouraged replying to those letters.\n\nYou can't help but think that something sounds familiar about that, but you can't quite put your finger on it.\n\nAs the ad break starts, you promptly put those worries to one side, as you [[investigate the noise at your front door|Albert end home 2]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Albert end why kill",
			"\"Murder? Why, that's a very serious accusation for you to throw around so casually, isn't it? Especially when I very clearly haven't murdered anyone!\"\n\nYou find yourself taken aback by his complete dismissal of his own confession of guilt.\n\n\"[[But you *did* murder me. Four times. Each one just as painful as the last.|Albert end 4 murders]]\"\n\"[[You literally just admitted to it, over the intercom.|Albert end confession]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end he knows",
			"\"I know that the only reason you came here was because you are aware that you've been killed multiple times over, and you've seen that namby-pamby notebook I nicked from the old fool and planted in the Archives, haven't ya?\"\n\nYou can hear Dr Albert shouting from behind you.\n\n\"You dastardly cad! That's it, you have crossed the line, I'm calling security right now.\"\n\nYou can hear Cillian let out a chuckle, as he [[moves his hand out of his pocket|Spreewald end has something in pocket]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd nope framed",
			"\"Or, a more likely conclusion would be that somebody else could have been setting it up to look like I was giving myself an opportunity to kill someone. After all, I don't even have a motive to set this up in the first place. Hell, I already told you that I have a very good motive to avoid having you killed!\"\n\nHis logic is honestly rather sound. Honestly, what were you thinking, trying to accuse Percival?\n\n{if:pAny(\"Confront Spreewald\")}{\"[[So, I guess that means Dr Albert is the true culprit, and was trying to frame you?|S its albert]]\"}{else:\"[[So, that means Dr Spreewald was the true culprit, and was trying to frame you?|A its spreewald]]\"}",
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
			"PEnd cheater",
			"He laughs again.\n\n\"Oh, please, did I ever say it was in any of my hands? Nope! But you're the cheater who only got it correct because you saw the answers already, didn't you?\"\n\nYou're about to open your mouth to protest, when you're interrupted by the intercom.\n\n#**FACILITY SELF-DESTRUCT IN... 5...**\n\n\"Don't dawdle, cheater!\"\n\n#**4...**\n\n[[You run to the override button.|PEnd button countdown 3]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Archives percival dun goofed",
			"You shout out into the room, asking Percival what happened.\n\n\"Someone booby-trapped this filing cabinet!\"\n\n[[Oh, for...|Archives boom]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"MainLoop-end",
			"You tell Percival that you think you know who is behind everything.\n\n\"[[It's obviously Spreewald, isn't it?|Confront Spreewald]]\"\n{if:tAny(\"albertSus\")}{\"[[It's clearly Dr Albert!|Confront Albert]]\"}",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"deja vu been there already",
			"He glances back at you, with a look of confusion.\n\n\"No, you haven't been there before. In fact, on this clipboard, I can tell you exactly where you *have* been before, as it's listed here in full.\"\n\nYou suspect that Percival's probably going to start saying the quiet part loud, and he definitely won't listen if you tell him that you've been in there before, so you think that it's in your best interests to just let him keep speaking instead of making a futile attempt to correct him.\n\n\"Let's see here... We received your response confirming your intent to participate here, so a subject retrieval team was sent to retrieve you, which they did. Whilst you were still unconscious, first thing that happened to you here was decontamination and being given that decontaminated outfit, after all, safety first. The science team did give you that serum they're testing, maybe you're disoriented because of that? I doubt it, personally. Anyway, after that, it says here that you were then moved to your room, where you woke up, and I was sent to escort you to the briefing room, which I'm doing now... Not entirely sure how you could have gone to the briefing room already...\"\n\nYour mind starts racing as you try to comprehend all the information Percival accidentally gave you. Retrieval? Decontamination? Injection?\n\nHowever, before you can properly think it through, Percival suddenly stops.\n\n\"Speaking of which...\"\n\nYou notice that he's stopped outside a door labelled with the words [[BRIEFING ROOM|BriefingRoom2 - entry]]",
			["noreturn","beenthere","serum"]
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
			"prank the end",
			"You're finally home, after a very long day.\n\nYou may as well get some sleep.\n\nYou get into [[bed|prank ending blackout]].",
			[]
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
			"Albert end why happy",
			"\"Well, it's obvious. You have helped us to make a massive breakthrough in fulfilling this facility's purpose, and you're back in one piece. Give yourself a pat on the back!\"\n\n{if:pAny(\"Albert end why kill\")}{\"[[Wait, so what does that mean?|Albert end been 5 days]]\"}{else:\"[[You serious? I was killed four times!|Albert end done died]]\"}",
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
			"Cafeteria eavesdrop",
			"You sneak up to the room which Cillian is in.\n\nYou can't hear the person he's talking to, but at least you can faintly hear him.\n\n\"*Yes, Albert, C managed to arrive in one piece, which is a relief...*\"\n\n\"*...Excuse me, they did what with what?*\"\n\n\"*...I guess I'll have to talk about that with him later on.*\"\n\n\"*Well, yeah. What else was I supposed to do with them? Have this conversation in front of them?*\"\n\n\"*In that case, hurry up and tell me what you're trying to tell me, so I can get back to them!*\"\n\nIt sounds like this conversation is almost over. You might want to get going now, before Cillian catches you.\n\nWill you [[go back to the room, acting like nothing ever happened|Cafeteria back]], or would you rather just [[run away|Cafeteria run away]]?",
			["percivalSus"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd reactor the wager",
			"Subject A brings his arms forward, making his hands, both closed up into fits, very visible.\n\n\"I came up with this one many lifetimes ago. If you can correctly tell me which hand you think I've put a piece of paper in, I'll let you survive in this timeline. But, if you get it wrong, you can just try again in another timeline, can't you?\"\n\n{if:pAll(\"PEnd left\",\"PEnd right\")}{You remember the last two attempts you made at this wager. You've figured out his trick already.}{else:This seems simple enough. You'll probably just fail it once, and then you'll win, won't you? He's almost making it too easy!}\n\nIt's time to choose.\n\n{if:not(pAny(\"PEnd left\"))}{\"[[It's in your left hand, isn't it?|PEnd left]]\"\n}{if:not(pAny(\"PEnd right\"))}{\"[[It's in your right hand, isn't it?|PEnd right]]\"\n}{if:pAll(\"PEnd left\", \"PEnd right\")}{\"[[I know you're cheating. Both of your hands are empty!|PEnd both empty]]\"}",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Spreewald end dude wheres my stuff",
			"Percival lets out a rather loud obscenity.\n\nHe has no idea either.\n\nAnd so, the two of you re-enter the facility once again, to embark on an epic quest to work out where your stuff is hidden.",
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
			"Spreewald end suicide",
			"You point the gun at your head.\n\nYou're not going to let Cillian get away scott-free.\n\nYou [[pull the trigger|Spreewald end suicided]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd light",
			"The red emergency light has been replaced by a brighter, white light.\n\nYou can see your surroundings clearly again.\n\n[[You're alive|PEnd alive]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"A premature conclusion",
			"\"The premature conclusion that *I'm* the one trying to kill you. If *I* was the one trying to kill you, surely I would have killed you as soon as we started having this conversation. But I haven't. Truth is, I'm the one who is trying to ensure my colleagues stop trying to perform such... atrocities in the name of knowledge.\"\n\nHe makes a good point.\n\n\"Furthermore, I have been inundated with paperwork all day, mostly regarding the monitoring of the research methods being used here, so, even if I wanted to leave here to murder someone, I simply cannot.\"\n\n\"[[So what you're saying is that someone else is the murderer, right?|A someone else]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd button countdown 1",
			"#**1...**\n\nYou [[press a blue button|PEnd button countdown over]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd button countdown 2",
			"#**2...**\n\nYou frantically look at the button labels.\n\nYour vision is a blur from how fast your eyes are darting around.\n\nYou start [[randomly pressing buttons|PEnd button countdown 1]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd reactor stop",
			"He looks back at you, with an air of haughty superiority.\n\n\"Oh yes, I've stopped. In fact, I'm the one who's going to stop you instead, and I might just stop you by breaking the one button you need to press to stop me, how does that sound?\"\n\n\"[[Don't do it!|PEnd reactor offered a wager]]\"",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"PEnd button countdown 3",
			"#**3...**\n\nThere's [[a lot of buttons here|PEnd button countdown 2]].",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"YourRoom whyKill",
			"Percival looks a bit bothered by this question.\n\n\"I suppose that is a good question. But something here doesn't really add up. Why would someone want to kill the test subjects? They clearly want the test subjects alive, otherwise, they would have probably stopped with the first one. And Dr. Spreewald *did* say something about needing you to remain alive, and he said I'd be responsible if another of his subjects died...\"\n\n{if:tCount(\"spreewald\")<1}{You don't recall hearing anything about a Dr. Spreewald before now. But whoever this person is, they might know something that Percival doesn't.}{else:That person *again*. At least it still sounds like they might be of some relevance in this timeline as well.}\n\nUnfortunately, before you can press Percival for some more tidbits of information, he lets out a \"We're back!\", as the two of you [[reach your destination|YourRoom arrival]].",
			["spreewald"]
		)
	);

	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

