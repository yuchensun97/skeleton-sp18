public class offByN implements CharacterComparator {

    private int n;
    public offByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return x - y == n || y - x == n;
    }
}
