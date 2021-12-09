package tommimon.d09;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;


public class Part1 {
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

        int counter = 0;
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
                if(isMin)
                    counter += 1+matrix[i][j];
            }
        }

        System.out.println(counter);
    }
}
