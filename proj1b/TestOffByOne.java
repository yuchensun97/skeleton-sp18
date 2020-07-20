import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        char a = 'h';
        char b = 'i';
        char c = '8';
        char d = 'g';
        assertTrue(offByOne.equalChars(a, b));
        assertTrue(offByOne.equalChars(b, a));
        assertTrue(offByOne.equalChars(a, d));
        assertTrue(offByOne.equalChars('B','A'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('z', 'A'));
        assertFalse(offByOne.equalChars(a, c));
        assertFalse(offByOne.equalChars(c, d));
        assertFalse(offByOne.equalChars('H', 'J'));
        assertFalse(offByOne.equalChars('%', '!'));
    }
}
