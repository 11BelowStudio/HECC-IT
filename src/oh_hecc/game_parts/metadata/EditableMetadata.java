package oh_hecc.game_parts.metadata;

import gameParts.Variable;
import heccCeptions.InvalidMetadataDeclarationException;
import heccCeptions.InvalidPassageNameException;
import heccCeptions.NoMatchException;
import oh_hecc.Parseable;

import java.util.ArrayList;
import java.util.UUID;

public class EditableMetadata implements MetadataEditingInterface, MetadataReadingInterface {

    //TODO: The 'editable' bit of this. Methods to edit title, author, variables, start passage, and multiline comment (ensuring new values are valid).

    /**
     * what the game title is. defaults to "An Interactive Fiction"
     */
    private String title = "An Interactive Fiction"; //default title used if undefined


    /**
     * author name. "Anonymous" by default
     */
    private String author = "Anonymous"; //default author name used if undefined


    /**
     * The starting passage. defaults to "Start" if undefined
     */
    private String startPassage = "Start";


    /**
     * The IFID. empty by default
     */
    private String ifid;

    /**
     * All the variable definitions
     */
    private final ArrayList<Variable> variables;

    /**
     * The multline comment held within the metadata. empty by default
     */
    private String comment;


    /**
     * No-argument constructor, only called by the other constructors,
     * that initialises stuff to default values
     */
    private EditableMetadata(){
        title = "An Interactive Fiction";
        author = "Anonymous";
        startPassage = "Start";
        ifid = generateIFIDString();
        variables = new ArrayList<>();
        comment = "";
    }

    /**
     * Metadata constructor that takes an author name and a game title as a constructor
     * Will only be used when creating a completely new HECC file
     */
    public EditableMetadata(String theTitle, String theAuthor){
        this();
        title = theTitle;
        author = theAuthor;
    }

    /**
     * EditableMetadata constructor that takes raw .hecc metadata as a constructor,
     * and uses that to construct this
     * @param rawMetadata the raw hecc metadata that this will be built from
     */
    public EditableMetadata(String rawMetadata){
        this(); //defaults made
        //gets the start passage
        startPassage = MetadataReadingInterface.findStartPassage(rawMetadata);
        //attempts to find the IFID declaration, title declaration, and author declaration
        try{ ifid = MetadataReadingInterface.findIfid(rawMetadata); } catch (NoMatchException ignored){}
        try{ title = MetadataReadingInterface.findTitle(rawMetadata); } catch (NoMatchException ignored){}
        try{ author = MetadataReadingInterface.findAuthor(rawMetadata); } catch (NoMatchException ignored){}

        //then the variables
        variables.addAll(MetadataReadingInterface.findVariables(rawMetadata));

        //finally, the multiline comment
        comment = MetadataReadingInterface.findComment(rawMetadata);
    }




    /**
     * Literally just generates an UUID (used as an IFID), returns its string version in uppercase
     * @return a string with a new UUID (in uppercase)
     */
    private static String generateIFIDString(){
        UUID ifid = UUID.randomUUID();
        return (ifid.toString().toUpperCase());
    }



    /**
     * Attempts to update the title of the game
     * @param newTitle the new title being used
     * @return true if the new title was valid (and the title could be changed)
     * @throws InvalidMetadataDeclarationException if newTitle is invalid
     */
    @Override
    public boolean updateTitle(String newTitle) throws InvalidMetadataDeclarationException {
        title = MetadataEditingInterface.checkTitleValidity(newTitle);
        return true;
    }


    /**
     * Attempts to update the 'author' field of this EditableMetadata object
     * @param newAuthor the new value for the 'author' field
     * @return true if the 'author' was updated successfully
     * @throws InvalidMetadataDeclarationException if the given 'newAuthor' is not a valid 'author' input
     */
    @Override
    public boolean updateAuthor(String newAuthor) throws InvalidMetadataDeclarationException{
        author = MetadataEditingInterface.checkAuthorValidity(newAuthor);
        return true;
    }

    /**
     * Attempts to update the 'startPassage' field of this object
     * @param newStartPassage the name of the new intended start passage
     * @return true if the start passage was updated successfully
     * @throws InvalidPassageNameException if the new start passage isn't valid
     */
    @Override
    public boolean updateStartPassage(String newStartPassage) throws InvalidPassageNameException {
        startPassage = Parseable.validatePassageNameRegex(newStartPassage);
        return true;
    }

    /**
     * updates the comment
     * @param newComment the new comment for the object
     */
    @Override
    public void updateComment(String newComment){
        comment = newComment;
    }

    /**
     * Obtains the 'title' of this object
     * @return the 'title' string
     */
    @Override
    public String getTitle(){
        return title;
    }

    /**
     * Obtains the 'author' belonging to this object
     * @return the 'author' string
     */
    @Override
    public String getAuthor(){
        return author;
    }

    /**
     * Obtains the start passage
     * @return the start passage
     */
    @Override
    public String getStartPassage(){
        return startPassage;
    }

    /**
     * Obtains the IFID
     * @return the IFID
     */
    @Override
    public String getIfid(){
        return ifid;
    }

    /**
     * obtains the comment
     * @return the comment
     */
    @Override
    public String getComment(){
        return comment;
    }

    /**
     * Converts this metadata object into hecc code
     * @return the metadata in .hecc text form
     */
    @Override
    public String toHecc(){
        StringBuilder heccBuilder = new StringBuilder();
        heccBuilder.append("Hecc code generated by OH-HECC at ");
        heccBuilder.append(new java.util.Date().toString()); //casually throwing in when this was made
        heccBuilder.append("\n\n");

        //title declaration
        heccBuilder.append("!StoryTitle: ");
        heccBuilder.append(title);
        heccBuilder.append("\n");

        //author declaration
        heccBuilder.append("!Author: ");
        heccBuilder.append(author);
        heccBuilder.append("\n");

        //IFID declaration
        heccBuilder.append("!IFID: ");
        heccBuilder.append(ifid);
        heccBuilder.append("\n");

        //Start passage declaration
        heccBuilder.append("!StartPassageName: ");
        heccBuilder.append(startPassage);
        heccBuilder.append("\n");


        //Variables
        heccBuilder.append("\n");
        for (Variable v: variables) {
            heccBuilder.append(v.toHecc());
            heccBuilder.append("\n");
        }
        heccBuilder.append("\n");

        //the multiline comment (after informing author that only the multiline comment comments will be preserved by OH-HECC)
        heccBuilder.append("Note to author: only comments saved in this multiline comment area (lines starting with //) will be preserved by OH-HECC!\n");
        heccBuilder.append(multilineCommentHeccFormatted(comment));
        heccBuilder.append("\n\n");

        //annotation indicating that the metadata is over.
        heccBuilder.append("end of metadata. game content starts below:\n");

        return heccBuilder.toString();
    }

    /**
     * Returns the hecc-formatted version of the multiline comment
     * @param multilineComment the multiline comment attribute of this EditableMetadata
     * @return the multiline comment, but formatted as .hecc
     */
    private static String multilineCommentHeccFormatted(String multilineComment){
        String[] segmentsOfTheComment = multilineComment.split("\n");
        StringBuilder heccedMultilineCommentBuilder = new StringBuilder();
        for (String s: segmentsOfTheComment) {
            heccedMultilineCommentBuilder.append("// ");
            heccedMultilineCommentBuilder.append(s);
            heccedMultilineCommentBuilder.append("\n");
        }
        return heccedMultilineCommentBuilder.toString();
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ");
        sb.append(title);
        sb.append("\nAuthor: ");
        sb.append(author);
        sb.append("\nIFID: ");
        sb.append(ifid);
        sb.append("\nStart passage: ");
        sb.append(startPassage);
        sb.append("\nVariables:\n");
        if (variables.isEmpty()){
            sb.append("no variables present\n");
        } else{
            for (Variable v: variables) {
                sb.append(v.toHecc());
                sb.append("\n");
            }
        }
        sb.append("Comment:\n");
        sb.append(comment);
        sb.append("\nEnd of metadata");
        return sb.toString();
    }
}
