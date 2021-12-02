package Gonduls.d02;

public class Point3d extends Point2d{
    public int z;

    public Point3d(){
        this(0, 0, 0);
    }
    public Point3d(int x, int y, int z){
        super(x, y);
        this.z = z;
    }
}
