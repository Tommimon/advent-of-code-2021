package DAY01_1;

/*  Reading file library  */
import java.io.File;
import java.util.Scanner;

/*  FilePath
 *      testPath = "/Users/matteoblack/Desktop/AOC_2021/DAY01/src/test.txt"
 *      inputPath = "/Users/matteoblack/Desktop/AOC_2021/DAY01/src/input.txt"
 */

public class ObjDay01_1 {
    public static void main(String[] args){
        int valPrev;
        int valCurr;
        int counterResult;
        try{
            //Pointer File
            File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY01/src/input.txt");
            //Obj to read the File
            Scanner scanner = new Scanner(file);

            //Cycle to read
            counterResult = 0;
            valPrev = scanner.nextInt();
            while (scanner.hasNextInt()) {
                valCurr = scanner.nextInt();
                if(valCurr>valPrev){
                    counterResult++;
                }
                valPrev = valCurr;
            }
            System.out.println("Il risultato è: " + counterResult);
        }catch (Exception e){
            System.err.println("Error Scanner!!");
        }
    }
}
