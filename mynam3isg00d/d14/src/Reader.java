package mynam3isg00d.d14.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Reader {
    public InpVal parseInput(String input) {
        File file;
        Scanner scanner = null;
        InpVal inp = new InpVal();
        try {
            file = new File(input);
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        inp.polymer = scanner.nextLine();
        scanner.nextLine(); //for the \n
        while(scanner.hasNextLine()) {
            String next = scanner.nextLine();
            inp.rules.put(next.split(" -> ")[0], next.split(" -> ")[1]);
            inp.count.put(next.split(" -> ")[0], 0L);
        }
        inp.init();

        return inp;
    }
}
