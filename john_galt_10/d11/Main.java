package john_galt_10.d11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("john_galt_10/d11/input.txt");
        Scanner myReader = new Scanner(myObj);
        int sol = parteUno(myReader);
        //int sol = parteDue(myReader);
        myReader.close();

        System.out.println("SOLUZIONE: " + sol);
    }

    private static int parteUno (Scanner reader) {
        Octopus[][] map = new Octopus[11][11];
        int height = 0;
        int width = 0;
        while (reader.hasNextLine()) {
            int w = 0;
            for (char c : reader.nextLine().toCharArray()) {
                map[height][w] = new Octopus(Character.getNumericValue(c));
                w++;
            }
            width = w;
            height++;
        }

        //OctopusMatrix.printMatrix(map, height, width);

        //attacco vicini
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //alto sx
                if (i > 0 && j > 0) map[i][j].attachOctopus(map[i-1][j-1]);
                //alto
                if (i > 0) map[i][j].attachOctopus(map[i-1][j]);
                //alto dx
                if (i > 0 && j < width-1) map[i][j].attachOctopus(map[i-1][j+1]);
                //sx
                if (j > 0) map[i][j].attachOctopus(map[i][j-1]);
                //dx
                if (j < width-1) map[i][j].attachOctopus(map[i][j+1]);
                //basso sx
                if (i < height-1 && j > 0) map[i][j].attachOctopus(map[i+1][j-1]);
                //basso
                if (i < height-1) map[i][j].attachOctopus(map[i+1][j]);
                //basso dx
                if (i < height-1 && j < width-1) map[i][j].attachOctopus(map[i+1][j+1]);
            }
        }

        int sol = 0;
        //day more
        for (int day = 0; day < 100; day++){
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    map[i][j].energyIncrease();
                }
            }

            //reset
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j].isFlashed()) {
                        sol++;
                        map[i][j].resetEnergyLevel();
                    }
                }
            }
        }

        OctopusMatrix.printMatrix(map, height, width);

        return sol;
    }

    private static int parteDue (Scanner reader) {
        Octopus[][] map = new Octopus[11][11];
        int height = 0;
        int width = 0;
        while (reader.hasNextLine()) {
            int w = 0;
            for (char c : reader.nextLine().toCharArray()) {
                map[height][w] = new Octopus(Character.getNumericValue(c));
                w++;
            }
            width = w;
            height++;
        }

        //OctopusMatrix.printMatrix(map, height, width);

        //attacco vicini
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //alto sx
                if (i > 0 && j > 0) map[i][j].attachOctopus(map[i-1][j-1]);
                //alto
                if (i > 0) map[i][j].attachOctopus(map[i-1][j]);
                //alto dx
                if (i > 0 && j < width-1) map[i][j].attachOctopus(map[i-1][j+1]);
                //sx
                if (j > 0) map[i][j].attachOctopus(map[i][j-1]);
                //dx
                if (j < width-1) map[i][j].attachOctopus(map[i][j+1]);
                //basso sx
                if (i < height-1 && j > 0) map[i][j].attachOctopus(map[i+1][j-1]);
                //basso
                if (i < height-1) map[i][j].attachOctopus(map[i+1][j]);
                //basso dx
                if (i < height-1 && j < width-1) map[i][j].attachOctopus(map[i+1][j+1]);
            }
        }

        //day more
        int sol = 0;
        while (true) {
            sol++;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    map[i][j].energyIncrease();
                }
            }

            int flashedDuringThisStep = 0;
            //reset
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j].isFlashed()) {
                        flashedDuringThisStep++;
                        map[i][j].resetEnergyLevel();
                    }
                }
            }
            if (flashedDuringThisStep == width*height) {
                break;
            }
        }

        OctopusMatrix.printMatrix(map, height, width);

        return sol;
    }
}
