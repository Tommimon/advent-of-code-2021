package tommimon.d13;

public class Dot {
    int x;
    int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void foldX(int x) {
        if (this.x > x)
            this.x = x - (this.x - x);
    }

    public void foldY(int y) {
        if (this.y > y)
            this.y = y - (this.y - y);
    }

    public Integer[] toArray() {
        return new Integer[]{x, y};
    }
}
