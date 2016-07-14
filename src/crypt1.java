
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
ID: samgear2
LANG: JAVA
PROG: crypt1
 */

public class crypt1 {
     static ArrayList<Integer> N;
     
   public static void main(String args[]) throws IOException {
        File in = new File("crypt1.in");
        File out = new File("crypt1.out");
        in.createNewFile();
        out.createNewFile();
        String line;
        int count = 0;
        N = new ArrayList<Integer>();
        BufferedReader br = new BufferedReader(new FileReader(in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        while ((line = br.readLine()) != null) {
       StringTokenizer st = new StringTokenizer(line);
       while(st.hasMoreTokens()){
       N.add(Integer.parseInt(st.nextToken()));
}
        }
        N.remove(0);
        for(int a: N){
            for(int b: N){
                for(int c: N){
                    for(int d: N){
                        for(int e: N){
                            int abc = (100 * a) + (10 * b) + c;
                            int de = (10 * d) + e;
                            if(isValid(abc * d) && isValid(abc * e) && isValidAnswer(abc * de)){
                              count++;  
                            }
                        }
                    }
                }
            }
        }
        pw.println(count);
        pw.close();
   }
   
   public static boolean isValid(int num){
       if(String.valueOf(num).length() == 3){
           for(int i = 0; i<String.valueOf(num).length(); i++){
               if(!N.contains(Integer.parseInt(String.valueOf(String.valueOf(num).charAt(i))))){
                   return false;
               }
           }
       }
       else{
           return false;
       }
       return true;
   }
   
   public static boolean isValidAnswer(int num){
   if(String.valueOf(num).length() == 4){
           for(int i = 0; i<String.valueOf(num).length(); i++){
               if(!N.contains(Integer.parseInt(String.valueOf(String.valueOf(num).charAt(i))))){
                   return false;
               }
           }
       }
       else{
           return false;
       }
       return true;
   }
}
