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

/**
 * Some tests for the EditablePassage class
 */
public class EditablePassageTest {

    /**
     * testing to see if I can set up a sample passage
     */
    @Test
    void testASamplePassage(){
        final String name = "Another passage";
        final String content = "congrats you clicked that link to get here, Another passage.\nDo you want to go [[Left]], [[Right]], [[Back to the start|Start]], or [[Skip this nonsense|dave]]?";
        final String comment = "this is an comment\nbottom text";
        final String lineEndMetadata = "[yes theres tags] < 34.5 ,35> //this is another passage";
        final List<String> expectedTags = Arrays.asList("yes","theres","tags");
        final Vector2D expectedPos = new Vector2D(34.5,35);
        final String expectedInlineComment = "this is another passage";
        final Set<String> expectedLinks = new TreeSet<>(Arrays.asList("Left", "Right", "Start", "dave"));
        final EditablePassage samplePassage = new EditablePassage(
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

    /**
     * just an equals test helper method (automatically handling the error message)
     * @param expected expected object
     * @param actual actual object
     */
    private static void equalsTest(Object expected, Object actual){
        assertEquals(expected, actual, "Expected:\n" + expected.toString() + "\nGot:\n" + actual.toString());
    }

    /**
     *  tests if the link renaming stuff works (and also finding the set of linked passage names)
     */
    @Test
    void renamingLinkTest(){

        // inputs + expected outputs
        final String inputContent = "[[old]] [[bob]] [[deez|old]] [[nutz|bob]] [[old|old]] [[old|bob]] [[ old ]] [[ bruh | old ]]";
        final String expected = "[[new]] [[bob]] [[deez|new]] [[nutz|bob]] [[old|new]] [[old|bob]] [[new]] [[ bruh |new]]";

        // renaming the link 'old' to be 'new' instead
        final String output = getPassageContentWithRenamedLinks(inputContent,"old","new");

        System.out.println(inputContent);
        equalsTest(expected, output);
        System.out.println(output);

        final Set<String> expectedLinks = new HashSet<>(Arrays.asList("new","bob"));
        final Set<String> actualLinks = SharedPassage.findLinks(output);

        equalsTest(expectedLinks, actualLinks);

    }

    /**
     * Testing renaming a passage with a valid name (and seeing if the links in the set of all passages update with it)
     */
    @Test
    void validRenamePassageTest(){

        final String[] theOtherPassageNames = {"Start","oldPassage","eecks dee","sample","Another placeholder name"};

        final HashMap<UUID, PassageEditingInterface> allPassages = new HashMap<>();

        final String placeholderContent = "[[%s]] [[text|%s]]";

        final String firstName = "kevin";
        final String initialContent = String.format(placeholderContent,firstName,firstName);

        for (String p: theOtherPassageNames){
            final PassageEditingInterface thisOne = new EditablePassage(p, initialContent);
            allPassages.put(thisOne.getPassageUUID(),thisOne);
        }

        final String[] valid = {"p1","dave","Another Passage","Deez-Nutz","_ayy-lmao_"};

        final PassageEditingInterface p1 = new EditablePassage(firstName, initialContent);

        final UUID p1UUID = p1.getPassageUUID();
        allPassages.put(p1UUID, p1);

        final Collection<PassageEditingInterface> allVals = allPassages.values();
        for (PassageEditingInterface ok: allVals){
            ok.updateLinkedUUIDs(allVals);
        }

        for (String s: valid) {

            Assertions.assertDoesNotThrow(
                    () -> Parseable.validatePassageNameRegex(s),
                    s + " check threw exception!"
            );
            assertDoesNotThrow(
                    () -> p1.renameThisPassage(s, allPassages),
                    s + " update threw exception!"
            );
            equalsTest(s,p1.getPassageName());
            final String updatedContent = String.format(placeholderContent, s, s);
            for (PassageEditingInterface pei: allPassages.values()){
                assertEquals(updatedContent, pei.getPassageContent());
                assertTrue(pei.getLinkedPassages().contains(s));
                assertTrue(pei.getLinkedPassageUUIDs().contains(p1UUID));
            }
        }

    }

    /**
     * Testing for the invalid passage names (should throw an exception upon attempting to rename them,
     * and links in their content should not be updated)
     */
    @Test
    public void testInvalidNamesThrowException(){


        final String[] theOtherPassageNames = {"Start","oldPassage","eecks dee","sample","Another placeholder name"};

        final HashMap<UUID, PassageEditingInterface> allPassages = new HashMap<>();

        final String placeholderContent = "[[%s]] [[text|%s]]";

        final String firstName = "kevin";
        final String theContent = String.format(placeholderContent,firstName,firstName);


        for (String p: theOtherPassageNames){
            final PassageEditingInterface thisOne = new EditablePassage(p, theContent);
            allPassages.put(thisOne.getPassageUUID(),thisOne);
        }

        final String[] invalid = {"","-xd-","nice meme!","0w0~", "chas & dave"};

        final PassageEditingInterface p1 = new EditablePassage(firstName, theContent);

        final UUID p1UUID = p1.getPassageUUID();
        allPassages.put(p1UUID, p1);


        final Collection<PassageEditingInterface> allVals = allPassages.values();
        for (PassageEditingInterface ok: allVals){
            ok.updateLinkedUUIDs(allVals);
        }


        for (String s: invalid) {
            assertThrows(
                    InvalidPassageNameException.class,
                    () -> Parseable.validatePassageNameRegex(s),
                    s + " check didn't throw exception!"
            );
            assertThrows(
                    InvalidPassageNameException.class,
                    () -> p1.renameThisPassage(s, allPassages),
                    s + " update didn't throw exception!"
            );
            assertEquals(firstName,p1.getPassageName());

            for (PassageEditingInterface pei: allPassages.values()){
                assertEquals(theContent, pei.getPassageContent());
                assertFalse(pei.getLinkedPassages().contains(s));
                assertTrue(pei.getLinkedPassages().contains(firstName));
                assertTrue(pei.getLinkedPassageUUIDs().contains(p1UUID));
            }
        }

    }

    /**
     * making sure that a rename operation does not happen if multiple passages share the same name
     */
    @Test
    void testDuplicatePassageRename(){


        final EditablePassage p1 = new EditablePassage("kevin");

        final String[] names = {"Start","oldPassage","eecks dee","sample","Another placeholder name"};

        final HashMap<UUID, PassageEditingInterface> allPassages = new HashMap<>();

        final UUID p1UUID = p1.getPassageUUID();
        allPassages.put(p1UUID, p1);

        for (String p: names){
            final PassageEditingInterface thisOne = new EditablePassage(p);
            allPassages.put(thisOne.getPassageUUID(),thisOne);
        }

        final Collection<PassageEditingInterface> allVals = allPassages.values();
        for (PassageEditingInterface ok: allVals){
            ok.updateLinkedUUIDs(allVals);
        }

        for (String s: names) {
            assertThrows(
                    DuplicatePassageNameException.class,
                    () -> p1.renameThisPassage(s, allPassages),
                    s + " update didn't throw exception!"
            );
        }
    }

    /**
     * Yet another test for the valid passage name updating
     */
    @Test
    void testPassageNameUpdate(){
        final EditablePassage[] samples = {
                new EditablePassage("deez nutz"),
                new EditablePassage("lmao gottem", "[[deez nutz]] [[text|deez nutz]]"),
                new EditablePassage(),
                new EditablePassage()
        };

        final Map<UUID, PassageEditingInterface> passages = new HashMap<>();

        for (PassageEditingInterface e: samples) {
            passages.put(e.getPassageUUID(),e);
        }

        final Collection<PassageEditingInterface> allVals = passages.values();
        for (PassageEditingInterface e: allVals){
            e.updateLinkedUUIDs(allVals);
        }
        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().getAsStringForDebuggingReasons());
        }

        PassageEditingInterface editThis = passages.get(samples[0].getPassageUUID());

        System.out.println("test 1");

        assertDoesNotThrow(
                () -> editThis.renameThisPassage("nice name", passages)
        );
        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getValue().getAsStringForDebuggingReasons());
        }

        System.out.println("test 2");

        assertDoesNotThrow(
                () -> editThis.renameThisPassage("deez nutz", passages)
        );
        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getValue().getAsStringForDebuggingReasons());
        }

        System.out.println("test 3");


        assertThrows(
                DuplicatePassageNameException.class,
                () -> editThis.renameThisPassage("lmao gottem",passages)
        );
        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getValue().getAsStringForDebuggingReasons());
        }

    }

    /**
     * Testing what happens if we delete a passage (all existing links to it should have the '! WAS DELETED !' suffix appended to them)
     */
    @Test
    void testPassageYeet() {
        final EditablePassage[] samples = {
                new EditablePassage("deez nutz"),
                new EditablePassage("lmao gottem", "[[deez nutz]] [[text|deez nutz]]"),
                new EditablePassage(),
                new EditablePassage()
        };

        final Map<UUID, PassageEditingInterface> passages = new HashMap<>();

        for (EditablePassage e: samples) {
            passages.put(e.getPassageUUID(),e);
        }
        final Collection<PassageEditingInterface> allVals = passages.values();
        for (PassageEditingInterface e: allVals){
            e.updateLinkedUUIDs(allVals);
        }

        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().getAsStringForDebuggingReasons());
        }

        System.out.println("\nyeet time\n");
        final PassageEditingInterface yeetThis = passages.get(samples[0].getPassageUUID());

        yeetThis.deleteThisPassage(passages);

        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().getAsStringForDebuggingReasons());
        }
        final PassageEditingInterface testThis = passages.get(samples[1].getPassageUUID());
        assertNotEquals(
                "[[deez nutz]] [[text|deez nutz]]",
                testThis.getPassageContent()
        );

        assertEquals(
                "[[deez nutz! WAS DELETED !]] [[text|deez nutz! WAS DELETED !]]",
                testThis.getPassageContent()
        );

    }

    /**
     * Making sure a passage is added if we add a link to a passage that doesn't exist yet to the PassageMap.
     */
    @Test
    void indirectlyAddNewPassage(){
        final EditablePassage samplePassage = new EditablePassage();

        final Map<UUID, PassageEditingInterface> passages = new HashMap<>();

        final UUID referenceUUID = samplePassage.getPassageUUID();

        passages.put(referenceUUID,samplePassage);
        passages.get(referenceUUID).updateLinkedUUIDs(passages.values());

        System.out.println("before adding the new passage:\n");
        for (Map.Entry<UUID, PassageEditingInterface> e: passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().getAsStringForDebuggingReasons());
        }

        passages.get(referenceUUID).updatePassageContent("[[dave]]", passages);

        //passages.get(referenceUUID).updateLinkedUUIDs(passages);

        System.out.println("after adding the new passage:\n");
        for (Map.Entry<UUID, PassageEditingInterface> e : passages.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue().getAsStringForDebuggingReasons());
        }

        assertEquals(2, passages.size());

    }

    /**
     * Testing the automatic escaping of :: lines and ;; lines (and ensuring there's no unintended escaping)
     */
    @Test
    void testEscaping() {
        final EditablePassage samplePassage = new EditablePassage();

        final String inputString = "::\n;;\ndeez nutz::\nso;;how's::life?\n;;k\n\n::q\n;;::\n::;;\n;\n:";

        final String expectedOutputString = "\\::\n\\;;\ndeez nutz::\nso;;how's::life?\n\\;;k\n\n\\::q\n\\;;::\n\\::;;\n;\n:";

        samplePassage.setPassageContent(inputString);

        samplePassage.setTrailingComment(inputString);

        assertEquals(expectedOutputString, samplePassage.getPassageContent());

        assertEquals(expectedOutputString, samplePassage.getTrailingComment());

    }

    /**
     * making sure the line end metadata works as expected
     */
    @Test
    void testLineEndMetadata(){

        final String lineEndData = "[yes theres tags noreturn] <69,420> // ayy lmao";

        final PassageEditingInterface metadataPassage = new EditablePassage("dave","content","",lineEndData);

        assertEquals(
                new Vector2D(69,420),
                metadataPassage.getPosition()
        );

        assertIterableEquals(
                Arrays.asList("yes", "theres", "tags", "noreturn"),
                metadataPassage.getPassageTags()
        );

        assertEquals(
                "ayy lmao",
                metadataPassage.getInlinePassageComment()
        );

        assertTrue(
                metadataPassage.isThisAPointOfNoReturn()
        );



        final PassageEditingInterface noMetadata = new EditablePassage("bob","content","","");

        assertEquals(
                new Vector2D(0,0),
                noMetadata.getPosition()
        );

        assertIterableEquals(
                new ArrayList<String>(),
                noMetadata.getPassageTags()
        );

        assertEquals(
                "",
                noMetadata.getInlinePassageComment()
        );

        assertFalse(
                noMetadata.isThisAPointOfNoReturn()
        );

        final String otherMetadata = "<-45135.1251,24151.1> [some tags]";

        final PassageEditingInterface meta2 = new EditablePassage("bob3","k","",otherMetadata);

        assertEquals(
                new Vector2D(-45135.1251,24151.1),
                meta2.getPosition()
        );

        assertIterableEquals(
                Arrays.asList("some", "tags"),
                meta2.getPassageTags()
        );

        assertEquals(
                "",
                meta2.getInlinePassageComment()
        );

        assertFalse(
                meta2.isThisAPointOfNoReturn()
        );

        final String multilineMetadata = "\n[some tags] <53,124> // woah";

        final PassageEditingInterface plsDontRead = new EditablePassage("w","o","w",multilineMetadata);


        assertEquals(
                new Vector2D(0,0),
                plsDontRead.getPosition()
        );

        assertIterableEquals(
                new ArrayList<String>(),
                plsDontRead.getPassageTags()
        );

        assertEquals(
                "",
                plsDontRead.getInlinePassageComment()
        );

        assertFalse(
                plsDontRead.isThisAPointOfNoReturn()
        );
    }

    /**
     * making sure the passage statuses are set correctly
     */
    @Test
    void testingPassageStatus(){


        final PassageEditingInterface normalPassage = new EditablePassage("normal","[[an link]]");

        assertEquals(
                PassageStatus.NORMAL,
                normalPassage.getPassageStatus()
        );

        final PassageEditingInterface normalPassage2 = new EditablePassage("normal2","[[text!|an link]]");

        assertEquals(
                PassageStatus.NORMAL,
                normalPassage2.getPassageStatus()
        );

        final PassageEditingInterface endPassage = new EditablePassage("end", "no links");

        assertEquals(
                PassageStatus.END_NODE,
                endPassage.getPassageStatus()
        );

        final PassageEditingInterface deletedLink = new EditablePassage("del", "[[an link! WAS DELETED !]]");

        assertEquals(
                PassageStatus.DELETED_LINK,
                deletedLink.getPassageStatus()
        );

        final PassageEditingInterface deletedLink2 = new EditablePassage("del", "[[text!|an link! WAS DELETED !]]");

        assertEquals(
                PassageStatus.DELETED_LINK,
                deletedLink2.getPassageStatus()
        );

        final PassageEditingInterface empty = new EditablePassage("empty","");

        assertEquals(
                PassageStatus.EMPTY_CONTENT,
                empty.getPassageStatus()
        );

        final PassageEditingInterface empty2 = new EditablePassage("empty2", "\n \n");

        assertEquals(
                PassageStatus.EMPTY_CONTENT,
                empty2.getPassageStatus()
        );
    }


}
