package marcomole00.d05;

public class Point {
    int pass;

    public Point()
    {
        pass =0;
    }

    public void incrementPass(){
        pass+=1;
    }

    public int getPass() {
        return pass;
    }
    public boolean moreThanOnePass()
    {
        return pass > 1;
    }

}
