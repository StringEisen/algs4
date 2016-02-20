import java.util.Iterator;

public class ResizingArrayStack<Item> {
    private Item[] s;
    // number of item in the stack
    private int N = 0;
    
    /*
     * initalize a new array with size one
     */
    public ResizingArrayStack() {
        // set a new array with size one
        s = (Item[]) new Object[1];
    }
    
    /*
     * push the item into the stack
     * and resize the capacity if the stack will overflow
     * resize method is only called when the length of stack 
     * (N: index of the next item being pushed) is a power of 2
     * @para item the pushed item
     */
    public void push(Item item) {
        // if the number of item in the stack equals to to its capacity
        // resize the capacity of the stack to twice of the original one
        if (N == s.length) resize(2 * s.length);
        // push the item into the stack
        s[N++] = item;
    }
    
    /*
     * after popping an item
     * when the number of not null item in a array is 25%
     * of the whole size, half the capacity of the array
     */
    public Item pop() {
        // decrement N: equals to the index of Nth item
        // then assign the string that will be poped
        Item item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return item;
    }
    
    /*
     * resize the array to its new capacity
     * @para capacity new capacity
     */
    public void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
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