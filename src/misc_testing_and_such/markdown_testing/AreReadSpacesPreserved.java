package misc_testing_and_such.markdown_testing;

import utilities.TextAssetReader;

public class AreReadSpacesPreserved {

    //just trying to see if TextAssetReader.fileToString() preserves trailing spaces at the end of lines

    //EDIT: no, they are not.

    public static void main(String[] args){
        try {
            String readHecc = TextAssetReader.fileToString("inputs/HeccSample.hecc");
            readHecc = readHecc.replaceAll(" ","%");
            System.out.println(readHecc);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
