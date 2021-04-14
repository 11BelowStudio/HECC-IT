package oh_hecc.game_parts.metadata;

import heccCeptions.InvalidMetadataDeclarationException;
import heccCeptions.InvalidPassageNameException;
import oh_hecc.Heccable;
import oh_hecc.Parseable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class has some tests for the EditableMetadata
 */
public class EditableMetadataTests {

    /**
     * This is mostly here to test the initial constructor for the EditableMetadata
     */
    @Test
    public void notAFullTestButSeeingIfTheConstructorAndToHeccWorksBeforeIGiveUpForTheNight(){
        final Heccable sampleEditableMetadataObject = new EditableMetadata(
                "this line is before the first passage declaration, so, officially, this line doesn't exist!\n" +
                "\n" +
                "//multiline comment line 1\n" +
                "\n" +
                "!start: Start\n" +
                "!IFID: de7b3d02-81bb-4c2a-82ba-7ca9398b2262\n" +
                "!title: HECCSample\n" +
                "!Author: R. Lowe\n" +
                "\n" +
                "//line 2\n" +
                "\n" +
                "!var: variableA = defaultValue //comment\n" +
                "!var: variableB = defaultValue\n" +
                "!var: variableC //comment\n" +
                "!var: variableD\n" +
                "\n" +
                "//deez\n" +
                "//\n" +
                "//nutz\n" +
                "// lmao gottem!\n"
        );

        System.out.println(sampleEditableMetadataObject.toHecc());

        System.out.println("\n\n");

        final EditableMetadata deezNutz = new EditableMetadata("deez","nutz");

        System.out.println(deezNutz.toHecc());

    }

    /**
     * Attempting to change the game title (valid titles and invalid titles)
     */
    @Test
    public void changeTitleTest(){

        assertDoesNotThrow(() -> MetadataEditingInterface.checkTitleValidity("new title"));

        final MetadataEditingInterface md1 = new EditableMetadata("old title", "ecks dee");

        assertDoesNotThrow(() -> md1.updateTitle("new title"));
        equalsTest("new title",md1.getTitle());

        System.out.println("\n\n");

        final MetadataEditingInterface md2 = new EditableMetadata("older title", "deez nutz");
        assertThrows(
                InvalidMetadataDeclarationException.class,
                () -> md2.updateTitle(" ") // whitespace = invalid title
        );

        System.out.println("\n\n");

        final MetadataEditingInterface md3 = new EditableMetadata("old","example");
        assertDoesNotThrow( () -> md3.updateTitle(" cool new title with spaces and punctuation! "));
        equalsTest("cool new title with spaces and punctuation!",md3.getTitle());

        System.out.println(md3.toHecc());

        assertDoesNotThrow(
            () -> MetadataEditingInterface.checkTitleValidity("x"),
            "single letter title threw exception!"
        );

    }

    /**
     * Attempting to update the author of the game (valid inputs)
     */
    @Test
    public void changeAuthorValidTests(){
        final String[] valid = {"A. N. Onymous", "deez nutz","D. Eez, N. Utz, and L. Mao Gottem", " oh look whitespace ", "thelegend27", "_dave","x"};


        final MetadataEditingInterface md1 = new EditableMetadata("title","author");

        for (String s: valid) {
            assertDoesNotThrow(
                    () -> MetadataEditingInterface.checkAuthorValidity(s),
                    s + " check threw exception!"
            );
            assertDoesNotThrow(
                    () -> md1.updateAuthor(s),
                    s + " update threw exception!"
            );
            equalsTest(s.trim(), md1.getAuthor());
        }


    }

    /**
     * Attempting to not change the author of the game (invalid inputs)
     */
    @Test
    public void invalidAuthorTests(){

        final String[] invalid = {"deez nutz!","$","bob?!","Dread Zeppelin - your time is gonna come (1990)","bruh~"};

        final String originalAuthor = "author";
        final MetadataEditingInterface md1 = new EditableMetadata("title", originalAuthor);

        for (String s: invalid) {
            assertThrows(
                    InvalidMetadataDeclarationException.class,
                    () -> MetadataEditingInterface.checkAuthorValidity(s),
                    s + " check did not throw exception!"
            );
            assertThrows(
                    InvalidMetadataDeclarationException.class,
                    () -> md1.updateAuthor(s),
                    s + " update did not throw exception!"
            );
        }
        equalsTest(originalAuthor, md1.getAuthor());

    }

    /**
     * Attempting author inputs that aren't entirely invalid (have a valid substring) but are still not valid.
     */
    @Test
    void testPartiallyInvalidAuthors(){

        final String[] notEntirelyInvalid = {"bob & also bob","kev!n","$anic","0^0"};

        final MetadataEditingInterface md1 = new EditableMetadata("title", "author");

        for (String s: notEntirelyInvalid) {
            assertThrows(
                    InvalidMetadataDeclarationException.class,
                    () -> {
                        final String partial = MetadataEditingInterface.checkAuthorValidity(s);
                        if (!partial.equals(s.trim())){
                            //if only part of the string was accepted (full string rejected), it's still been rejected.
                            throw new InvalidMetadataDeclarationException(s, "author");
                        }
                    },
                    s + " check did not throw exception!"
            );
            assertThrows(
                    InvalidMetadataDeclarationException.class,
                    () -> {
                        md1.updateAuthor(s);
                        if (!md1.getAuthor().equals(s.trim())){
                            //if only part of the string was accepted (full string rejected), it's still been rejected.
                            throw new InvalidMetadataDeclarationException(s, "author");
                        }
                    },
                    s + " update did not throw exception!"
            );
        }

    }

    /**
     * Testing valid start passage renames
     */
    @Test
    void changeValidStartPassageTests(){
        final String[] valid = {"Start","Another passage","dave","28 Stab Wounds","deez-nutz","ayy_lmao_","q"};

        final MetadataEditingInterface md1 = new EditableMetadata("no valid metadata here");

        for (String s: valid) {
            Assertions.assertDoesNotThrow(
                    () -> Parseable.validatePassageNameRegex(s),
                    s + " check threw exception!"
            );
            assertDoesNotThrow(
                    () -> md1.updateStartPassage(s),
                    s + " update threw exception!"
            );
            assertEquals(s, md1.getStartPassage());
        }
        System.out.println(md1.getStartPassage());

    }

    /**
     * Testing invalid start passage renames
     */
    @Test
    void changeInvalidStartPassageTests(){
        final String[] valid = {"Start?","Another * passage","-xd-","nice meme!","0w0~"};

        final MetadataEditingInterface md1 = new EditableMetadata("no valid metadata here");

        final String initialStart = md1.getStartPassage();

        for (String s: valid) {
            assertThrows(
                    InvalidPassageNameException.class,
                    () -> Parseable.validatePassageNameRegex(s),
                    s + " validation did not throw exception"
            );
            assertThrows(
                    InvalidPassageNameException.class,
                    () ->md1.updateStartPassage(s),
                    s + " update did not throw exception!"
            );
            assertEquals(initialStart, md1.getStartPassage());
        }
        System.out.println(md1.getStartPassage());

    }

    /**
     * Testing a comment update
     */
    @Test
    void testUpdateComment(){
        final String[] comments = {
                "deez nutz lmao gottem",
                "TOP TEXT\n\nBOTTOM TEXT",
                "\t\tnice tabs",
                "",
                "no tab\ttab1\n\ttab 2\n\t\t\ttab 3\n\n\n\t\t\t\tnice indentation\n\nbottom text\n\n"
        };

        final MetadataEditingInterface md1 = new EditableMetadata("nothing to see here, move along");
        assertEquals("",md1.getComment());

        for (String s: comments) {
            md1.updateComment(s);
            assertEquals(s,md1.getComment());
        }
    }

    /**
     * A helper method for comparing equals things with a prewritten method
     * @param expected expected value
     * @param actual actual value
     */
    private static void equalsTest(Object expected, Object actual){
        assertEquals(expected, actual, "Expected:\n" + expected.toString() + "\nGot:\n" + actual.toString());
    }


}
