package hecc_up;

import utilities.TextAssetReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FolderOutputter {

    private final String path = "outputs/hecc_up_testing_v2/";

    private String outputFolderPath;

    private File outputFolder;

    private boolean outputFolderExists;

    public FolderOutputter(){

        outputFolderExists = false;


    }

    public boolean setupOutputFolder(String folderName){

        outputFolderPath = path.concat(folderName).concat("/");

        outputFolder = new File(path + folderName);

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

        if (outputFolderExists) {
            File heccedFile = new File(outputFolderPath.concat("hecced.js"));
            writeTheFile(heccedFile, heccedData);

            File heccerFile = new File(outputFolderPath.concat("heccer.js"));
            writeTheFile(heccerFile, TextAssetReader.getHECCER());

            File indexFile = new File(outputFolderPath.concat("index.html"));
            writeTheFile(indexFile, TextAssetReader.getIndex());
        }

    }

    public void outputTheGameWithMetadata(ArrayList<String> heccedData, ArrayList<String> indexMetadata){

        if (outputFolderExists){
            File heccedFile = new File(outputFolderPath.concat("hecced.js"));
            writeTheFile(heccedFile, heccedData);

            File heccerFile = new File(outputFolderPath.concat("heccer.js"));
            writeTheFile(heccerFile, TextAssetReader.getHECCER());

            writeIndexButWithMetadata(indexMetadata);
        }
    }

    private void writeTheFile(File f, ArrayList<String> dataToWrite){
        try{
            f.createNewFile();
            FileWriter heccedFileWriter = new FileWriter(f);
            for(String s: dataToWrite){
                heccedFileWriter.write(s);
            }
            heccedFileWriter.close();
        } catch(FileNotFoundException e){
            System.out.println("FileNotFoundException!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException!");
            e.printStackTrace();
        }
    }

    private void writeIndexButWithMetadata(ArrayList<String> indexMetadata){

        ArrayList<String> indexData = TextAssetReader.getIndex();
        File f = new File(outputFolderPath.concat("index.html"));

        try{
            f.createNewFile();
            FileWriter heccedFileWriter = new FileWriter(f);
            for(String s: indexData){
                if (s.equals("<!-- METADATA GOES HERE -->\n")){
                    for(String m: indexMetadata){
                        heccedFileWriter.write(m);
                        heccedFileWriter.write("\n");
                    }
                } else {
                    heccedFileWriter.write(s);
                }
            }
            heccedFileWriter.close();
        } catch(FileNotFoundException e){
            System.out.println("FileNotFoundException!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException!");
            e.printStackTrace();
        }

    }

}
