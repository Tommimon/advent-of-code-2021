//
// Day 3 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 3847100
// Second part solution: 4105235
//

package riccardo_negri.d03;

import java.io.*;
import java.util.*;

public class d03 {
    public static void main(String[] args){
        File f = new File("riccardo_negri/d03/input.txt");
        Scanner reader = null;
        int[] ones = new int[100];
        int [] zeros = new int[100];
        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        String temp = "";
        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(true) {
            assert reader != null;
            if (!reader.hasNextLine()) break;
            temp = reader.nextLine();
            for (int i = 0; i < temp.length(); i++) {
                if (temp.charAt(i) == '1') {
                    ones[i] += 1;
                } else {
                    zeros[i] += 1;
                }
            }
        }
        for (int i = 0; i < temp.length(); i++) {
            if (ones[i] > zeros[i]) {
                gammaRate.append("1");
                epsilonRate.append("0");
            }
            else if (ones[i] < zeros[i]) {
                gammaRate.append("0");
                epsilonRate.append("1");
            }
            else {
                System.out.println("Something went wrong");
            }
        }

        System.out.println(Integer.parseInt(gammaRate.toString(), 2)*Integer.parseInt(epsilonRate.toString(), 2));

        List<String> numberOx = new ArrayList<>();
        List<String> numberCo2 = new ArrayList<>();
        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (reader.hasNextLine()) {
            temp = reader.nextLine();
            numberOx.add(temp.strip());
            numberCo2.add(temp.strip());
        }

        for (int i=0; i<temp.strip().length() && numberOx.size()>1; i++)  {
            int counterOnes = 0;
            int counterZeros = 0;
            List<String> numberOxToRemove = new ArrayList<>();
            for(String j : numberOx){
                if ('1' == j.charAt(i)) {
                    counterOnes += 1;
                }
                else if ('0' == j.charAt(i)) {
                    counterZeros += 1;
                }
                else {
                    System.out.println("Something went wrong");
                }
            }
            if (counterOnes > counterZeros) {
                for(String j : numberOx) {
                    if ('0' == j.charAt(i)) {
                        numberOxToRemove.add(j);
                    }
                }
            }
            else if (counterOnes < counterZeros) {
                for(String j : numberOx) {
                    if ('1' == j.charAt(i)) {
                        numberOxToRemove.add(j);
                    }
                }
            }
            else {
                for(String j : numberOx) {
                    if ('0' == j.charAt(i)) {
                        numberOxToRemove.add(j);
                    }
                }
            }

            for (String n : numberOxToRemove) {
                numberOx.remove(n);
            }


        }

        for (int i=0; i<temp.strip().length() && numberCo2.size()>1 ; i++)  {
            int counterOnes = 0;
            int counterZeros = 0;
            List<String> numberCo2ToRemove = new ArrayList<>();
            for(String j : numberCo2){
                if ('1' == j.charAt(i)) {
                    counterOnes += 1;
                }
                else if ('0' == j.charAt(i)) {
                    counterZeros += 1;
                }
                else {
                    System.out.println("Something went wrong");
                }
            }
            if (counterOnes > counterZeros) {
                for(String j : numberCo2) {
                    if ('1' == j.charAt(i)) {
                        numberCo2ToRemove.add(j);
                    }
                }
            }
            else if (counterOnes < counterZeros) {
                for(String j : numberCo2) {
                    if ('0' == j.charAt(i)) {
                        numberCo2ToRemove.add(j);
                    }
                }
            }
            else {
                for(String j : numberCo2) {
                    if ('1' == j.charAt(i)) {
                        numberCo2ToRemove.add(j);
                    }
                }
            }

            for (String n : numberCo2ToRemove) {
                numberCo2.remove(n);
            }
        }

        System.out.println(Integer.parseInt(numberOx.get(0), 2)*Integer.parseInt(numberCo2.get(0), 2));
    }
}
