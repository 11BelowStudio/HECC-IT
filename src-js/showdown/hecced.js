//HECC UP output (as of 15/10/2020) (R. Lowe, 2020)

var startingPassageName = "Start";


function getHECCED(){

	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"oh hey im here with my good friend inspector gadget and also\n\n#**DEEZ NUTZ**\n*lmao gottem!*\n\n[[next]]",
			//"<p><a class='passageLink' onclick='theHeccer.goToPassage(\"next\")'>next</a></p>",
			["tag1"]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"next",
			"* this is the next passage.\nDo you want to:\n1. [[go back|Start]]\n2. [[continue?|Greetings]]\n\n[[\nbruh]]\n\n[[\n\nbreh|\n\nok]]",
			[]
		)
	);

	theHeccer.addPassageToMap(
		new Passage(
			"Greetings",
			"Hello there.\n\n[[Hello!|hi]]\n\n[[Who are you?|stupid question]]",
			[]
		)
	);

	theHeccer.addPassageToMap(
		new Passage(
			"hi",
			"I, the voice inside your head who talks to you to you when you're reading,\nhave a very important thing to talk to you about.\n\n[[Which is...?|whats the buzz]]",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"makes sense",
			"Now, where we were?\nAh yes, now I remember.\nI have a very important announcement to make.\n\n[[What's the announcement?|whats the buzz]]",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"beemoviescript1",
			"Ooh, black and yellow!\nLet's shake it up a little.\n\n[[Barry! Breakfast is ready!|beemoviescript2]]",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"beemoviescript2",
			"Actually, wait, I've just had a call from my lawyer.\nIt looks like Dreamworks is going to sue us if we continue this performance of The Bee Movie.\n\n[[Then why did you start performing it in the first place?|whats the point]]",
			[]
		)
	);

	theHeccer.addPassageToMap(
		new Passage(
			"whats the point",
			"Hello?\n\nVoice inside your head that talks to you when you read things?\n\nYou read things, I say them to you?\n\nThat's what you read, so that's what I said.\n\n[[None of this makes any sense.|no sense]]",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"good idea",
			"Welp, let's stop wasting our time here, and let's go do that instead!",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"shameless plug",
			"Well, I have a perfect idea for what we can use to write our even better thing.\n\nHave you ever heard of a system called HECC-IT?\nActually, that's a silly question, seeing as you downloaded this game with that system.\nSo you probably know about it already.\n\nWe'll have to write our game in .hecc format, either manually or using OH-HECC to get a nice graphical overview of it,\nand then we simply use the HECC-UP program to convert it into a playable HECCIN Game.\n\nWe can read the HECC-SPECC document to find out all the syntax and such for the .hecc format,\nor we could just look at the .hecc file for this game and work it out from there.\n\nSo, let's write our own HECCIN Game, and put this poor excuse of a story to shame!\n\n[[I agree, let's do this!|good idea]]\n\n[[This sounds like a very poorly disguised advert.|shill alert]]",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"new phone who this",
			"I'm the voice inside your head who talks to you when you're reading, obviously.\nWho else could I have been?\nIt's not like anyone else is currently talking to you.\n\n[[That makes sense.|makes sense]]\n[[That's stupid!|getting very silly]]",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"stupidend",
			"Right, that's it.\nIf you're going to complain about me saying what you're reading, I'll just stop you from reading anything else.\n\nYou're welcome.",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"stupid question",
			"You know that's a stupid question, right?\nYou already know who I am!\n\n[[Oh, ok.|hi]]\n[[Nope, no idea.|new phone who this]]",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"getting very silly",
			"Well, excuuuse me.\nThe only reason for my existence is to speak to you when you're reading stuff.\nIf you're going to complain about me saying stupid things, why do you bother reading stupid things?\n\n[[You make a compelling argument.| makes sense ]]\n[[Yep, this is still stupid.|stupidend]]",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"shill alert",
			"Hey, I'm just the voice that vocalizes the things you read.\nLet's just blame the idiot who wrote this in the first place instead.\n\nAnywho, it looks like they're running out of words for me to say to you.\nSo I guess this is the end.",
			[]
		)
	);



	theHeccer.addPassageToMap(
		new Passage(
			"whats the buzz",
			"*ahem*\n\nAccording to all known laws of aviation,\nthere is no way that a bee should be able to fly.\nIts wings are too small to get its fat little body off the ground.\nThe bee, of course, flies anyway.\nBecause bees don't care what humans think is impossible.\n\n[[And this is important because...? | whats the point]]\n\n[[Yellow, black. Yellow, black. Yellow black. Yellow, black.|beemoviescript1]]",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"no sense",
			"I agree.\n\nYou know what, let's just stop wasting our time reading this incomprehensible nonsense, and find something else to do.\nYou got any ideas?\n\n[[Let's read something else.|good idea]]\n\n[[Let's write something even better than this, and put the idiot who wrote this to shame.| shameless plug ]]",
			[]
		)
	);



	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

