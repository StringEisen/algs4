/**
 * algs week 2 part 2, code in slide
 * implement insertion sort
 * created 20, Feb, 2016
 * @author Yixin Liu add some comments
 * @version 1.0
 */
public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // only sort first i elements in the array
            // do not see elements after that
            // is a stable sort: equal elements never past each other
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                //if (less(a[j], a[j-1]))
                exch(a, j, j-1);
                //else break;
            }
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