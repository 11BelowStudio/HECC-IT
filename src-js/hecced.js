//HECC UP output (as of 15/10/2020) (R. Lowe, 2020)

var startingPassageName = "1";


function getHECCED(){

	theHeccer.addPassageToMap(
		new Passage(
			"1",
			"<p>1<br><a class='passageLink' onclick='theHeccer.goToPassage(\"2\")'>next</a></p>",
			["A"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"2",
			"<p>2 {($seenTag('A'):)[yes](else:)[no]}</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"3\")'>next</a></p>",
			["B"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"3",
			"<p>3{($seenTag('A'):)[yes](else:)[no]}</p>",
			["C"]
		)
	);


	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

