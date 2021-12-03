package mynam3isg00d.d03.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        String[] valList = r.parseInput("input.txt");

        //Part 1
        String gamma = new String();
        int g, e, index = 0;
        while(index < valList[0].length()) {
            int one = 0;                                //lol
            for(String val : valList) if (val.charAt(index) == '1') one++;
            if (one > valList.length / 2) gamma = gamma.concat("1");
            else gamma = gamma.concat("0");
            index++;
        }
        g = Integer.parseInt(gamma, 2);
        e = (int)(Math.pow(2, index) - 1 - g);
        System.out.println("Part 1: " + (g*e));

        //Part 2, this is idiotic
        ArrayList<String> oxList = new ArrayList<>(Arrays.asList(valList));
        index = 0;
        while(index < valList[0].length() && oxList.size() != 1) {
            int oneOx = 0;
            char toCheckOx;
            for (String s : oxList) if (s.charAt(index) == '1') oneOx++;
            if (oneOx >= oxList.size() / 2) toCheckOx = '1';
            else toCheckOx = '0';
            Iterator<String> it = oxList.iterator();
            while (it.hasNext()) if (it.next().charAt(index) != toCheckOx) it.remove();
            index++;
        }

        ArrayList<String> coList = new ArrayList<>(Arrays.asList(valList));
        index = 0;
        while(index < valList[0].length() && coList.size() != 1) {
            int oneCo = 0;
            char toCheckCo;
            for(String s : coList) if(s.charAt(index) == '1') oneCo++;
            if (oneCo >= coList.size() / 2) toCheckCo = '0'; else toCheckCo = '1';
            Iterator<String> it = coList.iterator();
            while(it.hasNext()) if(it.next().charAt(index) != toCheckCo) it.remove();
            index++;
        }

        int ox = Integer.parseInt(oxList.get(0), 2);
        int co = Integer.parseInt(coList.get(0), 2);
        System.out.println("Part 2: " + (ox*co));
    }
}
