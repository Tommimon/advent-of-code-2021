package Gonduls.d14;

import java.io.*;
import java.util.*;


public class Input {
    private final String startingStr;
    private final HashMap<String, String> pairInsertion = new HashMap<>();
    private final List<Character> allLetters = new ArrayList<>();

    public Input(String filePath) throws IOException {
        Scanner scan = new Scanner(new File(filePath));
        startingStr = scan.nextLine();

        for(char c : startingStr.toCharArray()){
            if(!allLetters.contains(c))
                allLetters.add(c);
        }
        scan.nextLine();

        while (scan.hasNextLine()) {
            String str = scan.nextLine();
            pairInsertion.put(str.split(" -> ")[0], str.split(" -> ")[1]);
        }

        for(String str : pairInsertion.keySet()){
            for(char c : str.toCharArray()){
                if(!allLetters.contains(c))
                    allLetters.add(c);
            }
        }

        for(String str : pairInsertion.values()){
            for(char c : str.toCharArray()){
                if(!allLetters.contains(c))
                    allLetters.add(c);
            }
        }
    }

    // it's better to return a copy of internal attributes instead of returning the Object itself
    public String getStart(){
        return startingStr;
    }

    public HashMap<String, String> getInsertions(){
        return new HashMap<>(pairInsertion);
    }

    public List<Character> getAllLetters(){
        return new ArrayList<>(allLetters);
    }
}
