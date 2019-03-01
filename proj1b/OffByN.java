public class OffByN implements CharacterComparator {
    private int offByNum;

    public OffByN(int N) {
        offByNum = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return x - y == offByNum || y - x == offByNum;
    }
}