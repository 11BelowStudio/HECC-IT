package GameParts;


/**
 * This class is to be used to keep track of individual variables defined in the metadata
 */
public class Variable {

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
        comment = "";
        String[] splitString;
        if (unparsedText.contains("=")){
            splitString = unparsedText.split("=",2);
            variableName = splitString[0].trim();
            if (unparsedText.contains("//")){
                splitString = splitString[1].split("//",2);
                defaultValue = splitString[0].trim();
                comment = splitString[1].trim();
            } else{
                defaultValue = splitString[1].trim();
            }
        } else if (unparsedText.contains("//")){
            splitString = unparsedText.split("//",2);
            variableName = splitString[0].trim();
            comment = splitString[1].trim();
        } else{
            variableName = unparsedText.trim();
        }
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

}
