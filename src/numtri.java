
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
PROG: numtri
 */

public class numtri {
    static ArrayList<Integer> fileContents = new ArrayList<Integer>();
    static Tree<Integer> tree;
    static int count = 0;
    static long memoization[];
    static long max;
    public static void main(String[] args) throws IOException{
        File in = new File("numtri.in");
        File out = new File("numtri.out");
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
        fileContents.remove(0);
        memoization = new long[1000000];
        for(int i = 0; i<memoization.length; i++){
            memoization[i] = -1;
        }
        long answer = findLargest(0, 0);
        System.out.println(answer);
        pw.println(answer);
        pw.close();
        
        //works for test cases 1-5, used up too much memory on test 6!
//        tree = new Tree<Integer>(fileContents.get(0));
//        generateTree(tree.getRoot(), 0, 1, 0);
//        long answer = findLargestPath(tree.getRoot());
//        pw.println(answer);
//        pw.close();
        
    }
    
    public static long findLargest(int index, int offset){
        if(index >= fileContents.size()){
            return 0;
        }
        if(memoization[index] != -1){
            return memoization[index];
        }
        return memoization[index] = fileContents.get(index) + Math.max(findLargest(index+1 + offset, offset+1), findLargest(index+2 + offset, offset+1));
    }
    
    public static void generateTree(Node<Integer> current, int skip, int index, int offset){// pass in root, 0, and 1 initially
            if(index+1 + skip < fileContents.size()){
            current.addChild(new Node<Integer>(fileContents.get(index + skip)));
            current.addChild(new Node<Integer>(fileContents.get(index+1 + skip)));
            Node<Integer> newCurrent;
            for(int i = 0; i<current.getChildren().size(); i++){
                newCurrent = current.getChildren().get(i);
                generateTree(newCurrent, skip+1, ++index + offset, offset+1);
            }
            }
            
    }
    
    public static long findLargestPath(Node<Integer> current){
        if(current.getChildren().isEmpty()){
            return current.getData();
        }
        long maxPath = Math.max(current.getData() + findLargestPath(current.getChildren().get(0)), current.getData() + findLargestPath(current.getChildren().get(1)));
        return maxPath;
    }
}

class Tree<T> {
    private Node<T> root;
    
    public Tree(T data){
        this.root = new Node<T>(data);
    }
    
    public Node<T> getRoot(){
        return root;
    }
    
    public static int size(Node<Integer> current){
        if(current.getChildren().isEmpty()){
            return 1;
        }
        return 1 + size(current.getChildren().get(0)) + size(current.getChildren().get(1));
    }
    
    public static void inOrderTraversal(Node<Integer> root){
        if(root != null){
            if(!root.getChildren().isEmpty()){
            inOrderTraversal(root.getChildren().get(0));
            }
            System.out.println(root.getData());
            if(!root.getChildren().isEmpty()){
            inOrderTraversal(root.getChildren().get(1));
        }
        }
    }
}

class Node<T> {
    private T data;
    private Node<T> parent;
    private ArrayList<Node<T>> children;
    
    public Node(T data){
        this.data = data;
        this.parent = null;
        this.children = new ArrayList<Node<T>>();
    }
    
    public Node(){
        this.data = null;
        this.parent = null;
        this.children = new ArrayList<Node<T>>();
    }
    
    public void addChild(Node child){
        children.add(child);
    }
    
    public ArrayList<Node<T>> getChildren(){
        return children;
    }
    
    public Node<T> getParent(){
        return parent;
    }
    
    public void setData(T data){
        this.data = data;
    }
    
    public T getData(){
        return data;
    }
}
