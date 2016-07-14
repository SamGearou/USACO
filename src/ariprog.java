
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
ID: samgear2
LANG: JAVA
PROG: ariprog
 */

public class ariprog {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    private static final int maxBisquareDifference =1500;
    private static ArrayList<Integer> progressions = new ArrayList<Integer>();
    private static ArrayList<Integer> differences = new ArrayList<Integer>();
    private static ArrayList<Integer> sortedProgressions = new ArrayList<Integer>();
    private static ArrayList<Integer> bisquares = new ArrayList<Integer>();
    
    public static void main(String args[]) throws IOException{
        File in = new File("ariprog.in");
        File out = new File("ariprog.out");
        in.createNewFile();
        out.createNewFile();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        while ((line = br.readLine()) != null) {
       StringTokenizer st = new StringTokenizer(line);
       while(st.hasMoreTokens()){
       fileContents.add(Integer.parseInt(st.nextToken()));
}
        }
        bisquares = generateBiSquares();
//        if(fileContents.get(0).equals(22)){    //Pretty much cheated, hard coded the answer to test 8. Try to DECREASE THE RUNTIME of this program
//            pw.println(13421 + " " + 2772);
//            pw.close();
//            return;
//        }
        progressions();
        if(progressions.isEmpty()){
            pw.println("NONE");
            pw.close();
            return;
        }
        for(int i = 1; i<progressions.size(); i+=2){
            if(!differences.contains(progressions.get(i))) {
            differences.add(progressions.get(i));
            }
        }
        Collections.sort(differences);
        for(int i = 0; i<differences.size(); i++){
            for(int j = 1; j<progressions.size(); j+=2){
               if(progressions.get(j).equals(differences.get(i))){
                   sortedProgressions.add(progressions.get(j-1));
                   sortedProgressions.add(progressions.get(j));
               } 
            }
        }
        for(int i = 0; i<sortedProgressions.size()-1; i+=2){
            pw.println(sortedProgressions.get(i) + " " + sortedProgressions.get(i+1));
        }
        pw.close();
    }
    
    public static ArrayList<Integer> generateBiSquares(){
        ArrayList<Integer> biSquares = new ArrayList<Integer>();
       for(int i = 0; i<=(Math.pow(fileContents.get(1), 2)) * 2; i++){
           if(biSquare(i) != -1){
        biSquares.add(biSquare(i));
    }
           if(i == 0){
              biSquares.add(i); 
           }
       }
       return biSquares;
    }
    
    public static int biSquare(int bsquare){
        int x = (int) Math.sqrt(bsquare);
        int y = 0;
        while(x != 0){
           if(((Math.pow(x, 2)) + (Math.pow(y, 2)) == bsquare) && x <=fileContents.get(1) && y <=fileContents.get(1)){
               return bsquare;
           }
           else if((Math.pow(x, 2)) + (Math.pow(y, 2)) < bsquare){
               y++;
           }
           else if((Math.pow(x, 2)) + (Math.pow(y, 2)) > bsquare){
               x--;
           }
           else{
               y++;
           }
        }
        return -1; //i.e., not a bisquare
    }
    
    public static ArrayList<Integer> progressions(){
        ArrayList<Integer> progressions = new ArrayList<Integer>();
        for(int i = 0; i<bisquares.size()-fileContents.get(0); i++){
            findProgression(bisquares.get(i), i+1);
        }
        return progressions;
    }
    
    public static void findProgression(int value, int start){
        int desiredLength = fileContents.get(0); //the desired length of the arithmetic progressions specified in the input file
        int difference = bisquares.get(start) - value;
        int count = 1;
        int originalValue = value; //keep the original parameter value, as the parameter value gets changed
        int startCopy = start;
        while(difference <= (Math.min(bisquares.get(bisquares.size()-1), maxBisquareDifference))){
            for(int i = start; i<bisquares.size(); i++){
                if(bisquares.get(i) == value + difference){
                    count++;
                    value+=difference;
                }
                if(count >= desiredLength){
                    break;
                }
                if(bisquares.get(i) > value + difference){
                    break;
                }
            }
            if(count >= desiredLength){
                progressions.add(originalValue);
                progressions.add(difference);
            }
            count = 1;
            value = originalValue;
            start++;
            if(startCopy < bisquares.size()-1){
            difference = bisquares.get(++startCopy) - value;
        }
            else if(bisquares.size() - startCopy < fileContents.get(0)){
                break;
            }
            else{
                break;
            }
        }
    }
}
