
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
PROG: milk3
 */

public class milk3 {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    static ArrayList<String> validBuckets = new ArrayList<String>();
    static ArrayList<Integer> solutions = new ArrayList<Integer>();
    
    public static void main(String[] args) throws IOException{
        File in = new File("milk3.in");
        File out = new File("milk3.out");
        in.createNewFile();
        out.createNewFile();
        String line;
        String contents = "";
        String answer = "";
        int count = 0;
        int index = 0;
        BufferedReader br = new BufferedReader(new FileReader(in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        while ((line = br.readLine()) != null) {
       StringTokenizer st = new StringTokenizer(line);
       while(st.hasMoreTokens()){
       fileContents.add(Integer.parseInt(st.nextToken()));
}
        }
        contents+= "0" +  " " + "0" + " " + fileContents.get(fileContents.size()-1);
        generateValidBuckets(0, 0, fileContents.get(2));
        for(int i = 0; i<validBuckets.size(); i++){
            if(validBuckets.get(i).charAt(0) == '0'){
                while(index < validBuckets.get(i).length()){
                    if(validBuckets.get(i).charAt(index++) == ' '){
                        count++;
                    }
                    if(count == 2){
                        solutions.add(Integer.parseInt(validBuckets.get(i).substring(index)));
                        break;
                    }
                }
            }
            count = 0;
            index = 0;
        }
        Collections.sort(solutions);
        for(int i = 0; i<solutions.size(); i++){
            if(i != solutions.size()-1){
           answer+= String.valueOf(solutions.get(i)) + " ";
        }
            else{
                answer+= String.valueOf(solutions.get(i));
            }
        }
        pw.println(answer);
        pw.close();
    }
    
    public static void generateValidBuckets(int a, int b, int c){
        //lose = max
        //gain = min
        if(!validBuckets.contains(String.valueOf(a) + " " + String.valueOf(b) + " " + String.valueOf(c))){
            validBuckets.add(String.valueOf(a) + " " + String.valueOf(b) + " " + String.valueOf(c));
            
            generateValidBuckets(Math.max(a+b-fileContents.get(1), 0), Math.min(a+b, fileContents.get(1)), c); //from a to b
            generateValidBuckets(Math.max(a+c-fileContents.get(2), 0), b, Math.min(a+c, fileContents.get(2))); //from a to c
            generateValidBuckets(Math.min(b+a, fileContents.get(0)), Math.max(b+a-fileContents.get(0), 0), c); //from b to a
            generateValidBuckets(a, Math.max(b+c-fileContents.get(2), 0), Math.min(b+c, fileContents.get(2))); //from b to c
            generateValidBuckets(Math.min(c+a, fileContents.get(0)), b, Math.max(c+a-fileContents.get(0), 0)); //from c to a
            generateValidBuckets(a, Math.min(c+b, fileContents.get(1)), Math.max(c+b-fileContents.get(1), 0)); //from c to b
        }
    }
}
