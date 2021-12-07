package tommimon.d07;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;


public class Main1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d07/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));
        Integer[] crabs = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).sorted().toArray(Integer[]::new);

        int res = 0;
        for (Integer c: crabs) {
            res += Math.abs(c - crabs[crabs.length/2]);
        }

        System.out.println(res);
    }
}
