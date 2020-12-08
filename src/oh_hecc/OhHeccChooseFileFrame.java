package oh_hecc;

import oh_hecc.mvc.View;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

/**
 * This class is intended as a frame that holds the prompt for a user to pick a HECC file to edit from
 * (or alternatively create a completely new HECC file to write)
 * @deprecated {@see ChooseFile.java}
 */
public class OhHeccChooseFileFrame {

    /**
     * The JFrame that this shows stuff in
     */
    final JFrame theFrame;

    String fileLocation;


    private boolean selectedHeccFileToOpen;
    private JTextArea openTextArea;
    private JFileChooser openHeccFileChooser;
    private JButton startEditingButton;


    private boolean selectedHeccFileToMake;
    private JTextArea makeTextArea;
    private JFileChooser makeHeccFileChooser;


    /**
     * Le constructor has arrived
     */
    public OhHeccChooseFileFrame(){

        theFrame = new JFrame();

        theFrame.setTitle("Welcome to OH-HECC");
        theFrame.setLayout(new BorderLayout());

        //theFrame.setLayout(new BoxLayout(theFrame.getContentPane(),BoxLayout.Y_AXIS));

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel,BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        JLabel titleLabel = new JLabel("<html><h1>OH-HECC!</h1><html>", SwingConstants.CENTER);
        titlePanel.add(titleLabel);
        upperPanel.add(titlePanel);
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        upperPanel.add(separator);

        theFrame.add(upperPanel,BorderLayout.NORTH);

        JPanel actualControlPanel = new JPanel();
        actualControlPanel.setLayout(new GridLayout(1,2));


        //TODO: stuff for creating a new hecc file from scratch
        JPanel startFromScratchPanel = new JPanel();
        startFromScratchPanel.setLayout(new BoxLayout(startFromScratchPanel,BoxLayout.Y_AXIS));

        JLabel startTitle = new JLabel("<html><h3>Make a new .hecc file</h3></html>",SwingConstants.CENTER);
        startFromScratchPanel.add(startTitle);



        actualControlPanel.add(startFromScratchPanel);//, BorderLayout.WEST);



        //TODO: stuff for opening an existing file
        JPanel openExistingFilePanel = new JPanel(new BorderLayout());
        //openExistingFilePanel.setLayout(new BoxLayout(openExistingFilePanel, BoxLayout.Y_AXIS));

        JPanel openTitlePanel = new JPanel(new GridLayout(1,1));
        JLabel openLabel = new JLabel("<html><h3>Open existing HECC file</h3></html>", SwingConstants.CENTER);
        openTitlePanel.add(openLabel);
        openExistingFilePanel.add(openTitlePanel, BorderLayout.NORTH);

        JPanel selectFileToOpenPanel = new JPanel();
        selectFileToOpenPanel.setLayout(new BoxLayout(selectFileToOpenPanel,BoxLayout.Y_AXIS));

        openTextArea = new JTextArea("Please select a HECC file to open", 3,30);
        openTextArea.setEditable(false);
        openTextArea.setLineWrap(true);
        //openTextArea.setWrapStyleWord(true);
        selectFileToOpenPanel.add(openTextArea);

        openHeccFileChooser = new JFileChooser();
        openHeccFileChooser.setMultiSelectionEnabled(false);
        openHeccFileChooser.setFileFilter(new FileNameExtensionFilter(".hecc files","hecc","HECC","Hecc"));
        openHeccFileChooser.setDialogTitle("Pick a .hecc file to open");


        //openExistingFilePanel.add(selectFileToOpenPanel, BorderLayout.CENTER);

        JPanel openButtonPanel = new JPanel(new GridLayout(1,1));
        JButton openButton = new JButton("Select file to open");
        //openButton.setHorizontalAlignment(SwingConstants.CENTER);
        openButton.addActionListener( e-> selectFileToOpen());
        openButtonPanel.add(openButton);
        selectFileToOpenPanel.add(openButtonPanel);

        openExistingFilePanel.add(selectFileToOpenPanel,BorderLayout.CENTER);


        JPanel startEditingButtonPanel = new JPanel(new GridLayout(1,1));

        startEditingButton = new JButton("Start editing");
        startEditingButton.setHorizontalAlignment(SwingConstants.CENTER);
        startEditingButton.addActionListener( e -> startEditing());

        startEditingButton.setVisible(false);

        startEditingButtonPanel.add(startEditingButton);

        openExistingFilePanel.add(startEditingButtonPanel, BorderLayout.SOUTH);

        actualControlPanel.add(openExistingFilePanel);//, BorderLayout.EAST);



        theFrame.add(actualControlPanel, BorderLayout.CENTER);





        theFrame.setVisible(true);
        theFrame.pack();


    }



    private void selectFileToOpen(){
        //TODO: open JFileChooser, allow user to pick a .hecc file

        if(openHeccFileChooser.showDialog(theFrame,"Open this file") == JFileChooser.APPROVE_OPTION){
            selectedHeccFileToMake = true;
            fileLocation = openHeccFileChooser.getSelectedFile().getAbsolutePath();
            openTextArea.setText(fileLocation);
            startEditingButton.setVisible(true);

        }
    }

    private void startEditing(){
        //TODO: start editing the chosen file to edit
    }

    private void selectFileToMake(){
        //TODO: find path for new .hecc file
    }

    private void startEditingNewFile(){
        //TODO: start editing the new .hecc file
    }

    public static void main(String[] args) {
        new OhHeccChooseFileFrame();
    }
}
