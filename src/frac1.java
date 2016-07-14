
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
PROG: frac1
 */

public class frac1 {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    static ArrayList<String> fractions = new ArrayList<String>(1000);
    static ArrayList<Float> numbers = new ArrayList<Float>(1000);
    static ArrayList<String> sortedNumbers = new ArrayList<String>(1000);
    public static void main(String[] args) throws IOException{
        File in = new File("frac1.in");
        File out = new File("frac1.out");
        in.createNewFile();
        out.createNewFile();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        while ((line = br.readLine()) != null) {
        StringTokenizer st = new StringTokenizer(line);
        fileContents.add(Integer.parseInt(st.nextToken()));
}
        fractions.add("0/1");
        fractions.add("1/1");
        numbers.add(0/1f);
        numbers.add(1/1f);
        generateFractions();
        for(String x: fractions){
            pw.println(x);
        }
        pw.close();
    }
    
    public static void generateFractions(){
        int x = 2;
        int y = 1;
        int index;
        while(x <= fileContents.get(0)){
            outer: while(y < x){
           //if(!numbers.contains((float) y/x)){
                for(int j = 0; j<numbers.size(); j++){
                    if(numbers.get(j) == (float) y/x){
                        y++;
                        continue outer;
                    }
                    if(numbers.get(j) > (float) y/x){
                        break;
                    }
                }
               if(gcd(y,x) == 1){
                   index = 0;
                   while(index < fractions.size()){
                      if(extractFloat(String.valueOf(y + "/" + x)) < extractFloat(fractions.get(index))){
                         fractions.add(index, String.valueOf(y + "/" + x));
                         numbers.add(index, (float) y/x);
                         break;
                      }
                      index++;
                   }
               }
               else{
                   index = 0;
                   while(index < fractions.size()){
                      if(extractFloat(reduceFraction(y,x)) < extractFloat(fractions.get(index))){
                         fractions.add(index, reduceFraction(y,x));
                         numbers.add((float) ((y / gcd(y,x)) / (x / gcd(y,x))));
                         break;
                      }
                      index++;
                   }
               }
            //}
           y++;
            }
            y = 1;
            x++;
        }
    }
    
    public static String reduceFraction(int numer, int denom){
        int divisor = gcd(numer, denom);
        int newNum = numer / divisor;
        int newDenom = denom / divisor;
        return String.valueOf(newNum + "/" + newDenom);
    }
    
   public static int gcd(int x, int y){
  return (y == 0) ? x : gcd(y, x % y);
}
   
   public static float extractFloat(String str){
    int index = 0;
    while(true){
        if(str.charAt(index) == '/'){
            break;
        }
        index++;
    }
    int numer = Integer.parseInt(str.substring(0, index));
    int denom = Integer.parseInt(str.substring(index+1));
    return (float) numer / denom;
}
}
