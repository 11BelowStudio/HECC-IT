package oh_hecc;

import oh_hecc.mvc.View;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

/**
 * This class is intended as a frame that holds the prompt for a user to pick a HECC file to edit from
 * (or alternatively create a completely new HECC file to write)
 */
public class OhHeccChooseFileFrame {
    final JFrame theFrame;

    String fileLocation;


    private boolean selectedHeccFileToOpen;
    private JTextArea openTextArea;
    private JFileChooser openHeccFileChooser;
    private JButton startEditingButton;


    private boolean selectedHeccFileToMake;
    private JTextArea makeTextArea;
    private JFileChooser makeHeccFileChooser;


    public OhHeccChooseFileFrame(){

        theFrame = new JFrame();

        theFrame.setTitle("Welcome to OH-HECC");

        theFrame.setLayout(new BoxLayout(theFrame.getContentPane(),BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        JLabel titleLabel = new JLabel("OH-HECC!");
        titlePanel.add(titleLabel);
        theFrame.add(titlePanel);

        JPanel actualControlPanel = new JPanel();
        actualControlPanel.setLayout(new GridLayout(1,2));

        //TODO: stuff for opening an existing file
        JPanel openExistingFilePanel = new JPanel();
        openExistingFilePanel.setLayout(new BoxLayout(openExistingFilePanel, BoxLayout.Y_AXIS));

        JPanel openTitlePanel = new JPanel(new GridLayout(1,1));
        JLabel openLabel = new JLabel("Open existing HECC file");
        openTitlePanel.add(openLabel);
        openExistingFilePanel.add(openTitlePanel);

        JPanel selectFileToOpenPanel = new JPanel();
        selectFileToOpenPanel.setLayout(new BoxLayout(selectFileToOpenPanel,BoxLayout.Y_AXIS));

        openTextArea = new JTextArea("Please select a HECC file to open",3,48);
        openTextArea.setEditable(false);
        selectFileToOpenPanel.add(openTextArea);

        openHeccFileChooser = new JFileChooser();
        openHeccFileChooser.setMultiSelectionEnabled(false);
        openHeccFileChooser.setFileFilter(new FileNameExtensionFilter(".hecc files","hecc","HECC","Hecc"));
        openHeccFileChooser.setDialogTitle("Pick a .hecc file to open");

        JButton openButton = new JButton("Select file to open");
        openButton.addActionListener( e-> selectFileToOpen());
        selectFileToOpenPanel.add(openButton);

        openExistingFilePanel.add(selectFileToOpenPanel);

        startEditingButton = new JButton("Start editing");
        startEditingButton.addActionListener( e -> startEditing());

        startEditingButton.setVisible(false);

        openExistingFilePanel.add(startEditingButton);

        //selectFileToOpenPanel.add(openExistingFilePanel);



        theFrame.add(openExistingFilePanel);


        //TODO: stuff for creating a new hecc file from scratch
        JPanel startFromScratchPanel = new JPanel();
        startFromScratchPanel.setLayout(new BoxLayout(startFromScratchPanel,BoxLayout.Y_AXIS));


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
