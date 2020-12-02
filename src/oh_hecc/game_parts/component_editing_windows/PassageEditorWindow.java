package oh_hecc.game_parts.component_editing_windows;

import heccCeptions.DuplicatePassageNameException;
import heccCeptions.InvalidMetadataDeclarationException;
import heccCeptions.InvalidPassageNameException;
import oh_hecc.game_parts.metadata.PassageEditWindowMetadataInterface;
import oh_hecc.game_parts.passage.EditablePassage;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import utilities.Vector2D;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * A class that basically is a window that allows a user to edit an EditablePassage
 * (and indirectly edit the other passages it's linked to)
 */
public class PassageEditorWindow extends GenericEditorWindow {


    /**
     * This is the actual passage that this passageEditorWindow is, y'know, editing.
     * Accessed via PassageEditingInterface because efficiency or something
     */
    private final PassageEditingInterface thePassage;
    //JFrame theFrame;
    /**
     * the map of all the passages.
     * 'no PassageEditingInterface is an island' and all that
     *  (so do not ask for whom the PassageEditorWindow is editing: for it edits thee).
     */
    private final Map<UUID, PassageEditingInterface> allPassages;

    private final Optional<PassageEditWindowMetadataInterface> metadata;

    private JLabel titleLabel;

    private JTextField nameField;
    private JTextField tagField;
    private JTextField inlineCommentField;

    private JTextArea contentArea;
    private JTextArea commentArea;




    /**
     * Constructs the PassageEditorWindow
     * @param passageBeingEdited the passage which is being edited
     * @param allThePassages the map of all the passages (just in case that needs to be modified as well)
     * @param m optional metadata parameter (in case this is the start passage and is renamed)
     */
    public PassageEditorWindow(PassageEditingInterface passageBeingEdited, Map<UUID, PassageEditingInterface> allThePassages, PassageEditWindowMetadataInterface m){
        thePassage = passageBeingEdited;
        allPassages = allThePassages;

        makeTheFrame();

        theFrame.setVisible(true);

        metadata = Optional.ofNullable(m);



        refresh();

    }
    /**
     * Constructs the PassageEditorWindow
     * @param passageBeingEdited the passage which is being edited
     * @param allThePassages the map of all the passages (just in case that needs to be modified as well)
     */
    public PassageEditorWindow(PassageEditingInterface passageBeingEdited, Map<UUID, PassageEditingInterface> allThePassages){
        this(passageBeingEdited,allThePassages, null);
    }

    void makeTheFrame(){
        super.makeTheFrame();


        theFrame.setTitle("passage editor window");


        //RENAMING THE PASSAGE
        JPanel nameEditPanel = new JPanel(new GridLayout(3,1));
        nameEditPanel.setBorder(new TitledBorder(loweredEtchedBorder, "Edit passage name"));

        JLabel nameEditLabel = new JLabel();
        nameEditLabel.setFont(notBold);
        nameEditLabel.setText("Passage name must start/end with numbers/letters/underscore. May contain spaces. Duplicate names are banned.");
        nameEditPanel.add(nameEditLabel);

        nameField = new JTextField(thePassage.getPassageName(), 64);
        nameField.setEditable(true);
        nameEditPanel.add(nameField);
        JButton updateTitleButton = new JButton("Rename Passage");
        updateTitleButton.addActionListener( e -> updateName(nameField.getText().trim()));
        nameEditPanel.add(updateTitleButton);

        theFrame.add(nameEditPanel);


        //EDITING PASSAGE TAGS
        JPanel tagEditPanel = new JPanel(new GridLayout(3,1));
        tagEditPanel.setBorder(new TitledBorder(loweredEtchedBorder, "Edit passage tags"));

        JLabel tagEditLabel = new JLabel();
        tagEditLabel.setFont(notBold);
        tagEditLabel.setText("Tags may only contain letters/numbers/underscores. Must be separated by spaces");
        tagEditPanel.add(tagEditLabel);

        tagField = new JTextField(thePassage.getPassageTagsAsString(), 64);
        tagField.setEditable(true);
        tagEditPanel.add(tagField);
        JButton updateTagButton = new JButton("Update Tags");
        updateTagButton.addActionListener( e -> updateTags(tagField.getText().trim()));
        tagEditPanel.add(updateTagButton);

        theFrame.add(tagEditPanel);

        //EDITING INLINE COMMENT
        JPanel inlineEditPanel = new JPanel(new GridLayout(2,1));
        inlineEditPanel.setBorder(
                new TitledBorder(
                        loweredEtchedBorder,
                        "Edit inline comment (readers won't see this)"
                )
        );

        inlineCommentField = new JTextField(thePassage.getInlinePassageComment(), 64);
        inlineCommentField.setEditable(true);
        inlineEditPanel.add(inlineCommentField);
        JButton updateInlineButton = new JButton("Update Inline Comment");
        updateInlineButton.addActionListener( e -> updateInlineComment(inlineCommentField.getText().trim()));
        inlineEditPanel.add(updateInlineButton);

        theFrame.add(inlineEditPanel);


        //EDITING THE ACTUAL PASSAGE CONTENT (the important bit)
        JPanel contentEditingPanel = new JPanel(new BorderLayout());
        contentEditingPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Edit passage content (the bits the reader will actually read)"
                )
        ); //border

        //TODO: JFormattedTextField with hecc syntax highlighting or something like that idk

        contentArea = new JTextArea();
        contentArea.setRows(10);
        contentArea.setEditable(true);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setText(thePassage.getPassageContent());

        JScrollPane contentScroll = new JScrollPane(
                contentArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        contentEditingPanel.add(contentScroll,BorderLayout.CENTER);
        JButton contentUpdateButton = new JButton("Update content (yes, readers will read this. hopefully.)");
        contentUpdateButton.addActionListener( (e) -> updateContent(contentArea.getText()));
        contentEditingPanel.add(contentUpdateButton, BorderLayout.SOUTH);

        theFrame.add(contentEditingPanel);


        //updating the trailing comment editing panel
        JPanel commentEditingPanel = new JPanel(new BorderLayout());
        commentEditingPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Edit metadata comment (note: readers won't see this)"
                )
        ); //border

        commentArea = new JTextArea();
        commentArea.setRows(6);
        commentArea.setEditable(true);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setText(thePassage.getTrailingComment());

        JScrollPane commentScroll = new JScrollPane(
                commentArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        commentEditingPanel.add(commentScroll,BorderLayout.CENTER);
        JButton commentUpdateButton = new JButton("Update comment");
        commentUpdateButton.addActionListener( (e) -> updateTrailingComment(commentArea.getText()));
        commentEditingPanel.add(commentUpdateButton, BorderLayout.SOUTH);

        theFrame.add(commentEditingPanel);

        //exiting the panel
        theFrame.add(donePanel());


        //the 'delete' button
        JPanel yeetThisPanel = new JPanel();
        yeetThisPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "DELETE PASSAGE (CANNOT BE UNDONE)"
                )
        );

        JButton bigRedButtonExceptItsNotRed = new JButton("DELETE THIS PASSAGE");
        bigRedButtonExceptItsNotRed.addActionListener( e -> deletThis());
        yeetThisPanel.add(bigRedButtonExceptItsNotRed);

        theFrame.add(yeetThisPanel);
    }

    /**
     * This method attempts to update the passageName of thePassage (and any references to it)
     * @param newName the new name to give to the passage.
     */
    private void updateName(String newName){

        //if the user has entered the current passage name *again*
        if (newName.equals(thePassage.getPassageName())){
            //bruh
            JOptionPane.showMessageDialog(
                    theFrame,
                    "<html><p>"
                    + "If you want to rename this passage,<br>"
                    + "it helps if you provide a new name for it.</p>"
                    + "<p>Not the current name <em>again</em>"
                    + "</p></html>",
                    "bruh",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        try{
            String oldName = thePassage.getPassageName();
            thePassage.renameThisPassage(newName,allPassages);
            nameField.setText(thePassage.getPassageName());
            if (metadata.isPresent() && metadata.get().getStartPassage().equals(oldName)){
                metadata.get().updateStartPassage(newName);
            }
            refresh();
        } catch (InvalidPassageNameException e) {
            JOptionPane.showMessageDialog(
                    theFrame,
                    "<html><h1>ERROR!</h1>"
                            +"<p>Invalid passage name input!<br>"
                            +"Passage names must start and end with letters,<br>"
                            +"numbers, or underscores, but may contain any<br>"
                            +"number of letters/numbers/underscores or spaces<br>"
                            +"between the first and last characters</p></html>",
                    "Invalid passage name input!",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        } catch (DuplicatePassageNameException e) {
            JOptionPane.showMessageDialog(
                    theFrame,
                    "<html><h1>ERROR!</h1>"
                            +"<p>Duplicate passage name input!<br>"
                            +"A passage called '"
                            + newName
                            +"' already exists!</p></html>",
                    "Duplicate passage name input!",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }

    }

    /**
     * Attempts to update the tagList of the passage
     * @param newTagList the string that will be converted into this passage's tagList
     */
    private void updateTags(String newTagList){
        //TODO: update tag list
        try{
            thePassage.updatePassageTags(newTagList);
            tagField.setText(thePassage.getPassageTagsAsString());
            refresh();
        } catch (InvalidMetadataDeclarationException e) {
            JOptionPane.showMessageDialog(
                    theFrame,
                    "<html><h1>ERROR!</h1>"
                            +"<p>Invalid tag string entered!<br>"
                            +"Tags must only contain letters, numbers, and underscores<br>"
                            +"and must be separated by spaces.</p></html>",
                    "Invalid tag input!",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    /**
     * Will update the inline comment of the passage
     * @param newInlineComment the string being used as the passage's new inline comment
     */
    private void updateInlineComment(String newInlineComment){
        thePassage.setInlinePassageComment(newInlineComment);
        inlineCommentField.setText(thePassage.getInlinePassageComment());
        refresh();
    }

    /**
     * Attempts to update the passage content (and also creates new passages to link to if newly linked passages dont exist yet)
     * @param newContent the new content for this passage
     */
    private void updateContent(String newContent){
        thePassage.updatePassageContent(newContent,allPassages);
        contentArea.setText(thePassage.getPassageContent());
        refresh();
    }

    /**
     * Updates the trailing comment of this passage
     * @param newTrailingComment the new trailing comment for this passage
     */
    private void updateTrailingComment(String newTrailingComment){
        thePassage.setTrailingComment(newTrailingComment);
        commentArea.setText(thePassage.getTrailingComment());
        refresh();
    }

    /**
     * Asks the user if they're sure they want to delet this.
     * Then asks them if they're sure that they're sure.
     * If the user is still sure, DELET
     */
    private void deletThis(){
        //asks user if they want to delete this passage
        if (JOptionPane.showConfirmDialog(
                theFrame,
                "<html><p>"
                        +"Are you sure you want to delete this passage?<br>"
                        +"This passage will cease to exist<br>"
                        +"and this <em>cannot be undone</em>"
                        +"</p></html>",
                "Are you sure?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        ) == JOptionPane.YES_OPTION){
            //asks the user if they are sure they want to delete this passage
            if (JOptionPane.showConfirmDialog(
                    theFrame,
                    "<html><p>"
                            +"Okay so I just want to be sure<br>"
                            +"Deleting this passage <em>cannot be undone</em><br>"
                            +"and I don't want you to be all like<br>"
                            + "'I wasn't told about this' later on.<br><br>"
                            +"Are you <em>absolutely 100% sure</em><br>"
                            + "you want to delete this passage?"
                            +"</p></html>",
                    "Like seriously you sure you want to do this?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            ) == JOptionPane.YES_OPTION){
                //YEET
                thePassage.deleteThisPassage(allPassages);
                JOptionPane.showMessageDialog(
                        theFrame,
                        "This passage has been deleted.",
                        "YEET",
                        JOptionPane.INFORMATION_MESSAGE
                );
                closeTheWindow();
            }
        }
    }

    /**
     * le main method has arrived
     * @param args le command line parameters have not arrived (because they aren't used)
     */
    public static void main(String args[]){
        EditablePassage[] samples = {new EditablePassage("deez nutz", "ayy lmao\neecks dee", "nice\n\nmeme","[yes theres tags] < 256,96> //this is another passage"), new EditablePassage("lmao gottem", "[[deez nutz]]","","")};

        Map<UUID, PassageEditingInterface> passages = new HashMap<>();

        for (EditablePassage e: samples) {
            passages.put(e.getPassageUUID(),e);
        }
        System.out.println("Initial state of the passages:\n");
        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().outputAsStringForDebuggingReasons());
            System.out.println("");
        }

        PassageEditingInterface editThis = passages.get(samples[0].getPassageUUID());

        //PassageEditorWindow w = PassageEditingInterface.openEditorWindow(editThis,passages);

        PassageEditorWindow w = editThis.openEditorWindow(passages);

        w.addWindowClosedListener(
                new Consumer<WindowEvent>() {
                    @Override
                    public void accept(WindowEvent e) {
                        System.out.println("\nFinal state of the passages\n");
                        for (Map.Entry<UUID, PassageEditingInterface> entry: passages.entrySet()) {
                            System.out.println(entry.getKey());
                            System.out.println(entry.getValue().outputAsStringForDebuggingReasons());
                            System.out.println("");
                        }
                    }
                }
        );

        /*
        //using this for testing and such, printing everything in passages once the user is done editing and such
        w.theFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        //making sure that the window updated everything, by seeing the printout of its internal state
                        System.out.println("\nFinal state of the passages\n");
                        for (Map.Entry<UUID, PassageEditingInterface> entry: passages.entrySet()) {
                            System.out.println(entry.getKey());
                            System.out.println(entry.getValue().outputAsStringForDebuggingReasons());
                            System.out.println("");
                        }
                    }
                }
        );

         */


    }

}
