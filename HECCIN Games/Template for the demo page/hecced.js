//HECC UP output (as of 29/01/2021) (R. Lowe, 2021)

var startingPassageName = "Start";

function getHECCED(){
	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"**HeccSample**\nThis was the first game I produced using HECC-IT, mostly as a proof-of-concept before building HECC-IT properly. It ain't much, but it works.\n\n**A Conversation**\nThis was the first 'proper' game produced with HECC-IT, with something that bears a passing resemblence to a proper hypertext game.\nIt's also a **very** poorly disguised shameless plug for this tool.\n\n**Countdown**\nThis was a demonstration I made when I added conditional statements to HECC-IT, incorporating [*Showdown.js*](https://github.com/showdownjs/showdown/) into the output of the tool, both to provide markdown formatting, and also to assist in processing the guard conditions and changing the text of the passage displayed to the reader appropriately.\n\n*Further games will be added to this page when they're done!*",
			[]
		)
	);

	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

