package john_galt_10.d07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("john_galt_10/d07/input.txt");
        Scanner myReader = new Scanner(myObj);
        //parteUno(myReader);
        parteDue(myReader);
        myReader.close();
    }

    public static void parteUno(Scanner reader) {
        String input = reader.nextLine();
        String[] posString = input.split(",");
        List<Integer> posInt = new ArrayList<>();
        for (String s : posString) {
            posInt.add(Integer.parseInt(s));
        }
        Collections.sort(posInt, (i1, i2) -> {
            if (i1 > i2) return 1;
            else if (i1 < i2) return -1;
            else return 0;
        });

        float posFinale = 0;
        switch (posInt.size()%2) {
            case 0: posFinale = (posInt.get(posInt.size()/2) + posInt.get(posInt.size()/2 + 1))/2;
                break;
            case 1: posFinale = posInt.get(posInt.size()/2 + 1);
                break;
        }
        System.out.println("poizione ottima: " + posFinale);

        int costo=0;
        for (int i : posInt) {
            costo += Math.abs(i - posFinale);
        }
        System.out.println("costo minimo: " + costo);
    }

    public static void parteDue(Scanner reader) {
        String input = reader.nextLine();
        String[] posString = input.split(",");
        List<Integer> posInt = new ArrayList<>();
        for (String s : posString) {
            posInt.add(Integer.parseInt(s));
        }

        Collections.sort(posInt, (i1, i2) -> {
            if (i1 > i2) return 1;
            else if (i1 < i2) return -1;
            else return 0;
        });

        int max = posInt.get(posInt.size()-1);
        int costoMinimo = Integer.MAX_VALUE;
        for (int i = 0; i < max; i++) {
            //calcola il costo totale per raggiungere quella posizione
            int c = 0;
            for (int j : posInt) {
                c += costo(i, j);
            }
            System.out.println("costo per la posizione " + i + ": " + c);
            if (c < costoMinimo) costoMinimo = c;
        }

        System.out.println("soluz: " + costoMinimo);
    }

    //calcola il costo per arrivare da partenza ad arrivo
    private static int costo(int arrivo, int partenza) {
        int diff = Math.abs(arrivo-partenza);
        return ((diff)*(diff+1))/2;
    }
}
