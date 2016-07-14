
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
PROG: runround
 */

public class runround {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    static boolean[] digits;
    public static void main(String[] args) throws IOException{
        File in = new File("runround.in");
        File out = new File("runround.out");
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
        digits = new boolean[fileContents.get(0).toString().length()];
        for(int i = fileContents.get(0) + 1;; i++){
            if(isAValidNumber(i) && isARunaRound(i)){
                pw.println(i);
                //System.out.println(i);
                pw.close();
                break;
            }
        }
    }
    
    public static boolean isAValidNumber(int num){
        String str = String.valueOf(num);
        int index = 0;
        int next = 1;
        while(index < str.length()){
            if(str.charAt(index) == '0'){
                return false;
        }
            index++;
}
        index = 0;
        while(index < str.length()-1){
            if(str.charAt(index) == str.charAt(next)){
                return false;
            }
            next++;
            if(next == str.length()){
                index++;
                next = index + 1;
            }
        }
        return true;
    }
    
    public static boolean isARunaRound(int num){
        String str = String.valueOf(num);
        int index = 0;
        int times = 0;
        while(times < digits.length){
            index = (index + Integer.parseInt(String.valueOf(str.charAt(index)))) % digits.length;
            digits[index] = true;
            times++;
        }
        if(validDigitList()){
        return true;
        }
        else{
            digits = new boolean[str.length()];
            return false;
        }
    }
    public static boolean validDigitList(){
        for(int i = 0; i<digits.length; i++){
            if(!digits[i]){
                return false;
            }
        }
        return true;
    }
}
