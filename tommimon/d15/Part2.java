package tommimon.d15;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;


public class Part2 {
    static Integer[][] matrix;
    static Integer[][] risks;
    static boolean changed;

    static void simulate() {
        changed = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i > 0 && risks[i-1][j] + matrix[i][j] < risks[i][j]) {
                    risks[i][j] = risks[i-1][j] + matrix[i][j];
                    changed = true;
                }
                if(j > 0 && risks[i][j-1] + matrix[i][j] < risks[i][j]) {
                    risks[i][j] = risks[i][j-1] + matrix[i][j];
                    changed = true;
                }
                if(i < matrix.length-1 && risks[i+1][j] + matrix[i][j] < risks[i][j]) {
                    risks[i][j] = risks[i+1][j] + matrix[i][j];
                    changed = true;
                }
                if(j < matrix.length-1 && risks[i][j+1] + matrix[i][j] < risks[i][j]) {
                    risks[i][j] = risks[i][j+1] + matrix[i][j];
                    changed = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d15/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));

        Integer[][] tile = new Integer[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                tile[i][j] = Integer.parseInt(""+lines.get(i).toCharArray()[j]);
            }
        }

        matrix = new Integer[tile.length*5][tile.length*5];
        for (int dI = 0; dI < 5; dI++) {
            for (int dJ = 0; dJ < 5; dJ++) {
                for (int i = 0; i < tile.length; i++) {
                    for (int j = 0; j < tile.length; j++) {
                        matrix[i+dI*tile.length][j+dJ*tile.length] = (tile[i][j] + (dI+dJ) - 1) % 9 + 1;
                    }
                }
            }
        }

        risks = new Integer[tile.length*5][tile.length*5];
        for (Integer[] row : risks)
            Arrays.fill(row, 999999999);
        risks[0][0] = 0;
        changed = true;
        while (changed) {
            simulate();
        }

        System.out.println(risks[matrix.length-1][matrix.length-1]);
    }
}
