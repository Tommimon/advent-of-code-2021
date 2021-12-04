package Gonduls.d04;

import java.util.HashMap;
import java.util.stream.IntStream;

public class BingoBoard {
    private int[][] board = new int[5][5];
    private boolean[][] wasCalled = new boolean[5][5];
    private HashMap<Integer, Integer[]> remaining = new HashMap<>();
    private int lastCalled;

    public BingoBoard(int[] board){
        int x, y;
        for(y = 0; y< 5; y++){
            for(x = 0; x < 5; x++){
                this.board[y][x] = board[y * 5 + x];
                this.wasCalled[y][x] = false;
                this.remaining.put(this.board[y][x], new Integer[]{y, x});
            }
        }
    }

    public BingoBoard(int[][] board){
        for(int y = 0; y< 5; y++){
            for(int x = 0; x<5; x++){
                this.board[y][x] = board[y][x];
                this.wasCalled[y][x] = false;
                this.remaining.put(board[y][x], new Integer[]{y, x});
            }
        }
    }

    public void called(int num){
        int x, y;
        lastCalled = num;
        if(remaining.containsKey(num)){
            y = remaining.get(num)[0];
            x = remaining.get(num)[1];
            remaining.remove(num);
            wasCalled[y][x] = true;
        }
    }

    public boolean hasWon(){
        int i;
        for( i = 0; i< 5; i++) {
            int finalY = i;
            if(IntStream.range(0,5).allMatch(c -> wasCalled[finalY][c] == true))
                return(true);
            if(IntStream.range(0,5).allMatch(c -> wasCalled[c][finalY] == true))
                return(true);
        }
        return false;
    }

    public int score(){
        return (remaining.keySet().stream().reduce(0, (elem, acc) -> acc + elem) * lastCalled);
    }

    @Override
    public BingoBoard clone(){
        BingoBoard copy = new BingoBoard(board);
        for(Integer key : remaining.keySet()){
            copy.remaining.put(key, remaining.get(key));
        }
        for(int y = 0; y< 5; y++){
            for(int x = 0; x < 5; x++){
                copy.wasCalled[y][x] = wasCalled[y][x];
            }
        }
        return copy;
    }

}
