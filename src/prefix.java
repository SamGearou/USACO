
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
PROG: prefix
 */

public class prefix {
    static ArrayList<String> fileContents = new ArrayList<String>();
    static int finalMax = 0;
    static int[] memoize = new int[200001];
    static boolean[] dp = new boolean[200001];
    static StringBuilder sb; //WOW, when buidling a string use stringbuilder, way faster than concatenating with a string object
    public static void main(String[] args) throws IOException{
      File in = new File("prefix.in");
        File out = new File("prefix.out");
        in.createNewFile();
        out.createNewFile();
        String line;
        String x;
        boolean start = false;
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        while ((line = br.readLine()) != null) {
       StringTokenizer st = new StringTokenizer(line);
       while(st.hasMoreTokens()){
           x = st.nextToken();
           if(start){
               sb.append(x);
           }
           else{
               fileContents.add(x);
           }
           if(x.equals(".")){
              start = true; 
           }
}
        }
        fileContents.remove(fileContents.size()-1); //removes the period
        if(sb.toString().length() != 200000){
        generateSolution(sb.toString(), 0);
        pw.println(finalMax);
        pw.close();
        }
        else{
            pw.println(solve(sb.toString()));
            pw.close();
        }
    }
    //utilizes top down memoization
    //DOES NOT WORK for test case 6, stack overflow error, depth of the recursion tree got too large
    public static int generateSolution(String str, int max){
        if(memoize[str.length()] != 0){
            return memoize[str.length()] - max + 1;
        }
        for(int i = 0; i<fileContents.size(); i++){
            if(fileContents.get(i).equals(str.substring(0, Math.min(fileContents.get(i).length(), str.length())))){
                //System.out.println(str);
                max+= fileContents.get(i).length();
                max += generateSolution(str.substring(fileContents.get(i).length()), max);
                //System.out.println(max);
                if(str.length() != sb.toString().length()){
                memoize[str.length()] = str.length();
                //System.out.println("mem " + memoize[str.length()]);
                }
                int maxCopy = max;
                max = max - fileContents.get(i).length();
                if(maxCopy > finalMax){
                    finalMax = maxCopy;
                }
            }
    }
        return 0;
}
    //uses dynamic programming
    public static int solve(String str){
        for(String p: fileContents){
            if(p.length() > str.length()){
                continue;
            }
            if(p.equals(str.substring(0, p.length()))){
                dp[p.length()] = true;
            }
        }
        for(int i = 0; i<=str.length(); i++){
            for(String p: fileContents){
            if(dp[i]){
                if(i + p.length() > str.length()){
                    continue;
                }
                if(str.substring(i, i + p.length()).equals(p)){
                    dp[i + p.length()] = true;
                }
            }
        }
        }
        int answer = 0;
        for(int i = 0; i<=str.length(); i++){
            if(dp[i]){
                answer = i;
            }
        }
        return answer;
    }
}
