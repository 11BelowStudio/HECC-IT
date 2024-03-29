package hecc_up;

import heccCeptions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some tests for HECC-UP
 */
public class HeccUpTests {

    /**
     * don't construct this.
     */
    private HeccUpTests(){}

    /**
     * Testing the 'HECCSample'; seeing if it'll output the appropriate .hecc file.
     */
    @Test
    void testAHeccSample(){


        final HeccUpTestLogger log = new HeccUpTestLogger();

        final HeccParser heccSampleParser = new HeccParser(HeccUpTestConstants.HECCSAMPLE_STRING, log);

        final HeccUpHandler theHandler = new HeccUpHandler(log);

        assertDoesNotThrow(
                () -> assertTrue(theHandler.attemptToParseTheGame(heccSampleParser))
        );

        System.out.println(log.getLoggedString());

        assertEquals(
                log.getLoggedString(),
                HeccUpTestConstants.HECCSAMPLE_LOGS
        );

        final String singleStringHeccedData = String.join("", heccSampleParser.getHeccedData());

        assertEquals(
                String.join("",HeccUpTestConstants.HECCSAMPLE_OUTPUT),
                singleStringHeccedData
        );

        System.out.println(singleStringHeccedData);

    }

    /**
     * making sure it complains when the hecc file has no passages
     */
    @Test
    void testForEmptyFileComplaint(){

        HeccUpTestLogger log = new HeccUpTestLogger();

        final HeccParser nothingParser = new HeccParser("", log);

        NoPassagesException e = assertThrows(
                NoPassagesException.class,
                nothingParser::constructThePassageObjects
        );

        System.out.println(e.getMessage());

        log = new HeccUpTestLogger();

        HeccParser onlyMetadataParser = new HeccParser(
                "!title: no passages here lol\n" +
                        "!author: joe mamma",
                log
        );

        e = assertThrows(
                NoPassagesException.class,
                onlyMetadataParser::constructThePassageObjects
        );

        System.out.println(e.getMessage());

    }

    /**
     * Makes sure it complains if there are multiple passages with the same name
     */
    @Test
    void testForDuplicatePassageException(){

        HeccUpTestLogger log = new HeccUpTestLogger();

        final HeccParser dupeParser = new HeccParser(
                "!title: dupe time\n" +
                "!author: joe mamma\n" +
                "!start: Start\n" +
                "\n" +
                "::Start\n" +
                "this is the start passage\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "::bob\n" +
                "hi im bob\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "::bob\n" +
                "hi im also bob\n" +
                ";;\n" +
                "\n" +
                ";;", log);

        DuplicatePassageNameException e = assertThrows(
                DuplicatePassageNameException.class,
                dupeParser::constructThePassageObjects
        );

        System.out.println(e.getMessage());

        log = new HeccUpTestLogger();

        final HeccParser anotherDupe = new HeccParser(
                "!title: dupe time 2\n" +
                "!author: joe mamma\n" +
                "!start: Start\n" +
                "\n" +
                "::Start\n" +
                "this is the start passage\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "::Start\n" +
                "this is also the start passage?\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "::Start\n" +
                "how\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "::dave\n" +
                "im dave\n" +
                ";;\n" +
                "\n" +
                ";;", log
        );

        e = assertThrows(
                DuplicatePassageNameException.class,
                anotherDupe::constructThePassageObjects
        );

        System.out.println(e.getMessage());

    }

    /**
     * Testing for when there's no start passage
     */
    @Test
    void testForNoStart(){

        final HeccUpTestLogger log = new HeccUpTestLogger();

        HeccUpHandler theHandler = new HeccUpHandler(log);

        final HeccParser noExplicitStart = new HeccParser(
                "!title: no start\n" +
                "!author: joe mamma\n" +
                "!start: Dave\n" +
                "\n" +
                "::a\n" +
                "aaa\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "::b\n" +
                "bbbb\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "::c\n" +
                "see\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "::d\n" +
                "ddd\n" +
                ";;\n" +
                "\n" +
                ";;", log);


        MissingStartingPassageException e = assertThrows(
                MissingStartingPassageException.class,
                () -> theHandler.attemptToParseTheGame(noExplicitStart)
        );

        System.out.println(e.getMessage());


        final HeccParser noImplictStart = new HeccParser(
                "!title: no start\n" +
                "!author: joe mamma\n" +
                "\n" +
                "::a\n" +
                "aaa\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "::b\n" +
                "bbbb\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "::c\n" +
                "see\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "::d\n" +
                "ddd\n" +
                ";;\n" +
                "\n" +
                ";;", log);

        e = assertThrows(
                MissingStartingPassageException.class,
                () -> theHandler.attemptToParseTheGame(noImplictStart)
        );

        System.out.println(e.getMessage());

    }

    /**
     * empty passage
     */
    @Test
    void testForEmptyPassage(){

        final HeccUpTestLogger log = new HeccUpTestLogger();

        final HeccParser emptyPassageParser = new HeccParser(
                "!title: empty passage\n" +
                "!author: joe mamma\n" +
                "!start: Start\n" +
                "\n" +
                "::Start\n" +
                ";;\n" +
                "\n"+
                ";;\n" +
                "\n" +
                "::ok\n" +
                "yes\n" +
                ";;\n" +
                "\n" +
                ";;", log);

        final HeccUpHandler theHandler = new HeccUpHandler(log);


        final EmptyPassageException e = assertThrows(
                EmptyPassageException.class,
                () ->theHandler.attemptToParseTheGame(emptyPassageParser)
        );

        System.out.println(e.getMessage());

    }

    /**
     * not detecting passage with invalid name
     */
    @Test
    void invalidPassageTest(){

        final HeccUpTestLogger log = new HeccUpTestLogger();

        final HeccParser invalidPassageParser = new HeccParser(
                "!title: ok\n" +
                "!author: joe mamma\n" +
                "!start: !THIS IS EPIC! WOO!\n" +
                "\n" +
                "::!THIS IS EPIC! WOO!\n" +
                "deez nutz\n" +
                ";;\n" +
                ";;\n", log);

        final HeccUpHandler theHandler = new HeccUpHandler(log);

        final NoPassagesException e = assertThrows(
                NoPassagesException.class,
                () -> theHandler.attemptToParseTheGame(invalidPassageParser)
        );

        System.out.println(e.getMessage());
    }

    /**
     * Link to a passage which doesn't exist
     */
    @Test
    void undefinedPassageTest(){

        final HeccUpTestLogger log = new HeccUpTestLogger();

        final HeccUpHandler theHandler = new HeccUpHandler(log);

        final HeccParser undefinedParser = new HeccParser(
                "!title: davent\n" +
                "!author: joe mamma\n" +
                "!start: Start\n" +
                "\n" +
                "::Start\n" +
                "[[dave]]\n" +
                ";;\n" +
                ";;\n" +
                "\n" +
                "::im not dave\n" +
                "hi im not dave\n" +
                ";;\n" +
                "\n" +
                ";;", log);

        UndefinedPassageException e = assertThrows(
                UndefinedPassageException.class,
                () -> theHandler.attemptToParseTheGame(undefinedParser)
        );

        System.out.println(e.getMessage());

        final HeccParser undefinedIndirectParser = new HeccParser(
        "!title: davent\n" +
                "!author: joe mamma\n" +
                "!start: Start\n" +
                "\n" +
                "::Start\n" +
                "[[some text|dave]]\n" +
                ";;\n" +
                ";;\n" +
                "\n" +
                "::im not dave\n" +
                "hi im not dave\n" +
                ";;\n" +
                "\n" +
                ";;", log);

        e = assertThrows(
                UndefinedPassageException.class,
                () -> theHandler.attemptToParseTheGame(undefinedIndirectParser)
        );

        System.out.println(e.getMessage());

    }

    /**
     * check for a link to a deleted passage (deleted via OH-HECC and marked as deleted via OH-HECC)
     */
    @Test
    void testForDeletedPassageLinks(){

        final HeccUpTestLogger log = new HeccUpTestLogger();

        final HeccUpHandler theHandler = new HeccUpHandler(log);

        final HeccParser deletedParser = new HeccParser(
                "!title: davent\n" +
                "!author: joe mamma\n" +
                "!start: Start\n" +
                "\n" +
                "::Start\n" +
                "[[dave ! WAS DELETED !]]\n" +
                ";;\n" +
                ";;\n" +
                "\n" +
                "::im not dave\n" +
                "hi im not dave\n" +
                ";;\n" +
                "\n" +
                ";;", log);

        DeletedLinkPresentException e = assertThrows(
                DeletedLinkPresentException.class,
                () -> theHandler.attemptToParseTheGame(deletedParser)
        );

        System.out.println(e.getMessage());

        final HeccParser indirectDeletedParser = new HeccParser(
                "!title: davent\n" +
                        "!author: joe mamma\n" +
                        "!start: Start\n" +
                        "\n" +
                        "::Start\n" +
                        "[[this is dave|dave ! WAS DELETED !]]\n" +
                        ";;\n" +
                        ";;\n" +
                        "\n" +
                        "::im not dave\n" +
                        "hi im not dave\n" +
                        ";;\n" +
                        "\n" +
                        ";;", log);

        e = assertThrows(
                DeletedLinkPresentException.class,
                () -> theHandler.attemptToParseTheGame(indirectDeletedParser)
        );

        System.out.println(e.getMessage());

    }

    /**
     * And checking the weirdness with some passage content being mistaken for tags
     */
    @Test
    void testForTheWeirdnessWithTheTagsAndLineEndStuff(){

        final HeccUpTestLogger log = new HeccUpTestLogger();

        final HeccParser tagParser = new HeccParser("::Start [] <a,b> //\n" +
                "[[another passage]]\n" +
                "<3400,1343>\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "::another passage [!$!] //\n" +
                "[[Start]]\n" +
                "<-1000,-1000>\n" +
                ";;\n" +
                "\n" +
                ";;", log);

        final HeccUpHandler handler = new HeccUpHandler(log);

        final String expectedOutput =
                "//HECC UP output (as of 16/04/2021) (HECC-IT produced by Rachel Lowe, 2021)\n" +
                "\n" +
                "// This hecced.js file contains the data for:\n" +
                "// An Interactive Fiction\n" +
                "// by Anonymous\n" +
                "// IFID: unspecified\n" +
                "\n" +
                "/*\n" +
                "LEGAL STUFF:\n" +
                "The author of the game held in this hecced.js file shall be considered the\n" +
                "\towner of this file, and may opt to select any license they want for this hecced.js file.\n" +
                "If the author of this game has not selected a license, assume that this hecced.js file has\n" +
                "\tbeen distributed under the terms of the Mozilla Public License (v2.0) by the author.\n" +
                "\t\tYou can obtain a copy of that license at http://mozilla.org/MPL/2.0/\n" +
                "If the author of this file wishes to use a different license, they may include another one\n" +
                "\twithin the source code of this file, underneath this comment block, which shall,\n" +
                "\tfor all intents and purposes, be considered to be the license for this hecced.js file.\n" +
                "Alternatively, another license may be distributed with this file, in a file called 'LICENSE',\n" +
                "\twhich shall be the license under which the hecced.js file has been distributed.\n" +
                "TL;DR the author of this game owns and gets to choose the license for this hecced.js file (because it's their game).\n" +
                "*/\n\n" +
                "var startingPassageName = \"Start\";\n" +
                "\n" +
                "function getHECCED(){\n" +
                "\n" +
                "\ttheHeccer.addPassageToMap(\n" +
                "\t\tnew Passage(\n" +
                "\t\t\t\"Start\",\n" +
                "\t\t\t\"[[another passage]]\\n<3400,1343>\",\n" +
                "\t\t\t[]\n" +
                "\t\t)\n" +
                "\t);\n" +
                "\ttheHeccer.addPassageToMap(\n" +
                "\t\tnew Passage(\n" +
                "\t\t\t\"another passage\",\n" +
                "\t\t\t\"[[Start]]\\n<-1000,-1000>\",\n" +
                "\t\t\t[]\n" +
                "\t\t)\n" +
                "\t);\n" +
                "\n" +
                "\ttheHeccer.printPassages();\n" +
                "\n" +
                "\ttheHeccer.loadCurrentPassage();\n" +
                "\n" +
                "}\n" +
                "\n" +
                "//that's all, folks!";

        try {
            handler.attemptToParseTheGame(tagParser);

            assertEquals(expectedOutput, String.join("",tagParser.getHeccedData()));


        } catch (Exception e){
            fail("didn't expect that exception", e);
        }

    }

    /**
     * Making sure 'orphan' passages are omitted from the produced .hecc data
     */
    @Test
    void ensureOrphansAreNotOutput(){

        final String heccWithOrphanPassages = "!start: Start\n" +
                "\n" +
                "::Start\n" +
                "[[bob]]\n" +
                "\n" +
                "::bob\n" +
                "[[chaz]] [[text|kev]]\n" +
                "\n" +
                "::chaz\n" +
                "hi im chaz\n" +
                "\n" +
                "::kev\n" +
                "hi im kev\n" +
                "\n" +
                "::jacob\n" + // no parents, should not be in output
                "hi im jacob\n" +
                "\n" +
                "::lucy\n" + // no parents, should not be in output
                "hi im lucy\n" +
                "\n" +
                "::joe\n" + // no parents, should not be in output
                "[[hmm]]\n" +
                "\n" +
                "::hmm\n" + // it has a parent, but that one has no parents itself, so this shouldn't be in output.
                "shouldnt be here\n";

        final HeccUpTestLogger log = new HeccUpTestLogger();

        final HeccUpHandler handler = new HeccUpHandler(log);

        final HeccParser theParser = new HeccParser(heccWithOrphanPassages, log);

        // we do not expect to see 'jacob','lucy','joe', or 'hmm' anywhere in the output

        assertDoesNotThrow(
                () -> handler.attemptToParseTheGame(theParser)
        );

        final String heccedOutput = String.join("",theParser.getHeccedData());

        System.out.println(heccedOutput);

        assertFalse(
                heccedOutput.contains("jacob")
        );

        assertFalse(
                heccedOutput.contains("lucy")
        );

        assertFalse(
                heccedOutput.contains("joe")
        );

        assertFalse(
                heccedOutput.contains("hmm")
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
    private enum HeccUpTestConstants{

        ;

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
                "wack\n"+
                ";;\n" +
                "::unlinked\n"+
                "you wont see this\n"+
                ";;\n"+
                "no comment."
                ).trim();

        /**
         * The expected output from HECCSAMPLE as an ArrayList of strings.
         */
        final static ArrayList<String> HECCSAMPLE_OUTPUT = new ArrayList<>(Arrays.asList(
                "//HECC UP output (as of 16/04/2021) (HECC-IT produced by Rachel Lowe, 2021)\n" +
                "\n",
                "// This hecced.js file contains the data for:\n",
                "// HECCSample\n",
                "// by Rachel Lowe\n",
                "// IFID: DE7B3D02-81BB-4C2A-82BA-7CA9398B2262\n",
                "\n",
                "/*\n" +
                "LEGAL STUFF:\n" +
                "The author of the game held in this hecced.js file shall be considered the\n" +
                "\towner of this file, and may opt to select any license they want for this hecced.js file.\n"+
                "If the author of this game has not selected a license, assume that this hecced.js file has\n"+
                "\tbeen distributed under the terms of the Mozilla Public License (v2.0) by the author.\n" +
                "\t\tYou can obtain a copy of that license at http://mozilla.org/MPL/2.0/\n"+
                "If the author of this file wishes to use a different license, they may include another one\n"+
                "\twithin the source code of this file, underneath this comment block, which shall,\n" +
                "\tfor all intents and purposes, be considered to be the license for this hecced.js file.\n" +
                "Alternatively, another license may be distributed with this file, in a file called 'LICENSE',\n" +
                "\twhich shall be the license under which the hecced.js file has been distributed.\n"+
                "TL;DR the author of this game owns and gets to choose the license for this hecced.js file (because it's their game).\n" +
                "*/\n\n",
                "var startingPassageName = \"k\";\n",
                "\n",
                "function getHECCED(){\n",
                "\n",
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
                "\n",
                "\ttheHeccer.printPassages();\n",
                "\n",
                "\ttheHeccer.loadCurrentPassage();\n",
                "\n",
                "}\n",
                "\n",
                "//that's all, folks!"
        ));

        /**
         * The expected logged data from HECCSAMPLE but as a string
         */
        final static String HECCSAMPLE_LOGS =
                "Passage objects constructed\n" +
                "No metadata problems detected!\n";
    }

}
