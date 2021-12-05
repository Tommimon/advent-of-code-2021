package mynam3isg00d.d05.src;
public class Line {
    int x1, x2, y1, y2;

    Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public boolean check() {
        return ((x1 == x2) || (y1 == y2));
    }
}
