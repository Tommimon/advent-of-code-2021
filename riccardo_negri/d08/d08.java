//
// Day 8 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 525
// Second part solution: 1083859
//

package riccardo_negri.d08;

import java.io.*;
import java.util.*;

public class d08 {
    public static void main (String[] args) {
        File f = new File("riccardo_negri/d08/input.txt");
        Scanner reader = null;
        int counter = 0;
        int sum = 0;

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] patternTemp = line.split("\\| ")[0].split(" ");
            String[] digits = line.split("\\| ")[1].split(" ");

            Arrays.sort(patternTemp, Comparator.comparingInt(String::length));

            List<String> pattern = new ArrayList<>(Arrays.asList(patternTemp));

            String s3s6 = pattern.get(0); // s3s6 is number "1": s3 is the segment on the top-right, s6 the segment on bottom-right
            String s1 = difference(pattern.get(1), s3s6); // s1s3s6 - s3s6
            String s2s4 = difference(pattern.get(2), s3s6); // s2s3s4s6 - s3s6
            String s7 = "";
            for (String s : pattern) {
                if (s.length() == 6) {
                    String temp = difference(s, s3s6 + s1 + s2s4);
                    if (temp.length() == 1) {
                        s7 = temp;
                    }
                }
            }
            String s5 = difference(pattern.get(9), s1 + s2s4 + s3s6 + s7);
            String s6 = "";
            for (String s : pattern) {
                if (s.length() == 6) {
                    String temp = difference(s, s1 + s2s4 + s5 + s7);
                    if (temp.length() == 1) {
                        s6 = temp;
                    }
                }
            }
            String s3 = difference(s3s6, s6);
            String s4 = "";
            for (String s : pattern) {
                if (s.length() == 5) {
                    String temp = difference(s, s1 + s3 + s5 + s7);
                    if (temp.length() == 1) {
                        s4 = temp;
                    }
                }
            }
            String s2 = difference(s2s4, s4);

            HashMap<String, Integer> map = new HashMap<>();

            map.put(orderAlphabetically(s1 + s2 + s3 + s5 + s6 + s7), 0);
            map.put(orderAlphabetically(s3 + s6), 1);
            map.put(orderAlphabetically(s1 + s3 + s4 + s5 + s7), 2);
            map.put(orderAlphabetically(s1 + s3 + s4 + s6 + s7), 3);
            map.put(orderAlphabetically(s2 + s3 + s4 + s6), 4);
            map.put(orderAlphabetically(s1 + s2 + s4 + s6 + s7), 5);
            map.put(orderAlphabetically(s1 + s2 + s4 + s5 + s6 + s7), 6);
            map.put(orderAlphabetically(s1 + s3 + s6), 7);
            map.put(orderAlphabetically(s1 + s2 + s3 + s4 + s5 + s6 + s7), 8);
            map.put(orderAlphabetically(s1 + s2 + s3 + s4 + s6 + s7), 9);

            StringBuilder n = new StringBuilder();
            for (String s : digits) {
                try {
                    int temp = map.get(orderAlphabetically(s));
                    n.append(temp);
                    if (temp == 1 || temp == 4 || temp == 7 || temp == 8) {
                        counter++;
                    }

                } catch (NullPointerException e) {
                    System.out.println(s);
                }
            }
            sum += Integer.parseInt(n.toString());
        }

        System.out.println(counter);
        System.out.println(sum);
    }

    private static String difference (String str1, String str2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            String temp = String.valueOf(str1.charAt(i));
            if (!(str2.contains(temp))) {
                result.append(temp);
            }
        }
        return result.toString();
    }

    private static String orderAlphabetically (String s) {
        String[] chars = {"a", "b", "c", "d", "e", "f", "g"};
        StringBuilder result = new StringBuilder();
        for (String c : chars) {
            if (s.contains(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

}
