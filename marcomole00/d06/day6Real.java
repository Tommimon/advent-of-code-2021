package marcomole00.d06;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class day6Real {
    public static void main(String[] args) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d06/input"));
            String stringa = br.lines().toList().get(0);
            ArrayList<Integer> numeri = new ArrayList<Integer>();
            Arrays.stream(stringa.split(",")).map(Integer::parseInt).forEach(numeri::add);

            long[] numeriArray = new long[9];
            numeri.stream().forEach(i -> numeriArray[i]++);
            //for (long i : numeriArray){System.out.print(i);}
            long numberOfZeros =0;

            for (int j = 0; j<256; j++)
            {

                //for (long i : numeriArray){System.out.print(i);}
              // System.out.println();

                for(int k =0;k<8;k++)
                {
                    numeriArray[k] = numeriArray[k+1];
                }
                numeriArray[8] = numberOfZeros;
                numeriArray[6] += numberOfZeros;
                numberOfZeros = numeriArray[0];

                if(j ==79) {System.out.println(Arrays.stream(numeriArray).reduce(0, Long::sum));}
            }

            System.out.println(Arrays.stream(numeriArray).reduce(0, Long::sum));

        } catch (IOException ignored){}


    }
}
