package marcomole00.d06;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//DEPRECATED, TOO SLOW FOR PART TWO
public class day6 {

    public static int regenerate(int num)
    {
        if (num == 0) return 7;
        return num;
    }


    public static void main(String[] args) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d06/test"));
            String stringa = br.lines().toList().get(0);
            ArrayList<Integer>  numeri = new ArrayList<Integer>();
            Arrays.stream(stringa.split(",")).map(Integer::parseInt).forEach(numeri::add);
            long temp = 0;
            for (int i = 0; i<80;i++)
            {
                for (int k : numeri)
                {
                  //  System.out.print(k + " ");
                }


                //System.out.println();
                numeri =  numeri.stream().map(day6::regenerate).map(n -> n-1).collect(Collectors.toCollection(ArrayList::new));
                for (long j = 0; j < temp; j++)
                {
                    numeri.add(8);
                }
                temp =  numeri.stream().filter(n -> n == 0).count();

            }




        System.out.println(numeri.stream().count());




        } catch (IOException E)
        {
            System.out.println("bello di mamma");
        }
    }
}
