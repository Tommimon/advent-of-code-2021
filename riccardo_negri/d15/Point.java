package riccardo_negri.d15;

public class Point {
    private int x;
    private int y;
    private int dist;

    public Point (int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public int getDist () {
        return dist;
    }

    public void setX (int x) {
        this.x = x;
    }

    public void setY (int y) {
        this.y = y;
    }

    public void setDist (int dist) {
        this.dist = dist;
    }
}
