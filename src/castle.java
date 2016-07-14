
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
PROG: castle
 */

public class castle {
  static ArrayList<Integer> fileContents = new ArrayList<Integer>();
  static String[] walls;
  static boolean[] explored;
  static int components = 0;
  static int largestRoom = 0;
  static int largestRoomRemovedWall = 0;
  static int optimalWall = 10000; //default value of 10,000
  static int optimalWallIndex = 0;
  static boolean EAST = false;
  static boolean NORTH = false;
  static int wallXCoord = 0;
  static int wallYCoord = 0;
    public static void main(String[] args) throws IOException{
        File in = new File("castle.in");
        File out = new File("castle.out");
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
        walls = new String[16];
        walls[0] = "";
        walls[1] = "w";
        walls[2] = "n";
        walls[4] = "e";
        walls[8] = "s";
        walls[3] = "n,w";
        walls[5] = "e,w";
        walls[9] = "s,w";
        walls[7] = "w,e,n";
        walls[13] = "w,e,s";
        walls[6] = "n,e";
        walls[10] = "n,s";
        walls[14] = "n,e,s";
        walls[12] = "e,s";
        walls[15] = "w,n,e,s";
        walls[11] = "w,n,s";
        explored = new boolean[fileContents.size()]; //default values are false
        //System.out.println(exploreCastle(2));
        for(int i = 2; i<explored.length; i++){
            if(!explored[i]){
                int x = exploreCastle(i);
                components++;
                if(largestRoom < x){
                    largestRoom = x;
                }
            }
        }
        removeWall();
        wallXCoord = (((optimalWallIndex - 2) % fileContents.get(0)) + 1);
        wallYCoord = (((optimalWallIndex-2) / fileContents.get(0)) + 1);
        pw.println(components);
        pw.println(largestRoom);
        pw.println(largestRoomRemovedWall);
        pw.println(wallYCoord + " " + wallXCoord + " " + ((EAST) ? "E" : "N"));
        pw.close();
    }
    
    public static void resetExplored(){
        for(int i = 0; i<explored.length; i++){
            explored[i] = false;
        }
    }
    
    public static boolean hasSpecifiedWall(String str, char wall){
        int index = 0;
        while(index < str.length()){
            if(str.charAt(index++) == wall){
            return true;
        }
        }
        return false;
    }
    
   public static int exploreCastle(int index){ //start at 2
       if(index >= fileContents.size() || index <= 1 || explored[index]){
           return 0;
       }
       explored[index] = true; //mark the current node as explored
     return 1 + ((index-1 > 1 && !hasSpecifiedWall(walls[fileContents.get(index)], 'w')) 
             ? exploreCastle(index-1) : 0) 
              + ((index+1 < fileContents.size() && !hasSpecifiedWall(walls[fileContents.get(index)], 'e')) 
             ? exploreCastle(index+1) : 0)
              + ((index + fileContents.get(0)) < fileContents.size() && !hasSpecifiedWall(walls[fileContents.get(index)], 's')
             ? exploreCastle(index + fileContents.get(0)) : 0)
              + ((index - fileContents.get(0)) > 1 && !hasSpecifiedWall(walls[fileContents.get(index)], 'n')
             ? exploreCastle(index - fileContents.get(0)) : 0);
}
   public static void removeWall(){//subtract 4 to erase east wall, 1 for west wall, 2 for north, 8 for south
       for(int i = 2; i<explored.length-1; i++){
           if( i % fileContents.get(0) != 1 && hasSpecifiedWall(walls[fileContents.get(i)], 'e') && hasSpecifiedWall(walls[fileContents.get(i+1)], 'w')){ //removes all horizontal walls
               resetExplored(); //reset the explored array to be all false values
              int originalF = fileContents.get(i);
              int originalS = fileContents.get(i+1);
              fileContents.set(i, fileContents.get(i) - 4); //erase the east wall;
              fileContents.set(i+1, fileContents.get(i+1) - 1); //erase west wall;
              int x = exploreCastle(i);
              if(largestRoomRemovedWall <= x){
                  int oldMax = largestRoomRemovedWall;
                  largestRoomRemovedWall = x;
                  if(((i - 2) % fileContents.get(0) <= optimalWall && i != optimalWallIndex) || oldMax < x){
                      optimalWall = (i-2) % fileContents.get(0);
                      optimalWallIndex = i;
                      EAST = true;
                      NORTH = false;
                  }
              }
              fileContents.set(i, originalF); //reset the east wall
              fileContents.set(i+1, originalS); //reset the west wall
           }
           if(i < fileContents.size() - fileContents.get(0) 
                   && hasSpecifiedWall(walls[fileContents.get(i)], 's') && hasSpecifiedWall(walls[fileContents.get(i+fileContents.get(0))], 'n')){
               resetExplored();
               int originalF = fileContents.get(i);
               int originalS = fileContents.get(i + fileContents.get(0));
               fileContents.set(i, fileContents.get(i) - 8); //erase the south wall;
               fileContents.set(i + fileContents.get(0), fileContents.get(i + fileContents.get(0)) - 2); //erase the north wall
               int x = exploreCastle(i);
               if(largestRoomRemovedWall <= x){
                   int oldMax = largestRoomRemovedWall;
                   largestRoomRemovedWall = x;
                   if((i - 2) % fileContents.get(0) <= optimalWall || oldMax < x){
                       optimalWall = (i-2) % fileContents.get(0);
                       optimalWallIndex = i + fileContents.get(0);
                       NORTH = true;
                       EAST = false;
                   }
               }
               fileContents.set(i, originalF); //reset the south wall
               fileContents.set(i + fileContents.get(0), originalS); //reset the north wall
           }
       }
   }
}
