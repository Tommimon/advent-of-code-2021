//This day sucked lmao

package mynam3isg00d.d08.src;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        String[] lines = r.parseInput("input.txt");

        //Part 1
        int count = 0;
        for(String l : lines) {
            String lineout = l.split(Pattern.quote(" | "))[1];
            for(String w : lineout.split(" ")) {
                w = w.strip();
                if (w.length() == 2 || w.length() == 3 || w.length() == 4 || w.length() == 7) count++;
            }
        }

        System.out.println("Part 1: " + count);

        //Part 2
        int sum = 0;
        for(String l : lines) {
            //Fetch
            SDD display = new SDD();
            String[] words = l.replace(" | ", " ").split(" ");
            for(int i=0; i<words.length; i++) {
                words[i] = sortString(words[i]);
            }

            //Decode
            while(display.checkDecoded()) {
                for(String w : words) {
                    //Base cases
                    if (w.length() == 2) display.digits[1] = w;
                    if (w.length() == 3) display.digits[7] = w;
                    if (w.length() == 4) display.digits[4] = w;
                    if (w.length() == 7) display.digits[8] = w;

                    //Length 6 cases
                    //if len 6 and does NOT contain 1, it's a 6
                    if (w.length() == 6 && !display.digits[1].isEmpty() &&
                       !(w.indexOf(display.digits[1].charAt(0)) != -1 && w.indexOf(display.digits[1].charAt(1)) != -1))
                            display.digits[6] = w;

                    //if len 6 and fully contains 4, it's a 9
                    if (w.length() == 6 && !display.digits[4].isEmpty() &&
                        w.indexOf(display.digits[4].charAt(0)) != -1 && w.indexOf(display.digits[4].charAt(1)) != -1 &&
                        w.indexOf(display.digits[4].charAt(2)) != -1 && w.indexOf(display.digits[4].charAt(3)) != -1)
                            display.digits[9] = w;

                    //if len 6 and not 6 or 9 it's a 0
                    if (w.length() == 6 && !display.digits[6].isEmpty() && !display.digits[9].isEmpty() &&
                        w != display.digits[6] && w != display.digits[9])
                            display.digits[0] = w;

                    //Length 5 cases
                    //if len 5 and fully contains 1, it's a 3
                    if (w.length() == 5 && !display.digits[1].isEmpty() &&
                        w.indexOf(display.digits[1].charAt(0)) != -1 && w.indexOf(display.digits[1].charAt(1)) != -1)
                            display.digits[3] = w;

                    //if len 5, not a 3 and fully contained in 9, it's a 5
                    if (w.length() == 5 && !display.digits[9].isEmpty() && !display.digits[3].isEmpty() && w != display.digits[3] &&
                        display.digits[9].indexOf(w.charAt(0)) != -1 && display.digits[9].indexOf(w.charAt(1)) != -1 &&
                        display.digits[9].indexOf(w.charAt(2)) != -1 && display.digits[9].indexOf(w.charAt(3)) != -1 &&
                        display.digits[9].indexOf(w.charAt(4)) != -1)
                            display.digits[5] = w;

                    //if len 5 and not 3 or 5, it's a 2
                    if (w.length() == 5 && !display.digits[3].isEmpty() && !display.digits[5].isEmpty() &&
                        w != display.digits[3] && w != display.digits[5])
                            display.digits[2] = w;
                }
            }

            //Execute B)
            String output = "";
            String[] outputList = l.split(Pattern.quote(" | "))[1].split(" ");
            for(int i=0; i<outputList.length; i++) {
                outputList[i] = sortString(outputList[i]);
            }

            for(String o : outputList) {
                output = output.concat(display.find(o));
            }

            sum += Integer.parseInt(output);
        }

        System.out.println("Part 2: " + sum);
    }

    public static String sortString(String s) {
        char[] tempArray = s.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
}