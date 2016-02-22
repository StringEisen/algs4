/******************************************************************************
 *  Compilation:  javac ResizingArrayStackWithReflection.java
 *  Execution:    java ResizingArrayStackWithReflection < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
 *  
 *  Stack implementation with a resizing array.
 *
 *  % more tobe.txt 
 *  to be or not to - be - - that - - - is
 *
 *  % java ResizingArrayStackWithReflection < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 *  Written by Bruno Lehouque.
 *
 ******************************************************************************/

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A LIFO Stack using a resizeable array.
 */
public class ResizingArrayStackWithReflection<Item> implements Iterable<Item> {
    
    private Class<Item[]> itemArrayClass;
    private Item[] array;
    private int N = 0;

    public ResizingArrayStackWithReflection(Class<Item[]> itemArrayClass) {
        this.itemArrayClass = itemArrayClass;
        array = itemArrayClass.cast(Array.newInstance(itemArrayClass.getComponentType(), 1));
    }

    /**
     * Adds a non-{@code null} element on top of the Stack.
     * 
     * @param item
     *      the element which is to be added to the Stack
     * 
     * @throws IllegalArgumentException if {@code item} is {@code null}.
     */
    public void push(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null item.");
        if (N == array.length)
            resize(array.length * 2);
        array[N++] = item;
    }

    /**
     * Removes and returns the top element of the Stack.
     * 
     * @return the top element of the Stack
     * 
     * @throws NoSuchElementException if the Stack is empty
     */
    public Item pop() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = array[--N];
        array[N] = null;
        if ((N > 0) && (N <= array.length / 4))
            resize(array.length / 2);
        return item;
    }

    /**
     * Returns, but doesn't remove, the top element of the Stack.
     * 
     * @return the top element of the Stack
     * 
     * @throws NoSuchElementException if the Stack is empty
     */
    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return array[N - 1];
    }

    /**
     * Returns {@code true} if the Stack is empty.
     * 
     * @return {@code true} if the Stack is empty
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the size of the Stack.
     * 
     * @return the size of the Stack
     */
    public int size() {
        return N;
    }
    
    /**
     * Returns an iterator which iterates over the Stack from the top element
     * to the bottom element.
     * 
     * @return an iterator which iterates over the Stack from the top element
     * to the bottom element
     */
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private void resize(int size) {
        Item[] newarray = itemArrayClass.cast(Array.newInstance(itemArrayClass.getComponentType(), size));
        for (int i = 0; i < N; i++)
            newarray[i] = array[i];
        array = newarray;
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        
        private int i = N-1;

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return array[i--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported.");
        }
        
    }

    /**
     * Test client (copied from ResizingArrayStack).
     */
    public static void main(String[] args) {
        ResizingArrayStackWithReflection<String> s = new ResizingArrayStackWithReflection<String>(String[].class);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
