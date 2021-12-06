//
// Day 6 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 362639
// Second part solution: 1639854996917
//

package riccardo_negri.d06;

import java.io.*;
import java.util.*;

public class d06 {
    private static final HashMap<Long, Long> map = new HashMap<>(); // value*1000+daysLeft, result

    public static void main (String[] args) {
        File f = new File("riccardo_negri/d06/input.txt");
        Scanner reader = null;
        List<Integer> lanterns = new ArrayList<>();
        long result = 0;

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        while (reader.hasNextLine()) {
            String[] temp = reader.nextLine().split(",");
            for (String n : temp) {
                lanterns.add(Integer.valueOf(n));
            }
        }

        for (Integer l : lanterns) {
            result += evolve(l, 80);
        }
        System.out.println(result);

        result = 0;
        for (Integer l : lanterns) {
            result += evolve(l, 256);
        }
        System.out.println(result);
    }

    private static long evolve (long value, long daysLeft) {
        if (map.containsKey(value * 1000 + daysLeft)) {
            return map.get(value * 1000 + daysLeft);
        }
        else if (daysLeft == 0) {
            return 1;
        }
        else if (value == 0) {
            map.put(daysLeft, evolve(6, daysLeft - 1) + evolve(8, daysLeft - 1));
            return map.get(daysLeft);
        }
        else {
            map.put(value * 1000 + daysLeft, evolve(value - 1, daysLeft - 1));
            return map.get(value * 1000 + daysLeft);
        }
    }
}
