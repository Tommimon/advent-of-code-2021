package tommimon.d09;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;

public class Part2 {

    static int basisSize(Integer[][] matrix, int iStart, int jStart) {
        Integer[][] copy = matrix.clone();
        copy[iStart][jStart] = 11;
        int counter = 1;
        int oldCounter = 0;
        while (counter != oldCounter) {
            oldCounter = counter;
            for (int i = 0; i < copy.length; i++) {
                for (int j = 0; j < copy[i].length; j++) {
                    if(copy[i][j] == 11) {
                        if(i > 0 && copy[i-1][j] < 9) {
                            copy[i - 1][j] = 11;
                            counter++;
                        }
                        if(i < copy.length-1 && copy[i+1][j] < 9) {
                            copy[i + 1][j] = 11;
                            counter++;
                        }
                        if(j > 0 && copy[i][j-1] < 9) {
                            copy[i][j - 1] = 11;
                            counter++;
                        }
                        if(j < copy[i].length-1 && copy[i][j+1] < 9) {
                            copy[i][j + 1] = 11;
                            counter++;
                        }
                        copy[i][j] = 10;
                    }
                }
            }
        }

        return counter;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d09/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));

        Integer[][] matrix = new Integer[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.size(); i++) {
            String l = lines.get(i);
            char[] charArray = l.toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                char n = charArray[j];
                matrix[i][j] = Integer.parseInt("" + n);
            }
        }

        Integer[] threeLargest = {0, 0, 0};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                boolean isMin = true;
                if(i > 0 && matrix[i][j] >= matrix[i-1][j])
                    isMin = false;
                if(i < matrix.length-1 && matrix[i][j] >= matrix[i+1][j])
                    isMin = false;
                if(j > 0 && matrix[i][j] >= matrix[i][j-1])
                    isMin = false;
                if(j < matrix[i].length-1 && matrix[i][j] >= matrix[i][j+1])
                    isMin = false;
                if(isMin) {
                    int basis = basisSize(matrix, i, j);
                    for (int k = 0; k < 3; k++) {
                        if (basis > threeLargest[k]) {
                            if (k > 0)
                                threeLargest[k - 1] = threeLargest[k];
                            threeLargest[k] = basis;
                        }
                    }
                }
            }
        }

        System.out.println(threeLargest[0] * threeLargest[1] * threeLargest[2]);
    }
}
