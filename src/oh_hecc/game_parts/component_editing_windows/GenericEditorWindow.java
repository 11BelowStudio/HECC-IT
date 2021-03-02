package oh_hecc.game_parts.component_editing_windows;

import oh_hecc.Parseable;
import oh_hecc.game_parts.EditWindowGameDataInterface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class GenericEditorWindow implements EditorWindowInterface {

    //the frame itself
    final JFrame theFrame;

    //A lowered etched border
    final Border loweredEtchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

    //the
    final Font notBold = new JLabel().getFont().deriveFont(Font.PLAIN);

    final Color defaultTextFieldColor = new JTextField().getForeground();

    final Color errorTextFieldColor = Color.RED;

    final EditWindowGameDataInterface gameData;

    public GenericEditorWindow(EditWindowGameDataInterface data){
        gameData = data;
        theFrame = new JFrame();
        theFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //This basically allows a quit prompt to appear when the user tries to press the 'x' button on the window
        //If the user confirms that they want to close this window, this window closes.
        theFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        confirmWindowClose();
                    }
                }
        );

        //theFrame will use a BoxLayout.
        theFrame.setLayout(new BoxLayout(theFrame.getContentPane(), BoxLayout.Y_AXIS));

        theFrame.requestFocus();
    }


    void refresh(){
        theFrame.pack();
        theFrame.revalidate();
    }


    abstract void makeTheFrame();

    JPanel donePanel() {
        //button to say 'right thats it im done'
        JPanel donePanel = new JPanel(new GridLayout(1, 1));
        JButton thatsItImDone = new JButton("Save and Exit");
        thatsItImDone.addActionListener(this::imDone);
        donePanel.add(thatsItImDone);
        return donePanel;
    }

    /**
     * A method to be called by the 'exit' button in the 'done' panel
     *
     * @param e an actionEvent to allow this to be used as a lambda.
     */
    abstract void imDone(ActionEvent e);

    /**
     * Brings up a JOptionPane ConfirmDialog to ask the user if they're sure they want to quit
     * Closes the window if the user confirms that they want to close the window.
     */
    void confirmWindowClose() {
        if (JOptionPane.showConfirmDialog(
                theFrame,
                "<html><p>"
                        + "Do you want to close this window?<br>"
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
     * Literally just closes this window (frame is made invisible, and is promptly disposed of)
     */
    void closeTheWindow(){
        theFrame.setVisible(false);
        theFrame.dispose();
    }

    /**
     * Adding a WindowClosed event listener via a Consumer<\WindowEvent\> functional interface
     * @param closeEvent the functional interface holding the function that needs to be called when this window is closed.
     * @see java.util.function.Consumer
     */
    @Override
    public void addWindowClosedListener(java.util.function.Consumer<WindowEvent> closeEvent){
        theFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        closeEvent.accept(e);
                    }
                }
        );
    }


    /**
     * Both subclasses of this have a field involving a passage name.
     * This is a persistent record of whether or not it's currently valid.
     */
    boolean isPassageNameValid = true;

    /**
     * This can be called for either of the JTextComponents that are used for passage names (start passage/current passage name).
     * If the current text for them isn't a valid passage name (doesn't satisfy PASSAGE_NAME_REGEX), the text will be red.
     * Otherwise, the text will be same as default.
     * The text colour will only change if the validity of the JTextComponent's contents changes.
     * @param passageNameComponent the JTextComponent that
     */
    private void isPassageNameRegexValid(JTextComponent passageNameComponent) {
        boolean stillValid = (passageNameComponent.getText().trim().matches(Parseable.PASSAGE_NAME_REGEX));
        if (isPassageNameValid ^ stillValid){
            passageNameComponent.setForeground(stillValid? defaultTextFieldColor : errorTextFieldColor);
            isPassageNameValid = stillValid;
        }
    }

    /**
     * Creates a DocumentListener that can be used on the passage name JTextComponents,
     * which can call isPassageNameRegexValid when it's edited,
     * and adds it to aforementioned JTextComponent's document
     * @param passageNameComponent the component itself that this will be listening to/will need to update
     */
    void addPassageNameDocumentListener(JTextComponent passageNameComponent){
        passageNameComponent.getDocument().addDocumentListener(
        new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { isPassageNameRegexValid(passageNameComponent); }
            @Override
            public void removeUpdate(DocumentEvent e) { isPassageNameRegexValid(passageNameComponent); }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        }
        );
    }
}
