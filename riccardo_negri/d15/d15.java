//
// Day 15 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 602
// Second part solution: 2935
//

package riccardo_negri.d15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d15 {
    static int SIZE = 100;
    static int INFINITY = Integer.MAX_VALUE;

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
        PriorityQueue<Point> minHeap = new PriorityQueue<>(new PointComparator());
        List<Point> alreadyProcessed = new ArrayList<>();
        HashSet<String> alreadyProcessedReg = new HashSet<>();
        int[][] distances = new int[matrix.length][matrix.length];

        // Initializations
        minHeap.add(start);
        distances[0][0] = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (!(i == 0 && j == 0)) {
                    distances[i][j] = INFINITY;
                    minHeap.add(new Point(i, j, INFINITY));
                }
            }
        }

        // The main loop
        while (minHeap.size() > 0) {
            Point minDistPoint = minHeap.remove(); // gets min dist point and removes it from Q
            alreadyProcessed.add(minDistPoint);
            alreadyProcessedReg.add(minDistPoint.getX() + "-" + minDistPoint.getY());

            // update neighbors distances
            int currDist = minDistPoint.getDist();
            int[][] shifts = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] s : shifts) {
                if (minDistPoint.getX() + s[0] >= 0 && minDistPoint.getX() + s[0] < matrix.length && minDistPoint.getY() + s[1] >= 0 && minDistPoint.getY() + s[1] < matrix.length) {
                    int tempDist = currDist + matrix[minDistPoint.getX() + s[0]][minDistPoint.getY() + s[1]];
                    if (!alreadyProcessedReg.contains((minDistPoint.getX() + s[0]) + "-" + (minDistPoint.getY() + s[1])) && tempDist < distances[minDistPoint.getX() + s[0]][minDistPoint.getY() + s[1]]) {
                        distances[minDistPoint.getX() + s[0]][minDistPoint.getY() + s[1]] = tempDist;
                        updateDistOf(minHeap, minDistPoint.getX() + s[0], minDistPoint.getY() + s[1], tempDist);
                    }
                }
            }

        }

        return alreadyProcessed;
    }


    public static void updateDistOf (PriorityQueue<Point> list, int x, int y, int dist) {
        for (Point p : list) {
            if (p.getX() == x && p.getY() == y) {
                list.remove(p);
                p.setDist(dist);
                list.add(p);
                return;
            }
        }
    }

    public static int getDistOf (List<Point> list, int x, int y) throws Exception {
        for (Point p : list) {
            if (p.getX() == x && p.getY() == y) {
                return p.getDist();
            }
        }
        return INFINITY;
    }

}
