package oh_hecc;

import heccCeptions.DuplicatePassageNameException;
import heccCeptions.InvalidPassageNameException;
import org.junit.jupiter.api.Test;
import utilities.Vector2D;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;


public class EditablePassageTest {

    //testing to see if the setup stuff works
    @Test
    void testASamplePassage(){
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

    @Test
    void validRenamePassageTest(){
        Set<String> otherPassages = new TreeSet<String>(Arrays.asList("Start","oldPassage","eecks dee","sample","Another placeholder name"));

        String[] valid = {"p1","dave","Another Passage","Deez-Nutz","_ayy-lmao_"};

        EditablePassage p1 = new EditablePassage();

        for (String s: valid) {
            assertDoesNotThrow(
                    () -> Parseable.validatePassageNameRegex(s),
                    s + " check threw exception!"
            );
            assertDoesNotThrow(
                    () -> p1.renameThisPassage(s, otherPassages),
                    s + " update threw exception!"
            );
            equalsTest(s,p1.getPassageName());
        }

    }

    @Test
    public void testInvalidNamesThrowException(){
        Set<String> otherPassages = new TreeSet<String>(Arrays.asList("Start","oldPassage","eecks dee","sample","Another placeholder name"));

        String[] invalidPassages = {"","-xd-","nice meme!","p","0w0~"};

        EditablePassage p1 = new EditablePassage();

        for (String s: invalidPassages) {
            assertThrows(
                    InvalidPassageNameException.class,
                    () -> Parseable.validatePassageNameRegex(s),
                    s + " check didn't throw exception!"
            );
            assertThrows(
                    InvalidPassageNameException.class,
                    () -> p1.renameThisPassage(s, otherPassages),
                    s + " update didn't throw exception!"
            );
        }

    }

    @Test
    void testDuplicatePassageRename(){
        String[] names = {"Start","oldPassage","eecks dee","sample","Another placeholder name"};
        Set<String> otherPassages = new TreeSet<String>(Arrays.asList(names));

        EditablePassage p1 = new EditablePassage();
        for (String s: names) {
            assertThrows(
                    DuplicatePassageNameException.class,
                    () -> p1.renameThisPassage(s, otherPassages),
                    s + " update didn't throw exception!"
            );
        }
    }
    
    @Test
    void testPassageNameUpdate(){

    }


}
