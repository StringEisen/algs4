import java.util.Iterator;

/**
 * using array API
 */
public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] s;
    private int N = 0;
    
    public FixedCapacityStack(int capacity) {
        s = (Item[]) new Object[capacity];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public void push(Item item) {
        s[N++] = item;
    }
    
    public Item pop() {
        Item item = s[--N];
        //prevent loitering
        s[N] = null;
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }
    
    /*
     * reverse the output order within the array
     */
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        
        public boolean hasNext() { return i > 0; }
        public void remove() { }
        public Item next() { return s[--i]; }
    }
}