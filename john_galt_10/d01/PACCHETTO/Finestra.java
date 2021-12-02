package john_galt_10.d01.PACCHETTO;

import java.util.ArrayList;
import java.util.List;

public class Finestra {
    private List<Integer> lista;

    public Finestra() {
        lista = new ArrayList<Integer>();
    }

    public void append(Integer n) {
        lista.add(n);
        return;
    }

    public void remove() {
        lista.remove(0);
        return;
    }

    public int sommaMisurazioni() {
        int somma = 0;
        somma = lista.get(0) + lista.get(1) + lista.get(2);
        return somma;
    }
}
