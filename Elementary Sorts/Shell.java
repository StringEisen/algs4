/**
 * algs week 2 part 2 code in the slide
 * implementing h-sort , with warning
 * created 21, Feb, 2016
 * @author Yixin add comments in the code
 * @version 1.0
 */
public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        // find the first h increment, biggest increment
        int h = 1;
        while (h < N/3) h = 3*h + 1;
        // lg3(N) times h-sort
        while (h >= 1) {
            // do insertion sort, from position h to the end
            for (int i = h; i < N; i++) {
                // begin from position h, compare previous positions with a[j]
                // each j decrement h positions
                // compare N/h times at most, ~N times
                // look back every h positions
                for (int j = i; j >= h && less(a[j], a[j-h]); j-=h)
                    exch(a, j, j-h);
            }
            // move to next increment
            h = h/3;
        }
    }
    
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
    
        