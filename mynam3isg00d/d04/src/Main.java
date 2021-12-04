/*
First Bingo in the world that doesn't count the diagonal
Nevertheless, code with which I beat both two of those punks
 */

package mynam3isg00d.d04.src;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        String[] inp = r.parseInput("input.txt");
        ArrayList<Integer> intdrawn = new ArrayList<>();
        ArrayList<Integer> winlist = new ArrayList<>();

        Board[] boards = new Board[inp.length - 1];
        String drawn = inp[0];
        int len = inp.length;
        for(int i=1; i<inp.length; i++) {
            inp[i] = inp[i].replace("\n", " ").replace("  ", " ").strip();
            boards[i-1] = new Board(inp[i].split(" "));
        }

        for(String s : drawn.split(",")) {
            intdrawn.add(Integer.parseInt(s));
        }

        for(Integer i : intdrawn) {
            for(int j=0; j<boards.length; j++) {
                boards[j].check(i);
                int k = boards[j].checkBingo(i);
                if (k != -1) {
                    if(!winlist.contains(j)) {
                        System.out.println("Board " + j + " won on num " + i + " | " + k);
                        winlist.add(j);
                    }
                }
            }
        }

        for(Integer i : winlist) System.out.println(i);
    }
}
