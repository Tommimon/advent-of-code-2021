//
// Day 9 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 607
// Second part solution: 900864
//

package riccardo_negri.d09;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class d09 {
    public static void main (String[] args) {
        File f = new File("riccardo_negri/d09/input.txt");
        Scanner reader = null;
        int SIZE_R = 100;
        int SIZE_C = 100;
        final int[][] matrix = new int[SIZE_R][SIZE_C];
        List<Integer> basins = new ArrayList<>();

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        int j = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            for (int i = 0; i < line.length(); i++) {
                matrix[j][i] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }
            j++;
        }

        int counter = 0;
        for (int i = 0; i < SIZE_R; i++) {
            for (int z = 0; z < SIZE_C; z++) {
                boolean check = true;
                for (int a = -1; a <= 1; a++) {
                    for (int b = -1; b <= 1; b++) {
                        try {
                            if (matrix[i + a][z + b] <= matrix[i][z] && !(a == 0 && b == 0)) {
                                check = false;
                            }
                        } catch (ArrayIndexOutOfBoundsException ignored) {

                        }
                    }
                }
                if (check) {
                    counter += matrix[i][z] + 1;
                    List<Integer> alreadyChecked = new ArrayList<>();
                    basins.add(getBasin(i, z, matrix, alreadyChecked, SIZE_C));
                }
            }
        }
        System.out.println(counter);
        basins = basins.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(basins.get(0) * basins.get(1) * basins.get(2));
    }

    public static int getBasin (int r, int c, int[][] matrix, List<Integer> alreadyChecked, int SIZE) {
        int basin = 1;
        for (int a = -1; a <= 1; a++) {
            for (int b = -1; b <= 1; b++) {
                try {
                    int temp = (r + a) * SIZE + (c + b);
                    if (!alreadyChecked.contains(temp) && matrix[r + a][c + b] >= matrix[r][c] + 1 && matrix[r + a][c + b] < 9 && (a == 0 || b == 0)) {
                        alreadyChecked.add(temp);
                        basin += getBasin(r + a, c + b, matrix, alreadyChecked, SIZE);
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {

                }
            }
        }
        return basin;
    }
}
