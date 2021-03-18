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
 */
public class TextAssetReader {

    /**
     * where the static textAssets are stored (src/assets/textAssets)
     */
    private final static String path = "/assets/textAssets/";

    /**
     * heccer.js as an Arraylist of strings
     */
    private final static List<String> HECCER = fileToStaticStringList("heccer.js");

    /**
     * heccer.js as a string
     */
    public final static String HECCER_STRING = fileToStaticString("heccer.js");

    /**
     * index.html as an ArrayList of strings
     */
    private final static List<String> INDEX = fileToStaticStringList("index.html");

    /**
     * index.html as a String
     */
    public final static String INDEX_STRING = fileToStaticString("index.html");

    /**
     * iFictionTemplate.iFiction as an ArrayList of strings
     */
    private final static List<String> IFICTION_TEMPLATE = fileToStaticStringList("iFictionTemplate.iFiction");

    /**
     * HeccSample.hecc as a string
     */
    private final static String STATIC_HECC_STRING = fileToStaticString("HeccSample.hecc");

    /**
     * showdown.min.js as a string
     */
    private final static String SHOWDOWN_MIN_JS = fileToStaticString("showdown.min.js");

    /**
     * This reads the files held in src/assets/textAssets, in a way which allows them to be included in a .jar file
     * @param filename the name of the file
     * @return the contents of the file, as an ArrayList of Strings
     */
    private static List<String> fileToStaticStringList(String filename){
        List<String> output = new ArrayList<>();
        try{
            InputStream in = TextAssetReader.class.getResourceAsStream(path + filename);
            //This allows the specified text asset file to be packaged within the .jar ;)
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String currentString;
            //pretty much setting up the stuff for reading the file
            //until the end of the file is reached, it will add the current string to the file (even the empty ones)
            while ((currentString = br.readLine())!=null) {
                output.add(currentString.concat("\n"));
            }
            br.close(); //closes the bufferedReader
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    //this is/was used to read 'HeccSample.hecc' in src/assets/textAssets.
    /**
     * Like fileToStaticStringArrayList, but returns the file as a single String instead
     * @param filename the name of the file to read
     * @return the contents of the file, as a string
     */
    private static String fileToStaticString(String filename){
        StringBuilder output = new StringBuilder();
        try{
            InputStream in = TextAssetReader.class.getResourceAsStream(path + filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
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
     * @return the STATIC_HECC_STRING
     */
    public static String getHeccString(){ return STATIC_HECC_STRING; }

    /**
     * gets the static ArrayList<String> representation of the heccer.js file
     * @return the static ArrayList<String> representation of the heccer.js file
     */
    public static List<String> getHECCER() {return HECCER;}
    /**
     * gets the static ArrayList<String> representation of the index.html file
     * @return the static ArrayList<String> representation of the index.html file
     */
    public static List<String> getIndex() {return INDEX;}

    /**
     * gets the static ArrayList<String> representation of the iFictionTemplate.iFiction file
     * @return the static ArrayList<String> representation of the iFictionTemplate.iFiction file
     */
    public static List<String> getIFictionTemplate(){ return IFICTION_TEMPLATE; }


    /**
     * gets the showdown.min.js file contents
     * @return the showdown.min.js file contents
     */
    public static String getShowdownMinJs(){ return SHOWDOWN_MIN_JS; }

}
