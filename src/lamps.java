
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
PROG: lamps
 */

//TRICK FOR THIS PROBLEM: notice that there are only a maximum of 16 different configurations of lamps.
//Since there are 4 buttons, and each button mod 2 has only two possible values (0 or 1), 
//there are 2 to the power of 4 different configurations of lamps
public class lamps {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    static int B1 = 0;
    static int B2 = 0;
    static int B3 = 0;
    static int B4 = 0;
    static int onOff = 0;
    static boolean evenOn = false;
    static boolean evenOff = false;
    static boolean oddOn = false;
    static boolean oddOff = false;
    static boolean specialEvenOn = false;
    static boolean specialEvenOff = false;
    static boolean specialOddOn = false;
    static boolean specialOddOff = false;
    static ArrayList<String> answers = new ArrayList<String>();
    public static void main(String[] args) throws IOException{
        File in = new File("lamps.in");
        File out = new File("lamps.out");
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
        for(int i = 2; i<fileContents.size(); i++){
          if(fileContents.get(i) == -1){
              onOff++;
              continue;
          }
          if(fileContents.get(i) % 2 == 0 && onOff == 0){
              evenOn = true;
              if(special(fileContents.get(i))){
                  specialEvenOn = true;
                  evenOn = false;
              }
          }
          if(fileContents.get(i) % 2 == 0 && onOff == 1){
              evenOff = true;
              if(special(fileContents.get(i))){
                  specialEvenOff = true;
                  evenOff = false;
              }
          }
          if(fileContents.get(i) % 2 != 0 && onOff == 0){
              oddOn = true;
              if(special(fileContents.get(i))){
                  specialOddOn = true;
                  oddOn = false;
              }
          }
          if(fileContents.get(i) % 2 != 0 && onOff == 1){
              oddOff = true;
              if(special(fileContents.get(i))){
                  specialOddOff = true;
                  oddOff = false;
              }
          }
        }
        for(B1 = 0; B1<2; B1++){
            for(B2 = 0; B2<2; B2++){
                for(B3 = 0; B3<2; B3++){
                    for(B4 = 0; B4<2; B4++){
                        if(B1 + B2 + B3 + B4 <= fileContents.get(1)){
                        if(evenOn){
                            if((B1 + B3) % 2 != 0){
                                continue;
                            }
                        }
                        if(specialEvenOn){
                            if((B1 + B3 + B4) % 2 != 0){
                                continue;
                            }
                        }
                        if(evenOff){
                            if((B1 + B3) % 2 != 1){
                                continue;
                            }
                        }
                        if(specialEvenOff){
                            if((B1 + B3 + B4) % 2 != 1){
                                continue;
                            }
                        }
                        if(oddOn){
                            if((B1 + B2) % 2 != 0){
                                continue;
                            }
                        }
                        if(specialOddOn){
                            if((B1 + B2 + B4) % 2 != 0){
                                continue;
                            }
                        }
                        if(oddOff){
                            if((B1 + B2) % 2 != 1){
                                continue;
                            }
                        }
                        if(specialOddOff){
                            if((B1 + B2 + B4) % 2 != 1){
                                continue;
                            }
                        }
                        String computed = computeLamp(B1, B2, B3, B4);
                        answers.add(computed);
                }
                        }
                }
        }
    }
        Collections.sort(answers);
        for(int i = 0; i<answers.size(); i++){
            if(i != answers.size()-1 && !answers.get(i).equals(answers.get(i+1))){
            pw.println(answers.get(i));
        }
            if(i == answers.size()-1){
                pw.println(answers.get(i));
            }
        }
        if(answers.isEmpty()){
            pw.println("IMPOSSIBLE");
        }
        pw.close();
    }
    
    public static boolean special(int num){
        return (((num-1) / 3f) - ((num-1)/3) == 0);
    }
    
    public static String computeLamp(int b1, int b2, int b3, int b4){
        //System.out.println("B1 " + b1 + " B2 " + b2 + " B3 " + b3 + " B4 " + b4);
        String str = "";
        int index = 0;
        while(index < fileContents.get(0)){
            if(index % 2 != 0 && special(index+1)){ //even and special
                if((b1 + b3 + b4) % 2 != 0){
                    str+="0";
                }
                else{
                    str+="1";
                }
            }
            else if(index % 2 != 0 && !special(index+1)){ //even and not special
                if((b1 + b3) % 2 != 0){
                    str+="0";
                }
                else{
                    str+="1";
                }
            }
            if(index % 2 == 0 && special(index+1)){ //odd and special
                if((b1 + b2 + b4) % 2 != 0){
                    str+="0";
                }
                else{
                    str+="1";
                }
            }
            else if(index % 2 == 0 && !special(index+1)){ //odd and not special
                if((b1 + b2) % 2 != 0){
                    str+="0";
                }
                else{
                    str+="1";
                }
            }
            index++;
        }
        return str;
    }
}
