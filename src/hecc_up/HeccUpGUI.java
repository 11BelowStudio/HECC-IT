package hecc_up;

import utilities.AttributeString;
import utilities.ImageManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;

/**
 * This class is basically a GUI for the HECC-UP stuff
 * so a user can actually, y'know, hecc up their stuff.
 */
public class HeccUpGUI implements LoggerInterface {


    /**
     * the heccUpHandler object that handles the process of heccing things up
     */
    private final HeccUpHandler heccUpHandler;

    //some internal state stuff
    /**
     * whether or not the hecc file was chosen
     */
    private boolean heccFileChosen;
    /**
     * whether or not the output folder was chosen
     */
    private boolean outputFolderChosen;

    /**
     * the location of the .hecc file
     */
    private Path heccFileLocation;
    /**
     * where to save the HECCIN Game to
     */
    private Path outputFolderLocation;

    //and now, the classes which are to be used for the GUI
    /**
     * the frame that has the gui
     */
    private final JFrame theFrame;

    /**
     * JTextArea stating the location of the chosen hecc file
     */
    private JTextArea heccFileLocationDisplay;
    /**
     * AttributeString used to display the hecc file location
     */
    private final AttributeString<String> heccFileAttributeString;
    /**
     * JFileChooser for selecting the hecc file
     */
    private JFileChooser selectHeccFileChooser;

    /**
     * JTextArea showing path of currently selected output directory
     */
    private JTextArea gameLocationDisplay;
    /**
     * AttributeString used to display the output file location
     */
    private final AttributeString<String> gameLocationAttributeString;
    /**
     * JFileChooser that can be used to choose the output directory for the game
     */
    private JFileChooser selectGameLocationChooser;

    /**
     * JTextArea that important info gets logged to
     */
    private JTextArea logDisplay;

    /**
     * Constructor with no arguments
     * makes a new JFrame to hold all the other stuff.
     */
    public HeccUpGUI() {
        this(new JFrame());
    }

    /**
     * Constructor
     * sets up GUI frame + shows it
     * Initialises/constructs some other properties of this class
     * including the HeccUpHandler
     *
     * @param frame the JFrame that will hold all the HECC-UP GUI stuff.
     */
    private HeccUpGUI(JFrame frame) {
        theFrame = frame;
        theFrame.getContentPane().removeAll();
        //first it will actually, y'know, make the GUI
        makeTheGui();

        //then it'll make the GUI visible, revalidated, packed, etc
        theFrame.setVisible(true);
        theFrame.setPreferredSize(new Dimension(800, 600));
        theFrame.revalidate();
        theFrame.pack();

        //setting up the AttributeStrings so they can be easily used later on
        heccFileAttributeString = new AttributeString<>(".hecc file: ", "");
        gameLocationAttributeString = new AttributeString<>("Output folder: ", "");

        //these are false by default
        heccFileChosen = false;
        outputFolderChosen = false;

        heccUpHandler = new HeccUpHandler(this);

        theFrame.requestFocus();
    }

    /**
     * This is the constructor for the heccUpGUI that will be called by HECC-IT if the 'export' button is pressed instead.
     *
     * @param heccFilePath  path of the .hecc file in to be exported
     * @param existingFrame the existing frame that the HECC-UP stuff will be held in.
     */
    public HeccUpGUI(Path heccFilePath, JFrame existingFrame) {
        this(existingFrame);
        //pre-selects the .hecc file from OH-HECC.
        selectedAHeccFile(heccFilePath);

        Path fpath = heccFilePath.getFileName();

        String fname = fpath.toString();
        if (fname.endsWith(".hecc")) {
            fname = fname.substring(0, fname.length() - 5);
        }

        Path heccfileDirectory = heccFilePath.getParent().resolve(fname);

        selectedAnOutputFolder(heccfileDirectory);
    }

    /**
     * This is the constructor for the heccUpGUI that will be called by OH-HECC when the 'HECC-UP' button is pressed.
     *
     * @param heccFilePath      path of the .hecc file in OH-HECC.
     * @param windowClosedEvent the windowClosedEvent thing that will be added as a listener for this when it closes so OH-HECC knows that this has been closed.
     */
    public HeccUpGUI(Path heccFilePath, Runnable windowClosedEvent) {
        this(heccFilePath, new JFrame());


        theFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        theFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        windowClosedEvent.run();
                    }
                }
        );
    }

    /**
     * ok so basically this just makes the GUI
     */
    private void makeTheGui() {

        //sets stuff up with theFrame
        theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //exits when closed
        theFrame.setTitle("HECC Ultra Parser"); //verbose title

        //theFrame.setIconImage(ImageManager.getImage("HECC-UP icon"));
        theFrame.setIconImages(ImageManager.getHeccUpIcons());

        //A lowered etched border
        Border loweredEtchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        theFrame.setLayout(new GridLayout(1, 1));


        JPanel thePanel = new JPanel(); // The panel that will actually hold the GUI

        // it will have a BoxLayout
        thePanel.setLayout(new BoxLayout(thePanel, BoxLayout.Y_AXIS));


        //Creating the title panel to hold the title label
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1,1));
        //and now the title label
        JLabel titleLabel = new JLabel(
                "<html><h1>HECC-UP!</h1><html>",
                SwingConstants.CENTER
        );
        titlePanel.add(titleLabel); //it's been added to the titlePanel
        thePanel.add(titlePanel); //and this title panel is now in the frame
        

        //Creating the 'select your HECC file' panel
        JPanel selectHeccFilePanel = new JPanel();
        selectHeccFilePanel.setLayout(new GridLayout(3,1));
        selectHeccFilePanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Step 1"
                )
        );
        //instructions label
        JLabel selectHeccFileLabel = new JLabel(
                "Please select the .hecc file you want to parse",
                SwingConstants.CENTER
        );
        selectHeccFilePanel.add(selectHeccFileLabel);
        //time for the file choosing stuff
        //file location textArea
        heccFileLocationDisplay = new JTextArea("No .hecc file selected!");
        heccFileLocationDisplay.setLineWrap(true);
        heccFileLocationDisplay.setEditable(false);
        selectHeccFilePanel.add(heccFileLocationDisplay);
        //button to open hecc file chooser
        //Button to open the selectHeccFileChooser
        JButton selectHeccFileButton = new JButton("Choose .hecc file");
        selectHeccFileButton.addActionListener(this::selectHeccFileHandler);
        selectHeccFilePanel.add(selectHeccFileButton);
        //jFileChooser for hecc file
        selectHeccFileChooser = setupTheFileChoosers(true);

        //and now, the hecc selection panel is added to the gui frame
        thePanel.add(selectHeccFilePanel);



        //Output folder chooser panel
        JPanel selectGameLocationPanel = new JPanel();
        selectGameLocationPanel.setLayout(new GridLayout(3,1));
        selectGameLocationPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Step 2"
                )
        );
        JLabel selectGameLocationLabel = new JLabel(
                "Please select the directory for your HECCIN Game",
                SwingConstants.CENTER
        );
        selectGameLocationPanel.add(selectGameLocationLabel);
        //and now the chooser bit of this
        //JTextArea for instructions
        gameLocationDisplay = new JTextArea("No output directory chosen!");
        gameLocationDisplay.setLineWrap(true);
        gameLocationDisplay.setEditable(false);
        selectGameLocationPanel.add(gameLocationDisplay);
        //output directory button
        //will open selectGameLocationChooser
        JButton selectGameLocationButton = new JButton("Select output directory");
        selectGameLocationButton.addActionListener(this::selectOutputFileHandler);
        selectGameLocationPanel.add(selectGameLocationButton);
        //jFileChooser for the game location stuff
        selectGameLocationChooser = setupTheFileChoosers(false);


        thePanel.add(selectGameLocationPanel);

        //and now, a panel for the 'HECC-UP' button
        JPanel heccItUpPanel = new JPanel();
        heccItUpPanel.setLayout(new GridLayout(1,1));
        heccItUpPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Step 3"
                )
        );
        //the 'HECC-IT' button
        JButton heccItUpButton = new JButton("HECC-IT!");
        heccItUpButton.addActionListener(this::heccUpTheGame);

        heccItUpPanel.add(heccItUpButton);
        //and the heccItUpPanel is added to the gui Frame
        thePanel.add(heccItUpPanel);


        //And now, an output panel thing
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(1,1));
        outputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Event log"
                )
        );
        logDisplay = new JTextArea("Important info will be logged here!");
        logDisplay.setEditable(false);
        logDisplay.setLineWrap(true);
        logDisplay.setRows(5);
        JScrollPane logScroll = new JScrollPane(logDisplay,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outputPanel.add(logScroll);
        thePanel.add(outputPanel);

        theFrame.add(thePanel);
    }


    /**
     * This method sets up both of the JFileChoosers the GUI uses
     * @param isHeccFileChooser if true, sets up the hecc file chooser. if false, sets up the output folder chooser
     * @return A JFileChooser, which has been set up with the appropriate configuration
     */
    private JFileChooser setupTheFileChoosers(boolean isHeccFileChooser){
        JFileChooser fc = new JFileChooser(); //creates the JFileChooser
        fc.setMultiSelectionEnabled(false); //both of them will only allow a single file to be selected
        if (isHeccFileChooser){
            //.hecc file chooser
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY); //only allows files to be chosen
            //filters out everything that isn't a .hecc file
            FileNameExtensionFilter heccFilter = new FileNameExtensionFilter(".hecc files","hecc","HECC","Hecc");
            fc.setFileFilter(heccFilter);
            fc.setDialogTitle("Select a .hecc file to parse"); //reminds user that they need to select a .hecc file

        } else{
            //output folder file chooser
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //only allows directories to be chosen
            fc.setDialogTitle("Select a directory to export your HECCIN Game to"); //reminds user what they're meant to be doing
        }
        return fc;
    }


    /**
     * This method is called when someone attempts to select an input hecc file.
     * It handles the process of choosing the hecc file.
     *
     * @param e so I can use this as a lambda.
     */
    private void selectHeccFileHandler(ActionEvent e) {
        //opens the selectHeccFileChooser filechooser
        if (heccFileChosen){
            selectHeccFileChooser.setCurrentDirectory(heccFileLocation.toFile());
        }

        int fcReturnValue = selectHeccFileChooser.showOpenDialog(theFrame);
        if (fcReturnValue == JFileChooser.APPROVE_OPTION) { //if a .hecc file was chosen
            Path thePath = selectHeccFileChooser.getSelectedFile().toPath().toAbsolutePath();
            selectedAHeccFile(thePath);
        }
    }

    /**
     * Given a path to a .hecc file, this will save it as the heccFileLocation
     * and update the GUI/internal state of this object accordingly.
     *
     * @param heccFilePath the path to the hecc file
     */
    private void selectedAHeccFile(Path heccFilePath) {


        heccFileLocation = heccFilePath;

        //updates the display to show the new hecc file location
        String stringHeccPath = heccFilePath.toString();
        heccFileAttributeString.showValue(stringHeccPath);
        heccFileLocationDisplay.setText(heccFileAttributeString.toString());

        //marks it as being chosen
        heccFileChosen = true;

        //updates GUI, logs it as being chosen.
        revalidate();
        logInfo("hecc file has been chosen!");
    }

    /**
     * This method is used when the button to select an output folder is pressed
     * It handles the process of choosing an output folder
     *
     * @param e so I can use this as a lambda.
     */
    private void selectOutputFileHandler(ActionEvent e) {
        //opens the selectGameLocationChooser fileChooser

        if (outputFolderChosen){
            selectGameLocationChooser.setCurrentDirectory(outputFolderLocation.toFile());
        }

        int fcReturnValue = selectGameLocationChooser.showDialog(theFrame, "Select");
        if (fcReturnValue == JFileChooser.APPROVE_OPTION) { //if a directory was chosen

            Path thePath = selectGameLocationChooser.getSelectedFile().toPath().toAbsolutePath();
            selectedAnOutputFolder(thePath);

        }
    }

    /**
     * Given a path to an output folder for the heccin game, this will save it as the outputFolderLocation
     * and update the GUI/internal state of this object accordingly.
     *
     * @param outputPath the path to the output folder
     */
    private void selectedAnOutputFolder(Path outputPath) {
        //records the path of it
        outputFolderLocation = outputPath;

        String pathString = outputPath.toString();
        //updates the display to show the path of it
        gameLocationAttributeString.showValue(pathString);
        gameLocationDisplay.setText(gameLocationAttributeString.toString());
        //marks it as being chosen
        outputFolderChosen = true;
        revalidate();
        logInfo("Output folder has been chosen!");
    }


    /**
     * Revalidates and re-packs the guiFrame
     * bottom text
     */
    private void revalidate() {
        theFrame.revalidate();
        theFrame.pack();
    }


    /**
     * A version of heccUpTheGame but it's callable as a lambda instead.
     *
     * @param e is here so I can call it via lambda.
     */
    private void heccUpTheGame(ActionEvent e) {
        heccUpTheGame();
    }

    /**
     * Attempts to call the attemptToHeccUpTheGame method of the heccUpHandler object.
     * <p>
     * Complains via JOptionPane if user hasn't defined a hecc file and an output folder,
     * or if the .hecc file couldn't be hecced up successfully.
     * <p>
     * If it was successful, it gives the user the option of opening it directly in their web browser
     * (and will complain if it was unsuccessful)
     */
    private void heccUpTheGame(){
        //only attempts to hecc it up if the hecc file was chosen and the output folder was chosen
        if(heccFileChosen && outputFolderChosen) {
            //attempts to hecc it up
            if (heccUpHandler.attemptToHeccUpTheGame(
                    heccFileLocation,
                    outputFolderLocation
            )) {
                if (JOptionPane.showConfirmDialog(
                        theFrame,
                        "<html><p>Successfully exported your HECCIN Game!<br>Do you want to play it now?</p></html>",
                        "it worked!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                ) == JOptionPane.YES_OPTION){
                    try{
                        File gameFile = new File(outputFolderLocation + "/index.html");
                        Desktop desktop = Desktop.getDesktop();
                        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
                            desktop.browse(gameFile.toURI());
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(
                                theFrame,
                                "<html><p>HECC-UP was unable to open your web browser.<br>You'll need to go to your output folder and open index.html manually.</p></html>",
                                "Unable to open the game",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            } else{
                JOptionPane.showMessageDialog(
                        theFrame,
                        "<html><p>Could not HECC-UP your game!<br>Please see the event log for further details.</p></html>",
                        "uh oh looks like we had a HECC-UP!",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else{
            //complain if the user attempted to HECC-UP when there's no HECC to HECC-UP and/or no output folder was defined
            JOptionPane.showMessageDialog(
                    theFrame,
                    "Please select a .Hecc file to parse\n" +
                            "and a folder for your HECCIN Game\n" +
                            "before pressing the 'HECC-IT' button.",
                    "pls do steps 1 and 2 first",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }


    /**
     * Logs info in the logDisplay JTextArea
     * @param infoToLog the info what needs logging
     */
    public void logInfo(String infoToLog){
        logDisplay.append("\n");
        logDisplay.append(infoToLog);
        //revalidate();

    }


    /**
     * Main function
     * <br>
     * bottom text
     * @param args not used
     */
    public static void main(String[] args){
        new HeccUpGUI();
    }


}
