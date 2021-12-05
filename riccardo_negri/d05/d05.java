//
// Day 5 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 5147
// Second part solution: 16925
//

package riccardo_negri.d05;

import java.io.*;
import java.util.Scanner;

public class d05 {
    public static void main (String[] args) {
        File f = new File("riccardo_negri/d05/input.txt");
        Scanner reader = null;

        int SIZE = 1000;
        final int[][] matrix1 = new int[SIZE][SIZE];
        final int[][] matrix2 = new int[SIZE][SIZE];
        int x1, x2, y1, y2;
        int counter1 = 0, counter2 = 0;

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            assert reader != null;
            if (!reader.hasNextLine()) break;

            String[] temp = reader.nextLine().split(" -> ");
            x1 = Integer.parseInt(temp[0].split(",")[0]);
            y1 = Integer.parseInt(temp[0].split(",")[1]);
            x2 = Integer.parseInt(temp[1].split(",")[0]);
            y2 = Integer.parseInt(temp[1].split(",")[1]);

            if (x1 == x2) {
                for (int i = y1; i <= y2; i++) {
                    matrix1[i][x1] += 1;
                    matrix2[i][x1] += 1;
                }
                for (int i = y2; i <= y1; i++) {
                    matrix1[i][x1] += 1;
                    matrix2[i][x1] += 1;
                }
            } else if (y1 == y2) {
                for (int i = x1; i <= x2; i++) {
                    matrix1[y1][i] += 1;
                    matrix2[y1][i] += 1;
                }
                for (int i = x2; i <= x1; i++) {
                    matrix1[y1][i] += 1;
                    matrix2[y1][i] += 1;
                }
            }
            else if (x1 < x2 && y1 < y2){
                for (int i = 0; i <= x2-x1; i++) {
                    matrix2[y1+i][x1+i] += 1;
                }
            }
            else if (x2 < x1 && y1 < y2){
                for (int i = 0; i <= x1-x2; i++) {
                    matrix2[y1+i][x1-i] += 1;
                }
            }
            else if (x1 < x2){
                for (int i = 0; i <= x2-x1; i++) {
                    matrix2[y1-i][x1+i] += 1;
                }
            }
            else {
                for (int i = 0; i <= x1-x2; i++) {
                    matrix2[y1-i][x1-i] += 1;
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (matrix1[i][j] > 1) {
                    counter1++;
                }
                if (matrix2[i][j] > 1) {
                    counter2++;
                }
            }
        }

        System.out.println(counter1);
        System.out.println(counter2);
    }
}