package tommimon.d01;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Window implements Iterator<Integer> {
    Reports r;
    int size;
    int pos;
    int content;
    int count;

    public Window(Reports r, int size) {
        this.r = r;
        this.size = size;
        this.pos = 0;
        content = 0;
        count = 0;
        for(int i = pos; i < pos + size; i++) {
            content += r.get(i);
        }
    }

    public boolean hasNext() {
        return pos < r.size() - size;
    }

    void step() {
        content -= r.get(pos);
        content += r.get(pos + size);
        pos++;
    }

    public Integer next() throws NoSuchElementException {
        if(hasNext()) {
            int old = content;
            step();
            if(content > old)
                count++;
            return count;
        }
        else throw new NoSuchElementException();
    }
}
