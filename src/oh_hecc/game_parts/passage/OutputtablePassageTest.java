package oh_hecc.game_parts.passage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Some tests for the OutputtablePassage
 */
public class OutputtablePassageTest {


    /**
     * Making sure this constructor works as expected
     */
    @Test
    void testIt(){

        final OutputtablePassage op = new OutputtablePassage(
                "Another passage",
                "congrats you clicked that link to get here, \"Another passage\".\n" +
                        "why not [[click this|Yet Another Passage]] as well?",
                "[yes i guess idk] <54,-230> //"
        );

        assertEquals("Another passage",op.getPassageName());

        assertArrayEquals(
                new String[]{"Yet Another Passage"},
                op.getLinkedPassages().toArray(new String[0])
        );

        assertArrayEquals(
                new String[]{"yes","i","guess","idk"},
                op.getPassageTags().toArray(new String[0])
        );

        assertEquals(
                "congrats you clicked that link to get here, \\\"Another passage\\\".\\n" +
                        "why not [[click this|Yet Another Passage]] as well?",
                op.getPassageContent()
        );

        assertEquals(
                "\ttheHeccer.addPassageToMap(\n" +
                        "\t\tnew Passage(\n" +
                        "\t\t\t\"Another passage\",\n" +
                        "\t\t\t\"congrats you clicked that link to get here, \\\"Another passage\\\".\\nwhy not [[click this|Yet Another Passage]] as well?\",\n" +
                        "\t\t\t[\"yes\",\"i\",\"guess\",\"idk\"]\n" +
                        "\t\t)\n" +
                        "\t);\n",
                op.getHecced()
        );


        final OutputtablePassage op2 = new OutputtablePassage(
                "Kevin",
                "am kevin",
                ""
        );

        assertEquals("Kevin",op2.getPassageName());

        assertArrayEquals(
                new String[]{},
                op2.getLinkedPassages().toArray(new String[0])
        );

        assertArrayEquals(
                new String[]{},
                op2.getPassageTags().toArray(new String[0])
        );

        assertEquals(
                "am kevin",
                op2.getPassageContent()
        );



        assertEquals(
                "\ttheHeccer.addPassageToMap(\n" +
                        "\t\tnew Passage(\n" +
                        "\t\t\t\"Kevin\",\n" +
                        "\t\t\t\"am kevin\",\n" +
                        "\t\t\t[]\n" +
                        "\t\t)\n" +
                        "\t);\n",
                op2.getHecced()
        );
    }
}
