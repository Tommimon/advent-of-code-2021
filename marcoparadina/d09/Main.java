package marcoparadina.d09;

import java.io.File;
import java.util.*;

public class Main {
    public static void  main(String args[]){
        try{
            File inputFile = new File("input9.txt");
            Scanner input = new Scanner(inputFile);
            int rows=100;
            int columns=100;
            Integer[][] matrix = new Integer[rows][columns];
            for(int r=0; r<rows; r++){
                String str=input.next();
                for(int c=0; c<columns; c++){
                    matrix[r][c]=Character.getNumericValue(str.charAt(c));
                }
            }
            int result=0;
            for(int r=0; r<rows; r++){
                for(int c=0; c<columns; c++){
                    if(r==0&&c==0){
                        if(matrix[r][c]<matrix[r+1][c] && matrix[r][c]<matrix[r][c+1]){
                            result+=matrix[r][c]+1;
                        }
                    }
                    else if (r==99&&c==0){
                        if(matrix[r][c]<matrix[r-1][c] && matrix[r][c]<matrix[r][c+1]){
                            result+=matrix[r][c]+1;
                        }
                    }
                    else if(r==0&&c==99){
                        if(matrix[r][c]<matrix[r][c-1] && matrix[r][c]<matrix[r+1][c]){
                            result+=matrix[r][c]+1;
                        }
                    }
                    else if (r==99&&c==99){
                        if(matrix[r][c]<matrix[r-1][c] && matrix[r][c]<matrix[r][c-1]){
                            result+=matrix[r][c]+1;
                        }
                    }
                    else if(r==0&&c!=0&&c!=99){
                        if(matrix[r][c]<matrix[r+1][c] && matrix[r][c]<matrix[r][c+1] && matrix[r][c]<matrix[r][c-1]){
                            result+=matrix[r][c]+1;
                        }
                    }
                    else if (r==99&&c!=0&&c!=99){
                        if(matrix[r][c]<matrix[r-1][c] && matrix[r][c]<matrix[r][c+1] && matrix[r][c]<matrix[r][c-1]){
                            result+=matrix[r][c]+1;
                        }
                    }
                    else if (c==0&&r!=0&&r!=99){
                        if(matrix[r][c]<matrix[r+1][c] && matrix[r][c]<matrix[r-1][c] && matrix[r][c]<matrix[r][c+1]){
                            result+=matrix[r][c]+1;
                        }
                    }
                    else if (c==99&&r!=0&&r!=99){
                        if(matrix[r][c]<matrix[r+1][c] && matrix[r][c]<matrix[r-1][c] && matrix[r][c]<matrix[r][c-1]){
                            result+=matrix[r][c]+1;
                        }
                    }
                    else {
                        if(matrix[r][c]<matrix[r+1][c] && matrix[r][c]<matrix[r][c+1] && matrix[r][c]<matrix[r][c-1] && matrix[r][c]<matrix[r-1][c]){
                            result+=matrix[r][c]+1;
                        }
                    }
                }
            }
            System.out.println(result);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}