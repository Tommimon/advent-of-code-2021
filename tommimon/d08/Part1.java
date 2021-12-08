package tommimon.d08;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class Part1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d08/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));
        String[][] second = lines.stream().map(s->s.replace("|", "_").split("_ ")[1].split(" ")).toArray(String[][]::new);

        Integer[] unique = {2, 4, 3, 7};

        int total = 0;
        for (String[] s: second) {
            for (String d: s) {
                if (List.of(unique).contains(d.length())) {
                    total++;
                }
            }
        }

        System.out.println(total);
    }
}
