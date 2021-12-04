package marcomole00.d04;

public class Box {
     int number;
     boolean marked;

     public Box()
    {
        this.number = 0;
        this.marked = false;
    }
     public Box (int numberPar)
    {
        this.number = numberPar;
        this.marked = false;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public int getNumber() {
        return number;
    }
    public boolean getMarked() {
        return marked;
    }

}
