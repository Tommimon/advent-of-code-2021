package john_galt_10.d01.PACCHETTO;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    public static void main(String[] args) {
        try {
            File myObj = new File("john_galt_10/d01/input.txt");
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
        String prec = reader.nextLine();

        int ris = 0;

        while (reader.hasNextLine()) {
            String current = reader.nextLine();
            if (Integer.parseInt(current) > Integer.parseInt(prec)) ris++;
            prec = current;
        }

        System.out.println("PARTE 1: " + ris);
    }

    private static void parteDue(Scanner reader) {
        Finestra uno = new Finestra();

        uno.append(Integer.parseInt(reader.nextLine()));
        uno.append(Integer.parseInt(reader.nextLine()));
        uno.append(Integer.parseInt(reader.nextLine()));

        int ris = 0;
        while(reader.hasNextLine()) {
            int prec = uno.sommaMisurazioni();

            uno.remove();
            uno.append(Integer.parseInt(reader.nextLine()));
            if (uno.sommaMisurazioni() > prec) ris++;
        }

        System.out.println("PARTE 2: " + ris);
    }
}
