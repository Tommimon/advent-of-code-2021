//
// Day 11 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 1659
// Second part solution: 227
//

package riccardo_negri.d11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d11 {
    public static void main (String[] args) {
        File f = new File("riccardo_negri/d11/input.txt");
        Scanner reader = null;
        int SIZE = 10;
        int[][] matrix = new int[SIZE][SIZE];
        int[][] tempMatrix;
        int flashes = 0;
        int firstTime = 0;
        boolean allFlashSimultaneously;

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        int r = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            for (int c = 0; c < line.length(); c++) {
                matrix[r][c] = Integer.parseInt(String.valueOf(line.charAt(c)));
            }
            r++;
        }

        for (int step = 0; ; step++) {
            // increase every energy level by one
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    matrix[i][j] += 1;
                }
            }

            boolean check = true;
            while (check) {
                tempMatrix = matrix.clone();
                check = false;
                // make it flash (set it to -1 if to be propagated)
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (matrix[i][j] > 9) {
                            tempMatrix[i][j] = -1;
                            check = true;
                            if (step < 100) {
                                flashes += 1;
                            }
                        }
                    }
                }
                // increase adjacent by 1 if current is -1 (propagate)
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (tempMatrix[i][j] == -1) {
                            for (int a = -1; a <= 1; a++) {
                                for (int b = -1; b <= 1; b++) {
                                    try {
                                        if (tempMatrix[i + a][j + b] != -1 && tempMatrix[i + a][j + b] != 0 && !(a == 0 && b == 0)) {
                                            tempMatrix[i + a][j + b] += 1;
                                        }
                                    } catch (ArrayIndexOutOfBoundsException ignored) {

                                    }
                                }
                            }
                        }
                    }
                }
                // set -1 to 0 because it has been propagated
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (tempMatrix[i][j] == -1) {
                            tempMatrix[i][j] = 0;
                        }
                    }
                }

                matrix = tempMatrix.clone();
            }

            if (firstTime == 0) {
                allFlashSimultaneously = true;
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (matrix[i][j] != 0) {
                            allFlashSimultaneously = false;
                            break;
                        }
                    }
                }
                if (allFlashSimultaneously) {
                    firstTime = step + 1;
                }
            }

            if (firstTime != 0 && step >= 100) {
                break;
            }
        }

        System.out.println(flashes);
        System.out.println(firstTime);
    }
}

