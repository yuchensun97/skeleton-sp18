public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int head;
    private int tail;
    private int RFACTOR = 4;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }

    private void Resize(int new_size) {
        T[] resize = (T[]) new Object[new_size];
        System.arraycopy(items, head, resize, 0, size-tail);
        System.arraycopy(items, 0, resize, head, tail);
        head = 0;
        tail = size;
        items = resize;
    }

    public void addFirst(T item) {
        if (head == 0) {
            head = items.length - 1;
        }
        else{
            head -= 1;
        }
        items[head] = item;
        size += 1;
        if (head == tail) {
            Resize(size *RFACTOR);
        }
    }

    public void addLast(T item){
        items[tail] = item;
        size += 1;
        tail += 1;
        if (head == tail || tail == items.length) {
            Resize(size * RFACTOR);
        }
    }

    public boolean isEmpty(){
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if (size == 0) {
            return;
        }
        int curr = head;
        while (curr != tail) {
            System.out.print(items[curr] + " ");
            curr += 1;
            if (curr == items.length) {
                curr = 0;
            }
        }
    }

    public T removeFirst(){
        if (size == 0) {
            return null;
        }
        T first_item = items[head];
        head += 1;
        if (head == items.length) {
            head = 0;
        }
        size -= 1;
        if (size <= 0.25 * items.length && items.length >= 16) {
           Resize((int)(0.25 * items.length + 1)); 
        }
        return first_item;
    }

    public T removeLast(){
        if (size == 0) {
            return null;
        }
        T last_item = items[tail-1];
        tail -= 1;
        if (size <= 0.25 * items.length && items.length >= 16) {
            Resize((int)(0.25 * items.length + 1));
        }
        return last_item;
    }

    public T get(int index){
        if (size == 0) {
            return null;
        }
        index += head;
        if (index >= items.length) {
            index -= items.length;
        }
        return items[index];
    }
}