import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {
    @Test
    public void TestisNumberSame() {
        assertTrue(Flik.isSameNumber(128,128));
        assertFalse(Flik.isSameNumber(2,0));
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("all", FlikTest.class);
    }
}