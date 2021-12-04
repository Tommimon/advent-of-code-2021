package tommimon.d04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Script1 {
    public static void main(String[] args) throws FileNotFoundException {
        Vector<String> nums = new Vector<>();
        File myObj = new File("tommimon/d04/input");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            nums.add(data);
        }
        myReader.close();

        String[] firstLine = nums.get(0).split(",");
        Vector<Integer> extracted = new Vector<>();
        for (String s : firstLine) {
            extracted.add(Integer.parseInt(s));
        }

        Vector<Board> boards = new Vector<>();
        String[] array = new String[nums.size()];
        array = nums.toArray(array);
        int boardCount = 0;
        for(int i = 2; i < nums.size(); i+=Board.size+1) {
            boardCount++;
            boards.add(new Board(Arrays.copyOfRange(array, i, i+Board.size)));
        }

        boolean gameFinished = false;
        int res = 0;
        int wins = 0;
        for(int n : extracted) {
            if(!gameFinished) {
                for(Board b: boards) {
                    if(b.markNum(n)) {
                        gameFinished = true;
                        res = b.score();
                        break;
                    }
                }
            }
        }

        System.out.println(res);
    }
}
