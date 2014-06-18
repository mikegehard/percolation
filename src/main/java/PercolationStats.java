public class PercolationStats {
    private double[] runs;
    private Percolation perc;
    private int T;

    public PercolationStats(int N, int T) {
        this.T = T;
        runs = new double[T];
        for (int i = 0; i < T; i++) {
            runs[i] = calculatePercolationThreshold(N);
        }
    }

    private double calculatePercolationThreshold(int N) {
        perc = new Percolation(N);
        double numberOfSitesOpen = 0.0;
        int x, y;

        while (!perc.percolates()) {
            x = StdRandom.uniform(N) + 1;
            y = StdRandom.uniform(N) + 1;

            if (perc.isFull(x, y)) {
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
        return mean() - (1.96 * stddev()/Math.sqrt(this.T));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev()/Math.sqrt(this.T));
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
