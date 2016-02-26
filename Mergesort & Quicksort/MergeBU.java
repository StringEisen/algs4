public class MergeBU {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);
    
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            // if i is exhausted, copy j elements only
            if (i > mid) a[k] = aux[j++];
            // if j is exhausted, copy i elements only
            else if (j > hi) a[k] = aux[j++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    
        assert isSorted(a, lo, hi);
    }
    
    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        // compare each two subarray of size 1 to lgN
        // total lgN passes
        for (int sz = 1; sz < N; sz = sz+sz)
            // compare each two subarray and then merge two subarray 
            // until reach the end of the whole array (index N-1)
            // total NlgN compares
            for (int lo = 0; lo < N-sz; lo += sz+sz)
            merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
    }
}