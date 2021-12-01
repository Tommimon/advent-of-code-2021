package marcomole00.d01;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class myClass {
    public static void main(String[] args) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d01/input1"));

            int number1[] = br.lines().mapToInt(Integer::parseInt).toArray();
            int c1 = 0;
            int c2 = 0;

            for (int i= 1; i< number1.length;i++) {
                if (number1[i] - number1[i - 1] > 0) {
                    c1++;
                }
                try {
                    if (number1[i + 2] - number1[i - 1] > 0) {
                        c2++;
                    }
                } catch (IndexOutOfBoundsException e) {
                }
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
