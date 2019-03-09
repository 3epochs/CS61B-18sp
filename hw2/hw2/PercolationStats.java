package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import java.util.Random;

public class PercolationStats {
    private static double[] scores;
    private double sum = 0;
    private Random RANDOM = new Random(73);
    private Percolation[] pers;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Illegal Arguments");
        }
        scores = new double[T];
        for (int i = 0; i < T; i += 1) {
            pers[i] = pf.make(N);
            while (! pers[i].percolates()) {
                int a = RANDOM.nextInt(N);
                int b = RANDOM.nextInt(N);
                if (! pers[i].isOpen(a, b)) {
                    pers[i].open(a, b);
                }
            }
            scores[i] = ((double) pers[i].numberOfOpenSites()) / (N * N);
            sum += scores[i];
        }
    }



    public double mean() {
        return sum / scores.length;
    }

    public double stddev() {
        double mean = mean();
        double tmp = 0;
        for (int i = 0; i < scores.length; i += 1) {
            sum += (scores[i] - mean) * (scores[i] - mean);
        }
        double tmp1 = tmp / (scores.length - 1);
        return Math.pow(tmp1, 0.5);
    }

    public double confidenceLow() {
        double mu = mean();
        double sigma = stddev();
        double sqrtT = Math.sqrt(scores.length);
        double res = mu - 1.96 * sigma / sqrtT;
        return res;
    }

    public double confidenceHigh() {
        double mu = mean();
        double sigma = stddev();
        double sqrtT = Math.sqrt(scores.length);
        double res = mu + 1.96 * sigma / sqrtT;
        return res;
    }

}
