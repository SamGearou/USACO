
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
PROG: subset
 */

public class subset {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    static int maxSum = 0;
    static long answer = 0;
    static long[][] memoize;
    public static void main(String[] args) throws IOException{
       File in = new File("subset.in");
        File out = new File("subset.out");
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
        for(int i = 1; i<=fileContents.get(0); i++){
            maxSum+=i;
        }
        memoize = new long[maxSum/2 + 1][fileContents.get(0) + 1];
        for(int i = 0; i<memoize.length; i++){
            for(int j = 0; j<memoize[0].length; j++){
                memoize[i][j] = -1;
            }
        }
        if(maxSum / 2f - maxSum / 2 != 0){
            pw.println(0);
            pw.close();
        }
        else{
            answer = solve(maxSum/2,fileContents.get(0)) / 2;
            pw.println(answer);
            pw.close();
            System.out.println(answer);
        }
        }
    
    public static long solve(int n, int k){
        if(n < 0 || k < 0){
            return 0;
        }
        if(memoize[n][k] != -1){
            return memoize[n][k];
        }
        if(n == 0 && k == 0){
            return memoize[n][k] = 1;
        }
        return memoize[n][k] = solve(n, k-1) + solve(n-k, k-1);
    }
    }

