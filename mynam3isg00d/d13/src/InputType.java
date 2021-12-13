package mynam3isg00d.d13.src;

import java.util.ArrayList;
import java.util.Objects;

public class InputType {
    ArrayList<Integer[]> points;
    ArrayList<Integer> folds;

    InputType() {
        points = new ArrayList<>();
        folds = new ArrayList<>();
    }

    public void add(int... a) {
        Integer[] toAdd = new Integer[a.length];
        for(int i=0; i<toAdd.length; i++) toAdd[i] = a[i];
        points.add(toAdd);
    }

    public void addFold(int f) {
        folds.add(f);
    }

    public void printInfo() {
        System.out.println("points: ");
        for(Integer[] ia : points) {
            System.out.println(ia[0] + "|" + ia[1]);
        }
        System.out.println("\nfolds: ");
        for(Integer f : folds) {
            System.out.print(f);
            if (f < 0) System.out.print(" (" + Math.abs(f) + " along y)\n");
            else System.out.print(" (" + Math.abs(f) + " along x)\n");
        }
    }

    public void fold(int f) {
        ArrayList<Integer[]> ret = new ArrayList<>();
        if (f > 0) {
            //fold along x
            for(Integer[] p : points) {
                if (p[0] > Math.abs(f)) {
                    Integer[] toAdd = new Integer[]{2 * Math.abs(f) - p[0], p[1]};
                    if (!isIn(ret, toAdd)) ret.add(toAdd);
                } else {
                    if (!isIn(ret, p)) ret.add(p);
                }
            }
        } else {
            //fold along y
            for(Integer[] p : points) {
                if (p[1] > Math.abs(f)) {
                    Integer[] toAdd = new Integer[]{p[0], 2 * Math.abs(f) - p[1]};
                    if (!isIn(ret, toAdd)) ret.add(toAdd);
                }
                else if (!isIn(ret, p)) ret.add(p);
            }
        }

        points = new ArrayList<>(ret);
    }

    public boolean isIn(ArrayList<Integer[]> arr, Integer[] p0) {
        for(Integer[] p : arr) {
            if(Objects.equals(p[0], p0[0]) && Objects.equals(p[1], p0[1])) return true;
        }
        return false;
    }
}

