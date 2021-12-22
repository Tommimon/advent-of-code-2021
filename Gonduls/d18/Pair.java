package Gonduls.d18;

public class Pair {
    private int value = -1;
    private Pair left, right, father = null; // creates a tree every time

    public Pair(String str){
        // I consider single numbers as "Pair"s: not sure if easier or not
        if(!str.startsWith("[")){
            int i = 0;
            value = 0;
            while(str.charAt(i) != ',' && str.charAt(i) != ']'){
                value = value * 10 + str.charAt(i) - '0';
                i++;
            }

            left = null;
            right = null;
            return;
        }

        left = new Pair(str.substring(1));
        right = new Pair(str.substring(2 + left.toString().length()));
        left.father = this;
        right.father = this;
    }

    public static String combine(String a, String b){
        return "[" + a + "," + b + "]";
    }

    @Override
    public String toString(){
        if(value != -1)
            return ((Integer) value).toString();

        return combine(left.toString(), right.toString());
    }

    public static String reduction(String s){
        return(reduction(new Pair(s)));
    }

    private static String reduction(Pair p){
        Pair result;
        while (true){
            result = explosion(p, 0);
            if(result != null){
                continue;
            }

            // as to why do I need to reassign p to a new pair created by a string:
            // no idea whatsoever, lost much more time than I care to admit
            result = split(p);
            if(result != null) {
                p = new Pair(result.toString());
                continue;
            }
            break;
        }
        return p.toString();
    }

    // It's somewhat easier to parse the string than to search values in a tree, it should be faster (not sure)
    private static Pair split(Pair pair){
        if(pair.value > 9){
            //pair = new Pair((combine(((Integer) (pair.value / 2)).toString(), ((Integer) (pair.value / 2 + pair.value % 2)).toString())));
            pair.left = new Pair(((Integer) (pair.value / 2)) + ",");
            pair.right = new Pair(((Integer) (pair.value / 2 + pair.value % 2))+ "]");
            pair.value = -1;
            return pair;
        }
        if(pair.value != -1)
            return null;

        Pair result = split(pair.left);
        if(result == null)
            result = split(pair.right);
        return(result == null ? null : pair);//*/
        /*String result = pair.toString();
        for(int i = 0; i< result.length() - 1; i++){
            int value, j = i;
            while(result.charAt(j) >= '0' && result.charAt(j) <= '9'){
                j++;
            }
            if(j - i > 1){
                value = Integer.parseInt(result.substring(i,j));
                result = result.substring(0, i) + '[' + value / 2 + ',' + (value/2 + value % 2) + ']' + result.substring(j);
                return new Pair(result);
            }
        }
        return null;//*/
    }

    private static Pair explosion(Pair p, int layer){
        Pair result = null;

        if(p.left.value != -1 && p.right.value != -1){
            if(layer < 4)
                return null;
            //System.out.println(p);
            add(p);
            p.left = null;
            p.right = null;
            p.value = 0;
            return p;
        }


        if(p.left.value == -1)
            result = explosion(p.left, layer +1);
        if(result != null)
            return p;

        if(p.right.value == -1)
            result = explosion(p.right, layer +1);
        if(result != null)
            return p;

        return null;
    }

    private static void add(Pair p){
        Pair current = p;
        while(current.father != null && current.father.left == current)
            current = current.father;

        if(current.father != null){
            current = current.father.left;
            while(current.value == -1)
                current = current.right;

            current.value += p.left.value;
        }

        current = p;
        while(current.father != null && current.father.right == current)
            current = current.father;

        if(current.father != null){
            current = current.father.right;
            while(current.value == -1)
                current = current.left;

            current.value += p.right.value;
        }
    }

    public static int magnitude(String s){
        return magnitude(new Pair(s));
    }

    private static int magnitude(Pair p){
        if(p.value != -1)
            return p.value;

        return 3 * magnitude(p.left) + 2 * magnitude(p.right);
    }
}
