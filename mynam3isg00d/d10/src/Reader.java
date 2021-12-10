package mynam3isg00d.d10.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    public String[] parseInput(String input) {
        String inp = new String();
        try {
            File file = new File(input);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                inp = inp.concat(scanner.nextLine()).concat("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inp.split("\n");
    }
}
