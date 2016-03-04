import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class BruteCollinearPoints {
    // contains all linesegments in this arraylist
    private ArrayList<LineSegment> segmentList;
    private LineSegment[] segments;
    
    private void check(Point[] points) {
        if (points == null) throw new NullPointerException("null array");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException("null point");
            for (int j = i+1; j < points.length; j++)
                if (points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException("repeated points");
        }
    }
    
    
    /**
     * finds all line segments containing 4 points
     * such as p->q->r->s
     * should include either p->s or s->p
     * should not include subsegments like p->r and q->r
     * constructor
     */
    public BruteCollinearPoints(Point[] points) {
        check(points);
        int l = points.length;
        segmentList = new ArrayList<LineSegment>();
        // the points array should be first sorted into ascending order to find endpoints
        Comparator<Point> order = points[0].slopeOrder();
        Arrays.sort(points, order);
        // should find all combinations of 4 points
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                for (int n = j + 1; n < l; n++) {
                    //Comparator<Point> order = points[i].slopeOrder();
                    if (order.compare(points[j], points[n]) != 0) continue;
                    for(int m = n + 1; m < l; m++) {
                        if (order.compare(points[n], points[m]) != 0) continue;
                        LineSegment line = new LineSegment(points[i], points[m]);
                        
                        segmentList.add(line);
                    }
                }
            }
        }
    }
    
    public int numberOfSegments() {
        return segmentList.size();
    }
    
    /**
     * note that each of LineSegment only contains two points, endpoints
     * needs alternation
     * @return LineSegment[] line segments that connects at least 4 points
     */
    public LineSegment[] segments() {
        segments = new LineSegment[numberOfSegments()];
        for (int i = 0; i < segments.length; i++) {
            segments[i] = segmentList.get(i);
        }
        return segments;
    }
}