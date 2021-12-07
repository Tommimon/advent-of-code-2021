package marcomole00.d07;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.abs;
import static java.lang.Math.min;


public class day7 {


    public static void main(String[] args) {
        try{
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d07/input"));
            String stringa = br.lines().toList().get(0);
            ArrayList<Integer> numeri = new ArrayList<Integer>();
            Arrays.stream(stringa.split(",")).map(Integer::parseInt).forEachOrdered(numeri::add);
            numeri.sort(Integer::compareTo);

            int fuelConsumption = 0;
            int lowestFuelPoint;
            int result = (int) 10e8;


            for (int p: numeri) {
                lowestFuelPoint = p;
                fuelConsumption = 0;
                for (int n : numeri) {
                    fuelConsumption += abs( n - lowestFuelPoint) * ((abs( n - lowestFuelPoint)) +1 )/2;

                }
                System.out.println(fuelConsumption + " " + p);
                result = min(result, fuelConsumption);
            }

        System.out.println(result);
        } catch (IOException ignored){}
    }
}
