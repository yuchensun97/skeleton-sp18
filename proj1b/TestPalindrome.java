import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    
    @Test
    public void testIsPalindrome() {
        String a = "a";
        String b = "racecar";
        String c = "noon";

        String d = "horse";
        String e = "rancor";
        String f = "aaaaab";

        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome(a));
        assertTrue(palindrome.isPalindrome(b));
        assertTrue(palindrome.isPalindrome(c));
        assertFalse(palindrome.isPalindrome(d));
        assertFalse(palindrome.isPalindrome(e));
        assertFalse(palindrome.isPalindrome(f));
    }
}
