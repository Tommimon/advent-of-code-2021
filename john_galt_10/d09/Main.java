package john_galt_10.d09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("john_galt_10/d09/input.txt");
        Scanner myReader = new Scanner(myObj);
        //int sol = parteUno(myReader);
        int sol = parteDue(myReader);
        myReader.close();

        System.out.println("SOLUZIONE: " + sol);
    }

    private static int parteUno (Scanner reader) {
        int[][] mappa = new int[2000][2000];
        int i = 0;
        int larghezza=0;
        while (reader.hasNextLine()) {
            int j = 0;
            for (char c : reader.nextLine().toCharArray()) {
                mappa[i][j] = Character.getNumericValue(c);
                j++;
                larghezza = j;
            }
            i++;
        }
        int altezza = i;
        int ris = 0;
        for (int row = 0; row < altezza; row++) {
            for (int col = 0; col < larghezza; col++) {
                int x = check(mappa, row, col, altezza, larghezza);
                ris += x;
                if (x!=0) System.out.println(mappa[row][col] + ", r: " + row + " c: " + col);
            }
        }

        return ris;
    }

    private static int parteDue (Scanner reader) {
        int[][] mappa = new int[2000][2000];
        int i = 0;
        int larghezza=0;
        while (reader.hasNextLine()) {
            int j = 0;
            for (char c : reader.nextLine().toCharArray()) {
                mappa[i][j] = Character.getNumericValue(c);
                j++;
                larghezza = j;
            }
            i++;
        }
        int altezza = i;
        List<Integer> dimensioni = new ArrayList<>();
        for (int row = 0; row < altezza; row++) {
            for (int col = 0; col < larghezza; col++) {
                if (isMinimo(mappa, row, col, altezza, larghezza)) {
                    boolean[][] alreadyCounted = new boolean[altezza][larghezza];
                    int dim = calcolaDimensione(mappa, row, col, altezza, larghezza, alreadyCounted);
                    System.out.println("calcolato " + dim);
                    dimensioni.add(dim);
                }
            }
        }
        Collections.sort(dimensioni, (e1, e2) -> {
            if (e1>e2) return -1;
            if (e1<e2) return 1;
            return 0;
        });
        for (int u : dimensioni) {
            System.out.println("dimensione: " + u);
        }
        return dimensioni.get(0)*dimensioni.get(1)*dimensioni.get(2);
    }

    private static int check(int[][] m, int r, int c, int altezza, int larghezza) {
        if (r > 0 && m[r][c] >= m[r-1][c]) {
            return 0;
        }
        if (r < altezza-1 && m[r][c] >= m[r+1][c]) {
            return 0;
        }
        if (c < larghezza-1 && m[r][c] >= m[r][c+1]) {
            return 0;
        }
        if (c > 0 && m[r][c] >= m[r][c-1]) {
            return 0;
        }
        return 1 + m[r][c];
    }

    private static boolean isMinimo(int[][] m, int r, int c, int altezza, int larghezza) {
        if (r > 0 && m[r][c] >= m[r-1][c]) {
            return false;
        }
        if (r < altezza-1 && m[r][c] >= m[r+1][c]) {
            return false;
        }
        if (c < larghezza-1 && m[r][c] >= m[r][c+1]) {
            return false;
        }
        if (c > 0 && m[r][c] >= m[r][c-1]) {
            return false;
        }
        return true;
    }

    private static int calcolaDimensione(int[][] m, int r, int c, int altezza, int larghezza, boolean[][] contati) {
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;
        contati[r][c] = true;
        if (m[r][c] == 9) return 0;
        //System.out.println("lavorando su " + m[r][c]);
        if (r > 0 && m[r][c] < m[r-1][c] && !contati[r-1][c]) {
            up = calcolaDimensione(m, r-1, c, altezza, larghezza, contati);
        }
        if (r < altezza-1 && m[r][c] < m[r+1][c] && !contati[r+1][c]) {
            down = calcolaDimensione(m, r+1, c, altezza, larghezza, contati);
        }
        if (c < larghezza-1 && m[r][c] < m[r][c+1] && !contati[r][c+1]) {
            right = calcolaDimensione(m, r, c+1, altezza, larghezza, contati);
        }
        if (c > 0 && m[r][c] < m[r][c-1] && !contati[r][c-1]) {
            left = calcolaDimensione(m, r, c-1, altezza, larghezza, contati);
        }
        return 1 + up + right + down + left;
    }
}
