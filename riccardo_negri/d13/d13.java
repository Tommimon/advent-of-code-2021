//
// Day 13 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 724
// Second part solution: CPJBERUL
//

package riccardo_negri.d13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d13 {
    public static void main (String[] args) {
        File f = new File("riccardo_negri/d13/input.txt");
        Scanner reader = null;
        List<Coordinate> coordinates = new ArrayList<>();
        List<Coordinate> folds = new ArrayList<>();
        Set<Integer> coordinatesAfter = new HashSet<>();

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        boolean isCoordinate = true;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (Objects.equals(line, "")) {
                isCoordinate = false;
            }
            else if (isCoordinate) {
                coordinates.add(new Coordinate(Integer.parseInt(line.split(",")[0]), Integer.parseInt(line.split(",")[1])));
            }
            else {
                if (line.contains("x")) {
                    folds.add(new Coordinate(Integer.parseInt(line.split("=")[1]), 0));
                }
                else {
                    folds.add(new Coordinate(0, Integer.parseInt(line.split("=")[1])));
                }
            }
        }

        boolean firstFold = true;
        int lastX = 0, lastY = 0;
        for (Coordinate fold : folds) {
            if (fold.getX() == 0) {
                foldY(coordinates, fold.getY());
                lastY = fold.getY();
            }
            else {
                foldX(coordinates, fold.getX());
                lastX = fold.getX();
            }
            if (firstFold) {
                int MULTIPLIER = 100000;
                for (Coordinate c : coordinates) {
                    coordinatesAfter.add(c.getX() * MULTIPLIER + c.getY());
                }
                System.out.println(coordinatesAfter.size());
                firstFold = false;
            }
        }

        printCode(coordinates, lastX, lastY);
    }

    public static void foldY (List<Coordinate> coordinates, int y) {
        for (Coordinate c : coordinates) {
            if (c.getY() > y) {
                c.setY(c.getY() - (c.getY() - y) * 2);
            }
            assert (c.getY() < y);
        }
    }

    public static void foldX (List<Coordinate> coordinates, int x) {
        for (Coordinate c : coordinates) {
            if (c.getX() > x) {
                c.setX(c.getX() - (c.getX() - x) * 2);
            }
            assert (c.getX() < x);
        }
    }

    public static void printCode (List<Coordinate> coordinates, int maxX, int maxY) {
        int[][] matrix = new int[maxY][maxX];
        for (Coordinate c : coordinates) {
            matrix[c.getY()][c.getX()] = 1;
        }
        for (int r = 0; r < maxY; r++) {
            StringBuilder line = new StringBuilder();
            for (int c = 0; c < maxX; c++) {
                if (matrix[r][c] == 1) {
                    line.append("#");
                }
                else {
                    line.append(".");
                }
            }
            System.out.println(line);
        }
    }

}

