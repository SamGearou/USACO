
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
PROG: preface
 */

public class preface {
  static ArrayList<Integer> fileContents = new ArrayList<Integer>();
  static String[] numerals = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
  static int[] numbers = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
  static int[] numeralCount = new int[1001];
    public static void main(String[] args) throws IOException{
        File in = new File("preface.in");
        File out = new File("preface.out");
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
        numeralCount[1] = 0;
        numeralCount[4] = 0;//special
        numeralCount[5] = 0;
        numeralCount[9] = 0;//special
        numeralCount[10] = 0;
        numeralCount[40] = 0;//special
        numeralCount[50] = 0;
        numeralCount[90] = 0;//special
        numeralCount[100] = 0;
        numeralCount[400] = 0;//special
        numeralCount[500] = 0;
        numeralCount[900] = 0;//special
        numeralCount[1000] = 0;
        for(int i= fileContents.get(0); i>0; i--){
            createRomanNumeral(i);
        }
        for(int i = 0; i<numbers.length; i++){
            if(numeralCount[numbers[i]] != 0){
                pw.print(numerals[i] + " " + numeralCount[numbers[i]]);
                pw.println();
            }
        }
        pw.close();
    }
    
    public static String createRomanNumeral(int x){
        int index = 0;
        String numeral = "";
           while(x != 0){
               if(x / numbers[index] > 1 && index != numbers.length-1){
                   index++;
               }
               else if(x == numbers[index]){
                 updateCounts(index);
                 numeral+= numerals[index]; 
                 x = x - numbers[index];
                 index = 0;
               }
               else if(x / numbers[index] == 0){
                 updateCounts(index-1);
                 numeral+= numerals[index-1]; 
                 x = x - numbers[index-1];
                 index = 0;
               }
               else if(index == numbers.length-1){
                 updateCounts(index);
                 numeral+= numerals[index]; 
                 x = x - numbers[index];
                 index = 0; 
               }
               else{
                   index++;
               }
           } 
        return numeral;
    }
    
    public static void updateCounts(int index){
          if(index == 1){
              numeralCount[1]++;
              numeralCount[5]++;
          }
          else if(index == 3){
              numeralCount[1]++;
              numeralCount[10]++;
          }
          else if(index == 5){
              numeralCount[10]++;
              numeralCount[50]++;
          }
          else if(index == 7){
              numeralCount[10]++;
              numeralCount[100]++;
          }
          else if(index == 9){
              numeralCount[100]++;
              numeralCount[500]++;
          }
          else if(index == 11){
              numeralCount[100]++;
              numeralCount[1000]++;
          }
          else{
              numeralCount[numbers[index]]++;
          }
    }
}
