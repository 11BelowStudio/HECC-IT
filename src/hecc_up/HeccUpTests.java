package hecc_up;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static oh_hecc.game_parts.passage.PassageEditingInterface.getPassageContentWithRenamedLinks;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Some tests for HECC-UP
 */
public class HeccUpTests {

    /**
     * Testing the 'HECCSample'; seeing if it'll output the appropriate .hecc file.
     */
    @Test
    void testAHeccSample(){


        HeccUpTestLogger log = new HeccUpTestLogger();

        HeccParser heccSampleParser = new HeccParser(HeccUpTestConstants.HECCSAMPLE_STRING, log);

        HeccUpHandler theHandler = new HeccUpHandler(log);

        assertDoesNotThrow(
                () -> assertTrue(theHandler.attemptToParseTheGame(heccSampleParser))
        );

        System.out.println(log.getLoggedString());

        assertEquals(
                log.getLoggedString(),
                HeccUpTestConstants.HECCSAMPLE_LOGS
        );



        System.out.println(String.join("", heccSampleParser.getHeccedData()).trim());

        System.out.println("");

        System.out.println(String.join("",HeccUpTestConstants.HECCSAMPLE_OUTPUT).trim());

        assertEquals(
                String.join("", heccSampleParser.getHeccedData()),
                String.join("",HeccUpTestConstants.HECCSAMPLE_OUTPUT)
        );

    }

    /**
     * Basically a LoggerInterface class that records the logged stuff.
     */
    private static class HeccUpTestLogger implements LoggerInterface{

        /**
         * Keeps track of all the logged stuff in a single string
         */
        private final StringBuilder loggedString;

        /**
         * Constructs this, with the loggedString being a new StringBuilder
         */
        HeccUpTestLogger(){
            loggedString = new StringBuilder();
        }

        /**
         * Appends the logged info (and a newline) to the loggedString stringBuilder.
         * @param infoToLog the info what needs logging
         */
        @Override
        public void logInfo(String infoToLog){
            loggedString.append(infoToLog);
            loggedString.append("\n");
        }

        /**
         * gets the constructed logged string as a string
         * @return the string with all the logged info.
         */
        String getLoggedString(){
            return loggedString.toString();
        }

    }


    /**
     * Some constants for use in the tests.
     */
    private static class HeccUpTestConstants{

        /**
         * The 'HeccSample.hecc' file, except just a string.
         */
        final static String HECCSAMPLE_STRING =
                ("this line is before the first passage declaration, so, officially, this line doesn't exist!\n" +
                "\n" +
                "//multiline comment line 1\n" +
                "\n" +
                "!Start: k\n" +
                "!IFID: de7b3d02-81bb-4c2a-82ba-7ca9398b2262\n" +
                "!Title: HECCSample\n" +
                "!Author: Rachel Lowe\n" +
                "\n" +
                "//line 2\n" +
                "\n" +
                "//deez\n" +
                "//\n" +
                "//nutz\n" +
                "// lmao gottem!\n" +
                "\n" +
                "::k <69,69> //this is a comment //ayy lmao eecks dee\n" +
                "\n" +
                "starting passage content goes here.\n" +
                "\n" +
                "The following line contains a link to \"Another passage\".\n" +
                "[[Another passage]]\n" +
                "\n" +
                "::Another passage [yes theres tags] < 256,96> //this is another passage\n" +
                "\n" +
                "congrats you clicked that link to get here, Another passage.\n" +
                "why not [[click this|Yet Another Passage]] as well?\n" +
                "\n" +
                "\n" +
                "::Yet Another Passage <256,256> //\n" +
                "\n" +
                "woah you clicked that so you're now at Yet Another Passage.\n" +
                "\n" +
                "Do you want to go [[Left]], [[Right]], [[Back to the start|k]], or [[Skip this nonsense|dave]]?\n" +
                ";;\n" +
                "this is an comment\n" +
                "::Left <241,325> //the left option\n" +
                "\n" +
                "You go to the left, but the path leads you back to [[dave]].\n" +
                "\n" +
                "::Right <500,400>\n" +
                "\n" +
                "You went to the right, but the path leads you back to [[dave]].\n" +
                "\n" +
                "::dave <512,512> //oh hi dave\n" +
                "\n" +
                "This passage is called dave.\n" +
                "\n" +
                "dave's content doesn't include any links to any other passages.\n" +
                "So I guess this counts as the end.\n" +
                "\n" +
                ";;\n" +
                "yep it's the end\n" +
                "\n" +
                "wack").trim();

        /**
         * The expected output from HECCSAMPLE as an ArrayList of strings.
         */
        final static ArrayList<String> HECCSAMPLE_OUTPUT = new ArrayList<>(Arrays.asList(
                "//HECC UP output (as of 29/01/2021) (Rachel Lowe, 2021)\n",
                "\n",
                "var startingPassageName = \"k\";\n",
                "\n",
                "function getHECCED(){\n",
                "\n",
                "\ttheHeccer.addPassageToMap(\n",
                "\t\tnew Passage(\n",
                "\t\t\t\"dave\",\n",
                "\t\t\t\"This passage is called dave.\\n\\ndave's content doesn't include any links to any other passages.\\nSo I guess this counts as the end.\",\n",
                "\t\t\t[]\n",
                "\t\t)\n",
                "\t);\n",
                "\ttheHeccer.addPassageToMap(\n",
                "\t\tnew Passage(\n",
                "\t\t\t\"Left\",\n",
                "\t\t\t\"You go to the left, but the path leads you back to [[dave]].\",\n",
                "\t\t\t[]\n",
                "\t\t)\n",
                "\t);\n",
                "\ttheHeccer.addPassageToMap(\n",
                "\t\tnew Passage(\n",
                "\t\t\t\"Right\",\n",
                "\t\t\t\"You went to the right, but the path leads you back to [[dave]].\",\n",
                "\t\t\t[]\n",
                "\t\t)\n",
                "\t);\n",
                "\ttheHeccer.addPassageToMap(\n",
                "\t\tnew Passage(\n",
                "\t\t\t\"k\",\n",
                "\t\t\t\"starting passage content goes here.\\n\\nThe following line contains a link to \\\"Another passage\\\".\\n[[Another passage]]\",\n",
                "\t\t\t[]\n",
                "\t\t)\n",
                "\t);\n",
                "\ttheHeccer.addPassageToMap(\n",
                "\t\tnew Passage(\n",
                "\t\t\t\"Another passage\",\n",
                "\t\t\t\"congrats you clicked that link to get here, Another passage.\\nwhy not [[click this|Yet Another Passage]] as well?\",\n",
                "\t\t\t[\"yes\",\"theres\",\"tags\"]\n",
                "\t\t)\n",
                "\t);\n",
                "\ttheHeccer.addPassageToMap(\n",
                "\t\tnew Passage(\n",
                "\t\t\t\"Yet Another Passage\",\n",
                "\t\t\t\"woah you clicked that so you're now at Yet Another Passage.\\n\\nDo you want to go [[Left]], [[Right]], [[Back to the start|k]], or [[Skip this nonsense|dave]]?\",\n",
                "\t\t\t[]\n",
                "\t\t)\n",
                "\t);\n",
                "\n",
                "\ttheHeccer.printPassages();\n",
                "\n",
                "\ttheHeccer.loadCurrentPassage();\n",
                "\n",
                "}\n",
                "\n",
                "//that's all, folks!\n",
                "\n"
        ));

        /**
         * The expected logged data from HECCSAMPLE but as a string
         */
        final static String HECCSAMPLE_LOGS =
                "Passage objects constructed\n" +
                "No metadata problems detected!\n";
    }

}
