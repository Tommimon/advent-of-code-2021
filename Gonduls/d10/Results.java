package Gonduls.d10;

import java.io.*;
import java.util.*;

public class Results {
    public static void main(String[] args) throws FileNotFoundException {
        int result = 0;
        Scanner scan = new Scanner(new File("Gonduls/d10/input.txt"));
        final String openings = "([{<";
        final String closings = ")]}>";
        final int[] points = new int[]{3, 57, 1197, 25137};
        List<Long> results_2 = new ArrayList<>();

        while(scan.hasNextLine()){
            String line  = scan.nextLine();
            Stack<Character> expected = new Stack<>();

            while(line.length() > 0){
                int index = openings.indexOf(line.charAt(0));
                if(index != -1){
                    expected.push(closings.charAt(index));
                    line = line.substring(1);
                    continue;
                }

                char c = expected.pop();
                // if line starts with character that I expect to find:
                if(c == line.charAt(0)){
                    line = line.substring(1);
                    continue;
                }

                result += points[closings.indexOf(line.charAt(0))];
                break;

            }

            // if line was corrupted no change to line length happens => line.length > 0
            if(line.length() == 0){
                long result_2 = 0;
                while(expected.size() > 0){
                    char c = expected.pop();
                    result_2 = result_2 * 5 + (closings.indexOf(c) + 1);
                }
                results_2.add(result_2);
            }

        }

        Collections.sort(results_2);
        System.out.println("Result part 1 : " +result);
        System.out.println("Result part 2 : " + results_2.get(results_2.size() / 2));
    }
}
