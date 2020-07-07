/**
 * hw0
 */
public class hw0 {

    public static void drawTriangle(int N) {
        String newLine = "*";
        int i = 0;
        while (i < N) {
            System.out.print(newLine + "\n");
            newLine = newLine + "*";
            i += 1;
        }
    }

    public static int max(int[] m) {
        int max_num = m[0]; // initialization
        for (int i = 0; i < m.length; i++) {
            if (m[i] > max_num) {
                max_num = m[i];
            }
        }
        return max_num;
    }

    public static void windowPosSum(int[] a, int n) {
        /**your code here */
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                continue;
            } 
            for (int j = 1; j <= n; j++) {
                if (i+j >= a.length) {
                   break; 
                }
                a[i] += a[i+j];
            }
        }
    }

    public static void main(String[] args) {
        drawTriangle(10);
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.print(max(numbers));
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        //should print 4, -8, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}