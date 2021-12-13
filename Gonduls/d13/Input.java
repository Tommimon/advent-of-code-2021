package Gonduls.d13;

import Gonduls.d02.Point2d;

import java.io.*;
import java.util.*;

public class Input {
    private final List<Point2d> nums = new ArrayList<>();
    private final List<String> folds = new ArrayList<>();

    public Input(String filePath) throws IOException {
        Scanner scan = new Scanner(new File(filePath));
        while (true) {
            String str = scan.nextLine();
            if(str.equals(""))
                break;

            String[] both = str.split(",");
            nums.add(new Point2d(Integer.parseInt(both[0]), Integer.parseInt(both[1])));
        }

        while (scan.hasNextLine())
            folds.add(scan.nextLine().substring(11));
    }

    // it's better to return a copy of internal attributes instead of returning the Object itself
    public List<String> getFolds(){
        return new ArrayList<>(folds);
    }

    public List<Point2d> getNums(){
        List<Point2d> copy = new ArrayList<>();
        for(Point2d point : nums)
            copy.add(point.clone());

        return copy;
    }
}
