package tommimon.d15;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;


public class Part1 {
    static Integer[][] matrix;

    static void simulate() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] < 0) {
                    matrix[i][j] *= -1;
                    if(j < matrix.length-1) {
                        if(i == 0 || matrix[i][j] <= matrix[i-1][j+1]) {
                            matrix[i][j + 1] += matrix[i][j];
                            matrix[i][j+1] *= -1;
                        }
                    }
                    if(i < matrix.length-1) {
                        if(j == 0 || matrix[i][j] < Math.abs(matrix[i+1][j-1])) {
                            matrix[i + 1][j] += matrix[i][j];
                            matrix[i+1][j] *= -1;
                            if(j == 0)
                                return;
                        }
                    }
                    j++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d15/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));

        matrix = new Integer[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                matrix[i][j] = Integer.parseInt(""+lines.get(i).toCharArray()[j]);
            }
        }

        matrix[0][0] = 0;
        matrix[0][1] *= -1;
        matrix[1][0] *= -1;
        while (matrix[matrix.length-1][matrix.length-1] > 0) {
            simulate();
        }

        System.out.println(matrix[matrix.length-1][matrix.length-1] * -1);
    }
}
