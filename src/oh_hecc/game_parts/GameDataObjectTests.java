package oh_hecc.game_parts;

import oh_hecc.game_parts.metadata.EditableMetadata;
import oh_hecc.game_parts.passage.EditablePassage;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Some tests for the GameDataObject (mostly regarding the UUID that's supposed to point to the start passage)
 */
public class GameDataObjectTests {

    /**
     * Basically making sure the gamedataobject is as it should be without doing anything weird to it
     */
    @Test
    void testForStartPassageUUID(){
        final PassageEditingInterface[] samples = {new EditablePassage("Start", "ayy lmao\neecks dee", "nice\n\nmeme","[yes theres tags] < 256,96> //this is another passage"), new EditablePassage("ayy lmao", "[[Start]]","","")};

        final Map<UUID, PassageEditingInterface> passages = new HashMap<>();

        for (PassageEditingInterface e: samples) {
            passages.put(e.getPassageUUID(),e);
        }


        final GameDataObject gdo = new GameDataObject(
                passages,
                new EditableMetadata("A Hypertext Fiction","Anonymous"),
                Paths.get("Z://samplePath/ok.hecc")
        );

        assertTrue(gdo.getStartUUID().isPresent());

        assertEquals(
                samples[0].getPassageUUID(), gdo.getStartUUID().get()
        );

        assertEquals(2, gdo.getPassageMap().size());


    }

    /**
     * Making sure it behaves as expected if the start passage is deleted.
     */
    @Test
    void testStartPassageDelete(){
        final PassageEditingInterface[] samples = {new EditablePassage("Start", "ayy lmao\neecks dee", "nice\n\nmeme","[yes theres tags] < 256,96> //this is another passage"), new EditablePassage("ayy lmao", "[[Start]]","","")};

        final Map<UUID, PassageEditingInterface> passages = new HashMap<>();

        for (PassageEditingInterface e: samples) {
            passages.put(e.getPassageUUID(),e);
        }

        final GameDataObject gdo = new GameDataObject(
                passages,
                new EditableMetadata("A Hypertext Fiction","Anonymous"),
                Paths.get("Z://samplePath/ok.hecc")
        );

        System.out.println(gdo.toHecc());

        System.out.println("\ndelet start");

        gdo.thisPassageHasBeenDeleted(samples[0].getPassageUUID());

        assertTrue(gdo.getStartUUID().isPresent());

        assertEquals(
                samples[1].getPassageUUID(), gdo.getStartUUID().get()
        );

        assertEquals(1, gdo.getPassageMap().size());

        System.out.println(gdo.toHecc());

        System.out.println("\ndeleting the new replacement start too (should be replaced by a new one)");

        gdo.thisPassageHasBeenDeleted(samples[1].getPassageUUID());

        assertTrue(gdo.getStartUUID().isPresent());

        assertEquals(1, gdo.getPassageMap().size());

        System.out.println(gdo.toHecc());


    }
}
