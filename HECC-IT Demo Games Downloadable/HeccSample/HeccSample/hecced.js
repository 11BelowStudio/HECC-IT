//HECC UP output (as of 16/04/2021) (HECC-IT produced by Rachel Lowe, 2021)

// This hecced.js file contains the data for:
// HECCSample
// by Rachel Lowe
// IFID: DE7B3D02-81BB-4C2A-82BA-7CA9398B2262

/*
LEGAL STUFF:
The author of the game held in this hecced.js file shall be considered the
	owner of this file, and may opt to select any license they want for this hecced.js file.
If the author of this game has not selected a license, assume that this hecced.js file has
	been distributed under the terms of the Mozilla Public License (v2.0) by the author.
		You can obtain a copy of that license at http://mozilla.org/MPL/2.0/
If the author of this file wishes to use a different license, they may include another one
	within the source code of this file, underneath this comment block, which shall,
	for all intents and purposes, be considered to be the license for this hecced.js file.
Alternatively, another license may be distributed with this file, in a file called 'LICENSE',
	which shall be the license under which the hecced.js file has been distributed.
TL;DR the author of this game owns and gets to choose the license for this hecced.js file (because it's their game).
*/

var startingPassageName = "Start";

function getHECCED(){

	theHeccer.addPassageToMap(
		new Passage(
			"Start",
			"starting passage content goes here.\n\nThe following line contains a link to \"Another passage\".\n[[Another passage]]",
			[]
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
	theHeccer.addPassageToMap(
		new Passage(
			"dave",
			"This passage is called dave.\ndave's content doesn't include any links to any other passages.\nSo I guess this counts as the end.",
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
			"Right",
			"You went to the right, but the path leads you back to [[dave]].",
			["dave"]
		)
	);

	theHeccer.printPassages();

	theHeccer.loadCurrentPassage();

}

//that's all, folks!