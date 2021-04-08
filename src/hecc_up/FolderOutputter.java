package hecc_up;

import utilities.TextAssetReader;

import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * This class is responsible for outputting the HECCIN' Game into the folder it's supposed to be output into.
 */
public class FolderOutputter {


    /**
     * the filepath designating where to output stuff to
     */
    private final Path outputFolderPath; //where to output stuff to


    /**
     * whether or not the output folder exists
     */
    private boolean outputFolderExists; //whether or not the output folder exists

    /**
     * This is the FolderOutputter constructor
     *
     * @param outPath filepath for the output folder
     * @throws SecurityException if the output folder could not be made
     * @throws IOException       if the output folder could not be made
     */
    public FolderOutputter(Path outPath) throws SecurityException, IOException {
        //attempts to create the necessary directories for the output folder.
        outputFolderPath = Files.createDirectories(outPath);
        outputFolderExists = true;
    }

    /**
     * Working out whether or not the output folder exists
     *
     * @return whether or not the output folder exists
     */
    public boolean doesOutputFolderExist() {
        return outputFolderExists;
    }


    /**
     * This outputs the HECCIN Game, using the passed heccedData and the passed Metadata object
     * @param heccedData the hecced data to be put in hecced.js
     * @param metadata the metadata object to be used in a few places (via the outputterMetadata interface)
     * @return true if everything executes successfully
     * @throws SecurityException if there's a security problem preventing stuff from being output
     * @throws IOException if there's another input/output problem
     */
    public boolean outputTheGameWithMetadata(List<String> heccedData, FolderOutputterMetadataInterface metadata) throws
            SecurityException, IOException {
        if (outputFolderExists) {
            Path tempDir = Files.createTempDirectory("oldFiles");

            Path heccedFilePath = outputFolderPath.resolve("hecced.js");


            if (Files.exists(heccedFilePath)) {
                Files.move(heccedFilePath, tempDir.resolve(heccedFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            }

            Path heccedFile = Files.createFile(heccedFilePath);
            writeTheFile(heccedFile, heccedData);

            Path heccerFilePath = outputFolderPath.resolve("heccer.js");

            if (Files.exists(heccerFilePath)) {
                Files.move(heccerFilePath, tempDir.resolve(heccerFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            }

            Path heccerFile = Files.createFile(heccerFilePath);

            writeTheFile(heccerFile, TextAssetReader.getHECCER());


            Path showdownFilePath = outputFolderPath.resolve("showdown.min.js");

            if (Files.exists(showdownFilePath)) {
                Files.move(showdownFilePath, tempDir.resolve(showdownFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            }

            Path showdownFile = Files.createFile(showdownFilePath);

            writeTheFile(showdownFile, TextAssetReader.getShowdownMinJs());

            Path indexFilePath = outputFolderPath.resolve("index.html");

            if (Files.exists(indexFilePath)) {
                Files.move(indexFilePath, tempDir.resolve(indexFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
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
     * This is a generic method for writing a string to a file designated by a Path (p)
     *
     * @param p                  the Path to the file (which has been created) to be written to
     * @param s                  a string with all the data to be written to path p
     * @throws SecurityException if there's a security problem preventing this from working
     * @throws IOException       if there's another IO problem
     */
    private void writeTheFile(Path p, String s) throws SecurityException, IOException {
        BufferedWriter heccedFileWriter = Files.newBufferedWriter(p);
        heccedFileWriter.write(s);
        heccedFileWriter.close();
    }

    /**
     * This writes the index.html file, and uses the given Metadata object to help out
     *
     * @param metadata  the metadata object that data is being obtained from
     * @param indexPath the path for the file being read.
     * @throws SecurityException if there's a security problem preventing the file from being made
     * @throws IOException       if there's another IO problem
     */
    private void writeIndexButWithMetadata(FolderOutputterMetadataInterface metadata, Path indexPath) throws
            SecurityException, IOException {

        //List<String> indexData = TextAssetReader.getIndex();
        BufferedWriter indexFileWriter = Files.newBufferedWriter(indexPath);

        String theIndexString = TextAssetReader.getIndex();

        //puts the IFID HTML stuff into the index string.
        theIndexString = theIndexString.replace(
                "<!-- METADATA GOES HERE -->",
                metadata.getIfidButHtmlFormatted()
        );

        //replaces the <title> tag in the index.
        theIndexString = theIndexString.replace(
                "<title>HECCIN Game</title>",
                "<title>" + metadata.getTitle() + "</title>"
        );

        indexFileWriter.write(theIndexString);

        indexFileWriter.close();
    }

    /**
     * This writes the metadata.iFiction file, using data held in the metadata object
     *
     * @param metadata the metadata object that data is being obtained from
     * @param thePath  the path for the iFiction file
     * @throws SecurityException if there's a security problem preventing the file from being made
     * @throws IOException       if there's another IO problem
     */
    private void writeIFictionFile(FolderOutputterMetadataInterface metadata, Path thePath) throws
            SecurityException, IOException {
        BufferedWriter iFictionFileWriter = Files.newBufferedWriter(thePath);
        iFictionFileWriter.write(metadata.getIFictionMetadata());
        iFictionFileWriter.close();
    }

}
