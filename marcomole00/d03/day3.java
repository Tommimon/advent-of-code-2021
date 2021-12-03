package marcomole00.d03;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class day3 {

    public static void main(String[] args) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d03/input"));
            List<String> lista = br.lines().toList();
            String firstString = lista.get(0);
            String GammaString = "";
            String epsilonString = "";
            int counter = 0; //s
            int i = 0;

            while (i < firstString.length()) {
                counter = 0;
                for (String laterString : lista) {
                    counter += laterString.charAt(i) - '0';
                }
                if (counter > lista.size() / 2) {
                    GammaString = GammaString + '1';
                    epsilonString = epsilonString + '0';
                } else {
                    GammaString = GammaString + '0';
                    epsilonString = epsilonString + '1';
                }
                i++;
            }

            int out = Integer.parseInt(GammaString, 2) * Integer.parseInt(epsilonString, 2);
            System.out.println(out);
            // part2
            int counter1;
            int counter0;
            List<String> lista2 = lista;
            i = 0;
            char mostCommonBit;
            while (i < firstString.length()) {
                counter1 = 0;
                counter0 = 0;
                for (String laterString : lista2) {
                    if (laterString.charAt(i) == '1') {
                        counter1++;
                    } else {
                        counter0++;
                    }
                }
                if (counter1 >= counter0) {
                    mostCommonBit = '1';
                } else {
                    mostCommonBit = '0';
                }
                int finalI = i;
                char finalMostCommonBit = mostCommonBit;
                if (lista2.size() > 1) {
                    lista2 = lista2.stream().filter(s -> s.charAt(finalI) == finalMostCommonBit).toList();
                }
                i++;

            }
            char leastCommonBit;
            i = 0;
            while (i < firstString.length()) {
                counter1 = 0;
                counter0 = 0;
                for (String laterString : lista) {
                    if (laterString.charAt(i) == '1') {
                        counter1++;
                    } else {
                        counter0++;
                    }
                }
                if (counter1 >= counter0) {
                    leastCommonBit = '0';
                } else {
                    leastCommonBit = '1';
                }
                int finalI = i;
                char finalLeastCommonBit = leastCommonBit;
                if (lista.size() > 1) {
                    lista = lista.stream().filter(s -> s.charAt(finalI) == finalLeastCommonBit).toList();
                }
                i++;

            }
            int O2 = lista2.stream().mapToInt(s -> Integer.parseInt(s, 2)).reduce(0, (a, b) -> a + b);
            int CO2 = lista.stream().mapToInt(s -> Integer.parseInt(s, 2)).reduce(0, (a, b) -> a + b);
            System.out.println(O2 * CO2);


        } catch (IOException e) {
            System.out.println("oaoa");
        }

    }
}
