//HECC UP output (as of 15/10/2020) (R. Lowe, 2020)

var startingPassageName = "Greetings";

function getHECCED(){
	theHeccer.addPassageToMap(
		new Passage(
			"hi",
			"<p>I, the voice inside your head who talks to you to you when you&#39re reading,<br>have a very important thing to talk to you about.</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"whats the buzz\")'>Which is...?</a></p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"whats the point",
			"<p>Hello?<br>Voice inside your head that talks to you when you read things?<br>You read things, I say them to you?<br>That&#39s what you read, so that&#39s what I said.</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"no sense\")'>None of this makes any sense.</a></p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"makes sense",
			"<p>Now, where we were?<br>Ah yes, now I remember.<br>I have a very important announcement to make.</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"whats the buzz\")'>What&#39s the announcement?</a></p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"beemoviescript1",
			"<p>Ooh, black and yellow!<br>Let&#39s shake it up a little.</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"beemoviescript2\")'>Barry! Breakfast is ready!</a></p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"beemoviescript2",
			"<p>Actually, wait, I&#39ve just had a call from my lawyer.<br>It looks like Dreamworks is going to sue us if we continue this performance of The Bee Movie.</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"whats the point\")'>Then why did you start performing it in the first place?</a></p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"good idea",
			"<p>Welp, let&#39s stop wasting our time here, and let&#39s go do that instead!</p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"shameless plug",
			"<p>Well, I have a perfect idea for what we can use to write our even better thing.</p><p>Have you ever heard of a system called HECC-IT?<br>Actually, that&#39s a silly question, seeing as you downloaded this game with that system.<br>So you probably know about it already.</p><p>We&#39ll have to write our game in .hecc format,<br>and then we simply use the HECC-UP program to convert it into a playable HECCIN Game.</p><p>We can read the HECC-SPECC document to find out all the syntax and such for the .hecc format,<br>or we could just look at the .hecc file for this game and work it out from there.</p><p>So, let&#39s write our own HECCIN Game, and put this poor excuse of a story to shame!</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"good idea\")'>I agree, let&#39s do this!</a><br><a class='passageLink' onclick='theHeccer.goToPassage(\"shill alert\")'>This sounds like a very poorly disguised advert.</a></p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"new phone who this",
			"<p>I&#39m the voice inside your head who talks to you when you&#39re reading, obviously.<br>Who else could I have been?<br>It&#39s not like anyone else is currently talking to you.</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"makes sense\")'>That makes sense.</a><br><a class='passageLink' onclick='theHeccer.goToPassage(\"getting very silly\")'>That&#39s stupid!</a></p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"stupidend",
			"<p>Right, that&#39s it.<br>If you&#39re going to complain about me saying what you&#39re reading, I&#39ll just stop you from reading anything else.</p><p>You&#39re welcome.</p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"stupid question",
			"<p>You know that&#39s a stupid question, right?<br>You already know who I am!</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"hi\")'>Oh, ok.</a><br><a class='passageLink' onclick='theHeccer.goToPassage(\"new phone who this\")'>Nope, no idea.</a></p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"getting very silly",
			"<p>Well, excuuuse me.<br>The only reason for my existence is to speak to you when you&#39re reading stuff.<br>If you&#39re going to complain about me saying stupid things, why do you bother reading stupid things?</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"makes sense\")'>You make a compelling argument.</a><br><a class='passageLink' onclick='theHeccer.goToPassage(\"stupidend\")'>Yep, this is still stupid.</a></p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"shill alert",
			"<p>Hey, I&#39m just the voice that vocalizes the things you read.<br>Let&#39s just blame the idiot who wrote this in the first place instead.</p><p>Anywho, it looks like they&#39re running out of words for me to say to you.<br>So I guess this is the end.</p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"Greetings",
			"<p>Hello there.</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"hi\")'>Hello!</a><br><a class='passageLink' onclick='theHeccer.goToPassage(\"stupid question\")'>Who are you?</a></p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"whats the buzz",
			"<p>*ahem*</p><p>According to all known laws of aviation,<br>there is no way that a bee should be able to fly.<br>Its wings are too small to get its fat little body off the ground.<br>The bee, of course, flies anyway.<br>Because bees don&#39t care what humans think is impossible.</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"whats the point\")'>And this is important because...?</a><br><a class='passageLink' onclick='theHeccer.goToPassage(\"beemoviescript1\")'>Yellow, black. Yellow, black. Yellow black. Yellow, black.</a></p><p></p>",
			[]
		)
	);


	theHeccer.addPassageToMap(
		new Passage(
			"no sense",
			"<p>I agree.</p><p>You know what, let&#39s just stop wasting our time reading this incomprehensible nonsense, and find something else to do.<br>You got any ideas?</p><p><a class='passageLink' onclick='theHeccer.goToPassage(\"good idea\")'>Let&#39s read something else.</a><br><a class='passageLink' onclick='theHeccer.goToPassage(\"shameless plug\")'>Let&#39s write something even better than this, and put the idiot who wrote this to shame.</a></p><p></p>",
			[]
		)
	);



	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

