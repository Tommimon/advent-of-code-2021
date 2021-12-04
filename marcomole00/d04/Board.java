package marcomole00.d04;

public class Board {
    Box[][] cartella = new Box[5][5];
    boolean alreadyWon;

    public Board(int[][] matrix) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cartella[i][j] = new Box(matrix[i][j]);
            }
        }
        alreadyWon = false;
    }

    // 0 if no bingo, 1 if bingo
    public boolean checkNumber(int number) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cartella[i][j].getNumber() == number) {
                    cartella[i][j].setMarked(true);
                    return checkBingo(i, j);
                }
            }
        }
        return false;
    }

    // parame
    public boolean checkBingo(int i, int j) {

        boolean tempx = true;
        boolean tempy = true;

        for (int k = 0; k < 5; k++) {
            tempx = tempx && cartella[k][j].getMarked();
            tempy = tempy && cartella[i][k].getMarked();
        }
        boolean temp = tempx || tempy;
        if(temp) {
    //        System.out.println("");
        }

        return temp;

    }


    public void print() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(cartella[i][j].getNumber() + " ");
            }
            System.out.println();
        }
        System.out.println(" ");
    }


    public int metric()
    {
        int metric =0;
        for(int i=0; i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
                if (!cartella[i][j].getMarked()){metric += cartella[i][j].getNumber();}
            }
        }
        return metric;
    }
}



