package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BodyTextTest {
    private BodyText b;
    private ReplacePair r;

    @BeforeEach
    void runBefore() {
        b = new BodyText("hello, how are you?");
        r = new ReplacePair("hello", "hi");
    }

    @Test
    void testConstructor() {
        assertEquals("hello, how are you?", b.getText());
    }

    @Test
    void testReplace() {
        b.replace();
        assertEquals("hi, how are you?", b.getText());
    }

    @Test
    void testGetContainsReplacee() {
        assertTrue(b.getContainsReplacee(r));
    }

}
