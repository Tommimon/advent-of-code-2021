package tommimon.d06;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class Main {
    static long sum(Vector<Long> nums) {
        long sum = 0;
        for (long d : nums)
            sum += d;
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d06/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));
        List<Integer> fish = Arrays.asList(Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).toArray(Integer[]::new));


        Vector<Long> dayLeft = new Vector<>();
        for(int i = 0; i < 9; i++) {
            dayLeft.add(0L);
        }
        for(Integer f: fish) {
            dayLeft.set(f, dayLeft.get(f)+1);
        }

        for(int turn = 0; turn < 256; turn++) {
            if(turn == 80)
                System.out.println(sum(dayLeft));
            long zeros = dayLeft.get(0);
            for(int i = 0; i < dayLeft.size()-1; i++) {
                dayLeft.set(i, dayLeft.get(i+1));
            }
            dayLeft.set(8, 0L);
            dayLeft.set(6, dayLeft.get(6) + zeros);
            dayLeft.set(8, dayLeft.get(8) + zeros);
        }
        System.out.println(sum(dayLeft));
    }
}
