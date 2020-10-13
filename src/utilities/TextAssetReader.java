package utilities;

import java.io.*;
import java.util.ArrayList;

public class TextAssetReader {

    private final static String path = "/assets/textAssets/";

    //heccer.js as an Arraylist of strings
    private final static ArrayList<String> HECCER = fileToStaticStringArrayList("heccer.js");
    //index.html as an ArrayList of strings
    private final static ArrayList<String> INDEX = fileToStaticStringArrayList("index.html");


    //iFictionTemplate.iFiction as an ArrayList of strings
    private final static ArrayList<String> IFICTION_TEMPLATE = fileToStaticStringArrayList("iFictionTemplate.iFiction");


    //HeccSample.hecc as a string
    private final static String STATIC_HECC_STRING = fileToStaticString("HeccSample.hecc");

    //This reads the files held in src/assets/textAssets, in a way which allows them to be included in a .jar file
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

    //this is used by the GUI to read the HECC file that the user is trying to HECC-UP (or trying to OH-HECC)
    public static String fileToString(String fileLocation) throws IOException{
        StringBuilder output = new StringBuilder();
        //try{
            FileReader fr = new FileReader(fileLocation);
            BufferedReader br = new BufferedReader(fr);
            String currentString;
            while ((currentString = br.readLine())!=null) {
                output.append(currentString.concat("\n"));
            }
            br.close();
        //} catch (IOException e){
            //e.printStackTrace();
        //}
        return output.toString();
    }


    public static String getHeccString(){ return STATIC_HECC_STRING; }

    public static ArrayList<String> getHECCER() {return HECCER;}
    public static ArrayList<String> getIndex() {return INDEX;}

    public static ArrayList<String> getIFictionTemplate(){ return IFICTION_TEMPLATE; }
}
