import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {
    
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        while (!StdIn.isEmpty()) {
            // read one string
            String s = StdIn.readString();
            if      (s.equals("("))            ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                if (op.equals("*")) vals.push(vals.pop() * vals.pop());
            }
            // parse input values into double
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
                