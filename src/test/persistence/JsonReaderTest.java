package persistence;

import model.BodyText;
import model.ReplacePair;
import model.ReplacementManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    private List<ReplacePair> emptyList;

    @BeforeEach
    void runBefore() {
        emptyList = new ArrayList<ReplacePair>();
    }

    @Test
    void testReaderNonExistentFileRM() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ReplacementManager repMan = reader.repManRead();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNonExistentFileBT() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BodyText bt = reader.bodyTextRead();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyReplacementManager() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyReplacementManager.json");
        try {
            ReplacementManager repMan = reader.repManRead();
            assertEquals(emptyList, repMan.getRepPairs());
            assertEquals(0, repMan.getRepPairs().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyBodyText() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBodyText.json");
        try {
            BodyText bt = reader.bodyTextRead();
            assertEquals("", bt.getText());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralReplacementManager() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralReplacementManager.json");
        try {
            ReplacementManager repMan = reader.repManRead();
            List<ReplacePair> repPairs = repMan.getRepPairs();
            assertEquals(2, repPairs.size());
            assertEquals(2, repMan.getRepPairs().size());
            assertEquals("hi", repPairs.get(0).getReplacee());
            assertEquals("hello", repPairs.get(0).getReplacer());
            assertEquals("hello", repPairs.get(1).getReplacee());
            assertEquals("howdy", repPairs.get(1).getReplacer());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBodyText() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBodyText.json");
        try {
            BodyText bt = reader.bodyTextRead();
            assertEquals("hi hello nihao", bt.getText());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
