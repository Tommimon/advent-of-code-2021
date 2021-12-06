package Gonduls.d02;

import java.util.function.*;
import java.util.stream.Stream;
import java.nio.file.*;

public class Part1{
    public static void main(String[] args) {
        String input = "Gonduls/d02/input.txt";
        Point2d me = new Point2d(); // using x as horizontal, y as vertical

        BiFunction<Point2d, String, Point2d> movement = (oldPoint, s) -> {
            int num = Integer.parseInt(s.split(" ")[1]);
            int x = oldPoint.x, y = oldPoint.y;

            if(s.startsWith("fo"))
                x = x + num;
            else if(s.startsWith("up"))
                y = y - num;
            else if(s.startsWith("do"))
                y = y + num;

            return new Point2d(x, y);
        };


        try (Stream<String> stream = Files.lines(Paths.get(input))) {
            me = stream
                    .reduce(me, movement, (a, b) -> b);

        } catch (Exception e) {
            System.out.println("Problems with input, ending program\n" + e);
        }


        System.out.println("Result part 1: " + (me.x * me.y));
    }
}