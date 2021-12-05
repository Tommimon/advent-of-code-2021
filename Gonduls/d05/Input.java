package Gonduls.d05;

import java.io.*;
import java.util.*;

import Gonduls.d02.Point2d;

public class Input {
    private final List<Point2d[]> straightLines = new ArrayList<>(); // I represent lines with the 2 points at each line end
    private final List<Point2d[]> allLines = new ArrayList<>();

    public Input(String filePath) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(filePath));
        while(reader.hasNextLine()){
            String string = reader.nextLine();
            String[] parts = string.split(" -> ");

            // a,b = start,end of a line
            Point2d a = new Point2d(Integer.parseInt(parts[0].split(",")[0]), Integer.parseInt(parts[0].split(",")[1]));
            Point2d b = new Point2d(Integer.parseInt(parts[1].split(",")[0]), Integer.parseInt(parts[1].split(",")[1]));

            if(a.x == b.x || a.y == b.y){
                straightLines.add(new Point2d[]{a, b});
            }

            allLines.add(new Point2d[]{a, b});
        }
    }

    // probably need to send a copy of variables in here,
    // in case I need them more than once and someone modifies the lists outside this class
    public List<Point2d[]> getStraightLines(){
        List<Point2d[]> copy = new ArrayList<>();

        for(Point2d[] points : straightLines){
            copy.add(new Point2d[]{points[0].clone(), points[1].clone()});
        }
        return copy;
    }

    public List<Point2d[]> getAllLines(){
        List<Point2d[]> copy = new ArrayList<>();

        for(Point2d[] points : allLines){
            copy.add(new Point2d[]{points[0].clone(), points[1].clone()});
        }
        return copy;
    }
}
