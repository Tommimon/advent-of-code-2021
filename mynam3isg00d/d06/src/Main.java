package mynam3isg00d.d06.src;

public class Main {
    public static void main(String[] args) {
        String inp = "3,3,5,1,1,3,4,2,3,4,3,1,1,3,3,1,5,4,4,1,4,1,1,1,3,3,2,3,3,4,2,5,1,4,1,2,2,4,2,5,1,2,2,1,1,1,1,4,5,4,3,1,4,4,4,5,1,1,4,3,4,2,1,1,1,1,5,2,1,4,2,4,2,5,5,5,3,3,5,4,5,1,1,5,5,5,2,1,3,1,1,2,2,2,2,1,1,2,1,5,1,2,1,2,5,5,2,1,1,4,2,1,4,2,1,1,1,4,2,5,1,5,1,1,3,1,4,3,1,3,2,1,3,1,4,1,2,1,5,1,2,1,4,4,1,3,1,1,1,1,1,5,2,1,5,5,5,3,3,1,2,4,3,2,2,2,2,2,4,3,4,4,4,1,2,2,3,1,1,4,1,1,1,2,1,4,2,1,2,1,1,2,1,5,1,1,3,1,4,3,2,1,1,1,5,4,1,2,5,2,2,1,1,1,1,2,3,3,2,5,1,2,1,2,3,4,3,2,1,1,2,4,3,3,1,1,2,5,1,3,3,4,2,3,1,2,1,4,3,2,2,1,1,2,1,4,2,4,1,4,1,4,4,1,4,4,5,4,1,1,1,3,1,1,1,4,3,5,1,1,1,3,4,1,1,4,3,1,4,1,1,5,1,2,2,5,5,2,1,5";
        String test = "3,4,3,1,2";

        //name chosen by tommaso montanari, who solved p vs np
        long[] giovanni = new long[9];
        for(String s : inp.split(",")) {
            giovanni[Integer.parseInt(s)]++;
        }

        int days = 0;
        while(days < 256) {
            long temp = giovanni[0];
            for(int i=1; i<giovanni.length; i++) {
                giovanni[i-1] = giovanni[i];
            }
            giovanni[8] = temp;
            giovanni[6] += temp;

            if(days == 79) System.out.println("Part 1: " + findSum(giovanni));
            days++;
        }
        System.out.println("Part 2: " + findSum(giovanni));
    }

    public static long findSum(long g[]) {
        long sum = 0;
        for(int i=0; i<g.length; i++) sum += g[i];
        return sum;
    }
}
