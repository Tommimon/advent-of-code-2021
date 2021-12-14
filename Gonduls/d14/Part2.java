package Gonduls.d14;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        Input input = new Input("Gonduls/d14/input.txt");

        String letters = input.getStart();
        HashMap<String, String> insertions = input.getInsertions();
        List<Character> allLetters = input.getAllLetters();
        long[] occurrences = new long[allLetters.size()];

        final HashMap<String, Long> productionsReset = new HashMap<>();
        HashMap<String, Long> newChars, twoChars;

        // productionsReset contains all possible productions but sets their cardinality to 0
        for(String str : insertions.keySet()){
            productionsReset.put(str, 0L);
        }

        // twoChars contains the actual letters (in pairs) present in each step, by counting how many times they appear
        twoChars = new HashMap<>(productionsReset);
        for(int i = 0; i< letters.length() - 1; i++){
            twoChars.replace(letters.substring(i, i+2), twoChars.get(letters.substring(i, i+2)) +1);
        }

        // initializing the counters for every letter present
        for(Character c: letters.toCharArray())
            occurrences[allLetters.indexOf(c)] ++;

        // newChars is a temporary map
        for(int i = 0; i< 40; i++){
            newChars = new HashMap<>(productionsReset);

            for(String str : twoChars.keySet()){
                // every pair of letters creates 2 new pairs of letters: ab -> c == ab -> ac + cb
                String a = str.charAt(0) + insertions.get(str);
                String b = insertions.get(str) + str.charAt(1);

                // I need to add new pairs in the same amount I had the old pairs
                newChars.replace(a, newChars.get(a) + twoChars.get(str));
                newChars.replace(b, newChars.get(b) + twoChars.get(str));

                // every production actually adds a single letter to the final string,
                // multiplied by how many times I had the old pair
                occurrences[allLetters.indexOf(insertions.get(str).charAt(0))] += twoChars.get(str);
            }
            twoChars = new HashMap<>(newChars);
        }

        long max = occurrences[0], min = occurrences[0];
        for(long num : occurrences){
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        System.out.println(max - min);
    }
}

