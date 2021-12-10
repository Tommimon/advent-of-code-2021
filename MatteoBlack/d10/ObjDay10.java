package MatteoBlack.d10;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ObjDay10 {
    public static void main(String[] arg){
        int result1;
        long result2;
        long tempResult;
        boolean corrupted;
        char[] line;
        int[] counterErr = new int[4];
        ArrayList<Long> scores = new ArrayList<>();
        ArrayList<Character> fifo = new ArrayList<>();
        Scanner scanner = null;

        //Acquiring the file
        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY10/src/input.txt");
        try{
            scanner = new Scanner(file);
        }catch (Exception e){
            System.err.println("Errore acquisizione file!");
        }
        while(scanner.hasNextLine()){
            //Alg Part1 (PDA - PushDown Automa)
            corrupted = false;
            line =  scanner.nextLine().toCharArray();
            for(int pos=0; pos<line.length; pos++){
                if((line[pos]=='(')||(line[pos]=='[')||(line[pos]=='{')||(line[pos]=='<')){
                    fifo.add(0, line[pos]);
                }else if(line[pos]==')'){
                    if(fifo.get(0)=='('){
                        fifo.remove(0);
                        continue;
                    }
                    corrupted = true;
                    counterErr[0]++;
                    break;
                }else if(line[pos]==']'){
                    if(fifo.get(0)=='['){
                        fifo.remove(0);
                        continue;
                    }
                    corrupted = true;
                    counterErr[1]++;
                    break;
                }else if(line[pos]=='}'){
                    if(fifo.get(0)=='{'){
                        fifo.remove(0);
                        continue;
                    }
                    corrupted = true;
                    counterErr[2]++;
                    break;
                }else if(line[pos]=='>'){
                    if(fifo.get(0)=='<'){
                        fifo.remove(0);
                        continue;
                    }
                    corrupted = true;
                    counterErr[3]++;
                    break;
                }
            }

            //Alg Part2 - Analyzed the remaining part of the fifo whether it isn't corrupted
            if((fifo.size()!=0)&&(!corrupted)){
                tempResult = 0;
                for(int pos=0; pos< fifo.size(); pos++){
                    tempResult*= 5;
                    if(fifo.get(pos)=='('){
                        tempResult += 1;
                    }else if(fifo.get(pos)=='['){
                        tempResult += 2;
                    }else if(fifo.get(pos)=='{'){
                        tempResult += 3;
                    }else if(fifo.get(pos)=='<'){
                        tempResult += 4;
                    }
                }
                scores.add(tempResult);
            }
            //Cleaning the fifo after reading a line
            fifo.removeAll(fifo);
        }
        Collections.sort(scores);
        result2 = scores.get((scores.size()-1)/2);
        result1 = counterErr[0]*3 + counterErr[1]*57 + counterErr[2]*1197 + counterErr[3]*25137;

        System.out.println("Il risultato della parte 1 è: " + result1);
        System.out.println("Il risultato della parte 2 è: " + result2);
    }
}
