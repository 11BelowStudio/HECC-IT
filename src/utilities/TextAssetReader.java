package utilities;

import java.io.*;
import java.util.ArrayList;

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
    private final static ArrayList<String> HECCER = fileToStaticStringArrayList("heccer.js");
    /**
     * index.html as an ArrayList of strings
     */
    private final static ArrayList<String> INDEX = fileToStaticStringArrayList("index.html");

    /**
     * iFictionTemplate.iFiction as an ArrayList of strings
     */
    private final static ArrayList<String> IFICTION_TEMPLATE = fileToStaticStringArrayList("iFictionTemplate.iFiction");

    /**
     * HeccSample.hecc as a string
     */
    private final static String STATIC_HECC_STRING = fileToStaticString("HeccSample.hecc");

    /**
     * This reads the files held in src/assets/textAssets, in a way which allows them to be included in a .jar file
     * @param filename the name of the file
     * @return the contents of the file, as an ArrayList of Strings
     */
    private static ArrayList<String> fileToStaticStringArrayList(String filename){
        //if it wasn't obvious, yes, this was unceremoniously borrowed from my CE218 stuff
        ArrayList<String> output = new ArrayList<>();
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

    /**
     * Like fileToStaticStringArrayList, but returns the file as a single String instead
     * @param filename the name of the file to read
     * @return the contents of the file, as a strin
     */
    //this is/was used to read 'HeccSample.hecc' in src/assets/textAssets.
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
        StringBuilder output = new StringBuilder();
        FileReader fr = new FileReader(fileLocation);
        BufferedReader br = new BufferedReader(fr);
        String currentString;
        while ((currentString = br.readLine())!=null) {
            output.append(currentString.concat("\n"));
        }
        br.close();
        return output.toString();
    }


    /**
     * @return the STATIC_HECC_STRING
     */
    public static String getHeccString(){ return STATIC_HECC_STRING; }

    /**
     * gets the static ArrayList<String> representation of the heccer.js file
     * @return the static ArrayList<String> representation of the heccer.js file
     */
    public static ArrayList<String> getHECCER() {return HECCER;}
    /**
     * gets the static ArrayList<String> representation of the index.html file
     * @return the static ArrayList<String> representation of the index.html file
     */
    public static ArrayList<String> getIndex() {return INDEX;}

    /**
     * gets the static ArrayList<String> representation of the iFictionTemplate.iFiction file
     * @return the static ArrayList<String> representation of the iFictionTemplate.iFiction file
     */
    public static ArrayList<String> getIFictionTemplate(){ return IFICTION_TEMPLATE; }
}
