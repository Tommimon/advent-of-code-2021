package riccardo_negri.d18;

public class Pair {
    private Object first;  // it can be an Integer or a Pair
    private Object second; // it can be an Integer or a Pair

    public Pair () {
        first = null;
        second = null;
    }

    public Pair (Object a, Object b) {
        first = a;
        second = b;
    }

    public Object getFirst () {
        return first;
    }

    public Object getSecond () {
        return second;
    }

    public void setFirst (Object first) {
        this.first = first;
    }

    public void setSecond (Object second) {
        this.second = second;
    }

}
