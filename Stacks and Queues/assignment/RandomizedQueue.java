import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    // number of place not null in the array (number of items)
    private int N;
    private int first, last;
    
    public RandomizedQueue() {
        q = (Item[]) New Object[2];
        N = 0;
        first = 0;
        last = 0;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    private void resize(int capacity) {
        Item[] temp = new (Item[]) Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = N;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("cannot add null item");
        // double the size of array if whole array is full
        if (N == q.length) resize(q.length * 2);
        q[last++] = item;
        // after enqueue, if only the last position in the array is full,
        // take it to the first position to enable the empty space 
        // in the array is waiting for enqueue into the next one
        if (last == q.length) last = 0;
        N++;
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("nothing to dequeue");
        int label = StdRandom.uniform(first, last + 1);
        Item item = q[label];
        q[label] = null;
        N--;
        if (N > 0 && N == q.length/4) resize(q.length/2);
        return item;
    }
    
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("nothing to sample");
        int label = StdRandom.unifrom(first, last + 1);
        Item item = q[label];
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }
    
    private class RandomIterator implements Iterator<Item> {
        // copy not null elements into a new array
        private Item[N] a;
        for (int i = 0; i < N; i++) {
            a[i] = p[(first + i) % q.length];
        }
        // create a rearranged array shuffle 
        private Item[] shuffle = StdRandom.shuffle(a);
        private int i = 0;
        public boolean hasNext() { return i < N; }
        public Item next() {
            if (isEmpty()) throw new NoSuchElementException();
            Item item = shuffle[(first + i) % shuffle.length];
            i++;
            return item;
        }
        public void remove() { throw new UnsupportedOperationException("no such method"); }
    }
    
    public static void main(String[] args) {
    }
}
    