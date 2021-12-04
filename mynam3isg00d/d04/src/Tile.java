package mynam3isg00d.d04.src;
public class Tile {
    int val;
    boolean marked;

    Tile(int n) {
        val = n;
        marked = false;
    }

    public void mark() {
        marked = true;
    }
}
