package tommimon.d05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

class Matcher extends Thread {
    int index;
    Vector<Integer[]> points;
    Vector<Line> lines;
    final Object mutex = new Object();

    public Matcher(int index, Vector<Line> lines, Vector<Integer[]> points) {
        this.index = index;
        this.lines = lines;
        this.points = points;
    }

    void addPoint(Integer[] newPoint) {
        for( Integer[] p : getPoints()) {
            if(Arrays.equals(p, newPoint))
                return;
        }
        doAdd(newPoint);
    }

    synchronized Vector<Integer[]> getPoints() {
        synchronized (mutex) {
            return new Vector<>(points);
        }
    }

    synchronized void doAdd(Integer[] newPoint) {
        synchronized (mutex) {
            points.add(newPoint);
        }
    }

    @Override
    public void run() {
        findMatches();
    }

    void findMatches() {
        System.out.println(index);
        for(int j = index+1; j < lines.size(); j++) {
            for(Integer[] p1 : lines.get(index).points) {
                for(Integer[] p2 : lines.get(j).points) {
                    if(Arrays.equals(p1, p2)) {
                        addPoint(p1);
                    }
                }
            }
        }
    }
}

public class Problem {
    Vector<Integer[]> points = new Vector<>();

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

        Vector<Thread> threads = new Vector<>();
        for (int i = 0; i < lines.size(); i++) {
            Thread t = new Matcher(i, lines, points);
            threads.add(t);
            t.start();
        }
        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print("res: ");
        System.out.println(points.size());
    }
}
