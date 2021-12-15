package Gonduls.d15;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Input {
    private final int[][] matrix = new int[Results.size][Results.size];

    public Input(String filePath) throws IOException {
        Scanner scan = new Scanner(new File(filePath));

        for (int y = 0; y < Results.size; y++) {
            String str = scan.nextLine();
            for (int x = 0; x < Results.size; x++)
                matrix[y][x] = str.charAt(x) - '0';
        }
    }

    public int[][] getMatrix() {
        int[][] copy = new int[Results.size][Results.size];
        for (int y = 0; y < Results.size; y++)
            System.arraycopy(matrix[y], 0, copy[y], 0, Results.size);

        return copy;
    }
}
