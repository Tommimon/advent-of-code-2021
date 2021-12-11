package marcomole00.d11;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;





public class day11 {

    public static void printMatrix(int[][] matrix) {
        for (int a[] : matrix) {
            for (int i : a) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }

    public static int sumMatrix(int[][] matrix)
    {
        int p=0;
        for (int a[] : matrix) {
            for (int i : a) {
                p+=i;
            }
        }
        return p;
    }

    public  static  void propagate (int[][] matrix, int i, int j)
    {
        int[] rows = {  -1, 0, 1, 0, 1, -1, 1, -1};
        int[] columns = {0, 1, 0, -1, 1, -1, -1, 1};

        if (matrix[i][j] >9 && matrix[i][j] < 50 ){
            matrix[i][j]+=50;
            for (int m = 0; m < 8; m++) {
               try {

                   matrix[i + rows[m]][j + columns[m]]++;
                   propagate(matrix, i + rows[m], j + columns[m]);


               } catch (ArrayIndexOutOfBoundsException e){}

            }

        }



    }

    public static void main(String[] args) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d11/input"));
            List<String> lista = br.lines().toList();
            int sizex = lista.size();
            int sizey = lista.get(0).length();

            int[][] matrix = new int[sizex][sizey];

            for (int i = 0; i<sizex;i++)
            {
                for (int j= 0; j<sizey;j++)
                {
                    matrix[i][j] = lista.get(i).charAt(j) - '0';
                }
            }


            // loop this 100 times
            int numberOfFlashes=0;
            int turn =0;

            while (true) {
               turn++;
               //printMatrix(matrix);
               //System.out.println();

               for (int i = 0; i < sizex; i++) {
                   for (int j = 0; j < sizey; j++) {
                       ++matrix[i][j];
                       propagate(matrix, i, j);

                   }
               }

               for (int i = 0; i < sizex; i++) {
                   for (int j = 0; j < sizey; j++) {
                       if (matrix[i][j] >9) {
                           ++numberOfFlashes;
                           matrix[i][j] =0;
                       }

                   }
               }

               if(sumMatrix(matrix)==0){
                   System.out.println(turn);
                   break;
               }

               if (turn == 100) {System.out.println(numberOfFlashes);}
           }

        } catch (IOException e) {}


    }

}



