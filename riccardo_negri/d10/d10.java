//
// Day 10 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 265527
// Second part solution: 3969823589
//

package riccardo_negri.d10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class d10 {
    public static void main (String[] args) {
        File f = new File("riccardo_negri/d10/input.txt");
        Scanner reader = null;
        Stack<String> stk = new Stack<>();
        List<Long> scores = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, Integer> valuesIllegal = new HashMap<>();
        HashMap<String, Integer> valuesIncomplete = new HashMap<>();

        map.put("(", ")");
        map.put("[", "]");
        map.put("{", "}");
        map.put("<", ">");

        valuesIllegal.put(")", 3);
        valuesIllegal.put("]", 57);
        valuesIllegal.put("}", 1197);
        valuesIllegal.put(">", 25137);

        valuesIncomplete.put("(", 1);
        valuesIncomplete.put("[", 2);
        valuesIncomplete.put("{", 3);
        valuesIncomplete.put("<", 4);

        int counter = 0;

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            boolean illegal = false;
            stk.clear();
            for (int i = 0; i < line.length(); i++) {
                String c = String.valueOf(line.charAt(i));
                if(c.equals("(") || c.equals("[") || c.equals("{") || c.equals("<")) {
                    stk.push(c);
                }
                else {
                    try {
                        String temp = stk.pop();
                        if (!c.equals(map.get(temp))) {
                            //System.out.println("Expected " + map.get(temp) + ", but found " + c + " instead");
                            illegal = true;
                            counter += valuesIllegal.get(c);
                            i = line.length();
                        }
                    }
                    catch (EmptyStackException e) {
                        System.out.println("Something went wrong!");
                    }
                }
            }

            if (!illegal) {
                long score = 0;
                while(!stk.empty()) {
                    String temp = stk.pop();
                    score = score*5 + valuesIncomplete.get(temp);
                }
                scores.add(score);
            }
        }

        System.out.println(counter);
        System.out.println(scores.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList()).get(scores.size()/2));
    }
}
