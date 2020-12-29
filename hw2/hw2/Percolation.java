package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF virtualBottom;
    private int size;
    private int numSites = 0;
    private boolean[][] isOpenBool;
    private int index;

    /**
     * percolation constructor
     * @param N
     */
    public Percolation(int N) {
        // DONE: create N-by-N grid, with all sites initially blocked
        size = N;
        grid = new WeightedQuickUnionUF(N * N + 2); // N*N for virtual top site, N*N + 1 for virtual bottom site
        virtualBottom = new WeightedQuickUnionUF(N * N + 2);
        isOpenBool = new boolean[N][N];
    }

    /**
     * convert the 2d index to 1d index
     * @param row
     * @param col
     * @return 1d index
     */
    private int getIndex(int row, int col) {
        index = row * size + col;
        return index;
    }

    /**
     * union the current sites and its open neighbour
     * @param row
     * @param col
     */
    private void unionNeighbour(int row, int col) {
        int currIdx = getIndex(row, col);
        int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // in up, down, left, right, 
        
        // if open the site in the top row, connect with virtual top site
        if (row == 0) {
            grid.union(currIdx, size * size);
            virtualBottom.union(currIdx, size * size);
        } else if (row == size - 1) {
            virtualBottom.union(currIdx, size * size + 1);
        }

        for (int i = 0; i < 4; i++) {
            int drow = dir[i][0];
            int dcol = dir[i][1];
            // check whether the neighbour exist
            int nRow = row + drow;
            int nCol = col + dcol;
            if (nRow < 0 || nRow >= size || nCol < 0 || nCol >= size) {
                continue;
            } else if (isOpenBool[nRow][nCol]) {
                // union the current grid and its open neighbour
                int nIdx = getIndex(nRow, nCol); // neighbour's 1d index
                grid.union(currIdx, nIdx);
                virtualBottom.union(currIdx, nIdx);
            }
        }

        // if the open the bottom site, check whether it is full after connection
        // if the bottom site is full, connect it with the virtual bottom site
        if (row == size - 1) {
            boolean fullBool = isFull(row, col);
            if (fullBool) {
                grid.union(currIdx, size * size + 1);
            }
        }
    }

    public void open(int row, int col) {
        // DONE: open the site (row, col) if it is not open already
        if (!isOpenBool[row][col]) {
            if (size == 1) {
                grid.union(0, 1);
                grid.union(0, 2);
                virtualBottom.union(0, 1);
                virtualBottom.union(0, 2);
            }else {
                unionNeighbour(row, col);
            }
            isOpenBool[row][col] = true;
            numSites++;
        }  
    }

    public boolean isOpen(int row, int col) {
        //DONE: is the site (row, col) open?
        return isOpenBool[row][col];
    }

    public boolean isFull(int row, int col) {
        //DONE: is the site (row, col) full?
        int currIdx = getIndex(row, col);
        boolean fullBool = grid.connected(currIdx, size * size);
        return fullBool;
    }

    public int numberOfOpenSites() {
        //DONE: number of open sites
        return numSites;
    }

    public boolean percolates() {
        //DONE: does the system percolate
        return virtualBottom.connected(size * size, size * size + 1);
    }

    public static void main(String[] args) {
        //DONE: use for unit testing
        // test 1
        // Percolation test = new Percolation(5);
        // test.open(3, 4);
        // test.open(2, 4);
        // test.open(2, 2);
        // assert test.isOpen(3, 4): "(3, 4) should open";
        // assert test.isOpen(2, 4): "(2, 4) should open";
        // assert test.isOpen(2, 2): "(2, 2) should open";
        // assert !test.isOpen(0, 4): "(0, 4) should not open";
        // assert !test.isFull(3, 4): "(0, 4) should not be full";
        // assert !test.isFull(2, 4): "(0, 4) should not be full";
        // assert !test.isFull(2, 2): "(0, 4) should not be full";
        // test.open(2, 3);
        // test.open(0, 2);
        // assert test.isFull(0, 2): "(0,2) should be full";
        // test.open(1, 2);
        // assert test.isFull(3, 4): "(3,4) should be full";
        // assert test.isFull(2, 4): "(2,4) should be full";
        // test.open(4, 4);
        // assert test.percolates(): "should percolate";
        // test.open(4, 2);
        // assert !test.isFull(4, 2): "(0, 4) should not be full";
        // assert test.numberOfOpenSites() == 8: "number of open sites should be 8";
        // System.out.println("All test pass!");

        // test 2
        // Percolation test = new Percolation(5);
        // test.open(1, 2);
        // test.open(2, 1);
        // test.open(0, 2);

        // test 3
        // Percolation test = new Percolation(6);
        // test.open(0, 5);
        // test.open(1, 5);
        // test.open(2, 5);
        // test.open(3, 5);
        // test.open(4, 5);
        // test.open(4, 4);
        // test.open(3, 3);
        // test.open(2, 3);
        // test.open(1, 3);
        // test.open(1, 2);
        // test.open(1, 1);
        // test.open(1, 0);
        // test.open(2, 0);
        // test.open(3, 0);
        // test.open(4, 0);
        // test.open(4, 1);
        // test.open(5, 1);
        // test.open(4, 3);
        // test.percolates();

        // test 4
        Percolation test = new Percolation(1);
        test.open(0, 0);
        test.percolates();
    }
}
