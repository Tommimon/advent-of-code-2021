package MatteoBlack.d02;

/*  Reading file library  */
import java.io.File;
import java.util.Scanner;

/*  FilePath
 *      testPath = "/Users/matteoblack/Desktop/AOC_2021/DAY02/src/test.txt"
 *      inputPath = "/Users/matteoblack/Desktop/AOC_2021/DAY02/src/input.txt"
 */

public class ObjDay02_2 {
    public static void main(String[] args){
        String[] instr ;
        String temp;
        int aim;
        int xCounter;
        int yCounter;
        try{
            //Pointer File
            File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY02/src/input.txt");
            //Obj to read the File
            Scanner scanner = new Scanner(file);

            //Cycle to read
            aim = 0;
            xCounter = 0;
            yCounter = 0;
            instr = new String[2];
            while (scanner.hasNextLine()) {
                temp = scanner.nextLine();
                instr = temp.split(" ", 2);
                if(instr[0].contentEquals("forward")){
                    xCounter += Integer.parseInt(instr[1]);
                    yCounter = yCounter + (aim*Integer.parseInt(instr[1]));
                }else if(instr[0].contentEquals("up")){
                    aim -= Integer.parseInt(instr[1]);
                }else if(instr[0].contentEquals("down")){
                    aim += Integer.parseInt(instr[1]);
                }
            }
            System.out.println("Il risultato è: " + xCounter*yCounter);
        }catch (Exception e){
            System.err.println("Error Scanner!!");
        }
    }
}
