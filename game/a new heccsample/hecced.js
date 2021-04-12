//HECC UP output (as of 12/04/2021) (HECC-IT produced by Rachel Lowe, 2021)

// This hecced.js file contains the data for:
// a new heccsample
// by Rachel Lowe
// IFID: 5B4FD379-ECAE-45E1-A015-902D7CCE1105

var startingPassageName = "Start";

function getHECCED(){

	theHeccer.addPassageToMap(
		new Passage(
			"dave",
			"This passage is called dave.\ndave's content doesn't include any links to any other passages.\nAnd you cannot go back from dave.\nSo I guess this counts as the end.",
			["noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Left",
			"You go to the left, but the path leads you back to [[dave]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"starting passage content goes here.{if:pAny(\"Start\")}{ What else did you expect?}\nThe following line contains a link to \"Another passage\".\n[[Another passage]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Right",
			"You went to the right, but the path leads you back to [[dave]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Another passage",
			"congrats you clicked that link to get here, Another passage.\nwhy not [[click this|Yet Another Passage]] as well?",
			["yes"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Yet Another Passage",
			"woah you clicked that so you're now at Yet Another Passage{if:pAny(\"Yet Another Passage\")}{, yet again!}{else:.}\n\nDo you want to go [[Left]], [[Right]], [[Back to the start|Start]], or [[Skip this nonsense|dave]]?",
			[]
		)
	);

	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!