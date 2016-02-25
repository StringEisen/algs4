import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private int N;
    private Node first, last;
    
    private class Node {
        private Item item;
        private Node next;
        private Node pre;
    }
    
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return N;
    }
    
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.pre = null;
        first.next = oldfirst;
        if (oldfirst == null) last = first;
        else oldfirst.pre = first;
        N++;
    }
    
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.pre = oldlast;
        if (oldlast == null) first = last;
        else oldlast.next = last;
        N++;
    }
    
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("nothing to remove");
        Item item = first.item;
        if (first == last) { 
            first = null; 
            last = null; 
        }
        else { 
            first = first.next; 
            first.pre = null; 
        }
        N--;
        return item;
    }
    
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("nothing to remove");
        Item item = last.item;
        if (last == first) { 
            last = null; 
            first = null; 
        }
        else { 
            last = last.pre; 
            last.next = null; 
        }
        N--;
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() { return current != null; }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("no next element");
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove() { throw new UnsupportedOperationException("unsupported operation"); }
    }
    
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) deque.addFirst(item);
            else if (!deque.isEmpty()) StdOut.print(deque.removeLast() + " ");
        }
        StdOut.println("(" + deque.size() + " left on deque)");
    }
}