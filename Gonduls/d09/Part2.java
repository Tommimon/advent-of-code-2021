package Gonduls.d09;

import Gonduls.d02.Point2d;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    final static int MAXX = 100;
    final static int MAXY = 100;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Gonduls/d09/input.txt"));

        final char[][] matrix = new char[MAXY][MAXX];
        final int[] results = new int[]{0,0,0}; // used as min-heap of three elements
        int result;                             // used to calculate the size of a given basin
        int x, y = 0;
        Stack<Point2d> sameBasin;

        while(sc.hasNextLine()){
            // all information aside from '9's is noise that I need to be initialized to '0'
            matrix[y] = sc.nextLine().replaceAll("[0-8]", "0").toCharArray();
            y ++;
        }

        for(y = 0; y < MAXY; y++) {
            for(x = 0; x < MAXX; x++) {
                // I only need to visit new basins: they will have at least one element set to 0
                if(matrix[y][x] != '0')
                    continue;

                result = 0;
                sameBasin = new Stack<>();
                sameBasin.push(new Point2d(x, y));

                // I have to mark the points I have visited already,
                // so that I don't start loops visiting the same points more than once
                matrix[y][x] = '1';

                while(sameBasin.size() != 0){
                    result ++;
                    Point2d current = sameBasin.pop();

                    for(Point2d point : getAdjacent(current)){

                        // If a point it's 0 then I have to visit it still
                        if(matrix[point.y][point.x] == '0'){
                            sameBasin.push(point);
                            matrix[point.y][point.x] = '1';
                        }
                    }
                }

                if(result > results[0]){
                    results[0] = result;

                    // Swaps for a three-elements minHeap
                    if(results[0] > results[1]){
                        int temp = results[1];
                        results[1] = results[0];
                        results[0] = temp;
                    }
                    if(results[0] > results[2]){
                        int temp = results[2];
                        results[2] = results[0];
                        results[0] = temp;
                    }
                }
            }
        }

        System.out.println("Result part 2 = " + results[2] * results[1] * results[0]);
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
