package oh_hecc;

import javax.swing.*;
import java.awt.*;

public class ChooseFile {
    private JLabel titleLabel;
    private JPanel titlePanel;
    private JPanel mainPanel;
    private JPanel makeContainer;
    private JLabel makeTitle;
    private JLabel enterTitleLabel;
    private JTextField make_TitleInput;
    private JLabel enterAuthor;
    private JTextField make_AuthorInput;
    private JButton makeFileButton;
    private JPanel makeInputs;
    private JPanel thePanel;
    private JPanel makeContent;
    private JPanel openContainer;
    private JPanel openPanel;
    private JLabel openTitle;
    private JPanel openInputs;
    private JButton selectButton;
    private JTextArea chosenFile;
    private JButton startEditingButton;


    public static void main(String[] args) {
        ChooseFile cf = new ChooseFile();
        JFrame theFrame = new JFrame();
        theFrame.add(cf.thePanel, BorderLayout.CENTER);
        theFrame.pack();
        theFrame.revalidate();
        theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
    }
}
