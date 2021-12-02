//Wrote this at 23:34 after a very long day: I know it looks awful, better avoid reading. Not gonna fix it, not worth it.
import java.io.File;
import java.util.Scanner;
public class Main {
    private static int counter=0;
    public static void main(String args[]){
        try {
            File inputFile = new File("input1.txt");
            Scanner input = new Scanner(inputFile);
            int first=input.nextInt();
            int second=input.nextInt();
            int third=input.nextInt();
            int currDepth=(first)+second+third;
            while (input.hasNextInt()){
                first=second;
                second=third;
                third=input.nextInt();
                int prevDepth=currDepth;
                currDepth=first+second+third;

                if(currDepth>prevDepth){
                    counter++;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println(counter);
    }
}