package john_galt_10.d04;

import java.util.ArrayList;
import java.util.List;

public class SchedaDue {
    private List<Integer> numeri;
    private boolean[] estratti;
    private boolean vincente;

    public SchedaDue() {
        numeri = new ArrayList<>();
        estratti = new boolean[30];
        vincente = false;
    }

    public void aggiungiRiga(String s) {
        String[] nums = new String[25];
        nums = s.split(" ");


        for (String n : nums) {
            //System.out.print(n + ", ");
            if (n != "" && n!=" ") numeri.add(Integer.parseInt(n));
        }
    }

    private void putVincente(int i) {
        boolean vintoRiga = true;
        boolean vintoColonna = true;

        int row = i/5;

        int col = i%5;


        //scorre la riga
        for(int c = 0; c < 5; c++) {
            if (c != col && estratti[row*5 + c] == false) {
                vintoRiga = false;
            }
        }

        //scorre la colonna
        for(int r = 0; r < 5; r++) {
            if (r != row && estratti[r*5 + col] == false) {
                vintoColonna = false;
            }
        }

        if (vintoColonna || vintoRiga) {
            vincente = true;
        }
    }

    public boolean isVinto() {
        return vincente;
    }

    public void aggiorna(Integer estratto) {
        int i = numeri.indexOf(estratto);
        if (i != -1) {
            estratti[i] = true;
            putVincente(i);
        }


    }

    public int sommaNonEstratti() {
        int somma = 0;
        for (int i = 0; i<25; i++) {
            if (estratti[i] == false) {
                somma += numeri.get(i);
            }
        }
        //System.out.println("somma: "+somma);
        return somma;
    }

    public void stampa() {
        System.out.println("SCHEDA");
        for (Integer i : numeri) {
            System.out.print(i + ", ");
        }
        System.out.println("");
    }
}
