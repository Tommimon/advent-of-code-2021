package tommimon.d24;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day24 {
    static Integer[] parseSection(String[] program, int pc) {
        int den = Integer.parseInt(program[pc+4].split(" ")[2]);
        int a = Integer.parseInt(program[pc+5].split(" ")[2]);
        int b = Integer.parseInt(program[pc+15].split(" ")[2]);
        return new Integer[]{den, a, b};
    }

    static void section(String[] program, Integer[] input, int sectionCounter, ArrayList<Integer[]> stack, boolean max) {
        if(sectionCounter*18 == program.length) {
            return;
        }
        Integer[] params = parseSection(program, sectionCounter*18);
        if (params[0] == 1) {
            stack.add(new Integer[]{sectionCounter, params[2]});
            section(program, input, sectionCounter+1, stack, max);
        }
        else {
            Integer[] other = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            int delta = params[1] + other[1];
            input[other[0]] = max ? 9 : 1-delta;
            input[sectionCounter] = max ? 9+delta : 1;
            if(delta > 0)  {
                input[other[0]] += max ? -delta : delta;
                input[sectionCounter] += max ? -delta : delta;
            }
            section(program, input, sectionCounter+1, stack, max);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d24/input"));
        String[] lines = br.lines().filter(s->!s.isBlank()).toArray(String[]::new);

        Integer[] input = new Integer[14];
        section(lines, input, 0, new ArrayList<>(), true);
        for(Integer i: input)
            System.out.print(i);
        System.out.println();
        section(lines, input, 0, new ArrayList<>(), false);
        for(Integer i: input)
            System.out.print(i);
    }
}
