package Graphs;

import java.io.*;
import java.util.*;

public class TaskDominoPrincipleGraphs {

    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(){
            init();
        }

        public FastScanner(String name) {
            init(name);
        }

        public FastScanner(boolean isOnlineJudge){
            if(!isOnlineJudge || System.getProperty("ONLINE_JUDGE") != null){
                init();
            }else{
                init("input.txt");
            }
        }

        private void init(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private void init(String name){
            try {
                br = new BufferedReader(new FileReader(name));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public String nextToken(){
            while(st == null || !st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt(){
            return Integer.parseInt(nextToken());
        }

        public long nextLong(){
            return Long.parseLong(nextToken());
        }

        public double nextDouble(){
            return Double.parseDouble(nextToken());
        }

    }

    static ArrayList<ArrayList<Integer>> adj;
    static int maxTime;
    static int maxVertice;
    static int v;
    static int[] time;
    static int[] visited;
    static LinkedList<Integer> q;

    static void bfs(int start) {
        int localMaxTime = 0;
        Arrays.fill(visited, 0);
        visited[start] = 1;
        q.add(start);
        time[start] = 0;
        int count = 1;
        while (!q.isEmpty()) {
            int tmp = q.pop();
            for (int j : adj.get(tmp)) {
                if (visited[j] == 0) {
                    q.add(j);
                    time[j] = time[tmp] + 1;
                    count++;
                    visited[j] = 1;
                    if(time[j] >= localMaxTime) {
                        localMaxTime = time[j];
                    }
                }
            }
        }
        if(count == v) {
            if(localMaxTime >= maxTime) {
                maxTime = localMaxTime;
                maxVertice = start;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        maxVertice = 0;
        maxTime = -1;
        FastScanner fs = new FastScanner("inputs//input5graphs.txt");
        v = fs.nextInt();
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>(v));
        }
        int[] arr = new int[v];
        Arrays.fill(arr, 0);
        for (int i = 0; i < v; i++) {
            int l = fs.nextInt();
            ArrayList<Integer> tmp = adj.get(i);
            for (int j = 0; j < l; j++) {
                int next = fs.nextInt() - 1;
                tmp.add(next);
                arr[next] = arr[next] + 1;
            }
        }
        int count = 0;
        for(int i : arr) {
            if(i == 0) count++;
        }
        if(count > 2) {
            FileWriter fw = new FileWriter("outputs//output5graphs.txt");
            fw.write("impossible");
            fw.close();
        }
        else if (count == 1) {
            int j = 0;
            for (int i = 0; i < v; i++) {
                if (arr[i] == 0) {
                    j = i;
                    break;
                }
            }
            time = new int[v];
            visited = new int[v];
            q = new LinkedList<>();
            bfs(j);
            FileWriter fw = new FileWriter("outputs//output5graphs.txt");
            if(maxTime > -1) {
                fw.write(maxTime + "\n" + (maxVertice + 1));
            } else {
                fw.write("impossible");
            }
            fw.close();
        } else {
            time = new int[v];
            visited = new int[v];
            q = new LinkedList<>();
            for (int i = 0; i < v; i++) {
                bfs(i);
            }
            FileWriter fw = new FileWriter("outputs//output5graphs.txt");
            if (maxTime > -1) {
                fw.write(maxTime + "\n" + (maxVertice + 1));
            } else {
                fw.write("impossible");
            }
            fw.close();
        }
    }
}