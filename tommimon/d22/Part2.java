package tommimon.d22;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;


public class Part2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d22/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));

        ArrayList<Cube> cubes = new ArrayList<>();
        for (String l: lines) {
            String[] parts = Arrays.copyOfRange(l.replace(",","").split(" ")[1].split(".="), 1, 4);
            Integer[][] nums = Arrays.stream(parts).map(s-> Arrays.stream(s.split("\\.\\.")).map(Integer::parseInt).toArray(Integer[]::new)).toArray(Integer[][]::new);
            boolean on = (Objects.equals(l.split(" ")[0], "on"));
            cubes.add(new Cube(new Point(nums[0][0], nums[1][0], nums[2][0]), new Point(nums[0][1], nums[1][1], nums[2][1]), on));
        }

        ArrayList<Cube> sum = new ArrayList<>();
        for (Cube a: cubes) {
            ArrayList<Cube> added = new ArrayList<>();
            for (Cube b: sum) {
                Cube inter = b.intersection(a);
                if(!inter.empty)
                    added.add(inter);
            }
            sum.addAll(added);
            if (a.on)
                sum.add(a);
        }

        System.out.println(sum.stream().map(Cube::pieces).reduce(Long::sum).get());
    }
}
