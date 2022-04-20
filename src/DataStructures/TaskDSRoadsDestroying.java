package DataStructures;

import java.io.*;
import java.util.Stack;

public class TaskDSRoadsDestroying {

    private static int[] parent;
    private static int[] size;
    private static int[][] roads;
    private static int[] earthquakes;
    private static int[] roadsState;

    public static int belong(int x) {
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
        BufferedReader br = new BufferedReader(new FileReader("inputs//inputdestroyds.txt"));
        String[] nmq = br.readLine().split(" ");
        int n = Integer.parseInt(nmq[0]);
        int m = Integer.parseInt(nmq[1]);
        int q = Integer.parseInt(nmq[2]);
        parent = new int[n];
        size = new int[n];
        int numberOfComponents = n;
        roads = new int[m][2];
        roadsState = new int[m];
        earthquakes = new int[q];
        for (int i = 0; i < n; i++) {
            parent[i] = i + 1;
            size[i] = 1;
        }
        for (int i = 0; i < m; i++) {
            String[] tmp = br.readLine().split(" ");
            roads[i][0] = Integer.parseInt(tmp[0]);
            roads[i][1] = Integer.parseInt(tmp[1]);
        }
        for (int i = 0; i < q; i++) {
            earthquakes[i] = Integer.parseInt(br.readLine());
            roadsState[earthquakes[i] - 1] = 1;
        }
        for (int i = 0; i < m; i++) {
            if(roadsState[i] == 0)
                if(unite(roads[i][0], roads[i][1])) numberOfComponents--;
        }
        FileWriter fw = new FileWriter(new File("outputs//outputdestroyds.txt"));
        Stack<Character> output = new Stack<>();
        for (int i = q - 1; i > -1; i--) {
            if(numberOfComponents > 1) {
                if (unite(roads[earthquakes[i] - 1][0], roads[earthquakes[i] - 1][1]))
                    numberOfComponents--;
                output.push('0');
            }
            else output.push('1');
        }
        while(!output.empty()) {
            fw.write(output.peek());
            output.pop();
        }
        fw.close();
    }
}
