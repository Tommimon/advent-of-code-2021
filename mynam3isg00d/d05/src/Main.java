package mynam3isg00d.d05.src;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        String[] linesString = r.parseInput("input.txt");
        Line[] lines = new Line[linesString.length];

        ArrayList<Punto2d> points = new ArrayList<Punto2d>();
        ArrayList<Punto2d> counted = new ArrayList<Punto2d>(); //this is stupid lol

        ArrayList<Punto2d> points2 = new ArrayList<Punto2d>();
        ArrayList<Punto2d> counted2 = new ArrayList<Punto2d>(); //this is DOUBLE stupid lol

        for(int i=0; i<linesString.length; i++) {
            String ls = linesString[i];
            //100,40 -> 160,40
            int x1 = Integer.parseInt(ls.split(" -> ")[0].split(",")[0]);
            int y1 = Integer.parseInt(ls.split(" -> ")[0].split(",")[1]);
            int x2 = Integer.parseInt(ls.split(" -> ")[1].split(",")[0]);
            int y2 = Integer.parseInt(ls.split(" -> ")[1].split(",")[1]);
            lines[i] = new Line(x1, y1, x2, y2);
        }

        int count = 0;
        int count2 = 0;
        for(Line l : lines) {
            if (l.check()) {
                //Begin to add points
                for(int x = Math.min(l.x1, l.x2); x <= Math.max(l.x1, l.x2); x++) {
                    for(int y = Math.min(l.y1, l.y2); y <= Math.max(l.y1, l.y2); y++) {
                        Punto2d p = new Punto2d(x, y);
                        if (points.contains(p)) {
                            if (!counted.contains(p)) {
                                counted.add(p);
                                count++;
                            }
                        } else {
                            points.add(p);
                        }

                        if (points2.contains(p)) {
                            if (!counted2.contains(p)) {
                                counted2.add(p);
                                count2++;
                            }
                        } else {
                            points2.add(p);
                        }
                    }
                }
            } else {
                int x = l.x1; int y = l.y1;
                int loop = Math.abs((l.x1 - l.x2));
                for(int t=0; t<=loop; t++) {
                    Punto2d p = new Punto2d(x, y);
                    if (points2.contains(p)) {
                        if (!counted2.contains(p)) {
                            counted2.add(p);
                            count2++;
                        }
                    } else {
                        points2.add(p);
                    }
                    if (l.x1 < l.x2) x++; else x--;
                    if (l.y1 < l.y2) y++; else y--;
                }
            }
        }
        System.out.println("Part 1: " + count);
        System.out.println("Part 2: " + count2);
    }
}
