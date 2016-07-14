
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
PROG: holstein
 */

public class holstein {
    static ArrayList<ArrayList<Integer>> fileContents = new ArrayList<ArrayList<Integer>>();
    static int[] vitamins;
    static int minimumScoops = 10000;
    static int feedSum = 10000;
    static ArrayList<String> finalAnswers = new ArrayList<String>();
    public static void main(String[] args) throws IOException{
       File in = new File("holstein.in");
        File out = new File("holstein.out");
        int index = 0;
        in.createNewFile();
        out.createNewFile();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        while ((line = br.readLine()) != null) {
       StringTokenizer st = new StringTokenizer(line);
       fileContents.add(new ArrayList<Integer>());
       while(st.hasMoreTokens()){
       fileContents.get(index).add(Integer.parseInt(st.nextToken()));
}
       index++;
        }
        vitamins = new int[fileContents.get(0).get(0)];
        for(int i = 0; i<fileContents.get(1).size(); i++){
            vitamins[i] = fileContents.get(1).get(i);
        }
        int[] daily = new int[vitamins.length];
        int scoops = 1;
        int current = 3; 
        ArrayList<Integer> feeds = new ArrayList<Integer>();
        for(int i = 3; i<fileContents.get(2).get(0)+3; i++){
            feeds.add(i-2);
            generateSmallestScoops(current, daily, scoops, feeds);
             reset(daily);
            feeds.clear();
            current++;
        }
          pw.println(finalAnswers.get(0));
          pw.close();
//        for(int i = 0; i<finalAnswers.size(); i++){
//            System.out.println(finalAnswers.get(i));
//        }
    }
    
    public static void generateSmallestScoops(int current, int[] daily, int scoops, ArrayList<Integer> feeds){
        for(int j = 0; j<daily.length; j++){
                    daily[j] = daily[j] + fileContents.get(current).get(j);
                }
        String answer = "";
            if(solution(daily, vitamins) && scoops <= minimumScoops){
               if(scoops < minimumScoops){ //new optimal solution
                   finalAnswers.clear();
                   minimumScoops = scoops;
                   feedSum = sumOfFeeds(feeds);
               }
               else if(scoops == minimumScoops){
                   if(sumOfFeeds(feeds) < feedSum){ //new optimal solution
                    finalAnswers.clear();
                   minimumScoops = scoops;
                   feedSum = sumOfFeeds(feeds);   
                   }
                   else if(sumOfFeeds(feeds) > feedSum){ //worse solution, return
                      return; 
                   }
               }
                answer+= String.valueOf(scoops) + " ";
                for(int i = 0; i<feeds.size(); i++){
                    if(i < feeds.size()-1){
                  answer+= String.valueOf(feeds.get(i)) + " ";
            }
                    else if(i == feeds.size()-1){
                       answer+= String.valueOf(feeds.get(i)); 
                    }
                }
                finalAnswers.add(answer);
                return;
        }
            
            for(int i = current+1; i<fileContents.get(2).get(0)+3; i++){
                feeds.add(i-2);
                generateSmallestScoops(i, daily, scoops+1, feeds); //reset daily array!!
                for(int j = 0; j<daily.length; j++){
                    daily[j] = daily[j] - fileContents.get(i).get(j);
                }
                feeds.remove(feeds.size()-1);
            }
    }
    
    public static boolean solution(int[] one, int[] two){
        for(int i = 0; i<one.length; i++){
            if(one[i] < two[i]){
                return false;
            }
        }
        return true;
    }
    
    public static void reset(int[] arr){
        for(int i = 0; i<arr.length; i++){
            arr[i] = 0;
        }
    }
    
    public static int sumOfFeeds(ArrayList<Integer> feeds){
        int sum = 0;
        for(int i = 0; i<feeds.size(); i++){
          sum+=feeds.get(i);
        }
        return sum;
    }
}
