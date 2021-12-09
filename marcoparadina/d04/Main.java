import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.Scanner;

public class Main {
    private static ArrayList<Integer> randomNumbers= new ArrayList<>();
    private static ArrayList<Integer> winners= new ArrayList<>();


    public static void  main(String args[]){
        try{
            File inputFile = new File("input4.txt");
            Scanner input = new Scanner(inputFile);
            String str=input.next();
            List<String> temp = Arrays.asList(str.split(","));

            for(int i=0; i<temp.size(); i++){
                randomNumbers.add(Integer.parseInt(temp.get(i)));
            }

            /*
            for(int i=0; i<randomNumbers.size();i++){
                System.out.println(randomNumbers.get(i));
            }
             */


            while (input.hasNext()) {
                String emptyLine =input.nextLine();

                /*
                if((emptyLine.isEmpty())){
                    System.out.println("obama");
                }
                 */

                boolean winner=false;
                int rows = 5;
                int columns = 5;
                Integer[][] numbersMatrix = new Integer[rows][columns];
                Integer[][] marksMatrix = new Integer[rows][columns];
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < columns; c++) {
                        numbersMatrix[r][c]=input.nextInt();
                    }
                }
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < columns; c++) {
                        marksMatrix[r][c]=0;
                    }
                }

                for(int i=0; i<randomNumbers.size(); i++){
                    for (int r = 0; r < rows; r++) {
                        for (int c = 0; c < columns; c++) {
                            if(numbersMatrix[r][c]==randomNumbers.get(i)){
                                marksMatrix[r][c]=1;
                            }
                        }
                    }
                    //controllo se ho vinto per righe:

                    winner=true;
                    for (int r = 0; r < rows; r++) {
                        for (int c = 0; c < columns; c++) {
                            if(marksMatrix[r][c]==0){
                                winner=false;
                                break;
                            }
                        }
                        if(winner==true){
                            break;
                        }
                    }
                    if(winner==false){
                        //controllo se ho vinto per le colonne:
                        winner=true;
                        for (int c = 0; c < columns; c++) {
                            for (int r = 0; r < rows; r++) {
                                if(marksMatrix[r][c]==0){
                                    winner=false;
                                    break;
                                }
                            }
                            if(winner==true){
                                break;
                            }
                        }
                    }
                    if(winner==true) {
                        winners.add(i);
                        break;
                    }
                }
                if(winner==false){
                    winners.add(5000);
                }
            }

            /*
            int biggest=0;
            for(int i=0; i<winners.size(); i++){
                if((winners.get(i)>biggest) && winners.get(i)!=5000){
                    biggest=winners.get(i);
                }
            }
            int biggestMatrix=winners.indexOf(biggest);
            System.out.println("biggest:" + biggestMatrix);
            File inputFile2 = new File("input4.txt");
            Scanner input2 = new Scanner(inputFile2);
            int matrixCounter=0;
            while(matrixCounter!=biggestMatrix+1){
                String emptyChecker=input2.nextLine();
                if(emptyChecker.isEmpty()){
                    matrixCounter++;
                }
            }
            //elaboration of the winning matrix:
            int rows = 5;
            int columns = 5;
            Integer[][] numbersMatrix = new Integer[rows][columns];
            Integer[][] marksMatrix = new Integer[rows][columns];
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    numbersMatrix[r][c]=input2.nextInt();
                }
            }
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    marksMatrix[r][c]=0;
                }
            }
            for(int i=0; i<=biggest; i++){
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < columns; c++) {
                        if(numbersMatrix[r][c]==randomNumbers.get(i)){
                            marksMatrix[r][c]=1;
                        }
                    }
                }
            }
            int sum=0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    if(marksMatrix[r][c]==0){
                        sum+=numbersMatrix[r][c];
                    }
                }
            }
            int result=sum*(randomNumbers.get(biggest));
            System.out.println(result);

             */


            int smallest=5000;
            //winners contains how much time each board took to win
            for(int i=0; i<winners.size(); i++){
                if(winners.get(i)<smallest){
                    smallest=winners.get(i);
                }
            }
            int smallestMatrix= winners.indexOf(smallest);
            System.out.println("fastest:" + smallestMatrix);
            //the matrix to elaborate is the smallest-esima
            File inputFile1 = new File("input4.txt");
            Scanner input1 = new Scanner(inputFile1);
            int matrixCounter=0;
            while(matrixCounter!=smallestMatrix+1){
                String emptyChecker=input1.nextLine();
                if(emptyChecker.isEmpty()){
                    matrixCounter++;
                }
            }
            //elaboration of the winning matrix:
            int rows = 5;
            int columns = 5;
            Integer[][] numbersMatrix = new Integer[rows][columns];
            Integer[][] marksMatrix = new Integer[rows][columns];
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    numbersMatrix[r][c]=input1.nextInt();
                }
            }
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    marksMatrix[r][c]=0;
                }
            }
        for(int i=0; i<=smallest; i++){
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < columns; c++) {
                        if(numbersMatrix[r][c]==randomNumbers.get(i)){
                            marksMatrix[r][c]=1;
                        }
                    }
                }
            }
            int sum=0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    if(marksMatrix[r][c]==0){
                        sum+=numbersMatrix[r][c];
                    }
                }
            }
            int result=sum*(randomNumbers.get(smallest));
            System.out.println(result);


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
