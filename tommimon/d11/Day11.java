package tommimon.d11;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Stream;


public class Day11 {

    static void check(Integer[][]matrix, int i, int j) {
        if( 9 < matrix[i][j] && matrix[i][j] < 100) {
            matrix[i][j] += 100;
            for(int dI = -1; dI < 2; dI++) {
                for(int dJ = -1; dJ < 2; dJ++) {
                    if(dI !=0 || dJ != 0) {
                        int newI = i + dI;
                        int newJ = j + dJ;
                        if(0 <= newI && newI < matrix.length && 0 <= newJ && newJ < matrix[0].length) {
                            matrix[newI][newJ]++;
                            check(matrix, newI, newJ);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d11/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));

        Integer[][] matrix = new Integer[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                matrix[i][j] = Integer.parseInt(""+lines.get(i).toCharArray()[j]);
            }
        }

        int total = 0;
        int finalTotal = 0;
        int syncStep = 0;
        int step = 0;
        while (true) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j]++;
                }
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    check(matrix, i, j);
                }
            }


            boolean all = true;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] >= 100) {
                        matrix[i][j] = 0;
                        total++;
                    }
                    else {
                        all = false;
                    }
                }
            }
            if(all) {
                syncStep = step+1;
                if (finalTotal != 0)
                    break;

            }
            if(step == 99) {
                finalTotal = total;
                if (syncStep != 0)
                    break;
            }
            step++;
        }
        System.out.println(finalTotal);
        System.out.println(syncStep);
    }
}
