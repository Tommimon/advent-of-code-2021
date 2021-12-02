package marcoparadina.d01._1;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static int temp=0;
    private static int counter=0;
    public static void main(String args[]){
        try {
            File inputFile = new File("input1.1.txt");
            Scanner input = new Scanner(inputFile);

            while (input.hasNextInt()){
                int next=input.nextInt();
                if(temp<next){
                    counter++;
                }
                temp=next;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        //the first measurement doesn't count: removing 1
        System.out.println(counter-1);
    }

}
