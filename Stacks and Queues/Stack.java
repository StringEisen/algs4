import java.util.Iterator;

/**
 * using linked stack API
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first = null;
    
    /* 
     * build inner class to direct reference to instance variables
     */
    private class Node {
        Item item;
        Node next;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        // set the instance variables
        first.item = item;
        first.next = oldfirst;
    }
    
    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }
    
    public Iterator<Item> iterator() { 
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() { return current != null; }
        public void remove() { }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}