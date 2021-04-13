package oh_hecc.game_parts.component_editing_windows;

import heccCeptions.InvalidMetadataDeclarationException;
import heccCeptions.InvalidPassageNameException;
import oh_hecc.game_parts.EditWindowGameDataInterface;
import oh_hecc.game_parts.GameDataObject;
import oh_hecc.game_parts.metadata.EditableMetadata;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.metadata.SharedMetadata;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Basically this class is a dialog window to be used for editing EditableMetadata objects
 * (or, well, MetadataEditingInterface instances. same thing though)
 */
public class MetadataEditorWindow extends GenericEditorWindow {

    /**
     * you see this thing? yeah. this is what we're gonna be editing.
     */
    private final MetadataEditingInterface theMetadata;


    /**
     * Whether or not the current title input is valid
     */
    private boolean isTitleValid = true;

    /**
     * Whether or not the current author input is valid
     */
    private boolean isAuthorValid = true;

    /**
     * Displays the current title of the game
     */
    //private final AttributeString<String> currentTitle;

    //private JTextArea titleText;

    //the good shit what has been used
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel startPassageLabel;

    private JTextField titleInput;
    private JTextField authorInput;
    private JTextField startInput;

    private JTextArea commentInput;


    /**
     * Creates the MetadataEditorWindow. This is where the magic happens.
     * @param gameData the gameData itself
     */
    public MetadataEditorWindow(EditWindowGameDataInterface gameData){
        super(gameData);
        theMetadata = gameData.getTheMetadata();



        makeTheFrame();

        theFrame.setVisible(true);

        refresh();
    }


    /**
     * This is where the magic happens.
     * Or at least where the actual editor window itself is made.
     * Same thing, basically.
     */
    @Override
    void makeTheFrame(){

        theFrame.setTitle("Metadata Editor Window");


        //a panel to show the current data stuff
        final JPanel currentDataPanel = new JPanel(new GridLayout(2,2));

        currentDataPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Current data"
                )
        ); //border




        //TODO: possibly replace the things that actually display the metadatas with JTextAreas, to allow word wrapping etc
        final JPanel showTitlePanel = new JPanel(new GridLayout(2,1));
        showTitlePanel.add(new JLabel("Title:"));
        titleLabel = new JLabel(theMetadata.getTitle());
        titleLabel.setFont(notBold);
        showTitlePanel.add(titleLabel);
        currentDataPanel.add(showTitlePanel);

        //author
        final JPanel showAuthorPanel = new JPanel(new GridLayout(2,1));
        showAuthorPanel.add(new JLabel("Author:"));
        authorLabel = new JLabel(theMetadata.getAuthor());
        authorLabel.setFont(notBold);
        showAuthorPanel.add(authorLabel);
        currentDataPanel.add(showAuthorPanel);

        //start passage
        final JPanel showStartPanel = new JPanel(new GridLayout(2,1));
        showStartPanel.add(new JLabel("Start passage:"));
        startPassageLabel = new JLabel(theMetadata.getStartPassage());
        startPassageLabel.setFont(notBold);
        showStartPanel.add(startPassageLabel);
        currentDataPanel.add(showStartPanel);

        //ifid
        final JPanel showIfidPanel = new JPanel(new GridLayout(2,1));
        showIfidPanel.add(new JLabel("IFID:"));
        JLabel metaLabel = new JLabel(theMetadata.getIfid());
        metaLabel.setFont(notBold);
        showIfidPanel.add(metaLabel);
        currentDataPanel.add(showIfidPanel);

        //adding the currentDataPanel to theFrame
        theFrame.add(currentDataPanel);


        //title editing panel
        final JPanel titleEditingPanel = new JPanel(new GridLayout(3,1));
        titleEditingPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Edit title"
                )
        ); //border
        final JLabel titleInstructions = new JLabel("Enter title here. Must start and end with non-whitespace characters.");
        titleInstructions.setFont(notBold);
        titleEditingPanel.add(titleInstructions);
        titleInput = new JTextField(theMetadata.getTitle(),0);
        titleInput.getDocument().addDocumentListener(
                new DocumentListener() {
                   @Override
                    public void insertUpdate(DocumentEvent e) {
                        makeSureTitleIsValid();
                    }
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        makeSureTitleIsValid();
                    }
                    @Override
                    public void changedUpdate(DocumentEvent e) {}
                }
        );

        titleEditingPanel.add(titleInput);
        final JButton titleUpdateButton = new JButton("Update title");
        titleUpdateButton.addActionListener(this::attemptTitleUpdate);
        titleEditingPanel.add(titleUpdateButton);

        theFrame.add(titleEditingPanel);



        final JPanel authorEditingPanel = new JPanel(new GridLayout(3,1));
        authorEditingPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Edit author"
                )
        ); //border
        final JLabel authorInstructions = new JLabel("Enter author here. Must start and end with letters. May contain spaces, commas, and full stops.");
        authorInstructions.setFont(notBold);
        authorEditingPanel.add(authorInstructions);
        authorInput = new JTextField(theMetadata.getAuthor(),0);
        authorInput.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) { makeSureAuthorIsValid(); }
                    @Override
                    public void removeUpdate(DocumentEvent e) { makeSureAuthorIsValid(); }
                    @Override
                    public void changedUpdate(DocumentEvent e) {}
                }
        );
        authorEditingPanel.add(authorInput);
        final JButton authorUpdateButton = new JButton("Update author");
        authorUpdateButton.addActionListener(this::attemptAuthorUpdate);
        authorEditingPanel.add(authorUpdateButton);

        theFrame.add(authorEditingPanel);



        final JPanel startEditingPanel = new JPanel(new GridLayout(3,1));
        startEditingPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Edit start passage"
                )
        ); //border
        final JLabel startInstructions = new JLabel("Enter start passage here. Must be a valid passage name.");
        startInstructions.setFont(notBold);
        startEditingPanel.add(startInstructions);
        startInput = new JTextField(theMetadata.getStartPassage(),0);
        addPassageNameDocumentListener(startInput);
        startEditingPanel.add(startInput);
        final JButton startUpdateButton = new JButton("Update start");
        startUpdateButton.addActionListener(this::attemptStartUpdate);
        startEditingPanel.add(startUpdateButton);

        theFrame.add(startEditingPanel);



        final JPanel commentEditingPanel = new JPanel(new BorderLayout());
        commentEditingPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Edit metadata comment (note: readers won't see this)"
                )
        ); //border

        commentInput = new JTextArea();
        commentInput.setRows(10);
        commentInput.setEditable(true);
        commentInput.setLineWrap(true);
        commentInput.setWrapStyleWord(true);

        final JScrollPane commentScroll = new JScrollPane(
                commentInput,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        commentInput.setText(theMetadata.getComment());
        commentEditingPanel.add(commentScroll,BorderLayout.CENTER);
        final JButton commentUpdateButton = new JButton("Update comment");
        commentUpdateButton.addActionListener(this::attemptCommentUpdate);
        commentEditingPanel.add(commentUpdateButton, BorderLayout.SOUTH);

        theFrame.add(commentEditingPanel);


        //no variable stuff because they are currently unimplemented


        theFrame.add(donePanel());


    }

    /**
     * A method to be called by the 'exit' button in the 'done' panel
     *
     * @param e an actionEvent to allow this to be used as a lambda.
     */
    @Override
    void imDone(ActionEvent e) {
        attemptCommentUpdate();
        if (attemptTitleUpdate() && attemptStartUpdate() && attemptAuthorUpdate()) {
            closeTheWindow();
        }
    }

    /**
     * Attempts to update the metadata title
     *
     * @param e so we can use this as a lambda.
     */
    void attemptTitleUpdate(ActionEvent e) {
        attemptTitleUpdate();
    }

    /**
     * Attempts to update the metadata title
     *
     * @return true if it could be updated, false otherwise
     */
    boolean attemptTitleUpdate() {
        final String newTitle = titleInput.getText().trim();
        try {
            if (!isTitleValid) {
                throw new InvalidMetadataDeclarationException(newTitle, "title");
            }
            theMetadata.updateTitle(newTitle);
            showNewTitle();
            return true;
        } catch (InvalidMetadataDeclarationException e) {
            JOptionPane.showMessageDialog(
                    theFrame,
                    "<html><h1>ERROR!</h1>"
                            + "<p>Invalid title input!<br>"
                            + "Titles must start and end with non-whitespace characters<br>"
                            + "but may contain spaces between the first and last characters</p></html>",
                    "Invalid title input!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        showNewTitle();
        return false;
    }

    /**
     * Attempts to update the metadata author
     *
     * @param e so we can use this as a lambda
     */
    void attemptAuthorUpdate(ActionEvent e) {
        attemptAuthorUpdate();
    }

    /**
     * Attempts to update the metadata author
     *
     * @return true if it could be updated, false otherwise
     */
    boolean attemptAuthorUpdate() {
        final String newAuthor = authorInput.getText().trim();
        try {
            if (!isAuthorValid) {
                throw new InvalidMetadataDeclarationException(newAuthor, "author");
            }
            theMetadata.updateAuthor(newAuthor);
            showNewAuthor();
            return true;
        } catch (InvalidMetadataDeclarationException e) {
            JOptionPane.showMessageDialog(
                    theFrame,
                    "<html><h1>ERROR!</h1>"
                            + "<p>Invalid author input!<br>"
                            +"Author names must start and end with letters<br>"
                            + "but may contain spaces, commas, and full stops<br>"
                            + "between the first and last characters</p></html>",
                    "Invalid author input!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        showNewAuthor();
        return false;
    }

    /**
     * Attempts to update the metadata start passage
     *
     * @param e so we can lambda this
     */
    void attemptStartUpdate(ActionEvent e) {
        attemptStartUpdate();
    }


    /**
     * Attempts to update the metadata start passage
     *
     * @return true if it could be updated, false otherwise
     */
    boolean attemptStartUpdate() {
        final String newStart = startInput.getText().trim();
        try {
            if (!isPassageNameValid) {
                throw new InvalidPassageNameException(newStart);
            }
            gameData.updateStartPassage(newStart);
            //TODO: inform user if specified start passage doesn't exist yet
            showNewStartPassage();
            return true;
        } catch (InvalidPassageNameException e) {
            JOptionPane.showMessageDialog(
                    theFrame,
                    "<html><h1>ERROR!</h1>"
                            +"<p>Invalid start passage input!<br>"
                            +"Passage names must start and end with letters,<br>"
                            + "numbers, or underscores, but may contain any<br>"
                            + "number of letters/numbers/underscores or spaces<br>"
                            + "between the first and last characters</p></html>",
                    "Invalid start passage input!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        showNewStartPassage();
        return false;
    }

    /**
     * Attempts to update the metadata comment
     *
     * @param e so this can be lambda'd
     */
    void attemptCommentUpdate(ActionEvent e) {
        attemptCommentUpdate();
    }

    /**
     * Attempts to update the metadata comment
     */
    void attemptCommentUpdate() {
        final String newComment = commentInput.getText().trim();
        theMetadata.updateComment(newComment);
        showNewComment();
    }

    void showNewTitle(){
        final String newTitle = theMetadata.getTitle();
        titleLabel.setText(newTitle);
        titleInput.setText(newTitle);
        refresh();
    }

    void showNewAuthor(){
        final String newAuthor = theMetadata.getAuthor();
        authorLabel.setText(newAuthor);
        authorInput.setText(newAuthor);
        refresh();
    }

    void showNewStartPassage(){
        final String newStart = theMetadata.getStartPassage();
        startPassageLabel.setText(newStart);
        startInput.setText(newStart);
        refresh();
    }

    void showNewComment(){
        final String newComment = theMetadata.getComment();
        commentInput.setText(newComment);
        refresh();
    }




    /**
     * Ensures that the title input text in titleInput is valid
     * It does this by seeing if it satisfies the SharedMetadata.VALID_TITLE_REGEX regex
     * If it's valid, the text will be black.
     * Otherwise, it'll be red.
     */
    private void makeSureTitleIsValid(){
        final boolean stillValid = titleInput.getText().trim().matches(SharedMetadata.VALID_TITLE_REGEX);
        if (isTitleValid ^ stillValid){
            titleInput.setForeground( stillValid ? defaultTextFieldColor : Color.RED);
            isTitleValid = stillValid;
        }
    }

    /**
     * Ensures that the author input text is valid
     * It does this by seeing if it satisfies the SharedMetadata.VALID_AUTHOR_REGEX regex.
     * If it's valid, the text will be black.
     * Otherwise, it'll be red.
     */
    private void makeSureAuthorIsValid(){
        final boolean stillValid = authorInput.getText().trim().matches(SharedMetadata.VALID_AUTHOR_REGEX);
        if (isAuthorValid ^ stillValid){
            authorInput.setForeground( stillValid ?  defaultTextFieldColor : Color.RED);
            isAuthorValid = stillValid;
        }
    }



    public static void main(String[] args){
        final EditableMetadata theTestMetadata = new EditableMetadata("sample title","an author");

        final GameDataObject gdo = new GameDataObject(
                theTestMetadata,
                java.nio.file.Paths.get("Z://samplePath/ok.hecc")
        );

        System.out.println(theTestMetadata.toString());

        //MetadataEditorWindow w = new MetadataEditorWindow(theTestMetadata);
        //MetadataEditorWindow w = theTestMetadata.openEditingWindow();

        final EditorWindowInterface w = gdo.openMetadataEditWindow();

        w.addWindowClosedListener(
                () -> {
                        //making sure that the window updated theTestMetadata, by seeing the printout of its internal state
                        System.out.println(theTestMetadata.toString());
                }
        );



        

    }
}
