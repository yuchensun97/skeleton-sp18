package synthesizer;
import java.util.Iterator;

/**
 * BoundedQueue interface
 */
public interface BoundedQueue<T> extends Iterable<T> {

    public int capacity();    // return size of the buffer
    public int fillCount();    // return number of items currently in the buffer
    public void enqueue(T x);    // add item x to the end
    public T dequeue();    // delete and return item from the front
    public T peek();    // return (but not delete) item from the front
    public Iterator<T> iterator();

    /**
     * is the buffer empty
     * @return
     */
    default public boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }

    /**
     * is the buffer full?
     * @return
     */
    default public boolean isFull() {
        if (fillCount() == capacity()) {
            return true;
        }
        return false;
    }
}
