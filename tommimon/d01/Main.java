package tommimon.d01;

import java.util.Iterator;

// main
public class Main {
    public static void main(String[] args) {
        Reports r = new Reports("tommimon/d01/input");
        int ris = 0;
        Iterator<Integer> w1 = r.window(1);
        while (w1.hasNext()) {
            ris = w1.next();
        }
        System.out.println(ris);
        Iterator<Integer> w2 = r.window(3);
        while (w2.hasNext()) {
            ris = w2.next();
        }
        System.out.println(ris);
    }
}
