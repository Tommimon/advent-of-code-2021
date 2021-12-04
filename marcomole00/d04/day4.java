package marcomole00.d04;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day4 {
    public static void main(String[] args) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d04/input"));
            List<String> lista = br.lines().filter(s->!s.isBlank()).toList();
           // lista.forEach(System.out::println);
            List<Board> boardList = new ArrayList<>();

            int[] randomNumbers = Arrays.stream(lista.get(0).split(",")).mapToInt(Integer::parseInt).toArray();

            int[] tempLine;
            int[][][] tempMatrix = new int[(lista.size()-1)/5][5][5];
           for (int i = 1;i< lista.size();i+=5)
           {

               for(int j=0; j<5;j++)
               {
                   tempLine =  Arrays.stream(lista.get(i+j).replaceAll(" {2}", " ").split(" ")).filter(s-> s != "").mapToInt(Integer::parseInt).toArray();
                   System.arraycopy(tempLine, 0, tempMatrix[i / 5][j], 0, 5);
               }
               boardList.add(new Board(tempMatrix[i/5]));
           }

           boolean first = false;
           int metric = 0;
           int nuntemp = 0;

           for (int number : randomNumbers)
           {
              nuntemp = number;
               //System.out.println("number "+number);
               for (Board b : boardList)
               {

                   if (b.checkNumber(number)){
                       //b.print();
                    // System.out.println( b.metric());
                      metric = b.metric();
                        first = true;
                   }
                   if (first){break;}
               }
               if (first){break;}
           }
           System.out.println( nuntemp * metric);

            int cartucelle=0;
           for (int number : randomNumbers)
            {
                nuntemp = number;
                //System.out.println("number "+number);
                for (Board b : boardList)
                {
                    if (b.alreadyWon) {continue;}
                    if (b.checkNumber(number)){
                        //b.print();
                        //System.out.println( b.metric());
                        metric = b.metric();
                        b.alreadyWon = true;
                        cartucelle++;

                    }
                    if (cartucelle == boardList.size()){break;}

                }
                if (cartucelle == boardList.size()){break;}

            }
            System.out.println( nuntemp * metric);








        } catch (IOException e)
        {
            System.out.println("ao");
        }

    }
}
