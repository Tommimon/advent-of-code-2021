package mynam3isg00d.d13.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    public InputType parseInput(String input) {
        File file = null;
        Scanner scanner = null;
        String inp = "";
        InputType it = new InputType();
        try {
            file = new File(input);
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNextLine()) {
            inp = inp + scanner.nextLine() + "\n";
        }

        for(String l : inp.split("\n\n")[0].split("\n")) {
            it.add(Integer.parseInt(l.split(",")[0]), Integer.parseInt(l.split(",")[1]));
        }

        for(String l : inp.split("\n\n")[1].split("\n")) {
            l = l.replace("fold along ", "");
            l = l.replace("x=", "");
            l = l.replace("y=", "-");
            it.addFold(Integer.parseInt(l));
        }

        return it;
    }
}
