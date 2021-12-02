//
// Day 2 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 1947824
// Second part solution: 1813062561
//

package riccardo_negri.d02;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class d02 {
    public static void main(String[] args){
        File f = new File("riccardo_negri/d02/input.txt");
        Scanner reader = null;
        int x = 0, z = 0, m = 0;
        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(true) {
            assert reader != null;
            if (!reader.hasNextLine()) break;
            String[] temp = reader.nextLine().split(" ");

            if (Objects.equals(temp[0].strip(), "forward")){
                x += Integer.parseInt(temp[1]);
                z += Integer.parseInt(temp[1])*m;
            }
            else if (Objects.equals(temp[0].strip(), "up")) {
                m -= Integer.parseInt(temp[1]);
            }
            else {
                m += Integer.parseInt(temp[1]);
            }
        }

        System.out.println(x*m);
        System.out.println(x*z);
    }
}
