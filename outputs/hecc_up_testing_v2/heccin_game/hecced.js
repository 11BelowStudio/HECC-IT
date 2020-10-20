//HECC UP output (as of 15/10/2020) (R. Lowe, 2020)

var startingPassageName = "Start";

function getHECCED(){
	theHeccer.addPassageToMap(
		new Passage(
			"dave",
			"<p>This passage is called dave.<br>dave&#39s content doesn&#39t include any links to any other passages.<br>So I guess this counts as the end.</p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"Left",
			"<p>You go to the left, but the path leads you back to <a class='passageLink' onclick='theHeccer.goToPassage(\"dave\")'>dave</a>.</p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"<p>starting passage content goes here.</p><p>The following line contains a link to &quotAnother passage&quot.<br><a class='passageLink' onclick='theHeccer.goToPassage(\"Another passage\")'>Another passage</a></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"Right",
			"<p>You went to the right, but the path leads you back to <a class='passageLink' onclick='theHeccer.goToPassage(\"dave\")'>dave</a>.</p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"Another passage",
			"<p>congrats you clicked that link to get here, Another passage.<br>why not <a class='passageLink' onclick='theHeccer.goToPassage(\"Yet Another Passage\")'>click this</a> as well?</p>",
			["yes","theres","tags"]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"Yet Another Passage",
			"<p>woah you clicked that so you&#39re now at Yet Another Passage.</p><p>Do you want to go <a class='passageLink' onclick='theHeccer.goToPassage(\"Left\")'>Left</a>, <a class='passageLink' onclick='theHeccer.goToPassage(\"Right\")'>Right</a>, <a class='passageLink' onclick='theHeccer.goToPassage(\"Start\")'>Back to the start</a>, or <a class='passageLink' onclick='theHeccer.goToPassage(\"dave\")'>Skip this nonsense</a>?</p>",
			[]
		)
	);



	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

