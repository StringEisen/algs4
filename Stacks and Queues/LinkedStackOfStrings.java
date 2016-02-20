public class LinkedStackOfStrings {
    private Node first = null;
    
    /* 
     * build inner class to direct reference to instance variables
     */
    private class Node {
        String item;
        Node next;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        // set the instance variables
        first.item = item;
        first.next = oldfirst;
    }
    
    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }
}