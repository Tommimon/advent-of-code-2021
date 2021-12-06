package john_galt_10.d06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("john_galt_10/d06/input.txt");
        Scanner myReader = new Scanner(myObj);
        //parteUno(myReader);
        parteDue(myReader);
        myReader.close();
    }

    public static void parteUno(Scanner reader) {
        String[] iniziali = reader.nextLine().split(",");

        for (String t : iniziali) {
            Tempo.addPesce(Integer.parseInt(t));
        }
        for (int i = 0; i < 80; i++) Tempo.giornoInPiu();
        System.out.println("Soluzione: " + Tempo.getDimensione());
    }

    public static void parteDue(Scanner reader) {
        String[] iniziali = reader.nextLine().split(",");

        for (String t : iniziali) {
            TempoDue.addPesce(Integer.parseInt(t));
        }
        for (int i = 0; i < 256; i++) TempoDue.giornoInPiu();
        System.out.println("Soluzione: " + TempoDue.getNumeroPesci());
    }
}
