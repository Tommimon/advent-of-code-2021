package mynam3isg00d.d01.src;

public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        int[] depths = strToInt(r.parseInput("input.txt"));

        //Part 1
        int count = 0;
        for(int i=1; i<depths.length; i++) {
            if (depths[i] > depths[i-1]) count++;
        }
        System.out.println("Part 1: " + count);

        //Part 2
        count = 0;
        for(int i=1; i<depths.length - 2; i++) {
            int A = depths[i-1] + depths[i] + depths[i+1];
            int B = depths[i] + depths[i+1] + depths[i+2];
            if (B > A) count++;
        }
        System.out.println("Part 2: " + count);
    }

    public static int[] strToInt(String... sA) {
        int[] ret = new int[sA.length];
        for(int i=0; i<sA.length; i++) ret[i] = Integer.parseInt(sA[i]);
        return ret;
    }
}
