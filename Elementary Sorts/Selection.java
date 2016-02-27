/**
 * algs week 2 part 2 code in the slide
 * implement selection sort
 * created 20, Feb, 2016
 * @author Yixin Liu add comments in the code
 * @version 1.0
 */
public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            // selection sort is not stable
            // may have long-distance exchange that let one item past some equal item 
            for (int j = i + 1; j < N; j++)
                // exchange the minimum value in the subarray from position i + 1
                // to the end of the array
                if (less(a[j], a[min]))
                    min = j;
            exch(a, i, min);
        }
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
                