package tommimon.d13;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;

public class Day13 {
    static Dot[] dots;

    static void doFold(String f) {
        for (Dot d : dots) {
            if(f.contains("x="))
                d.foldX(Integer.parseInt(f.split("=")[1]));
            else
                d.foldY(Integer.parseInt(f.split("=")[1]));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d13/input"));
        String[] lines = br.lines().toArray(String[]::new);
        String[] dotLines = Arrays.stream(lines).filter(s->!s.isBlank() && !s.contains("fold")).toArray(String[]::new);
        dots = Arrays.stream(Arrays.stream(dotLines).map(s->s.split(",")).toArray(String[][]::new)).map(i-> new Dot(Integer.parseInt(i[0]), Integer.parseInt(i[1]))).toArray(Dot[]::new);
        String[] folds = Arrays.stream(lines).filter(s-> s.contains("fold")).toArray(String[]::new);

        doFold(folds[0]);

        int total = 0;
        for (int i = 0; i < dots.length; i++) {
            Integer[] d = dots[i].toArray();
            boolean unique = true;
            for (int j = i+1; j < dots.length; j++)
                if (Arrays.equals(dots[i].toArray(), dots[j].toArray()))
                    unique = false;
            if (unique)
                total++;
        }
        System.out.println(total + "\n");

        for (int i = 1; i < folds.length; i++)
            doFold(folds[i]);

        int maxX = 0;
        int maxY = 0;
        for (Dot d : dots) {
            if(d.x > maxX)
                maxX = d.x;
            if(d.y > maxY)
                maxY = d.y;
        }
        for (int i = 0; i <= maxY; i++) {
            for (int j = 0; j <= maxX; j++) {
                boolean found = false;
                for (Dot d : dots)
                    if(Arrays.equals(d.toArray(), new Integer[]{j, i}))
                        found = true;
                if(found)
                    System.out.print("#");
                else
                    System.out.print(".");
            }
            System.out.println("");
        }
    }
}
