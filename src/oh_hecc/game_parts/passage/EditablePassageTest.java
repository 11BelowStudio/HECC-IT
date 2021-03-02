package oh_hecc.game_parts.passage;

import heccCeptions.DuplicatePassageNameException;
import heccCeptions.InvalidPassageNameException;
import oh_hecc.Parseable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.Vector2D;

import java.util.*;

import static oh_hecc.game_parts.passage.PassageEditingInterface.getPassageContentWithRenamedLinks;
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


        String output = getPassageContentWithRenamedLinks(inputContent,"old","new");

        System.out.println(inputContent);
        equalsTest(expected, output);
        System.out.println(output);

        Set<String> expectedLinks = new TreeSet<>(Arrays.asList("new","bob"));
        Set<String> actualLinks = SharedPassage.findLinks(output);

        equalsTest(expectedLinks, actualLinks);

    }

    @Test
    void validRenamePassageTest(){
        Set<String> otherPassages = new TreeSet<String>(Arrays.asList("Start","oldPassage","eecks dee","sample","Another placeholder name"));

        String[] valid = {"p1","dave","Another Passage","Deez-Nutz","_ayy-lmao_"};

        EditablePassage p1 = new EditablePassage();

        for (String s: valid) {
            Assertions.assertDoesNotThrow(
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

        String[] invalidPassages = {"","-xd-","nice meme!","0w0~"};

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
        EditablePassage[] samples = {new EditablePassage("deez nutz", new Vector2D(0,0)), new EditablePassage("lmao gottem", "[[deez nutz]]","",""), new EditablePassage(), new EditablePassage()};

        Map<UUID, PassageEditingInterface> passages = new HashMap<>();

        for (PassageEditingInterface e: samples) {
            passages.put(e.getPassageUUID(),e);
        }
        for (PassageEditingInterface e: passages.values()){
            e.updateLinkedUUIDs(passages);
        }
        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().outputAsStringForDebuggingReasons());
        }

        PassageEditingInterface editThis = passages.get(samples[0].getPassageUUID());

        System.out.println("test 1");

        assertDoesNotThrow( () -> editThis.renameThisPassage("nice name", passages));
        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getValue().outputAsStringForDebuggingReasons());
        }

        System.out.println("test 2");

        assertDoesNotThrow( () -> editThis.renameThisPassage("deez nutz", passages));
        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getValue().outputAsStringForDebuggingReasons());
        }

        System.out.println("test 3");

        /*
        try {
            editThis.renameThisPassage("lmao gottem", passages);
        } catch (Exception e){ e.printStackTrace();}*/
        assertThrows(DuplicatePassageNameException.class, () -> editThis.renameThisPassage("lmao gottem",passages) );
        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getValue().outputAsStringForDebuggingReasons());
        }

    }

    @Test
    void testPassageYeet() {
        EditablePassage[] samples = {new EditablePassage("deez nutz", new Vector2D(0,0)), new EditablePassage("lmao gottem", "[[deez nutz]]","",""), new EditablePassage(), new EditablePassage()};

        Map<UUID, PassageEditingInterface> passages = new HashMap<>();

        for (EditablePassage e: samples) {
            passages.put(e.getPassageUUID(),e);
        }
        for (PassageEditingInterface e: passages.values()){
            e.updateLinkedUUIDs(passages);
        }

        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().outputAsStringForDebuggingReasons());
        }

        System.out.println("\nyeet time\n");
        PassageEditingInterface yeetThis = passages.get(samples[0].getPassageUUID());

        yeetThis.deleteThisPassage(passages);

        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().outputAsStringForDebuggingReasons());
        }
        PassageEditingInterface testThis = passages.get(samples[1].getPassageUUID());
        assertNotEquals(testThis.getPassageContent(),"[[deez nutz]]");


    }

    @Test
    void indirectlyAddNewPassage(){
        EditablePassage samplePassage = new EditablePassage();

        String newPassageName = "dave";

        Map<UUID, PassageEditingInterface> passages = new HashMap<>();

        UUID referenceUUID = samplePassage.getPassageUUID();

        passages.put(referenceUUID,samplePassage);
        passages.get(referenceUUID).updateLinkedUUIDs(passages);

        System.out.println("before adding the new passage:\n");
        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().outputAsStringForDebuggingReasons());
        }

        passages.get(referenceUUID).updatePassageContent("[[dave]]", passages);

        //passages.get(referenceUUID).updateLinkedUUIDs(passages);

        System.out.println("after adding the new passage:\n");
        for (Map.Entry<UUID, PassageEditingInterface> e : passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().outputAsStringForDebuggingReasons());
        }

        assertEquals(2, passages.size());

    }

    @Test
    void testEscaping() {
        EditablePassage samplePassage = new EditablePassage();

        String inputString = "::\n;;\ndeez nutz::\nso;;how's::life?\n;;k\n\n::q\n;;::\n::;;\n;\n:";

        String expectedOutputString = "\\::\n\\;;\ndeez nutz::\nso;;how's::life?\n\\;;k\n\n\\::q\n\\;;::\n\\::;;\n;\n:";

        samplePassage.setPassageContent(inputString);

        samplePassage.setTrailingComment(inputString);

        assertEquals(expectedOutputString, samplePassage.getPassageContent());

        assertEquals(expectedOutputString, samplePassage.getTrailingComment());

    }


}
