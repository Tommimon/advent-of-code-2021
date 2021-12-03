package marcoparadina.d03._2;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]){
        try{
            File inputFile = new File("input3.txt");
            Scanner input = new Scanner(inputFile);

            Oxygen oxygen = new Oxygen(input);

            File inputFile1 = new File("input3.txt");
            Scanner input1 = new Scanner(inputFile1);

            CO2 carbon = new CO2(input1);
            System.out.println(oxygen.getRating()*carbon.getRating());


        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}