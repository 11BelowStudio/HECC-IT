package hecc_up;

import gameParts.Metadata;
import utilities.TextAssetReader;

import java.io.*;
import java.nio.file.*;
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
    private Path outputFolderPath; //where to output stuff to

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
    public FolderOutputter(String outputFolderPath, LoggerInterface logger) throws SecurityException {
        outputFolderExists = false;
        this.logger = logger;
        setupOutputFolder(outputFolderPath);
    }

    /**
     * This is the FolderOutputter constructor
     *
     * @param outputFolderPath filepath for the output folder
     * @param logger           LoggerInterface object where this logs data to
     * @throws SecurityException if the output folder could not be made
     * @throws IOException       if the output folder could not be made
     */
    public FolderOutputter(Path outputFolderPath, LoggerInterface logger) throws SecurityException, IOException {
        outputFolderExists = false;
        this.logger = logger;
        setupOutputFolder(outputFolderPath);
    }


    /**
     * Attempts to create a folder at the given folderPath
     *
     * @param folderPath The filepath of where the folder is to be made
     * @return whether or not the folder was created successfully/now exists
     * @throws SecurityException if there's a security problem preventing it from being made
     */
    public boolean setupOutputFolder(String folderPath) throws SecurityException {

        outputFolderPath = Paths.get(folderPath.concat("/")); //puts an extra / at the end of the filename
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
     * Attempts to create a folder at the given folderPath
     *
     * @param folderPath The filepath of where the folder is to be made
     * @return whether or not the folder was created successfully/now exists
     * @throws SecurityException if there's a security problem preventing it from being made
     * @throws IOException       if there's an IO problem preventing it from being made.
     */
    public boolean setupOutputFolder(Path folderPath) throws SecurityException, IOException {

        //outputFolderPath = folderPath.concat("/"); //puts an extra / at the end of the filename
        outputFolderPath = Files.createDirectories(folderPath);
        outputFolderExists = false;

        //attempts to create the necessary folder(s) to output the game into
        //outputFolder.mkdirs();
        //makes sure that folder exists
        outputFolderExists = true;

        //returns whether or not the output folder exists
        return outputFolderExists;
    }

    /**
     * Working out whether or not the output folder exists
     *
     * @return whether or not the output folder exists
     */
    public boolean doesOutputFolderExist() {
        return outputFolderExists;
    }

    public void outputTheGame(List<String> heccedData){
        try {
            if (outputFolderExists) {

                Path tempDir = Files.createTempDirectory("oldFiles");

                Path heccedFilePath = outputFolderPath.resolve("hecced.js");

                if (Files.exists(heccedFilePath)) {
                    Files.move(heccedFilePath, tempDir.resolve(heccedFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    //Files.deleteIfExists(heccedFilePath);
                }

                Path heccedFile = Files.createFile(heccedFilePath);
                //File heccedFile = new File(outputFolderPath.concat("hecced.js"));
                writeTheFile(heccedFile, heccedData);

                Path heccerFilePath = outputFolderPath.resolve("heccer.js");

                if (Files.exists(heccerFilePath)) {
                    Files.move(heccerFilePath, tempDir.resolve(heccerFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    //Files.deleteIfExists(heccerFilePath);
                }

                Path heccerFile = Files.createFile(heccerFilePath);

                //File heccerFile = new File(outputFolderPath.concat("heccer.js"));
                writeTheFile(heccerFile, TextAssetReader.getHECCER());

                Path indexFilePath = outputFolderPath.resolve("index.html");

                if (Files.exists(indexFilePath)) {
                    Files.move(indexFilePath, tempDir.resolve(indexFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    //Files.deleteIfExists(indexFilePath);
                }

                Path indexFile = Files.createFile(indexFilePath);

                //File indexFile = new File(outputFolderPath.concat("index.html"));
                writeTheFile(indexFile, TextAssetReader.getIndex());

                Path showdownFilePath = outputFolderPath.resolve("showdown.min.js");

                if (Files.exists(showdownFilePath)) {
                    Files.move(showdownFilePath, tempDir.resolve(showdownFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    //Files.deleteIfExists(showdownFilePath);
                }

                Path showdownFile = Files.createFile(showdownFilePath);

                //File showdownFile = new File(outputFolderPath.concat("showdown.min.js"));
                writeTheFile(showdownFile, TextAssetReader.getShowdownMinJs());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * this outputs the HECCIN Game, using the passed heccedData and the passed metadata.
     *
     * @param heccedData the heccedData to output
     * @param metadata   the metadata for the game
     */
    @Deprecated
    public void outputTheGameWithMetadataOldVersion(ArrayList<String> heccedData, Metadata metadata) {
        try {
            if (outputFolderExists) {
                Path tempDir = Files.createTempDirectory("oldFiles");

                Path heccedFilePath = outputFolderPath.resolve("hecced.js");

                if (Files.exists(heccedFilePath)) {
                    Files.move(heccedFilePath, tempDir.resolve(heccedFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    //Files.deleteIfExists(heccedFilePath);
                }

                Path heccedFile = Files.createFile(heccedFilePath);
                //File heccedFile = new File(outputFolderPath.concat("hecced.js"));
                writeTheFile(heccedFile, heccedData);

                Path heccerFilePath = outputFolderPath.resolve("heccer.js");

                if (Files.exists(heccerFilePath)) {
                    Files.move(heccerFilePath, tempDir.resolve(heccerFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    //Files.deleteIfExists(heccerFilePath);
                }

                Path heccerFile = Files.createFile(heccerFilePath);

                //File heccerFile = new File(outputFolderPath.concat("heccer.js"));
                writeTheFile(heccerFile, TextAssetReader.getHECCER());


                Path showdownFilePath = outputFolderPath.resolve("showdown.min.js");

                if (Files.exists(showdownFilePath)) {
                    Files.move(showdownFilePath, tempDir.resolve(showdownFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    //Files.deleteIfExists(showdownFilePath);
                }

                Path showdownFile = Files.createFile(showdownFilePath);

                //File showdownFile = new File(outputFolderPath.concat("showdown.min.js"));
                writeTheFile(showdownFile, TextAssetReader.getShowdownMinJs());

                Path indexFilePath = outputFolderPath.resolve("index.html");

                if (Files.exists(indexFilePath)) {
                    Files.move(indexFilePath, tempDir.resolve(indexFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    //Files.deleteIfExists(indexFilePath);
                }

                Path indexFile = Files.createFile(indexFilePath);

                writeIndexButWithMetadata(metadata, indexFile);

                Path iFictionFilePath = outputFolderPath.resolve("metadata.ifiction");

                if (Files.exists(iFictionFilePath)) {
                    Files.move(iFictionFilePath, tempDir.resolve(iFictionFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    //Files.deleteIfExists(iFictionFilePath);
                }

                Path iFictionFile = Files.createFile(iFictionFilePath);

                writeIFictionFile(metadata, iFictionFile);
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
            Path tempDir = Files.createTempDirectory("oldFiles");

            Path heccedFilePath = outputFolderPath.resolve("hecced.js");


            if (Files.exists(heccedFilePath)) {
                Files.move(heccedFilePath, tempDir.resolve(heccedFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                //Files.deleteIfExists(heccedFilePath);
            }

            Path heccedFile = Files.createFile(heccedFilePath);
            //File heccedFile = new File(outputFolderPath.concat("hecced.js"));
            writeTheFile(heccedFile, heccedData);

            Path heccerFilePath = outputFolderPath.resolve("heccer.js");

            if (Files.exists(heccerFilePath)) {
                Files.move(heccerFilePath, tempDir.resolve(heccerFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                //Files.deleteIfExists(heccerFilePath);
            }

            Path heccerFile = Files.createFile(heccerFilePath);

            //File heccerFile = new File(outputFolderPath.concat("heccer.js"));
            writeTheFile(heccerFile, TextAssetReader.getHECCER());


            Path showdownFilePath = outputFolderPath.resolve("showdown.min.js");

            if (Files.exists(showdownFilePath)) {
                Files.move(showdownFilePath, tempDir.resolve(showdownFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                //Files.deleteIfExists(showdownFilePath);
            }

            Path showdownFile = Files.createFile(showdownFilePath);

            //File showdownFile = new File(outputFolderPath.concat("showdown.min.js"));
            writeTheFile(showdownFile, TextAssetReader.getShowdownMinJs());

            Path indexFilePath = outputFolderPath.resolve("index.html");

            if (Files.exists(indexFilePath)) {
                Files.move(indexFilePath, tempDir.resolve(indexFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                //Files.deleteIfExists(indexFilePath);
            }

            Path indexFile = Files.createFile(indexFilePath);

            writeIndexButWithMetadata(metadata, indexFile);

            Path iFictionFilePath = outputFolderPath.resolve("metadata.ifiction");

            if (Files.exists(iFictionFilePath)) {
                Files.move(iFictionFilePath, tempDir.resolve(iFictionFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                Files.deleteIfExists(iFictionFilePath);
            }

            Path iFictionFile = Files.createFile(iFictionFilePath);

            writeIFictionFile(metadata, iFictionFile);

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
     * @deprecated now using the java.nio.Files stuff instead.
     */
    @Deprecated
    private File makeTheFile(String filename) throws SecurityException, IOException {
        File f = new File(outputFolderPath.toString().concat(filename));
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
    @Deprecated
    private void writeTheFile(File f, List<String> dataToWrite) throws SecurityException, IOException {
        FileWriter heccedFileWriter = new FileWriter(f);
        for (String s : dataToWrite) {
            heccedFileWriter.write(s);
        }
        heccedFileWriter.close();
    }

    /**
     * This is a generic method for writing a Path (p)
     *
     * @param p           the Path (which has been created) to be written to
     * @param dataToWrite all the data to be written to path p
     * @throws SecurityException if there's a security problem preventing this from working
     * @throws IOException       if there's another IO problem
     */
    private void writeTheFile(Path p, List<String> dataToWrite) throws SecurityException, IOException {
        BufferedWriter heccedFileWriter = Files.newBufferedWriter(p);
        for (String s : dataToWrite) {
            heccedFileWriter.write(s);
        }
        heccedFileWriter.close();
    }


    /**
     * This writes the index.html file, and uses the given Metadata object to help out
     *
     * @param metadata the metadata object that data is being obtained from
     * @throws SecurityException if there's a security problem preventing the file from being made
     * @throws IOException       if there's another IO problem
     */
    @Deprecated
    private void writeIndexButWithMetadata(FolderOutputterMetadataInterface metadata) throws SecurityException, IOException{

        List<String> indexData = TextAssetReader.getIndex();
        //File f = new File(outputFolderPath.concat("index.html"));
        //f.createNewFile();
        //File f = makeTheFile("index.html");

        FileWriter indexFileWriter = new FileWriter(makeTheFile("index.html"));
        for (String s : indexData) {
            if (s.equals("<!-- METADATA GOES HERE -->\n")) {
                indexFileWriter.write(metadata.getIfidButHtmlFormatted());
            } else {
                indexFileWriter.write(s);
            }
        }
        indexFileWriter.close();
    }

    /**
     * This writes the index.html file, and uses the given Metadata object to help out
     *
     * @param metadata  the metadata object that data is being obtained from
     * @param indexPath the path for the file being read.
     * @throws SecurityException if there's a security problem preventing the file from being made
     * @throws IOException       if there's another IO problem
     */
    private void writeIndexButWithMetadata(FolderOutputterMetadataInterface metadata, Path indexPath) throws SecurityException, IOException {

        List<String> indexData = TextAssetReader.getIndex();
        //File f = new File(outputFolderPath.concat("index.html"));
        //f.createNewFile();
        //File f = makeTheFile("index.html");

        BufferedWriter indexFileWriter = Files.newBufferedWriter(indexPath);
        for (String s : indexData) {
            if (s.equals("<!-- METADATA GOES HERE -->\n")) {
                indexFileWriter.write(metadata.getIfidButHtmlFormatted());
            } else {
                indexFileWriter.write(s);
            }
        }
        indexFileWriter.close();
    }

    /**
     * This writes the metadata.iFiction file, using data held in the metadata object
     *
     * @param metadata the metadata object that data is being obtained from
     * @throws SecurityException if there's a security problem preventing the file from being made
     * @throws IOException       if there's another IO problem
     */
    @Deprecated
    private void writeIFictionFile(FolderOutputterMetadataInterface metadata) throws SecurityException, IOException {
        FileWriter iFictionFileWriter = new FileWriter(makeTheFile("metadata.iFiction"));
        iFictionFileWriter.write(metadata.getIFictionMetadata());
        iFictionFileWriter.close();
    }

    /**
     * This writes the metadata.iFiction file, using data held in the metadata object
     *
     * @param metadata the metadata object that data is being obtained from
     * @param thePath  the path for the iFiction file
     * @throws SecurityException if there's a security problem preventing the file from being made
     * @throws IOException       if there's another IO problem
     */
    private void writeIFictionFile(FolderOutputterMetadataInterface metadata, Path thePath) throws SecurityException, IOException {
        BufferedWriter iFictionFileWriter = Files.newBufferedWriter(thePath);

        //FileWriter iFictionFileWriter = new FileWriter(makeTheFile("metadata.iFiction"));
        iFictionFileWriter.write(metadata.getIFictionMetadata());
        iFictionFileWriter.close();
    }

}
