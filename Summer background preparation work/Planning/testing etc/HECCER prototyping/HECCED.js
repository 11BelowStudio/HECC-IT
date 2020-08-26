/*
This is HECCED.js (HECC Exported Data)

This is a representation of the dynamically generated bit of output of HECC-UP
    * This demonstrates what you'd get from HECC-UP if you were to parse the sample code present in the HECC-SPECC v0.1 through HECC-UP
well, it works, at very least!

R. Lowe 21/8/2020
*/


/*
if there's a bit of startState metadata present in the HECC code
    the value of this variable will be set to whatever the specified starting state name was
otherwise
    it defaults to "Start".
*/
var startingPassageName = "Start";

// Other metadata-y variable initialisation-y bits will be here as well (if added to HECC/HECC-UP/HECCER)

/*
this is the 'getHECCED()' function

    It is called by index.html once it's loaded

    It effectively makes the HECCER getHECCED
        (ps: that means that the HECCER will be given the HECCED data :^) (see, totally innocent and such!))
    This contains the declarations for passages which are to be added to the passage map of the HECCER object (theHeccer)

    After the HECCER has been given all the passages, it calls the 'loadCurrentPassage()' method of theHeccer.
        * This will load the specified start passage (and allows the game to start)
*/
function getHECCED(){

    theHeccer.addPassageToMap(
        new Passage(
            "Start",
            "<p>"+
            "starting passage content goes here."+
            "<br/>"+
            "The following line contains a link to Another passage."+
            "<br/>"+
            "<a class=\"passageLink\" onclick=\"theHeccer.goToPassage('Another passage')\">Another passage</a>"+
            "</p>"
        )
    );

    theHeccer.addPassageToMap(
        new Passage(
            "Another passage",
            "<p>"+
            "congrats you clicked that link to get here, Another passage."+
            "<br/>"+
            "why not "+
            "<a class=\"passageLink\" onclick=\"theHeccer.goToPassage('Yet Another Passage')\">click this</a>"+
            " as well?"+
            "</p>"
        )
    );

    theHeccer.addPassageToMap(
        new Passage(
            "Yet Another Passage",
            "<p>"+
            "woah you clicked that so you're now at Yet Another Passage."+
            "<br/>"+
            "<br/>"+
            "Do you want to go "+
            "<a class=\"passageLink\" onclick=\"theHeccer.goToPassage('Left')\">Left</a>"+
            ", "+
            "<a class=\"passageLink\" onclick=\"theHeccer.goToPassage('Right')\">Right</a>"+
            ", "+
            "<a class=\"passageLink\" onclick=\"theHeccer.goToPassage('Start')\">Back to the start</a>"+
            ", or "+
            "<a class=\"passageLink\" onclick=\"theHeccer.goToPassage('dave')\">Skip this nonsense</a>"+
            "?"+
            "</p>"
        )
    );

    theHeccer.addPassageToMap(
        new Passage(
            "Left",
            "<p>"+
            "You go to the left, but the path leads you back to "+
            "<a class=\"passageLink\" onclick=\"theHeccer.goToPassage('dave')\">dave</a>"+
            "."+
            "</p>"
        )
    );

    theHeccer.addPassageToMap(
        new Passage(
            "Right",
            "<p>"+
            "You went to the right, but the path lead you back to "+
            "<a class=\"passageLink\" onclick=\"theHeccer.goToPassage('dave')\">dave</a>"+
            "."+
            "</p>"
        )
    );


    theHeccer.addPassageToMap(
        new Passage(
            "dave",
            "<p>"+
            "This passage is called dave."+
            "<br/>"+
            "dave's content doesn't contain links to any other passages"+
            "<br/>"+
            "So I guess this counts as the end"+
            "<br/>"+
            "Taumatawhakatangihangakoauauotamateaturipukakapikimaungahoronukupokaiwhenuakitanatahu is a very long name for a hill, y'know? Just like how Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch is a long name for a town."+
            "</p>"
        )
    );

    theHeccer.printPassages();

    theHeccer.loadCurrentPassage();
}

/*

    //SOME TESTING FOR PASSAGES CONTAINING SPEECHMARKS AND QUOTATION MARKS IN THEIR NAMES

    theHeccer.addPassageToMap(
        new Passage(
            "dave",
            "<p>"+
            "This passage is called dave."+
            "<br/>"+
            "dave's content links to \"bob\""+
            "<br/>"+
            "As you can see here: "+
            "<a class=\"passageLink\" onclick=\"theHeccer.goToPassage('&quot;bob&quot;')\">\"bob\"</a>"+
            "</p>"
        )
    );

    theHeccer.addPassageToMap(
        new Passage(
            "\"bob\"",
            "<p>"+
            "This passage is called \"bob\"."+
            "<br/>"+
            "\"bob\"'s content links to 'ross'"+
            "<br/>"+
            "As you can see here: "+
            "<a class=\"passageLink\" onclick=\'theHeccer.goToPassage(\"&apos;ross&apos;\")\'>'ross'</a>"+
            "</p>"
        )
    );

    theHeccer.addPassageToMap(
        new Passage(
            "'ross'",
            "<p>"+
            "This passage is called 'ross'."+
            "<br/>"+
            "'ross' doesn't include any links to any other passages."+
            "<br/>"+
            "Except from my "+
            "<a class=\"passageLink\" onclick=\'theHeccer.goToPassage(\"wit&apos;s end\")\'>\"wit's end\"</a>"+
            "</p>"
        )
    );

    theHeccer.addPassageToMap(
        new Passage(
            "wit's end",
            "<p>"+
            "This passage is called wit's end."+
            "<br/>"+
            "Wit's end doesn't include any links to any other passages."+
            "<br/>"+
            "So I guess that it isn't just my wit that's been exhausted."+
            "</p>"
        )
    );
*/