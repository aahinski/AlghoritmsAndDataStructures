package DataStructures;

import java.io.*;
import java.util.Stack;

public class Task111DSMaze {
    static int n;
    static int m;
    static int[][] maze; // for inputs & outputs
    static int[][] labyrinth; // for keeping results of previous moves
    static int survivors;
    static Stack<Pair> stack;
    static boolean flag;
    static int k;
    static int[] hatches;

    public static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void goLeft(int j, Pair peek) {
        if (peek.x == (n - 1)) {
            for (int l = 0; l < k; l++) {
                if (peek.y == hatches[l]) {
                    flag = false;
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
    }

    public static void goDown(int j, Pair peek) {
        if (peek.x == (n - 1)) {
            for (int l = 0; l < k; l++) {
                if (peek.y == hatches[l]) {
                    flag = false;
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
    }

    public static void goRight(int j, Pair peek) {
        if (peek.x == (n - 1)) {
            for (int l = 0; l < k; l++) {
                if (peek.y == hatches[l]) {
                    flag = false;
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
    }

    public static void goUp(int j, Pair peek) {
        if (peek.x == (n - 1)) {
            for (int l = 0; l < k; l++) {
                if (peek.y == hatches[l]) {
                    flag = false;
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
    }

    public static void deadEnd(int j, Pair peek) {
        if (peek.x == (n - 1)) {
            boolean hatchFlag = false;
            for (int l = 0; l < k; l++) {
                if (peek.y == hatches[l]) {
                    hatchFlag = true;
                    flag = false;
                    while (!stack.empty()) {
                        maze[stack.peek().x][stack.peek().y] = j + 2;
                        stack.pop();
                    }
                    labyrinth[peek.x][peek.y] = 0;
                    survivors++;
                    break;
                }
            }
            if(!hatchFlag) {
                stack.pop();
                if (stack.empty())
                    flag = false;
            }
        } else {
            stack.pop();
            if (stack.empty())
                flag = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs//input111ds.txt"));
        String[] nmk = br.readLine().split(" ");
        n = Integer.parseInt(nmk[0]);
        m = Integer.parseInt(nmk[1]);
        k = Integer.parseInt(nmk[2]);
        int[] entrances = new int[k];
        String[] tmp = br.readLine().split(" ");
        for (int j = 0; j < k; j++)
            entrances[j] = Integer.parseInt(tmp[j]) - 1;
        hatches = new int[k];
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

        survivors = 0;
        for (int j = 0; j < k; j++) {
            stack = new Stack<>();
            if(labyrinth[0][entrances[j]] == 0) {
                stack.push(new Pair(0, entrances[j]));
                flag = true;
                int direction = 0;
                //down - 0
                //right - 1
                //up - 2
                //left - 3
                while (flag) {
                    Pair peek = new Pair(stack.peek().x, stack.peek().y);
                    labyrinth[peek.x][peek.y] = 1;
                    if(direction == 0) {
                        if (peek.y != 0 && labyrinth[peek.x][peek.y - 1] == 0) {
                            goLeft(j, peek);
                            direction = 3;
                        } else if (peek.x != (n - 1) && labyrinth[peek.x + 1][peek.y] == 0) {
                            goDown(j, peek);
                            direction = 0;
                        } else if (peek.y != (m - 1) && labyrinth[peek.x][peek.y + 1] == 0) {
                            goRight(j, peek);
                            direction = 1;
                        } else if (peek.x != 0 && labyrinth[peek.x - 1][peek.y] == 0) {
                            goUp(j, peek);
                            direction = 2;
                        } else {
                            deadEnd(j, peek);
                        }
                    } else if(direction == 1) {
                        if (peek.x != (n - 1) && labyrinth[peek.x + 1][peek.y] == 0) {
                            goDown(j, peek);
                            direction = 0;
                        } else if (peek.y != (m - 1) && labyrinth[peek.x][peek.y + 1] == 0) {
                            goRight(j, peek);
                            direction = 1;
                        } else if (peek.x != 0 && labyrinth[peek.x - 1][peek.y] == 0) {
                            goUp(j, peek);
                            direction = 2;
                        } else if (peek.y != 0 && labyrinth[peek.x][peek.y - 1] == 0) {
                            goLeft(j, peek);
                            direction = 3;
                        } else {
                            deadEnd(j, peek);
                        }
                    } else if(direction == 2) {
                        if (peek.y != (m - 1) && labyrinth[peek.x][peek.y + 1] == 0) {
                            goRight(j, peek);
                            direction = 1;
                        } else if (peek.x != 0 && labyrinth[peek.x - 1][peek.y] == 0) {
                            goUp(j, peek);
                            direction = 2;
                        } else if (peek.y != 0 && labyrinth[peek.x][peek.y - 1] == 0) {
                            goLeft(j, peek);
                            direction = 3;
                        } else if (peek.x != (n - 1) && labyrinth[peek.x + 1][peek.y] == 0) {
                            goDown(j, peek);
                            direction = 0;
                        } else {
                            deadEnd(j, peek);
                        }
                    } else {
                        if (peek.x != 0 && labyrinth[peek.x - 1][peek.y] == 0) {
                            goUp(j, peek);
                            direction = 2;
                        } else if (peek.y != 0 && labyrinth[peek.x][peek.y - 1] == 0) {
                            goLeft(j, peek);
                            direction = 3;
                        } else if (peek.x != (n - 1) && labyrinth[peek.x + 1][peek.y] == 0) {
                            goDown(j, peek);
                            direction = 0;
                        } else if (peek.y != (m - 1) && labyrinth[peek.x][peek.y + 1] == 0) {
                            goRight(j, peek);
                            direction = 1;
                        } else {
                            deadEnd(j, peek);
                        }
                    }
                }
            }
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
    }
}