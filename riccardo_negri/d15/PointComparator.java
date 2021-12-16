package riccardo_negri.d15;

import java.util.Comparator;

public class PointComparator implements Comparator<Point> {
    @Override
    public int compare (Point point1, Point point2) {
        return point1.getDist() - point2.getDist();
    }
}