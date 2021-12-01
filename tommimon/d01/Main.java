package tommimon.d01;

public class Main {
    public static void main(String[] args) {
        Reports r = new Reports("tommimon/d01/input");
        Window w1 = new Window(r, 1);
        System.out.println(w1.count());
        Window w2 = new Window(r, 3);
        System.out.println(w2.count());
    }
}
