package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BodyTextTest {
    private BodyText testbt;
    private ReplacePair r1;
    private ReplacePair r2;
    private ReplacePair r3;

    @BeforeEach
    void runBefore() {
        testbt = new BodyText("hello, how are you?");
        r1 = new ReplacePair("hello", "hi");
        r2 = new ReplacePair("good day", "howdy");
        r3 = new ReplacePair("HELLO", "how");
    }

    @Test
    void testConstructor() {
        assertEquals("hello, how are you?", testbt.getText());
    }

    @Test
    void testReplace() {
        testbt.replace(r1);
        assertEquals("hi, how are you?", testbt.getText());
    }

    @Test
    void testReplaceIgnoreCase() {
        testbt.replaceIgnoreCase(r3);
        assertEquals("how, how are you?", testbt.getText());
    }

    @Test
    void testGetContainsReplacee() {
        assertTrue(testbt.getContainsReplacee(r1));
        assertFalse(testbt.getContainsReplacee(r2));
    }

    @Test
    void testGetContainsReplacer() {
        assertTrue(testbt.getContainsReplacer(r3));
        assertFalse(testbt.getContainsReplacer(r2));
    }

}
