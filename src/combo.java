
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
PROG: combo
 */

public class combo {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    public static void main(String args[]) throws IOException{
        File in = new File("combo.in");
        File out = new File("combo.out");
        in.createNewFile();
        out.createNewFile();
        String line;
        int count = 0;
        BufferedReader br = new BufferedReader(new FileReader(in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        while ((line = br.readLine()) != null) {
       StringTokenizer st = new StringTokenizer(line);
       while(st.hasMoreTokens()){
       fileContents.add(Integer.parseInt(st.nextToken()));
}
        }
        for(int i=1; i<=fileContents.get(0); i++){
            for(int j = 1; j<=fileContents.get(0); j++){
                for(int k = 1; k<=fileContents.get(0); k++){
                 if(withinRange(i,j,k)){
                     count++;
                 }   
                }
            }
        }
        pw.println(count);
        pw.close();
    }
    
    public static boolean withinRange(int x, int y, int z){
        return (check(x, 1) && check(y, 2) && check(z, 3)) || (check(x, 4) && check(y, 5) && check(z, 6));
    }
    
    public static boolean check(int x, int i){
        return (Math.abs(x - fileContents.get(i)) == fileContents.get(0)-2) || (Math.abs(x - fileContents.get(i)) == fileContents.get(0)-1)
                || (Math.abs(x - fileContents.get(i)) == 0) || (Math.abs(x - fileContents.get(i)) == 1) || (Math.abs(x - fileContents.get(i)) == 2);
    }
}
