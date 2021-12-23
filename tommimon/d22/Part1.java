package tommimon.d22;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

public class Part1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d22/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s -> !s.isBlank()).toArray(String[]::new)));

        ArrayList<Cube> cubes = new ArrayList<>();
        for( String l:lines) {
            String[] parts = Arrays.copyOfRange(l.replace(",", "").split(" ")[1].split(".="), 1, 4);
            Integer[][] nums = Arrays.stream(parts).map(s -> Arrays.stream(s.split("\\.\\.")).map(Integer::parseInt).toArray(Integer[]::new)).toArray(Integer[][]::new);
            boolean on = (Objects.equals(l.split(" ")[0], "on"));
            cubes.add(new Cube(new Point(nums[0][0], nums[1][0], nums[2][0]), new Point(nums[0][1], nums[1][1], nums[2][1]), on));
        }

        Cube[] firstCubes = cubes.stream().filter(c->c.pieces() < 1000000).toArray(Cube[]::new);
        int counter = 0;
        for (int i = -50; i <= 50; i++) {
            for (int j = -50; j <= 50; j++) {
                for (int k = -50; k <= 50; k++) {
                    boolean on = false;
                    for (Cube c : firstCubes) {
                        if (c.contains(new Point(i, j, k)))
                            on = c.on;
                    }
                    if (on)
                        counter++;
                }
            }
        }
        System.out.println(counter);
    }
}
