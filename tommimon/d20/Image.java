package tommimon.d20;

import java.util.Arrays;

public class Image {
    static int size = 300;
    Boolean[][] grid;
    int offset;

    public Image() {
        grid = new Boolean[size][size];
        for (Boolean[] row: grid)
            Arrays.fill(row, false);
        offset = size/2;
    }

    void setWithOffset(int i, int j, boolean n) {
        grid[i + offset][j + offset] = n;
    }

    Image loadImage(String[] input) {
        offset = (size - input[0].length()) / 2;
        for(int i = 0; i < input.length; i++) {
            char[] row = input[i].toCharArray();
            for(int j = 0; j < row.length; j++) {
                setWithOffset(i, j, row[j] == '#');
            }
        }
        return this;
    }

    public void print() {
        for (Boolean[] i: this.grid) {
            for (boolean j: i) {
                System.out.print(j ? '#' : '.');
            }
            System.out.println();
        }
    }

    String slice(Boolean[] row, int j) {
        return Arrays.stream(Arrays.copyOfRange(row, j-1, j+2)).map(e -> e ? "1":"0").reduce("", (a,b) -> a + b);
    }

    public Image enhance(Boolean[] algorithm, boolean infinity) {
        Image output = new Image();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    output.grid[i][j] = infinity;
                }
                else {
                    String code = slice(grid[i-1], j) + slice(grid[i], j) + slice(grid[i+1], j);
                    output.grid[i][j] = algorithm[Integer.parseInt(code, 2)];
                }
            }
        }
        return output;
    }

    public int pixelsCount() {
        return Arrays.stream(grid).map(r -> Arrays.stream(r).reduce(0, (a,b)->a+(b?1:0), Integer::sum)).reduce(Integer::sum).get();
    }
}
