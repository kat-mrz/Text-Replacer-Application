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

public class JsonWriterTest {
    private List<ReplacePair> emptyList;
    private String text;

    @BeforeEach
    void runBefore() {
        emptyList = new ArrayList<ReplacePair>();
    }

    @Test
    void testWriterInvalidFileRM() {
        try {
            ReplacementManager repMan = new ReplacementManager();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterInvalidFileBT() {
        try {
            BodyText bt = new BodyText(text);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyReplacementManager() {
        try {
            ReplacementManager repMan = new ReplacementManager();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyReplacementManager.json");
            writer.open();
            writer.RMwrite(repMan);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyReplacementManager.json");
            repMan = reader.RMread();
            assertEquals(emptyList, repMan.getRepPairs());
            assertEquals(0, repMan.getRepPairs().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBodyText() {
        try {
            text = "hi hello";
            BodyText bt = new BodyText(text);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBodyText.json");
            writer.open();
            writer.BTwrite(bt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBodyText.json");
            bt = reader.BTread();
            assertEquals(text, bt.getText());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralReplacementManager() {
        try {
            ReplacementManager repMan = new ReplacementManager();
            ReplacePair rp1 = new ReplacePair("hello", "nihao");
            ReplacePair rp2 = new ReplacePair("hola", "bonjour");
            repMan.addRepPair(rp1);
            repMan.addRepPair(rp2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralReplacementManager.json");
            writer.open();
            writer.RMwrite(repMan);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralReplacementManager.json");
            repMan = reader.RMread();
            List<ReplacePair> repPairs = repMan.getRepPairs();
            assertEquals(2, repPairs.size());
            assertEquals("hello", repPairs.get(0).getReplacee());
            assertEquals("nihao", repPairs.get(0).getReplacer());
            assertEquals("hola", repPairs.get(1).getReplacee());
            assertEquals("bonjour", repPairs.get(1).getReplacer());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }    
}
