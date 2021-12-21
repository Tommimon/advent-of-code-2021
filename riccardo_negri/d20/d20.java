//
// Day 20 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 5475
// Second part solution: 17548
//

package riccardo_negri.d20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class d20 {
    private static final int OFFSET = 110;
    private static final int SIZE = 100;

    public static void main (String[] args) throws Exception {
        File f = new File("riccardo_negri/d20/input.txt");
        Scanner reader = null;
        int[][] matrix = new int[SIZE + OFFSET * 2][SIZE + OFFSET * 2];
        List<Integer> algorithm = new ArrayList<>();

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        int r = 0;
        boolean firstLine = true;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (firstLine) {
                firstLine = false;
                for (int c = 0; c < line.length(); c++) {
                    algorithm.add(Integer.parseInt(String.valueOf(line.charAt(c)).replace("#", "1").replace(".", "0")));
                }
            }
            else if (line.length() != 0) {
                for (int c = 0; c < line.length(); c++) {
                    matrix[r + OFFSET][c + OFFSET] = Integer.parseInt(String.valueOf(line.charAt(c)).replace("#", "1").replace(".", "0"));
                }
                r++;
            }
        }

        //printMatrix(matrix);
        for(int i = 0; i < 50; i++) {
            matrix = enhance(matrix, algorithm, i+1);
            if(i == 1) {
                int count = 0;
                for (int[] line : matrix) {
                    for (int j = 0; j < matrix.length; j++) {
                        if (line[j] == 1) {
                            count++;
                        }
                    }
                }
                System.out.println(count);
            }
            //printMatrix(matrix);
        }

        int count = 0;
        for (int[] line : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                if (line[j] == 1) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static int[][] enhance (int[][] matrix, List<Integer> algorithm, int enhanceNumber) {
        int[][] newMatrix = new int[SIZE + OFFSET * 2][SIZE + OFFSET * 2];
        for (int i = enhanceNumber; i < OFFSET*2 + SIZE - enhanceNumber; i++) {
            for (int j = enhanceNumber; j < OFFSET*2 + SIZE - enhanceNumber ; j++) {
                StringBuilder binaryString = new StringBuilder();
                for (int a = -1; a <= 1; a++) {
                    for (int b = -1; b <= 1; b++) {
                        binaryString.append(matrix[i + a][j + b]);
                    }
                }
                int number = Integer.parseInt(binaryString.toString(), 2);
                newMatrix[i][j] = algorithm.get(number);
            }
        }
        return newMatrix;
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println("Matrix:");
        for(int i = 0; i < OFFSET*2+SIZE; i++) {
            for(int j = 0; j < OFFSET*2+SIZE; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print("#");
                }
                else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}