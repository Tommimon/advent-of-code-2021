    package Gonduls.d02;

    public class Point2d {
    public final int x, y;

    public Point2d(){
        this(0, 0);
    }

    public Point2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int valueInMatrix(int[][] matrix){
        return  matrix[y][x];
    }

    public Integer valueInMatrix(Integer[][] matrix){
        return  matrix[y][x];
    }

    public Object valueInMatrix(Object[][] matrix){
        return  matrix[y][x];
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
        return result;
    }
}