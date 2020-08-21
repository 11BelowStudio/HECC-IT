

class Passage{
    constructor(passageName, passageContent){
        //passageName: the string identifier for the passage
        //passageContent: the string content of the passage (it's already in HTML)
        this.name = passageName;
        this.content = passageContent;
    }
    getName(){
        return this.name;
    }
    getContent(){
        return this.content;
    }
}


class GameState{
    constructor(pName){
        //pName: the string identifier for the passage this refers to
        this.passageName = pName;
    }
    getPassageName(){
        return passageName;
    }
}


class GameStateStack{
    constructor(firstState){
        var firstID = "Start"; //starting state is 'Start' by default
        if (firstState != null){
            //setting id for firstState to the specified starting state
            firstID = firstState;
        }
        this.states = [new GameState(firstID)];
    }
    canGoBack(){
        return(this.states.length>1);
    }
    topState(){
        return(this.states[this.states.length -1]);
    }
    pushState(passage){
        this.states.push(new GameState(passageName));
        return this;
    }
    popState(){
        if (this.states.length > 1){
            this.states.pop();
        } else{
            window.alert("why are you trying to go back? theres no prior states to go back to! >:(");
        }
        return this;
    }
}

class Heccer{
    constructor(startState){
        this.passageMap = new Map();
        this.canGoBack = false;
        this.stateStack = new GameStateStack(firstState);
    }
    addPassageToMap(passage){
        this.passageMap.set(passage.getName(), passage.getContent());
    }
    getCanGoBack(){
        return this.canGoBack;
    }
    loadCurrentPassage(){
        this.canGoBack = (sessionStorage.getItem("gameStates")).canGoBack();
        var currentState = (sessionStorage.getItem("gameStates")).topState();
        var currentPassage = passageMap.get(currentState.getPassageName());

        //and now, the bit where I have to replace the HTML passage content that's already on the page.

        document.getElementByID("divWhatHoldsPassageContent").innerHTML = currentPassage.getContent();

        //in theory, that should replace the contents of the "div what holds passage content" div with the content of the new passage.
    }
}

function goToPassage(passageName){
    sessionStorage.setItem("gamestates",(sessionStorage.getItem(gamestates)).pushState(passageName));
    theHeccer.loadCurrentPassage();
}

function backButtonFunction(){
    var states = sessionStorage.getItem("gameStates");
    if (states.canGoBack()){
        sessionStorage.setItem("gamestates",states.popState());
        //document.getElementByID("backButton").disabled = !(states.canGoBack()); //maybe disable button if it can't go back any further?
        theHeccer.loadCurrentPassage();
    }
}