package Gonduls.d04;

import java.util.HashMap;
import java.util.stream.IntStream;

public class BingoBoard{
    // I don't actually store the board itself: I did, but then took it out because I didn't use it
    private final boolean[][] wasCalled = new boolean[5][5];
    private final HashMap<Integer, Integer[]> remaining = new HashMap<>(); // numbers yet to be called
    private int lastCalled;

    public BingoBoard(int[][] board){
        for(int y = 0; y< 5; y++){
            for(int x = 0; x<5; x++){
                this.wasCalled[y][x] = false;
                this.remaining.put(board[y][x], new Integer[]{y, x});
            }
        }
    }
    private BingoBoard(final HashMap<Integer, Integer[]> remaining, final boolean[][] wasCalled){
        for(int y = 0; y< 5; y++){
            System.arraycopy(wasCalled[y], 0, this.wasCalled[y], 0, 5);
        }

        for(Integer key : remaining.keySet()){
            this.remaining.put(key, remaining.get(key));
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

    // checks if any row or column only contains 'true' values
    public boolean hasWon(){
        int i;
        for( i = 0; i< 5; i++) {
            int finalY = i;
            // range(0,5) outputs integer stream of numbers from 0 to 4,
            // allMatch verifies condition 'wasCalled[finalY][c] == true' (simplified to 'wasCalled[finalY][c]')
            if(IntStream.range(0,5).allMatch(c -> wasCalled[finalY][c]))
                return(true);
            if(IntStream.range(0,5).allMatch(c -> wasCalled[c][finalY]))
                return(true);
        }
        return false;
    }

    public int score(){
        return (remaining.keySet().stream().reduce(0, Integer::sum) * lastCalled);
    }

    // would have implemented clone but no real benefits come from doing that
    public BingoBoard deepCopy(){
        return new BingoBoard(this.remaining, this.wasCalled);
    }

}
