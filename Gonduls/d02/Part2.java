package Gonduls.d02;

import java.nio.file.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) {
        String input = "input.txt";
        Point3d me = new Point3d();


        Consumer<String> movement = s -> {
            int num = Integer.parseInt(s.split(" ")[1]);

            if(s.startsWith("fo")) {
                me.x = me.x + num;
                me.y = me.y + num*me.z;
            }
            else if(s.startsWith("up"))
                me.z = me.z - num;
            else if(s.startsWith("do"))
                me.z = me.z + num;
        };

        try (Stream<String> stream = Files.lines(Paths.get(input))) {
            stream.forEach(movement);

        } catch (Exception e) {
            System.out.println("Problems with input, ending program\n" + e);
        }

        System.out.println("Result part 2: " + (me.x * me.y));
    }
}
