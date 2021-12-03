package tommimon.d03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Script {
    public static void main(String[] args) throws FileNotFoundException {
        Vector<String> nums = new Vector<>();
        File myObj = new File("tommimon/d03/input");
        Scanner myReader = new Scanner(myObj);
        while(myReader.hasNextLine()) {
            String data = myReader.nextLine();
            nums.add(data);
        }
        myReader.close();

        Integer[] ones = new Integer[12];
        for (int i = 0; i < 12; i++) {
            ones[i] = 0;
        }
        for (String num : nums) {
            for (int i = 0; i < 12; i++) {
                if (num.charAt(i) == '1') {
                    ones[i]++;
                }
            }
        }

        String gamma = "";

        for (Integer u : ones) {
            if (u > nums.size()/2)
                gamma = gamma + '1';
            else
                gamma = gamma + '0';
        }

        int g = Integer.parseInt(gamma, 2);
        int res = g * ((int) (Math.pow(2, 12)) - 1 - g);
        System.out.println(res);

        Vector<String> o2nums;
        Vector<String> oldO2 = (Vector<String>) nums.clone();
        Vector<String> co2nums;
        Vector<String> oldCo2 = (Vector<String>) nums.clone();

        for(int i = 0; i < 12; i++) {
            o2nums = new Vector<>();
            co2nums = new Vector<>();
            int countO2 = 0;
            int countCo2 = 0;
            char selectedO2 = '1';
            char selectedCo2 = '1';
            for(String num : oldO2) {
                if (num.charAt(i) == '1')
                    countO2++;
            }
            for(String num : oldCo2) {
                if (num.charAt(i) == '1')
                    countCo2++;
            }
            if (countO2*2 < oldO2.size())
                selectedO2 = '0';
            if (countCo2*2 < oldCo2.size())
                selectedCo2 = '0';
            for (String num : oldO2) {
                if (num.charAt(i) == selectedO2)
                    o2nums.add(num);
            }
            if(oldCo2.size() != 1) {
                for (String num : oldCo2) {
                    if (num.charAt(i) != selectedCo2)
                        co2nums.add(num);
                }
                oldCo2 = (Vector<String>) co2nums.clone();
            }
            oldO2 = (Vector<String>) o2nums.clone();
        }

        res = Integer.parseInt(oldO2.get(0), 2) * Integer.parseInt(oldCo2.get(0), 2);
        System.out.println(res);
    }
}
