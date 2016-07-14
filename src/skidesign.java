
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
PROG: skidesign
 */

public class skidesign {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    
public static void main(String args[]) throws IOException{
        File in = new File("skidesign.in");
        File out = new File("skidesign.out");
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
        fileContents.remove(0);
        Collections.sort(fileContents);
        pw.println(calculate());
        pw.close();
}

public static int calculate(){
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int sum = 0;
    int finalSum = -1;
    int bestValue = 0;
    int smallest = fileContents.get(0); //6 initially
    int largest = fileContents.get(fileContents.size()-1); //35 initially
    int swing = largest - smallest - 17; //12 initially
   ArrayList<Integer> copy = new ArrayList<Integer>();
   for(int i = 0; i<fileContents.size(); i++){
    copy.add(fileContents.get(i));
   }
   for(int i = 1; i<=swing; i++){
       for(int j = 0; j<copy.size(); j++){
           if(copy.get(j) == smallest){
              sum+= (Math.pow(copy.get(j) + i - fileContents.get(j), 2)); 
              copy.set(j, copy.get(j) + i);
              int value = copy.get(j);
              Collections.sort(copy);
              int index = 0;
              while(index < copy.size()){
                  if(copy.get(index) < value){
                      sum+= (Math.pow(value - copy.get(index), 2));
                      copy.set(index, copy.get(index) + (value - copy.get(index)));
                  }
                  index++;
              }
           }
           if(copy.get(j) == largest){
                sum+= (Math.pow(fileContents.get(j) - (copy.get(j) - (swing - i)), 2));
                copy.set(j, copy.get(j) - (swing - i));
                int value = copy.get(j);
                Collections.sort(copy);
                int index = 0;
                while(index < copy.size()){
                  if(copy.get(index) > value){
                      sum+= (Math.pow(copy.get(index) - value, 2));
                      copy.set(index, copy.get(index) - (copy.get(index) - value));
                  }
                  index++;
              }
            }
       }
      if(sum < finalSum || finalSum == -1){
            finalSum = sum; 
            bestValue = i;
        }
      answers.add(sum);
        sum = 0;
        //reset copy arraylist
        copy.clear();
        for(int k = 0; k<fileContents.size(); k++){
             copy.add(fileContents.get(k));
       }
   }
   //sort answers
   Collections.sort(answers);
   if(answers.size() > 0){
   return answers.get(0);
   }
   else{
       return 0;
   }
}
}
