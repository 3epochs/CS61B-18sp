import java.util.concurrent.DelayQueue;

public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> tmp = new ArrayDeque<>();
        for (int i= 0; i < word.length(); i+= 1) {
            tmp.addLast(word.charAt(i));
        }

        return tmp;
    }

/*    *//** implement with iteration. *//*
    public boolean isPalindrome(String word) {
        if (word.length() == 1) {
            return true;
        }
        Deque<Character> wordDeque = wordToDeque(word);
        int i = 0;
        int j = word.length() - 1;
        while ( i < j) {
            if (wordDeque.get(i) != wordDeque.get(j)) {
                return false;
            }
            i += 1;
            j -= 1;
        }
        return true;
    }*/

    /** implement with recursion. */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque);
    }

    /** helper function use recursion. */
    private boolean isPalindrome(Deque<Character> deque) {
        if (deque.size() == 1) {
            return true;
        }
        return deque.removeFirst() == deque.removeLast() && isPalindrome(deque);
    }

    /** new methods that overloads isPalindrome. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque  = wordToDeque(word);
        return isPalindrome(deque, cc);
    }

    /** helper function. */
    private boolean isPalindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() <= 1) {
            return true;
        }
        return cc.equalChars(deque.removeFirst(), deque.removeLast()) && isPalindrome(deque, cc);
    }

}
