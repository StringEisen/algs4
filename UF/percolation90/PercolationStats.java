import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;

/**
 * Perform a series of computational experiments, 
 * calculate the estimate of percolation threshold
 * created 15th, Feb, 2016
 * @author Yixin Liu
 * @Version 1.0
 * 
 */
public class PercolationStats {
    private double[] p;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;
    
    /**
     * construct an experiment that perform T independent experiments on a
     * N-by-N grid, and calculate the mean, 
     * standard deviation and 95% confidence interval of
     * percolation threshold
     * @para N size of the grid
     * @para T time of experiments
     * 
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException("illegal N or T");
        p = new double[T];
        // repeat the experiment T times
        for (int t = 0; t < T; t++) {
            // initialize all sites to be blocked
            Percolation experiment = new Percolation(N);
            int count = 0;
            while (!experiment.percolates()) {
                // choose a stie (row i, column j) uniformly at StdRandom
                // among all blocked sites
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (!experiment.isOpen(i, j)) {
                // open the site (row i, column j)
                    experiment.open(i, j);
                    count++;
                }
            }
            // calculate the fraction of sites opened when the system percolates,
            // this fraction provides an estimated percolation threshold
            // store the estimated percolation threshold into the array
            p[t] = (double) count/(double) (N * N);
        }
        // calculate the mean, standard deviation and 95% confidence interval
        this.mean = StdStats.mean(p);
        this.stddev = StdStats.stddev(p);
        this.confidenceLo = this.mean - (1.96 * this.stddev / Math.sqrt(T));
        this.confidenceHi = this.mean + (1.96 * this.stddev / Math.sqrt(T));
    }
    
    /**
     * return the mean of estimated threshold
     */
    public double mean() {
        return this.mean;
    }
    
    /**
     * return the standard deviation of estimated threshold
     */
    public double stddev() {
        return this.stddev;
    }
    
    /**
     * return the low endpoint of 95% confidence threshold
     */
    public double confidenceLo() {
        return this.confidenceLo;
    }
    
    /**
     * return the high endpoint of 95% confidence threshold
     */
    public double confidenceHi() {
        return this.confidenceHi;
    }
    
    /**
     * take two command-line arguments N and T to perform T
     * independent computational experiments on a N-by-N grid
     * 
     */
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats percolation = new PercolationStats(N, T);
        System.out.println("mean                    = " + percolation.mean());
        System.out.println("stddev                  = " + percolation.stddev());
        System.out.println("95% confidence interval = " + percolation.confidenceLo()
                                   + ", " + percolation.confidenceHi());
    }
}