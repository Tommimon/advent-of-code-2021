package mynam3isg00d.d04.src;
public class Board {
    Tile[] board;

    Board(String... b) {
        board = new Tile[b.length];
        for(int i=0; i<b.length; i++) {
            board[i] = new Tile(Integer.parseInt(b[i]));
        }
    }

    public void print() {
        for(int i=0; i<25; i++) {
            System.out.println(board[i]);
            if((i+1) % 5 == 0) System.out.println("\n");
        }
    }

    public void check(int num) {
        for(int i=0; i<25; i++) {
            if (board[i].val == num) {
                board[i].mark();
            }
        }
    }

    public int checkBingo(int num) {
        for(int i=0; i<25; i += 5) {
            if (board[i].marked && board[i+1].marked && board[i+2].marked && board[i+3].marked && board[i+4].marked) return findsum(num);
        }
        for(int i=0; i<5; i++) {
            if (board[i].marked && board[i+5].marked && board[i+10].marked && board[i+15].marked && board[i+20].marked) return findsum(num);
        }
        return -1;
    }

    public int findsum(int num) {
        int sum = 0;
        for(int i=0; i<25; i++) {
            if(!board[i].marked) sum += board[i].val;
        }
        return sum * num;
    }
}
