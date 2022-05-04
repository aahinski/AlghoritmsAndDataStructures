package Graphs;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TaskDominoPrincipleGraphs {
    static int V;
    static boolean possible;

    static class Graph {
        private int V;                              //number of nodes in the graph
        private LinkedList<Integer> adj[];              //adjacency list
        private Queue<Integer> queue;                   //maintaining a queue

        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
            queue = new LinkedList<Integer>();
        }


        void addEdge(int v, int w) {
            adj[v].add(w);                          //adding an edge to the adjacency list (edges are bidirectional in this example)
        }

        int BFS(int n) {
            int time = - 1;
            int count = 0;
            boolean nodes[] = new boolean[V];       //initialize boolean array for holding the data
            int a = 0;

            nodes[n] = true;
            queue.add(n);                   //root node is added to the top of the queue

            while (queue.size() != 0) {
                n = queue.poll();             //remove the top element of the queue

                for (int i = 0; i < adj[n].size(); i++)  //iterate through the linked list and push all neighbors into queue
                {
                    a = adj[n].get(i);
                    if (!nodes[a])                    //only insert nodes into queue if they have not been explored already
                    {
                        nodes[a] = true;
                        queue.add(a);
                        count++;
                        time++;
                    }
                }
            }
            if(count == V - 1) possible = true;
            return time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs//input5graphs.txt"));
        possible = false;
        V = Integer.parseInt(br.readLine());
        Graph g = new Graph(V);
        for (int i = 0; i < V; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 1; j < tmp.length; j++) {
                g.addEdge(i, Integer.parseInt(tmp[j]) - 1);
            }
        }
        int vertice = 0;
        int maxTime = 0;
        for (int i = 0; i < V; i++) {
            int time = g.BFS(i);
            if(time > maxTime) {
                maxTime = time;
                vertice = i;
            }
        }
        FileWriter fw = new FileWriter(new File("outputs//output5graphs.txt"));
        if(possible) {
            fw.write(maxTime + "\n" + (vertice + 1));
        } else {
            fw.write("impossible");
        }
        fw.close();
    }
}