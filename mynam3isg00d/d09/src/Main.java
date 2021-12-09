package mynam3isg00d.d09.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static String[] map;
    public static String[] updatedmap;

    public static void main(String[] args) {
        Reader r = new Reader();
        String[] inp = r.parseInput("input.txt");
        ArrayList<Integer> basins = new ArrayList<Integer>();
        map = new String[inp.length+2];

        //Set borders, fuck the patriarchy embedded into the try catch system
        for(int i=0; i<map.length; i++) map[i] = new String();
        for(int i=0; i<inp[0].length()+2; i++) map[0] = map[0].concat("9");
        for(int i=1; i<inp.length+1; i++) map[i] = "9" + inp[i-1] + "9";
        for(int i=0; i<inp[0].length()+2; i++) map[inp.length + 1] = map[inp.length + 1].concat("9");

        //Part 1
        int risk = 0;
        for(int i = 1; i < map.length - 1; i++) {
            for(int j = 1; j < map[0].length() - 1; j++) {
                int ij1, ij2, ij3, ij4;
                int ij = Integer.parseInt("" + map[i].charAt(j));
                ij1 = Integer.parseInt("" + map[i-1].charAt(j));
                ij2 = Integer.parseInt("" + map[i].charAt(j+1));
                ij3 = Integer.parseInt("" + map[i+1].charAt(j));
                ij4 = Integer.parseInt("" + map[i].charAt(j-1));

                if ((ij < ij1) && (ij < ij2) && (ij < ij3) && (ij < ij4)) {
                    risk += (ij + 1);
                    updatedmap = Arrays.copyOf(map, map.length);
                    basins.add(findBasin(i, j));
                }
            }
        }
        System.out.println("Part 1: " + risk);

        Collections.sort(basins);
        Collections.reverse(basins);
        System.out.println("Part 2: " + (basins.get(0) * basins.get(1) * basins.get(2)));
    }

    public static int findBasin(int i, int j) {
        int retval = 1;
        updatedmap[i] = updatedmap[i].substring(0, j) + "9" + updatedmap[i].substring(j+1);

        int ij1 = Integer.parseInt("" + updatedmap[i-1].charAt(j));
        if(ij1 < 9) retval += findBasin(i-1, j);

        int ij2 = Integer.parseInt("" + updatedmap[i].charAt(j+1));
        if(ij2 < 9) retval += findBasin(i, j+1);

        int ij3 = Integer.parseInt("" + updatedmap[i+1].charAt(j));
        if(ij3 < 9) retval += findBasin(i+1, j);

        int ij4 = Integer.parseInt("" + updatedmap[i].charAt(j-1));
        if(ij4 < 9) retval += findBasin(i, j-1);

        return retval;
    }
}




