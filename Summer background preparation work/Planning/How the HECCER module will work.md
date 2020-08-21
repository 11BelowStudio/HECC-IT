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
        return this.name;
    }
    getContent(){
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
        return passageName;
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
    constructor(startState){
        this.states = [new GameState(startState)];
    }
    canGoBack(){
        return(this.states.length>1);
    }
    topState(){
        return(this.states[this.states.length -1]);
    }
    pushState(passage){
        this.states.push(new GameState(passageName));
    }
    popState(){
        if (this.states.length > 1){
            this.states.pop();
        } else{
            window.alert("why are you trying to go back? theres no prior states to go back to! >:(");
        }
    }
    getStackAsJSONString(){
        return JSON.stringify(this.states);
    }
    parseStackFromStringJSON(stackAsString){
        //need to use JSON.parse() to get an array of JSON objects
        //Then for each 
    }
}
```

### HECCER class

* Attributes
    * `passageMap = new map();`
        * map object to hold passage objects
        * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Map
    * `canGoBack = false`;
        * variable to keep track of whether or not the player shall be allowed to go back or not
    * `stateStack = new GameStateStack(startState, false)`
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
class Heccer{
    constructor(){
        passageMap = new map();
        canGoBack = false;
    }
    addPassageToMap(passage){
        passageMap.set(passage.getName(), passage.getContent());
    }
    getCanGoBack(){
        return canGoBack;
    }
    loadCurrentPassage(){
        canGoBack = (sessionStorage.getItem("gameStates")).canGoBack();
        var currentState = (sessionStorage.getItem("gameStates")).topState();
        var currentPassage = passageMap.get(currentState.getPassageName());

        //and now, the bit where I have to replace the HTML passage content that's already on the page.

        document.getElementByID("divWhatHoldsPassageContent").innerHTML = currentPassage.getContent();

        //in theory, that should replace the contents of the "div what holds passage content" div with the content of the new passage.
    }
}
```

### Links to passages

* All links within the page content shall be formatted as `<a class="passageLink" onclick="goToPassage('passageName')">link text</a>` by `HECC-UP`
* The `goToPassage(passageName)` function
    * Must push a new state with the passageName onto the gamestates stack
    * Then must call `heccer.loadCurrentPassage();`
    
```
function goToPassage(passageName){
    sessionStorage.setItem("gamestates",(sessionStorage.getItem(gamestates)).pushState(passageName));
    theHeccer.loadCurrentPassage();
}
```

### The back button

* Will be a `<a id="backButton" onclick="backButtonFunction()">back</a>` or something like that
* `backButtonFunction()` will basically attempt to go back
    * Will obtain the current gamestates
    * Checks if it can go back
        * If it can go back, it pops the top item off the current gamestates, and puts taht back into sessionStorage
        * Then tells heccer to load the current passage.
    * Does nothing if it can't go back

```
function backButtonFunction(){
    var states = sessionStorage.getItem("gameStates");
    if (states.canGoBack()){
        sessionStorage.setItem("gamestates",states.popState());
        //document.getElementByID("backButton").disabled = !(states.canGoBack()); //maybe disable button if it can't go back any further?
        theHeccer.loadCurrentPassage();
    }
}
```


## How this stuff will be set up
* A `Heccer` object, called `theHeccer`, will be declared outside methods (so it has global scope)
    * `var theHeccer = new Heccer();`
* index.html will call a method in `HECCED.js` once it has loaded
    * This method will
        * Set up the gamestate stack
            * `sessionStorage.setItem("gamestates",new GameStateStack());`
        * Feed the passages into `theHeccer`
            * `theHeccer.addPassageToMap(new Passage("dave","<p>hi you're reading the contents of the passage called dave</p>"));`
        * Get `theHeccer` to actually load the first passage
            * `theHeccer.loadCurrentPassage();`