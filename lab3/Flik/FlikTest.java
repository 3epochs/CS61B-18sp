import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {
    @Test
    public void isSameNumberTest() {
        assertEquals(true,Flik.isSameNumber(129, 129));
        assertTrue(Flik.isSameNumber(128, 128));
        assertNotEquals(true, Flik.isSameNumber(73, 0));
    }

//    @Test
//    public void
}
