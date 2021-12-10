package john_galt_10.d10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("john_galt_10/d10/input.txt");
        Scanner myReader = new Scanner(myObj);
        //int sol = parteUno(myReader);
        long sol = parteDue(myReader);
        myReader.close();

        System.out.println("SOLUZIONE: " + sol);
    }

    public static int parteUno(Scanner reader) {
        int ris = 0;
        while (reader.hasNextLine()) {
            Espressione x = new Espressione();
            for (char c : reader.nextLine().toCharArray()) {
                int n = x.check(c);
                ris += n;
                System.out.println(x);
                if (n!=0) break;
            }
        }
        return ris;
    }

    public static long parteDue(Scanner reader) {
        int ris = 0;
        List<Long> punteggi = new ArrayList<>();
        while (reader.hasNextLine()) {
            Espressione x = new Espressione();
            boolean scarta = false;
            for (char c : reader.nextLine().toCharArray()) {
                int n = x.check(c);
                System.out.println(x);
                if (n!=0) {
                    scarta = true;
                    break;
                }
            }
            if (scarta == false && x.stato().equals("incompleta")) {
                long p = x.punteggioMancanti();
                punteggi.add(p);
                System.out.println("punteggio: " + p);
            }
        }

        Collections.sort(punteggi, (p1, p2) -> {
            if (p1 > p2) {
                return 1;
            } else if (p1 < p2) {
                return -1;
            } else {
                return 0;
            }
        });

        //for (long p : punteggi) {
        //    System.out.print(p + "    ");
        //}
        return punteggi.get(punteggi.size()/2);
    }
}
