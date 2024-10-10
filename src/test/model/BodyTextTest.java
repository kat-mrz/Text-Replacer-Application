package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BodyTextTest {
    private BodyText b;
    private ReplacePair r1;
    private ReplacePair r2;
    private ReplacePair r3;

    @BeforeEach
    void runBefore() {
        b = new BodyText("hello, how are you?");
        r1 = new ReplacePair("hello", "hi");
        r2 = new ReplacePair("good day", "howdy");
        r3 = new ReplacePair("HELLO", "hi");
    }

    @Test
    void testConstructor() {
        assertEquals("hello, how are you?", b.getText());
    }

    @Test
    void testReplace() {
        b.replace(r1);
        assertEquals("hi, how are you?", b.getText());
    }

    @Test
    void testReplaceIgnoreCase() {
        b.replaceIgnoreCase(r3);
        assertEquals("hi, how are you?", b.getText());
    }

    @Test
    void testGetContainsReplacee() {
        assertTrue(b.getContainsReplacee(r1));
        assertFalse(b.getContainsReplacee(r2));
    }

}
