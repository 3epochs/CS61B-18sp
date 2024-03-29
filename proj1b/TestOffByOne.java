import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        //assertTrue(offByOne.equalChars('x', 'x'));
        assertTrue(offByOne.equalChars('x','y'));
        assertFalse(offByOne.equalChars('x', 'z'));

    }
} //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. *