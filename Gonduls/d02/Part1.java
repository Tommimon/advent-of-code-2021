package Gonduls.d02;

import java.util.function.Consumer;
import java.util.stream.Stream;
import java.nio.file.*;

public class Part1{
    public static void main(String[] args) {
        String input = "Gonduls/d02/input.txt";
        Point2d me = new Point2d();         // using x as horizontal, y as depth

        // HAVE TO REDO

        /*Consumer<String> movement = s -> {
            int num = Integer.parseInt(s.split(" ")[1]);

            if(s.startsWith("fo"))
                me = new Point2d(me.x + num, me.y);
                //me.x = me.x + num;
            else if(s.startsWith("up"))
                me.y = me.y - num;
            else if(s.startsWith("do"))
                me.y = me.y + num;
        };

        try (Stream<String> stream = Files.lines(Paths.get(input))) {
            stream.forEach(movement);

        } catch (Exception e) {
            System.out.println("Problems with input, ending program\n" + e);
        }

        System.out.println("Result part 1: " + (me.x * me.y));*/
    }
}