package oh_hecc.component_editing_windows;

import heccCeptions.DuplicatePassageNameException;
import heccCeptions.InvalidPassageNameException;
import oh_hecc.passage.EditablePassage;
import oh_hecc.passage.PassageEditingInterface;
import utilities.Vector2D;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PassageEditorWindow extends GenericEditorWindow {


    private PassageEditingInterface thePassage;
    //JFrame theFrame;
    private Map<UUID, ? extends PassageEditingInterface> allPassages;

    private JLabel titleLabel;

    private JTextField titleField;
    private JTextField tagField;
    private JTextField inlineCommentField;

    private JTextArea contentArea;
    private JTextArea commentArea;

    public PassageEditorWindow(PassageEditingInterface passageBeingEdited, Map<UUID, ? extends PassageEditingInterface> allThePassages){
        thePassage = passageBeingEdited;
        allPassages = allThePassages;

        makeTheFrame();

        theFrame.setVisible(true);

        refresh();

    }

    void makeTheFrame(){
        super.makeTheFrame();


        theFrame.setTitle("passage editor window");

        //JPanel topPanel = new JPanel(new GridLayout(2,1));

        JPanel titleEditPanel = new JPanel(new GridLayout(2,1));
        titleEditPanel.setBorder(new TitledBorder(loweredEtchedBorder, "Edit passage name"));

        titleField = new JTextField(thePassage.getPassageName());
        titleField.setEditable(true);
        JScrollPane titleScroll = new JScrollPane(
                titleField,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        titleEditPanel.add(titleScroll);
        JButton updateTitleButton = new JButton("Update Title");
        updateTitleButton.addActionListener( e -> updateTitle(titleField.getText().trim()));
        titleEditPanel.add(updateTitleButton);

        theFrame.add(titleEditPanel);

        JPanel tagEditPanel = new JPanel(new GridLayout(2,1));
        tagEditPanel.setBorder(new TitledBorder(loweredEtchedBorder, "Edit passage tags"));

        tagField = new JTextField(thePassage.getPassageTagsAsString());
        tagField.setEditable(true);
        JScrollPane tagScroll = new JScrollPane(
                tagField,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        tagEditPanel.add(tagScroll);
        JButton updateTagButton = new JButton("Update Tags");
        updateTagButton.addActionListener( e -> updateTags(tagField.getText().trim()));
        tagEditPanel.add(updateTagButton);

        theFrame.add(tagEditPanel);



        JPanel inlineEditPanel = new JPanel(new GridLayout(2,1));
        tagEditPanel.setBorder(new TitledBorder(loweredEtchedBorder, "Edit inline comment (readers won't see this)"));

        inlineCommentField = new JTextField(thePassage.getInlinePassageComment());
        inlineCommentField.setEditable(true);
        JScrollPane inlineScroll = new JScrollPane(
                inlineCommentField,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        inlineEditPanel.add(inlineScroll);
        JButton updateInlineButton = new JButton("Update Inline Comment");
        updateInlineButton.addActionListener( e -> updateTags(inlineCommentField.getText().trim()));
        inlineEditPanel.add(updateInlineButton);

        theFrame.add(inlineEditPanel);


        JPanel contentEditingPanel = new JPanel(new BorderLayout());
        contentEditingPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Edit passage content"
                )
        ); //border

        contentArea = new JTextArea();
        contentArea.setRows(10);
        contentArea.setEditable(true);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        JScrollPane contentScroll = new JScrollPane(
                contentArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        contentEditingPanel.add(contentScroll,BorderLayout.CENTER);
        JButton contentUpdateButton = new JButton("Update content");
        contentUpdateButton.addActionListener( (e) -> updateContent(contentArea.getText()));
        contentEditingPanel.add(contentUpdateButton, BorderLayout.SOUTH);

        theFrame.add(contentEditingPanel);


        JPanel commentEditingPanel = new JPanel(new BorderLayout());
        commentEditingPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Edit metadata comment (note: readers won't see this)"
                )
        ); //border

        commentArea = new JTextArea();
        commentArea.setRows(10);
        commentArea.setEditable(true);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);

        JScrollPane commentScroll = new JScrollPane(
                commentArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        commentEditingPanel.add(commentScroll,BorderLayout.CENTER);
        JButton commentUpdateButton = new JButton("Update comment");
        commentUpdateButton.addActionListener( (e) -> updateTrailingComment(commentArea.getText()));
        commentEditingPanel.add(commentUpdateButton, BorderLayout.SOUTH);

        theFrame.add(commentEditingPanel);

        theFrame.add(donePanel());
    }

    void updateTitle(String newTitle){

        try{
            thePassage.renameThisPassage(newTitle,allPassages);
            titleField.setText(newTitle);
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
                            + newTitle
                            +"' already exists!</p></html>",
                    "Duplicate passage name input!",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }

    }


    void updateTags(String newTagList){
        //TODO: update tag list
    }

    void updateInlineComment(String newInlineComment){
        //TODO: update inline comment
    }

    void updateContent(String newContent){
        //TODO: update passage content
    }

    void updateTrailingComment(String newTrailingComment){
        //TODO: update trailing comment
    }


    public static void main(String args[]){
        EditablePassage[] samples = {new EditablePassage("deez nutz", new Vector2D(0,0)), new EditablePassage("lmao gottem", "[[deez nutz]]","",""), new EditablePassage(), new EditablePassage()};

        Map<UUID, EditablePassage> passages = new HashMap<>();

        for (EditablePassage e: samples) {
            passages.put(e.getPassageUUID(),e);
        }
        System.out.println("Initial state of the passages:");
        for (Map.Entry<UUID, EditablePassage> e: passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().outputAsStringForDebuggingReasons());
        }

        EditablePassage editThis = passages.get(samples[0].getPassageUUID());

        PassageEditorWindow w = PassageEditingInterface.openEditorWindow(editThis,passages);

        w.theFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        //making sure that the window updated everything, by seeing the printout of its internal state
                        System.out.println("\nFinal state of the passages");
                        for (Map.Entry<UUID, EditablePassage> entry: passages.entrySet()) {
                            System.out.println(entry.getKey());
                            System.out.println(entry.getValue().outputAsStringForDebuggingReasons());
                            System.out.println("");
                        }
                    }
                }
        );


    }

}
