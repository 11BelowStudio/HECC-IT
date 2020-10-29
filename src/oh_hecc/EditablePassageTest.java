package oh_hecc;

import org.junit.jupiter.api.Test;
import utilities.Vector2D;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditablePassageTest {

    //testing to see if the setup stuff works
    @Test
    public void testASamplePassage(){
        String name = "Another passage";
        String content = "congrats you clicked that link to get here, Another passage.\nDo you want to go [[Left]], [[Right]], [[Back to the start|Start]], or [[Skip this nonsense|dave]]?";
        String comment = "this is an comment\nbottom text";
        String lineEndMetadata = "[yes theres tags] < 34.5 ,35> //this is another passage";
        List<String> expectedTags = Arrays.asList("yes","theres","tags");
        Vector2D expectedPos = new Vector2D(34.5,35);
        String expectedInlineComment = "this is another passage";
        Set<String> expectedLinks = new TreeSet<>(Arrays.asList("Left", "Right", "Start", "dave"));
        EditablePassage samplePassage = new EditablePassage(
                name,
                content,
                comment,
                lineEndMetadata
        );


        equalsTest(name, samplePassage.getPassageName());
        equalsTest(content, samplePassage.getPassageContent());
        equalsTest(comment, samplePassage.getTrailingComment());
        equalsTest(expectedInlineComment, samplePassage.getInlinePassageComment());

        equalsTest(expectedPos, samplePassage.getPosition());
        equalsTest(samplePassage.getPassageTags(), expectedTags);
        equalsTest(samplePassage.getLinkedPassages(), expectedLinks);

        System.out.println(samplePassage.toHecc());
    }

    private static void equalsTest(Object expected, Object actual){
        assertEquals(expected, actual, "Expected:\n" + expected.toString() + "\nGot:\n" + actual.toString());
    }

    //tests if the link renaming stuff works (and also finding the set of linked passage names)
    @Test
    void renamingLinkTest(){
        String inputContent = "[[old]] [[bob]] [[deez|old]] [[nutz|bob]] [[old|old]] [[old|bob]] [[ old ]] [[ bruh | old ]]";
        String expected = "[[new]] [[bob]] [[deez|new]] [[nutz|bob]] [[old|new]] [[old|bob]] [[new]] [[ bruh |new]]";


        String output = EditablePassage.getPassageContentWithRenamedLinks(inputContent,"old","new");

        equalsTest(expected, output);
        System.out.println(output);

        Set<String> expectedLinks = new TreeSet<>(Arrays.asList("new","bob"));
        Set<String> actualLinks = EditablePassage.findLinks(output);

        equalsTest(expectedLinks, actualLinks);

    }


}
