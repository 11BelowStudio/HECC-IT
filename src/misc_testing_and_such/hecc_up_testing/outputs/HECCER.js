
/*
this is HECCER.js (HECC Environment for Runtime) (v0.1)

This is basically a prototype of HECCER, which I mainly made right now because I honestly had no idea how JavaScript actually works.

The game data stuff is in HECCED.js (the HECCED output of a HECC game (parsed via HECC-UP))
    * the 'getHECCED' method in HECCED.js

by R. Lowe, 21/8/2020

(this version has been fed into the HeccUpParserTest!)
*/

class Passage{
    constructor(passageName, passageContent){
        //passageName: the string identifier for the passage
        //passageContent: the string content of the passage (it's already in HTML)
        this.name = passageName;
        this.content = passageContent;
    }
    getName(){
        //returns passage name
        return this.name;
    }
    getContent(){
        //returns passage content
        return this.content;
    }
}


class GameState{
    constructor(pName){
        //pName: the string identifier for the passage this refers to
        this.passageName = pName;
    }
    getPassageName(){
        //just returns the passageName
        return this.passageName;
    }
}


class GameStateStack{
    constructor(startPassageName){
        //sets up the 'states' array, initially only holding a gamestate referencing the start passage
        this.states = [new GameState(startPassageName)];
    }
    canGoBack(){
        //called when working out if the player is to be allowed to go back or not.
        //true if the length of 'states' is greater than 1, false otherwise
            //if there's only 1 state in the stack, there aren't any earlier stacks to go back to
        return(this.states.length>1);
    }
    topState(){
        //returns whichever state is the topmost one in the 'states' array-which-is-being-treated-like-a-stack
        return(this.states[this.states.length -1]);
    }
    pushState(passageName){
        //pushes a new gamestate, referring to a passage with the specified name, to the top of the stack
        this.states.push(new GameState(passageName));
    }
    popState(){
        //called when trying to go back
        //first makes very sure that there's a state after this one that the user can go back to
        if (this.canGoBack){
            //if there is a state which it can go back to, it just pops the top state off the stack
            this.states.pop();
        } else{
            //complains (very loudly!) if the player attempts to go back when they aren't allowed to go back
            window.alert("why are you trying to go back? theres no prior states to go back to! >:(");
        }
    }
    getStackAsJSONString(){
        //returns a json stringified version of the states array
        return JSON.stringify(this.states);
    }
    parseStackFromStringJSON(stackAsString){
        //need to make a new empty array for new states (newStates)
        //then need to use JSON.parse() on the stackAsString to get an array of JSON objects (jsonArray)
        //Then for each entry in jsonArray, I'd need to JSON.parse() it again, re-construct it as a GameState object, and then shove it into newStates
        //Finally, replace this.states with newStates.
        //just leaving the method signature here for the time being along with logic for how to get stuff from it
    }
}

class HECCER{
    constructor(startState){
        this.passageMap = new Map(); //empty map that will hold passages
        this.stateStack = new GameStateStack(startState); //a GameStateStack object, starting from the specified startState
    }
    addPassageToMap(passage){
        //called by HECCED.js, to add passage objects to the passageMap, identified by passage name
        //console.log(passage);
        this.passageMap.set(passage.getName(), passage);
    }
    goBack(){
        //The 'back' button will call this method in an attempt to go back
        if(this.stateStack.canGoBack()){
            //will only go back if the stateStack permits it
            this.stateStack.popState(); //pops the top state from stateStack
            this.loadCurrentPassage(); //loads the current passage (topmost on stateStack)
        }
    }
    goToPassage(passageName){
        //called whenever a passage link is clicked
        //console.log(passageName);
        this.stateStack.pushState(passageName); //pushes a new state, referencing the current passage, to top of the stateStack
        this.loadCurrentPassage(); //loads the current passage (topmost on stateStack)
    }
    loadCurrentPassage(){
        //and now, the bit where I have to replace the HTML passage content that's already on the page.

        var currentState = this.stateStack.topState(); //obtains top GameState from stateStack
        //console.log(currentState);

        var pName = currentState.getPassageName(); //obtains the passage name from the currentState
        //console.log(pName);

        var currentPassage = this.passageMap.get(pName); //obtains the passage object from the passageMap which the top GameState refers to
        //console.log(currentPassage);

        if (currentPassage == undefined){

            window.alert("uh oh, there's no passage called " + pName + "!");

        } else{

            var passageContent = currentPassage.getContent(); //obtains passage content from the currentPassage
            //console.log(passageContent);

            document.getElementById("divWhatHoldsPassageContent").innerHTML = passageContent; //loads that passage's content

            //in theory, that should replace the contents of the "div what holds passage content" div with the content of the new passage.
                //update: yep, it does.
        }

        //VERSION OF THIS ENTIRE METHOD BUT IT'S ENTIRELY ON A SINGLE LINE:
        //document.getElementById("divWhatHoldsPassageContent").innerHTML = (this.passageMap.get((this.stateStack.topState()).getPassageName())).getContent();
    }
    printPassages(){
        console.log(this.passageMap);
    }
}

