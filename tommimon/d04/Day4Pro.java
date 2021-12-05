package tommimon.d04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day4Pro {
    public static void main(String[] args) throws IOException {
        String text = Files.readString(Path.of("tommimon/d04/input"));
        String[] paragraphs = text.split("\n\n");
        Integer[] firstLine = Arrays.stream(paragraphs[0].split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[][][] matrix = Arrays.stream(Arrays.copyOfRange(paragraphs, 1, paragraphs.length)).map(p -> Arrays.stream(p.split("\n")).map(r -> Arrays.stream(r.split(" ")).filter(s -> !s.isBlank()).map(s -> Arrays.asList(firstLine).indexOf(Integer.parseInt(s.strip()))).toArray(Integer[]::new)).toArray(Integer[][]::new)).toArray(Integer[][][]::new);
        int part = 2;

        int number = part == 1 ? firstLine.length + 1 : 0;
        int sum = 0;
        for (Integer[][] b : matrix) {
            int min = firstLine.length + 1;
            for (Integer[] r : b) {
                int max = 0;
                for(Integer n : r)
                    max = Math.max(max, n);
                min = Math.min(min, max);
            }
            for (int i = 0; i < b.length; i++) {
                int max = 0;
                for(int j = 0; j < b[i].length; j++)
                    max = Math.max(max, b[j][i]);
                min = Math.min(min, max);
            }
            if((part == 1 && min < number) || (part == 2 && min > number)) {
                number = min;
                sum = 0;
                for (Integer[] r : b)
                    for(Integer n : r)
                        if(n > number)
                            sum += firstLine[n];
            }
        }
        System.out.println(sum * firstLine[number]);
    }
}
