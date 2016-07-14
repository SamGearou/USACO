
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
ID: samgear2
LANG: JAVA
PROG: gift1
 */
public class gift1 {
    static List<String> myList;
    static List<String> myList2;
    static List<String> myList3;
    static List<String> myList4;
    static List<String> myList5;
    static int sum=0;
     public static void main(String[] args) throws IOException{
File in = new File("gift1.in");
File out = new File("gift1.out");
String line;
in.createNewFile();
out.createNewFile();
myList = new ArrayList<String>();//all of the contents of gift1.in file
myList2 = new ArrayList<String>();//all the names of the people in the gift1.in file
myList3 = new ArrayList<String>();//all of the numbers
myList4= new ArrayList<String>();
myList5= new ArrayList<String>();
BufferedReader br = new BufferedReader(new FileReader(in));
PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
while ((line = br.readLine()) != null) {
    StringTokenizer st = new StringTokenizer(line);
    while(st.hasMoreTokens())
    myList.add(st.nextToken());
}
for(int i=0;i<myList.size();i++){
    myList4.add(myList.get(i));
}
for(int i=1;i<Integer.parseInt(myList.get(0))+1; i++){
    myList2.add(myList.get(i));
}
for(int i=Integer.parseInt(myList.get(0)) + 1;i<myList.size();i++){
   myList3.add(myList.get(i));
}
outer: for(int i=0;i<myList3.size();i++){
 for(int j=0; j<myList2.size(); j++){
        if(!myList3.get(i).equals(myList2.get(j))){
            
        }
        else{
            myList3.remove(i);//elements get shifted when removed
            i=i-1;
            continue outer;
        }
    }
}
for(String x:myList3){
    System.out.println(x);
}
for(int i=0;i<myList3.size()-1;i+=2){
    for(int j=Integer.parseInt(myList.get(0))+1; j<myList.size()-1; j++){
         if(myList3.get(i).equals(myList.get(j)) && Integer.parseInt(myList3.get(i+1)) != 0 && 
                 myList3.get(i+1).equals(myList.get(j+1))){
          myList4.set(j-1, Integer.toString(Integer.parseInt(myList3.get(i))%Integer.parseInt(myList3.get(i+1)) + 
                 -Integer.parseInt(myList3.get(i))));
        }
         else if(myList3.get(i).equals(myList.get(j)) && Integer.parseInt(myList3.get(i+1)) == 0){
          myList4.set(j-1, Integer.toString(-Integer.parseInt(myList3.get(i))));
    }
}
}
for(int i=1;i<myList3.size();i+=2){
    for(int j=Integer.parseInt(myList.get(0))+1; j<myList.size(); j++){
         if(myList3.get(i).equals(myList.get(j)) && Integer.parseInt(myList3.get(i)) != 0){
    for(int s=1; s<Integer.parseInt(myList3.get(i))+1; s++){
        myList4.set(s+j, Integer.toString(Integer.parseInt(myList.get(j-1))/Integer.parseInt(myList3.get(i))));
    }
        }
    }
}
for(int i=1;i<Integer.parseInt(myList.get(0))+1;i++){
    myList.set(i, "Tennis");
}
for(int i=0;i<myList2.size();i++){
    for(int j=Integer.parseInt(myList.get(0))+1;j<myList.size();j++){
        if(myList.get(j).equals(myList2.get(i))){
            sum+=Integer.parseInt(myList4.get(j));
        }
    }
    myList5.add(Integer.toString(sum));
    sum=0;  
}
for(int i=0;i<myList2.size();i++){
    pw.write(myList2.get(i) +  " " + myList5.get(i));
    pw.println();
}
pw.close();
System.exit(0);
}
}
