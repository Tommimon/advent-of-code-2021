package tommimon.d20;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;

public class Day20 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d20/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));
        Boolean[] algorithm = lines.get(0).chars().mapToObj(c -> ((char)c) == '#').toArray(Boolean[]::new);
        lines.remove(0);
        boolean infinity0 = algorithm[0];
        boolean infinity1 = infinity0 && algorithm[algorithm.length-1];

        Image img = new Image().loadImage(lines.toArray(String[]::new));
        img = img.enhance(algorithm, infinity0);
        img = img.enhance(algorithm, infinity1);
        System.out.println(img.pixelsCount());

        img = new Image().loadImage(lines.toArray(String[]::new));
        for (int step = 0; step < 50; step++) {
            if(step % 2 == 0)
                img = img.enhance(algorithm, infinity0);
            else
                img = img.enhance(algorithm, infinity1);
        }
        System.out.println(img.pixelsCount());
    }
}
