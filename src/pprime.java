
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
PROG: pprime
 */

public class pprime {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    static ArrayList<Integer> pals = new ArrayList<Integer>();
    public static void main(String[] args) throws IOException{
        File in = new File("pprime.in");
        File out = new File("pprime.out");
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
        //for 1 digit palindromes
        for(int a = 1; a<=9; a+=2){
            int palindrome = a;
            pals.add(palindrome);
        }
        
        //for 2 digit palindromes
        for(int a = 1; a<=9; a+=2){
            int palindrome = 10 * a + a;
            pals.add(palindrome);
        }
        //for 3 digit palindromes
        for(int a = 1; a<=9; a+=2){
            for(int b = 0; b<=9; b++){
                int palindrome = 100 * a + 10 * b + a;
              pals.add(palindrome);
            }
        }
        
        //for 4 digit palindromes
        for(int a = 1; a<=9; a+=2){
            for(int b = 0; b<=9; b++){
                int palindrome = 1000 * a + 100 * b + 10 * b + a;
               pals.add(palindrome);
            }
        }
        
        //for 5 digit palindromes
        for(int a = 1; a<=9; a+=2){
            for(int b = 0; b<=9; b++){
             for(int c = 0; c<=9; c++){
                 int palindrome = 10000*a + 1000*b +100*c + 10*b + a;
                pals.add(palindrome);
             }   
            }
        }
        
        //for 6 digit palindromes
        for(int a = 1; a<=9; a+=2){
            for(int b = 0; b<=9; b++){
                for(int c = 0; c<=9; c++){
                    int palindrome = 100000 * a + 10000 * b + 1000 * c + 100 * c + 10 * b + a;
                  pals.add(palindrome);
                }
            }
        }
      
        //for 7 digit palindromes
        for(int a = 1; a<=9; a+=2){
            for(int b = 0; b<=9; b++){
                for(int c = 0; c<=9; c++){
                    for(int d = 0; d<=9; d++){
                       int palindrome = 1000000 * a + 100000 * b + 10000 * c + 1000 * d + 100 * c + 10 * b + a;
                 pals.add(palindrome);
                    }
                }
}
        }
        
        //for 8 digit palindromes
        for(int a = 1; a<=9; a+=2){
            for(int b = 0; b<=9; b++){
                for(int c = 0; c<=9; c++){
                    for(int d = 0; d<=9; d++){
                       int palindrome = 10000000 * a + 1000000 * b + 100000 * c + 10000 * d + 1000 * d + 100 * c + 10 * b + a; 
                  pals.add(palindrome);
                    }
                }
            }
        }
        
        //for 9 digit palindromes
        for(int a = 1; a<=9; a+=2){
            for(int b = 0; b<=9; b++){
                for(int c = 0; c<=9; c++){
                    for(int d = 0; d<=9; d++){
                        for(int e = 0; e<=9; e++){
                            int palindrome = 100000000 * a + 10000000 * b + 1000000 * c + 100000 * d + 10000 * e + 1000 * d + 100 * c + 10 * b + a;
                          pals.add(palindrome);
                        }
                    }
                }
            }
                    }
        for(int i = 0; i<pals.size(); i++){
            if(pals.get(i) >= fileContents.get(0) && pals.get(i) <= fileContents.get(1)){
                if(isPrime(pals.get(i))){
                    pw.println(pals.get(i));
                }
            }
            else if(pals.get(i) > fileContents.get(1)){
                break;
            }
        }
        pw.close();
    }
    
    public static boolean isAPalindrome(int x){
        StringBuffer str = new StringBuffer(String.valueOf(x));
        String one = str.toString();
        str.reverse();
        String two = str.toString();
        return (one.equals(two));
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
