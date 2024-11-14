package persistence;

import model.ReplacePair;
import model.ReplacementManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRepMan(ReplacementManager repMan, List<ReplacePair> repPairs) {
        assertEquals(repPairs, repMan.getRepPairs());
    }
}
