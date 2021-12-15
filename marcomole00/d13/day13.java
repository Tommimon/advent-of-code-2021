package marcomole00.d13;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class day13 {
    public static void main(String[] args) {
        try{

           String input = "marcomole00/d13/inputDvd";
            BufferedReader br = Files.newBufferedReader(Paths.get(input));
            int[] coordx = br.lines().filter(s->s.matches("\\d*,\\d*"))
                    .map(s -> s.split(",")[0])
                    .mapToInt(Integer::parseInt).toArray();
            br = Files.newBufferedReader(Paths.get(input)); // this is idiotic

            int[] coordy = br.lines().filter(s->s.matches("\\d*,\\d*"))
                    .map(s -> s.split(",")[1])
                    .mapToInt(Integer::parseInt).toArray();

            br = Files.newBufferedReader(Paths.get(input)); // this is idiotic
            List<String> creases= br.lines().filter(s->s.matches("fold along [yx]=\\d*")).map(s -> s.split(" ")[2]).toList();

            
            int sizex = 2* creases.stream().filter(s -> s.matches("x=\\d*")).mapToInt(s -> Integer.parseInt(s.split("=")[1])).reduce(Integer.MIN_VALUE,Integer::max)+1;
            int sizey = 2 * creases.stream().filter(s -> s.matches("y=\\d*")).mapToInt(s -> Integer.parseInt(s.split("=")[1])).reduce(Integer.MIN_VALUE,Integer::max) +1;

            //apparently is initialized at false, cool
            boolean[][] matrix = new boolean[sizey][sizex];

            assert coordx.length == coordy.length;

            for (int i = 0; i<coordx.length;i++) {
                matrix[coordy[i]][coordx[i]] = true;
            }

            int numberOfPoint =0;
            boolean first = false;
            for (String crease : creases) {
                if (crease.charAt(0) == 'y') {

                    for (int i = 0; i < sizey / 2; i++) {
                        for (int j = 0; j < sizex; j++) {
                            matrix[i][j] = matrix[i][j] || matrix[sizey - 1 - i][j];
                            if (matrix[i][j]){numberOfPoint++;}
                        }
                    }
                    sizey = sizey / 2;
                } else {
                    for (int i = 0; i < sizey; i++) {
                        for (int j = 0; j < sizex / 2; j++) {
                            matrix[i][j] = matrix[i][j] || matrix[i][sizex - 1 - j];
                            if (matrix[i][j]){numberOfPoint++;}
                        }
                    }
                    sizex = sizex / 2;
                }
                if (!first) {System.out.println(numberOfPoint); first = true;}
            }
            
           for (int i = 0; i<sizey;i++) {
               for (int j = 0; j < sizex; j++) {
                   if (matrix[i][j]) {
                       System.out.print("██");
                   } else {
                      System.out.print("  ");
                   }
               }
               System.out.println();
           }
           
        } catch (IOException ignored){}
    }
}
