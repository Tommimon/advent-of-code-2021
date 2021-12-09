package MatteoBlack.d09;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ObjDay09 {
    //Method to search Basin
    private static int surrounded(ArrayList<Integer[]> map, int row, int col){
        int result = 0;
        //Look at all direction
        if((row>0)&&(map.get(row-1)[col]!=9)&&(map.get(row-1)[col]!=-1)){
            result++;
            map.get(row-1)[col]=-1;
            result += surrounded(map, row-1, col);
        }
        if((col>0)&&(map.get(row)[col-1]!=9)&&(map.get(row)[col-1]!=-1)){
            result++;
            map.get(row)[col-1]=-1;
            result += surrounded(map, row, col-1);
        }
        if((row<map.size()-1)&&(map.get(row+1)[col]!=9)&&(map.get(row+1)[col]!=-1)){
            result++;
            map.get(row+1)[col]=-1;
            result += surrounded(map, row+1, col);
        }
        if((col<map.get(row).length-1)&&(map.get(row)[col+1]!=9)&&(map.get(row)[col+1]!=-1)){
            result++;
            map.get(row)[col+1]=-1;
            result += surrounded(map, row, col+1);
        }
        return result;
    }
    public static void main(String[] arg){
        int level;
        int result1;
        int result2;
        int tempBasin;
        int counterMin;
        int[] bestBasin = new int[3];
        Integer[] tempRow;
        ArrayList<Integer[]> map = new ArrayList<>();
        String temp[];
        Scanner scanner = null;

        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY09/src/input.txt");
        try{
            scanner = new Scanner(file);
        }catch (Exception e){
            System.err.println("Err acquisizione!");
        }

        //Fill the map
        while(scanner.hasNextLine()){
            temp = scanner.nextLine().split("|");
            tempRow = new Integer[temp.length];
            for(int i=0; i<temp.length; i++)
                tempRow[i] = Integer.parseInt(temp[i]);
            map.add(tempRow);
        }

        //Alg for the first part
        result1 = 0;
        for(int r=0; r<map.size(); r++){
            for(int c=0; c< map.get(r).length; c++){
                counterMin = 0;
                level = 4;
                if((r>0)&&(map.get(r)[c]<map.get(r-1)[c])) counterMin++;
                else if(r==0) level--;
                if((c>0)&&(map.get(r)[c]<map.get(r)[c-1])) counterMin++;
                else if(c==0) level--;
                if((r<map.size()-1)&&(map.get(r)[c]<map.get(r+1)[c])) counterMin++;
                else if(r==map.size()-1) level--;
                if((c<map.get(r).length-1)&&(map.get(r)[c]<map.get(r)[c+1])) counterMin++;
                else if(c==map.get(r).length-1) level--;

                if(counterMin==level) result1 += map.get(r)[c]+1;
            }
        }
        //Alg for the second part
        for(int r=0; r<map.size(); r++){
            for(int c=0; c< map.get(r).length; c++){
                tempBasin = 0;
                if((map.get(r)[c]!=9)&&(map.get(r)[c]!=-1)) tempBasin = surrounded(map, r, c);
                Arrays.sort(bestBasin);
                for(int i=0; i<3; i++){
                    if(bestBasin[i]<tempBasin){
                        bestBasin[i] = tempBasin;
                        break;
                    }
                }
            }
        }
        result2 = 1;
        for(int i=0; i<3; i++) result2 *= bestBasin[i];

        System.out.println("Il risultato parte 1 è: " + result1);
        System.out.println("Il risultato parte 2 è: " + result2);
    }
}
