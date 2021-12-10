package mynam3isg00d.d10.src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        String[] lines = r.parseInput("test.txt");

        ArrayList<Character> open = new ArrayList<Character>();
            open.add('('); open.add('['); open.add('{'); open.add('<');
        ArrayList<Character> close = new ArrayList<Character>();
            close.add(')'); close.add(']'); close.add('}'); close.add('>');

        int[] occurrances = new int[4];
        ArrayList<Long> cScores = new ArrayList<Long>();

        for (String l : lines) {
            boolean corrupted = false;
            Stack s = new Stack();

            for(int i=0; i<l.length(); i++) {
                if (open.contains(l.charAt(i))) {
                    s.push(l.charAt(i));
                } else {
                    Character c = (Character) s.peek();

                    if (close.indexOf(l.charAt(i)) == open.indexOf(c)) {
                        s.pop();
                    } else {
                        //Corrupted
                        occurrances[close.indexOf(l.charAt(i))]++;
                        corrupted = true;
                        break;
                    }
                }
            }
            if (!corrupted) {
                long completionScore = 0;
                while(!s.empty()) {
                    completionScore = completionScore * 5;
                    Character c = (Character) s.pop();
                    completionScore += 1+open.indexOf(c);
                }
                cScores.add(completionScore);
            }
        }
        for(int i=0; i<4; i++) System.out.println(occurrances[i]);
        long res = 3 * occurrances[0] + 57 * occurrances[1] + 1197 * occurrances[2] + 25137 * occurrances[3];
        System.out.println("Part 1: " + res);

        Collections.sort(cScores);
        System.out.println("Part 2: " + cScores.get(cScores.size()/2));

    }
}