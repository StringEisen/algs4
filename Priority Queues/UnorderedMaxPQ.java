public class UnorderedMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;
    
    public UnorderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity]; 
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public void insert(Key x) {
        pq[N++] = x;
    }
    // take time proportional to N
    // find the maximum item and delete it
    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++)
            if (less(max, i)) max = i;
        // put the maximum to the end of the queue
        exch(max, N-1);
        // null out entry to prevent loitering
        return pq[--N];
    }
    
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }
    
    private void exch(int i, int j) {
        Key temp;
        temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
}