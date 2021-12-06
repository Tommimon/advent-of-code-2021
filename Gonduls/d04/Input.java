package Gonduls.d04;

import java.io.*;
import java.util.*;

public class Input {
    private final Integer[] numbers;
    private final List<BingoBoard> boards;

    public Input(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        numbers = Arrays.stream(reader.readLine().split(","))
                .map(Integer :: parseInt)
                .toArray(Integer[] :: new);

        boards = new ArrayList<>();

        // I check for eof while reading the empty line separating boards
        while(reader.readLine() != null){
            int[][] temporary = new int[5][5];
            for(int i = 0; i< 5; i++){
                Integer[] array = Arrays.stream(
                        reader.readLine()
                                .replace("\s+", " ")
                                .trim()                 // to account for the starting space in a line for some numbers
                                .split("\s+")     // "\s+" is a regular expression for: one 1+ blank spaces
                        )
                            .map(Integer :: parseInt)
                            .toArray(Integer[] :: new);

                // couldn't manage to do it in stream
                for(int j = 0; j< 5; j++)
                    temporary[i][j] = array[j];


            }
            boards.add(new BingoBoard(temporary));
        }
        reader.close();
    }

    public Integer[] getNumbers(){
        return(numbers.clone());
    }

    public List<BingoBoard> getBoards(){
        List<BingoBoard> copy = new ArrayList<>();
        for(BingoBoard board : boards){
            copy.add(board.deepCopy());
        }
        return(copy);
    }

}
