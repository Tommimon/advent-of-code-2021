package Gonduls.d04;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class Results {
    public static void main(String[] args) {
        Input input;
        Integer[] numbers;
        List<BingoBoard> bingoBoards;

        try {
            input = new Input("Gonduls/d04/input.txt");
        }
        catch (IOException e){
            System.out.println("Something useful about the file not opening, @Marco\n");
            e.printStackTrace();
            return;
        }

        numbers = input.getNumbers();
        bingoBoards = input.getBoards();

        int result_1 = 0, result_2 = 0;
        HashSet<BingoBoard> previous = new HashSet<>(); // contains all boards that have won

        for(int num: numbers){
            for(BingoBoard board : bingoBoards){
                board.called(num);
                if(!previous.contains(board) && board.hasWon()){
                    previous.add(board);

                    if(result_1 == 0)
                        result_1 = board.score();

                    // If it was the last board to win and be put in 'previous':
                    if(previous.size() == bingoBoards.size()) {
                        result_2 = board.score();
                        break;
                    }
                }
            }
        }

        System.out.println("Part1 result is: " + result_1);
        System.out.println("Part2 result is: " + result_2);
    }
}
