//HECC UP output (as of 15/10/2020) (R. Lowe, 2020)

var startingPassageName = "Start";

function getHECCED(){
	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"<p>hi we have no metadata here</p><p>yes its sad, I know</p><p>insert additional funny words here</p><p>lets see if these characters break the output &lt&gt&quot&amp&#39&lt/p&gt eecks dee</p>",
			[]
		)
	);



	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

