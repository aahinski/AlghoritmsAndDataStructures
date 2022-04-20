package DataStructures;

import java.io.*;

public class Task01DSRoads {

    private static int[] parent;
    private static int[] size;

    public static int belong(int x){
        if(parent[x - 1] == x)
            return x;
        else{
            parent[x - 1] = belong(parent[x - 1]);
            return parent[x - 1];
        }
    }
    public static boolean unite(int x, int y){
        x = belong(x);
        y = belong(y);
        if(x != y) {
            if (size[x - 1] > size[y - 1]) {
                parent[y - 1] = x;
                size[x - 1] += size[y - 1];
            }
            else{
                parent[x - 1] = y;
                size[y - 1] += size[x - 1];
            }
            return true;
        }
        else return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs//input01ds.txt"));
        String[] nq = br.readLine().split(" ");
        int n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);
        parent = new int[n];
        size = new int[n];
        int numberOfComponents = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i + 1;
            size[i] = 1;
        }
        FileWriter fw = new FileWriter(new File("outputs//output01ds.txt"));
        for(int i = 0; i < q; i++) {
            String[] tmp = br.readLine().split(" ");
            int verticeX = Integer.parseInt(tmp[0]);
            int verticeY = Integer.parseInt(tmp[1]);
            if(unite(verticeX, verticeY)) numberOfComponents--;
            fw.write(numberOfComponents + "\n");
        }
        fw.close();
    }
}