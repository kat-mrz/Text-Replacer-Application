package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReplacementManagerTest {
    private ReplacementManager rpm;
    private List<ReplacePair> emptyList;
    private List<ReplacePair> rp1List;
    private List<ReplacePair> rp12List;
    private ReplacePair rp1;
    private ReplacePair rp2;

    @BeforeEach
    void runBefore() {
        rpm = new ReplacementManager();

        rp1 = new ReplacePair("hi", "hello");
        rp2 = new ReplacePair("hola", "nihao");

        emptyList = new ArrayList<>();

        rp1List = new ArrayList<>();
        rp1List.add(rp1);

        rp12List = new ArrayList<>();
        rp12List.add(rp1);
        rp12List.add(rp2);

    }

    @Test
    void testConstructor() {
        assertEquals(emptyList, rpm.getRepPairs());
        assertNull(rpm.getPossibleRepPair());
    }

    @Test
    void testAddRepPair() {
        //ReplacePair rp1 = new ReplacePair("hi", "hello");
        rpm.addRepPair(rp1);
        assertEquals(rp1, rpm.getLastRep());
        assertEquals(rp1List, rpm.getRepPairs());
        assertEquals(1, rpm.getRepPairs().size());
        assertEquals(rp1, rpm.getRepPairs().get(0));
    }

    @Test
    void testSetPossibleRepPair() {
        rpm.setPossibleRepPair(rp1);
        assertEquals(rp1, rpm.getPossibleRepPair());
        assertEquals("hi", rpm.getPossibleRepPair().getReplacee());
        assertEquals("hello", rpm.getPossibleRepPair().getReplacer());
    }

    @Test
    void testGetPossibleRepPair() {
        assertNull(rpm.getPossibleRepPair());
        rpm.setPossibleRepPair(rp2);
        assertEquals(rp2, rpm.getPossibleRepPair());
        assertEquals("hola", rpm.getPossibleRepPair().getReplacee());
        assertEquals("nihao", rpm.getPossibleRepPair().getReplacer());
    }

    @Test
    void testGetRepPairs() {
        rpm.addRepPair(rp1);
        assertEquals(rp1List, rpm.getRepPairs());
        assertEquals(1, rpm.getRepPairs().size());
        assertEquals(rp1, rpm.getRepPairs().get(0));
    }

    @Test
    void testGetLastRep() {
        rpm.addRepPair(rp1);
        rpm.addRepPair(rp2);
        assertEquals(rp12List, rpm.getRepPairs());
        assertEquals(rp2, rpm.getLastRep());
    }

    @Test
    void testGetLastRepEmptyList() {
        assertEquals(emptyList, rpm.getRepPairs());
        assertEquals(null, rpm.getLastRep());
    }
    
}
