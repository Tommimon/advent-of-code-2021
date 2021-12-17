//
// Day 17 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 7875
// Second part solution: 2321
//

package riccardo_negri.d17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

public class d17 {
    public static void main (String[] args) throws Exception {
        File f = new File("riccardo_negri/d17/input.txt");
        Scanner reader = null;

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        String line = reader.nextLine();
        int minX = Integer.parseInt(line.split(",")[0].split("=")[1].split("\\.")[0]);
        int maxX = Integer.parseInt(line.split(",")[0].split("=")[1].split("\\.")[2]);
        int minY = Integer.parseInt(line.split(",")[1].split("=")[1].split("\\.")[0]);
        int maxY = Integer.parseInt(line.split(",")[1].split("=")[1].split("\\.")[2]);

        int x = 0;
        int minxVel = 0;
        for (int i = 1; x < minX; i++) {
            x = i * (i + 1) / 2;
            minxVel = i;
        }
        int yVelCurr;
        int counter = 0;
        int highestY = 0;
        for (int xVel = minxVel; xVel <= maxX; xVel++) {
            int yVel = minY;
            while (yVel < abs(minY)) {
                int xVelCurr = xVel;
                yVelCurr = yVel;
                x = 0;
                int y = 0;
                int localHighestY = 0;
                while (true) {
                    x += xVelCurr;
                    y += yVelCurr;
                    if (x > maxX || y < minY) {
                        break;
                    }
                    if (x >= minX && y <= maxY) {
                        counter++;
                        if (localHighestY > highestY) {
                            highestY = localHighestY;
                        }
                        break;
                    }
                    if (y > localHighestY) {
                        localHighestY = y;
                    }
                    xVelCurr--;
                    if (xVelCurr < 0) {
                        xVelCurr = 0;
                    }
                    yVelCurr--;
                }
                yVel++;
            }
        }
        System.out.println(highestY);
        System.out.println(counter);
    }
}