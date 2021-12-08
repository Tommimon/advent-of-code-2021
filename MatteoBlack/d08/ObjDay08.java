package MatteoBlack.d08;

import java.io.File;
import java.util.Scanner;

public class ObjDay08 {
    //Method to find allCharStr1 in Str2
    private static boolean containChar(String str1, String str2){
        boolean temp;
        char target;

        for(int i=0; i<str1.length(); i++){
            temp = false;
            target = str1.charAt(i);
            for(int c=0; (!temp)&&(c<str2.length()); c++)
                if(target==str2.charAt(c)) temp = true;
            if(temp==false) return false;
        }
        return true;
    }

    public static void main(String[] arg){
        int counterResult;
        int find;
        int numTemp;
        boolean done;
        String[] numbers = new String[10];
        String[] notes;
        String[] output;
        String[] input;
        String temp;
        String canc;
        Scanner scanner = null;

        //First part
        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY08/src/input.txt");
        try{
             scanner = new Scanner(file);
        }catch (Exception e){
            System.err.println("Errore acquisizione input");
        }

        //Find special digit filtered by #segments
        counterResult = 0;
        while(scanner.hasNextLine()){
            notes = scanner.nextLine().split(" \\| ");
            output = notes[1].split(" ");
            for(int i=0; i<output.length; i++)
                if((output[i].length()==2)||(output[i].length()==4)||(output[i].length()==3)||(output[i].length()==7))
                    counterResult++;
        }

        System.out.println("Il risultato della parte 1 è: " + counterResult);

        //Second part
        try{
            scanner = new Scanner(file);
        }catch (Exception e){
            System.err.println("Errore acquisizione input");
        }

        //Learn from input
        counterResult = 0;
        while(scanner.hasNextLine()){
            find = 0;
            done = false;
            notes = scanner.nextLine().split(" \\| ");
            input = notes[0].split(" ");
            for(int i=0; !done; i++){
                if((numbers[1]==null)&&(input[i].length()==2)){
                    numbers[1] = input[i];
                    find++;
                }else if((numbers[4]==null)&&(input[i].length()==4)){
                    numbers[4] = input[i];
                    find++;
                }else if((numbers[7]==null)&&(input[i].length()==3)){
                    numbers[7] = input[i];
                    find++;
                }else if((numbers[8]==null)&&(input[i].length()==7)){
                    numbers[8] = input[i];
                    find++;
                }else if((numbers[9]==null)&&(numbers[4]!=null)&&(numbers[1]!=null)&&(input[i].length()==6)&&(containChar(numbers[1], input[i]))&&(containChar(numbers[4], input[i]))){
                    numbers[9] = input[i];
                    find++;
                }else if((numbers[0]==null)&&(numbers[9]!=null)&&(numbers[1]!=null)&&(input[i].length()==6)&&(containChar(numbers[1], input[i]))&&(!containChar(numbers[9], input[i]))){
                    numbers[0] = input[i];
                    find++;
                }else if((numbers[6]==null)&&(numbers[0]!=null)&&(numbers[9]!=null)&&(input[i].length()==6)&&(!containChar(numbers[9], input[i]))){
                    numbers[6] = input[i];
                    find++;
                }else if((numbers[3]==null)&&(numbers[1]!=null)&&(input[i].length()==5)&&(containChar(numbers[1], input[i]))){
                    numbers[3] = input[i];
                    find++;
                }else if((numbers[5]==null)&&(numbers[1]!=null)&&(numbers[4]!=null)&&(input[i].length()==5)){
                    temp = numbers[4];
                    for(int c=0; c<numbers[1].length(); c++){
                        canc = "" + numbers[1].charAt(c);
                        temp = temp.replace(canc, "");
                    }
                    if(containChar(temp, input[i])){
                        numbers[5] = input[i];
                        find++;
                    }
                }else if((find==9)&&(input[i].length()==5)&&(!containChar(numbers[5], input[i]))&&(!containChar(numbers[3], input[i]))){
                    numbers[2] = input[i];
                    find++;
                }
                //Restart condition & Exit condition
                if(find==10) done = true;
                if((i==input.length-1)&& !done) i = -1;
            }

            //Work out the result
            numTemp = 0;
            output = notes[1].split(" ");
            for(int i=0; i<output.length; i++){
                for(int c=0; c<numbers.length; c++){
                    if((numbers[c].length()==output[i].length())&&(containChar(output[i], numbers[c]))){
                        numTemp += c * Math.pow(10, (output.length-i-1));
                    }
                }
            }
            counterResult += numTemp;

            //Clean ArrayNumbers & prepared it for the new line
            for(int i=0; i<10; i++){
                numbers[i] = null;
            }
        }

        System.out.println("Il risultato della parte 2 è: " + counterResult);
    }
}
