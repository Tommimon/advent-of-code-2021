package Gonduls.d08;

import java.io.*;
import java.nio.file.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {

        Stream<String> stream = Files.lines(Paths.get("Gonduls/d08/input.txt"));

        // just count correct length strings
        long result = stream
                .map(s -> s.split(Pattern.quote(" | "))[1])
                .flatMap(s -> Stream.of(s.trim().split(" ")))
                .filter(s -> (s.length() < 5 || s.length() == 7))
                .count();

        stream.close();
        System.out.println("Result part 1 = " + result);
    }
}
