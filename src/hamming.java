
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
PROG: hamming
 */

public class hamming {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    static ArrayList<String> codeWords = new ArrayList<String>();
    static int wordCount = 0;
    public static void main(String[] args) throws IOException{
        File in = new File("hamming.in");
        File out = new File("hamming.out");
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
        int current = 0;
        for(int i = 0; i<Math.pow(2, fileContents.get(1)); i++){
            if(i == 0){
            codeWords.add(adjustLength(Integer.toBinaryString(current)));
            wordCount++;
        }
            if(i != current && valid(adjustLength(Integer.toBinaryString(i)))){
                codeWords.add(adjustLength(Integer.toBinaryString(i)));
                wordCount++;
            }
            if(wordCount == fileContents.get(0)){
                break;
            }
            if(i == Math.pow(2, fileContents.get(1)) - 1 && wordCount < fileContents.get(0) && current < Math.pow(2, fileContents.get(1)) - 1){
               wordCount = 0;
               i = -1;
               current++;
            }
        }
        for(int i = 0; i<codeWords.size(); i++){
            if(i == codeWords.size()-1){
                if(i % 10 == 0){
                pw.println(Integer.parseInt(codeWords.get(i), 2));
                break;
            }
                else{
                    pw.print(Integer.parseInt(codeWords.get(i), 2));
                    pw.println();
                    break;
                }
            }
            if((i != 0 && (i+1) % 10 != 0) || i == 0){
            pw.print(Integer.parseInt(codeWords.get(i), 2) + " ");
            }
            else{
                pw.print(Integer.parseInt(codeWords.get(i), 2));
                pw.println();
            }
        }
        pw.close();
    }
    
    public static boolean valid(String str){
        int ham= 0;
        for(int i = 0; i<codeWords.size(); i++){
            for(int j = 0; j<codeWords.get(i).length(); j++){
                if(str.charAt(j) != codeWords.get(i).charAt(j)){
                    ham++;
                }
            }
         if(ham < fileContents.get(2)){
            return false;
        }
         else{
             ham = 0;
         }
        }
        return true;
    }
    
    public static String adjustLength(String str){
        while(str.length() < fileContents.get(1)){
            str = "0" + str;
        }
        return str;
    }
}
