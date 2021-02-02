//HECC UP output (as of 29/01/2021) (R. Lowe, 2021)

var startingPassageName = "Start";

function getHECCED(){
	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"You find yourself in a dark room.\n\nYou cannot see anything.\n\nBut you can feel [[a button]] on a table in front of you.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"boom",
			"#**BEEP BEEP BEEP BEEP**\n\nBefore you realize what's happening, [[everything fades out to white|bomb]]",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"blueCut",
			"You cut the blue wire.\n\n{if:not(pAny(\"redCut\",\"wiresCut\"))}{You [[wait around|boom]] to see what happens next}{else:Do you [[wait around|boom]] to see what happens next?\n\nOr do you [[cut the red wire|wiresCut]] as well?}",
			["blueCut"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"A box",
			"You open the box and [[look inside|bomb]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"a button",
			"You press the button.\n\nThe lights turn on.\n\n[[A box]] falls down from the ceiling, landing on the table in front of you.\n\nIt's beeping.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"bomb",
			"You find yourself in front of a bomb.\n\nYou know it's a bomb, because, in large, friendly letters on the surface of the bomb, it says **This is a bomb**.\n\nDo you [[look for an exit]], or do you [[attempt to defuse the bomb|bomb1]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"look for an exit",
			"The walls of the room are all completely featureless, with nothing to indicate that an exit even exists.\n\nYou look up at where the bomb fell down from.\n\nThe ceiling looks too high up to reach, and there isn't a hole in the ceiling in the first place.\n\nDo you [[attempt to find a secret exit button]] or do you [[attempt to defuse the bomb|bomb1]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"attempt to find a secret exit button",
			"You fumble around the walls, hoping to find some sort of hidden button that will reveal an exit.\n\nYour search is interrupted when you hear a [[very distinctive noise|boom]] from the middle of the room.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"wiresCut",
			"With both wires cut, you wait to see what happens next.\n\nAnd, for some reason, the cover of the bomb opens up, to reveal two buttons.\n\nOne of those buttons is big, red button, labelled with the word '**DETONATE**'\nThe other one is a small, green button, labelled with a power button (&#x23fb;).\n\nDo you [[press the big red button|boom]]?\nOr do you [[press the small green button|greenPressed]]?",
			["wiresCut"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"greenPressed",
			"The bomb turns off.\n\nyou win.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"bomb1",
			"You notice two wires poking out from the bomb, along with some pliers.  \nOne wire is red, and the other wire is blue.  \nAnd you've seen enough bomb defusal scenes in films to work out that you're supposed to cut at least one wire.\n\nBut which wire do you cut{if:not(pAll(\"redCut\",\"blueCut\"))}{ first}?\n\n{if:not(pAny(\"redCut\"))}{[[The red wire|redCut]]}\n{if:not(pAny(\"blueCut\"))}{[[The blue wire|blueCut]]}\n{if:pAll(\"wiresCut\")}{[[You cut both wires|wiresCut]]}",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"redCut",
			"You cut the red wire.\n\n{if:not(pAny(\"blueCut\",\"wiresCut\"))}{You [[wait around|boom]] to see what happens next}{else:Do you [[wait around|boom]] to see what happens next?\n\nOr do you [[cut the blue wire|wiresCut]] as well?}",
			["redCut"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"notes n such",
			"Sample Content",
			[]
		)
	);

	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

