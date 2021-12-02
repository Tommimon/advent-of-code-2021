package john_galt_10.d02;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    public static void main (String[] args) {
        try {
            File myObj = new File("john_galt_10/d02/input.txt");
            Scanner myReader = new Scanner(myObj);
            //parteUno(myReader);
            parteDue(myReader);
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    private static void parteUno (Scanner reader) {
        Sottomarino sottomarinoPazzoSgravato = new Sottomarino();
        while (reader.hasNextLine()) {
            sottomarinoPazzoSgravato.move(reader.nextLine());
        }
        int ris = sottomarinoPazzoSgravato.getDepth() * sottomarinoPazzoSgravato.getHorizontal();
        System.out.println(ris);
    }

    private static void parteDue (Scanner reader) {
        SottomarinoDue sottomarinoPazzoSgravatoDue = new SottomarinoDue();
        while (reader.hasNextLine()) {
            sottomarinoPazzoSgravatoDue.move(reader.nextLine());
        }
        int ris = sottomarinoPazzoSgravatoDue.getDepth() * sottomarinoPazzoSgravatoDue.getHorizontal();
        System.out.println(ris);
    }
}
