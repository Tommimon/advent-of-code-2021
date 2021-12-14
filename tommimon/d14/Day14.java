package tommimon.d14;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;

class Transition {
    String start;
    String end;

    public Transition(String start, String end) {
        this.start = start;
        this.end = end;
    }
}

class Couple {
    int steps;
    String letters;
    Long[] counter;

    public Couple(String letters, int steps) {
        this.letters = letters;
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Couple couple = (Couple) o;
        return steps == couple.steps && Objects.equals(letters, couple.letters);
    }
}

public class Day14 {
    static Vector<Couple> cache;
    static Vector<Transition> tr;
    static String alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase(Locale.ROOT);

    static Long[] sum(Long[] a, Long[] b) {
        Long[] result = new Long[a.length];
        Arrays.setAll(result, i -> a[i] + b[i]);
        return result;
    }

    static Long[] getCount(String letters, int steps) {
        Couple newCouple = new Couple(letters, steps);
        Long[] counter = new Long[26];
        Arrays.fill(counter, 0L);
        char[] array = newCouple.letters.toCharArray();
        counter[alphabet.indexOf("" + array[0])]++;
        counter[alphabet.indexOf("" + array[1])]++;
        if(steps == 0) {
            return counter;
        }
        for (Couple c: cache) {
            if (c.equals(newCouple))
                return c.counter;
        }
        for (Transition t: tr) {
            if(t.start.equals(newCouple.letters)) {
                String str1 = "" + array[0] + t.end;
                String str2 = t.end + array[1];
                counter = sum(getCount(str1, steps-1), getCount(str2, steps-1));
                counter[alphabet.indexOf(t.end)]--;
                break;
            }
        }
        newCouple.counter = counter;
        cache.add(newCouple);
        return counter;
    }

    static Long simulate(String poly, int steps) {
        cache = new Vector<>();

        Long[] counter = new Long[26];
        Arrays.fill(counter, 0L);
        for (int j = 0; j < poly.length()-1; j++) {
            String couple = poly.substring(j, j+2);
            counter = sum(counter, getCount(couple, steps));
            if(j != 0) {
                char first = couple.toCharArray()[0];
                counter[alphabet.indexOf(first)]--;
            }
        }

        Long max = Arrays.stream(counter).max(Long::compareTo).get();
        long min = max;
        for (Long n : counter) {
            if(n != 0L && n < min) {
                min = n;
            }
        }
        return max-min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d14/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));
        String poly = lines.get(0);
        tr = new Vector<>();

        for (int i = 1; i < lines.size(); i++) {
            String l = lines.get(i);
            tr.add(new Transition(l.split(" -> ")[0], l.split(" -> ")[1]));
        }

        System.out.println(simulate(poly, 10));
        System.out.println(simulate(poly, 40));
    }
}
