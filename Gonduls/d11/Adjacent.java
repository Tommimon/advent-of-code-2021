package Gonduls.d11;

import Gonduls.d02.Point2d;

import java.util.ArrayList;
import java.util.List;

public class Adjacent {

    public static List<Point2d> getAdjacent(Point2d point, final int MAXX, final int MAXY, final boolean diagonal){
        List<Point2d> adjacent = new ArrayList<>();

        if(point.x > 0) {
            adjacent.add(new Point2d(point.x - 1, point.y));
            if(diagonal){
                if(point.y > 0)
                    adjacent.add(new Point2d(point.x-1, point.y - 1));
                if(point.y < MAXY - 1)
                    adjacent.add(new Point2d(point.x-1, point.y + 1));
            }
        }
        if(point.x < MAXX -1) {
            adjacent.add(new Point2d(point.x + 1, point.y));
            if(diagonal){
                if(point.y > 0)
                    adjacent.add(new Point2d(point.x+1, point.y - 1));
                if(point.y < MAXY - 1)
                    adjacent.add(new Point2d(point.x+1, point.y + 1));
            }
        }
        if(point.y > 0)
            adjacent.add(new Point2d(point.x, point.y - 1));
        if(point.y < MAXY - 1)
            adjacent.add(new Point2d(point.x, point.y + 1));

        return adjacent;
    }
}
