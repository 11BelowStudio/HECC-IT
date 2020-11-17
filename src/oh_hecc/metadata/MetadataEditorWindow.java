package oh_hecc.metadata;

import heccCeptions.InvalidMetadataDeclarationException;
import heccCeptions.InvalidPassageNameException;
import utilities.AttributeString;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MetadataEditorWindow {
    
    MetadataEditingInterface theMetadata;

    JFrame theFrame;


    AttributeString<String> currentTitle;
    AttributeString<String> currentAuthor;
    AttributeString<String> currentStartPassage;
    final AttributeString<String> currentIFID;

    JTextArea titleText;
    JTextArea authorText;
    JTextArea startText;

    JLabel titleLabel;
    JLabel authorLabel;
    JLabel startPassageLabel;

    JTextField titleInput;
    JTextField authorInput;
    JTextField startInput;

    JTextArea commentInput;
    
    

    MetadataEditorWindow(EditableMetadata metadata){
        theMetadata = metadata;
        currentTitle = new AttributeString<>("Title:\n", metadata.getTitle());
        currentAuthor = new AttributeString<>("Author:\n", metadata.getAuthor());
        currentStartPassage = new AttributeString<>("Start passage:\n", metadata.getStartPassage());
        currentIFID = new AttributeString<>("IFID:\n",metadata.getIfid());

        makeTheFrame();

        theFrame.setVisible(true);

        refresh();
    }

    private void refresh(){
        theFrame.pack();
        theFrame.revalidate();
    }
    
    private void makeTheFrame(){
        theFrame = new JFrame("Metadata Editor Window");

        theFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //This basically allows a quit prompt to appear when the user tries to press the 'x' button on the window
        //If the user confirms that they want to close this window, this window closes.
        theFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        confirmWindowClose();
                    }
                }
        );
        


        //A lowered etched border
        Border loweredEtchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        //theFrame will use a BoxLayout.
        theFrame.setLayout(new BoxLayout(theFrame.getContentPane(), BoxLayout.Y_AXIS));

        //a panel to show the current data stuff
        JPanel currentDataPanel = new JPanel(new GridLayout(2,2));

        currentDataPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Current data"
                )
        ); //border




        //JFormattedTextField.AbstractFormatterFactory formatterFactory = new DefaultFormatterFactory();

        //showing current data

        Font notBold = new JLabel().getFont().deriveFont(Font.PLAIN);
        System.out.println("not bold: " + notBold);

        //title
        titleText = new JTextArea(theMetadata.getTitle(),0,0);
        titleText.setLineWrap(true);
        titleText.setWrapStyleWord(true);
        titleText.setEditable(false);
        //currentDataPanel.add(titleText);
        //itleText.setRows(3);
        //titleText.setText();

        //TODO: possibly replace the things that actually display the metadatas with JTextAreas, to allow word wrapping etc
        JPanel showTitlePanel = new JPanel(new GridLayout(2,1));
        showTitlePanel.add(new JLabel("Title:"));
        titleLabel = new JLabel(theMetadata.getTitle());
        titleLabel.setFont(notBold);
        showTitlePanel.add(titleLabel);
        currentDataPanel.add(showTitlePanel);

        //author
        JPanel showAuthorPanel = new JPanel(new GridLayout(2,1));
        showAuthorPanel.add(new JLabel("Author:"));
        authorLabel = new JLabel(theMetadata.getAuthor());
        authorLabel.setFont(authorLabel.getFont().deriveFont(Font.PLAIN));
        showAuthorPanel.add(authorLabel);
        currentDataPanel.add(showAuthorPanel);

        //start passage
        JPanel showStartPanel = new JPanel(new GridLayout(2,1));
        showStartPanel.add(new JLabel("Start passage:"));
        startPassageLabel = new JLabel(theMetadata.getStartPassage());
        startPassageLabel.setFont(notBold);
        showStartPanel.add(startPassageLabel);
        currentDataPanel.add(showStartPanel);

        //ifid
        JPanel showIfidPanel = new JPanel(new GridLayout(2,1));
        showIfidPanel.add(new JLabel("IFID:"));
        JLabel metaLabel = new JLabel(theMetadata.getIfid());
        metaLabel.setFont(notBold);
        showIfidPanel.add(metaLabel);
        currentDataPanel.add(showIfidPanel);

        //adding the currentDataPanel to theFrame
        theFrame.add(currentDataPanel);


        //title editing panel
        JPanel titleEditingPanel = new JPanel(new GridLayout(3,1));
        titleEditingPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Edit title"
                )
        ); //border
        JLabel titleInstructions = new JLabel("Enter title here. Must start and end with non-whitespace characters.");
        titleInstructions.setFont(notBold);
        titleEditingPanel.add(titleInstructions);
        titleInput = new JTextField(theMetadata.getTitle(),0);
        titleEditingPanel.add(titleInput);
        JButton titleUpdateButton = new JButton("Update title");
        titleUpdateButton.addActionListener( (e) -> attemptTitleUpdate(titleInput.getText()));
        titleEditingPanel.add(titleUpdateButton);

        theFrame.add(titleEditingPanel);



        JPanel authorEditingPanel = new JPanel(new GridLayout(3,1));
        authorEditingPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Edit author"
                )
        ); //border
        JLabel authorInstructions = new JLabel("Enter author here. Must start and end with letters. May contain spaces, commas, and full stops.");
        authorInstructions.setFont(notBold);
        authorEditingPanel.add(authorInstructions);
        authorInput = new JTextField(theMetadata.getAuthor(),0);
        authorEditingPanel.add(authorInput);
        JButton authorUpdateButton = new JButton("Update author");
        authorUpdateButton.addActionListener( (e) -> attemptAuthorUpdate(authorInput.getText()));
        authorEditingPanel.add(authorUpdateButton);

        theFrame.add(authorEditingPanel);



        JPanel startEditingPanel = new JPanel(new GridLayout(3,1));
        startEditingPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Edit start passage"
                )
        ); //border
        JLabel startInstructions = new JLabel("Enter start passage here. Must be a valid passage name.");
        startInstructions.setFont(notBold);
        startEditingPanel.add(startInstructions);
        startInput = new JTextField(theMetadata.getStartPassage(),0);
        startEditingPanel.add(startInput);
        JButton startUpdateButton = new JButton("Update start");
        startUpdateButton.addActionListener( (e) -> attemptStartUpdate(startInput.getText()));
        startEditingPanel.add(startUpdateButton);

        theFrame.add(startEditingPanel);



        JPanel commentEditingPanel = new JPanel(new BorderLayout());
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

        JScrollPane commentScroll = new JScrollPane(
                commentInput,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        commentEditingPanel.add(commentScroll,BorderLayout.CENTER);
        JButton commentUpdateButton = new JButton("Update comment");
        commentUpdateButton.addActionListener( (e) -> attemptCommentUpdate(commentInput.getText()));
        commentEditingPanel.add(commentUpdateButton, BorderLayout.SOUTH);

        theFrame.add(commentEditingPanel);


        //no variable stuff because they are currently unimplemented


        //button to say 'right thats it im done'
        JPanel donePanel = new JPanel(new GridLayout(1,1));
        JButton thatsItImDone = new JButton("Exit");
        thatsItImDone.addActionListener( (e) -> confirmWindowClose());
        donePanel.add(thatsItImDone);
        theFrame.add(donePanel);



    }

    /**
     * Attempts to update the metadata title
     * @param newTitle the new title for the game
     */
    void attemptTitleUpdate(String newTitle){
        System.out.println(newTitle);
        try{
            theMetadata.updateTitle(newTitle);
        } catch (InvalidMetadataDeclarationException e){
            JOptionPane.showMessageDialog(
                    theFrame,
                    "<html><h1>ERROR!</h1>"
                            +"<p>Invalid title input!<br>"
                            +"Titles must start and end with non-whitespace characters<br>"
                            +"but may contain spaces between the first and last characters</p></html>",
                    "Invalid title input!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        showNewTitle();
    }

    /**
     * Attempts to update the metadata author
     * @param newAuthor the new author for the game
     */
    void attemptAuthorUpdate(String newAuthor){
        //TODO: attempt to update the author
        System.out.println(newAuthor);
        try{
            theMetadata.updateAuthor(newAuthor);
        } catch (InvalidMetadataDeclarationException e){
            JOptionPane.showMessageDialog(
                    theFrame,
                    "<html><h1>ERROR!</h1>"
                            +"<p>Invalid author input!<br>"
                            +"Titles must start and end with letters<br>"
                            +"but may contain any number of letters,<br>"
                            +"spaces, full stops, and commas,<br>"
                            +"between the first and last characters</p></html>",
                    "Invalid author input!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        showNewAuthor();
    }

    /**
     * Attempts to update the metadata start passage
     * @param newStart the new start passage for the game
     */
    void attemptStartUpdate(String newStart){
        //TODO: attempt to update start passage
        System.out.println(newStart);
        try{
            theMetadata.updateStartPassage(newStart);
            //TODO: inform user if specified start passage doesn't exist yet
        } catch ( InvalidPassageNameException e){
            JOptionPane.showMessageDialog(
                    theFrame,
                    "<html><h1>ERROR!</h1>"
                            +"<p>Invalid start passage input!<br>"
                            +"Passage names must start and end with letters,<br>"
                            +"numbers, or underscores, but may contain any<br>"
                            +"number of letters/numbers/underscores or spaces<br>"
                            +"between the first and last characters</p></html>",
                    "Invalid start passage input!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        showNewStartPassage();

    }

    /**
     * Attempts to update the metadata comment
     * @param newComment the new comment for the metadata
     */
    void attemptCommentUpdate(String newComment){
        //TODO: attempt to update the comment
        theMetadata.updateComment(newComment);
        showNewComment();
    }

    void showNewTitle(){
        String newTitle = theMetadata.getTitle();
        titleLabel.setText(newTitle);
        titleInput.setText(newTitle);
        currentTitle.showValue(newTitle);
        titleText.setText(currentTitle.toString());
        refresh();
        System.out.println(newTitle);
    }

    void showNewAuthor(){
        String newAuthor = theMetadata.getAuthor();
        authorLabel.setText(newAuthor);
        //TODO: author text entry thing
        refresh();
        System.out.println(newAuthor);
    }

    void showNewStartPassage(){
        String newStart = theMetadata.getStartPassage();
        startPassageLabel.setText(newStart);
        //TODO: start passage text entry thing
        refresh();
        System.out.println(newStart);
    }

    void showNewComment(){
        String newComment = theMetadata.getComment();
        commentInput.setText(newComment);
        //TODO: show the new comment in the comment textarea
        refresh();
        System.out.println(newComment);
    }

    /**
     * Brings up a JOptionPane ConfirmDialog to ask the user if they're sure they want to quit
     * Closes the window if the user confirms that they want to close the window.
     */
    void confirmWindowClose(){
        if (JOptionPane.showConfirmDialog(
                theFrame,
                "<html><p>"
                +"Do you want to close this window?<br>"
                +"All unsaved changes will be lost!"
                +"</p></html>",
                "Are you sure?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            ) == JOptionPane.YES_OPTION){
            closeTheWindow();
        }
    }

    /**
     * Literally just closes this window (frame is made invisible, and is disposed of)
     */
    private void closeTheWindow(){
        theFrame.setVisible(false);
        theFrame.dispose();
    }


    public static void main(String[] args){
        EditableMetadata theTestMetadata = new EditableMetadata("sample title","an author");

        System.out.println(theTestMetadata.toString());

        //MetadataEditorWindow w = new MetadataEditorWindow(theTestMetadata);
        MetadataEditorWindow w = theTestMetadata.openEditingWindow();

        w.theFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        //making sure that the window updated theTestMetadata, by seeing the printout of its internal state
                        System.out.println(theTestMetadata.toString());
                    }
                }
        );


        

    }
}
