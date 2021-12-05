package Gonduls.d02;

public class Point3d extends Point2d{
    public final int z;

    public Point3d(){
        this(0, 0, 0);
    }
    public Point3d(int x, int y, int z){
        super(x, y);
        this.z = z;
    }

    @Override
    public Point3d clone(){
        return new Point3d(x, y, z);
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y +", " + z +")";
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null || obj.getClass() != this.getClass())
            return false;
        final Point3d point = (Point3d) obj;
        return point.x == this.x && point.y == this.y && point.z == this.z;
    }

    public int hashCode(){
        int result = "AdventOfCode2021".hashCode();
        result = 37 * result + x;
        result = 37 * result + y;
        result = 37 * result + z;
        //System.out.println("Hashcode = " + result);
        return result;
    }
}
