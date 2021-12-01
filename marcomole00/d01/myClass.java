package marcomole00.d01;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class myClass {
    public static void main(String[] args) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d01/input1"));

            int number1[] = br.lines().mapToInt(Integer::parseInt).toArray();
            int c1 = 0;
            int c2 = 0;



            List<Integer> windows = new ArrayList<>();
            for( int i= 0;i < number1.length -2 ;i++)
            {
               try {
                   int e = number1[i] + number1[i + 1] + number1[i + 2];
                   windows.add(e);
               } catch (IndexOutOfBoundsException e)
               {
                   System.out.println("ahoy");
               }
            }

            for (int i= 1; i< number1.length;i++)
            {
                if (number1[i]- number1[i-1] >0) {c1++;}

            }
            for (int i= 1; i< windows.size();i++)
            {
                if (windows.get(i) - windows.get(i-1) >0) {c2++;}

            }

            //part one
            System.out.println(c1);
            //part two
            System.out.println(c2);


        } catch(IOException e)
        {
            System.out.println("ahoy");
        }


    }
}
