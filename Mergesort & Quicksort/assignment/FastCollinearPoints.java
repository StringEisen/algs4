import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<Point> points;
    
    public FastCollinearPoints(Point[] points) {
        for (Point p : points) {
            Comparator<Point> order = p.slopeOrder();
            Arrays.sort(points, order);
            // check if any 3 or more adjacent points have equal slope