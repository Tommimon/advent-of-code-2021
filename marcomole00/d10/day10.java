package marcomole00.d10;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class day10 {





    public static void main(String[] args) {
        try {

            String openers = "([{<";
            String closers = ")]}>";
            int[] errorScores = {3,57,1197,25137};
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d10/input"));
            List<String> lista = br.lines().toList();

            int charPos;
            int stackPointer; // points at the new and empty space
            long errorValueTotal = 0;
            long errorValueLine;
            long closingValueLine;
            List<Long> closingValues = new ArrayList<>();
            List<Character> openersFound = new ArrayList<>();
            for (String line : lista) {
                charPos = 0;
                stackPointer = 0;
                openersFound.removeAll(openersFound);
                errorValueLine = 0;
                closingValueLine = 0;
                while (charPos < line.length()) {
                    if (openers.indexOf(line.charAt(charPos)) > -1) {
                        openersFound.add(line.charAt(charPos));
                        stackPointer++;
                    } else {
                        if (closers.indexOf(line.charAt(charPos)) == openers.indexOf(openersFound.get(stackPointer - 1))) {
                            openersFound.remove(stackPointer - 1);
                            stackPointer--;
                        } else {

                           errorValueLine += errorScores[closers.indexOf(line.charAt(charPos))];
                            charPos = line.length();
                            errorValueTotal += errorValueLine;
                        }

                    }
                    charPos++;
                    if (charPos == line.length() && errorValueLine == 0) {
                        while (stackPointer > 0) {
                            closingValueLine = closingValueLine * 5 + openers.indexOf(openersFound.get(stackPointer - 1))+1;
                            stackPointer--;
                        }
                        closingValues.add(closingValueLine);
                    }
                }
            }
            closingValues.sort(Long::compareTo);
            System.out.println(errorValueTotal);
            System.out.println(closingValues.get(closingValues.size()/2));

        } catch (IOException ignored){}



    }
}
