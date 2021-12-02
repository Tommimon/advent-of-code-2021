    package Gonduls.d02;

public class Point2d {
    public int x, y;

    public Point2d(){
        this(0, 0);
    }

    public Point2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int manhdist(){
        return x+y;
    }
}