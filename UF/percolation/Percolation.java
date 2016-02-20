import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

/**
 * model a percolation system by constructing 
 * a N-by-N grid
 * created 14th, Feb, 2016
 * @author Yixin Liu
 * @version 1.0
 * 
 */
public class Percolation {
    private boolean[] open;
    private int N;
    private WeightedQuickUnionUF uf;
    
    /**
     * construct a N-by-N grid with all sites blocked (full)
     * @para N size of the grid
     * 
     */
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("illegal N");
        this.N = N;
        // use WeightedQuickUnionUF to construct a new experiment environment
        uf = new WeightedQuickUnionUF(N * N + 2);
        open = new boolean[N * N];
        for (int i = 0; i < N * N; i++) {
            open[i] = false;
            if (i < N) 
                uf.union(i, N * N);
            else if (i >= N * (N - 1)) 
                uf.union(i, N * N + 1);
        }
    }
    
    /**
     * validate indices and throw exceptions
     * @para i row index
     * @para j column index
     * 
     */
    private void validate(int i, int j) {
        if (i <= 0 || i > N)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > N)
            throw new IndexOutOfBoundsException("column index j out of bounds");
    }
 
    private boolean acceptedIndex(int i, int j) {
        boolean flag = true;
        if (i <= 0 || i > N || j <= 0 || j > N) flag = false;
        else flag = true;
        return flag;
    }
    
    /**
     * convert row index and column index to 
     * a one-dimension array index
     * @para i row index
     * @para j column index
     * @return the index of the array
     */
    private int xyToID(int i, int j) {
        return (i - 1) * N + j - 1;
    }
    
    /**
     * open a site on the grid and connect it to its open neighbors
     * @para i row index
     * @para j column index
     * 
     */
    public void open(int i, int j) {
        validate(i, j);
        if (!open[xyToID(i, j)]) {
            open[xyToID(i, j)] = true;
            // use WeightedQuickUnionUF to connect this site to its open neighbors
             if (acceptedIndex(i - 1, j) && isOpen(i - 1, j)) uf.union(xyToID(i, j), xyToID(i - 1, j));
             if (acceptedIndex(i + 1, j) && isOpen(i + 1, j)) uf.union(xyToID(i, j), xyToID(i + 1, j));
             if (acceptedIndex(i, j - 1) && isOpen(i, j - 1)) uf.union(xyToID(i, j), xyToID(i, j - 1));
             if (acceptedIndex(i, j + 1) && isOpen(i, j + 1)) uf.union(xyToID(i, j), xyToID(i, j + 1));
            /*if (i > 1 && i < N && j > 1 && j < N) {
                if (isOpen(i - 1, j)) uf.union(xyToID(i, j), xyToID(i - 1, j));
                if (isOpen(i + 1, j)) uf.union(xyToID(i, j), xyToID(i + 1, j));
                if (isOpen(i, j - 1)) uf.union(xyToID(i, j), xyToID(i, j - 1));
                if (isOpen(i, j + 1)) uf.union(xyToID(i, j), xyToID(i, j + 1));
            }
            if (i == 1 && j > 1 && j < N) {
                if (isOpen(i + 1, j)) uf.union(xyToID(i, j), xyToID(i + 1, j));
                if (isOpen(i, j - 1)) uf.union(xyToID(i, j), xyToID(i, j - 1));
                if (isOpen(i, j + 1)) uf.union(xyToID(i, j), xyToID(i, j + 1));
            }
            if (i == N && j > 1 && j < N) {
                if (isOpen(i - 1, j)) uf.union(xyToID(i, j), xyToID(i - 1, j));
                if (isOpen(i, j - 1)) uf.union(xyToID(i, j), xyToID(i, j - 1));
                if (isOpen(i, j + 1)) uf.union(xyToID(i, j), xyToID(i, j + 1));
            }
            if (i > 1 && i < N && j == 1) {
                if (isOpen(i - 1, j)) uf.union(xyToID(i, j), xyToID(i - 1, j));
                if (isOpen(i + 1, j)) uf.union(xyToID(i, j), xyToID(i + 1, j));
                if (isOpen(i, j + 1)) uf.union(xyToID(i, j), xyToID(i, j + 1));
            }
            if (i > 1 && i < N && j == N) {
                if (isOpen(i - 1, j)) uf.union(xyToID(i, j), xyToID(i - 1, j));
                if (isOpen(i + 1, j)) uf.union(xyToID(i, j), xyToID(i + 1, j));
                if (isOpen(i, j - 1)) uf.union(xyToID(i, j), xyToID(i, j - 1));
            }
            if (i == 1 && j == 1) {
                if (isOpen(i, j + 1)) uf.union(xyToID(i, j), xyToID(i, j + 1));
                if (isOpen(i + 1, j)) uf.union(xyToID(i, j), xyToID(i + 1, j));
            }
            if (i == 1 && j == N) {
                if (isOpen(i + 1, j)) uf.union(xyToID(i, j), xyToID(i + 1, j));
                if (isOpen(i, j - 1)) uf.union(xyToID(i, j), xyToID(i, j - 1));
            }
            if (i == N && j == 1) {
                if (isOpen(i - 1, j)) uf.union(xyToID(i, j), xyToID(i - 1, j));
                if (isOpen(i, j + 1)) uf.union(xyToID(i, j), xyToID(i, j + 1));
            }
            if (i == N && j == N) {
                if (isOpen(i - 1, j)) uf.union(xyToID(i, j), xyToID(i - 1, j));
                if (isOpen(i, j - 1)) uf.union(xyToID(i, j), xyToID(i, j - 1));
            }*/
        }
        else return;
    }
    
    /**
     * judge whether a site is open
     * @para i row index of the site
     * @para j column index of the site
     * @return whether the site is open
     */
    public boolean isOpen(int i, int j) {
        validate(i, j);
        return open[xyToID(i, j)];
    }
    
    /**
     * judge whether a site is full
     * @para i row index of the site
     * @para j column index of the site
     * @return whether the site is full
     */
    public boolean isFull(int i, int j) {
        validate(i, j);
        boolean flag = false;
        int[] adjacent = adjacent(i, j);
        for (int m = 0; m < adjacent.length; m++) {
            if (adjacent[m] != -1) { if (open[adjacent[m]]) flag = true; }
        }
  return flag;
    }
 
 private int[] adjacent(int i, int j) {
  int[] adjacent = new int[4];
  if (acceptedIndex(i - 1, j)) { if (isFull(i - 1, j)) adjacent[0] = xyToID(i - 1, j); else adjacent[0] = -1; }
  if (acceptedIndex(i + 1, j)) { if (isFull(i + 1, j)) adjacent[1] = xyToID(i + 1, j); else adjacent[1] = -1; }
  if (acceptedIndex(i, j - 1)) { if (isFull(i, j - 1)) adjacent[2] = xyToID(i, j - 1); else adjacent[2] = -1; }
  if (acceptedIndex(i, j + 1)) { if (isFull(i, j + 1)) adjacent[3] = xyToID(i, j + 1); else adjacent[3] = -1; }
  return adjacent;
 }
    
    /**
     * judge whether the system percolates
     * @return whether the system percolates
     */
    public boolean percolates() {
        return uf.connected(N * N, N * N + 1);
    }
    
    /**
     * test
     */
    public static void main(String[] args) {
        Percolation experiment = new Percolation(20);
        int count = 0;
        while (!experiment.percolates()) {
            // choose a stie (row i, column j) uniformly at StdRandom
            // among all blocked sites
            int i = StdRandom.uniform(1, 20 + 1);
            int j = StdRandom.uniform(1, 20 + 1);
            if (experiment.isFull(i, j)) {
                // open the site (row i, column j)
                experiment.open(i, j);
                count++;
            }
        }
        System.out.println(experiment.percolates());
        System.out.println(count);
        System.out.println((double) count/(double) (20 * 20));
    }
}