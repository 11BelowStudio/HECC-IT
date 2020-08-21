# The HECCER module

## High level overview

* What it does
    * Handles the execution and such of the game
    * Engine
* Inputs
    * Passage data
        * Exported by `HECC-UP`
        * Held in `HECCED`
    * User input events
        * Must respond to users clicking on passage links
        * Must respond to users clicking on the 'back' button
* Outputs
    * Must ensure the content of the currently active passage is displayed in index.html.
    *
    

## Classes it will need

* HECCER class
    * The `heccer` object will have global scope
* GameState class
* GameStateStack class
* Passage class
* goToPassage method
* backButton method

https://www.w3schools.com/Js/js_object_definition.asp
https://www.w3schools.com/Js/js_object_methods.asp

### Index.html overview
* Will contain a `<div id = "divWhatHoldsPassageContent"></div>` div element, to hold the passage content.

### Passage class

* Attributes
    * name
        * string for passage name
    * content
        * string for passage content
* Methods
    * a constructor
    * getter methods for name/content

```
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
```

### GameState class

* Attributes
    * passageName
        * String name of the passage this GameState refers to
* Methods
    * constructor
        * Give it the name of the passage it should refer to
    * getter method for passageName
* When variables are added, an attribute to hold variable value definitions will be added.


```
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
```

### The gamestate stack

* This is the object that holds the game state stack        
* ok so basically it looks like sessionStorage only allows strings, meaning that I'd need to save the stack as a JSON string in sessionStorage, so I'll have to re-evaluate how I do this.
    * And it looks like trying to parse JSON objects back into a form that supports strings 


* Attributes
    * states
        * Array of GameState objects (this is the stack)
* methods
    * constructor
        * Creates the initial stack
        * Parameters
            * startState
                * String that will hold a starting state stack
    * canGoBack()
        * Returns true if there's more than 1 item in the stack, false otherwise
        * Will be called by `heccer` upon investigating a state change.
    * topState()
        * Returns current GameState object
    * pushState(passage)
        * Makes a new GameState referring to the specified passage
            * Pushes this new GameState to the top of the stack
    * popState()
        * pops the topmost GameState from the stack (unless there is only one item in the stack)
    * getStackAsJSONString()
        * Returns a JSON string version of the SessionStorage stack
            * Will be used for saving games
    * parseStackFromStringJSON(stackAsString)
        * Will be used for loading games
            * Parses the JSON string it has been given
            * Converts it back to an array of GameState objects
            * Then replaces `states` with that parsed stack
                
```
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
```

### HECCER class

* Attributes
    * `passageMap = new map();`
        * map object to hold passage objects
        * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Map
    * `stateStack = new GameStateStack(startState);`
        * GameStateStack object
* Methods
    * `addPassageToMap(passage){ /* method body goes here */ }`
        * The `HECCED` file will call this method for each passage defined within it, and then the passages will be added to `heccer.passageMap`
        * Parameters
            * `passage`: The passage object that shall be added to the map
        * How it adds the passage to the map
            * `passageMap.set(passage.getName(), passage.getContent());`
                * name used as the key
                * content used as the value
    * `loadCurrentPassage()`
        * Obtains the topmost item from the gamestates stack in sessionstorage
        * Will then have to load it
            * This is the tricky bit.
```
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

        var passageContent = currentPassage.getContent(); //obtains passage content from the currentPassage
        //console.log(passageContent);

        document.getElementById("divWhatHoldsPassageContent").innerHTML = passageContent; //loads that passage's content

        //in theory, that should replace the contents of the "div what holds passage content" div with the content of the new passage.
            //update: yep, it does.

        //VERSION OF THIS ENTIRE METHOD BUT IT'S ENTIRELY ON A SINGLE LINE:
        //document.getElementById("divWhatHoldsPassageContent").innerHTML = (this.passageMap.get((this.stateStack.topState()).getPassageName())).getContent();
    }
}
```

### Links to passages

* All links within the page content shall be formatted as `<a class="passageLink" onclick="theHeccer.goToPassage('passageName')">link text</a>` by `HECC-UP`
    * Well, given that passage content will be within JavaScript strings, it'll be more like
        * `<a class=\"passageLink\" onclick=\"theHeccer.goToPassage('passageName')\">link text</a>`
* Just calls `theHeccer.goToPassage(passageName)` function when clicked


### The back button

* Will be a `<a id="backButton" onclick="theHeccer.goBack()">back</a>` or something like that
* Attempts to call `theHeccer.goBack()` when clicked
    * Goes back if possible
    * If going back isn't possible, it does nothing.



## How this stuff will be set up
* A `Heccer` object, called `theHeccer`, will be declared outside methods (so it has global scope)
    * `var theHeccer = new HECCER(startingPassageName);`
        * the 'startingPassageName' variable will be declared in `HECCED.js`
* index.html will call a `getHecced` method in `HECCED.js` once it has loaded
    * This method will
        * Set up the gamestate stack
            * `sessionStorage.setItem("gamestates",new GameStateStack());`
        * Feed the passages into `theHeccer`
            * `theHeccer.addPassageToMap(new Passage("dave","<p>hi you're reading the contents of the passage called dave</p>"));`
        * Get `theHeccer` to actually load the first passage
            * `theHeccer.loadCurrentPassage();`