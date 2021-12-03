package marcoparadina.d03._2;
import java.util.ArrayList;
import java.util.Scanner;

public class CO2 {
    private int rating;
    Scanner input;
    private ArrayList<String> list = new ArrayList<String>();
    public CO2(Scanner input) {
        this.rating = 0;
        this.input = input;
    }

    private String parse(int position, Scanner input){
        int zeroCounter=0;
        int oneCounter=0;
        while (input.hasNext()){
            this.list.add(input.next());
        }
        while (this.list.size() > 1){
            for (int i = 0; i < this.list.size(); i++) {
                switch (Character.getNumericValue(this.list.get(i).charAt(position))) {
                    case 1:
                        oneCounter++;
                        break;
                    case 0:
                        zeroCounter++;
                        break;
                }
            }
            //selection:
            for(int j=0; j<20; j++) {
                if (zeroCounter > oneCounter) {
                    for (int i = 0; i < this.list.size(); i++) {
                        if (Character.getNumericValue(this.list.get(i).charAt(position)) == 0) {
                            this.list.remove(i);
                        }
                    }
                } else if (oneCounter > zeroCounter) {
                    for (int i = 0; i < this.list.size(); i++) {
                        if (Character.getNumericValue(this.list.get(i).charAt(position)) == 1) {
                            this.list.remove(i);
                        }
                    }
                } else if (oneCounter == zeroCounter) {
                    for (int i = 0; i < this.list.size(); i++) {
                        if (Character.getNumericValue(this.list.get(i).charAt(position)) == 1) {
                            this.list.remove(i);
                        }
                    }
                }
            }
            oneCounter = 0;
            zeroCounter = 0;
            position++;
        }
        return this.list.get(0);
    }

    private int stringToDecimal(String string){
        int decimal=0;
        int exponent=0;
        for(int i=string.length()-1; i>=0; i--){
            decimal+=Math.pow(2,exponent)*Character.getNumericValue(string.charAt(i));
            exponent++;
        }
        return decimal;
    }

    public int getRating(){
        return this.stringToDecimal(this.parse(0, this.input));
    }

}
