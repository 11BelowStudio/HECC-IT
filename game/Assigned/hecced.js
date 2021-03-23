//HECC UP output (as of 29/01/2021) (Rachel Lowe, 2021)

var startingPassageName = "Start";

function getHECCED(){
	theHeccer.addPassageToMap(
		new Passage(
			"toys",
			"That continues for some time.\n\nYou start to get an ingrained idea of what a 'boy' is, what a 'girl' is, and that you are supposed to be a {if:tAny(\"f\")}{girl}{else:boy}.\n\nTo you, this is probably the least of your concerns, so you just accept that you apparently are a {if:tAny(\"m\")}{boy}{else:girl}, not really giving it much thought.\n\nEspecially not today, because your parents have taken you to the toystore, because you've recently been a very good {if:tAny(\"f\")}{girl}{else:boy} who has been eating all {if:tAny(\"m\")}{his}{else:her} greens!\n\nWhilst your parents are escorting you through the toystore, you notice two particular toys that look rather appealing to you. They're right next to each other on the shelves, and they both look rather fun!\n\nYour parents only said you could have one toy, unfortunately.\n\nSo, which one do you want?\n\n[[The one on the left|monster truck]] or [[the one on the right|dolly]]?",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"{if:Math.random()<0.1}{[[You have just been born.|JustBorn i]]}{else:{if:Math.random()<0.5/}{[[You have just been born.|JustBorn m]]/}{else:[[You have just been born.|JustBorn f]]/}}",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"early life",
			"Even as a baby, society lays out how it expects you to behave.\n\nYour {if:tAny(\"m\")}[blue}{else:pink} romper has the words \"*Little {if:tAny(\"f\")}{Princess}{else:Terror}*\" plastered on it, in large, friendly letters, accompanied by an embroidered cartoony {if:tAny(\"m\")}{dinosaur}{else:fairy}.\n\nOf course, you still don't know how to read, or how to pick up on any blatant symbols, so the meaning plastered across your clothing is completely lost to you.\n\nIt isn't lost on all the adults who stop and gawp at you, as a precious little baby {if:tAny(\"f\")}{girl}{else:boy}, as they form their own, mostly homogenous, impressions of how you will grow up during your life.\n\nExpectations which you are presently blissfully unaware of.\n\n[[For now|toys]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"JustBorn m",
			"{if:tAny(\"i\")}{The problem has been resolved.}{else:Everything is as expected.}\n\nA form is filled in. Boxes are checked off. Choices are filled in.\n\nLiam, this is your life.\n\n[[And you must follow along|early life]].",
			["m","noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"monster truck 2",
			"You play with the monster truck.\n\nYour parents remark at how much of a boy you are{if:tAny(\"f1\")}{, even though you didn't get to have any say in the matter}, saying how *He's just like his daddy!*\n\nYou find yourself given more toys of a similar calibre. Stuff like toy dinosaurs, superhero action figures, and even more toy cars.\n\nYou don't have anything better to do with your time, so you just play with them{if:tAny(\"f1\")}{, but part of you still wants to try playing with something different}.\n\nLife carries on.\n\n[[But now it's time for school.|school 1]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"JustBorn i",
			"The doctors look at you.\n\nYou do not fit neatly into a distinct category.\n\n{if:Math.random()<0.5}{[[They rectify that mistake.|JustBorn m]]}{else:[[They rectify that mistake.|JustBorn f]]}",
			["i","noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"JustBorn f",
			"{if:tAny(\"i\")}{The problem has been resolved.}{else:Everything is as expected.}\n\nA form is filled in. Boxes are checked off. Choices are filled in.\n\nEmma, this is your life.\n\n[[And you must follow along|early life]].",
			["f","noreturn"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"dolly",
			"{if:tAny(\"m\")}{Your parents are less than enthusiastic.\n\n\"*Liam, you silly boy, that's a girl's toy! You aren't a pretty little girl, you're a big, strong, boy! Why don't we get you this cool monster truck instead?*\"\n\nYou can't help but feel disappointed in this outcome. You wanted the dolly, not the monster truck!\n\nBut you're still a small child, so there's nothing you can really do, as your parents [[buy the monster truck|monster truck 2]] for you instead.\n}{else:Your parents are pleased with your decision.\n\nThey chuckle to each other, with a \"*girls will be girls*\", gladly [[buying the dolly|dolly 2]] for you.}",
			["noreturn","f1"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"monster truck",
			"You decide that this monster truck toy looks rather fun, so, you ask your parents if you can have it.\n\n{if:tAny(\"f\")}{Your parents are less than enthusiastic.\n\n\"*Emma, you silly girl, that's a boy toy! You aren't a stinky, nasty, boy, you're a beautiful girl! Why don't we get you this pretty little dolly instead?*\"\n\nYou can't help but feel disappointed in this outcome. You wanted the monster truck, not the dolly!\n\nBut you're still a small child, so there's nothing you can really do, as your parents [[buy the dolly|dolly 2]] for you instead.\n}{else:Your parents are pleased with your decision.\n\nThey chuckle to each other, with a \"*Boys will be boys*\", gladly [[buying the monster truck|monster truck 2]] for you.}",
			["noreturn","m1"]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"school 1",
			"That's right, {if:tAny(\"m\")}{Liam}{else:Emma}, it's time for you to start learning how to be a productive member of society!\n\nBut first things first, you need the uniform, don't you?\n\nGood thing your parents have already bought it for you.\n\nIt isn't much to look at. Just a bland {if:tAny(\"f\")}{cardigan}{else:jumper} with the school crest on it, worn on top of a {if:tAny(\"m\")}{white button-up shirt, with some grey trousers, accompanied by a fake, pre-tied, tie, which stays around your neck with the assistance of some elastic}{else:blue checkered dress (or a white blouse worn underneath a grey pinafore dress when it's colder), with some black tights covering your legs}.\n\nThe PE kit in the uniform is just a shirt with the school crest on it, accompanied with some shorts, and some plimsolls.\n\nYour school shoes are a pair of not overtly attention-grabbing black {if:tAny(\"f\")}{mary-janes, with some subtle flower shapes}{else:velcro shoes, with a subtle football shape} embedded in the faux-leather surface of the shoes.\n\n[[It's the first day of school|school 2]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"dolly 2",
			"You play with the dolly.\n\nYour parents remark at how much of a girl you are{if:tAny(\"m1\")}{, even though you didn't get to have any say in the matter}, saying how *She's just like her mummy!*\n\nYou find yourself given more toys of a similar calibre. Stuff like even more dollies, a toy kitchen, and one of those 'pretend baby' dolls.\n\nYou don't have anything better to do with your time, so you just play with them{if:tAny(\"m1\")}{, but part of you still wants to try playing with something different}.\n\nLife carries on.\n\n[[But now it's time for school.|school 1]]",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"school 2",
			"You are at school.\n\nThe first thing that happens at school is when the teacher reads out all of your names from a book, and you simply need to respond when your name is called out.\n\nYou notice the names of everyone else being called out, and see them responding.\n\nThat's who everyone else is.\n\nEventually, the teacher says\n\n\"*{if:tAny(\"f\")}{Emma}{else:Liam}?*\"\n\n[[It's your turn|school 3]].",
			[]
		)
	);
	theHeccer.addPassageToMap(
		new Passage(
			"school 3",
			"You respond, confirming that you are, in fact, present, and that you actually are called {if:tAny(\"m\")}{Liam}{else:Emma}.",
			[]
		)
	);

	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!

