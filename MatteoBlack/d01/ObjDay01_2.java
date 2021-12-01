package DAY01_2;

//Reading file library
import java.io.File;
import java.util.Scanner;

/*  FilePath
 *      testPath = "/Users/matteoblack/Desktop/AOC_2021/DAY01/src/test.txt"
 *      inputPath = "/Users/matteoblack/Desktop/AOC_2021/DAY01/src/input.txt"
 */

public class ObjDay01_2 {
    public static void main(String[] args){
        int token1 = 3;
        int token2 = 2;
        int token3 = 1;
        int token4 = 0;
        int sum1;
        int sum2;
        int sum3;
        int sum4;
        int temp;
        int counterResult;
        try{
            //Pointer File
            File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY01/src/input.txt");
            //Obj to read the File
            Scanner scanner = new Scanner(file);

            //Cycle to read
            counterResult = 0;
            sum1 = scanner.nextInt();
            sum2 = scanner.nextInt();
            sum3 = scanner.nextInt();
            sum2 += sum3;
            sum1 += sum2;
            sum4 = 0;
            while (scanner.hasNextInt()) {
                temp = scanner.nextInt();
                if(token1!=3){
                    sum1 += temp;
                    token1++;
                }
                if(token2!=3){
                    sum2 += temp;
                    token2++;
                }
                if(token3!=3){
                    sum3 += temp;
                    token3++;
                }
                if(token4!=3){
                    sum4 += temp;
                    token4++;
                }
                if (token1==3 && token2==3) {
                    if(sum2>sum1){
                        counterResult++;
                    }
                    token1 = 0;
                    sum1 = 0;
                } else if (token2==3 && token3==3) {
                    if(sum3>sum2){
                        counterResult++;
                    }
                    token2 = 0;
                    sum2 = 0;
                }else if (token3==3 && token4==3) {
                    if(sum4>sum3){
                        counterResult++;
                    }
                    token3 = 0;
                    sum3 = 0;
                }else if (token4==3 && token1==3) {
                    if (sum1 > sum4) {
                        counterResult++;
                    }
                    token4 = 0;
                    sum4 = 0;
                }
            }
            System.out.println("Il risultato è: " + counterResult);
        }catch (Exception e){
            System.err.println("Error Scanner!!");
        }
    }

}
