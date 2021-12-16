package Gonduls.d16;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Input {
    Scanner sc;
    String in, output;

    public Input(String path) throws IOException{
        sc = new Scanner(new File(path));
        in = sc.nextLine().trim();

        output = in.chars()
                .mapToObj(c -> String.valueOf( (char) c))
                .map(c -> new BigInteger( c, 16).toString(2))
                .map(c -> "0000" + c)
                .map(c -> c.substring(c.length() -4))
                .reduce("", (a, b) -> a+b);
    }

    public String getOutput(){
        return output;
    }
}
