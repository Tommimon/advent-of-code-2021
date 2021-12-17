package MatteoBlack.d13;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjDay13 {
    public static void main(String[] arg){
        boolean ready = false;
        boolean first = true;
        Integer[] tempCoord;
        int result1 = 0;
        int xMax = 0;
        int yMax = 0;
        int xLimit = 0;
        int yLimit = 0;
        String tempFirst;
        String[] tempSplit = null;
        int[][] paper = null;
        ArrayList<Integer[]> coord = new ArrayList<>();
        Scanner scanner = null;

        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY13/src/input.txt");
        try {
            scanner = new Scanner(file);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        //cond parte1 &&(xLimit==0)&&(yLimit==0)
        while((scanner.hasNextLine())){
            tempFirst = scanner.nextLine();
            if(tempFirst.contains("fold")){
                tempSplit = tempFirst.split(" ");
                tempSplit = tempSplit[2].split("=");
                if(tempSplit[0].equals("x")) xLimit = Integer.parseInt(tempSplit[1]);
                if(tempSplit[0].equals("y")) yLimit = Integer.parseInt(tempSplit[1]);
                ready = true;
            } else if(!tempFirst.equals("")){
                tempSplit = tempFirst.split(",");
                tempCoord = new Integer[2];
                tempCoord[0] = Integer.parseInt(tempSplit[0]);
                tempCoord[1] = Integer.parseInt(tempSplit[1]);
                coord.add(tempCoord);
                if(tempCoord[0]>xMax) xMax = tempCoord[0];
                if(tempCoord[1]>yMax) yMax = tempCoord[1];
            }
            if(ready){
                if(first){
                    paper = new int[yMax+1][xMax+1];
                    first = false;
                    if(xLimit==0) xLimit = xMax;
                    if(yLimit==0) yLimit = yMax;
                    for(int i=0; i<coord.size(); i++){
                        if((coord.get(i)[0]<=xLimit)&&(coord.get(i)[1]<=yLimit)){
                            paper[coord.get(i)[1]][coord.get(i)[0]] = 1;
                        }else if((coord.get(i)[1]<=yLimit)&&(coord.get(i)[0]>xLimit)){
                            paper[coord.get(i)[1]][2*xLimit-coord.get(i)[0]] = 1;
                        }else if((coord.get(i)[0]<=xLimit)&&(coord.get(i)[1]>yLimit)){
                            paper[2*yLimit-coord.get(i)[1]][coord.get(i)[0]] = 1;
                        }
                    }
                    for(int row=0; row<=yMax; row++){
                        for(int col=0; col<=xMax; col++){
                            result1 += paper[row][col];
                        }
                    }
                    continue;
                }
                if(tempSplit[0].equals("x")){
                    for(int row=0; row<=yLimit; row++){
                        for(int col=xLimit; (col<=2*xLimit)&&(col<=xMax); col++) {
                            if(paper[row][col]==1){
                                paper[row][2*xLimit-col] = 1;
                            }
                        }
                    }
                }
                else if(tempSplit[0].equals("y")){
                    for(int row=yLimit; (row<=2*yLimit)&&(row<=yMax); row++){
                        for(int col=0; col<=xLimit; col++) {
                            if(paper[row][col]==1){
                                paper[2*yLimit-row][col] = 1;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Il risultato della parte 1 è: " + result1);
        System.out.println("Il risultato della parte 2 è: ");
        for(int i=0; i<yLimit; i++){
            for(int c=0; c<xLimit; c++){
                if(paper[i][c]==1) System.out.print("#");
                else System.out.print("_");
            }
            System.out.print("\n");
        }
    }
}
