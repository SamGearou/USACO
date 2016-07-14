/*
ID: samgear2
TASK: test
LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class test {

    public static void main(String[] args) throws FileNotFoundException, IOException {
BufferedReader br = new BufferedReader(new FileReader("test.in"));
PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
StringTokenizer st = new StringTokenizer(br.readLine());
int i1 = Integer.parseInt(st.nextToken());
int i2 = Integer.parseInt(st.nextToken());
pw.println(i1 + i2);
pw.close();
System.exit(0);
//input file: two numbers 
//output file: prints out the sum of the two numbers
    }
}

