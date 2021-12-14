//
// Day 14 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 5656
// Second part solution: 12271437788530
//

package riccardo_negri.d14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class d14 {
    static int STEPS_1 = 10;
    static int STEPS_2 = 40;
    static String[] alphabet = "a b c d e f g h i j k l m n o p q r s t u v w x y z".toUpperCase(Locale.ROOT).split(" ");

    public static void main (String[] args) {
        File f = new File("riccardo_negri/d14/input.txt");
        Scanner reader = null;
        String string = "";
        HashMap<String, String> pairInsertions = new HashMap<>();
        HashMap<String, HashMap<String, Long>> history = new HashMap<>();

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        boolean isFirst = true;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (isFirst) {
                string = line;
                isFirst = false;
                reader.nextLine();
            }
            else {
                pairInsertions.put(line.split(" -> ")[0], line.split(" -> ")[1]);
            }
        }

        HashMap<String, Long> part1LettersCounter = new HashMap<>();
        for (String letter : alphabet) {
            part1LettersCounter.put(letter, 0L);
        }
        HashMap<String, Long> part2LettersCounter = new HashMap<>(part1LettersCounter);
        HashMap<String, Long> tempLettersCounter;

        for (int i = 0; i < string.length()-1; i++) {
            if (i == string.length()-2) {
                tempLettersCounter = evolve(string.substring(i, i+2), pairInsertions, history, STEPS_1, true);
                for (String letter : alphabet) {
                    part1LettersCounter.put(letter, part1LettersCounter.get(letter)+tempLettersCounter.get(letter));
                }
                tempLettersCounter = evolve(string.substring(i, i+2), pairInsertions, history, STEPS_2, true);
                for (String letter : alphabet) {
                    part2LettersCounter.put(letter, part2LettersCounter.get(letter)+tempLettersCounter.get(letter));
                }
            }
            else {
                tempLettersCounter = evolve(string.substring(i, i+2), pairInsertions, history, STEPS_1, false);
                for (String letter : alphabet) {
                    part1LettersCounter.put(letter, part1LettersCounter.get(letter)+tempLettersCounter.get(letter));
                }
                tempLettersCounter = evolve(string.substring(i, i+2), pairInsertions, history, STEPS_2, false);
                for (String letter : alphabet) {
                    part2LettersCounter.put(letter, part2LettersCounter.get(letter)+tempLettersCounter.get(letter));
                }
            }

        }

        long max = 0;
        long min = -1;
        for (Long value : part1LettersCounter.values()) {
            if (min == -1 && value != 0) {
                min = value;
            }
            if (value != 0 && value > max) {
                max = value;
            }
            if (value != 0 && value < min) {
                min = value;
            }
        }
        System.out.println(max-min);

        max = 0;
        min = -1;
        for (Long value : part2LettersCounter.values()) {
            if (min == -1 && value != 0) {
                min = value;
            }
            if (value != 0 && value > max) {
                max = value;
            }
            if (value != 0 && value < min) {
                min = value;
            }
        }
        System.out.println(max-min);
    }

    public static HashMap<String, Long> evolve (String pair, HashMap<String, String> pairInsertions, HashMap<String, HashMap<String, Long>> history, int steps, boolean isRightEndPair) {
        // return if already calculated
        if (history.containsKey(pair+steps+isRightEndPair)) {
            return  history.get(pair+steps+isRightEndPair);
        }

        // initialize hashmap
        HashMap<String, Long> newLettersCounter = new HashMap<>();
        for (String letter : alphabet) {
            newLettersCounter.put(letter, 0L);
        }

        // base case
        if (steps == 1) {
            newLettersCounter.put(pair.charAt(0)+"", newLettersCounter.get(pair.charAt(0)+"")+1L);
            newLettersCounter.put(pairInsertions.get(pair), newLettersCounter.get(pairInsertions.get(pair))+1L);
            if (isRightEndPair) {
                newLettersCounter.put(pair.charAt(1)+"", newLettersCounter.get(pair.charAt(1)+"")+1L);
            }
            return newLettersCounter;
        }

        // other cases (note that we have all the possible paris in the pairInsertions map)
        else {
            String ch = pairInsertions.get(pair);
            HashMap<String, Long> tempLettersCounter = evolve(pair.charAt(0)+ch, pairInsertions, history,steps-1, false);
            for (String letter : alphabet) {
                newLettersCounter.put(letter, newLettersCounter.get(letter)+tempLettersCounter.get(letter));
            }
            tempLettersCounter = evolve(ch+pair.charAt(1), pairInsertions, history,steps-1, isRightEndPair);
            for (String letter : alphabet) {
                newLettersCounter.put(letter, newLettersCounter.get(letter)+tempLettersCounter.get(letter));
            }
        }

        // save results to avoid future computations
        history.put(pair+steps+isRightEndPair, newLettersCounter);
        return newLettersCounter;
    }

}
