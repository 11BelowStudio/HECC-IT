package hecc_up;

import utilities.AttributeString;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * This class is basically a GUI for the HECC-UP stuff
 * so a user can actually, y'know, hecc up their stuff.
 */
public class HeccUpGUI implements LoggerInterface {

    //TODO: incorporate into OH-HECC

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
    private String heccFileLocation;
    /**
     * where to save the HECCIN Game to
     */
    private String outputFolderLocation;

    //and now, the classes which are to be used for the GUI
    /**
     * the frame that has the gui
     */
    public JFrame theFrame;

    /**
     * The panel that will actually hold the GUI
     */
    private JPanel thePanel;

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
     * Constructor
     * Makes the GUI frame + shows it
     * Initialises/constructs some other properties of this class
     * including the HeccUpHandler
     */
    public HeccUpGUI(){
        //first it will actually, y'know, make the GUI
        makeTheGui();

        //then it'll make the GUI visible, revalidated, packed, etc
        theFrame.setVisible(true);
        theFrame.setPreferredSize(new Dimension(800,600));
        theFrame.revalidate();
        theFrame.pack();

        //setting up the AttributeStrings so they can be easily used later on
        heccFileAttributeString = new AttributeString<>(".hecc file: ","");
        gameLocationAttributeString = new AttributeString<>("Output folder: ","");

        //these are false by default
        heccFileChosen = false;
        outputFolderChosen = false;

        //outputter = new FolderOutputter();

        heccUpHandler = new HeccUpHandler(this);
    }

    /**
     * ok so basically this just makes the GUI
     */
    private void makeTheGui(){

        //makes the frame
        theFrame = new JFrame();
        theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //exits when closed
        theFrame.setTitle("HECC Uncomplicated Parser"); //verbose title

        //A lowered etched border
        Border loweredEtchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        theFrame.setLayout(new GridLayout(1,1));

        //thePanel will have a BoxLayout
        thePanel = new JPanel();
        thePanel.setLayout(new BoxLayout(thePanel, BoxLayout.Y_AXIS));

        //guiFrame will use a BoxLayout.
        //theFrame.setLayout(new BoxLayout(theFrame.getContentPane(), BoxLayout.Y_AXIS));


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
        selectHeccFileButton.addActionListener(e -> this.selectHeccFileHandler());
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
        selectGameLocationButton.addActionListener(e -> this.selectOutputFileHandler());
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
        heccItUpButton.addActionListener(e -> this.heccUpTheGame());

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
     */
    private void selectHeccFileHandler(){
        //opens the selectHeccFileChooser filechooser
        int fcReturnValue = selectHeccFileChooser.showOpenDialog(theFrame);
        if (fcReturnValue == JFileChooser.APPROVE_OPTION) { //if a .hecc file was chosen
            //finds the path of the selected .hecc file
            heccFileLocation = selectHeccFileChooser.getSelectedFile().getAbsolutePath();
            //updates the display to show the new hecc file location
            heccFileAttributeString.showValue(heccFileLocation);
            heccFileLocationDisplay.setText(heccFileAttributeString.toString());
            //marks it as being chosen
            heccFileChosen = true;
            revalidate();
            logInfo("hecc file has been chosen!");
        }
    }

    /**
     * This method is used when the button to select an output folder is pressed
     * It handles the process of choosing an output folder
     */
    private void selectOutputFileHandler(){
        //opens the selectGameLocationChooser fileChooser
        int fcReturnValue = selectGameLocationChooser.showDialog(theFrame,"Select");
        if (fcReturnValue == JFileChooser.APPROVE_OPTION) { //if a directory was chosen
            //records the path of it
            outputFolderLocation = selectGameLocationChooser.getSelectedFile().getAbsolutePath();
            //updates the display to show the path of it
            gameLocationAttributeString.showValue(outputFolderLocation);
            gameLocationDisplay.setText(gameLocationAttributeString.toString());
            //marks it as being chosen
            outputFolderChosen = true;
            revalidate();
            logInfo("Output folder has been chosen!");
        }
    }



    /**
     * Revalidates and re-packs the guiFrame
     * bottom text
     */
    private void revalidate(){
        theFrame.revalidate();
        theFrame.pack();
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
