package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] tiles2D;
    private int[][] tmp;
    private int[] tile1D;
    private int[][] goal2D;
    private int[] goal1D;
    int BLANK = 0;

    public Board(int[][] tiles) {
        tiles2D = new int[tiles.length][tiles.length];
        tile1D = new int[size() * size()];
        goal2D = new int[size()][size()];
        goal1D = new int[size() * size()];

        for (int i = 0; i < size(); i += 1) {
            for (int j = 0; j < size(); j += 1) {
                tiles2D[i][j] = tiles[i][j];
                tile1D[index1D(i, j)] = tiles[i][j];
                goal2D[i][j] = index1D(i, j) + 1;
                goal1D[index1D(i, j)] = index1D(i, j) + 1;
            }
        }
        goal2D[size() - 1][size() - 1] = BLANK;
        goal1D[size() * size() - 1] = BLANK;
    }

    private int index1D(int i, int j) {
        return i * size() + j;
    }

    private int[] index2D(int i) {
        int[] res = new int[2];
        res[0] = i / size();
        res[1] = i % size();
        return res;
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= size() || j < 0 || j > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (tiles2D[i][j] == BLANK) {return 0;}
        return tiles2D[i][j];
    }

    public int size() {
        return tiles2D.length;
    }

    /** @source:http://joshh.ug/neighbors.html. */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < size() * size(); i += 1) {
            if (goal1D[i] != BLANK && tile1D[i] != goal1D[i]) {
                count += 1;
            }
        }
        return count;
    }

    public int manhattan() {
        int[] goal2DIndex;
        int[] tile2DIndex;
        int distance = 0;

        for (int i = 0; i < size() * size(); i += 1) {
            for (int j = 0; j < size() * size(); j += 1) {
                if (goal1D[i] != BLANK && goal1D[i] == tile1D[j]) {
                    goal2DIndex = index2D(i);
                    tile2DIndex = index2D(j);
                    distance += calcDistance(goal2DIndex[0], goal2DIndex[1],
                            tile2DIndex[0], tile2DIndex[1]);
                }
            }
        }
        return distance;
    }

    private int calcDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        return false;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
