import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * this version cannot avoid dequeuing null item 
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    // number of place not null in the array (number of items)
    private int N;
    private int first, last;
    
    public RandomizedQueue() {
        q = (Item[]) new Object[2];
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
        Item[] temp = (Item[]) new Object[capacity];
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
        int label = StdRandom.uniform(first, last + 1);
        Item item = q[label];
        return item;
    }
    
    public Iterator<Item> iterator() {
        q = shuffle();
        return new RandomIterator();
    }
    
    // copy not null elements into a new array
    private Item[] shuffle() {
        Item[] a;
        a = (Item[]) new Object[N];
        for (int j = 0; j < N; j++) {
            a[j] = q[(first + j) % q.length];
        }
        // rearrange array
        StdRandom.shuffle(a);
        return a;
    }
    
    private class RandomIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() { return i < N; }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(first + i) % q.length];
            i++;
            return item;
        }
        public void remove() { throw new UnsupportedOperationException("no such method"); }
    }
    
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-")) q.enqueue(s);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.print(q.size() + "items remains");
    }
}
    