package tommimon.d10;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;


public class Day10 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d10/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));

        String opening = "([{<";
        String closing = ")]}>";
        int[] errorScore = {3, 57, 1197, 25137};
        int[] completeScore = {1, 2, 3, 4};

        int total = 0;
        Vector<Long> scores = new Vector<>();
        for (String l: lines) {
            String stack = "";
            boolean error = false;
            for (char c : l.toCharArray()) {
                if(opening.contains(""+c)) {
                    stack += c;
                }
                else {
                    int index = opening.indexOf(stack.toCharArray()[stack.length()-1]);
                    if(c == closing.toCharArray()[index]) {
                        stack = stack.substring(0, stack.length()-1);  // remove last element
                    }
                    else {
                        int illegalIndex = closing.indexOf(c);
                        total += errorScore[illegalIndex];
                        error = true;
                        break;
                    }
                }
            }
            if(!error) {
                long score = 0;
                for (int i = stack.length()-1; i >= 0; i--) {
                    int index = opening.indexOf(stack.toCharArray()[i]);
                    score *= 5;
                    score += completeScore[index];
                }
                scores.add(score);
            }
        }

        System.out.println(total);
        Long[] sorted = scores.stream().sorted().toArray(Long[]::new);
        System.out.println(sorted[sorted.length/2]);
    }
}
