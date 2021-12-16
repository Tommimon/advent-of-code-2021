package john_galt_10.d12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("john_galt_10/d12/input.txt");
        Scanner myReader = new Scanner(myObj);
        int sol = parteUno(myReader);
        //int sol = parteDue(myReader);
        myReader.close();

        System.out.println("SOLUZIONE: " + sol);
    }

    private static int parteUno(Scanner reader) {
        int sol = 0;
        List<Cave> caves = new ArrayList<>();

        //creates caves map
        //metti in cima all'input un percorso che inizia con start che sono pigro
        while (reader.hasNextLine()) {
            String input = reader.nextLine();
            String from = input.split("-")[0];
            String to = input.split("-")[1];
            //System.out.println(from + " - " + to);

            Cave n = new Cave(from);

            if (caves.contains(n)) {
                caves.get(caves.indexOf(n)).addAdiacente(new Cave(to));
            } else {
                n.addAdiacente(new Cave(to));
                caves.add(n);
            }

            n = new Cave(to);
            if (caves.contains(n)) {
                caves.get(caves.indexOf(n)).addAdiacente(new Cave(from));
            } else {
                n.addAdiacente(new Cave(from));
                caves.add(n);
            }
        }

        for (Cave c : caves) System.out.println(c);

        //sol = countPaths(caves.get(0), caves, new ArrayList<String>());
        sol = countPathsDue(caves.get(0), caves, new ArrayList<String>());

        return sol;
    }

    private static int countPaths(Cave start, List<Cave> caves, List<String> path) {
        int sol = 0;

        if (start.isSmall() && path.contains(start.getName())) {
            return 0;
        }
        if (start.getName().equals("end")) {
            return 1;
        }

        List<String> newPath = new ArrayList<>();
        for (String s : path) {
            newPath.add(s);
        }
        newPath.add(start.getName());
        System.out.println("");

        for (Cave c : start.getAdiacenti()) {
            sol += countPaths(caves.get(caves.indexOf(c)), caves, newPath);
        }
        return sol;
    }

    private static int countPathsDue(Cave start, List<Cave> caves, List<String> path) {
        int sol = 0;

        if (start.isSmall() && path.contains(start.getName())) {
            if (start.getName().equals("start") || start.getName().equals("end") || alreadyTwo(path)) return 0;
        }
        if (start.getName().equals("end")) {
            return 1;
        }

        List<String> newPath = new ArrayList<>();
        for (String s : path) {
            newPath.add(s);
        }
        newPath.add(start.getName());

        for (Cave c : start.getAdiacenti()) {
            sol += countPathsDue(caves.get(caves.indexOf(c)), caves, newPath);
        }
        return sol;
    }

    private static boolean alreadyTwo(List<String> path) {


        for (int i = 0; i < path.size(); i++) {
            for (int j = 0; j < path.size(); j++) {
                if (path.get(i).charAt(0) >= 'a' && path.get(i).charAt(0) <= 'z' && j!=i && path.get(i).equals(path.get(j))) return true;
            }
        }


        return false;
    }
}

