import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new offByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        char a = 'h';
        char b = 'i';
        char c = '8';
        char d = 'g';
        assertTrue(offByOne.equalChars(a, b));
        assertTrue(offByOne.equalChars(a, d));
        assertFalse(offByOne.equalChars(a, c));
        assertFalse(offByOne.equalChars(c, d));
    }
}
