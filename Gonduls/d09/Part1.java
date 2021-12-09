package Gonduls.d09;

import Gonduls.d02.Point2d;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    static final int MAXX = 100;
    static final int MAXY = 100;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Gonduls/d09/input.txt"));

        char[][] matrix = new char[MAXY][MAXX];
        int result = 0;
        int x, y = 0;

        while(sc.hasNextLine()){
            matrix[y] = sc.nextLine().toCharArray();
            y ++;
        }

        for(y = 0; y < MAXY; y++){
            for(x = 0; x < MAXX; x++){
                boolean lowPoint = true;

                for(Point2d point : getAdjacent(new Point2d(x, y))){

                    if(matrix[y][x] >= matrix[point.y][point.x]) {
                        lowPoint = false;
                        break;
                    }
                }
                // matrix stores chars, I need to convert them to int
                result += lowPoint ? matrix[y][x] + 1 - '0' : 0;
            }
        }

        System.out.println("Result part 1 = " + result);
    }

    private static List<Point2d> getAdjacent(Point2d point){
        List<Point2d> adjacent = new ArrayList<>();

        if(point.x > 0)
            adjacent.add(new Point2d(point.x - 1, point.y));
        if(point.x < MAXX -1)
            adjacent.add(new Point2d(point.x + 1, point.y));
        if(point.y > 0)
            adjacent.add(new Point2d(point.x, point.y - 1));
        if(point.y < MAXY - 1)
            adjacent.add(new Point2d(point.x, point.y + 1));

        return adjacent;
    }
}
