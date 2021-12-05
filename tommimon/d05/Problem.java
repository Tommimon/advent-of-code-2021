package tommimon.d05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Problem {
    Vector<Integer[]> points = new Vector<>();

    void addPoint(Integer[] newPoint) {
        for( Integer[] p : points) {
            if(Arrays.equals(p, newPoint))
                return;
        }
        points.add(newPoint);
    }

    public void solve(int part) throws FileNotFoundException {
        Vector<String> strings = new Vector<>();
        File myObj = new File("tommimon/d05/input");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            strings.add(data);
        }
        myReader.close();

        Vector<Line> lines = new Vector<>();

        for (String s : strings) {
            Line l = new Line(s);
            if(part == 2 || l.horizontal || l.vertical)
                lines.add(l);
        }

        for (int i = 0; i < lines.size(); i++) {
            System.out.println(i);
            for(int j = i+1; j < lines.size(); j++) {
                for(Integer[] p1 : lines.get(i).points) {
                    for(Integer[] p2 : lines.get(j).points) {
                        if(Arrays.equals(p1, p2)) {
                            addPoint(p1);
                        }
                    }
                }
            }
        }

        System.out.print("res: ");
        System.out.println(points.size());
    }
}

