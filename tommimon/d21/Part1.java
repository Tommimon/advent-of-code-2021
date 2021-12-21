package tommimon.d21;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;


public class Part1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d21/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));
        int pos1 = Integer.parseInt(lines.get(0).split("Player 1 starting position: ")[1]) - 1;
        int pos2 = Integer.parseInt(lines.get(1).split("Player 2 starting position: ")[1]) - 1;

        int score1 = 0;
        int score2 = 0;
        int counter = 0;
        while (score1 < 1000 && score2 < 1000) {
            if (counter % 6 == 0) {
                pos1 = (pos1 + 3*counter+6) % 10;
                score1 += pos1 + 1;
            }
            else {
                pos2 = (pos2 + 3*counter+6) % 10;
                score2 += pos2 + 1;
            }
            counter += 3;
        }
        int finalScore;
        if(counter % 6 == 0)
            finalScore = score1;
        else
            finalScore = score2;
        System.out.println(finalScore * counter);
    }
}
