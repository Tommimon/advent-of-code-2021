//
// Day 7 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 333755
// Second part solution: 94017638
//

package riccardo_negri.d07;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.abs;

public class d07 {
    public static void main (String[] args) {
        int MAXVALUE = 1500;
        File f = new File("riccardo_negri/d07/input.txt");
        Scanner reader = null;
        Integer[] numbers = new Integer[0];
        long dist;
        long distMin;

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        while (reader.hasNextLine()) {
            String temp = reader.nextLine();
            numbers = Arrays.stream(temp.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        }

        distMin = 0;
        for (int i = 0; i < MAXVALUE; i++) {
            dist = 0;
            for (Integer n : numbers) {
                dist += abs(n - i);
            }
            if (dist < distMin || distMin == 0) {
                distMin = dist;

            }
        }
        System.out.println(distMin);

        distMin = 0;
        for (int i = 0; i < MAXVALUE; i++) {
            dist = 0;
            for (Integer n : numbers) {
                dist += func(abs(n - i));
            }
            if (dist < distMin || distMin == 0) {
                distMin = dist;

            }
        }
        System.out.println(distMin);
    }

    private static int func (int n) {
        if (n <= 1) return n;
        return func(n - 1) + n;
    }
}
