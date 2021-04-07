package oh_hecc;

import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.game_parts.passage.SharedPassage;
import org.junit.jupiter.api.Test;
import utilities.Vector2D;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some tests for the OH-HECC parser.
 */
public class OhHeccParserTest {

    /**
     * Just testing a valid .hecc file, no trickery or anything here (evaluating correctness via passage names)
     */
    @Test
    void testValidHecc(){
        String validHeccSample = "Hecc code generated by OH-HECC at Tue Dec 08 10:10:58 GMT 2020\n" +
                "\n" +
                "!Title: HECCSample\n" +
                "!Author: Rachel Lowe\n" +
                "!IFID: DE7B3D02-81BB-4C2A-82BA-7CA9398B2262\n" +
                "!start: Start\n" +
                "\n" +
                "\n" +
                "Note to author: only comments saved in this multiline comment area (lines starting with //) will be preserved by OH-HECC!\n" +
                "//                 multiline comment line 1\n" +
                "//                 line 2\n" +
                "//                 deez\n" +
                "//                 \n" +
                "//                 nutz\n" +
                "//                  lmao gottem!\n" +
                "\n" +
                "\n" +
                "end of metadata. game content starts below:\n" +
                "\n" +
                "::Start [] <-259.0,-205.0> //this is a comment //ayy lmao eecks dee\n" +
                "starting passage content goes here.\n" +
                "\n" +
                "The following line contains a link to \"Another passage\".\n" +
                "[[Another passage]]\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "::Another passage [yes theres tags] <30.0,-212.0> //this is another passage\n" +
                "congrats you clicked that link to get here, Another passage.\n" +
                "why not [[click this|Yet Another Passage]] as well?\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "::Left <-174.0,73.0> [dave] //the left option\n" +
                "You go to the left, but the path leads you back to [[dave]].\n" +
                ";;\n" +
                "\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "::Yet Another Passage [] <-65.0,-60.0> //\n" +
                "woah you clicked that so you're now at Yet Another Passage.\n" +
                "\n" +
                "Do you want to go [[Left]], [[Right]], [[Back to the start|Start]], or [[Skip this nonsense|dave]]?\n" +
                ";;\n" +
                "this is an comment\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "\n" +
                "::Right [dave] <128.0,-39.0> //\n" +
                "You went to the right, but the path leads you back to [[dave]].\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "::dave [] <81.0,142.0> //oh hi dave\n" +
                "This passage is called dave.\n" +
                "\n" +
                "dave's content doesn't include any links to any other passages.\n" +
                "So I guess this counts as the end.\n" +
                ";;\n" +
                "yep it's the end\n" +
                "\n" +
                "wack\n" +
                ";;\n";

        // literally just HeccSample.

        Set<String> validHeccPassageNames = new HashSet<>(Arrays.asList("Start","Another passage","Yet Another Passage","Left","Right","dave"));

        OhHeccParser hp = new OhHeccParser(validHeccSample);

        Set<String> parsedNames = hp.getHeccMap().values().stream().map(SharedPassage::getPassageName).collect(Collectors.toSet());

        assertEquals(validHeccPassageNames, parsedNames);

    }

    /**
     * Just that it can automatically handle duplicate passage names (evaluating correctness via passage names)
     */
    @Test
    void testDuplicateName(){
        String dupeHeccSample = "Hecc code generated by OH-HECC at Tue Dec 08 10:10:58 GMT 2020\n" +
                "\n" +
                "!Title: HECCSample\n" +
                "!Author: Rachel Lowe\n" +
                "!IFID: DE7B3D02-81BB-4C2A-82BA-7CA9398B2262\n" +
                "!start: Start\n" +
                "\n" +
                "\n" +
                "Note to author: only comments saved in this multiline comment area (lines starting with //) will be preserved by OH-HECC!\n" +
                "//                 multiline comment line 1\n" +
                "//                 line 2\n" +
                "//                 deez\n" +
                "//                 \n" +
                "//                 nutz\n" +
                "//                  lmao gottem!\n" +
                "\n" +
                "\n" +
                "end of metadata. game content starts below:\n" +
                "\n" +
                "::Start [] <-259.0,-205.0> //this is a comment //ayy lmao eecks dee\n" +
                "starting passage content goes here.\n" +
                "\n" +
                "The following line contains a link to \"Another passage\".\n" +
                "[[Another passage]]\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "::Another passage [yes theres tags] <30.0,-212.0> //this is another passage\n" +
                "congrats you clicked that link to get here, Another passage.\n" +
                "why not [[click this|Yet Another Passage]] as well?\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "::Left <-174.0,73.0> [dave] //the left option\n" +
                "You go to the left, but the path leads you back to [[dave]].\n" +
                ";;\n" +
                "\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "::Yet Another Passage [] <-65.0,-60.0> //\n" +
                "woah you clicked that so you're now at Yet Another Passage.\n" +
                "\n" +
                "Do you want to go [[Left]], [[Right]], [[Back to the start|Start]], or [[Skip this nonsense|dave]]?\n" +
                ";;\n" +
                "this is an comment\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "\n" +
                "::Right [dave] <128.0,-39.0> //\n" +
                "You went to the right, but the path leads you back to [[dave]].\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "::Right [dave] <128.0,-39.0> //\n" +
                "This is also the right.\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "::dave [] <81.0,142.0> //oh hi dave\n" +
                "This passage is called dave.\n" +
                "\n" +
                "dave's content doesn't include any links to any other passages.\n" +
                "So I guess this counts as the end.\n" +
                ";;\n" +
                "yep it's the end\n" +
                "\n" +
                "wack\n" +
                ";;\n";

        // It should rename one of the 'Right' passages to 'Right_1'

        Set<String> dupeHeccPassageNames = new HashSet<>(Arrays.asList("Start","Another passage","Yet Another Passage","Left","Right","Right_1","dave"));

        OhHeccParser hp = new OhHeccParser(dupeHeccSample);

        Set<String> parsedNames = hp.getHeccMap().values().stream().map(SharedPassage::getPassageName).collect(Collectors.toSet());

        assertEquals(dupeHeccPassageNames, parsedNames);

    }

    /**
     * Testing that it will create a linked passage which doesn't exist yet (evaluating correctness via passage names)
     */
    @Test
    void testUndeclaredHecc(){
        String undeclaredHeccSample = "Hecc code generated by OH-HECC at Tue Dec 08 10:10:58 GMT 2020\n" +
                "\n" +
                "!Title: HECCSample\n" +
                "!Author: Rachel Lowe\n" +
                "!IFID: DE7B3D02-81BB-4C2A-82BA-7CA9398B2262\n" +
                "!start: Start\n" +
                "\n" +
                "\n" +
                "Note to author: only comments saved in this multiline comment area (lines starting with //) will be preserved by OH-HECC!\n" +
                "//                 multiline comment line 1\n" +
                "//                 line 2\n" +
                "//                 deez\n" +
                "//                 \n" +
                "//                 nutz\n" +
                "//                  lmao gottem!\n" +
                "\n" +
                "\n" +
                "end of metadata. game content starts below:\n" +
                "\n" +
                "::Start [] <-259.0,-205.0> //this is a comment //ayy lmao eecks dee\n" +
                "starting passage content goes here.\n" +
                "\n" +
                "The following line contains a link to \"Another passage\".\n" +
                "[[Another passage]]\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "::Another passage [yes theres tags] <30.0,-212.0> //this is another passage\n" +
                "congrats you clicked that link to get here, Another passage.\n" +
                "why not [[click this|Yet Another Passage]] as well?\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "::Left <-174.0,73.0> [dave] //the left option\n" +
                "You go to the left, but the path leads you back to [[dave]].\n" +
                ";;\n" +
                "\n" +
                "\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "::Yet Another Passage [] <-65.0,-60.0> //\n" +
                "woah you clicked that so you're now at Yet Another Passage.\n" +
                "\n" +
                "Do you want to go [[Left]], [[Right]], [[Back to the start|Start]], or [[Skip this nonsense|dave]]?\n" +
                ";;\n" +
                "this is an comment\n" +
                ";;\n" +
                "\n" +
                "\n" +
                "\n" +
                "::Right [dave] <128.0,-39.0> //\n" +
                "You went to the right, but the path leads you back to [[dave]].\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "::dave [] <81.0,142.0> //oh hi dave\n" +
                "This passage is called dave.\n" +
                "\n" +
                "dave's content doesn't include any links to any other passages.\n" +
                "Well, except from [[Kevin]].\n"+
                "So I guess this counts as the end.\n" +
                ";;\n" +
                "yep it's the end\n" +
                "\n" +
                "wack\n" +
                ";;\n";

        // it should notice the link to Kevin, who doesn't exist, and then make Kevin exist.

        Set<String> undeclaredHeccPassageNames = new HashSet<>(Arrays.asList("Start","Another passage","Yet Another Passage","Left","Right","dave","Kevin"));

        OhHeccParser hp = new OhHeccParser(undeclaredHeccSample);

        Set<String> parsedNames = hp.getHeccMap().values().stream().map(SharedPassage::getPassageName).collect(Collectors.toSet());

        assertEquals(undeclaredHeccPassageNames, parsedNames);

    }

    /**
     * Testing the thing where sometimes passage links end up in the passage tags
     */
    @Test
    void testLineEndMetadataStaysInTheLine(){

        String theHecc = "::Start [] <a,b> //\n" +
                "[[another passage]]\n" +
                "<3400,1343>\n" +
                ";;\n" +
                "\n" +
                ";;\n" +
                "::another passage //\n" +
                "[[Start]]\n" +
                "<-1000,-1000>\n" +
                ";;\n" +
                "\n" +
                ";;";

        OhHeccParser hp = new OhHeccParser(theHecc);

        Map<UUID, PassageEditingInterface> theMap = hp.getHeccMap();

        Optional<PassageEditingInterface> theOptionalStart = theMap.values().stream().filter(
                p -> p.getPassageName().equals("Start")
        ).findFirst();

        Optional<PassageEditingInterface> theOptionalOther = theMap.values().stream().filter(
                p2 -> p2.getPassageName().equals("another passage")
        ).findFirst();

        try {
            PassageEditingInterface theStart = null;
            if (theOptionalStart.isPresent()) {
                theStart = theOptionalStart.get();
            } else {
                fail("start passage doesnt actually exist!");
            }
            PassageEditingInterface theOtherOne = null;
            if (theOptionalOther.isPresent()) {
                theOtherOne = theOptionalOther.get();
            } else {
                fail("other passage doesnt actually exist!");
            }


            System.out.println("Asserting pos of start is 0,0");
            assertEquals(new Vector2D(0, 0), theStart.getPosition());
            System.out.println(theStart.getPosition());

            System.out.println("Asserting tags of start are empty");
            assertEquals(new ArrayList<String>(), theStart.getPassageTags());
            System.out.println(theStart.getPassageTags());

            System.out.println("and making sure the content of start is as expected");
            assertEquals("[[another passage]]\n<3400,1343>", theStart.getPassageContent());
            System.out.println(theStart.getPassageContent());

            System.out.println("---\n");

            System.out.println("Asserting pos of 2nd isnt -1000, -1000");
            assertNotEquals(new Vector2D(-1000, -1000), theOtherOne.getPosition());
            System.out.println(theOtherOne.getPosition());

            System.out.println("Asserting tags of the other one are empty");
            assertEquals(new ArrayList<String>(), theOtherOne.getPassageTags());
            System.out.println(theOtherOne.getPassageTags());

            System.out.println("and making sure the content of other is as expected");
            assertEquals("[[Start]]\n<-1000,-1000>", theOtherOne.getPassageContent());

            System.out.println(theOtherOne.getPassageContent());
            System.out.println("---\n");

        } catch (NullPointerException e){
            e.printStackTrace();
            fail("uh oh, didn't work");
        }


    }
}
