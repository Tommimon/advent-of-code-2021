package Gonduls.d15;

import Gonduls.d02.Point2d;

import java.io.IOException;

public class Results {
    static final int size = 100;

    public static void main(String[] args) throws IOException {
        Input input = new Input("Gonduls/d15/input.txt");

        // I had implemented pathManager as a singleton for part 1, should have changed its implementation
        PathManager pathManager = PathManager.getInstance(newMatrix(input.getMatrix()));

        // In the Dijkstra algorithm I implemented only points whose coordinates are less than or equal to the endpoint's
        // coordinates are considered (lazy adaptation for part 2, but it works)
        System.out.println("Result part 1 = " + pathManager.getFinalScore(new Point2d(size - 1, size -1)));
        System.out.println("Result part 2 = " + pathManager.getFinalScore(new Point2d(size*5 - 1, size*5 -1)));

    }

    private static int[][] newMatrix(int[][] matrix){
        int[][] newMatrix = new int[5 * size][5 * size];

        for(int j = 0; j < 5; j++)
            for(int i = 0; i < 5; i++)
                for(int y = 0; y < size; y ++)
                    for(int x = 0; x < size; x ++) {
                        newMatrix[y + i * size][x + j * size] = (matrix[y][x] + i + j);
                        if(newMatrix[y + i * size][x + j * size] > 9)
                            newMatrix[y + i * size][x + j * size] -= 9;
                    }
        return newMatrix;
    }
}
