package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<Double>(10);
        assertTrue(arb.isEmpty());
        arb.enqueue(9.3);
        arb.enqueue(15.1);
        arb.enqueue(31.2);
        arb.enqueue(76.2);
        arb.enqueue(3.2);
        arb.enqueue(345.3);
        assertFalse(arb.isEmpty());
        assertFalse(arb.isFull());
        assertEquals(9.3, arb.peek(), 0.0);
        assertEquals(9.3, arb.dequeue(), 0.0);
        assertEquals(15.1, arb.dequeue(), 0.0);
        arb.enqueue(77.2);
        arb.enqueue(2.0);
        arb.enqueue(76.9);
        arb.enqueue(6.3);
        arb.enqueue(3.2);
        arb.enqueue(28.2);
        assertTrue(arb.isFull());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
