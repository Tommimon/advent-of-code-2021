package tommimon.d05;

import java.util.Vector;

public class Line {
    int x1;
    int y1;
    int x2;
    int y2;
    boolean horizontal;
    boolean vertical;
    boolean diagUp;
    public Vector<Integer[]> points = new Vector<>();

    public Line(String input) {
        String[] half = input.split(" -> ");
        String[] parts1 = half[0].split(",");
        String[] parts2 = half[1].split(",");
        x1 = Integer.parseInt(parts1[0]);
        y1 = Integer.parseInt(parts1[1]);
        x2 = Integer.parseInt(parts2[0]);
        y2 = Integer.parseInt(parts2[1]);
        horizontal = (y1 == y2);
        vertical = (x1 == x2);
        diagUp = (x1-x2 == y2-y1);
        if(vertical) {
            if(y1 > y2) {
                int t = y1;
                y1 = y2;
                y2 = t;
            }
        }
        else {
            if(x1 > x2) {
                int t = x1;
                x1 = x2;
                x2 = t;
                t = y1;
                y1 = y2;
                y2 = t;
            }
        }
        int t = 0;
        while (x1 + t <= x2 || y1 + t <= y2) {
            Integer[] newPoint = new Integer[2];
            newPoint[0] = Math.min(x1+t, x2);
            if(diagUp)
                newPoint[1] = Math.max(y1-t, y2);
            else
                newPoint[1] = Math.min(y1+t, y2);
            points.add(newPoint);
            t++;
        }
    }
}
