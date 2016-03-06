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
    private ArrayList<LineSegment> list;
    
    public FastCollinearPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            Comparator<Point> order = points[i].slopeOrder();
            Arrays.sort(points, order);
            // check if any 3 or more adjacent points have equal slope
            for (int j = i+1; j < points.length; j++) {
                double slope = points[i].slopeTo(points[j]);
                if (points[i].slopeTo(points[j+1]) == slope && points[i].slopeTo(points[j+2]) == slope) {
                    LineSegment line = new LineSegment(points[i], points[j+2]);
                    list.add(line);
                }
            }
        }
    }
    
    public int numberOfSegments() {
        return list.size();
    }
    
    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[numberOfSegments()];
        for (int i = 0; i < segments.length; i++) 
            segments[i] = list.get(i);
        return segments;
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}