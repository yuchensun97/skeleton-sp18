package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int size;
    private int numTest;
    private double[] testRecord;
    private PercolationFactory test;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        //DONE: perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        numTest = T;
        test = pf;
        size = N;
        testRecord = new double[T];

        for (int i = 0; i < T; i++) {
            testRecord[i] = (double) singleExperiment() / (N * N);
        }
    }

    private int singleExperiment() {
        Percolation singleTest = test.make(size);
        while (!singleTest.percolates()) {
            int row = StdRandom.uniform(size);
            int col = StdRandom.uniform(size);
            singleTest.open(row, col);
        }
        return singleTest.numberOfOpenSites();
    }

    public double mean() {
        //DONE: sample mean of percolation threshold
        return StdStats.mean(testRecord);
    }

    public double stddev() {
        //DONE: sample standard deviation of percolation threshold
        return StdStats.stddev(testRecord);
    }

    public double confidenceLow() {
        //DONE: low endpoint of 95% confidence interval
        double mean = this.mean();
        double var = this.stddev();
        double lowBound = mean - 1.96 * var / Math.sqrt((double) numTest);
        return lowBound;
    }

    public double confidenceHigh() {
        //DONE: high endpoint of 95% confidence interval
        double mean = this.mean();
        double var = this.stddev();
        double highBound = mean + 1.96 * var / Math.sqrt((double) numTest);
        return highBound;
    }
}
