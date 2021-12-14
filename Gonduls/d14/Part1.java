package Gonduls.d14;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        Input input = new Input("Gonduls/d14/input.txt");

        String letters = input.getStart();
        HashMap<String, String> insertions = input.getInsertions();
        List<Character> allLetters = input.getAllLetters();

        for(int i= 0; i< 10; i++){
            int index = 0;

            // Actively substituting the two letters with themselves and their production
            while(index < letters.length() - 1){
                if(insertions.containsKey(letters.substring(index, 2 + index))){
                    letters = letters.substring(0, index +1) + insertions.get(letters.substring(index, 2 + index)) + letters.substring(index + 1);
                    index++;
                }
                index ++;
            }
        }

        long max = 0, min = letters.length();
        for(char c : allLetters){
            long current = letters.chars()
                    .filter(a -> a == c)
                    .count();
            max = Math.max(current, max);
            min = Math.min(current, min);
        }

        System.out.println(max - min);
    }
}
