package oh_hecc.component_editing_windows;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class GenericEditorWindow {

    //the frame itself
    JFrame theFrame;

    //A lowered etched border
    Border loweredEtchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

    //the
    Font notBold = new JLabel().getFont().deriveFont(Font.PLAIN);


    void refresh(){
        theFrame.pack();
        theFrame.revalidate();
    }


    void makeTheFrame(){
        theFrame = new JFrame();

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





        //theFrame will use a BoxLayout.
        theFrame.setLayout(new BoxLayout(theFrame.getContentPane(), BoxLayout.Y_AXIS));

    }

    JPanel donePanel(){
        //button to say 'right thats it im done'
        JPanel donePanel = new JPanel(new GridLayout(1,1));
        JButton thatsItImDone = new JButton("Exit");
        thatsItImDone.addActionListener( (e) -> confirmWindowClose());
        donePanel.add(thatsItImDone);
        return donePanel;
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
}
