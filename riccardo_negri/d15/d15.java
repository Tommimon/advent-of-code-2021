//
// Day 15 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 602
// Second part solution: 2935
//

package riccardo_negri.d15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class d15 {
    static int SIZE = 100;
    static int INFINITE = Integer.MAX_VALUE;

    public static void main (String[] args) throws Exception {
        File f = new File("riccardo_negri/d15/input.txt");
        Scanner reader = null;
        int[][] inputMatrix = new int[SIZE][SIZE];
        int[][] realMatrix = new int[SIZE * 5][SIZE * 5];

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        int z = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            for (int i = 0; i < line.length(); i++) {
                inputMatrix[z][i] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }
            z++;
        }

        // first part
        List<Point> distances = Dijkstra(inputMatrix, new Point(0, 0, 0));
        System.out.println(getDistOf(distances, SIZE - 1, SIZE - 1));

        // second part
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        int temp = inputMatrix[i][j] + x + y;
                        if (temp > 9) {
                            temp = 1 + temp % 10;
                            if (temp > 9) {
                                temp = 1;
                            }
                        }
                        realMatrix[i + x * SIZE][j + y * SIZE] = temp;
                    }
                }
            }
        }
        distances = Dijkstra(realMatrix, new Point(0, 0, 0));
        System.out.println(getDistOf(distances, SIZE * 5 - 1, SIZE * 5 - 1));
    }

    public static List<Point> Dijkstra (int[][] matrix, Point start) throws Exception {
        List<Point> Q = new ArrayList<>();
        List<Point> alreadyProcessed = new ArrayList<>();

        // Initializations
        Q.add(start);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (!(i == 0 && j == 0)) {
                    Q.add(new Point(i, j, INFINITE));
                }
            }
        }

        // The main loop
        while (Q.size() > 0) {
            Point minDistPoint = getMinDist(Q); // gets min dist point and removes it from Q
            alreadyProcessed.add(minDistPoint);

            // update neighbors distances
            int currDist = minDistPoint.getDist();
            int[][] shifts = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] s : shifts) {
                if (minDistPoint.getX() + s[0] >= 0 && minDistPoint.getX() + s[0] < matrix.length && minDistPoint.getY() + s[1] >= 0 && minDistPoint.getY() + s[1] < matrix.length) {
                    int tempDist = currDist + matrix[minDistPoint.getX() + s[0]][minDistPoint.getY() + s[1]];
                    if (tempDist < getDistOf(Q, minDistPoint.getX() + s[0], minDistPoint.getY() + s[1])) {
                        updateDistOf(Q, minDistPoint.getX() + s[0], minDistPoint.getY() + s[1], tempDist);
                    }
                }
            }

        }

        return alreadyProcessed;
    }

    public static void updateDistOf (List<Point> list, int x, int y, int dist) {
        for (Point p : list) {
            if (p.getX() == x && p.getY() == y) {
                p.setDist(dist);
            }
        }
    }

    public static int getDistOf (List<Point> list, int x, int y) throws Exception {
        for (Point p : list) {
            if (p.getX() == x && p.getY() == y) {
                return p.getDist();
            }
        }
        return INFINITE;
    }

    public static Point getMinDist (List<Point> list) {
        Point min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getDist() < min.getDist()) {
                min = list.get(i);
            }
        }
        list.remove(min);
        return min;
    }

}
