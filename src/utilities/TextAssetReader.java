package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class that can read text stuff
 *
 * @author Rachel Lowe
 */
public class TextAssetReader {

    /**
     * where the static textAssets are stored (src/assets/textAssets)
     */
    private final static String path = "/assets/textAssets/";

    /**
     * heccer.js as a string
     */
    private final static String HECCER = fileToStaticString("heccer.js");


    /**
     * index.html as a String
     */
    private final static String INDEX = fileToStaticString("index.html");

    /**
     * showdown.min.js as a string
     */
    private final static String SHOWDOWN_MIN_JS = fileToStaticString("showdown.min.js");


    /**
     *  This reads the files held in src/assets/textAssets, in a way which allows them to be included in a .jar file,
     *  in the form of a static string.
     * @param filename the name of the file to read
     * @return the contents of the file, as a string
     */
    private static String fileToStaticString(String filename){
        final StringBuilder output = new StringBuilder();
        try{
            final InputStream in = TextAssetReader.class.getResourceAsStream(path + filename);
            assert in != null;
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String currentString;
            while ((currentString = br.readLine())!=null) {
                output.append(currentString.concat("\n"));
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return output.toString();
    }

    /**
     * This is use by the GUI/OH-HECC to read the user-specified HECC file as a string
     * @param fileLocation where the file is on the user's computer
     * @return the file contents as a single string
     * @throws IOException if the file couldn't be read
     */
    public static String fileToString(String fileLocation) throws IOException{
        return String.join("\n", Files.readAllLines(Paths.get(fileLocation)));
    }



    /**
     * gets the static String representation of the heccer.js file
     * @return the static String representation of the heccer.js file
     */
    public static String getHECCER() {return HECCER;}
    /**
     * gets the static String representation of the index.html file
     * @return the static String representation of the index.html file
     */
    public static String getIndex() {return INDEX;}


    /**
     * gets the showdown.min.js file contents
     * @return the showdown.min.js file contents
     */
    public static String getShowdownMinJs(){ return SHOWDOWN_MIN_JS; }

}
