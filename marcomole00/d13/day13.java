package marcomole00.d13;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class day13 {
    public static void main(String[] args) {
        try{
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d13/input"));
            int[] coordx = br.lines().filter(s->s.matches("\\d*,\\d*")).map(s -> s.split(",")[0]).mapToInt(Integer::parseInt).toArray();
            br = Files.newBufferedReader(Paths.get("marcomole00/d13/input")); // this is idiotic
            int[] coordy = br.lines().filter(s->s.matches("\\d*,\\d*")).map(s -> s.split(",")[1]).mapToInt(Integer::parseInt).toArray();
            br = Files.newBufferedReader(Paths.get("marcomole00/d13/input")); // this is idiotic
            List<String> creases= br.lines().filter(s->s.matches("fold along [yx]=\\d*")).map(s -> s.split(" ")[2]).toList();
            creases.forEach(System.out::println);




            int sizex = 2* creases.stream().filter(s -> s.matches("x=\\d*")).mapToInt(s -> Integer.parseInt(s.split("=")[1])).reduce(Integer.MIN_VALUE,Integer::max)+1;
            int sizey = 2 * creases.stream().filter(s -> s.matches("y=\\d*")).mapToInt(s -> Integer.parseInt(s.split("=")[1])).reduce(Integer.MIN_VALUE,Integer::max) +1;


            for(int x : coordx){System.out.print(x +" ");}
            System.out.println("max = " + sizex);
            for(int y : coordy){System.out.print(y +" ");}
            System.out.println("max = " + sizey);
            boolean[][] matrix = new boolean[sizey][sizex];
            for (int i = 0; i<sizey;i++)
            {
                for (int j =0; j <sizex;j++){matrix[i][j] = false;}
            }


            assert coordx.length == coordy.length;

            for (int i = 0; i<coordx.length;i++)
            {
                matrix[coordy[i]][coordx[i]] = true;
            }

            for (int i = 0; i<sizey;i++) {
                for (int j = 0; j < sizex; j++) {
                    if (matrix[i][j]) {
                       ///System.out.print("█");
                    } else {
                      //  System.out.print("-");
                    }
                }
               // System.out.println();

            }

            //first crease
            int numberOfPoint =0;
            boolean first = false;
            for (String crease : creases) {

                if (crease.charAt(0) == 'y') {
                    //int whereToCrease = Integer.parseInt(creases.get(0).split("=")[1]);
                    System.out.println(crease.charAt(0));
                    for (int i = 0; i < sizey / 2; i++) {
                        for (int j = 0; j < sizex; j++) {
                            matrix[i][j] = matrix[i][j] || matrix[sizey - 1 - i][j];
                            if (matrix[i][j]){numberOfPoint++;}
                        }
                    }
                    sizey = sizey / 2;
                } else {
                   // int whereToCrease = Integer.parseInt(creases.get(0).split("=")[1]);
                    System.out.println(crease.charAt(0));
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
                       System.out.print("█");
                       ++numberOfPoint;
                   } else {
                      System.out.print("-");
                   }

               }
               System.out.println();
           }


               System.out.println(numberOfPoint);
















        } catch (IOException iognored){}
    }
}
