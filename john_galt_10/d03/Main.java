package john_galt_10.d03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("john_galt_10/d03/input.txt");
        Scanner myReader = new Scanner(myObj);
        //parteUno(myReader);
        parteDue(myReader);
        myReader.close();
    }

    private static void parteUno(Scanner reader) {
        Occorrenze occ = new Occorrenze();
        int contRighe = 0;
        while(reader.hasNextLine()) {
            occ.segnaOccorrenze(reader.nextLine());
            contRighe++;
        }


        int gamma = 0;
        int epsilon = 0;
        for (int i = 0; i < 12; i++) {
            if (occ.getOccorrenzeBit(i) > contRighe/2) {
                gamma += Math.pow(2, 11-i);
            } else {
                epsilon += Math.pow(2, 11-i);
            }
        }

        System.out.println("Parte Uno: " + gamma*epsilon);
    }

    private static Predicate<String> l (char bit, int i) {
        return (dato -> dato.toCharArray()[i] == bit);
    }

    private static void parteDue(Scanner reader) {
        Occorrenze occ = new Occorrenze();
        List<String> data = new ArrayList<>(1000);
        int contRighe = 0;
        String curr;
        //calcola le occorrenze iniziali
        while(reader.hasNextLine()) {
            curr = reader.nextLine();
            data.add(curr);
        }

        //System.out.println("cont: " + contRighe);
        String oxygenRating = oxygenCalc(data);
        String co2Rating = co2Calc(data);

        System.out.println("oxy: " + oxygenRating);
        System.out.println("co2: " + co2Rating);
    }

    private static String oxygenCalc(List<String> data) {
        Occorrenze occ = new Occorrenze();
        int i = 0;
        while (data.size() != 1) {

            occ.svuotaOccorrenze();
            int contRighe=0;
            //calcola le occorrenze dei rimanenti
            for (int j = 0; j < data.size(); j++) {
                occ.segnaOccorrenze(data.get(j));
                contRighe++;
            }

            if (occ.getOccorrenzeBit(i) >= contRighe-occ.getOccorrenzeBit(i)) { //in posizione i ci sono più 1
                data = data.stream()
                        .filter(l('1', i))
                        .collect(Collectors.toList());

            } else { //in posizione i ci sono più 0
                data = data.stream()
                        .filter(l('0', i))
                        .collect(Collectors.toList());
            }


            i++;
        }

        return data.get(0);
    }

    private static String co2Calc (List<String> data) {
        Occorrenze occ = new Occorrenze();
        int i = 0;
        while (data.size() != 1) {

            occ.svuotaOccorrenze();
            int contRighe=0;
            //calcola le occorrenze dei rimanenti
            for (int j = 0; j < data.size(); j++) {
                occ.segnaOccorrenze(data.get(j));
                contRighe++;
            }

            if (occ.getOccorrenzeBit(i) >= contRighe-occ.getOccorrenzeBit(i)) { //se in pos i ci sono più 1
                data = data.stream()
                        .filter(l('0', i))
                        .collect(Collectors.toList());

            } else {        //se in pos i ci sono più 0
                data = data.stream()
                        .filter(l('1', i))
                        .collect(Collectors.toList());
            }


            i++;
        }

        return data.get(0);
    }
}
