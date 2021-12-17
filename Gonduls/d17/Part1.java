package Gonduls.d17;

public class Part1 {
    public static final int YMIN = -109;
    public static void main(String[] args) {
        int velY = - 1 - YMIN;
        System.out.println("Result part 1 = " +  velY * (velY +1) / 2);
    }
}
