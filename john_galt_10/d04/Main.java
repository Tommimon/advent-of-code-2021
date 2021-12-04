package john_galt_10.d04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("john_galt_10/d04/input.txt");
        Scanner myReader = new Scanner(myObj);
        //parteUno(myReader);
        parteDue(myReader);
        myReader.close();
    }

    private static void parteUno (Scanner reader) {
        //input numeri estratti (in array di stringhe)
        String[] numeriEstratti = new String[1000];
        String input = reader.nextLine();
        numeriEstratti = input.split(",");

        //input schede
        List<Scheda> schede = new ArrayList<>();
        int i = 0;
        String temp = reader.nextLine();

        while (reader.hasNextLine()) {
            temp = reader.nextLine();

            Scheda schedaTemp = new Scheda();
            while (!(temp.equals(""))) {
                schedaTemp.aggiungiRiga(temp);

                if (reader.hasNextLine()) {
                    temp = reader.nextLine();
                } else {
                    temp = "";
                }
            }
            schede.add(schedaTemp);

            i++;
        }

        int vincente = -1;
        int numeroVincente = 0;
        for (String n : numeriEstratti) {
            System.out.println("numero " + n + ":");
            int c = 0;
            for (Scheda s : schede) {
                if (s.aggiorna(Integer.parseInt(n))) {
                    vincente = c;
                }
                //System.out.println("scheda " + c + " non vincente");
                c++;
                if (vincente != -1) break;
            }
            if (vincente != -1) {
                numeroVincente = Integer.parseInt(n);
                System.out.println("n vincente " + numeroVincente);
                break;
            }
        }

        System.out.println("vince " + vincente);

        schede.get(vincente).stampa();
        System.out.println("soluzione: " + schede.get(vincente).sommaNonEstratti()*numeroVincente);
    }

    private static void parteDue (Scanner reader) {
        //input numeri estratti (in array di stringhe)
        String[] numeriEstratti = new String[1000];
        String input = reader.nextLine();
        numeriEstratti = input.split(",");

        //input schede
        List<SchedaDue> schede = new ArrayList<>();
        int i = 0;
        String temp = reader.nextLine();

        while (reader.hasNextLine()) {
            temp = reader.nextLine();

            SchedaDue schedaTemp = new SchedaDue();
            while (!(temp.equals(""))) {
                schedaTemp.aggiungiRiga(temp);

                if (reader.hasNextLine()) {
                    temp = reader.nextLine();
                } else {
                    temp = "";
                }
            }
            schede.add(schedaTemp);

            i++;
        }

        int numeroPerdente = 0;
        int vincitori = 0;
        int perdente=0;
        int c = 0;
        for (String n : numeriEstratti) {
            c = 0;
            for (SchedaDue s : schede) {
                if (!s.isVinto()){
                    s.aggiorna(Integer.parseInt(n));
                    if (s.isVinto()) vincitori++;
                }

                if (vincitori == schede.size()) break;
                c++;
            }

            if (vincitori == schede.size()) {
                numeroPerdente = Integer.parseInt(n);
                perdente = c;
                break;
            }
        }


        System.out.println("soluzione: " + numeroPerdente*schede.get(perdente).sommaNonEstratti());

    }
}
