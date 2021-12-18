package tommimon.d18;

public class Number {
    public Number a;  // used if this is a pair
    public Number b;
    public int n;  // used if this is a normal number
    public boolean isPair;
    public Number parent;

    public Number (Number a, Number b, Number parent) {
        this.a = a;
        this.b = b;
        this.isPair = true;
        this.parent = parent;
    }

    public Number (int n, Number parent) {
        this.n = n;
        this.isPair = false;
        this.parent = parent;
    }

    public Number plus(Number b) {
        Number num = new Number(this, b, null);
        this.parent = num;
        b.parent = num;
        String prev = num.toString();
        while (num.reduce()) {
            prev = num.toString();
        }
        return num;
    }

    void explodeRight(int delta, Number comingFrom) {
        if(isPair) {
            if(a == comingFrom)
                b.explodeRight(delta, this);
            else {
                if (b == comingFrom) {
                    if (parent != null)
                        parent.explodeRight(delta, this);
                }
                else
                    a.explodeRight(delta, this);
            }
        }
        else
            n += delta;
    }

    void explodeLeft(int delta, Number comingFrom) {
        if(isPair) {
            if(b == comingFrom)
                a.explodeLeft(delta, this);
            else {
                if (a == comingFrom) {
                    if (parent != null)
                        parent.explodeLeft(delta, this);
                }
                else
                    b.explodeLeft(delta, this);
            }
        }
        else
            n += delta;
    }

    void explode() {
        parent.explodeRight(b.n, this);
        parent.explodeLeft(a.n, this);
        this.isPair = false;
        this.a = null;
        this.b = null;
        this.n = 0;
    }

    void split() {
        this.isPair = true;
        this.a = new Number(n / 2, this);
        this.b = new Number(n - a.n, this);
        this.n = 0;
    }

    boolean findExplode(int depth) {
        if (isPair) {
            if(!a.isPair && !b.isPair && depth >= 4) {
                this.explode();
                return true;
            }
            if (a.findExplode(depth+1))
                return  true;
            else
                return b.findExplode(depth+1);
        }
        else
            return false;
    }

    boolean findSplit() {
        if (isPair) {
            if (a.findSplit())
                return  true;
            else
                return b.findSplit();
        }
        else
            if(n >= 10) {
                this.split();
                return true;
            }
            else
                return false;
    }

    // returns true if something is exploded or split
    boolean reduce() {
        if(findExplode(0))
            return true;
        else
            return findSplit();
    }

    public long magnitude() {
        if (isPair)
            return a.magnitude()*3 + b.magnitude()*2;
        else
            return n;
    }

    @Override
    public String toString() {
        if (isPair)
            return "[" + a.toString() + "," + b.toString() + "]";
        else
            return Integer.toString(n);
    }
}
