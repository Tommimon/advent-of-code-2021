import java.io.File;
import java.util.Scanner;
public class Main {
    private static long result=0;
    public static void main(String args[]){
        try {
            File inputFile = new File("input2.txt");
            Scanner input = new Scanner(inputFile);
            String direction=null;
            int distance, horizontal=0, depth=0;
            while (input.hasNext()){
                direction=input.next();
                distance=input.nextInt();
                switch(direction){
                    case "forward":
                        horizontal+=distance;
                        break;
                    case "up":
                        depth-=distance;
                        break;
                    case "down":
                        depth+=distance;
                        break;
                }
                result=horizontal*depth;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println(result);
    }
}