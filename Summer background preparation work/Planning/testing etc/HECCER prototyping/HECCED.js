
var startState = "Start";

function getHecced(){

    var stateStack = new GameStateStack("Start");

    console.log(stateStack);
    sessionStorage.setItem("gamestates",JSON.stringify(stateStack));

    theHeccer.addPassageToMap(
            new Passage(
                "Start",
                "<p>hello this is the first passage<br/>do you want to go to the <a class=\"passageLink\" onclick=\"goToPassage('left')\">left path</a> or to <a class=\"passageLink\" onclick=\"goToPassage('dave')\">dave</a>?</p>"
            )
        );

    theHeccer.addPassageToMap(
        new Passage(
            "left",
            "<p>you go to the left.<br/><a class=\"passageLink\" onclick=\"goToPassage('dave')\">dave</a> is also here.</p>"
        )
    );

    theHeccer.addPassageToMap(
        new Passage(
            "dave",
            "<p>hi you're reading the contents of the passage called dave</p>"
        )
    );

    //theHeccer.loadCurrentPassage();
}