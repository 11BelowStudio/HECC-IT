package heccCeptions;

/**
 * An exception that will get thrown if a passage still contains a link to a deleted passage.
 */
public class DeletedLinkPresentException extends HeccCeption {

    /**
     * YEET
     *
     * @param passageWithBadLink the name of the passage which still links to a deleted passage
     */
    public DeletedLinkPresentException(String passageWithBadLink) {
        super("The passage called " + passageWithBadLink +
                " *still* has a link to a deleted passage! pls remove that link k thx.");
    }
}
