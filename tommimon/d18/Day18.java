package tommimon.d18;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

public class Day18 {
    static int pos = 0;

    public static Number createNumber(String oldS) {
        String s = oldS.substring(pos);
        boolean isFirst = (pos == 0);
        char[] array = s.toCharArray();
        if (array[0] == '[') {
            pos++;  // skip '['
            Number a = createNumber(oldS);
            pos++;  // skip ','
            Number b = createNumber(oldS);
            pos++;  // skip ']'
            if (isFirst)
                pos = 0;
            Number num = new Number(a, b, null);
            a.parent = num;
            b.parent = num;
            return num;
        }
        else {
            String number = s.split(",")[0].split("]")[0];
            pos += number.length();
            return new Number(Integer.parseInt(number), null);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d18/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s -> !s.isBlank()).toArray(String[]::new)));
        long max = 0;
        for (String a: lines) {
            for (String b: lines) {
                if (!Objects.equals(a, b)) {
                    long val = createNumber(a).plus(createNumber(b)).magnitude();
                    if (val > max)
                        max = val;
                }
            }
        }
        Number[] nums = lines.stream().map(Day18::createNumber).toArray(Number[]::new);
        System.out.println(Arrays.stream(nums).reduce(Number::plus).get().magnitude());
        System.out.println(max);
    }
}
