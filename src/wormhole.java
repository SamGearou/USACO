
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
PROG: wormhole
 */

//DOES NOT WORK!! CANT REALLY FIGURE OUT WHAT THE PROBLEM IS ASKING
public class wormhole {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    static ArrayList<String> StringContents = new ArrayList<String>();
    
    public static void main(String args[]) throws IOException{
        File in = new File("wormhole.in");
        File out = new File("wormhole.out");
        in.createNewFile();
        out.createNewFile();
        int count = 0;
        BufferedReader br = new BufferedReader(new FileReader(in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        String line;
        while((line = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line);
            while(st.hasMoreTokens()){
             fileContents.add(Integer.parseInt(st.nextToken()));
            }
        }
        for(int i=1; i<fileContents.size()-1; i+=2){
            StringContents.add(String.valueOf(fileContents.get(i)) + String.valueOf(fileContents.get(i+1)));
        }
        int index = 0;
        for(int i=1; i<fileContents.size()-1; i+=2){
            count+= checkWormHole(fileContents.get(i), fileContents.get(i+1), index);
            index++;
        }
        pw.println(count);
        pw.close();
    }
    public static int checkWormHole(int x, int y, int index){//index should be the index of StringContents!
        ArrayList<String> copy = new ArrayList<String>();
        for(int i = 0; i<StringContents.size(); i++){
            copy.add(StringContents.get(i));
        }
        copy.remove(index);
        int count = 0;
        String test = String.valueOf(x) + String.valueOf(y);
        while(x <= largestX(fileContents)){
            if(copy.contains(test)){
                count++;
            }
            x++;
            test = String.valueOf(x) + String.valueOf(y);
        }
        return count;
    }
    
    public static int largestX(ArrayList<Integer> contents){
         int largest= 0;
         for(int i = 1; i<contents.size()-1; i+=2){
             if(largest < contents.get(i)){
                 largest = contents.get(i);
             }
         }
         return largest;
    }
}
