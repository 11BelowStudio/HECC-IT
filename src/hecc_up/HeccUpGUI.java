package hecc_up;

import utilities.AttributeString;
import utilities.TextAssetReader;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class HeccUpGUI implements LoggerInterface {

    //the PassageParser object which will be used for parsing the passages and such
    private PassageParser passageParser;
    //the FolderOutputter object which will be used for outputting the HECCIN Game and such
    private FolderOutputter outputter;

    //some internal state stuff
    private boolean heccFileChosen;
    private boolean outputFolderChosen;

    //the location of the .hecc file
    private String heccFileLocation;
    //where to save the HECCIN Game to
    private String outputFolderLocation;

    //and now, the classes which are to be used for the GUI
    public JFrame guiFrame;


    private JTextArea heccFileLocationDisplay; //Label stating the location of the chosen hecc file
    private final AttributeString<String> heccFileAttributeString; //AttributeString used to display the hecc file location
    private JFileChooser selectHeccFileChooser; //FileChooser for selecting the hecc file

    private JTextArea gameLocationDisplay; //currently selected output directory
    private final AttributeString<String> gameLocationAttributeString; //AttributeString used to display the output file location
    private JFileChooser selectGameLocationChooser; //choose where to save it


    private JButton heccItUpButton; //the 'HECC-IT' button

    private JTextArea logDisplay;

    public HeccUpGUI(){
        //first it will actually, y'know, make the GUI
        makeTheGui();

        //then it'll make the GUI visible, revalidated, packed, etc
        guiFrame.setVisible(true);
        guiFrame.setPreferredSize(new Dimension(800,600));
        guiFrame.revalidate();
        guiFrame.pack();

        //setting up the AttributeStrings so they can be easily used later on
        heccFileAttributeString = new AttributeString<>(".hecc file: ","");
        gameLocationAttributeString = new AttributeString<>("Output folder: ","");

        //these are false by default
        heccFileChosen = false;
        outputFolderChosen = false;

        outputter = new FolderOutputter();
    }

    private void makeTheGui(){

        //makes the frame
        guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //exits when closed
        guiFrame.setTitle("HECC Uncomplicated Parser"); //verbose title

        //A lowered etched border
        Border loweredEtchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        //guiFrame will use a BoxLayout.
        guiFrame.setLayout(new BoxLayout(guiFrame.getContentPane(), BoxLayout.Y_AXIS));


        //Creating the title
        //panel to hold the title label
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1,1));
        //and now the title label
        JLabel titleLabel = new JLabel(
                "<html><h1>HECC-UP!</h1><html>",
                SwingConstants.CENTER
        );
        titlePanel.add(titleLabel); //it's been added to the titlePanel
        guiFrame.add(titlePanel); //and this title panel is now in the frame
        

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
        selectHeccFileButton.addActionListener(e -> {this.selectHeccFileHandler();});
        selectHeccFilePanel.add(selectHeccFileButton);
        //jFileChooser for hecc file
        selectHeccFileChooser = setupTheFileChoosers(true);

        //and now, the hecc selection panel is added to the gui frame
        guiFrame.add(selectHeccFilePanel);



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
        selectGameLocationButton.addActionListener(e -> {this.selectOutputFileHandler();});
        selectGameLocationPanel.add(selectGameLocationButton);
        //jFileChooser for the game location stuff
        selectGameLocationChooser = setupTheFileChoosers(false);


        guiFrame.add(selectGameLocationPanel);

        //and now, a panel for the 'HECC-UP' button
        JPanel heccItUpPanel = new JPanel();
        heccItUpPanel.setLayout(new GridLayout(1,1));
        heccItUpPanel.setBorder(
                BorderFactory.createTitledBorder(
                        loweredEtchedBorder,
                        "Step 3"
                )
        );
        heccItUpButton = new JButton("HECC-IT!");
        heccItUpButton.addActionListener( e -> {this.heccUpTheGame();});

        heccItUpPanel.add(heccItUpButton);
        //and the heccItUpPanel is added to the gui Frame
        guiFrame.add(heccItUpPanel);


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
        guiFrame.add(outputPanel);
    }

    //just going to set up the file choosers using this method. why? because I dont want it clogging up the already clogged method above.
    // if true, it sets up the hecc file chooser. if false, it sets up the output folder chooser
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

    //this method is called when the 'selectHeccFileButton' is pressed
    private void selectHeccFileHandler(){
        //opens the selectHeccFileChooser filechooser
        int fcReturnValue = selectHeccFileChooser.showOpenDialog(guiFrame);
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
    
    private void selectOutputFileHandler(){
        //opens the selectGameLocationChooser fileChooser
        int fcReturnValue = selectGameLocationChooser.showDialog(guiFrame,"Select");
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

    private void revalidate(){
        guiFrame.revalidate();
        guiFrame.pack();
    }
    
    

    private void heccUpTheGame(){
        //only attempts to hecc it up if the hecc file was chosen and the output folder was chosen
        if(heccFileChosen && outputFolderChosen) {
            try {
                //creates a passageParser object, parsing the specified hecc file
                passageParser = new PassageParser(
                        TextAssetReader.fileToString(heccFileLocation),
                        this
                );

                //sets the output folder of the outputter to the specified folder
                outputter.setupOutputFolder(outputFolderLocation);

                //first, check that the output folder actually exists
                if (outputter.doesOutputFolderExist()) {
                    //if the output folder exists, attempt to construct the passage objects
                    System.out.println("output folder exists");

                    //if the output folder exists, attempt to construct the passage objects
                    if (passageParser.constructThePassageObjects()) {
                        //then, attempt to prepare the hecced data
                        if (passageParser.prepareHeccedData()) {
                            //finally, if everything worked, output the game

                            //uses heccedData and metadata to from passageParser to output the HECCIN Game
                            outputter.outputTheGameWithMetadata(passageParser.getHeccedData(), passageParser.getMetadata());


                            System.out.println("done.");
                            logInfo("Done!");

                        }
                    }
                } else {
                    //log an error message if the output folder vanishes
                    logInfo("The game cannot be parsed and output, as there is no output folder for it");
                }
            } catch (Exception e){
                //report the exception if something bad happens
                logInfo(e.getMessage());
                e.printStackTrace();
            }
        } else{
            //complain if the user attempted to HECC-UP when there's no HECC to HECC-UP and/or no output folder was defined
            JOptionPane.showMessageDialog(
                    guiFrame,
                    "Please select a .Hecc file to parse\n" +
                            "and a folder for your HECCIN Game\n" +
                            "before pressing the 'HECC-IT' button.",
                    "pls do steps 1 and 2 first",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    //prints stuff to the info log thing at the bottom of the GUI
    public void logInfo(String infoToLog){
        logDisplay.append("\n");
        logDisplay.append(infoToLog);
        //revalidate();

    }

    //le main has arrived
    public static void main(String[] args){
        new HeccUpGUI();
    }


}
