import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;
    
    public RandomizedQueue() {
        a = (Item[]) New Object[2];
        N = 0;
    }
    
    