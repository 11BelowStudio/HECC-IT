package oh_hecc;

import heccCeptions.InvalidPassageNameException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Currently only holds some constants with passage name regexes,
 * and a static method to validate a passage name via regex
 */
public interface Parseable {

    /**
     * A regex pattern for the passage names.
     * Starts and ends with any word character (alphanumeric+underscores),
     * may have spaces and/or - between first and last character (min length of 1)
     */
    String PASSAGE_NAME_REGEX = "([\\w]+[\\w- ]*)?[\\w]+";
    /**
     * Ditto, but allows leading/trailing whitespace
     */
    String PASSAGE_NAME_REGEX_WITH_WHITESPACE = "[\\h]*" + PASSAGE_NAME_REGEX + "[\\h]*";




    /**
     * regex for checking if a string that's just supposed to contain a passage name is valid or not (expects end of line)
     */
    String STANDALONE_PASSAGE_NAME_REGEX_WITH_WHITESPACE = "[\\h]*" + PASSAGE_NAME_REGEX + "(?=\\h*$)";

    /**
     * A method to ensure a given passage name satisfies the passage name regex rules
     * @param passageNameToCheck the passage name to check
     * @return a trimmed version of the valid passage name (if it's valid)
     * @throws InvalidPassageNameException if the passage name was invalid
     */
    static String validatePassageNameRegex(String passageNameToCheck) throws InvalidPassageNameException {
        Matcher validNameMatcher = Pattern.compile(STANDALONE_PASSAGE_NAME_REGEX_WITH_WHITESPACE).matcher(passageNameToCheck);
        if (validNameMatcher.find()){
            return validNameMatcher.group(0).trim();
        } else{
            throw new InvalidPassageNameException(passageNameToCheck);
        }
    }
}
