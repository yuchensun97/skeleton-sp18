/** Performs some basic linked list tests. */
public class ArrayDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
    }

    public static boolean checkIndex(int expected, int actual) {
		if (expected != actual) {
			System.out.println("get() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
    }

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");

		ArrayDeque<String> lld1 = new ArrayDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
    }

    public static void circularTest() {
        System.out.println("Running circular test.");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        lld1.addFirst(11);
        lld1.addLast(22);
        lld1.addLast(24);
		// should not be empty 
        passed = checkEmpty(false, lld1.isEmpty()) && passed;
        passed = checkSize(4, lld1.size()) && passed;
        passed = checkIndex(22, lld1.get(2)) && passed;

        for (int i = 0; i < 4; i++) {
            lld1.removeFirst();
        }
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
    }
    
    public static void resizeTest() {

		System.out.println("Running resizeTest test.");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

        for (int i = 0; i < 8; i++) {
            lld1.addFirst(i);
        }
		
		// should not be empty 
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addFirst(8);
		// should not be empty 
        passed = checkEmpty(false, lld1.isEmpty()) && passed;
        passed = checkSize(9, lld1.size()) && passed;
        passed = checkIndex(4, lld1.get(4)) && passed;

        lld1.addLast(9);
        // should not be empty 
        passed = checkEmpty(false, lld1.isEmpty()) && passed;
        passed = checkSize(10, lld1.size()) && passed;
        passed = checkIndex(9, lld1.get(9)) && passed;

		printTestStatus(passed);
    }
    
    public static void resizeNotNullTest() {

		System.out.println("Running resizeNotNullTest test.");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

        for (int i = 0; i < 8; i++) {
            lld1.addFirst(i);
        }
		
		// should not be empty 
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should not be empty 
        passed = checkEmpty(false, lld1.isEmpty()) && passed;
        passed = checkSize(7, lld1.size()) && passed;
        passed = checkIndex(6, lld1.get(0)) && passed;

        lld1.removeLast();
        // should not be empty 
        passed = checkEmpty(false, lld1.isEmpty()) && passed;
        passed = checkSize(6, lld1.size()) && passed;
        passed = checkIndex(1, lld1.get(5)) && passed;

		printTestStatus(passed);
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
        addRemoveTest();
        circularTest();
        resizeTest();
        resizeNotNullTest();
	}
} 