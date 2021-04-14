package hecc_up;

/**
 * This is an interface for the Metadata class to be used by FolderOutputter
 */
public interface FolderOutputterMetadataInterface {


    /**
     * Called when writing the index.html page
     * @return the IFID but formatted as HTML
     */
    String getIfidButHtmlFormatted();

    /**
     * Obtains the iFiction version of the metadata
     * @return the metadata but in the form you'd expect in an iFiction file
     */
    String getIFictionMetadata();


    /**
     * gets the Title, but with html escape characters
     * @return the title but with any special html characters escaped and such
     */
    String getTitleHtmlEscaped();

    /**
     * gets the author, but HTML escaped
     * @return the html-escaped version of the author's name
     */
    String getAuthorHTMLEscaped();
}
