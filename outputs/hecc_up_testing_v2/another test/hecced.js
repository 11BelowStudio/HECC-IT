//HECC UP output (as of 15/10/2020) (R. Lowe, 2020)

var startingPassageName = "Start";

function getHECCED(){
	theHeccer.addPassageToMap(
		new Passage(
			"has a child though",
			"<p>Sample Content</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"another kid ayy nice\")'>another kid ayy nice</a></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"bob",
			"<p>Sample Content</p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"<p>Sample Content</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"child 1\")'>child 1</a></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"child 1",
			"<p>Sample Content</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"grand child\")'>grand child</a> <a class='passageLink' onclick='theHeccer.goToPassage(\"bob\")'>bob</a></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"billy no mates",
			"<p>Sample Content</p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"orphan passage",
			"<p>Sample Content</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"has a child though\")'>has a child though</a></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"another kid ayy nice",
			"<p>Sample Content</p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"grand child",
			"<p>Sample Content</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"another kid ayy nice\")'>another kid ayy nice</a></p>",
			[]
		)
	);



	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

