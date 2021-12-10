package marcomole00.d09;

import java.util.Comparator;

public class Point {
    int x;
    int y;
    int height;
    boolean isLowPoint;
    int basinSize; //used only if lowpoint is true;
    int xBasin; //
    int yBasin;

    public Point(int x, int y, int heightPar) {
        this.x = x;
        this.y = y;
        this.height = heightPar;
        basinSize = 0;
        xBasin=-1;
        yBasin= -1;
    }

    public Point(Point pointToCopy) {
        this(pointToCopy.getX(), pointToCopy.getY(), pointToCopy.getHeight());
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}


class SortByBasinSize implements Comparator<Point>
{
    @Override
    public int compare(Point o1, Point o2) {
        return -o1.basinSize +o2.basinSize;
    }
}
