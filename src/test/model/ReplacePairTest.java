package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReplacePairTest {
    private ReplacePair rp; 

    @BeforeEach
    void runBefore() {
        rp = new ReplacePair("hi", "hello");
    }

    @Test
    void testConstructor() {
        assertEquals("hi", rp.getReplacee());
        assertEquals("hello", rp.getReplacer());
    }

    @Test
    void testSetReplacer() {
        rp.setReplacer("howdy");
        assertEquals("howdy", rp.getReplacer());
        rp.setReplacer("hola");
        assertEquals("hola", rp.getReplacer());
    }

    @Test
    void testSetReplacee() {
        rp.setReplacee("hiya");
        assertEquals("hiya", rp.getReplacee());
        rp.setReplacee("nihao");
        assertEquals("nihao", rp.getReplacee());
    }
    
}
