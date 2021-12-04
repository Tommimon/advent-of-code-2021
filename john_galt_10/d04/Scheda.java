package john_galt_10.d04;

import java.util.ArrayList;
import java.util.List;

public class Scheda {
    private List<Integer> numeri;
    private boolean[] estratti;

    public Scheda() {
        numeri = new ArrayList<>();
        estratti = new boolean[30];
    }

    public void aggiungiRiga(String s) {
        String[] nums = new String[25];
        nums = s.split(" ");


        for (String n : nums) {
            //System.out.print(n + ", ");
            if (n != "" && n!=" ") numeri.add(Integer.parseInt(n));
        }
    }

    private boolean isVincente(int i) {
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

        return vintoRiga || vintoColonna;
    }

    public boolean aggiorna(Integer estratto) {
        int i = numeri.indexOf(estratto);
        System.out.println("i " + i);
        if (i != -1) {
            estratti[i] = true;
            return this.isVincente(i);
        } else {
            return false;
        }
    }

    public int sommaNonEstratti() {
        int somma = 0;
        for (int i = 0; i<25; i++) {
            if (estratti[i] == false) {
                somma += numeri.get(i);
            }
        }
        System.out.println("somma: "+somma);
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
