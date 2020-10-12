package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TextAssetReader {

    private final static String path = "/assets/textAssets/";

    private final static ArrayList<String> HECCER = fileToStringArrayList("heccer.js");
    private final static ArrayList<String> INDEX = fileToStringArrayList("index.html");

    //private final static ArrayList<String> HECC_ARRAYLIST = fileToStringArrayList("HeccSample.hecc");
    private final static String STATIC_HECC_STRING = fileToString("HeccSample.hecc");

    private final static ArrayList<String> IFICTION_TEMPLATE = fileToStringArrayList("iFictionTemplate.iFiction");

    private static ArrayList<String> fileToStringArrayList(String filename){

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

    private static String fileToString(String filename){
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

    public static String getHeccString(){ return STATIC_HECC_STRING; }

    public static ArrayList<String> getHECCER() {return HECCER;}
    public static ArrayList<String> getIndex() {return INDEX;}

    public static ArrayList<String> getIFictionTemplate(){ return IFICTION_TEMPLATE; }
}
