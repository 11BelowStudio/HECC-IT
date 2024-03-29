package oh_hecc;

import oh_hecc.game_parts.metadata.EditableMetadata;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.metadata.SharedMetadata;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Basically responsible for allowing the author to pick a .hecc file to open and such.
 */
@SuppressWarnings("unused") // ignore unused warnings because the UI builder stuff is weird with it
public class ChooseFile {

    /**
     * This is the top level JPanel that holds everything else
     */
    private JPanel thePanel;


    /**
     * The JTextField where you can enter the title for a new hecc file
     */
    private JTextField make_TitleInput;

    /**
     * The JTextField where you can enter the author name for a new hecc file
     */
    private JTextField make_AuthorInput;

    /**
     * The JButton which you press to actually make the new hecc file
     */
    private JButton makeFileButton;


    /**
     * The JButton which brings up the JFileChooser that selects an existing .hecc file
     */
    private JButton selectButton;
    /**
     * The JTextArea which will hold the path of the selected .hecc file to open
     */
    private JTextArea chosenFile;
    /**
     * The JButton which allows a user to start editing an existing .hecc file
     */
    private JButton startEditingButton;

    /**
     * The JButton which opens HECC-UP.
     */
    private JButton exportButton;


    /**
     * main inner content panel
     */
    private JPanel mainPanel;
    /**
     * outer panel for 'make hecc file'
     */
    private JPanel makeContainer;
    /**
     * jlabel title for the 'make hecc file' panel
     */
    private JLabel makeTitle;
    /**
     * jlabel prompting user to enter title for making hecc file
     */
    private JLabel enterTitleLabel;
    /**
     * jlabel prompting user to enter author name for making hecc file
     */
    private JLabel enterAuthor;
    /**
     * jpanel holding the inputs and such for the 'make file' panel
     */
    private JPanel makeInputs;
    /**
     * jpanel holding the content of the 'make hecc file' area
     */
    private JPanel makeContent;
    /**
     * outer jpanel for the 'open hecc file' stuff
     */
    private JPanel openContainer;
    /**
     * main jpanel for the 'open hecc file' stuff
     */
    private JPanel openPanel;
    /**
     * title jlabel for 'open hecc file'
     */
    private JLabel openTitle;
    /**
     * jpanel holding the 'pick file' stuff in the 'open hecc file' panel
     */
    private JPanel openInputs;
    /**
     * jpanel holding the main 'HECC-IT' title
     */
    private JPanel titlePanel;
    /**
     * the main 'HECC-IT' title
     */
    private JLabel title;



    /**
     * This holds whether the current title input is valid
     */
    private boolean isTitleValid = true;
    /**
     * Whether the current author input is valid
     */
    private boolean isAuthorValid = true;

    /**
     * The path of the selected existing .hecc file that's being opened
     */
    private Path selectedFileToOpen;

    /**
     * Whether an existing .hecc file has been selected
     */
    private boolean fileHasBeenSelected = false;

    /**
     * A backup of the default JTextField font colour
     */
    private final Color standardColor = make_TitleInput.getForeground();


    /**
     * Constructs this object
     *
     * @param editFilePathGoesHere           a Path Predicate function. This will be called if the user is trying to open an existing .hecc file.
     * @param newFilePathAndMetadataGoesHere a Path, MetadataEditingInterface BiPredicate function. This will be called if the user is trying to make a new .hecc file
     * @param heccUpThisPath                 given a Path, this will open this file in HECC-UP.
     */
    public ChooseFile(
            Predicate<Path> editFilePathGoesHere,
            BiPredicate<Path, MetadataEditingInterface> newFilePathAndMetadataGoesHere,
            Predicate<Path> heccUpThisPath
    ) {

        // yeah the GUI was already constructed via a UI designer, just need to make it functional here

        // calls the method that makes a new hecc file
        makeFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // will make a new hecc file when pressed
                makeHeccFile(newFilePathAndMetadataGoesHere);
            }
        });

        selectButton.addActionListener(new ActionListener() {
            /**
             * This will open the dialog for choosing an existing .hecc file to continue editing.
             * First, it'll let the user actually select the file.
             * Then, it'll make sure the selection actually exists and is a .hecc file.
             * (if it doesn't exist/isn't a .hecc file/etc, it will complain).
             * If the selection was valid, it'll remember what the selection was, and will show the 'start editing' button.
             * Otherwise, the user won't be allowed to start editing.
             * @param e oh look an ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                selectHeccFile();
            }
        });

        startEditingButton.addActionListener(new ActionListener() {
            /**
             * If the start editing button is pressed, and the user had actually selected a .hecc file to open,
             * it'll then attempt to open said file for editing.
             * If it can't be opened, it'll let the user know.
             * @param e oh look an ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileHasBeenSelected){
                    new SwingWorker<Boolean, Object>() {
                        @Override
                        protected Boolean doInBackground() throws Exception {
                            return editFilePathGoesHere.test(selectedFileToOpen);
                        }

                        @Override
                        protected void done(){
                            boolean unsuccessful = true;
                            try {
                                unsuccessful = !get();
                            } catch (Exception ignored){ }
                            if (unsuccessful){
                                JOptionPane.showMessageDialog(
                                        thePanel,
                                        "Unable to open .hecc file!",
                                        "oh noes",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                    }.execute();
                }
            }
        });

        exportButton.addActionListener(new ActionListener() {
            /**
             * If the export button is pressed, and the user had actually selected a .hecc file to open,
             * it'll then attempt to launch HECC-UP, with that file passed to it.
             * If it can't be opened, it'll let the user know.
             *
             * @param e oh look an ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileHasBeenSelected) {
                    new SwingWorker<Boolean, Object>() {
                        @Override
                        protected Boolean doInBackground() throws Exception {
                            return heccUpThisPath.test(selectedFileToOpen);
                        }

                        @Override
                        protected void done() {
                            boolean unsuccessful = true;
                            try {
                                unsuccessful = !get();
                            } catch (Exception ignored) {
                            }
                            if (unsuccessful) {
                                JOptionPane.showMessageDialog(
                                        thePanel,
                                        "Unable to open HECC-UP!",
                                        "oh noes",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                    }.execute();
                }
            }
        });


        /*
        TL;DR calls makeSureTitleIsValid() whenever the input in make_TitleInput changes
         */
        make_TitleInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                makeSureTitleIsValid();
            }
            @Override
            public void removeUpdate(DocumentEvent e) { makeSureTitleIsValid(); }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        /*
        Encourages the user to ensure that the text in make_TitleInput is valid
         */
        make_TitleInput.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                return isTitleValid;
            }
        });

        /*
        Encourages the user to ensure that the text in make_AuthorInput is valid
         */
        make_AuthorInput.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                return isAuthorValid;
            }
        });

        /*
        TL;DR calls makeSureAuthorIsValid() whenever the input in make_AuthorInput changes
         */
        make_AuthorInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { makeSureAuthorIsValid(); }
            @Override
            public void removeUpdate(DocumentEvent e) { makeSureAuthorIsValid(); }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });


    }

    /**
     * Ensures that the author input text in the 'make new hecc file' window is valid.
     * It does this by seeing if it satisfies the SharedMetadata.VALID_AUTHOR_REGEX regex.
     * If it's valid, the text will be black.
     * Otherwise, it'll be red.
     */
    private void makeSureAuthorIsValid(){
        final boolean stillValid = make_AuthorInput.getText().trim().matches(SharedMetadata.VALID_AUTHOR_REGEX);
        if (isAuthorValid ^ stillValid){
            make_AuthorInput.setForeground( stillValid ? standardColor : Color.RED);
            isAuthorValid = stillValid;
        }
    }

    /**
     * Ensures that the title input text in the 'make new hecc file' window is valid.
     * It does this by seeing if it satisfies the SharedMetadata.VALID_TITLE_REGEX regex,
     * as well as ensuring it doesn't contain any letters that are invalid for a filename (via Paths.get)
     * If it's valid, the text will be black.
     * Otherwise, it'll be red.
     */
    private void makeSureTitleIsValid(){
        final String titleinput = make_TitleInput.getText().trim();
        boolean validPath = false;
        try{
            Paths.get(titleinput + ".hecc");
            validPath = true;
        } catch (Exception ignored){}
        final boolean stillValid = (titleinput.matches(SharedMetadata.VALID_TITLE_REGEX) && validPath);
        if (isTitleValid ^ stillValid){
            make_TitleInput.setForeground( stillValid ? standardColor : Color.RED);
            isTitleValid = stillValid;
        }
    }

    /**
     * Returns the main JPanel that holds all the other components for the file choosing stuff
     * @return thePanel
     */
    public JPanel getThePanel(){ return thePanel; }

    /**
     * Okay so
     * This will first check to see if the title and author inputs are valid.
     * Then it'll make a JFileChooser to allow a user to choose where they want to save their new .hecc file
     * Then it does some validation
     * Finally, if everything seems legit, it'll give the selected path, and a newly constructed EditableMetadata to the newFilePathAndMetadataGoesHere BiConsumer
     * @param newFilePathAndMetadataGoesHere a Path, MetadataEditingInterface BiPredicate function. This will be called if the user is trying to make a new .hecc file
     */
    private void makeHeccFile(BiPredicate<Path, MetadataEditingInterface> newFilePathAndMetadataGoesHere) {
        if (isTitleValid) {
            if (isAuthorValid) {
                final String title = make_TitleInput.getText().trim();
                final String author = make_AuthorInput.getText().trim();
                final JFileChooser f = new JFileChooser();
                f.setDialogTitle("Select where you want to save your .hecc file");
                f.setMultiSelectionEnabled(false);
                f.setSelectedFile(new File(title + ".hecc"));
                f.setFileSelectionMode(JFileChooser.FILES_ONLY);
                f.setFileFilter(new FileNameExtensionFilter(".hecc files", "hecc"));
                if (f.showSaveDialog(thePanel) == JFileChooser.APPROVE_OPTION) {
                    try {
                        //System.out.println(f.getSelectedFile().toPath());


                        if (f.getSelectedFile().exists()) {
                            // if the file exists, we make sure user really wants to overwrite it
                            if (JOptionPane.showConfirmDialog(
                                    thePanel,
                                    "Are you sure you want to overwrite that file?",
                                    "A file with that name already exists!",
                                    JOptionPane.YES_NO_OPTION
                            ) != JOptionPane.YES_OPTION) {
                                return;
                            }
                        }
                        // we obtain the path of the file, and, if it's not a .hecc file, we make it a .hecc file
                        String stringPath = f.getSelectedFile().getAbsolutePath();
                        if (!stringPath.toLowerCase().endsWith(".hecc")) {
                            stringPath = stringPath.concat(".hecc");
                        }
                        final Path finalPath = Paths.get(stringPath);

                        // this basically attempts to make the .hecc file and start editing it.
                        new SwingWorker<Boolean, Object>() {

                            @Override
                            protected Boolean doInBackground() throws Exception {
                                return newFilePathAndMetadataGoesHere.test(
                                        finalPath,
                                        new EditableMetadata(title, author)
                                );
                            }
                            @Override
                            protected void done() {
                                boolean unsuccessful = true;
                                try {
                                    unsuccessful = !get();
                                } catch (Exception ignored) {
                                }
                                if (unsuccessful) {
                                    JOptionPane.showMessageDialog(
                                            thePanel,
                                            "Unable to make .hecc file!",
                                            "oh noes",
                                            JOptionPane.ERROR_MESSAGE
                                    );
                                }
                            }

                        }.execute();
                    } catch (InvalidPathException p) {
                        JOptionPane.showMessageDialog(
                                thePanel,
                                "Invalid filename! Please try again",
                                "Unable to create file",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            } else {
                JOptionPane.showMessageDialog(thePanel,
                        "Invalid author name!",
                        "pls fix",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(thePanel,
                    "Invalid game title!",
                    "pls fix",
                    JOptionPane.WARNING_MESSAGE);
        }
    }


    /**
     * This will open the dialog for choosing an existing .hecc file to continue editing.
     * First, it'll let the user actually select the file.
     * Then, it'll make sure the selection actually exists and is a .hecc file.
     * (if it doesn't exist/isn't a .hecc file/etc, it will complain).
     * If the selection was valid, it'll remember what the selection was, and will show the 'start editing' button.
     * Otherwise, the user won't be allowed to start editing.
     */
    private void selectHeccFile(){
        final JFileChooser f = new JFileChooser();


        f.setDialogTitle("Select a .hecc file to open");
        f.setMultiSelectionEnabled(false);
        f.setFileSelectionMode(JFileChooser.FILES_ONLY);
        f.setFileFilter(new FileNameExtensionFilter(".hecc files","hecc"));
        f.setDialogTitle("Pick a .hecc file to open");

        if (fileHasBeenSelected){
            // start from the currently selected file if one is currently selected
            f.setCurrentDirectory(selectedFileToOpen.toFile());
        }

        if (f.showDialog(
                thePanel,
                "Open this file"
        ) == JFileChooser.APPROVE_OPTION){

            final File selected = f.getSelectedFile();
            boolean valid = false;
            try{
                selectedFileToOpen = Paths.get(selected.getAbsolutePath());
                valid = true;
            } catch (Exception ignored){}
            if (selected.exists() && selected.getAbsolutePath().toLowerCase().endsWith(".hecc") && valid) {
                chosenFile.setText("Chosen file: " + selectedFileToOpen);
                fileHasBeenSelected = true;
            } else{
                JOptionPane.showMessageDialog(thePanel,
                        "You need to actually select a .hecc file that exists",
                        "invalid selection",
                        JOptionPane.ERROR_MESSAGE);
                fileHasBeenSelected = false;
            }
        } else{
            fileHasBeenSelected = false;
            chosenFile.setText("Please select a .hecc file to open");
        }
        startEditingButton.setVisible(fileHasBeenSelected);
        exportButton.setVisible(fileHasBeenSelected);
    }


    /**
     * le main method for testing has arrived
     *
     * @param args command line arguments.
     * @deprecated this exists for testing only, pls use HeccItRunner's main method instead.
     */
    @Deprecated
    public static void main(String[] args) {
        final ChooseFile cf = new ChooseFile(
                e -> {
                    System.out.println("edit " + e.toString());
                    return true;
                },
                (c, m) -> {
                    System.out.println("make " + c);
                    System.out.println(m.toString());
                    return true;
                },
                o -> {
                    System.out.println("export " + o);
                    System.out.println(o.toString());
                    return true;
                }
        );

        final JFrame theFrame = new JFrame();
        theFrame.add(cf.thePanel, BorderLayout.CENTER);
        theFrame.pack();
        theFrame.revalidate();
        theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
    }
}
