package tommimon.d01;

public class Window {
    Reports r;
    int size;
    int pos;
    int content;

    public Window(Reports r, int size) {
        this.r = r;
        this.size = size;
        this.pos = 0;
        content = 0;
        for(int i = pos; i < pos + size; i++) {
            content += r.get(i);
        }
    }

    void step() {
        content -= r.get(pos);
        content += r.get(pos + size);
        pos++;
    }

    int countStep() {
        int old = content;
        step();
        return content > old ? 1 : 0;
    }

    public int count() {
        int total = 0;
        while (pos < r.size() - size)
            total += countStep();
        return total;
    }
}
