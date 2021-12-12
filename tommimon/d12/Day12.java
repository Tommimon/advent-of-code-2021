package tommimon.d12;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;


public class Day12 {
    static String[][] links;

    static int paths(String current, Vector<String> visited, String doubled, int part) {
        int total = 0;
        if(Objects.equals(current, "end"))
            return 1;
        if(current.toLowerCase(Locale.ROOT).equals(current))
            visited.add(current);
        for (String[] l: links) {
            String other = "";
            if (Objects.equals(l[0], current))
                other = l[1];
            if (Objects.equals(l[1], current))
                other = l[0];
            if (!other.isEmpty())
                if (!visited.contains(other))
                    total += paths(other, visited, doubled, part);
                else
                    if (part == 2 && Objects.equals(doubled, "") && other.toLowerCase(Locale.ROOT).equals(other) && !other.equals("start"))
                        total += paths(other, visited, other, part);
        }
        if(current.toLowerCase(Locale.ROOT).equals(current))
            visited.remove(visited.size()-1);
        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d12/input"));
        String[] lines = (new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)))).toArray(String[]::new);
        links = Arrays.stream(lines).map(s -> s.split("-")).toArray(String[][]::new);

        System.out.println(paths("start", new Vector<>(), "", 1));
        System.out.println(paths("start", new Vector<>(), "", 2));
    }
}
