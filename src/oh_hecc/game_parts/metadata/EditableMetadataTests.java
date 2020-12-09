package oh_hecc.game_parts.metadata;

import heccCeptions.InvalidMetadataDeclarationException;
import oh_hecc.Parseable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EditableMetadataTests {

    @Test
    public void notAFullTestButSeeingIfTheConstructorAndToHeccWorksBeforeIGiveUpForTheNight(){
        EditableMetadata sampleEditableMetadataObject = new EditableMetadata(
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

        EditableMetadata deezNutz = new EditableMetadata("deez","nutz");

        System.out.println(deezNutz.toHecc());

    }

    @Test
    public void changeTitleTest(){

        assertDoesNotThrow(() -> MetadataEditingInterface.checkTitleValidity("new title"));

        EditableMetadata md1 = new EditableMetadata("old title", "ecks dee");

        assertDoesNotThrow(() -> md1.updateTitle("new title"));
        equalsTest("new title",md1.getTitle());

        System.out.println("\n\n");

        EditableMetadata md2 = new EditableMetadata("older title", "deez nutz");
        assertThrows(
                InvalidMetadataDeclarationException.class,
                () -> md2.updateTitle(" ")
        );

        System.out.println("\n\n");

        EditableMetadata md3 = new EditableMetadata("old","example");
        assertDoesNotThrow( () -> md3.updateTitle(" cool new title with spaces and punctuation! "));
        equalsTest("cool new title with spaces and punctuation!",md3.getTitle());

        System.out.println(md3.toHecc());

        assertDoesNotThrow(
            () -> MetadataEditingInterface.checkTitleValidity("x"),
            "single letter title threw exception!"
        );

    }

    @Test
    public void changeAuthorValidTests(){
        String[] valid = {"A. N. Onymous", "deez nutz","D. Eez, N. Utz, and L. Mao Gottem", " oh look whitespace "};


        EditableMetadata md1 = new EditableMetadata("title","author");

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

    @Test
    public void invalidAuthorTests(){

        String[] invalid = {"deez nutz!","42","x","Dread Zeppelin - your time is gonna come (1990)","bruh~"};

        String originalAuthor = "author";
        EditableMetadata md1 = new EditableMetadata("title", originalAuthor);

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

    @Test
    public void changeStartPassageTests(){
        String[] valid = {"Start","Another passage","dave","28 Stab Wounds","deez-nutz","ayy_lmao_","q"};

        EditableMetadata md1 = new EditableMetadata("no valid metadata here");

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

    @Test
    void testUpdateComment(){
        String[] comments = {
                "deez nutz lmao gottem",
                "TOP TEXT\n\nBOTTOM TEXT",
                "\t\tnice tabs",
                "",
                "no tab\ttab1\n\ttab 2\n\t\t\ttab 3\n\n\n\t\t\t\tnice indentation\n\nbottom text\n\n"
        };

        EditableMetadata md1 = new EditableMetadata("nothing to see here, move along");
        assertEquals("",md1.getComment());

        for (String s: comments) {
            md1.updateComment(s);
            assertEquals(s,md1.getComment());
        }
    }

    private static void equalsTest(Object expected, Object actual){
        assertEquals(expected, actual, "Expected:\n" + expected.toString() + "\nGot:\n" + actual.toString());
    }


}
