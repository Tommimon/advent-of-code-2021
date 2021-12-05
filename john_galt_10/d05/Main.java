package john_galt_10.d05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("john_galt_10/d05/input.txt");
        Scanner myReader = new Scanner(myObj);
        parteUno(myReader);
        //parteDue(myReader);
        myReader.close();
    }

    private static void parteUno(Scanner reader) {
        Tabellone gigi = new Tabellone();
        while (reader.hasNextLine()) {
            String input = reader.nextLine();
            gigi.updateGriglia(input);
        }

        System.out.println("soluzione: " + gigi.contaDue());
    }
}
