import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

/**
 * error for /collinear/input9.txt
 */

public class FastCollinearPoints {
    private LineSegment[] segments;
    ArrayList<LineSegment> list = new ArrayList<LineSegment>();
    
    public FastCollinearPoints(Point[] points) {
        check(points);
        
        for (int i = 0; i < points.length; i++) {
            Comparator<Point> order = points[i].slopeOrder();
            Arrays.sort(points, order);
            // point i has the index 0 after sorting by itself
            for (int j = 1; j < points.length; ) {
                // j is the beginning of the adjacent 3 collinear points
                int n = j;
                while (j < points.length-1 && order.compare(points[j], points[j+1]) == 0) { j++; }
                if (j-n >= 2) {
                    Arrays.sort(points, n ,j+1);
                    //StdOut.println("lines " + n + " " + j);
                    //for (int e = n; e <= j; e++) {
                 
                        //StdOut.println(points[e]);
                    //}
                    Point[] p = new Point[3];
                    p[0] = points[0];
                    p[1] = points[n];
                    p[2] = points[j];
                    Arrays.sort(p);
                    int flag = 0;
                    // check repeat
                    for (int m = 0; m <list.size(); m++) {
                        if (list.get(m).toString().equals(p[0] + " -> " + p[2])) { flag=1; break; }
                    }
                    
                    if (flag == 0) {
                    //StdOut.println("points");
                    //for (int e = 0; e <= 2; e++) {
                        
                        //StdOut.println(p[e]);
                    //}
                    LineSegment line = new LineSegment(p[0], p[2]);
                    list.add(line);
                    }
                }
                //StdOut.println("j = " + j);
                j++;
            }
        }
    }
    
    public int numberOfSegments() {
        return segments.length;
    }
    
    public LineSegment[] segments() {
        segments = new LineSegment[list.size()];
        for (int i = 0; i < segments.length; i++) 
            segments[i] = list.get(i);
        return segments; 
    }
    
    private void check(Point[] points) {
        if (points == null) throw new NullPointerException("null array");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException("null point");
            for (int j = i+1; j < points.length; j++)
                if (points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException("repeated points");
        }
    }
    
    public static void main(String[] args) {
        
        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}