/**
 * @deprecated
 * now part of heccer.js in this folder.
 *
 * pls to ignore
 */



/**
 An extension for showdown to do some hecc-related stuff
 */
var heccstensionDeprecated = function(){

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

            replacementString = conditionals.filter(replacementString); //process the output of this conditional (for nested conditionals)

            let prefixString = text.substring(0,ifElseStart); //everything before this conditional

            let suffixString = text.substring(ifElseEnd); //everything after this conditional

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


    showdown.extension('heccstensionDeprecated', function () {
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
