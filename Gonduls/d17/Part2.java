package Gonduls.d17;

public class Part2 {
    public static final String input = "target area: x=179..201, y=-109..-63";
    public static final int XMIN = 179, XMAX = 201, YMIN = -109, YMAX = -63;

    public static void main(String[] args) {
        // direct hits: it only takes one step to reach target
        int result = (XMAX - XMIN + 1) * (YMAX - YMIN + 1);

        // upper bound for x velocity for bruteforce
        int MX = 0;
        for(int x = 0; x <= (XMAX )/2 + 1; x++)
            if(hitsX(x))
                MX = x;

        // upper bound for y velocity for bruteforce
        int MY = 0;
        for(int y = 0; y < (-YMIN); y++)
            if(hitsY(y))
                MY = y;

        // lower bound for y velocity for bruteforce
        int mY = 0;
        for(int y = 0; y > YMIN / 2 -1; y--)
            if(hitsY(y))
                mY = y;

        for(int x = 0; x <= MX; x++)
            for(int y = mY; y <= MY; y++)
                if(hitsTarget(x, y))
                    result++;

        System.out.println("Result part 2 = " + result);
    }

    public static boolean hitsY(int velY){
        int y = 0;
        while(y > YMAX){
            y += velY;
            velY --;
        }
        return(y >= YMIN);
    }

    public static boolean hitsX(int velX){
        if(velX < 0) return false;
        int x = 0;
        while(x < XMIN){
            if(velX == 0)
                return false;
            x += velX;
            velX --;
        }
        return(x <= XMAX);
    }

    public static boolean hitsTarget(int velX, int velY){
        int x = 0, y= 0;
        while(x <= XMAX && y >= YMIN){
            if(x >= XMIN && y <= YMAX) return true;

            x += velX;
            y += velY;
            velX -= velX > 0 ? 1 : 0;
            velY --;
        }
        return false;
    }
}
