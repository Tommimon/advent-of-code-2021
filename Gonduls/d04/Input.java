package Gonduls.d04;

import java.io.*;
import java.util.*;

public class Input {
    private BufferedReader reader;
    private Integer[] numbers;
    private List<BingoBoard> boards;

    public Input(String filePath) throws IOException {
        reader = new BufferedReader(new FileReader(filePath));

        numbers = Arrays.stream(reader.readLine().split(","))
                .map(c -> Integer.parseInt(c))
                .toArray(Integer[] :: new);
        boards = new ArrayList<>();

        while(true){
            reader.readLine();

            try{
                int[][] temporary = new int[5][5];
                for(int i = 0; i< 5; i++){
                    String string = reader.readLine().replace("\s+", " ");
                    if(string.startsWith("\s")){
                        string = string.substring(1);
                    }
                    Integer[] array = Arrays.stream(string.split("\s+"))
                            .map(c -> Integer.parseInt(c))
                            .toArray(Integer[] :: new);
                    //System.arraycopy(array, 0, temporary[i], 0, 5);
                    for(int j = 0; j< 5; j++){
                        //System.out.println(array[j]);
                        temporary[i][j] = array[j];
                    }

                }
                boards.add(new BingoBoard(temporary));
            } catch(Exception e){
                break;
            }
        }
        reader.close();
    }

    public Integer[] getNumbers(){
        return(numbers.clone());
    }

    public List<BingoBoard> getBoards(){
        List<BingoBoard> copy = new ArrayList<>();
        for(BingoBoard board : boards){
            copy.add(board.clone());
        }
        return(boards);
    }

}
