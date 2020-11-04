package heccCeptions;

/**
 * A HeccCeption for invalid metadata declarations
 * (to be thrown when a user attempts to give invalid metadata when editing it via OH-HECC)
 */
public class InvalidMetadataDeclarationException extends HeccCeption {
    /**
     * Throws this exception stating the invalid input and that it's not valid for the field
     * @param invalidInput the input which is invalid
     * @param metadataField the metadata field which it isn't valid for
     */
    public InvalidMetadataDeclarationException(String invalidInput, String metadataField){
        super(invalidInput + " is not a valid value for " + metadataField);
    }

    /**
     * Throws this exception, but only stating the name of the metadata field that had the invalid input
     * @param metadataField the name of the metadata field that the invalid input was input into
     */
    public InvalidMetadataDeclarationException(String metadataField){
        super("Invalid input for " + metadataField + "!");
    }
}
