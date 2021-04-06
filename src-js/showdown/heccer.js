
/*
this is heccer.js (HECC Environment for Runtime) (v3.0)

The game data stuff is in hecced.js (the HECCED output of a HECC game (parsed via HECC-UP))
    * the 'getHECCED' method in hecced.js gives the passage info to the HECCER.

by Rachel Lowe, 02/02/2021

(this version has been fed into the Hecc Up Parser!)
*/

/**
 * tl;dr JavaScript's /s (whitespace) regex matches linebreaks. Which I don't want. So this is basically just horizontal whitespace
 * @type {string}
 */
var anyHorizontalWhitespace = "[ \\t\\u00a0\\u1680\\u2000-\\u200a\\u202f\\u2025\\u3000\\ufeff]*";
/**
 * Passage name regexes with horizontal whitespace
 * @type {string}
 */
var passageNameWithWhitespace = anyHorizontalWhitespace + "(([\\w]+[\\w- ]*)?[\\w]+)\\s*" + anyHorizontalWhitespace;

/**
 * Tag name regex with horizontal whitespace
 * @type {string}
 */
var tagWithWhitespace = anyHorizontalWhitespace + "([\\w]+)" + anyHorizontalWhitespace;

/**
 * HECC Engine for Runtime
 */
var theHeccer = {

    /**
     * The map of all the passage objects
     * @type {Map<String, Passage>}
     */
    passageMap : new Map(),
    /**
     * The stack of gamestate objects
     */
    stateStack : new GameStateStack(startingPassageName),

    /**
     * Adds a passage object to the passageMap (in the form passageName -> passage)
     * @param passage the passage to add to the map
     */
    addPassageToMap : function(passage){
        theHeccer.passageMap.set(passage.getName(),passage);
    },

    /**
     * resets the heccer
     */
    reset : function(){
        theHeccer.passageMap = new Map();
        theHeccer.stateStack = new GameStateStack(startingPassageName);
    },

    /**
     * Will be called by the 'back' button when attempting to go back.
     * And it will only go back if the 'back' button allows it (there is something to go back to, and the back button isn't disabled)
     */
    goBack: function(){
        //The 'back' button will call this method in an attempt to go back
        if(theHeccer.allowedToGoBack && theHeccer.stateStack.areTherePriorStates()){
            //will only go back if the reader is allowed to go back, and if there are prior states to go back to.
            theHeccer.stateStack.popState(); //pops the top state from stateStack
            theHeccer.loadCurrentPassage(); //loads the current passage (topmost on stateStack)
        }
    },

    /**
     * Called whenever a passage link is clicked
     * @param passageName the name of the passage that should be loaded
     */
    goToPassage: function(passageName){
        theHeccer.stateStack.pushState(passageName); //pushes a new state, referencing the current passage, to top of the stateStack
        theHeccer.loadCurrentPassage(); //loads the current passage (topmost on stateStack)
    },

    /**
     * The bit where the HTML passage content that's already on the page is replaced with the content of the topmost passage on the stack.
     */
    loadCurrentPassage: function(){
        //and now, the bit where I have to replace the HTML passage content that's already on the page.

        const currentState = theHeccer.stateStack.getTopState(); //obtains top GameState from stateStack
        //console.log(currentState);

        const pName = currentState.getPassageName(); //obtains the passage name from the currentState
        //console.log(pName);

        const currentPassage = theHeccer.passageMap.get(pName); //obtains the passage object from the passageMap which the top GameState refers to
        //console.log(currentPassage);

        if (currentPassage === undefined){

            window.alert("uh oh, there's no passage called " + pName + "!");
            theHeccer.stateStack.popState();

        } else{

            const passageContent = currentPassage.getParsedContent(); //obtains passage content from the currentPassage
            //console.log(passageContent);

            document.getElementById("divWhatHoldsPassageContent").innerHTML = passageContent; //loads that passage's content

            //whether or not the current passage allows the player to go back
            const pointOfNoReturn = currentPassage.containsSpecifiedTag("noreturn");

            //if the back button currently is enabled
            if (theHeccer.allowedToGoBack){
                //and if this passage is a point of no return
                if (pointOfNoReturn){
                    //the back button gets disabled.
                    document.getElementById("backButton").innerHTML = theHeccer.noReturnText;
                    theHeccer.allowedToGoBack = false;
                }
            } else if (!pointOfNoReturn){
                //but, if the back button was enabled, and this passage isn't a point of no return, the back button is re-enabled.
                document.getElementById("backButton").innerHTML = theHeccer.defaultBackText;
                theHeccer.allowedToGoBack = true;
            }



            //in theory, that should replace the contents of the "div what holds passage content" div with the content of the new passage.
            //update: yep, it does.
        }

        //VERSION OF THIS ENTIRE METHOD BUT IT'S ENTIRELY ON A SINGLE LINE:
        //document.getElementById("divWhatHoldsPassageContent").innerHTML = (this.passageMap.get((this.stateStack.topState()).getPassageName())).getParsedContent();
    },

    /**
     * default text for the back button
     */
    defaultBackText: "Click this button to go back",

    /**
     * Text for the back button when a 'noreturn' passage is reached
     */
    noReturnText: "POINT OF NO RETURN",

    /**
     * whether or not the player is currently allowed to go back
     */
    allowedToGoBack: true,


    /**
     * Logs the passageMap to the console for debugging reasons
     */
    printPassages: function(){
        console.log(this.passageMap);
    },

    /**
     * Checks the conditionals and such
     */
    checcer: new Checcer(),



};


/**
 * checks the hecc for conditionals and such
 * @constructor
 */
function Checcer(){



    /**
     * Will checc a statement it's been given
     * @param statementToCheck
     * @returns {boolean} whether it evaulates to true or not
     */
    this.checc = function(statementToCheck){

        //TODO: maybe it would be better to change this stuff in the hecc->hecced converter thing?

        let statement = statementToCheck.replace(/pAny\(/g, "theHeccer.checcer.pAny(");
        statement = statement.replace(/pAll\(/g, "theHeccer.checcer.pAll(");

        statement = statement.replace(/tAny\(/g, "theHeccer.checcer.tAny(");
        statement = statement.replace(/tAll\(/g, "theHeccer.checcer.tAll(");

        statement = statement.replace(/and\(/g, "theHeccer.checcer.and(");
        statement = statement.replace(/or\(/g, "theHeccer.checcer.or(");
        statement = statement.replace(/not\(/g, "theHeccer.checcer.not(");

        statement = statement.replace(/tCount\(/g, "theHeccer.checcer.tCount(");
        statement = statement.replace(/pCount\(/g, "theHeccer.checcer.pCount(");

        console.log(statement);

        try {
            return !!eval(statement);
        } catch (e) {
            return false;
        }
    }

    /**
     * Whether or not any of the named passages have been encountered (OR)
     * @param passageNames the names of the passages being looked for
     * @returns {boolean} false if none were visited, true if at least one was visited
     */
    this.pAny = function(...passageNames){
        if (!theHeccer.stateStack.areTherePriorStates()){//}  || passageNames.length === 0){
            //if this is the first state, the other passages haven't been visited yet
            return false;
        }
        const priorStates = theHeccer.stateStack.visitedPassages;
        let wereAnyVisited = false;
        for(let i = passageNames.length-1; i >= 0; i--){
            if (priorStates.has(passageNames[i])){
                wereAnyVisited = true;
                break;
            }
        }
        return wereAnyVisited;
    };

    /**
     * Whether or not all of the named passages have been visited (AND)
     * @param passageNames the names of the passages being looked for
     * @returns {boolean} true if all were visited, false otherwise
     */
    this.pAll = function(...passageNames){
        if (!theHeccer.stateStack.areTherePriorStates()  || passageNames.length === 0){
            //if this is the first state, the other passages haven't been visited yet
            return false;
        }
        const priorStates = theHeccer.stateStack.visitedPassages;
        let wereAllVisited = true;
        for(let i = passageNames.length-1; i >= 0; i--){
            if (!priorStates.has(passageNames[i])){
                wereAllVisited = false;
                break;
            }
        }
        return wereAllVisited;
    };

    /**
     * Whether or not any of the named tags have been encountered/a passage with any of the named tags was visited (OR)
     * @param tags the passage tags of being looked for
     * @returns {boolean} false if none were encountered, true if at least one was encountered
     */
    this.tAny = function(...tags){
        if (!theHeccer.stateStack.areTherePriorStates()){//}  || tags.length === 0){
            //if this is the first state, the other passages haven't been visited yet
            return false;
        }

        const priorTags = theHeccer.stateStack.seenTags;

        let wereAnyEncountered = false;
        for(let i = tags.length-1; i >= 0; i--){
            if (priorTags.has(tags[i])){
                wereAnyEncountered = true;
                break;
            }
        }
        return wereAnyEncountered;
    };

    /**
     * Whether or not all of the given tags have been encountered in prior passages(AND)
     * @param tags the tags being looked for
     * @returns {boolean} true if all were encountered, false otherwise
     */
    this.tAll = function(...tags){
        if (!theHeccer.stateStack.areTherePriorStates() || tags.length === 0){
            //if this is the first state, the other passages haven't been visited yet
            return false;
        }

        const priorTags = theHeccer.stateStack.seenTags;

        let wereAllEncountered = true;
        for(let i = tags.length-1; i >= 0; i--){
            if (!priorTags.has(tags[i])){
                wereAllEncountered = false;
                break;
            }
        }
        return wereAllEncountered;
    };

    /**
     * performs an AND for all the given statements
     * @param statements the statements to check
     * @returns {boolean} true if they are all true, false otherwise
     */
    this.and = function(...statements){

        let notFalseYet = true;
        let nothingTrueYet = true;
        for(let i = statements.length - 1; i >= 0; i--){
            let currentResult = false;
            try{
                currentResult = !!eval(statements[i]);
            } catch{
                currentResult = false;
            }

            if (currentResult){
                if (nothingTrueYet){
                    nothingTrueYet = false;
                }
            } else{
                notFalseYet = false;
                break;
            }
        }

        return ((!nothingTrueYet) && notFalseYet);

    }

    /**
     * Performs an OR for all the given statements
     * @param statements the statements to check
     * @returns {boolean} true if at least one is true, false otherwise
     */
    this.or = function(...statements){

        let result = false;
        for(let i = statements.length - 1; i >= 0; i--){
            try {
                if (!!eval(statements[i])) {
                    result = true;
                    break;
                }
            } catch (e) {} //if current eval throws an exception, skip it.
        }
        return result;
    }

    /**
     * Performs a binary NOT for the given statement
     * @param statement the statement to check
     * @returns {boolean} the reverse of its result
     */
    this.not = function (statement) {
        return (!eval(statement));
    }

    /**
     * Returns count of times that passages with the given tag have been visited.
     * @param tagName the tag that is being looked for
     * @returns {Number} the number of times a passage with that tag has been visited. 0 if unvisited.
     */
    this.tCount = function (tagName) {
        if (theHeccer.stateStack.seenTags.has(tagName)) {
            return theHeccer.stateStack.seenTags.get(tagName);
        } else {
            return 0;
        }
    }

    /**
     * Returns count of times that a given passage has been visited.
     * @param passageName the passage that is being looked at
     * @returns {Number} the number of times which that particular passage has been visited. 0 if unvisited.
     */
    this.pCount = function (passageName) {
        if (theHeccer.stateStack.visitedPassages.has(passageName)) {
            return theHeccer.stateStack.visitedPassages.get(passageName);
        } else {
            return 0;
        }
    }
}

function GameState(pName) {
    this.passageName = pName;
    this.getPassageName = function () {
        return this.passageName;
    };
}

function GameStateStack(startPassageName){
    //sets up the 'states' array, initially only holding a gamestate referencing the start passage
    this.states = [new GameState(startPassageName)];

    /**
     * Called when working out if there's prior states or not.
     * @returns {boolean} true if the 'states' array has length greater than 1, false otherwise
     */
    this.areTherePriorStates = function(){
        return(this.states.length>1);
    };

    /**
     * Returns the topmost GameState from the states stack
     * @returns {GameState} topmost GameState
     */
    this.getTopState = function(){
        return(this.states[this.states.length -1]);
    };

    /**
     * pushes a new gamestate, referring to a passage with the specified name, to the top of the stack
     * @param passageName the name of the passage being pushed
     */
    this.pushState = function(passageName){
        this.states.push(new GameState(passageName));
        this.refreshVisitedStuff();
    };

    /**
     * Called when trying to go back.
     * Pops the topmost item from the stack, complains if the current gamestate is the only one in the stack.
     */
    this.popState = function(){
        //called when trying to go back
        //first makes very sure that there's a state after this one that the user can go back to
        if (this.areTherePriorStates) {
            //if there is a state which it can go back to, it just pops the top state off the stack
            this.states.pop();
            this.refreshVisitedStuff();
        } else {
            //complains (very loudly!) if the player attempts to go back when they aren't allowed to go back
            window.alert("why are you trying to go back? theres no prior states to go back to! >:(");
        }
    };

    /**
     * Refreshes info about visited tags/passages whenever a new passage is navigated to/from
     */
    this.refreshVisitedStuff = function () {

        /**
         * map of visited passage names and the count of times they were visited
         * @type {Map<String, Number>}
         */
        const passages = new Map();

        /**
         * map of encountered passage tags and the count of times they were visited
         * @type {Map<String, Number>}
         */
        const tags = new Map();

        if (this.areTherePriorStates) {

            const prior = this.states.slice(0, this.states.length - 1);
            prior.forEach(
                state => {
                    let pname = state.getPassageName();
                    if (passages.has(pname)) {
                        let count = passages.get(pname);
                        count += 1;
                        passages.set(pname, count);
                    } else {
                        passages.set(pname, 1);
                    }
                    theHeccer.passageMap.get(pname).getTags().forEach(
                        tag => {
                            if (tags.has(tag)) {
                                let count = tags.get(tag);
                                count += 1;
                                tags.set(tag, count);
                            } else {
                                tags.set(tag, 1);
                            }
                        }
                    );
                }
            );
        }
        this.seenTags = tags;
        this.visitedPassages = passages;
    }

    /**
     * returns a json stringified version of the states array
     * @returns {string} json stringified states array
     */
    this.getStackAsJSONString = function(){
        return JSON.stringify(this.states);
    };

    /**
     * need to make a new empty array for new states (newStates)
     * then need to use JSON.parse() on the stackAsString to get an array of JSON objects (jsonArray)
     * Then for each entry in jsonArray, I'd need to JSON.parse() it again, re-construct it as a GameState object, and then shove it into newStates
     * Finally, replace this.states with newStates.
     * just leaving the method signature here for the time being along with logic for how to get stuff from it
     * @param stackAsString Stringified version of the gamestates stack
     */
    this.parseStackFromStringJSON = function(stackAsString){
    };

    /**
     * Gets all the names of the unique previously visited states (as a set)
     * @returns {Set<String>} The names of every previously visited state (but with no duplicates)
     */
    this.getUniqueVisitedStateNames = function(){
        const uniqueStates = new Set();
        const priorStates = this.states.slice(0,this.states.length-1);
        priorStates.forEach(
            state => uniqueStates.add(state.getPassageName())
        );
        return uniqueStates;
    };
}

/**
 * The passage objects
 * @param passageName the name for the passage
 * @param passageContent the content of the passage
 * @param passageTags the tags for the passage
 * @constructor bottom text
 */
function Passage(passageName, passageContent, passageTags){
    this.name = passageName;
    this.content = passageContent;
    this.tags = passageTags;

    /**
     * returns name of the passage
     * @returns {string} passage name
     */
    this.getName = function(){
        return this.name;
    };

    /**
     * returns the passage contents
     * @returns {string} contents of the passage
     */
    this.getContent = function(){
        return this.content;
    };

    /**
     * Returns the passage contents but parsed by the global showdown converter
     * @returns {string} the passage content but markdown formatted + hecc formatted
     */
    this.getParsedContent = function(){
        //stores content as tempContent for the time being
        const tempContent = this.content;
        //const htmlContent = converter.makeHtml(tempContent);
        return converter.makeHtml(tempContent);
    };

    /**
     * Returns the array of tags for this passage
     * @returns {array<string>} tags that this passage has
     */
    this.getTags = function(){
        return this.tags;
    };

    /**
     * returns whether or not this.tags contains the specifiedTag
     * does this by returning whether or not the result of find(specifiedTag) is undefined or not
     * if undefined, it's not present, else, it is present.
     * @param specifiedTag the tag being looked for in the tags
     * @returns {boolean} true if the specifiedTag is present, false otherwise
     */
    this.containsSpecifiedTag = function(specifiedTag){
        //returns whether or not this.tags contains the specifiedTag
        //does this by checking tags.includes(specifiedTag).
        //true if present, false otherwise.
        return (this.tags.includes(specifiedTag));
    };
}



/**
 An extension for showdown to do some hecc-related stuff
 */
var heccstension = function(){

    //const anyHorizontalWhitespace = "[ \\t\\u00a0\\u1680\\u2000-\\u200a\\u202f\\u2025f\\u3000\\ufeff]*";
    //const passageNameWithWhitespace = anyHorizontalWhitespace + "(([\\w]+[\\w- ]*)?[\\w]+)\\s*" + anyHorizontalWhitespace;

    /**
     * This will convert any direct passage links (in the form [[PassageName]]) into hecc links. 'lang' rule so it's done before other stuff.
     * @type {{regex: string, replace: (function(*, *, *): string), type: string}}
     */
    const directPassageLinks = {
        type: "lang", //done after everything else
        regex: "\\[\\["+passageNameWithWhitespace+"]]",
        replace: function(match, group1, group2){
            return "<a class='passageLink' onClick='theHeccer.goToPassage(\""+group1+"\")'>"+group1+"</a>";
        }
    };
    /**
     * This will convert any indirect passage links (in the form [[link text|PassageName]]) into hecc links. 'lang' rule so it's done before other stuff.
     * @type {{regex: string, replace: (function(*, *, *): string), type: string}}
     */
    const indirectPassageLinks = {
        type: "lang", //done before everything else
        regex: "\\[\\["+ anyHorizontalWhitespace + "([^\\[\\]\\|]+)"+anyHorizontalWhitespace+"\\|" + passageNameWithWhitespace + "]]",
        replace: function(match, group1, group2){
            return "<a class='passageLink' onClick='theHeccer.goToPassage(\""+group2+"\")'>"+group1+"</a>";
        }
    };

    /**
     * This basically processes the conditional statements within the hecc, outputting the text which is supposed to be displayed if they evaluate to true.
     * Also handles nested if statements.
     * @type {{filter: (function(*, *): (*)), type: string}}
     */
    const conditionals = {
        type: "lang",
        filter: function(text, converter){

            //a regex check for a } which does not have a / in front of it
            const unescapedCloseBrace = /(?<!\/)}/g;

            //look for where the if/else statement starts
            let ifElseStart = text.search(/(?<!\/){if:/g); //find where the if/else statement starts
            if (ifElseStart === -1){
                //if nothing's found, return text as-is
                return text;
            }
            //console.log(text);

            let trimmedString = text.substring(ifElseStart); //removes everything before the first if
            //console.log(trimmedString);
            let statementEnd = trimmedString.search(unescapedCloseBrace);

            let theStatement = trimmedString.substring(4,statementEnd); //find the statement itself

            let ifElseEnd = ifElseStart + statementEnd; //find where the if/else statement ends

            theStatement = theStatement.replace(/\/}/g,"}"); //unescaping any /'d }s in the if statement
            //console.log("statement:" + theStatement);

            let currentSubstring = trimmedString.substring(statementEnd+1); //move the trimmedString to everything after the statement

            ifElseEnd += 1;

            //find where the if branch starts
            let ifBranchStart = trimmedString.search("{");
            currentSubstring = currentSubstring.substring(ifBranchStart+1); //currentSubstring is now everything after the start of the if branch

            ifElseEnd += (ifBranchStart + 1);

            let ifBranchEnd = currentSubstring.search(unescapedCloseBrace); //where the if branch ends
            ifElseEnd += (ifBranchEnd + 1);

            let ifBranchText = currentSubstring.substring(0, ifBranchEnd); //the entire if branch

            //console.log("if branch text: " + ifBranchText);

            let afterIfBranch = currentSubstring.substring(ifBranchEnd+1);
            let elseBranchDeclaration = afterIfBranch.search("{else:"); //look for an else branch
            let hasElse = (elseBranchDeclaration !== -1); //if there is no else branch, don't bother validating it
            let elseBranchText = ""; //else branch text initially false

            if (hasElse){
                //if there's just whitespace between the end of the if and the start of the else, we associate the else with the if
                if(/^\s*$/g.test(afterIfBranch.substring(0,elseBranchDeclaration))){

                    //and then we basically extract the else text and find out where the entire if/else ends

                    //console.log("has else");
                    afterIfBranch = afterIfBranch.substring(elseBranchDeclaration + 6);
                    //console.log("The after if branch text: " + afterIfBranch);


                    let potentialElseEnd = (elseBranchDeclaration + 6)


                    let elseEnd = afterIfBranch.search(unescapedCloseBrace);

                    //if there's no end to the else, we ignore it.
                    if (elseEnd === -1){
                        //console.log("actually nope thats not an else");
                        hasElse = false;
                    } else {
                        potentialElseEnd += elseEnd;
                        elseBranchText = afterIfBranch.substring(0,elseEnd);
                        ifElseEnd += potentialElseEnd;
                        ifElseEnd += 1;

                    }

                } else{
                    hasElse = false;
                    //console.log("no else");
                }
            }
            //console.log("Else branch text: " + elseBranchText);

            let replacementString = "";

            //if theStatement evaluates to true when checced
            if(theHeccer.checcer.checc(theStatement)){

                //use the ifBranchText
                replacementString = ifBranchText;

            } else if (hasElse){
                //if it's false, and there's an else branch, use the elseBranchText
                replacementString = elseBranchText;

            }
            //console.log("unescaped replacement string: " + replacementString);

            replacementString = replacementString.replace(/\/}/g,"}"); //unescaping any escaped /}s in the if statement

            //console.log("escaped replacement string: " + replacementString);

            replacementString = conditionals.filter(replacementString, converter); //process the output of this conditional (for nested conditionals)



            let prefixString = text.substring(0,ifElseStart); //everything before this conditional

            let suffixString = text.substring(ifElseEnd); //everything after this conditional

            suffixString = conditionals.filter(suffixString, converter);

            text = prefixString + replacementString + suffixString; //basically replace the conditional with the output we just found

            return text; //and return it

        }
    }

    /*
    {if: conditions }{ text if that thing is true }{else:text if that thing is false}

    not(statement)
    and(statement1, statement2, ...)
    or(statement1, statement2, ...)
    pAny(passage1, passage2, ...)
    pAll(passage1, passage2, ...)
    tAny(tag1, tag2, ...)
    tAll(tag1, tag2, ...)
     */


    return[directPassageLinks, indirectPassageLinks, conditionals];
}




