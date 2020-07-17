/**
 * Palindrome
 */
public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        char x;
        Deque<Character> wordDeque = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            x = word.charAt(i);
            wordDeque.addLast(x);
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> wordDeque = wordToDeque(word);
        boolean bool = true;
        while (wordDeque.size() > 1) {
            char firstItem = wordDeque.removeFirst();
            char lastItem = wordDeque.removeLast();
            bool = firstItem == lastItem;
            if (!bool) {
                return false;
            }
        }
        return true;
    }
}
