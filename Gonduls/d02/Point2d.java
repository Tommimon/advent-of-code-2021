    package Gonduls.d02;

    import static java.lang.Math.abs;

    public class Point2d {
    public final int x, y;

    public Point2d(){
        this(0, 0);
    }

    public Point2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int manhdist(){
        return abs(x)+abs(y);
    }

    @Override
    public Point2d clone(){
        return new Point2d(x, y);
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y +")";
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null || obj.getClass() != this.getClass())
            return false;
        final Point2d point = (Point2d) obj;
        return point.x == this.x && point.y == this.y;
    }

    @Override
    public int hashCode(){
        int result = "AdventOfCode2021".hashCode();
        result = 3701 * result + x;
        result = 3701 * result + y;
        //System.out.println("Hashcode = " + result);
        return result;
    }
}