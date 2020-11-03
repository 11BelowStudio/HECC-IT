package oh_hecc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditableMetadataTests {

    @Test
    public void notAFullTestButSeeingIfTheConstructorAndToHeccWorksBeforeIGiveUpForTheNight(){
        EditableMetadata sampleEditableMetadataObject = new EditableMetadata(
                "this line is before the first passage declaration, so, officially, this line doesn't exist!\n" +
                "\n" +
                "//multiline comment line 1\n" +
                "\n" +
                "!StartPassageName: Start\n" +
                "!IFID: de7b3d02-81bb-4c2a-82ba-7ca9398b2262\n" +
                "!StoryTitle: HECCSample\n" +
                "!Author: R. Lowe\n" +
                "\n" +
                "//line 2\n" +
                "\n" +
                "!Var: variableA = defaultValue //comment\n" +
                "!Var: variableB = defaultValue\n" +
                "!Var: variableC //comment\n" +
                "!Var: variableD\n" +
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

    private static void equalsTest(Object expected, Object actual){
        assertEquals(expected, actual, "Expected:\n" + expected.toString() + "\nGot:\n" + actual.toString());
    }
}
