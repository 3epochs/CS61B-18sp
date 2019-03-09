package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int virtualTop = 0;
    private int N;
    private int virtualBottom;
    private boolean[] grid;
    private boolean isPercolated;
    private int numOpened;
    private WeightedQuickUnionUF unionInstance;
    private WeightedQuickUnionUF backWash;

    /** Constructor, use two virtual node to achieve constant time. */
    public Percolation(int N) {
        if (N < 1) {
            throw new java.lang.IndexOutOfBoundsException("illegal argument");
        }
        this.N = N;
        int gridLength = N * N + 2;
        virtualBottom = gridLength -1;
        isPercolated = false;
        unionInstance = new WeightedQuickUnionUF(gridLength);
        backWash = new WeightedQuickUnionUF(gridLength - 1);
        grid = new boolean[gridLength];
        numOpened = 0;
    }

    /** Convert grid index to a number. */
    private int indices2num(int i, int j) {
        return i * N + j + 1;
    }

    /** Open a grid
     *  first, check the index;
     *  second, if it is already open, just return;
     *  third, if on the top or on the bottom, union this gird with virtual top/ bottom;
     *  forth, check nearby grid, union this with already opened nearby grid;
     */
    public void open(int row, int col) {
        isValid(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int gridIndex = indices2num(row, col);
        if (0 == row) {
            unionInstance.union(virtualTop, gridIndex);
            backWash.union(virtualTop, gridIndex);
        }
        if (N-1 == row) {
            unionInstance.union(virtualBottom, gridIndex);
        }
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        for (int i = 0; i < 4; i += 1) {
            int posX = row + dx[i];
            int posY = col + dx[i];
            if (isPosValid(posX, posY) && isOpen(posX, posY)) {
                int posIndex = indices2num(posX, posY);
                unionInstance.union(gridIndex, posIndex);
                backWash.union(gridIndex, posIndex);
            }
        }
        numOpened += 1;

    }

    /** functions help check indices when we deal with nearby grids. */
    private boolean isPosValid(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            return false;
        }
        return true;
    }

    /** check indices. */
    private void isValid(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    /** check whether a grid is already open. */
    public boolean isOpen(int row, int col) {
        isValid(row, col);
        return grid[indices2num(row, col)] == true;
    }

    /** check whether a specific grid is full of water. */
    public boolean isFull(int row, int col) {
        isValid(row, col);
        return backWash.connected(virtualTop, indices2num(row, col));
    }

    /** return num of opened grids, takes O(N) time complexity. */
    public int numberOfOpenSites() {
        return numOpened;
    }

    /** check whether the whole system is percolated. */
    public boolean percolates() {
        if (isPercolated) {
            return true;
        }
        if (unionInstance.connected(virtualTop, virtualBottom)) {
            isPercolated = true;
            return true;
        }
        return false;
    }
    public static void main(String[] args) {}

}
