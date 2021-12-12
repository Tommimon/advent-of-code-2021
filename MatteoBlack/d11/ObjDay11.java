package MatteoBlack.d11;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjDay11 {
    //Method to spread the FlashEffect
    private static int flash(int[][] map, int r, int c, int counter){
        counter++;
        //Increase
        map[r][c] = -1;
        if((r>0)&&(map[r-1][c]!=-1)) map[r-1][c]++;
        if((r>0)&&(c<9)&&(map[r-1][c+1]!=-1)) map[r-1][c+1]++;
        if((c<9)&&(map[r][c+1]!=-1)) map[r][c+1]++;
        if((r<9)&&(c<9)&&(map[r+1][c+1]!=-1)) map[r+1][c+1]++;
        if((r<9)&&(map[r+1][c]!=-1))  map[r+1][c]++;
        if((r<9)&&(c>0)&&(map[r+1][c-1]!=-1)) map[r+1][c-1]++;
        if((c>0)&&(map[r][c-1]!=-1)) map[r][c-1]++;
        if((r>0)&&(c>0)&&(map[r-1][c-1]!=-1)) map[r-1][c-1]++;
        //Check for spreading
        if((r>0)&&(map[r-1][c]>9)) counter = flash(map, r-1, c, counter);
        if((r>0)&&(c<9)&&(map[r-1][c+1]>9)) counter = flash(map, r-1, c+1, counter);
        if((c<9)&&(map[r][c+1]>9)) counter = flash(map, r, c+1, counter);
        if((r<9)&&(c<9)&&(map[r+1][c+1]>9)) counter = flash(map, r+1, c+1, counter);
        if((r<9)&&(map[r+1][c]>9)) counter = flash(map, r+1, c, counter);
        if((r<9)&&(c>0)&&(map[r+1][c-1]>9)) counter = flash(map, r+1, c-1, counter);
        if((c>0)&&(map[r][c-1]>9)) counter = flash(map, r, c-1, counter);
        if((r>0)&&(c>0)&&(map[r-1][c-1]>9)) counter = flash(map, r-1, c-1, counter);
        return counter;
    }

    public static void main(String[] arg){
        String[] temp;
        int specialStep = 0;
        int counterFlash = 0;
        int counterAll;
        int[][] map = new int[10][10];
        Scanner scanner = null;

        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY11/src/input.txt");
        try{
            scanner = new Scanner(file);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        for(int row=0; row<10; row++){
            temp = scanner.nextLine().split("|");
            for(int col=0; col<10; col++){
                map[row][col] = Integer.parseInt(temp[col]);
            }
        }
        //Changing condition for the 2nd part (Cond. 1st part:  step<=100)
        for(int step=1; true; step++){
            //Increase the MapElem
            for(int row=0; row<10; row++){
                for(int col=0; col<10; col++){
                    map[row][col]++;
                }
            }
            //Check for FlashEffect
            for(int row=0; row<10; row++){
                for(int col=0; col<10; col++){
                    if(map[row][col]>9) counterFlash = flash(map, row, col, counterFlash);
                }
            }
            //Check for the 2nd part
            counterAll = 0;
            for(int row=0; row<10; row++){
                for(int col=0; col<10; col++){
                    if(map[row][col]==-1){
                        counterAll++;
                        map[row][col] = 0;
                    }
                }
            }
            if(counterAll==100){
                specialStep = step;
                break;
            }
        }

        //System.out.println("Il risultato della parte 1 è: " + counterFlash);
        System.out.println("Il risultato della parte 2 è: " + specialStep);
    }
}
