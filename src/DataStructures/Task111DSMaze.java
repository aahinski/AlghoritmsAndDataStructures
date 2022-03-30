package DataStructures;

import java.io.*;
import java.util.Stack;

public class Task111DSMaze {
    static int n;
    static int m;
    static int[][] maze; // for inputs & outputs
    static int[][] labyrinth; // for keeping results of previous moves

    public static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        long time = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("inputs//input111ds.txt"));
        String[] nmk = br.readLine().split(" ");
        n = Integer.parseInt(nmk[0]);
        m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);
        int[] entrances = new int[k];
        String[] tmp = br.readLine().split(" ");
        for (int j = 0; j < k; j++)
            entrances[j] = Integer.parseInt(tmp[j]) - 1;
        int[] hatches = new int[k];
        tmp = br.readLine().split(" ");
        for (int j = 0; j < k; j++)
            hatches[j] = Integer.parseInt(tmp[j]) - 1;
        maze = new int[n][m];
        int i = 0;
        while (i < n) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < m; j++)
                maze[i][j] = Integer.parseInt(tmp[j]);
            i++;
        }
        labyrinth = new int[n][m];
        for (int j = 0; j < n; j++) {
            for (int l = 0; l < m; l++) {
                labyrinth[j][l] = maze[j][l];
            }
        }
        int[] hatchersLastUsers = new int[k];
        for(int hatcher : hatchersLastUsers)
            hatcher = -1;

        int survivors = 0;
        for (int j = 0; j < k; j++) {
            Stack<Pair> stack = new Stack<>();
            if(labyrinth[0][entrances[j]] == 0) {
                stack.push(new Pair(0, entrances[j]));
                boolean flag = true;
                while (flag) {
                    Pair peek = new Pair(stack.peek().x, stack.peek().y);
                    if (peek.y != 0 && labyrinth[peek.x][peek.y - 1] == 0) {
                        labyrinth[peek.x][peek.y] = 1;
                        if (peek.x == (n - 1)) {
                            for (int l = 0; l < k; l++) {
                                if (peek.y == hatches[l]) {
                                    flag = false;
                                    hatchersLastUsers[l] = (j + 2);
                                    stack.pop();
                                    while (!stack.empty()) {
                                        maze[stack.peek().x][stack.peek().y] = j + 2;
                                        stack.pop();
                                    }
                                    labyrinth[peek.x][peek.y] = 0;
                                    survivors++;
                                    break;
                                }
                            }
                        }
                        stack.push(new Pair(peek.x, peek.y - 1));
                    } else if (peek.x != (n - 1) && labyrinth[peek.x + 1][peek.y] == 0) {
                        labyrinth[stack.peek().x][stack.peek().y] = 1;
                        stack.push(new Pair(peek.x + 1, peek.y));
                        if (peek.x == (n - 1)) {
                            for (int l = 0; l < k; l++) {
                                if (peek.y == hatches[l]) {
                                    flag = false;
                                    hatchersLastUsers[l] = (j + 2);
                                    stack.pop();
                                    while (!stack.empty()) {
                                        maze[stack.peek().x][stack.peek().y] = j + 2;
                                        stack.pop();
                                    }
                                    labyrinth[peek.x][peek.y] = 0;
                                    survivors++;
                                    break;
                                }
                            }
                        }
                        stack.push(new Pair(peek.x + 1, peek.y));
                    } else if (peek.y != (m - 1) && labyrinth[peek.x][peek.y + 1] == 0) {
                        labyrinth[stack.peek().x][stack.peek().y] = 1;
                        if (peek.x == (n - 1)) {
                            for (int l = 0; l < k; l++) {
                                if (peek.y == hatches[l]) {
                                    flag = false;
                                    hatchersLastUsers[l] = (j + 2);
                                    stack.pop();
                                    while (!stack.empty()) {
                                        maze[stack.peek().x][stack.peek().y] = j + 2;
                                        stack.pop();
                                    }
                                    labyrinth[peek.x][peek.y] = 0;
                                    survivors++;
                                    break;
                                }
                            }
                        }
                        stack.push(new Pair(peek.x, peek.y + 1));
                    } else if (peek.x != 0 && labyrinth[peek.x - 1][peek.y] == 0) {
                        labyrinth[stack.peek().x][stack.peek().y] = 1;
                        if (peek.x == (n - 1)) {
                            for (int l = 0; l < k; l++) {
                                if (peek.y == hatches[l]) {
                                    flag = false;
                                    hatchersLastUsers[l] = (j + 2);
                                    stack.pop();
                                    while (!stack.empty()) {
                                        maze[stack.peek().x][stack.peek().y] = j + 2;
                                        stack.pop();
                                    }
                                    labyrinth[peek.x][peek.y] = 0;
                                    survivors++;
                                    break;
                                }
                            }
                        }
                        stack.push(new Pair(peek.x - 1, peek.y));
                    } else {
                        if (peek.x == (n - 1)) {
                            for (int l = 0; l < k; l++) {
                                if (peek.y == hatches[l]) {
                                    flag = false;
                                    hatchersLastUsers[l] = (j + 2);
                                    stack.pop();
                                    while (!stack.empty()) {
                                        maze[stack.peek().x][stack.peek().y] = j + 2;
                                        stack.pop();
                                    }
                                    labyrinth[peek.x][peek.y] = 0;
                                    survivors++;
                                    break;
                                }
                            }
                        } else {
                            stack.pop();
                            if (stack.empty())
                                flag = false;
                        }
                    }
                }
            }
        }

        for(int j = 0; j < k; j++) {
            if(hatchersLastUsers[j] != -1)
                maze[n - 1][hatches[j]] = hatchersLastUsers[j];
        }
        PrintWriter pw = new PrintWriter("outputs//output111ds.txt");
        pw.write(survivors + "\n");
        for(i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pw.write(maze[i][j] + " ");
            }
            pw.write("\n");
        }
        pw.close();
        System.out.println(System.currentTimeMillis() - time);
    }
}