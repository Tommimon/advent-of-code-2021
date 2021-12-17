package tommimon.d17;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;


public class Part2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d17/input"));
        String line = br.lines().filter(s->!s.isBlank()).toArray(String[]::new)[0];
        line = line.replace("target area: x=", "").replace(".", "_");
        int minX = Integer.parseInt(line.split(", y=")[0].split("__")[0]);
        int maxX = Integer.parseInt(line.split(", y=")[0].split("__")[1]);
        int minY = Integer.parseInt(line.split(", y=")[1].split("__")[0]);
        int maxY = Integer.parseInt(line.split(", y=")[1].split("__")[1]);
        int maxVelY = minY*(minY+1)/2;  // from part 1

        int counter = 0;
        for (int startY = minY; startY <= maxVelY; startY++) {
            int velY  = startY;
            int y = 0;
            int steps = 0;
            int minCorrectStartX = maxX+1;  // use this to limit startX to avoid duplicates
            while (y >= minY) {
                if (y <= maxY) {
                    for (int startX = minCorrectStartX-1; startX >= 0; startX--) {
                        int x;
                        if (steps < startX)
                            x = steps * startX - (steps*(steps+1)/2) + steps;
                        else
                            x = startX * (startX+1) / 2;
                        if (minX <= x && x <= maxX) {
                            minCorrectStartX = startX;  // next time start from current startX-1
                            counter++;
                        }
                    }
                }
                y += velY;
                steps++;
                velY--;
            }
        }
        System.out.println(counter);
    }
}
