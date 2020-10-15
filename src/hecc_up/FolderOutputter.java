package hecc_up;

import utilities.TextAssetReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FolderOutputter {

    //private final String path = "outputs/hecc_up_testing_v2/";

    private String outputFolderPath;

    private File outputFolder;

    private boolean outputFolderExists;

    //constructor
    //bottom text
    public FolderOutputter(){

        outputFolderExists = false;

    }



    //this function creates a folder with the given folderName
    public boolean setupOutputFolder(String folderName){

        outputFolderPath = folderName.concat("/");

        outputFolder = new File(folderName);

        outputFolderExists = false;

        try {
            outputFolder.mkdirs();
            outputFolderExists = outputFolder.exists();
        } catch (SecurityException e){
            outputFolderExists = false;
            e.printStackTrace();
        }

        return outputFolderExists;
    }

    public boolean doesOutputFolderExist(){
        return outputFolderExists;
    }

    public void outputTheGame(ArrayList<String> heccedData){
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

    //this outputs the HECCIN Game, using the passed heccedData and the passed metadata.
    public boolean outputTheGameWithMetadata(ArrayList<String> heccedData, Metadata metadata) throws FileNotFoundException, IOException {
        if (outputFolderExists) {
            File heccedFile = new File(outputFolderPath.concat("hecced.js"));
            writeTheFile(heccedFile, heccedData);

            File heccerFile = new File(outputFolderPath.concat("heccer.js"));
            writeTheFile(heccerFile, TextAssetReader.getHECCER());

            writeIndexButWithMetadata(metadata);

            writeIFictionFile(metadata);

            return true;
        } else {
            return false;
        }

    }

    //this is a generic method for writing a File (f), containing the contents of the dataToWrite ArrayList<String>
    private void writeTheFile(File f, ArrayList<String> dataToWrite) throws FileNotFoundException, IOException{
        //try{
            f.createNewFile();
            FileWriter heccedFileWriter = new FileWriter(f);
            for(String s: dataToWrite){
                heccedFileWriter.write(s);
            }
            heccedFileWriter.close();
            /*
        } catch(FileNotFoundException e){
            System.out.println("FileNotFoundException!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException!");
            e.printStackTrace();
        }*/
    }


    //this writes the Index.html file, using the given Metadata object
    private void writeIndexButWithMetadata(Metadata metadata) throws FileNotFoundException, IOException{

        ArrayList<String> indexData = TextAssetReader.getIndex();
        File f = new File(outputFolderPath.concat("index.html"));

        //try{
            f.createNewFile();
            FileWriter indexFileWriter = new FileWriter(f);
            for(String s: indexData){
                if (s.equals("<!-- METADATA GOES HERE -->\n")){
                    indexFileWriter.write(metadata.getIfidButHtmlFormatted());
                } else {
                    indexFileWriter.write(s);
                }
            }
            indexFileWriter.close();
            /*
        } catch(FileNotFoundException e){
            System.out.println("FileNotFoundException!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException!");
            e.printStackTrace();
        }*/

    }

    //this outputs the iFictionFile, based on iFictionTemplate.iFiction and the given Metadata object
    private void writeIFictionFile(Metadata metadata) throws FileNotFoundException, IOException {
        ArrayList<String> iFictionData = TextAssetReader.getIFictionTemplate();
        File f = new File(outputFolderPath.concat("metadata.iFiction"));

        //try{
            f.createNewFile();
            FileWriter iFictionFileWriter = new FileWriter(f);
            for(String s: iFictionData){
                if (s.equals("\t\t\t<ifid></ifid>\n")){
                    iFictionFileWriter.write("\t\t\t<ifid>" + metadata.getIFID() +"</ifid>\n");
                } else if(s.equals("\t\t\t<title></title>\n")) {
                    iFictionFileWriter.write("\t\t\t<title>" + metadata.getTitle() +"</title>\n");
                } else if (s.equals("\t\t\t<author></author>\n")){
                    iFictionFileWriter.write("\t\t\t<author>" + metadata.getAuthor() +"</author>\n");
                } else {
                    iFictionFileWriter.write(s);
                }
            }
            iFictionFileWriter.close();
            /*
        } catch(FileNotFoundException e){
            System.out.println("FileNotFoundException!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException!");
            e.printStackTrace();
        }
             */
    }

}
