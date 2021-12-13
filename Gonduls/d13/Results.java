package Gonduls.d13;

import Gonduls.d02.Point2d;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Results {
    public static void main(String[] args) throws IOException {
        Input input = new Input("Gonduls/d13/input.txt");

        List<Point2d> points = input.getNums();
        List<String> folds = input.getFolds();

        System.out.println("Result part 1 = " + doFolds(points, folds.get(0)).size());

        for(String fold : folds){
            points = doFolds(points, fold);
        }

        // building line by line the final image and printing it
        for(int y = 0; y< 6; y++){
            StringBuilder str = new StringBuilder();
            for(int x = 0; x < 40; x ++){
                if(points.contains(new Point2d(x, y)))
                    str.append('█');
                else
                    str.append(' ');
            }
            System.out.println(str);
        }
    }

    private static List<Point2d> doFolds(List<Point2d> points, String fold){
        boolean x = fold.split("=")[0].equals("x");
        int lineNumber = Integer.parseInt(fold.split("=")[1]);
        List<Point2d> newList = new ArrayList<>();

        for(Point2d point : points){
            if(x){
                if(point.x < lineNumber) {
                    if (!newList.contains(point))
                        newList.add(point.clone());
                } else{
                    Point2d folded = new Point2d(point.x - 2*(point.x - lineNumber), point.y);
                    if (!newList.contains(folded))
                        newList.add(folded.clone());
                }
                continue;
            }
            // else
            if(point.y < lineNumber) {
                if (!newList.contains(point))
                    newList.add(point.clone());
            } else{
                Point2d folded = new Point2d(point.x , point.y- 2*(point.y - lineNumber));
                if (!newList.contains(folded))
                    newList.add(folded.clone());
            }
        }
        return newList;
    }
}

