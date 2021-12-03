package MatteoBlack.d03;

import java.io.File;
import java.util.Scanner;

/*  FilePath
 *      testPath = "/Users/matteoblack/Desktop/AOC_2021/DAY03/src/test.txt"
 *      inputPath = "/Users/matteoblack/Desktop/AOC_2021/DAY03/src/input.txt"
 */

public class ObjDay03_1 {
    public static void main(String[] args){
        String line;
        int[] sum;
        int counterLine;
        int result;
        String gamma;
        String epsilon;

        try {
            //Pointer File
            File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY03/src/input.txt");
            //Obj to read the File
            Scanner scanner = new Scanner(file);

            //Alg
            counterLine = 1;
            line = scanner.nextLine();
            sum = new int[line.length()];
            for(int pos=0; pos<line.length(); pos++){
                sum[pos] = (line.charAt(pos) - '0');
            }
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                for(int pos=0; pos<line.length(); pos++){
                    sum[pos] += (line.charAt(pos) - '0');
                }
                counterLine++;
            }

            //Result
            gamma = new String();
            epsilon = new String();
            for(int pos=0; pos<line.length(); pos++){
                if(sum[pos]>(counterLine/2)){
                    gamma += "1";
                    epsilon += "0";
                }else{
                    gamma += "0";
                    epsilon += "1";
                }
            }
            result = Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
            System.out.println("Il risultato è: " + result);

        }catch (Exception e){
            System.err.println("Error Scanner!!");
        }
    }
}
