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
* LinkHandler method
* BackHandler method

https://www.w3schools.com/Js/js_object_definition.asp
https://www.w3schools.com/Js/js_object_methods.asp

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
        return pName;
    }
}
```

### GameStateStack class

* This is the object that holds the game state stack

* Attributes
    * identifier
        * String that will be used to identify the stack in SessionStorage
        * Will initially just be `HECC`, however, if I can add IFID stuff, it will be `HECC - #[IFID GOES HERE]`
    * states
        * Array of gamestates (this is the stack)
    * length
        * Current length of the stack
* methods
    * constructor
        * Creates the identifier and the states stack
        * No arguments
            * Later versions will be extended with arguments for the IFID (used for identifier), and specifying a particular 'start' passage.
    * getIdentifier()
        * returns the sessionStorage string identifier
    * canGoBack()
        * Returns true if there's more than 1 item in the stack, false otherwise
        * Will be called by `heccer` upon investigating a state change.
    * topState()
        * Returns current GameState object
    * pushState(GameState)
        * adds the new GameState to the top of the stack
        * updates the copy of this stack within SessionStorage
    * popState()
        * pops the topmost GameState from the stack (unless there is only one item in the stack)
        * also updates the copy of this stack within SessionStorage
        
```
class GameStateStack{
    constructor(ifid, firstState){
        this.id = "HECC";
        if (ifid != null){
            //appending IFID to id if it's not null
            this.id = this.id.concat(" - #",ifid);
        }
        var firstID = "Start"; //starting state is 'Start' by default
        if (firstState != null){
            //setting id for firstState to the specified starting state
            firstID = firstState;
        }
        var states = [new GameState(firstID)];
        this.length = states.length;
        sessionStorage.setItem(this.id, this);
    }
    getIdentifier(){
        
    }
    canGoBack(){
        return(length>1);
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
    * `states = new GameStateStack`
* Methods
    * `addPassageToMap(passage){ /* method body goes here */ }`
        * The `HECCED` file will call this method for each passage defined within it, and then the passages will be added to `heccer.passageMap`
        * Parameters
            * `passage`: The passage object that shall be added to the map
        * How it adds the passage to the map
            * `passageMap.set(passage.getName(), passage.getContent());`
                * name used as the key
                * content used as the value
    * `loadPassage()`
        * Obtains the 
            
```
class Heccer{
    constructor(){
    }
}
```

### LinkHandler

* All links within the page content shall have an 

