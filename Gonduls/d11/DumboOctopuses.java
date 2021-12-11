package Gonduls.d11;

import Gonduls.d02.Point2d;

import java.util.List;

public class DumboOctopuses {
    private final int matrix[][] = new int[10][10];

    public DumboOctopuses(int[][] matrix){
        for(int y = 0; y<10; y++){
            System.arraycopy(matrix[y], 0, this.matrix[y], 0, 10);
        }
    }

    public int passStep(){
        int flashes = 0;
        boolean changes = true;
        boolean firstRound = true;
        while(changes){
            changes = false;
            for(int y = 0; y< 10; y++){
                for(int x = 0; x< 10; x++){
                    if(matrix[y][x] != 10) {
                        if(firstRound)
                            matrix[y][x] ++;

                        if(matrix[y][x] != 10)
                            continue;
                    }

                    //System.out.println(new Point2d(x, y));
                    flashes ++;
                    changes = true;
                    matrix[y][x] ++;
                    List<Point2d> adjacent = Adjacent.getAdjacent(new Point2d(x, y), 10, 10, true);
                    for(Point2d point : adjacent){
                        if(matrix[point.y][point.x] != 10)
                            matrix[point.y][point.x] ++;
                    }

                }
            }

            //System.out.println(this);
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
