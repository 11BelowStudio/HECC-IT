//HECC UP test output (as of 12/10/2020) (R. Lowe, 2020)

var startingPassageName = "Start";

function getHECCED(){
	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"<p>hi we have no metadata here</p><p>yes its sad, I know</p><p>insert additional funny words here</p><p></p>",
			[]
		)
	);



	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

