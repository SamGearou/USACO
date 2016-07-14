 
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
PROG: ride
 */
public class ride {
    
    public static void main(String args[]) throws IOException {
        
 File in = new File("ride.in");
File out = new File("ride.out");
String line;
int first =1;
int first1=1;
in.createNewFile();
out.createNewFile();
ArrayList<Integer> answer = new ArrayList<Integer>();
ArrayList<Integer> answer1 = new ArrayList<Integer>();
BufferedReader br = new BufferedReader(new FileReader(in));
PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
List<String> myList = new ArrayList<String>();
while ((line = br.readLine()) != null) {
    StringTokenizer st = new StringTokenizer(line);
    myList.add(st.nextToken());
}
for(int i=0;i<myList.get(0).length();i++){
switch(myList.get(0).charAt(i)) {
    case 'A': answer.add(1);
         break;
    case 'B': answer.add(2);
        break;
    case 'C': answer.add(3);
        break;
    case 'D': answer.add(4);
        break;
    case 'E': answer.add(5);
        break;
    case 'F': answer.add(6);
        break;
    case 'G': answer.add(7);
        break;
    case 'H': answer.add(8);
        break;
    case 'I': answer.add(9);
        break;
    case 'J': answer.add(10);
        break;
    case 'K': answer.add(11);
        break;
    case 'L': answer.add(12);
        break;
    case 'M': answer.add(13);
        break;
    case 'N': answer.add(14);
        break;
    case 'O': answer.add(15);
        break;
    case 'P': answer.add(16);
        break;
    case 'Q': answer.add(17);
        break;
    case 'R': answer.add(18);
        break;
    case 'S': answer.add(19);
        break;
     case 'T': answer.add(20);
        break;
     case 'U': answer.add(21);
        break;
     case 'V': answer.add(22);
        break;
     case 'W': answer.add(23);
        break;
     case 'X': answer.add(24);
        break;
     case 'Y': answer.add(25);
        break;
     case 'Z': answer.add(26);
        break;
    
}
}
for(int l=0;l<answer.size();l++){
    first *= answer.get(l);
}
first=first % 47;

for(int i=0;i<myList.get(1).length();i++){
switch(myList.get(1).charAt(i)) {
    case 'A': answer1.add(1);
         break;
    case 'B': answer1.add(2);
        break;
    case 'C': answer1.add(3);
        break;
    case 'D': answer1.add(4);
        break;
    case 'E': answer1.add(5);
        break;
    case 'F': answer1.add(6);
        break;
    case 'G': answer1.add(7);
        break;
    case 'H': answer1.add(8);
        break;
    case 'I': answer1.add(9);
        break;
    case 'J': answer1.add(10);
        break;
    case 'K': answer1.add(11);
        break;
    case 'L': answer1.add(12);
        break;
    case 'M': answer1.add(13);
        break;
    case 'N': answer1.add(14);
        break;
    case 'O': answer1.add(15);
        break;
    case 'P': answer1.add(16);
        break;
    case 'Q': answer1.add(17);
        break;
    case 'R': answer1.add(18);
        break;
    case 'S': answer1.add(19);
        break;
     case 'T': answer1.add(20);
        break;
     case 'U': answer1.add(21);
        break;
     case 'V': answer1.add(22);
        break;
     case 'W': answer1.add(23);
        break;
     case 'X': answer1.add(24);
        break;
     case 'Y': answer1.add(25);
        break;
     case 'Z': answer1.add(26);
        break;
    
}
}
for(int l=0;l<answer1.size();l++){
    first1 *= answer1.get(l);
}
first1=first1 % 47;
if(first == first1)
    pw.write("GO\n");
else
    pw.write("STAY\n");
//pw.write(myList.get(0));
//pw.write(" " + myList.get(1));
pw.close();
}
}
