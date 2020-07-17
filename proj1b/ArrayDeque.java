public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int head;
    private int tail;
    private int RFACTOR = 4;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }

    /**
     * Circular addition
     * @param index
     * @return results of circular addition
     */
    private int addOne(int index) {
        int newIndex =  (index + 1) % items.length;
        return newIndex;
    }

    private int minusOne(int index) {
        int newIndex = (index + items.length - 1) % items.length;
        return newIndex;
    }

    private int addition(int idxOne, int idxTwo) {
        int newIndex = (idxOne + idxTwo) % items.length;
        return newIndex;
    }

    /**
     * rearrange items & resize
     * @param num
     */
    private void reSize(int num) {
        T[] resize = (T[]) new Object[num];
        int curr = head;
        int i = 0;
        while (i < size) {
            resize[i] = items[curr];
            curr = addOne(curr);
            i += 1;
        }
        items = resize;
        head = 0;
        tail = size;
    }

    private void upScaling() {
        reSize(RFACTOR * items.length);
    }

    private void downScaling() {
        reSize(items.length / RFACTOR + 1);
    }

    @Override
    public void addFirst(T item) {
        head = minusOne(head);
        items[head] = item;
        size += 1;
        if (head == tail) {
            upScaling();
        }
    }

    @Override
    public void addLast(T item) {
        items[tail] = item;
        size += 1;
        tail = addOne(tail);
        if (head == tail) {
            upScaling();
        }
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        int curr = head;
        while (curr != tail) {
            System.out.print(items[curr] + " ");
            curr = addOne(curr);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T firstItem = items[head];
        items[head] = null;
        head = addOne(head);
        size -= 1;
        if (size <= items.length / RFACTOR && items.length >= 16) {
            downScaling();
        }
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        tail = minusOne(tail);
        T lastItem = items[tail];
        items[tail] = null;
        size -= 1;
        if (size <= items.length / RFACTOR && items.length >= 16) {
            downScaling();
        }
        return lastItem;
    }

    @Override
    public T get(int index) {
        if (size == 0) {
            return null;
        }
        index = addition(head, index);
        return items[index];
    }
}
