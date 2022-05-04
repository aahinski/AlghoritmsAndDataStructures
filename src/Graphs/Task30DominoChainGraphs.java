package Graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Task30DominoChainGraphs {
    static boolean[] visited = new boolean[7];
    static int[][] matrix = new int[7][7];

    public static void DFS(int v){
        visited[v] = true;
        for(int i = 0; i < matrix[v].length; i++){
            int tmp = matrix[v][i];
            if(tmp == 1)
                if (!visited[i])
                    DFS(i);
        }
    }

    public static boolean isEulersGraph(int[] vertices){
        Arrays.fill(visited, false);
        for(int v = 0; v < 7; v++) {
            if(!visited[v] && vertices[v] != 0) {
                DFS(v);
                break;
            }
        }
        for(int i = 0; i < 7; i++) {
            if(!visited[i] && vertices[i] > 0)
                return false;
        }
        for(int i = 0; i < 7; i++) {
            if(vertices[i] % 2 == 1)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs//input30graphs.txt"));
        int[] vertices = new int[7];
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String[] domino = sc.nextLine().split(" ");
            matrix[Integer.parseInt(domino[0])][Integer.parseInt(domino[1])] = 1;
            matrix[Integer.parseInt(domino[1])][Integer.parseInt(domino[0])] = 1;
            vertices[Integer.parseInt(domino[0])]++;
            vertices[Integer.parseInt(domino[1])]++;
        }
        FileWriter fw = new FileWriter(new File("outputs//output30graphs.txt"));
        if(isEulersGraph(vertices)) {
            fw.write("Yes");
        } else {
            fw.write("No");
        }
        fw.close();
    }
}
