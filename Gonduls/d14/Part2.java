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


        HashMap<String, Long> twoChars;
        HashMap<String, Long> allCombos = new HashMap<>();
        HashMap<String, Long> newChars;

        for(String str : insertions.keySet()){
            allCombos.put(str, 0L);
        }

        twoChars = new HashMap<>(allCombos);
        for(int i = 0; i< letters.length() - 1; i++){
            twoChars.replace(letters.substring(i, i+2), twoChars.get(letters.substring(i, i+2)) +1);
        }

        for(Character c: letters.toCharArray())
            occurrences[allLetters.indexOf(c)] ++;

        for(int i = 0; i< 40; i++){
            newChars = new HashMap<>(allCombos);
            for(String str : twoChars.keySet()){
                String a = str.charAt(0) + insertions.get(str);
                String b = insertions.get(str) + str.charAt(1);
                newChars.replace(a, newChars.get(a) + twoChars.get(str));
                newChars.replace(b, newChars.get(b) + twoChars.get(str));
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

