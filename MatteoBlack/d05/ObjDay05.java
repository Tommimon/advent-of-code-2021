package MatteoBlack.d05;

import java.io.File;
import java.util.Scanner;

public class ObjDay05 {
    public static void main(String[] arg){
        int[][] map = new int[1000][1000];
        int counterResult = 0;
        int x1;
        int x2;
        int y1;
        int y2;
        int limitUp;
        int limitDown;
        int limitRight;
        int limitLeft;
        String[] coord;

        for(int r=0; r<1000; r++){
            for(int c=0; c<1000; c++){
                map[r][c] = 0;
            }
        }

        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY05/src/input.txt");
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                coord = scanner.nextLine().replace(" -> ", ",").split(",");

                //Coordinates
                x1 = Integer.parseInt(coord[0]);
                y1 = Integer.parseInt(coord[1]);
                x2 = Integer.parseInt(coord[2]);
                y2 = Integer.parseInt(coord[3]);

                if (Integer.parseInt(coord[1]) > Integer.parseInt(coord[3])) {
                    limitUp = Integer.parseInt(coord[1]);
                    limitDown = Integer.parseInt(coord[3]);
                }else{
                    limitUp = Integer.parseInt(coord[3]);
                    limitDown = Integer.parseInt(coord[1]);
                }
                if (Integer.parseInt(coord[0]) > Integer.parseInt(coord[2])) {
                    limitRight = Integer.parseInt(coord[0]);
                    limitLeft = Integer.parseInt(coord[2]);
                }else{
                    limitRight = Integer.parseInt(coord[2]);
                    limitLeft = Integer.parseInt(coord[0]);
                }

                //Increase Counter
                if(limitLeft==limitRight){
                    for(int r=limitDown; r<=limitUp; r++) map[r][limitLeft]++;
                }else if(limitUp==limitDown){
                    for(int c=limitLeft; c<=limitRight; c++) map[limitDown][c]++;
                }else{
                    //Alg part2 (Comment to evaluate part_1)
                    if((x1<x2)&&(y1<y2))
                        for(int i=0; i<=(x2-x1); i++) map[y1+i][x1+i]++;
                    else if((x1<x2)&&(y1>y2))
                        for(int i=0; i<=(x2-x1); i++) map[y1-i][x1+i]++;
                    else if((x1>x2)&&(y1<y2))
                        for(int i=0; i<=(x1-x2); i++) map[y1+i][x1-i]++;
                    else if((x1>x2)&&(y1>y2))
                        for(int i=0; i<=(x1-x2); i++) map[y1-i][x1-i]++;
                }
            }

            //Evaluate the result
            counterResult = 0;
            for(int r=0; r<1000; r++)
                for(int c=0; c<1000; c++) if(map[r][c]>1) counterResult++;

        }catch (Exception e){
            System.err.println("Errore!!");
        }
        System.out.println("Il risultato è: " + counterResult);
    }
}
