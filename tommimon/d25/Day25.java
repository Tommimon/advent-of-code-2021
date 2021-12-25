package tommimon.d25;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;

public class Day25 {
    static char[][] matrix;
    static int counter = 0;

    static int correctI(int i) {
        if (i == -1)
            return matrix.length-1;
        if (i == matrix.length)
            return 0;
        return i;
    }

    static int correctJ (int j) {
        if (j == -1)
            return matrix[0].length-1;
        if (j == matrix[0].length)
            return 0;
        return j;
    }

    static boolean step(){
        boolean res = false;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '>')
                    if (matrix[i][correctJ(j+1)] == '.')
                        matrix[i][j] = 'E';
            }

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (matrix[i][j] == 'E') {
                    matrix[i][j] = '.';
                    matrix[i][correctJ(j + 1)] = '>';
                    res = true;
                }

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'v')
                    if (matrix[correctI(i+1)][j] == '.')
                        matrix[i][j] = 'S';
            }

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (matrix[i][j] == 'S') {
                    matrix[i][j] = '.';
                    matrix[correctI(i+1)][j] = 'v';
                    res = true;
                }
        counter++;
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d25/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));

        matrix = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < matrix.length; i++)
            matrix[i] = lines.get(i).toCharArray();

        while (step());

        for (char[] r: matrix) {
            for (char c: r)
                System.out.print(c);
            System.out.println();
        }
        System.out.println(counter);
    }
}
