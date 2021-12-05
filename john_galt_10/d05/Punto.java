package john_galt_10.d05;

public class Punto {
    private int x;
    private int y;

    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return ("[" + x + ", " + y + "]");
    }

    public static boolean uguale (Punto p1, Punto p2) {
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) return true;
        return false;
    }

    public void muoviDiag(int direz) {
        //1 per x crescente y crescente
        //2 per x decrescente y crescente
        //3 per x decrescente y decrescente
        //4 per x crescente y decrescente
        switch (direz) {
            case 1: x+=1;
                y+=1;
                break;
            case 2: x-=1;
                y+=1;
                break;
            case 3: x-=1;
                y-=1;
                break;
            case 4: x+=1;
                y-=1;
                break;
        }
    }
    public static String allineamento(Punto p1, Punto p2) {
        if (p1.getY() == p2.getY()) return "horizontal";
        if (p1.getX() == p2.getX()) return "vertical";
        return "obliquo";
    }
}
