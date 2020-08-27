//HECC UP parser test output (as of 27/08/2020) (R. Lowe, 2020)

var startingPassageName = "Start";

function getHECCED(){
	theHeccer.addPassageToMap(
		new Passage(
			"Another passage",
			"<p>congrats you clicked that link to get here, Another passage.</br>why not <a class=\"passageLink\" onclick='theHeccer.goToPassage(\"Yet Another Passage\")'>click this</a> as well?</p>"
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"Left",
			"<p>You go to the left, but the path leads you back to <a class=\"passageLink\" onclick='theHeccer.goToPassage(\"dave\")'>dave</a>.</p>"
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"Right",
			"<p>You went to the right, but the path leads you back to <a class=\"passageLink\" onclick='theHeccer.goToPassage(\"dave\")'>dave</a>.</p>"
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"<p>starting passage content goes here.</br>The following line contains a link to \"Another passage\".</br><a class=\"passageLink\" onclick='theHeccer.goToPassage(\"Another passage\")'>Another passage</a></p>"
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"Yet Another Passage",
			"<p>woah you clicked that so you're now at Yet Another Passage.</br></br>Do you want to go <a class=\"passageLink\" onclick='theHeccer.goToPassage(\"Left\")'>Left</a>, <a class=\"passageLink\" onclick='theHeccer.goToPassage(\"Right\")'>Right</a>, <a class=\"passageLink\" onclick='theHeccer.goToPassage(\"Start\")'>Back to the start</a>, or <a class=\"passageLink\" onclick='theHeccer.goToPassage(\"dave\")'>Skip this nonsense</a>?</p>"
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"dave",
			"<p>This passage is called dave.</br>dave's content doesn't include any links to any other passages.</br>So I guess this counts as the end.</p>"
		)
	);



	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

