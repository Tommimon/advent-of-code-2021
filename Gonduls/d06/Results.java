// using a "circular" array: instead of moving every number over,
// I move index0 (modulo length array), so that it results as if one day has passed.
// Since fish with 0 days left produce the same number of fish in day 8, new day 8 is old day 0.
// only sum needed: day 0 to day 7 (before index is moved, day 6 after index is moved)

package Gonduls.d06;

import java.io.*;
import java.util.*;

import static java.util.Arrays.stream;

public class Results {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("Gonduls/d06/input.txt"));
        String string = reader.nextLine();

        Integer[] fish = Arrays.stream(string.split(","))
                .map(Integer::parseInt)
                .toArray(Integer[] :: new);


        long[] daysLeft = new long[9];
        for(Integer day : fish)
            daysLeft[day] ++;

        int index0 = 0, index7;
        long result_1=0;

        for(int i = 0; i< 256; i++){

            if(i == 80)
                result_1 = stream(daysLeft).reduce(0, Long::sum);

            index7 = (index0 + 7) % 9;
            daysLeft[index7] += daysLeft[index0];
            index0 = (index0 + 1) % 9;
        }

        long result_2 = stream(daysLeft).reduce(0, Long::sum);

        System.out.println("Result part 1 = "+ result_1);
        System.out.println("Result part 2 = "+ result_2);
    }
}
