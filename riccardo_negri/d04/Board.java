package riccardo_negri.d04;

import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private final int[][] matrix;
    private final int rows;
    private final int cols;

    public Board(Scanner reader, int rows, int cols){
        matrix = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;
        for(int i = 0; i < rows; i++) {
            String temp = reader.nextLine();
            Scanner scanner = new Scanner(temp);
            for (int j = 0; j < cols; j++){
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    public void printBoard() {
        for(int i = 0; i < rows; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("\n");
    }

    public void play(int number) {
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++){
                if (matrix[i][j] == number) {
                    matrix[i][j] = -1;
                }
            }
        }
    }

    public boolean isWinner() {
        for(int i = 0; i < rows; i++) {
            boolean winner = true;
            for (int j = 0; j < cols; j++){
                if (matrix[i][j] != -1) {
                    winner = false;
                    break;
                }
            }
            if (winner) {
                return true;
            }
        }
        for(int i = 0; i < rows; i++) {
            boolean winner = true;
            for (int j = 0; j < cols; j++){
                if (matrix[j][i] != -1) {
                    winner = false;
                    break;
                }
            }
            if (winner) {
                return true;
            }
        }
        return false;
    }

    public int unmarkedNumbersTotal(){
        int total = 0;
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++){
                if (matrix[i][j] != -1) {
                    total += matrix[i][j];
                }
            }
        }
        return total;
    }
}
