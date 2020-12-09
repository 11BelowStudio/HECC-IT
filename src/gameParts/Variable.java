package gameParts;


import oh_hecc.Heccable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is to be used to keep track of individual variables defined in the metadata
 */
public class Variable implements Heccable {

    /**
     * The name of the variable
     */
    String variableName;
    /**
     * The default value for the variable
     */
    String defaultValue;
    /**
     * The comment associated with the variable
     */
    String comment;


    /**
     * Constructor for variables when it gets parsed from the HECC file
     * @param unparsedText the unparsed variable text, fresh from the HECC file
     */
    public Variable(String unparsedText){
        defaultValue = "0";
        comment = getInlineComment(unparsedText);
        String[] splitString;
        if (unparsedText.contains("=")){
            splitString = unparsedText.split("=",2);
            variableName = splitString[0].trim();
            if (unparsedText.contains("//")){
                splitString = splitString[1].split("//",2);
                defaultValue = splitString[0].trim();
                //comment = splitString[1].trim();
            } else{
                defaultValue = splitString[1].trim();
            }
        } else if (unparsedText.contains("//")){
            splitString = unparsedText.split("//",2);
            variableName = splitString[0].trim();
            //comment = splitString[1].trim();
        } else{
            variableName = unparsedText.trim();
        }
    }

    /**
     * Extracts the comment from the raw data
     * @param rawData the raw data
     * @return The comment from the raw data (everything between a // and the end of the line).
     * returns empty string if no comment is found.
     */
    private String getInlineComment(String rawData){
        Matcher inlineCommentMatcher = Pattern.compile(
                "((?<=\\/\\/).*)"
        ).matcher(rawData); //matches everything between the first // and the end of the line
        String theComment = ""; //blank comment by default
        if (inlineCommentMatcher.find()){
            theComment = inlineCommentMatcher.group(0).trim(); //obtains the comment (and also trims it) if it exists
            //System.out.println(theComment);
        }
        return theComment; //returns the comment

    }

    /**
     * A constructor where you define the name, value, and comment manually
     */
    public Variable(String variableName, String defaultValue, String comment){
        this.variableName = variableName;
        this.defaultValue = defaultValue;
        this.comment = comment;
    }

    public boolean equals(Variable other){
        return (
                (variableName.equals(other.variableName))
                && (defaultValue.equals(other.defaultValue))
                && (comment.equals(other.comment))
        );
    }

    public String toString(){
        return (
                "Name: " + variableName + "\n"
                + "Value: " + defaultValue + "\n"
                + "Comment: " + comment
        );
    }

    /**
     * Obtains the .hecc representation of this variable
     * @return this as .hecc code
     */
    @Override
    public String toHecc() {
        StringBuilder heccBuilder = new StringBuilder();
        heccBuilder.append("!var: ");
        heccBuilder.append(variableName);
        heccBuilder.append(" = ");
        heccBuilder.append(defaultValue);
        heccBuilder.append(" // ");
        heccBuilder.append(comment);
        return heccBuilder.toString();
    }
}
