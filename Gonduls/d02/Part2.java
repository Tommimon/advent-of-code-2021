package Gonduls.d02;

import java.nio.file.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) {
        String input = "Gonduls/d02/input.txt";
        Point3d me = new Point3d(); // using z as aim, x as horizontal, y as vertical

        BiFunction<Point3d, String, Point3d> movement = (oldPoint, s) -> {
            int num = Integer.parseInt(s.split(" ")[1]);
            int x = oldPoint.x, y = oldPoint.y, z = oldPoint.z;

            if(s.startsWith("fo")) {
                x = x + num;
                y = y + num*z;
            }
            else if(s.startsWith("up"))
                z = z - num;
            else if(s.startsWith("do"))
                z = z + num;

            return new Point3d(x, y, z);
        };


        try (Stream<String> stream = Files.lines(Paths.get(input))) {
            me = stream
                    .reduce(me, movement, (a, b) -> b);

        } catch (Exception e) {
            System.out.println("Problems with input, ending program\n" + e);
        }

        System.out.println("Result part 2: " + (me.x * me.y));
    }
}
