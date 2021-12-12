package Gonduls.d11;

import Gonduls.d02.Point2d;

import java.util.List;

public class DumboOctopuses {
    private final int[][] matrix = new int[10][10];

    public DumboOctopuses(int[][] matrix){
        for(int y = 0; y<10; y++){
            System.arraycopy(matrix[y], 0, this.matrix[y], 0, 10);
        }
    }

    public int passStep(){
        int flashes = 0;
        boolean changes = true;
        boolean firstRound = true;

        // it cycles over the matrix to see if other flashes need to happen until no more flashes happen
        while(changes){
            changes = false;
            for(int y = 0; y< 10; y++){
                for(int x = 0; x< 10; x++){
                    // I need != and not < to differentiate flashes that were not already accounted for from others
                    if(matrix[y][x] != 10) {
                        // only need to add 1 to all elements during the first pass of the matrix
                        if(firstRound)
                            matrix[y][x] ++;

                        // if a flash didn't occur: skip next part
                        if(matrix[y][x] != 10)
                            continue;
                    }

                    flashes ++;
                    changes = true;
                    matrix[y][x] ++; // it goes to 11: the flash has been accounted for
                    List<Point2d> adjacent = Adjacent.getAdjacent(new Point2d(x, y), 10, 10, true);
                    for(Point2d point : adjacent){
                        if(matrix[point.y][point.x] != 10)
                            matrix[point.y][point.x] ++;
                    }

                }
            }

            firstRound = false;
        }

        for(int y = 0; y< 10; y++)
            for(int x = 0; x< 10; x++)
                if(matrix[y][x] >= 10)
                    matrix[y][x] = 0;

        return flashes;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("");
        for(int y = 0; y< 10; y++){
            str.append("[");
            for(int x = 0; x<10 ; x++){
                str.append(matrix[y][x]);
                if(x < 9)
                    str.append(", ");
            }
            str.append("]\n");
        }
        return str.toString();
    }
}
