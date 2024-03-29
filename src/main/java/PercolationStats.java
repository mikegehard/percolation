public class PercolationStats {
    private double[] runs;
    private int T;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        runs = new double[T];
        for (int i = 0; i < T; i++) {
            runs[i] = calculatePercolationThreshold(N);
        }
    }

    private double calculatePercolationThreshold(int N) {
        Percolation perc = new Percolation(N);
        double numberOfSitesOpen = 0.0;
        int x, y;

        while (!perc.percolates()) {
            x = StdRandom.uniform(N) + 1;
            y = StdRandom.uniform(N) + 1;

            if (!perc.isOpen(x, y)) {
                perc.open(x, y);
                numberOfSitesOpen += 1.0;
            }
        }
        return numberOfSitesOpen / (N * N);
    }

    public double mean() {
        return StdStats.mean(runs);
    }

    public double stddev() {
        return StdStats.stddev(runs);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(this.T));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(this.T));
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.println("mean = " + stats.mean());
        StdOut.println("stddev = " + stats.stddev());
        StdOut.println("95% confidence interval =" + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}
