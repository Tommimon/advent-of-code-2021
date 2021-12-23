package tommimon.d22;

public class Cube {
    public boolean on;
    Point start;
    Point end;
    boolean empty = false;

    static Long cubePieces(Point start, Point end) {
        return ((long) end.x - start.x + 1) * (end.y - start.y + 1) * (end.z - start.z + 1);
    }

    Long pieces() {
        return (on ? 1 : -1) * cubePieces(start, end);
    }

    public Cube(Point start, Point end, boolean on) {
        this.on = on;
        this.start = new Point(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.min(start.z, end.z));
        this.end = new Point(Math.max(start.x, end.x), Math.max(start.y, end.y), Math.max(start.z, end.z));
    }

    public Cube intersection(Cube other) {
        Point a = new Point(Math.max(start.x, other.start.x), Math.max(start.y, other.start.y), Math.max(start.z, other.start.z));
        Point b = new Point(Math.min(end.x, other.end.x), Math.min(end.y, other.end.y), Math.min(end.z, other.end.z));
        if(a.x > b.x || a.y > b.y || a.z > b.z) {
            Cube res = new Cube(new Point(0, 0, 0), new Point(0, 0, 0), true);
            res.empty = true;
            return res;
        }
        return new Cube(a ,b, !on);
    }

    boolean contains(Point p) {
        return start.x <= p.x && p.x <= end.x && start.y <= p.y && p.y <= end.y && start.z <= p.z && p.z <= end.z;
    }
}
