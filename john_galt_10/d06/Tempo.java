package john_galt_10.d06;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tempo {
    private static int day = 0;
    private static List<Pesce> pesci = new ArrayList<>();

    public static void giornoInPiu() {
        day++;
        int daAggiungere = 0;

        //no!
        for (Pesce p : pesci) {
            boolean nuovo = p.update();
            if (nuovo == true) daAggiungere++;
        }

        /* nemmeno!
        Iterator<Pesce> itr = pesci.iterator();
        while (itr.hasNext()) {
            Pesce nuovo = itr.next().update();
            if (nuovo != null) pesci.add(nuovo);
        }
        */

        /*Iterator<Pesce> itr = pesci.iterator();
        while (itr.hasNext()) {
            boolean nuovo = itr.next().update();
            if (nuovo == true) daAggiungere++;
        }*/

        for (int i = 0; i < daAggiungere; i++) pesci.add(new Pesce(8));
    }

    public static void addPesce(int timer) {
        pesci.add(new Pesce(timer));
    }

    public static int getDay() {
        return day;
    }

    public static int getDimensione() {
        return pesci.size();
    }
}
