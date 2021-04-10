
/*
this is heccer.js (HECC Environment for Runtime) (v0.1)

This is basically a prototype of HECCER, which I mainly made right now because I honestly had no idea how JavaScript actually works.

The game data stuff is in hecced.js (the HECCED output of a HECC game (parsed via HECC-UP))
    * the 'getHECCED' method in hecced.js gives the passage info to the HECCER.

by R. Lowe, 07/09/2020

(this version has been fed into the Hecc Up Parser!)
*/

class Passage{
    constructor(passageName, passageContent, passageTags){
        //passageName: the string identifier for the passage
        //passageContent: the string content of the passage (it's already in HTML)
        //tags: an array of declared 'tags' for the passage
        this.name = passageName;
        this.content = passageContent;
        this.tags = passageTags;
    }
    getName(){
        //returns passage name
        return this.name;
    }
    getContent(){
        //returns passage content
        return this.content;
    }
    getParsedContent(){
        //stores content as tempContent for the time being
        const tempContent = this.content;

    }
    getTags(){
        //returns passage tags
        return this.tags;
    }
    containsSpecifiedTag(specifiedTag){
        //returns whether or not this.tags contains the specifiedTag
            //does this by returning whether or not the result of find(specifiedTag) is undefined or not
            //if undefined, it's not present, else, it is present.
        return (this.tags.find(specifiedTag) !== undefined);
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
    isThisNotTheFirstState(){
        //called when working out if the player is to be allowed to go back or not.
        //true if the length of 'states' is greater than 1, false otherwise
            //if there's only 1 state in the stack, there aren't any earlier stacks to go back to
        return(this.states.length>1);
    }
    getTopState(){
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
        if (this.isThisNotTheFirstState){
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
    getUniqueVisitedStateNames(){
        const uniqueStates = new Set();
        const priorStates = this.states.slice(0,this.states.length-1);
        priorStates.forEach(
            state => uniqueStates.add(state.getPassageName())
        );
        return uniqueStates;
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
        if(this.stateStack.isThisNotTheFirstState()){
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

        const currentState = this.stateStack.getTopState(); //obtains top GameState from stateStack
        //console.log(currentState);

        const pName = currentState.getPassageName(); //obtains the passage name from the currentState
        //console.log(pName);

        const currentPassage = this.passageMap.get(pName); //obtains the passage object from the passageMap which the top GameState refers to
        //console.log(currentPassage);

        if (currentPassage === undefined){

            window.alert("uh oh, there's no passage called " + pName + "!");

        } else{

            const passageContent = currentPassage.getContent(); //obtains passage content from the currentPassage
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

    /**
     * Whether or not any of the named passages have been encountered (OR)
     * @param passageNames the names of the passages being looked for
     * @returns {boolean} false if none were visited, true if at least one was visited
     */
    haveAnyOfThesePassagesBeenVisited(...passageNames){
        if (!this.stateStack.isThisNotTheFirstState()){//}  || passageNames.length === 0){
            //if this is the first state, the other passages haven't been visited yet
            return false;
        }
        const priorStates = this.stateStack.getUniqueVisitedStateNames();
        let wereAnyVisited = false;
        for(let i = passageNames.length-1; i >= 0; i--){
            if (priorStates.has(passageNames[i])){
                wereAnyVisited = true;
                break;
            }
        }
        return wereAnyVisited;
    }

    /**
     * Whether or not all of the named passages have been visited (AND)
     * @param passageNames the names of the passages being looked for
     * @returns {boolean} true if all were visited, false otherwise
     */
    haveAllOfThesePassagesBeenVisited(...passageNames){
        if (!this.stateStack.isThisNotTheFirstState()  || passageNames.length === 0){
            //if this is the first state, the other passages haven't been visited yet
            return false;
        }
        const priorStates = this.stateStack.getUniqueVisitedStateNames();
        let wereAllVisited = true;
        for(let i = passageNames.length-1; i >= 0; i--){
            if (!priorStates.has(passageNames[i])){
                wereAllVisited = false;
                break;
            }
        }
        return wereAllVisited;
    }

    /**
     * Whether or not any of the named tags have been encountered/a passage with any of the named tags was visited (OR)
     * @param tags the passage tags of being looked for
     * @returns {boolean} false if none were encountered, true if at least one was encountered
     */
    haveAnyOfTheseTagsBeenEncountered(...tags){
        if (!this.stateStack.isThisNotTheFirstState()){//}  || tags.length === 0){
            //if this is the first state, the other passages haven't been visited yet
            return false;
        }
        const priorStates = this.stateStack.getUniqueVisitedStateNames();
        const priorTags = new Set();

        priorStates.forEach(
            state => this.passageMap.get(state).getTags().forEach(
                tag => priorTags.add(tag)
            )
        );

        let wereAnyEncountered = false;
        for(let i = tags.length-1; i >= 0; i--){
            if (priorTags.has(tags[i])){
                wereAnyEncountered = true;
                break;
            }
        }
        return wereAnyEncountered;
    }
    /**
     * Whether or not all of the given tags have been encountered in prior passages(AND)
     * @param tags the tags being looked for
     * @returns {boolean} true if all were encountered, false otherwise
     */
    haveAllOfTheseTagsBeenEncountered(...tags){
        if (!this.stateStack.isThisNotTheFirstState() || tags.length === 0){
            //if this is the first state, the other passages haven't been visited yet
            return false;
        }
        const priorStates = this.stateStack.getUniqueVisitedStateNames();
        const priorTags = new Set();

        priorStates.forEach(
            state => this.passageMap.get(state).getTags().forEach(
                tag => priorTags.add(tag)
            )
        );

        let wereAllEncountered = true;
        for(let i = tags.length-1; i >= 0; i--){
            if (!priorTags.has(tags[i])){
                wereAllEncountered = false;
                break;
            }
        }
        return wereAllEncountered;
    }

}
