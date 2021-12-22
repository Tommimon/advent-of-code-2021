package Gonduls.d18;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    Scanner sc;
    ArrayList<String> output = new ArrayList<>();

    public Input(String path) throws IOException {
        sc = new Scanner(new File(path));
        while (sc.hasNextLine())
            output.add(sc.nextLine());
    }

    public ArrayList<String> getInput(){
        return new ArrayList<>(output);
    }
}
