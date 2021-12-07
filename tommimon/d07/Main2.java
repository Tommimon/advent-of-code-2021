package tommimon.d07;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;


public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d07/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));
        Integer[] crubs = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        long min = -1;
        for(int mean = 0; mean < 99999; mean++) {
            long dev = 0;
            for (Integer c: crubs) {
                int steps = Math.abs(c-mean);
                int fuel = (steps)*(steps+1)/2;
                dev += Math.abs(fuel);
            }
            if(min == -1)
                min = dev;
            min = Math.min(min, dev);
        }

        System.out.println(min);
    }
}
