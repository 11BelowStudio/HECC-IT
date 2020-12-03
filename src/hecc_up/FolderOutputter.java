package hecc_up;

import GameParts.Metadata;
import utilities.TextAssetReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for outputting the HECCIN' Game into the folder it's supposed to be output into.
 */
public class FolderOutputter {

    //private final String path = "outputs/hecc_up_testing_v2/";

    /**
     * LoggerInterface object to log data to
     */
    LoggerInterface logger; //LoggerInterface object to log data to

    /**
     * the filepath designating where to output stuff to
     */
    private String outputFolderPath; //where to output stuff to

    //private File outputFolder;

    /**
     * whether or not the output folder exists
     */
    private boolean outputFolderExists; //whether or not the output folder exists

    /**
     * obsolete constructor
     * bottom text
     */
    public FolderOutputter(){
        outputFolderExists = false;
        logger = new LoggerInterface(){};
    }

    /**
     * This is the FolderOutputter constructor
     * @param outputFolderPath filepath for the output folder
     * @param logger LoggerInterface object where this logs data to
     * @throws SecurityException if the output folder could not be made
     */
    public FolderOutputter(String outputFolderPath, LoggerInterface logger) throws SecurityException{
        outputFolderExists = false;
        this.logger = logger;
        setupOutputFolder(outputFolderPath);
    }


    /**
     * Attempts to create a folder at the given folderPath
     * @param folderPath The filepath of where the folder is to be made
     * @return whether or not the folder was created successfully/now exists
     * @throws SecurityException if there's a security problem preventing it from being made
     */
    public boolean setupOutputFolder(String folderPath) throws SecurityException{

        outputFolderPath = folderPath.concat("/"); //puts an extra / at the end of the filename
        File outputFolder = new File(folderPath);
        outputFolderExists = false;

        //attempts to create the necessary folder(s) to output the game into
        outputFolder.mkdirs();
        //makes sure that folder exists
        outputFolderExists = outputFolder.exists();

        //returns whether or not the output folder exists
        return outputFolderExists;
    }

    /**
     * Working out whether or not the output folder exists
     * @return whether or not the output folder exists
     */
    public boolean doesOutputFolderExist(){
        return outputFolderExists;
    }

    public void outputTheGame(List<String> heccedData){
        try {
            if (outputFolderExists) {
                File heccedFile = new File(outputFolderPath.concat("hecced.js"));
                writeTheFile(heccedFile, heccedData);

                File heccerFile = new File(outputFolderPath.concat("heccer.js"));
                writeTheFile(heccerFile, TextAssetReader.getHECCER());

                File indexFile = new File(outputFolderPath.concat("index.html"));
                writeTheFile(indexFile, TextAssetReader.getIndex());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //this outputs the HECCIN Game, using the passed heccedData and the passed metadata.
    public void outputTheGameWithMetadataOldVersion(ArrayList<String> heccedData, Metadata metadata){
        try {
            if (outputFolderExists) {
                File heccedFile = new File(outputFolderPath.concat("hecced.js"));
                writeTheFile(heccedFile, heccedData);

                File heccerFile = new File(outputFolderPath.concat("heccer.js"));
                writeTheFile(heccerFile, TextAssetReader.getHECCER());

                writeIndexButWithMetadata(metadata);

                writeIFictionFile(metadata);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This outputs the HECCIN Game, using the passed heccedData and the passed Metadata object
     * @param heccedData the hecced data to be put in hecced.js
     * @param metadata the metadata object to be used in a few places (via the outputterMetadata interface)
     * @return true if everything executes successfully
     * @throws SecurityException if there's a security problem preventing stuff from being output
     * @throws IOException if there's another input/output problem
     */
    public boolean outputTheGameWithMetadata(List<String> heccedData, FolderOutputterMetadataInterface metadata) throws SecurityException, IOException {
        if (outputFolderExists) {
            File heccedFile = makeTheFile("hecced.js");
            writeTheFile(heccedFile, heccedData);

            File heccerFile = makeTheFile("heccer.js");
            writeTheFile(heccerFile, TextAssetReader.getHECCER());

            writeIndexButWithMetadata(metadata);

            writeIFictionFile(metadata);

            return true;
        } else {
            return false;
        }

    }

    /**
     * Creates a file with specified filename at the outputFolderPath
     * @param filename name of the file
     * @return the file which was made
     * @throws SecurityException if security problem
     * @throws IOException if IO problem
     */
    private File makeTheFile(String filename) throws SecurityException, IOException{
        File f = new File(outputFolderPath.concat(filename));
        f.createNewFile();
        return f;
    }

    /**
     * This is a generic method for writing a File (f)
     * @param f the file (which has been created) to be written to
     * @param dataToWrite all the data to be written to file f
     * @throws SecurityException if there's a security problem preventing this from working
     * @throws IOException if there's another IO problem
     */
    private void writeTheFile(File f, List<String> dataToWrite) throws SecurityException, IOException{
        FileWriter heccedFileWriter = new FileWriter(f);
        for(String s: dataToWrite){
            heccedFileWriter.write(s);
        }
        heccedFileWriter.close();
    }


    /**
     * This writes the index.html file, and uses the given Metadata object to help out
     * @param metadata the metadata object that data is being obtained from
     * @throws SecurityException if there's a security problem preventing the file from being made
     * @throws IOException if there's another IO problem
     */
    private void writeIndexButWithMetadata(FolderOutputterMetadataInterface metadata) throws SecurityException, IOException{

        List<String> indexData = TextAssetReader.getIndex();
        //File f = new File(outputFolderPath.concat("index.html"));
        //f.createNewFile();
        //File f = makeTheFile("index.html");

        FileWriter indexFileWriter = new FileWriter(makeTheFile("index.html"));
        for(String s: indexData){
            if (s.equals("<!-- METADATA GOES HERE -->\n")){
                indexFileWriter.write(metadata.getIfidButHtmlFormatted());
            } else {
                indexFileWriter.write(s);
            }
        }
        indexFileWriter.close();

    }

    /**
     * This writes the metadata.iFiction file, using data held in the metadata object
     * @param metadata the metadata object that data is being obtained from
     * @throws SecurityException if there's a security problem preventing the file from being made
     * @throws IOException if there's another IO problem
     */
    private void writeIFictionFile(FolderOutputterMetadataInterface metadata) throws SecurityException, IOException {
        //ArrayList<String> iFictionData = TextAssetReader.getIFictionTemplate();

        //File f = new File(outputFolderPath.concat("metadata.iFiction"));
        //f.createNewFile();
        FileWriter iFictionFileWriter = new FileWriter(makeTheFile("metadata.iFiction"));
        iFictionFileWriter.write(metadata.getIFictionMetadata());
        /*
        for(String s: iFictionData){
            switch (s) {
                case "\t\t\t<ifid></ifid>\n":
                    iFictionFileWriter.write("\t\t\t<ifid>" + metadata.getIfid() + "</ifid>\n");
                    break;
                case "\t\t\t<title></title>\n":
                    iFictionFileWriter.write("\t\t\t<title>" + metadata.getTitle() + "</title>\n");
                    break;
                case "\t\t\t<author></author>\n":
                    iFictionFileWriter.write("\t\t\t<author>" + metadata.getAuthor() + "</author>\n");
                    break;
                default:
                    iFictionFileWriter.write(s);
                    break;
            }
        }
        */
        iFictionFileWriter.close();
    }

}
