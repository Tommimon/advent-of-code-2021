package marcomole00.d09;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class day9 {
    public static void main(String[] args) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d09/input"));
            List<String> lista = br.lines().toList();
            int SIZEx = lista.size();
            int SIZEy = lista.get(0).length();
            int numberOfNine=0;
            Point[][] heightmap = new Point[SIZEx][SIZEy];
            for(int i = 0; i<SIZEx; i++)
            {
                for (int j =0; j<SIZEy; j++)
                {
                    heightmap[i][j]= new Point(i,j,lista.get(i).charAt(j) - '0');
                    if(heightmap[i][j].height != 9){System.out.print(heightmap[i][j].height);}
                    else {System.out.print("*");}

                    if (heightmap[i][j].height==9){numberOfNine++;}
                }
                System.out.println();
            }
            List<Point> lowPoints = new ArrayList<>();
            int riskLevelTot=0;
            int sideCounter;
            int numberOfLowPoints=0;
            for(int i = 0; i<SIZEx; i++)
            {
                for (int j =0; j<SIZEy; j++)
                {
                    sideCounter = 0;
                    try{
                        if(heightmap[i][j].height < heightmap[i-1][j].height ) {sideCounter++;}
                    } catch (ArrayIndexOutOfBoundsException ignored){ sideCounter++;}
                    try{
                        if(heightmap[i][j].height  < heightmap[i+1][j].height ) {sideCounter++;}
                    } catch (ArrayIndexOutOfBoundsException ignored){ sideCounter++;}
                    try{
                        if(heightmap[i][j].height  < heightmap[i][j-1].height ) {sideCounter++;}
                    } catch (ArrayIndexOutOfBoundsException ignored){ sideCounter++;}
                    try{
                        if(heightmap[i][j].height  < heightmap[i][j+1].height ) {sideCounter++;}
                    } catch (ArrayIndexOutOfBoundsException ignored){ sideCounter++;}

                    if (sideCounter == 4 ) {
                        riskLevelTot += heightmap[i][j].height + 1;
                        System.out.println(riskLevelTot);
                        numberOfLowPoints++;
                        heightmap[i][j].isLowPoint = true;
                        //heightmap[i][j].basinSize = 0;
                        heightmap[i][j].xBasin = i;
                        heightmap[i][j].yBasin = j;
                        lowPoints.add(heightmap[i][j]);

                    }

                }

            }

            int numberOfOp =0;

            while(numberOfOp < 10*(SIZEx*SIZEy-numberOfNine))
            {
                for (int i=0; i<SIZEx;i++)
                {
                    for(int j = 0;j<SIZEy;j++)
                    {
                            if(heightmap[i][j].height==9 || heightmap[i][j].isLowPoint){continue;}
                        try
                        {
                            if((heightmap[i-1][j].isLowPoint || heightmap[i-1][j].xBasin + heightmap[i-1][j].yBasin != -2) &&  heightmap[i][j].height>heightmap[i-1][j].height)
                            {
                                heightmap[i][j].xBasin = heightmap[i-1][j].xBasin;
                                heightmap[i][j].yBasin = heightmap[i-1][j].yBasin;
                               // heightmap[heightmap[i][j].xBasin][heightmap[i][j].yBasin].basinSize++;
                                numberOfOp++;
                            }
                        } catch (ArrayIndexOutOfBoundsException ignored){}
                        try
                        {
                            if((heightmap[i+1][j].isLowPoint || heightmap[i+1][j].xBasin + heightmap[i+1][j].yBasin != -2) &&  heightmap[i][j].height>heightmap[i+1][j].height)
                            {
                                heightmap[i][j].xBasin = heightmap[i+1][j].xBasin;
                                heightmap[i][j].yBasin = heightmap[i+1][j].yBasin;
                                //heightmap[heightmap[i][j].xBasin][heightmap[i][j].yBasin].basinSize++;
                                numberOfOp++;
                            }
                        } catch (ArrayIndexOutOfBoundsException ignored){}
                        try
                        {
                            if((heightmap[i][j-1].isLowPoint || heightmap[i][j-1].xBasin + heightmap[i][j-1].yBasin != -2) && heightmap[i][j].height>heightmap[i][j-1].height)
                            {
                                heightmap[i][j].xBasin = heightmap[i][j-1].xBasin;
                                heightmap[i][j].yBasin = heightmap[i][j-1].yBasin;
                                //heightmap[heightmap[i][j].xBasin][heightmap[i][j].yBasin].basinSize++;
                                numberOfOp++;
                            }
                        } catch (ArrayIndexOutOfBoundsException ignored){}
                        try
                        {
                            if((heightmap[i][j+1].isLowPoint || heightmap[i][j+1].xBasin + heightmap[i][j+1].yBasin != -2) &&  heightmap[i][j].height>heightmap[i][j+1].height)
                            {
                                heightmap[i][j].xBasin = heightmap[i][j+1].xBasin;
                                heightmap[i][j].yBasin = heightmap[i][j+1].yBasin;
                               // heightmap[heightmap[i][j].xBasin][heightmap[i][j].yBasin].basinSize++;
                                numberOfOp++;
                            }
                        } catch (ArrayIndexOutOfBoundsException ignored){}
                    }
                }
             System.out.println(numberOfOp);

            }




            for(Point[] pa : heightmap) {
                for (Point p : pa) {
                    System.out.print("(" + p.xBasin+ "," + p.yBasin+ ") ");
                    if (p.xBasin !=-1 && p.yBasin!=-1){heightmap[p.xBasin][p.yBasin].basinSize++;}

                }

                System.out.println();
            }



            for(Point[] pa : heightmap) {
                for (Point p : pa) {
                    //System.out.print("(" + p.xBasin+ "," + p.yBasin+ ") ");
                    //heightmap[p.xBasin][p.yBasin].basinSize++;
                     System.out.print(p.basinSize + " ");
                }

                System.out.println();
            }

            lowPoints.sort(new sortByBasinSize());
            lowPoints.forEach(s->System.out.println(s.basinSize));




            System.out.println(riskLevelTot+ " <-- risk number-->" + numberOfLowPoints);

        } catch (IOException ignored){}
    }
}
