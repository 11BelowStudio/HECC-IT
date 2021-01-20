
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
        type: "lang",
        regex: /(?:{if:([^}]*)}{(.*)})(?:{else:}{(.*)})/gm,
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
        type: "lang",
        regex: /(?:{if:([^}]*)}{(.*)})/gm,
        replace: function(match, statement, showIf){
            //Doesn't work entirely as intended yet. But it's a start I guess.
            if (theHeccer.checcer.checc(statement)){
                return showIf;
            } else{
                return "";
            }
        }
    };

    /*
    {if: *conditions* }{ text if that thing is true }{else:}{ text if that thing is false}

    not(statement)
    and(statement1, statement2, ...)
    or(statement1, statement2, ...)
    pAny(passage1, passage2, ...)
    pAll(passage1, passage2, ...)
    tAny(tag1, tag2, ...)
    tAll(tag1, tag2, ...)
     */

    return[directPassageLinks, indirectPassageLinks, ifWithElse,ifWithoutElse];
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
