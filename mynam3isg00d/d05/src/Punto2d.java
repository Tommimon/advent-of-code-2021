package mynam3isg00d.d05.src;
public class Punto2d {
    int x, y;
    Punto2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Punto2d)) {
            return false;
        }
        Punto2d p = (Punto2d) o;
        return (x == p.x && y == p.y);
    }
}
