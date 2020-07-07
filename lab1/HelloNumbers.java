public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0, total = 0;
        while (x<10) {
            total += x;
            x += 1;
            System.out.print(total + " ");
        }
    }
}