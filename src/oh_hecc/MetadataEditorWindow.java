package oh_hecc;

import heccCeptions.InvalidMetadataDeclarationException;
import oh_hecc.metadata.MetadataEditingInterface;
import org.w3c.dom.Attr;
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

        //showing current data

        //title
        JPanel showTitlePanel = new JPanel(new GridLayout(2,1));
        showTitlePanel.add(new JLabel("Title:"));
        titleLabel = new JLabel(theMetadata.getTitle());
        showTitlePanel.add(titleLabel);
        currentDataPanel.add(showTitlePanel);

        //author
        JPanel showAuthorPanel = new JPanel(new GridLayout(2,1));
        showAuthorPanel.add(new JLabel("Author:"));
        authorLabel = new JLabel(theMetadata.getAuthor());
        showAuthorPanel.add(authorLabel);
        currentDataPanel.add(showAuthorPanel);

        //start passage
        JPanel showStartPanel = new JPanel(new GridLayout(2,1));
        showStartPanel.add(new JLabel("Start passage:"));
        startPassageLabel = new JLabel(theMetadata.getStartPassage());
        showStartPanel.add(startPassageLabel);
        currentDataPanel.add(showStartPanel);

        //ifid
        JPanel showIfidPanel = new JPanel(new GridLayout(2,1));
        showIfidPanel.add(new JLabel("IFID:"));
        showIfidPanel.add(new JLabel(theMetadata.getIfid()));
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
        titleEditingPanel.add(new JLabel("Enter title here. Must start and end with non-whitespace characters."));
        titleInput = new JTextField(theMetadata.getTitle(),0);
        titleEditingPanel.add(titleInput);
        JButton titleUpdateButton = new JButton("Update title");
        titleUpdateButton.addActionListener( (e) -> attemptTitleUpdate(titleInput.getText()));
        titleEditingPanel.add(titleUpdateButton);

        theFrame.add(titleEditingPanel);

        //TODO: simple author editing panel (like title editing panel)

        //TODO: simple start passage editing panel (like title editing panel)


        //TODO: comment editing panel (using a JTextArea instead, might need a different layout to others)


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
    }

    /**
     * Attempts to update the metadata start passage
     * @param newStart the new start passage for the game
     */
    void attemptStartUpdate(String newStart){
        //TODO: attempt to update start passage
    }

    /**
     * Attempts to update the metadata comment
     * @param newComment the new comment for the metadata
     */
    void attemptCommentUpdate(String newComment){
        //TODO: attempt to update the comment
    }

    void showNewTitle(){
        String newTitle = theMetadata.getTitle();
        titleLabel.setText(newTitle);
        titleInput.setText(newTitle);
        refresh();
        System.out.println(newTitle);
    }

    void showNewAuthor(){
        String newAuthor = theMetadata.getAuthor();
        authorLabel.setText(newAuthor);
        //TODO: author text entry thing
        refresh();
    }

    void showNewStartPassage(){
        String newStart = theMetadata.getStartPassage();
        startPassageLabel.setText(newStart);
        //TODO: start passage text entry thing
        refresh();
    }

    void showNewComment(){
        String newComment = theMetadata.getComment();
        //TODO: show the new comment in the comment textarea
        refresh();
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
