package tommimon.d08;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;

public class Part2 {
    static String sorted (String s) {
        Vector<Character> vec = new Vector<>();
        for (char c : s.toCharArray()) {
            vec.add(c);
        }
        Character[] sorted = vec.stream().sorted().toArray(Character[]::new);
        String out = "";
        for (char c : sorted) {
            out += c;
        }
        return out;
    }

    static String[] ofLen (String[] tags, int l) {
        Vector<String> res = new Vector<>();
        for (String s : tags) {
            if (s.length() == l)
                res.add(s);
        }
        return res.toArray(String[]::new);
    }

    static String diff (String a, String b) {
        String res = "";
        for (char c : a.toCharArray()) {
            if (!b.contains("" + c))
                res += c;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d08/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));
        String[][] first = lines.stream().map(s->s.replace("|", "_").split("_ ")[0].split(" ")).toArray(String[][]::new);
        String[][] second = lines.stream().map(s->s.replace("|", "_").split("_ ")[1].split(" ")).toArray(String[][]::new);

        String[] shapes = {"abcefg", "cf", "acdeg", "acdfg", "bcdf", "abdfg", "abdefg", "acf", "abcdefg", "abcdfg"};
        Integer[] lengths = Arrays.stream(shapes).map(String::length).toArray(Integer[]::new);
        String letters = "abcdefg";

        int total = 0;

        for (int i = 0; i < first.length; i++) {
            String[] l = first[i];
            //a, b, c, d, f,
            String[] matches = {"abcdefg", "abcdefg", "abcdefg", "abcdefg", "abcdefg", "abcdefg", "abcdefg"};
            String tag = ofLen(l, 2)[0];
                matches[2] = tag;
                matches[5] = tag;
            tag = ofLen(l, 3)[0];
            matches[0] = diff(tag, matches[2]);
            tag = ofLen(l, 4)[0];
            matches[1] = diff(tag, matches[2]);
            matches[3] = diff(tag, matches[2]);
            String[] tags = ofLen(l, 6);
            for (String t: tags) {
                char missing = diff(letters, t).toCharArray()[0];
                if (matches[2].contains("" + missing)) { // using 6
                    matches[2] = "" + missing;
                    matches[5] = diff(matches[5], matches[2]);
                    matches[4] = diff(t, matches[0] + matches[1] + matches[2]);
                    matches[6] = matches[4];
                }
            }

            for (String t: tags) {
                char missing = diff(letters, t).toCharArray()[0];
                if (matches[1].contains("" + missing)) { // using 0
                    matches[3] = "" + missing;
                    matches[1] = diff(matches[1], matches[3]);
                }
            }

            for (String t: tags) {
                char missing = diff(letters, t).toCharArray()[0];
                if(matches[4].contains(""+missing)) { // using 0
                    matches[4] = "" + missing;
                    matches[6] = diff(matches[6], matches[5] + matches[4]);
                }
            }
            String[] display = second[i];
            int num = 0;
            for (String d: display) {
                String converted = d.replace(matches[0].toCharArray()[0], 'A');
                converted = converted.replace(matches[1].toCharArray()[0], 'B');
                converted = converted.replace(matches[2].toCharArray()[0], 'C');
                converted = converted.replace(matches[3].toCharArray()[0], 'D');
                converted = converted.replace(matches[4].toCharArray()[0], 'E');
                converted = converted.replace(matches[5].toCharArray()[0], 'F');
                converted = sorted(converted.replace(matches[6].toCharArray()[0], 'G').toLowerCase(Locale.ROOT));
                for (int j = 0; j < shapes.length; j++) {
                    if(Objects.equals(shapes[j], converted)) {
                        num *= 10;
                        num += j;
                    }
                }
            }
            total += num;
        }
        System.out.println(total);

    }
}
