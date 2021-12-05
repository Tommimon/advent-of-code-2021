package marcomole00.d05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Math.*;

public class day5 {

    public static void main(String[] args) {
        try {
            boolean part2 = true;
            int SIZE = 1000;
            int fromX,fromY, toX,toY;
            Point[][] matrix = new Point[SIZE][SIZE];
            for (int i = 0; i<SIZE;i++)
            {
                for (int j = 0; j<SIZE;j++)
                {
                    matrix[i][j]= new Point();
                }
            }
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d05/input"));
            List<String[]> lista = br.lines().map(s -> s.split(" -> ")).toList();

           // lista.forEach(s-> System.out.println(s[0] + " " + s[1]));
            for (String[] line : lista)
            {
                fromX = Integer.parseInt(line[0].split(",")[0]);
                fromY = Integer.parseInt(line[0].split(",")[1]);
                toX = Integer.parseInt(line[1].split(",")[0]);
                toY = Integer.parseInt(line[1].split(",")[1]);

                if ((fromX == toX || fromY == toY)) {
                    for (int i = min(fromX, toX); i <= max(fromX, toX); i++) {
                        for (int j = min(fromY, toY); j <= max(fromY, toY); j++) {
                            matrix[i][j].incrementPass();
                        }
                    }
                }
                else if(part2)
                {
                    //ystem.out.println("x: " + fromX + " y: " + fromY  );
                    int xDiff = toX - fromX;
                    int yDiff = toY - fromY;
                    int xIncr=0; int yIncr = 0;
                    matrix[fromX+xIncr][fromY+yIncr].incrementPass();
                    do{

                        xIncr += xDiff / (abs(xDiff));
                        yIncr += yDiff / (abs(yDiff));
                        matrix[fromX+xIncr][fromY+yIncr].incrementPass();


                    }while(fromX + xIncr != toX  && fromY+yIncr != toY);


                }


            }

            int count = 0;
            for(Point[] line: matrix )
            {
                for (Point p : line)
                {
                    if (p.moreThanOnePass()) count++;
                }
            }
            System.out.println(count);



        } catch(IOException e){
            System.out.println("ME DESPIACE");
        }
    }
}
