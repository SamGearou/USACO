
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
PROG: sprime
 */

public class sprime {
   static ArrayList<Integer> fileContents = new ArrayList<Integer>();
   static int[] attach = {1,3,7,9};
    static ArrayList<Integer> pribs = new ArrayList<Integer>();
    static PrintWriter pw;
    
    public static void main(String[] args) throws IOException{
        File in = new File("sprime.in");
        File out = new File("sprime.out");
        in.createNewFile();
        out.createNewFile();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        while ((line = br.readLine()) != null) {
       StringTokenizer st = new StringTokenizer(line);
       while(st.hasMoreTokens()){
       fileContents.add(Integer.parseInt(st.nextToken()));
}
        }
        generateSuperPrimes(fileContents.get(0));
        pw.close();
    }
    
    public static void generateSuperPrimes(int N){
        pribs.add(2);
        pribs.add(3);
        pribs.add(5);
        pribs.add(7);
        int elementLength = String.valueOf(pribs.get(0)).length();
        int size = pribs.size();
        while(elementLength < N){
            for(int i = 0; i<size; i++){
                for(int j = 0; j<attach.length; j++){
                    String first = String.valueOf(pribs.get(i));
                    String second = String.valueOf(attach[j]);
                    String combined = first + second;
                    pribs.add(Integer.parseInt(combined));
                }
            }
            elementLength++; 
            for(int i = 0; i<size; i++){
                 pribs.remove(0);
            }
            size = pribs.size();
            for(int i = 0; i<size; i++){
                if(!isPrime(pribs.get(i))){
                    pribs.remove(i);
                    i = i-1;
                    size--;
                }
            }
        }
        Collections.sort(pribs);
        for(int i = 0; i<size; i++){
               pw.println(pribs.get(i));
        }
    }
    
    public static boolean isPrime(int x){
        for(int i = 3; i<=Math.sqrt(x); i+=2){
            if(x % i == 0){
                return false;
            }
        }
        return true;
    }
}
