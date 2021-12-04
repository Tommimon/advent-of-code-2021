package MatteoBlack.d04;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*  FilePath
 *      testPath = "/Users/matteoblack/Desktop/AOC_2021/DAY04/src/test.txt"
 *      inputPath = "/Users/matteoblack/Desktop/AOC_2021/DAY04/src/input.txt"
 */

public class ObjDay04_1 {
    public static void main(String[] arg){
        int num;
        int sum;
        int find;
        int check;
        int sumTab;
        int result = 0;
        int bestNum = 0;
        int bestSeq = 1000;
        int bestSeqTab = 1000;
        int[][] table = new int[5][5];
        String[] temp;
        ArrayList<Integer> numbers = new ArrayList<>();

        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY04/src/input.txt");
        try{
            Scanner scanner = new Scanner(file);
            temp = scanner.nextLine().split(",");
            for(int i=0; i< temp.length; i++) numbers.add(Integer.parseInt(temp[i]));

            while(scanner.hasNext()){
                //Fill & evaluate sum of all the table
                sum = 0;
                for(int r=0; r<5; r++){
                    for(int c=0; c<5; c++){
                        table[r][c] = scanner.nextInt();
                        sum += table[r][c];
                    }
                }

                //Check Row
                for(int row=0; row<5; row++){
                    find = 0;
                    Iterator itr = numbers.iterator();
                    for (int seq=0; itr.hasNext(); seq++){
                        num = (int) itr.next();
                        for(int col=0; col<5; col++){
                            if(num==table[row][col]){
                                if((find==4)&&(seq<bestSeq)){
                                    bestSeq = seq;
                                    bestNum = num;
                                }
                                find++;
                            }
                        }
                    }
                }

                //Check Column
                for(int col=0; col<5; col++){
                    find = 0;
                    Iterator itr = numbers.iterator();
                    for (int seq=0; itr.hasNext(); seq++){
                        num = (int) itr.next();
                        for(int row=0; row<5; row++){
                            if(num==table[row][col]){
                                if((find==4)&&(seq<bestSeq)){
                                    bestSeq = seq;
                                    bestNum = num;
                                }
                                find++;
                            }
                        }
                    }
                }

                //Evaluate the result
                if(bestSeqTab>bestSeq){
                    bestSeqTab = bestSeq;
                    Iterator itrSeq = numbers.iterator();
                    sumTab = sum;
                    for(int i=0; i<=bestSeq; i++){
                        check = (int)itrSeq.next();
                        for(int r=0; r<5; r++){
                            for(int c=0; c<5; c++){
                                if(check==table[r][c]) sumTab -= check;
                            }
                        }
                    }
                    result = sumTab*bestNum;
                }
            }
            System.out.println("Il risultato è: " + result);
        }catch (Exception e){
            System.err.println("Errore!!");
        }

    }
}
