//HECC UP output (as of 29/01/2021) (Rachel Lowe, 2021)

var startingPassageName = "Start";

function getHECCED(){

	theHeccer.addPassageToMap(
		new Passage(
			"dave",
			"This passage is called dave.\n\ndave's content doesn't include any links to any other passages.\nSo I guess this counts as the end.",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Left",
			"You go to the left, but the path leads you back to [[dave]].",
			["dave"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"starting passage content goes here.\n\nThe following line contains a link to \"Another passage\".\n[[Another passage]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Right",
			"You went to the right, but the path leads you back to [[dave]].",
			["dave"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Another passage",
			"congrats you clicked that link to get here, Another passage.\nwhy not [[click this|Yet Another Passage]] as well?",
			["yes","theres","tags"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Yet Another Passage",
			"woah you clicked that so you're now at Yet Another Passage.\n\nDo you want to go [[Left]], [[Right]], [[Back to the start|Start]], or [[Skip this nonsense|dave]]?",
			[]
		)
	);

	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

