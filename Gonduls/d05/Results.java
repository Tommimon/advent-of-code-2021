package Gonduls.d05;

import Gonduls.d02.Point2d;

import java.util.*;

import static java.lang.Math.*;


public class Results {
    public static void main(String[] args) {
        Input input;
        HashMap<Point2d, Integer> pointsInStraightLines = new HashMap<>();

        try {
            input = new Input("Gonduls/d05/input.txt");
        } catch (Exception e){
            System.out.println("Errors in opening file, closing program\n");
            e.printStackTrace();
            return;
        }

        // identifying lines by the two points at each end, found in length 2 arrays
        List<Point2d[]> straightLines = input.getStraightLines();
        List<Point2d> lines;

        for(Point2d[] endPoints : straightLines){
            lines = new ArrayList<>();

            if(endPoints[0].y == endPoints[1].y) {
                for (int x = endPoints[0].x; x != endPoints[1].x; ) {
                    lines.add(new Point2d(x, endPoints[0].y));
                    x += endPoints[0].x < endPoints[1].x ? 1 : -1;
                }
                lines.add(endPoints[1].clone()); // needed to cover end point as well
            }
            else {
                for (int y = endPoints[0].y; y != endPoints[1].y; ) {
                    lines.add(new Point2d(endPoints[0].x, y));
                    y += endPoints[0].y < endPoints[1].y ? 1 : -1;

                }
                lines.add(endPoints[1].clone()); // needed to cover end point as well
            }

            // using hashmap to store points contained in lines (lines contain ALL points in a line now)
            // didn't bother counting how many times a point is passed by a line: if it was passed more than once -> 2
            for(Point2d point : lines){
                if(pointsInStraightLines.containsKey(point))
                    pointsInStraightLines.replace(point, 2);
                else
                    pointsInStraightLines.put(point, 1);
            }
        }

        long result = pointsInStraightLines.values().stream()
                .filter(i -> i==2)
                .count();

        System.out.println("Result part 1 = " + result);


        ////////////////////////// Part 2 //////////////////////////////
        // I already accounted for points in straight lines, copying what found before
        HashMap<Point2d, Integer> pointsInAllLines = new HashMap<>(pointsInStraightLines);

        List<Point2d[]> allLines = input.getAllLines();

        for(Point2d[] endPoints : allLines){
            lines = new ArrayList<>();

            if(abs(endPoints[0].x - endPoints[1].x) == abs(endPoints[0].y - endPoints[1].y)) {
                int cursorX = endPoints[0].x;
                int cursorY = endPoints[0].y;

                // just checking with the x value, doesn't change if I check with y value
                while(cursorX != endPoints[1].x){

                    lines.add(new Point2d(cursorX, cursorY));
                    cursorX += endPoints[1].x > endPoints[0].x ? 1 : -1;
                    cursorY += endPoints[1].y > endPoints[0].y ? 1 : -1;

                }
                lines.add(endPoints[1].clone()); // have to add other end as well, not in while
            }

            // same as part 1
            for(Point2d point : lines){
                if(pointsInAllLines.containsKey(point))
                    pointsInAllLines.replace(point, 2);
                else
                    pointsInAllLines.put(point, 1);
            }
        }

        result = pointsInAllLines.values().stream()
                .filter(i -> i==2)
                .count();

        System.out.println("Result part 2 = " + result);
    }
}
