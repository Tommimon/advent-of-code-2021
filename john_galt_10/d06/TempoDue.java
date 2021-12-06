package john_galt_10.d06;

public class TempoDue {
    private static long [] tempiVita = new long [9];
    private static int day;

    public static void addPesce(int t) {
        tempiVita[t]++;
    }
    // 0   1   2   3   4   5   6   7   8
    //[0] [1] [2] [3] [4] [5] [6] [7] [8]
    public static void giornoInPiu() {
        day++;
        long copulati = tempiVita[0];
        for (int i = 0; i < 8; i++) {
            tempiVita[i] = tempiVita[i+1];
        }

        tempiVita[8] = copulati;
        tempiVita[6] += copulati;
    }

    public static long getNumeroPesci() {
        long pesci = 0;
        for (long k : tempiVita) {
            pesci += k;
        }
        return pesci;
    }
}
