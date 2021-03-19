package heccCeptions;

/**
 * An exception that will get thrown if a passage still contains a link to a deleted passage.
 */
public class DeletedLinkPresentException extends HeccCeption {

    /**
     * YEET
     *
     * @param p the name of the passage which still links to a deleted passage
     */
    public DeletedLinkPresentException(String p) {
        super("The passage called " + p + " *still* has a link to a deleted passage! pls remove that link k thx.");
    }
}
