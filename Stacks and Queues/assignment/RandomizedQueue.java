import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * this version avoid dequeuing null item by getLabel method
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    // number of place not null in the array (number of items)
    private int N;
    //private int last;
    
    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        N = 0;
        //last = 0;
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
            temp[i] = q[i];
        }
        q = temp;
        //last = N;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("cannot add null item");
        // double the size of array if whole array is full
        if (N == q.length) resize(q.length * 2);
        q[N++] = item;
    }
    
    /*private int getLabel() {
        // note that the index of the array is from 0~last - 1
        int label = StdRandom.uniform(last);
        while (q[label] == null) label = StdRandom.uniform(last);
        return label;
    }*/
    
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("nothing to dequeue");
        StdRandom.shuffle(q, 0, N - 1);
        Item item = q[N - 1];
        q[N - 1] = null;
        N--;
        if (N > 0 && N == q.length/4) resize(q.length/2);
        return item;
    }
    
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("nothing to sample");
        int label = StdRandom.uniform(N);
        Item item = q[label];
        return item;
    }
    
    public Iterator<Item> iterator() {
        StdRandom.shuffle(q, 0, N - 1);
        return new RandomIterator();
    }
    
    // copy not null elements into a new array
    /*private Item[] shuffle() {
        Item[] a;
        a = (Item[]) new Object[N];
        int n = 0;
        for (int j = 0; j < q.length; j++) {
            if (q[j] != null) a[n++] = q[j];
        }
        // rearrange array
        StdRandom.shuffle(a);
        return a;
    }*/
    
    private class RandomIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() { return i < N; }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[i];
            i++;
            return item;
        }
        public void remove() { throw new UnsupportedOperationException("no such method"); }
    }
    
    public static void main(String[] args) {
         RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("s");
        q.enqueue("t");
        q.enqueue("r");
        q.enqueue("e");
        q.enqueue("a");
        q.enqueue("m");
        StdOut.print("before dequeue: ");
        for (String s : q)
            StdOut.print(s + " ");
        StdOut.println(" ");
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.print("after dequeue: ");
        for (String s : q)
            StdOut.print(s + " ");
    }
}