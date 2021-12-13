package john_galt_10.d12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("john_galt_10/d12/test.txt");
        Scanner myReader = new Scanner(myObj);
        int sol = parteUno(myReader);
        //int sol = parteDue(myReader);
        myReader.close();

        System.out.println("SOLUZIONE: " + sol);
    }

    private static int parteUno(Scanner reader) {
        int sol = 0;
        List<Cave> caves = new ArrayList<>();


        while (reader.hasNextLine()) {
            String input = reader.nextLine();
            String from = input.split("-")[0];
            String to = input.split("-")[1];
            //System.out.println(from + " - " + to);

            Cave n = new Cave(from);

            System.out.println(caves.contains(n));
            if (caves.contains(n)) {
                System.out.println("c'è");
                caves.get(caves.indexOf(n)).addAdiacente(new Cave(to));
            } else {
                n.addAdiacente(new Cave(to));
                caves.add(n);
                System.out.println("aggiunto " + from);
            }
            //(from, to) = input.split("-");
        }



        String s = "start";
        Cave n = new Cave(s);
        caves.add(n);
        Cave nn = new Cave(s);
        System.out.println(caves.contains(nn));


        for (Cave c : caves) System.out.println(c);

        return sol;
    }
}

