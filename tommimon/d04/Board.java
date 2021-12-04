package tommimon.d04;

public class Board {
    static int size = 5;
    Number[][] numbers;
    int lastMarked;
    public boolean win = false;

    public Board(String[] input) {
        numbers =  new Number[size][size];
        int x = 0;
        for(String s : input) {
            String[] parts = s.strip().replace("  ", " ").split(" ");
            int y = 0;
            for (String p : parts) {
                numbers[x][y] = new Number(Integer.parseInt(p));
                y++;
            }
            x++;
        }
    }

    void print() {
        for(Number[] row : numbers) {
            for(Number i : row) {
                System.out.print(i.value);
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    void printMarked() {
        for(Number[] row : numbers) {
            for(Number i : row) {
                System.out.print(i.cancelled);
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public boolean markNum(int n) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(numbers[i][j].value == n) {
                    numbers[i][j].cancelled = true;
                    lastMarked = n;
                    win = checkRow(i) || checkCol(j);
                    return win;
                }
            }
        }
        return false;
    }

    boolean checkRow(int x) {
        for (Number n : numbers[x]) {
            if(!n.cancelled)
                return false;
        }
        return true;
    }

    boolean checkCol(int y) {
        for (Number[] row : numbers) {
            if(!row[y].cancelled)
                return false;
        }
        return true;
    }

    public int score() {
        int sum = 0;
        for (Number[] row : numbers) {
            for (Number n : row) {
                if(!n.cancelled)
                    sum += n.value;
            }
        }
        return sum * lastMarked;
    }
}

