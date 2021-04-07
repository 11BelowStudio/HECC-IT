package oh_hecc.game_parts.passage;

import utilities.Vector2D;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This encapsulates all the passage functionality that's exclusively used for when passages are being read
 * from .hecc format into actual usable objects.
 */
public interface PassageReadingInterface extends SharedPassage {


    /**
     * This reads the tag metadata stuff
     * @param lineEndMetadata The full line end metadata
     * @return an ArrayList<String> containing all the tag metadata
     */
    static ArrayList<String> readTagMetadata(String lineEndMetadata){
        ArrayList<String> tagList = new ArrayList<>();
        /*
            this processes the list of tags (if they exist)
            tags may be alphanumeric with underscores, divided by spaces, must be within []
            like '[List of tags divided by spaces and allows d1gits plus under_scores]'

            will also accept `[]` as a valid 'tag list' (but it'll be empty)
         */
        Matcher tagListMatcher = Pattern.compile("\\[("+TAG_STRING_REGEX+")?]").matcher(lineEndMetadata);
        //finds the tag list metadata
        if (tagListMatcher.find()){ //if found
            String tagListString = tagListMatcher.group(0); //gets it
            tagListString = tagListString.substring(1,tagListString.length()-1); //removes surrounding []
            tagList = SharedPassage.actuallyPutValidTagListStringIntoArrayList(tagListString); // and makes it a list.
        }
        return tagList; //return the tagList
    }


    /**
     * This reads the position Vector2D metadata
     * @param lineEndMetadata the full line end metadata stuff
     * @return A Vector2D for the OH-HECC position of this passage
     */
    static Vector2D readVectorMetadata(String lineEndMetadata){
        Vector2D readVector = new Vector2D();
        /*
        Searches for it in the form
            <x,y>
        x and y are double numbers, may be negative, may have decimal point, and can have leading/trailing whitespace
         */
        Matcher vectorCoordsMatcher = Pattern.compile(
                "<\\h*-?\\h*\\d*\\.?\\d+\\h*,\\h*-?\\h*\\d*\\.?\\d+\\h*>"
        ).matcher(lineEndMetadata);
        if (vectorCoordsMatcher.find()){
            //if it's found, it extracts that string
            String vectorCoordsString = vectorCoordsMatcher.group(0);
            //trims the trailing/leading '<''>' characters
            vectorCoordsString = vectorCoordsString.substring(1,vectorCoordsString.length()-1);
            //converts it into an array, splitting at the comma
            String[] vectorCoordsArray = vectorCoordsString.split(",");
            if(vectorCoordsArray.length > 1){ //if there's 2 (or more) indexes in the array
                int i = 0; //counter thing
                double[] coords = {0,0}; //2d array of doubles to store the coordinate doubles
                while (i < 2){
                    try{
                        //puts the double value of the string in the current index of the array into coords[i]
                        coords[i] = Double.parseDouble(vectorCoordsArray[i]);
                    } catch (Exception e){
                        //sets coords[i] to 0 if no double could be found
                        coords[i] = 0;
                    }
                    i++;
                }
                //sets the coordinates of the readVector accordingly
                readVector.set(coords[0],coords[1]);
            }
        }
        return readVector;
    }

    /**
     * Obtains the inline comment from the end of the line end metadata
     * @param lineEndMetadata the line end metadata
     * @return The comment of it (everything between a // and the end of the line).
     * returns empty string if no comment is found.
     */
    static String getInlineComment(String lineEndMetadata){
        Matcher inlineCommentMatcher = Pattern.compile(
                "((?<=\\/\\/).*)"
        ).matcher(lineEndMetadata); //matches everything between the first // and the end of the line
        String theComment = ""; //blank comment by default
        if (inlineCommentMatcher.find()){
            theComment = inlineCommentMatcher.group(0).trim(); //obtains the comment (and also trims it) if it exists
            //System.out.println(theComment);
        }
        return theComment; //returns the comment
    }
}
