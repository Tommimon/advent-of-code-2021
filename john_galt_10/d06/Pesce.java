package john_galt_10.d06;

public class Pesce {
    private int timer;

    public Pesce(int inizio) {
        timer = inizio;
    }

    public boolean update() {
        if (timer > 0) {
            timer--;
            return false;
        }
        else {
            timer = 6;
            return true;
        }
    }
}
