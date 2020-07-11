public class LinkedListDeque<T> {

    private static class IntNode<TT>{
        public IntNode<TT> prev; // the previous node
        public TT item; // data
        public IntNode<TT> next; // the next node

        public IntNode(TT t, IntNode<TT> p, IntNode<TT> n){
            item = t;
            prev = p;
            next = n;
        }
    }

    /** initializtion */
    private IntNode<T> sentinel;
    private int size;


    public LinkedListDeque() {
        /**
         * Initialize an empty list
         */
        sentinel = new IntNode<>(null,null,null); // how to initialize T type?
        sentinel.next = sentinel;
        sentinel.prev = sentinel.next;
        size = 0;
    }

    public LinkedListDeque(T item){
        /**
         * Initialize a deque with the front item
         */
        sentinel = new IntNode<>(null, null,null); // how to inialize T type?
        sentinel.next = new IntNode<>(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        /**
         * adds item to the front of the deque
         */
        sentinel.next = new IntNode<>(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        /**
         * adds item to the back of the deque
         */
        sentinel.prev = new IntNode<>(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        /**
         * returns true if deque is empty, false otherwise
         */
        if (size==0) {
            return true;
        }
        return false;
    }

    public int size() {
        /**
         * returns the number of items in the deque
         */
        return size;
    }

    public void printDeque() {
        /**
         * Prints the items in the deque from first to lase, separated by a space
         */
        IntNode<T> ptr = sentinel.next;
        while (!ptr.equals(sentinel)) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    public T removeFirst() {
        /**
         * removes and returns the item at the front of the deque.
         * If no such item existes, returns null
         */
        if (size == 0) {
            return null;
        }
        /**how to deal with type */
        T first_item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first_item;
    }

    public T removeLast() {
        /**
         * removes and returns the item at the back of the deque
         * If no such item exists, returns null
         */
        if (size == 0) {
            return null;
        }
        T last_item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return last_item;
    }

    public T get(int index) {
        /**
         * gets the item at the given index, starts from 0
         */
        if (size == 0) {
            return null;
        }
        IntNode<T> ptr = sentinel.next;
        T ith_item;
        int i = 0;
        while (i < index) {
            ptr = ptr.next;
            i += 1;
        }
        ith_item = ptr.item;
        return ith_item;
    }

    private T getRecursive(int index, IntNode<T> p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index-1, p.next);
    }

    public T getRecursive(int index){
        if (size == 0) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }
}