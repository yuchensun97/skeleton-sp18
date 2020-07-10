public class LinkedListDeque {

    private static class IntNode{
        public IntNode prev; // the previous node
        public int item; // data
        public IntNode next; // the next node

        public IntNode(int t,IntNode p,IntNode n){
            item = t;
            prev = p;
            next = n;
        }
    }

    /** initializtion */
    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        /**
         * Initialize an empty list
         */
        sentinel = new IntNode(99,null,null); // how to initialize T type?
        sentinel.next = sentinel;
        sentinel.prev = sentinel.next;
        size = 0;
    }

    public LinkedListDeque(int item){
        /**
         * Initialize a deque with the front item
         */
        sentinel = new IntNode(99, null,null); // how to inialize T type?
        sentinel.next = new IntNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(int item) {
        /**
         * adds item to the front of the deque
         */
        sentinel.next = new IntNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(int item) {
        /**
         * adds item to the back of the deque
         */
        sentinel.prev = new IntNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        /**
         * returns true if deque is empty, false otherwise
         */
        if (size==0) {
            return false;
        }
        return true;
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
        IntNode ptr = sentinel.next;
        String outprint = "";
        while (!ptr.equals(sentinel)) {
            /** To be filled */
            /** how to convert a generic type to String??? */
            outprint += Integer.toString(ptr.item) + " ";
            ptr = ptr.next;
        }
        System.out.println(outprint);
    }

    public Integer removeFirst() {
        /**
         * removes and returns the item at the front of the deque.
         * If no such item existes, returns null
         */
        if (size == 0) {
            return null;
        }
        /**how to deal with type */
        int first_item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first_item;
    }

    public Integer removeLast() {
        /**
         * removes and returns the item at the back of the deque
         * If no such item exists, returns null
         */
        if (size == 0) {
            return null;
        }
        int last_item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return last_item;
    }

    public Integer get(int index) {
        /**
         * gets the item at the given index, starts from 0
         */
        if (size == 0) {
            return null;
        }
        IntNode ptr = sentinel.next;
        int ith_item;
        int i = 0;
        while (true) {
            if (ptr.equals(sentinel)) {
                ptr = ptr.next;
                continue;
            }
            if (i == index) {
                ith_item = ptr.item;
                break;
            }
            i += 1;
            ptr = ptr.next;
        }
        return ith_item;
    }

    /**
     * For debugging
     * To be deleted
     */
    public static void main(String[] args) {
        LinkedListDeque L = new LinkedListDeque();
        L.addFirst(3);
        L.addFirst(6);
        L.addFirst(7);
        L.printDeque();
        L.addLast(8);
        L.addLast(2);
        L.addFirst(5);

        /** expexcted outputs: 5 7 6 3 8 2 */
        L.printDeque();

        /** expected output: 6 */
        System.out.println(L.size());
        
        /**expectd output: 3 */
        System.out.println(L.get(3));

        L.removeFirst();
        L.removeFirst();
        L.removeLast();
        L.removeLast();

        /** expexted outputs: 6 3 */
        L.printDeque();

        /** expected output: 2 */
        System.out.println(L.size());

        /**expected outputs: 6 */
        System.out.println(L.get(0));
    }
}