//HECC UP output (as of 16/04/2021) (HECC-IT produced by Rachel Lowe, 2021)

// This hecced.js file contains the data for:
// the really cool and very very epic maze that is totally cool and epic
// by Rachel Lowe
// IFID: A6F7DBF9-09E8-4157-B36F-79D35A40A825

/*
LEGAL STUFF:
The author of the game held in this hecced.js file shall be considered the
	owner of this file, and may opt to select any license they want for this hecced.js file.
If the author of this game has not selected a license, assume that this hecced.js file has
	been distributed under the terms of the Mozilla Public License (v2.0) by the author.
		You can obtain a copy of that license at http://mozilla.org/MPL/2.0/
If the author of this file wishes to use a different license, they may include another one
	within the source code of this file, underneath this comment block, which shall,
	for all intents and purposes, be considered to be the license for this hecced.js file.
Alternatively, another license may be distributed with this file, in a file called 'LICENSE',
	which shall be the license under which the hecced.js file has been distributed.
TL;DR the author of this game owns and gets to choose the license for this hecced.js file (because it's their game).
*/

var startingPassageName = "A";

function getHECCED(){

	theHeccer.addPassageToMap(
		new Passage(
			"A",
			"welcome to the totes cool maze thats totally epic and such and isn't a scam\n\n[[left|B]]\n[[right|C]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"B",
			"Congrats ur still in the maze\n\n[[left|D]]\n[[right|E]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"C",
			"{if:pAny(\"E\")}{You step out of the wormhole, and are greeted by}{else:Oh look,} some maize. in a maze! whatever next?\n\n[[keep going forwards|F]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"D",
			"oh no! it's a dead end! guess you need to turn around!",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"E",
			"this looks like a nice place to go to.\n\n[[left|G]]\n[[right|H]]\n\n[[actually imma go through that wormhole|C]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"F",
			"Your path is completely blocked by a big pile of maize.\n\nYou can see something that looks like an [[invoice|F invoice]] sellotaped to a piece of maize.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"F invoice",
			"You pick up the invoice.\n\nIt appears that the maize was delivered by *Inexplicable Inconvenient Immovable Maize Deliveries LTD*.\n\nThe price on the invoice is surprisingly reasonable for the sheer quantity of maize that's in your way.\n\nBut that doesn't change the fact that your path is completely blocked, and it doesn't look like this maize is going to be clearable anyway.\n\nYou may as well turn around.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"G",
			"The hedges here look particularly nice.\n\n[[The path turns to the right|I]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"H",
			"The hedges here look particularly bland\n\n[[The path turns to the left|I]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"I",
			"You arrive at a T-junction, opposite a path with some hedges that look particularly {if:pAny(\"G\")}{bland}{else: nice}, which very clearly continues turning to the {if:pAny(\"G\")}{right}{else:left}.\n\n[[May as well use that perpendicular path instead|J]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"J",
			"Kevin is here.\n\n[[Attempt talking to him|talk to Kevin]]\n[[Walk past him|K]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"talk to Kevin",
			"Kevin, being literally just the physical manifestation of the word 'Kevin', in 4-foot tall Calibri font, isn't known for being talkative.\n\nKevin starts asserting dominance over you simply by existing in front of you.\n\nYou can't help but feel like this situation has become rather awkward for everyone involved.\n\nKevin looks like he's blaming you.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"K",
			"Guess what? You're at another junction!\n\n[[left|not the end]]\n[[right|the end]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"not the end",
			"You turn left and are promptly greeted by {if:pAny(\"the end\")}{yet another}{else:a} hedge blocking your path.\n\nIt looks like you have reached a{if:pAny(\"the end\")nother} dead end.\n\nThere is a{if:pAny(\"the end\")nother} rather crudely-constructed sign in front of this hedge.\n\n[[May as well read it|not the end sign]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"the end",
			"You turn right and are promptly greeted by {if:pAny(\"not the end\")}{yet another}{else:a} hedge blocking your path.\n\nIt looks like you have reached a{if:pAny(\"not the end\")nother} dead end.\n\nThere is a{if:pAny(\"not the end\")nother} rather crudely-constructed sign in front of this hedge.\n\n[[May as well read it|the end sign]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"the end sign",
			"It says:\n\n*congrats u reached the end of this totes epic maze*\n\n**no refunds.**\n\nYou can't help but feel disappointed.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"not the end sign",
			"It says:\n\n*Yes, this is a dead end.*",
			[]
		)
	);

	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!