
/**
 An extension for showdown to do some hecc-related stuff
 */
var heccstension = function(){

    //const anyHorizontalWhitespace = "[ \\t\\u00a0\\u1680\\u2000-\\u200a\\u202f\\u2025f\\u3000\\ufeff]*";
    //const passageNameWithWhitespace = anyHorizontalWhitespace + "(([\\w]+[\\w- ]*)?[\\w]+)\\s*" + anyHorizontalWhitespace;
    const directPassageLinks = {
        type: "lang",
        regex: "\\[\\["+passageNameWithWhitespace+"]]",
        replace: function(match, group1, group2){
            return "<a class='passageLink' onClick='theHeccer.goToPassage(\""+group1+"\")'>"+group1+"</a>";
        }
    };
    const indirectPassageLinks = {
        type: "lang",
        regex: "\\[\\["+ anyHorizontalWhitespace + "([^\\[\\]\\|]+)"+anyHorizontalWhitespace+"\\|" + passageNameWithWhitespace + "]]",
        replace: function(match, group1, group2){
            return "<a class='passageLink' onClick='theHeccer.goToPassage(\""+group2+"\")'>"+group1+"</a>";
        }
    };

    const ifWithElse = {
        type: "output",
        regex: /(?:{if:([^}]*)}{(.*)})(?:{else:}{(.*)})/g,
        replace: function(match, statement, showIf, showElse){
            //Doesn't work entirely as intended yet. But it's a start I guess.
            if (theHeccer.checcer.checc(statement)){
                return showIf;
            } else{
                return showElse;
            }
        }
    };

    const ifWithoutElse = {
        type: "output",
        regex: /(?:{if:([^}]*)}{(.*)})/g,
        replace: function(match, statement, showIf){
            //Doesn't work entirely as intended yet. But it's a start I guess.
            if (theHeccer.checcer.checc(statement)){
                return showIf;
            } else{
                return "";
            }
        }
    };

    const anotherRule = {
        type: "output",
        filter: function(text, converter){
            const unescapedCloseBrace = "(?<!\\/)}";
            let ifElseStart = text.search(/(?<!\/){if:/g); //find where the if/else statement starts
            if (ifElseStart === -1){
                return text;
            }
            let trimmedString = text.substring(ifElseStart); //removes everything before the first if
            console.log(trimmedString);
            let statementEnd = trimmedString.search(unescapedCloseBrace);

            let theStatement = trimmedString.substring(4,statementEnd);

            let ifElseEnd = ifElseStart + statementEnd; //find where the if/else statement ends

            theStatement = theStatement.replace(/\/}/g,"}"); //unescaping any escaped }s in the if statement
            console.log("statement:" + theStatement);

            let currentSubstring = trimmedString.substring(statementEnd+1); //move the trimmedString to everything after the statement

            ifElseEnd += 1;

            let ifBranchStart = trimmedString.search("{");
            currentSubstring = currentSubstring.substring(ifBranchStart+1); //trimmedString is now everything after the start of the if branch

            ifElseEnd += (ifBranchStart + 1);

            let ifBranchEnd = currentSubstring.search(unescapedCloseBrace);
            ifElseEnd += (ifBranchEnd + 1);

            let ifBranchText = currentSubstring.substring(0, ifBranchEnd);

            let afterIfBranch = currentSubstring.substring(ifBranchEnd+1);
            let elseBranchDeclaration = afterIfBranch.search("{else:");
            let hasElse = (elseBranchDeclaration !== -1);
            let elseBranchText = "";

            if (hasElse){

                if(/^\s*$/g.test(afterIfBranch.substring(0,elseBranchDeclaration))){

                    console.log("has else");
                    afterIfBranch = afterIfBranch.substring(elseBranchDeclaration + 6);
                    console.log("The after if branch text: " + afterIfBranch);

                    let potentialElseEnd = (elseBranchDeclaration + 6)


                    let elseEnd = afterIfBranch.search(unescapedCloseBrace);

                    if (elseEnd === -1){
                        console.log("actually nope thats not an else");
                        hasElse = false;
                    } else {
                        potentialElseEnd += elseEnd;
                        elseBranchText = afterIfBranch.substring(0,elseEnd);
                        ifElseEnd += potentialElseEnd;
                        ifElseEnd += 1;
                        console.log("Else branch text: " + elseBranchText);
                    }

                } else{
                    hasElse = false;
                    console.log("no else");
                }
            }


            let replacementString = "";

            if(theHeccer.checcer.checc(theStatement)){

                replacementString = ifBranchText;

            } else if (hasElse){

                replacementString = elseBranchText;
                //DO THE ELSE STUFF
            }
            console.log("unescaped replacement string: " + replacementString);
            replacementString = replacementString.replace(/\/}/g,"}"); //unescaping any escaped }s in the if statement
            console.log("escaped replacement string: " + replacementString);
            replacementString = anotherRule.filter(replacementString);

            let prefixString = text.substring(0,ifElseStart);

            let suffixString = text.substring(ifElseEnd);
            console.log("suffix: " + suffixString);

            text = prefixString + replacementString + suffixString;

            return text;

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

    //return[directPassageLinks, indirectPassageLinks, ifWithElse,ifWithoutElse];
    return[directPassageLinks, indirectPassageLinks, anotherRule];
}


/*
(function (extension) {

    if (typeof showdown !== 'undefined') {
        extension(showdown);
    } else if (typeof define === 'function' && define.amd) {
        define(['src-js/showdown/showdown'], extension);
    } else if (typeof exports === 'object') {
        module.exports = extension(require('src-js/showdown/showdown'));
    } else {
        throw Error('Could not find showdown library');
    }
}(function (showdown) {


    showdown.extension('heccstension', function () {
        //const passageNameWithWhitespace = "\\h*(([\\w]+[\\w- ]*)?[\\w]+)\\h*";

        return [
            {
                //direct passage links
                type: "lang",
                //regex: "\\[\\[" + passageNameWithWhitespace + "\\]\\]/gm",
                regex: "\\[\\[\\h*(([\w]+[\w- ]*)?[\w]+)\h*\\]\\]/gm",
                replace: "<a className = 'passageLink' onClick = 'theHeccer.goToPassage(\"$1\")'>$1</a>"
            }
        ];
    });
}));

 */

/*
    var directPassageLinks = {
        type: "lang",
        regex: "\\[\\["+passageNameWithWhitespace+"\\]\\]/gm",
        replace: "<a className = 'passageLink' onClick = 'theHeccer.goToPassage(\"$1\")'>$1</a>"
    };

 */
