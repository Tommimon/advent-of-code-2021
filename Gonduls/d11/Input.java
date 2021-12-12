package Gonduls.d11;

import java.io.*;
import java.util.Scanner;

public class Input {
    final int[][] matrix = new int[10][10];

    public Input(String filePath) throws IOException {
        Scanner scan = new Scanner(new File(filePath));


        for (int y = 0; y < 10; y++) {

            String str = scan.nextLine();
            for (int x = 0; x < 10; x++)
                matrix[y][x] = str.charAt(x) - '0';


        }
    }

    public int[][] getDumbos() {
        int[][] dumbos = new int[10][10];
        for (int y = 0; y < 10; y++)
            System.arraycopy(matrix[y], 0, dumbos[y], 0, 10);

        return dumbos;
    }
}
