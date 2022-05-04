package Graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Task08Graphs {
    static int mark;

    public static void main(String[] args) throws IOException {
        mark = 1;
        Scanner sc = new Scanner(new File("inputs//input08graphs.txt"));
        int v = Integer.parseInt(sc.nextLine());
        int[][] adj = new int[v][v];
        for (int i = 0; i < v; i++) {
            String[] tmp = sc.nextLine().split(" ");
            for (int j = 0; j < v; j++) {
                adj[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        int[] marks = new int[v];
        Arrays.fill(marks, 0);

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (marks[i] == 0) {
                marks[i] = mark++;
                q.add(i);
            }
            while (!q.isEmpty()) {
                int tmp = ((LinkedList<Integer>) q).pop();
                for (int j = 0; j < adj[i].length; j++) {
                    if (adj[tmp][j] == 1 && marks[j] == 0) {
                        q.add(j);
                        marks[j] = mark++;
                    }
                }
            }
        }

        FileWriter fw = new FileWriter(new File("outputs//output08graphs.txt"));
        for (int mark : marks) {
            fw.write(mark + " ");
        }
        fw.close();
    }
}