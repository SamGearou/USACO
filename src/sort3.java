
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
ID: samgear2
LANG: JAVA
PROG: sort3
 */

public class sort3 {
    static int[] fileContents = new int[1000];
    static int[] sorted = new int[1000];
    static int index = 0;
    static int answer = 0;
    static int size = 0;
    static int ones = 0;
    static int twos;
    static int w = -1;
    static int v = -1;
    public static void main(String[] args) throws IOException{
        File in = new File("sort3.in");
        File out = new File("sort3.out");
        in.createNewFile();
        out.createNewFile();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        size = Integer.parseInt(br.readLine());
        while ((line = br.readLine()) != null) {
       StringTokenizer st = new StringTokenizer(line);
       while(st.hasMoreTokens()){
       fileContents[index++] = Integer.parseInt(st.nextToken());
}
        }
        for(int i = 0; i<size(); i++){
            if(fileContents[i] == 1){
                ones++;
            }
            if(fileContents[i] == 2){
                twos++;
            }
        }
        for(int i = ones; i<size(); i++){
            for(int j = 0; j<ones; j++){
                if(fileContents[i] == 1 && fileContents[j] == 2){
                   w = i;
                   v = j;
                   break;
                }
            }
            if(w == -1 && v == -1){
                for(int j = 0; j<ones; j++){
                if(fileContents[i] == 1 && fileContents[j] == 3){
                   w = i;
                   v = j;
                   break;
                }
            }
            }
            if(w !=-1 && v != -1){
                answer++;
            int temp = fileContents[i];
            fileContents[i] = fileContents[v];
            fileContents[v] = temp;
            w = -1;
            v = -1;
        }
        }
        for(int i = ones + twos; i<size(); i++){
            if(fileContents[i] == 2){
                answer++;
            }
        }
        System.out.println(answer);
        pw.println(answer);
        pw.close();
    }
    
    public static int size(){
        return size;
}
}
