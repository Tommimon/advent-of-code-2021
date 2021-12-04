//
// Day 4 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 6592
// Second part solution: 31755
//

package riccardo_negri.d04;

import java.io.*;
import java.util.*;

public class d04 {
    public static void main(String[] args){
        File f = new File("riccardo_negri/d04/input.txt");
        Scanner reader = null;
        List<Board> boards = new ArrayList<>();
        List<Board> boardsAlreadyWon = new ArrayList<>();
        String[] numbersDrawn = new String[0];
        List<Integer> results = new ArrayList<>();

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(true) {
            assert reader != null;
            if (!reader.hasNextLine()) break;
            numbersDrawn = reader.nextLine().split(",");
            while(true) {
                if (!reader.hasNextLine()) break;
                reader.nextLine();
                boards.add(new Board(reader, 5, 5));
                if (!reader.hasNextLine()) break;

            }
        }

        for (String numberDrawn : numbersDrawn) {
            for (Board board : boards) {
                board.play(Integer.parseInt(numberDrawn));
            }
            for (Board board : boards) {
                if(board.isWinner()) {
                    results.add(board.unmarkedNumbersTotal() * Integer.parseInt(numberDrawn));
                    boardsAlreadyWon.add(board);
                }
            }
            for (Board board : boardsAlreadyWon) {
                boards.remove(board);
            }
        }

        System.out.println(results.get(0));
        System.out.println(results.get(results.size()-1));
    }
}
